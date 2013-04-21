package com.blue.monitor;

import org.apache.log4j.Logger;

import com.blue.common.User;
import com.blue.tools.PageService;
import com.blue.tools.Tools;

public class WuHunMonitor {
	private static Logger logger = Logger.getLogger(WuHunMonitor.class);
	//http://s4.verycd.9wee.com/modules/soul.php?act=gem&op=freeBatchPurple&soul_id=3&timeStamp=1360385854913&callback_func_name=ajaxCallback&callback_obj_name=dlg_soul_gem_gem
	public static void getFreeStone(User user){
		String url = user.getUrl()+"modules/soul.php?act=gem&op=freeBatchPurple&soul_id=3"+Tools.getTimeStamp(true);
		String page = PageService.getPageWithCookie(url, user);
		if(page.indexOf("武魂宝石") != -1){
			logger.info(user.getRoleName()+"免费领取武魂宝石成功");
		}
	}
}
