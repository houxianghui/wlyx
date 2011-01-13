 
package com.work; 
 
import java.util.*; 
 
import com.eis.base.BaseForm; 
import com.eis.cache.*; 
 
/** 
 * 说明：工作列表的数据对象 
 */ 
public class PROJECT_LISTForm extends BaseForm { 
 
	/** 
	 * 构造函数 
	 */ 
	public PROJECT_LISTForm() { 
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
	private String curr_step_f;
	private String project_no_f;
	private Collection curr_step_c;
 
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
	public void  setUser_id(String str) {  
		user_id = str; 
	} 
 
	/** 
	 *  设置project_no 
	 */ 
	public void  setProject_no(String str) {  
		project_no = str; 
	} 
 
	/** 
	 *  设置project_name 
	 */ 
	public void  setProject_name(String str) {  
		project_name = str; 
	} 
 
	/** 
	 *  设置curr_step 
	 */ 
	public void  setCurr_step(String str) {  
		curr_step = str; 
	} 
 
	/** 
	 *  设置start_date 
	 */ 
	public void  setStart_date(String str) {  
		start_date = str; 
	} 
 
	/** 
	 *  设置end_date 
	 */ 
	public void  setEnd_date(String str) {  
		end_date = str; 
	} 
 
    /**
     * @author houxh 2007-12-26
     * 
     * @return 
     */
    public Collection getCurr_step_c() {
        return SingleDicMap.getOptionCollection("9994");
    }

    /**
     * @author houxh 2007-12-26
     * 
     * @param collection 
     */
    public void setCurr_step_c(Collection collection) {
        curr_step_c = collection;
    }

    /**
     * @author houxh 2007-12-27
     * 
     * @return 
     */
    public String getCurr_step_f() {
        return curr_step_f;
    }

    /**
     * @author houxh 2007-12-27
     * 
     * @return 
     */
    public String getProject_no_f() {
        return project_no_f;
    }

    /**
     * @author houxh 2007-12-27
     * 
     * @param string 
     */
    public void setCurr_step_f(String string) {
        curr_step_f = string;
    }

    /**
     * @author houxh 2007-12-27
     * 
     * @param string 
     */
    public void setProject_no_f(String string) {
        project_no_f = string;
    }

} 

