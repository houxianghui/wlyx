/*********************************************************
 * File: LoginBO.java
 * 
 * Version 1.0
 * 
 * Date     2005-9-19
 * 
 * Author   lihaibao
 * 
 ********************************************************/

package com.eis.portal.login;

import java.util.List;
import java.sql.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;

import com.eis.base.BaseBO;
import com.eis.base.BaseVO;
import com.eis.base.BaseDAO;
import com.eis.base.PageObject;
import com.eis.portal.UserContext;
import com.eis.factory.*;
import com.eis.util.*;
import com.eis.connectionPool.*;
import com.eis.portal.oplog.*; 
import com.eis.portal.userlogin.*;

/**
 * ˵������¼����ҵ���߼���
 * 
 */
public class LoginBO extends BaseBO {
	
	/** 
	 * ���캯�� 
	 */ 
	public LoginBO(){ 
		super(); 
	} 

	

	/** 
	 * �����û���¼��¼���û�������¼�� 
	 */ 
	public void add(BaseVO loginvo,BaseVO oplogvo,UserContext user)  throws Exception {
		
				  /**���û�������¼�����Ӽ�¼*/			
//				  OpLogDAO opLogDAO = new OpLogDAO();
//			
//				  opLogDAO.add(oplogvo);
		
		
		
		      //��ͬһ�����У������û���¼��¼����û�������¼������
			  Connection connts = DBPoolManager.getConnection();
			  connts.setAutoCommit(false);
		
			  try{
			
				  /**���û�������¼�����Ӽ�¼*/			
				  OpLogDAO opLogDAO = new OpLogDAO();
			
				  opLogDAO.add(oplogvo,connts);
			
				  /**���û���¼��¼�����Ӽ�¼*/
				  UserLoginDAO userLoginDAO = new UserLoginDAO();
			
				  userLoginDAO.add(loginvo,connts);
			
				  connts.commit();
				
			  }catch(Exception ex){			
				  connts.rollback();
			  }finally{
				  if(connts!=null)
				  connts.close();
			  }		

	}
		
	public void add(BaseVO vo, UserContext user) throws Exception { }	
	
	/** 
	 * ���������û��б� 
	 */ 
	public void addList(BaseVO[] list, UserContext user) throws Exception { 
 
		BaseDAO  dao = (BaseDAO)getBean("user_dao"); 
		dao.addList(list); 
 
	} 

	/*
	 * �޸�����
	 */
	public void update(BaseVO vo, UserContext user) throws Exception {
		
		
		BaseDAO  dao = (BaseDAO)BeanFactory.getBean("login_dao");
		LoginVO loginVo = (LoginVO)vo;		
		
		
		dao.update(vo, " where USER_ID ='" + loginVo.getUser_id().trim() +"'");

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

		
		BaseDAO  dao = (BaseDAO)BeanFactory.getBean("login_dao");
		dao.delete(dao.getDeleteSQL()+" where USER_ID ='" + ((LoginVO) vo).getUser_id()+"'");

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
 
		BaseDAO  dao = (BaseDAO)getBean("login_dao"); 
		StringBuffer sql =new StringBuffer("select * from ep_user where 1=1 "); 
		//�ڴ˴����ģ��ƥ������ 
		String login_id = (String) page.getFilter("login_id");
		String password = (String) page.getFilter("password");
			if (login_id != null &&password != null)
				sql.append(" and LOGIN_ID = '" + login_id + "' and PASSWORD = '" + password + "'");

			SysLog.debug(sql.toString());
		//ִ�в�ѯ 
		page.setList(dao.queryPage(page,sql.toString())); 
		return page.getList(); 
 
	} 

	/** 
	 * ��ѯά�����ݣ����ݲ�ѯ�������ط���������һҳ��¼ 
	 */ 
	public List list(PageObject page, UserContext user) throws Exception { 
 
		BaseDAO  dao = (BaseDAO)getBean("login_dao"); 
		StringBuffer sql =new StringBuffer("select * from ep_user where 1=1 "); 
		//�ڴ˴����ģ��ƥ������ 
		String login_id = (String) page.getFilter("login_id");
		String password = (String) page.getFilter("password");
				if (login_id != null)
		sql.append(" and LOGIN_ID = '" + login_id + "' and PASSWORD = '" + password + "'");
		//ִ�в�ѯ 
		page.setList(dao.queryPage(page,sql.toString())); 
		return page.getList(); 
 
	} 

	/* 
	 * ��ѯά�����ݣ��������м�¼
	 */
	public List list(UserContext user) throws Exception {
		
		return null;
	}

	/** 
	 * ��ѯ��ϸ��Ϣ�����ص�һ��¼ 
	 */ 
	public BaseVO retrieve(BaseVO vo, UserContext user) throws Exception { 
 
		LoginVO  bean = (LoginVO)vo; 
		BaseDAO  dao = (BaseDAO)getBean("login_dao"); 
 
		return dao.retrieve(dao.getQuerySQL()+" where USER_ID='"+((LoginVO) vo).getUser_id()+"'"); 
 
	} 
	
	/**
	 * ����where������ѯ��ϸ��Ϣ�����ص�һ��¼ 
	 */ 
	public BaseVO retrieve(String whereClause, UserContext user) throws Exception { 
 
		BaseDAO  dao = (BaseDAO)getBean("login_dao"); 
		return dao.retrieve(dao.getQuerySQL()+ whereClause); 
 
	}
	

	/**
	 * ת�򵽴�����Ϣ��ʾҳ��
	 * @param request
	 * @param mapping
	 * @param message
	 * @return ActionForward
	 * 
	 */
	public ActionForward forwardError(
		HttpServletRequest request,
		ActionMapping mapping,
		String message) {

		request.setAttribute("message", message);

		return (mapping.findForward("default_error"));
	} 

}
