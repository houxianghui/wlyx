 
package com.lx; 
 
import com.eis.base.BaseVO; 
 
/** 
 * ˵������ϵ��Ϣ�����ݶ��� 
 */ 
public class Lx_infoVO extends BaseVO { 
 
	/** 
	 * ���캯�� 
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
	 *  ���lx_id 
	 */ 
	public String getLx_id() {  
		return lx_id; 
	} 
 
	/** 
	 *  ���depart 
	 */ 
	public String getDepart() {  
		return depart; 
	} 
 
	/** 
	 *  ���name 
	 */ 
	public String getName() {  
		return name; 
	} 
 
	/** 
	 *  ���phone 
	 */ 
	public String getPhone() {  
		return phone; 
	} 
 
	/** 
	 *  ���mobile 
	 */ 
	public String getMobile() {  
		return mobile; 
	} 
 
	/** 
	 *  ���email 
	 */ 
	public String getEmail() {  
		return email; 
	} 
 
	/** 
	 *  ����lx_id 
	 */ 
	public void  setLx_id(String val) {  
		lx_id = val; 
	} 
 
	/** 
	 *  ����depart 
	 */ 
	public void  setDepart(String val) {  
		depart = val; 
	} 
 
	/** 
	 *  ����name 
	 */ 
	public void  setName(String val) {  
		name = val; 
	} 
 
	/** 
	 *  ����phone 
	 */ 
	public void  setPhone(String val) {  
		phone = val; 
	} 
 
	/** 
	 *  ����mobile 
	 */ 
	public void  setMobile(String val) {  
		mobile = val; 
	} 
 
	/** 
	 *  ����email 
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

