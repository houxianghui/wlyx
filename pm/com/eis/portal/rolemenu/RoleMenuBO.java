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
 * 说明：角色菜单权限管理业务逻辑类
 * 
 */
public class RoleMenuBO extends BaseBO {

	/** 
	 * 增加
	 */
	public void add(BaseVO vo, UserContext user) throws Exception {

		BaseDAO dao = (BaseDAO) BeanFactory.getBean("rolemenu_dao");

		dao.add(vo);

	}

	/*
	 * 批量增加数据
	 */
	public void addList(BaseVO[] list, UserContext user) throws Exception {

	}
	

	/**	 
	 * 更新对角色role_id选定的菜单权限修改,批量更新数据
	 */	
	public void update(String role_id,List list,UserContext user)  throws Exception{
		
		//事务操作
		Connection connts = DBPoolManager.getConnection();
		connts.setAutoCommit(false);
	
		try{
			
			RoleMenuDAO  dao = (RoleMenuDAO)BeanFactory.getBean("rolemenu_dao");
			//找到list和父亲菜单的组成的集合
			List menulist=dao.getSelectMenuSet(list);//新加	
			
			/**如果去掉勾选的菜单,其下操作权限有已分配给角色的，
			 * 则需要首先删除这些操作权限		
			 **/		
			dao.deleteOPs(role_id,menulist,user,connts);//加入事务
			
	
			//对角色菜单表进行更新操作				
			dao.update(role_id,menulist,user,connts);	
				
			connts.commit();
				
		}catch(Exception ex){			
			connts.rollback();
		}finally{
			if(connts!=null)
			connts.close();
		}	
		
		//登记修改角色菜单权限操作日志		
		OpLog.Log(user,"03","u","修改角色菜单权限","角色"+user.getRoleID());	
		
	}

	/*
	 * 修改数据
	 */
	public void update(BaseVO vo, UserContext user) throws Exception {

		//BaseDAO  dao = (BaseDAO)BeanFactory.getBean("role_dao");
		//dao.update(vo, " where ROLE_ID ='" + ((RoleVO)vo).getRole_id()+"'");

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
		
	}

	public void delete(String role_id, List list,UserContext user) throws Exception {

		//事务操作
		Connection connts = DBPoolManager.getConnection();
		connts.setAutoCommit(false);
		
		try{	
			
			RoleMenuDAO  rolemenudao = (RoleMenuDAO)BeanFactory.getBean("rolemenu_dao");
			rolemenudao.deleteOPs(role_id,list,user,connts);	
	
			BaseDAO dao = (BaseDAO) BeanFactory.getBean("rolemenu_dao");//要在事务中
			dao.delete(
				"delete  from ep_role_menu  where ROLE_ID='" + role_id + "'",connts);
				
				
			connts.commit();
				
		}catch(Exception ex){			
			connts.rollback();
		}finally{
			if(connts!=null)
			connts.close();
		}						
			
		//登记删除角色菜单权限操作日志		
		OpLog.Log(user,"03","d","删除角色菜单权限","角色"+user.getRoleID());	

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
	 * 查询角色名称
	 */
	public String queryRoleName(String role_id) throws Exception {
		BaseDAO dao = (BaseDAO) BeanFactory.getBean("role_dao");
		return dao.querySingle("select ROLE_NAME from ep_role  where ROLE_ID='"+role_id+"'");
		
	}

	/*
	 * 查询列表，根据查询条件返回符合条件的一页纪录
	 */
	public List queryList(PageObject page, UserContext user) throws Exception {

		return null;
	}

	/* 
	 * 查询维护数据，根据查询条件返回符合条件的一页纪录
	 */
	public List list(PageObject page, UserContext user) throws Exception {

		BaseDAO dao = (BaseDAO) BeanFactory.getBean("role_dao");

		//模糊匹配条件
		StringBuffer sql = new StringBuffer("select * from ep_role where 1=1 ");
		String role_id = (String) page.getFilter("role_id");
		if (role_id != null)
			sql.append(" and ROLE_ID like '%" + role_id + "%'");

		String role_name = (String) page.getFilter("role_name");
		if (role_name != null)
			sql.append(" and ROLE_NAME like '%" + role_name + "%'");

		SysLog.debug(sql.toString());

		//执行查询结果
		page.setList(dao.queryPage(page, sql.toString()));

		return null;
	}

	/* 
	* 查询菜单维护数据，返回所有纪录，不分页
	*/
	public List list(UserContext user) throws Exception {

		return null;

	}

	/* 
	 * 查询菜单维护数据，返回所有纪录，不分页
	 */
	public List list(String role_id, UserContext user) throws Exception {
		RoleMenuDAO dao = (RoleMenuDAO) BeanFactory.getBean("rolemenu_dao");		
		List l = dao.selectMenuList(role_id, user);	
		
		//登记查看操作日志		
		OpLog.Log(user,"03","r","查看角色菜单权限",role_id);
		return l;

	}

	/* 
	 * 查询明细信息，返回单一纪录
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
	 * 根据where条件查询明细信息，返回单一纪录
	 */
	public BaseVO retrieve(String whereClause, UserContext user)
		throws Exception {

		return null;
	}

}
