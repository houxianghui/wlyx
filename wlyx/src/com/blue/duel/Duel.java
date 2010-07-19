package com.blue.duel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.blue.common.User;
import com.blue.tools.PageService;
import com.blue.tools.Tools;

public class Duel{
	public static final String FREE_FIGHT="http://s4.verycd.9wee.com/modules/duel_fight.php?action=fight&rid=4961&isfree=1&timeStamp=1279096429823&callback_func_name=callbackFnDuelRoleFight";
	public static final String DUEL_LIST_URL = "modules/duel.php?act=hall";
	public static final String FIGHT_URL = "modules/duel_fight.php?action=fight&rid=";
	private Pattern p = Pattern.compile("<td.*?view_role\\s\\(\\s(\\d+).*?No.(\\d+).*?Lv.(\\d+)",Pattern.DOTALL);
	private Pattern times = Pattern.compile("今日你已经发起了.*?(\\d+)",Pattern.MULTILINE);

	public void setChallengerInfo(User user){
		Random r = new Random();
		String url = user.getUrl()+DUEL_LIST_URL+Tools.getTimeStamp(true);
		String page = PageService.getPageWithCookie(url, user);
		Matcher m = p.matcher(page);
		List<Challenger> l = new ArrayList<Challenger>(10);
		while(m.find()){
			l.add(new Challenger(m.group(1), m.group(2),m.group(3)));
		}
		user.setChallengeList(l);
		Matcher m2 = times.matcher(page);
		if(m2.find()){
			user.setChallengeTimes(Integer.parseInt(m2.group(1)));
		}
	}
	public boolean challenge(User user,Challenger c){
		String page= PageService.getPageWithCookie(user.getUrl()+FIGHT_URL+c.getId()+Tools.getTimeStamp(true), user);
		return Tools.success(page);
	}
}
