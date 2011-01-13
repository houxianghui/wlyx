/*********************************************************
 * File: MenuForm.java
 * 
 * Version 1.0
 * 
 * Date     2005-9-14
 * 
 * Author   陈 蓉
 * 
 ********************************************************/

package com.eis.portal.menu;

import com.eis.base.BaseForm;

/**
 * 说明：
 * 
 */
public class MenuForm extends BaseForm {

	/**
	 * 构造函数
	 */
	public MenuForm() {
		super();
		
	}
	
	/**
	 * 菜单编号
	 */
	private String menu_id;
	
	/**
	 * 上级菜单
	 */

	private String parent_id;
	
	/**
	 * 上级菜单名称
	 */

	private String parent_name;
	
	/**
	 * 菜单名称
	 */
	private String menu_name;

	/**
	 * 叶子节点标志
	 */
	private String menu_mark;

	/**
	 * 菜单级次
	 */
	private String menu_level;

	/**
	 * 显示顺序
	 */
	private String list_order;
	
	

	/**
	 * 菜单URL
	 */
	private String menu_url;
	
	
	
	
	
   /******** get方法 *********/

	/**
	 * @return 获取菜单ID 
	 */
	public String getMenu_id() {
		return menu_id;
	}

	/**
	 * @return 获取上级菜单ID
	 */
	public String getParent_id() {
		return parent_id;
	}
	
	/**
	 * @return 获取上级菜单名称
	 */
	public String getParent_name() {
		return parent_name;
	}

	/**
	 * @return 获取菜单名称
	 */
	public String getMenu_name() {
		return menu_name;
	}

	/**
	 * @return
	 */
	public String getMenu_mark() {
		return menu_mark;
	}

	/**
	 * @return 获取菜单级别
	 */
	public String getMenu_level() {
		return menu_level;
	}

	/**
	 * @return 获取菜单显示顺序L
	 */
	public String getList_order() {
		return list_order;
	}
	


	/**
	 * @return 获取菜单URL
	 */
	public String getMenu_url() {
		return menu_url;
	}
	
	
	
	/******** set方法 *********/
	
	

	/**
	 * @param string  设置菜单ID
	 */
	public void setMenu_id(String str) {
		menu_id = str;
	}

	/**
	 * @param string 设置上级菜单ID
	 */
	public void setParent_id(String str) {
		parent_id = str;
	}
	
	/**
	 * @param string 设置上级菜单名称
	 */
	public void setParent_name(String str) {
		parent_name = str;
	}

	/**
	 * @param string 设置菜单名称
	 */
	public void setMenu_name(String str) {
		menu_name = str;
	}

	/**
	 * @param string
	 */
	public void setMenu_mark(String str) {
		menu_mark = str;
	}

	/**
	 * @param int 设置菜单级别
	 */
	public void setMenu_level(String str) {
		menu_level = str;
	}

	/**
	 * @param int 设置菜单显示顺序
	 */
	public void setList_order(String str) {
		list_order = str;
	}
	


	/**
	 * @param string 设置菜单URL
	 */
	public void setMenu_url(String str) {
		menu_url = str;
	}



}
