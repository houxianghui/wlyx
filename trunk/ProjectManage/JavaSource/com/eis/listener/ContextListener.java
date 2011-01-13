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
 * 说明：
 * 
 */
public class ContextListener implements ServletContextListener {

	private java.util.Timer timer = null;

	public void contextInitialized(ServletContextEvent event) {
		
		//加载定时器,周期为小时调度一次
		timer = new java.util.Timer(true);
		
		SysLog.info("定时器已启动");		
		timer.schedule(new DataDicSyncTimer(), 0, 60 * 60 * 1000);
		SysLog.info("已经添加数据同步定时任务");
		

	}

	public void contextDestroyed(ServletContextEvent event) {
		//销毁定时器
		timer.cancel();
		SysLog.info("定时器已销毁");
		
	}

}
