
 
/*********************************************************
 * File:BaseException.java
 * 
 * @version 1.0
 * 
 * Date     2005-8-31
 * @author   xin yong
 * 
 * Copyright (C) 2005 huateng
 * all rights reserved.
 * 
 ********************************************************/
package com.eis.base;

/**
 * ˵����ͨ���쳣�࣬���ڹ淶������Ϣ��ʾ
 * 
 */
public class BaseException extends Exception {
	
	public BaseException() {
		super();
		
	}
	
	public BaseException(String msg) {
		super(msg);
		
	}
	
	/**
	 * ������
	 */
	private String errorCode;	

	/**
	 * ��ô�����
	 * @return String ������
	 */
	public String getErrorCode() {
		return errorCode;
	}

	/**
	 * ���ô�����
	 * @param string ������
	 */
	public void setErrorCode(String string) {
		errorCode = string;
	}

}
