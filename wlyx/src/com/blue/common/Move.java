package com.blue.common;

import com.blue.tools.PageService;
import com.blue.tools.Tools;

public class Move {
	public static final String WORLD_MOVE="modules/scene_walk.php?action=world_move&callback_func_name=callbackFnWorldTransport&scene_id=";
	public static final String WALK = "modules/scene_walk.php?action=walk&callback_func_name=callbackfnScene&sid=";
	public static final String THIRD = "";
	
	public boolean worldMove(User user,String id){
		String url = user.getUrl()+id+Tools.getRandAndTime();
		String page = PageService.getPageWithCookie(url, user);
		return Tools.success(page);
	}
}
