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
 * 说明：主键产生类
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
	 * 从种子表中获得自增一主键
	 * @param keyName - 数据库表名
	 * @return 新的主键
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
	 * 获得自加一的定长字符串
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