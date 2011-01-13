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
 * 说明：角色操作权限业务逻辑类
 * 
 */
public class RoleOPBO extends BaseBO {
	
	
	/**	 
	 * 返回角色待选的操作权限，返回所有纪录，不分页 
	 */
	public List listOPNotSelected(String role_id,UserContext user) throws Exception{
		RoleOPDAO  dao = (RoleOPDAO)BeanFactory.getBean("roleop_dao");
		return dao.listOPNotSelected(role_id,user);
	}
	
	/**	 
	 * 返回对角色role_id选定的操作权限
	 */
	public List listSelected(String role_id,UserContext user) throws Exception{
		RoleOPDAO  dao2 = (RoleOPDAO)BeanFactory.getBean("roleop_dao");
		return dao2.listSelected(role_id,user);	
		
	}

	/**	 
	 * 更新对角色role_id选择的操作权限修改
	 */	
	public void update(String role_id,List list,UserContext user)  throws Exception{
		RoleOPDAO  dao = (RoleOPDAO)BeanFactory.getBean("roleop_dao");
		dao.update(role_id,list,user);	
		
		//登记修改角色操作权限操作日志		
		OpLog.Log(user,"03","u","修改角色操作权限","角色"+user.getRoleID());		
		
	}
	
	/**	 
	 * 删除角色role_id所有已选操作权限
	 */		
	public void delete(String role_id,UserContext user)  throws Exception{
		RoleOPDAO  dao = (RoleOPDAO)BeanFactory.getBean("roleop_dao");
		dao.delete(role_id,user);
		
		//登记删除角色操作权限操作日志		
		OpLog.Log(user,"03","d","删除角色操作权限","角色"+user.getRoleID());				
		
	}	
	
	

	/** 
	 * 增加
	 */
	public void add(BaseVO vo, UserContext user) throws Exception {

	}

	/*
	 * 批量增加数据
	 */
	public void addList(BaseVO[] list, UserContext user) throws Exception {
		

	}

	/*
	 * 修改数据
	 */
	public void update(BaseVO vo, UserContext user) throws Exception {
		
		
		//BaseDAO  dao = (BaseDAO)BeanFactory.getBean("menu_dao");
		//dao.update(vo, " where MENU_ID ='" + ((MenuVO) vo).getMenu_id()+"'");

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

		
		//BaseDAO  dao = (BaseDAO)BeanFactory.getBean("menu_dao");
		//dao.delete(dao.getDeleteSQL()+" where MENU_ID ='" + ((MenuVO) vo).getMenu_id()+"'");

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
		
			
		return null;
	}

	/*
	 * 查询角色名称
	 */
	public String queryRoleName(String role_id) throws Exception {
		BaseDAO dao = (BaseDAO) BeanFactory.getBean("role_dao");
		return dao.querySingle("select ROLE_NAME from ep_role  where ROLE_ID='"+role_id+"'");
		
	}
	
	/* 
	 * 查询维护数据，根据查询条件返回符合条件的一页纪录
	 */
	public List list(PageObject page, UserContext user) throws Exception {

		return null;
	}

	/* 
	 * 查询菜单维护数据，返回所有纪录，不分页
	 */
	public List list(UserContext user) throws Exception {
		return null;		
		
	}

	/* 
	 * 查询明细信息，返回单一纪录
	 */
	public BaseVO retrieve(BaseVO vo, UserContext user) throws Exception {		

		return null;
	}	
	
	
	/* 
	 * 根据where条件查询明细信息，返回单一纪录
	 */
	public BaseVO retrieve(String whereClause, UserContext user) throws Exception {
		return null;
		
	}	
	

}

