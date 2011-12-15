/*********************************************************
 * File: RoleOPVO.java
 * 
 * Version 1.0
 * 
 * Date     2005-9-24
 * 
 * Author   Chen Rong
 * 
 ********************************************************/

package com.eis.portal.user;

import com.eis.base.BaseVO;

/**
 * 说明：用户角色关系的数据对象
 * 
 */
public class UserRoleVO extends BaseVO {

	/**
	 * 构造函数
	 */
	public UserRoleVO() {
		super();
		
	}
		
	private String user_id	;	//用户代码
	
	private String role_id	;	//角色代码	
	
	private String role_name;	//角色名称	
			


	/********   get 方法 *******/



	/**
	 * @return 返回用户代码
	 */
	public String getUser_id() {
		return user_id;
	}
	
	
	/**
	 * @return 返回角色代码
	 */
	public String getRole_id() {
		return role_id;
	}
	
	/**
	 * @return 返回角色名称
	 */
	public String getRole_name() {
		return role_name;
	}
			
	
	/********   set 方法 *******/	
	


		
	/**
	 * @param 设置用户代码
	 */
	public void setUser_id(String str) {
		user_id = str;
	}
	
	/**
	 * @param 设置角色代码
	 */
	public void setRole_id(String str) {
		role_id = str;
	}
	
	/**
	 * @param 设置角色名称
	 */
	public void setRole_name(String str) {
		role_name = str;
	}

	

}

