/*********************************************************
 * File:DataSourcePool.java
 * 
 * Version 1.0
 * 
 * Date     2005-8-22
 * Author   辛勇
 * 
 * Copyright (C) 2005 huateng.
 * all rights reserved.
 * 
 ********************************************************/

package com.eis.connectionPool;

import javax.naming.*;


/**
 * 说明：数据库连接池类，管理一阶段提交事务的数据源
 * 
 */

public final class DataSourcePool extends DBPool {

	/**
	 * 
	 * 数据源
	 *
	 */
	public static javax.sql.DataSource ds = null;

	/**
	 * 
	 */
	public DataSourcePool() throws Exception {
		super();
	}

	/**
	 * 根据JNDI名字获得数据源
	 * @param jndiName
	 * @throws Exception
	 */
	public void setDataSource(String jndiName) throws Exception {

		InitialContext ctx = new InitialContext(System.getProperties());

		ds = (javax.sql.DataSource) ctx.lookup(jndiName);

	}

	/**
	 * 获得数据库连接
	 * @return java.sql.Connection 数据库连接
	 */
	public java.sql.Connection getConnection() throws Exception {

		return ds.getConnection();

	}

}
