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
	//fnBusGloryReward( 73, 'material', '紫仙玉', 10000, 538 )
	//fnBusGloryReward( 19, 'function', '灵台清明', 29000, 221455 )
	//fnBusGloryReward( 13, 'function', '灵台清明', 20000, 189840 )
	private static Pattern gloryList = Pattern.compile("fnBusGloryReward\\( (\\d+), '\\S+?', '(\\S+?)', \\d+, \\d+ \\)");
	//http://s4.verycd.9wee.com/modules/duel.php?act=glory&op=buy&itemID=62&itemNum=1&timeStamp=1280560866420
	//http://s4.verycd.9wee.com/modules/duel.php?act=glory&op=buy&itemID=15&timeStamp=1280560866420&callback_func_name=callbackFnBusGloryReward
	private static String CHANGE_URL = "modules/duel.php?act=glory&op=buy&itemNum=1&itemID=";
	//http://s4.verycd.9wee.com/modules/duel.php?act=glory&op=buy&itemID=19&timeStamp=1297579736265&callback_func_name=callbackFnBusGloryReward
	//http://s4.verycd.9wee.com/modules/duel.php?act=glory&op=buy&itemID=13&timeStamp=1298290082713&callback_func_name=callbackFnBusGloryReward
	private static String CHANGE_JING_YAN = "modules/duel.php?act=glory&op=buy&callback_func_name=callbackFnBusGloryReward&itemID=";
	private static final String JING_YAN = "1";
	private static final String ZI_YU = "2";
	private static final String ZU_MU_LV = "3";
	
	public static void gloryChange(User user){
		String s = user.getGloryBuy();
		if(s == null || s.trim().length() == 0 || "0".equals(s)){
			return;
		}
		java.util.Map<String, String> map = user.getGloryMap(); 
		String itemId = map.get(s);
		if(itemId == null){
			initGloryMap(user);
			itemId = map.get(s);
		}
		if(itemId == null){
			return;
		}
		String url = user.getUrl()+CHANGE_URL+itemId+Tools.getTimeStamp(true);
		if("1".equals(s)){
			url = user.getUrl()+CHANGE_JING_YAN+itemId+Tools.getTimeStamp(true);
		}
		String page = PageService.getPageWithCookie(url, user);
		page = PageService.getPageWithCookie(url, user);
		
		String type = null;
		if("1".equals(s)){
			type = "灵台清明 ";
		}else if("2".equals(s)){
			type = "紫玉";
		}else if("3".equals(s)){
			type = "祖母绿";
		}
		if(Tools.success(page)){
			logger.info(user.getRoleName()+"荣誉换"+type+"成功");
		}
		
	}
	

	private static void initGloryMap(User user) {
		String url = user.getUrl()+GLORY+Tools.getTimeStamp(true);
		String page = PageService.getPageWithCookie(url, user);
		Matcher m = gloryList.matcher(page);
		java.util.Map<String,String> map = user.getGloryMap();
		while(m.find()){
			if("灵台清明".equals(m.group(2))){
				map.put(JING_YAN, m.group(1));
			}
			if("紫仙玉".equals(m.group(2))){
				map.put(ZI_YU, m.group(1));
			}
			if("祖母绿".equals(m.group(2))){
				map.put(ZU_MU_LV, m.group(1));
			}
		}
	}
}