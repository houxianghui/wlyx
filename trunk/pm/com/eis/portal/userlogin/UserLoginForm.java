 
package com.eis.portal.userlogin; 
 
import com.eis.base.BaseForm; 
 
/** 
 * ˵���������û���¼��¼������ݶ��� 
 */ 
public class UserLoginForm extends BaseForm { 
 
	/** 
	 * ���캯�� 
	 */ 
	public UserLoginForm() { 
		super(); 
	} 
	private String user_id_f;
 
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
 
	/**
	 * @return
	 */
	public String getUser_id_f() {
		return user_id_f;
	}

	/**
	 * @param string
	 */
	public void setUser_id_f(String string) {
		user_id_f = string;
	}

} 

