 
package com.eis.portal.userlogout;  
 
import com.eis.base.BaseForm; 
 
/** 
 * 说明：用户签退的数据对象 
 */ 
public class UserLogoutForm extends BaseForm { 
 
	/** 
	 * 构造函数 
	 */ 
	public UserLogoutForm() { 
		super(); 
	} 
 
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
 
} 

