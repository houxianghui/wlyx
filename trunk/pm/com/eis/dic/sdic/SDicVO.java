 
package com.eis.dic.sdic; 
 
import com.eis.base.BaseVO; 
 
/** 
 * ˵���������ֵ�����ݶ��� 
 */ 
public class SDicVO extends BaseVO { 
 
	/** 
	 * ���캯�� 
	 */ 
	public SDicVO() { 
		super(); 
	} 
 
	/** 
	 * ϵͳ��� 
	 */ 
	private long sys_id; 
 
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
	private short list_order; 
 
	/** 
	 * �߼����� 
	 */ 
	private String logic_id; 
 
	/** 
	 * ״̬ 
	 */ 
	private String status; 
 
	/** 
	 *  ���ϵͳ��� 
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
	public short getList_order() {  
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
 
	/** 
	 *  ����ϵͳ��� 
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
	 *  ����ѡ����� 
	 */ 
	public void  setItem_code(String val) {  
		item_code = val; 
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
	 *  �����߼����� 
	 */ 
	public void  setLogic_id(String val) {  
		logic_id = val; 
	} 
 
	/** 
	 *  ����״̬ 
	 */ 
	public void  setStatus(String val) {  
		status = val; 
	} 
 
} 

