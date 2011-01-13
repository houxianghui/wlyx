/*********************************************************
 * File: RoleMenuDAO.java
 * 
 * Version 1.0
 * 
 * Date     2005-9-21
 * 
 * Author   Chen Rong
 * 
 ********************************************************/

package com.eis.portal.rolemenu;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.sql.*;
import java.util.*;

import com.eis.base.BaseDAO;
import com.eis.base.BaseVO;
import com.eis.base.PageObject;
import com.eis.portal.UserContext;

import com.eis.util.*;
import com.eis.portal.rolemenu.*;

import com.eis.db.*;
import com.eis.base.*;
import com.eis.portal.roleop.*;
import com.eis.portal.op.*;
import com.eis.factory.*;

/**
 * 说明：角色菜单权限管理
 * 
 */
public class RoleMenuDAO extends BaseDAO {

	/**
	 * 
	 */
	public RoleMenuDAO() {
		super();

	}

	/**
	 * @param dsName
	 */
	public RoleMenuDAO(String dsName) {
		//super(dsName);

	}

	/*
	 * 
	 */
	public void initSQL() {
		setInsertSQL("insert into ep_role_menu(ROLE_ID,MENU_ID) values(?,?)");
		setUpdateSQL("update ep_role_menu set ROLE_ID=?,MENU_ID=? ");
		setQuerySQL(" select * from ep_role_menu ");
		setListSQL(" select * from ep_role_menu ");
		setDeleteSQL(" delete  from ep_role_menu  ");

		setOrderBy(" order by MENU_ID");
	}

	/*
	 * 对执行数据增加的PreparedStatement中的参数进行赋值
	 */
	public void prepareInsertStatement(PreparedStatement ps, BaseVO bean)
		throws Exception {
		RoleMenuVO vo = (RoleMenuVO) bean;

		ps.setString(1, vo.getRole_id());
		ps.setString(2, vo.getMenu_id());

	}

	/*	 
	 * 对执行数据修改的PreparedStatement中的参数进行赋值
	 */

	public void prepareUpdateStatement(PreparedStatement ps, BaseVO bean)
		throws Exception {
		RoleMenuVO vo = (RoleMenuVO) bean;

		ps.setString(1, vo.getRole_id());
		ps.setString(2, vo.getMenu_id());

	}

	/**更新角色菜单权限*/
	public void update(String role_id, List list, UserContext user, Connection connts)
		throws Exception {
		/**删除对角色role_id原来选择的菜单权限，
		 * 将新的菜单权限选择逐条加入到角色菜单关系表ep_role_menu中
		 * **/

		//删除角色role_id的所有原来菜单权限选择			
		delete(role_id, user,connts);//操作加入事务
		SysLog.debug("after delete from 角色菜单关系表");

		/**将新设置的角色菜单关系添加到数据库中**/

		if (list != null) {

			Connection conn = null;
			PreparedStatement ps = null;

			try {
				//conn = getConnection();
				//ps = conn.prepareStatement(getInsertSQL());
				ps = connts.prepareStatement(getInsertSQL());//事务

				Iterator iter = list.iterator();
				while (iter.hasNext()) {
					String menu_id = (String) iter.next();

					ps.setString(1, role_id); //角色代码
					ps.setString(2, menu_id); //权限码
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

	/**删除角色菜单权限*/
	public void delete(String role_id, UserContext user,Connection connts) throws Exception {
		/**删除对角色role_id原来选择的所有操作权限，
		 * 删除角色菜单关系表ep_role_menu中所有有关role_id的记录
		 * **/
		delete("delete from ep_role_menu where ROLE_ID='" + role_id + "'",connts);

	}
	
	
	/** 如果去掉勾选的菜单，其下的操作权限有已分配给角色的，则首先删除这些操作权限：
	 * 找出原来曾选择但现在取消了选择的菜单集合 
	 * 对于集合中的每个menu_id, 删除属于其下且已分配给了此角色的操作权限
	 * */	
	public void deleteOPs(String role_id,List list,UserContext user,Connection conn) throws Exception{
		
		/**将角色当前选择的菜单menu_id集合写成一字符串
		 * 假设选择的菜单编号集合为00000013和00000015
		 * 则创建字符串str='00000013','00000015'以用于sql语句中
		 */	
		String str=null;
		str="''";
		
		if(list!=null){			
			Iterator iter=list.iterator();
			while(iter.hasNext()){
				str=str+",'"+(String)iter.next()+"'";
				
			}			
		}
		
			
		//获得角色权限数据访问对象
		RoleOPDAO  roleopdao = (RoleOPDAO)BeanFactory.getBean("roleop_dao");
			
		String sql="delete from ep_role_auth where ROLE_ID='"+role_id+"' and OP_CODE in  "
				+" ( select OP_CODE from ep_op_def where MENU_ID not in  "
				+"("+str+"))";	
				
		roleopdao.delete(sql,conn);	
	}	
	

	/*
	 * 
	 */
	public void delete(BaseVO bean) throws Exception {

	}
	
	/*
	 *查询角色role_id分配的菜单权限列表，不分页 
	 */
	public List queryListByRoleId(String sql,String role_id) throws Exception {

		ArrayList list;

		Connection conn = null;
		
		DBQueryUtil dbUtil = new DBQueryUtil();

		try {
			conn = getConnection();
			Statement stmt =
				conn.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);

			
			String totalString =
				dbUtil.sqlQuerySingle("select count(*) from ep_role_menu where ROLE_ID='"+role_id+"'");
			int total = Integer.parseInt(totalString);

			
			//如果纪录数为0，返回
			if (total <= 0) {
				return null;
			}
			list = new ArrayList(total);
			
						

			ResultSet rs = stmt.executeQuery(sql);
			
			rs.next();
			

			for (int i = 1; i <= total; i++) {

				list.add(retrieveResult(rs));
				rs.next();

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
			dbUtil.close();
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
	


	
	//找到list和父亲菜单的组成的集合
	List getSelectMenuSet(List list) throws Exception {

		HashMap map = new HashMap();
		List listResult = new ArrayList();
		
		Connection conn = getConnection();

		try {
			//如果对菜单的选择集合不为空，寻找此菜单集合及其父菜单组成的集合
			if (list != null) {
				Iterator iter = list.iterator();
				String menu_id = null; //菜单编码

				while (iter.hasNext()) {
					menu_id = (String) iter.next();					
					//获得menu_id的父亲节点集合，放入map中
					getParentMenuSet(menu_id, map, conn);
				}
			}
			
			Object[] parent_menu = map.values().toArray();
			for (int i = 0; i < parent_menu.length; i++) {
				listResult.add(parent_menu[i].toString());
				SysLog.debug("parent_id" + parent_menu[i].toString());
			}
			
			//将叶节点集合加入
			listResult.addAll(list);
			
		} catch (Exception ex) {
			 SysLog.debug(ex.getMessage());
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


		

		return listResult;
	}

	/**方法用于返回：菜单menu_id的父亲菜单集合
	 ***/
	public void getParentMenuSet(String mid, HashMap map, Connection conn)
		throws Exception {

			DBQueryUtil db = new DBQueryUtil();
		try {
			
			Statement stmt =
				conn.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);

			String sql = null;
			String menu_id = null; //菜单编号变量
			String parent_id = null;
			int count = 0;
			ResultSet rs = null;
			

			/**查询菜单编号为mid的菜单是否有上级菜单 */
			sql =
				" select PARENT_ID from ep_menu "
					+ " where PARENT_ID!='00000000' and MENU_ID='"
					+ mid
					+ "' ";

		
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				parent_id = rs.getString("PARENT_ID");

				map.put(parent_id, parent_id); 
				getParentMenuSet(parent_id, map, conn);

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
			db.close();
		}

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
		String sql = "select * from ep_role_menu ";
		return queryList(sql);
	}

	public List selectMenuList(String role_id, UserContext user)
		throws Exception {
		/**选择父节点为"00000000"的菜单，即一级菜单
		 * 然后对每一菜单调用selectMenuPartList()方法，
		 * 即返回代表此菜单及其下所有菜单进行显示的对象的集合 
		 * **/

		List list = new ArrayList();

		Connection conn = null;

		try {
			conn = getConnection();
			selectMenuPartList(role_id, "00000000", list, conn);

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
		}finally{
			if(conn != null)
			conn.close();
		}

		return list;

	}

	/**方法用于返回：
	 * 能代表菜单menu_id及其下级菜单按层次显示信息的对象集合，
	 * 其中，对于角色role_id拥有的菜单权限有所标注
	 * **/
	public List selectMenuPartList(
		String role_id,
		String menu_id,
		List list,
		Connection conn)
		throws Exception {

			DBQueryUtil db = new DBQueryUtil();
		try {
			
			Statement stmt =
				conn.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);

			String sql = null;

			String mid = null; //菜单编号变量
			String name = null; //菜单名称变量			
			int level = 1; //菜单级别变量
			int check = 0; //是否属角色拥有权限的菜单，1为已选，0为非选

			/**获取菜单编号为menu_id的记录的相关字段值	
			 ***/

			sql = "select * from ep_menu where PARENT_ID='" + menu_id + "'";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				SysLog.debug("获取菜单开始");
				mid = rs.getString("MENU_ID"); //菜单编号
				name = rs.getString("MENU_NAME"); //菜单名称
				level = rs.getInt("MENU_LEVEL"); //菜单级别				
				SysLog.debug("菜单编号" + mid);

				/**查询菜单编号为menu_id的记录是否是角色role_id拥有权限的菜单，
				 * 即查询ep_role_menu表中是否有菜单编号为menu_id，角色为role_id的记录，
				 * 若有,是角色拥有权限菜单，用check=1表示,将来在页面将勾选复选框，
				 * 否则check=0，将来在页面不勾选复选框
				 * **/

				
				int count =
					db.sqlQueryCount(
						"select * from ep_role_menu where MENU_ID='"
							+ mid
							+ "' and ROLE_ID='"
							+ role_id
							+ "'");

				if (count <= 0) {
					check = 0;
				} else {
					check = 1;
				}

				SelectMenuVO vo = new SelectMenuVO();
				vo.setMenu_id(mid);
				vo.setMenu_name(name);
				vo.setMenu_level(level);
				vo.setCheck(check);
				vo.setCheck_display(1); //显示复选框，设置为1

				/**查询菜单编号为menu_id的记录是否具有下级菜单
							 ***/

				count =
					db.sqlQueryCount(
						"select * from ep_menu where PARENT_ID='" + mid + "'");

				if (count >= 1) {

					/**如果有下级菜单，check_display=0,
					 * 将来在页面将不显示复选框
					 * */

					vo.setCheck_display(0); //不显示复选框，设置check_display为0
					list.add(vo);

					selectMenuPartList(role_id, mid, list, conn);

				} else {

					/**如果无下级菜单，即菜单为叶级菜单，
					 * 设置check_display=1,将来在页面将显示复选框,
					 * 并显示勾选情况
					 **/

					vo.setCheck_display(1); //显示复选框，设置为1
					list.add(vo);

				}

				

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
			db.close();
		}
		return list;
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
		RoleMenuVO vo = new RoleMenuVO();
		vo.setRole_id(rs.getString("ROLE_ID"));
		vo.setMenu_id(rs.getString("MENU_ID"));
		return vo;
	}

	/* 
	 * 获取明细信息
	 */
	public BaseVO detail(ResultSet rs) throws Exception {
		RoleMenuVO vo = new RoleMenuVO();
		vo.setRole_id(rs.getString("ROLE_ID"));
		vo.setMenu_id(rs.getString("MENU_ID"));
		return vo;
	}

	/* 
	 * 
	 */
	public List list(PageObject page, UserContext user) throws Exception {

		return null;
	}

}
