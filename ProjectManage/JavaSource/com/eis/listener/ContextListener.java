/*********************************************************
 * File: ContextListener.java
 * 
 * Version 1.0
 * 
 * Date     2005-10-26
 * 
 * Author   xin yong
 * 
 ********************************************************/

package com.eis.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.eis.util.*;
import com.eis.timer.*;


/**
 * ˵����
 * 
 */
public class ContextListener implements ServletContextListener {

	private java.util.Timer timer = null;

	public void contextInitialized(ServletContextEvent event) {
		
		//���ض�ʱ��,����ΪСʱ����һ��
		timer = new java.util.Timer(true);
		
		SysLog.info("��ʱ��������");		
		timer.schedule(new DataDicSyncTimer(), 0, 60 * 60 * 1000);
		SysLog.info("�Ѿ��������ͬ����ʱ����");
		

	}

	public void contextDestroyed(ServletContextEvent event) {
		//���ٶ�ʱ��
		timer.cancel();
		SysLog.info("��ʱ��������");
		
	}

}
