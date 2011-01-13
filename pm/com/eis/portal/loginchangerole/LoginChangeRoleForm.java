/*********************************************************
 * File: LoginChangeRoleForm.java
 * 
 * Version 1.0
 * 
 * Date     2005-10-19
 * 
 * Author   Chen Rong
 * 
 ********************************************************/

package com.eis.portal.loginchangerole;

import com.eis.base.BaseForm;

/**
 * ˵�����û���ɫ��ϵ�����ݶ���
 * 
 */
public class LoginChangeRoleForm extends BaseForm {

	/**
	 * ���캯��
	 */
	public LoginChangeRoleForm() {
		super();
		
	}
		
	private String user_id	;	//�û�����
	
	private String role_id	;	//��ɫ����	
	
	private String role_name;	//��ɫ����	
			


	/********   get ���� *******/



	/**
	 * @return �����û�����
	 */
	public String getUser_id() {
		return user_id;
	}
	
	
	/**
	 * @return ���ؽ�ɫ����
	 */
	public String getRole_id() {
		return role_id;
	}
	
	/**
	 * @return ���ؽ�ɫ����
	 */
	public String getRole_name() {
		return role_name;
	}
			
	
	/********   set ���� *******/	
	


		
	/**
	 * @param �����û�����
	 */
	public void setUser_id(String str) {
		user_id = str;
	}
	
	/**
	 * @param ���ý�ɫ����
	 */
	public void setRole_id(String str) {
		role_id = str;
	}
	
	/**
	 * @param ���ý�ɫ����
	 */
	public void setRole_name(String str) {
		role_name = str;
	}

	

}

