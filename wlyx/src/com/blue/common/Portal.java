package com.blue.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.blue.tools.PageService;
import com.blue.tools.Tools;

public class Portal {
	//http://s4.verycd.9wee.com/modules/role_info.php?timeStamp=1279701586234&callback_func_name=callback_load_content&callback_obj_name=content
	public static final String USER_INFO="modules/role_info.php?callback_func_name=callback_load_content&callback_obj_name=content";
	private Pattern p = Pattern.compile("µÈ¼¶£º<span class=highlight>Lv.(\\d+)");
	
	public boolean setUserInfo(User user){
		user.setKillMonstorOnce("1");
		String url = user.getUrl();
		String page = PageService.getPageWithCookie(url, user);
		Matcher m = p.matcher(page);
		if(m.find()){
			user.setLevel(m.group(1));
		}
		return Tools.success(page);
	}
	
}
