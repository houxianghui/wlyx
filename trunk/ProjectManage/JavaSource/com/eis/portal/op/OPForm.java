/*********************************************************
 * File: OPForm.java
 * 
 * Version 1.0
 * 
 * Date     2005-9-20
 * 
 * Author   陈 蓉
 * 
 ********************************************************/

package com.eis.portal.op;

import com.eis.base.BaseForm;

/**
 * 说明：
 * 
 */
public class OPForm extends BaseForm {

	/**
	 * 构造函数
	 */
	public OPForm() {
		super();
		
	}
	
	/**
	* 功能菜单编号
	*/
	private String menu_id;
	
	/**
	 * 功能菜单名称
	 */
	private String menu_name;
	
	/**
	* 权限码
	*/

	private String op_code;
	
	/**
	* 操作名称
	*/
	private String caption;
	
	
	
	/******** get方法 *********/

	 /**
	  * @return 返回功能菜单编号
	  */
	 public String getMenu_id() {
		 return menu_id;
	 }
	 
	/**
	 * @return 返回功能菜单名称
	 */
	public String getMenu_name() {
		return menu_name;
	}

	 /**
	  * @return 返回权限码
	  */
	 public String getOp_code() {
		 return op_code;
	 }

	 /**
	  * @return 返回操作名称
	  */
	 public String getCaption() {
		 return caption;
	 }	
	
	
	
	 /******** set方法 *********/
	
	

	 /**
	  * @param 设置功能菜单编号
	  */
	 public void setMenu_id(String str) {
		 menu_id = str;
	 }
	 
	/**
	 * @param 设置功能菜单名称
	 */
	public void setMenu_name(String str) {
		menu_name = str;
	}

	 /**
	  * @param 设置权限码
	  */
	 public void setOp_code(String str) {
		 op_code = str;
	 }

	 /**
	  * @param 设置操作名称
	  */
	 public void setCaption(String str) {
		 caption = str;
	 }



}
