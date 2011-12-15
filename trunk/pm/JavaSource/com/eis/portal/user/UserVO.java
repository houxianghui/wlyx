/*********************************************************
 * File: UserVO.java
 * 
 * Version 1.0
 * 
 * Date     2005-9-27
 * 
 * Author   陈 蓉
 * 
 ********************************************************/ 
 
package com.eis.portal.user; 
 
import com.eis.base.BaseVO; 
 
/** 
 * 说明：用户列表的数据对象 
 */ 
public class UserVO extends BaseVO { 
 
	/** 
	 * 构造函数 
	 */ 
	public UserVO() { 
		super(); 
	} 
 
	/** 
	 * 用户编号 
	 */ 
	private String user_id; 
 
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
	 * 登录编号，用于检查登录编号有无重复 
	 */ 
	private String login_id2; 
 
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
	 * st_chg_dt 
	 */ 
	private String st_chg_dt; 
 
	/** 
	 * admin_id 
	 */ 
	private String admin_id; 
 
	/** 
	 * 网点号 
	 */ 
	private String amsd_store; 
	
	
	private String[] user_roles;
	private String amsd_name_line_1;
	
	
	/****************  get方法  *****************/
 
	/** 
	 *  获得用户编号 
	 */ 
	public String getUser_id() {  
		return user_id; 
	} 
 
	/** 
	 *  获得部门代码 
	 */ 
	public String getDepart_id() {  
		return depart_id; 
	} 
 
	/** 
	 *  获得主角色代码 
	 */ 
	public String getRole_id() {  
		return role_id; 
	} 
	
 
	/** 
	 *  获得登录编号 
	 */ 
	public String getLogin_id() {  
		return login_id; 
	} 
 
	/** 
	 *  获得用户姓名 
	 */ 
	public String getUser_name() {  
		return user_name; 
	} 
 
	/** 
	 *  获得密码 
	 */ 
	public String getPassword() {  
		return password; 
	} 
 
	/** 
	 *  获得联系电话 
	 */ 
	public String getPhone() {  
		return phone; 
	} 
 
	/** 
	 *  获得手机 
	 */ 
	public String getMobile() {  
		return mobile; 
	} 
 
	/** 
	 *  获得电子邮件 
	 */ 
	public String getEmail() {  
		return email; 
	} 
 
	/** 
	 *  获得邮编 
	 */ 
	public String getPostcode() {  
		return postcode; 
	} 
 
	/** 
	 *  获得联系地址 
	 */ 
	public String getAddress() {  
		return address; 
	} 
 
	/** 
	 *  获得状态 
	 */ 
	public String getStatus() {  
		return status; 
	} 
 
	/** 
	 *  获得建立日期 
	 */ 
	public String getReg_dt() {  
		return reg_dt; 
	} 
 
	/** 
	 *  获得起始日期 
	 */ 
	public String getBegin_dt() {  
		return begin_dt; 
	} 
 
	/** 
	 *  获得失效日期 
	 */ 
	public String getInvalid_dt() {  
		return invalid_dt; 
	} 
 
	/** 
	 *  获得密码失效日期 
	 */ 
	public String getModify_dt() {  
		return modify_dt; 
	} 
 
	/** 
	 *  获得备注 
	 */ 
	public String getMemo() {  
		return memo; 
	} 
 
	/** 
	 *  获得st_chg_dt 
	 */ 
	public String getSt_chg_dt() {  
		return st_chg_dt; 
	} 
 
	/** 
	 *  获得admin_id 
	 */ 
	public String getAdmin_id() {  
		return admin_id; 
	} 
 
	/** 
	 *  获得网点号 
	 */ 
	public String getAmsd_store() {  
		return amsd_store; 
	} 
	

	
	
	/**************  set 设置方法  ****************/
	
 
	/** 
	 *  设置用户编号 
	 */ 
	public void  setUser_id(String val) {  
		user_id = val; 
	} 
 
	/** 
	 *  设置部门代码 
	 */ 
	public void  setDepart_id(String val) {  
		depart_id = val; 
	} 
 
	/** 
	 *  设置主角色代码 
	 */ 
	public void  setRole_id(String val) {  
		role_id = val; 
	} 
	
 
	/** 
	 *  设置登录编号 
	 */ 
	public void  setLogin_id(String val) {  
		login_id = val; 
	} 
 
	/** 
	 *  设置用户姓名 
	 */ 
	public void  setUser_name(String val) {  
		user_name = val; 
	} 
 
	/** 
	 *  设置密码 
	 */ 
	public void  setPassword(String val) {  
		password = val; 
	} 
 
	/** 
	 *  设置联系电话 
	 */ 
	public void  setPhone(String val) {  
		phone = val; 
	} 
 
	/** 
	 *  设置手机 
	 */ 
	public void  setMobile(String val) {  
		mobile = val; 
	} 
 
	/** 
	 *  设置电子邮件 
	 */ 
	public void  setEmail(String val) {  
		email = val; 
	} 
 
	/** 
	 *  设置邮编 
	 */ 
	public void  setPostcode(String val) {  
		postcode = val; 
	} 
 
	/** 
	 *  设置联系地址 
	 */ 
	public void  setAddress(String val) {  
		address = val; 
	} 
 
	/** 
	 *  设置状态 
	 */ 
	public void  setStatus(String val) {  
		status = val; 
	} 
 
	/** 
	 *  设置建立日期 
	 */ 
	public void  setReg_dt(String val) {  
		reg_dt = val; 
	} 
 
	/** 
	 *  设置起始日期 
	 */ 
	public void  setBegin_dt(String val) {  
		begin_dt = val; 
	} 
 
	/** 
	 *  设置失效日期 
	 */ 
	public void  setInvalid_dt(String val) {  
		invalid_dt = val; 
	} 
 
	/** 
	 *  设置密码失效日期 
	 */ 
	public void  setModify_dt(String val) {  
		modify_dt = val; 
	} 
 
	/** 
	 *  设置备注 
	 */ 
	public void  setMemo(String val) {  
		memo = val; 
	} 
 
	/** 
	 *  设置st_chg_dt 
	 */ 
	public void  setSt_chg_dt(String val) {  
		st_chg_dt = val; 
	} 
 
	/** 
	 *  设置admin_id 
	 */ 
	public void  setAdmin_id(String val) {  
		admin_id = val; 
	} 
 
	/** 
	 *  设置网点号 
	 */ 
	public void  setAmsd_store(String val) {  
		amsd_store = val; 
	} 
 
	/**
	 * @return
	 */
	public String[] getUser_roles() {
		return user_roles;
	}

	/**
	 * @param strings
	 */
	public void setUser_roles(String[] strings) {
		user_roles = strings;
	}



	/**
	 * @return
	 */
	public String getLogin_id2() {
		return login_id2;
	}

	/**
	 * @param string
	 */
	public void setLogin_id2(String string) {
		login_id2 = string;
	}

    /**
     * Create on 2007-5-9 10:05:49 Administrator
     * 
     * 
     * @return
     */
    public String getAmsd_name_line_1() {
        return amsd_name_line_1;
    }

    /**
     * Create on 2007-5-9 10:05:49 Administrator
     * 
     * 
     * @param string
     */
    public void setAmsd_name_line_1(String string) {
        amsd_name_line_1 = string;
    }

} 

