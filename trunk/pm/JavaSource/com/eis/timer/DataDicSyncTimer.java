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
 * ˵���������ֵ䶨ʱͬ����ʱ���࣬ͬ������Ϊÿ��һ��
 * 
 */
public class DataDicSyncTimer extends TimerTask {

	//���һ��ִ������ͬ��������
	private String lastDate = null;

	//����ͬ��ʱ�䣬Сʱ
	private int syncTime =
		Integer.parseInt(SysConfig.getProperty("dataDicSyncTime"));

	public void run() {
		boolean noneed = true;
		if(noneed){
			return;
		}

		SysLog.debug("��ʱ�����񱻴���");
		
		//�����Զ��塢����Ȩ���ֵ�		
		ReDefSDicMap.reloadDicMap();
		OpMap.reloadDicMap();
		
		

		//ȡ�õ�ǰ����
		String currentDate = DateUtil.getDTStr();

		int currentTime = new java.util.Date().getHours();

		if (lastDate == null || !lastDate.equals(currentDate)) { //����δִ������

			//�ж�ʱ���Ƿ񳬹�ָ��ʱ���Ժ�
			if (currentTime >= syncTime) {

				SysLog.info("��ʼ�ֵ�����ͬ��");

				SingleDicMap.reloadDicMap();				
				MLDicMap.reloadDicMap();
				RedefMDicMap.loadDicMap();
				ErrorCodeMap.reloadDicMap();
				Portal.init();

				//��¼��ǰʱ��
				lastDate = currentDate;

				SysLog.info("�����ֵ�ͬ������");

			}

		}

	}

}
