 
package com.eis.portal.oplog; 
 
import com.eis.base.BaseVO; 
 
/** 
 * ˵����������־�����ݶ��� 
 */ 
public class OpLogVO extends BaseVO { 
 
	/** 
	 * ���캯�� 
	 */ 
	public OpLogVO() { 
		super(); 
	} 
	
	
	/** 
	 * ϵͳ��� 
	 */ 
	private long sys_id; 
 
	
	/** 
	 * �û���� 
	 */ 
	private String user_id; 
	
	/** 
	 * �û��� 
	 */ 
	private String user_login_id;
	
	/** 
	 * �������� 
	 */ 
	private String user_name;
 
	/** 
	 * �¼�ʱ�� 
	 */ 
	private String event_time; 
 
	/** 
	 * �¼����� 
	 */ 
	private String event_type; 
 
	/** 
	 * ���� 
	 */ 
	private String org_id; 
 
	/** 
	 * ��ɫ 
	 */ 
	private String role_id; 
 
	/** 
	 * �������� 
	 */ 
	private String op_id; 
 
	/** 
	 * ��ע 
	 */ 
	private String memo; 
 
	/** 
	 * �ؼ��� 
	 */ 
	private String op_key; 
 
	/** 
	 *  ����û� 
	 */ 
	public String getUser_id() {  
		return user_id; 
	} 
 
	/** 
	 *  ����¼�ʱ�� 
	 */ 
	public String getEvent_time() {  
		return event_time; 
	} 
 
	/** 
	 *  ����¼����� 
	 */ 
	public String getEvent_type() {  
		return event_type; 
	} 
 
	/** 
	 *  ��û��� 
	 */ 
	public String getOrg_id() {  
		return org_id; 
	} 
 
	/** 
	 *  ��ý�ɫ 
	 */ 
	public String getRole_id() {  
		return role_id; 
	} 
 
	/** 
	 *  ��ò������� 
	 */ 
	public String getOp_id() {  
		return op_id; 
	} 
 
	/** 
	 *  ��ñ�ע 
	 */ 
	public String getMemo() {  
		return memo; 
	} 
 
	/** 
	 *  ��ùؼ��� 
	 */ 
	public String getOp_key() {  
		return op_key; 
	} 
 
	/** 
	 *  �����û� 
	 */ 
	public void  setUser_id(String val) {  
		user_id = val; 
	} 
 
	/** 
	 *  �����¼�ʱ�� 
	 */ 
	public void  setEvent_time(String val) {  
		event_time = val; 
	} 
 
	/** 
	 *  �����¼����� 
	 */ 
	public void  setEvent_type(String val) {  
		event_type = val; 
	} 
 
	/** 
	 *  ���û��� 
	 */ 
	public void  setOrg_id(String val) {  
		org_id = val; 
	} 
 
	/** 
	 *  ���ý�ɫ 
	 */ 
	public void  setRole_id(String val) {  
		role_id = val; 
	} 
 
	/** 
	 *  ���ò������� 
	 */ 
	public void  setOp_id(String val) {  
		op_id = val; 
	} 
 
	/** 
	 *  ���ñ�ע 
	 */ 
	public void  setMemo(String val) {  
		memo = val; 
	} 
 
	/** 
	 *  ���ùؼ��� 
	 */ 
	public void  setOp_key(String val) {  
		op_key = val; 
	} 
 
	/**
	 * @return
	 */
	public long getSys_id() {
		return sys_id;
	}

	/**
	 * @param l
	 */
	public void setSys_id(long l) {
		sys_id = l;
	}

	/**
	 * @return
	 */
	public String getUser_name() {
		return user_name;
	}

	/**
	 * @param string
	 */
	public void setUser_name(String string) {
		user_name = string;
	}

	/**
	 * @return
	 */
	public String getUser_login_id() {
		return user_login_id;
	}

	/**
	 * @param string
	 */
	public void setUser_login_id(String string) {
		user_login_id = string;
	}

} 

