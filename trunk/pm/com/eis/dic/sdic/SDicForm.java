 
package com.eis.dic.sdic; 
 
import com.eis.base.BaseForm; 
 
/** 
 * ˵���������ֵ�����ݶ��� 
 */ 
public class SDicForm extends BaseForm { 
 
	/** 
	 * ���캯�� 
	 */ 
	public SDicForm() { 
		super(); 
	} 
    
    private String item_code_f;
	private String item_val_f;
	private String logic_id_f;
	/** 
	 * ϵͳ��� 
	 */ 
	private String sys_id; 
 
	/** 
	 * ������ 
	 */ 
	private String type_id; 
 
	/** 
	 * ѡ����� 
	 */ 
	private String item_code; 
 
	/** 
	 * ѡ��ֵ 
	 */ 
	private String item_val; 
 
	/** 
	 * ���˳�� 
	 */ 
	private String list_order; 
 
	/** 
	 * �߼����� 
	 */ 
	private String logic_id; 
 
	/** 
	 * ״̬ 
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
	 *  ���ϵͳ��� 
	 */ 
	public String getSys_id() {  
		return sys_id; 
	} 
 
	/** 
	 *  ��ù����� 
	 */ 
	public String getType_id() {  
		return type_id; 
	} 
 
	/** 
	 *  ���ѡ����� 
	 */ 
	public String getItem_code() {  
		return item_code; 
	} 
 
	/** 
	 *  ���ѡ��ֵ 
	 */ 
	public String getItem_val() {  
		return item_val; 
	} 
 
	/** 
	 *  ������˳�� 
	 */ 
	public String getList_order() {  
		return list_order; 
	} 
 
	/** 
	 *  ����߼����� 
	 */ 
	public String getLogic_id() {  
		return logic_id; 
	} 
 
	/** 
	 *  ���״̬ 
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
	 *  ����ϵͳ��� 
	 */ 
	public void  setSys_id(String str) {  
		sys_id = str; 
	} 
 
	/** 
	 *  ���ù����� 
	 */ 
	public void  setType_id(String str) {  
		type_id = str; 
	} 
 
	/** 
	 *  ����ѡ����� 
	 */ 
	public void  setItem_code(String str) {  
		item_code = str; 
	} 
 
	/** 
	 *  ����ѡ��ֵ 
	 */ 
	public void  setItem_val(String str) {  
		item_val = str; 
	} 
 
	/** 
	 *  �������˳�� 
	 */ 
	public void  setList_order(String str) {  
		list_order = str; 
	} 
 
	/** 
	 *  �����߼����� 
	 */ 
	public void  setLogic_id(String str) {  
		logic_id = str; 
	} 
 
	/** 
	 *  ����״̬ 
	 */ 
	public void  setStatus(String str) {  
		status = str; 
	} 
 
} 

