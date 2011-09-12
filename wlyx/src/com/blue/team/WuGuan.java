package com.blue.team;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.blue.beauty.Beauty;
import com.blue.common.Monitor;
import com.blue.common.Portal;
import com.blue.common.User;
import com.blue.monstor.Monstor;
import com.blue.tools.PageService;
import com.blue.tools.Tools;

public class WuGuan {
	public static double MIN_PERCENT = 0.4;
	private static Logger logger = Logger.getLogger(WuGuan.class);
	//http://s4.verycd.9wee.com/modules/team.php?act=reduce_durable&rand=1280043809090&timeStamp=1280043576233&callback_func_name=callbackFnTeamSceneReduceDurable
	public static final String TI_GUAN = "modules/team.php?act=reduce_durable&callback_func_name=callbackFnTeamSceneReduceDurable";
	//http://s4.verycd.9wee.com/modules/team.php?act=add_durable&rand=1280047786584&timeStamp=1280047781563&callback_func_name=callbackFnTeamSceneAddDurable
	public static final String HU_GUAN = "modules/team.php?act=add_durable&callback_func_name=callbackFnTeamSceneAddDurable";
	//http://s4.verycd.9wee.com/modules/team.php?act=team_scene_move&tid=61&sid=3&timeStamp=1280050633201&callback_func_name=callbackFnTeamSceneWalk
	//http://s4.verycd.9wee.com/modules/team.php?act=team_scene_move&tid=30&sid=2&timeStamp=1282985514400&callback_func_name=callbackFnTeamSceneWalk
	public static final String MOVE="modules/team.php?act=team_scene_move&sid=2&callback_func_name=callbackFnTeamSceneWalk&tid=";
	//s_now_team_scene_id":"2","s_open_time":"10","s_close_time":"18"
	private static Pattern p = Pattern.compile("s_now_team_scene_id\":\"(\\d+)\",\"s_open_time\":\"(\\d+)\",\"s_close_time\":\"(\\d+)\"");
	public static final String MIAN_CHI_DESTROY = "modules/scene_brick.php?act=down&callback_func_name=callbackfnWarSceneBrick";
	//http://s4.verycd.9wee.com/modules/scene_brick.php?act=add&rand=1281162338117&timeStamp=1281162312191&callback_func_name=callbackfnWarSceneBrick
	public static final String MIAN_CHI_REPAIR = "modules/scene_brick.php?act=add&callback_func_name=callbackfnWarSceneBrick";
	//http://s4.verycd.9wee.com/modules/warrior.php?act=team&timeStamp=1281678437515
	public static final String TEAM_LIST="modules/warrior.php?act=team";
	//http://s4.verycd.9wee.com/modules/warrior.php?act=team&type=credits&page=10&country=0&chk_open_team=0&timeStamp=1283164565356&callback_func_name=ajaxCallback&callback_obj_name=content
	//http://s4.verycd.9wee.com/modules/warrior.php?act=team&timeStamp=1289218876446
	public static final String GET_ALL_TEAM = "modules/warrior.php?act=team&type=credits&country=0&chk_open_team=0&callback_func_name=ajaxCallback&callback_obj_name=content&page=";
	//查看我的武馆
	//http://s4.verycd.9wee.com/modules/team.php?act=my_team&timeStamp=1282922453022&callback_func_name=ajaxCallback&callback_obj_name=dlg_team
	public static final String MY_TEAM = "modules/team.php?act=my_team&callback_func_name=ajaxCallback&callback_obj_name=dlg_team";
	//联盟武馆列表
	//http://s4.verycd.9wee.com/modules/team.php?act=union_team&timeStamp=1282915063961&callback_func_name=ajaxCallback&callback_obj_name=union_team_box
	public static final String UNION_TEAM = "modules/team.php?act=union_team&callback_func_name=ajaxCallback&callback_obj_name=union_team_box";
	//查看武馆情况
	//http://s4.verycd.9wee.com/modules/team.php?act=view_team&team_id=54&timeStamp=1282915063961&callback_func_name=ajaxCallback&callback_obj_name=dlg_view_team
	public static final String VIEW_TEAM = "modules/team.php?act=view_team&callback_func_name=ajaxCallback&callback_obj_name=dlg_view_team&team_id=";
	public static Pattern union = Pattern.compile("onclick=\"view_team \\( '(\\d+)' \\)\">(\\S+?)</a>"); 
	public static Pattern xuanWuMen = Pattern.compile("	<tr><td><span class=\"purple\">玄武门</span></td><td class=\"highlight small_font\" align=\"right\">(\\S+?) / (\\S+?)</td></tr>");
	
	public static Pattern myXuanWu = Pattern.compile("玄武门.*?修建</a></td><td class=\"highlight small_font\" align=\"right\">(\\S+?) / (\\S+?)</td>");
	//fnEnterTeamScene( 56 , 1 , 0)
	public static Pattern myTeamId = Pattern.compile("fnEnterTeamScene\\( (\\d+) , (\\d+) , ()\\d+\\)");
	//http://s4.verycd.9wee.com/modules/team.php?act=go_into_team_scene&team_id=56&scene_id=1&stand_point=0&timeStamp=1282923891973&callback_func_name=callbackFnEnterTeamScene
	public static final String PROTECT_MY_TEAM = "modules/team.php?act=go_into_team_scene&callback_func_name=callbackFnEnterTeamScene&scene_id=1&stand_point=0&team_id=";
	//http://s4.verycd.9wee.com/modules/refresh_team_scene_data.php?action=refresh&timeMark=1282959774&time=10&timeStamp=1282959775563&callback_func_name=fnInitTeamSceneData
	public static final String GET_SCENE="modules/refresh_team_scene_data.php?action=refresh&callback_func_name=fnInitTeamSceneData";
	public static Pattern currTeam = Pattern.compile("team_id\":\"(\\d+)\",\"team_scene_id\":\"(\\d+)\"");
	public static Pattern currXuanWu = Pattern.compile("current_build_durable\":\"(\\d+)\",\"build_max_durable\":\"(\\d+)\"");
	//http://s4.verycd.9wee.com/modules/team.php?act=leave_team_scene&timeStamp=1282963263594&callback_func_name=callbackFnLeaveTeamScene
	public static final String LEAVE_TEAM = "modules/team.php?act=leave_team_scene&callback_func_name=callbackFnLeaveTeamScene";
	//http://s4.verycd.9wee.com/modules/team.php?act=go_into_team_scene&team_id=15&scene_id=1&stand_point=2&timeStamp=1282964761504&callback_func_name=callbackFnEnterTeamScene
	//http://s4.verycd.9wee.com/modules/team.php?act=go_into_team_scene&team_id=30&scene_id=1&stand_point=2&timeStamp=1282985448456&callback_func_name=callbackFnEnterTeamScene
	public static final String DESDROY_TEAM = "modules/team.php?act=go_into_team_scene&scene_id=1&stand_point=2&timeStamp=1282964761504&callback_func_name=callbackFnEnterTeamScene&team_id=";
	public static Pattern teams = Pattern.compile("view_team \\( (\\d+) \\)\" title=\"(\\S+?)\">.*?Lv.(\\d+).*?Lv.(\\d+)</td>",Pattern.DOTALL);
	public static Pattern openTime = Pattern.compile("s_now_team_scene_id\":\"(\\d+)\",\"s_open_time\":\"(\\d+)\",\"s_close_time\":\"(\\d+)\"");
	private static Pattern times = Pattern.compile("今日进入他方武馆次数：<span class=\"highlight\">(\\d+) / (\\d+)</span>");
	
	public static Map<String, String> teamMap = new HashMap<String, String>();
	
	public static Map<String,String> protectTeam = new HashMap<String,String>();
	private static String[] VIP_TEAM = {"58","65","60"};
	static{
		protectTeam.put("黑木崖", "58");
		protectTeam.put("沧浪异苑", "65");
		protectTeam.put("沧浪茗阁", "60");
	}
	public static String getTeamList(User user){
		String url = user.getUrl()+TEAM_LIST+Tools.getTimeStamp(true);
		String data = "country=0&type=credits&search_var=&chk_open_team=1&callback_func_name=ajaxCallback&callback_obj_name=content";
		String page = PageService.postPage(url, data, user);
		return page;
	}
	
	public static boolean tiGuan(User user){
		if(user.getBeatTeam() != null && user.getBeatTeam().trim().length() > 0 && !user.isFriendly()){
			String url = user.getUrl()+GET_SCENE+Tools.getTimeStamp(true);
			String page = PageService.getPageWithCookie(url, user);
			Matcher m = openTime.matcher(page);
			if(m.find()){
				int close = Integer.parseInt(m.group(3));
				Calendar c = Calendar.getInstance();
				if(c.get(Calendar.HOUR_OF_DAY) == close-1 && c.get(Calendar.MINUTE) >= 40){
					Beauty.tiGuan(user);
				}else {
					return true;
				}
			}
		}
		String url = user.getUrl()+TI_GUAN+Tools.getRandAndTime();
		String page = PageService.getPageWithCookie(url, user);
		return Tools.success(page);
	}
	public static boolean huGuan(User user){
		String url = user.getUrl()+HU_GUAN+Tools.getRandAndTime();
		String page = PageService.getPageWithCookie(url, user);
		return Tools.success(page);
	}
	public static boolean destroy(User user){
		String url = user.getUrl()+MIAN_CHI_DESTROY+Tools.getRandAndTime();
		String page  = PageService.getPageWithCookie(url, user);
		
		if(Tools.success(page)){
			logger.info(user.getRoleName()+"destroy");
		}else{
			url = user.getUrl()+MIAN_CHI_REPAIR+Tools.getRandAndTime();
			page = PageService.getPageWithCookie(url, user);
			if(Tools.success(page)){
				logger.info(user.getRoleName()+"add");
			}
		}
		return true;
	}
	private static String getCurrTeam(User user){
		String url = user.getUrl()+GET_SCENE+Tools.getMarkAndTime();
		String page = PageService.getPageWithCookie(url, user);
		Matcher ct = currTeam.matcher(page);
		if(ct.find()){
			return ct.group(1);
		}
		return null;
	}
	/**
	 * 是否用完踢馆护馆次数
	 * @param user
	 * @return
	 */
	private static boolean noTimes(User user){
		String url = user.getUrl()+TEAM_LIST+Tools.getTimeStamp(true);
		String page = PageService.getPage(url, user);
		Matcher m = times.matcher(page);
		if(m.find()){
			if(m.group(1).equals(m.group(2))){
				return true;
			}
		}
		return false;
	}
	public static boolean gotoWuGuan(User user){
		if(Monitor.isHuanJing(user)){
			logger.info(user.getRoleName()+"正在幻境塔，暂不进入武馆");
			return false;
		}
		setUnionTeam(user);
		if(protectTeam.get(user.getBeatTeam()) != null && !user.isFriendly()){
			logger.info(user.getRoleName()+"恶意踢我盟关，退出啦");
			System.exit(0);
		}
		if(user.isShouldKillMonstor()){
			leaveTeam(user);
			Monstor.killMonstor(user);
			return false;
		}
		if(needProtectMyTeam(user)){
			return true;
		}
		getAllTeam(user);
		
		Portal.setTeamId(user);
	
		boolean inUnion = false;
		if(!user.isShouldKillMonstor()){		
			
			if(Monitor.inWuGuan(user)){
				String url = user.getUrl()+GET_SCENE+Tools.getMarkAndTime();
				String page = PageService.getPageWithCookie(url, user);
				Matcher ct = currTeam.matcher(page);
				if(ct.find()){
					String id = ct.group(1);
					if(user.getUnionTeam().get(id)!=null){
						inUnion = true;
					}
				}
				Matcher m = currXuanWu.matcher(page);
				if(m.find()){
					if(m.group(1).equals(m.group(2)) && inUnion){
						logger.info(user.getRoleName()+"修满了，换个地方");
						leaveTeam(user);
					}else{
						try{
							if(!inUnion && (Tools.getValue(m.group(1))/Tools.getValue(m.group(2)) <= MIN_PERCENT || Tools.getValue(m.group(1)) < 10000)){
								logger.info(user.getRoleName()+"要砸坏人家门了，撤退");
								leaveTeam(user);
							}else{
								return true;
							}
						}catch(Exception e){}
					}
				}
			}else{
				if(noTimes(user)){
					return needProtectMyTeam(user);
				}
			}
			if(user.getBeatTeam() != null && user.getBeatTeam().trim().length() > 0){
				String teamId = teamMap.get(user.getBeatTeam().trim());
				if(teamId != null){
					if(Monitor.inWuGuan(user) && teamId.equals(getCurrTeam(user))){
//						desdroyTeam(user, teamId);
						tiGuan(user);
						return true;
					}else{
						desdroyTeam(user, teamId);
						return true;
					}
				}
			}
			List<String> l = new ArrayList<String>();
			Set<String> set = user.getUnionTeam().keySet();
		
			Iterator<String> it = set.iterator();
			while(it.hasNext()){
				l.add(it.next());
			}
			String mid = selectTeam(user, l);
			if(mid != null && user.isNeedHuGuan()){
				return protectTeam(user, mid);
			}
			
			mid = selectEnemyTeam(user);
			if(mid != null && user.isNeedTiGuan()){
				return desdroyTeam(user, mid);
			}else{
				return needProtectMyTeam(user);
			}
		}
		return false;
	}
	public static void leaveTeam(User user){
		String url = user.getUrl()+LEAVE_TEAM+Tools.getTimeStamp(true);
		PageService.getPageWithCookie(url, user);
	}
	public static boolean protectTeam(User user,String mid){
		String enterMyTeam = user.getUrl()+PROTECT_MY_TEAM+mid+Tools.getTimeStamp(true);
		String page = PageService.getPageWithCookie(enterMyTeam, user);
		String url = user.getUrl()+MOVE+mid+Tools.getTimeStamp(true);
		String result = PageService.getPageWithCookie(url, user);
		Beauty.huGuan(user);
		if(user.isOpenRedProtect()){
			Beauty.redHuGuan(user);
		}
		return Tools.success(result);
	}
	public static boolean desdroyTeam(User user,String mid){
		String enterMyTeam = user.getUrl()+DESDROY_TEAM+mid+Tools.getTimeStamp(true);
		String page = PageService.getPageWithCookie(enterMyTeam, user);
		String url = user.getUrl()+MOVE+mid+Tools.getTimeStamp(true);
		String result = PageService.getPageWithCookie(url, user);
		if(user.getBeatTeam() == null || user.getBeatTeam().trim().length() == 0 || user.isFriendly()){
			for(int i = 0;i < VIP_TEAM.length;i++){
				if(VIP_TEAM[i].equals(user.getTeamId())){
					Beauty.tiGuan(user);
					if(user.isOpenRedBeat()){
						Beauty.redTiGuan(user);
					}
					break;
				}
			}
		}
		return Tools.success(result);
	}
	public static boolean inMyteam(User user){
		if(!Monitor.inWuGuan(user)){
			return false;
		}
		Portal.setTeamId(user);
		String url = user.getUrl()+GET_SCENE+Tools.getMarkAndTime();
		String page = PageService.getPageWithCookie(url, user);
		Matcher m = currTeam.matcher(page);
		if(m.find()){
			if(user.getTeamId().equals(m.group(1)) && "2".equals(m.group(2))){
				return true;
			}
		}
		return false;
	}
	private static boolean needProtectMyTeam(User user){
		String url = user.getUrl()+MY_TEAM+Tools.getTimeStamp(true);
		String page = PageService.getPageWithCookie(url, user);
		Matcher m = myXuanWu.matcher(page);
		if(m.find()){
			try{
				double d = Tools.getValue(m.group(1))/Tools.getValue(m.group(2));
				if(d<=user.getTeamProtectedPercent()){
					if(inMyteam(user)){
						return true;
					}
					m = myTeamId.matcher(page);
					if(m.find()){
						protectTeam(user, m.group(1));
					}
				}
			}catch(Exception e){
				logger.info(e.getMessage());
			}
		}
		return false;
	}
	public static String selectTeam(User user,List l){
		double min = 1;
		String minId = null;
		Iterator<String> it = l.iterator();
		while(it.hasNext()){
			String id = it.next();
			String url = user.getUrl()+VIEW_TEAM+id+Tools.getTimeStamp(true);
			String page = PageService.getPageWithCookie(url, user);
			Matcher m = xuanWuMen.matcher(page);
			if(m.find()){
				try{
					double currPercent = Tools.getValue(m.group(1))/Tools.getValue(m.group(2));
					if(currPercent < min){
						min = currPercent;
						minId = id;
					}
				}catch(Exception e){
					logger.info(e.getMessage());
				}
			}
		}
		if(min >= 0.8){
			logger.info(user.getRoleName()+"盟馆很安全，还是去踢馆吧");
			return null;
		}
		return minId;
	}
	public static String selectEnemyTeam(User user){
		double max = 0.4;
		String mid = null;
		
		String page = getTeamList(user);
		Matcher m = teams.matcher(page);
		while(m.find()){
			if(user.getUnionTeam().get(m.group(1))!=null){
				continue;
			}
			if(user.getLevel().compareTo(m.group(4)) > 0){
				continue;
			}
			String url = user.getUrl()+VIEW_TEAM+m.group(1)+Tools.getTimeStamp(true);
			String s = PageService.getPageWithCookie(url, user);
			Matcher xw = xuanWuMen.matcher(s);
			if(xw.find()){
				try{
					double d = Tools.getValue(xw.group(1))/Tools.getValue(xw.group(2));
					if(Tools.getValue(xw.group(1)) < 300000){
						continue;
					}
					if(d >= max){
						max = d;
						mid = m.group(1);
					}
				}catch(Exception e){
					logger.error(e.getMessage());
				}
			}
		}
		if(max <= MIN_PERCENT){
			return null;
		}
		return mid;
	}
	private static void setUnionTeam(User user) {
		String url = user.getUrl()+UNION_TEAM+Tools.getTimeStamp(true);
		String page = PageService.getPageWithCookie(url, user);
		if(Tools.success(page)){
			Matcher m = union.matcher(page);
			while(m.find()){
				user.getUnionTeam().put(m.group(1), m.group(2));
			}
		}
		
	}
	private static void getAllTeam(User user){
		for(int i = 1;i < 12;i++){
//			String url = user.getUrl()+TEAM_LIST+
			String url = user.getUrl()+GET_ALL_TEAM+i+Tools.getTimeStamp(true);
			String page = PageService.getPageWithCookie(url, user);
			Matcher m = teams.matcher(page);
			while(m.find()){
				teamMap.put(m.group(2), m.group(1));
			}
		}
	}
}
