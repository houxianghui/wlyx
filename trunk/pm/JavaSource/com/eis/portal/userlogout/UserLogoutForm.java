 
package com.eis.portal.userlogout;  
 
import com.eis.base.BaseForm; 
 
/** 
 * ˵�����û�ǩ�˵����ݶ��� 
 */ 
public class UserLogoutForm extends BaseForm { 
 
	/** 
	 * ���캯�� 
	 */ 
	public UserLogoutForm() { 
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
	public void  setUser_id(String str) {  
		user_id = str; 
	} 
 
	/** 
	 *  ���õ�¼ʱ�� 
	 */ 
	public void  setLogin_time(String str) {  
		login_time = str; 
	} 
 
} 

