 
package com.eis.dic.mdic; 
 
import java.util.*; 
 
import com.eis.base.BaseForm; 
import com.eis.cache.*; 
 
/** 
 * 说明：多级字典的数据对象 
 */ 
public class MDicForm extends BaseForm { 
 
	/** 
	 * 构造函数 
	 */ 
	public MDicForm() { 
		super(); 
	} 
 /**
  * 查询条件
  * */
 private String sys_id_f;
	/** 
	 * 系统编码 
	 */ 
	private String sys_id; 
 
	/** 
	 * 归类码 
	 */ 
	private String type_id; 
 
	/** 
	 * 上级编码 
	 */ 
	private String parent_id; 
 
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
	private String list_order; 
 
	/** 
	 * 级次 
	 */ 
	private String item_level; 
 
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
	public String getSys_id() {  
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
	public String getParent_id() {  
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
	public String getList_order() {  
		return list_order; 
	} 
 
	/** 
	 *  获得级次 
	 */ 
	public String getItem_level() {  
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
	public void  setSys_id(String str) {  
		sys_id = str; 
	} 
 
	/** 
	 *  设置归类码 
	 */ 
	public void  setType_id(String str) {  
		type_id = str; 
	} 
 
	/** 
	 *  设置上级编码 
	 */ 
	public void  setParent_id(String str) {  
		parent_id = str; 
	} 
 
	/** 
	 *  设置选项编码 
	 */ 
	public void  setItem_id(String str) {  
		item_id = str; 
	} 
 
	/** 
	 *  设置选项值 
	 */ 
	public void  setItem_val(String str) {  
		item_val = str; 
	} 
 
	/** 
	 *  设置输出顺序 
	 */ 
	public void  setList_order(String str) {  
		list_order = str; 
	} 
 
	/** 
	 *  设置级次 
	 */ 
	public void  setItem_level(String str) {  
		item_level = str; 
	} 
 
	/** 
	 *  设置逻辑代码 
	 */ 
	public void  setLogic_id(String str) {  
		logic_id = str; 
	} 
 
	/** 
	 *  设置status 
	 */ 
	public void  setStatus(String str) {  
		status = str; 
	} 
 
/**
 * @return
 */
public String getSys_id_f() {
	return sys_id_f;
}

/**
 * @param string
 */
public void setSys_id_f(String string) {
	sys_id_f = string;
}

} 

