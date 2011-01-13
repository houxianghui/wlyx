/*********************************************************
 * File: SelectMenuVO.java
 * 
 * Version 1.0
 * 
 * Date     2005-9-22
 * 
 * Author   Chen Rong
 * 
 ********************************************************/

package com.eis.portal.rolemenu;

import com.eis.base.BaseVO;

/**
 * ˵������ɫѡ��˵������ݶ���
 * 
 */
public class SelectMenuVO extends BaseVO {

	/**
	 * ���캯��
	 */
	public SelectMenuVO() {
		super();
		
	}

	private String menu_id;	//�˵����
	
	private String menu_name;	//�˵�����
	
	private int menu_level;	//�˵�����
	
	private int check;	//�˵���ѡ��־��1Ϊ��ѡ��0Ϊ��ѡ	
	
	private int check_display;	//�Ƿ���ʾ�˵���ѡ���־��1Ϊ��ʾ��0Ϊ����ʾ		


	/********   get ���� *******/


	/**
	 * @return ���ز˵����
	 */
	public String getMenu_id() {
		return menu_id;
	}

	/**
	 * @return ���ز˵�����
	 */
	public String getMenu_name() {
		return menu_name;
	}
		
	/**
	 * @return ���ز˵�����
	 */
	public int getMenu_level() {
		return menu_level;
	}	
	
	/**
	 * @return ���ع�ѡ����־
	 */
	public int getCheck() {
		return check;
	}
	
	/**
	 * @return �����Ƿ���ʾ��ѡ���־
	 */
	public int getCheck_display() {
		return check_display;
	}
			
	
	/********   set ���� *******/	
	

	/**
	 * @param string
	 */
	public void setMenu_id(String str) {
		menu_id = str;
	}

	/**
	 * @param string
	 */
	public void setMenu_name(String str) {
		menu_name = str;
	}
	
	/**
	 * @param int
	 */
	public void setMenu_level(int i) {
		menu_level = i;
	}
	
	/**
	 * @param string
	 */
	public void setCheck(int i) {
		check = i;
	}
	
	/**
	 * @param string
	 */
	public void setCheck_display(int i) {
		check_display = i;
	}	


}
