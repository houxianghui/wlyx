/*********************************************************
 * File:XADataSourcePool.java
 * 
 * Version 1.0
 * 
 * Date     2005-8-22
 * Author   辛勇
 * 
 * Copyright (C) 2005 huateng
 * all rights reserved.
 * 
 ********************************************************/

package com.eis.connectionPool;


import javax.naming.*;

/**
 * 说明：数据库连接池类，管理两阶段提交事务的数据源
 * 
 */

public class XADataSourcePool extends DBPool {

	/**
	 * 配置信息初始化标志
	 */
	private static boolean init = false;

	/**
	 * 构造函数
	 */
	public XADataSourcePool() throws Exception  {
		super();
		
	}

	/**
	 * 
	 * 数据源
	 *
	 */
	public static javax.sql.XADataSource ds = null;
	
	/**
	 * 根据JNDI名字获得数据源
	 * @param jndiName
	 * @throws Exception
	 */
	public void setDataSource(String jndiName) throws Exception {
		if (!init) {
			
			InitialContext ctx = new InitialContext(System.getProperties());
			
			ds = (javax.sql.XADataSource) ctx.lookup(jndiName);
		}		
	}

	/**
	 * 获得数据库连接
	 * @return java.sql.Connection 数据库连接
	 */
	public java.sql.Connection getConnection() throws Exception {

		return ds.getXAConnection().getConnection();

	}

}
