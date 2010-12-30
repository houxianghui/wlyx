package com.blue.common;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.blue.team.WuGuan;
import com.blue.tools.PageService;
import com.blue.tools.Tools;
import com.blue.warrior.Warrior;

public class Portal {
	private static Logger logger = Logger.getLogger(Portal.class);
	//http://s4.verycd.9wee.com/modules/role_info.php?timeStamp=1279701586234&callback_func_name=callback_load_content&callback_obj_name=content
	public static final String USER_INFO="modules/role_info.php?callback_func_name=callback_load_content&callback_obj_name=content";
	//http://s4.verycd.9wee.com/modules/scenes_role.php?sid=0&timeStamp=1279868148062&callback_func_name=switch_scene_callback
	public static final String GO_HOME = "modules/scenes_role.php?sid=0&callback_func_name=switch_scene_callback";
	public static final String MIAN_CHI = "modules/scene_walk.php?action=world_move&scene_id=164&callback_func_name=callbackFnWorldTransport";
	//http://s4.verycd.9wee.com/modules/revival.php?revival_type=2&timeStamp=1280717098093&callback_func_name=revival_callback
	public static final String REVIVE = "modules/revival.php?revival_type=2&callback_func_name=revival_callback";
	private static Pattern scene = Pattern.compile("var now_scene_id = (\\d+)"); 
	private static Pattern zhuangTai = Pattern.compile("状态：.*?>(正常|死亡|训练中|战斗中|修炼中|虚弱|运输中|授艺中)",Pattern.DOTALL);
	private static Pattern p = Pattern.compile("等级：<span class=highlight>Lv.(\\d+)");
	private static Pattern point = Pattern.compile("点精力\">(\\d+)</span> / ");
	private static Pattern name = Pattern.compile("<span class=\"highlight\" title=\"查看改名记录\"><a onclick=\"dialog.open\\('/modules/role_name.php', 'dlg_change_name' \\);\">(\\S+?)</a></span>");
	/*
	 * <li><div class="attr_hr_lite" title="命中率：额外 <span class=highlight>+0%</span> 的几率命中对手">命中率 <span class="highlight small_font">+0%</span></div></li>
		<li><div class="attr_dr_lite" title="躲闪率：<span class=highlight>4%</span> 的几率闪避对手的攻击">躲闪率 <span class="highlight small_font">4%</span></div></li>
		<li><div class="attr_ds_lite" title="暴击率：<span class=highlight>1%</span> 的几率给对手造成 1.5 倍伤害">暴击率 <span class="highlight small_font">1%</span></div></li>
		<li><div class="attr_id_lite" title="破击率：<span class=highlight>0%</span> 的几率无视对手防御">破击率 <span class="highlight small_font">0%</span></div></li>
	 */
	private static Pattern attribute = Pattern.compile("命中率：额外 <span class=highlight>\\+(\\d+)%</span>.*?躲闪率：<span class=highlight>(\\d+)%</span>.*?暴击率：<span class=highlight>(\\d+)%</span>.*?破击率：<span class=highlight>(\\d+)%",Pattern.DOTALL);
	
	private static Pattern HP = Pattern.compile("<div class=\"point_bar_bg\" title=\"当前气血：<span class=highlight>(\\d+) / (\\d+)</span>");
	private static Pattern MP = Pattern.compile("<div class=\"point_bar_bg\" title=\"当前内息：<span class=highlight>(\\d+) / (\\d+)</span>");
	/*
	 * <div>你对 <span class="highlight">元兽</span> 发动了攻击！【<a href="javascript:void(0);" onclick="view_combat ( '223019181188' )">查看</a>】</div>

	<span class="date" style="float:right">12月30日 01:09</span>
	 */
	private static Pattern beating = Pattern.compile("你对.*?发动了攻击.*?月\\d+日 (\\d+):(\\d+)</span>",Pattern.DOTALL);
	//http://s4.verycd.9wee.com/modules/message.php?timeStamp=1293642534287&act=events&callback_func_name=ajaxCallback&callback_obj_name=message_list
	private static String BEAT_INFO = "modules/message.php?&act=events&callback_func_name=ajaxCallback&callback_obj_name=message_list";
	public static int getIntValue(String s){
		int i = 0;
		while(i < s.length()){
			if(s.charAt(i) != '0'){
				return Integer.parseInt(s.substring(i));
			}
			i++;
		}
		return -1;
	}
	public static boolean isBeating(User user){
		String url = user.getUrl()+BEAT_INFO+Tools.getTimeStamp(true);
		String page = PageService.getPageWithCookie(url, user);
		Matcher m = beating.matcher(page);
		
		if(m.find()){			
			
			int hour = getIntValue(m.group(1));
			int min = getIntValue(m.group(2));
			int nowHour = Tools.getNowHour();
			int nowMin = Tools.getNowMinute();
			if(nowHour==0){
				if(hour==0){
					if(nowMin-min < 3){
						return true;
					}
				}else{
					if(nowHour+24-hour==1){
						if(nowMin+60-min < 3){
							return true;
						}
					}
				}
				
			}else{
				if(nowHour-hour == 0){
					if(nowMin-min < 3){
						return true;
					}
				}else if(nowHour-hour == 1){
					if(nowMin+60-min < 3){
						return true;
					}
				}
			}
		}
		return false;
	}
	public static void setUserAttribute(User user){
		String url = user.getUrl()+USER_INFO+Tools.getTimeStamp(true);
		String page = PageService.getPageWithCookie(url, user);
		Matcher m = attribute.matcher(page);
		if(m.find()){
			user.setMingZhong(Integer.parseInt(m.group(1)));
			user.setDuoShan(Integer.parseInt(m.group(2)));
			user.setBaoJi(Integer.parseInt(m.group(3)));
			user.setPoJi(Integer.parseInt(m.group(4)));
		}
		user.getAttribMap().put("躲闪率", user.getDuoShan());
		user.getAttribMap().put("命中率", user.getMingZhong());
		user.getAttribMap().put("暴击率", user.getBaoJi());
		user.getAttribMap().put("破击率", user.getPoJi());
	}
	public static boolean setUserInfo(User user){
		user.setKillMonstorOnce("24");
		String url = user.getUrl();
		String page = PageService.getPageWithCookie(url, user);
		while(!Tools.success(page)){
			try{
				user.login(true);
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
		m = name.matcher(page);
		if(m.find()){
			user.setRoleName(m.group(1));
		}else{
			user.setRoleName("------");
		}
		m = zhuangTai.matcher(page);
		if(m.find()){
			user.setStatus(m.group(1));
			if("死亡".equals(m.group(1))){
				revive(user);
			}
			if((!"正常".equals(m.group(1)) && !"虚弱".equals(m.group(1))) || Monitor.inWuGuan(user)){
				user.setCanMove(false);
			}else{
				user.setCanMove(true);
			}
		}
		if(isBeating(user)){
			user.setCanMove(false);
		}
		m = HP.matcher(page);
		if(m.find()){
			user.setMaxHP(Integer.parseInt(m.group(2)));
			user.getAttribMap().put("气血", user.getMaxHP());
		}
		m = MP.matcher(page);
		if(m.find()){
			user.setMaxMP(Integer.parseInt(m.group(2)));
			user.getAttribMap().put("内息", user.getMaxMP());
		}
		int now = getNow();
		
		if(user.getBeginTime() > now || now >= user.getEndTime()){
			user.setShouldKillMonstor(false);
		}else{
			if(Integer.parseInt(user.getPoint()) > user.getSavePoint() && !Monitor.inWuGuan(user) && !Warrior.need10HoursTrain() && !Monitor.atFuBen(user)){
				user.setShouldKillMonstor(true);
			}else{
				user.setShouldKillMonstor(false);
			}
		}
//		setTeamId(user);
		return Tools.success(page);
	}
	public static void setTeamId(User user){
		String url = user.getUrl()+WuGuan.MY_TEAM+Tools.getTimeStamp(true);
		String page = PageService.getPageWithCookie(url, user);
		Matcher m = WuGuan.myTeamId.matcher(page);
		if(m.find()){
			user.setTeamId(m.group(1));
		}
	}
	public static void revive(User user){
		String url = user.getUrl()+REVIVE+Tools.getTimeStamp(true);
		String page = PageService.getPageWithCookie(url, user);
		if(Tools.success(page)){
			logger.info(user.getRoleName()+"免费回城复活成功");
		}
	}
	private static int getNow(){
		Calendar c = Calendar.getInstance();
		return c.get(Calendar.HOUR_OF_DAY);
	}
	public static boolean goHome(User user){
		String url = null;
		int now = getNow();
		if(now > 12 || now < 8){
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
		if(Monitor.atFuBen(user)){
			logger.info(user.getRoleName()+"正在副本，停止移动");
			return true;
		}
		if(Monitor.isDefeatingMianChi(user)){
			logger.info(user.getRoleName()+"正在渑池防守战，停止移动");
			return true;
		}
		if(isBeating(user)){
			logger.info(user.getRoleName()+"正在连续打怪，停止移动");
			return true;
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
