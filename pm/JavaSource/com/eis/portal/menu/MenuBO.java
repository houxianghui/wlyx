/*********************************************************
 * File: MenuBO.java
 * 
 * Version 1.0
 * 
 * Date     2005-9-14
 * 
 * Author   Chen Rong
 * 
 ********************************************************/

package com.eis.portal.menu;

import java.util.*;

import com.eis.base.BaseBO;
import com.eis.base.BaseVO;
import com.eis.base.BaseDAO;
import com.eis.base.PageObject;
import com.eis.portal.UserContext;
import com.eis.factory.*;
import com.eis.portal.rolemenu.*;
import com.eis.portal.roleop.*;
import com.eis.portal.op.*;
import com.eis.util.*;
import com.eis.db.*;
import com.eis.connectionPool.*;

/**
 * ˵�����˵�����ҵ���߼���
 * 
 */
public class MenuBO extends BaseBO {

	/** 
	 * ���Ӳ˵�
	 */
	public void add(BaseVO vo, UserContext user) throws Exception {

		BaseDAO dao = (BaseDAO) BeanFactory.getBean("menu_dao");
		dao.add(vo);

		//�Ǽ����Ӳ˵�������־		
		OpLog.Log(user, "03", "c", "���Ӳ˵�", ((MenuVO) vo).getMenu_id());

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

		BaseDAO dao = (BaseDAO) BeanFactory.getBean("menu_dao");
		dao.update(vo, " where MENU_ID ='" + ((MenuVO) vo).getMenu_id() + "'");

		//�Ǽ��޸Ĳ˵�������־		
		OpLog.Log(user, "03", "u", "�޸Ĳ˵�", ((MenuVO) vo).getMenu_id());

	}

	/*
	 * �����޸�����
	 */
	public void updateList(BaseVO[] list, UserContext user) throws Exception {

	}

	/*
	 * ɾ���˵����¼��˵�����
	 */
	public void delete(BaseVO vo, UserContext user) throws Exception {

		//��ͬһ�����У�ɾ����˵����������ϵ�����б�
		java.sql.Connection con = DBPoolManager.getConnection();
		con.setAutoCommit(false);

		try {
			ArrayList list = new ArrayList();
			//��ȡ�������¼��˵����ϣ������ǰ��˵������ɵ���߻�ȡ��
			getSubMenus(((MenuVO) vo).getMenu_id(),list);
			for(int i=0;i<list.size();i++) {
				SysLog.debug("�¼��˵����:"+list.get(i).toString());
				//���˵������ɵ͸�ɾ���˵�
				deleteMenu(list.get(i).toString(),con);
			}
			

			//�ύ����
			con.commit();

			//�Ǽ�ɾ���˵�������־		
			OpLog.Log(user, "03", "d", "ɾ���˵�", ((MenuVO) vo).getMenu_id());

		} catch (Exception ex) {
			con.rollback();
		} finally {
			if (con != null)
				con.close();
		}

	}

	/*
	 * ɾ����˵��������ϵ�����й�ϵ����ɾ���˵�����
	 */
	public void deleteMenu(String menuID, java.sql.Connection con)
		throws Exception {

		//ɾ����ɫ�˵���ϵ���еļ�¼ep_role_menu
		RoleMenuDAO rolemenudao = (RoleMenuDAO) getBean("rolemenu_dao");
		rolemenudao.delete(
			"delete  from ep_role_menu  where MENU_ID ='"
				+menuID
				+ "'",
			con);

		

		//ɾ���˵��²���Ȩ�޶���(ep_op_def)�ڽ�ɫ����Ȩ�޹�ϵ���еļ�¼��ep_role_auth
		RoleOPDAO roleopdao = (RoleOPDAO) getBean("roleop_dao");
		StringBuffer sqlb =
			new StringBuffer(" delete from ep_role_auth where OP_CODE in ");
		sqlb.append(
			"( select OP_CODE from ep_op_def where MENU_ID ='"
				+menuID
				+ "' )");
		roleopdao.delete(sqlb.toString(), con);

		//ɾ������Ȩ�޶�����еļ�¼��ep_op_def
		OPDAO opdao = (OPDAO) BeanFactory.getBean("op_dao");
		opdao.delete(
			"delete from ep_op_def where MENU_ID ='"
				+ menuID
				+ "'",
			con);

		//ɾ���˵����еļ�¼��ep_menu	
		BaseDAO dao = (BaseDAO) BeanFactory.getBean("menu_dao");		
		dao.delete(
			dao.getDeleteSQL()
				+ " where MENU_ID ='"
				+ menuID
				+ "'",
			con);

	}
	

	/*
	 * �ݹ��ѯ�˵����ݣ���������֮�¸����˵����뼯��list
	 */
	public void getSubMenus(String menuID, List list) throws Exception {

		
		DBQueryUtil db = new DBQueryUtil();
		try {
			java.sql.ResultSet rs =
				db.sqlQuery(
					"select * from ep_menu  where PARENT_ID ='" + menuID + "'");
			while (rs.next()) {
				//������¼��˵����ݹ����				
				getSubMenus(rs.getString("MENU_ID"), list);
			}
			//�������˵����뼯��
			if (!list.contains(menuID))
				list.add(menuID);

		} finally {
			db.close();

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
		MenuDAO dao = (MenuDAO) BeanFactory.getBean("menu_dao");
		return dao.queryList(user);
	}

	/*
	 * ��ѯ�б����ݲ�ѯ�������ط���������һҳ��¼
	 */
	public List queryList(PageObject page, UserContext user) throws Exception {

		return null;
	}

	/* 
	 * ��ѯά�����ݣ����ݲ�ѯ�������ط���������һҳ��¼
	 */
	public List list(PageObject page, UserContext user) throws Exception {

		BaseDAO dao = (BaseDAO) BeanFactory.getBean("menu_dao");

		//ģ��ƥ������
		StringBuffer sql = new StringBuffer("select * from ep_menu where 1=1 ");
		String menu_id = (String) page.getFilter("menu_id");
		if (menu_id != null)
			sql.append(" and MENU_ID like '%" + menu_id + "%'");

		String menu_name = (String) page.getFilter("menu_name");
		if (menu_name != null)
			sql.append(" and MENU_NAME like '%" + menu_name + "%'");

		sql.append(" " + dao.getOrderBy());
		SysLog.debug(sql.toString());

		//ִ�в�ѯ���
		page.setList(dao.queryPage(page, sql.toString()));

		return null;
	}

	/* 
	 * ��ѯ�˵�ά�����ݣ��������м�¼������ҳ
	 */
	public List list(UserContext user) throws Exception {
		MenuDAO dao = (MenuDAO) BeanFactory.getBean("menu_dao");
		return dao.queryList(user);

	}

	/* 
	 * ��ѯ�˵�ά�����ݣ������û���ɫӵ��Ȩ�޵ļ�¼������ҳ
	 */
	public List listWithPerm(UserContext user) throws Exception {
		MenuDAO dao = (MenuDAO) BeanFactory.getBean("menu_dao");
		return dao.queryListWithPerm(user);

	}

	/* 
	 * ��ѯ��ϸ��Ϣ�����ص�һ��¼
	 */
	public BaseVO retrieve(BaseVO vo, UserContext user) throws Exception {

		BaseDAO dao = (BaseDAO) BeanFactory.getBean("menu_dao");
		return dao.retrieve(
			dao.getQuerySQL()
				+ " where a.MENU_ID='"
				+ ((MenuVO) vo).getMenu_id()
				+ "'");
	}

	/* 
	 * ����where������ѯ�˵���ϸ��Ϣ�����ص�һ��¼
	 */
	public BaseVO retrieve(String whereClause, UserContext user)
		throws Exception {

		//��ò˵��������ݿ���ʶ���BaseDAO
		BaseDAO dao = (BaseDAO) BeanFactory.getBean("menu_dao");
		
		//���ò�����BaseDAO��retrieve(sql){}���������ص�һ��¼��Ϣ
		BaseVO vo=dao.retrieve(dao.getQuerySQL() + " " + whereClause);
		
		//�Ǽǲ鿴�˵�������־		
		OpLog.Log(user, "03", "r", "��ѯ�˵�", ((MenuVO) vo).getMenu_id());		
		return vo;

	}

	//���ز˵�����Ϊ���ֵ�ļ�¼
	public String retrieveMax(UserContext user) throws Exception {

		MenuDAO dao = (MenuDAO) BeanFactory.getBean("menu_dao");
		return dao.retrieveMax(user);

	}

}
