
/*********************************************************
 * File: DBPool.java
 * 
 * Version 1.0
 * 
 * Date     2005-8-31
 * 
 * Author   xin yong
 * 
 ********************************************************/

package com.eis.connectionPool;

/**
 * ˵�������ݿ����ӳ���
 * 
 */
public abstract class DBPool {
	
	/**
	 * ������ݿ�����
	 * @return
	 * @throws Exception
	 */
	public abstract java.sql.Connection getConnection() throws Exception;
	
}
