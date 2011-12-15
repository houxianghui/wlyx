/*********************************************************
 * File: MenuDAO.java
 * 
 * Version 1.0
 * 
 * Date     2005-9-14
 * 
 * Author   陈 蓉
 * 
 ********************************************************/

package com.eis.portal.menu;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.sql.*;

import com.eis.base.BaseDAO;
import com.eis.base.BaseVO;
import com.eis.base.PageObject;
import com.eis.portal.UserContext;

import com.eis.util.*;
import com.eis.db.*;

import java.util.*;
import com.eis.base.*;

/**
 * 说明：菜单管理数据对象
 * 
 */
public class MenuDAO extends BaseDAO {

	/**
	 * 
	 */
	public MenuDAO() {
		super();

	}

	/**
	 * @param dsName
	 */
	public MenuDAO(String dsName) {
		super(dsName);

	}

	/*
	 * 
	 */
	public void initSQL() {
		setInsertSQL("insert into ep_menu(MENU_ID,PARENT_ID,MENU_NAME,MENU_MARK,MENU_LEVEL,LIST_ORDER,MENU_URL) values(?,?,?,?,?,?,?)");
		setUpdateSQL("update ep_menu set PARENT_ID=?,MENU_NAME=?,MENU_MARK=?,MENU_LEVEL=?,LIST_ORDER=?,MENU_URL=? ");

		//设置查询菜单明细语句	
		//setQuerySQL(" select * from ep_menu ");	
		String s =
			"select (CASE WHEN b.MENU_NAME IS NULL THEN '根' ELSE b.MENU_NAME END) as PARENT_NAME , a.* "
				+ " from ep_menu a  "
				+ " left join ep_menu b on a.PARENT_ID=b.MENU_ID ";
		setQuerySQL(s);

		setListSQL(" select * from ep_menu ");
		setDeleteSQL(" delete  from ep_menu ");
		setOrderBy(" order by MENU_LEVEL");
	}

	/*
	 * 对执行数据增加的PreparedStatement中的参数进行赋值
	 */
	public void prepareInsertStatement(PreparedStatement ps, BaseVO bean)
		throws Exception {
		MenuVO vo = (MenuVO) bean;

		ps.setString(1, vo.getMenu_id());
		ps.setString(2, vo.getParent_id());
		ps.setString(3, vo.getMenu_name());
		ps.setString(4, vo.getMenu_mark());
		ps.setInt(5, vo.getMenu_level());
		ps.setInt(6, vo.getList_order());

		if (vo.getMenu_url() != null) {
			ps.setString(7, vo.getMenu_url());
		} else {
			ps.setNull(7, Types.VARCHAR);
		}

	}

	/*	 
	 * 对执行数据修改的PreparedStatement中的参数进行赋值
	 */

	public void prepareUpdateStatement(PreparedStatement ps, BaseVO bean)
		throws Exception {
		MenuVO vo = (MenuVO) bean;

		ps.setString(1, vo.getParent_id());
		ps.setString(2, vo.getMenu_name());
		ps.setString(3, vo.getMenu_mark());
		ps.setInt(4, vo.getMenu_level());
		ps.setInt(5, vo.getList_order());

		if (vo.getMenu_url() != null) {
			ps.setString(6, vo.getMenu_url());
		} else {
			ps.setNull(6, Types.VARCHAR);
		}
	}

	/*
	 * 
	 */
	public void delete(BaseVO bean) throws Exception {

	}


	/*
	 *查询按角色权限的菜单记录列表，不分页 
	 */
	public List queryListWithPerm(String sql) throws Exception {

		ArrayList list = null;

		Connection conn = null;

		try {
			conn = getConnection();
			Statement stmt =
				conn.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);

			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				list = new ArrayList();
			}
			rs.previous();

			while (rs.next()) {
				list.add(retrieveResult(rs));
			}

			if (rs != null)
				rs.close();

			if (stmt != null)
				stmt.close();

		} catch (SQLException ex) {

			while (ex != null) {
				SysLog.error("BaseDAO查询结果失败！");
				SysLog.error("error code:" + ex.getErrorCode());
				SysLog.error("SQL State:" + ex.getSQLState());
				SysLog.error("异常信息:" + ex.getSQLState());
				ex = ex.getNextException();
			}

			BaseException e = new BaseException();
			e.setErrorCode("E010018");
			throw e;

		} catch (Throwable t) {
			SysLog.error("BaseDAO查询结果失败！");
			SysLog.error(t.getMessage());

			BaseException e = new BaseException();
			e.setErrorCode("E010018");
			throw e;
		} finally {
			try {
				//释放数据库连接
				if (conn != null) {
					conn.close();
				}
			} catch (Exception ex) {
				SysLog.error("BaseDAO 释放数据库连接失败！");
				SysLog.error(ex.getMessage());
				BaseException e = new BaseException();
				e.setErrorCode("E010019");
				throw e;
			}
		}
		return list;
	}

	/*
	 * 
	 */
	public List queryList(PageObject page, UserContext user) throws Exception {

		return null;
	}

	/*
	* 返回全部菜单列表
	*/
	public List queryList(UserContext user) throws Exception {
		String sql = "select * from ep_menu  order by MENU_LEVEL,LIST_ORDER";
		return queryList(sql);
	}

	/*
	* 
	*/
	public List queryListWithPerm(UserContext user) throws Exception {
		String role_id = user.getRoleID();
		
		//检查用户所属机构是否有单独的权限控制
		String sql = null;
		DBQueryUtil db = new DBQueryUtil();
		
		try {
			
						sql =
							"select * from ep_menu a,ep_role_menu b  "
								+ " where a.MENU_ID=b.MENU_ID and b.ROLE_ID='"
								+ role_id
								+ "' ";

					
								
			sql+=" order by a.MENU_LEVEL,a.LIST_ORDER";
			SysLog.debug(sql);
			return queryList(sql);
					
			
		} catch (Exception ex){
			throw ex;
		} finally {
			db.close();
		}
				
		
		
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
		MenuVO vo = new MenuVO();
		vo.setMenu_id(rs.getString("MENU_ID"));
		vo.setParent_id(rs.getString("PARENT_ID"));
		vo.setMenu_name(rs.getString("MENU_NAME"));
		vo.setMenu_mark(rs.getString("MENU_MARK"));
		vo.setMenu_level(rs.getInt("MENU_LEVEL"));
		vo.setList_order(rs.getInt("LIST_ORDER"));
		vo.setMenu_url(rs.getString("MENU_URL"));

		return vo;
	}

	/* 
	 * 获取明细信息
	 */
	public BaseVO detail(ResultSet rs) throws Exception {
		MenuVO vo = new MenuVO();

		vo.setMenu_id(rs.getString("MENU_ID"));
		vo.setParent_id(rs.getString("PARENT_ID"));
		vo.setParent_name(rs.getString("PARENT_NAME"));
		vo.setMenu_name(rs.getString("MENU_NAME"));
		vo.setMenu_mark(rs.getString("MENU_MARK"));
		vo.setMenu_level(rs.getInt("MENU_LEVEL"));
		vo.setList_order(rs.getInt("LIST_ORDER"));
		vo.setMenu_url(rs.getString("MENU_URL"));

		return vo;
	}

	/* 
	 * 
	 */
	public List list(PageObject page, UserContext user) throws Exception {

		return null;
	}
	//返回菜单编码为最大值的记录
	public String retrieveMax(UserContext user) throws Exception {
		DBQueryUtil db = new DBQueryUtil();
		
		try {
			return db.sqlQuerySingle("select MAX(MENU_ID) from ep_menu");
		} finally{
			db.close();
		}
	}



}
