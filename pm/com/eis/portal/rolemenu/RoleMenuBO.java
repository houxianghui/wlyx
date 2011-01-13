/*********************************************************
 * File: RoleMenuBO.java
 * 
 * Version 1.0
 * 
 * Date     2005-9-21
 * 
 * Author   Chen Rong
 * 
 ********************************************************/

package com.eis.portal.rolemenu;

import java.util.*;
import java.sql.*;

import com.eis.base.BaseBO;
import com.eis.base.BaseVO;
import com.eis.base.BaseDAO;
import com.eis.base.PageObject;
import com.eis.portal.UserContext;
import com.eis.factory.*;
import com.eis.util.*;

import com.eis.connectionPool.*;

/**
 * ˵������ɫ�˵�Ȩ�޹���ҵ���߼���
 * 
 */
public class RoleMenuBO extends BaseBO {

	/** 
	 * ����
	 */
	public void add(BaseVO vo, UserContext user) throws Exception {

		BaseDAO dao = (BaseDAO) BeanFactory.getBean("rolemenu_dao");

		dao.add(vo);

	}

	/*
	 * ������������
	 */
	public void addList(BaseVO[] list, UserContext user) throws Exception {

	}
	

	/**	 
	 * ���¶Խ�ɫrole_idѡ���Ĳ˵�Ȩ���޸�,������������
	 */	
	public void update(String role_id,List list,UserContext user)  throws Exception{
		
		//�������
		Connection connts = DBPoolManager.getConnection();
		connts.setAutoCommit(false);
	
		try{
			
			RoleMenuDAO  dao = (RoleMenuDAO)BeanFactory.getBean("rolemenu_dao");
			//�ҵ�list�͸��ײ˵�����ɵļ���
			List menulist=dao.getSelectMenuSet(list);//�¼�	
			
			/**���ȥ����ѡ�Ĳ˵�,���²���Ȩ�����ѷ������ɫ�ģ�
			 * ����Ҫ����ɾ����Щ����Ȩ��		
			 **/		
			dao.deleteOPs(role_id,menulist,user,connts);//��������
			
	
			//�Խ�ɫ�˵�����и��²���				
			dao.update(role_id,menulist,user,connts);	
				
			connts.commit();
				
		}catch(Exception ex){			
			connts.rollback();
		}finally{
			if(connts!=null)
			connts.close();
		}	
		
		//�Ǽ��޸Ľ�ɫ�˵�Ȩ�޲�����־		
		OpLog.Log(user,"03","u","�޸Ľ�ɫ�˵�Ȩ��","��ɫ"+user.getRoleID());	
		
	}

	/*
	 * �޸�����
	 */
	public void update(BaseVO vo, UserContext user) throws Exception {

		//BaseDAO  dao = (BaseDAO)BeanFactory.getBean("role_dao");
		//dao.update(vo, " where ROLE_ID ='" + ((RoleVO)vo).getRole_id()+"'");

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
		
	}

	public void delete(String role_id, List list,UserContext user) throws Exception {

		//�������
		Connection connts = DBPoolManager.getConnection();
		connts.setAutoCommit(false);
		
		try{	
			
			RoleMenuDAO  rolemenudao = (RoleMenuDAO)BeanFactory.getBean("rolemenu_dao");
			rolemenudao.deleteOPs(role_id,list,user,connts);	
	
			BaseDAO dao = (BaseDAO) BeanFactory.getBean("rolemenu_dao");//Ҫ��������
			dao.delete(
				"delete  from ep_role_menu  where ROLE_ID='" + role_id + "'",connts);
				
				
			connts.commit();
				
		}catch(Exception ex){			
			connts.rollback();
		}finally{
			if(connts!=null)
			connts.close();
		}						
			
		//�Ǽ�ɾ����ɫ�˵�Ȩ�޲�����־		
		OpLog.Log(user,"03","d","ɾ����ɫ�˵�Ȩ��","��ɫ"+user.getRoleID());	

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
	 * ��ѯ��ɫ����
	 */
	public String queryRoleName(String role_id) throws Exception {
		BaseDAO dao = (BaseDAO) BeanFactory.getBean("role_dao");
		return dao.querySingle("select ROLE_NAME from ep_role  where ROLE_ID='"+role_id+"'");
		
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

		BaseDAO dao = (BaseDAO) BeanFactory.getBean("role_dao");

		//ģ��ƥ������
		StringBuffer sql = new StringBuffer("select * from ep_role where 1=1 ");
		String role_id = (String) page.getFilter("role_id");
		if (role_id != null)
			sql.append(" and ROLE_ID like '%" + role_id + "%'");

		String role_name = (String) page.getFilter("role_name");
		if (role_name != null)
			sql.append(" and ROLE_NAME like '%" + role_name + "%'");

		SysLog.debug(sql.toString());

		//ִ�в�ѯ���
		page.setList(dao.queryPage(page, sql.toString()));

		return null;
	}

	/* 
	* ��ѯ�˵�ά�����ݣ��������м�¼������ҳ
	*/
	public List list(UserContext user) throws Exception {

		return null;

	}

	/* 
	 * ��ѯ�˵�ά�����ݣ��������м�¼������ҳ
	 */
	public List list(String role_id, UserContext user) throws Exception {
		RoleMenuDAO dao = (RoleMenuDAO) BeanFactory.getBean("rolemenu_dao");		
		List l = dao.selectMenuList(role_id, user);	
		
		//�Ǽǲ鿴������־		
		OpLog.Log(user,"03","r","�鿴��ɫ�˵�Ȩ��",role_id);
		return l;

	}

	/* 
	 * ��ѯ��ϸ��Ϣ�����ص�һ��¼
	 */
	public BaseVO retrieve(BaseVO vo, UserContext user) throws Exception {

		BaseDAO dao = (BaseDAO) BeanFactory.getBean("rolemenu_dao");
		return dao.retrieve(
			dao.getQuerySQL()
				+ " where MENU_ID='"
				+ ((RoleMenuVO) vo).getMenu_id()
				+ "'");
	}

	/* 
	 * ����where������ѯ��ϸ��Ϣ�����ص�һ��¼
	 */
	public BaseVO retrieve(String whereClause, UserContext user)
		throws Exception {

		return null;
	}

}
