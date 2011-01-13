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

package com.eis.portal.roleop;

import com.eis.base.BaseVO;

/**
 * 说明：角色操作权限的数据对象
 * 
 */
public class RoleOPVO extends BaseVO {

	/**
	 * 构造函数
	 */
	public RoleOPVO() {
		super();
		
	}

	private String role_id	;	//角色代码
	
	private String caption	;	//操作名称
	
	private String op_code;	//权限码	
			


	/********   get 方法 *******/

	/**
	 * @return 返回角色代码
	 */
	public String getRole_id() {
		return role_id;
	}

	/**
	 * @return 返回操作名称
	 */
	public String getCaption() {
		return caption;
	}

	/**
	 * @return 返回权限码
	 */
	public String getOp_code() {
		return op_code;
	}		
	
			
	
	/********   set 方法 *******/	
	

	/**
	 * @param 设置角色代码
	 */
	public void setRole_id(String str) {
		role_id = str;
	}
		
	/**
	 * @param 设置操作名称
	 */
	public void setCaption(String str) {
		caption = str;
	}

	/**
	 * @param 设置权限码
	 */
	public void setOp_code(String str) {
		op_code = str;
	}	
	

}

