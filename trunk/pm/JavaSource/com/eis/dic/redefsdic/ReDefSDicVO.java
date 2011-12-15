 
package com.eis.dic.redefsdic; 
 
import com.eis.base.BaseVO; 
 
/** 
 * 说明：自定义单级字典的数据对象 
 */ 
public class ReDefSDicVO extends BaseVO { 
 
	/** 
	 * 构造函数 
	 */ 
	public ReDefSDicVO() { 
		super(); 
	} 
 
	/** 
	 * 规类码 
	 */ 
	private String type_id; 
 
	/** 
	 * 字典内容 
	 */ 
	private String caption; 
 
	/** 
	 * 数据查询语句 
	 */ 
	private String stmt; 
 
	/** 
	 * 备注 
	 */ 
	private String memo; 
 
	/** 
	 * 更新人员 
	 */ 
	private String user_id; 
 
	/** 
	 * 更新日期 
	 */ 
	private String reg_dt; 
 
	/** 
	 *  获得规类码 
	 */ 
	public String getType_id() {  
		return type_id; 
	} 
 
	/** 
	 *  获得字典内容 
	 */ 
	public String getCaption() {  
		return caption; 
	} 
 
	/** 
	 *  获得数据查询语句 
	 */ 
	public String getStmt() {  
		return stmt; 
	} 
 
	/** 
	 *  获得备注 
	 */ 
	public String getMemo() {  
		return memo; 
	} 
 
	/** 
	 *  获得更新人员 
	 */ 
	public String getUser_id() {  
		return user_id; 
	} 
 
	/** 
	 *  获得更新日期 
	 */ 
	public String getReg_dt() {  
		return reg_dt; 
	} 
 
	/** 
	 *  设置规类码 
	 */ 
	public void  setType_id(String val) {  
		type_id = val; 
	} 
 
	/** 
	 *  设置字典内容 
	 */ 
	public void  setCaption(String val) {  
		caption = val; 
	} 
 
	/** 
	 *  设置数据查询语句 
	 */ 
	public void  setStmt(String val) {  
		stmt = val; 
	} 
 
	/** 
	 *  设置备注 
	 */ 
	public void  setMemo(String val) {  
		memo = val; 
	} 
 
	/** 
	 *  设置更新人员 
	 */ 
	public void  setUser_id(String val) {  
		user_id = val; 
	} 
 
	/** 
	 *  设置更新日期 
	 */ 
	public void  setReg_dt(String val) {  
		reg_dt = val; 
	} 
 
} 

