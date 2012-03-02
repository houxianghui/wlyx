package com.blue.daily;

import com.blue.common.Monitor;
import com.blue.common.User;
import com.blue.monstor.Monstor;
import com.blue.tools.ItemMerge;
import com.blue.tools.PageService;
import com.blue.tools.Tools;

public class CountryChange {
	private static String change = "/modules/awards.php?act=get_mystery_award&reward_id=offer_reward_LV50";
	public static void change(User user){
		String url = user.getUrl()+change+Tools.getTimeStamp(true);
		PageService.getPageWithCookie(url, user);
	}
	public static void batchChange(User user){
		ItemMerge.merge(user);
		for(int i = 0;i<10;i++){
			CountryChange.change(user);
		}
		Monitor.getAwards(user);
		ItemMerge.merge(user);
	}
}
