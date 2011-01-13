/*********************************************************
 * File:DataSourcePool.java
 * 
 * Version 1.0
 * 
 * Date     2005-8-22
 * Author   ����
 * 
 * Copyright (C) 2005 huateng.
 * all rights reserved.
 * 
 ********************************************************/

package com.eis.connectionPool;

import javax.naming.*;


/**
 * ˵�������ݿ����ӳ��࣬����һ�׶��ύ���������Դ
 * 
 */

public final class DataSourcePool extends DBPool {

	/**
	 * 
	 * ����Դ
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
	 * ����JNDI���ֻ������Դ
	 * @param jndiName
	 * @throws Exception
	 */
	public void setDataSource(String jndiName) throws Exception {

		InitialContext ctx = new InitialContext(System.getProperties());

		ds = (javax.sql.DataSource) ctx.lookup(jndiName);

	}

	/**
	 * ������ݿ�����
	 * @return java.sql.Connection ���ݿ�����
	 */
	public java.sql.Connection getConnection() throws Exception {

		return ds.getConnection();

	}

}
