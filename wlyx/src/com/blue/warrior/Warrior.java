package com.blue.warrior;

import java.util.Calendar;

import org.apache.log4j.Logger;

import com.blue.common.Portal;
import com.blue.common.User;
import com.blue.tools.PageService;
import com.blue.tools.Tools;

public class Warrior {
	Logger logger = Logger.getLogger(this.getClass());
	public static final String WARRIOR_URL="modules/warrior.php?act=hall&op=train&hours=";
	//http://s4.verycd.9wee.com/modules/warrior.php?act=hall&op=work&hours=1&timeStamp=1280372681656
	//���������ͭ���
	public static final String WORK = "modules/warrior.php?act=hall&op=work&hours=";
	public boolean startTrain(User user){
		Portal.setUserInfo(user);
		if(user.isShouldKillMonstor()){
			logger.info(user.getRoleName()+"��Ҫ��Ұ���ݲ�����ѵ��");
			return true;
		}
		int hourOnce = 1;
		if(need10HoursTrain()){
			hourOnce = 10;
		}
		String url = user.getUrl()+WARRIOR_URL+hourOnce+Tools.getTimeStamp(true);
		String page = PageService.getPageWithCookie(url, user);
		if(Tools.success(page)){
			logger.info(user.getRoleName()+"��ʼ"+hourOnce+"Сʱѵ���ɹ�");
			return true;
		}else{
			if(user.getStatus().equals("ѵ����")||user.getStatus().equals("������")){
				return false;
			}
		}
		return false;
	}
	public boolean startWork(User user){
		Portal.setUserInfo(user);
		String data = "callback_func_name=work_callback";
		if(user.isShouldKillMonstor()){
			logger.info(user.getRoleName()+"��Ҫ��Ұ���ݲ���������");
			return true;
		}
		int hourOnce = 1;
		if(need10HoursTrain()){
			hourOnce = 10;
		}
		String url = user.getUrl()+WORK+hourOnce+Tools.getTimeStamp(true);
		
		try{
			String page = PageService.postPage(url, data, user);
		
			if(Tools.success(page)){
				logger.info(user.getRoleName()+"��ʼ"+hourOnce+"Сʱ���ճɹ�");
				return true;
			}else{
				if(user.getStatus().equals("ѵ����") || user.getStatus().equals("������")){
					return false;
				}
			}
			
		}catch(Exception e){};
		return false;
	}
	public boolean need10HoursTrain(){
		Calendar c = Calendar.getInstance();
		int day = c.get(Calendar.DAY_OF_WEEK);
		int hour = c.get(Calendar.HOUR_OF_DAY);
		int minute = c.get(Calendar.MINUTE);
		if (day == Calendar.TUESDAY){
			if(hour >= 11 && minute>=30){
				return true;
			}
		}else if(day == Calendar.WEDNESDAY){
			if(hour >0 && hour < 8){
				return true;
			}
		}
		return false;
	}
}
