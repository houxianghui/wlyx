/*********************************************************
 * File: UserBO.java
 * 
 * Version 1.0
 * 
 * Date     2005-9-27
 * 
 * Author   陈 蓉
 * 
 ********************************************************/

package com.eis.portal.user;

import java.util.List;
import java.sql.*;
import java.util.*;

import com.eis.base.*;
import com.eis.base.BaseVO;
import com.eis.base.BaseDAO;
import com.eis.base.PageObject;
import com.eis.portal.UserContext;
import com.eis.factory.*;
import com.eis.cache.*;
import com.eis.util.*;
import com.eis.connectionPool.*;

/** 
 * 说明：用户列表的业务逻辑类 
 */
public class UserBO extends BaseBO {

	/** 
	 * 构造函数 
	 */
	public UserBO() {
		super();
	}

	/**	 
	 * 返回用户user_id在全部角色中没有选择的角色
	 * @param user_id - 用户编号
	 * @return 查询到的角色列表
	 */
	public List listRolesNotSelected(
		String user_id,
		UserContext user,
		String orgID)
		throws Exception {
		/**
		 * 从角色定义表ep_role，用户角色关系表ep_user_role中,
		 * 选择出没有分配给用户user_id的全部角色。
		 */
		UserRoleDAO dao = (UserRoleDAO) BeanFactory.getBean("userrole_dao");
		String sql = null;
		if (user_id == null) { //增加用户
			//直接选择指定机构所有的角色和角色级别与机构相同的角色,角色状态为正常
			sql =			
				" select ROLE_ID,ROLE_NAME from ep_role where  STAT='1' ";			

		} else { //修改角色
			//直接选择指定机构所有的角色和角色级别与机构相同的角色而且不在用户已有角色范围内
			sql =
				" select ROLE_ID,ROLE_NAME from ep_role where STAT='1' "
				+" and ROLE_ID not in "
				+"(select ROLE_ID from ep_user_role where USER_ID='"+user_id+"')";	

		}

		return dao.queryList(sql);
	}

	/**	 
	 * 返回对用户user_id分配的角色
	 * @param user_id - 用户编号
	 * @return 查询到的角色列表
	 */
	public List listRolesSelected(String user_id, UserContext user)
		throws Exception {
		/**从用户角色关系表ep_user_role中（USER_ID,ROLE_ID），
		 * 选择已分配给用户user_id的角色集合
		 ***/
		UserRoleDAO dao = (UserRoleDAO) BeanFactory.getBean("userrole_dao");
		String sql = " select a.ROLE_ID,b.ROLE_NAME  ";
		sql = sql + "  from ep_user_role a,ep_role b  ";
		sql = sql + "  where a.ROLE_ID=b.ROLE_ID  ";
		sql = sql + "  and  a.USER_ID = '" + user_id + "'";
		return dao.queryList(sql);

	}
	
	/**	 
	 * 返回系统中存在的催收员角色信息
	 * @return 查询到的角色列表
	 */
	public List getCollRoles(
		UserContext user)
		throws Exception {
		/**
		 * 从角色定义表ep_role中获得所有的“催收员”角色信息
		 */
		UserRoleDAO dao = (UserRoleDAO) BeanFactory.getBean("userrole_dao");
		String sql = " select ROLE_ID,ROLE_NAME from ep_role where LOGIC_ID='coll'";
		
		List list = new ArrayList();
		list = dao.queryList(sql);
		List collList = new ArrayList();
		if(list != null){
			Iterator iter = list.iterator();
			while(iter.hasNext()){
				collList.add(((UserRoleVO)iter.next()).getRole_id().trim());
			}
		}
		return collList;
	}

	/**	 
	 * 校验用户已选角色列表中是否含有催收员角色
	 * @param  已选角色列表
	 * @param  催收员角色列表
	 * @return 查询到的角色列表
	 */
	public String doCollCheck(
		List rolesSelected,
    	List collRoles)
		throws Exception {
		/**
		 * 校验用户已选角色列表中是否含有催收员角色
		 */
		String collCheck = "1";
		if(rolesSelected != null){
			Iterator rs_iter = rolesSelected.iterator();
			String roleSel = null;
			while(rs_iter.hasNext()){
				roleSel = ((UserRoleVO)rs_iter.next()).getRole_id();
				if(collRoles != null){
					Iterator coll_iter = collRoles.iterator();
					String coll_id = null;
					while(coll_iter.hasNext()){
						coll_id = (String)coll_iter.next();
						if(roleSel.trim().equals(coll_id.trim())){
							collCheck = "0";
						}
					}
				}
			}
		}
		return collCheck;
	}


	/** 
	 * 增加用户定义表和用户角色关系表数据 
	 */
	public void add(String user_id, BaseVO vo, List list, UserContext user)
		throws Exception {

		//在同一事务中，增加用户定义表和用户角色关系表数据
		Connection conn = DBPoolManager.getConnection();
		conn.setAutoCommit(false);

		try {

			/**在用户定义表增加记录*/
			add(vo, user, conn);

			/**在用户角色关系表增加列表记录*/
			addList(user_id, list, user, conn);

			conn.commit();

			//登记增加用户操作日志		
			OpLog.Log(user, "03", "c", "增加用户", ((UserVO) vo).getUser_id());

		} catch (Exception ex) {
			conn.rollback();
		} finally {
			if (conn != null)
				conn.close();
		}

	}

	/** 
	 * 批量增加用户角色关系表列表数据 
	 */
	public void addList(
		String user_id,
		List list,
		UserContext user,
		Connection conn)
		throws Exception {
		UserRoleDAO dao = (UserRoleDAO) getBean("userrole_dao");
		dao.addList(user_id, list, user, conn);

	}

	/** 
	 * 更新用户定义表和用户角色关系表数据 
	 */
	public void update(String user_id, BaseVO vo, List list, UserContext user)
		throws Exception {

		//在同一事务中，增加用户定义表和用户角色关系表数据
		Connection conn = DBPoolManager.getConnection();
		conn.setAutoCommit(false);

		try {

			/**更新用户定义表*/
			update(vo, user, conn);

			/**更新用户角色关系表*/
			updateList(user_id, list, user, conn);

			conn.commit();

			//登记修改用户操作日志		
			OpLog.Log(user, "03", "u", "修改用户", ((UserVO) vo).getUser_id());

		} catch (Exception ex) {
			conn.rollback();
		} finally {
			if (conn != null)
				conn.close();
		}

	}

	/** 
	 * 修改用户列表
	 */
	public void update(BaseVO vo, UserContext user, Connection conn)
		throws Exception {

		BaseDAO dao = (BaseDAO) getBean("user_dao");
		dao.update(
			vo,
			" where USER_ID='" + ((UserVO) vo).getUser_id() + "'",
			conn);

	}

	public void update(BaseVO vo, UserContext user) throws Exception {
	}

	/** 
	 * 批量修改用户角色关系列表 
	 */
	public void updateList(
		String user_id,
		List list,
		UserContext user,
		Connection conn)
		throws Exception {
		UserRoleDAO dao = (UserRoleDAO) getBean("userrole_dao");
		dao.updateList(user_id, list, user, conn);

	}

	/** 
	 * 增加用户列表 
	 */
	public void add(BaseVO vo, UserContext user, Connection conn)
		throws Exception {
		BaseDAO dao = (BaseDAO) getBean("user_dao");
		dao.add(vo, conn);

	}

	public void add(BaseVO vo, UserContext user) throws Exception {
	}

	/**
	 * 设置废除状态
	 */
	public void terminate(BaseVO vo, UserContext user) throws Exception {

		//将用户user_id的状态设置为0，废除
		UserDAO dao = (UserDAO) BeanFactory.getBean("user_dao");
		String sql =
			"update ep_user set STAT='0',ST_CHG_DT='"
				+ ((UserVO) vo).getSt_chg_dt()
				+ "',ADMIN_ID='"
				+ ((UserVO) vo).getAdmin_id()
				+ "'";
		sql = sql + " where USER_ID ='" + ((UserVO) vo).getUser_id() + "'";
		dao.executeUpdate(sql);

		//登记废除用户操作日志		
		OpLog.Log(user, "03", "u", "废除用户", ((UserVO) vo).getUser_id());

	}

	/**
	 * 设置启用状态
	 */
	public void able(BaseVO vo, UserContext user) throws Exception {

		//将用户user_id的状态设置为1，正常
		UserDAO dao = (UserDAO) BeanFactory.getBean("user_dao");
		String sql =
			"update ep_user set STAT='1',ST_CHG_DT='"
				+ ((UserVO) vo).getSt_chg_dt()
				+ "',ADMIN_ID='"
				+ ((UserVO) vo).getAdmin_id()
				+ "'";
		sql = sql + " where USER_ID ='" + ((UserVO) vo).getUser_id() + "'";
		dao.executeUpdate(sql);

		//登记启用用户操作日志		
		OpLog.Log(user, "03", "u", "启用用户", ((UserVO) vo).getUser_id());

	}

	/**
	 * 设置停用状态
	 */
	public void disable(BaseVO vo, UserContext user) throws Exception {

		//将用户user_id的状态设置为2，停用
		UserDAO dao = (UserDAO) BeanFactory.getBean("user_dao");
		String sql =
			"update ep_user set STAT='2',ST_CHG_DT='"
				+ ((UserVO) vo).getSt_chg_dt()
				+ "',ADMIN_ID='"
				+ ((UserVO) vo).getAdmin_id()
				+ "'";
		sql = sql + " where USER_ID ='" + ((UserVO) vo).getUser_id() + "'";
		dao.executeUpdate(sql);

		//登记停用用户操作日志		
		OpLog.Log(user, "03", "u", "停用用户", ((UserVO) vo).getUser_id());

	}
	public void exclude(BaseVO vo, UserContext user) throws Exception {

		//将用户user_id的状态设置为2，停用
		UserDAO dao = (UserDAO) BeanFactory.getBean("user_dao");
		String sql =
			"update ep_user set STAT='4',ST_CHG_DT='"
				+ ((UserVO) vo).getSt_chg_dt()
				+ "',ADMIN_ID='"
				+ ((UserVO) vo).getAdmin_id()
				+ "'";
		sql = sql + " where USER_ID ='" + ((UserVO) vo).getUser_id() + "'";
		dao.executeUpdate(sql);

	}


	/** 
	 * 批量增加用户列表 
	 */
	public void addList(BaseVO[] list, UserContext user) throws Exception {

		BaseDAO dao = (BaseDAO) getBean("user_dao");
		dao.addList(list);

	}

	/** 
	 * 批量修改用户列表 
	 */
	public void updateList(BaseVO[] list, UserContext user) throws Exception {

	}

	/** 
	 * 删除用户列表 
	 */
	public void delete(BaseVO vo, UserContext user) throws Exception {

		/**在同一事务中,先删除用户角色关系，再删除用户
		 * **/
		Connection conn = DBPoolManager.getConnection();
		conn.setAutoCommit(false);

		try {

			/**删除用户角色关系 */
			UserRoleDAO userroledao = (UserRoleDAO) getBean("userrole_dao");
			userroledao.delete(
				userroledao.getDeleteSQL()
					+ " where USER_ID='"
					+ ((UserVO) vo).getUser_id()
					+ "'",
				conn);

			/**删除用户 */
			BaseDAO dao = (BaseDAO) getBean("user_dao");
			dao.delete(
				dao.getDeleteSQL()
					+ " where USER_ID='"
					+ ((UserVO) vo).getUser_id()
					+ "'",
				conn);

			//提交事务
			conn.commit();

			//登记删除用户操作日志		
			OpLog.Log(user, "03", "d", "删除用户", ((UserVO) vo).getUser_id());

		} catch (Exception ex) {
			conn.rollback();
		} finally {
			if (conn != null)
				conn.close();
		}

	}

	/** 
	 * 批量删除用户列表 
	 */
	public void deleteList(BaseVO[] list, UserContext user) throws Exception {

	}

	/** 
	 * 查询并返回用户的角色列表 
	 */
	public List queryRolesList(UserContext user) throws Exception {

		UserRoleDAO dao = (UserRoleDAO) getBean("userrole_dao");

		return dao.queryList(
			"select ROLE_ID,0 as ROLE_NAME from ep_user_role where USER_ID='"
				+ user.getUserID()
				+ "'");

	}

	/** 
	 * 查询列表，没有查询条件，返回所有纪录 
	 */
	public List queryList(UserContext user) throws Exception {

		return null;

	}

	/** 
	 * 查询列表，根据查询条件返回符合条件的一页纪录 
	 */
	public List queryList(PageObject page, UserContext user) throws Exception {

		BaseDAO dao = (BaseDAO) getBean("user_dao");
		StringBuffer sql = new StringBuffer("select * from ep_user where 1=1 and USER_ID in(select USER_ID from ep_user_role where ROLE_ID in(select ROLE_ID from ep_role where LOGIC_ID='check'))" );
		//在此处添加模糊匹配条件 
		
		
		String amsd_store_f = (String)page.getFilter("cre_branch_f");
		if (amsd_store_f != null && amsd_store_f.trim().length() > 0) {
			sql.append(" and AMSD_STORE = '" + amsd_store_f + "'");
		}
		
		//执行查询 
		page.setList(dao.queryPage(page, sql.toString()));
		return page.getList();

	}
	public List queryList1(PageObject page, UserContext user) throws Exception {

		 BaseDAO dao = (BaseDAO) getBean("user_dao");
		 StringBuffer sql = new StringBuffer("select * from ep_user where 1=1 and USER_ID in(select USER_ID from ep_user_role where ROLE_ID in(select ROLE_ID from ep_role where LOGIC_ID='check'))" );
		 //在此处添加模糊匹配条件 
	
	
		 String amsd_store_f = (String)page.getFilter("cre_branch_f");
	     if (amsd_store_f != null && amsd_store_f.trim().length() > 0) {
		   sql.append(" and AMSD_STORE = '" + amsd_store_f + "'");
	     }
	
		 //执行查询 
		 page.setList(dao.queryPage(page, sql.toString()));
		 return page.getList();
	}
	/** 
	 * 查询维护数据，根据查询条件返回符合条件的一页纪录 
	 */
	public List list(PageObject page, UserContext user) throws Exception {

		BaseDAO dao = (BaseDAO) getBean("user_dao");
		StringBuffer sql =
			new StringBuffer("select * from ep_user  where 1=1 ");

		//模糊匹配条件 	

		//添加用户所属机构查询过滤条件	
		String org_id = (String) page.getFilter("amsd_store_f");

		if (org_id != null)
			sql.append(" and AMSD_STORE = '" + org_id + "'");

		//添加用户姓名查询过滤条件
		String user_name = (String) page.getFilter("user_name_f");

		if (user_name != null && user_name.trim().length() > 0)
			sql.append(" and USER_NAME like '%" + user_name + "%'");

		//添加用户状态查询过滤条件 
		String status = (String) page.getFilter("status_f");

		if (status != null && status.trim().length() > 0)
			sql.append(" and STAT = '" + status + "'");

		SysLog.debug(sql.toString());

		//执行查询 
		page.setList(dao.queryPage(page, sql.toString()));
		return page.getList();

	}
	public List list2(PageObject page, UserContext user) throws Exception {

		BaseDAO dao = (BaseDAO) getBean("user_dao");

		StringBuffer sql =
		//查询当前登陆人员所属机构及其下属机构
			new StringBuffer("select ep_user.* from ep_user where 1=1 " );
		//查询语句

		/**
		 * 限制查寻范围
		 */
		
		sql.append(" and USER_ID in(select USER_ID from ep_user_role where ROLE_ID in(select ROLE_ID from ep_role where LOGIC_ID='check'))");
		
		
	

		//模糊匹配条件 	

		
		//执行查询 
		page.setList(dao.queryLargePage(page, sql.toString()));
		return page.getList();

	}
	public List queryAssignOperList(PageObject page, UserContext user,String amsdStore) throws Exception {

		BaseDAO dao = (BaseDAO) getBean("user_dao");
		StringBuffer sql =new StringBuffer("select * from ep_user where 1=1 and AMSD_STORE='"+amsdStore+ "' and USER_ID in(select USER_ID from ep_user_role where ROLE_ID in(select ROLE_ID from ep_role where LOGIC_ID in('check','phone')))");
		
		//添加用户姓名查询过滤条件
	    String user_name = (String) page.getFilter("user_name_f");
	    if (user_name != null && user_name.trim().length() > 0)
			sql.append(" and USER_NAME like '%" + user_name + "%'");
			
		page.setList(dao.queryLargePage(page, sql.toString()));
		return page.getList();

	}
	
	/**
	 * 判断一个用户是否是复核员或者电话复核员,依据logicID
	 * @param vo
	 * @param logicId
	 * @return
	 * @throws Exception
	 */
	public boolean judgeUserLogicID(UserContext vo,String logicId) throws Exception{
		BaseDAO dao = (BaseDAO) getBean("user_dao");
		String sql="select * from ep_user_role where USER_ID='"+vo.getUserID()+"' and ROLE_ID in(select ROLE_ID from ep_role where LOGIC_ID='"+logicId+"')";
		if(dao.queryCount(sql)>0)
			return true;
		else
			return false;
	}

	/** 
	 * 查询维护数据，返回所有纪录 
	 */
	public List list(UserContext user) throws Exception {

		return null;

	}

	/** 
	 * 查询明细信息，返回单一纪录 
	 */
	public BaseVO retrieve(BaseVO vo, UserContext user) throws Exception {

		UserVO bean = (UserVO) vo;
		BaseDAO dao = (BaseDAO) getBean("user_dao");

		BaseVO v =
			dao.retrieve(
				dao.getQuerySQL()
					+ " where USER_ID='"
					+ ((UserVO) vo).getUser_id()
					+ "'");

		//登记用户操作日志		
		OpLog.Log(user, "03", "r", "查询用户", ((UserVO) vo).getUser_id());

		return v;

	}
	public UserVO retrieve1(BaseVO vo, UserContext user) throws Exception {

			UserVO bean = (UserVO) vo;
			BaseDAO dao = (BaseDAO) getBean("user_dao");

			UserVO v =
				(UserVO)dao.retrieve(
					dao.getQuerySQL()
						+ " where USER_ID='"
						+ ((UserVO) vo).getUser_id()
						+ "'");
      
			//登记用户操作日志		


			return v;

		}

	/** 
	 * 根据where条件查询明细信息，返回单一纪录 
	 */
	public BaseVO retrieve(String whereClause, UserContext user)
		throws Exception {

		BaseDAO dao = (BaseDAO) getBean("user_dao");
		return dao.retrieve(dao.getQuerySQL() + whereClause);

	}

	/**返回用户代码为最大值的记录 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public String retrieveMax(UserContext user) throws Exception {

		UserDAO dao = (UserDAO) BeanFactory.getBean("user_dao");
		return dao.retrieveMax(user);

	}
	/**返回用户登陆编号为login_id的记录
	 * */
	public String retrieveLoginId(String login_id, UserContext user)
		throws Exception {

		UserDAO dao = (UserDAO) BeanFactory.getBean("user_dao");
		return dao.retrieveLoginId(login_id, user);
	}

	public String checkLoginIdRepeat(BaseVO vo, UserContext user)
		throws Exception {
		//更新用户时，检查登录编号有无重复
		BaseDAO dao = (BaseDAO) getBean("user_dao");

		String repeat = "n";

		SysLog.debug("用户原来登录编码为：" + ((UserVO) vo).getLogin_id2());
		SysLog.debug("用户新输入的登录编码为：" + ((UserVO) vo).getLogin_id());

		String sql1 =
			dao.getQuerySQL()
				+ " where LOGIN_ID='"
				+ ((UserVO) vo).getLogin_id()
				+ "' and LOGIN_ID <> '"
				+ ((UserVO) vo).getLogin_id2()
				+ "'";

		SysLog.debug(sql1);

		if (dao.queryCount(sql1) > 0) {
			repeat = "y";
			BaseException ex = new BaseException();
			ex.setErrorCode("E010041");
			throw ex;
		}
		return repeat;
	}

	/** 
	 * 查询用户列表 
	 */
	public List listpop(PageObject page, UserContext user) throws Exception {

		BaseDAO dao = (BaseDAO) getBean("user_dao");
		StringBuffer sql =
			new StringBuffer("select * from ep_user where 1=1 ");

		//模糊匹配条件	
		String org_id = (String) page.getFilter("amsd_store_f");
		String user_name = (String) page.getFilter("user_name_f");
		if (org_id != null && org_id.trim().length() > 0)
			sql.append(" and AMSD_STORE = '" + org_id + "'");
		if (user_name != null && user_name.trim().length() > 0)
			sql.append(" and USER_NAME = '" + user_name + "'");

		SysLog.debug(sql.toString());

		//执行查询 
		page.setList(dao.queryPage(page, sql.toString()));
		return page.getList();
	}
	
	/**
	 * 重置密码
	 */
	public void resetPwd(String user_id, UserContext user) throws Exception {

		//将用户user_id的状态设置为2，停用
		UserDAO dao = (UserDAO) BeanFactory.getBean("user_dao");
		String sql ="update ep_user set PASSWORD='"+ MD5Util.digest("00000000") + "' where USER_ID ='" + user_id + "'";
		dao.executeUpdate(sql);
	}

	/**若所选角色中，同时包括录入员和复核员，则报错，保存失败
	 * 根据角色编号role_id，取得控制代码logic_id*/
	public String getLogicIdByRoleId(String role_id, UserContext user) throws Exception {

		//将用户user_id的状态设置为2，停用
		UserDAO dao = (UserDAO) BeanFactory.getBean("user_dao");
		String sql ="select LOGIC_ID from ep_role where ROLE_ID = '"+role_id+"'";
		return dao.querySingle(sql);
	}
	/***/

}
