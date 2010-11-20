package com.blue.duel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.blue.beauty.Beauty;
import com.blue.common.DropWeapon;
import com.blue.common.User;
import com.blue.tools.PageService;
import com.blue.tools.Tools;

public class Duel{
	private static Logger logger = Logger.getLogger(Duel.class);
	public static final String FREE_FIGHT="modules/duel_fight.php?action=fight&isfree=1&callback_func_name=callbackFnDuelRoleFight&rid=";
	public static final String DUEL_LIST_URL = "modules/duel.php?act=hall";
	public static final String FIGHT_URL = "modules/duel_fight.php?action=fight&rid=";
	
	private static Pattern p = Pattern.compile("<td.*?view_role\\s\\(\\s(\\d+) \\)\" title=\"(\\S+?)\">.*?No.(\\d+).*?Lv.(\\d+)",Pattern.DOTALL);
	private static Pattern times = Pattern.compile("今日你已经发起了.*?(\\d+)",Pattern.MULTILINE);
	//免费竞技
	private static Pattern free = Pattern.compile("免费",Pattern.DOTALL);
	
	public static boolean duel(User user){
		int time = Tools.getNowHour();
		if(time < user.getDuelStartTime() ){
			return true;
		}
		if(!needChallenge(user)){
			return true;
		}
		if(user.isDuelDropWeapon()){
			if(!DropWeapon.dropWeapon(user)){
				logger.info(user.getRoleName()+"卸武失败，暂不竞技");
				return true;
			}
		}
		boolean flag = challenge(user);
		if(user.isDuelDropWeapon()){
			DropWeapon.mountWeapon(user);
		}
		return flag;
	}
	private static boolean needChallenge(User user){
		String url = user.getUrl()+DUEL_LIST_URL+Tools.getTimeStamp(true);
		String page = PageService.getPageWithCookie(url, user);
		Matcher m2 = times.matcher(page);
		if(m2.find()){
			user.setChallengeTimes(Integer.parseInt(m2.group(1)));
		}
		if(user.getChallengeTimes()>=15){
			Matcher f = free.matcher(page);
			if(f.find()){
				return true;
			}else{
				return false;
			}
		}
		return true;
	}
	private static boolean challenge(User user){
		
		String url = user.getUrl()+DUEL_LIST_URL+Tools.getTimeStamp(true);
		String page = PageService.getPageWithCookie(url, user);
		Matcher m = p.matcher(page);
		List<Challenger> l = new ArrayList<Challenger>(10);
		while(m.find()){
			l.add(new Challenger(m.group(1), m.group(3),m.group(4),m.group(2)));
		}
		
		Matcher m2 = times.matcher(page);
		if(m2.find()){
			user.setChallengeTimes(Integer.parseInt(m2.group(1)));
			if(user.getChallengeTimes() > 2){
				Beauty.jingJi(user);
			}
		}
		Challenger me = l.get(l.size()-1);
		if(me == null){
			return false;
		}
		if(l.size() < 2){
			return false;
		}
		Challenger c = l.get(l.size()-2);
		boolean find = false;
		if(user.getDuelType().equals("2")){
			for(int i = l.size()-2;i>=0;i--){
				int clevel = Integer.parseInt(l.get(i).getLevel());
				int melevel = Integer.parseInt(me.getLevel());
				if(melevel-5 <= clevel && clevel <= melevel+5){
					c = l.get(i);
					find = true;
					break;
				}
			}
			if(!find){
				c = l.get(0);
			}
		}
		if(Integer.parseInt(me.getDuelNo()) < 10 && user.isNeedBeatTail()){
			logger.info(user.getRoleName()+"开始刷尾巴");
			Iterator<Challenger> it = l.iterator();
			while(it.hasNext()){
				Challenger t = it.next();
				if(Integer.parseInt(t.getDuelNo()) >10 && t.getLevel().compareTo(me.getLevel())<0){
					c = t;
					break;
				}
			}
		}
		
		if(user.getChallengeTimes()<15){
			page= PageService.getPageWithCookie(user.getUrl()+FIGHT_URL+c.getId()+Tools.getTimeStamp(true), user);
			logger.info(user.getRoleName()+"尝试挑战"+c.getName());
			if(Tools.success(page)){
				logger.info(user.getRoleName()+"挑战"+c.getName()+"成功");
				return true;
			}
		}
		if(user.getChallengeTimes()>=15){
			Matcher f = free.matcher(page);
			if(f.find()){
				String u = user.getUrl()+FREE_FIGHT+c.getId()+Tools.getTimeStamp(true);
				String fpage = PageService.getPageWithCookie(u, user);
				if(Tools.success(fpage)){
					logger.info(user.getRoleName()+"使用免费竞技成功");
					return true;
				}
			}
		}
		return true;
	}
}
