
/*********************************************************
 * File: DBPoolManager.java
 * 
 * Version 1.0
 * 
 * Date     2005-8-31
 * 
 * Author   xin yong
 * 
 ********************************************************/

package com.eis.connectionPool;

import java.util.*;
import java.io.*;
import java.sql.*;

import org.dom4j.io.*;
import org.dom4j.*;

import com.eis.util.*;
import com.eis.base.BaseException;
import resource.*;


/**
 * 说明：
 * 
 */
public class DBPoolManager {

	private static final short CONNECTION = 1;
	private static final short DATASOURCE = 2;
	private static final short XADATASOURCE = 3;

	private static int DSType = 0;

	private static java.util.HashMap map = new HashMap(3);

	static {
		initPool();
	}

	/**
	 * 初始化数据库连接池
	 * 
	 */
	public static void initPool() {
		try {

			SysLog.info("开始数据库配置");

			//初始化数据源	

			SAXReader reader = new SAXReader();
			reader.setEncoding("gb2312");

			Document document =
				reader.read(ResourceFile.getResource("dbconfig.xml"));

			SysLog.info("初始化数据库配置");

			//得到数据源列表			
			Element root = document.getRootElement();
			java.util.List nodes = root.elements();

			if (null == nodes || nodes.size() <= 0) {
				BaseException e = new BaseException();
				e.setErrorCode("E010038");
				throw e;
			} else {
				for (int i = 0; i < nodes.size(); i++) {

					Element node = (Element) nodes.get(i);

					String dsName = node.attributeValue("dsName");
					String dsType = node.attributeValue("dsType");

					DSType = Integer.parseInt(dsType);

					if (Integer.parseInt(dsType) == CONNECTION) {
						//创建ConnectionPool
						ConnectionPool conPool = new ConnectionPool();
						Properties prop = new Properties();
						prop.put("user", node.attributeValue("userID"));
						prop.put("password",  node.attributeValue("password"));
						prop.put("CHARSET",  "cp936");

						conPool.setDriver(node.attributeValue("driver"));
						conPool.setUrl(node.attributeValue("url"));
						
						//conPool.setUserID(node.attributeValue("userID"));
						//conPool.setPassword(node.attributeValue("password"));
						Class.forName(conPool.getDriver()); 
						conPool.setProp(prop);

						map.put(dsName, conPool);

					} else if (Integer.parseInt(dsType) == DATASOURCE) {
						//创建DataSource
						DataSourcePool dsPool = new DataSourcePool();
						dsPool.setDataSource(node.attributeValue("jndi"));
						map.put(dsName, dsPool);

					} else if (Integer.parseInt(dsType) == XADATASOURCE) {
						//创建XADataSource
						XADataSourcePool xdsPool = new XADataSourcePool();
						xdsPool.setDataSource(node.attributeValue("jndi"));
						map.put(dsName, xdsPool);
					}
				}

			}

		} catch (Exception e) {
			SysLog.error("初始化数据源失败！请检查数据库配置信息");
			SysLog.error(e.getMessage());
		}

	}

	/**
	 * 从默认的数据源获得数据库连接
	 * 
	 * @return java.sql.Connection 数据库连接
	 * @throws  Exception
	 */
	public static java.sql.Connection getConnection() throws Exception {
		try {

			if (map == null)
				initPool();
			java.sql.Connection conn =
				((DBPool) (map.get("default"))).getConnection();

			conn.setTransactionIsolation(
				java.sql.Connection.TRANSACTION_READ_COMMITTED);
			return conn;

		} catch (Exception ex) {
			SysLog.error("DBPoolManager 获取默认数据库连接失败！");
			SysLog.error(ex.getMessage());
			BaseException e = new BaseException();
			e.setErrorCode("E010032");
			throw e;
		}

	}

	/**
	 * 从指定的数据源获得连接
	 * @param String 数据源名字
	 * @return java.sql.Connection 数据库连接
	 */
	public static java.sql.Connection getConnection(String dsName)
		throws Exception {
		try {
			if (map == null)
				initPool();

			return ((DBPool) (map.get(dsName))).getConnection();

		} catch (Exception ex) {
			SysLog.error("DBPoolManager 从指定的数据源获得数据库连接失败！");
			SysLog.error(ex.getMessage());
			BaseException e = new BaseException();
			e.setErrorCode("E010033");
			throw e;
		}

	}

}
