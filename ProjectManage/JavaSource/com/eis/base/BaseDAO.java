/*********************************************************
 * File:BasePO.java
 * 
 * Version 1.0
 * 
 * Date     2005-8-31
 * Author   ����
 * 
 * Copyright (C) 2005 huateng
 * all rights reserved.
 * 
 ********************************************************/

package com.eis.base;

import java.sql.*;
import java.util.*;
import java.lang.reflect.*;

import org.apache.log4j.Logger;

import com.eis.config.*;
import com.eis.util.*;
import com.eis.connectionPool.*;
import com.eis.portal.UserContext;
import com.eis.factory.*;

/**
 * ˵�������ݿ���ʲ�ĸ���
 * 
 */

public abstract class BaseDAO {
	private static Logger logger = Logger.getLogger(BaseDAO.class);
	/**
	 * ���ݿ�����dbName
	 */
	private String dsName = null;

	/**
	 * ��������SQL
	 */
	private String insertSQL = null;

	/**
	 * �޸�����SQL
	 */
	private String updateSQL = null;

	/**
	 * ɾ������SQL
	 */
	private String deleteSQL = null;

	/**
	 * ����¼��ѯSQL
	 */
	private String querySQL = null;

	/**
	 * �б��ѯSQL
	 */
	private String listSQL = null;

	/**
	 * Ĭ�ϵ������������൱��SQL����е�ORDER BY��䡣
	 * ��ʽ�� order by user_id desc ��,ǰ������пո�
	 */
	protected String orderBy = null;

	/**
	 * ���캯��
	 */
	public BaseDAO() {
		super();
		initSQL();

	}

	/**
	 * ���캯��
	 * @param dsName ����Դ����
	 */
	public BaseDAO(String dsName) {
		super();
		initSQL();

	}

	/**
	 * ��ʼ��insertSQL��updateSQL
	 * 
	 */
	public abstract void initSQL();

	/**
	 * ���ز���SQL��䡣 
	 *
	 * @return ����SQL���
	 */
	public String getInsertSQL() {
		return insertSQL;
	}

	/**
	 * ���ز�ѯSQL��䡣
	 * 
	 * @return ��ѯSQL���
	 */
	public String getQuerySQL() {
		return querySQL;

	}

	/**
	 * ���ظ���SQL��䡣
	 * 
	 * @return ����SQL���
	 */
	public String getUpdateSQL() {
		return updateSQL;
	}

	/**
	 * ����ɾ��SQL���
	 * @return ɾ��SQL���
	 *
	 */
	public String getDeleteSQL() {
		return deleteSQL;
	}

	/**
	 * �����б��ѯSQL
	 * @return �б��ѯSQL
	 */
	public String getListSQL() {
		return listSQL;
	}

	/**
	 * ���������Ӿ�
	 * @return �����Ӿ�
	 */
	public String getOrderBy() {
		return orderBy;
	}

	/**
	 * �����������ӵ�PraparedStatement���
	 * @param str - �������ݵ�PraparedStatement���
	 */
	protected void setInsertSQL(String str) {
		insertSQL = str;
	}

	/**
	 * ���������޸ĵ�PraparedStatement���
	 * @param str - �޸����ݵ�PraparedStatement���
	 */
	protected void setUpdateSQL(String str) {
		updateSQL = str;
	}

	/**
	 * ����ɾ������SQL���
	 * @param str - ����ɾ��SQL���
	 */
	protected void setDeleteSQL(String str) {
		deleteSQL = str;
	}

	/**
	 * �����б��ѯ��SQL���
	 * @param str - �б��ѯ��SQL���
	 */
	protected void setListSQL(String str) {
		listSQL = str;
	}

	/**
	 * ���ò�ѯ��ϸ��Ϣ��SQL���
	 * @param str - ��ѯ��ϸ��Ϣ��SQL���
	 */
	protected void setQuerySQL(String str) {
		querySQL = str;
	}
	/**
	 * ������ݿ�����
	 * @return ���ݿ�����
	 * @throws Exception - �������ʧ��
	 */
	protected java.sql.Connection getConnection() throws Exception {
		Connection conn = null;
		try {
			conn =
				(dsName == null
					? (DBPoolManager.getConnection())
					: (DBPoolManager.getConnection(dsName)));
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error("BaseDAO������ݿ�����ʧ�ܣ�");
			logger.error(ex.getMessage());

			BaseException e = new BaseException();
			e.setErrorCode("E010017");
			throw e;
		}
		return conn;
	}

	/**
	 * ��ý���������ŵ�����¼������
	 * @return ����¼���� 
	 */
	public int getMaxFetchRows() {
		return Integer.parseInt(SysConfig.getProperty("maxFetchRows"));
	}

	public BaseVO retrieve(String sql,Connection conn)throws Exception{
				
		Statement stmt = null;
		ResultSet rs = null;
		BaseVO vo = null;

		try {
			stmt =
				conn.createStatement(
					ResultSet.TYPE_FORWARD_ONLY,
					ResultSet.CONCUR_READ_ONLY);
					//��ѯ����
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				vo = detail(rs);
			}

			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			SysLog.getLogger().error("���ݲ�ѯ��䣺" + sql);
			while (ex != null) {
				SysLog.getLogger().error("���ݲ�ѯ�쳣��");
				SysLog.getLogger().error("error code:" + ex.getErrorCode());
				SysLog.getLogger().error("SQL State:" + ex.getSQLState());
				SysLog.getLogger().error("�쳣��Ϣ:" + ex.getMessage());

				ex = ex.getNextException();
			}

			BaseException e = new BaseException();
			e.setErrorCode("E010016");
			throw e;

		} catch (Throwable t) {
			t.printStackTrace();

			SysLog.getLogger().error("BaseDAO ��ѯ����ʧ�ܣ�");
			SysLog.getLogger().error(t.getMessage());
			BaseException e = new BaseException();
			e.setErrorCode("E010016");
			throw e;
		} 
		return vo;
	}
	/**
	 * �����ݱ��в���ָ���ļ�¼��������ϸ��Ϣ�Ľ���������ڵ�������Ĳ�ѯ��
	 * �����Ϊ�����¼��ֻ���ص�һ����¼������Ľ����ԡ�
	 * @param sql - ��ѯ���
	 * @return ������ݶ��� 
	 * @throws Exception - ���ݲ�ѯ�쳣
	 * 
	 */
	public BaseVO retrieve(String sql) throws Exception {

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		BaseVO vo = null;

		try {
			conn = getConnection();
			stmt =
				conn.createStatement(
					ResultSet.TYPE_FORWARD_ONLY,
					ResultSet.CONCUR_READ_ONLY);
			//��ѯ����
			logger.debug(sql);
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				vo = detail(rs);
			}

			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			logger.error("���ݲ�ѯ��䣺" + sql);
			while (ex != null) {
				logger.error("���ݲ�ѯ�쳣��");
				logger.error("error code:" + ex.getErrorCode());
				logger.error("SQL State:" + ex.getSQLState());
				logger.error("�쳣��Ϣ:" + ex.getMessage());

				ex = ex.getNextException();
			}

			BaseException e = new BaseException();
			e.setErrorCode("E010016");
			throw e;

		} catch (Throwable t) {
			t.printStackTrace();

			logger.error("BaseDAO ��ѯ����ʧ�ܣ�");
			logger.error(t.getMessage());
			BaseException e = new BaseException();
			e.setErrorCode("E010016");
			throw e;
		} finally {
			try {
				//�ͷ����ݿ�����
				if (conn != null) {
					conn.close();
				}
			} catch (Exception ex) {

				ex.printStackTrace();

				logger.error("BaseDAO �ͷ����ݿ�����ʧ�ܣ�");
				logger.error(ex.getMessage());
				BaseException e = new BaseException();
				e.setErrorCode("E010019");
				throw e;
			}

		}
		return vo;

	}

	/**
	 * �����ݱ��в���ָ���ļ�¼��������ϸ��Ϣ�Ľ���������ڵ�������Ĳ�ѯ��
	 * �����Ϊ�����¼��ֻ���ص�һ����¼������Ľ����ԡ�
	 * @param sql - ��ѯ���
	 * @param method - ���fetch������
	 * @return ������ݶ��� 
	 * @throws Exception - ���ݲ�ѯ�쳣
	 * 
	 */
	public BaseVO retrieve(String sql, String method) throws Exception {

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		BaseVO vo = null;

		try {
			conn = getConnection();
			stmt =
				conn.createStatement(
					ResultSet.TYPE_FORWARD_ONLY,
					ResultSet.CONCUR_READ_ONLY);
			//��ѯ����
			logger.debug(sql);
			rs = stmt.executeQuery(sql);
			if (rs.next()) {

				Class[] cls = { ResultSet.class };
				Object[] param = { rs };
				Method mth = getClass().getDeclaredMethod(method, cls);

				vo = (BaseVO) mth.invoke(this, param);

			}

			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			logger.error("���ݲ�ѯ��䣺" + sql);

			while (ex != null) {
				logger.error("���ݲ�ѯ�쳣��");
				logger.error("error code:" + ex.getErrorCode());
				logger.error("SQL State:" + ex.getSQLState());
				logger.error("�쳣��Ϣ:" + ex.getMessage());

				ex = ex.getNextException();
			}

			BaseException e = new BaseException();
			e.setErrorCode("E010016");
			throw e;

		} catch (Throwable t) {
			t.printStackTrace();

			logger.error("BaseDAO ��ѯ����ʧ�ܣ�");
			logger.error(t.getMessage());
			BaseException e = new BaseException();
			e.setErrorCode("E010016");
			throw e;
		} finally {
			try {
				//�ͷ����ݿ�����
				if (conn != null) {
					conn.close();
				}
			} catch (Exception ex) {
				ex.printStackTrace();

				logger.error("BaseDAO �ͷ����ݿ�����ʧ�ܣ�");
				logger.error(ex.getMessage());
				BaseException e = new BaseException();
				e.setErrorCode("E010019");
				throw e;
			}

		}
		return vo;

	}

	/**
	 * ����һ����¼
	 * @param bean - Ҫ��������ݶ���
	 * @throws Exception - ����ʧ��
	 */
	public int add(BaseVO vo) throws Exception {
		Connection conn = null;

		PreparedStatement ps = null;

		int row = 0;

		try {
			conn = getConnection();
			ps = conn.prepareStatement(getInsertSQL());
			logger.debug(getInsertSQL());

			//ʵ������̬SQL
			prepareInsertStatement(ps, vo);
			row = ps.executeUpdate();

			if (ps != null) {
				ps.close();
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			logger.error("���ݿ������䣺" + ps.toString());

			while (ex != null) {
				logger.error("��������ʧ�ܣ�");
				logger.error("error code:" + ex.getErrorCode());
				logger.error("SQL State:" + ex.getSQLState());
				logger.error("�쳣��Ϣ:" + ex.getMessage());

				ex = ex.getNextException();
			}

			BaseException e = new BaseException();
			e.setErrorCode("E010011");
			throw e;

		} catch (Throwable t) {
			t.printStackTrace();

			logger.error("��������ʧ�ܣ�");
			logger.error(t.getMessage());
			BaseException e = new BaseException();
			e.setErrorCode("E010011");
			throw e;

		} finally {
			try {
				//�ͷ����ݿ�����
				if (conn != null) {
					conn.close();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				logger.error("BaseDAO �ͷ����ݿ�����ʧ�ܣ�");
				logger.error(ex.getMessage());
				BaseException e = new BaseException();
				e.setErrorCode("E010019");
				throw e;
			}

		}
		return row;
	}

	/**
	 * ͨ����������ݿ����Ӳ���һ����¼���������������
	 * @param bean - Ҫ��������ݶ���
	 * @param conn - ���ݿ�����
	 * @throws Exception - ����ʧ��
	 */
	public int add(BaseVO vo, java.sql.Connection conn) throws Exception {

		PreparedStatement ps = null;

		int row = 0;

		try {
			ps = conn.prepareStatement(getInsertSQL());
			logger.debug(getInsertSQL());

			//ʵ������̬SQL
			prepareInsertStatement(ps, vo);
			row = ps.executeUpdate();

			if (ps != null) {
				ps.close();
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			logger.error("���ݿ������䣺" + ps.toString());

			while (ex != null) {
				logger.error("��������ʧ�ܣ�");
				logger.error("error code:" + ex.getErrorCode());
				logger.error("SQL State:" + ex.getSQLState());
				logger.error("�쳣��Ϣ:" + ex.getMessage());

				ex = ex.getNextException();
			}

			BaseException e = new BaseException();
			e.setErrorCode("E010011");
			throw e;

		} catch (Throwable t) {
			t.printStackTrace();

			logger.error("��������ʧ�ܣ�");
			logger.error(t.getMessage());
			BaseException e = new BaseException();
			e.setErrorCode("E010011");
			throw e;

		}
		return row;
	}

	/**
	 * ���������¼
	 * @param list - Ҫ��������ݶ�������
	 * @throws Exception - ����ʧ��
	 */
	public int addList(BaseVO[] list) throws Exception {

		Connection conn = null;
		PreparedStatement ps = null;
		int row = 0;

		try {
			conn = getConnection();
			ps = conn.prepareStatement(getInsertSQL());

			for (int i = 0; i < list.length; i++) {
				//ʵ������̬SQL
				prepareInsertStatement(ps, list[i]);

				//�����������
				ps.addBatch();

			}

			//ִ�����ݿ����
			if (list.length >= 1) {

				int[] rows = ps.executeBatch();
				for (int j = 0; j < rows.length; j++) {
					row += rows[j];
				}
			}

			//�ر���Դ
			if (ps != null) {
				ps.close();
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			logger.error("���ݿ������䣺" + ps.toString());

			while (ex != null) {
				logger.error("������������ʧ�ܣ�");
				logger.error("error code:" + ex.getErrorCode());
				logger.error("SQL State:" + ex.getSQLState());
				logger.error("�쳣��Ϣ:" + ex.getMessage());

				ex = ex.getNextException();
			}

			BaseException e = new BaseException();
			e.setErrorCode("E010012");
			throw e;
		} catch (Throwable t) {
			t.printStackTrace();

			logger.error("������������ʧ�ܣ�");
			logger.error(t.getMessage());
			BaseException e = new BaseException();
			e.setErrorCode("E010012");
			throw e;

		} finally {
			try {
				//�ͷ����ݿ�����
				if (conn != null) {
					conn.close();
				}
			} catch (Exception ex) {
				ex.printStackTrace();

				logger.error("BaseDAO �ͷ����ݿ�����ʧ��");
				logger.error(ex.getMessage());
				BaseException e = new BaseException();
				e.setErrorCode("E010019");
				throw e;
			}

		}
		return row;

	}

	/**
	 * ͨ����������ݿ�����ִ�����������¼���������������
	 * @param list - Ҫ��������ݶ�������
	 * @param conn - ���ݿ�����
	 * @throws Exception - ����ʧ��
	 */
	public int addList(BaseVO[] list, java.sql.Connection conn)
		throws Exception {

		PreparedStatement ps = null;
		int row = 0;

		try {
			ps = conn.prepareStatement(getInsertSQL());

			for (int i = 0; i < list.length; i++) {
				//ʵ������̬SQL
				prepareInsertStatement(ps, list[i]);

				//�����������
				ps.addBatch();

			}

			//ִ�����ݿ����
			if (list.length >= 1) {

				int[] rows = ps.executeBatch();
				for (int j = 0; j < rows.length; j++) {
					row += rows[j];
				}
			}

			//�ر���Դ
			if (ps != null) {
				ps.close();
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			logger.error("���ݿ������䣺" + ps.toString());

			while (ex != null) {
				logger.error("������������ʧ�ܣ�");
				logger.error("error code:" + ex.getErrorCode());
				logger.error("SQL State:" + ex.getSQLState());
				logger.error("�쳣��Ϣ:" + ex.getMessage());

				ex = ex.getNextException();
			}

			BaseException e = new BaseException();
			e.setErrorCode("E010012");
			throw e;
		} catch (Throwable t) {
			t.printStackTrace();

			logger.error("������������ʧ�ܣ�");
			logger.error(t.getMessage());
			BaseException e = new BaseException();
			e.setErrorCode("E010012");
			throw e;

		}
		return 0;

	}

	/**
	 * ɾ������
	 * @param sql �� ����ɾ��SQL���
	 * @throws Exception - ɾ��ʧ��
	 */
	public int delete(String sql) throws Exception {

		// ɾ������
		int row = 0;

		Connection conn = null;
		Statement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			//����ɾ��������
			logger.debug("ɾ����䣺" + sql);
			row = stmt.executeUpdate(sql);

			if (stmt != null) {
				stmt.close();
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			logger.error("���ݿ������䣺" + sql);

			while (ex != null) {
				logger.error("ɾ������ʧ�ܣ�");
				logger.error("error code:" + ex.getErrorCode());
				logger.error("SQL State:" + ex.getSQLState());
				logger.error("�쳣��Ϣ:" + ex.getMessage());

				ex = ex.getNextException();
			}

			BaseException e = new BaseException();
			e.setErrorCode("E010013");
			throw e;

		} catch (Throwable t) {
			t.printStackTrace();

			logger.error("ɾ������ʧ�ܣ�");
			logger.error(t.getMessage());
			BaseException e = new BaseException();
			e.setErrorCode("E010013");
			throw e;
		} finally {
			try {
				//�ͷ����ݿ�����
				if (conn != null) {
					conn.close();
				}
			} catch (Exception ex) {
				ex.printStackTrace();

				logger.error("BaseDAO �ͷ����ݿ�����ʧ�ܣ�");
				logger.error(ex.getMessage());
				BaseException e = new BaseException();
				e.setErrorCode("E010019");
				throw e;
			}

		}
		return row;
	}

	/**
	 * ͨ����������ݿ�����ִ��ɾ������,�������������
	 * @param sql �� ����ɾ��SQL���
	 * @param conn - ���ݿ�����
	 * @throws Exception - ɾ��ʧ��
	 */
	public int delete(String sql, java.sql.Connection conn) throws Exception {

		// ɾ������
		int row = 0;

		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			//����ɾ��������
			logger.error("ɾ����䣺" + sql);
			row = stmt.executeUpdate(sql);

			if (stmt != null) {
				stmt.close();
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			logger.error("����ɾ����䣺" + sql);

			while (ex != null) {
				logger.error("ɾ������ʧ�ܣ�");
				logger.error("error code:" + ex.getErrorCode());
				logger.error("SQL State:" + ex.getSQLState());
				logger.error("�쳣��Ϣ:" + ex.getMessage());

				ex = ex.getNextException();
			}

			BaseException e = new BaseException();
			e.setErrorCode("E010013");
			throw e;

		} catch (Throwable t) {
			t.printStackTrace();

			logger.error("ɾ������ʧ�ܣ�");
			logger.error(t.getMessage());
			BaseException e = new BaseException();
			e.setErrorCode("E010013");
			throw e;
		}
		return row;
	}

	/**
	 * �������ݱ��еļ�¼��
	 * @param bean - ���ݶ���
	 * @param whereClause - ��������
	 */
	public int update(BaseVO vo, String whereClause) throws Exception {

		// ���µ���������
		int row = 0;

		Connection conn = null;

		PreparedStatement ps = null;

		try {
			conn = getConnection();
			logger.debug(getUpdateSQL()+whereClause);
			ps = conn.prepareStatement(getUpdateSQL() + whereClause);

			//ʵ������̬SQL
			prepareUpdateStatement(ps, vo);

			//ִ�и��²���
			row = ps.executeUpdate();

			if (ps != null) {
				ps.close();
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			logger.error("���ݿ������䣺" + ps.toString());

			while (ex != null) {
				logger.error("��������ʧ�ܣ�");
				logger.error("error code:" + ex.getErrorCode());
				logger.error("SQL State:" + ex.getSQLState());
				logger.error("�쳣��Ϣ:" + ex.getMessage());

				ex = ex.getNextException();
			}

			BaseException e = new BaseException();
			e.setErrorCode("E010014");
			throw e;

		} catch (Throwable t) {
			t.printStackTrace();

			logger.error("��������ʧ�ܣ�");
			logger.error(t.getMessage());
			BaseException e = new BaseException();
			e.setErrorCode("E010014");
			throw e;

		} finally {
			try {
				//�ͷ����ݿ�����
				if (conn != null) {
					conn.close();
				}
			} catch (Exception ex) {
				ex.printStackTrace();

				logger.error("BaseDAO �ͷ����ݿ�����ʧ�ܣ�");
				logger.error(ex.getMessage());
				BaseException e = new BaseException();
				e.setErrorCode("E010019");
				throw e;
			}
		}
		return row;
	}

	/**
	 * ͨ����������ݿ����Ӹ������ݱ��еļ�¼�����������������
	 * @param bean - ���ݶ���
	 * @param conn - ���ݿ�����
	 * @param whereClause - ��������
	 */
	public int update(BaseVO vo, String whereClause, java.sql.Connection conn)
		throws Exception {

		// ���µ���������
		int row = 0;

		PreparedStatement ps = null;

		try {

			ps = conn.prepareStatement(getUpdateSQL() + whereClause);
			logger.debug(getUpdateSQL()+whereClause);
			//ʵ������̬SQL
			prepareUpdateStatement(ps, vo);

			//ִ�и��²���
			row = ps.executeUpdate();

			if (ps != null) {
				ps.close();
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			logger.error("���ݿ������䣺" + ps.toString());

			while (ex != null) {
				logger.error("��������ʧ�ܣ�");
				logger.error("error code:" + ex.getErrorCode());
				logger.error("SQL State:" + ex.getSQLState());
				logger.error("�쳣��Ϣ:" + ex.getMessage());

				ex = ex.getNextException();
			}

			BaseException e = new BaseException();
			e.setErrorCode("E010014");
			throw e;

		} catch (Throwable t) {
			t.printStackTrace();

			logger.error("��������ʧ�ܣ�");
			logger.error(t.getMessage());
			BaseException e = new BaseException();
			e.setErrorCode("E010014");
			throw e;

		}
		return row;
	}

	/**
	 * ִ�����ݸ��²���������SQLֱ���ɲ���ָ����
	 * @param sql - ���ݿ����SQL���
	 * @throws Exception - ����ʧ��
	 */
	public int executeUpdate(String sql) throws Exception {

		// ��������
		int row = 0;

		Connection conn = null;

		Statement statement = null;

		try {
			conn = getConnection();
			statement = conn.createStatement();
			logger.debug(sql);
			//���ظ��µļ�¼����
			row = statement.executeUpdate(sql);

			if (statement != null) {
				statement.close();
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			logger.error("���ݿ������䣺" + sql);

			while (ex != null) {
				logger.error("���ݿ����ʧ�ܣ�");
				logger.error("error code:" + ex.getErrorCode());
				logger.error("SQL State:" + ex.getSQLState());
				logger.error("�쳣��Ϣ:" + ex.getMessage());

				ex = ex.getNextException();
			}

			BaseException e = new BaseException();
			e.setErrorCode("E010015");
			throw e;

		} catch (Throwable t) {
			t.printStackTrace();

			logger.error("���ݿ����ʧ�ܣ�");
			logger.error(t.getMessage());
			BaseException e = new BaseException();
			e.setErrorCode("E010015");
			throw e;

		} finally {

			try {
				//�ͷ����ݿ�����
				if (conn != null) {
					conn.close();
				}
			} catch (Exception ex) {
				ex.printStackTrace();

				logger.error("BaseDAO �ͷ����ݿ�����ʧ�ܣ�");
				logger.error(ex.getMessage());
				BaseException e = new BaseException();
				e.setErrorCode("E010019");
				throw e;
			}
		}

		return row;
	}

	/**
	 * ͨ����������ݿ�����ִ�����ݸ��²���������SQLֱ���ɲ���ָ�������������������
	 * @param sql - ���ݿ����SQL���
	 * @param conn - ���ݿ�����
	 * @throws Exception - ����ʧ��
	 */
	public int executeUpdate(String sql, java.sql.Connection conn)
		throws Exception {

		// ��������
		int row = 0;

		Statement statement = null;

		try {

			statement = conn.createStatement();
			logger.debug(sql);
			//���ظ��µļ�¼����
			row = statement.executeUpdate(sql);
			logger.debug("query finished");
			if (statement != null) {
				statement.close();
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			logger.error("���ݿ������䣺" + sql);

			while (ex != null) {
				logger.error("���ݿ����ʧ�ܣ�");
				logger.error("error code:" + ex.getErrorCode());
				logger.error("SQL State:" + ex.getSQLState());
				logger.error("�쳣��Ϣ:" + ex.getMessage());

				ex = ex.getNextException();
			}

			BaseException e = new BaseException();
			e.setErrorCode("E010015");
			throw e;

		} catch (Throwable t) {
			t.printStackTrace();

			logger.error("���ݿ����ʧ�ܣ�");
			logger.error(t.getMessage());
			BaseException e = new BaseException();
			e.setErrorCode("E010015");
			throw e;

		}

		return row;
	}

	/**
	 * ���������־�
	 * @param orderBy - �����־�
	 */
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	/**
	 * ��ѯ����������ش���ҳ��Ϣ��list
	 * @param startPosition - ��ʼ��¼λ��
	 * @param endPosition - ��ֹ��¼λ��
	 * @return һҳ����
	 * @throws SQLException
	 * 
	 */
	private java.util.List queryPage(
		int startPosition,
		int endPosition,
		java.sql.ResultSet rs)
		throws Throwable {

		//�����¼��Ϊ0������
		if (endPosition <= 0)
			return null;

		ArrayList list = new ArrayList(endPosition - startPosition + 1);

		//�������λ
		rs.absolute(startPosition);
		for (int i = startPosition; i <= endPosition; i++) {

			list.add(retrieveResult(rs));
			rs.next();

		}

		return list;

	}

	/**
	 * ��ѯ����������ش���ҳ��Ϣ��list
	 * @param startPosition - ��ʼ��¼λ��
	 * @param endPosition - ��ֹ��¼λ��
	 * @param method - ���fetch������
	 * @return һҳ����
	 * @throws SQLException
	 * 
	 */
	protected java.util.List queryPage(
		int startPosition,
		int endPosition,
		java.sql.ResultSet rs,
		String method)
		throws Throwable {

		//�����¼��Ϊ0������
		if (endPosition <= 0)
			return null;

		ArrayList list = new ArrayList(endPosition - startPosition + 1);

		//�������λ
		rs.absolute(startPosition);
		for (int i = startPosition; i <= endPosition; i++) {

			Class[] cls = { ResultSet.class };
			Object[] param = { rs };
			Method mth = getClass().getDeclaredMethod(method, cls);

			list.add(mth.invoke(this, param));

			rs.next();

		}

		return list;

	}

	/**
	 * ��ѯ����������ش���ҳ��Ϣ��list
	 * @param page - ��ҳ����
	 * @param sql - ��ѯ���
	 * @return һҳ����
	 * @throws Exception
	 * 
	 */
	public java.util.List queryPage(PageObject page, String sql)
		throws Exception {

		//��ʼλ����Action����и�ֵ����	
		int startPosition = page.getStartPosition();

		int endPosition;

		List list = null;

		Connection conn = null;

		try {
			conn = getConnection();

			Statement stmt =
				conn.createStatement(
					ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_READ_ONLY);

			logger.debug(sql);

			ResultSet rs = stmt.executeQuery(sql);
			logger.debug("query finished");
			//��ѯ�������ܼ�¼��
			rs.last();

			page.setTotalRecord(rs.getRow());
			page.setMaxRecords(rs.getRow());


			endPosition = page.getEndPosition();

			if (endPosition <= 0) {
				page.setCurPage(0);
				page.setStartPosition(0);

			}

			list = queryPage(startPosition, endPosition, rs);

			if (rs != null)
				rs.close();

			if (stmt != null)
				stmt.close();

		} catch (SQLException ex) {
			ex.printStackTrace();
			logger.error("���ݷ�����䣺" + sql);

			while (ex != null) {
				logger.error("BaseDAO��ѯ��ҳ���ʧ�ܣ�");
				logger.error("error code:" + ex.getErrorCode());
				logger.error("SQL State:" + ex.getSQLState());
				logger.error("�쳣��Ϣ:" + ex.getMessage());

				ex = ex.getNextException();
			}

			BaseException e = new BaseException();
			e.setErrorCode("E010018");
			throw e;

		} catch (Throwable t) {
			t.printStackTrace();

			logger.error("BaseDAO��ѯ��ҳ���ʧ�ܣ�");
			logger.error(t.getMessage());

			BaseException e = new BaseException();
			e.setErrorCode("E010018");
			throw e;
		} finally {
			try {
				//�ͷ����ݿ�����
				if (conn != null) {
					conn.close();
				}
			} catch (Exception ex) {
				ex.printStackTrace();

				logger.error("BaseDAO �ͷ����ݿ�����ʧ�ܣ�");
				logger.error(ex.getMessage());
				BaseException e = new BaseException();
				e.setErrorCode("E010019");
				throw e;
			}
		}

		page.setList(list);

		return list;

	}

	public java.util.List queryForwardPage(PageObject page, String sql) throws Exception 
	{
		//��ʼλ����Action����и�ֵ����	
		int startPosition = page.getStartPosition();

		int endPosition;

		logger.debug("��ҳ��ѯ��䣺" + sql);

		List list = null;

		Connection conn = null;

		try 
		{
			conn = getConnection();

			ResultSet rs;
			
			Statement stmt =
				conn.createStatement(
					ResultSet.TYPE_FORWARD_ONLY,
					ResultSet.CONCUR_READ_ONLY);

			//exclude the ORDER BY clause
			int iob = sql.toLowerCase().indexOf("order by");
			String countSql;
			if (iob == -1)
				countSql = "select count(*) from ("+sql+") c";
			else
				countSql = "select count(*) from ("+sql.substring(0, iob)+") c";
				 
			//��ѯ���������	
			logger.debug(countSql);		
			rs = stmt.executeQuery(countSql);
			logger.debug("query finished");
			rs.next();
			page.setTotalRecord(rs.getInt(1));
			page.setMaxRecords(rs.getInt(1));
			rs.close();

			rs = stmt.executeQuery(sql);

			endPosition = page.getEndPosition();

			if (endPosition <= 0)
			{
				page.setCurPage(0);
				page.setStartPosition(0);

			}
			else
			{
				list = new ArrayList(endPosition - startPosition + 1);

				//�������λ
				for (int i=0;i<startPosition;i++)
					rs.next();
				for (int i = startPosition; i <= endPosition; i++) 
				{
					list.add(retrieveResult(rs));
					rs.next();
				}
			}

			if (rs != null)
				rs.close();

			if (stmt != null)
				stmt.close();

		} catch (SQLException ex) {
			ex.printStackTrace();
			logger.error("���ݷ�����䣺" + sql);

			while (ex != null) {
				logger.error("BaseDAO��ѯ��ҳ���ʧ�ܣ�");
				logger.error("error code:" + ex.getErrorCode());
				logger.error("SQL State:" + ex.getSQLState());
				logger.error("�쳣��Ϣ:" + ex.getMessage());

				ex = ex.getNextException();
			}

			BaseException e = new BaseException();
			e.setErrorCode("E010018");
			throw e;

		} catch (Throwable t) {
			t.printStackTrace();

			logger.error("BaseDAO��ѯ��ҳ���ʧ�ܣ�");
			logger.error(t.getMessage());

			BaseException e = new BaseException();
			e.setErrorCode("E010018");
			throw e;
		} finally {
			try {
				//�ͷ����ݿ�����
				if (conn != null) {
					conn.close();
				}
			} catch (Exception ex) {
				ex.printStackTrace();

				logger.error("BaseDAO �ͷ����ݿ�����ʧ�ܣ�");
				logger.error(ex.getMessage());
				BaseException e = new BaseException();
				e.setErrorCode("E010019");
				throw e;
			}
		}

		page.setList(list);

		return list;

	}
	/**
	 * @author houxh 2008-3-18
	 * @param page
	 * @param sql
	 * @return
	 * @throws Exception
	 */
	public java.util.List queryLargePageByForward(PageObject page,String sql)throws Exception{
		return queryForwardPage(page,sql,getMaxFetchRows());
	}
	/**
     * @author houxh 2008-1-2
     * ��ø��������Ĳ�ѯ���
     * 
     * @param page
     * @param sql
     * @param count
     * @return
     * @throws Exception 
     */
    public java.util.List queryForwardPage(PageObject page, String sql,int count) throws Exception 
	{
		int startPosition = page.getStartPosition();

		int endPosition;

		logger.debug("��ҳ��ѯ��䣺" + sql);

		List list = null;

		Connection conn = null;

		try 
		{
			conn = getConnection();

			ResultSet rs;
			
			Statement stmt =
				conn.createStatement(
					ResultSet.TYPE_FORWARD_ONLY,
					ResultSet.CONCUR_READ_ONLY);

			//exclude the ORDER BY clause
			int iob = sql.toLowerCase().indexOf("order by");
			String countSql;
			if (iob == -1)
				countSql = "select count(*) from ("+sql+") c";
			else
				countSql = "select count(*) from ("+sql.substring(0, iob)+") c";
				 
			//��ѯ���������		
			logger.debug(countSql);	
			rs = stmt.executeQuery(countSql);
			logger.debug("query finished");
			rs.next();
			int row = rs.getInt(1);
			page.setTotalRecord(count);
			
			SysLog.debug("�ܼ�¼����" + row);
			rs.close();
			
			page.setMaxRecords(row);
			page.setMaxFetchRow(count);
			page.setTotalRecord((row < 0)? 0: ((row > count) ? count : row));
			stmt.execute("set rowcount "+count+" ");
			logger.debug(sql);
			rs = stmt.executeQuery(sql);
			logger.debug("query finished");
			//���÷����ܼ�¼��
			endPosition = page.getEndPosition();
			
			if (endPosition <= 0)
			{
				page.setCurPage(0);
				page.setStartPosition(0);

			}
			else
			{
				list = new ArrayList(endPosition - startPosition + 1);

				//�������λ
				for (int i=0;i<startPosition;i++)
					rs.next();
				for (int i = startPosition; i <= endPosition; i++) 
				{
					list.add(retrieveResult(rs));
					rs.next();
				}
			}
			stmt.execute(" set rowcount 0");
			if (rs != null)
				rs.close();

			if (stmt != null)
				stmt.close();

		} catch (SQLException ex) {
			ex.printStackTrace();
			logger.error("���ݷ�����䣺" + sql);

			while (ex != null) {
				logger.error("BaseDAO��ѯ��ҳ���ʧ�ܣ�");
				logger.error("error code:" + ex.getErrorCode());
				logger.error("SQL State:" + ex.getSQLState());
				logger.error("�쳣��Ϣ:" + ex.getMessage());

				ex = ex.getNextException();
			}

			BaseException e = new BaseException();
			e.setErrorCode("E010018");
			throw e;

		} catch (Throwable t) {
			t.printStackTrace();

			logger.error("BaseDAO��ѯ��ҳ���ʧ�ܣ�");
			logger.error(t.getMessage());

			BaseException e = new BaseException();
			e.setErrorCode("E010018");
			throw e;
		} finally {
			try {
				//�ͷ����ݿ�����
				if (conn != null) {
					conn.close();
				}
			} catch (Exception ex) {
				ex.printStackTrace();

				logger.error("BaseDAO �ͷ����ݿ�����ʧ�ܣ�");
				logger.error(ex.getMessage());
				BaseException e = new BaseException();
				e.setErrorCode("E010019");
				throw e;
			}
		}

		page.setList(list);

		return list;

	}
	/**
	 * ��ѯ����������ش���ҳ��Ϣ��list,�����ڴ��������Ĳ�ѯ
	 * @param page - ��ҳ����
	 * @param sql - ��ѯ���
	 * @return һҳ����
	 * @throws Exception
	 * 
	 */
	public java.util.List queryLargePage(PageObject page, String sql)
		throws Exception {

		//��ʼλ����Action����и�ֵ����	
		int startPosition = page.getStartPosition();

		int endPosition;

		List list = null;

		Connection conn = null;

		try {
			conn = getConnection();

			Statement stmt =
				conn.createStatement(
					ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_READ_ONLY);

			//���ý��������				
			stmt.execute("set rowcount 1");
			
			int iob = sql.toLowerCase().indexOf("order by");
			String countSql;
			if (iob == -1){
				countSql = "select count(1) " + sql.substring(sql.indexOf("from"));				
			}else{
				countSql = "select count(1) " + sql.substring(sql.indexOf("from"),iob);				
			}
			logger.debug(countSql);
			//��ѯ���������			
			ResultSet rs = stmt.executeQuery(countSql);

			logger.debug("query end");

			int maxRecord = -1;

			if (rs.next()) {
				maxRecord = rs.getInt(1);
			}

			
						
			stmt.execute("set rowcount " + getMaxFetchRows());

			logger.debug(sql);
			rs = stmt.executeQuery(sql);
			logger.debug("query finished");
			

			//��ѯ�����ܼ�¼��
			page.setMaxRecords((maxRecord < 0) ? 0 : maxRecord);

			//���÷����ܼ�¼��
			int maxRows = getMaxFetchRows(); //ϵͳ�����ѯ���ܼ�¼��
			page.setTotalRecord(
				(maxRecord < 0)
					? 0
					: ((maxRecord > maxRows) ? maxRows : maxRecord));

			endPosition = page.getEndPosition();

			if (endPosition <= 0) {
				page.setCurPage(0);
				page.setStartPosition(0);
			}

			list = queryPage(startPosition, endPosition, rs);

			//�ͷŶԲ�ѯ���������
			stmt.execute("set rowcount 0");

			if (rs != null)
				rs.close();

			if (stmt != null)
				stmt.close();

		} catch (SQLException ex) {
			ex.printStackTrace();
			logger.error("���ݷ�����䣺" + sql);

			while (ex != null) {
				logger.error("BaseDAO��ѯ��ҳ���ʧ�ܣ�");
				logger.error("error code:" + ex.getErrorCode());
				logger.error("SQL State:" + ex.getSQLState());
				logger.error("�쳣��Ϣ:" + ex.getMessage());

				ex = ex.getNextException();
			}

			BaseException e = new BaseException();
			e.setErrorCode("E010018");
			throw e;

		} catch (Throwable t) {
			t.printStackTrace();

			logger.error("BaseDAO��ѯ��ҳ���ʧ�ܣ�");
			logger.error(t.getMessage());

			BaseException e = new BaseException();
			e.setErrorCode("E010018");
			throw e;
		} finally {
			try {
				//�ͷ����ݿ�����
				if (conn != null) {
					conn.close();
				}
			} catch (Exception ex) {
				ex.printStackTrace();

				logger.error("BaseDAO �ͷ����ݿ�����ʧ�ܣ�");
				logger.error(ex.getMessage());
				BaseException e = new BaseException();
				e.setErrorCode("E010019");
				throw e;
			}
		}

		page.setList(list);

		return list;

	}

	/**
	 * ��ѯ����������ش���ҳ��Ϣ��list
	 * @param page - ��ҳ����
	 * @param sql - ��ѯ���
	 * @param method - ���fetch������
	 * @return һҳ����
	 * @throws Exception
	 * 
	 */
	public java.util.List queryPage(PageObject page, String sql, String method)
		throws Exception {

		//��ʼλ����Action����и�ֵ����	
		int startPosition = page.getStartPosition();

		int endPosition;

		logger.debug("��ҳ��ѯ��䣺" + sql);

		List list = null;

		Connection conn = null;

		try {
			conn = getConnection();
			Statement stmt =
				conn.createStatement(
					ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_READ_ONLY);

			//stmt.setMaxRows(getMaxFetchRows());
			logger.debug(sql);
			ResultSet rs = stmt.executeQuery(sql);
			logger.debug("query finished");
			//��ѯ�������ܼ�¼��
			rs.last();

			page.setTotalRecord(rs.getRow());
			page.setMaxRecords(rs.getRow());

			endPosition = page.getEndPosition();

			if (endPosition <= 0) {
				page.setCurPage(0);
				page.setStartPosition(0);

			}

			list = queryPage(startPosition, endPosition, rs, method);

			if (rs != null)
				rs.close();

			if (stmt != null)
				stmt.close();

		} catch (SQLException ex) {

			ex.printStackTrace();
			logger.error("���ݲ�ѯ��䣺" + sql);

			while (ex != null) {
				logger.error("BaseDAO��ѯ��ҳ���ʧ�ܣ�");
				logger.error("error code:" + ex.getErrorCode());
				logger.error("SQL State:" + ex.getSQLState());
				logger.error("�쳣��Ϣ:" + ex.getMessage());

				ex = ex.getNextException();
			}

			BaseException e = new BaseException();
			e.setErrorCode("E010018");
			throw e;

		} catch (Throwable t) {
			t.printStackTrace();

			logger.error("BaseDAO��ѯ��ҳ���ʧ�ܣ�");
			logger.error(t.getMessage());

			BaseException e = new BaseException();
			e.setErrorCode("E010018");
			throw e;
		} finally {
			try {
				//�ͷ����ݿ�����
				if (conn != null) {
					conn.close();
				}
			} catch (Exception ex) {
				ex.printStackTrace();

				logger.error("BaseDAO �ͷ����ݿ�����ʧ�ܣ�");
				logger.error(ex.getMessage());
				BaseException e = new BaseException();
				e.setErrorCode("E010019");
				throw e;
			}
		}

		page.setList(list);

		return list;

	}

	/**
	 * ��ѯ����������ش���ҳ��Ϣ��list
	 * @param page - ��ҳ����
	 * @param sql - ��ѯ���
	 * @param method - ���fetch������
	 * @return һҳ����
	 * @throws Exception
	 * 
	 */
	public java.util.List queryLargePage(
		PageObject page,
		String sql,
		String method)
		throws Exception {

		//��ʼλ����Action����и�ֵ����	
		int startPosition = page.getStartPosition();

		int endPosition;

		logger.debug("��ҳ��ѯ��䣺" + sql);

		List list = null;

		Connection conn = null;

		try {
			conn = getConnection();
			Statement stmt =
				conn.createStatement(
					ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_READ_ONLY);

			//���ý��������
			stmt.execute("set rowcount 1");
			
			int iob = sql.toLowerCase().indexOf("order by");
			String countSql;
			if (iob == -1){
				countSql = "select count(1) " + sql.substring(sql.indexOf("from"));				
			}else{
				countSql = "select count(1) " + sql.substring(sql.indexOf("from"),iob);				
			}
			
			logger.debug(countSql);
			//��ѯ���������			
			ResultSet rs = stmt.executeQuery(countSql);
			logger.debug("query finished");
			

			int maxRecord = -1;

			if (rs.next()) {
				maxRecord = rs.getInt(1);
			}

			
			stmt.execute("set rowcount " + getMaxFetchRows());

			logger.debug(sql);
			rs = stmt.executeQuery(sql);
			logger.debug("query finished");

			//��ѯ�����ܼ�¼��
			page.setMaxRecords((maxRecord < 0) ? 0 : maxRecord);

			//���÷����ܼ�¼��
			int maxRows = getMaxFetchRows(); //ϵͳ�����ѯ���ܼ�¼��
			page.setTotalRecord(
				(maxRecord < 0)
					? 0
					: ((maxRecord > maxRows) ? maxRows : maxRecord));

			endPosition = page.getEndPosition();

			if (endPosition <= 0) {
				page.setCurPage(0);
				page.setStartPosition(0);

			}

			list = queryPage(startPosition, endPosition, rs, method);

			//�ͷŶԲ�ѯ���������			
			stmt.execute("set rowcount 0");

			if (rs != null)
				rs.close();

			if (stmt != null)
				stmt.close();

		} catch (SQLException ex) {
			ex.printStackTrace();
			logger.error("���ݲ�ѯ��䣺" + sql);

			while (ex != null) {
				logger.error("BaseDAO��ѯ��ҳ���ʧ�ܣ�");
				logger.error("error code:" + ex.getErrorCode());
				logger.error("SQL State:" + ex.getSQLState());
				logger.error("�쳣��Ϣ:" + ex.getMessage());

				ex = ex.getNextException();
			}

			BaseException e = new BaseException();
			e.setErrorCode("E010018");
			throw e;

		} catch (Throwable t) {
			t.printStackTrace();

			logger.error("BaseDAO��ѯ��ҳ���ʧ�ܣ�");
			logger.error(t.getMessage());

			BaseException e = new BaseException();
			e.setErrorCode("E010018");
			throw e;
		} finally {
			try {
				//�ͷ����ݿ�����
				if (conn != null) {
					conn.close();
				}
			} catch (Exception ex) {
				ex.printStackTrace();

				logger.error("BaseDAO �ͷ����ݿ�����ʧ�ܣ�");
				logger.error(ex.getMessage());
				BaseException e = new BaseException();
				e.setErrorCode("E010019");
				throw e;
			}
		}

		page.setList(list);

		return list;

	}

	//-----------------���·�����Ҫ����----------------------

	/**
	 * ����insert Statement
	 * @param ps -��Ҫִ�е�PreparedStatement
	 * @param bean - ���ݶ��� 
	 * @throws Exception - �����쳣
	 */
	public abstract void prepareInsertStatement(
		PreparedStatement ps,
		BaseVO vo)
		throws Exception;

	/**
	 * ����update Statement
	 * @param ps -��Ҫִ�е�PreparedStatement
	 * @param bean - ���ݶ��� 
	 * @throws Exception - ���ݲ�ѯ�쳣
	 */
	public abstract void prepareUpdateStatement(
		PreparedStatement ps,
		BaseVO vo)
		throws Exception;

	/**
	 * ɾ������
	 * @param bean - ���ݶ�������ƴװwhere���� 
	 * @throws Exception - ���ݲ�ѯ�쳣
	 */
	public abstract void delete(BaseVO vo) throws Exception;

	/**
	 * ��ѯȫ�����ݣ����ض��¼
	 * @param sql - ��ѯSQL���
	 * @return ȫ�����ݣ�����ҳ
	 * @throws Exception - ���ݲ�ѯ�쳣
	 */
	public final java.util.List queryList(String sql) throws Exception {

		ArrayList list;

		Connection conn = null;

		try {
			conn = getConnection();

			Statement stmt =
				conn.createStatement(
					ResultSet.TYPE_FORWARD_ONLY,
					ResultSet.CONCUR_READ_ONLY);

			list = new ArrayList();
			logger.debug(sql);
			ResultSet rs = stmt.executeQuery(sql);
			logger.debug("query finished");
			while (rs.next()) {

				list.add(retrieveResult(rs));

			}

			if (rs != null)
				rs.close();

			if (stmt != null)
				stmt.close();

		} catch (SQLException ex) {
			ex.printStackTrace();
			logger.error("���ݲ�ѯ��䣺" + sql);

			while (ex != null) {
				logger.error("BaseDAO��ѯ���ʧ�ܣ�");
				logger.error("error code:" + ex.getErrorCode());
				logger.error("SQL State:" + ex.getSQLState());
				logger.error("�쳣��Ϣ:" + ex.getSQLState());
				ex = ex.getNextException();
			}

			BaseException e = new BaseException();
			e.setErrorCode("E010018");
			throw e;

		} catch (Throwable t) {
			t.printStackTrace();

			logger.error("BaseDAO��ѯ���ʧ�ܣ�");
			logger.error(t.getMessage());

			BaseException e = new BaseException();
			e.setErrorCode("E010018");
			throw e;
		} finally {
			try {
				//�ͷ����ݿ�����
				if (conn != null) {
					conn.close();
				}
			} catch (Exception ex) {
				logger.error("BaseDAO �ͷ����ݿ�����ʧ�ܣ�");
				logger.error(ex.getMessage());
				BaseException e = new BaseException();
				e.setErrorCode("E010019");
				throw e;
			}
		}
		return list;
	}

	/**
	 * ��ѯȫ�����ݣ�����С���޶���¼�������м�¼
	 * @param sql - ��ѯSQL���
	 * @param maxFetchSize - ����¼��
	 * @return ȫ�����ݣ�����ҳ
	 * @throws Exception - ���ݲ�ѯ�쳣
	 */
	public java.util.List queryList(String sql, int maxFetchSize)
		throws Exception {

		ArrayList list;

		Connection conn = null;

		try {
			conn = getConnection();
			Statement stmt =
				conn.createStatement(
					ResultSet.TYPE_FORWARD_ONLY,
					ResultSet.CONCUR_READ_ONLY);

			list = new ArrayList();

			stmt.execute("set rowcount " + maxFetchSize);
			logger.debug(sql);
			ResultSet rs = stmt.executeQuery(sql);
			logger.debug("query finished");
			
			while (rs.next()) {

				list.add(retrieveResult(rs));

			}

			stmt.execute("set rowcount 0");

			if (rs != null)
				rs.close();

			if (stmt != null)
				stmt.close();

		} catch (SQLException ex) {
			ex.printStackTrace();
			logger.error("���ݲ�ѯ��䣺" + sql);

			while (ex != null) {
				logger.error("BaseDAO��ѯ���ʧ�ܣ�");
				logger.error("error code:" + ex.getErrorCode());
				logger.error("SQL State:" + ex.getSQLState());
				logger.error("�쳣��Ϣ:" + ex.getSQLState());
				ex = ex.getNextException();
			}

			BaseException e = new BaseException();
			e.setErrorCode("E010018");
			throw e;

		} catch (Throwable t) {
			t.printStackTrace();

			logger.error("BaseDAO��ѯ���ʧ�ܣ�");
			logger.error(t.getMessage());

			BaseException e = new BaseException();
			e.setErrorCode("E010018");
			throw e;
		} finally {
			try {
				//�ͷ����ݿ�����
				if (conn != null) {
					conn.close();
				}
			} catch (Exception ex) {
				logger.error("BaseDAO �ͷ����ݿ�����ʧ�ܣ�");
				logger.error(ex.getMessage());
				BaseException e = new BaseException();
				e.setErrorCode("E010019");
				throw e;
			}
		}
		return list;
	}

	/**
	 * ��ѯ����������ش���ҳ��Ϣ��list
	 *
	 * @param sql - ��ѯ���
	 * @param method - ���fetch������
	 * @param maxFetchSize - ���ص�����¼��
	 * @return һҳ����
	 * @throws Exception
	 * 
	 */
	public java.util.List queryList(
		String sql,
		String method,
		int maxFetchSize)
		throws Exception {

		ArrayList list;

		Connection conn = null;

		try {
			conn = getConnection();
			Statement stmt =
				conn.createStatement(
					ResultSet.TYPE_FORWARD_ONLY,
					ResultSet.CONCUR_READ_ONLY);

			list = new ArrayList();
			stmt.execute("set rowcount " + maxFetchSize);
			logger.debug(sql);
			ResultSet rs = stmt.executeQuery(sql);
			logger.debug("query finished");
			while (rs.next()) {

				Class[] cls = { ResultSet.class };
				Object[] param = { rs };
				Method mth = getClass().getDeclaredMethod(method, cls);

				list.add(mth.invoke(this, param));

			}

			stmt.execute("set rowcount 0");

			if (rs != null)
				rs.close();

			if (stmt != null)
				stmt.close();

		} catch (SQLException ex) {
			ex.printStackTrace();
			logger.error("���ݲ�ѯ��䣺" + sql);

			while (ex != null) {
				logger.error("BaseDAO��ѯ���ʧ�ܣ�");
				logger.error("error code:" + ex.getErrorCode());
				logger.error("SQL State:" + ex.getSQLState());
				logger.error("�쳣��Ϣ:" + ex.getSQLState());
				ex = ex.getNextException();
			}

			BaseException e = new BaseException();
			e.setErrorCode("E010018");
			throw e;

		} catch (Throwable t) {
			t.printStackTrace();

			logger.error("BaseDAO��ѯ���ʧ�ܣ�");
			logger.error(t.getMessage());

			BaseException e = new BaseException();
			e.setErrorCode("E010018");
			throw e;
		} finally {
			try {
				//�ͷ����ݿ�����
				if (conn != null) {
					conn.close();
				}
			} catch (Exception ex) {
				logger.error("BaseDAO �ͷ����ݿ�����ʧ�ܣ�");
				logger.error(ex.getMessage());
				BaseException e = new BaseException();
				e.setErrorCode("E010019");
				throw e;
			}
		}
		return list;
	}

	/**
	 * ��ѯ��¼����
	 * 
	 * @param sql - ��ѯ���
	 * @return ���������ļ�¼���� 
	 * @throws Exception - ���ݲ�ѯ�쳣
	 * 
	 */
	public int queryCount(String sql) throws Exception {

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		int rows = 0;

		try {
			conn = getConnection();
			stmt =
				conn.createStatement(
					ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			//��ѯ����
			logger.debug(sql);
			rs = stmt.executeQuery(sql);
			logger.debug("query finished");
			rs.last();
			rows = rs.getRow();

			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			logger.error("���ݲ�ѯ��䣺" + sql);

			while (ex != null) {
				logger.error("���ݲ�ѯ�쳣��");
				logger.error("error code:" + ex.getErrorCode());
				logger.error("SQL State:" + ex.getSQLState());
				logger.error("�쳣��Ϣ:" + ex.getMessage());

				ex = ex.getNextException();
			}

			BaseException e = new BaseException();
			e.setErrorCode("E010016");
			throw e;

		} catch (Throwable t) {
			t.printStackTrace();

			logger.error("BaseDAO ��ѯ���������ʧ�ܣ�");
			logger.error(t.getMessage());
			BaseException e = new BaseException();
			e.setErrorCode("E010016");
			throw e;
		} finally {
			try {
				//�ͷ����ݿ�����
				if (conn != null) {
					conn.close();
				}
			} catch (Exception ex) {

				ex.printStackTrace();

				logger.error("BaseDAO �ͷ����ݿ�����ʧ�ܣ�");
				logger.error(ex.getMessage());
				BaseException e = new BaseException();
				e.setErrorCode("E010019");
				throw e;
			}

		}
		return rows;

	}

	/** 
	 * ִ�����ݿ��ѯ���ܣ�����Ψһ��¼��int���ֶΣ�ʹ��ʱ��ע�⣩
	 * @param sql ��׼��ѯsql���
	 * @return ��ѯ���
	 * @throws SQLException - ���ݲ�ѯʧ��
	 */
	public int sqlQuerySingleInt(String sql) throws Exception {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		int rows = 0;

		try {
			conn = getConnection();
			stmt =
				conn.createStatement(
					ResultSet.TYPE_FORWARD_ONLY,
					ResultSet.CONCUR_READ_ONLY);
			//��ѯ����
			logger.debug(sql);
			rs = stmt.executeQuery(sql);
			logger.debug("query finished");
			if (rs.next())
				rows = rs.getInt(1);

			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			logger.error("���ݲ�ѯ��䣺" + sql);

			while (ex != null) {
				logger.error("���ݲ�ѯ�쳣��");
				logger.error("error code:" + ex.getErrorCode());
				logger.error("SQL State:" + ex.getSQLState());
				logger.error("�쳣��Ϣ:" + ex.getMessage());

				ex = ex.getNextException();
			}

			BaseException e = new BaseException();
			e.setErrorCode("E010016");
			throw e;

		} catch (Throwable t) {
			t.printStackTrace();

			logger.error("BaseDAO ��ѯ���������ʧ�ܣ�");
			logger.error(t.getMessage());
			BaseException e = new BaseException();
			e.setErrorCode("E010016");
			throw e;
		} finally {
			try {
				//�ͷ����ݿ�����
				if (conn != null) {
					conn.close();
				}
			} catch (Exception ex) {

				ex.printStackTrace();

				logger.error("BaseDAO �ͷ����ݿ�����ʧ�ܣ�");
				logger.error(ex.getMessage());
				BaseException e = new BaseException();
				e.setErrorCode("E010019");
				throw e;
			}

		}
		return rows;

	}

	/**
	 * ��ѯ��һ��¼�Ľ��
	 * 
	 * @param sql - ��ѯ���
	 * @return ���������Ľ�� 
	 * @throws Exception - ���ݲ�ѯ�쳣
	 * 
	 */
	public String querySingle(String sql) throws Exception {

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String result = null;

		try {
			conn = getConnection();
			stmt =
				conn.createStatement(
					ResultSet.TYPE_FORWARD_ONLY,
					ResultSet.CONCUR_READ_ONLY);

			//��ѯ����			
			logger.debug(sql);
			rs = stmt.executeQuery(sql);
			logger.debug("query finished");
			if (rs.next())
				result = rs.getString(1);

			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			logger.error("���ݲ�ѯ��䣺" + sql);

			while (ex != null) {
				logger.error("���ݲ�ѯ�쳣��");
				logger.error("error code:" + ex.getErrorCode());
				logger.error("SQL State:" + ex.getSQLState());
				logger.error("�쳣��Ϣ:" + ex.getMessage());

				ex = ex.getNextException();
			}

			BaseException e = new BaseException();
			e.setErrorCode("E010016");
			throw e;

		} catch (Throwable t) {
			t.printStackTrace();

			logger.error("BaseDAO ��ѯ����¼���ʧ�ܣ�");
			logger.error(t.getMessage());
			BaseException e = new BaseException();
			e.setErrorCode("E010016");
			throw e;
		} finally {
			try {
				//�ͷ����ݿ�����
				if (conn != null) {
					conn.close();
				}
			} catch (Exception ex) {

				ex.printStackTrace();

				logger.error("BaseDAO �ͷ����ݿ�����ʧ�ܣ�");
				logger.error(ex.getMessage());
				BaseException e = new BaseException();
				e.setErrorCode("E010019");
				throw e;
			}

		}
		return result;

	}

	/**
	 * ����bean ID���ʵ������
	 * @param name - bean ID	 
	 * @return bean��ʵ������
	 *
	 */
	public Object getBean(String name) throws Exception {

		return BeanFactory.getBean(name);
	}

	/**
	 * ʵ�ַ�ҳ��ѯ������һҳ��¼���÷�����BO�����queryList()����Ĭ�ϵĵ��÷���
	 * @param page - ��ҳ����
	 * @param user - �û���������Ϣ
	 * @return - һҳ����
	 * @throws Exception - ���ݲ�ѯ�쳣
	 */
	public abstract java.util.List queryList(PageObject page, UserContext user)
		throws Exception;

	/**
	 * ʵ�ַ�ҳ��ѯ������һҳ��¼,�÷�����BO�����list()����Ĭ�ϵĵ��÷���
	 * @param page - ��ҳ����
	 * @param user - �û���������Ϣ
	 * @return - һҳ����
	 * @throws Exception - ���ݲ�ѯ�쳣
	 */
	public abstract java.util.List list(PageObject page, UserContext user)
		throws Exception;

	/**
	 * ����һ���б��¼���÷���ֻ���б��ֶζ�Ӧ����Ϣ�ֵ
	 * @return �����б����	
	 * @throws Exception - ���ݲ�ѯ�쳣
	 */
	public abstract BaseVO retrieveResult(java.sql.ResultSet rs)
		throws Exception;

	/**
	 * �����ݱ��в���ָ���ļ�¼��������ϸ��Ϣ����������ڵ�������Ĳ�ѯ��
	 * �����Ϊ�����¼��ֻ���ص�һ����¼������Ľ����ԡ�
	 * @param rs - �����
	 * @return ������ݶ��� 
	 * @throws Exception - ���ݲ�ѯ�쳣
	 * 
	 */
	public abstract BaseVO detail(java.sql.ResultSet rs) throws Exception;

	//-----------------��������ģ�����----------------------//

}
