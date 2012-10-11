package com.blue.monitor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.blue.common.Monitor;
import com.blue.common.User;
import com.blue.tools.PageService;
import com.blue.tools.Tools;

public class FreeItemMonitor {
	private static Logger logger = Logger.getLogger(FreeItemMonitor.class);
	//http://s4.verycd.9wee.com/modules/duel.php?act=pvehall&action=get_num&timeStamp=1328076123039&callback_func_name=ajaxCallback
	private static String GET_FREE_TIMES = "modules/duel.php?act=pvehall&action=get_num";
	private static Pattern ONE_FLAG = Pattern.compile("window.oneflag = '1';");
	/**
	 * 领取免费幻境塔次数
	 * @param user
	 */
	public static void getFreeTimes(User user){
		String url = user.getUrl()+GET_FREE_TIMES+Tools.getTimeStamp(true);
		PageService.getPageWithCookie(url, user);
	}
	
	public static void getFreeSearch(User user){
		if(!hasFreeSearch(user)){
			return;
		}
		//http://s4.verycd.9wee.com/modules/fam_explore.php?action=enter&select_type=1&timeStamp=1340159802313&callback_func_name=ajaxCallback&callback_obj_name=callbackFamExplore
		//http://s4.verycd.9wee.com/modules/fam_explore.php?action=enter&select_type=1&timeStamp=1340176599310&callback_func_name=ajaxCallback&callback_obj_name=callbackFamExplore&mirror_money_type=0
		String url = user.getUrl()+"modules/fam_explore.php?action=enter&select_type=1"+Tools.getTimeStamp(true);
		String page = PageService.getPageWithCookie(url, user);
		//http://s4.verycd.9wee.com/modules/fam_explore.php?action=view&mirror_money_type=&select_type=1&rand=1340176482361&timeStamp=1340176482348&callback_func_name=ajaxCallback&callback_obj_name=dialog-3
		//http://s4.verycd.9wee.com/modules/fam_explore.php?action=view&mirror_money_type=0&select_type=1&rand=1340176605499&timeStamp=1340176605463&callback_func_name=ajaxCallback&callback_obj_name=dialog-4
		url = user.getUrl()+"modules/fam_explore.php?action=view&mirror_money_type=&select_type=1"+Tools.getRandAndTime();
		page = PageService.getPageWithCookie(url, user);
		if(page.indexOf("秦王宝库")!=-1){
			logger.info(user.getRoleName()+"免费探索秦王宝库成功");
		}
	}
	public static void search(User user,int count){
		for(int i = 0;i<count-1;i++){
			//http://s4.verycd.9wee.com/modules/fam_explore.php?action=enter&select_type=10&timeStamp=1342450441433&callback_func_name=ajaxCallback&callback_obj_name=callbackFamExplore&mirror_money_type=1
			for(int j=0;j<5;j++){
				String url = user.getUrl()+"modules/fam_explore.php?action=enter&select_type=10&mirror_money_type=1"+Tools.getTimeStamp(true);
				String page = PageService.getPageWithCookie(url, user);
				//http://s4.verycd.9wee.com/modules/fam_explore.php?action=view&mirror_money_type=&select_type=1&rand=1340176482361&timeStamp=1340176482348&callback_func_name=ajaxCallback&callback_obj_name=dialog-3
				//http://s4.verycd.9wee.com/modules/fam_explore.php?action=view&mirror_money_type=0&select_type=1&rand=1340176605499&timeStamp=1340176605463&callback_func_name=ajaxCallback&callback_obj_name=dialog-4
				url = user.getUrl()+"modules/fam_explore.php?action=view&mirror_money_type=1&select_type=10"+Tools.getRandAndTime();
				page = PageService.getPageWithCookie(url, user);
				if(page.indexOf("秦王宝库")!=-1){
					logger.info(user.getRoleName()+"免费探索秦王宝库成功");
				}
			}
			reset(user);
		}
		for(int j=0;j<5;j++){
			String url = user.getUrl()+"modules/fam_explore.php?action=enter&select_type=10&mirror_money_type=1"+Tools.getTimeStamp(true);
			String page = PageService.getPageWithCookie(url, user);
			//http://s4.verycd.9wee.com/modules/fam_explore.php?action=view&mirror_money_type=&select_type=1&rand=1340176482361&timeStamp=1340176482348&callback_func_name=ajaxCallback&callback_obj_name=dialog-3
			//http://s4.verycd.9wee.com/modules/fam_explore.php?action=view&mirror_money_type=0&select_type=1&rand=1340176605499&timeStamp=1340176605463&callback_func_name=ajaxCallback&callback_obj_name=dialog-4
			url = user.getUrl()+"modules/fam_explore.php?action=view&mirror_money_type=1&select_type=10"+Tools.getRandAndTime();
			page = PageService.getPageWithCookie(url, user);
			if(page.indexOf("秦王宝库")!=-1){
				logger.info(user.getRoleName()+"免费探索秦王宝库成功");
			}
		}
		Monitor.getAwards(user);
	}
	public static void reset(User user){
		//http://s4.verycd.9wee.com/modules/fam_explore.php?action=clear&timeStamp=1342450648629&callback_func_name=callbackClearFamExplore&mirror_money_type=1
		String url = user.getUrl()+"modules/fam_explore.php?action=clear&mirror_money_type=1"+Tools.getTimeStamp(true);
		PageService.getPageWithCookie(url, user);
		logger.info("重置次数");
	}
	public static boolean hasFreeSearch(User user){
		//http://s4.verycd.9wee.com/modules/fam_explore.php?timeStamp=1340177587312&callback_func_name=callback_load_content&callback_obj_name=content
		String url = user.getUrl()+"modules/fam_explore.php?"+Tools.getTimeStamp(false);
		String page = PageService.getPageWithCookie(url, user);
		Matcher m = ONE_FLAG.matcher(page);
		if(m.find()){
			return true;
		}	
		return false;
	}
}
