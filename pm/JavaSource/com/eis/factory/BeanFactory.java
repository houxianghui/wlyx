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
 * ˵�������������
 * 
 */
public class BeanFactory {
	
	
	private static ApplicationContext factory = null;	
	

	/**
	 * ͨ��spring���beanʵ��
	 * @param name - bean ID
	 * @return beanʵ��
	 */
	public static Object getBean(String name) throws Exception {
		try {
			if (factory == null) {
				
				factory = new  ClassPathXmlApplicationContext("resource/bean-config.xml");			

				SysLog.info("���������ʼ���ɹ���");
			}
			
			return factory.getBean(name);

		} catch (BeansException ex) {
			SysLog.info("���������ʼ���ɹ���");
			SysLog.error(ex.getMessage());

			BaseException e = new BaseException();
			e.setErrorCode("E010039");
			throw e;
		}

	}

}
