/*********************************************************
 * File:BaseDBUtil.java
 * 
 * Version 1.0
 * 
 * Date     2005-8-23
 * Author   辛勇
 * 
 * Copyright (C) 2005 huateng
 * all rights reserved.
 * 
 ********************************************************/

package com.eis.db;

import java.sql.*;

import org.apache.log4j.Logger;

import com.eis.connectionPool.*;

import com.eis.util.*;
import com.eis.base.*;

/**
 * 说明：数据库操作工具类
 * 
 */

public class DBUtil {
	
	private static Logger logger = Logger.getLogger(DBUtil.class);

	Connection con = null;
	Statement query = null;
	Statement update = null;

	ResultSet rs = null;

	
	/**
	 * 构造函数
	 * @param dsName 数据源名字
	 */
	public DBUtil(String daName) {

		try {

			//获取连接
			con = DBPoolManager.getConnection(daName);
			query =
				con.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			update = con.createStatement();
		} catch (Exception ex) {
			logger.error("BaseDBUtil类实例化失败");
			logger.error(ex.toString());
		}

	}

	/**
	 * 构造函数
	 */
	public DBUtil() {
		try {
			//获取连接
			con = DBPoolManager.getConnection();
			query =
				con.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			update = con.createStatement();
		} catch (Exception ex) {
			logger.error("BaseDBUtil类实例化失败");
			logger.error(ex.toString());
		}

	}
	/** 
	 * 获得数据库连接  
	 * @return java.sql.Connection 数据库连接  
	 */
	public Connection getConnection() {
		return con;
	}

	/** 
	 * 执行数据库查询功能，返回查询结果
	 * @param  sql 数据库查询语句
	 * @return ResultSet 查询结果集
	 * @throws 查询失败
	 */
	public ResultSet sqlQuery(String sql) throws Exception {

		if (null == sql || sql.trim().length() <= 0) {
			return null;
		}
		logger.debug(sql);
		ResultSet result = query.executeQuery(sql);
		logger.debug("query end");
		return result;

	}

	/** 
	 * 执行数据库查询功能，返回满足条件的记录数（使用时请注意）
	 * 
	 * @param  sql 数据库查询语句
	 * @return 记录数
	 */
	public int sqlQueryCount(String sql) throws Exception {
		int rows = 0;

		if (null == sql || sql.trim().length() <= 0) {
			return 0;
		}
		try {
			logger.debug(sql);
			rs = query.executeQuery(sql);
			logger.debug("query end");
			rs.last();
			rows = rs.getRow();
			return rows;
		} catch (SQLException ex) {
			ex.printStackTrace();
			logger.error("查询结果集失败");
			BaseException e = new BaseException();
			e.setErrorCode("E010001");
			throw e;
		}
	}

	/** 
	 * 执行数据库查询功能，返回某个字段唯一记录的字符串（使用时请注意）
	 * @param sql 标准查询sql语句
	 * @return 查询结果
	 * @throws SQLException - 数据查询失败
	 */
	public String sqlQuerySingle(String sql) throws Exception {
		String str = null;

		if (null == sql || sql.trim().length() <= 0) {
			return str;
		}

		try {
			logger.debug(sql);
			rs = query.executeQuery(sql);
			logger.debug("query finished");
			if (rs.next())
				str = rs.getString(1);
			return str;

		} catch (SQLException ex) {
			logger.error("查询结果集失败");
			ex.printStackTrace();
			BaseException e = new BaseException();
			e.setErrorCode("E010001");
			throw e;
		}
	}

	/** 
	 * 执行数据库删除、插入和修改功能，返回执行结果
	 * @param sql 数据库操作语句
	 * @return 执行结果，更新的记录数
	 * @throws Exception - 数据更新失败
	 */
	public int sqlUpdate(String sql) throws Exception {

		int rows = 0;
		if (null == sql || sql.trim().length() <= 0) {
			return 0;
		}
		try {
			rows = update.executeUpdate(sql);
			return rows;
		} catch (SQLException ex) {
			logger.error("更新结果集失败");
			ex.printStackTrace();
			BaseException e = new BaseException();
			e.setErrorCode("E010002");
			throw e;
		}
	}


	/** 
	 * 关闭数据库连接，释放资源  
	 */
	public void close()  {
		try {
			if (rs != null)
				rs.close();
			if (query != null)
				query.close();
			if (update != null)
				update.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
			logger.error("释放数据库连接失败！");
			logger.error(ex.toString());

		} finally {
			try {
				if (con != null)
					con.close();

			} catch (Exception ex) {
				ex.printStackTrace();
				logger.error("DBUtil类释放数据库连接失败！");
				logger.error(ex.getMessage());				
			}
		}

	}

	/** 
	 * 设置数据库是否自动提交
	 * @param autoCommit 是否自动提交
	 * @throws SQLException 
	 */
	public void setAutoCommit(boolean autoCommit) throws SQLException {
		try {
			con.setAutoCommit(autoCommit);
		} catch (SQLException ex) {
			logger.error("设置数据库事务提交失败");
			logger.debug(ex.toString());
			throw ex;
		}

	}

	/** 
	 * 获得数据库是否自动提交标志
	 * @return boolean 是否自动提交
	 * @throws SQLException 
	 */
	public boolean getAutoCommit() throws SQLException {
		return con.getAutoCommit();
	}

	/**
	 * 提交数据库操作
	 * 
	 * @throws SQLException 提交数据库操作失败
	 */

	public void commit() throws SQLException {
		con.commit();
	}

	/**     
	 * 数据库操作回滚
	 *
	 * @throws Exception 回滚失败
	 */
	public void rollback() throws Exception {
		if (getAutoCommit()) {
			BaseException e = new BaseException();
			e.setErrorCode("E010005");
			throw e;
		}
		con.rollback();
	}

	/**
	 * 
	 * 开始批处理
	 *
	 */
	
	public void beginBatch() throws Exception {
		try {
			setAutoCommit(false);
		} catch (SQLException ex) {
			logger.error("开始批处理失败");
			logger.debug(ex.toString());
			throw ex;
		}
	}

	/**
	*添加批处理命令
	*@param sql 批处理命令
	*@return boolean 成功返回true,否则false
	*@throws Exception 
	*/
	public boolean addBatch(String sql) throws Exception {
		try {
			update.addBatch(sql);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error("增加数据库批处理失败");
			throw ex;
		}

	}

	/**
	*进行批处理
	*
	*@return boolean 成功返回true,否则false
	*@throws Exception 
	*/
	public boolean exeBatch() throws Exception {
		boolean success = true;
		try {
			int[] btnum = update.executeBatch();

			for (int i = 0; i < btnum.length; i++) {
				if (btnum[i] <= 0) {
					success = false;
				}
			}
			if (success) {
				con.commit();
				update.clearBatch();

			} else {
				con.rollback();
				update.clearBatch();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error("执行数据库批处理失败");
			throw ex;

		} finally {			
			return success;
		}
	}

}
