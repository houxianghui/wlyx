 
package com.eis.dic.mdic; 
 
import java.util.*; 
 
import com.eis.base.BaseForm; 
import com.eis.cache.*; 
 
/** 
 * ˵�����༶�ֵ�����ݶ��� 
 */ 
public class MDicForm extends BaseForm { 
 
	/** 
	 * ���캯�� 
	 */ 
	public MDicForm() { 
		super(); 
	} 
 /**
  * ��ѯ����
  * */
 private String sys_id_f;
	/** 
	 * ϵͳ���� 
	 */ 
	private String sys_id; 
 
	/** 
	 * ������ 
	 */ 
	private String type_id; 
 
	/** 
	 * �ϼ����� 
	 */ 
	private String parent_id; 
 
	/** 
	 * ѡ����� 
	 */ 
	private String item_id; 
 
	/** 
	 * ѡ��ֵ 
	 */ 
	private String item_val; 
 
	/** 
	 * ���˳�� 
	 */ 
	private String list_order; 
 
	/** 
	 * ���� 
	 */ 
	private String item_level; 
 
	/** 
	 * �߼����� 
	 */ 
	private String logic_id; 
 
	/** 
	 * status 
	 */ 
	private String status; 
 
	/** 
	 *  ���ϵͳ���� 
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
	 *  ����ϼ����� 
	 */ 
	public String getParent_id() {  
		return parent_id; 
	} 
 
	/** 
	 *  ���ѡ����� 
	 */ 
	public String getItem_id() {  
		return item_id; 
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
	 *  ��ü��� 
	 */ 
	public String getItem_level() {  
		return item_level; 
	} 
 
	/** 
	 *  ����߼����� 
	 */ 
	public String getLogic_id() {  
		return logic_id; 
	} 
 
	/** 
	 *  ���status 
	 */ 
	public String getStatus() {  
		return status; 
	} 
 
	/** 
	 *  ����ϵͳ���� 
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
	 *  �����ϼ����� 
	 */ 
	public void  setParent_id(String str) {  
		parent_id = str; 
	} 
 
	/** 
	 *  ����ѡ����� 
	 */ 
	public void  setItem_id(String str) {  
		item_id = str; 
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
	 *  ���ü��� 
	 */ 
	public void  setItem_level(String str) {  
		item_level = str; 
	} 
 
	/** 
	 *  �����߼����� 
	 */ 
	public void  setLogic_id(String str) {  
		logic_id = str; 
	} 
 
	/** 
	 *  ����status 
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

