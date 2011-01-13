/*********************************************************
 * File: UserForm.java
 * 
 * Version 1.0
 * 
 * Date     2005-9-27
 * 
 * Author   �� ��
 * 
 ********************************************************/ 
 
package com.eis.portal.user; 
 
import java.util.*;

import com.eis.base.BaseForm; 
import com.eis.cache.*;
 
/** 
 * ˵�����û��б�����ݶ��� 
 */ 
public class UserForm extends BaseForm { 
 
	/** 
	 * ���캯�� 
	 */ 
	public UserForm() { 
		super(); 
	}
			 	 

 
	/** 
	 * �û���� 
	 */ 
	private String user_id; 
 
	/** 
	 * ���Ŵ��� 
	 */ 
	private String depart_id; 
 
	/** 
	 * ����ɫ���� 
	 */ 
	private String role_id; 	
	

	/** 
	 * ��¼��� 
	 */ 
	private String login_id; 
	
	/** 
	 * ��¼���2 ,��ȡ�ã����ڼ���¼����ظ�
	 */ 
	private String login_id2; 


 
	/** 
	 * �û����� 
	 */ 
	private String user_name; 
 
	/** 
	 * ���� 
	 */ 
	private String password; 
 
	/** 
	 * ��ϵ�绰 
	 */ 
	private String phone; 
 
	/** 
	 * �ֻ� 
	 */ 
	private String mobile; 
 
	/** 
	 * �����ʼ� 
	 */ 
	private String email; 
	
	private String[] user_roles;
 
	/** 
	 * �ʱ� 
	 */ 
	private String postcode; 
 
	/** 
	 * ��ϵ��ַ 
	 */ 
	private String address; 
 
	/** 
	 * ״̬ 
	 */ 
	private String status; 
 
	/** 
	 * �������� 
	 */ 
	private String reg_dt; 
 
	/** 
	 * ��ʼ���� 
	 */ 
	private String begin_dt; 
 
	/** 
	 * ʧЧ���� 
	 */ 
	private String invalid_dt; 
 
	/** 
	 * ����ʧЧ���� 
	 */ 
	private String modify_dt; 
 
	/** 
	 * ��ע 
	 */ 
	private String memo; 
 
	/** 
	 * �������� 
	 */ 
	private String st_chg_dt; 
 
	/** 
	 * ���¹���Ա���� 
	 */ 
	private String admin_id; 
 
	/** 
	 * ����� 
	 */ 
	private String amsd_store;	
	
	
	/** 
	 * �������� 
	 */ 
	private String org_name;	
	
	
	/** 
	 * �û�����ѯ���� 
	 */ 
	private String user_name_f;	
 
	/** 
	 * ����Ų�ѯ���� 
	 */ 
	private String amsd_store_f;
	
	/** 
	 * �û�״̬��ѯ���� 
	 */ 
	private String status_f;
	
	
	private Collection status_options = null;
	
	
	private String id_field;
	private String user_id_f;
    private String amsd_store_line;
    private Collection amsd_store_f_options;
    private String seq_no_f;
    private String name_field;
	private String cre_branch_name_f;
	private String cre_branch_f;
	private String amsd_store_name;
	
	private String currAmsdStore;
	
	
	
	/********   get����   ***********/
	
	
	/** 
	 *  ����û���� 
	 */ 
	public String getUser_id() {  
		return user_id; 
	} 
 
	/** 
	 *  ��ò��Ŵ��� 
	 */ 
	public String getDepart_id() {  
		return depart_id; 
	} 
 
	/** 
	 *  �������ɫ���� 
	 */ 
	public String getRole_id() {  
		return role_id; 
	}
	
 
	/** 
	 *  ��õ�¼��� 
	 */ 
	public String getLogin_id() {  
		return login_id; 
	} 
 
	/** 
	 *  ����û����� 
	 */ 
	public String getUser_name() {  
		return user_name; 
	} 
 
	/** 
	 *  ������� 
	 */ 
	public String getPassword() {  
		return password; 
	} 
 
	/** 
	 *  �����ϵ�绰 
	 */ 
	public String getPhone() {  
		return phone; 
	} 
 
	/** 
	 *  ����ֻ� 
	 */ 
	public String getMobile() {  
		return mobile; 
	} 
 
	/** 
	 *  ��õ����ʼ� 
	 */ 
	public String getEmail() {  
		return email; 
	} 
 
	/** 
	 *  ����ʱ� 
	 */ 
	public String getPostcode() {  
		return postcode; 
	} 
 
	/** 
	 *  �����ϵ��ַ 
	 */ 
	public String getAddress() {  
		return address; 
	} 
 
	/** 
	 *  ���״̬ 
	 */ 
	public String getStatus() {  
		return status; 
	} 
 
	/** 
	 *  ��ý������� 
	 */ 
	public String getReg_dt() {  
		return reg_dt; 
	} 
 
	/** 
	 *  �����ʼ���� 
	 */ 
	public String getBegin_dt() {  
		return begin_dt; 
	} 
 
	/** 
	 *  ���ʧЧ���� 
	 */ 
	public String getInvalid_dt() {  
		return invalid_dt; 
	} 
 
	/** 
	 *  �������ʧЧ���� 
	 */ 
	public String getModify_dt() {  
		return modify_dt; 
	} 
 
	/** 
	 *  ��ñ�ע 
	 */ 
	public String getMemo() {  
		return memo; 
	} 
 
	/** 
	 *  ��ø������� 
	 */ 
	public String getSt_chg_dt() {  
		return st_chg_dt; 
	} 
 
	/** 
	 *  ��ø��¹���Ա���� 
	 */ 
	public String getAdmin_id() {  
		return admin_id; 
	} 
 
	/** 
	 *  �������� 
	 */ 
	public String getAmsd_store() {  
		return amsd_store; 
	} 
	

	
	/********   set����   ***********/
	

	 
	/** 
	 *  �����û���� 
	 */ 
	public void  setUser_id(String str) {  
		user_id = str; 
	} 
 
	/** 
	 *  ���ò��Ŵ��� 
	 */ 
	public void  setDepart_id(String str) {  
		depart_id = str; 
	} 
 
	/** 
	 *  ��������ɫ���� 
	 */ 
	public void  setRole_id(String str) {  
		role_id = str; 
	} 
	
 
	/** 
	 *  ���õ�¼��� 
	 */ 
	public void  setLogin_id(String str) {  
		login_id = str; 
	} 
 
	/** 
	 *  �����û����� 
	 */ 
	public void  setUser_name(String str) {  
		user_name = str; 
	} 
 
	/** 
	 *  �������� 
	 */ 
	public void  setPassword(String str) {  
		password = str; 
	} 
 
	/** 
	 *  ������ϵ�绰 
	 */ 
	public void  setPhone(String str) {  
		phone = str; 
	} 
 
	/** 
	 *  �����ֻ� 
	 */ 
	public void  setMobile(String str) {  
		mobile = str; 
	} 
 
	/** 
	 *  ���õ����ʼ� 
	 */ 
	public void  setEmail(String str) {  
		email = str; 
	} 
 
	/** 
	 *  �����ʱ� 
	 */ 
	public void  setPostcode(String str) {  
		postcode = str; 
	} 
 
	/** 
	 *  ������ϵ��ַ 
	 */ 
	public void  setAddress(String str) {  
		address = str; 
	} 
 
	/** 
	 *  ����״̬ 
	 */ 
	public void  setStatus(String str) {  
		status = str; 
	} 
 
	/** 
	 *  ���ý������� 
	 */ 
	public void  setReg_dt(String str) {  
		reg_dt = str; 
	} 
 
	/** 
	 *  ������ʼ���� 
	 */ 
	public void  setBegin_dt(String str) {  
		begin_dt = str; 
	} 
 
	/** 
	 *  ����ʧЧ���� 
	 */ 
	public void  setInvalid_dt(String str) {  
		invalid_dt = str; 
	} 
 
	/** 
	 *  ��������ʧЧ���� 
	 */ 
	public void  setModify_dt(String str) {  
		modify_dt = str; 
	} 
 
	/** 
	 *  ���ñ�ע 
	 */ 
	public void  setMemo(String str) {  
		memo = str; 
	} 
 
	/** 
	 *  ���ø������� 
	 */ 
	public void  setSt_chg_dt(String str) {  
		st_chg_dt = str; 
	} 
 
	/** 
	 *  ���ø��¹���Ա���� 
	 */ 
	public void  setAdmin_id(String str) {  
		admin_id = str; 
	} 
 
	/** 
	 *  ��������� 
	 */ 
	public void  setAmsd_store(String str) {  
		amsd_store = str; 
	}



	/**
	 * @return
	 */
	public String getAmsd_store_f() {
		return amsd_store_f;
	}

	/**
	 * @return
	 */
	public String getStatus_f() {
		return status_f;
	}

	/**
	 * @return
	 */
	public String getUser_name_f() {
		return user_name_f;
	}

	/**
	 * @param string
	 */
	public void setAmsd_store_f(String string) {
		amsd_store_f = string;
	}

	/**
	 * @param string
	 */
	public void setStatus_f(String string) {
		status_f = string;
	}

	/**
	 * @param string
	 */
	public void setUser_name_f(String string) {
		user_name_f = string;
	}

	/**
	 * @return
	 */
	public Collection getStatus_options() {
		return  SingleDicMap.getOptionCollection("0001");
	}

	/**
	 * @param collection
	 */
	public void setStatus_options(Collection collection) {
		status_options = collection;
	}

	/**
	 * @return
	 */
	public String getOrg_name() {
		return org_name;
	}

	/**
	 * @param string
	 */
	public void setOrg_name(String string) {
		org_name = string;
	}

	/**
	 * @return
	 */
	public String getLogin_id2() {
		return login_id2;
	}

	/**
	 * @return
	 */
	public String[] getUser_roles() {
		return user_roles;
	}

	/**
	 * @param string
	 */
	public void setLogin_id2(String string) {
		login_id2 = string;
	}

	/**
	 * @param strings
	 */
	public void setUser_roles(String[] strings) {
		user_roles = strings;
	}

	/**
	 * @return
	 */
	public String getId_field() {
		return id_field;
	}

	/**
	 * @return
	 */
	
	/**
	 * @param string
	 */
	public void setId_field(String string) {
		id_field = string;
	}

	/**
	 * @param string
	 */
	
    /**
     * Create on 2007-4-29 14:03:16 Administrator
     * 
     * 
     * @return
     */
    public Collection getAmsd_store_f_options() {
        return ReDefSDicMap.getOptionCollection("0011");
    }

    /**
     * Create on 2007-4-29 14:03:17 Administrator
     * 
     * 
     * @return
     */
    public String getAmsd_store_line() {
        return amsd_store_line;
    }

    /**
     * Create on 2007-4-29 14:03:17 Administrator
     * 
     * 
     * @param collection
     */
    public void setAmsd_store_f_options(Collection collection) {
        amsd_store_f_options = collection;
    }

    /**
     * Create on 2007-4-29 14:03:17 Administrator
     * 
     * 
     * @param string
     */
    public void setAmsd_store_line(String string) {
        amsd_store_line = string;
    }

    /**
     * Create on 2007-4-30 14:32:59 Administrator
     * 
     * 
     * @return
     */
    public String getSeq_no_f() {
        return seq_no_f;
    }

    /**
     * Create on 2007-4-30 14:32:59 Administrator
     * 
     * 
     * @param string
     */
    public void setSeq_no_f(String string) {
        seq_no_f = string;
    }

    /**
     * Create on 2007-5-8 12:41:21 Administrator
     * 
     * 
     * @return
     */
    public String getUser_id_f() {
        return user_id_f;
    }

    /**
     * Create on 2007-5-8 12:41:21 Administrator
     * 
     * 
     * @param string
     */
    public void setUser_id_f(String string) {
        user_id_f = string;
    }

    /**
     * Create on 2007-5-8 13:55:00 Administrator
     * 
     * 
     * @return
     */
    public String getName_field() {
        return name_field;
    }

    /**
     * Create on 2007-5-8 13:55:00 Administrator
     * 
     * 
     * @param string
     */
    public void setName_field(String string) {
        name_field = string;
    }

    /**
     * Create on 2007-5-8 16:20:53 Administrator
     * 
     * 
     * @return
     */
    public String getCre_branch_f() {
        return cre_branch_f;
    }

    /**
     * Create on 2007-5-8 16:20:53 Administrator
     * 
     * 
     * @return
     */
    public String getCre_branch_name_f() {
        return cre_branch_name_f;
    }

    /**
     * Create on 2007-5-8 16:20:53 Administrator
     * 
     * 
     * @param string
     */
    public void setCre_branch_f(String string) {
        cre_branch_f = string;
    }

    /**
     * Create on 2007-5-8 16:20:53 Administrator
     * 
     * 
     * @param string
     */
    public void setCre_branch_name_f(String string) {
        cre_branch_name_f = string;
    }

    /**
     * Create on 2007-5-9 11:50:01 Administrator
     * 
     * 
     * @return
     */
    public String getAmsd_store_name() {
        return amsd_store_name;
    }

    /**
     * Create on 2007-5-9 11:50:01 Administrator
     * 
     * 
     * @param string
     */
    public void setAmsd_store_name(String string) {
        amsd_store_name = string;
    }

	/**
	 * @return
	 */
	public String getCurrAmsdStore() {
		return currAmsdStore;
	}

	/**
	 * @param string
	 */
	public void setCurrAmsdStore(String string) {
		currAmsdStore = string;
	}

} 

