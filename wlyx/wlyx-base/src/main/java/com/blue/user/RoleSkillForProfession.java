package com.blue.user;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.blue.common.User;
import com.blue.enums.Profession;
import com.blue.fyzb.ServerDuelConfig;
import com.blue.tools.PageService;
import com.blue.tools.Tools;

public class RoleSkillForProfession {
	private static Pattern sector = Pattern.compile("<tr>.*?</tr>",Pattern.DOTALL);
	private static Pattern profession = Pattern.compile("¶Ô¿¹¡¾(.{2}Ïµ)¡¿",Pattern.DOTALL);
	private static Pattern activeSkill = Pattern.compile("equip_skill_for_class.*?</select>",Pattern.DOTALL);
	private static Pattern assistSkill = Pattern.compile("assistant.*?</select>",Pattern.DOTALL);
	private static Pattern selected = Pattern.compile("selected>(.*?) Lv",Pattern.DOTALL);
	public static void setServerDuelConfig(User user){
		setUserProfession(user, getPage(user));
	}
	public static void setUserProfession(User user,String page){
		Matcher sectorMatcher = sector.matcher(page);
		ServerDuelConfig config = null;
		while(sectorMatcher.find()){
			
			String s = sectorMatcher.group();
			Matcher m = profession.matcher(s);
			if(m.find()){
				config = new ServerDuelConfig();
				config.setChallengProfession(Profession.valueOf(m.group(1)));
				config.setActiveSkill(getActiveSkill(s));
				String[] assist = getAssist(s);
				config.setAssistSkillA(assist[0]);
				config.setAssistSkillB(assist[1]);
				user.getServerDuelConfigMap().put(config.getChallengProfession(), config);
			}else{
				continue;
			}
		}
	}
	private static String getActiveSkill(String s){
		Matcher m = activeSkill.matcher(s);
		if(m.find()){
			return getSelectedSkill(m.group());
		}
		return null;
	}
	private static String[] getAssist(String s){
		String[] assists = new String[2];
		Matcher m = assistSkill.matcher(s);
		int i = 0;
		while(m.find()){
			assists[i++] = getSelectedSkill(m.group());
		}
		return assists;
	}
	
	private static String getSelectedSkill(String s){
		Matcher m = selected.matcher(s);
		if(m.find()){
			return m.group(1);
		}
		return null;
	}
	private static String getPage(User user){
		StringBuffer url = new StringBuffer(user.getUrl());
		url.append("modules/role_skill.php?act=equip_for_class");
		url.append(Tools.getTimeStamp(true));
		return PageService.getPageWithCookie(url.toString(), user);
	}
}
