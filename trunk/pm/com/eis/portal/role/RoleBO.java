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
 * 说明：角色管理业务逻辑类
 * 
 */
public class RoleBO extends BaseBO {

	/** 
	 * 增加公共参数
	 */
	public void add(BaseVO vo, UserContext user) throws Exception {
		
		
		BaseDAO  dao = (BaseDAO)getBean("role_dao");
		
		dao.add(vo);
		
		//登记增加角色操作日志		
		OpLog.Log(user,"03","c","增加角色",((RoleVO)vo).getRole_id());

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
		
		
		BaseDAO  dao = (BaseDAO)getBean("role_dao");
		dao.update(vo, " where ROLE_ID ='" + ((RoleVO)vo).getRole_id()+"'");
		
		//登记修改角色操作日志		
		OpLog.Log(user,"03","u","修改角色",((RoleVO)vo).getRole_id());

	}

	/*
	 * 批量修改数据
	 */
	public void updateList(BaseVO[] list, UserContext user) throws Exception {

	}
	
	/*
	* 废除角色，
	* vo：角色vo
	* user:用户对象 
	 */
	public void terminate(BaseVO vo, UserContext user) throws Exception {
		

		
		/**
		 * 与角色表有关联的表包括:ep_user,ep_user_role,ep_role_auth,ep_role_menu,ep_check_role,
		 * ，在同一事务中,
		 * 将角色定义表中的角色设置废除状态，即"0"值,
		 * 将角色用户关系表中与此角色相关的记录删除,
		 * 将用户表中以此角色为主角色的记录的主角色字段设置为"0"值,
		 * **/
		
		Connection conn = DBPoolManager.getConnection();
		conn.setAutoCommit(false);
		
		try{

			
			/**更新角色定义表，
			 * 将角色设置为废除状态，即"0"值 */			
			RoleDAO  dao = (RoleDAO)getBean("role_dao");
			dao.executeUpdate("update ep_role set STAT='0',REG_DT='"+((RoleVO)vo).getReg_dt()+"',USER_ID='"+((RoleVO)vo).getUser_id()+"'  where ROLE_ID ='" + ((RoleVO)vo).getRole_id()+"'",conn);
			
			/**删除角色用户关系表中与此角色相关的记录 */
			UserRoleDAO userroleDAO=(UserRoleDAO)getBean("userrole_dao");			
			userroleDAO.delete("delete from ep_user_role where ROLE_ID='"+((RoleVO)vo).getRole_id()+"'",conn);
			
			/**更新用户表，将以此为主角色的记录的主角色字段设置为空"0"值 */
			UserDAO userDAO=(UserDAO)getBean("user_dao");				
			userDAO.executeUpdate("update ep_user set ROLE_ID='0' where ROLE_ID='"+((RoleVO)vo).getRole_id()+"'",conn);	
			
			/**删除角色操作权限表中与此角色相关的记录 */
			RoleOPDAO roleOpDAO=(RoleOPDAO)getBean("roleop_dao");		
			roleOpDAO.delete("delete from ep_role_auth where ROLE_ID='"+((RoleVO)vo).getRole_id()+"'",conn);
			
			/**删除角色菜单关系表中与此角色相关的记录 */
			RoleMenuDAO roleMenuDAO=(RoleMenuDAO)getBean("rolemenu_dao");
			roleMenuDAO.delete("delete from ep_role_menu where ROLE_ID='"+((RoleVO)vo).getRole_id()+"'",conn);	
			
			/**删除复核角色定义表中与此角色相关的记录 */
			//BaseDAO checkRoleDAO=(BaseDAO)getBean("rolemenu_dao");			
			//checkRoleDAO.delete("delete from ep_check_role where ROLE_ID='"+((RoleVO)vo).getRole_id()+"'",conn);	

			//提交事务
			conn.commit();
			
			//登记废除角色操作日志		
			OpLog.Log(user,"03","u","废除角色",((RoleVO)vo).getRole_id());
				
		}catch(Exception ex){			
			conn.rollback();
		}finally{
			if(conn!=null)
			conn.close();
		}

	}
	
	

	/*
	 * 删除角色数据，同时更新用户表中以此角色为主角色的记录
	 */
	public void delete(BaseVO vo, UserContext user) throws Exception {
		

				
		
		/**在同一事务中，
		 * 更新用户表，将其主角色字段设置为空，
		 * 删除角色表中的角色
		 */
		Connection conn = DBPoolManager.getConnection();
		conn.setAutoCommit(false);
		
		try{
			
			/**更新用户表，
			 * 将以此角色为主角色的记录的主角色字段设置为空"0"值 */
			UserDAO userdao=(UserDAO)getBean("user_dao");
			String sql="update ep_user set ROLE_ID='0' where ROLE_ID='"+((RoleVO)vo).getRole_id()+"'";
			userdao.executeUpdate(sql,conn);
			
		
			/**删除角色用户关系表中与此角色相关的记录 */
			UserRoleDAO userroleDAO=(UserRoleDAO)getBean("userrole_dao");			
			userroleDAO.delete("delete from ep_user_role where ROLE_ID='"+((RoleVO)vo).getRole_id()+"'",conn);
			
			/**更新用户表，将以此为主角色的记录的主角色字段设置为空"0"值 */
			UserDAO userDAO=(UserDAO)getBean("user_dao");				
			userDAO.executeUpdate("update ep_user set ROLE_ID='0' where ROLE_ID='"+((RoleVO)vo).getRole_id()+"'",conn);	
			
			/**删除角色操作权限表中与此角色相关的记录 */
			RoleOPDAO roleOpDAO=(RoleOPDAO)getBean("roleop_dao");		
			roleOpDAO.delete("delete from ep_role_auth where ROLE_ID='"+((RoleVO)vo).getRole_id()+"'",conn);
			
			/**删除角色菜单关系表中与此角色相关的记录 */
			RoleMenuDAO roleMenuDAO=(RoleMenuDAO)getBean("rolemenu_dao");
			roleMenuDAO.delete("delete from ep_role_menu where ROLE_ID='"+((RoleVO)vo).getRole_id()+"'",conn);	
			
			/**删除复核角色定义表中与此角色相关的记录 */
			//BaseDAO checkRoleDAO=(BaseDAO)getBean("rolemenu_dao");			
			//checkRoleDAO.delete("delete from ep_check_role where ROLE_ID='"+((RoleVO)vo).getRole_id()+"'",conn);	

			
			
			/**删除角色定义表中的此角色*/
			BaseDAO  dao = (BaseDAO)getBean("role_dao");		
			sql=dao.getDeleteSQL()+" where ROLE_ID ='" + ((RoleVO)vo).getRole_id()+"'";		
			dao.delete(sql,conn);
			
			//提交事务
			conn.commit();
			
						
			//登记删除角色操作日志		
			OpLog.Log(user,"03","d","删除角色",((RoleVO)vo).getRole_id());
				
		}catch(Exception ex){			
			conn.rollback();
		}finally{
			if(conn!=null)
			conn.close();
		}				

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
		
		
		BaseDAO  dao = (BaseDAO)getBean("role_dao");
		String sql = "select * from ep_role ";
		return dao.queryList(sql);
	}

	/* 
	 * 查询维护数据，根据查询条件返回符合条件的一页纪录
	 */
	public List list(PageObject page, UserContext user) throws Exception {
		
		BaseDAO  dao = (BaseDAO)getBean("role_dao");
		
		//模糊匹配条件
		StringBuffer sql =new StringBuffer("select * from ep_role where 1=1 ");
		
		
		String role_name = (String)page.getFilter("role_name");
		if(role_name != null && role_name.trim().length()>0) 
				sql.append(" and ROLE_NAME like '%"+role_name+"%'");

		
		//执行查询结果
		page.setList(dao.queryPage(page,sql.toString()));
		
		return null;
	}

	/* 
	 * 查询维护数据，返回所有纪录
	 */
	public List list(UserContext user) throws Exception {
		
		return null;
	}

	/* 
	 * 查询明细信息，返回单一纪录
	 */
	public BaseVO retrieve(BaseVO vo, UserContext user) throws Exception {
		
		BaseDAO  dao = (BaseDAO)getBean("role_dao");
		BaseVO v = dao.retrieve(dao.getQuerySQL()+" where ROLE_ID='" + ((RoleVO)vo).getRole_id()+"'");
		//登记查询角色明细操作日志		
		OpLog.Log(user,"03","r","查询角色",((RoleVO)vo).getRole_id());
		return v;
	}
	
	/* 
	 * 根据where条件查询明细信息，返回单一纪录
	 */
	public BaseVO retrieve(String whereClause, UserContext user) throws Exception {
		
		return null;
	}
	
	//返回角色编码为最大值的记录
	public String retrieveMax(UserContext user) throws Exception {
		
		BaseDAO  dao = (BaseDAO)getBean("role_dao");		
		return dao.querySingle("select MAX(ROLE_ID) from ep_role");		
		
	}

}
