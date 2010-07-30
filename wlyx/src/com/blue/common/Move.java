package com.blue.common;

import org.apache.log4j.Logger;

import com.blue.monstor.LevelVSMonstor;
import com.blue.task.AutoTask;
import com.blue.tools.PageService;
import com.blue.tools.Tools;

public class Move {
	private static Logger logger = Logger.getLogger(Move.class);
	public static final String WORLD_MOVE="modules/scene_walk.php?action=world_move&callback_func_name=callbackFnWorldTransport&scene_id=";
	//http://s4.verycd.9wee.com/modules/scenes_role.php?sid=134&timeStamp=1280388427484&callback_func_name=switch_scene_callback
	//http://s4.verycd.9wee.com/modules/scenes_role.php?sid=154&timeStamp=1280389027968&callback_func_name=switch_scene_callback
	public static final String FROM_MIAN_CHI = "modules/scenes_role.php?callback_func_name=switch_scene_callback&sid=";
	//http://s4.verycd.9wee.com/modules/scene_walk.php?action=transport&sid=164&g_time=1280389326&timeStamp=1280389334593&callback_func_name=callbackfnTransportScene
	public static final String GO_TO_MIAN_CHI="modules/scene_walk.php?action=transport&sid=164&callback_func_name=callbackfnTransportScene";
	//http://s4.verycd.9wee.com/modules/scene_walk.php?action=walk&sid=72&g_time=1280389756&timeStamp=1280389764656&callback_func_name=callbackfnScene
	public static final String GO_TO_FIRST = "modules/scene_walk.php?action=walk&callback_func_name=callbackfnScene&sid=";
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
	
	
	public static String worldMove(User user,String key,AutoTask at){
		goToMianChi(user);
		at.autoAcceptTask(user);
		String id = Map.getId(1, key);
		String url = user.getUrl()+WORLD_MOVE+id+Tools.getRandAndTime();
		if(!key.equals("安平郡") && Monitor.atMianChi(user)){
			url = user.getUrl()+FROM_MIAN_CHI+id+Tools.getTimeStamp(true);
			logger.info("免费移动");
		}
//		String url = user.getUrl()+WORLD_MOVE+id+Tools.getRandAndTime();
		String page = PageService.getPageWithCookie(url, user);
		logger.info("移动到"+ key);
		return page;
	}
	public static boolean goToMianChi(User user){
		String lv = user.getLevel();
		String[] host = LevelVSMonstor.getMonstorInfo(lv);
		String first = host[0];
		//返回1级场景
		String url = user.getUrl()+GO_TO_FIRST+Map.getId(1, first)+Tools.getTimeStamp(true);
		String page = PageService.getPageWithCookie(url, user);
		if(Tools.success(page)){
			logger.info(user.getRoleName()+"前往"+first);
		}
		//前往渑池
		url = user.getUrl()+GO_TO_MIAN_CHI+Tools.getGtimeAndTime();
		page = PageService.getPageWithCookie(url, user);
		if(Tools.success(page)){
			logger.info(user.getRoleName()+"返回渑池");
			return true;
		}
		return false;
	}
	public static String secMove(User user,String key){
		String id = Map.getId(2, key);
		String url = user.getUrl()+WALK+id+Tools.getGtimeAndTime();
		String page = PageService.getPageWithCookie(url, user);
		logger.info("移动到"+ key);
		return page;
	}
	public static String thirdMove(User user,String key){
		String id = Map.getId(3, key);
		String url = user.getUrl()+THIRD+id+Tools.getGtimeAndTime();
		String page = PageService.getPageWithCookie(url, user);
		logger.info("移动到"+key);
		return page;
	}
}
