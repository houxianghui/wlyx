 
package com.eis.dic.redefsdic; 
 
import com.eis.base.BaseVO; 
 
/** 
 * ˵�����Զ��嵥���ֵ�����ݶ��� 
 */ 
public class ReDefSDicVO extends BaseVO { 
 
	/** 
	 * ���캯�� 
	 */ 
	public ReDefSDicVO() { 
		super(); 
	} 
 
	/** 
	 * ������ 
	 */ 
	private String type_id; 
 
	/** 
	 * �ֵ����� 
	 */ 
	private String caption; 
 
	/** 
	 * ���ݲ�ѯ��� 
	 */ 
	private String stmt; 
 
	/** 
	 * ��ע 
	 */ 
	private String memo; 
 
	/** 
	 * ������Ա 
	 */ 
	private String user_id; 
 
	/** 
	 * �������� 
	 */ 
	private String reg_dt; 
 
	/** 
	 *  ��ù����� 
	 */ 
	public String getType_id() {  
		return type_id; 
	} 
 
	/** 
	 *  ����ֵ����� 
	 */ 
	public String getCaption() {  
		return caption; 
	} 
 
	/** 
	 *  ������ݲ�ѯ��� 
	 */ 
	public String getStmt() {  
		return stmt; 
	} 
 
	/** 
	 *  ��ñ�ע 
	 */ 
	public String getMemo() {  
		return memo; 
	} 
 
	/** 
	 *  ��ø�����Ա 
	 */ 
	public String getUser_id() {  
		return user_id; 
	} 
 
	/** 
	 *  ��ø������� 
	 */ 
	public String getReg_dt() {  
		return reg_dt; 
	} 
 
	/** 
	 *  ���ù����� 
	 */ 
	public void  setType_id(String val) {  
		type_id = val; 
	} 
 
	/** 
	 *  �����ֵ����� 
	 */ 
	public void  setCaption(String val) {  
		caption = val; 
	} 
 
	/** 
	 *  �������ݲ�ѯ��� 
	 */ 
	public void  setStmt(String val) {  
		stmt = val; 
	} 
 
	/** 
	 *  ���ñ�ע 
	 */ 
	public void  setMemo(String val) {  
		memo = val; 
	} 
 
	/** 
	 *  ���ø�����Ա 
	 */ 
	public void  setUser_id(String val) {  
		user_id = val; 
	} 
 
	/** 
	 *  ���ø������� 
	 */ 
	public void  setReg_dt(String val) {  
		reg_dt = val; 
	} 
 
} 

