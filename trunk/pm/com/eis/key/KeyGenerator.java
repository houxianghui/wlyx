/*********************************************************
 * File: KeyGenerator.java
 * 
 * Version 1.0
 * 
 * Date     2005-9-13
 * 
 * Author   xin yong
 * 
 ********************************************************/

package com.eis.key;

import java.util.Date;
import java.util.HashMap;

import com.eis.util.*;

/**
 * ˵��������������
 * 
 */
public class KeyGenerator {

	private static HashMap map = new HashMap(20);

	private KeyGenerator() {

	}
	
	public static long getDateNextKey(String keyName) throws Exception {
		KeyInfo keyinfo = (KeyInfo) map.get(keyName);

		if (keyinfo == null) {
			keyinfo = new KeyInfo(keyName);
			map.put(keyName, keyinfo);
		}
		
		return keyinfo.getDateNextKey();
		
	}

	/**
	 * �����ӱ��л������һ����
	 * @param keyName - ���ݿ����
	 * @return �µ�����
	 * @throws Exception
	 */

	public static long getNextKey(String keyName) throws Exception {
		KeyInfo keyinfo = (KeyInfo) map.get(keyName);

		if (keyinfo == null) {
			keyinfo = new KeyInfo(keyName);
			map.put(keyName, keyinfo);
		}
		
		return keyinfo.getNextKey();
	}

	/**
	 * ����Լ�һ�Ķ����ַ���
	 * @param curKey
	 * @return
	 */
	public static String getNextKeyStr(String curKey, int length)
		throws Exception {

		if (null == curKey || curKey.trim().length() <= 0) {
			curKey = "0";
		}

		curKey = String.valueOf(Long.parseLong(curKey) + 1);

		return StringUtil.addZero(curKey, length);
	}

}