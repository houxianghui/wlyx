 
package com.eis.dic.mdic; 
 
import com.eis.base.BaseVO; 
 
/** 
 * ˵�����༶�ֵ�����ݶ��� 
 */ 
public class MDicVO extends BaseVO { 
 
	/** 
	 * ���캯�� 
	 */ 
	public MDicVO() { 
		super(); 
	} 
 
	/** 
	 * ϵͳ���� 
	 */ 
	private long sys_id; 
 
	/** 
	 * ������ 
	 */ 
	private String type_id; 
 
	/** 
	 * �ϼ����� 
	 */ 
	private long parent_id; 
 
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
	private short list_order; 
 
	/** 
	 * ���� 
	 */ 
	private short item_level; 
 
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
	public long getSys_id() {  
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
	public long getParent_id() {  
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
	public short getList_order() {  
		return list_order; 
	} 
 
	/** 
	 *  ��ü��� 
	 */ 
	public short getItem_level() {  
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
	public void  setSys_id(long val) {  
		sys_id = val; 
	} 
 
	/** 
	 *  ���ù����� 
	 */ 
	public void  setType_id(String val) {  
		type_id = val; 
	} 
 
	/** 
	 *  �����ϼ����� 
	 */ 
	public void  setParent_id(long val) {  
		parent_id = val; 
	} 
 
	/** 
	 *  ����ѡ����� 
	 */ 
	public void  setItem_id(String val) {  
		item_id = val; 
	} 
 
	/** 
	 *  ����ѡ��ֵ 
	 */ 
	public void  setItem_val(String val) {  
		item_val = val; 
	} 
 
	/** 
	 *  �������˳�� 
	 */ 
	public void  setList_order(short val) {  
		list_order = val; 
	} 
 
	/** 
	 *  ���ü��� 
	 */ 
	public void  setItem_level(short val) {  
		item_level = val; 
	} 
 
	/** 
	 *  �����߼����� 
	 */ 
	public void  setLogic_id(String val) {  
		logic_id = val; 
	} 
 
	/** 
	 *  ����status 
	 */ 
	public void  setStatus(String val) {  
		status = val; 
	} 
 
} 

