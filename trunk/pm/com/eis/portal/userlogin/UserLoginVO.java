 
package com.eis.portal.userlogin; 
 
import com.eis.base.BaseVO; 
 
/** 
 * 说明：管理用户登录记录表的数据对象 
 */ 
public class UserLoginVO extends BaseVO { 
 
	/** 
	 * 构造函数 
	 */ 
	public UserLoginVO() { 
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
	 * 客户端IP 
	 */ 
	private String client_ip; 
 
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

