
/*********************************************************
 * File: DBQueryUtil.java
 * 
 * Version 1.0
 * 
 * Date     2005-9-1
 * 
 * Author   xin yong
 * 
 ********************************************************/

package com.eis.db;

import java.sql.*;

import org.apache.log4j.Logger;

import com.eis.base.*;
import com.eis.connectionPool.*;

import com.eis.util.*;

/**
 * 说明：数据库查询工具类
 * 
 */
public class DBQueryUtil {
	private static Logger logger = Logger.getLogger(DBQueryUtil.class);

	Connection con = null;
	Statement query = null;

	ResultSet rs = null;
	

	/**
	 * 构造函数
	 */
	public DBQueryUtil() {
		try {
			//获取连接
			con = DBPoolManager.getConnection();
			query =
				con.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);

		} catch (Exception ex) {
			logger.error("DBQueryUtil类实例化失败");
			logger.error(ex.toString());
		}

	}

	/**
	 * 构造函数
	 * @param dsName 数据源名字
	 */
	public DBQueryUtil(String dsName) {

		try {

			//获取连接
			con = DBPoolManager.getConnection(dsName);
			query =
				con.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);

		} catch (Exception ex) {
			logger.error("DBQueryUtil类实例化失败");
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

		ResultSet result = query.executeQuery(sql);

		return result;

	}
	
	/** 
	 * 执行数据库查询功能，返回查询结果
	 * @param  sql 数据库查询语句
	 * @return ResultSet 查询结果集
	 * @throws 查询失败
	 */
	public ResultSet sqlQueryR(String sql) throws Exception {

		if (null == sql || sql.trim().length() <= 0) {
			return null;
		}
		
		Statement stmt =
			con.createStatement(
				ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY);

		ResultSet result = stmt.executeQuery(sql);

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
			rs = query.executeQuery(sql);
			rs.last();
			rows = rs.getRow();
			return rows;
		} catch (SQLException ex) {
			logger.error("查询结果集失败");
			logger.error(ex.toString());
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
			rs = query.executeQuery(sql);
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
	 * 执行数据库查询功能，返回列表记录的第一条纪录的第一个字段（使用时请注意）
	 * @param sql 标准查询sql语句
	 * @return 查询结果
	 * @throws SQLException - 数据查询失败
	 */
	public String sqlQueryFirst(String sql) throws Exception {
		String str = null;

		if (null == sql || sql.trim().length() <= 0) {
			return str;
		}

		try {	
			query.execute("set rowcount 1");		
			rs = query.executeQuery(sql);
			if (rs.next())
				str = rs.getString(1);
				
			query.execute("set rowcount 0");
			return str;

		} catch (SQLException ex) {
			ex.printStackTrace();
			logger.error("查询结果集失败");
			BaseException e = new BaseException();
			e.setErrorCode("E010001");
			throw e;
		}
	}
	
	/** 
	 * 执行数据库查询功能，返回某个字段唯一记录的int型字段（使用时请注意）
	 * @param sql 标准查询sql语句
	 * @return 查询结果
	 * @throws SQLException - 数据查询失败
	 */
	public int sqlQuerySingleInt(String sql) throws Exception {
		int i = 0;

		if (null == sql || sql.trim().length() <= 0) {
			return i;
		}

		try {
			rs = query.executeQuery(sql);
			if (rs.next())
				i = rs.getInt(1);
			return i;

		} catch (SQLException ex) {
			ex.printStackTrace();
			logger.error("查询结果集失败");
			BaseException e = new BaseException();
			e.setErrorCode("E010001");
			throw e;
		}
	}



	/** 
	 * 关闭数据库连接，释放资源  
	 */
	public void close() {
		try {
			if (rs != null)
				rs.close();
			if (query != null)
				query.close();

		} catch (SQLException ex) {
			logger.error("释放资源失败！");
			logger.error(ex.toString());

		} finally {
			try {
				if (con != null)
					con.close();

			} catch (Exception ex) {
				logger.error("DBQueryUtil类释放数据库连接失败！");
				logger.error(ex.getMessage());

			}
		}

	}

}