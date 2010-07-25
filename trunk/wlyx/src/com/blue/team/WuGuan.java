package com.blue.team;

import java.util.regex.Pattern;

import com.blue.common.User;
import com.blue.tools.PageService;
import com.blue.tools.Tools;

public class WuGuan {
	//http://s4.verycd.9wee.com/modules/team.php?act=reduce_durable&rand=1280043809090&timeStamp=1280043576233&callback_func_name=callbackFnTeamSceneReduceDurable
	public static final String TI_GUAN = "modules/team.php?act=reduce_durable&callback_func_name=callbackFnTeamSceneReduceDurable";
	//http://s4.verycd.9wee.com/modules/team.php?act=add_durable&rand=1280047786584&timeStamp=1280047781563&callback_func_name=callbackFnTeamSceneAddDurable
	public static final String HU_GUAN = "modules/team.php?act=add_durable&callback_func_name=callbackFnTeamSceneAddDurable";
	//http://s4.verycd.9wee.com/modules/team.php?act=team_scene_move&tid=61&sid=3&timeStamp=1280050633201&callback_func_name=callbackFnTeamSceneWalk
	public static final String MOVE="modules/team.php?act=team_scene_move&tid=61&sid=3&timeStamp=1280050633201&callback_func_name=callbackFnTeamSceneWalk";
	//s_now_team_scene_id":"2","s_open_time":"10","s_close_time":"18"
	private static Pattern p = Pattern.compile("s_now_team_scene_id\":\"(\\d+)\",\"s_open_time\":\"(\\d+)\",\"s_close_time\":\"(\\d+)\"");
	
	
	
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
	
}
