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
	//http://s4.verycd.9wee.com/modules/role_item.php?act=drag_item&id=1597712&from=pack&to=pack&pos_x=6&pos_y=2&quantity=4&timeStamp=1285151539353&callback_func_name=itemClass.dragItemCallback
	private static String MERGE = "modules/role_item.php?act=drag_item&from=pack&to=pack&callback_func_name=itemClass.dragItemCallback";
	//http://s4.verycd.9wee.com/modules/warrior.php?act=hall&op=teamstock&timeStamp=1317356185300&callback_func_name=ajaxCallback&callback_obj_name=team_foster_stock
	private static String SI_HAI_KU_FANG = "modules/warrior.php?act=hall&op=teamstock";
	//http://s4.verycd.9wee.com/modules/role_item.php?act=drag_item&id=2878778&from=pack&to=teamstock&pos_x=0&pos_y=7&timeStamp=1317362887977&callback_func_name=itemClassTeamStk.dragItemCallback
	private static String PACK_TO_SI_HAI = "modules/role_item.php?act=drag_item&from=pack&to=teamstock";
	private static Pattern p = Pattern.compile("item_id\":\"(\\d+)\",\"role_id\":\"\\d+\",\"name\":\"(\\S+?)\",\"equip_type\":\"(\\d+)\".*?\"position_x\":\"(\\d+)\",\"position_y\":\"(\\d+)\".*?max_superpose\":\"(\\d+)\",\"superpose\":\"(\\d+)\"");
	private static Pattern item = Pattern.compile("item_id.*?theory_max_strengthen");
	public static void merge(User user){
		List<Item> l = getPack(user);
		merge(user,l);
	}
	private static void mergeToTeamStock(User user,List l,Map<String, Item> m){		
		Iterator<Item> it = l.iterator();
		while(it.hasNext()){
			Item i = it.next();
			if(i.getCount().equals(i.getMaxCount()) || !i.getEquipType().equals("0")){
				continue;
			}
			Item to = m.get(i.getName());
			if(to!=null){
				String data = "&id="+i.getId()+"&pos_x="+to.getPositonX()+"&pos_y="+to.getPositionY()+"&quantity="+i.getCount();
				String url = user.getUrl()+PACK_TO_SI_HAI+data+Tools.getTimeStamp(true);
				String page = PageService.getPageWithCookie(url, user);
				if(Tools.success(page)){
					logger.info(user.getRoleName()+"�ϲ�"+Tools.hexToString(i.getName())+"���ĺ��ⷿ�ɹ�");
				}
			}
		}
	}
	private static void merge(User user,List l){		
		Iterator<Item> it = l.iterator();
		Map<String, Item> m = new HashMap<String, Item>();
		while(it.hasNext()){
			Item i = it.next();
			if(i.getCount().equals(i.getMaxCount()) || !i.getEquipType().equals("0")){
				continue;
			}
			Item to = null;
			if((to = m.get(i.getName())) != null && !to.getCount().equals(to.getMaxCount())){
				String data = "&id="+i.getId()+"&pos_x="+to.getPositonX()+"&pos_y="+to.getPositionY()+"&quantity="+i.getCount();
				String url = user.getUrl()+MERGE+data+Tools.getTimeStamp(true);
				String page = PageService.getPageWithCookie(url, user);
				if(Tools.success(page)){
					logger.info(user.getRoleName()+"�ϲ�"+Tools.hexToString(i.getName())+"�ɹ�");
				}
			}else{
				if(!i.getCount().equals(i.getMaxCount())){
					m.put(i.getName(), i);
				}
			}
		}
	}
	public static void mergeSiHaiKuFang(User user){
		List<Item> l = getSiHaiKuFang(user);
		Map<String,Item> m = new HashMap<String, Item>();
		for(Item i:l){
			if(i.getEquipType().equals("0") && !i.getCount().equals(i.getMaxCount())){
				m.put(i.getName(), i);
			}
		}
		mergeToTeamStock(user,getPack(user),m);
	}
	private static List<Item> getSiHaiKuFang(User user){
		List<Item> l = new ArrayList<Item>();
		String url = user.getUrl()+SI_HAI_KU_FANG+Tools.getTimeStamp(true);
		String page = PageService.getPageWithCookie(url, user);
		int index = page.indexOf("teamstock\":{");
		page = page.substring(index);
		Matcher out = item.matcher(page);
		while(out.find()){
			String s = out.group();
			Matcher m = p.matcher(s);
			if(m.find()){
				l.add(new Item(m.group(1),m.group(2),m.group(3),m.group(4),m.group(5),m.group(7),m.group(6)));
			}
		}
		return l;
	}
	private static List<Item> getPack(User user){
		List<Item> l = new ArrayList<Item>();
		String url = user.getUrl()+Portal.USER_INFO+Tools.getTimeStamp(true);
		String page = PageService.getPageWithCookie(url, user);
		Matcher out = item.matcher(page);
		while(out.find()){
			String s = out.group();
			Matcher m = p.matcher(s);
			if(m.find()){
				l.add(new Item(m.group(1),m.group(2),m.group(3),m.group(4),m.group(5),m.group(7),m.group(6)));
			}
		}
		return l;
	}
}