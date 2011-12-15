/*********************************************************
 * File: MD5Util.java
 * 
 * Version 1.0
 * 
 * Date     2005-9-26
 * 
 * Author   xin yong
 * 
 ********************************************************/

package com.eis.util;

import java.security.*;

import com.eis.util.*;
import com.eis.base.*;
/**
 * ˵����
 * 
 */
public class MD5Util {
	
	/**
	 * ����MD5���ܣ�����ժҪ����
	 * @param str �� Դ�ַ���
	 * @return ժҪ����
	 * @throws Exception
	 */
	public static String digest(String str) throws Exception{
		String digest = null;
		try {

			MessageDigest alga = MessageDigest.getInstance("MD5");
			alga.update(str.getBytes());

			byte[] digestByte = alga.digest();
			digest = byte2hex(digestByte);

		
		} catch (java.security.NoSuchAlgorithmException ex) {
			
			SysLog.error("�Ƿ�ժҪ�㷨!");
			SysLog.error(ex.getMessage());
			
			BaseException e = new BaseException();
			e.setErrorCode("E010040");
			throw e;
		}

		return digest;

	}

	/**
	 * ��������ת�����ַ���
	 * @param b
	 * @return
	 */
	public static String byte2hex(byte[] b) {
		String hs = "";
		String stmp = null;
		for (int n = 0; n < b.length; n++) {
			stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1)
				hs = hs + "0" + stmp;
			else
				hs = hs + stmp;

		}

		return hs;

	}
	
	
}
