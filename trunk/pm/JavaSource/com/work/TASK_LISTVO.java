 
package com.work; 
 
import com.eis.base.BaseVO; 
 
/** 
 * ˵���������б�����ݶ��� 
 */ 
public class TASK_LISTVO extends BaseVO { 
 
	/** 
	 * ���캯�� 
	 */ 
	public TASK_LISTVO() { 
		super(); 
	} 
 	private int id;
	/** 
	 * task_no 
	 */ 
 	private String type;
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
	 *  ���task_no 
	 */ 
	public String getTask_no() {  
		return task_no; 
	} 
 
	/** 
	 *  ���task_date 
	 */ 
	public String getTask_date() {  
		return task_date; 
	} 
 
	/** 
	 *  ���task_step 
	 */ 
	public String getTask_step() {  
		return task_step; 
	} 
 
	/** 
	 *  ���task_type 
	 */ 
	public String getTask_type() {  
		return task_type; 
	} 
 
	/** 
	 *  ���task_goal 
	 */ 
	public String getTask_goal() {  
		return task_goal; 
	} 
 
	/** 
	 *  ���task_cost 
	 */ 
	public double getTask_cost() {  
		return task_cost; 
	} 
 
	/** 
	 *  ���task_user 
	 */ 
	public String getTask_user() {  
		return task_user; 
	} 
 
	/** 
	 *  ���update_date 
	 */ 
	public String getUpdate_date() {  
		return update_date; 
	} 
 
	/** 
	 *  ���task_memo 
	 */ 
	public String getTask_memo() {  
		return task_memo; 
	} 
 
	/** 
	 *  ����task_no 
	 */ 
	public void  setTask_no(String val) {  
		task_no = val; 
	} 
 
	/** 
	 *  ����task_date 
	 */ 
	public void  setTask_date(String val) {  
		task_date = val; 
	} 
 
	/** 
	 *  ����task_step 
	 */ 
	public void  setTask_step(String val) {  
		task_step = val; 
	} 
 
	/** 
	 *  ����task_type 
	 */ 
	public void  setTask_type(String val) {  
		task_type = val; 
	} 
 
	/** 
	 *  ����task_goal 
	 */ 
	public void  setTask_goal(String val) {  
		task_goal = val; 
	} 
 
	/** 
	 *  ����task_cost 
	 */ 
	public void  setTask_cost(double val) {  
		task_cost = val; 
	} 
 
	/** 
	 *  ����task_user 
	 */ 
	public void  setTask_user(String val) {  
		task_user = val; 
	} 
 
	/** 
	 *  ����update_date 
	 */ 
	public void  setUpdate_date(String val) {  
		update_date = val; 
	} 
 
	/** 
	 *  ����task_memo 
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

} 

