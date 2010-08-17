package com.blue.team;

import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.blue.common.User;
import com.blue.tools.PageService;
import com.blue.tools.Tools;

public class WuGuan {
	private static Logger logger = Logger.getLogger(WuGuan.class);
	//http://s4.verycd.9wee.com/modules/team.php?act=reduce_durable&rand=1280043809090&timeStamp=1280043576233&callback_func_name=callbackFnTeamSceneReduceDurable
	public static final String TI_GUAN = "modules/team.php?act=reduce_durable&callback_func_name=callbackFnTeamSceneReduceDurable";
	//http://s4.verycd.9wee.com/modules/team.php?act=add_durable&rand=1280047786584&timeStamp=1280047781563&callback_func_name=callbackFnTeamSceneAddDurable
	public static final String HU_GUAN = "modules/team.php?act=add_durable&callback_func_name=callbackFnTeamSceneAddDurable";
	//http://s4.verycd.9wee.com/modules/team.php?act=team_scene_move&tid=61&sid=3&timeStamp=1280050633201&callback_func_name=callbackFnTeamSceneWalk
	public static final String MOVE="modules/team.php?act=team_scene_move&tid=61&sid=3&timeStamp=1280050633201&callback_func_name=callbackFnTeamSceneWalk";
	//s_now_team_scene_id":"2","s_open_time":"10","s_close_time":"18"
	private static Pattern p = Pattern.compile("s_now_team_scene_id\":\"(\\d+)\",\"s_open_time\":\"(\\d+)\",\"s_close_time\":\"(\\d+)\"");
	public static final String MIAN_CHI_DESTROY = "modules/scene_brick.php?act=down&callback_func_name=callbackfnWarSceneBrick";
	//http://s4.verycd.9wee.com/modules/scene_brick.php?act=add&rand=1281162338117&timeStamp=1281162312191&callback_func_name=callbackfnWarSceneBrick
	public static final String MIAN_CHI_REPAIR = "modules/scene_brick.php?act=add&callback_func_name=callbackfnWarSceneBrick";
	//http://s4.verycd.9wee.com/modules/warrior.php?act=team&timeStamp=1281678437515
	public static final String TEAM_LIST="modules/warrior.php?act=team";
	
	public static String getTeamList(User user){
		String url = user.getUrl()+TEAM_LIST+Tools.getTimeStamp(true);
		String data = "country=0&type=credits&search_var=&chk_open_team=1&callback_func_name=ajaxCallback&callback_obj_name=content";
		String page = PageService.postPage(url, data, user);
		return page;
	}
	
	public static boolean tiGuan(User user){
		String url = user.getUrl()+TI_GUAN+Tools.getRandAndTime();
		String page = PageService.getPageWithCookie(url, user);
		return Tools.success(page);
	}
	public static boolean huGuan(User user){
		String url = user.getUrl()+HU_GUAN+Tools.getRandAndTime();
		String page = PageService.getPageWithCookie(url, user);
		return Tools.success(page);
	}
	public static boolean destroy(User user){
		String url = user.getUrl()+MIAN_CHI_DESTROY+Tools.getRandAndTime();
		String page  = PageService.getPageWithCookie(url, user);
		
		if(Tools.success(page)){
			logger.info(user.getRoleName()+"destroy");
		}else{
			url = user.getUrl()+MIAN_CHI_REPAIR+Tools.getRandAndTime();
			page = PageService.getPageWithCookie(url, user);
			if(Tools.success(page)){
				logger.info(user.getRoleName()+"add");
			}
		}
		return true;
	}
	
}
