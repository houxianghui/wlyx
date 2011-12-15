 
package com.eis.dic.sdic; 
 
import com.eis.base.BaseForm; 
 
/** 
 * 说明：单级字典的数据对象 
 */ 
public class SDicForm extends BaseForm { 
 
	/** 
	 * 构造函数 
	 */ 
	public SDicForm() { 
		super(); 
	} 
    
    private String item_code_f;
	private String item_val_f;
	private String logic_id_f;
	/** 
	 * 系统编号 
	 */ 
	private String sys_id; 
 
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
	private String list_order; 
 
	/** 
	 * 逻辑代码 
	 */ 
	private String logic_id; 
 
	/** 
	 * 状态 
	 */ 
	private String status; 
	
	public String getItem_code_f() {  
			return item_code_f; 
		} 
	public String getItem_val_f() {  
				return item_val_f; 
			} 
	public String getLogic_id_f() {  
				return logic_id_f; 
			} 
	/** 
	 *  获得系统编号 
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
	public String getList_order() {  
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
	
	public void  setItem_code_f(String str) {  
			item_code_f = str; 
		} 
	public void  setItem_val_f(String str) {  
				item_val_f = str; 
			}
	public void  setLogic_id_f(String str) {  
				logic_id_f = str; 
			}
 
	/** 
	 *  设置系统编号 
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
	 *  设置选项编码 
	 */ 
	public void  setItem_code(String str) {  
		item_code = str; 
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
	 *  设置逻辑代码 
	 */ 
	public void  setLogic_id(String str) {  
		logic_id = str; 
	} 
 
	/** 
	 *  设置状态 
	 */ 
	public void  setStatus(String str) {  
		status = str; 
	} 
 
} 

