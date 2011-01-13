/*********************************************************
 * File: RoleOPDAO.java
 * 
 * Version 1.0
 * 
 * Date     2005-9-24
 * 
 * Author   陈 蓉
 * 
 ********************************************************/

package com.eis.portal.roleop;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.*;
import java.util.*;

import com.eis.base.BaseDAO;
import com.eis.base.BaseVO;
import com.eis.base.PageObject;
import com.eis.portal.UserContext;

import com.eis.util.*;
import com.eis.db.*;


import com.eis.base.*;

/**
 * 说明：角色操作权限管理数据访问对象
 * 
 */
public class RoleOPDAO extends BaseDAO {

	/**
	 * 
	 */
	public RoleOPDAO() {
		super();

	}

	/**
	 * @param dsName
	 */
	public RoleOPDAO(String dsName) {
		super(dsName);

	}

	/*
	 * 
	 */
	public void initSQL() {
		setInsertSQL("insert into ep_role_auth(ROLE_ID,OP_CODE) values(?,?)");
		setUpdateSQL("update ep_role_auth set ROLE_ID=?,OP_CODE=? ");
		setQuerySQL(" select * from ep_role_auth ");
		setListSQL(" select * from ep_role_auth ");
		setDeleteSQL(" delete  from ep_role_auth ");

		setOrderBy(" order by ROLE_ID");
	}

	/*
	 * 对执行数据增加的PreparedStatement中的参数进行赋值
	 */
	public void prepareInsertStatement(PreparedStatement ps, BaseVO bean)
		throws Exception {
		RoleOPVO vo = (RoleOPVO) bean;

		ps.setString(1, vo.getRole_id());
		ps.setString(2, vo.getOp_code());

	}

	/*	 
	 * 对执行数据修改的PreparedStatement中的参数进行赋值
	 */

	public void prepareUpdateStatement(PreparedStatement ps, BaseVO bean)
		throws Exception {
		RoleOPVO vo = (RoleOPVO) bean;

		ps.setString(1, vo.getCaption());
		ps.setString(2, vo.getOp_code());
	}

	/**	 
	 * 返回角色可选操作权限，即角色可以选择但没有选择的操作权限
	 */

	public List listOPNotSelected(String role_id, UserContext user)
		throws Exception {
		/**从角色菜单关系表ep_role_menu，角色权限定义表ep_op_def,
		 * 角色权限关系表ep_role_auth中,选择角色能够选择的、
		 * 但没选择的操作权限
		 */
		String sql = " select a.ROLE_ID,b.OP_CODE,b.CAPTION  ";
		sql = sql + "  from ep_role_menu a,ep_op_def b  ";
		sql = sql + "  where a.MENU_ID=b.MENU_ID  ";
		sql = sql + "  and a.ROLE_ID='" + role_id + "' ";
		sql = sql + " and b.OP_CODE not in ";
		sql = sql + " (select c.OP_CODE from ep_role_auth c ";
		sql = sql + " where c.ROLE_ID='" + role_id + "') ";
		return queryList(sql);
	}

	/**	 
	 * 返回角色role_id的操作权限
	 */

	public List listSelected(String role_id, UserContext user)
		throws Exception {
		/**从角色权限关系表ep_role_auth中（ROLE_ID,OP_CODE），
		 * 选择角色已经选择的操作权限,操作名称来自角色权限定义表ep_op_def
		 ***/
		String sql = " select a.ROLE_ID,a.OP_CODE,b.CAPTION ";
		sql = sql + "  from ep_role_auth a,ep_op_def b  ";
		sql = sql + "  where a.OP_CODE=b.OP_CODE  "; //左连接吗？
		sql = sql + "  and  a.ROLE_ID = '" + role_id + "'";
		return queryList(sql);

	}

	/**更新角色操作权限*/
	public void update(String role_id, List list, UserContext user)
		throws Exception {
		/**删除对角色role_id原来选择的操作权限，
		 * 将新的操作权限选择逐条加入到角色权限关系表ep_role_auth中
		 * **/

		//删除角色role_id原来所有操作权限			
		delete(role_id, user);
		SysLog.debug("after delete from 角色权限关系表");

		/**将新设置的角色操作权限关系添加到数据库中**/		

		if (list != null) {

			Connection conn = null;
			PreparedStatement ps = null;

			try {
				conn = getConnection();
				ps = conn.prepareStatement(getInsertSQL());

				Iterator iter = list.iterator();
				while (iter.hasNext()) {
					String op_code = (String) iter.next();
					
					ps.setString(1, role_id);
					ps.setString(2, op_code);
					ps.executeUpdate();

				}
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException ex) {

				while (ex != null) {
					SysLog.error("批量增加数据失败！");
					SysLog.error("error code:" + ex.getErrorCode());
					SysLog.error("SQL State:" + ex.getSQLState());
					SysLog.error("异常信息:" + ex.getSQLState());
					ex = ex.getNextException();
				}

				BaseException e = new BaseException();
				e.setErrorCode("E010012");
				throw e;
			} catch (Throwable t) {

				SysLog.error("批量增加数据失败！");
				SysLog.error(t.getMessage());
				BaseException e = new BaseException();
				e.setErrorCode("E010012");
				throw e;

			} finally {
				try {
					//释放数据库连接
					if (conn != null) {
						conn.close();
					}
				} catch (Exception ex) {
					SysLog.error("BaseDAO 释放数据库连接失败");
					SysLog.error(ex.getMessage());
					BaseException e = new BaseException();
					e.setErrorCode("E010019");
					throw e;
				}

			}
		}

	}

	/**删除角色操作权限*/
	public void delete(String role_id, UserContext user) throws Exception {
		/**删除对角色role_id所有选择的操作权限，
		 * 删除角色权限关系表ep_role_auth中所有有关role_id的记录
		 * **/
		delete("delete from ep_role_auth where ROLE_ID='" + role_id + "'");
		//要返回row吗？	

	}

	/*
	 * 
	 */
	public void delete(BaseVO bean) throws Exception {

	}


	/*
	 * 
	 */
	public List queryList(PageObject page, UserContext user) throws Exception {

		return null;
	}
	/*
	* 
	*/
	public List queryList(UserContext user) throws Exception {
		//String sql="select * from ep_menu ";		
		//return queryList(sql);
		return null;
	}

	/*
	 * 
	 */
	public void retrieve(BaseVO bean) throws Exception {

	}

	/*
	 * 获取列表数据的一条纪录
	 */

	public BaseVO retrieveResult(ResultSet rs) throws Exception {
		RoleOPVO vo = new RoleOPVO();
		vo.setRole_id(rs.getString("ROLE_ID"));
		vo.setOp_code(rs.getString("OP_CODE"));
		vo.setCaption(rs.getString("CAPTION"));

		return vo;
	}

	/* 
	 * 获取明细信息
	 */
	public BaseVO detail(ResultSet rs) throws Exception {
		RoleOPVO vo = new RoleOPVO();
		vo.setRole_id(rs.getString("ROLE_ID"));
		vo.setCaption(rs.getString("CAPTION"));
		vo.setOp_code(rs.getString("OP_CODE"));

		return vo;
	}

	/* 
	 * 
	 */
	public List list(PageObject page, UserContext user) throws Exception {

		return null;
	}

}
