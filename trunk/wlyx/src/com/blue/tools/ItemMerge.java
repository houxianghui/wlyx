package com.blue.tools;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;

import org.apache.log4j.Logger;

import com.blue.common.User;

public class ItemMerge {
	static Logger logger = Logger.getLogger(ItemMerge.class);
	//http://s4.verycd.9wee.com/modules/role_item.php?act=drag_item&id=1597716&from=pack&to=pack&pos_x=6&pos_y=2&timeStamp=1285146745061&callback_func_name=itemClass.dragItemCallback
	//http://s4.verycd.9wee.com/modules/role_item.php?act=drag_item&id=1597712&from=pack&to=pack&pos_x=6&pos_y=2&quantity=4&timeStamp=1285151539353&callback_func_name=itemClass.dragItemCallback
	private static String MERGE = "modules/role_item.php?act=drag_item&from=pack&to=pack&callback_func_name=itemClass.dragItemCallback";
	//http://s4.verycd.9wee.com/modules/role_item.php?act=drag_item&id=2878778&from=pack&to=teamstock&pos_x=0&pos_y=7&timeStamp=1317362887977&callback_func_name=itemClassTeamStk.dragItemCallback
	private static String PACK_TO_SI_HAI = "modules/role_item.php?act=drag_item&from=pack&to=teamstock";
	private static String PACK_TO_STOCK = "modules/role_item.php?act=drag_item&from=pack&to=stock";
	
	
	public static void merge(User user){
		List<Item> l = ItemTools.getPack(user);
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
		List<Item> l = ItemTools.getSiHaiKuFang(user);
		if(l == null || l.size() == 0){
			return;
		}
		Map<String,Item> m = new HashMap<String, Item>();
		for(Item i:l){
			if(i.getEquipType().equals("0") && !i.getCount().equals(i.getMaxCount())){
				m.put(i.getName(), i);
			}
		}
		mergeToTeamStock(user,ItemTools.getPack(user),m);
	}
	public static void mergeStock(User user){
		List<Item> l = ItemTools.getStockList(user);
		if(l == null || l.size() == 0){
			return;
		}
		Map<String,Item> m = new HashMap<String, Item>();
		for(Item i:l){
			if(i.getEquipType().equals("0") && !i.getCount().equals(i.getMaxCount())){
				m.put(i.getName(), i);
			}
		}
		mergeToStock(user,ItemTools.getPack(user),m);
	}
	static Item getItem(Matcher m ){
		return new Item(m.group(1),m.group(2),m.group(3),m.group(4),m.group(5),m.group(8),m.group(7),"1".equals(m.group(6)));
	}
	public static void find(String key,final List<User> users){
		final String[] regexs = key.split(" ");
		if(regexs.length == 0){
			logger.info("请输入关键字");
			return;
		}
		
		for(final User user:users){
			new Thread(){
				public void run() {
					List<Item> l = ItemTools.getPack(user);
					List<Item> stock = ItemTools.getStockList(user);
					if(stock != null){
						l.addAll(stock);
					}
					List<Item> teamStock = ItemTools.getSiHaiKuFang(user);
					if(teamStock != null){
						l.addAll(teamStock);
					}
					boolean find = true;
					boolean match = false;
					for(Item i:l){
						find = true;
						for(String s:regexs){
							if(i.getCNName().indexOf(s) < 0){
								find = false;
								break;
							}
						}
						if(find){
							match = true;
							logger.info(user.getRoleName()+" "+i.getCNName()+" "+("0".equals(i.getCount())?"":i.getCount()+"个 ")+(i.isBind()?"已":"未")+"绑定");
						}
					}
					if(!match){
						logger.info(user.getRoleName()+"未找到");
					}
				};
			}.start();
			
		}
	}
}
