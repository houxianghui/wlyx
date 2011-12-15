 
package com.eis.dic.sdic; 
 
import com.eis.base.BaseVO; 
 
/** 
 * 说明：单级字典的数据对象 
 */ 
public class SDicVO extends BaseVO { 
 
	/** 
	 * 构造函数 
	 */ 
	public SDicVO() { 
		super(); 
	} 
 
	/** 
	 * 系统编号 
	 */ 
	private long sys_id; 
 
	/** 
	 * 归类码 
	 */ 
	private String type_id; 
 
	/** 
	 * 选项编码 
	 */ 
	private String item_code; 
 
	/** 
	 * 选项值 
	 */ 
	private String item_val; 
 
	/** 
	 * 输出顺序 
	 */ 
	private short list_order; 
 
	/** 
	 * 逻辑代码 
	 */ 
	private String logic_id; 
 
	/** 
	 * 状态 
	 */ 
	private String status; 
 
	/** 
	 *  获得系统编号 
	 */ 
	public long getSys_id() {  
		return sys_id; 
	} 
 
	/** 
	 *  获得归类码 
	 */ 
	public String getType_id() {  
		return type_id; 
	} 
 
	/** 
	 *  获得选项编码 
	 */ 
	public String getItem_code() {  
		return item_code; 
	} 
 
	/** 
	 *  获得选项值 
	 */ 
	public String getItem_val() {  
		return item_val; 
	} 
 
	/** 
	 *  获得输出顺序 
	 */ 
	public short getList_order() {  
		return list_order; 
	} 
 
	/** 
	 *  获得逻辑代码 
	 */ 
	public String getLogic_id() {  
		return logic_id; 
	} 
 
	/** 
	 *  获得状态 
	 */ 
	public String getStatus() {  
		return status; 
	} 
 
	/** 
	 *  设置系统编号 
	 */ 
	public void  setSys_id(long val) {  
		sys_id = val; 
	} 
 
	/** 
	 *  设置归类码 
	 */ 
	public void  setType_id(String val) {  
		type_id = val; 
	} 
 
	/** 
	 *  设置选项编码 
	 */ 
	public void  setItem_code(String val) {  
		item_code = val; 
	} 
 
	/** 
	 *  设置选项值 
	 */ 
	public void  setItem_val(String val) {  
		item_val = val; 
	} 
 
	/** 
	 *  设置输出顺序 
	 */ 
	public void  setList_order(short val) {  
		list_order = val; 
	} 
 
	/** 
	 *  设置逻辑代码 
	 */ 
	public void  setLogic_id(String val) {  
		logic_id = val; 
	} 
 
	/** 
	 *  设置状态 
	 */ 
	public void  setStatus(String val) {  
		status = val; 
	} 
 
} 

