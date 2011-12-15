 
package com.eis.portal.oplog; 
 
import java.util.*; 

import com.eis.base.BaseForm;
import com.eis.cache.*;

 
/** 
 * ˵����������־�����ݶ��� 
 */ 
public class OpLogForm extends BaseForm { 
 
	/** 
	 * ���캯�� 
	 */ 
	public OpLogForm() { 
		super(); 
	} 

 
	/** 
	 * ��ѯ������ 
	 */ 
 	private String user_id_f; 
	private String event_date_begin;
	private String event_date_end;
	private String branch_name;
	private String branch_id;
	private String login_id;
	
	/** 
	 * ϵͳ��� 
	 */ 
	private String sys_id; 
	
	
	/** 
	 * �û���� 
	 */ 
	private String user_id; 
	
	/** 
	 * �û��� 
	 */ 
	private String user_login_id; 
	
	/** 
	 * �������� 
	 */ 
	private String user_name; 
 
	/** 
	 * �¼�ʱ�� 
	 */ 
	private String event_time; 
 
	/** 
	 * �¼����� 
	 */ 
	private String event_type; 
 
	/** 
	 * ���� 
	 */ 
	private String org_id; 
 
	/** 
	 * ��ɫ 
	 */ 
	private String role_id; 
 
	/** 
	 * �������� 
	 */ 
	private String op_id; 
 
	/** 
	 * ��ע 
	 */ 
	private String memo; 
 
	/** 
	 * �ؼ��� 
	 */ 
	private String op_key; 
	
	private String event_date;
	private Collection event_type_options = null;	
	private Collection op_id_options = null;

 
	/** 
	 *  ����û� 
	 */ 
	public String getUser_id() {  
		return user_id; 
	} 
 
	/** 
	 *  ����¼�ʱ�� 
	 */ 
	public String getEvent_time() {  
		return event_time; 
	} 
 
	/** 
	 *  ����¼����� 
	 */ 
	public String getEvent_type() {  
		return event_type; 
	} 
 
	/** 
	 *  ��û��� 
	 */ 
	public String getOrg_id() {  
		return org_id; 
	} 
 
	/** 
	 *  ��ý�ɫ 
	 */ 
	public String getRole_id() {  
		return role_id; 
	} 
 
	/** 
	 *  ��ò������� 
	 */ 
	public String getOp_id() {  
		return op_id; 
	} 
 
	/** 
	 *  ��ñ�ע 
	 */ 
	public String getMemo() {  
		return memo; 
	} 
 
	/** 
	 *  ��ùؼ��� 
	 */ 
	public String getOp_key() {  
		return op_key; 
	} 
 
	/** 
	 *  �����û� 
	 */ 
	public void  setUser_id(String str) {  
		user_id = str; 
	} 
 
	/** 
	 *  �����¼�ʱ�� 
	 */ 
	public void  setEvent_time(String str) {  
		event_time = str; 
	} 
 
	/** 
	 *  �����¼����� 
	 */ 
	public void  setEvent_type(String str) {  
		event_type = str; 
	} 
 
	/** 
	 *  ���û��� 
	 */ 
	public void  setOrg_id(String str) {  
		org_id = str; 
	} 
 
	/** 
	 *  ���ý�ɫ 
	 */ 
	public void  setRole_id(String str) {  
		role_id = str; 
	} 
 
	/** 
	 *  ���ò������� 
	 */ 
	public void  setOp_id(String str) {  
		op_id = str; 
	} 
 
	/** 
	 *  ���ñ�ע 
	 */ 
	public void  setMemo(String str) {  
		memo = str; 
	} 
 
	/** 
	 *  ���ùؼ��� 
	 */ 
	public void  setOp_key(String str) {  
		op_key = str; 
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

	/**
	 * @return
	 */
	public Collection getEvent_type_options() {
		return SingleDicMap.getOptionCollection("0006");
	}

	/**
	 * @return
	 */
	public Collection getOp_id_options() {
		return SingleDicMap.getOptionCollection("0005");
	}

	/**
	 * @param collection
	 */
	public void setEvent_type_options(Collection collection) {
		event_type_options = collection;
	}

	/**
	 * @param collection
	 */
	public void setOp_id_options(Collection collection) {
		op_id_options = collection;
	}

	/**
	 * @return
	 */
	public String getEvent_date() {
		return event_date;
	}

	/**
	 * @param string
	 */
	public void setEvent_date(String string) {
		event_date = string;
	}

/**
 * @return
 */
public String getBranch_id() {
	return branch_id;
}

/**
 * @return
 */
public String getBranch_name() {
	return branch_name;
}

/**
 * @return
 */
public String getEvent_date_begin() {
	return event_date_begin;
}

/**
 * @return
 */
public String getEvent_date_end() {
	return event_date_end;
}

/**
 * @return
 */
public String getLogin_id() {
	return login_id;
}

/**
 * @param string
 */
public void setBranch_id(String string) {
	branch_id = string;
}

/**
 * @param string
 */
public void setBranch_name(String string) {
	branch_name = string;
}

/**
 * @param string
 */
public void setEvent_date_begin(String string) {
	event_date_begin = string;
}

/**
 * @param string
 */
public void setEvent_date_end(String string) {
	event_date_end = string;
}

/**
 * @param string
 */
public void setLogin_id(String string) {
	login_id = string;
}

	/**
	 * @return
	 */
	public String getSys_id() {
		return sys_id;
	}

	/**
	 * @param string
	 */
	public void setSys_id(String string) {
		sys_id = string;
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

