 
package com.eis.dic.mdic; 
 
import com.eis.base.BaseVO; 
 
/** 
 * 说明：多级字典的数据对象 
 */ 
public class MDicVO extends BaseVO { 
 
	/** 
	 * 构造函数 
	 */ 
	public MDicVO() { 
		super(); 
	} 
 
	/** 
	 * 系统编码 
	 */ 
	private long sys_id; 
 
	/** 
	 * 归类码 
	 */ 
	private String type_id; 
 
	/** 
	 * 上级编码 
	 */ 
	private long parent_id; 
 
	/** 
	 * 选项编码 
	 */ 
	private String item_id; 
 
	/** 
	 * 选项值 
	 */ 
	private String item_val; 
 
	/** 
	 * 输出顺序 
	 */ 
	private short list_order; 
 
	/** 
	 * 级次 
	 */ 
	private short item_level; 
 
	/** 
	 * 逻辑代码 
	 */ 
	private String logic_id; 
 
	/** 
	 * status 
	 */ 
	private String status; 
 
	/** 
	 *  获得系统编码 
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
	 *  获得上级编码 
	 */ 
	public long getParent_id() {  
		return parent_id; 
	} 
 
	/** 
	 *  获得选项编码 
	 */ 
	public String getItem_id() {  
		return item_id; 
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
	 *  获得级次 
	 */ 
	public short getItem_level() {  
		return item_level; 
	} 
 
	/** 
	 *  获得逻辑代码 
	 */ 
	public String getLogic_id() {  
		return logic_id; 
	} 
 
	/** 
	 *  获得status 
	 */ 
	public String getStatus() {  
		return status; 
	} 
 
	/** 
	 *  设置系统编码 
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
	 *  设置上级编码 
	 */ 
	public void  setParent_id(long val) {  
		parent_id = val; 
	} 
 
	/** 
	 *  设置选项编码 
	 */ 
	public void  setItem_id(String val) {  
		item_id = val; 
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
	 *  设置级次 
	 */ 
	public void  setItem_level(short val) {  
		item_level = val; 
	} 
 
	/** 
	 *  设置逻辑代码 
	 */ 
	public void  setLogic_id(String val) {  
		logic_id = val; 
	} 
 
	/** 
	 *  设置status 
	 */ 
	public void  setStatus(String val) {  
		status = val; 
	} 
 
} 

