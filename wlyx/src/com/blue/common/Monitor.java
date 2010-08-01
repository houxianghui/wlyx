package com.blue.common;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.blue.tools.PageService;
import com.blue.tools.Tools;

public class Monitor {
	private static Logger logger = Logger.getLogger(Monitor.class);
	//http://s4.verycd.9wee.com/modules/scenes_role.php?sid=0&timeStamp=1279162121165&callback_func_name=switch_scene_callback
	public static final String RETURN_HOME="modules/scenes_role.php?sid=0&callback_func_name=switch_scene_callback";
	public static final String POSITION="modules/scene.php";
	//http://s4.verycd.9wee.com/modules/role_slavery.php?act=fawn_submit&rand=1280046928316&timeStamp=1280046903963
	public static final String SLAVY = "modules/role_slavery.php?act=fawn_submit";
	//http://s4.verycd.9wee.com/modules/refresh_team_scene_data.php?action=refresh&timeMark=1280047930&time=41&timeStamp=1280047781563&callback_func_name=fnInitTeamSceneData
	public static final String SCENE="";
	//http://s4.verycd.9wee.com/modules/role_slavery.php?timeStamp=1280199739437&callback_func_name=ajaxCallback&callback_obj_name=dlg_sociality
	public static final String SLAVY_MASTER = "modules/role_slavery.php?callback_func_name=ajaxCallback&callback_obj_name=dlg_sociality";
	//http://s4.verycd.9wee.com/modules/role_slavery.php?act=comfort_submit&rand=1280199942890&timeStamp=1280199919046
	public static final String COMFORT_SLAVY = "modules/role_slavery.php?act=comfort_submit";
	//http://s4.verycd.9wee.com/modules/role_slavery.php?act=pain_submit&rand=1280199951562&timeStamp=1280199943328
	public static final String PAIN_SLAVY = "modules/role_slavery.php?act=pain_submit";
	//http://s4.verycd.9wee.com/modules/role_slavery.php?timeStamp=1280242663691&callback_func_name=ajaxCallback&callback_obj_name=dlg_sociality
	public static final String GET_SLAVY_MASTER = "modules/role_slavery.php?callback_func_name=ajaxCallback&callback_obj_name=dlg_sociality";
	//http://s4.verycd.9wee.com/modules/day_weals_activity.php?act=weal&id=1rand=1280246976709&timeStamp=1280246968613&callback_func_name=ajaxCallback
	public static final String DAILY_WEALS = "modules/day_weals_activity.php?act=weal&callback_func_name=ajaxCallback&id=1";
	//渑池演武厅奖励
	//http://s4.verycd.9wee.com/modules/warrior.php?act=arena&timeStamp=1280322179334&callback_func_name=callback_load_content&callback_obj_name=content
	public static final String MIAN_CHI = "modules/warrior.php?act=arena&callback_func_name=callback_load_content&callback_obj_name=content";
	//http://s4.verycd.9wee.com/modules/warrior.php?act=arena&op=get_prise&arena_key=9_2_1280246400&team_mode=0&timeStamp=1280321831079
	public static final String MIAN_CHI_WEAL="modules/warrior.php?act=arena&op=get_prise";
	//荣誉换经验
	//http://s4.verycd.9wee.com/modules/duel.php?act=glory&op=buy&itemID=15&timeStamp=1280560866420&callback_func_name=callbackFnBusGloryReward
	public static final String GLORY_TO_JING_YAN = "modules/duel.php?act=glory&op=buy&itemID=15&callback_func_name=callbackFnBusGloryReward";
	//换紫玉
	//http://s4.verycd.9wee.com/modules/duel.php?act=glory&op=buy&itemID=62&itemNum=1&timeStamp=1280560866420
	public static final String GLORY_TO_ZI_YU = "modules/duel.php?act=glory&op=buy&itemID=62&itemNum=1";
	
	
	public static Pattern p = Pattern.compile("<div class=\"city_scene_name\">(\\S+?国|渑池)\\s*?</div>");
	private static Pattern wuGuan = Pattern.compile("武馆战打斗极其混乱");
	public static Pattern slavy = Pattern.compile("<a href=\"javascript:void\\(0\\);\" onclick=\"view_role \\( (\\d+) \\)\" title=\"(\\S+?)\">(\\S+?)</a>");
	public static Pattern slavyMaster = Pattern.compile("我的主人：<a href=\"javascript:void\\(0\\);\" onclick=\"view_role \\( (\\d+) \\)\">");
	public static Pattern mianChiWeal = Pattern.compile("<a href=\"javascript:void\\(0\\);\" onclick=\"arena_get_prise \\( '(\\S+?)', '(\\d+)' \\)\">领取</a>");
	public static boolean goHome(User user){
		String page = PageService.getPageWithCookie(RETURN_HOME, user);
		return Tools.success(page);
			
	}
	public static String getScenes(User user){
		String url = user.getUrl()+POSITION+Tools.getTimeStamp(false);
		return PageService.getPageWithCookie(url, user);
	}
	public static boolean atHome(User user){
		String page = getScenes(user);
		Matcher m = p.matcher(page);
		if(m.find()){
			if("渑池".equals(m.group(1))){
				return false;
			}else{
				return true;
			}
		}
		return false;
	}
	public static boolean atMianChi(User user){
		String page = getScenes(user);
		Matcher m = p.matcher(page);
		if(m.find()){
			if("渑池".equals(m.group(1))){
				return true;
			}
		}
		return false;
	}
	public static boolean inWuGuan(User user){
		String page = getScenes(user);
		Matcher m = wuGuan.matcher(page);
		return m.find();
	}
	public static boolean slavy(User user){
		
		String url = user.getUrl()+GET_SLAVY_MASTER+Tools.getRandAndTime();
		String page = PageService.getPageWithCookie(url, user);
		Matcher m = slavyMaster.matcher(page);
		if(m.find()){
			try{
			url = user.getUrl()+SLAVY+Tools.getRandAndTime();
			String data = "fawn_type=10&boss_id="+m.group(1)+"&type=3&callback_func_name=callbackFnSlaveOptSubmit";
			page = PageService.postPage(url,data, user);
			if(page == null){
				return false;
			}
			if(Tools.success(page)){
				logger.info(user.getRoleName()+"讨好主人成功");
				return true;
			}
			}catch(Exception e){}
		}
		return false;
	}
	public static boolean activeSlavys(User user){
		String url = user.getUrl()+SLAVY_MASTER+Tools.getTimeStamp(true);
		String page = PageService.getPageWithCookie(url, user);
		Matcher m = slavy.matcher(page);
		while(m.find()){
			comfortSlavy(user, m.group(1), m.group(2));
			painSlavy(user, m.group(1), m.group(2));
		}
		slavy(user);
		return true;
	}
	public static boolean comfortSlavy(User user,String id,String name){
		try{
			String url = user.getUrl()+COMFORT_SLAVY+Tools.getRandAndTime();
			String data = "comfort_type=6&slave_id="+id+"&type=2&callback_func_name=callbackFnSlaveOptSubmit";
			String page = PageService.postPage(url, data,user);
			
			if(Tools.success(page)){
				logger.info(user.getRoleName()+"安抚"+name+"成功");
				return true;
			}else{
				return false;
			}
		}catch(Exception e){
			return false;
		}
	}
	public static boolean painSlavy(User user,String id,String name){
		try{
			// /modules/role_slavery.php?act=pain_submit&rand=1280639390157&timeStamp=1280639377847
			String url = user.getUrl()+PAIN_SLAVY+Tools.getRandAndTime();
			//关小黑屋
			//scene_id=0&scene_type=0&slavery_scene_id=2&slavery_scene_type=durable&pain_type=17&slave_id=22051&type=1&callback_func_name=callbackFnSlaveOptSubmit
			String data = "scene_id=0&scene_type=0&slavery_scene_id=2&slavery_scene_type=durable&pain_type=17&slave_id="+id+"&type=1&callback_func_name=callbackFnSlaveOptSubmit";
			String page = PageService.postPage(url, data,user);
			
			if(Tools.success(page)){
				logger.info(user.getRoleName()+"关小黑屋"+name+"成功");
				return true;
			}else{
				data = "scene_id=0&scene_type=0&pain_type=13&slavery_scene_id=2&slavery_scene_type=durable&slave_id="+id+"&type=1&callback_func_name=callbackFnSlaveOptSubmit";
				page = PageService.postPage(url, data,user);
				if(Tools.success(page)){
					logger.info(user.getRoleName()+"折磨奴隶"+name+"宣传武馆成功");
					return true;
				}else{
					return false;
				}
			}
		}catch(Exception e){
			return false;
		}
	}
	public static boolean dailyWeals(User user){
		String url = user.getUrl()+DAILY_WEALS+Tools.getRandAndTime().substring(1);
		String page = PageService.getPageWithCookie(url, user);
		if(Tools.success(page)){
			logger.info(user.getRoleName()+"每日福利领取成功");
			return true;
		}else{
			return false;
		}
	}
	public static boolean mianChiWeals(User user){	
		Calendar c = Calendar.getInstance();
		int hour = c.get(Calendar.HOUR_OF_DAY);
		if(hour > 17){
			String page = intoMianChiYanWuTing(user);
			Matcher m = mianChiWeal.matcher(page);
			try{
			if(m.find()){
				//&arena_key=9_2_1280246400&team_mode=0&timeStamp=1280321831079
				String url = user.getUrl()+MIAN_CHI_WEAL+"&arena_key="+m.group(1)+"&team_mode="+m.group(2)+Tools.getTimeStamp(true);
				String data = "callback_func_name=warrior_common_callback";
				String p = PageService.postPage(url, data,user);
				if(Tools.success(p)){
					logger.info(user.getRoleName()+"领取渑池演武厅奖励成功");
					return true;
				}
			}
			}catch(Exception e){}
		}
		return false;
	}
	private static String intoMianChiYanWuTing(User user){
		String url = user.getUrl()+MIAN_CHI+Tools.getTimeStamp(true);
		String page = PageService.getPageWithCookie(url, user);
		return page;
	}
	public static boolean buyGlory(User user){
		String s = user.getGloryBuy();
		if(s == null || s.trim().length() == 0 || "0".equals(s)){
			return false;
		}
		String url = null;
		String type = null;
		if("1".equals(s)){
			url = user.getUrl()+GLORY_TO_JING_YAN+Tools.getTimeStamp(true);
			type = "经验";
		}else if("2".equals(s)){
			url = user.getUrl()+GLORY_TO_ZI_YU+Tools.getTimeStamp(true);
			type = "紫玉";
		}else{
			return false;
		}
		
		String page = PageService.getPageWithCookie(url, user);
		if(Tools.success(page)){
			logger.info(user.getRoleName()+"荣誉换"+type+"成功");
			return true;
		}
		return false;
	}
}
