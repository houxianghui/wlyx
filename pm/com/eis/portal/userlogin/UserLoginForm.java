 
package com.eis.portal.userlogin; 
 
import com.eis.base.BaseForm; 
 
/** 
 * 说明：管理用户登录记录表的数据对象 
 */ 
public class UserLoginForm extends BaseForm { 
 
	/** 
	 * 构造函数 
	 */ 
	public UserLoginForm() { 
		super(); 
	} 
	private String user_id_f;
 
	/** 
	 * 用户编号 
	 */ 
	private String user_id; 
 
	/** 
	 * 登录时间 
	 */ 
	private String login_time; 
 
	/** 
	 *  获得用户编号 
	 */ 
	public String getUser_id() {  
		return user_id; 
	} 
 
	/** 
	 *  获得登录时间 
	 */ 
	public String getLogin_time() {  
		return login_time; 
	} 
 
	/** 
	 *  设置用户编号 
	 */ 
	public void  setUser_id(String str) {  
		user_id = str; 
	} 
 
	/** 
	 *  设置登录时间 
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

