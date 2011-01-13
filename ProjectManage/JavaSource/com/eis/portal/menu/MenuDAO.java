/*********************************************************
 * File: MenuDAO.java
 * 
 * Version 1.0
 * 
 * Date     2005-9-14
 * 
 * Author   �� ��
 * 
 ********************************************************/

package com.eis.portal.menu;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.sql.*;

import com.eis.base.BaseDAO;
import com.eis.base.BaseVO;
import com.eis.base.PageObject;
import com.eis.portal.UserContext;

import com.eis.util.*;
import com.eis.db.*;

import java.util.*;
import com.eis.base.*;

/**
 * ˵�����˵��������ݶ���
 * 
 */
public class MenuDAO extends BaseDAO {

	/**
	 * 
	 */
	public MenuDAO() {
		super();

	}

	/**
	 * @param dsName
	 */
	public MenuDAO(String dsName) {
		super(dsName);

	}

	/*
	 * 
	 */
	public void initSQL() {
		setInsertSQL("insert into ep_menu(MENU_ID,PARENT_ID,MENU_NAME,MENU_MARK,MENU_LEVEL,LIST_ORDER,MENU_URL) values(?,?,?,?,?,?,?)");
		setUpdateSQL("update ep_menu set PARENT_ID=?,MENU_NAME=?,MENU_MARK=?,MENU_LEVEL=?,LIST_ORDER=?,MENU_URL=? ");

		//���ò�ѯ�˵���ϸ���	
		//setQuerySQL(" select * from ep_menu ");	
		String s =
			"select (CASE WHEN b.MENU_NAME IS NULL THEN '��' ELSE b.MENU_NAME END) as PARENT_NAME , a.* "
				+ " from ep_menu a  "
				+ " left join ep_menu b on a.PARENT_ID=b.MENU_ID ";
		setQuerySQL(s);

		setListSQL(" select * from ep_menu ");
		setDeleteSQL(" delete  from ep_menu ");
		setOrderBy(" order by MENU_LEVEL");
	}

	/*
	 * ��ִ���������ӵ�PreparedStatement�еĲ������и�ֵ
	 */
	public void prepareInsertStatement(PreparedStatement ps, BaseVO bean)
		throws Exception {
		MenuVO vo = (MenuVO) bean;

		ps.setString(1, vo.getMenu_id());
		ps.setString(2, vo.getParent_id());
		ps.setString(3, vo.getMenu_name());
		ps.setString(4, vo.getMenu_mark());
		ps.setInt(5, vo.getMenu_level());
		ps.setInt(6, vo.getList_order());

		if (vo.getMenu_url() != null) {
			ps.setString(7, vo.getMenu_url());
		} else {
			ps.setNull(7, Types.VARCHAR);
		}

	}

	/*	 
	 * ��ִ�������޸ĵ�PreparedStatement�еĲ������и�ֵ
	 */

	public void prepareUpdateStatement(PreparedStatement ps, BaseVO bean)
		throws Exception {
		MenuVO vo = (MenuVO) bean;

		ps.setString(1, vo.getParent_id());
		ps.setString(2, vo.getMenu_name());
		ps.setString(3, vo.getMenu_mark());
		ps.setInt(4, vo.getMenu_level());
		ps.setInt(5, vo.getList_order());

		if (vo.getMenu_url() != null) {
			ps.setString(6, vo.getMenu_url());
		} else {
			ps.setNull(6, Types.VARCHAR);
		}
	}

	/*
	 * 
	 */
	public void delete(BaseVO bean) throws Exception {

	}


	/*
	 *��ѯ����ɫȨ�޵Ĳ˵���¼�б�����ҳ 
	 */
	public List queryListWithPerm(String sql) throws Exception {

		ArrayList list = null;

		Connection conn = null;

		try {
			conn = getConnection();
			Statement stmt =
				conn.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);

			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				list = new ArrayList();
			}
			rs.previous();

			while (rs.next()) {
				list.add(retrieveResult(rs));
			}

			if (rs != null)
				rs.close();

			if (stmt != null)
				stmt.close();

		} catch (SQLException ex) {

			while (ex != null) {
				SysLog.error("BaseDAO��ѯ���ʧ�ܣ�");
				SysLog.error("error code:" + ex.getErrorCode());
				SysLog.error("SQL State:" + ex.getSQLState());
				SysLog.error("�쳣��Ϣ:" + ex.getSQLState());
				ex = ex.getNextException();
			}

			BaseException e = new BaseException();
			e.setErrorCode("E010018");
			throw e;

		} catch (Throwable t) {
			SysLog.error("BaseDAO��ѯ���ʧ�ܣ�");
			SysLog.error(t.getMessage());

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
				SysLog.error("BaseDAO �ͷ����ݿ�����ʧ�ܣ�");
				SysLog.error(ex.getMessage());
				BaseException e = new BaseException();
				e.setErrorCode("E010019");
				throw e;
			}
		}
		return list;
	}

	/*
	 * 
	 */
	public List queryList(PageObject page, UserContext user) throws Exception {

		return null;
	}

	/*
	* ����ȫ���˵��б�
	*/
	public List queryList(UserContext user) throws Exception {
		String sql = "select * from ep_menu  order by MENU_LEVEL,LIST_ORDER";
		return queryList(sql);
	}

	/*
	* 
	*/
	public List queryListWithPerm(UserContext user) throws Exception {
		String role_id = user.getRoleID();
		
		//����û����������Ƿ��е�����Ȩ�޿���
		String sql = null;
		DBQueryUtil db = new DBQueryUtil();
		
		try {
			
						sql =
							"select * from ep_menu a,ep_role_menu b  "
								+ " where a.MENU_ID=b.MENU_ID and b.ROLE_ID='"
								+ role_id
								+ "' ";

					
								
			sql+=" order by a.MENU_LEVEL,a.LIST_ORDER";
			SysLog.debug(sql);
			return queryList(sql);
					
			
		} catch (Exception ex){
			throw ex;
		} finally {
			db.close();
		}
				
		
		
	}

	/*
	 * 
	 */
	public void retrieve(BaseVO bean) throws Exception {

	}

	/*
	 * ��ȡ�б����ݵ�һ����¼
	 */

	public BaseVO retrieveResult(ResultSet rs) throws Exception {
		MenuVO vo = new MenuVO();
		vo.setMenu_id(rs.getString("MENU_ID"));
		vo.setParent_id(rs.getString("PARENT_ID"));
		vo.setMenu_name(rs.getString("MENU_NAME"));
		vo.setMenu_mark(rs.getString("MENU_MARK"));
		vo.setMenu_level(rs.getInt("MENU_LEVEL"));
		vo.setList_order(rs.getInt("LIST_ORDER"));
		vo.setMenu_url(rs.getString("MENU_URL"));

		return vo;
	}

	/* 
	 * ��ȡ��ϸ��Ϣ
	 */
	public BaseVO detail(ResultSet rs) throws Exception {
		MenuVO vo = new MenuVO();

		vo.setMenu_id(rs.getString("MENU_ID"));
		vo.setParent_id(rs.getString("PARENT_ID"));
		vo.setParent_name(rs.getString("PARENT_NAME"));
		vo.setMenu_name(rs.getString("MENU_NAME"));
		vo.setMenu_mark(rs.getString("MENU_MARK"));
		vo.setMenu_level(rs.getInt("MENU_LEVEL"));
		vo.setList_order(rs.getInt("LIST_ORDER"));
		vo.setMenu_url(rs.getString("MENU_URL"));

		return vo;
	}

	/* 
	 * 
	 */
	public List list(PageObject page, UserContext user) throws Exception {

		return null;
	}
	//���ز˵�����Ϊ���ֵ�ļ�¼
	public String retrieveMax(UserContext user) throws Exception {
		DBQueryUtil db = new DBQueryUtil();
		
		try {
			return db.sqlQuerySingle("select MAX(MENU_ID) from ep_menu");
		} finally{
			db.close();
		}
	}



}
