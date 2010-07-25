package com.blue.common;

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
	
	
	private static Pattern p = Pattern.compile("<div class=\"city_scene_name\">(\\S+)国 (\\s+)</div>");
	private static Pattern wuGuan = Pattern.compile("武馆战打斗极其混乱");
	
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
		String url = user.getUrl()+SLAVY+Tools.getRandAndTime();
		String page = PageService.getPageWithCookie(url, user);
		return Tools.success(page);
	}
}
