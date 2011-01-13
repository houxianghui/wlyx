 
package com.eis.portal.oplog; 
 
import com.eis.base.BaseVO; 
 
/** 
 * 说明：操作日志的数据对象 
 */ 
public class OpLogVO extends BaseVO { 
 
	/** 
	 * 构造函数 
	 */ 
	public OpLogVO() { 
		super(); 
	} 
	
	
	/** 
	 * 系统编号 
	 */ 
	private long sys_id; 
 
	
	/** 
	 * 用户编号 
	 */ 
	private String user_id; 
	
	/** 
	 * 用户名 
	 */ 
	private String user_login_id;
	
	/** 
	 * 中文姓名 
	 */ 
	private String user_name;
 
	/** 
	 * 事件时间 
	 */ 
	private String event_time; 
 
	/** 
	 * 事件类型 
	 */ 
	private String event_type; 
 
	/** 
	 * 机构 
	 */ 
	private String org_id; 
 
	/** 
	 * 角色 
	 */ 
	private String role_id; 
 
	/** 
	 * 操作类型 
	 */ 
	private String op_id; 
 
	/** 
	 * 备注 
	 */ 
	private String memo; 
 
	/** 
	 * 关键字 
	 */ 
	private String op_key; 
 
	/** 
	 *  获得用户 
	 */ 
	public String getUser_id() {  
		return user_id; 
	} 
 
	/** 
	 *  获得事件时间 
	 */ 
	public String getEvent_time() {  
		return event_time; 
	} 
 
	/** 
	 *  获得事件类型 
	 */ 
	public String getEvent_type() {  
		return event_type; 
	} 
 
	/** 
	 *  获得机构 
	 */ 
	public String getOrg_id() {  
		return org_id; 
	} 
 
	/** 
	 *  获得角色 
	 */ 
	public String getRole_id() {  
		return role_id; 
	} 
 
	/** 
	 *  获得操作类型 
	 */ 
	public String getOp_id() {  
		return op_id; 
	} 
 
	/** 
	 *  获得备注 
	 */ 
	public String getMemo() {  
		return memo; 
	} 
 
	/** 
	 *  获得关键字 
	 */ 
	public String getOp_key() {  
		return op_key; 
	} 
 
	/** 
	 *  设置用户 
	 */ 
	public void  setUser_id(String val) {  
		user_id = val; 
	} 
 
	/** 
	 *  设置事件时间 
	 */ 
	public void  setEvent_time(String val) {  
		event_time = val; 
	} 
 
	/** 
	 *  设置事件类型 
	 */ 
	public void  setEvent_type(String val) {  
		event_type = val; 
	} 
 
	/** 
	 *  设置机构 
	 */ 
	public void  setOrg_id(String val) {  
		org_id = val; 
	} 
 
	/** 
	 *  设置角色 
	 */ 
	public void  setRole_id(String val) {  
		role_id = val; 
	} 
 
	/** 
	 *  设置操作类型 
	 */ 
	public void  setOp_id(String val) {  
		op_id = val; 
	} 
 
	/** 
	 *  设置备注 
	 */ 
	public void  setMemo(String val) {  
		memo = val; 
	} 
 
	/** 
	 *  设置关键字 
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

