package com.blue.serverarean;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.blue.common.User;
import com.blue.tools.PageService;
import com.blue.tools.Tools;

public class ServerAreanLogin {
	private static Pattern location = Pattern.compile("href='(.*?)'");
	public static boolean login(User user){
		StringBuilder sb = new StringBuilder();
		sb.append(user.getUrl());
		sb.append("api/league/enter.php");
		sb.append(Tools.getRand('?'));
		String page = PageService.getPageWithCookie(sb.toString(), user);
		Matcher m = location.matcher(page);
		String url = null;
		if(m.find()){
			url = m.group(1);
		}
		String cookie = PageService.getCookie(url, user);
		user.setServerAreanCookie(cookie);
		if(cookie != null){
			return true;
		}
		return false;
	}
}
