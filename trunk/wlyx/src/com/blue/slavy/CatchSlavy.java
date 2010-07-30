package com.blue.slavy;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.blue.beauty.Beauty;
import com.blue.common.Monitor;
import com.blue.common.User;
import com.blue.tools.PageService;
import com.blue.tools.Tools;

public class CatchSlavy {
	private Logger logger = Logger.getLogger(this.getClass());
	//http://s4.verycd.9wee.com/modules/duel.php?act=slavery&timeStamp=1280449399109&callback_func_name=callback_load_content&callback_obj_name=content
	public static final String SLAVY_LIST = "modules/duel.php?act=slavery&callback_func_name=callback_load_content&callback_obj_name=content";
	//http://s4.verycd.9wee.com/modules/slavery_fight.php?act=enemy_fight&rid=11154&is_reverse=0&rand=1280454988125&timeStamp=1280452728828&callback_func_name=callbackFnSlaveryFight
	public static final String CATCH_SLAVY = "modules/slavery_fight.php?act=enemy_fight&is_reverse=0&callback_func_name=callbackFnSlaveryFight&rid=";
	
	/*
	 * <span class="highlight">自由身</span>
														</td>
							<td width="50" align="center">男</td>
							<td width="60" align="center">Lv.41</td>
							<td width="80" align="center">侍卫统领</td>
							<td width="80" align="center">
														<a href="javascript:void(0);" onclick="fnSlaveryFight( 2, 11154, 'bujingyun', 1, 0 );">俘获奴隶</a>
	 */
	private Pattern slavyList = Pattern.compile("<span class=\"highlight\">(\\S+?)</span>.*?Lv.(\\d+).*?fnSlaveryFight\\(.*?,\\s*(\\d+), '(\\S+?)'",Pattern.DOTALL);
	private Pattern catchCount = Pattern.compile("今日已发起 <span class=\"highlight\">(\\d+) / (\\d+)</span> 场");
	private Pattern isSlavyMaster = Pattern.compile("下次发起俘获还需");
	
	private boolean canCatchSlavy(User user){
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
	
	private boolean needCatchSlavy(User user){
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
	public boolean catchSlavy(User user){
		if(!needCatchSlavy(user)){
			return true;
		}
		if(!canCatchSlavy(user)){
			return true;
		}
		List<Slavy> l = getSlavyList(user);
		Iterator<Slavy> it = l.iterator();
		boolean catched = false;
		//先抓自由身
		while(it.hasNext()){
			Slavy s = it.next();
			if(s.level < Integer.parseInt(user.getLevel()) && s.status.equals("自由身")){
				if(catchIt(s.id,s.slavyName,user)){
					logger.info(user.getRoleName()+"对"+s.slavyName+"发起奴隶捕获");
					catched = true;
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
					catched = true;
					return true;
				}
			}
		}
		return catched;
	}
	private boolean catchIt(String id,String name,User user){
		String url = user.getUrl()+CATCH_SLAVY+id+Tools.getRandAndTime();
		String page = PageService.getPageWithCookie(url, user);
		return Tools.success(page);
	}
	private List<Slavy> getSlavyList(User user){
		String url = user.getUrl()+SLAVY_LIST+Tools.getTimeStamp(true);
		String page = PageService.getPageWithCookie(url, user);
		Matcher m = slavyList.matcher(page);
		List<Slavy> l = new ArrayList<Slavy>();
		while(m.find()){
			l.add(new Slavy(m.group(4),m.group(1),Integer.parseInt(m.group(2)),m.group(3)));
		}
		return l;
	}
	class Slavy{
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
