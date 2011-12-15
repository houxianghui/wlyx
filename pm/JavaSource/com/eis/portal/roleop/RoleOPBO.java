/*********************************************************
 * File: RoleOPBO.java
 * 
 * Version 1.0
 * 
 * Date     2005-9-24
 * 
 * Author   Chen Rong
 * 
 ********************************************************/

package com.eis.portal.roleop;

import java.util.List;

import com.eis.base.BaseBO;
import com.eis.base.BaseVO;
import com.eis.base.BaseDAO;
import com.eis.base.PageObject;
import com.eis.portal.UserContext;
import com.eis.factory.*;
import com.eis.util.*;
import com.eis.db.*;

/**
 * ˵������ɫ����Ȩ��ҵ���߼���
 * 
 */
public class RoleOPBO extends BaseBO {
	
	
	/**	 
	 * ���ؽ�ɫ��ѡ�Ĳ���Ȩ�ޣ��������м�¼������ҳ 
	 */
	public List listOPNotSelected(String role_id,UserContext user) throws Exception{
		RoleOPDAO  dao = (RoleOPDAO)BeanFactory.getBean("roleop_dao");
		return dao.listOPNotSelected(role_id,user);
	}
	
	/**	 
	 * ���ضԽ�ɫrole_idѡ���Ĳ���Ȩ��
	 */
	public List listSelected(String role_id,UserContext user) throws Exception{
		RoleOPDAO  dao2 = (RoleOPDAO)BeanFactory.getBean("roleop_dao");
		return dao2.listSelected(role_id,user);	
		
	}

	/**	 
	 * ���¶Խ�ɫrole_idѡ��Ĳ���Ȩ���޸�
	 */	
	public void update(String role_id,List list,UserContext user)  throws Exception{
		RoleOPDAO  dao = (RoleOPDAO)BeanFactory.getBean("roleop_dao");
		dao.update(role_id,list,user);	
		
		//�Ǽ��޸Ľ�ɫ����Ȩ�޲�����־		
		OpLog.Log(user,"03","u","�޸Ľ�ɫ����Ȩ��","��ɫ"+user.getRoleID());		
		
	}
	
	/**	 
	 * ɾ����ɫrole_id������ѡ����Ȩ��
	 */		
	public void delete(String role_id,UserContext user)  throws Exception{
		RoleOPDAO  dao = (RoleOPDAO)BeanFactory.getBean("roleop_dao");
		dao.delete(role_id,user);
		
		//�Ǽ�ɾ����ɫ����Ȩ�޲�����־		
		OpLog.Log(user,"03","d","ɾ����ɫ����Ȩ��","��ɫ"+user.getRoleID());				
		
	}	
	
	

	/** 
	 * ����
	 */
	public void add(BaseVO vo, UserContext user) throws Exception {

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
		
		
		//BaseDAO  dao = (BaseDAO)BeanFactory.getBean("menu_dao");
		//dao.update(vo, " where MENU_ID ='" + ((MenuVO) vo).getMenu_id()+"'");

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

		
		//BaseDAO  dao = (BaseDAO)BeanFactory.getBean("menu_dao");
		//dao.delete(dao.getDeleteSQL()+" where MENU_ID ='" + ((MenuVO) vo).getMenu_id()+"'");

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
	 * ��ѯά�����ݣ����ݲ�ѯ�������ط���������һҳ��¼
	 */
	public List list(PageObject page, UserContext user) throws Exception {

		return null;
	}

	/* 
	 * ��ѯ�˵�ά�����ݣ��������м�¼������ҳ
	 */
	public List list(UserContext user) throws Exception {
		return null;		
		
	}

	/* 
	 * ��ѯ��ϸ��Ϣ�����ص�һ��¼
	 */
	public BaseVO retrieve(BaseVO vo, UserContext user) throws Exception {		

		return null;
	}	
	
	
	/* 
	 * ����where������ѯ��ϸ��Ϣ�����ص�һ��¼
	 */
	public BaseVO retrieve(String whereClause, UserContext user) throws Exception {
		return null;
		
	}	
	

}

