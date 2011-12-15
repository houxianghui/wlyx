/*********************************************************
 * File: RoleBO.java
 * 
 * Version 1.0
 * 
 * Date     2005-9-12
 * 
 * Author   Chen Rong
 * 
 ********************************************************/

package com.eis.portal.role;

import java.util.List;
import java.sql.*;

import com.eis.base.BaseBO;
import com.eis.base.BaseVO;
import com.eis.base.BaseDAO;
import com.eis.base.PageObject;
import com.eis.portal.UserContext;

import com.eis.util.*;
import com.eis.portal.user.*;
import com.eis.portal.rolemenu.*;
import com.eis.portal.roleop.*;
import com.eis.connectionPool.*;


/**
 * ˵������ɫ����ҵ���߼���
 * 
 */
public class RoleBO extends BaseBO {

	/** 
	 * ���ӹ�������
	 */
	public void add(BaseVO vo, UserContext user) throws Exception {
		
		
		BaseDAO  dao = (BaseDAO)getBean("role_dao");
		
		dao.add(vo);
		
		//�Ǽ����ӽ�ɫ������־		
		OpLog.Log(user,"03","c","���ӽ�ɫ",((RoleVO)vo).getRole_id());

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
		
		
		BaseDAO  dao = (BaseDAO)getBean("role_dao");
		dao.update(vo, " where ROLE_ID ='" + ((RoleVO)vo).getRole_id()+"'");
		
		//�Ǽ��޸Ľ�ɫ������־		
		OpLog.Log(user,"03","u","�޸Ľ�ɫ",((RoleVO)vo).getRole_id());

	}

	/*
	 * �����޸�����
	 */
	public void updateList(BaseVO[] list, UserContext user) throws Exception {

	}
	
	/*
	* �ϳ���ɫ��
	* vo����ɫvo
	* user:�û����� 
	 */
	public void terminate(BaseVO vo, UserContext user) throws Exception {
		

		
		/**
		 * ���ɫ���й����ı����:ep_user,ep_user_role,ep_role_auth,ep_role_menu,ep_check_role,
		 * ����ͬһ������,
		 * ����ɫ������еĽ�ɫ���÷ϳ�״̬����"0"ֵ,
		 * ����ɫ�û���ϵ������˽�ɫ��صļ�¼ɾ��,
		 * ���û������Դ˽�ɫΪ����ɫ�ļ�¼������ɫ�ֶ�����Ϊ"0"ֵ,
		 * **/
		
		Connection conn = DBPoolManager.getConnection();
		conn.setAutoCommit(false);
		
		try{

			
			/**���½�ɫ�����
			 * ����ɫ����Ϊ�ϳ�״̬����"0"ֵ */			
			RoleDAO  dao = (RoleDAO)getBean("role_dao");
			dao.executeUpdate("update ep_role set STAT='0',REG_DT='"+((RoleVO)vo).getReg_dt()+"',USER_ID='"+((RoleVO)vo).getUser_id()+"'  where ROLE_ID ='" + ((RoleVO)vo).getRole_id()+"'",conn);
			
			/**ɾ����ɫ�û���ϵ������˽�ɫ��صļ�¼ */
			UserRoleDAO userroleDAO=(UserRoleDAO)getBean("userrole_dao");			
			userroleDAO.delete("delete from ep_user_role where ROLE_ID='"+((RoleVO)vo).getRole_id()+"'",conn);
			
			/**�����û������Դ�Ϊ����ɫ�ļ�¼������ɫ�ֶ�����Ϊ��"0"ֵ */
			UserDAO userDAO=(UserDAO)getBean("user_dao");				
			userDAO.executeUpdate("update ep_user set ROLE_ID='0' where ROLE_ID='"+((RoleVO)vo).getRole_id()+"'",conn);	
			
			/**ɾ����ɫ����Ȩ�ޱ�����˽�ɫ��صļ�¼ */
			RoleOPDAO roleOpDAO=(RoleOPDAO)getBean("roleop_dao");		
			roleOpDAO.delete("delete from ep_role_auth where ROLE_ID='"+((RoleVO)vo).getRole_id()+"'",conn);
			
			/**ɾ����ɫ�˵���ϵ������˽�ɫ��صļ�¼ */
			RoleMenuDAO roleMenuDAO=(RoleMenuDAO)getBean("rolemenu_dao");
			roleMenuDAO.delete("delete from ep_role_menu where ROLE_ID='"+((RoleVO)vo).getRole_id()+"'",conn);	
			
			/**ɾ�����˽�ɫ���������˽�ɫ��صļ�¼ */
			//BaseDAO checkRoleDAO=(BaseDAO)getBean("rolemenu_dao");			
			//checkRoleDAO.delete("delete from ep_check_role where ROLE_ID='"+((RoleVO)vo).getRole_id()+"'",conn);	

			//�ύ����
			conn.commit();
			
			//�ǼǷϳ���ɫ������־		
			OpLog.Log(user,"03","u","�ϳ���ɫ",((RoleVO)vo).getRole_id());
				
		}catch(Exception ex){			
			conn.rollback();
		}finally{
			if(conn!=null)
			conn.close();
		}

	}
	
	

	/*
	 * ɾ����ɫ���ݣ�ͬʱ�����û������Դ˽�ɫΪ����ɫ�ļ�¼
	 */
	public void delete(BaseVO vo, UserContext user) throws Exception {
		

				
		
		/**��ͬһ�����У�
		 * �����û�����������ɫ�ֶ�����Ϊ�գ�
		 * ɾ����ɫ���еĽ�ɫ
		 */
		Connection conn = DBPoolManager.getConnection();
		conn.setAutoCommit(false);
		
		try{
			
			/**�����û���
			 * ���Դ˽�ɫΪ����ɫ�ļ�¼������ɫ�ֶ�����Ϊ��"0"ֵ */
			UserDAO userdao=(UserDAO)getBean("user_dao");
			String sql="update ep_user set ROLE_ID='0' where ROLE_ID='"+((RoleVO)vo).getRole_id()+"'";
			userdao.executeUpdate(sql,conn);
			
		
			/**ɾ����ɫ�û���ϵ������˽�ɫ��صļ�¼ */
			UserRoleDAO userroleDAO=(UserRoleDAO)getBean("userrole_dao");			
			userroleDAO.delete("delete from ep_user_role where ROLE_ID='"+((RoleVO)vo).getRole_id()+"'",conn);
			
			/**�����û������Դ�Ϊ����ɫ�ļ�¼������ɫ�ֶ�����Ϊ��"0"ֵ */
			UserDAO userDAO=(UserDAO)getBean("user_dao");				
			userDAO.executeUpdate("update ep_user set ROLE_ID='0' where ROLE_ID='"+((RoleVO)vo).getRole_id()+"'",conn);	
			
			/**ɾ����ɫ����Ȩ�ޱ�����˽�ɫ��صļ�¼ */
			RoleOPDAO roleOpDAO=(RoleOPDAO)getBean("roleop_dao");		
			roleOpDAO.delete("delete from ep_role_auth where ROLE_ID='"+((RoleVO)vo).getRole_id()+"'",conn);
			
			/**ɾ����ɫ�˵���ϵ������˽�ɫ��صļ�¼ */
			RoleMenuDAO roleMenuDAO=(RoleMenuDAO)getBean("rolemenu_dao");
			roleMenuDAO.delete("delete from ep_role_menu where ROLE_ID='"+((RoleVO)vo).getRole_id()+"'",conn);	
			
			/**ɾ�����˽�ɫ���������˽�ɫ��صļ�¼ */
			//BaseDAO checkRoleDAO=(BaseDAO)getBean("rolemenu_dao");			
			//checkRoleDAO.delete("delete from ep_check_role where ROLE_ID='"+((RoleVO)vo).getRole_id()+"'",conn);	

			
			
			/**ɾ����ɫ������еĴ˽�ɫ*/
			BaseDAO  dao = (BaseDAO)getBean("role_dao");		
			sql=dao.getDeleteSQL()+" where ROLE_ID ='" + ((RoleVO)vo).getRole_id()+"'";		
			dao.delete(sql,conn);
			
			//�ύ����
			conn.commit();
			
						
			//�Ǽ�ɾ����ɫ������־		
			OpLog.Log(user,"03","d","ɾ����ɫ",((RoleVO)vo).getRole_id());
				
		}catch(Exception ex){			
			conn.rollback();
		}finally{
			if(conn!=null)
			conn.close();
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
		
		
		BaseDAO  dao = (BaseDAO)getBean("role_dao");
		String sql = "select * from ep_role ";
		return dao.queryList(sql);
	}

	/* 
	 * ��ѯά�����ݣ����ݲ�ѯ�������ط���������һҳ��¼
	 */
	public List list(PageObject page, UserContext user) throws Exception {
		
		BaseDAO  dao = (BaseDAO)getBean("role_dao");
		
		//ģ��ƥ������
		StringBuffer sql =new StringBuffer("select * from ep_role where 1=1 ");
		
		
		String role_name = (String)page.getFilter("role_name");
		if(role_name != null && role_name.trim().length()>0) 
				sql.append(" and ROLE_NAME like '%"+role_name+"%'");

		
		//ִ�в�ѯ���
		page.setList(dao.queryPage(page,sql.toString()));
		
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
		
		BaseDAO  dao = (BaseDAO)getBean("role_dao");
		BaseVO v = dao.retrieve(dao.getQuerySQL()+" where ROLE_ID='" + ((RoleVO)vo).getRole_id()+"'");
		//�Ǽǲ�ѯ��ɫ��ϸ������־		
		OpLog.Log(user,"03","r","��ѯ��ɫ",((RoleVO)vo).getRole_id());
		return v;
	}
	
	/* 
	 * ����where������ѯ��ϸ��Ϣ�����ص�һ��¼
	 */
	public BaseVO retrieve(String whereClause, UserContext user) throws Exception {
		
		return null;
	}
	
	//���ؽ�ɫ����Ϊ���ֵ�ļ�¼
	public String retrieveMax(UserContext user) throws Exception {
		
		BaseDAO  dao = (BaseDAO)getBean("role_dao");		
		return dao.querySingle("select MAX(ROLE_ID) from ep_role");		
		
	}

}
