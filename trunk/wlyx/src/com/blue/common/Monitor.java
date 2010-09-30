package com.blue.common;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.blue.tools.PageService;
import com.blue.tools.Tools;

public class Monitor {
	private static Logger logger = Logger.getLogger(Monitor.class);
	//http://s4.verycd.9wee.com/modules/scenes_role.php?sid=0&timeStamp=1279162121165&callback_func_name=switch_scene_callback
	public static final String RETURN_HOME="modules/scenes_role.php?sid=0&callback_func_name=switch_scene_callback";
	public static final String POSITION="modules/scene.php";
	//http://s4.verycd.9wee.com/modules/role_slavery.php?act=fawn_submit&rand=1280046928316&timeStamp=1280046903963
	public static final String SLAVY = "modules/role_slavery.php?act=fawn_submit";
	//http://s4.verycd.9wee.com/modules/refresh_team_scene_data.php?action=refresh&timeMark=1280047930&time=41&timeStamp=1280047781563&callback_func_name=fnInitTeamSceneData
	public static final String SCENE="";
	//http://s4.verycd.9wee.com/modules/role_slavery.php?timeStamp=1280199739437&callback_func_name=ajaxCallback&callback_obj_name=dlg_sociality
	public static final String SLAVY_MASTER = "modules/role_slavery.php?callback_func_name=ajaxCallback&callback_obj_name=dlg_sociality";
	//http://s4.verycd.9wee.com/modules/role_slavery.php?act=comfort_submit&rand=1280199942890&timeStamp=1280199919046
	public static final String COMFORT_SLAVY = "modules/role_slavery.php?act=comfort_submit";
	//宣传武馆
	//http://s4.verycd.9wee.com/modules/role_slavery.php?act=pain_submit&rand=1280199951562&timeStamp=1280199943328
	public static final String PAIN_SLAVY = "modules/role_slavery.php?act=pain_submit";	
	//http://s4.verycd.9wee.com/modules/role_slavery.php?timeStamp=1280242663691&callback_func_name=ajaxCallback&callback_obj_name=dlg_sociality
	public static final String GET_SLAVY_MASTER = "modules/role_slavery.php?callback_func_name=ajaxCallback&callback_obj_name=dlg_sociality";
	//http://s4.verycd.9wee.com/modules/day_weals_activity.php?act=weal&id=1rand=1280246976709&timeStamp=1280246968613&callback_func_name=ajaxCallback
	public static final String DAILY_WEALS = "modules/day_weals_activity.php?act=weal&callback_func_name=ajaxCallback&id=1";
	//http://s4.verycd.9wee.com/modules/day_weals.php?act=show&rand=1284254922646&timeStamp=1284254912950&callback_func_name=ajaxCallback&callback_obj_name=dlg_view_weals
	public static final String SHOW_DAILY_WEALS = "modules/day_weals.php?act=show&callback_func_name=ajaxCallback&callback_obj_name=dlg_view_weals";
	//渑池演武厅奖励
	//http://s4.verycd.9wee.com/modules/warrior.php?act=arena&timeStamp=1280322179334&callback_func_name=callback_load_content&callback_obj_name=content
	public static final String MIAN_CHI = "modules/warrior.php?act=arena&callback_func_name=callback_load_content&callback_obj_name=content";
	//http://s4.verycd.9wee.com/modules/warrior.php?act=arena&op=get_prise&arena_key=9_2_1280246400&team_mode=0&timeStamp=1280321831079
	public static final String MIAN_CHI_WEAL="modules/warrior.php?act=arena&op=get_prise";
	//荣誉换经验
	//http://s4.verycd.9wee.com/modules/duel.php?act=glory&op=buy&itemID=3&timeStamp=1280648912568&callback_func_name=callbackFnBusGloryReward
	//http://s4.verycd.9wee.com/modules/duel.php?act=glory&op=buy&itemID=15&timeStamp=1280560866420&callback_func_name=callbackFnBusGloryReward
	public static final String GLORY_TO_JING_YAN = "modules/duel.php?act=glory&op=buy&callback_func_name=callbackFnBusGloryReward&itemID=";
	//换紫玉
	//http://s4.verycd.9wee.com/modules/duel.php?act=glory&op=buy&itemID=62&itemNum=1&timeStamp=1280560866420
	public static final String GLORY_TO_ZI_YU = "modules/duel.php?act=glory&op=buy&itemID=62&itemNum=1";
	public static final String GLORY_TO_ZU_MU = "modules/duel.php?act=glory&op=buy&itemID=63&itemNum=1";
	//http://s4.verycd.9wee.com/modules/duel.php?act=glory&timeStamp=1280649008972&callback_func_name=callback_load_content&callback_obj_name=content
	public static final String GLORY_TREE = "modules/duel.php?act=glory&callback_func_name=callback_load_content&callback_obj_name=content";
	//http://s4.verycd.9wee.com/modules/warrior.php?act=guestroom&timeStamp=1280718623671&callback_func_name=callback_load_content&callback_obj_name=content
	public static final String CUSTOM_ROOM="modules/warrior.php?act=guestroom&callback_func_name=callback_load_content&callback_obj_name=content";
	public static final Pattern canRoomSleep = Pattern.compile("weal_guestroom_restore \\( '(\\d+)',");
	//http://s4.verycd.9wee.com/modules/warrior.php?act=guestroom&op=restore&id=4&isfree=1&timeStamp=1280719522625&callback_func_name=warrior_common_callback
	public static final String ROOM_WEAL = "modules/warrior.php?act=guestroom&op=restore&isfree=1&callback_func_name=warrior_common_callback&id=";
	//免费气血包
	//http://s4.verycd.9wee.com/modules/buy_pool.php?act=weal&rand=1284254944330&timeStamp=1284254912950&callback_func_name=callback_weal_buy_pool
	//http://s4.verycd.9wee.com/modules/buy_pool.php?act=weal&rand=1282913768192&timeStamp=1282913719064&callback_func_name=callback_weal_buy_pool
	public static final String QI_XUE_BAO = "modules/buy_pool.php?act=weal&callback_func_name=callback_weal_buy_pool";
	
	public static Pattern p = Pattern.compile("<div class=\"city_scene_name\">(\\S+?国|渑池)\\s*?</div>");
	private static Pattern wuGuan = Pattern.compile("武馆战打斗极其混乱");
	public static Pattern slavy = Pattern.compile("<a href=\"javascript:void\\(0\\);\" onclick=\"view_role \\( (\\d+) \\)\" title=\"(\\S+?)\">(\\S+?)</a>");
	public static Pattern slavyMaster = Pattern.compile("我的主人：<a href=\"javascript:void\\(0\\);\" onclick=\"view_role \\( (\\d+) \\)\">");
	public static Pattern mianChiWeal = Pattern.compile("<a href=\"javascript:void\\(0\\);\" onclick=\"arena_get_prise \\( '(\\S+?)', '(\\d+)' \\)\">领取</a>");
	public static Pattern jingYan = Pattern.compile("fnBusGloryReward\\( (\\d+), 'function', '灵台清明', (\\d+), (\\d+) \\);\">购买</a>");
	public static Pattern fuBen = Pattern.compile("map_name\":\"(\\S+?)\"");;
	public static Pattern buyPool = Pattern.compile("免费\\S+?包");
	
	//http://s4.verycd.9wee.com/modules/awards.php?timeStamp=1282995533899&callback_func_name=ajaxCallback&callback_obj_name=dlg_awards
	public static final String AWARD="modules/awards.php?callback_func_name=ajaxCallback&callback_obj_name=dlg_awards";
	//awards_view ( 110573 )">辎重营荣誉礼包</a>
	public static Pattern awards = Pattern.compile("awards_view \\( (\\d+) \\)\">[全民福利礼包|辎重营荣誉礼包|中秋.*?月饼礼包].*?([立即领取|已领取])",Pattern.DOTALL);
	//http://s4.verycd.9wee.com/modules/awards.php?act=fetch&award_id=119709&timeStamp=1282995924182&callback_func_name=awards_fetch_callback
	public static final String GET_AWARD = "modules/awards.php?act=fetch&callback_func_name=awards_fetch_callback&award_id=";
	//http://s4.verycd.9wee.com/modules/warrior.php?act=arena&op=join&part=1&timeStamp=1283176271321
	public static final String GUO_DU = "modules/warrior.php?act=arena&op=join&part=1";
	//http://s4.verycd.9wee.com/modules/warrior.php?act=arena&op=get_prise&arena_key=5_1_1283184000&team_mode=0&timeStamp=1283260822448
	public static final String GET_GUO_DU_AWARD = "modules/warrior.php?act=arena&op=get_prise&team_mode=0&arena_key=";
	//http://s4.verycd.9wee.com/modules/warrior.php?act=arena&timeStamp=1283261017202&callback_func_name=callback_load_content&callback_obj_name=content
	public static final String GOTO_GUO_DU_YAN_WU = "modules/warrior.php?act=arena&callback_func_name=callback_load_content&callback_obj_name=content";
	public static Pattern guoDuPrise = Pattern.compile("arena_get_prise \\( '(\\S+?)', '0' \\)\">领取");

	public static void guoDu(User user){
		if(!user.isNeedGuoDu()){
			return;
		}
		String url = user.getUrl()+GUO_DU+Tools.getTimeStamp(true);
		String data = "callback_func_name=warrior_common_callback";
		String page = PageService.postPage(url, data, user);
		if(Tools.success(page)){
			logger.info(user.getRoleName()+"报名国都演武厅");
		}
	}
	public static void getGuoDuAward(User user){
		String data = "callback_func_name=warrior_common_callback";
		String url = user.getUrl()+GOTO_GUO_DU_YAN_WU+Tools.getTimeStamp(true);
		String page = PageService.getPageWithCookie(url, user);
		if(Tools.success(page)){
			Matcher m = guoDuPrise.matcher(page);
			if(m.find()){
				url = user.getUrl()+GET_GUO_DU_AWARD+m.group(1)+Tools.getTimeStamp(true);
				page = PageService.getPage(url, user);
				if(Tools.success(page)){
					logger.info(user.getRoleName()+"领取国都演武厅奖励成功");
				}
			}
		}
	}
	public static void getAwards(User user){
		if(!user.isNeedGetAward()){
			return ;
		}
		String url = user.getUrl()+AWARD+Tools.getTimeStamp(true);
		String page = PageService.getPageWithCookie(url, user);
		Matcher m = awards.matcher(page);
		while(m.find()){
			if(m.group(2).equals("已领取")){
				return;
			}
			url = user.getUrl()+GET_AWARD+m.group(1)+Tools.getTimeStamp(true);
			page = PageService.getPageWithCookie(url, user);
			if(Tools.success(page)){
				logger.info(user.getRoleName()+"领取全民福利成功");
			}
		}
	}
	
	public static String getScenes(User user){
		String url = user.getUrl()+POSITION+Tools.getTimeStamp(false);
		return PageService.getPageWithCookie(url, user);
	}
	public static boolean atHome(User user){
		String page = getScenes(user);
		Matcher m = p.matcher(page);
		if(m.find()){
			if("渑池".equals(m.group(1))){
				return false;
			}else{
				return true;
			}
		}
		return false;
	}
	
	public static boolean atFuBen(User user){
		String page = getScenes(user);
		Matcher m = fuBen.matcher(page);
		if(m.find()){
			String map = Tools.hexToString(m.group(1));
			if("函谷关".equals(map) || "秦始皇陵".equals(map)){
				return true;
			}
		}
		m = p.matcher(page);
		if(m.find()){
			if("渑池战场".equals(m.group(1))){
				return true;
			}
		}
		return false;
	}
	public static boolean atMianChi(User user){
		String page = getScenes(user);
		Matcher m = p.matcher(page);
		if(m.find()){
			if("渑池".equals(m.group(1))){
				return true;
			}
		}
		return false;
	}
	public static boolean inWuGuan(User user){
		String page = getScenes(user);
		Matcher m = wuGuan.matcher(page);
		return m.find();
	}
	public static boolean slavy(User user){
		
		String url = user.getUrl()+GET_SLAVY_MASTER+Tools.getRandAndTime();
		String page = PageService.getPageWithCookie(url, user);
		Matcher m = slavyMaster.matcher(page);
		if(m.find()){
			try{
			url = user.getUrl()+SLAVY+Tools.getRandAndTime();
			String data = "fawn_type=10&boss_id="+m.group(1)+"&type=3&callback_func_name=callbackFnSlaveOptSubmit";
			page = PageService.postPage(url,data, user);
			if(page == null){
				return false;
			}
			if(Tools.success(page)){
				logger.info(user.getRoleName()+"讨好主人成功");
				return true;
			}
			}catch(Exception e){}
		}
		return false;
	}
	public static boolean activeSlavys(User user){
		String url = user.getUrl()+SLAVY_MASTER+Tools.getTimeStamp(true);
		String page = PageService.getPageWithCookie(url, user);
		Matcher m = slavy.matcher(page);
		while(m.find()){
			comfortSlavy(user, m.group(1), m.group(2));
			painSlavy(user, m.group(1), m.group(2));
		}
		slavy(user);
		return true;
	}
	public static boolean comfortSlavy(User user,String id,String name){
		try{
			String url = user.getUrl()+COMFORT_SLAVY+Tools.getRandAndTime();
			String data = "comfort_type=6&slave_id="+id+"&type=2&callback_func_name=callbackFnSlaveOptSubmit";
			String page = PageService.postPage(url, data,user);
			
			if(Tools.success(page)){
				logger.info(user.getRoleName()+"安抚"+name+"成功");
				return true;
			}else{
				return false;
			}
		}catch(Exception e){
			return false;
		}
	}
	public static boolean painSlavy(User user,String id,String name){
		try{
			// /modules/role_slavery.php?act=pain_submit&rand=1280639390157&timeStamp=1280639377847
			String url = user.getUrl()+PAIN_SLAVY+Tools.getRandAndTime();
			//关小黑屋
			//scene_id=0&scene_type=0&slavery_scene_id=2&slavery_scene_type=durable&pain_type=17&slave_id=22051&type=1&callback_func_name=callbackFnSlaveOptSubmit
			String data = "scene_id=0&scene_type=0&slavery_scene_id=2&slavery_scene_type=durable&pain_type=17&slave_id="+id+"&type=1&callback_func_name=callbackFnSlaveOptSubmit";
			String page = PageService.postPage(url, data,user);
			
			if(Tools.success(page)){
				logger.info(user.getRoleName()+"关小黑屋"+name+"成功");
				return true;
			}else{
				int nowHour = Tools.getNowHour();
				if(nowHour >= user.getBlackStartTime() && nowHour < user.getBlackEndTime()){
					logger.info(user.getRoleName()+"关小黑屋失败，等待");
					return true;
				}
				final String XUAN_CHUAN = "1";
				final String BUILD = "2";
				
				String painType = user.getPainType();
				if(XUAN_CHUAN.equals(painType)){
					//宣传武馆
					data = "scene_id=0&scene_type=0&pain_type=13&slavery_scene_id=2&slavery_scene_type=durable&slave_id="+id+"&type=1&callback_func_name=callbackFnSlaveOptSubmit";
					page = PageService.postPage(url, data,user);
					if(Tools.success(page)){
						logger.info(user.getRoleName()+"折磨奴隶"+name+"宣传武馆成功");
						return true;
					}else{
						return false;
					}
				}else{
					//修建武馆-玄武scene_id 2 玄武 3 朱雀 4 白虎 5 青龙
					//scene_id=0&scene_type=0&pain_type=14&slavery_scene_id=2&slavery_scene_type=durable&slave_id=29433&type=1&callback_func_name=callbackFnSlaveOptSubmit
					
					int door = 2;
					if(user.getBuildDoor() > 5 || user.getBuildDoor() < 2){
						door = 2;
					}else{
						door = user.getBuildDoor();
					}
					data = "scene_id=0&scene_type=0&pain_type=14&slavery_scene_id="+door+"&slavery_scene_type=durable&slave_id="+id+"&type=1&callback_func_name=callbackFnSlaveOptSubmit";
					page = PageService.postPage(url, data, user);
					if(Tools.success(page)){
						logger.info(user.getRoleName()+"折磨奴隶"+name+"修建"+getDoorName(door)+"成功");
						return true;
					}else{
						return false;
					}
					
				}
			}
		}catch(Exception e){
			return false;
		}
	}
	private static String getDoorName(int i){
		switch(i){
		case 2:return "玄武门";
		case 3:return "朱雀门";
		case 4:return "白虎门";
		case 5:return "青龙门";
		}
		return String.valueOf(i);
	}
	public static boolean dailyWeals(User user){
		String url = user.getUrl()+DAILY_WEALS+Tools.getRandAndTime().substring(1);
		String page = PageService.getPageWithCookie(url, user);
		if(Tools.success(page)){
			logger.info(user.getRoleName()+"每日福利领取成功");
			return true;
		}else{
			return false;
		}
	}
	public static boolean buyPool(User user){
		String url = user.getUrl()+SHOW_DAILY_WEALS+Tools.getRandAndTime().substring(1);
		String page = PageService.getPageWithCookie(url, user);
		Matcher m = buyPool.matcher(page);
		if(m.find()){
			url = user.getUrl()+QI_XUE_BAO+Tools.getRandAndTime();
			page = PageService.getPageWithCookie(url, user);
			if(Tools.success(page)){
				logger.info(user.getRoleName()+"使用"+m.group());
				return true;
			}
		}
		return false;
	}
	public static boolean mianChiWeals(User user){	
		Calendar c = Calendar.getInstance();
		int hour = c.get(Calendar.HOUR_OF_DAY);
		if(hour > 17){
			String page = intoMianChiYanWuTing(user);
			Matcher m = mianChiWeal.matcher(page);
			try{
			if(m.find()){
				//&arena_key=9_2_1280246400&team_mode=0&timeStamp=1280321831079
				String url = user.getUrl()+MIAN_CHI_WEAL+"&arena_key="+m.group(1)+"&team_mode="+m.group(2)+Tools.getTimeStamp(true);
				String data = "callback_func_name=warrior_common_callback";
				String p = PageService.postPage(url, data,user);
				if(Tools.success(p)){
					logger.info(user.getRoleName()+"领取渑池演武厅奖励成功");
					return true;
				}
			}
			}catch(Exception e){}
		}
		return false;
	}
	private static String intoMianChiYanWuTing(User user){
		String url = user.getUrl()+MIAN_CHI+Tools.getTimeStamp(true);
		String page = PageService.getPageWithCookie(url, user);
		return page;
	}
	public static boolean buyGlory(User user){
		String s = user.getGloryBuy();
		if(s == null || s.trim().length() == 0 || "0".equals(s)){
			return false;
		}
		String url = user.getUrl()+GLORY_TREE+Tools.getTimeStamp(true);
		String page = PageService.getPageWithCookie(url, user);
		Matcher m = jingYan.matcher(page);
		String itemId = null;
		if(m.find()){
			itemId = m.group(1);
		}else{
			return false;
		}		
		String type = null;
		if("1".equals(s)){
			url = user.getUrl()+GLORY_TO_JING_YAN+itemId+Tools.getTimeStamp(true);
			type = "经验";
		}else if("2".equals(s)){
			url = user.getUrl()+GLORY_TO_ZI_YU+Tools.getTimeStamp(true);
			type = "紫玉";
		}else if("3".equals(s)){
			url = user.getUrl()+GLORY_TO_ZU_MU+Tools.getTimeStamp(true);
			type = "祖母绿";
		}else
			return false;
		
		page = PageService.getPageWithCookie(url, user);
		if(Tools.success(page)){
			logger.info(user.getRoleName()+"荣誉换"+type+"成功");
			return true;
		}
		return false;
	}
	public static boolean roomWeal(User user){
		String url = user.getUrl()+CUSTOM_ROOM+Tools.getTimeStamp(true);
		String page = PageService.getPageWithCookie(url, user);
		Matcher m = canRoomSleep.matcher(page);
		if(m.find()){
			String id = m.group(1);
			url = user.getUrl()+ROOM_WEAL+id+Tools.getTimeStamp(true);
			page = PageService.getPageWithCookie(url, user);
			if(Tools.success(page)){
				logger.info(user.getRoleName()+"免费使用客房福利");
				return true;
			}
		}
		return false;
	}
}
