package com.blue.serverarean;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.blue.common.User;
import com.blue.tools.PageService;
import com.blue.tools.SkillUtil;
import com.blue.tools.Tools;

public class ServerArean {
	private static Logger logger = Logger.getLogger(ServerArean.class);
	private static Pattern person = Pattern.compile("<li>.*?</li",Pattern.DOTALL);
	private static Pattern remind = Pattern.compile("titlecontent=\"(.*?)\".*?personageRoleFight\\((\\d+),(\\d+),(\\d+)\\)",Pattern.DOTALL);
	private static Pattern count = Pattern.compile("今日挑战次数：<span class=\"highlight\" >(\\d+)/(\\d+)</span>",Pattern.DOTALL);
	/**
	 * 报名
	 * @param user
	 */
	public static void sign(User user){
		ServerAreanConfig config = user.getServerAreanConfig();
		if(config.isNeedAutoSign()){
			if(Tools.isEmpty(config.getSignId())){
				logger.info(user.getRoleName()+"自动报名黄金联赛类型未指定");
				return;
			}
			StringBuilder sb = new StringBuilder();
			sb.append(user.getUrl());
			sb.append("modules/server_arean.php?act=sign&id=");
			sb.append(config.getSignId());
			sb.append(Tools.getTimeStamp(true));
			PageService.getPageWithCookie(sb.toString(), user);
		}
	}
	/**
	 * 挑战名人堂
	 * @param user
	 */
	public static void challenge(User user){
		ServerAreanConfig config = user.getServerAreanConfig();
		if(!config.isNeedAutoChallenge()){
			return;
		}
		if(Tools.isEmpty(user.getServerAreanCookie())){
			if(!ServerAreanLogin.login(user)){
				logger.info(user.getRoleName()+"登录黄金联赛失败，不进行自动挑战");
				return;
			}
		}
		//http://league.50hero.com/modules/personage.php
		String url = "http://league.50hero.com/modules/personage.php?"+Tools.getTimeStamp(false);
		String page = PageService.getPage(url,user.getServerAreanCookie(), user);
		if(noTimes(page)){
			return;
		}
		List<Personage> famous = getPersonages(page);
		for(Personage p:famous){
			AutoChallengeConfig c = config.getChallengeConfig(p.getName());
			if(c!=null){
				if(refreshSkill(user,c)){
					autoChallenge(p,user);
					break;
				}
			}
		}
		
	}
	private static boolean noTimes(String page) {
		Matcher m = count.matcher(page);
		if(m.find()){
			if(m.group(1).equals(m.group(2))){
				return true;
			}
		}
		return false;
	}
	private static void autoChallenge(Personage p,User user) {
		//http://league.50hero.com/modules/personage.php?action=fight&rid=1&attId=12398&mode=18&rand=1350289613582&timeStamp=1350289608968&callback_func_name=callbackPersonageRoleFight
		StringBuilder url = new StringBuilder("http://league.50hero.com/modules/personage.php?action=fight");
		url.append("&rid="+p.getId());
		url.append("&attId="+p.getAttId());
		url.append("&mode="+p.getMode());
		url.append(Tools.getRandAndTime());
		String page = PageService.getPage(url.toString(), user.getServerAreanCookie(), user);
		if(Tools.success(page)){
			logger.info(user.getRoleName()+"自动挑战名人堂["+p.getName()+"]");
		}
	}
	private static List<Personage> getPersonages(String page) {
		Matcher m = person.matcher(page);
		List<Personage> l = new ArrayList<Personage>();
		while(m.find()){
			String result = m.group();
			Personage p = getRemind(result);
			if(p != null){
				l.add(p);
			}
		}
		return l;
	}
	private static Personage getRemind(String page){
		Matcher m = remind.matcher(page);
		if(m.find()){
			Personage p = new Personage();
			p.setName(m.group(1));
			p.setId(m.group(2));
			p.setAttId(m.group(3));
			p.setMode(m.group(4));
			return p;
		}
		return null;
	}
	private static boolean refreshSkill(User user,AutoChallengeConfig ac){
		//http://s4.verycd.9wee.com/modules/refresh_team_scene_data.php?action=refresh&timeMark=1350279548&time=37&timeStamp=1350279548411&callback_func_name=fnInitTeamSceneData
		//http://league.50hero.com/modules/refresh_data.php?rand=1350290735250&timeStamp=1350290719003&callback_func_name=callbacRefreshData&callback_obj_name=olympicsObj_refresh
		SkillUtil.equipSkill(user, ac);
		StringBuilder sb = new StringBuilder("http://league.50hero.com/modules/refresh_data.php?callback_func_name=callbacRefreshData");
		sb.append(Tools.getRandAndTime());
		String page = PageService.getPage(sb.toString(),user.getServerAreanCookie(), user);
		if(page.indexOf("error")!=-1){
			return false;
		}
		return true;
	}
}
