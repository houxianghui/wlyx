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
 * 说明：菜单管理业务逻辑类
 * 
 */
public class MenuBO extends BaseBO {

	/** 
	 * 增加菜单
	 */
	public void add(BaseVO vo, UserContext user) throws Exception {

		BaseDAO dao = (BaseDAO) BeanFactory.getBean("menu_dao");
		dao.add(vo);

		//登记增加菜单操作日志		
		OpLog.Log(user, "03", "c", "增加菜单", ((MenuVO) vo).getMenu_id());

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

		BaseDAO dao = (BaseDAO) BeanFactory.getBean("menu_dao");
		dao.update(vo, " where MENU_ID ='" + ((MenuVO) vo).getMenu_id() + "'");

		//登记修改菜单操作日志		
		OpLog.Log(user, "03", "u", "修改菜单", ((MenuVO) vo).getMenu_id());

	}

	/*
	 * 批量修改数据
	 */
	public void updateList(BaseVO[] list, UserContext user) throws Exception {

	}

	/*
	 * 删除菜单及下级菜单数据
	 */
	public void delete(BaseVO vo, UserContext user) throws Exception {

		//在同一事务中，删除与菜单表有外键关系的所有表
		java.sql.Connection con = DBPoolManager.getConnection();
		con.setAutoCommit(false);

		try {
			ArrayList list = new ArrayList();
			//获取本级和下级菜单集合，集合是按菜单级别由低向高获取的
			getSubMenus(((MenuVO) vo).getMenu_id(),list);
			for(int i=0;i<list.size();i++) {
				SysLog.debug("下级菜单编号:"+list.get(i).toString());
				//按菜单级别由低高删除菜单
				deleteMenu(list.get(i).toString(),con);
			}
			

			//提交事务
			con.commit();

			//登记删除菜单操作日志		
			OpLog.Log(user, "03", "d", "删除菜单", ((MenuVO) vo).getMenu_id());

		} catch (Exception ex) {
			con.rollback();
		} finally {
			if (con != null)
				con.close();
		}

	}

	/*
	 * 删除与菜单有外键关系的所有关系，和删除菜单数据
	 */
	public void deleteMenu(String menuID, java.sql.Connection con)
		throws Exception {

		//删除角色菜单关系表中的纪录ep_role_menu
		RoleMenuDAO rolemenudao = (RoleMenuDAO) getBean("rolemenu_dao");
		rolemenudao.delete(
			"delete  from ep_role_menu  where MENU_ID ='"
				+menuID
				+ "'",
			con);

		

		//删除菜单下操作权限定义(ep_op_def)在角色操作权限关系表中的记录，ep_role_auth
		RoleOPDAO roleopdao = (RoleOPDAO) getBean("roleop_dao");
		StringBuffer sqlb =
			new StringBuffer(" delete from ep_role_auth where OP_CODE in ");
		sqlb.append(
			"( select OP_CODE from ep_op_def where MENU_ID ='"
				+menuID
				+ "' )");
		roleopdao.delete(sqlb.toString(), con);

		//删除操作权限定义表中的记录，ep_op_def
		OPDAO opdao = (OPDAO) BeanFactory.getBean("op_dao");
		opdao.delete(
			"delete from ep_op_def where MENU_ID ='"
				+ menuID
				+ "'",
			con);

		//删除菜单表中的记录，ep_menu	
		BaseDAO dao = (BaseDAO) BeanFactory.getBean("menu_dao");		
		dao.delete(
			dao.getDeleteSQL()
				+ " where MENU_ID ='"
				+ menuID
				+ "'",
			con);

	}
	

	/*
	 * 递归查询菜单数据，将本级和之下各级菜单加入集合list
	 */
	public void getSubMenus(String menuID, List list) throws Exception {

		
		DBQueryUtil db = new DBQueryUtil();
		try {
			java.sql.ResultSet rs =
				db.sqlQuery(
					"select * from ep_menu  where PARENT_ID ='" + menuID + "'");
			while (rs.next()) {
				//如果有下级菜单，递归调用				
				getSubMenus(rs.getString("MENU_ID"), list);
			}
			//将本级菜单加入集合
			if (!list.contains(menuID))
				list.add(menuID);

		} finally {
			db.close();

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
		MenuDAO dao = (MenuDAO) BeanFactory.getBean("menu_dao");
		return dao.queryList(user);
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

		BaseDAO dao = (BaseDAO) BeanFactory.getBean("menu_dao");

		//模糊匹配条件
		StringBuffer sql = new StringBuffer("select * from ep_menu where 1=1 ");
		String menu_id = (String) page.getFilter("menu_id");
		if (menu_id != null)
			sql.append(" and MENU_ID like '%" + menu_id + "%'");

		String menu_name = (String) page.getFilter("menu_name");
		if (menu_name != null)
			sql.append(" and MENU_NAME like '%" + menu_name + "%'");

		sql.append(" " + dao.getOrderBy());
		SysLog.debug(sql.toString());

		//执行查询结果
		page.setList(dao.queryPage(page, sql.toString()));

		return null;
	}

	/* 
	 * 查询菜单维护数据，返回所有纪录，不分页
	 */
	public List list(UserContext user) throws Exception {
		MenuDAO dao = (MenuDAO) BeanFactory.getBean("menu_dao");
		return dao.queryList(user);

	}

	/* 
	 * 查询菜单维护数据，返回用户角色拥有权限的纪录，不分页
	 */
	public List listWithPerm(UserContext user) throws Exception {
		MenuDAO dao = (MenuDAO) BeanFactory.getBean("menu_dao");
		return dao.queryListWithPerm(user);

	}

	/* 
	 * 查询明细信息，返回单一纪录
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
	 * 根据where条件查询菜单明细信息，返回单一纪录
	 */
	public BaseVO retrieve(String whereClause, UserContext user)
		throws Exception {

		//获得菜单管理数据库访问对象BaseDAO
		BaseDAO dao = (BaseDAO) BeanFactory.getBean("menu_dao");
		
		//调用并返回BaseDAO的retrieve(sql){}方法，返回单一记录信息
		BaseVO vo=dao.retrieve(dao.getQuerySQL() + " " + whereClause);
		
		//登记查看菜单操作日志		
		OpLog.Log(user, "03", "r", "查询菜单", ((MenuVO) vo).getMenu_id());		
		return vo;

	}

	//返回菜单编码为最大值的记录
	public String retrieveMax(UserContext user) throws Exception {

		MenuDAO dao = (MenuDAO) BeanFactory.getBean("menu_dao");
		return dao.retrieveMax(user);

	}

}
