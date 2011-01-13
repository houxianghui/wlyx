/*********************************************************
 * File: UserRoleDAO.java
 * 
 * Version 1.0
 * 
 * Date     2005-9-28
 * 
 * Author   陈 蓉
 * 
 ********************************************************/ 
 
package com.eis.portal.user; 
 
import java.util.*; 
import java.sql.*; 
 
import com.eis.base.BaseVO; 
import com.eis.base.BaseDAO; 
import com.eis.base.PageObject; 
import com.eis.portal.UserContext; 
import com.eis.util.*; 
import com.eis.base.*;
 
 
/** 
 * 说明：用户角色关系的数据库访问类 
 */ 
public class UserRoleDAO extends BaseDAO { 
 
	/** 
	 * 构造函数 
	 */ 
	public UserRoleDAO() { 
		super(); 
	} 
 
	/** 
	 * 构造函数 
	 */ 
	public UserRoleDAO(String dsName) { 
		super(dsName); 
	} 
 
	/** 
	 * 初始化数据库访问语句 
	 */ 
	public void initSQL() { 
 
		setInsertSQL("  insert into ep_user_role(USER_ID,ROLE_ID) values (?,?)"); 
		setUpdateSQL(" update ep_user_role set USER_ID=?,ROLE_ID=?"); 
		setQuerySQL(" select * from  ep_user_role ");  
		setQuerySQL(" select * from  ep_user_role ");  
		setDeleteSQL(" delete from  ep_user_role ");  
		//此处添加order by 语句 
		//setOrderBy(" order by REG_DT"); 
 
	} 
 
	/** 
	 * 对执行数据增加的PreparedStatement中的参数进行赋值 
	 */ 
	public void prepareInsertStatement(PreparedStatement ps, BaseVO bean) throws Exception { 
 
		UserVO vo = (UserVO) bean;
		ps.setString(1, vo.getUser_id());  
		ps.setString(2, vo.getRole_id()); 
				 
 
	} 
 
	/** 
	 * 对执行数据修改的PreparedStatement中的参数进行赋值 
	 */ 
	public void prepareUpdateStatement(PreparedStatement ps, BaseVO bean) throws Exception { 
 
		UserVO vo = (UserVO) bean; 
		ps.setString(1, vo.getUser_id()); 
		ps.setString(2, vo.getRole_id()); 		
 
	} 
 
	/** 
	 * 删除用户角色列表 
	 */ 
	public void delete(BaseVO vo) throws Exception { 
 
		delete(getDeleteSQL()+" where USER_ID='"+((UserRoleVO) vo).getUser_id()+"'"); 
 
	} 
 


	/**增加用户角色关系列表*/
	public void addList(String user_id, List list, UserContext user,Connection conn) throws Exception {
		/** 将新用户的角色分配逐条加入到用户角色关系表ep_user_role中
		 ***/			

		if (list != null) {
			
			PreparedStatement ps = null;

			try {				
				ps = conn.prepareStatement(getInsertSQL());

				Iterator iter = list.iterator();
				while (iter.hasNext()) {
					String role_id = (String) iter.next();
					
					ps.setString(1, user_id);
					ps.setString(2, role_id);
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

			}
		}

	}


	
	/**更新用户角色关系*/
	public void updateList(String user_id, List list, UserContext user,Connection conn) throws Exception {
		/**删除对用户user_id原来的角色选择，
		 * 将新的角色分配逐条加入到用户角色关系表ep_user_role中
		 * **/

		//删除用户user_id的所有角色选择			
		delete(user_id, user);  //cannot delete last ep_user_role because ep_user exists?
		SysLog.debug("after delete from 用户角色关系表");

		/**将用户现在的角色选择集合添加到数据库中**/		

		if (list != null) {
			
			PreparedStatement ps = null;

			try {				
				ps = conn.prepareStatement(getInsertSQL());

				Iterator iter = list.iterator();
				while (iter.hasNext()) {
					String role_id = (String) iter.next();
					
					ps.setString(1, user_id);
					ps.setString(2, role_id);
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
			}
		}

	}
	
	
	/**删除用户角色分配*/
	public void delete(String user_id, UserContext user) throws Exception {
		/** 删除用户角色关系表ep_user_role中所有有关用户user_id的记录
		 * **/
		delete("delete from ep_user_role where USER_ID='" + user_id + "'");
		//要返回row吗？	

	}
	
 
	/** 
	 * 查询列表，根据查询条件返回一页数据 
	 */ 
	public List queryList(PageObject page, UserContext user) throws Exception { 
 
		return null; 
 
	} 
 
	/** 
	 * 维护功能的列表，根据查询条件，返回一页数据 
	 */ 
	public List list(PageObject page, UserContext user) throws Exception { 
 
		return null; 
 
	} 
 

 
	/** 
	 * 获取列表数据的一条纪录 
	 */ 
	public BaseVO retrieveResult(ResultSet rs) throws Exception { 
 
		UserRoleVO vo = new UserRoleVO();
		vo.setRole_id(rs.getString("ROLE_ID").trim()); 		 
		vo.setRole_name(rs.getString("ROLE_NAME").trim()); 		
		return vo; 
 
	} 
 
	/** 
	 * 获取明细信息 
	 */ 
	public BaseVO detail(ResultSet rs) throws Exception { 
 
		UserVO vo = new UserVO(); 
		vo.setRole_id(rs.getString("ROLE_ID").trim());
		vo.setUser_id(rs.getString("USER_ID").trim());		
		return vo; 
 
	} 
 
} 

