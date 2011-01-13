 
package com.eis.portal.userlogin; 
 
import com.eis.base.BaseVO; 
 
/** 
 * ˵���������û���¼��¼������ݶ��� 
 */ 
public class UserLoginVO extends BaseVO { 
 
	/** 
	 * ���캯�� 
	 */ 
	public UserLoginVO() { 
		super(); 
	} 
 
	/** 
	 * �û���� 
	 */ 
	private String user_id; 
 
	/** 
	 * ��¼ʱ�� 
	 */ 
	private String login_time; 
	
	/** 
	 * �ͻ���IP 
	 */ 
	private String client_ip; 
 
	/** 
	 *  ����û���� 
	 */ 
	public String getUser_id() {  
		return user_id; 
	} 
 
	/** 
	 *  ��õ�¼ʱ�� 
	 */ 
	public String getLogin_time() {  
		return login_time; 
	} 
 
	/** 
	 *  �����û���� 
	 */ 
	public void  setUser_id(String val) {  
		user_id = val; 
	} 
 
	/** 
	 *  ���õ�¼ʱ�� 
	 */ 
	public void  setLogin_time(String val) {  
		login_time = val; 
	} 
 
	/**
	 * @return
	 */
	public String getClient_ip() {
		return client_ip;
	}

	/**
	 * @param string
	 */
	public void setClient_ip(String string) {
		client_ip = string;
	}

} 

