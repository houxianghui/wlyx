 
package com.maintainrecord; 
 
import java.util.*; 
 
import com.eis.base.BaseForm; 
import com.eis.cache.*; 
 
/** 
 * ˵��������֧�ַ����¼�����ݶ��� 
 */ 
public class Maintain_recordForm extends BaseForm { 
 
	/** 
	 * ���캯�� 
	 */ 
	public Maintain_recordForm() { 
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
	private String qus_date_f;
	private String res_result_f;
 	private Collection res_result_c;
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
	private String res_cost; 
 
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
	public String getRes_cost() {  
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
	public void  setSeq_no(String str) {  
		seq_no = str; 
	} 
 
	/** 
	 *  ����������� 
	 */ 
	public void  setQus_date(String str) {  
		qus_date = str; 
	} 
 
	/** 
	 *  ��������˻������� 
	 */ 
	public void  setQus_user(String str) {  
		qus_user = str; 
	} 
 
	/** 
	 *  ������������ 
	 */ 
	public void  setQus_info(String str) {  
		qus_info = str; 
	} 
 
	/** 
	 *  ���ô���Ա 
	 */ 
	public void  setRes_user(String str) {  
		res_user = str; 
	} 
 
	/** 
	 *  ���ô𸴽�� 
	 */ 
	public void  setRes_result(String str) {  
		res_result = str; 
	} 
 
	/** 
	 *  ���ñ�ע 
	 */ 
	public void  setRes_memo(String str) {  
		res_memo = str; 
	} 
 
	/** 
	 *  �������ù�ʱ 
	 */ 
	public void  setRes_cost(String str) {  
		res_cost = str; 
	} 
 
	/** 
	 *  ����¼��Ա 
	 */ 
	public void  setInput_user(String str) {  
		input_user = str; 
	} 
 
	/** 
	 *  ����input_time 
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

