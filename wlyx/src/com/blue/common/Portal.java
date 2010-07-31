package com.blue.common;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.blue.tools.PageService;
import com.blue.tools.Tools;

public class Portal {
	private static Logger logger = Logger.getLogger(Portal.class);
	//http://s4.verycd.9wee.com/modules/role_info.php?timeStamp=1279701586234&callback_func_name=callback_load_content&callback_obj_name=content
	public static final String USER_INFO="modules/role_info.php?callback_func_name=callback_load_content&callback_obj_name=content";
	//http://s4.verycd.9wee.com/modules/scenes_role.php?sid=0&timeStamp=1279868148062&callback_func_name=switch_scene_callback
	public static final String GO_HOME = "modules/scenes_role.php?sid=0&callback_func_name=switch_scene_callback";
	public static final String MIAN_CHI = "modules/scene_walk.php?action=world_move&scene_id=164&callback_func_name=callbackFnWorldTransport";
	
	private static Pattern scene = Pattern.compile("var now_scene_id = (\\d+)"); 
	private static Pattern zhuangTai = Pattern.compile("状态：.*?>(正常|死亡|训练中|战斗中|修炼中|虚弱|运输中|授艺中)",Pattern.DOTALL);
	
	private static Pattern p = Pattern.compile("等级：<span class=highlight>Lv.(\\d+)");
	private static Pattern point = Pattern.compile("点精力\">(\\d+)</span> / ");
	private static Pattern name = Pattern.compile("<span class=\"highlight\" title=\"查看改名记录\"><a onclick=\"dialog.open\\('/modules/role_name.php', 'dlg_change_name' \\);\">(\\S+?)</a></span>");
	
	
	public static boolean setUserInfo(User user){
		user.setKillMonstorOnce("24");
		String url = user.getUrl();
		String page = PageService.getPageWithCookie(url, user);
		if(!Tools.success(page)){
			try{
				user.login();
				page = PageService.getPageWithCookie(url, user);
			}catch(Exception e){
				logger.error(user.getRoleName()+"登陆失败");
			}
		}
		Matcher m = p.matcher(page);
		if(m.find()){
			user.setLevel(m.group(1));
		}
		m = point.matcher(page);
		if(m.find()){
			user.setPoint(m.group(1));
		}
		if(user.getSavePoint() == 0){
			user.setSavePoint(20);
		}
		m = zhuangTai.matcher(page);
		if(m.find()){
			user.setStatus(m.group(1));
			if(!"正常".equals(m.group(1)) && !"虚弱".equals(m.group(1))){
				user.setCanMove(false);
			}else{
				user.setCanMove(true);
			}
		}
		m = name.matcher(page);
		if(m.find()){
			user.setRoleName(m.group(1));
		}else{
			user.setRoleName("------");
		}
		int now = getNow();
		if(user.getBeginTime() > now || now >= user.getEndTime()){
			user.setShouldKillMonstor(false);
		}else{
			if(Integer.parseInt(user.getPoint()) > user.getSavePoint()){
				user.setShouldKillMonstor(true);
			}else{
				user.setShouldKillMonstor(false);
			}
		}
		return Tools.success(page);
	}
	private static int getNow(){
		Calendar c = Calendar.getInstance();
		return c.get(Calendar.HOUR_OF_DAY);
	}
	public static boolean goHome(User user){
		String url = null;
		if(getNow() > 12){
			url = user.getUrl()+MIAN_CHI+Tools.getTimeStamp(true);
			if(Monitor.atMianChi(user)){
				return true;
			}
		}else{
			
			url = user.getUrl()+GO_HOME+Tools.getTimeStamp(true);
			if(Monitor.atHome(user)){
				return true;
			}
		}
		
		String page = PageService.getPageWithCookie(url, user);
		return Tools.success(page);
	}
	public static String getSceneId(User user){
		String url = user.getUrl();
		String page = PageService.getPageWithCookie(url, user);
		Matcher m = scene.matcher(page);
		if(m.find()){
			return m.group(1);
		}
		return null;
	}
	
}
