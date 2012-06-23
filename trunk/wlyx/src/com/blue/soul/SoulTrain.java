package com.blue.soul;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.blue.common.User;
import com.blue.tools.PageService;
import com.blue.tools.Tools;
import com.blue.warrior.Warrior;

public class SoulTrain {
	private static Logger logger = Logger.getLogger(SoulTrain.class);
	private static Pattern ORIGINAL_TOTAL = Pattern.compile("原属性<span class=\"highlight2\">（总和：(\\d+)）");
	private static Pattern NEW_TOTAL = Pattern.compile("新属性<span class=\"highlight3\">（总和：(\\d+)<img ");
	private static Pattern CAN_PROMOTION = Pattern.compile("（剩余<span class=\"highlight2\">(\\d+)</span>次）");
	private static Pattern SOUL_ID_P = Pattern.compile("onclick=\"clsSoul.my\\((\\d+)\\)\"",Pattern.DOTALL);
		//Pattern.compile("onclick=\"loader.refreshCache\\(\\);clsSoul.train\\((\\d+)\\);\">训练</a>"); 
	private static List<String> getSoulId(User user){
		//http://s4.verycd.9wee.com/modules/soul.php?timeStamp=1339565014318&callback_func_name=callback_load_content&callback_obj_name=content
		List<String> souls = new ArrayList<String>();
		String url = user.getUrl()+"modules/soul.php?"+Tools.getTimeStamp(false);
		String page = PageService.getPageWithCookie(url, user);
		Matcher m = SOUL_ID_P.matcher(page);
		while(m.find()){
			souls.add(m.group(1));
		}
		return souls;
	}
	public static void train(User user){
		List<String> ids = getSoulId(user);
		if(ids == null){
			return ;
		}
		for(String id:ids){
			if(user.isNeedWHTrain()){
				train(user, id);
			}
			if(user.isNeedWHProm()){
				promotion(user,id);
			}
			if(user.isNeedWHFoster()){
				foster(user,id);
			}
		}
	}
	private static void foster(User user,String id){
		
	}
	private static void train(User user, String id) {
		int hourOnce = 1;
		//http://s4.verycd.9wee.com/modules/soul.php?act=train&op=start&soul_id=4&hour=8&timeStamp=1339565050142&callback_func_name=ajaxCallback&callback_obj_name=dlg_soul_train
		if(Warrior.need10HoursTrain()){
			hourOnce = 8;
		}
		int nowHour = Tools.getNowHour();
		if(nowHour>=user.getLongWHTrainStart() && user.getLongWHTrainStart()!=0){
			hourOnce = 8;
		}
		String url = user.getUrl()+"modules/soul.php?act=train&op=start&soul_id="+id+"&hour="+hourOnce+Tools.getTimeStamp(true);
		String page = PageService.getPageWithCookie(url, user);
		if(page.startsWith("<script>")){
			logger.info(user.getRoleName()+"武魂训练"+hourOnce+"小时开始");
		}
	}
	
	private static void promotion(User user,String id){
		String data = "soul_id="+id+"&act=promotion&op=ok&type=1&callback_func_name=ajaxCallback&callback_obj_name=dlg_soul_promotion";
		//soul_id=4&act=promotion&op=ok&type=1&callback_func_name=ajaxCallback&callback_obj_name=dlg_soul_promotion
		String url = user.getUrl()+"modules/soul.php?"+Tools.getTimeStamp(false);
		String page = PageService.postPage(url, data, user);
		if(getOriginal(page)<getNewTotal(page)){
			logger.info(user.getRoleName()+"武魂品质提升到"+getNewTotal(page));
			save(user,id);
		}
		if(canPromotion(page)){
			promotion(user,id);
		}else{
			return;
		}
	}
	private static boolean canPromotion(String page){
		Matcher m = CAN_PROMOTION.matcher(page);
		if(m.find()){
			return true;
		}
		return false;
	}
	private static void save(User user,String id){
		//http://s4.verycd.9wee.com/modules/soul.php?act=promotion&op=save&soul_id=4&type=1&timeStamp=1339571494940&callback_func_name=ajaxCallback&callback_obj_name=dlg_soul_promotion\
		String url = user.getUrl()+"modules/soul.php?act=promotion&op=save&soul_id="+id+"&type=1"+Tools.getTimeStamp(true);
		PageService.getPageWithCookie(url, user);
	}

	private static int getOriginal(String page){
		Matcher m = ORIGINAL_TOTAL.matcher(page);
		if(m.find()){
			return Integer.parseInt(m.group(1));
		}
		return -1;
	}
	private static int getNewTotal(String page){
		Matcher m = NEW_TOTAL.matcher(page);
		if(m.find()){
			return Integer.parseInt(m.group(1));
		}
		return -1;
	}
}
