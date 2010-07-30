package com.blue.monstor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.blue.beauty.Beauty;
import com.blue.common.Move;
import com.blue.common.Portal;
import com.blue.common.User;
import com.blue.task.AutoTask;
import com.blue.tools.PageService;
import com.blue.tools.Tools;

/**
 * check item 
 * http://s4.verycd.9wee.com/modules/role_item.php?act=check_item&item_type=temp&id=2962891&timeStamp=1279249487002&callback_func_name=itemClass.dragItemCallback
 * http://s4.verycd.9wee.com/modules/role_item.php?act=check_item&item_type=temp&id=2961514&timeStamp=1279249563631&callback_func_name=itemClass.dragItemCallback
 * 
 * 入包
 * http://s4.verycd.9wee.com/modules/role_item.php?act=drag_item&id=2962891&from=temp&to=pack&timeStamp=1279249615760&callback_func_name=itemClass.dragItemCallback
 * 全部出售
 * http://s4.verycd.9wee.com/modules/role_item.php?act=sell_all_temp&timeStamp=1279249631858
 * 
 * 出售
 * http://s4.verycd.9wee.com/modules/role_item.php?act=drag_item&id=3011599&from=temp&to=shop&shop_id=0&timeStamp=1279793643031&callback_func_name=itemClass.dragItemCallback
 * 
 * 自动完成免费：http://s4.verycd.9wee.com/modules/auto_combats.php?act=show&mid=29&timeStamp=1279182628328&callback_func_name=ajaxCallback&callback_obj_name=dlg_view_monster
 * http://s4.verycd.9wee.com/modules/auto_combats.php?act=complete&isfree=1&timeStamp=1280214500500&callback_func_name=callbackFnCancelAutoCombat
 * 挂起训练：http://s4.verycd.9wee.com/modules/auto_combats.php?act=complete&isfree=1&timeStamp=1279182562076&callback_func_name=callbackFnCancelAutoCombat
 * 怪物信息
 * http://s4.verycd.9wee.com/modules/auto_combats.php?act=show&mid=63&timeStamp=1279615833845&callback_func_name=ajaxCallback&callback_obj_name=dlg_view_monster
 * 
 * @author Administrator
 *
 */


public class Monstor {
	private Logger logger = Logger.getLogger(Monstor.class);
	
	private static final String KILL_URL="modules/auto_combats.php?act=start";
	private static final String CHECK_URL = "modules/role_item.php?act=check_item&item_type=temp&callback_func_name=itemClass.dragItemCallback&id=";
	private static final String PUT_TO_PACK = "modules/role_item.php?act=drag_item&from=temp&to=pack&callback_func_name=itemClass.dragItemCallback&id=";
	private static final String SELL = "modules/role_item.php?act=drag_item&from=temp&to=shop&shop_id=0&callback_func_name=itemClass.dragItemCallback&id=";
	//http://s4.verycd.9wee.com/modules/role_item.php?act=drag_item&id=2972659&from=temp&to=none&&timeStamp=1280459658953&callback_func_name=itemClass.dragItemCallback
	private static final String GIVE_UP = "modules/role_item.php?act=drag_item&from=temp&to=none&callback_func_name=itemClass.dragItemCallback";
	//http://s4.verycd.9wee.com/modules/auto_combats.php?act=view&rand=1280208689250&timeStamp=1280208680546&callback_func_name=ajaxCallback&callback_obj_name=dlg_view_monster
	public static final String VIEW_COMBAT="modules/auto_combats.php?act=view&callback_func_name=ajaxCallback&callback_obj_name=dlg_view_monster&id=";
	
	public static final String FREE_FINISH = "modules/auto_combats.php?act=complete&isfree=1&callback_func_name=callbackFnCancelAutoCombat";
	//http://s4.verycd.9wee.com/modules/role_item.php?act=repair_all_item&timeStamp=1280243508295&callback_func_name=itemClass.dragItemCallback
	public static final String REPAIR = "modules/role_item.php?act=repair_all_item&callback_func_name=itemClass.dragItemCallback";
	
	private Pattern p = Pattern.compile("monster_id\":\"(\\d+)\",\"level_range\":\"Lv.(\\d+)-(\\d+)");
	//id+name+quality+checked
	private Pattern item = Pattern.compile("item_id\":\"(\\d+)\",\"role_id\":\"\\d+\",\"name\":\"(\\S+?)\",.*?quality\":\"(\\d+).*?sell_price\":\"(\\d+)\".*?is_checkup\":\"(\\d+)\"",Pattern.UNICODE_CASE);
	private Pattern temp = Pattern.compile("temp\":\\{\".*pack",Pattern.DOTALL);
	private Pattern freeFinish = Pattern.compile("免费完成修炼");
	public boolean killMonstor(User user,AutoTask at)throws Exception{
		Portal.setUserInfo(user);
		if(Integer.parseInt(user.getPoint()) <= user.getSavePoint()){
			return Portal.goHome(user);
		}
		if(!user.isShouldKillMonstor()){
			return Portal.goHome(user);
		}
		if(!user.getStatus().equals("修炼中") && !user.getStatus().equals("战斗中")){
			checkItem(user);
		}
		return moveToMonstor(user,at);	
	}
	public boolean canFreeFinish(User user){
		String url = user.getUrl()+VIEW_COMBAT+Tools.getRandAndTime();
		String page = PageService.getPageWithCookie(url, user);
		Matcher m = freeFinish.matcher(page);
		return m.find();
	}
	private  boolean moveToMonstor(User user,AutoTask at)throws Exception{
		String level = user.getLevel();
		String[] monstor = LevelVSMonstor.getMonstorInfo(level);
		if(!user.isCanMove()){
			logger.info(user.getRoleName()+user.getStatus()+",停止移动");
			return true;
		}
		String page = null;
		int times = 3;
		while(times > 0){
			page = Move.worldMove(user, monstor[0],at);
			if(!Tools.success(page)){
				times--;
				continue;
			}else{
				break;
			}
		}
		if(times <= 0){
			logger.info("连续3次移动失败，停止移动");			
			user.setBeginTime(0);
			user.setEndTime(0);
			return false;
		}
		page = Move.secMove(user, monstor[1]);
		if(monstor.length == 3){
			if(monstor[2]!=null && monstor[2].trim().length() != 0){
				page = Move.thirdMove(user, monstor[2]);			
			}
		}
		Matcher m = p.matcher(page);
		String mid = null;
		int l = Integer.parseInt(level)+3;
		String tmp = null;
		boolean find = false;
		while(m.find()){
			int i = Integer.parseInt(m.group(2));
			if(i == l){
				mid = m.group(1);
				find = true;
				break;
			}
			if(l-i == 1){
				tmp = m.group(1);
			}
			
		}
		if(!find){
			mid = tmp;
		}
		return killIt(mid, user);
	}
	private boolean repairAll(User user){
		String url = user.getUrl()+REPAIR+Tools.getRandAndTime();
		String page = PageService.getPageWithCookie(url, user);
		
		if(Tools.success(page)){
			logger.info(user.getRoleName()+"修理装备");
			return true;
		}
		return false;
	}
	private boolean killIt(String monstor,User user)throws Exception{
		Beauty.jingYan(user);
		repairAll(user);
		String url = user.getUrl()+KILL_URL+Tools.getTimeStamp(true);
		String page = PageService.postPage(url, getData(monstor,user), user);
		if(Tools.success(page)){
			logger.info(user.getRoleName()+"正在对"+monstor+"发起自动攻击");
		}else{
			
		}
		if(canFreeFinish(user)){
			String free = user.getUrl()+FREE_FINISH+monstor+Tools.getTimeStamp(true);
			page = PageService.getPageWithCookie(free, user);
			logger.info(user.getRoleName()+"免费自动完成修炼1次");
		}
		return Tools.success(page);
	}
	
	private String getData(String monstor,User user){
		int point = Integer.parseInt(user.getPoint());
		int killOnce = Integer.parseInt(user.getKillMonstorOnce());
		if(point - killOnce <= user.getSavePoint()){
			killOnce = point - user.getSavePoint();
		}
		
		return "mid="+monstor+"&select_frequency="+killOnce+"&callback_func_name=callbackFnStartAutoCombat";
	}
	/*
	 * group(1) id
	 * group(2) name
	 * group(3) quality 4紫装  2 绿 3 蓝 
	 * group(4) check
	 * 
	 */
	private void checkItem(User user){
		logger.info(user.getRoleName()+"开始鉴定物品");
		List<Item> l = getTempPack(user);
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
			if(i.getQuality().compareTo("3")<0){
				sellItem(user, i.getId(),i.getName());
			}
		}
		l = getTempPack(user);
		it = l.iterator();
		while(it.hasNext()){
			Item i = it.next();
			int quality = Integer.parseInt(i.getQuality());
			if(quality < user.getQualitySave()){
				if(i.getSellPrice()>0){
					sellItem(user, i.getId(),i.getName());
				}else{
					if(i.getQuality().compareTo("3")>0){
						putToPack(user, i.getId(),i.getName());
					}else{
						giveUp(user,i.getId(),i.getName());
					}
				}
			}
		}		
		
	}
	
	private List<Item> getTempPack(User user){
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
			l.add(new Item(m.group(1),Tools.hexToString(m.group(2)),m.group(3),m.group(5),Integer.parseInt(m.group(4))));
			
		}
		return l;
	}
	public void displayTempPack(User user){
		List<Item> l = getTempPack(user);
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
	public void checkAndSell(User user){
		checkItem(user);
	}
	private String getQualityName(String qualityId){
		int i = Integer.parseInt(qualityId);
		switch(i){
		case 1:return "普通装备";
		case 2:return "绿色，直接卖，别鉴定了";
		case 3:return "蓝色，值得鉴定";
		case 4:return "紫色装备";
		}
		return qualityId;
	}
	private boolean putToPack(User user,String id,String name){
		String url = user.getUrl()+PUT_TO_PACK+id+Tools.getTimeStamp(true);
		String page = PageService.getPageWithCookie(url, user);
		logger.info(user.getRoleName()+"放"+name+"到包裹");
		return Tools.success(page);
	}
	private boolean checkIt(User user,String id,String name){
		String url = user.getUrl()+CHECK_URL+id+Tools.getTimeStamp(true);
		String page = PageService.getPageWithCookie(url, user);
		logger.info("鉴定"+name+"成功");
		return Tools.success(page);
	}
	private boolean sellItem(User user,String id,String name){
		String url = user.getUrl()+SELL+id+Tools.getTimeStamp(true);
		String page = PageService.getPageWithCookie(url, user);
		logger.info(user.getRoleName()+"售出"+name);
		return Tools.success(page);
	}
	private boolean giveUp(User user,String id,String name){
		String url = user.getUrl()+GIVE_UP+id+Tools.getTimeStamp(true);
		String page = PageService.getPageWithCookie(url, user);
		logger.info(user.getRoleName()+"扔掉"+name);
		return Tools.success(page);
	}
}
