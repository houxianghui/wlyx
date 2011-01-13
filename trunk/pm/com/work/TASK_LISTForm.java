 
package com.work; 
 
import java.util.*; 
 
import com.eis.base.BaseForm; 
import com.eis.cache.*; 
 
/** 
 * 说明：工作列表的数据对象 
 */ 
public class TASK_LISTForm extends BaseForm { 
 
	/** 
	 * 构造函数 
	 */ 
	public TASK_LISTForm() { 
		super(); 
	} 
 	private int id;
	/** 
	 * task_no 
	 */ 
	private String task_no; 
 	private String project_no;
	/** 
	 * task_date 
	 */ 
	private String task_date; 
 	private String task_date_f;
 	private String task_date_e;
 	private String project_no_f;
	/** 
	 * task_step 
	 */ 
	private String task_step; 
	private Collection task_step_c;
 
	/** 
	 * task_type 
	 */ 
	private String task_type; 
	private Collection task_type_c;
 
	/** 
	 * task_goal 
	 */ 
	private String task_goal; 
	private Collection task_goal_c;
 
	/** 
	 * task_cost 
	 */ 
	private String task_cost; 
 
	/** 
	 * task_user 
	 */ 
	private String task_user; 
 	private String task_user_f;
	/** 
	 * update_date 
	 */ 
	private String update_date; 
 
	/** 
	 * task_memo 
	 */ 
	private String task_memo; 
	private Collection stuff;
 
	/** 
	 *  获得task_no 
	 */ 
	public String getTask_no() {  
		return task_no; 
	} 
 
	/** 
	 *  获得task_date 
	 */ 
	public String getTask_date() {  
		return task_date; 
	} 
 
	/** 
	 *  获得task_step 
	 */ 
	public String getTask_step() {  
		return task_step; 
	} 
 
	/** 
	 *  获得task_type 
	 */ 
	public String getTask_type() {  
		return task_type; 
	} 
 
	/** 
	 *  获得task_goal 
	 */ 
	public String getTask_goal() {  
		return task_goal; 
	} 
 
	/** 
	 *  获得task_cost 
	 */ 
	public String getTask_cost() {  
		return task_cost; 
	} 
 
	/** 
	 *  获得task_user 
	 */ 
	public String getTask_user() {  
		return task_user; 
	} 
 
	/** 
	 *  获得update_date 
	 */ 
	public String getUpdate_date() {  
		return update_date; 
	} 
 
	/** 
	 *  获得task_memo 
	 */ 
	public String getTask_memo() {  
		return task_memo; 
	} 
 
	/** 
	 *  设置task_no 
	 */ 
	public void  setTask_no(String str) {  
		task_no = str; 
	} 
 
	/** 
	 *  设置task_date 
	 */ 
	public void  setTask_date(String str) {  
		task_date = str; 
	} 
 
	/** 
	 *  设置task_step 
	 */ 
	public void  setTask_step(String str) {  
		task_step = str; 
	} 
 
	/** 
	 *  设置task_type 
	 */ 
	public void  setTask_type(String str) {  
		task_type = str; 
	} 
 
	/** 
	 *  设置task_goal 
	 */ 
	public void  setTask_goal(String str) {  
		task_goal = str; 
	} 
 
	/** 
	 *  设置task_cost 
	 */ 
	public void  setTask_cost(String str) {  
		task_cost = str; 
	} 
 
	/** 
	 *  设置task_user 
	 */ 
	public void  setTask_user(String str) {  
		task_user = str; 
	} 
 
	/** 
	 *  设置update_date 
	 */ 
	public void  setUpdate_date(String str) {  
		update_date = str; 
	} 
 
	/** 
	 *  设置task_memo 
	 */ 
	public void  setTask_memo(String str) {  
		task_memo = str; 
	} 
 
    /**
     * @author houxh 2007-12-26
     * 
     * @return 
     */
    public Collection getTask_step_c() {
        return SingleDicMap.getOptionCollection("9994");
    }

    /**
     * @author houxh 2007-12-26
     * 
     * @return 
     */
    public Collection getTask_type_c() {
        return SingleDicMap.getOptionCollection("9992");
    }

    /**
     * @author houxh 2007-12-26
     * 
     * @param collection 
     */
    public void setTask_step_c(Collection collection) {
        task_step_c = collection;
    }

    /**
     * @author houxh 2007-12-26
     * 
     * @param collection 
     */
    public void setTask_type_c(Collection collection) {
        task_type_c = collection;
    }

    /**
     * @author houxh 2007-12-26
     * 
     * @return 
     */
    public String getProject_no() {
        return project_no;
    }

    /**
     * @author houxh 2007-12-26
     * 
     * @param string 
     */
    public void setProject_no(String string) {
        project_no = string;
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
     * @return 
     */
    public String getTask_date_f() {
        return task_date_f;
    }

    /**
     * @author houxh 2007-12-27
     * 
     * @param string 
     */
    public void setProject_no_f(String string) {
        project_no_f = string;
    }

    /**
     * @author houxh 2007-12-27
     * 
     * @param string 
     */
    public void setTask_date_f(String string) {
        task_date_f = string;
    }

    /**
     * @author houxh 2007-12-27
     * 
     * @return 
     */
    public String getTask_date_e() {
        return task_date_e;
    }

    /**
     * @author houxh 2007-12-27
     * 
     * @param string 
     */
    public void setTask_date_e(String string) {
        task_date_e = string;
    }

    /**
     * @author houxh 2008-1-11
     * 
     * @return 
     */
    public Collection getTask_goal_c() {
        return SingleDicMap.getOptionCollection("9991");
    }

    /**
     * @author houxh 2008-1-11
     * 
     * @param collection 
     */
    public void setTask_goal_c(Collection collection) {
        task_goal_c = collection;
    }

    /**
     * @author houxh 2008-1-17
     * 
     * @return 
     */
    public String getTask_user_f() {
        return task_user_f;
    }

    /**
     * @author houxh 2008-1-17
     * 
     * @param string 
     */
    public void setTask_user_f(String string) {
        task_user_f = string;
    }

	/**
	 * @return
	 */
	public Collection getStuff() {
		return ReDefSDicMap.getOptionCollection(RedefSDicCodes.STUFF_COLLECTION);
	}

	/**
	 * @param collection
	 */
	public void setStuff(Collection collection) {
		stuff = collection;
	}

	/**
	 * @return
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param i
	 */
	public void setId(int i) {
		id = i;
	}

} 

