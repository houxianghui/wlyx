 
package com.lx; 
 
import java.util.*; 
 
import com.eis.base.BaseForm; 
import com.eis.cache.*; 
 
/** 
 * ˵������ϵ��Ϣ�����ݶ��� 
 */ 
public class Lx_infoForm extends BaseForm { 
 
	/** 
	 * ���캯�� 
	 */ 
	public Lx_infoForm() { 
		super(); 
	} 
 
	/** 
	 * lx_id 
	 */ 
	private String depart_f;
	private String lx_id; 
	private String lx_id_f;
	private String name_f;
	private Collection depart_f_options;
	private String stuff_id;
 
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
	public void  setLx_id(String str) {  
		lx_id = str; 
	} 
 
	/** 
	 *  ����depart 
	 */ 
	public void  setDepart(String str) {  
		depart = str; 
	} 
 
	/** 
	 *  ����name 
	 */ 
	public void  setName(String str) {  
		name = str; 
	} 
 
	/** 
	 *  ����phone 
	 */ 
	public void  setPhone(String str) {  
		phone = str; 
	} 
 
	/** 
	 *  ����mobile 
	 */ 
	public void  setMobile(String str) {  
		mobile = str; 
	} 
 
	/** 
	 *  ����email 
	 */ 
	public void  setEmail(String str) {  
		email = str; 
	} 
 
	/**
	 * @return
	 */
	public String getName_f() {
		return name_f;
	}

	/**
	 * @param string
	 */
	public void setName_f(String string) {
		name_f = string;
	}

	/**
	 * @return
	 */
	public String getLx_id_f() {
		return lx_id_f;
	}

	/**
	 * @param string
	 */
	public void setLx_id_f(String string) {
		lx_id_f = string;
	}

	/**
	 * @return
	 */
	public Collection getDepart_f_options() {
		return  SingleDicMap.getOptionCollection("9990");
	}

	/**
	 * @param collection
	 */
	public void setDepart_f_options(Collection collection) {
		depart_f_options = collection;
	}

	/**
	 * @return
	 */
	public String getDepart_f() {
		return depart_f;
	}

	/**
	 * @param string
	 */
	public void setDepart_f(String string) {
		depart_f = string;
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

