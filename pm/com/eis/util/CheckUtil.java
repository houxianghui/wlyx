/*
 * @(#) CheckUtil.java V(1.0) 2007-4-18 houxh
 * 
 * Copyright  (c)  2007 	Huateng. All Right Reserver. 
 */
package com.eis.util;

public class CheckUtil {

	
	/**
	 * @author houxh 2007-12-21
	 * ���������ַ����Ƿ�Ϊ��
	 * 
	 * @param s
	 * @return 
	 */
	public static boolean isEmptry(String s){
		if(s == null || s.trim().length() == 0){
			return true;
		}
		return false;
	}	
} 
