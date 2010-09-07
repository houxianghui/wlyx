package com.blue.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.blue.tools.PageService;
import com.blue.tools.Tools;

public class DropWeapon {
	private static Logger logger = Logger.getLogger(DropWeapon.class);
	private static Pattern drop = Pattern.compile("\"fromId\":1,\"fromType\":\"equip\",\"noRemove\":false,\"toId\":(\\d+)");
	//http://s4.verycd.9wee.com/modules/role_item.php?act=drag_item&id=1&from=equip&to=pack&timeStamp=1283862050880&callback_func_name=itemClass.dragItemCallback
	public static final String dropWeapon = "modules/role_item.php?act=drag_item&id=1&from=equip&to=pack&callback_func_name=itemClass.dragItemCallback";
	//http://s4.verycd.9wee.com/modules/role_item.php?act=drag_item&id=1552750&from=pack&to=equip&timeStamp=1283862123915&callback_func_name=itemClass.dragItemCallback
	public static final String mountWeapon = "modules/role_item.php?act=drag_item&from=pack&to=equip&callback_func_name=itemClass.dragItemCallback&id=";
	
	public static void dropWeapon(User user){
		String url = user.getUrl()+dropWeapon+Tools.getTimeStamp(true);
		String page = PageService.getPageWithCookie(url, user);
		if(Tools.success(page)){
			Matcher m = drop.matcher(page);
			if(m.find()){
				logger.info(user.getRoleName()+"–∂Œ‰≥…π¶");
				user.setWeapon(m.group(1));
			}
		}else{
			logger.info(user.getRoleName()+"–∂Œ‰ ß∞‹");
		}
	}
	public static void mountWeapon(User user){
		String url = user.getUrl()+mountWeapon+user.getWeapon()+Tools.getTimeStamp(true);
		String page = PageService.getPageWithCookie(url, user);
		if(Tools.success(page)){
			logger.info(user.getRoleName()+"…œŒ‰∆˜≥…π¶");
		}
	}
}
