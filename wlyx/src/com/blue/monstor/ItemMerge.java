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
	private static String PACK_TO_STOCK = "modules/role_item.php?act=drag_item&from=pack&to=stock";
	//http://s4.verycd.9wee.com/modules/warrior.php?act=hall&op=stock&timeStamp=1317910014344&callback_func_name=ajaxCallback&callback_obj_name=dlg_view_stock
	private static String LIST_STOCK = "modules/warrior.php?act=hall&op=stock";
	//http://s4.verycd.9wee.com/modules/warrior.php?act=hall&op=check_pwd&timeStamp=1317909962943
	private static String CHECK_STOCK_PWD = "modules/warrior.php?act=hall&op=check_pwd";
	
	private static Pattern p = Pattern.compile("item_id\":\"(\\d+)\",\"role_id\":\"\\d+\",\"name\":\"(\\S+?)\",\"equip_type\":\"(\\d+)\".*?\"position_x\":\"(\\d+)\",\"position_y\":\"(\\d+)\".*?binding_type\":\"(\\d+)\".*?max_superpose\":\"(\\d+)\",\"superpose\":\"(\\d+)\"");
	private static Pattern item = Pattern.compile("item_id.*?theory_max_strengthen");
	private static Pattern checkResult = Pattern.compile("\"success\":1",Pattern.DOTALL);
	
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
					logger.info(user.getRoleName()+"合并"+Tools.hexToString(i.getName())+"到四海库房成功");
				}
			}
		}
	}
	private static void mergeToStock(User user,List l,Map<String, Item> m){		
		Iterator<Item> it = l.iterator();
		while(it.hasNext()){
			Item i = it.next();
			if(i.getCount().equals(i.getMaxCount()) || !i.getEquipType().equals("0")){
				continue;
			}
			Item to = m.get(i.getName());
			if(to!=null){
				String data = "&id="+i.getId()+"&pos_x="+to.getPositonX()+"&pos_y="+to.getPositionY()+"&quantity="+i.getCount();
				String url = user.getUrl()+PACK_TO_STOCK+data+Tools.getTimeStamp(true);
				String page = PageService.getPageWithCookie(url, user);
				if(Tools.success(page)){
					logger.info(user.getRoleName()+"合并"+Tools.hexToString(i.getName())+"到仓库成功");
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
					logger.info(user.getRoleName()+"合并"+Tools.hexToString(i.getName())+"成功");
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
		if(l == null || l.size() == 0){
			return;
		}
		Map<String,Item> m = new HashMap<String, Item>();
		for(Item i:l){
			if(i.getEquipType().equals("0") && !i.getCount().equals(i.getMaxCount())){
				m.put(i.getName(), i);
			}
		}
		mergeToTeamStock(user,getPack(user),m);
	}
	public static void mergeStock(User user){
		List<Item> l = getStockList(user);
		if(l == null || l.size() == 0){
			return;
		}
		Map<String,Item> m = new HashMap<String, Item>();
		for(Item i:l){
			if(i.getEquipType().equals("0") && !i.getCount().equals(i.getMaxCount())){
				m.put(i.getName(), i);
			}
		}
		mergeToStock(user,getPack(user),m);
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
	private static List<Item> getStockList(User user){
		if(!checkStockPwd(user)){
			logger.info(user.getRoleName()+"仓库密码错误");
			return null;
		}
		
		List<Item> l = new ArrayList<Item>();
		String url = user.getUrl()+LIST_STOCK+Tools.getTimeStamp(true);
		String page = PageService.getPageWithCookie(url, user);
		int index = page.indexOf("\"stock\":{");
		page = page.substring(index);
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
	private static List<Item> getSiHaiKuFang(User user){
		List<Item> l = new ArrayList<Item>();
		String url = user.getUrl()+SI_HAI_KU_FANG+Tools.getTimeStamp(true);
		String page = PageService.getPageWithCookie(url, user);
		int index = page.indexOf("teamstock\":{");
		if(index == -1){
			logger.info(user.getRoleName()+"四海库房到期");
			return null;
		}
		page = page.substring(index);
		Matcher out = item.matcher(page);
		while(out.find()){
			String s = out.group();
			Matcher m = p.matcher(s);
			if(m.find()){
				l.add(getItem(m));
			}
		}
		return l;
	}
	private static Item getItem(Matcher m ){
		return new Item(m.group(1),m.group(2),m.group(3),m.group(4),m.group(5),m.group(8),m.group(7),"0".equals(m.group(6)));
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
				l.add(getItem(m));
			}
		}
		return l;
	}
	public static void find(String key,final List<User> users){
		String[] regexs = key.split(" ");
		if(regexs.length == 0){
			logger.info("请输入关键字");
			return;
		}
		StringBuffer regex = new StringBuffer();
		for(int i = 0;i<regexs.length;i++){
			regex.append(regexs[i]);
			if(i<regexs.length-1){
				regex.append(".*?");
			}
		}
		final Pattern p = Pattern.compile(regex.toString());
		for(final User user:users){
			new Thread(){
				public void run() {
					List<Item> l = getPack(user);
					List<Item> stock = getStockList(user);
					if(stock != null){
						l.addAll(stock);
					}
					List<Item> teamStock = getSiHaiKuFang(user);
					if(teamStock != null){
						l.addAll(teamStock);
					}
					boolean find = false;
					for(Item i:l){
						Matcher m = p.matcher(i.getCNName());
						if(m.find()){
							find = true;
							logger.info(user.getRoleName()+" "+i.getCNName()+" "+("0".equals(i.getCount())?"":i.getCount()+"个 ")+(i.isBind()?"已":"未")+"绑定");
						}
					}
					if(!find){
						logger.info(user.getRoleName()+"未找到");
					}
				};
			}.start();
			
		}
	}
}
