/*********************************************************
 * File: MenuVO.java
 * 
 * Version 1.0
 * 
 * Date     2005-9-14
 * 
 * Author   �� ��
 * 
 ********************************************************/

package com.eis.portal.menu;

import com.eis.base.BaseVO;

/**
 * ˵�����Ż� �˵���������ݶ���
 * 
 */
public class MenuVO extends BaseVO {

	/**
	 * ���캯��
	 */
	public MenuVO() {
		super();
		
	}

	/**
	 * �˵����
	 */
	private String menu_id;
	
	/**
	 * �ϼ��˵�
	 */

	private String parent_id;
	
	/**
	 * �ϼ��˵�����
	 */
	private String parent_name;
	
	/**
	 * �˵�����
	 */
	private String menu_name;

	/**
	 * Ҷ�ӽڵ��־
	 */
	private String menu_mark;

	/**
	 * �˵�����
	 */
	private int menu_level;

	/**
	 * ��ʾ˳��
	 */
	private int list_order;
	

	/**
	 * �˵�URL
	 */
	private String menu_url;
	
	
   /******** get���� *********/

	/**
	 * @return ��ȡ�ϼ��˵�id 
	 */
	public String getMenu_id() {
		return menu_id;
	}

	/**
	 * @return ��ȡ�ϼ��˵�id 
	 */
	public String getParent_id() {
		return parent_id;
	}
	
	/**
	 * @return ��ȡ�ϼ��˵�����
	 */
	public String getParent_name() {
		return parent_name;
	}

	/**
	 * @return ��ȡ�˵�����
	 */
	public String getMenu_name() {
		return menu_name;
	}

	/**
	 * @return
	 */
	public String getMenu_mark() {
		return menu_mark;
	}

	/**
	 * @return ��ȡ�ϼ��˵�����
	 */
	public int getMenu_level() {
		return menu_level;
	}

	/**
	 * @return ��ȡ�˵���ʾ˳��
	 */
	public int getList_order() {
		return list_order;
	}
	

	

	/**
	 * @return ��ȡ�˵�URL
	 */
	public String getMenu_url() {
		return menu_url;
	}
	
	
	
	/******** set���� *********/
	
	

	/**
	 * @param string
	 */
	public void setMenu_id(String str) {
		menu_id = str;
	}

	/**
	 * @param string �����ϼ��˵� id
	 */
	public void setParent_id(String str) {
		parent_id = str;
	}
	
	/**
	 * @param string �����ϼ��˵�����
	 */
	public void setParent_name(String str) {
		parent_name = str;
	}

	/**
	 * @param string ���ò˵�����
	 */
	public void setMenu_name(String str) {
		menu_name = str;
	}

	/**
	 * @param string
	 */
	public void setMenu_mark(String str) {
		menu_mark = str;
	}

	/**
	 * @param int ���ò˵�����
	 */
	public void setMenu_level(int i) {
		menu_level = i;
	}

	/**
	 * @param int ���ò˵���ʾ˳��
	 */
	public void setList_order(int i) {
		list_order = i;
	}
	


	/**
	 * @param string ���ò˵�URL
	 */
	public void setMenu_url(String str) {
		menu_url = str;
	}



}
