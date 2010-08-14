package com.blue.duel;

import java.util.ArrayList;
import java.util.Iterator;
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
	private Pattern p = Pattern.compile("<td.*?view_role\\s\\(\\s(\\d+) \\)\" title=\"(\\S+?)\">.*?No.(\\d+).*?Lv.(\\d+)",Pattern.DOTALL);
	private Pattern times = Pattern.compile("�������Ѿ�������.*?(\\d+)",Pattern.MULTILINE);
	//��Ѿ���
	private Pattern free = Pattern.compile("���",Pattern.DOTALL);
	public boolean challenge(User user){
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
		if(Integer.parseInt(me.getDuelNo()) < 10){
			logger.info(user.getRoleName()+"��ʼˢβ��");
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
			logger.info(user.getRoleName()+"������ս"+c.getName());
			if(Tools.success(page)){
				logger.info(user.getRoleName()+"��ս"+c.getName()+"�ɹ�");
				return true;
			}
		}
		if(user.getChallengeTimes()>=15){
			Matcher f = free.matcher(page);
			if(f.find()){
				String u = user.getUrl()+FREE_FIGHT+c.getId()+Tools.getTimeStamp(true);
				String fpage = PageService.getPageWithCookie(u, user);
				if(Tools.success(fpage)){
					logger.info(user.getRoleName()+"ʹ����Ѿ����ɹ�");
					return true;
				}
			}
		}
		return true;
	}
}