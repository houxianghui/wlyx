 
package com.eis.key.pmkey; 
 
import com.eis.base.BaseVO; 
 
/** 
 * ˵�����������õ����ݶ��� 
 */ 
public class PmKeyVO extends BaseVO { 
 
	/** 
	 * ���캯�� 
	 */ 
	public PmKeyVO() { 
		super(); 
	} 
 
	/** 
	 * ���ݿ���� 
	 */ 
	private String tb_name; 
 
	/** 
	 * �����ֶ� 
	 */ 
	private String key_field; 
 
	/** 
	 * ���� 
	 */ 
	private int step_len; 
 
	/** 
	 * ��ǰ���ֵ 
	 */ 
	private long max_val; 
 
	/** 
	 *  ������ݿ���� 
	 */ 
	public String getTb_name() {  
		return tb_name; 
	} 
 
	/** 
	 *  ��������ֶ� 
	 */ 
	public String getKey_field() {  
		return key_field; 
	} 
 
	/** 
	 *  ��ò��� 
	 */ 
	public int getStep_len() {  
		return step_len; 
	} 
 
	/** 
	 *  ��õ�ǰ���ֵ 
	 */ 
	public long getMax_val() {  
		return max_val; 
	} 
 
	/** 
	 *  �������ݿ���� 
	 */ 
	public void  setTb_name(String val) {  
		tb_name = val; 
	} 
 
	/** 
	 *  ���������ֶ� 
	 */ 
	public void  setKey_field(String val) {  
		key_field = val; 
	} 
 
	/** 
	 *  ���ò��� 
	 */ 
	public void  setStep_len(int val) {  
		step_len = val; 
	} 
 
	/** 
	 *  ���õ�ǰ���ֵ 
	 */ 
	public void  setMax_val(long val) {  
		max_val = val; 
	} 
 
} 

