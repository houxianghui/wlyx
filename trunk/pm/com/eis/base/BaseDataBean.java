/*********************************************************
 * File:DataBean.java
 * 
 * @version 1.0
 * 
 * Date     2004-6-1
 * @author   ����
 * 
 * Copyright (C) 2005 huateng
 * all rights reserved.
 * 
 ********************************************************/

package com.eis.base;

import java.util.*;


/**
 * ˵���������������࣬���ڱ������ݼ���
 * 
 */

public abstract class BaseDataBean extends BaseVO implements java.io.Serializable {

	/**
	 * ���캯����
	 */
	public BaseDataBean() {
		list = new ArrayList();
	}
	
	/**
	 * �����б�
	 */
	protected java.util.List list = null;

	/**
	 * ��ֵ���뵽�б��С�
	 * @param bean�����뵽�б��е����ݶ���
	 */
	public void add(Object bean) {		
		if (list == null)
					list = new ArrayList();	
		list.add(bean);
	}

	/**
	 * �б������ݸ�����
	 */
	public int size() {
		if (list == null)
			return 0;
		else
			return list.size();
	}

	/**
	 * �ж�ָ�������Ƿ����б���
	 * @param obj ���Ҷ���
	 * @return boolean true, ���ڣ�false, ������
	 */
	public boolean contains(Object bean) {
		if (null == list)
			return false;
		return list.contains(bean);

	}

	/**
	 * ���б���ɾ��ָ���Ķ���
	 * @param bean��Ҫɾ���Ķ���
	 */
	public boolean remove(Object bean) {
		if (list == null)
			return false;

		return list.remove(bean);
	}
	
	/**
	 * iterator
	 */
	public java.util.Collection iter() {
		return null;//list.iterator();
	}


	/**
	 * ����ǰ����ֵ���뵽�ϼ�������б��С�
	 * @param bean���ϼ�����
	 */
	public void addtoParent(BaseDataBean bean) {		
		bean.add(this);
	}

}
