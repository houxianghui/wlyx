/*********************************************************
 * File: OPBO.java
 * 
 * Version 1.0
 * 
 * Date     2005-9-20
 * 
 * Author   陈 蓉
 * 
 ********************************************************/

package com.eis.portal.op;

import java.util.List;

import com.eis.base.BaseBO;
import com.eis.base.BaseVO;
import com.eis.base.BaseDAO;
import com.eis.base.PageObject;
import com.eis.portal.UserContext;
import com.eis.portal.roleop.*;
import com.eis.factory.*;
import com.eis.util.*;
import com.eis.connectionPool.*;

/**
 * 说明：操作权限定义业务逻辑类
 * 
 */
public class OPBO extends BaseBO {

	/** 
	 * 增加操作权限
	 */
	public void add(BaseVO vo, UserContext user) throws Exception {

		BaseDAO dao = (BaseDAO) BeanFactory.getBean("op_dao");

		dao.add(vo);

		//登记增加操作权限定义操作日志		
		OpLog.Log(user, "03", "c", "增加操作权限定义", ((OPVO) vo).getOp_code());

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

		BaseDAO dao = (BaseDAO) BeanFactory.getBean("op_dao");
		dao.update(vo, " where OP_CODE ='" + ((OPVO) vo).getOp_code() + "'");

		//登记修改操作权限定义操作日志		
		OpLog.Log(user, "03", "u", "修改操作权限定义", ((OPVO) vo).getOp_code());

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


		//在同一事务中，删除与操作权限表有外键关系的所有表
		java.sql.Connection con = DBPoolManager.getConnection();
		con.setAutoCommit(false);

		try {
			//删除操作权限角色关系表中记录，ep_role_auth
			RoleOPDAO  roleopdao = (RoleOPDAO)getBean("roleop_dao");
			roleopdao.delete("delete from ep_role_auth where OP_CODE ='" + ((OPVO)vo).getOp_code()+"'",con);

			//删除操作权限定义表中的纪录ep_op_def
			BaseDAO dao = (BaseDAO) BeanFactory.getBean("op_dao");
			dao.delete(
				dao.getDeleteSQL()
					+ " where OP_CODE ='"
					+ ((OPVO) vo).getOp_code()
					+ "'",con);
			con.commit();

			//登记删除操作权限定义操作日志		
			OpLog.Log(user, "03", "d", "删除操作权限定义", ((OPVO) vo).getOp_code());

		} catch (Exception ex) {
			con.rollback();
		} finally {
			if (con != null)
				con.close();
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

		BaseDAO dao = (BaseDAO) BeanFactory.getBean("op_dao");
		String sql = "select * from ep_op_def ";
		return dao.queryList(sql);
	}

	/* 
	 * 查询维护数据，根据查询条件返回符合条件的一页纪录
	 */
	public List list(PageObject page, UserContext user) throws Exception {

		BaseDAO dao = (BaseDAO) BeanFactory.getBean("op_dao");

		//模糊匹配条件
		StringBuffer sql =
			new StringBuffer(" select a.*,b.MENU_NAME from ep_op_def a ");

		sql.append(" left join ep_menu b on a.MENU_ID=b.MENU_ID  ");

		sql.append(" where 1=1 ");

		String menu_id = (String) page.getFilter("menu_id");
		if (menu_id != null)
			sql.append(" and a.MENU_ID like '%" + menu_id + "%'");

		String op_code = (String) page.getFilter("op_code");
		if (op_code != null)
			sql.append(" and a.OP_CODE like '%" + op_code + "%'");

		String caption = (String) page.getFilter("caption");
		if (caption != null)
			sql.append(" and a.CAPTION like '%" + caption + "%'");

		SysLog.debug(sql.toString());

		//执行查询结果
		page.setList(dao.queryPage(page, sql.toString()));

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

		BaseDAO dao = (BaseDAO) BeanFactory.getBean("op_dao");
		
		BaseVO v= dao.retrieve(
			dao.getQuerySQL()
				+ " where a.OP_CODE='"
				+ ((OPVO) vo).getOp_code()
				+ "'");
				
		//登记删除操作权限定义操作日志			
		OpLog.Log(user, "03", "r", "查看操作权限定义", ((OPVO) vo).getOp_code());
		
		return v;
	}

	/* 
	 * 根据where条件查询明细信息，返回单一纪录
	 */
	public BaseVO retrieve(String whereClause, UserContext user)
		throws Exception {

		return null;
	}

}
