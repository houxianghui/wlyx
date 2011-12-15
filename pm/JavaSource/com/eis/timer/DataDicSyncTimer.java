/*********************************************************
 * File: DataDicSyncTimer.java
 * 
 * Version 1.0
 * 
 * Date     2005-10-26
 * 
 * Author   xin yong
 * 
 ********************************************************/

package com.eis.timer;

import java.util.TimerTask;

import com.eis.cache.*;
import com.eis.config.*;
import com.eis.util.*;
import com.eis.portal.*;

/**
 * 说明：数据字典定时同步计时器类，同步周期为每天一次
 * 
 */
public class DataDicSyncTimer extends TimerTask {

	//最后一次执行数据同步的日期
	private String lastDate = null;

	//数据同步时间，小时
	private int syncTime =
		Integer.parseInt(SysConfig.getProperty("dataDicSyncTime"));

	public void run() {
		boolean noneed = true;
		if(noneed){
			return;
		}

		SysLog.debug("定时器任务被触发");
		
		//更新自定义、操作权限字典		
		ReDefSDicMap.reloadDicMap();
		OpMap.reloadDicMap();
		
		

		//取得当前日期
		String currentDate = DateUtil.getDTStr();

		int currentTime = new java.util.Date().getHours();

		if (lastDate == null || !lastDate.equals(currentDate)) { //当天未执行任务

			//判断时间是否超过指定时间以后
			if (currentTime >= syncTime) {

				SysLog.info("开始字典数据同步");

				SingleDicMap.reloadDicMap();				
				MLDicMap.reloadDicMap();
				RedefMDicMap.loadDicMap();
				ErrorCodeMap.reloadDicMap();
				Portal.init();

				//纪录当前时间
				lastDate = currentDate;

				SysLog.info("数据字典同步结束");

			}

		}

	}

}
