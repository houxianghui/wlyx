 
package com.eis.portal.userlogout; 
 
import com.eis.base.BaseVO; 
 
/** 
 * ˵�����û�ǩ�˵����ݶ��� 
 */ 
public class UserLogoutVO extends BaseVO { 
 
	/** 
	 * ���캯�� 
	 */ 
	public UserLogoutVO() { 
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
 
} 

