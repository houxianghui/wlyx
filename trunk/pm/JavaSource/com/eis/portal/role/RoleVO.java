/*********************************************************
 * File: RoleVO.java
 * 
 * Version 1.0
 * 
 * Date     2005-9-12
 * 
 * Author   Chen Rong
 * 
 ********************************************************/

package com.eis.portal.role;

import com.eis.base.BaseVO;

/**
 * ˵������ɫ��������ݶ���
 * 
 */
public class RoleVO extends BaseVO {

	/**
	 * ���캯��
	 */
	public RoleVO() {
		super();
		
	}

	private String role_id;	//��ɫ����
	
	private String role_name;	//��ɫ����
	
	private String logic_id;	//���ƴ���
	
	private String amsd_store;	//�����
	
	private int templ_id;	//��ҳģ��
	
	private int sesn_time;	//�Ự��ʱ����
	
	private String role_level; //��ɫ����	
	
	private String status;	//״̬	
	
	private String reg_dt;	//��������
	
	private String user_id;	//������Ա
	


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
	public String getRole_name() {
		return role_name;
	}
	
	/**
	* @return
	*/
	public String getLogic_id() {
		return logic_id;
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
	public int getTempl_id() {
		return templ_id;
	}

	/**
	 * @return
	 */
	public int getSesn_time() {
		return sesn_time;
	}
	
	/**
	 * @return
	 */
	public String getRole_level() {
		return role_level;
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
	public String getUser_id() {
		return user_id;
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
	public void setRole_name(String str) {
		role_name = str;
	}
	
	/**
	 * @param string
	 */
	public void setLogic_id(String str) {
		logic_id = str;
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
	public void setTempl_id(int i) {
		templ_id = i;
	}

	/**
	 * @param string
	 */
	public void setSesn_time(int i) {
		sesn_time = i;
	}
	
	/**
	 * @param string
	 */
	public void setRole_level(String str) {
		role_level = str;
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
	public void setReg_dt(String str) {
		reg_dt = str;
	}
	
	/**
	 * @param string
	 */
	public void setUser_id(String str) {
		user_id = str;
	}


}
