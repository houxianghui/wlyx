package com.blue.warrior;

import com.blue.common.Portal;
import com.blue.common.User;
import com.blue.tools.PageService;
import com.blue.tools.Tools;

public class Warrior {
	public static final String WARRIOR_URL="modules/warrior.php?act=hall&op=train&hours=1";
	public boolean startTrain(User user){
		Portal.setUserInfo(user);
		if(user.isShouldKillMonstor()){
			return true;
		}
		String url = user.getUrl()+WARRIOR_URL+Tools.getTimeStamp(true);
		String page = PageService.getPageWithCookie(url, user);
		if(Tools.success(page)){
			System.out.println("train start success");
			return true;
		}else{
			System.out.println("train failed");
		}
		return false;
	}
}
