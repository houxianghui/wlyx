/*********************************************************
 * File: OPBO.java
 * 
 * Version 1.0
 * 
 * Date     2005-9-20
 * 
 * Author   �� ��
 * 
 ********************************************************/

package com.eis.portal.op;

import java.util.List;

import com.eis.base.BaseBO;
import com.eis.base.BaseVO;
import com.eis.base.BaseDAO;
import com.eis.base.PageObject;
import com.eis.portal.UserContext;
import com.eis.portal.roleop.*;
import com.eis.factory.*;
import com.eis.util.*;
import com.eis.connectionPool.*;

/**
 * ˵��������Ȩ�޶���ҵ���߼���
 * 
 */
public class OPBO extends BaseBO {

	/** 
	 * ���Ӳ���Ȩ��
	 */
	public void add(BaseVO vo, UserContext user) throws Exception {

		BaseDAO dao = (BaseDAO) BeanFactory.getBean("op_dao");

		dao.add(vo);

		//�Ǽ����Ӳ���Ȩ�޶��������־		
		OpLog.Log(user, "03", "c", "���Ӳ���Ȩ�޶���", ((OPVO) vo).getOp_code());

	}

	/*
	 * ������������
	 */
	public void addList(BaseVO[] list, UserContext user) throws Exception {

	}

	/*
	 * �޸�����
	 */
	public void update(BaseVO vo, UserContext user) throws Exception {

		BaseDAO dao = (BaseDAO) BeanFactory.getBean("op_dao");
		dao.update(vo, " where OP_CODE ='" + ((OPVO) vo).getOp_code() + "'");

		//�Ǽ��޸Ĳ���Ȩ�޶��������־		
		OpLog.Log(user, "03", "u", "�޸Ĳ���Ȩ�޶���", ((OPVO) vo).getOp_code());

	}

	/*
	 * �����޸�����
	 */
	public void updateList(BaseVO[] list, UserContext user) throws Exception {

	}

	/*
	 * ɾ������
	 */
	public void delete(BaseVO vo, UserContext user) throws Exception {


		//��ͬһ�����У�ɾ�������Ȩ�ޱ��������ϵ�����б�
		java.sql.Connection con = DBPoolManager.getConnection();
		con.setAutoCommit(false);

		try {
			//ɾ������Ȩ�޽�ɫ��ϵ���м�¼��ep_role_auth
			RoleOPDAO  roleopdao = (RoleOPDAO)getBean("roleop_dao");
			roleopdao.delete("delete from ep_role_auth where OP_CODE ='" + ((OPVO)vo).getOp_code()+"'",con);

			//ɾ������Ȩ�޶�����еļ�¼ep_op_def
			BaseDAO dao = (BaseDAO) BeanFactory.getBean("op_dao");
			dao.delete(
				dao.getDeleteSQL()
					+ " where OP_CODE ='"
					+ ((OPVO) vo).getOp_code()
					+ "'",con);
			con.commit();

			//�Ǽ�ɾ������Ȩ�޶��������־		
			OpLog.Log(user, "03", "d", "ɾ������Ȩ�޶���", ((OPVO) vo).getOp_code());

		} catch (Exception ex) {
			con.rollback();
		} finally {
			if (con != null)
				con.close();
		}

	}

	/*
	 * ����ɾ������
	 */
	public void deleteList(BaseVO[] list, UserContext user) throws Exception {

	}

	/*
	 * ��ѯ�б�û�в�ѯ�������������м�¼
	 */
	public List queryList(UserContext user) throws Exception {

		return null;
	}

	/*
	 * ��ѯ�б����ݲ�ѯ�������ط���������һҳ��¼
	 */
	public List queryList(PageObject page, UserContext user) throws Exception {

		BaseDAO dao = (BaseDAO) BeanFactory.getBean("op_dao");
		String sql = "select * from ep_op_def ";
		return dao.queryList(sql);
	}

	/* 
	 * ��ѯά�����ݣ����ݲ�ѯ�������ط���������һҳ��¼
	 */
	public List list(PageObject page, UserContext user) throws Exception {

		BaseDAO dao = (BaseDAO) BeanFactory.getBean("op_dao");

		//ģ��ƥ������
		StringBuffer sql =
			new StringBuffer(" select a.*,b.MENU_NAME from ep_op_def a ");

		sql.append(" left join ep_menu b on a.MENU_ID=b.MENU_ID  ");

		sql.append(" where 1=1 ");

		String menu_id = (String) page.getFilter("menu_id");
		if (menu_id != null)
			sql.append(" and a.MENU_ID like '%" + menu_id + "%'");

		String op_code = (String) page.getFilter("op_code");
		if (op_code != null)
			sql.append(" and a.OP_CODE like '%" + op_code + "%'");

		String caption = (String) page.getFilter("caption");
		if (caption != null)
			sql.append(" and a.CAPTION like '%" + caption + "%'");

		SysLog.debug(sql.toString());

		//ִ�в�ѯ���
		page.setList(dao.queryPage(page, sql.toString()));

		return null;
	}

	/* 
	 * ��ѯά�����ݣ��������м�¼
	 */
	public List list(UserContext user) throws Exception {

		return null;
	}

	/* 
	 * ��ѯ��ϸ��Ϣ�����ص�һ��¼
	 */
	public BaseVO retrieve(BaseVO vo, UserContext user) throws Exception {

		BaseDAO dao = (BaseDAO) BeanFactory.getBean("op_dao");
		
		BaseVO v= dao.retrieve(
			dao.getQuerySQL()
				+ " where a.OP_CODE='"
				+ ((OPVO) vo).getOp_code()
				+ "'");
				
		//�Ǽ�ɾ������Ȩ�޶��������־			
		OpLog.Log(user, "03", "r", "�鿴����Ȩ�޶���", ((OPVO) vo).getOp_code());
		
		return v;
	}

	/* 
	 * ����where������ѯ��ϸ��Ϣ�����ص�һ��¼
	 */
	public BaseVO retrieve(String whereClause, UserContext user)
		throws Exception {

		return null;
	}

}
