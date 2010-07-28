package com.blue.duel;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.blue.beauty.Beauty;
import com.blue.common.User;
import com.blue.tools.PageService;
import com.blue.tools.Tools;

public class Duel{
	private Logger logger = Logger.getLogger(this.getClass());
	public static final String FREE_FIGHT="modules/duel_fight.php?action=fight&isfree=1&callback_func_name=callbackFnDuelRoleFight&rid=";
	public static final String DUEL_LIST_URL = "modules/duel.php?act=hall";
	public static final String FIGHT_URL = "modules/duel_fight.php?action=fight&rid=";
	private Pattern p = Pattern.compile("<td.*?view_role\\s\\(\\s(\\d+).*?No.(\\d+).*?Lv.(\\d+)",Pattern.DOTALL);
	private Pattern times = Pattern.compile("今日你已经发起了.*?(\\d+)",Pattern.MULTILINE);
	//免费竞技
	private Pattern free = Pattern.compile("免费",Pattern.DOTALL);
	public boolean challenge(User user){
		String url = user.getUrl()+DUEL_LIST_URL+Tools.getTimeStamp(true);
		String page = PageService.getPageWithCookie(url, user);
		Matcher m = p.matcher(page);
		List<Challenger> l = new ArrayList<Challenger>(10);
		while(m.find()){
			l.add(new Challenger(m.group(1), m.group(2),m.group(3)));
		}
		
		Matcher m2 = times.matcher(page);
		if(m2.find()){
			user.setChallengeTimes(Integer.parseInt(m2.group(1)));
			if(user.getChallengeTimes() > 2){
				Beauty.jingJi(user);
			}
		}
		Challenger c = l.get(l.size()-2);
		if(user.getChallengeTimes()<15){
			page= PageService.getPageWithCookie(user.getUrl()+FIGHT_URL+c.getId()+Tools.getTimeStamp(true), user);
			logger.info(user.getRoleName()+"尝试挑战"+c.getId());
			if(Tools.success(page)){
				logger.info(user.getRoleName()+"挑战"+c.getId()+"成功");
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
