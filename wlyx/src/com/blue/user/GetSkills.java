package com.blue.user;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.blue.common.User;
import com.blue.tools.PageService;
import com.blue.tools.Tools;



public class GetSkills {
	private static Pattern skill = Pattern.compile("id\":\"(.*?)\",\"name\":\"(.*?)\"");
	public static void getSkill(User user)throws Exception{
		String url = user.getUrl()+"modules/role_skill.php?"+Tools.getTimeStamp(false);
		String page = PageService.getPageWithCookie(url, user);
		Matcher	m = skill.matcher(page);
		while(m.find()){
			System.out.println(Tools.hexToString(m.group(2))+"="+m.group(1));
		}
	}
}
