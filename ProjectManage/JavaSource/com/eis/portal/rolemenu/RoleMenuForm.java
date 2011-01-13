/*********************************************************
 * File: RoleMenuForm.java
 * 
 * Version 1.0
 * 
 * Date     2005-9-21
 * 
 * Author   Chen Rong
 * 
 ********************************************************/

package com.eis.portal.rolemenu;

import com.eis.base.BaseForm;

/**
 * 说明：角色菜单权限管理部分
 * 
 */
public class RoleMenuForm extends BaseForm {

	/**
	 * 构造函数
	 */
	public RoleMenuForm() {
		super();
		
	}
	
	private String role_id;	//角色代码
	
	private String role_name;	//角色名称
	
	private String menu_id;	//菜单编号		


	/********   get 方法 *******/


	/**
	 * @return
	 */
	public String getRole_id() {
		return role_id;
	}

	/**
	 * @return
	 */
	public String getMenu_id() {
		return menu_id;
	}
		
	
	
	/********   set 方法 *******/	
	

	/**
	 * @param string
	 */
	public void setRole_id(String str) {
		role_id = str;
	}

	/**
	 * @param string
	 */
	public void setMenu_id(String str) {
		menu_id = str;
	}



	/**
	 * @return
	 */
	public String getRole_name() {
		return role_name;
	}

	/**
	 * @param string
	 */
	public void setRole_name(String string) {
		role_name = string;
	}

}
