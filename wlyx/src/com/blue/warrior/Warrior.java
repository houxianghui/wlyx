package com.blue.warrior;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.blue.common.Portal;
import com.blue.common.User;
import com.blue.tools.PageService;
import com.blue.tools.Tools;

public class Warrior {
	Logger logger = Logger.getLogger(this.getClass());
	public static final String WARRIOR_URL="modules/warrior.php?act=hall&op=train&hours=";
	//http://s4.verycd.9wee.com/modules/warrior.php?act=hall&op=work&hours=1&timeStamp=1280372681656
	//�����������ͭ���
	public static final String WORK = "modules/warrior.php?act=hall&op=work&hours=";
	//���http://s4.verycd.9wee.com/modules/warrior.php?act=hall&op=war&rand=1280557860833&timeStamp=1280557849142&callback_func_name=ajaxCallback&callback_obj_name=dlg_train_work
	public static final String WAR = "modules/warrior.php?act=hall&op=war&callback_func_name=ajaxCallback&callback_obj_name=dlg_train_work";
	//http://s4.verycd.9wee.com/modules/warrior.php?act=hall&op=war&minters=60&timeStamp=1280557849142
	public static final String WAR_START = "modules/warrior.php?act=hall&op=war&minters=";
	
	private Pattern p = Pattern.compile("<option value=\"(\\d+)\">\\d+\\ ����</option>\\s+</select>",Pattern.DOTALL);
	private boolean canWar(User user){
		if(user.getNeedWar().equals("0")){
			return false;
		}
		Calendar c = Calendar.getInstance();
		int day = c.get(Calendar.DAY_OF_WEEK);
		if(day == Calendar.WEDNESDAY || day == Calendar.SATURDAY){
			int hour = c.get(Calendar.HOUR_OF_DAY);
			int minute = c.get(Calendar.MINUTE);
			if(hour >= 14){
				if(hour < 15){
					return true;
				}else if(hour == 15){
					if(minute <= 30){
						return true;
					}else{
						return false;
					}
				}
			}
		}
		return false;
	}
	private boolean canTrain(User user){
		Portal.setUserInfo(user);
		if(user.isShouldKillMonstor()){
			logger.info(user.getRoleName()+"��Ҫ��Ұ���ݲ����д���");
			return false;
		}
		if(user.getStatus().equals("ѵ����")||user.getStatus().equals("������")||user.getStatus().equals("������")){
			return false;
		}
		return true;
	}
	public boolean startWar(User user)throws Exception{
//		if(!canTrain(user)){
//			return true;
//		}
//		if(!canWar(user)){
//			return true;
//		}
		String url = user.getUrl()+WAR+Tools.getRandAndTime();
		String page = PageService.getPageWithCookie(url, user);
		Matcher m = p.matcher(page);
		if(m.find()){
			String data = "callback_func_name=war_callback";
			url = user.getUrl()+WAR_START+m.group(1)+Tools.getTimeStamp(true);
			page = PageService.postPage(url, data, user);
			if(Tools.success(page)){
				logger.info(user.getRoleName()+"��ʼ�ų����"+m.group(1)+"����");
				return true;
			}
		}
		return false;
		
	}
	public boolean startTrain(User user)throws Exception{
		if(!canTrain(user)){
			return true;
		}
		if(canWar(user)){
			if(!startWar(user)){
				logger.info(user.getRoleName()+"���ʧ�ܣ�ѡ������������ʽ");
			}else{
				return true;
			}
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
		}
		logger.info(user.getRoleName()+"�Ҵ���ʧ��");
		return false;
	}
	public boolean startWork(User user)throws Exception{
		if(!canTrain(user)){
			return true;
		}
		if(canWar(user)){
			if(!startWar(user)){
				logger.info(user.getRoleName()+"���ʧ�ܣ�ѡ������������ʽ");
			}else{
				return true;
			}
		}
		String data = "callback_func_name=work_callback";
		
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
			}
			
		}catch(Exception e){
			e.printStackTrace();
			logger.info(e.getMessage());
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