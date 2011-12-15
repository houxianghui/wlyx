/*********************************************************
 * File: LoginForm.java
 * 
 * Version 1.0
 * 
 * Date     2005-9-19
 * 
 * Author   lihaibao
 * 
 ********************************************************/

package com.eis.portal.password;

import com.eis.base.BaseForm;

/**
 * 说明：修改密码form对象
 * 
 */
public class PasswordForm extends BaseForm {

	/**
	 * 构造函数
	 */
	public PasswordForm() {
		super();
		
	}
	
	/**
	 * 用户编号
	 */
	private String user_id;
	

	/**
	 * 主角色代码
	 */
	private String role_id;

	/**
	 * 登录编号
	 */
	private String login_id;


	/**
	 * 原密码
	 */
	private String oldPassword;
	
	/**
	 * 密码
	 */
	private String password;



		
	/**
	 * 状态
	 */
		private String status;
		
	/**
	 * 建立日期
	 */
		private String reg_dt;
		
	/**
	 * 起始日期
	 */
		private String begin_dt;
		
	/**
	 * 失效日期
	 */
		private String invalid_dt;
		
	/**
	 * 密码失效日期
	 */
		private String modify_dt;

		
	/**
	 * 更新日期
	 */
		private String st_chg_dt;
		
	/**
	 * 
	 */
		private String admin_id;
		

		
		
	/**
	 * @return
	 */
	public String getUser_id() {
		return user_id;
	}
	

	/**
	 * @return
	 */
	public String getRole_id() {
		return role_id;
	}

	/**
	 * @return
	 */
	public String getLogin_id() {
		return login_id;
	}


	/**
	 * @return
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @return
	 */
	public String getOldPassword() {
		return oldPassword;
	}






	/**
	 * @return
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @return
	 */
	public String getReg_dt() {
		return reg_dt;
	}
	/**
	 * @return
	 */
	public String getBegin_dt() {
		return begin_dt;
	}
	/**
	 * @return
	 */
	public String getInvalid_dt() {
		return invalid_dt;
	}
	/**
	 * @return
	 */
	public String getModify_dt() {
		return modify_dt;
	}

	/**
	 * @return
	 */
	public String getSt_chg_dt() {
		return st_chg_dt;
	}
	/**
	 * @return
	 */
	public String getAdmin_id() {
		return admin_id;
	}



	/**
	 * @param string
	 */
	public void setUser_id(String str) {
		user_id = str;
	}


	/**
	 * @param string
	 */
	public void setReg_dt(String str) {
		reg_dt = str;
	}

	/**
	 * @param string
	 */
	public void setRole_id(String str) {
		role_id = str;
	}

	/**
	 * @param string
	 */
	public void setLogin_id(String str) {
		login_id = str;
	}

	/**
	 * @param string
	 */
	public void setPassword(String str) {
		password = str;
	}

	/**
	 * @param string
	 */
	public void setOldPassword(String str) {
		oldPassword = str;
	}




	/**
	 * @param string
	 */
	public void setStatus(String str) {
		status = str;
	}

	/**
	 * @param string
	 */
	public void setBegin_dt(String str) {
		begin_dt = str;
	}

	/**
	 * @param string
	 */
	public void setInvalid_dt(String str) {
		invalid_dt = str;
	}

	/**
	 * @param string
	 */
	public void setModify_dt(String str) {
		modify_dt = str;
	}

	/**
	 * @param string
	 */
	public void setSt_chg_dt(String str) {
		st_chg_dt = str;
	}

	/**
	 * @param string
	 */
	public void setAdmin_id(String str) {
		admin_id = str;
	}

}
