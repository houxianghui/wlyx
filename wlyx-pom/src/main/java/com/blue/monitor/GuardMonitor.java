package com.blue.monitor;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.blue.common.User;
import com.blue.tools.Item;
import com.blue.tools.ItemMerge;
import com.blue.tools.ItemTools;
import com.blue.tools.PageService;
import com.blue.tools.Tools;

public class GuardMonitor {
	private static Logger logger = Logger.getLogger(GuardMonitor.class);
	private static Pattern guard = Pattern.compile("awards_view \\( (\\d+) \\)\">(.{1,10}����).*?(������ȡ|����ȡ)",Pattern.DOTALL);
	public static final String AWARD_URL="modules/awards.php?callback_func_name=ajaxCallback&callback_obj_name=dlg_awards";
	public static final String GET_AWARD_URL = "modules/awards.php?act=fetch&callback_func_name=awards_fetch_callback&award_id=";

	public static void getGuard(User user){
		String url = user.getUrl()+AWARD_URL+Tools.getTimeStamp(true);
		String page = PageService.getPageWithCookie(url, user);
		Matcher m = guard.matcher(page);
		while(m.find()){
			if(m.group(3).equals("����ȡ")){
				continue;
			}
			
			url = user.getUrl()+GET_AWARD_URL+m.group(1)+Tools.getTimeStamp(true);
			String p = PageService.getPageWithCookie(url, user);
			if(Tools.success(p)){
				logger.info(user.getRoleName()+"��ȡ"+m.group(2)+"�ɹ�");
				ItemMerge.merge(user);
			}
		}
//		wearGuard(user);
	}
	private static void wearGuard(User user){
		List<Item> items = ItemTools.getPack(user);
		String guard = "��ľ��";
		for(Item item:items){
			if(item.getCNName().indexOf(guard) != -1){
				ItemTools.wearItem(user, item);
			}
		}
	}
}
