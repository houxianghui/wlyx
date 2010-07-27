package com.blue.common;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.blue.tools.PageService;
import com.blue.tools.Tools;

public class Monitor {
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
	
	private static Pattern p = Pattern.compile("<div class=\"city_scene_name\">(\\S+)国 (\\s+)</div>");
	private static Pattern wuGuan = Pattern.compile("武馆战打斗极其混乱");
	private static Pattern slavy = Pattern.compile("<a href=\"javascript:void\\(0\\);\" onclick=\"view_role \\( (\\d+) \\)\" title=\"(\\S+?)\">(\\S+?)</a>");
	private static Pattern slavyMaster = Pattern.compile("我的主人：<a href=\"javascript:void\\(0\\);\" onclick=\"view_role \\( (\\d+) \\)\">");
	
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
		return m.find();
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
			System.out.println("讨好主人");
			return Tools.success(page);
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
				System.out.println(user.getUserName()+" comfort "+name+" success");
				return true;
			}else{
				System.out.println(user.getUserName()+" comfort "+name+" fail");
				return false;
			}
		}catch(Exception e){
			return false;
		}
	}
	public static boolean painSlavy(User user,String id,String name){
		try{
			String url = user.getUrl()+PAIN_SLAVY+Tools.getRandAndTime();
			//关小黑屋
			String data = "scene_id=0&scene_type=0&slavery_scene_id=2&slavery_scene_type=durable&pain_type=17&slave_id="+id+"&type=1&callback_func_name=callbackFnSlaveOptSubmit";
			String page = PageService.postPage(url, data);
			
			if(Tools.success(page)){
				System.out.println(user.getUserName()+" pain "+name+" success");
				return true;
			}else{
				System.out.println(user.getUserName()+" 关黑屋 "+name+" fail");
				data = "scene_id=0&scene_type=0&pain_type=13&slavery_scene_id=2&slavery_scene_type=durable&slave_id="+id+"&type=1&callback_func_name=callbackFnSlaveOptSubmit";
				page = PageService.postPage(url, data,user);
				if(Tools.success(page)){
					System.out.println(name+"宣传武馆成功");
					return true;
				}else{
					System.out.println(name+"宣传武馆失败");
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
			System.out.println(user.getUserName()+"每日福利领取成功");
			return true;
		}else{
			return false;
		}
	}
}
