package com.blue.slavy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.blue.beauty.Beauty;
import com.blue.common.Monitor;
import com.blue.common.User;
import com.blue.tools.PageService;
import com.blue.tools.Tools;

public class CatchSlavy {
	private static Logger logger = Logger.getLogger(CatchSlavy.class);
	//http://s4.verycd.9wee.com/modules/duel.php?act=slavery&timeStamp=1280449399109&callback_func_name=callback_load_content&callback_obj_name=content
	public static final String SLAVY_LIST = "modules/duel.php?act=slavery&callback_func_name=callback_load_content&callback_obj_name=content";
	//http://s4.verycd.9wee.com/modules/slavery_fight.php?act=enemy_fight&rid=11154&is_reverse=0&rand=1280454988125&timeStamp=1280452728828&callback_func_name=callbackFnSlaveryFight
	public static final String CATCH_SLAVY = "modules/slavery_fight.php?act=enemy_fight&is_reverse=0&callback_func_name=callbackFnSlaveryFight&rid=";
	//http://s4.verycd.9wee.com/modules/role_slavery.php?act=slaves_list&boss_id=1401&rand=1280645233206&timeStamp=1280644598821&callback_func_name=ajaxCallback&callback_obj_name=dlg_view_slaves_list
	public static final String SLAVYS_LIST="modules/role_slavery.php?act=slaves_list&callback_func_name=ajaxCallback&callback_obj_name=dlg_view_slaves_list&boss_id=";
	//http://s4.verycd.9wee.com/modules/slavery_fight.php?act=enemy_fight&rid=17798&capture_role_id=20821&is_reverse=1&timeStamp=1280647211817&callback_func_name=callbackFnSlaveryFight
	public static final String FIGHT_MASTER = "modules/slavery_fight.php?act=enemy_fight&callback_func_name=callbackFnSlaveryFight";
	
	private static Pattern slavyList = Pattern.compile("<span class=\"\\S+?\">(奴隶|奴隶主|自由身)</span>.*?Lv.(\\d+).*?fnSlaveryFight\\(.*?,\\s*(\\d+), '(\\S+?)'",Pattern.DOTALL);
	private static Pattern catchCount = Pattern.compile("今日已发起 <span class=\"highlight\">(\\d+) / (\\d+)</span> 场");
	private static Pattern isSlavyMaster = Pattern.compile("下次发起俘获还需");
	//<a href="javascript:void(0);" onclick="fnChoiceSlaveToFight( 17798, '北北', 20821, '暗夜龙隐', 1 )">俘获</a>
	private static Pattern masterSlavyList = Pattern.compile("<a href=\"javascript:void\\(0\\);\" onclick=\"fnChoiceSlaveToFight\\( (\\d+), '(\\S+?)', (\\d+), '(\\S+?)', (\\d+) \\)\">俘获</a>");
	public static Pattern fail = Pattern.compile("我在奴隶市场中输给了 <a href=\"javascript:void\\(0\\);\" onclick=\"view_role\\((\\d+)\\)\">(\\S+?)</a>");
	public static Pattern failToSlavy = Pattern.compile("我想从 <a href=\"javascript:void\\(0\\);\" onclick=\"view_role\\(\\d+\\)\">\\S+?</a> 的手上抢夺 <a href=\"javascript:void\\(0\\);\" onclick=\"view_role\\((\\d+)\\)\">(\\S+?)</a>  作为自己的奴隶");
	
	private static boolean canCatchSlavy(User user){
		String url = user.getUrl()+SLAVY_LIST+Tools.getTimeStamp(true);
		String page = PageService.getPageWithCookie(url, user);
		Matcher m = isSlavyMaster.matcher(page);
		if(!m.find()){
			return false;
		}
		m = catchCount.matcher(page);
		if(m.find()){
			if(m.group(1).compareTo("3") >= 0){
				Beauty.nuLi(user);
			}
			if(m.group(1).compareTo(m.group(2)) == 0){
				return false;
			}else{
				return true;
			}
			
		}
		return false;
	}
	
	private static boolean needCatchSlavy(User user){
		String url = user.getUrl()+Monitor.SLAVY_MASTER+Tools.getRandAndTime();
		String page = PageService.getPageWithCookie(url, user);
		Matcher m = Monitor.slavy.matcher(page);
		int slavyCount = 0;
		while(m.find()){
			slavyCount++;
		}
		if(slavyCount >= user.getSlavyMin()){
			logger.info(user.getRoleName()+"有"+slavyCount+"个奴隶，不用捕获");
			return false;
		}
		return true;
	}
	public static boolean catchSlavy(User user){
		if(!needCatchSlavy(user)){
			return true;
		}
		if(!canCatchSlavy(user)){
			return true;
		}
		
		List<Slavy> l = getSlavyList(user);
		Iterator<Slavy> it = l.iterator();
		
		//先抓自由身
		while(it.hasNext()){
			Slavy s = it.next();
			if(s.level <= Integer.parseInt(user.getLevel()) && s.status.equals("自由身")){
				
				if(catchIt(s.id,s.slavyName,user)){
					logger.info(user.getRoleName()+"对"+s.slavyName+"发起奴隶捕获");
					return true;
				}
			}
		}
		//如果没抓到，就抓奴隶
		it = l.iterator();
		while(it.hasNext()){
			Slavy s= it.next();
			if(s.level<=Integer.parseInt(user.getLevel()) && s.status.equals("奴隶")){
				if(catchIt(s.id, s.slavyName, user)){
					logger.info(user.getRoleName()+"对"+s.slavyName+"发起奴隶捕获");
					return true;
				}
			}
		}
		//还没抓到，就抓奴隶主的奴隶
		it = l.iterator();
		while(it.hasNext()){
			Slavy s= it.next();
			if(s.level<=Integer.parseInt(user.getLevel()) && s.status.equals("奴隶主")){
				String url = user.getUrl()+SLAVYS_LIST+s.id+Tools.getRandAndTime();
				String page = PageService.getPageWithCookie(url, user);
				Matcher m = masterSlavyList.matcher(page);
				while(m.find()){
					if(!failCheckSuccess(m.group(1), user)){
						continue;
					}
					url = user.getUrl()+FIGHT_MASTER+getFightSlavyMaster(m.group(1), m.group(3),m.group(5));
					page = PageService.getPageWithCookie(url, user);
					if(Tools.success(page)){
						logger.info(user.getRoleName()+"抢夺"+m.group(2)+"的奴隶"+m.group(4));
						return true;
					}
				}
			}
		}
		return false;
	}
	private static String getFightSlavyMaster(String masterId,String slavyId,String isReverse){
		return  "&rid="+masterId+"&capture_role_id="+slavyId+"&is_reverse="+isReverse;
	}
	private static boolean catchIt(String id,String name,User user){
		if(!failCheckSuccess(id, user)){
			return false;
		}
		String url = user.getUrl()+CATCH_SLAVY+id+Tools.getRandAndTime();
		String page = PageService.getPageWithCookie(url, user);
		return Tools.success(page);
	}

	private static boolean failCheckSuccess(String id, User user) {
		Map<String , CatchFail> map = getFailList(user);
		CatchFail cf = map.get(id);
		if(cf != null && cf.times>=1){
			logger.info(user.getRoleName()+"输给"+cf.name+cf.times+"次了，抢别人去！");
			return false;
		}
		return true;
	}
	private static List<Slavy> getSlavyList(User user){
		String url = user.getUrl()+SLAVY_LIST+Tools.getTimeStamp(true);
		String page = PageService.getPageWithCookie(url, user);
		Matcher m = slavyList.matcher(page);
		List<Slavy> l = new ArrayList<Slavy>();
		while(m.find()){
			l.add(new Slavy(m.group(4),m.group(1),Integer.parseInt(m.group(2)),m.group(3)));
		}
		return l;
	}
	private static Map<String, CatchFail> getFailList(User user){
		
		Map<String,CatchFail> map = new HashMap<String, CatchFail>();
		String url = user.getUrl()+SLAVY_LIST+Tools.getTimeStamp(true);
		String page = PageService.getPageWithCookie(url, user);
		Matcher m = fail.matcher(page);
		
		while(m.find()){
			String id = m.group(1);
			CatchFail cf = map.get(id);
			if(cf == null){
				map.put(id, new CatchFail(id, m.group(2), 1));
			}else{
				map.put(id, new CatchFail(id, m.group(2), ++cf.times));
			}
		}
		m = failToSlavy.matcher(page);
		while(m.find()){
			String id = m.group(1);
			CatchFail cf = map.get(id);
			if(cf == null){
				map.put(id, new CatchFail(id, m.group(2), 1));
			}else{
				map.put(id, new CatchFail(id, m.group(2), ++cf.times));
			}
		}
		
		return map;
	}
	static class CatchFail{
		String id;
		String name;
		int times;
		CatchFail(String id,String name,int times){
			this.id = id;
			this.name = name;
			this.times = times;
		}
	}
	static class Slavy{
		String slavyName;
		String status;
		String id;
		int level;
		public Slavy(String slavyName,String status,int level,String id) {
			this.slavyName = slavyName;
			this.status = status;
			this.level = level;
			this.id = id;
		}
	}
}
