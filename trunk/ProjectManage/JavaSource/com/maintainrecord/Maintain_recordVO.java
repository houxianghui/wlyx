 
package com.maintainrecord; 
 
import com.eis.base.BaseVO;
import com.eis.portal.UserContext;
import com.eis.util.DateUtil;
 
/** 
 * ˵��������֧�ַ����¼�����ݶ��� 
 */ 
public class Maintain_recordVO extends BaseVO { 
 
	/** 
	 * ���캯�� 
	 */ 
	public Maintain_recordVO() { 
		super(); 
	} 
 
	/** 
	 * ˳��� 
	 */ 
	private String seq_no; 
 
	/** 
	 * ������� 
	 */ 
	private String qus_date; 
 
	/** 
	 * ����˻������� 
	 */ 
	private String qus_user; 
 
	/** 
	 * �������� 
	 */ 
	private String qus_info; 
 
	/** 
	 * ����Ա 
	 */ 
	private String res_user; 
 
	/** 
	 * �𸴽�� 
	 */ 
	private String res_result; 
 
	/** 
	 * ��ע 
	 */ 
	private String res_memo; 
 
	/** 
	 * ���ù�ʱ 
	 */ 
	private double res_cost; 
 
	/** 
	 * ¼��Ա 
	 */ 
	private String input_user; 
 
	/** 
	 * input_time 
	 */ 
	private String input_time; 
	//response time
	private String res_time;

    /**
     * 	0��δ���	1���Ժ���	2���ѽ��	3����ɾ��
     *
     */
    public static final String UNRESP = "0";

    public static final String FUTURE_RESP = "1";

    public static final String FINISHED = "2";

    public static final String DELETED = "3";
 
	/** 
	 *  ���˳��� 
	 */ 
	public String getSeq_no() {  
		return seq_no; 
	} 
 
	/** 
	 *  ���������� 
	 */ 
	public String getQus_date() {  
		return qus_date; 
	} 
 
	/** 
	 *  �������˻������� 
	 */ 
	public String getQus_user() {  
		return qus_user; 
	} 
 
	/** 
	 *  ����������� 
	 */ 
	public String getQus_info() {  
		return qus_info; 
	} 
 
	/** 
	 *  ��ô���Ա 
	 */ 
	public String getRes_user() {  
		return res_user; 
	} 
 
	/** 
	 *  ��ô𸴽�� 
	 */ 
	public String getRes_result() {  
		return res_result; 
	} 
 
	/** 
	 *  ��ñ�ע 
	 */ 
	public String getRes_memo() {  
		return res_memo; 
	} 
 
	/** 
	 *  ������ù�ʱ 
	 */ 
	public double getRes_cost() {  
		return res_cost; 
	} 
 
	/** 
	 *  ���¼��Ա 
	 */ 
	public String getInput_user() {  
		return input_user; 
	} 
 
	/** 
	 *  ���input_time 
	 */ 
	public String getInput_time() {  
		return input_time; 
	} 
 
	/** 
	 *  ����˳��� 
	 */ 
	public void  setSeq_no(String val) {  
		seq_no = val; 
	} 
 
	/** 
	 *  ����������� 
	 */ 
	public void  setQus_date(String val) {  
		qus_date = val; 
	} 
 
	/** 
	 *  ��������˻������� 
	 */ 
	public void  setQus_user(String val) {  
		qus_user = val; 
	} 
 
	/** 
	 *  ������������ 
	 */ 
	public void  setQus_info(String val) {  
		qus_info = val; 
	} 
 
	/** 
	 *  ���ô���Ա 
	 */ 
	public void  setRes_user(String val) {  
		res_user = val; 
	} 
 
	/** 
	 *  ���ô𸴽�� 
	 */ 
	public void  setRes_result(String val) {  
		res_result = val; 
	} 
 
	/** 
	 *  ���ñ�ע 
	 */ 
	public void  setRes_memo(String val) {  
		res_memo = val; 
	} 
 
	/** 
	 *  �������ù�ʱ 
	 */ 
	public void  setRes_cost(double val) {  
		res_cost = val; 
	} 
 
	/** 
	 *  ����¼��Ա 
	 */ 
	public void  setInput_user(String val) {  
		input_user = val; 
	} 
 
	/** 
	 *  ����input_time 
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

