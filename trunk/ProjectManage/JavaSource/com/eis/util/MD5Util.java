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
 * 说明：
 * 
 */
public class MD5Util {
	
	/**
	 * 进行MD5加密，返回摘要密文
	 * @param str － 源字符串
	 * @return 摘要密文
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
			
			SysLog.error("非法摘要算法!");
			SysLog.error(ex.getMessage());
			
			BaseException e = new BaseException();
			e.setErrorCode("E010040");
			throw e;
		}

		return digest;

	}

	/**
	 * 将二行制转换成字符串
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
