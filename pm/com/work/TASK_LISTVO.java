 
package com.work; 
 
import com.eis.base.BaseVO; 
 
/** 
 * 说明：工作列表的数据对象 
 */ 
public class TASK_LISTVO extends BaseVO { 
 
	/** 
	 * 构造函数 
	 */ 
	public TASK_LISTVO() { 
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
 
	/** 
	 * task_step 
	 */ 
	private String task_step; 
 
	/** 
	 * task_type 
	 */ 
	private String task_type; 
 
	/** 
	 * task_goal 
	 */ 
	private String task_goal; 
 
	/** 
	 * task_cost 
	 */ 
	private double task_cost; 
 
	/** 
	 * task_user 
	 */ 
	private String task_user; 
 
	/** 
	 * update_date 
	 */ 
	private String update_date; 
 
	/** 
	 * task_memo 
	 */ 
	private String task_memo; 
 
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
	public double getTask_cost() {  
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
	public void  setTask_no(String val) {  
		task_no = val; 
	} 
 
	/** 
	 *  设置task_date 
	 */ 
	public void  setTask_date(String val) {  
		task_date = val; 
	} 
 
	/** 
	 *  设置task_step 
	 */ 
	public void  setTask_step(String val) {  
		task_step = val; 
	} 
 
	/** 
	 *  设置task_type 
	 */ 
	public void  setTask_type(String val) {  
		task_type = val; 
	} 
 
	/** 
	 *  设置task_goal 
	 */ 
	public void  setTask_goal(String val) {  
		task_goal = val; 
	} 
 
	/** 
	 *  设置task_cost 
	 */ 
	public void  setTask_cost(double val) {  
		task_cost = val; 
	} 
 
	/** 
	 *  设置task_user 
	 */ 
	public void  setTask_user(String val) {  
		task_user = val; 
	} 
 
	/** 
	 *  设置update_date 
	 */ 
	public void  setUpdate_date(String val) {  
		update_date = val; 
	} 
 
	/** 
	 *  设置task_memo 
	 */ 
	public void  setTask_memo(String val) {  
		task_memo = val; 
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

