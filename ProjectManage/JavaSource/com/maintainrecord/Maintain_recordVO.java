 
package com.maintainrecord; 
 
import com.eis.base.BaseVO;
import com.eis.portal.UserContext;
import com.eis.util.DateUtil;
 
/** 
 * 说明：技术支持服务记录的数据对象 
 */ 
public class Maintain_recordVO extends BaseVO { 
 
	/** 
	 * 构造函数 
	 */ 
	public Maintain_recordVO() { 
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
	private double res_cost; 
 
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
     * 	0、未解决	1、以后解决	2、已解决	3、已删除
     *
     */
    public static final String UNRESP = "0";

    public static final String FUTURE_RESP = "1";

    public static final String FINISHED = "2";

    public static final String DELETED = "3";
 
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
	public double getRes_cost() {  
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
	public void  setSeq_no(String val) {  
		seq_no = val; 
	} 
 
	/** 
	 *  设置提出日期 
	 */ 
	public void  setQus_date(String val) {  
		qus_date = val; 
	} 
 
	/** 
	 *  设置提出人机构姓名 
	 */ 
	public void  setQus_user(String val) {  
		qus_user = val; 
	} 
 
	/** 
	 *  设置问题描述 
	 */ 
	public void  setQus_info(String val) {  
		qus_info = val; 
	} 
 
	/** 
	 *  设置答复人员 
	 */ 
	public void  setRes_user(String val) {  
		res_user = val; 
	} 
 
	/** 
	 *  设置答复结果 
	 */ 
	public void  setRes_result(String val) {  
		res_result = val; 
	} 
 
	/** 
	 *  设置备注 
	 */ 
	public void  setRes_memo(String val) {  
		res_memo = val; 
	} 
 
	/** 
	 *  设置所用工时 
	 */ 
	public void  setRes_cost(double val) {  
		res_cost = val; 
	} 
 
	/** 
	 *  设置录入员 
	 */ 
	public void  setInput_user(String val) {  
		input_user = val; 
	} 
 
	/** 
	 *  设置input_time 
	 */ 
	public void  setInput_time(String val) {  
		input_time = val; 
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

    public void initAddVO(UserContext user) {
        setRes_result(UNRESP);
        setRes_user("");
        setRes_time("");
        setRes_memo("");
        setInput_time(DateUtil.getTimeStr());
        setInput_user(user.getUserID());
    }
} 

