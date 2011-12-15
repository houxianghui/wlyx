/*********************************************************
 * File: BeanContainer.java
 * 
 * Version 1.0
 * 
 * Date     2005-9-6
 * 
 * Author   xin yong
 * 
 ********************************************************/

package com.eis.factory;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.*;



import com.eis.base.BaseException;
import com.eis.util.SysLog;

/**
 * 说明：组件容器类
 * 
 */
public class BeanFactory {
	
	
	private static ApplicationContext factory = null;	
	

	/**
	 * 通过spring获得bean实例
	 * @param name - bean ID
	 * @return bean实例
	 */
	public static Object getBean(String name) throws Exception {
		try {
			if (factory == null) {
				
				factory = new  ClassPathXmlApplicationContext("resource/bean-config.xml");			

				SysLog.info("组件容器初始化成功！");
			}
			
			return factory.getBean(name);

		} catch (BeansException ex) {
			SysLog.info("组件容器初始化成功！");
			SysLog.error(ex.getMessage());

			BaseException e = new BaseException();
			e.setErrorCode("E010039");
			throw e;
		}

	}

}
