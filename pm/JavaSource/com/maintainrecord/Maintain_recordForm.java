 
package com.maintainrecord; 
 
import java.util.*; 
 
import com.eis.base.BaseForm; 
import com.eis.cache.*; 
 
/** 
 * 说明：技术支持服务记录的数据对象 
 */ 
public class Maintain_recordForm extends BaseForm { 
 
	/** 
	 * 构造函数 
	 */ 
	public Maintain_recordForm() { 
		super(); 
	} 
 
	/** 
	 * 顺序号 
	 */ 
	private String seq_no; 
 
	/** 
	 * 提出日期 
	 */ 
	private String qus_date; 
	private String qus_date_f;
	private String res_result_f;
 	private Collection res_result_c;
	/** 
	 * 提出人机构姓名 
	 */ 
	private String qus_user; 
 
	/** 
	 * 问题描述 
	 */ 
	private String qus_info; 
 
	/** 
	 * 答复人员 
	 */ 
	private String res_user; 
 
	/** 
	 * 答复结果 
	 */ 
	private String res_result; 
 
	/** 
	 * 备注 
	 */ 
	private String res_memo; 
 
	/** 
	 * 所用工时 
	 */ 
	private String res_cost; 
 
	/** 
	 * 录入员 
	 */ 
	private String input_user; 
 
	/** 
	 * input_time 
	 */ 
	private String input_time; 
	//response time
	private String res_time;
 
	/** 
	 *  获得顺序号 
	 */ 
	public String getSeq_no() {  
		return seq_no; 
	} 
 
	/** 
	 *  获得提出日期 
	 */ 
	public String getQus_date() {  
		return qus_date; 
	} 
 
	/** 
	 *  获得提出人机构姓名 
	 */ 
	public String getQus_user() {  
		return qus_user; 
	} 
 
	/** 
	 *  获得问题描述 
	 */ 
	public String getQus_info() {  
		return qus_info; 
	} 
 
	/** 
	 *  获得答复人员 
	 */ 
	public String getRes_user() {  
		return res_user; 
	} 
 
	/** 
	 *  获得答复结果 
	 */ 
	public String getRes_result() {  
		return res_result; 
	} 
 
	/** 
	 *  获得备注 
	 */ 
	public String getRes_memo() {  
		return res_memo; 
	} 
 
	/** 
	 *  获得所用工时 
	 */ 
	public String getRes_cost() {  
		return res_cost; 
	} 
 
	/** 
	 *  获得录入员 
	 */ 
	public String getInput_user() {  
		return input_user; 
	} 
 
	/** 
	 *  获得input_time 
	 */ 
	public String getInput_time() {  
		return input_time; 
	} 
 
	/** 
	 *  设置顺序号 
	 */ 
	public void  setSeq_no(String str) {  
		seq_no = str; 
	} 
 
	/** 
	 *  设置提出日期 
	 */ 
	public void  setQus_date(String str) {  
		qus_date = str; 
	} 
 
	/** 
	 *  设置提出人机构姓名 
	 */ 
	public void  setQus_user(String str) {  
		qus_user = str; 
	} 
 
	/** 
	 *  设置问题描述 
	 */ 
	public void  setQus_info(String str) {  
		qus_info = str; 
	} 
 
	/** 
	 *  设置答复人员 
	 */ 
	public void  setRes_user(String str) {  
		res_user = str; 
	} 
 
	/** 
	 *  设置答复结果 
	 */ 
	public void  setRes_result(String str) {  
		res_result = str; 
	} 
 
	/** 
	 *  设置备注 
	 */ 
	public void  setRes_memo(String str) {  
		res_memo = str; 
	} 
 
	/** 
	 *  设置所用工时 
	 */ 
	public void  setRes_cost(String str) {  
		res_cost = str; 
	} 
 
	/** 
	 *  设置录入员 
	 */ 
	public void  setInput_user(String str) {  
		input_user = str; 
	} 
 
	/** 
	 *  设置input_time 
	 */ 
	public void  setInput_time(String str) {  
		input_time = str; 
	} 
 
    /**
     * @author houxh 2008-1-9
     * 
     * @return 
     */
    public String getRes_time() {
        return res_time;
    }

    /**
     * @author houxh 2008-1-9
     * 
     * @param string 
     */
    public void setRes_time(String string) {
        res_time = string;
    }

    /**
     * @author houxh 2008-1-9
     * 
     * @return 
     */
    public String getQus_date_f() {
        return qus_date_f;
    }

    /**
     * @author houxh 2008-1-9
     * 
     * @return 
     */
    public String getRes_result_f() {
        return res_result_f;
    }

    /**
     * @author houxh 2008-1-9
     * 
     * @param string 
     */
    public void setQus_date_f(String string) {
        qus_date_f = string;
    }

    /**
     * @author houxh 2008-1-9
     * 
     * @param string 
     */
    public void setRes_result_f(String string) {
        res_result_f = string;
    }

    /**
     * @author houxh 2008-1-9
     * 
     * @return 
     */
    public Collection getRes_result_c() {
        return SingleDicMap.getOptionCollection("9993");
    }

    /**
     * @author houxh 2008-1-9
     * 
     * @param collection 
     */
    public void setRes_result_c(Collection collection) {
        res_result_c = collection;
    }

} 

