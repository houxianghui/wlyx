/*********************************************************
 * File: UserVO.java
 * 
 * Version 1.0
 * 
 * Date     2005-9-27
 * 
 * Author   �� ��
 * 
 ********************************************************/ 
 
package com.eis.portal.user; 
 
import com.eis.base.BaseVO; 
 
/** 
 * ˵�����û��б�����ݶ��� 
 */ 
public class UserVO extends BaseVO { 
 
	/** 
	 * ���캯�� 
	 */ 
	public UserVO() { 
		super(); 
	} 
 
	/** 
	 * �û���� 
	 */ 
	private String user_id; 
 
	/** 
	 * ���Ŵ��� 
	 */ 
	private String depart_id; 
 
	/** 
	 * ����ɫ���� 
	 */ 
	private String role_id; 
	
 
	/** 
	 * ��¼��� 
	 */ 
	private String login_id; 
	
	/** 
	 * ��¼��ţ����ڼ���¼��������ظ� 
	 */ 
	private String login_id2; 
 
	/** 
	 * �û����� 
	 */ 
	private String user_name; 
 
	/** 
	 * ���� 
	 */ 
	private String password; 
 
	/** 
	 * ��ϵ�绰 
	 */ 
	private String phone; 
 
	/** 
	 * �ֻ� 
	 */ 
	private String mobile; 
 
	/** 
	 * �����ʼ� 
	 */ 
	private String email; 
 
	/** 
	 * �ʱ� 
	 */ 
	private String postcode; 
 
	/** 
	 * ��ϵ��ַ 
	 */ 
	private String address; 
 
	/** 
	 * ״̬ 
	 */ 
	private String status; 
 
	/** 
	 * �������� 
	 */ 
	private String reg_dt; 
 
	/** 
	 * ��ʼ���� 
	 */ 
	private String begin_dt; 
 
	/** 
	 * ʧЧ���� 
	 */ 
	private String invalid_dt; 
 
	/** 
	 * ����ʧЧ���� 
	 */ 
	private String modify_dt; 
 
	/** 
	 * ��ע 
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
	 * ����� 
	 */ 
	private String amsd_store; 
	
	
	private String[] user_roles;
	private String amsd_name_line_1;
	
	
	/****************  get����  *****************/
 
	/** 
	 *  ����û���� 
	 */ 
	public String getUser_id() {  
		return user_id; 
	} 
 
	/** 
	 *  ��ò��Ŵ��� 
	 */ 
	public String getDepart_id() {  
		return depart_id; 
	} 
 
	/** 
	 *  �������ɫ���� 
	 */ 
	public String getRole_id() {  
		return role_id; 
	} 
	
 
	/** 
	 *  ��õ�¼��� 
	 */ 
	public String getLogin_id() {  
		return login_id; 
	} 
 
	/** 
	 *  ����û����� 
	 */ 
	public String getUser_name() {  
		return user_name; 
	} 
 
	/** 
	 *  ������� 
	 */ 
	public String getPassword() {  
		return password; 
	} 
 
	/** 
	 *  �����ϵ�绰 
	 */ 
	public String getPhone() {  
		return phone; 
	} 
 
	/** 
	 *  ����ֻ� 
	 */ 
	public String getMobile() {  
		return mobile; 
	} 
 
	/** 
	 *  ��õ����ʼ� 
	 */ 
	public String getEmail() {  
		return email; 
	} 
 
	/** 
	 *  ����ʱ� 
	 */ 
	public String getPostcode() {  
		return postcode; 
	} 
 
	/** 
	 *  �����ϵ��ַ 
	 */ 
	public String getAddress() {  
		return address; 
	} 
 
	/** 
	 *  ���״̬ 
	 */ 
	public String getStatus() {  
		return status; 
	} 
 
	/** 
	 *  ��ý������� 
	 */ 
	public String getReg_dt() {  
		return reg_dt; 
	} 
 
	/** 
	 *  �����ʼ���� 
	 */ 
	public String getBegin_dt() {  
		return begin_dt; 
	} 
 
	/** 
	 *  ���ʧЧ���� 
	 */ 
	public String getInvalid_dt() {  
		return invalid_dt; 
	} 
 
	/** 
	 *  �������ʧЧ���� 
	 */ 
	public String getModify_dt() {  
		return modify_dt; 
	} 
 
	/** 
	 *  ��ñ�ע 
	 */ 
	public String getMemo() {  
		return memo; 
	} 
 
	/** 
	 *  ���st_chg_dt 
	 */ 
	public String getSt_chg_dt() {  
		return st_chg_dt; 
	} 
 
	/** 
	 *  ���admin_id 
	 */ 
	public String getAdmin_id() {  
		return admin_id; 
	} 
 
	/** 
	 *  �������� 
	 */ 
	public String getAmsd_store() {  
		return amsd_store; 
	} 
	

	
	
	/**************  set ���÷���  ****************/
	
 
	/** 
	 *  �����û���� 
	 */ 
	public void  setUser_id(String val) {  
		user_id = val; 
	} 
 
	/** 
	 *  ���ò��Ŵ��� 
	 */ 
	public void  setDepart_id(String val) {  
		depart_id = val; 
	} 
 
	/** 
	 *  ��������ɫ���� 
	 */ 
	public void  setRole_id(String val) {  
		role_id = val; 
	} 
	
 
	/** 
	 *  ���õ�¼��� 
	 */ 
	public void  setLogin_id(String val) {  
		login_id = val; 
	} 
 
	/** 
	 *  �����û����� 
	 */ 
	public void  setUser_name(String val) {  
		user_name = val; 
	} 
 
	/** 
	 *  �������� 
	 */ 
	public void  setPassword(String val) {  
		password = val; 
	} 
 
	/** 
	 *  ������ϵ�绰 
	 */ 
	public void  setPhone(String val) {  
		phone = val; 
	} 
 
	/** 
	 *  �����ֻ� 
	 */ 
	public void  setMobile(String val) {  
		mobile = val; 
	} 
 
	/** 
	 *  ���õ����ʼ� 
	 */ 
	public void  setEmail(String val) {  
		email = val; 
	} 
 
	/** 
	 *  �����ʱ� 
	 */ 
	public void  setPostcode(String val) {  
		postcode = val; 
	} 
 
	/** 
	 *  ������ϵ��ַ 
	 */ 
	public void  setAddress(String val) {  
		address = val; 
	} 
 
	/** 
	 *  ����״̬ 
	 */ 
	public void  setStatus(String val) {  
		status = val; 
	} 
 
	/** 
	 *  ���ý������� 
	 */ 
	public void  setReg_dt(String val) {  
		reg_dt = val; 
	} 
 
	/** 
	 *  ������ʼ���� 
	 */ 
	public void  setBegin_dt(String val) {  
		begin_dt = val; 
	} 
 
	/** 
	 *  ����ʧЧ���� 
	 */ 
	public void  setInvalid_dt(String val) {  
		invalid_dt = val; 
	} 
 
	/** 
	 *  ��������ʧЧ���� 
	 */ 
	public void  setModify_dt(String val) {  
		modify_dt = val; 
	} 
 
	/** 
	 *  ���ñ�ע 
	 */ 
	public void  setMemo(String val) {  
		memo = val; 
	} 
 
	/** 
	 *  ����st_chg_dt 
	 */ 
	public void  setSt_chg_dt(String val) {  
		st_chg_dt = val; 
	} 
 
	/** 
	 *  ����admin_id 
	 */ 
	public void  setAdmin_id(String val) {  
		admin_id = val; 
	} 
 
	/** 
	 *  ��������� 
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

