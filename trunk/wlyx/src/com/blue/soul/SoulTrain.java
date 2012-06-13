package com.blue.soul;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.blue.common.User;
import com.blue.tools.PageService;
import com.blue.tools.Tools;
import com.blue.warrior.Warrior;

public class SoulTrain {
	private static Logger logger = Logger.getLogger(SoulTrain.class);
	private static Pattern SOUL_ID_P = Pattern.compile("onclick=\"loader.refreshCache\\(\\);clsSoul.train\\((\\d+)\\);\">ÑµÁ·</a>"); 
	private static String getSoulId(User user){
		//http://s4.verycd.9wee.com/modules/soul.php?timeStamp=1339565014318&callback_func_name=callback_load_content&callback_obj_name=content
		String url = user.getUrl()+"modules/soul.php"+Tools.getTimeStamp(true);
		String page = PageService.getPageWithCookie(url, user);
		Matcher m = SOUL_ID_P.matcher(page);
		if(m.find()){
			return m.group(1);
		}
		return null;
	}
	public static void train(User user){
		String id = getSoulId(user);
		if(id == null){
			return ;
		}
		int hourOnce = 1;
		//http://s4.verycd.9wee.com/modules/soul.php?act=train&op=start&soul_id=4&hour=8&timeStamp=1339565050142&callback_func_name=ajaxCallback&callback_obj_name=dlg_soul_train
		if(Warrior.need10HoursTrain()){
			hourOnce = 8;
		}
		String url = user.getUrl()+"modules/soul.php?act=train&op=start&soul_id="+id+"&hour="+hourOnce+Tools.getTimeStamp(true);
		String page = PageService.getPageWithCookie(url, user);
		if(page.startsWith("<script>")){
			logger.info(user.getRoleName()+"Îä»êÑµÁ·"+hourOnce+"¿ªÊ¼");
		}
	}
}
