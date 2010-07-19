package com.blue.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.blue.tools.PageService;
import com.blue.tools.Tools;

public class Monitor {
	//http://s4.verycd.9wee.com/modules/scenes_role.php?sid=0&timeStamp=1279162121165&callback_func_name=switch_scene_callback
	public static final String RETURN_HOME="modules/scenes_role.php?sid=0&callback_func_name=switch_scene_callback";
	public static final String POSITION="modules/scene.php";
	private Pattern p = Pattern.compile("<div class=\"city_scene_name\">(\\S+)¹ú (\\s+)</div>");
	public boolean goHome(User user){
		String page = PageService.getPageWithCookie(RETURN_HOME, user);
		return Tools.success(page);
			
	}
	public boolean atHome(User user){
		String url = user.getUrl()+POSITION+Tools.getTimeStamp(false);
		String page = PageService.getPageWithCookie(url, user);
		Matcher m = p.matcher(page);
		return m.find();
	}
}
