
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
 * 说明：数据库连接池类
 * 
 */
public abstract class DBPool {
	
	/**
	 * 获得数据库连接
	 * @return
	 * @throws Exception
	 */
	public abstract java.sql.Connection getConnection() throws Exception;
	
}
