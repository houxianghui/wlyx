package com.blue.tools;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.blue.common.Portal;
import com.blue.common.User;

public class ItemTools {
	private static Logger logger = Logger.getLogger(ItemTools.class);
	private static Pattern temp = Pattern.compile("temp\":\\{\".*?}},",Pattern.DOTALL);
	private static Pattern item = Pattern.compile("item_id\":\"(\\d+)\",\"role_id\":\"\\d+\",\"name\":\"(\\S+?)\",\"equip_type\":\"(\\d+)\".*?quality\":\"(\\d+).*?buy_price\":\"(\\d+)\".*?is_checkup\":\"(\\d+)\".*?show_strengthen_tips",Pattern.UNICODE_CASE);
	private static final String CHECK_URL = "modules/role_item.php?act=check_item&item_type=temp&callback_func_name=itemClass.dragItemCallback&id=";
	//http://s4.verycd.9wee.com/modules/role_item.php?act=drag_item&id=4048839&from=temp&to=pack&timeStamp=1293058008242&callback_func_name=itemClass.dragItemCallback
	private static final String PUT_TO_PACK = "modules/role_item.php?act=drag_item&from=temp&to=pack&callback_func_name=itemClass.dragItemCallback&id=";
	private static final String SELL = "modules/role_item.php?act=drag_item&from=temp&to=shop&shop_id=0&callback_func_name=itemClass.dragItemCallback&id=";
	//http://s4.verycd.9wee.com/modules/role_item.php?act=drag_item&id=2972659&from=temp&to=none&&timeStamp=1280459658953&callback_func_name=itemClass.dragItemCallback
	private static final String GIVE_UP = "modules/role_item.php?act=drag_item&from=temp&to=none&callback_func_name=itemClass.dragItemCallback";
	//http://s4.verycd.9wee.com/modules/warrior.php?act=hall&op=stock&timeStamp=1317910014344&callback_func_name=ajaxCallback&callback_obj_name=dlg_view_stock
	private static String LIST_STOCK = "modules/warrior.php?act=hall&op=stock";
	//http://s4.verycd.9wee.com/modules/warrior.php?act=hall&op=check_pwd&timeStamp=1317909962943
	private static String CHECK_STOCK_PWD = "modules/warrior.php?act=hall&op=check_pwd";
	//http://s4.verycd.9wee.com/modules/warrior.php?act=hall&op=teamstock&timeStamp=1317356185300&callback_func_name=ajaxCallback&callback_obj_name=team_foster_stock
	private static String SI_HAI_KU_FANG = "modules/warrior.php?act=hall&op=teamstock";
	//public static final String mountWeapon = "modules/role_item.php?act=drag_item&from=pack&to=equip&callback_func_name=itemClass.dragItemCallback&id=";
	private static String WEAR_ITEM = "modules/role_item.php?act=drag_item&from=pack&to=equip&id=";
	private static Pattern p = Pattern.compile("item_id\":\"(\\d+)\",\"role_id\":\"\\d+\",\"name\":\"(\\S+?)\",\"equip_type\":\"(\\d+)\".*?\"position_x\":\"(\\d+)\",\"position_y\":\"(\\d+)\".*?binding_type\":\"(\\d+)\".*?max_superpose\":\"(\\d+)\",\"superpose\":\"(\\d+)\"");
	private static Pattern packItem = Pattern.compile("item_id.*?theory_max_strengthen");
	private static Pattern checkResult = Pattern.compile("\"success\":1",Pattern.DOTALL);
	
	public static void wearItem(User user,Item item){
		String url = user.getUrl()+WEAR_ITEM+item.getId()+Tools.getTimeStamp(true);
		String page = PageService.getPageWithCookie(url, user);
		if(Tools.success(page)){
			logger.info(user.getRoleName()+"装备"+item.getCNName()+"成功");
		}
	}
	
	public static List<Item> getTempPack(User user){
		String url = user.getUrl()+Portal.USER_INFO+Tools.getTimeStamp(true);
		String page = PageService.getPageWithCookie(url, user);
		Matcher m1 = temp.matcher(page);
		if(m1.find()){
			page = m1.group();
		}else{
			return null;
		}
		Matcher m = item.matcher(page);
		List<Item> l = new ArrayList<Item>();
		while(m.find()){
			l.add(new Item(m.group(1),Tools.hexToString(m.group(2)),m.group(3),m.group(4),m.group(6),Integer.parseInt(m.group(5))));
			
		}
		return l;
	}
	/*
	 * group(1) id
	 * group(2) name
	 * group(3) quality 4紫装  2 绿 3 蓝 
	 * group(4) check
	 * 
	 */
	private static void checkItem(User user){
		logger.info(user.getRoleName()+"开始鉴定物品");
		List<Item> l = ItemTools.getTempPack(user);
		if(l == null){
			logger.info(user.getRoleName()+"没有物品需要鉴定");
			return;
		}
		Iterator<Item> it = l.iterator();
		while(it.hasNext()){
			Item i = it.next();
			if(i.getChecked().equals("0") && i.getQuality().compareTo("3")>=0){
				checkIt(user, i.getId(),i.getName());
			}
			if(i.getQuality().compareTo("3")<0 && !i.getEquipType().equals(Item.HORSE)){
				sellItem(user, i.getId(),i.getName());
			}
		}
		
		l = ItemTools.getTempPack(user);
		if(l == null){
			logger.info(user.getRoleName()+"获得包裹信息失败");
			return;
		}
		it = l.iterator();
		while(it.hasNext()){
			Item i = it.next();
			int quality = Integer.parseInt(i.getQuality());
			if(quality < user.getQualitySave()){
				if(i.getSellPrice()>0 && !i.getEquipType().equals(Item.HORSE)){
					sellItem(user, i.getId(),i.getName());
				}
			}
			putToPack(user, i.getId(), i.getName());
		}		
		
		ItemMerge.merge(user);
	}
	
	public static void displayTempPack(User user){
		List<Item> l = ItemTools.getTempPack(user);
		if(l == null){
			logger.info(user.getRoleName()+"包裹是空的");
			return;
		}
		Iterator<Item> it = l.iterator();
		logger.info("开始检查"+user.getRoleName()+"的临时包裹");
		logger.info("----------------------------------------");
		while(it.hasNext()){
			Item i = it.next();
			logger.info(i.getName()+" "+getQualityName(i.getQuality()));
		}
		logger.info(user.getRoleName()+"的物品检查完毕");
	}
	public static void checkAndSell(User user){
		checkItem(user);
	}
	private static String getQualityName(String qualityId){
		int i = Integer.parseInt(qualityId);
		switch(i){
		case 1:return "普通装备";
		case 2:return "绿色，直接卖，别鉴定了";
		case 3:return "蓝色，值得鉴定";
		case 4:return "紫色装备";
		}
		return qualityId;
	}
	private static boolean putToPack(User user,String id,String name){
		String url = user.getUrl()+PUT_TO_PACK+id+Tools.getTimeStamp(true);
		String page = PageService.getPageWithCookie(url, user);
		logger.info(user.getRoleName()+"放"+name+"到包裹");
		return Tools.success(page);
	}
	private static boolean checkIt(User user,String id,String name){
		String url = user.getUrl()+CHECK_URL+id+Tools.getTimeStamp(true);
		String page = PageService.getPageWithCookie(url, user);
		logger.info(user.getRoleName()+"鉴定"+name+"成功");
		return Tools.success(page);
	}
	private static boolean sellItem(User user,String id,String name){
		String url = user.getUrl()+SELL+id+Tools.getTimeStamp(true);
		String page = PageService.getPageWithCookie(url, user);
		logger.info(user.getRoleName()+"售出"+name);
		return Tools.success(page);
	}
	private static boolean giveUp(User user,String id,String name){
		String url = user.getUrl()+GIVE_UP+id+Tools.getTimeStamp(true);
		String page = PageService.getPageWithCookie(url, user);
		logger.info(user.getRoleName()+"扔掉"+name);
		return Tools.success(page);
	}
	public static List<Item> getPack(User user){
		List<Item> l = new ArrayList<Item>();
		String url = user.getUrl()+Portal.USER_INFO+Tools.getTimeStamp(true);
		String page = PageService.getPageWithCookie(url, user);
		Matcher m = p.matcher(page);
		while(m.find()){
			l.add(ItemMerge.getItem(m));
		}
//		Matcher out = packItem.matcher(page);
//		while(out.find()){
//			String s = out.group();
//			Matcher m = p.matcher(s);
//			if(m.find()){
//				l.add(ItemMerge.getItem(m));
//			}
//		}
		return l;
	}
	public static List<Item> getStockList(User user){
		if(!ItemTools.checkStockPwd(user)){
			logger.info(user.getRoleName()+"仓库密码错误");
			return null;
		}
		
		List<Item> l = new ArrayList<Item>();
		String url = user.getUrl()+LIST_STOCK+Tools.getTimeStamp(true);
		String page = PageService.getPageWithCookie(url, user);
		int index = page.indexOf("\"stock\":{");
		int end = page.indexOf("\"my_equip\":");
		if(index < 0 || end <0){
			logger.info(user.getRoleName()+"获取仓库信息出错");
			return null;
		}
		page = page.substring(index,end);
		Matcher out = item.matcher(page);
		while(out.find()){
			String s = out.group();
			Matcher m = p.matcher(s);
			if(m.find()){
				l.add(new Item(m.group(1),m.group(2),m.group(3),m.group(4),m.group(5),m.group(8),m.group(7),"0".equals(m.group(6))));
			}
		}
		return l;
	}
	private static boolean checkStockPwd(User user){
		String url = user.getUrl()+CHECK_STOCK_PWD+Tools.getTimeStamp(true);
		String data = "pwd="+user.getStockPwd()+"&callback_func_name=callback_submit_form_check_pwd&callback_obj_name=dlg_check_pwd";
		String page = PageService.postPage(url, data, user);
		Matcher m = checkResult.matcher(page);
		if(m.find()){
			return true;
		}
		return false;
	}
	public static List<Item> getSiHaiKuFang(User user){
		List<Item> l = new ArrayList<Item>();
		String url = user.getUrl()+SI_HAI_KU_FANG+Tools.getTimeStamp(true);
		String page = PageService.getPageWithCookie(url, user);
		if(page.indexOf("你的四海库房已经到期")!=-1){
			if(user.isNeedAutoRent()){
				rentStock(user);
				return getSiHaiKuFang(user);
			}
		}
		int index = page.indexOf("teamstock\":{");
		int end = page.indexOf("\"my_equip\":");
		if(index == -1){
			logger.info(user.getRoleName()+"四海库房到期");
			return null;
		}
		page = page.substring(index,end);
		Matcher out = item.matcher(page);
		while(out.find()){
			String s = out.group();
			Matcher m = p.matcher(s);
			if(m.find()){
				l.add(ItemMerge.getItem(m));
			}
		}
		return l;
	}
	private static void rentStock(User user){
		//radio_team_stock_daynum=price_ten&confirm_button=%E7%A1%AE%E5%AE%9A&cancel_button=%E5%8F%96%E6%B6%88&callback_func_name=ajaxCallback
		//http://s4.verycd.9wee.com/modules/team_foster.php?act=build&action=stockhire&timeStamp=1340174658049
		String url = user.getUrl()+"modules/team_foster.php?act=build&action=stockhire"+Tools.getTimeStamp(true);
		String data = "radio_team_stock_daynum=price_ten&confirm_button=%E7%A1%AE%E5%AE%9A&cancel_button=%E5%8F%96%E6%B6%88&callback_func_name=ajaxCallback";
		String page = PageService.postPage(url, data, user);
		page = Tools.hexToString(page);
		if(page.indexOf("成功")!=-1){
			logger.info(user.getRoleName()+"续租四海库房成功");
		}
	}
}
