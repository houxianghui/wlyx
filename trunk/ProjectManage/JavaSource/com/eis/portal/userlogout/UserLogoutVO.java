 
package com.eis.portal.userlogout; 
 
import com.eis.base.BaseVO; 
 
/** 
 * 说明：用户签退的数据对象 
 */ 
public class UserLogoutVO extends BaseVO { 
 
	/** 
	 * 构造函数 
	 */ 
	public UserLogoutVO() { 
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
	public void  setUser_id(String val) {  
		user_id = val; 
	} 
 
	/** 
	 *  设置登录时间 
	 */ 
	public void  setLogin_time(String val) {  
		login_time = val; 
	} 
 
} 

