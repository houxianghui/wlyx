/*********************************************************
 * File:BasePO.java
 * 
 * Version 1.0
 * 
 * Date     2005-8-31
 * Author   辛勇
 * 
 * Copyright (C) 2005 huateng
 * all rights reserved.
 * 
 ********************************************************/

package com.eis.base;

import java.sql.*;
import java.util.*;
import java.lang.reflect.*;

import org.apache.log4j.Logger;

import com.eis.config.*;
import com.eis.util.*;
import com.eis.connectionPool.*;
import com.eis.portal.UserContext;
import com.eis.factory.*;

/**
 * 说明：数据库访问层的父类
 * 
 */

public abstract class BaseDAO {
	private static Logger logger = Logger.getLogger(BaseDAO.class);
	/**
	 * 数据库连接dbName
	 */
	private String dsName = null;

	/**
	 * 增加数据SQL
	 */
	private String insertSQL = null;

	/**
	 * 修改数据SQL
	 */
	private String updateSQL = null;

	/**
	 * 删除数据SQL
	 */
	private String deleteSQL = null;

	/**
	 * 单记录查询SQL
	 */
	private String querySQL = null;

	/**
	 * 列表查询SQL
	 */
	private String listSQL = null;

	/**
	 * 默认的排序条件，相当于SQL语句中的ORDER BY语句。
	 * 格式“ order by user_id desc ”,前面必须有空格。
	 */
	protected String orderBy = null;

	/**
	 * 构造函数
	 */
	public BaseDAO() {
		super();
		initSQL();

	}

	/**
	 * 构造函数
	 * @param dsName 数据源名字
	 */
	public BaseDAO(String dsName) {
		super();
		initSQL();

	}

	/**
	 * 初始化insertSQL和updateSQL
	 * 
	 */
	public abstract void initSQL();

	/**
	 * 返回插入SQL语句。 
	 *
	 * @return 插入SQL语句
	 */
	public String getInsertSQL() {
		return insertSQL;
	}

	/**
	 * 返回查询SQL语句。
	 * 
	 * @return 查询SQL语句
	 */
	public String getQuerySQL() {
		return querySQL;

	}

	/**
	 * 返回更新SQL语句。
	 * 
	 * @return 更新SQL语句
	 */
	public String getUpdateSQL() {
		return updateSQL;
	}

	/**
	 * 返回删除SQL语句
	 * @return 删除SQL语句
	 *
	 */
	public String getDeleteSQL() {
		return deleteSQL;
	}

	/**
	 * 返回列表查询SQL
	 * @return 列表查询SQL
	 */
	public String getListSQL() {
		return listSQL;
	}

	/**
	 * 返回排序子句
	 * @return 排序子句
	 */
	public String getOrderBy() {
		return orderBy;
	}

	/**
	 * 设置数据增加的PraparedStatement语句
	 * @param str - 增加数据的PraparedStatement语句
	 */
	protected void setInsertSQL(String str) {
		insertSQL = str;
	}

	/**
	 * 设置数据修改的PraparedStatement语句
	 * @param str - 修改数据的PraparedStatement语句
	 */
	protected void setUpdateSQL(String str) {
		updateSQL = str;
	}

	/**
	 * 设置删除数据SQL语句
	 * @param str - 数据删除SQL语句
	 */
	protected void setDeleteSQL(String str) {
		deleteSQL = str;
	}

	/**
	 * 设置列表查询的SQL语句
	 * @param str - 列表查询的SQL语句
	 */
	protected void setListSQL(String str) {
		listSQL = str;
	}

	/**
	 * 设置查询明细信息的SQL语句
	 * @param str - 查询明细信息的SQL语句
	 */
	protected void setQuerySQL(String str) {
		querySQL = str;
	}
	/**
	 * 获得数据库连接
	 * @return 数据库连接
	 * @throws Exception - 获得连接失败
	 */
	protected java.sql.Connection getConnection() throws Exception {
		Connection conn = null;
		try {
			conn =
				(dsName == null
					? (DBPoolManager.getConnection())
					: (DBPoolManager.getConnection(dsName)));
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error("BaseDAO获得数据库连接失败！");
			logger.error(ex.getMessage());

			BaseException e = new BaseException();
			e.setErrorCode("E010017");
			throw e;
		}
		return conn;
	}

	/**
	 * 获得结果集允许存放的最大纪录数量。
	 * @return 最大纪录数量 
	 */
	public int getMaxFetchRows() {
		return Integer.parseInt(SysConfig.getProperty("maxFetchRows"));
	}

	public BaseVO retrieve(String sql,Connection conn)throws Exception{
				
		Statement stmt = null;
		ResultSet rs = null;
		BaseVO vo = null;

		try {
			stmt =
				conn.createStatement(
					ResultSet.TYPE_FORWARD_ONLY,
					ResultSet.CONCUR_READ_ONLY);
					//查询数据
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				vo = detail(rs);
			}

			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			SysLog.getLogger().error("数据查询语句：" + sql);
			while (ex != null) {
				SysLog.getLogger().error("数据查询异常！");
				SysLog.getLogger().error("error code:" + ex.getErrorCode());
				SysLog.getLogger().error("SQL State:" + ex.getSQLState());
				SysLog.getLogger().error("异常信息:" + ex.getMessage());

				ex = ex.getNextException();
			}

			BaseException e = new BaseException();
			e.setErrorCode("E010016");
			throw e;

		} catch (Throwable t) {
			t.printStackTrace();

			SysLog.getLogger().error("BaseDAO 查询数据失败！");
			SysLog.getLogger().error(t.getMessage());
			BaseException e = new BaseException();
			e.setErrorCode("E010016");
			throw e;
		} 
		return vo;
	}
	/**
	 * 在数据表中查找指定的记录，返回明细信息的结果对象。用于单个对象的查询。
	 * 若结果为多个记录，只返回第一个记录，其余的将忽略。
	 * @param sql - 查询语句
	 * @return 结果数据对象 
	 * @throws Exception - 数据查询异常
	 * 
	 */
	public BaseVO retrieve(String sql) throws Exception {

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		BaseVO vo = null;

		try {
			conn = getConnection();
			stmt =
				conn.createStatement(
					ResultSet.TYPE_FORWARD_ONLY,
					ResultSet.CONCUR_READ_ONLY);
			//查询数据
			logger.debug(sql);
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				vo = detail(rs);
			}

			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			logger.error("数据查询语句：" + sql);
			while (ex != null) {
				logger.error("数据查询异常！");
				logger.error("error code:" + ex.getErrorCode());
				logger.error("SQL State:" + ex.getSQLState());
				logger.error("异常信息:" + ex.getMessage());

				ex = ex.getNextException();
			}

			BaseException e = new BaseException();
			e.setErrorCode("E010016");
			throw e;

		} catch (Throwable t) {
			t.printStackTrace();

			logger.error("BaseDAO 查询数据失败！");
			logger.error(t.getMessage());
			BaseException e = new BaseException();
			e.setErrorCode("E010016");
			throw e;
		} finally {
			try {
				//释放数据库连接
				if (conn != null) {
					conn.close();
				}
			} catch (Exception ex) {

				ex.printStackTrace();

				logger.error("BaseDAO 释放数据库连接失败！");
				logger.error(ex.getMessage());
				BaseException e = new BaseException();
				e.setErrorCode("E010019");
				throw e;
			}

		}
		return vo;

	}

	/**
	 * 在数据表中查找指定的记录，返回明细信息的结果对象。用于单个对象的查询。
	 * 若结果为多个记录，只返回第一个记录，其余的将忽略。
	 * @param sql - 查询语句
	 * @param method - 结果fetch方法名
	 * @return 结果数据对象 
	 * @throws Exception - 数据查询异常
	 * 
	 */
	public BaseVO retrieve(String sql, String method) throws Exception {

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		BaseVO vo = null;

		try {
			conn = getConnection();
			stmt =
				conn.createStatement(
					ResultSet.TYPE_FORWARD_ONLY,
					ResultSet.CONCUR_READ_ONLY);
			//查询数据
			logger.debug(sql);
			rs = stmt.executeQuery(sql);
			if (rs.next()) {

				Class[] cls = { ResultSet.class };
				Object[] param = { rs };
				Method mth = getClass().getDeclaredMethod(method, cls);

				vo = (BaseVO) mth.invoke(this, param);

			}

			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			logger.error("数据查询语句：" + sql);

			while (ex != null) {
				logger.error("数据查询异常！");
				logger.error("error code:" + ex.getErrorCode());
				logger.error("SQL State:" + ex.getSQLState());
				logger.error("异常信息:" + ex.getMessage());

				ex = ex.getNextException();
			}

			BaseException e = new BaseException();
			e.setErrorCode("E010016");
			throw e;

		} catch (Throwable t) {
			t.printStackTrace();

			logger.error("BaseDAO 查询数据失败！");
			logger.error(t.getMessage());
			BaseException e = new BaseException();
			e.setErrorCode("E010016");
			throw e;
		} finally {
			try {
				//释放数据库连接
				if (conn != null) {
					conn.close();
				}
			} catch (Exception ex) {
				ex.printStackTrace();

				logger.error("BaseDAO 释放数据库连接失败！");
				logger.error(ex.getMessage());
				BaseException e = new BaseException();
				e.setErrorCode("E010019");
				throw e;
			}

		}
		return vo;

	}

	/**
	 * 插入一条记录
	 * @param bean - 要插入的数据对象
	 * @throws Exception - 插入失败
	 */
	public int add(BaseVO vo) throws Exception {
		Connection conn = null;

		PreparedStatement ps = null;

		int row = 0;

		try {
			conn = getConnection();
			ps = conn.prepareStatement(getInsertSQL());
			logger.debug(getInsertSQL());

			//实例化动态SQL
			prepareInsertStatement(ps, vo);
			row = ps.executeUpdate();

			if (ps != null) {
				ps.close();
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			logger.error("数据库操作语句：" + ps.toString());

			while (ex != null) {
				logger.error("增加数据失败！");
				logger.error("error code:" + ex.getErrorCode());
				logger.error("SQL State:" + ex.getSQLState());
				logger.error("异常信息:" + ex.getMessage());

				ex = ex.getNextException();
			}

			BaseException e = new BaseException();
			e.setErrorCode("E010011");
			throw e;

		} catch (Throwable t) {
			t.printStackTrace();

			logger.error("增加数据失败！");
			logger.error(t.getMessage());
			BaseException e = new BaseException();
			e.setErrorCode("E010011");
			throw e;

		} finally {
			try {
				//释放数据库连接
				if (conn != null) {
					conn.close();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				logger.error("BaseDAO 释放数据库连接失败！");
				logger.error(ex.getMessage());
				BaseException e = new BaseException();
				e.setErrorCode("E010019");
				throw e;
			}

		}
		return row;
	}

	/**
	 * 通过传入的数据库连接插入一条记录，适用与事务操作
	 * @param bean - 要插入的数据对象
	 * @param conn - 数据库连接
	 * @throws Exception - 插入失败
	 */
	public int add(BaseVO vo, java.sql.Connection conn) throws Exception {

		PreparedStatement ps = null;

		int row = 0;

		try {
			ps = conn.prepareStatement(getInsertSQL());
			logger.debug(getInsertSQL());

			//实例化动态SQL
			prepareInsertStatement(ps, vo);
			row = ps.executeUpdate();

			if (ps != null) {
				ps.close();
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			logger.error("数据库操作语句：" + ps.toString());

			while (ex != null) {
				logger.error("增加数据失败！");
				logger.error("error code:" + ex.getErrorCode());
				logger.error("SQL State:" + ex.getSQLState());
				logger.error("异常信息:" + ex.getMessage());

				ex = ex.getNextException();
			}

			BaseException e = new BaseException();
			e.setErrorCode("E010011");
			throw e;

		} catch (Throwable t) {
			t.printStackTrace();

			logger.error("增加数据失败！");
			logger.error(t.getMessage());
			BaseException e = new BaseException();
			e.setErrorCode("E010011");
			throw e;

		}
		return row;
	}

	/**
	 * 批量插入记录
	 * @param list - 要插入的数据对象数组
	 * @throws Exception - 插入失败
	 */
	public int addList(BaseVO[] list) throws Exception {

		Connection conn = null;
		PreparedStatement ps = null;
		int row = 0;

		try {
			conn = getConnection();
			ps = conn.prepareStatement(getInsertSQL());

			for (int i = 0; i < list.length; i++) {
				//实例化动态SQL
				prepareInsertStatement(ps, list[i]);

				//添加批量处理
				ps.addBatch();

			}

			//执行数据库操作
			if (list.length >= 1) {

				int[] rows = ps.executeBatch();
				for (int j = 0; j < rows.length; j++) {
					row += rows[j];
				}
			}

			//关闭资源
			if (ps != null) {
				ps.close();
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			logger.error("数据库操作语句：" + ps.toString());

			while (ex != null) {
				logger.error("批量增加数据失败！");
				logger.error("error code:" + ex.getErrorCode());
				logger.error("SQL State:" + ex.getSQLState());
				logger.error("异常信息:" + ex.getMessage());

				ex = ex.getNextException();
			}

			BaseException e = new BaseException();
			e.setErrorCode("E010012");
			throw e;
		} catch (Throwable t) {
			t.printStackTrace();

			logger.error("批量增加数据失败！");
			logger.error(t.getMessage());
			BaseException e = new BaseException();
			e.setErrorCode("E010012");
			throw e;

		} finally {
			try {
				//释放数据库连接
				if (conn != null) {
					conn.close();
				}
			} catch (Exception ex) {
				ex.printStackTrace();

				logger.error("BaseDAO 释放数据库连接失败");
				logger.error(ex.getMessage());
				BaseException e = new BaseException();
				e.setErrorCode("E010019");
				throw e;
			}

		}
		return row;

	}

	/**
	 * 通过传入的数据库连接执行批量插入记录，适用于事务操作
	 * @param list - 要插入的数据对象数组
	 * @param conn - 数据库连接
	 * @throws Exception - 插入失败
	 */
	public int addList(BaseVO[] list, java.sql.Connection conn)
		throws Exception {

		PreparedStatement ps = null;
		int row = 0;

		try {
			ps = conn.prepareStatement(getInsertSQL());

			for (int i = 0; i < list.length; i++) {
				//实例化动态SQL
				prepareInsertStatement(ps, list[i]);

				//添加批量处理
				ps.addBatch();

			}

			//执行数据库操作
			if (list.length >= 1) {

				int[] rows = ps.executeBatch();
				for (int j = 0; j < rows.length; j++) {
					row += rows[j];
				}
			}

			//关闭资源
			if (ps != null) {
				ps.close();
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			logger.error("数据库操作语句：" + ps.toString());

			while (ex != null) {
				logger.error("批量增加数据失败！");
				logger.error("error code:" + ex.getErrorCode());
				logger.error("SQL State:" + ex.getSQLState());
				logger.error("异常信息:" + ex.getMessage());

				ex = ex.getNextException();
			}

			BaseException e = new BaseException();
			e.setErrorCode("E010012");
			throw e;
		} catch (Throwable t) {
			t.printStackTrace();

			logger.error("批量增加数据失败！");
			logger.error(t.getMessage());
			BaseException e = new BaseException();
			e.setErrorCode("E010012");
			throw e;

		}
		return 0;

	}

	/**
	 * 删除数据
	 * @param sql － 数据删除SQL语句
	 * @throws Exception - 删除失败
	 */
	public int delete(String sql) throws Exception {

		// 删除行数
		int row = 0;

		Connection conn = null;
		Statement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			//返回删除的行数
			logger.debug("删除语句：" + sql);
			row = stmt.executeUpdate(sql);

			if (stmt != null) {
				stmt.close();
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			logger.error("数据库操作语句：" + sql);

			while (ex != null) {
				logger.error("删除数据失败！");
				logger.error("error code:" + ex.getErrorCode());
				logger.error("SQL State:" + ex.getSQLState());
				logger.error("异常信息:" + ex.getMessage());

				ex = ex.getNextException();
			}

			BaseException e = new BaseException();
			e.setErrorCode("E010013");
			throw e;

		} catch (Throwable t) {
			t.printStackTrace();

			logger.error("删除数据失败！");
			logger.error(t.getMessage());
			BaseException e = new BaseException();
			e.setErrorCode("E010013");
			throw e;
		} finally {
			try {
				//释放数据库连接
				if (conn != null) {
					conn.close();
				}
			} catch (Exception ex) {
				ex.printStackTrace();

				logger.error("BaseDAO 释放数据库连接失败！");
				logger.error(ex.getMessage());
				BaseException e = new BaseException();
				e.setErrorCode("E010019");
				throw e;
			}

		}
		return row;
	}

	/**
	 * 通过传入的数据库连接执行删除数据,适用于事务操作
	 * @param sql － 数据删除SQL语句
	 * @param conn - 数据库连接
	 * @throws Exception - 删除失败
	 */
	public int delete(String sql, java.sql.Connection conn) throws Exception {

		// 删除行数
		int row = 0;

		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			//返回删除的行数
			logger.error("删除语句：" + sql);
			row = stmt.executeUpdate(sql);

			if (stmt != null) {
				stmt.close();
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			logger.error("数据删除语句：" + sql);

			while (ex != null) {
				logger.error("删除数据失败！");
				logger.error("error code:" + ex.getErrorCode());
				logger.error("SQL State:" + ex.getSQLState());
				logger.error("异常信息:" + ex.getMessage());

				ex = ex.getNextException();
			}

			BaseException e = new BaseException();
			e.setErrorCode("E010013");
			throw e;

		} catch (Throwable t) {
			t.printStackTrace();

			logger.error("删除数据失败！");
			logger.error(t.getMessage());
			BaseException e = new BaseException();
			e.setErrorCode("E010013");
			throw e;
		}
		return row;
	}

	/**
	 * 更新数据表中的记录。
	 * @param bean - 数据对象
	 * @param whereClause - 搜索条件
	 */
	public int update(BaseVO vo, String whereClause) throws Exception {

		// 更新的数据行数
		int row = 0;

		Connection conn = null;

		PreparedStatement ps = null;

		try {
			conn = getConnection();
			logger.debug(getUpdateSQL()+whereClause);
			ps = conn.prepareStatement(getUpdateSQL() + whereClause);

			//实例化动态SQL
			prepareUpdateStatement(ps, vo);

			//执行更新操作
			row = ps.executeUpdate();

			if (ps != null) {
				ps.close();
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			logger.error("数据库操作语句：" + ps.toString());

			while (ex != null) {
				logger.error("更新数据失败！");
				logger.error("error code:" + ex.getErrorCode());
				logger.error("SQL State:" + ex.getSQLState());
				logger.error("异常信息:" + ex.getMessage());

				ex = ex.getNextException();
			}

			BaseException e = new BaseException();
			e.setErrorCode("E010014");
			throw e;

		} catch (Throwable t) {
			t.printStackTrace();

			logger.error("更新数据失败！");
			logger.error(t.getMessage());
			BaseException e = new BaseException();
			e.setErrorCode("E010014");
			throw e;

		} finally {
			try {
				//释放数据库连接
				if (conn != null) {
					conn.close();
				}
			} catch (Exception ex) {
				ex.printStackTrace();

				logger.error("BaseDAO 释放数据库连接失败！");
				logger.error(ex.getMessage());
				BaseException e = new BaseException();
				e.setErrorCode("E010019");
				throw e;
			}
		}
		return row;
	}

	/**
	 * 通过传入的数据库连接更新数据表中的记录，适用于事务操作。
	 * @param bean - 数据对象
	 * @param conn - 数据库连接
	 * @param whereClause - 搜索条件
	 */
	public int update(BaseVO vo, String whereClause, java.sql.Connection conn)
		throws Exception {

		// 更新的数据行数
		int row = 0;

		PreparedStatement ps = null;

		try {

			ps = conn.prepareStatement(getUpdateSQL() + whereClause);
			logger.debug(getUpdateSQL()+whereClause);
			//实例化动态SQL
			prepareUpdateStatement(ps, vo);

			//执行更新操作
			row = ps.executeUpdate();

			if (ps != null) {
				ps.close();
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			logger.error("数据库操作语句：" + ps.toString());

			while (ex != null) {
				logger.error("更新数据失败！");
				logger.error("error code:" + ex.getErrorCode());
				logger.error("SQL State:" + ex.getSQLState());
				logger.error("异常信息:" + ex.getMessage());

				ex = ex.getNextException();
			}

			BaseException e = new BaseException();
			e.setErrorCode("E010014");
			throw e;

		} catch (Throwable t) {
			t.printStackTrace();

			logger.error("更新数据失败！");
			logger.error(t.getMessage());
			BaseException e = new BaseException();
			e.setErrorCode("E010014");
			throw e;

		}
		return row;
	}

	/**
	 * 执行数据更新操作，更新SQL直接由参数指定。
	 * @param sql - 数据库操作SQL语句
	 * @throws Exception - 操作失败
	 */
	public int executeUpdate(String sql) throws Exception {

		// 更新行数
		int row = 0;

		Connection conn = null;

		Statement statement = null;

		try {
			conn = getConnection();
			statement = conn.createStatement();
			logger.debug(sql);
			//返回更新的纪录行数
			row = statement.executeUpdate(sql);

			if (statement != null) {
				statement.close();
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			logger.error("数据库操作语句：" + sql);

			while (ex != null) {
				logger.error("数据库操作失败！");
				logger.error("error code:" + ex.getErrorCode());
				logger.error("SQL State:" + ex.getSQLState());
				logger.error("异常信息:" + ex.getMessage());

				ex = ex.getNextException();
			}

			BaseException e = new BaseException();
			e.setErrorCode("E010015");
			throw e;

		} catch (Throwable t) {
			t.printStackTrace();

			logger.error("数据库操作失败！");
			logger.error(t.getMessage());
			BaseException e = new BaseException();
			e.setErrorCode("E010015");
			throw e;

		} finally {

			try {
				//释放数据库连接
				if (conn != null) {
					conn.close();
				}
			} catch (Exception ex) {
				ex.printStackTrace();

				logger.error("BaseDAO 释放数据库连接失败！");
				logger.error(ex.getMessage());
				BaseException e = new BaseException();
				e.setErrorCode("E010019");
				throw e;
			}
		}

		return row;
	}

	/**
	 * 通过传入的数据库连接执行数据更新操作，更新SQL直接由参数指定，适用于事务操作。
	 * @param sql - 数据库操作SQL语句
	 * @param conn - 数据库连接
	 * @throws Exception - 操作失败
	 */
	public int executeUpdate(String sql, java.sql.Connection conn)
		throws Exception {

		// 更新行数
		int row = 0;

		Statement statement = null;

		try {

			statement = conn.createStatement();
			logger.debug(sql);
			//返回更新的纪录行数
			row = statement.executeUpdate(sql);
			logger.debug("query finished");
			if (statement != null) {
				statement.close();
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			logger.error("数据库操作语句：" + sql);

			while (ex != null) {
				logger.error("数据库操作失败！");
				logger.error("error code:" + ex.getErrorCode());
				logger.error("SQL State:" + ex.getSQLState());
				logger.error("异常信息:" + ex.getMessage());

				ex = ex.getNextException();
			}

			BaseException e = new BaseException();
			e.setErrorCode("E010015");
			throw e;

		} catch (Throwable t) {
			t.printStackTrace();

			logger.error("数据库操作失败！");
			logger.error(t.getMessage());
			BaseException e = new BaseException();
			e.setErrorCode("E010015");
			throw e;

		}

		return row;
	}

	/**
	 * 设置排序字句
	 * @param orderBy - 排序字句
	 */
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	/**
	 * 查询结果集，返回带翻页信息的list
	 * @param startPosition - 起始纪录位置
	 * @param endPosition - 截止纪录位置
	 * @return 一页数据
	 * @throws SQLException
	 * 
	 */
	private java.util.List queryPage(
		int startPosition,
		int endPosition,
		java.sql.ResultSet rs)
		throws Throwable {

		//如果纪录数为0，返回
		if (endPosition <= 0)
			return null;

		ArrayList list = new ArrayList(endPosition - startPosition + 1);

		//结果集定位
		rs.absolute(startPosition);
		for (int i = startPosition; i <= endPosition; i++) {

			list.add(retrieveResult(rs));
			rs.next();

		}

		return list;

	}

	/**
	 * 查询结果集，返回带翻页信息的list
	 * @param startPosition - 起始纪录位置
	 * @param endPosition - 截止纪录位置
	 * @param method - 结果fetch方法名
	 * @return 一页数据
	 * @throws SQLException
	 * 
	 */
	protected java.util.List queryPage(
		int startPosition,
		int endPosition,
		java.sql.ResultSet rs,
		String method)
		throws Throwable {

		//如果纪录数为0，返回
		if (endPosition <= 0)
			return null;

		ArrayList list = new ArrayList(endPosition - startPosition + 1);

		//结果集定位
		rs.absolute(startPosition);
		for (int i = startPosition; i <= endPosition; i++) {

			Class[] cls = { ResultSet.class };
			Object[] param = { rs };
			Method mth = getClass().getDeclaredMethod(method, cls);

			list.add(mth.invoke(this, param));

			rs.next();

		}

		return list;

	}

	/**
	 * 查询结果集，返回带翻页信息的list
	 * @param page - 翻页对象
	 * @param sql - 查询语句
	 * @return 一页数据
	 * @throws Exception
	 * 
	 */
	public java.util.List queryPage(PageObject page, String sql)
		throws Exception {

		//起始位置在Action层进行赋值处理	
		int startPosition = page.getStartPosition();

		int endPosition;

		List list = null;

		Connection conn = null;

		try {
			conn = getConnection();

			Statement stmt =
				conn.createStatement(
					ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_READ_ONLY);

			logger.debug(sql);

			ResultSet rs = stmt.executeQuery(sql);
			logger.debug("query finished");
			//查询并更新总纪录数
			rs.last();

			page.setTotalRecord(rs.getRow());
			page.setMaxRecords(rs.getRow());


			endPosition = page.getEndPosition();

			if (endPosition <= 0) {
				page.setCurPage(0);
				page.setStartPosition(0);

			}

			list = queryPage(startPosition, endPosition, rs);

			if (rs != null)
				rs.close();

			if (stmt != null)
				stmt.close();

		} catch (SQLException ex) {
			ex.printStackTrace();
			logger.error("数据访问语句：" + sql);

			while (ex != null) {
				logger.error("BaseDAO查询翻页结果失败！");
				logger.error("error code:" + ex.getErrorCode());
				logger.error("SQL State:" + ex.getSQLState());
				logger.error("异常信息:" + ex.getMessage());

				ex = ex.getNextException();
			}

			BaseException e = new BaseException();
			e.setErrorCode("E010018");
			throw e;

		} catch (Throwable t) {
			t.printStackTrace();

			logger.error("BaseDAO查询翻页结果失败！");
			logger.error(t.getMessage());

			BaseException e = new BaseException();
			e.setErrorCode("E010018");
			throw e;
		} finally {
			try {
				//释放数据库连接
				if (conn != null) {
					conn.close();
				}
			} catch (Exception ex) {
				ex.printStackTrace();

				logger.error("BaseDAO 释放数据库连接失败！");
				logger.error(ex.getMessage());
				BaseException e = new BaseException();
				e.setErrorCode("E010019");
				throw e;
			}
		}

		page.setList(list);

		return list;

	}

	public java.util.List queryForwardPage(PageObject page, String sql) throws Exception 
	{
		//起始位置在Action层进行赋值处理	
		int startPosition = page.getStartPosition();

		int endPosition;

		logger.debug("翻页查询语句：" + sql);

		List list = null;

		Connection conn = null;

		try 
		{
			conn = getConnection();

			ResultSet rs;
			
			Statement stmt =
				conn.createStatement(
					ResultSet.TYPE_FORWARD_ONLY,
					ResultSet.CONCUR_READ_ONLY);

			//exclude the ORDER BY clause
			int iob = sql.toLowerCase().indexOf("order by");
			String countSql;
			if (iob == -1)
				countSql = "select count(*) from ("+sql+") c";
			else
				countSql = "select count(*) from ("+sql.substring(0, iob)+") c";
				 
			//查询结果集总数	
			logger.debug(countSql);		
			rs = stmt.executeQuery(countSql);
			logger.debug("query finished");
			rs.next();
			page.setTotalRecord(rs.getInt(1));
			page.setMaxRecords(rs.getInt(1));
			rs.close();

			rs = stmt.executeQuery(sql);

			endPosition = page.getEndPosition();

			if (endPosition <= 0)
			{
				page.setCurPage(0);
				page.setStartPosition(0);

			}
			else
			{
				list = new ArrayList(endPosition - startPosition + 1);

				//结果集定位
				for (int i=0;i<startPosition;i++)
					rs.next();
				for (int i = startPosition; i <= endPosition; i++) 
				{
					list.add(retrieveResult(rs));
					rs.next();
				}
			}

			if (rs != null)
				rs.close();

			if (stmt != null)
				stmt.close();

		} catch (SQLException ex) {
			ex.printStackTrace();
			logger.error("数据访问语句：" + sql);

			while (ex != null) {
				logger.error("BaseDAO查询翻页结果失败！");
				logger.error("error code:" + ex.getErrorCode());
				logger.error("SQL State:" + ex.getSQLState());
				logger.error("异常信息:" + ex.getMessage());

				ex = ex.getNextException();
			}

			BaseException e = new BaseException();
			e.setErrorCode("E010018");
			throw e;

		} catch (Throwable t) {
			t.printStackTrace();

			logger.error("BaseDAO查询翻页结果失败！");
			logger.error(t.getMessage());

			BaseException e = new BaseException();
			e.setErrorCode("E010018");
			throw e;
		} finally {
			try {
				//释放数据库连接
				if (conn != null) {
					conn.close();
				}
			} catch (Exception ex) {
				ex.printStackTrace();

				logger.error("BaseDAO 释放数据库连接失败！");
				logger.error(ex.getMessage());
				BaseException e = new BaseException();
				e.setErrorCode("E010019");
				throw e;
			}
		}

		page.setList(list);

		return list;

	}
	/**
	 * @author houxh 2008-3-18
	 * @param page
	 * @param sql
	 * @return
	 * @throws Exception
	 */
	public java.util.List queryLargePageByForward(PageObject page,String sql)throws Exception{
		return queryForwardPage(page,sql,getMaxFetchRows());
	}
	/**
     * @author houxh 2008-1-2
     * 获得给定条数的查询结果
     * 
     * @param page
     * @param sql
     * @param count
     * @return
     * @throws Exception 
     */
    public java.util.List queryForwardPage(PageObject page, String sql,int count) throws Exception 
	{
		int startPosition = page.getStartPosition();

		int endPosition;

		logger.debug("翻页查询语句：" + sql);

		List list = null;

		Connection conn = null;

		try 
		{
			conn = getConnection();

			ResultSet rs;
			
			Statement stmt =
				conn.createStatement(
					ResultSet.TYPE_FORWARD_ONLY,
					ResultSet.CONCUR_READ_ONLY);

			//exclude the ORDER BY clause
			int iob = sql.toLowerCase().indexOf("order by");
			String countSql;
			if (iob == -1)
				countSql = "select count(*) from ("+sql+") c";
			else
				countSql = "select count(*) from ("+sql.substring(0, iob)+") c";
				 
			//查询结果集总数		
			logger.debug(countSql);	
			rs = stmt.executeQuery(countSql);
			logger.debug("query finished");
			rs.next();
			int row = rs.getInt(1);
			page.setTotalRecord(count);
			
			SysLog.debug("总纪录数：" + row);
			rs.close();
			
			page.setMaxRecords(row);
			page.setMaxFetchRow(count);
			page.setTotalRecord((row < 0)? 0: ((row > count) ? count : row));
			stmt.execute("set rowcount "+count+" ");
			logger.debug(sql);
			rs = stmt.executeQuery(sql);
			logger.debug("query finished");
			//设置返回总纪录数
			endPosition = page.getEndPosition();
			
			if (endPosition <= 0)
			{
				page.setCurPage(0);
				page.setStartPosition(0);

			}
			else
			{
				list = new ArrayList(endPosition - startPosition + 1);

				//结果集定位
				for (int i=0;i<startPosition;i++)
					rs.next();
				for (int i = startPosition; i <= endPosition; i++) 
				{
					list.add(retrieveResult(rs));
					rs.next();
				}
			}
			stmt.execute(" set rowcount 0");
			if (rs != null)
				rs.close();

			if (stmt != null)
				stmt.close();

		} catch (SQLException ex) {
			ex.printStackTrace();
			logger.error("数据访问语句：" + sql);

			while (ex != null) {
				logger.error("BaseDAO查询翻页结果失败！");
				logger.error("error code:" + ex.getErrorCode());
				logger.error("SQL State:" + ex.getSQLState());
				logger.error("异常信息:" + ex.getMessage());

				ex = ex.getNextException();
			}

			BaseException e = new BaseException();
			e.setErrorCode("E010018");
			throw e;

		} catch (Throwable t) {
			t.printStackTrace();

			logger.error("BaseDAO查询翻页结果失败！");
			logger.error(t.getMessage());

			BaseException e = new BaseException();
			e.setErrorCode("E010018");
			throw e;
		} finally {
			try {
				//释放数据库连接
				if (conn != null) {
					conn.close();
				}
			} catch (Exception ex) {
				ex.printStackTrace();

				logger.error("BaseDAO 释放数据库连接失败！");
				logger.error(ex.getMessage());
				BaseException e = new BaseException();
				e.setErrorCode("E010019");
				throw e;
			}
		}

		page.setList(list);

		return list;

	}
	/**
	 * 查询结果集，返回带翻页信息的list,适用于大数据量的查询
	 * @param page - 翻页对象
	 * @param sql - 查询语句
	 * @return 一页数据
	 * @throws Exception
	 * 
	 */
	public java.util.List queryLargePage(PageObject page, String sql)
		throws Exception {

		//起始位置在Action层进行赋值处理	
		int startPosition = page.getStartPosition();

		int endPosition;

		List list = null;

		Connection conn = null;

		try {
			conn = getConnection();

			Statement stmt =
				conn.createStatement(
					ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_READ_ONLY);

			//设置结果集总数				
			stmt.execute("set rowcount 1");
			
			int iob = sql.toLowerCase().indexOf("order by");
			String countSql;
			if (iob == -1){
				countSql = "select count(1) " + sql.substring(sql.indexOf("from"));				
			}else{
				countSql = "select count(1) " + sql.substring(sql.indexOf("from"),iob);				
			}
			logger.debug(countSql);
			//查询结果集总数			
			ResultSet rs = stmt.executeQuery(countSql);

			logger.debug("query end");

			int maxRecord = -1;

			if (rs.next()) {
				maxRecord = rs.getInt(1);
			}

			
						
			stmt.execute("set rowcount " + getMaxFetchRows());

			logger.debug(sql);
			rs = stmt.executeQuery(sql);
			logger.debug("query finished");
			

			//查询到的总纪录数
			page.setMaxRecords((maxRecord < 0) ? 0 : maxRecord);

			//设置返回总纪录数
			int maxRows = getMaxFetchRows(); //系统允许查询的总纪录数
			page.setTotalRecord(
				(maxRecord < 0)
					? 0
					: ((maxRecord > maxRows) ? maxRows : maxRecord));

			endPosition = page.getEndPosition();

			if (endPosition <= 0) {
				page.setCurPage(0);
				page.setStartPosition(0);
			}

			list = queryPage(startPosition, endPosition, rs);

			//释放对查询结果的限制
			stmt.execute("set rowcount 0");

			if (rs != null)
				rs.close();

			if (stmt != null)
				stmt.close();

		} catch (SQLException ex) {
			ex.printStackTrace();
			logger.error("数据访问语句：" + sql);

			while (ex != null) {
				logger.error("BaseDAO查询翻页结果失败！");
				logger.error("error code:" + ex.getErrorCode());
				logger.error("SQL State:" + ex.getSQLState());
				logger.error("异常信息:" + ex.getMessage());

				ex = ex.getNextException();
			}

			BaseException e = new BaseException();
			e.setErrorCode("E010018");
			throw e;

		} catch (Throwable t) {
			t.printStackTrace();

			logger.error("BaseDAO查询翻页结果失败！");
			logger.error(t.getMessage());

			BaseException e = new BaseException();
			e.setErrorCode("E010018");
			throw e;
		} finally {
			try {
				//释放数据库连接
				if (conn != null) {
					conn.close();
				}
			} catch (Exception ex) {
				ex.printStackTrace();

				logger.error("BaseDAO 释放数据库连接失败！");
				logger.error(ex.getMessage());
				BaseException e = new BaseException();
				e.setErrorCode("E010019");
				throw e;
			}
		}

		page.setList(list);

		return list;

	}

	/**
	 * 查询结果集，返回带翻页信息的list
	 * @param page - 翻页对象
	 * @param sql - 查询语句
	 * @param method - 结果fetch方法名
	 * @return 一页数据
	 * @throws Exception
	 * 
	 */
	public java.util.List queryPage(PageObject page, String sql, String method)
		throws Exception {

		//起始位置在Action层进行赋值处理	
		int startPosition = page.getStartPosition();

		int endPosition;

		logger.debug("翻页查询语句：" + sql);

		List list = null;

		Connection conn = null;

		try {
			conn = getConnection();
			Statement stmt =
				conn.createStatement(
					ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_READ_ONLY);

			//stmt.setMaxRows(getMaxFetchRows());
			logger.debug(sql);
			ResultSet rs = stmt.executeQuery(sql);
			logger.debug("query finished");
			//查询并更新总纪录数
			rs.last();

			page.setTotalRecord(rs.getRow());
			page.setMaxRecords(rs.getRow());

			endPosition = page.getEndPosition();

			if (endPosition <= 0) {
				page.setCurPage(0);
				page.setStartPosition(0);

			}

			list = queryPage(startPosition, endPosition, rs, method);

			if (rs != null)
				rs.close();

			if (stmt != null)
				stmt.close();

		} catch (SQLException ex) {

			ex.printStackTrace();
			logger.error("数据查询语句：" + sql);

			while (ex != null) {
				logger.error("BaseDAO查询翻页结果失败！");
				logger.error("error code:" + ex.getErrorCode());
				logger.error("SQL State:" + ex.getSQLState());
				logger.error("异常信息:" + ex.getMessage());

				ex = ex.getNextException();
			}

			BaseException e = new BaseException();
			e.setErrorCode("E010018");
			throw e;

		} catch (Throwable t) {
			t.printStackTrace();

			logger.error("BaseDAO查询翻页结果失败！");
			logger.error(t.getMessage());

			BaseException e = new BaseException();
			e.setErrorCode("E010018");
			throw e;
		} finally {
			try {
				//释放数据库连接
				if (conn != null) {
					conn.close();
				}
			} catch (Exception ex) {
				ex.printStackTrace();

				logger.error("BaseDAO 释放数据库连接失败！");
				logger.error(ex.getMessage());
				BaseException e = new BaseException();
				e.setErrorCode("E010019");
				throw e;
			}
		}

		page.setList(list);

		return list;

	}

	/**
	 * 查询结果集，返回带翻页信息的list
	 * @param page - 翻页对象
	 * @param sql - 查询语句
	 * @param method - 结果fetch方法名
	 * @return 一页数据
	 * @throws Exception
	 * 
	 */
	public java.util.List queryLargePage(
		PageObject page,
		String sql,
		String method)
		throws Exception {

		//起始位置在Action层进行赋值处理	
		int startPosition = page.getStartPosition();

		int endPosition;

		logger.debug("翻页查询语句：" + sql);

		List list = null;

		Connection conn = null;

		try {
			conn = getConnection();
			Statement stmt =
				conn.createStatement(
					ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_READ_ONLY);

			//设置结果集总数
			stmt.execute("set rowcount 1");
			
			int iob = sql.toLowerCase().indexOf("order by");
			String countSql;
			if (iob == -1){
				countSql = "select count(1) " + sql.substring(sql.indexOf("from"));				
			}else{
				countSql = "select count(1) " + sql.substring(sql.indexOf("from"),iob);				
			}
			
			logger.debug(countSql);
			//查询结果集总数			
			ResultSet rs = stmt.executeQuery(countSql);
			logger.debug("query finished");
			

			int maxRecord = -1;

			if (rs.next()) {
				maxRecord = rs.getInt(1);
			}

			
			stmt.execute("set rowcount " + getMaxFetchRows());

			logger.debug(sql);
			rs = stmt.executeQuery(sql);
			logger.debug("query finished");

			//查询到的总纪录数
			page.setMaxRecords((maxRecord < 0) ? 0 : maxRecord);

			//设置返回总纪录数
			int maxRows = getMaxFetchRows(); //系统允许查询的总纪录数
			page.setTotalRecord(
				(maxRecord < 0)
					? 0
					: ((maxRecord > maxRows) ? maxRows : maxRecord));

			endPosition = page.getEndPosition();

			if (endPosition <= 0) {
				page.setCurPage(0);
				page.setStartPosition(0);

			}

			list = queryPage(startPosition, endPosition, rs, method);

			//释放对查询结果的限制			
			stmt.execute("set rowcount 0");

			if (rs != null)
				rs.close();

			if (stmt != null)
				stmt.close();

		} catch (SQLException ex) {
			ex.printStackTrace();
			logger.error("数据查询语句：" + sql);

			while (ex != null) {
				logger.error("BaseDAO查询翻页结果失败！");
				logger.error("error code:" + ex.getErrorCode());
				logger.error("SQL State:" + ex.getSQLState());
				logger.error("异常信息:" + ex.getMessage());

				ex = ex.getNextException();
			}

			BaseException e = new BaseException();
			e.setErrorCode("E010018");
			throw e;

		} catch (Throwable t) {
			t.printStackTrace();

			logger.error("BaseDAO查询翻页结果失败！");
			logger.error(t.getMessage());

			BaseException e = new BaseException();
			e.setErrorCode("E010018");
			throw e;
		} finally {
			try {
				//释放数据库连接
				if (conn != null) {
					conn.close();
				}
			} catch (Exception ex) {
				ex.printStackTrace();

				logger.error("BaseDAO 释放数据库连接失败！");
				logger.error(ex.getMessage());
				BaseException e = new BaseException();
				e.setErrorCode("E010019");
				throw e;
			}
		}

		page.setList(list);

		return list;

	}

	//-----------------以下方法需要重载----------------------

	/**
	 * 设置insert Statement
	 * @param ps -需要执行的PreparedStatement
	 * @param bean - 数据对象 
	 * @throws Exception - 数据异常
	 */
	public abstract void prepareInsertStatement(
		PreparedStatement ps,
		BaseVO vo)
		throws Exception;

	/**
	 * 设置update Statement
	 * @param ps -需要执行的PreparedStatement
	 * @param bean - 数据对象 
	 * @throws Exception - 数据查询异常
	 */
	public abstract void prepareUpdateStatement(
		PreparedStatement ps,
		BaseVO vo)
		throws Exception;

	/**
	 * 删除数据
	 * @param bean - 数据对象，用来拼装where条件 
	 * @throws Exception - 数据查询异常
	 */
	public abstract void delete(BaseVO vo) throws Exception;

	/**
	 * 查询全部数据，返回多纪录
	 * @param sql - 查询SQL语句
	 * @return 全部数据，不分页
	 * @throws Exception - 数据查询异常
	 */
	public final java.util.List queryList(String sql) throws Exception {

		ArrayList list;

		Connection conn = null;

		try {
			conn = getConnection();

			Statement stmt =
				conn.createStatement(
					ResultSet.TYPE_FORWARD_ONLY,
					ResultSet.CONCUR_READ_ONLY);

			list = new ArrayList();
			logger.debug(sql);
			ResultSet rs = stmt.executeQuery(sql);
			logger.debug("query finished");
			while (rs.next()) {

				list.add(retrieveResult(rs));

			}

			if (rs != null)
				rs.close();

			if (stmt != null)
				stmt.close();

		} catch (SQLException ex) {
			ex.printStackTrace();
			logger.error("数据查询语句：" + sql);

			while (ex != null) {
				logger.error("BaseDAO查询结果失败！");
				logger.error("error code:" + ex.getErrorCode());
				logger.error("SQL State:" + ex.getSQLState());
				logger.error("异常信息:" + ex.getSQLState());
				ex = ex.getNextException();
			}

			BaseException e = new BaseException();
			e.setErrorCode("E010018");
			throw e;

		} catch (Throwable t) {
			t.printStackTrace();

			logger.error("BaseDAO查询结果失败！");
			logger.error(t.getMessage());

			BaseException e = new BaseException();
			e.setErrorCode("E010018");
			throw e;
		} finally {
			try {
				//释放数据库连接
				if (conn != null) {
					conn.close();
				}
			} catch (Exception ex) {
				logger.error("BaseDAO 释放数据库连接失败！");
				logger.error(ex.getMessage());
				BaseException e = new BaseException();
				e.setErrorCode("E010019");
				throw e;
			}
		}
		return list;
	}

	/**
	 * 查询全部数据，返回小于限定纪录数的所有纪录
	 * @param sql - 查询SQL语句
	 * @param maxFetchSize - 最大纪录数
	 * @return 全部数据，不分页
	 * @throws Exception - 数据查询异常
	 */
	public java.util.List queryList(String sql, int maxFetchSize)
		throws Exception {

		ArrayList list;

		Connection conn = null;

		try {
			conn = getConnection();
			Statement stmt =
				conn.createStatement(
					ResultSet.TYPE_FORWARD_ONLY,
					ResultSet.CONCUR_READ_ONLY);

			list = new ArrayList();

			stmt.execute("set rowcount " + maxFetchSize);
			logger.debug(sql);
			ResultSet rs = stmt.executeQuery(sql);
			logger.debug("query finished");
			
			while (rs.next()) {

				list.add(retrieveResult(rs));

			}

			stmt.execute("set rowcount 0");

			if (rs != null)
				rs.close();

			if (stmt != null)
				stmt.close();

		} catch (SQLException ex) {
			ex.printStackTrace();
			logger.error("数据查询语句：" + sql);

			while (ex != null) {
				logger.error("BaseDAO查询结果失败！");
				logger.error("error code:" + ex.getErrorCode());
				logger.error("SQL State:" + ex.getSQLState());
				logger.error("异常信息:" + ex.getSQLState());
				ex = ex.getNextException();
			}

			BaseException e = new BaseException();
			e.setErrorCode("E010018");
			throw e;

		} catch (Throwable t) {
			t.printStackTrace();

			logger.error("BaseDAO查询结果失败！");
			logger.error(t.getMessage());

			BaseException e = new BaseException();
			e.setErrorCode("E010018");
			throw e;
		} finally {
			try {
				//释放数据库连接
				if (conn != null) {
					conn.close();
				}
			} catch (Exception ex) {
				logger.error("BaseDAO 释放数据库连接失败！");
				logger.error(ex.getMessage());
				BaseException e = new BaseException();
				e.setErrorCode("E010019");
				throw e;
			}
		}
		return list;
	}

	/**
	 * 查询结果集，返回带翻页信息的list
	 *
	 * @param sql - 查询语句
	 * @param method - 结果fetch方法名
	 * @param maxFetchSize - 返回的最大纪录数
	 * @return 一页数据
	 * @throws Exception
	 * 
	 */
	public java.util.List queryList(
		String sql,
		String method,
		int maxFetchSize)
		throws Exception {

		ArrayList list;

		Connection conn = null;

		try {
			conn = getConnection();
			Statement stmt =
				conn.createStatement(
					ResultSet.TYPE_FORWARD_ONLY,
					ResultSet.CONCUR_READ_ONLY);

			list = new ArrayList();
			stmt.execute("set rowcount " + maxFetchSize);
			logger.debug(sql);
			ResultSet rs = stmt.executeQuery(sql);
			logger.debug("query finished");
			while (rs.next()) {

				Class[] cls = { ResultSet.class };
				Object[] param = { rs };
				Method mth = getClass().getDeclaredMethod(method, cls);

				list.add(mth.invoke(this, param));

			}

			stmt.execute("set rowcount 0");

			if (rs != null)
				rs.close();

			if (stmt != null)
				stmt.close();

		} catch (SQLException ex) {
			ex.printStackTrace();
			logger.error("数据查询语句：" + sql);

			while (ex != null) {
				logger.error("BaseDAO查询结果失败！");
				logger.error("error code:" + ex.getErrorCode());
				logger.error("SQL State:" + ex.getSQLState());
				logger.error("异常信息:" + ex.getSQLState());
				ex = ex.getNextException();
			}

			BaseException e = new BaseException();
			e.setErrorCode("E010018");
			throw e;

		} catch (Throwable t) {
			t.printStackTrace();

			logger.error("BaseDAO查询结果失败！");
			logger.error(t.getMessage());

			BaseException e = new BaseException();
			e.setErrorCode("E010018");
			throw e;
		} finally {
			try {
				//释放数据库连接
				if (conn != null) {
					conn.close();
				}
			} catch (Exception ex) {
				logger.error("BaseDAO 释放数据库连接失败！");
				logger.error(ex.getMessage());
				BaseException e = new BaseException();
				e.setErrorCode("E010019");
				throw e;
			}
		}
		return list;
	}

	/**
	 * 查询记录总数
	 * 
	 * @param sql - 查询语句
	 * @return 复合条件的记录总数 
	 * @throws Exception - 数据查询异常
	 * 
	 */
	public int queryCount(String sql) throws Exception {

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		int rows = 0;

		try {
			conn = getConnection();
			stmt =
				conn.createStatement(
					ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			//查询数据
			logger.debug(sql);
			rs = stmt.executeQuery(sql);
			logger.debug("query finished");
			rs.last();
			rows = rs.getRow();

			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			logger.error("数据查询语句：" + sql);

			while (ex != null) {
				logger.error("数据查询异常！");
				logger.error("error code:" + ex.getErrorCode());
				logger.error("SQL State:" + ex.getSQLState());
				logger.error("异常信息:" + ex.getMessage());

				ex = ex.getNextException();
			}

			BaseException e = new BaseException();
			e.setErrorCode("E010016");
			throw e;

		} catch (Throwable t) {
			t.printStackTrace();

			logger.error("BaseDAO 查询结果集总数失败！");
			logger.error(t.getMessage());
			BaseException e = new BaseException();
			e.setErrorCode("E010016");
			throw e;
		} finally {
			try {
				//释放数据库连接
				if (conn != null) {
					conn.close();
				}
			} catch (Exception ex) {

				ex.printStackTrace();

				logger.error("BaseDAO 释放数据库连接失败！");
				logger.error(ex.getMessage());
				BaseException e = new BaseException();
				e.setErrorCode("E010019");
				throw e;
			}

		}
		return rows;

	}

	/** 
	 * 执行数据库查询功能，返回唯一记录的int型字段（使用时请注意）
	 * @param sql 标准查询sql语句
	 * @return 查询结果
	 * @throws SQLException - 数据查询失败
	 */
	public int sqlQuerySingleInt(String sql) throws Exception {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		int rows = 0;

		try {
			conn = getConnection();
			stmt =
				conn.createStatement(
					ResultSet.TYPE_FORWARD_ONLY,
					ResultSet.CONCUR_READ_ONLY);
			//查询数据
			logger.debug(sql);
			rs = stmt.executeQuery(sql);
			logger.debug("query finished");
			if (rs.next())
				rows = rs.getInt(1);

			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			logger.error("数据查询语句：" + sql);

			while (ex != null) {
				logger.error("数据查询异常！");
				logger.error("error code:" + ex.getErrorCode());
				logger.error("SQL State:" + ex.getSQLState());
				logger.error("异常信息:" + ex.getMessage());

				ex = ex.getNextException();
			}

			BaseException e = new BaseException();
			e.setErrorCode("E010016");
			throw e;

		} catch (Throwable t) {
			t.printStackTrace();

			logger.error("BaseDAO 查询结果集总数失败！");
			logger.error(t.getMessage());
			BaseException e = new BaseException();
			e.setErrorCode("E010016");
			throw e;
		} finally {
			try {
				//释放数据库连接
				if (conn != null) {
					conn.close();
				}
			} catch (Exception ex) {

				ex.printStackTrace();

				logger.error("BaseDAO 释放数据库连接失败！");
				logger.error(ex.getMessage());
				BaseException e = new BaseException();
				e.setErrorCode("E010019");
				throw e;
			}

		}
		return rows;

	}

	/**
	 * 查询单一记录的结果
	 * 
	 * @param sql - 查询语句
	 * @return 复合条件的结果 
	 * @throws Exception - 数据查询异常
	 * 
	 */
	public String querySingle(String sql) throws Exception {

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String result = null;

		try {
			conn = getConnection();
			stmt =
				conn.createStatement(
					ResultSet.TYPE_FORWARD_ONLY,
					ResultSet.CONCUR_READ_ONLY);

			//查询数据			
			logger.debug(sql);
			rs = stmt.executeQuery(sql);
			logger.debug("query finished");
			if (rs.next())
				result = rs.getString(1);

			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			logger.error("数据查询语句：" + sql);

			while (ex != null) {
				logger.error("数据查询异常！");
				logger.error("error code:" + ex.getErrorCode());
				logger.error("SQL State:" + ex.getSQLState());
				logger.error("异常信息:" + ex.getMessage());

				ex = ex.getNextException();
			}

			BaseException e = new BaseException();
			e.setErrorCode("E010016");
			throw e;

		} catch (Throwable t) {
			t.printStackTrace();

			logger.error("BaseDAO 查询单纪录结果失败！");
			logger.error(t.getMessage());
			BaseException e = new BaseException();
			e.setErrorCode("E010016");
			throw e;
		} finally {
			try {
				//释放数据库连接
				if (conn != null) {
					conn.close();
				}
			} catch (Exception ex) {

				ex.printStackTrace();

				logger.error("BaseDAO 释放数据库连接失败！");
				logger.error(ex.getMessage());
				BaseException e = new BaseException();
				e.setErrorCode("E010019");
				throw e;
			}

		}
		return result;

	}

	/**
	 * 根据bean ID获得实例对象
	 * @param name - bean ID	 
	 * @return bean的实例对象
	 *
	 */
	public Object getBean(String name) throws Exception {

		return BeanFactory.getBean(name);
	}

	/**
	 * 实现翻页查询，返回一页纪录，该方法是BO对象的queryList()方法默认的调用方法
	 * @param page - 翻页对象
	 * @param user - 用户上下文信息
	 * @return - 一页数据
	 * @throws Exception - 数据查询异常
	 */
	public abstract java.util.List queryList(PageObject page, UserContext user)
		throws Exception;

	/**
	 * 实现翻页查询，返回一页纪录,该方法是BO对象的list()方法默认的调用方法
	 * @param page - 翻页对象
	 * @param user - 用户上下文信息
	 * @return - 一页数据
	 * @throws Exception - 数据查询异常
	 */
	public abstract java.util.List list(PageObject page, UserContext user)
		throws Exception;

	/**
	 * 返回一条列表纪录，该方法只对列表字段对应的信息项赋值
	 * @return 单个列表对象	
	 * @throws Exception - 数据查询异常
	 */
	public abstract BaseVO retrieveResult(java.sql.ResultSet rs)
		throws Exception;

	/**
	 * 在数据表中查找指定的记录，返回明细信息结果对象。用于单个对象的查询。
	 * 若结果为多个记录，只返回第一个记录，其余的将忽略。
	 * @param rs - 结果集
	 * @return 结果数据对象 
	 * @throws Exception - 数据查询异常
	 * 
	 */
	public abstract BaseVO detail(java.sql.ResultSet rs) throws Exception;

	//-----------------方法重载模块结束----------------------//

}
