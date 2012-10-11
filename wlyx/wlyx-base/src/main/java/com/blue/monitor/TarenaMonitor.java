package com.blue.monitor;

import com.blue.common.User;
import com.blue.tools.PageService;
import com.blue.tools.Tools;

public class TarenaMonitor {
	//callback_func_name=ajaxCallback
	//http://s4.verycd.9wee.com/modules/tarena.php?act=race&timeStamp=1334053963569&callback_func_name=callback_load_content&callback_obj_name=content
	//http://s4.verycd.9wee.com/modules/tarena.php?act=race&op=getAward&timeStamp=1334054054784
//	private static Pattern p = Pattern.compile("");
	public static void getReward(User user){
//		String url = user.getUrl()+"modules/tarena.php?act=race"+Tools.getTimeStamp(true);
//		String page = PageService.getPageWithCookie(url, user);
//		Matcher m = p.matcher(page);
//		if(m.find()){
		String url = user.getUrl()+"modules/tarena.php?act=race&op=getAward"+Tools.getTimeStamp(true);
		PageService.postPage(url, "callback_func_name=ajaxCallback", user);
//		}
	}
}
