/*********************************************************
 * File: OPForm.java
 * 
 * Version 1.0
 * 
 * Date     2005-9-20
 * 
 * Author   �� ��
 * 
 ********************************************************/

package com.eis.portal.op;

import com.eis.base.BaseForm;

/**
 * ˵����
 * 
 */
public class OPForm extends BaseForm {

	/**
	 * ���캯��
	 */
	public OPForm() {
		super();
		
	}
	
	/**
	* ���ܲ˵����
	*/
	private String menu_id;
	
	/**
	 * ���ܲ˵�����
	 */
	private String menu_name;
	
	/**
	* Ȩ����
	*/

	private String op_code;
	
	/**
	* ��������
	*/
	private String caption;
	
	
	
	/******** get���� *********/

	 /**
	  * @return ���ع��ܲ˵����
	  */
	 public String getMenu_id() {
		 return menu_id;
	 }
	 
	/**
	 * @return ���ع��ܲ˵�����
	 */
	public String getMenu_name() {
		return menu_name;
	}

	 /**
	  * @return ����Ȩ����
	  */
	 public String getOp_code() {
		 return op_code;
	 }

	 /**
	  * @return ���ز�������
	  */
	 public String getCaption() {
		 return caption;
	 }	
	
	
	
	 /******** set���� *********/
	
	

	 /**
	  * @param ���ù��ܲ˵����
	  */
	 public void setMenu_id(String str) {
		 menu_id = str;
	 }
	 
	/**
	 * @param ���ù��ܲ˵�����
	 */
	public void setMenu_name(String str) {
		menu_name = str;
	}

	 /**
	  * @param ����Ȩ����
	  */
	 public void setOp_code(String str) {
		 op_code = str;
	 }

	 /**
	  * @param ���ò�������
	  */
	 public void setCaption(String str) {
		 caption = str;
	 }



}
