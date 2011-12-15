/*********************************************************
 * File:BaseVO.java
 * 
 * Version 1.0
 * 
 * Date     2005-8-1
 * Author   辛勇
 * 
 * Copyright (C) 2005 huateng.
 * all rights reserved.
 * 
 ********************************************************/

package com.eis.base;

import com.abc.logic.QueryObject;

/**
 * 说明：所有Data Transfer Object的父类
 * 
 */

public abstract class BaseVO implements java.io.Serializable,BaseVOInterface ,QueryObject{
    protected int isCount;
	protected int queryCount;
	/**
	 * 构造函数
	 */
	public BaseVO() {
				
	}
	/**
	 * @return
	 */
	public int getQueryCount() {
		return queryCount;
	}

	/**
	 * @param i
	 */
	public void setQueryCount(int i) {
		queryCount = i;
	}
	public int getIsCount() {
		return isCount;
	}
	public void setIsCount(int isCount) {
		this.isCount = isCount;
	}

}
