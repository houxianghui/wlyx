package com.blue.daily;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.blue.common.*;
import com.blue.tools.PageService;
import com.blue.tools.Tools;

public class DailyWeals {
	//url :&timeStamp=1279099004405&rand=1279099024048
	public static final String DAILY_WEALS="modules/day_weals.php?act=weal&rand=1279099024048&callback_func_name=callbackFnStartWeals";
	public static final String SHOW_DAILY_WEALS="modules/day_weals.php?act=show&rand=1279158026128&callback_func_name=ajaxCallback&callback_obj_name=dlg_view_weals";
	public static final String GET_DAILY_REWARD="modules/day_weals_activity.php?act=weal&id=1rand=1279158695611&callback_func_name=ajaxCallback";
	public static final String CHECK_DAILY_REWARD = "modules/day_weals_activity.php?act=show&rand=1279158807518&callback_func_name=ajaxCallback&callback_obj_name=dlg_view_weals";
	
	private Pattern p = Pattern.compile("今天的事情就到这里了，明天再来吧");
	
	public boolean getDailyWeals(User user){
		String url = user.getUrl()+DAILY_WEALS+Tools.getTimeStamp(true);
		String page = PageService.getPageWithCookie(url, user);
		return Tools.success(page);
	}
	public boolean alreadyHasWeals(User user){
		String url = user.getUrl()+SHOW_DAILY_WEALS+Tools.getTimeStamp(true);
		String page = PageService.getPageWithCookie(url, user);
		Matcher m = p.matcher(page);
		if(m.find()){
			System.out.println("今天就到这里了");
			return true;
		}
		return false;
	}
}
