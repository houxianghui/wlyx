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
	public boolean startTrain(User user){
		Portal.setUserInfo(user);
		if(user.isShouldKillMonstor()){
			logger.info(user.getRoleName()+"需要挂野，暂不进行训练");
			return true;
		}
		int hourOnce = 1;
		if(need10HoursTrain()){
			hourOnce = 10;
		}
		String url = user.getUrl()+WARRIOR_URL+hourOnce+Tools.getTimeStamp(true);
		String page = PageService.getPageWithCookie(url, user);
		if(Tools.success(page)){
			logger.info(user.getRoleName()+"开始"+hourOnce+"小时训练成功");
			return true;
		}else{
			if(user.getStatus().equals("训练中")){
				return false;
			}
		}
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
