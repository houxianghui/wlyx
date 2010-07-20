package com.blue.common;

import com.blue.tools.PageService;
import com.blue.tools.Tools;

public class Move {
	public static final String WORLD_MOVE="modules/scene_walk.php?action=world_move&callback_func_name=callbackFnWorldTransport&scene_id=";
	/*
	 * 二级场景
	 * http://s4.verycd.9wee.com/modules/scene_walk.php?action=walk&sid=141&g_time=1279608278&timeStamp=1279608274519&callback_func_name=callbackfnScene
	 */
	public static final String WALK = "modules/scene_walk.php?action=walk&callback_func_name=callbackfnScene&sid=";
	/*
	 * 三级场景
	 * http://s4.verycd.9wee.com/modules/scene_walk.php?action=enterThirdScene&sid=478&pk_status=0&hide_tips=0&isfree=0&g_time=1279608472&timeStamp=1279608473269&callback_func_name=callbackfnEnterThirdScene
	 */
	public static final String THIRD = "modules/scene_walk.php?action=enterThirdScene&pk_status=0&hide_tips=0&isfree=0&callback_func_name=callbackfnEnterThirdScene&sid=";
	
	
	public static String worldMove(User user,String key){
		String id = Map.getId(1, key);
		String url = user.getUrl()+WORLD_MOVE+id+Tools.getRandAndTime();
		String page = PageService.getPageWithCookie(url, user);
		return page;
	}
	public static String secMove(User user,String key){
		String id = Map.getId(2, key);
		String url = user.getUrl()+WALK+id+Tools.getGtimeAndTime();
		String page = PageService.getPageWithCookie(url, user);
		return page;
	}
	public static String thirdMove(User user,String key){
		String id = Map.getId(3, key);
		String url = user.getUrl()+THIRD+id+Tools.getGtimeAndTime();
		String page = PageService.getPageWithCookie(url, user);
		return page;
	}
}
