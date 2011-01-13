/*********************************************************
 * File: LoginVO.java
 * 
 * Version 1.0
 * 
 * Date     2005-9-19
 * 
 * Author   lihaibao
 * 
 ********************************************************/

package com.eis.portal.login;

import com.eis.base.BaseVO;

/**
 * 说明：用户登录的数据对象
 * 
 */
public class LoginVO extends BaseVO {

	/**
	 * 构造函数
	 */
	public LoginVO() {
		super();
		
	}

	/**
	 * 用户编号
	 */
	private String user_id;
	
	/**
	 * 网点号
	 */

	private String amsd_store;
	
	/**
	 * 部门代码
	 */
	private String depart_id;

	/**
	 * 主角色代码
	 */
	private String role_id;

	/**
	 * 登录编号
	 */
	private String login_id;

	/**
	 * 用户姓名
	 */
	private String user_name;

	/**
	 * 密码
	 */
	private String password;

	/**
	 * 联系电话
	 */
	private String phone;
		
	/**
	 * 手机
	 */
	private String mobile;
		
	/**
	 * 电子邮件
	 */
	private String email;
		
	/**
	 * 邮编
	 */
	private String postcode;
		
	/**
	 * 联系地址
	 */
	private String address;
		
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
	 * 备注
	 */
	private String memo;
		
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
	public String getAmsd_store() {
		return amsd_store;
	}

	/**
	 * @return
	 */
	public String getDepart_id() {
		return depart_id;
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
	public String getUser_name() {
		return user_name;
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
	public String getPhone() {
		return phone;
	}
	/**
	 * @return
	 */
	public String getMobile() {
		return mobile;
	}
	/**
	 * @return
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @return
	 */
	public String getPostcode() {
		return postcode;
	}
	/**
	 * @return
	 */
	public String getAddress() {
		return address;
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
	public String getMemo() {
		return memo;
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
	public void setMemo(String str) {
		memo = str;
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
	public void setUser_name(String str) {
		user_name = str;
	}

	/**
	 * @param string
	 */
	public void setAmsd_store(String str) {
		amsd_store = str;
	}

	/**
	 * @param string
	 */
	public void setDepart_id(String str) {
		depart_id = str;
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
	public void setPhone(String str) {
		phone = str;
	}

	/**
	 * @param string
	 */
	public void setMobile(String str) {
		mobile = str;
	}

	/**
	 * @param string
	 */
	public void setEmail(String str) {
		email = str;
	}

	/**
	 * @param string
	 */
	public void setPostcode(String str) {
		postcode = str;
	}

	/**
	 * @param string
	 */
	public void setAddress(String str) {
		address = str;
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
