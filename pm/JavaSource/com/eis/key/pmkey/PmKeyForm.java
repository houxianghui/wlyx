 
package com.eis.key.pmkey; 
 
import com.eis.base.BaseForm; 
 
/** 
 * ˵�����������õ����ݶ��� 
 */ 
public class PmKeyForm extends BaseForm { 
 
	/** 
	 * ���캯�� 
	 */ 
	public PmKeyForm() { 
		super(); 
	} 
 
 
	private String tb_name_f;
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
	private String step_len; 
 
	/** 
	 * ��ǰ���ֵ 
	 */ 
	private String max_val; 
 
	/** 
	 *  ������ݿ���� 
	 */ 
	
	public String getTb_name_f() {  
			return tb_name_f; 
		} 
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
	public String getStep_len() {  
		return step_len; 
	} 
 
	/** 
	 *  ��õ�ǰ���ֵ 
	 */ 
	public String getMax_val() {  
		return max_val; 
	} 
 
	/** 
	 *  �������ݿ���� 
	 */ 
	
	public void  setTb_name_f(String str) {  
			tb_name_f = str; 
		} 
	public void  setTb_name(String str) {  
		tb_name = str; 
	} 
 
	/** 
	 *  ���������ֶ� 
	 */ 
	public void  setKey_field(String str) {  
		key_field = str; 
	} 
 
	/** 
	 *  ���ò��� 
	 */ 
	public void  setStep_len(String str) {  
		step_len = str; 
	} 
 
	/** 
	 *  ���õ�ǰ���ֵ 
	 */ 
	public void  setMax_val(String str) {  
		max_val = str; 
	} 
 
} 

