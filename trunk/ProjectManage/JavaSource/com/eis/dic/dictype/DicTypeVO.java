 
package com.eis.dic.dictype; 
 
import com.eis.base.BaseVO; 
 
/** 
 * ˵�����ֵ������Ϣ�����ݶ��� 
 */ 
public class DicTypeVO extends BaseVO { 
 
	/** 
	 * ���캯�� 
	 */ 
	public DicTypeVO() { 
		super(); 
	} 
 
	/** 
	 * ������ 
	 */ 
	private String type_id; 
 
	/** 
	 * ���� 
	 */ 
	private String type_name; 
 
	/** 
	 * �����־ 
	 */ 
	private String dic_level; 
 
	/** 
	 *  ��ù����� 
	 */ 
	public String getType_id() {  
		return type_id; 
	} 
 
	/** 
	 *  ������� 
	 */ 
	public String getType_name() {  
		return type_name; 
	} 
 
	/** 
	 *  ��ü����־ 
	 */ 
	public String getDic_level() {  
		return dic_level; 
	} 
 
	/** 
	 *  ���ù����� 
	 */ 
	public void  setType_id(String val) {  
		type_id = val; 
	} 
 
	/** 
	 *  �������� 
	 */ 
	public void  setType_name(String val) {  
		type_name = val; 
	} 
 
	/** 
	 *  ���ü����־ 
	 */ 
	public void  setDic_level(String val) {  
		dic_level = val; 
	} 
 
} 

