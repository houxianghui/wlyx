
/*********************************************************
 * File: DBQueryUtil.java
 * 
 * Version 1.0
 * 
 * Date     2005-9-1
 * 
 * Author   xin yong
 * 
 ********************************************************/

package com.eis.db;

import java.sql.*;

import org.apache.log4j.Logger;

import com.eis.base.*;
import com.eis.connectionPool.*;

import com.eis.util.*;

/**
 * ˵�������ݿ��ѯ������
 * 
 */
public class DBQueryUtil {
	private static Logger logger = Logger.getLogger(DBQueryUtil.class);

	Connection con = null;
	Statement query = null;

	ResultSet rs = null;
	

	/**
	 * ���캯��
	 */
	public DBQueryUtil() {
		try {
			//��ȡ����
			con = DBPoolManager.getConnection();
			query =
				con.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);

		} catch (Exception ex) {
			logger.error("DBQueryUtil��ʵ����ʧ��");
			logger.error(ex.toString());
		}

	}

	/**
	 * ���캯��
	 * @param dsName ����Դ����
	 */
	public DBQueryUtil(String dsName) {

		try {

			//��ȡ����
			con = DBPoolManager.getConnection(dsName);
			query =
				con.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);

		} catch (Exception ex) {
			logger.error("DBQueryUtil��ʵ����ʧ��");
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

		ResultSet result = query.executeQuery(sql);

		return result;

	}
	
	/** 
	 * ִ�����ݿ��ѯ���ܣ����ز�ѯ���
	 * @param  sql ���ݿ��ѯ���
	 * @return ResultSet ��ѯ�����
	 * @throws ��ѯʧ��
	 */
	public ResultSet sqlQueryR(String sql) throws Exception {

		if (null == sql || sql.trim().length() <= 0) {
			return null;
		}
		
		Statement stmt =
			con.createStatement(
				ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY);

		ResultSet result = stmt.executeQuery(sql);

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
			rs = query.executeQuery(sql);
			rs.last();
			rows = rs.getRow();
			return rows;
		} catch (SQLException ex) {
			logger.error("��ѯ�����ʧ��");
			logger.error(ex.toString());
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
			rs = query.executeQuery(sql);
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
	 * ִ�����ݿ��ѯ���ܣ������б��¼�ĵ�һ����¼�ĵ�һ���ֶΣ�ʹ��ʱ��ע�⣩
	 * @param sql ��׼��ѯsql���
	 * @return ��ѯ���
	 * @throws SQLException - ���ݲ�ѯʧ��
	 */
	public String sqlQueryFirst(String sql) throws Exception {
		String str = null;

		if (null == sql || sql.trim().length() <= 0) {
			return str;
		}

		try {	
			query.execute("set rowcount 1");		
			rs = query.executeQuery(sql);
			if (rs.next())
				str = rs.getString(1);
				
			query.execute("set rowcount 0");
			return str;

		} catch (SQLException ex) {
			ex.printStackTrace();
			logger.error("��ѯ�����ʧ��");
			BaseException e = new BaseException();
			e.setErrorCode("E010001");
			throw e;
		}
	}
	
	/** 
	 * ִ�����ݿ��ѯ���ܣ�����ĳ���ֶ�Ψһ��¼��int���ֶΣ�ʹ��ʱ��ע�⣩
	 * @param sql ��׼��ѯsql���
	 * @return ��ѯ���
	 * @throws SQLException - ���ݲ�ѯʧ��
	 */
	public int sqlQuerySingleInt(String sql) throws Exception {
		int i = 0;

		if (null == sql || sql.trim().length() <= 0) {
			return i;
		}

		try {
			rs = query.executeQuery(sql);
			if (rs.next())
				i = rs.getInt(1);
			return i;

		} catch (SQLException ex) {
			ex.printStackTrace();
			logger.error("��ѯ�����ʧ��");
			BaseException e = new BaseException();
			e.setErrorCode("E010001");
			throw e;
		}
	}



	/** 
	 * �ر����ݿ����ӣ��ͷ���Դ  
	 */
	public void close() {
		try {
			if (rs != null)
				rs.close();
			if (query != null)
				query.close();

		} catch (SQLException ex) {
			logger.error("�ͷ���Դʧ�ܣ�");
			logger.error(ex.toString());

		} finally {
			try {
				if (con != null)
					con.close();

			} catch (Exception ex) {
				logger.error("DBQueryUtil���ͷ����ݿ�����ʧ�ܣ�");
				logger.error(ex.getMessage());

			}
		}

	}

}