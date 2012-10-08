package com.blue.daily;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.blue.common.Portal;
import com.blue.common.User;
import com.blue.tools.PageService;
import com.blue.tools.Tools;

public class MianChiLingPai {
	private static Logger logger = Logger.getLogger(MianChiLingPai.class);
	public static final String ENTER_SEC = "modules/scenes_role.php?callback_func_name=switch_scene_callback&sid=";
	private static Pattern p = Pattern.compile("9000");
	//http://s4.verycd.9wee.com/modules/npc.php?npc_id=3001&rand=1294320817334&timeStamp=1294320814981&callback_func_name=ajaxCallbackNoClose&callback_obj_name=dlg_view_npc
	private static String DIALOG = "modules/npc.php?callback_func_name=ajaxCallbackNoClose&callback_obj_name=dlg_view_npc&npc_id=";
	//http://s4.verycd.9wee.com/modules/role_mission.php?act=detail&function=branch&id=100027&op=accept&timeStamp=1294315226739&callback_func_name=mission_common_callback
	//http://s4.verycd.9wee.com/modules/role_mission.php?act=detail&function=branch&id=100024&op=accept&timeStamp=1294319361713&callback_func_name=mission_common_callback
	private static String ACCEPT = "modules/role_mission.php?act=detail&function=branch&op=accept&callback_func_name=mission_common_callback&id=";
	//http://s4.verycd.9wee.com/modules/npc.php?npc_id=3002&rand=1300971641455&mission_id=100033&city_scene=&timeStamp=1300971640886&callback_func_name=ajaxCallbackNoClose&callback_obj_name=dlg_view_npc
	//http://s4.verycd.9wee.com/modules/role_mission.php?act=rewards&function=branch&id=100033&timeStamp=1300972541195&callback_func_name=mission_common_callback
	private static String REWARD = "modules/role_mission.php?act=rewards&function=branch&callback_func_name=mission_common_callback&id=";
	//http://s4.verycd.9wee.com/modules/role_mission.php?act=rewards&function=branch&id=100027&timeStamp=1294315257502&callback_func_name=mission_common_callback
	//http://s4.verycd.9wee.com/modules/scenes_role.php?sid=9000&timeStamp=1294315275776&callback_func_name=switch_scene_callback
	//http://s4.verycd.9wee.com/modules/scene_walk.php?action=world_move&scene_id=9000&rand=1294318950489&timeStamp=1294318926887&callback_func_name=callbackFnWorldTransport
	private static String LIAN_ZONG = "modules/scene_walk.php?action=world_move&scene_id=9000&callback_func_name=callbackFnWorldTransport";
	//http://s4.verycd.9wee.com/modules/scenes_role.php?sid=9000&timeStamp=1294323155578&callback_func_name=switch_scene_callback
	private static String BACK = "modules/scenes_role.php?sid=9000&callback_func_name=switch_scene_callback";
	private static Pattern mission = Pattern.compile("view_mission \\( 'branch', (\\d+) \\)",Pattern.DOTALL);
	public static void getTongTian(User user){
		StringBuffer url = new StringBuffer();
		url.append(user.getUrl()+"modules/role_mission.php?act=rewards&function=branch&id=100033"+Tools.getTimeStamp(true));
		PageService.getPageWithCookie(url.toString(), user);
		url = new StringBuffer(user.getUrl()+"modules/role_mission.php?act=detail&function=branch&id=100024"+Tools.getTimeStamp(true));
		PageService.getPageWithCookie(url.toString(), user);
		url = new StringBuffer(user.getUrl()+"modules/role_mission.php?act=rewards&function=branch&id=100024"+Tools.getTimeStamp(true));
		PageService.getPageWithCookie(url.toString(), user);
	}
	public static void moveToLianZongXiYing(User user){
		String ids[] = {"9003","9007","9012"};
		String npc[] = {"3000","3001","3002"};
		String field[] = {"梅岭乡","西风堡","白宇堡"};
		String url = user.getUrl()+LIAN_ZONG+Tools.getRandAndTime();
		PageService.getPageWithCookie(url, user);
		logger.info(user.getRoleName()+"准备领取令牌");
		for(int i = 0;i < ids.length;i++){
			boolean flag = false;
			int count = 3;
			while(!flag && count>0){
				url = user.getUrl()+ENTER_SEC+ids[i]+Tools.getTimeStamp(true);
				String page = PageService.getPageWithCookie(url, user);
				Matcher m = p.matcher(page);
				if(m.find()){
					flag = true;
				}
				count--;
			}
			url = user.getUrl()+DIALOG+npc[i]+Tools.getRandAndTime();
			String page = PageService.getPageWithCookie(url, user);
			Matcher m = mission.matcher(page);
			String taskId = "";
			if(m.find()){
				taskId = m.group(1);
			}
			//accept task
			//http://s4.verycd.9wee.com/modules/npc.php?npc_id=3001&rand=1295970709382&timeStamp=1295970701024&callback_func_name=ajaxCallbackNoClose&callback_obj_name=dlg_view_npc
			//http://s4.verycd.9wee.com/modules/npc.php?npc_id=3001&rand=1295970709753&mission_id=100024&city_scene=&timeStamp=1295970701024&callback_func_name=ajaxCallbackNoClose&callback_obj_name=dlg_view_npc
			
			url = user.getUrl()+ACCEPT+taskId+Tools.getTimeStamp(true);
			PageService.getPageWithCookie(url, user);
			logger.info(user.getRoleName()+"领取任务"+i+"成功");
			url = user.getUrl()+"modules/npc.php?city_scene=&callback_func_name=ajaxCallbackNoClose&callback_obj_name=dlg_view_npc";
			url+="&npc_id="+npc[i]+"&mission_id="+taskId+Tools.getRandAndTime();
			PageService.getPageWithCookie(url, user);
			url = user.getUrl()+REWARD+taskId+Tools.getTimeStamp(true);
			PageService.getPageWithCookie(url, user);
			logger.info(user.getRoleName()+"领取令牌"+i+"成功");
			url = user.getUrl()+BACK+Tools.getTimeStamp(true);
			PageService.getPageWithCookie(url, user);
			getTongTian(user);
		}
//		Portal.goHome(user);
	}

	public static void getLingPai(User user){
//		if(Tools.needGetLingPai() && user.isCanMove()){
//			int now = Tools.getNowHour();
//			if(now >= 5 && now <=7){
//				MianChiLingPai.moveToLianZongXiYing(user);
//			}
//		}
	}
}
