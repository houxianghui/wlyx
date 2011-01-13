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
 * 说明：登录功能业务逻辑类
 * 
 */
public class LoginBO extends BaseBO {
	
	/** 
	 * 构造函数 
	 */ 
	public LoginBO(){ 
		super(); 
	} 

	

	/** 
	 * 增加用户登录记录表、用户操作记录表 
	 */ 
	public void add(BaseVO loginvo,BaseVO oplogvo,UserContext user)  throws Exception {
		
				  /**在用户操作记录表增加记录*/			
//				  OpLogDAO opLogDAO = new OpLogDAO();
//			
//				  opLogDAO.add(oplogvo);
		
		
		
		      //在同一事务中，增加用户登录记录表和用户操作记录表数据
			  Connection connts = DBPoolManager.getConnection();
			  connts.setAutoCommit(false);
		
			  try{
			
				  /**在用户操作记录表增加记录*/			
				  OpLogDAO opLogDAO = new OpLogDAO();
			
				  opLogDAO.add(oplogvo,connts);
			
				  /**在用户登录记录表增加记录*/
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
	 * 批量增加用户列表 
	 */ 
	public void addList(BaseVO[] list, UserContext user) throws Exception { 
 
		BaseDAO  dao = (BaseDAO)getBean("user_dao"); 
		dao.addList(list); 
 
	} 

	/*
	 * 修改数据
	 */
	public void update(BaseVO vo, UserContext user) throws Exception {
		
		
		BaseDAO  dao = (BaseDAO)BeanFactory.getBean("login_dao");
		LoginVO loginVo = (LoginVO)vo;		
		
		
		dao.update(vo, " where USER_ID ='" + loginVo.getUser_id().trim() +"'");

	}

	/*
	 * 批量修改数据
	 */
	public void updateList(BaseVO[] list, UserContext user) throws Exception {

	}

	/*
	 * 删除数据
	 */
	public void delete(BaseVO vo, UserContext user) throws Exception {

		
		BaseDAO  dao = (BaseDAO)BeanFactory.getBean("login_dao");
		dao.delete(dao.getDeleteSQL()+" where USER_ID ='" + ((LoginVO) vo).getUser_id()+"'");

	}

	/*
	 * 批量删除数据
	 */
	public void deleteList(BaseVO[] list, UserContext user) throws Exception {
		

	}

	/*
	 * 查询列表，没有查询条件，返回所有纪录
	 */
	public List queryList(UserContext user) throws Exception {
		
		return null;
	}

	/*
	 * 查询列表，根据查询条件返回符合条件的一页纪录
	 */
	public List queryList(PageObject page, UserContext user) throws Exception { 
 
		BaseDAO  dao = (BaseDAO)getBean("login_dao"); 
		StringBuffer sql =new StringBuffer("select * from ep_user where 1=1 "); 
		//在此处添加模糊匹配条件 
		String login_id = (String) page.getFilter("login_id");
		String password = (String) page.getFilter("password");
			if (login_id != null &&password != null)
				sql.append(" and LOGIN_ID = '" + login_id + "' and PASSWORD = '" + password + "'");

			SysLog.debug(sql.toString());
		//执行查询 
		page.setList(dao.queryPage(page,sql.toString())); 
		return page.getList(); 
 
	} 

	/** 
	 * 查询维护数据，根据查询条件返回符合条件的一页纪录 
	 */ 
	public List list(PageObject page, UserContext user) throws Exception { 
 
		BaseDAO  dao = (BaseDAO)getBean("login_dao"); 
		StringBuffer sql =new StringBuffer("select * from ep_user where 1=1 "); 
		//在此处添加模糊匹配条件 
		String login_id = (String) page.getFilter("login_id");
		String password = (String) page.getFilter("password");
				if (login_id != null)
		sql.append(" and LOGIN_ID = '" + login_id + "' and PASSWORD = '" + password + "'");
		//执行查询 
		page.setList(dao.queryPage(page,sql.toString())); 
		return page.getList(); 
 
	} 

	/* 
	 * 查询维护数据，返回所有纪录
	 */
	public List list(UserContext user) throws Exception {
		
		return null;
	}

	/** 
	 * 查询明细信息，返回单一纪录 
	 */ 
	public BaseVO retrieve(BaseVO vo, UserContext user) throws Exception { 
 
		LoginVO  bean = (LoginVO)vo; 
		BaseDAO  dao = (BaseDAO)getBean("login_dao"); 
 
		return dao.retrieve(dao.getQuerySQL()+" where USER_ID='"+((LoginVO) vo).getUser_id()+"'"); 
 
	} 
	
	/**
	 * 根据where条件查询明细信息，返回单一纪录 
	 */ 
	public BaseVO retrieve(String whereClause, UserContext user) throws Exception { 
 
		BaseDAO  dao = (BaseDAO)getBean("login_dao"); 
		return dao.retrieve(dao.getQuerySQL()+ whereClause); 
 
	}
	

	/**
	 * 转向到错误信息提示页面
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
