
 
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
 * 说明：通用异常类，用于规范错误信息提示
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
	 * 错误码
	 */
	private String errorCode;	

	/**
	 * 获得错误码
	 * @return String 错误码
	 */
	public String getErrorCode() {
		return errorCode;
	}

	/**
	 * 设置错误码
	 * @param string 错误码
	 */
	public void setErrorCode(String string) {
		errorCode = string;
	}

}
