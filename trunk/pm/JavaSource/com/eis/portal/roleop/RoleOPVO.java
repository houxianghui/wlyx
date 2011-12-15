/*********************************************************
 * File: RoleOPVO.java
 * 
 * Version 1.0
 * 
 * Date     2005-9-24
 * 
 * Author   Chen Rong
 * 
 ********************************************************/

package com.eis.portal.roleop;

import com.eis.base.BaseVO;

/**
 * ˵������ɫ����Ȩ�޵����ݶ���
 * 
 */
public class RoleOPVO extends BaseVO {

	/**
	 * ���캯��
	 */
	public RoleOPVO() {
		super();
		
	}

	private String role_id	;	//��ɫ����
	
	private String caption	;	//��������
	
	private String op_code;	//Ȩ����	
			


	/********   get ���� *******/

	/**
	 * @return ���ؽ�ɫ����
	 */
	public String getRole_id() {
		return role_id;
	}

	/**
	 * @return ���ز�������
	 */
	public String getCaption() {
		return caption;
	}

	/**
	 * @return ����Ȩ����
	 */
	public String getOp_code() {
		return op_code;
	}		
	
			
	
	/********   set ���� *******/	
	

	/**
	 * @param ���ý�ɫ����
	 */
	public void setRole_id(String str) {
		role_id = str;
	}
		
	/**
	 * @param ���ò�������
	 */
	public void setCaption(String str) {
		caption = str;
	}

	/**
	 * @param ����Ȩ����
	 */
	public void setOp_code(String str) {
		op_code = str;
	}	
	

}

