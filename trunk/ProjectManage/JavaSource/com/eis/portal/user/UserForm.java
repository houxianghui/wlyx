/*********************************************************
 * File: UserForm.java
 * 
 * Version 1.0
 * 
 * Date     2005-9-27
 * 
 * Author   陈 蓉
 * 
 ********************************************************/ 
 
package com.eis.portal.user; 
 
import java.util.*;

import com.eis.base.BaseForm; 
import com.eis.cache.*;
 
/** 
 * 说明：用户列表的数据对象 
 */ 
public class UserForm extends BaseForm { 
 
	/** 
	 * 构造函数 
	 */ 
	public UserForm() { 
		super(); 
	}
			 	 

 
	/** 
	 * 用户编号 
	 */ 
	private String user_id; 
 
	/** 
	 * 部门代码 
	 */ 
	private String depart_id; 
 
	/** 
	 * 主角色代码 
	 */ 
	private String role_id; 	
	

	/** 
	 * 登录编号 
	 */ 
	private String login_id; 
	
	/** 
	 * 登录编号2 ,读取用，用于检查登录编号重复
	 */ 
	private String login_id2; 


 
	/** 
	 * 用户姓名 
	 */ 
	private String user_name; 
 
	/** 
	 * 密码 
	 */ 
	private String password; 
 
	/** 
	 * 联系电话 
	 */ 
	private String phone; 
 
	/** 
	 * 手机 
	 */ 
	private String mobile; 
 
	/** 
	 * 电子邮件 
	 */ 
	private String email; 
	
	private String[] user_roles;
 
	/** 
	 * 邮编 
	 */ 
	private String postcode; 
 
	/** 
	 * 联系地址 
	 */ 
	private String address; 
 
	/** 
	 * 状态 
	 */ 
	private String status; 
 
	/** 
	 * 建立日期 
	 */ 
	private String reg_dt; 
 
	/** 
	 * 起始日期 
	 */ 
	private String begin_dt; 
 
	/** 
	 * 失效日期 
	 */ 
	private String invalid_dt; 
 
	/** 
	 * 密码失效日期 
	 */ 
	private String modify_dt; 
 
	/** 
	 * 备注 
	 */ 
	private String memo; 
 
	/** 
	 * 更新日期 
	 */ 
	private String st_chg_dt; 
 
	/** 
	 * 更新管理员代码 
	 */ 
	private String admin_id; 
 
	/** 
	 * 网点号 
	 */ 
	private String amsd_store;	
	
	
	/** 
	 * 网点名称 
	 */ 
	private String org_name;	
	
	
	/** 
	 * 用户名查询条件 
	 */ 
	private String user_name_f;	
 
	/** 
	 * 网点号查询条件 
	 */ 
	private String amsd_store_f;
	
	/** 
	 * 用户状态查询条件 
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
	
	
	
	/********   get方法   ***********/
	
	
	/** 
	 *  获得用户编号 
	 */ 
	public String getUser_id() {  
		return user_id; 
	} 
 
	/** 
	 *  获得部门代码 
	 */ 
	public String getDepart_id() {  
		return depart_id; 
	} 
 
	/** 
	 *  获得主角色代码 
	 */ 
	public String getRole_id() {  
		return role_id; 
	}
	
 
	/** 
	 *  获得登录编号 
	 */ 
	public String getLogin_id() {  
		return login_id; 
	} 
 
	/** 
	 *  获得用户姓名 
	 */ 
	public String getUser_name() {  
		return user_name; 
	} 
 
	/** 
	 *  获得密码 
	 */ 
	public String getPassword() {  
		return password; 
	} 
 
	/** 
	 *  获得联系电话 
	 */ 
	public String getPhone() {  
		return phone; 
	} 
 
	/** 
	 *  获得手机 
	 */ 
	public String getMobile() {  
		return mobile; 
	} 
 
	/** 
	 *  获得电子邮件 
	 */ 
	public String getEmail() {  
		return email; 
	} 
 
	/** 
	 *  获得邮编 
	 */ 
	public String getPostcode() {  
		return postcode; 
	} 
 
	/** 
	 *  获得联系地址 
	 */ 
	public String getAddress() {  
		return address; 
	} 
 
	/** 
	 *  获得状态 
	 */ 
	public String getStatus() {  
		return status; 
	} 
 
	/** 
	 *  获得建立日期 
	 */ 
	public String getReg_dt() {  
		return reg_dt; 
	} 
 
	/** 
	 *  获得起始日期 
	 */ 
	public String getBegin_dt() {  
		return begin_dt; 
	} 
 
	/** 
	 *  获得失效日期 
	 */ 
	public String getInvalid_dt() {  
		return invalid_dt; 
	} 
 
	/** 
	 *  获得密码失效日期 
	 */ 
	public String getModify_dt() {  
		return modify_dt; 
	} 
 
	/** 
	 *  获得备注 
	 */ 
	public String getMemo() {  
		return memo; 
	} 
 
	/** 
	 *  获得更新日期 
	 */ 
	public String getSt_chg_dt() {  
		return st_chg_dt; 
	} 
 
	/** 
	 *  获得更新管理员代码 
	 */ 
	public String getAdmin_id() {  
		return admin_id; 
	} 
 
	/** 
	 *  获得网点号 
	 */ 
	public String getAmsd_store() {  
		return amsd_store; 
	} 
	

	
	/********   set方法   ***********/
	

	 
	/** 
	 *  设置用户编号 
	 */ 
	public void  setUser_id(String str) {  
		user_id = str; 
	} 
 
	/** 
	 *  设置部门代码 
	 */ 
	public void  setDepart_id(String str) {  
		depart_id = str; 
	} 
 
	/** 
	 *  设置主角色代码 
	 */ 
	public void  setRole_id(String str) {  
		role_id = str; 
	} 
	
 
	/** 
	 *  设置登录编号 
	 */ 
	public void  setLogin_id(String str) {  
		login_id = str; 
	} 
 
	/** 
	 *  设置用户姓名 
	 */ 
	public void  setUser_name(String str) {  
		user_name = str; 
	} 
 
	/** 
	 *  设置密码 
	 */ 
	public void  setPassword(String str) {  
		password = str; 
	} 
 
	/** 
	 *  设置联系电话 
	 */ 
	public void  setPhone(String str) {  
		phone = str; 
	} 
 
	/** 
	 *  设置手机 
	 */ 
	public void  setMobile(String str) {  
		mobile = str; 
	} 
 
	/** 
	 *  设置电子邮件 
	 */ 
	public void  setEmail(String str) {  
		email = str; 
	} 
 
	/** 
	 *  设置邮编 
	 */ 
	public void  setPostcode(String str) {  
		postcode = str; 
	} 
 
	/** 
	 *  设置联系地址 
	 */ 
	public void  setAddress(String str) {  
		address = str; 
	} 
 
	/** 
	 *  设置状态 
	 */ 
	public void  setStatus(String str) {  
		status = str; 
	} 
 
	/** 
	 *  设置建立日期 
	 */ 
	public void  setReg_dt(String str) {  
		reg_dt = str; 
	} 
 
	/** 
	 *  设置起始日期 
	 */ 
	public void  setBegin_dt(String str) {  
		begin_dt = str; 
	} 
 
	/** 
	 *  设置失效日期 
	 */ 
	public void  setInvalid_dt(String str) {  
		invalid_dt = str; 
	} 
 
	/** 
	 *  设置密码失效日期 
	 */ 
	public void  setModify_dt(String str) {  
		modify_dt = str; 
	} 
 
	/** 
	 *  设置备注 
	 */ 
	public void  setMemo(String str) {  
		memo = str; 
	} 
 
	/** 
	 *  设置更新日期 
	 */ 
	public void  setSt_chg_dt(String str) {  
		st_chg_dt = str; 
	} 
 
	/** 
	 *  设置更新管理员代码 
	 */ 
	public void  setAdmin_id(String str) {  
		admin_id = str; 
	} 
 
	/** 
	 *  设置网点号 
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

