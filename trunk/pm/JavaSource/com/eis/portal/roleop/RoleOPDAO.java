/*********************************************************
 * File: RoleOPDAO.java
 * 
 * Version 1.0
 * 
 * Date     2005-9-24
 * 
 * Author   �� ��
 * 
 ********************************************************/

package com.eis.portal.roleop;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.*;
import java.util.*;

import com.eis.base.BaseDAO;
import com.eis.base.BaseVO;
import com.eis.base.PageObject;
import com.eis.portal.UserContext;

import com.eis.util.*;
import com.eis.db.*;


import com.eis.base.*;

/**
 * ˵������ɫ����Ȩ�޹������ݷ��ʶ���
 * 
 */
public class RoleOPDAO extends BaseDAO {

	/**
	 * 
	 */
	public RoleOPDAO() {
		super();

	}

	/**
	 * @param dsName
	 */
	public RoleOPDAO(String dsName) {
		super(dsName);

	}

	/*
	 * 
	 */
	public void initSQL() {
		setInsertSQL("insert into ep_role_auth(ROLE_ID,OP_CODE) values(?,?)");
		setUpdateSQL("update ep_role_auth set ROLE_ID=?,OP_CODE=? ");
		setQuerySQL(" select * from ep_role_auth ");
		setListSQL(" select * from ep_role_auth ");
		setDeleteSQL(" delete  from ep_role_auth ");

		setOrderBy(" order by ROLE_ID");
	}

	/*
	 * ��ִ���������ӵ�PreparedStatement�еĲ������и�ֵ
	 */
	public void prepareInsertStatement(PreparedStatement ps, BaseVO bean)
		throws Exception {
		RoleOPVO vo = (RoleOPVO) bean;

		ps.setString(1, vo.getRole_id());
		ps.setString(2, vo.getOp_code());

	}

	/*	 
	 * ��ִ�������޸ĵ�PreparedStatement�еĲ������и�ֵ
	 */

	public void prepareUpdateStatement(PreparedStatement ps, BaseVO bean)
		throws Exception {
		RoleOPVO vo = (RoleOPVO) bean;

		ps.setString(1, vo.getCaption());
		ps.setString(2, vo.getOp_code());
	}

	/**	 
	 * ���ؽ�ɫ��ѡ����Ȩ�ޣ�����ɫ����ѡ��û��ѡ��Ĳ���Ȩ��
	 */

	public List listOPNotSelected(String role_id, UserContext user)
		throws Exception {
		/**�ӽ�ɫ�˵���ϵ��ep_role_menu����ɫȨ�޶����ep_op_def,
		 * ��ɫȨ�޹�ϵ��ep_role_auth��,ѡ���ɫ�ܹ�ѡ��ġ�
		 * ��ûѡ��Ĳ���Ȩ��
		 */
		String sql = " select a.ROLE_ID,b.OP_CODE,b.CAPTION  ";
		sql = sql + "  from ep_role_menu a,ep_op_def b  ";
		sql = sql + "  where a.MENU_ID=b.MENU_ID  ";
		sql = sql + "  and a.ROLE_ID='" + role_id + "' ";
		sql = sql + " and b.OP_CODE not in ";
		sql = sql + " (select c.OP_CODE from ep_role_auth c ";
		sql = sql + " where c.ROLE_ID='" + role_id + "') ";
		return queryList(sql);
	}

	/**	 
	 * ���ؽ�ɫrole_id�Ĳ���Ȩ��
	 */

	public List listSelected(String role_id, UserContext user)
		throws Exception {
		/**�ӽ�ɫȨ�޹�ϵ��ep_role_auth�У�ROLE_ID,OP_CODE����
		 * ѡ���ɫ�Ѿ�ѡ��Ĳ���Ȩ��,�����������Խ�ɫȨ�޶����ep_op_def
		 ***/
		String sql = " select a.ROLE_ID,a.OP_CODE,b.CAPTION ";
		sql = sql + "  from ep_role_auth a,ep_op_def b  ";
		sql = sql + "  where a.OP_CODE=b.OP_CODE  "; //��������
		sql = sql + "  and  a.ROLE_ID = '" + role_id + "'";
		return queryList(sql);

	}

	/**���½�ɫ����Ȩ��*/
	public void update(String role_id, List list, UserContext user)
		throws Exception {
		/**ɾ���Խ�ɫrole_idԭ��ѡ��Ĳ���Ȩ�ޣ�
		 * ���µĲ���Ȩ��ѡ���������뵽��ɫȨ�޹�ϵ��ep_role_auth��
		 * **/

		//ɾ����ɫrole_idԭ�����в���Ȩ��			
		delete(role_id, user);
		SysLog.debug("after delete from ��ɫȨ�޹�ϵ��");

		/**�������õĽ�ɫ����Ȩ�޹�ϵ��ӵ����ݿ���**/		

		if (list != null) {

			Connection conn = null;
			PreparedStatement ps = null;

			try {
				conn = getConnection();
				ps = conn.prepareStatement(getInsertSQL());

				Iterator iter = list.iterator();
				while (iter.hasNext()) {
					String op_code = (String) iter.next();
					
					ps.setString(1, role_id);
					ps.setString(2, op_code);
					ps.executeUpdate();

				}
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException ex) {

				while (ex != null) {
					SysLog.error("������������ʧ�ܣ�");
					SysLog.error("error code:" + ex.getErrorCode());
					SysLog.error("SQL State:" + ex.getSQLState());
					SysLog.error("�쳣��Ϣ:" + ex.getSQLState());
					ex = ex.getNextException();
				}

				BaseException e = new BaseException();
				e.setErrorCode("E010012");
				throw e;
			} catch (Throwable t) {

				SysLog.error("������������ʧ�ܣ�");
				SysLog.error(t.getMessage());
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
					SysLog.error("BaseDAO �ͷ����ݿ�����ʧ��");
					SysLog.error(ex.getMessage());
					BaseException e = new BaseException();
					e.setErrorCode("E010019");
					throw e;
				}

			}
		}

	}

	/**ɾ����ɫ����Ȩ��*/
	public void delete(String role_id, UserContext user) throws Exception {
		/**ɾ���Խ�ɫrole_id����ѡ��Ĳ���Ȩ�ޣ�
		 * ɾ����ɫȨ�޹�ϵ��ep_role_auth�������й�role_id�ļ�¼
		 * **/
		delete("delete from ep_role_auth where ROLE_ID='" + role_id + "'");
		//Ҫ����row��	

	}

	/*
	 * 
	 */
	public void delete(BaseVO bean) throws Exception {

	}


	/*
	 * 
	 */
	public List queryList(PageObject page, UserContext user) throws Exception {

		return null;
	}
	/*
	* 
	*/
	public List queryList(UserContext user) throws Exception {
		//String sql="select * from ep_menu ";		
		//return queryList(sql);
		return null;
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
		RoleOPVO vo = new RoleOPVO();
		vo.setRole_id(rs.getString("ROLE_ID"));
		vo.setOp_code(rs.getString("OP_CODE"));
		vo.setCaption(rs.getString("CAPTION"));

		return vo;
	}

	/* 
	 * ��ȡ��ϸ��Ϣ
	 */
	public BaseVO detail(ResultSet rs) throws Exception {
		RoleOPVO vo = new RoleOPVO();
		vo.setRole_id(rs.getString("ROLE_ID"));
		vo.setCaption(rs.getString("CAPTION"));
		vo.setOp_code(rs.getString("OP_CODE"));

		return vo;
	}

	/* 
	 * 
	 */
	public List list(PageObject page, UserContext user) throws Exception {

		return null;
	}

}
