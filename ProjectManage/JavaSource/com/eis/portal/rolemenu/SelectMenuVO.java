/*********************************************************
 * File: SelectMenuVO.java
 * 
 * Version 1.0
 * 
 * Date     2005-9-22
 * 
 * Author   Chen Rong
 * 
 ********************************************************/

package com.eis.portal.rolemenu;

import com.eis.base.BaseVO;

/**
 * 说明：角色选择菜单的数据对象
 * 
 */
public class SelectMenuVO extends BaseVO {

	/**
	 * 构造函数
	 */
	public SelectMenuVO() {
		super();
		
	}

	private String menu_id;	//菜单编号
	
	private String menu_name;	//菜单名称
	
	private int menu_level;	//菜单级别
	
	private int check;	//菜单勾选标志，1为勾选，0为不选	
	
	private int check_display;	//是否显示菜单复选框标志，1为显示，0为不显示		


	/********   get 方法 *******/


	/**
	 * @return 返回菜单编号
	 */
	public String getMenu_id() {
		return menu_id;
	}

	/**
	 * @return 返回菜单名称
	 */
	public String getMenu_name() {
		return menu_name;
	}
		
	/**
	 * @return 返回菜单级别
	 */
	public int getMenu_level() {
		return menu_level;
	}	
	
	/**
	 * @return 返回勾选与否标志
	 */
	public int getCheck() {
		return check;
	}
	
	/**
	 * @return 返回是否显示复选框标志
	 */
	public int getCheck_display() {
		return check_display;
	}
			
	
	/********   set 方法 *******/	
	

	/**
	 * @param string
	 */
	public void setMenu_id(String str) {
		menu_id = str;
	}

	/**
	 * @param string
	 */
	public void setMenu_name(String str) {
		menu_name = str;
	}
	
	/**
	 * @param int
	 */
	public void setMenu_level(int i) {
		menu_level = i;
	}
	
	/**
	 * @param string
	 */
	public void setCheck(int i) {
		check = i;
	}
	
	/**
	 * @param string
	 */
	public void setCheck_display(int i) {
		check_display = i;
	}	


}
