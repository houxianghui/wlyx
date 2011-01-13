/*********************************************************
 * File:ConnectionPool.java
 * 
 * Version 1.0
 * 
 * Date     2005-8-7
 * Author   辛勇
 * 
 * Copyright (C) 2005 huateng
 * all rights reserved.
 *  
 ********************************************************/

package com.eis.connectionPool;

import java.sql.*;
import java.util.*;


/**
 * 说明：数据库连接池控制类
 */

public class ConnectionPool extends DBPool {
	
	
	private String driver;
	
	private String userID;
	
	private String password;
	
	private String url;
	
	private Properties prop = new Properties();
	


	/**
	* 构造函数
	*/
	public ConnectionPool() throws Exception {

	}

	/**
	 * 从指定的数据源获得连接
	 * @param String 数据源名字
	 * @return java.sql.Connection 数据库连接
	 */
	public java.sql.Connection getConnection()	throws Exception {											
				
				//Connection conn= DriverManager.getConnection(url,userID,password);
				Connection conn= DriverManager.getConnection(url,prop);				
				return conn;
				
        
	}



	/**
	 * @return
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @return
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @return
	 */
	public String getUserID() {
		return userID;
	}

	/**
	 * @param string
	 */
	public void setPassword(String string) {
		password = string;
	}

	/**
	 * @param string
	 */
	public void setUrl(String string) {
		url = string;
	}

	/**
	 * @param string
	 */
	public void setUserID(String string) {
		userID = string;
	}

	/**
	 * @return
	 */
	public String getDriver() {
		return driver;
	}

	/**
	 * @param string
	 */
	public void setDriver(String string) {
		driver = string;
	}



	/**
	 * @return
	 */
	public Properties getProp() {
		return prop;
	}

	/**
	 * @param properties
	 */
	public void setProp(Properties properties) {
		prop = properties;
	}

}
