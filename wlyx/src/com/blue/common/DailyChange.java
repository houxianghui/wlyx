package com.blue.common;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.blue.tools.PageService;
import com.blue.tools.Tools;

public class DailyChange {
	private static Logger logger = Logger.getLogger(DailyChange.class);
	//http://s4.verycd.9wee.com/modules/duel.php?act=glory&timeStamp=1296126507418&callback_func_name=callback_load_content&callback_obj_name=content
	private static String GLORY = "modules/duel.php?act=glory&callback_func_name=callback_load_content&callback_obj_name=content";
	//fnBusGloryReward( 73, 'material', '×ÏÏÉÓñ', 10000, 538 )
	private static Pattern gloryList = Pattern.compile("fnBusGloryReward\\( (\\d+), '\\S+?', '(\\S+?)', \\d+, \\d+ \\)");
	//http://s4.verycd.9wee.com/modules/duel.php?act=glory&op=buy&itemID=62&itemNum=1&timeStamp=1280560866420
	//http://s4.verycd.9wee.com/modules/duel.php?act=glory&op=buy&itemID=15&timeStamp=1280560866420&callback_func_name=callbackFnBusGloryReward
	private static String CHANGE_URL = "modules/duel.php?act=glory&op=buy&itemNum=1&itemID=";
	private static HashMap<String, String> map = new HashMap<String, String>(); 
	private static final String JING_YAN = "1";
	private static final String ZI_YU = "2";
	private static final String ZU_MU_LV = "3";
	
	public static void gloryChange(User user){
		String s = user.getGloryBuy();
		if(s == null || s.trim().length() == 0 || "0".equals(s)){
			return;
		}
		String itemId = map.get(s);
		if(itemId == null){
			initGloryMap(user);
			itemId = map.get(s);
		}
		if(itemId == null){
			return;
		}
		String url = user.getUrl()+CHANGE_URL+Tools.getTimeStamp(true);
		String page = PageService.getPageWithCookie(url, user);
		page = PageService.getPageWithCookie(url, user);
		
		String type = null;
		if("1".equals(s)){
			type = "¾­Ñé";
		}else if("2".equals(s)){
			type = "×ÏÓñ";
		}else if("3".equals(s)){
			type = "×æÄ¸ÂÌ";
		}
		if(Tools.success(page)){
			logger.info(user.getRoleName()+"ÈÙÓþ»»"+type+"³É¹¦");
		}
		
	}
	

	private static void initGloryMap(User user) {
		String url = user.getUrl()+GLORY+Tools.getTimeStamp(true);
		String page = PageService.getPageWithCookie(url, user);
		Matcher m = gloryList.matcher(page);
		while(m.find()){
			if("ÁéÌ¨ÇåÃ÷".equals(m.group(2))){
				map.put(JING_YAN, m.group(1));
			}
			if("×ÏÏÉÓñ".equals(m.group(2))){
				map.put(ZI_YU, m.group(1));
			}
			if("×æÄ¸ÂÌ".equals(m.group(2))){
				map.put(ZU_MU_LV, m.group(1));
			}
		}
	}
}