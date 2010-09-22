package com.blue.monstor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.blue.common.Portal;
import com.blue.common.User;
import com.blue.tools.PageService;
import com.blue.tools.Tools;

public class ItemMerge {
	private static Logger logger = Logger.getLogger(ItemMerge.class);
	//http://s4.verycd.9wee.com/modules/role_item.php?act=drag_item&id=1597716&from=pack&to=pack&pos_x=6&pos_y=2&timeStamp=1285146745061&callback_func_name=itemClass.dragItemCallback
	private static String MERGE = "modules/role_item.php?act=drag_item&from=pack&to=pack&callback_func_name=itemClass.dragItemCallback";
	private static Pattern p = Pattern.compile("item_id\":\"(\\d+)\",\"role_id\":\"\\d+\",\"name\":\"(\\S+?)\",\"equip_type\":\"(\\d+)\".*?\"position_x\":\"(\\d+)\",\"position_y\":\"(\\d+)\",\"item_type\":\"(\\d+)\".*?quality\":\"\\d+.*?buy_price\":\"\\d+\".*?is_checkup\":\"\\d+\".*?max_superpose\":\"(\\d+)\",\"superpose\":\"(\\d+)\"",Pattern.UNICODE_CASE);
	public static void merge(User user){
		List<Item> l = getPack(user);
		Iterator<Item> it = l.iterator();
		Map<String, Item> m = new HashMap<String, Item>();
		while(it.hasNext()){
			Item i = it.next();
//			System.out.println(i);
			Item to = null;
			if((to = m.get(i.getName())) != null && !to.getCount().equals(to.getMaxCount())){
				String data = "&id="+i.getId()+"&pos_x="+to.getPositonX()+"&pos_y="+to.getPositionY();
				String url = user.getUrl()+MERGE+data+Tools.getTimeStamp(true);
				String page = PageService.getPageWithCookie(url, user);
				if(Tools.success(page)){
					logger.info(user.getRoleName()+"合并"+Tools.hexToString(i.getName())+"成功");
				}
			}else{
				if(!i.getCount().equals(i.getMaxCount())){
					m.put(i.getName(), i);
				}
			}
		}
	}
	public static List<Item> getPack(User user){
		List<Item> l = new ArrayList<Item>();
		String url = user.getUrl()+Portal.USER_INFO+Tools.getTimeStamp(true);
		String page = PageService.getPageWithCookie(url, user);
		Matcher m = p.matcher(page);
		while(m.find()){
			l.add(new Item(m.group(1),m.group(2),m.group(3),m.group(4),m.group(5),m.group(7),m.group(6)));
		}
		return l;
	}
}
