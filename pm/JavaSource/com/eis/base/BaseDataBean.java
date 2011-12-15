/*********************************************************
 * File:DataBean.java
 * 
 * @version 1.0
 * 
 * Date     2004-6-1
 * @author   辛勇
 * 
 * Copyright (C) 2005 huateng
 * all rights reserved.
 * 
 ********************************************************/

package com.eis.base;

import java.util.*;


/**
 * 说明：简单数据容器类，用于保存数据集合
 * 
 */

public abstract class BaseDataBean extends BaseVO implements java.io.Serializable {

	/**
	 * 构造函数。
	 */
	public BaseDataBean() {
		list = new ArrayList();
	}
	
	/**
	 * 对象列表
	 */
	protected java.util.List list = null;

	/**
	 * 将值加入到列表中。
	 * @param bean　加入到列表中的数据对象。
	 */
	public void add(Object bean) {		
		if (list == null)
					list = new ArrayList();	
		list.add(bean);
	}

	/**
	 * 列表中数据个数。
	 */
	public int size() {
		if (list == null)
			return 0;
		else
			return list.size();
	}

	/**
	 * 判断指定对象是否在列表中
	 * @param obj 查找对象
	 * @return boolean true, 存在，false, 不存在
	 */
	public boolean contains(Object bean) {
		if (null == list)
			return false;
		return list.contains(bean);

	}

	/**
	 * 从列表中删除指定的对象。
	 * @param bean　要删除的对象。
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
	 * 将当前对象值加入到上级对象的列表中。
	 * @param bean　上级对象。
	 */
	public void addtoParent(BaseDataBean bean) {		
		bean.add(this);
	}

}
