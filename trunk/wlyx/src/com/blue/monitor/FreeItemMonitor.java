package com.blue.monitor;

import com.blue.common.User;
import com.blue.tools.PageService;
import com.blue.tools.Tools;

public class FreeItemMonitor {
	//http://s4.verycd.9wee.com/modules/duel.php?act=pvehall&action=get_num&timeStamp=1328076123039&callback_func_name=ajaxCallback
	private static String GET_FREE_TIMES = "modules/duel.php?act=pvehall&action=get_num";
	/**
	 * 领取免费幻境塔次数
	 * @param user
	 */
	public static void getFreeTimes(User user){
		String url = user.getUrl()+GET_FREE_TIMES+Tools.getTimeStamp(true);
		PageService.getPageWithCookie(url, user);
	}
	
	public static void getFreeSearch(User user){
		//http://s4.verycd.9wee.com/modules/fam_explore.php?action=enter&select_type=1&timeStamp=1340159802313&callback_func_name=ajaxCallback&callback_obj_name=callbackFamExplore
		String url = user.getUrl()+"modules/fam_explore.php?action=enter&select_type=1"+Tools.getTimeStamp(true);
		PageService.getPageWithCookie(url, user);
	}
}
