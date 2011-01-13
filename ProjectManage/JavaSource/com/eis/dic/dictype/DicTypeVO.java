 
package com.eis.dic.dictype; 
 
import com.eis.base.BaseVO; 
 
/** 
 * 说明：字典归类信息的数据对象 
 */ 
public class DicTypeVO extends BaseVO { 
 
	/** 
	 * 构造函数 
	 */ 
	public DicTypeVO() { 
		super(); 
	} 
 
	/** 
	 * 归类码 
	 */ 
	private String type_id; 
 
	/** 
	 * 名称 
	 */ 
	private String type_name; 
 
	/** 
	 * 级别标志 
	 */ 
	private String dic_level; 
 
	/** 
	 *  获得归类码 
	 */ 
	public String getType_id() {  
		return type_id; 
	} 
 
	/** 
	 *  获得名称 
	 */ 
	public String getType_name() {  
		return type_name; 
	} 
 
	/** 
	 *  获得级别标志 
	 */ 
	public String getDic_level() {  
		return dic_level; 
	} 
 
	/** 
	 *  设置归类码 
	 */ 
	public void  setType_id(String val) {  
		type_id = val; 
	} 
 
	/** 
	 *  设置名称 
	 */ 
	public void  setType_name(String val) {  
		type_name = val; 
	} 
 
	/** 
	 *  设置级别标志 
	 */ 
	public void  setDic_level(String val) {  
		dic_level = val; 
	} 
 
} 

