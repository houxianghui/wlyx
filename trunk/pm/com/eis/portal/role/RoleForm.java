/*********************************************************
 * File: RoleForm.java
 * 
 * Version 1.0
 * 
 * Date     2005-9-12
 * 
 * Author   Chen Rong
 * 
 ********************************************************/

package com.eis.portal.role;

import java.util.*;


import com.eis.base.BaseForm;
import com.eis.cache.*;

/**
 * 说明：
 * 
 */
public class RoleForm extends BaseForm {

	/**
	 * 构造函数
	 */
	public RoleForm() {
		super();
		
	}
	
	private String role_id;	//角色代码
	
	private String role_name;	//角色名称
	
	private String logic_id;	//控制代码
	
	private String amsd_store;	//网点号
	
	private String templ_id;	//首页模板
	
	private String sesn_time;	//会话超时限制
	
	private String role_level; //角色级别	
	
	private String status;	//状态	
	
	private String reg_dt;	//建立日期
	
	private String user_id;	//更新人员
	
	private String checked;	//单选钮选中标志	
	
	
	private Collection templ_id_options = null;
	
	private Collection role_level_options = null;





	/********   get 方法 *******/


	/**
	 * @return
	 */
	public String getRole_id() {
		return role_id;
	}

	/**
	 * @return
	 */
	public String getRole_name() {
		return role_name;
	}
	
	/**
	* @return
	*/
	public String getLogic_id() {
		return logic_id;
	}

	/**
	 * @return
	 */
	public String getAmsd_store() {
		return amsd_store;
	}

	/**
	 * @return
	 */
	public String getTempl_id() {
		return templ_id;
	}

	/**
	 * @return
	 */
	public String getSesn_time() {
		return sesn_time;
	}
	
	/**
	 * @return
	 */
	public String getRole_level() {
		return role_level;
	}	
	

	/**
	 * @return
	 */
	public String getStatus() {
		return status;
	}
	
	
	/**
	 * @return
	 */
	public String getReg_dt() {
		return reg_dt;
	}
	
	/**
	 * @return
	 */
	public String getUser_id() {
		return user_id;
	}	
	
	
	
	/********   set 方法 *******/	
	

	/**
	 * @param string
	 */
	public void setRole_id(String str) {
		role_id = str;
	}

	/**
	 * @param string
	 */
	public void setRole_name(String str) {
		role_name = str;
	}
	
	/**
	 * @param string
	 */
	public void setLogic_id(String str) {
		logic_id = str;
	}	
	

	/**
	 * @param string
	 */
	public void setAmsd_store(String str) {
		amsd_store = str;
	}

	/**
	 * @param string
	 */
	public void setTempl_id(String str) {
		templ_id = str;
	}

	/**
	 * @param string
	 */
	public void setSesn_time(String str) {
		sesn_time = str;
	}
	
	/**
	 * @param string
	 */
	public void setRole_level(String str) {
		role_level = str;
	}	
	

	/**
	 * @param string
	 */
	public void setStatus(String str) {
		status = str;
	}


	
	/**
	 * @param string
	 */
	public void setReg_dt(String str) {
		reg_dt = str;
	}
	
	/**
	 * @param string
	 */
	public void setUser_id(String str) {
		user_id = str;
	}



	/**
	 * @return
	 */
	public Collection getRole_level_options() {		
		return SingleDicMap.getOptionCollection("0004");
	}

	/**
	 * @return
	 */
	public Collection getTempl_id_options() {		
		return ReDefSDicMap.getOptionCollection("0001");
	}

	/**
	 * @param collection
	 */
	public void setRole_level_options(Collection collection) {
		role_level_options = collection;
	}

	/**
	 * @param collection
	 */
	public void setTempl_id_options(Collection collection) {
		templ_id_options = collection;
	}

	/**
	 * @return
	 */
	public String getChecked() {
		return checked;
	}

	/**
	 * @param string
	 */
	public void setChecked(String string) {
		checked = string;
	}

}
