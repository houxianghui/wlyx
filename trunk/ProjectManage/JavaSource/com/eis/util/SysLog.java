
/*********************************************************
 * File: SysLog.java
 * 
 * Version 1.0
 * 
 * Date     2005-8-31
 * 
 * Author   xin yong
 * 
 ********************************************************/

package com.eis.util;

import org.apache.log4j.*;
import java.util.*;

import resource.ResourceFile;

/**
 * 说明：系统日志工具类，实现向文件中输出程序日志
 * 
 */
public class SysLog {

	private static Logger log;

	static {
		try {
			Properties prop = new Properties();
			prop.load(ResourceFile.getResource("log4j.properties"));

			PropertyConfigurator.configure(prop);
			log = Logger.getRootLogger();

		} catch (Exception ex) {
			System.out.println("读取日志配置信息失败！");
			ex.printStackTrace();
			
		}

	}

	public static void debug(String info) {
		log.debug(info);
	}

	public static void info(String info) {
		log.info(info);
	}

	public static void warn(String info) {
		log.warn(info);
	}
	public static void error(String info) {
		log.error(info);

	}
	
	public static void error(String info, Throwable cause)
	{
		log.error(info, cause);
	}
	
	public static Logger getLogger() {
		return log;
	}

}
