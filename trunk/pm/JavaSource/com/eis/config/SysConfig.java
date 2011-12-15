/*********************************************************
 * File: SysConfig.java
 * 
 * Version 1.0
 * 
 * Date     2005-10-11
 * 
 * Author   xin yong
 * 
 ********************************************************/

package com.eis.config;

import java.util.*;

import com.eis.util.*;
import resource.*;

/**
 * ˵����ϵͳ���ò�����
 * 
 */
public class SysConfig {

	private static Properties sysConfig;
	
	private static boolean init = false;

	static {
		loadSysConfig();
	}
	/**
	 * װ��ϵͳ���������ļ�
	 * @param fileName - ��ǰ·���µ���Դ�ļ���
	 * @return ��Դ�ļ���File����
	 */
	public static void loadSysConfig() {

		try {
			sysConfig = new Properties();
			sysConfig.load(ResourceFile.getResource("SysConfig.properties"));			
			init = true;

		} catch (Exception ex) {
			SysLog.error("��ȡϵͳ���ò�����Ϣʧ�ܣ�");
			SysLog.error(ex.getMessage());
		}
	}

	/**
	 * ��ȡָ����ϵͳ���ò���ֵ
	 * @param key - ��������
	 * @return ����ֵ
	 */
	public static String getProperty(String key) {
		if(!init) {
			loadSysConfig();			
		}

		return sysConfig.getProperty(key);
	}

}
