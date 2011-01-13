/*********************************************************
 * File: MessageException.java
 * 
 * Version 1.0
 * 
 * Date     2005-11-4
 * 
 * Author   xin yong
 * 
 ********************************************************/

package com.eis.exception;

import com.eis.base.BaseException;

/**
 * 说明：文本信息异常类
 * 
 */
public class MessageException extends BaseException{
	
	public  MessageException(String msg) {
		super(msg);		
	}
	
}
