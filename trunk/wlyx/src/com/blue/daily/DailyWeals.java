package com.blue.daily;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.blue.common.*;
import com.blue.tools.PageService;
import com.blue.tools.Tools;

public class DailyWeals {
	private static Logger logger = Logger.getLogger(DailyWeals.class);
	//url :&timeStamp=1279099004405&rand=1279099024048
	public static final String DAILY_WEALS="modules/day_weals.php?act=weal";
	public static final String SHOW_DAILY_WEALS="modules/day_weals.php?act=show";
	public static final String GET_DAILY_REWARD="modules/day_weals_activity.php?act=weal&id=1rand=1279158695611&callback_func_name=ajaxCallback";
	public static final String CHECK_DAILY_REWARD = "modules/day_weals_activity.php?act=show&rand=1279158807518&callback_func_name=ajaxCallback&callback_obj_name=dlg_view_weals";
	//http://s4.verycd.9wee.com/modules/day_weals.php?act=weal&rand=1304180160946&timeStamp=1304180142543&callback_func_name=callbackFnStartWeals
	//http://s4.verycd.9wee.com/modules/day_weals.php?act=show&rand=1304180372125&timeStamp=1304180323373&callback_func_name=ajaxCallback&callback_obj_name=dlg_view_weals
	private static Pattern p = Pattern.compile("今天的事情就到这里了");
	
	public static boolean getDailyWeals(User user){
		String url = user.getUrl()+DAILY_WEALS+Tools.getRandAndTime();
		if(alreadyHasWeals(user)){
			return true;
		}
		String page = PageService.getPageWithCookie(url, user);
		logger.info(user.getRoleName()+"每日占卜开始");
		return Tools.success(page);
	}
	public static boolean alreadyHasWeals(User user){
		String url = user.getUrl()+SHOW_DAILY_WEALS+Tools.getRandAndTime();
		String page = PageService.getPageWithCookie(url, user);
		Matcher m = p.matcher(page);
		if(m.find()){
			return true;
		}
		return false;
	}
}
