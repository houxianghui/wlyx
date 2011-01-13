/*********************************************************
 * File:XADataSourcePool.java
 * 
 * Version 1.0
 * 
 * Date     2005-8-22
 * Author   ����
 * 
 * Copyright (C) 2005 huateng
 * all rights reserved.
 * 
 ********************************************************/

package com.eis.connectionPool;


import javax.naming.*;

/**
 * ˵�������ݿ����ӳ��࣬�������׶��ύ���������Դ
 * 
 */

public class XADataSourcePool extends DBPool {

	/**
	 * ������Ϣ��ʼ����־
	 */
	private static boolean init = false;

	/**
	 * ���캯��
	 */
	public XADataSourcePool() throws Exception  {
		super();
		
	}

	/**
	 * 
	 * ����Դ
	 *
	 */
	public static javax.sql.XADataSource ds = null;
	
	/**
	 * ����JNDI���ֻ������Դ
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
	 * ������ݿ�����
	 * @return java.sql.Connection ���ݿ�����
	 */
	public java.sql.Connection getConnection() throws Exception {

		return ds.getXAConnection().getConnection();

	}

}
