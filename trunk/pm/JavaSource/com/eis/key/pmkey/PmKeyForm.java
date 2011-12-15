 
package com.eis.key.pmkey; 
 
import com.eis.base.BaseForm; 
 
/** 
 * 说明：主键配置的数据对象 
 */ 
public class PmKeyForm extends BaseForm { 
 
	/** 
	 * 构造函数 
	 */ 
	public PmKeyForm() { 
		super(); 
	} 
 
 
	private String tb_name_f;
	/** 
	 * 数据库表名 
	 */ 
	private String tb_name; 
 
	/** 
	 * 主键字段 
	 */ 
	private String key_field; 
 
	/** 
	 * 步长 
	 */ 
	private String step_len; 
 
	/** 
	 * 当前最大值 
	 */ 
	private String max_val; 
 
	/** 
	 *  获得数据库表名 
	 */ 
	
	public String getTb_name_f() {  
			return tb_name_f; 
		} 
	public String getTb_name() {  
		return tb_name; 
	} 
 
	/** 
	 *  获得主键字段 
	 */ 
	public String getKey_field() {  
		return key_field; 
	} 
 
	/** 
	 *  获得步长 
	 */ 
	public String getStep_len() {  
		return step_len; 
	} 
 
	/** 
	 *  获得当前最大值 
	 */ 
	public String getMax_val() {  
		return max_val; 
	} 
 
	/** 
	 *  设置数据库表名 
	 */ 
	
	public void  setTb_name_f(String str) {  
			tb_name_f = str; 
		} 
	public void  setTb_name(String str) {  
		tb_name = str; 
	} 
 
	/** 
	 *  设置主键字段 
	 */ 
	public void  setKey_field(String str) {  
		key_field = str; 
	} 
 
	/** 
	 *  设置步长 
	 */ 
	public void  setStep_len(String str) {  
		step_len = str; 
	} 
 
	/** 
	 *  设置当前最大值 
	 */ 
	public void  setMax_val(String str) {  
		max_val = str; 
	} 
 
} 

