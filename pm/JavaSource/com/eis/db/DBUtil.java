/*********************************************************
 * File:BaseDBUtil.java
 * 
 * Version 1.0
 * 
 * Date     2005-8-23
 * Author   ����
 * 
 * Copyright (C) 2005 huateng
 * all rights reserved.
 * 
 ********************************************************/

package com.eis.db;

import java.sql.*;

import org.apache.log4j.Logger;

import com.eis.connectionPool.*;

import com.eis.util.*;
import com.eis.base.*;

/**
 * ˵�������ݿ����������
 * 
 */

public class DBUtil {
	
	private static Logger logger = Logger.getLogger(DBUtil.class);

	Connection con = null;
	Statement query = null;
	Statement update = null;

	ResultSet rs = null;

	
	/**
	 * ���캯��
	 * @param dsName ����Դ����
	 */
	public DBUtil(String daName) {

		try {

			//��ȡ����
			con = DBPoolManager.getConnection(daName);
			query =
				con.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			update = con.createStatement();
		} catch (Exception ex) {
			logger.error("BaseDBUtil��ʵ����ʧ��");
			logger.error(ex.toString());
		}

	}

	/**
	 * ���캯��
	 */
	public DBUtil() {
		try {
			//��ȡ����
			con = DBPoolManager.getConnection();
			query =
				con.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			update = con.createStatement();
		} catch (Exception ex) {
			logger.error("BaseDBUtil��ʵ����ʧ��");
			logger.error(ex.toString());
		}

	}
	/** 
	 * ������ݿ�����  
	 * @return java.sql.Connection ���ݿ�����  
	 */
	public Connection getConnection() {
		return con;
	}

	/** 
	 * ִ�����ݿ��ѯ���ܣ����ز�ѯ���
	 * @param  sql ���ݿ��ѯ���
	 * @return ResultSet ��ѯ�����
	 * @throws ��ѯʧ��
	 */
	public ResultSet sqlQuery(String sql) throws Exception {

		if (null == sql || sql.trim().length() <= 0) {
			return null;
		}
		logger.debug(sql);
		ResultSet result = query.executeQuery(sql);
		logger.debug("query end");
		return result;

	}

	/** 
	 * ִ�����ݿ��ѯ���ܣ��������������ļ�¼����ʹ��ʱ��ע�⣩
	 * 
	 * @param  sql ���ݿ��ѯ���
	 * @return ��¼��
	 */
	public int sqlQueryCount(String sql) throws Exception {
		int rows = 0;

		if (null == sql || sql.trim().length() <= 0) {
			return 0;
		}
		try {
			logger.debug(sql);
			rs = query.executeQuery(sql);
			logger.debug("query end");
			rs.last();
			rows = rs.getRow();
			return rows;
		} catch (SQLException ex) {
			ex.printStackTrace();
			logger.error("��ѯ�����ʧ��");
			BaseException e = new BaseException();
			e.setErrorCode("E010001");
			throw e;
		}
	}

	/** 
	 * ִ�����ݿ��ѯ���ܣ�����ĳ���ֶ�Ψһ��¼���ַ�����ʹ��ʱ��ע�⣩
	 * @param sql ��׼��ѯsql���
	 * @return ��ѯ���
	 * @throws SQLException - ���ݲ�ѯʧ��
	 */
	public String sqlQuerySingle(String sql) throws Exception {
		String str = null;

		if (null == sql || sql.trim().length() <= 0) {
			return str;
		}

		try {
			logger.debug(sql);
			rs = query.executeQuery(sql);
			logger.debug("query finished");
			if (rs.next())
				str = rs.getString(1);
			return str;

		} catch (SQLException ex) {
			logger.error("��ѯ�����ʧ��");
			ex.printStackTrace();
			BaseException e = new BaseException();
			e.setErrorCode("E010001");
			throw e;
		}
	}

	/** 
	 * ִ�����ݿ�ɾ����������޸Ĺ��ܣ�����ִ�н��
	 * @param sql ���ݿ�������
	 * @return ִ�н�������µļ�¼��
	 * @throws Exception - ���ݸ���ʧ��
	 */
	public int sqlUpdate(String sql) throws Exception {

		int rows = 0;
		if (null == sql || sql.trim().length() <= 0) {
			return 0;
		}
		try {
			rows = update.executeUpdate(sql);
			return rows;
		} catch (SQLException ex) {
			logger.error("���½����ʧ��");
			ex.printStackTrace();
			BaseException e = new BaseException();
			e.setErrorCode("E010002");
			throw e;
		}
	}


	/** 
	 * �ر����ݿ����ӣ��ͷ���Դ  
	 */
	public void close()  {
		try {
			if (rs != null)
				rs.close();
			if (query != null)
				query.close();
			if (update != null)
				update.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
			logger.error("�ͷ����ݿ�����ʧ�ܣ�");
			logger.error(ex.toString());

		} finally {
			try {
				if (con != null)
					con.close();

			} catch (Exception ex) {
				ex.printStackTrace();
				logger.error("DBUtil���ͷ����ݿ�����ʧ�ܣ�");
				logger.error(ex.getMessage());				
			}
		}

	}

	/** 
	 * �������ݿ��Ƿ��Զ��ύ
	 * @param autoCommit �Ƿ��Զ��ύ
	 * @throws SQLException 
	 */
	public void setAutoCommit(boolean autoCommit) throws SQLException {
		try {
			con.setAutoCommit(autoCommit);
		} catch (SQLException ex) {
			logger.error("�������ݿ������ύʧ��");
			logger.debug(ex.toString());
			throw ex;
		}

	}

	/** 
	 * ������ݿ��Ƿ��Զ��ύ��־
	 * @return boolean �Ƿ��Զ��ύ
	 * @throws SQLException 
	 */
	public boolean getAutoCommit() throws SQLException {
		return con.getAutoCommit();
	}

	/**
	 * �ύ���ݿ����
	 * 
	 * @throws SQLException �ύ���ݿ����ʧ��
	 */

	public void commit() throws SQLException {
		con.commit();
	}

	/**     
	 * ���ݿ�����ع�
	 *
	 * @throws Exception �ع�ʧ��
	 */
	public void rollback() throws Exception {
		if (getAutoCommit()) {
			BaseException e = new BaseException();
			e.setErrorCode("E010005");
			throw e;
		}
		con.rollback();
	}

	/**
	 * 
	 * ��ʼ������
	 *
	 */
	
	public void beginBatch() throws Exception {
		try {
			setAutoCommit(false);
		} catch (SQLException ex) {
			logger.error("��ʼ������ʧ��");
			logger.debug(ex.toString());
			throw ex;
		}
	}

	/**
	*�������������
	*@param sql ����������
	*@return boolean �ɹ�����true,����false
	*@throws Exception 
	*/
	public boolean addBatch(String sql) throws Exception {
		try {
			update.addBatch(sql);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error("�������ݿ�������ʧ��");
			throw ex;
		}

	}

	/**
	*����������
	*
	*@return boolean �ɹ�����true,����false
	*@throws Exception 
	*/
	public boolean exeBatch() throws Exception {
		boolean success = true;
		try {
			int[] btnum = update.executeBatch();

			for (int i = 0; i < btnum.length; i++) {
				if (btnum[i] <= 0) {
					success = false;
				}
			}
			if (success) {
				con.commit();
				update.clearBatch();

			} else {
				con.rollback();
				update.clearBatch();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error("ִ�����ݿ�������ʧ��");
			throw ex;

		} finally {			
			return success;
		}
	}

}
