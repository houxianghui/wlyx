/*
 * 创建日期 2009-7-29
 *
 * Author Songlijun
 */
package com.abc.dao;

public class DBObserver {
	private static ThreadLocal local = new ThreadLocal();

	public static void putDBName(String dbname) {
		local.set(dbname);
	}

	public static String getDBName() {
		return (String) local.get();
	}
	
}
