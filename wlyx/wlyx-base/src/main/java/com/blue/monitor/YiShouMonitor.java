package com.blue.monitor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.blue.common.User;
import com.blue.tools.PageService;
import com.blue.tools.Tools;

public class YiShouMonitor {
	private static Logger logger = Logger.getLogger(YiShouMonitor.class);
	private static Pattern hasFreeEgg = Pattern.compile("item_id\":\"(\\d+)\",\"gold_price\":0,",Pattern.DOTALL);
	//http://s4.verycd.9wee.com/modules/shop.php?act=treasure&action=buy&item_id=7034&awards=1&timeStamp=1338980866448
	public static void getYiShouEgg(User user){
		String s = hasFreeEgg(user);
		if(s == null){
			return;
 		}
		String url = user.getUrl()+"modules/shop.php?act=treasure&action=buy&item_id="+s+"&awards=1"+Tools.getTimeStamp(true);
		//callback_func_name=callback_buy_treasure_item&mirror_money_type=1
		String data = "callback_func_name=callback_buy_treasure_item&mirror_money_type=1";
		String page = PageService.postPage(url, data, user);
		if(page.indexOf("item_id") != -1){
			logger.info(user.getRoleName()+"免费领取异兽蛋成功");
		}
	}
	private static String hasFreeEgg(User user){
		//http://s4.verycd.9wee.com/modules/shop.php?act=treasure&timeStamp=1338981138098&callback_func_name=ajaxCallback&callback_obj_name=dlg_shop
		String url = user.getUrl()+"modules/shop.php?act=treasure"+Tools.getTimeStamp(true);
		String page = PageService.getPageWithCookie(url, user);
		Matcher m = hasFreeEgg.matcher(page);
		if(m.find()){
			return m.group(1);
		}
		return null;
	}
	
}
