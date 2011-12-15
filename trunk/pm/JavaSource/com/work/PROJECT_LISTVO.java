 
package com.work; 
 
import com.eis.base.BaseVO; 
 
/** 
 * 说明：工作列表的数据对象 
 */ 
public class PROJECT_LISTVO extends BaseVO { 
 	public static final String BRD = "0";
 	public static final String FTS = "1";
 	public static final String DEV = "2";
 	public static final String SIT = "3";
 	public static final String UAT = "4";
 	public static final String PRD = "5";
 	public static final String DEL = "6";
 	public static final String PAUSE = "7";
 	
	/** 
	 * 构造函数 
	 */ 
	public PROJECT_LISTVO() { 
		super(); 
	} 
 
	/** 
	 * user_id 
	 */ 
	private String user_id; 
 
	/** 
	 * project_no 
	 */ 
	private String project_no; 
 
	/** 
	 * project_name 
	 */ 
	private String project_name; 
 
	/** 
	 * curr_step 
	 */ 
	private String curr_step; 
 
	/** 
	 * start_date 
	 */ 
	private String start_date; 
 
	/** 
	 * end_date 
	 */ 
	private String end_date; 
 
	/** 
	 *  获得user_id 
	 */ 
	public String getUser_id() {  
		return user_id; 
	} 
 
	/** 
	 *  获得project_no 
	 */ 
	public String getProject_no() {  
		return project_no; 
	} 
 
	/** 
	 *  获得project_name 
	 */ 
	public String getProject_name() {  
		return project_name; 
	} 
 
	/** 
	 *  获得curr_step 
	 */ 
	public String getCurr_step() {  
		return curr_step; 
	} 
 
	/** 
	 *  获得start_date 
	 */ 
	public String getStart_date() {  
		return start_date; 
	} 
 
	/** 
	 *  获得end_date 
	 */ 
	public String getEnd_date() {  
		return end_date; 
	} 
 
	/** 
	 *  设置user_id 
	 */ 
	public void  setUser_id(String val) {  
		user_id = val; 
	} 
 
	/** 
	 *  设置project_no 
	 */ 
	public void  setProject_no(String val) {  
		project_no = val; 
	} 
 
	/** 
	 *  设置project_name 
	 */ 
	public void  setProject_name(String val) {  
		project_name = val; 
	} 
 
	/** 
	 *  设置curr_step 
	 */ 
	public void  setCurr_step(String val) {  
		curr_step = val; 
	} 
 
	/** 
	 *  设置start_date 
	 */ 
	public void  setStart_date(String val) {  
		start_date = val; 
	} 
 
	/** 
	 *  设置end_date 
	 */ 
	public void  setEnd_date(String val) {  
		end_date = val; 
	} 
 
} 

