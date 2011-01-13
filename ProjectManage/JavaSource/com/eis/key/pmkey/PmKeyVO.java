 
package com.eis.key.pmkey; 
 
import com.eis.base.BaseVO; 
 
/** 
 * 说明：主键配置的数据对象 
 */ 
public class PmKeyVO extends BaseVO { 
 
	/** 
	 * 构造函数 
	 */ 
	public PmKeyVO() { 
		super(); 
	} 
 
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
	private int step_len; 
 
	/** 
	 * 当前最大值 
	 */ 
	private long max_val; 
 
	/** 
	 *  获得数据库表名 
	 */ 
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
	public int getStep_len() {  
		return step_len; 
	} 
 
	/** 
	 *  获得当前最大值 
	 */ 
	public long getMax_val() {  
		return max_val; 
	} 
 
	/** 
	 *  设置数据库表名 
	 */ 
	public void  setTb_name(String val) {  
		tb_name = val; 
	} 
 
	/** 
	 *  设置主键字段 
	 */ 
	public void  setKey_field(String val) {  
		key_field = val; 
	} 
 
	/** 
	 *  设置步长 
	 */ 
	public void  setStep_len(int val) {  
		step_len = val; 
	} 
 
	/** 
	 *  设置当前最大值 
	 */ 
	public void  setMax_val(long val) {  
		max_val = val; 
	} 
 
} 

