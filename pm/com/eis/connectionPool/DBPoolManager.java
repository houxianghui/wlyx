
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
 * ˵����
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
	 * ��ʼ�����ݿ����ӳ�
	 * 
	 */
	public static void initPool() {
		try {

			SysLog.info("��ʼ���ݿ�����");

			//��ʼ������Դ	

			SAXReader reader = new SAXReader();
			reader.setEncoding("gb2312");

			Document document =
				reader.read(ResourceFile.getResource("dbconfig.xml"));

			SysLog.info("��ʼ�����ݿ�����");

			//�õ�����Դ�б�			
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
						//����ConnectionPool
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
						//����DataSource
						DataSourcePool dsPool = new DataSourcePool();
						dsPool.setDataSource(node.attributeValue("jndi"));
						map.put(dsName, dsPool);

					} else if (Integer.parseInt(dsType) == XADATASOURCE) {
						//����XADataSource
						XADataSourcePool xdsPool = new XADataSourcePool();
						xdsPool.setDataSource(node.attributeValue("jndi"));
						map.put(dsName, xdsPool);
					}
				}

			}

		} catch (Exception e) {
			SysLog.error("��ʼ������Դʧ�ܣ��������ݿ�������Ϣ");
			SysLog.error(e.getMessage());
		}

	}

	/**
	 * ��Ĭ�ϵ�����Դ������ݿ�����
	 * 
	 * @return java.sql.Connection ���ݿ�����
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
			SysLog.error("DBPoolManager ��ȡĬ�����ݿ�����ʧ�ܣ�");
			SysLog.error(ex.getMessage());
			BaseException e = new BaseException();
			e.setErrorCode("E010032");
			throw e;
		}

	}

	/**
	 * ��ָ��������Դ�������
	 * @param String ����Դ����
	 * @return java.sql.Connection ���ݿ�����
	 */
	public static java.sql.Connection getConnection(String dsName)
		throws Exception {
		try {
			if (map == null)
				initPool();

			return ((DBPool) (map.get(dsName))).getConnection();

		} catch (Exception ex) {
			SysLog.error("DBPoolManager ��ָ��������Դ������ݿ�����ʧ�ܣ�");
			SysLog.error(ex.getMessage());
			BaseException e = new BaseException();
			e.setErrorCode("E010033");
			throw e;
		}

	}

}
