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
 * ˵������ɫ�˵�Ȩ�޹�����
 * 
 */
public class RoleMenuForm extends BaseForm {

	/**
	 * ���캯��
	 */
	public RoleMenuForm() {
		super();
		
	}
	
	private String role_id;	//��ɫ����
	
	private String role_name;	//��ɫ����
	
	private String menu_id;	//�˵����		


	/********   get ���� *******/


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
		
	
	
	/********   set ���� *******/	
	

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
