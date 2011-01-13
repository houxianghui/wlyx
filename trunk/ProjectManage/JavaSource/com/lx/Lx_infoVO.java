 
package com.lx; 
 
import com.eis.base.BaseVO; 
 
/** 
 * 说明：联系信息的数据对象 
 */ 
public class Lx_infoVO extends BaseVO { 
 
	/** 
	 * 构造函数 
	 */ 
	public Lx_infoVO() { 
		super(); 
	} 
 
	/** 
	 * lx_id 
	 */ 
	private String lx_id; 
 
	/** 
	 * depart 
	 */ 
	private String depart; 
 
	/** 
	 * name 
	 */ 
	private String name; 
 
	/** 
	 * phone 
	 */ 
	private String phone; 
 
	/** 
	 * mobile 
	 */ 
	private String mobile; 
 
	/** 
	 * email 
	 */ 
	private String email; 
	private  String stuff_id;
 
	/** 
	 *  获得lx_id 
	 */ 
	public String getLx_id() {  
		return lx_id; 
	} 
 
	/** 
	 *  获得depart 
	 */ 
	public String getDepart() {  
		return depart; 
	} 
 
	/** 
	 *  获得name 
	 */ 
	public String getName() {  
		return name; 
	} 
 
	/** 
	 *  获得phone 
	 */ 
	public String getPhone() {  
		return phone; 
	} 
 
	/** 
	 *  获得mobile 
	 */ 
	public String getMobile() {  
		return mobile; 
	} 
 
	/** 
	 *  获得email 
	 */ 
	public String getEmail() {  
		return email; 
	} 
 
	/** 
	 *  设置lx_id 
	 */ 
	public void  setLx_id(String val) {  
		lx_id = val; 
	} 
 
	/** 
	 *  设置depart 
	 */ 
	public void  setDepart(String val) {  
		depart = val; 
	} 
 
	/** 
	 *  设置name 
	 */ 
	public void  setName(String val) {  
		name = val; 
	} 
 
	/** 
	 *  设置phone 
	 */ 
	public void  setPhone(String val) {  
		phone = val; 
	} 
 
	/** 
	 *  设置mobile 
	 */ 
	public void  setMobile(String val) {  
		mobile = val; 
	} 
 
	/** 
	 *  设置email 
	 */ 
	public void  setEmail(String val) {  
		email = val; 
	} 
 
	/**
	 * @return
	 */
	public String getStuff_id() {
		return stuff_id;
	}

	/**
	 * @param string
	 */
	public void setStuff_id(String string) {
		stuff_id = string;
	}

} 

