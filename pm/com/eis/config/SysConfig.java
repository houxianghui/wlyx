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
 * 说明：系统配置参数类
 * 
 */
public class SysConfig {

	private static Properties sysConfig;
	
	private static boolean init = false;

	static {
		loadSysConfig();
	}
	/**
	 * 装载系统参数配置文件
	 * @param fileName - 当前路径下的资源文件名
	 * @return 资源文件的File对象
	 */
	public static void loadSysConfig() {

		try {
			sysConfig = new Properties();
			sysConfig.load(ResourceFile.getResource("SysConfig.properties"));			
			init = true;

		} catch (Exception ex) {
			SysLog.error("读取系统配置参数信息失败！");
			SysLog.error(ex.getMessage());
		}
	}

	/**
	 * 获取指定的系统配置参数值
	 * @param key - 参数主键
	 * @return 参数值
	 */
	public static String getProperty(String key) {
		if(!init) {
			loadSysConfig();			
		}

		return sysConfig.getProperty(key);
	}

}
