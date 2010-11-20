package com.blue.monitor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.blue.common.User;
import com.blue.tools.PageService;
import com.blue.tools.Tools;

public class ItemChange {
	private static Logger logger = Logger.getLogger(ItemChange.class);
	//http://s4.verycd.9wee.com/modules/ore.php?submit=1&id=3&num=1&timeStamp=1290239964789&callback_func_name=ajaxCallback
	private static final String CHENG_JI = "modules/ore.php?submit=1&num=1&callback_func_name=ajaxCallback&id=";
	private static Pattern success = Pattern.compile("result:\":\"(.*?)<span");
	public static void changeChengJiDan(User user){
		String page = null;
		for(int i = 1;i < 5;i++){
			do{
				String url = user.getUrl()+CHENG_JI+i+Tools.getTimeStamp(true);
				page = PageService.getPageWithCookie(url, user);
				logger.info(user.getRoleName()+"兑换成绩单成功");
			}while(successChange(page));
		}
		for(int i = 6;i < 11;i++){
			do{
				String url = user.getUrl()+CHENG_JI+i+Tools.getTimeStamp(true);
				page = PageService.getPageWithCookie(url, user);
				logger.info(user.getRoleName()+"兑换月饼成功");
			}while(successChange(page));
		}
	}
	private static boolean successChange(String s){
		Matcher m = success.matcher(s);
		if(m.find()){
			if(Tools.hexToString(m.group(1)).startsWith("你口中念念有词")){
				return true;
			}
		}
		return false;
	}
}
