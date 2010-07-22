package com.blue.monstor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.blue.common.Move;
import com.blue.common.Portal;
import com.blue.common.User;
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
 * 挂起训练：http://s4.verycd.9wee.com/modules/auto_combats.php?act=complete&isfree=1&timeStamp=1279182562076&callback_func_name=callbackFnCancelAutoCombat
 * 怪物信息
 * http://s4.verycd.9wee.com/modules/auto_combats.php?act=show&mid=63&timeStamp=1279615833845&callback_func_name=ajaxCallback&callback_obj_name=dlg_view_monster
 * 
 * @author Administrator
 *
 */


public class Monstor {
	private static final String KILL_URL="modules/auto_combats.php?act=start";
	private static final String CHECK_URL = "modules/role_item.php?act=check_item&item_type=temp&callback_func_name=itemClass.dragItemCallback&id=";
	private static final String PUT_TO_PACK = "modules/role_item.php?act=drag_item&from=temp&to=pack&callback_func_name=itemClass.dragItemCallback&id=";
	private static final String SELL = "modules/role_item.php?act=drag_item&from=temp&to=shop&shop_id=0&callback_func_name=itemClass.dragItemCallback&id=";
	
	private Pattern p = Pattern.compile("monster_id\":\"(\\d+)\",\"level_range\":\"Lv.(\\d+)-(\\d+)");
	//id+name+quality+checked
	private Pattern item = Pattern.compile("item_id\":\"(\\d+)\",\"role_id\":\"\\d+\",\"name\":\"(\\S+?)\",.*?quality\":\"(\\d+).*?is_checkup\":\"(\\d+)\"",Pattern.UNICODE_CASE);
	private Pattern temp = Pattern.compile("temp\":\\{.*");
	
	public boolean killMonstor(User user,Portal p)throws Exception{
		p.setUserInfo(user);
		return moveToMonstor(user);	
	}
	private boolean moveToMonstor(User user)throws Exception{
		String level = user.getLevel();
		String[] monstor = LevelVSMonstor.getMonstorInfo(level);
		int times = 3;
		String page = Move.worldMove(user, monstor[0]);
		while(!Tools.success(page) && times > 0){
			System.out.println("move to "+monstor[0]+" failed! retry!");
			times--;
		}
		if(times == 0){
			return false;
		}
		times = 3;
		page = Move.secMove(user, monstor[1]);
		while(!Tools.success(page) && times > 0){
			System.out.println("move to "+monstor[0]+" failed! retry!");
			times--;
		}
		if(times == 0){
			return false;
		}
		times = 3;
		page = Move.thirdMove(user, monstor[2]);
		while(!Tools.success(page) && times > 0){
			System.out.println("move to "+monstor[0]+" failed! retry!");
			times--;
		}
		if(times == 0){
			return false;
		}
		Matcher m = p.matcher(page);
		String mid = null;
		while(m.find()){
			if(m.group(2).equals(level)){
				mid = m.group(1);
				break;
			}
		}
		return killIt(mid, user);
	}
	private boolean killIt(String monstor,User user)throws Exception{
		String url = user.getUrl()+KILL_URL+Tools.getTimeStamp(true);
		String page = PageService.postPage(url, getData(monstor,user), user);
		return Tools.success(page);
	}
	private String getData(String monstor,User user){
		return "mid="+monstor+"&select_frequency="+user.getKillMonstorOnce()+"&callback_func_name=callbackFnStartAutoCombat";
	}
	/*
	 * group(1) id
	 * group(2) name
	 * group(3) quality 4紫装  2 绿 3 蓝 
	 * group(4) check
	 * 
	 */
	public void checkItem(User user){
		List<Item> l = getTempPack(user);
		Iterator<Item> it = l.iterator();
		while(it.hasNext()){
			Item i = it.next();
			if(i.getChecked().equals("0")){
				checkIt(user, i.getId());
			}
			if(i.getChecked().equals("1") && i.getQuality().compareTo("3")<0){
				sellItem(user, i.getId());
			}
		}
		l = getTempPack(user);
		it = l.iterator();
		while(it.hasNext()){
			Item i = it.next();
			if(i.getQuality().compareTo("3")>=0){
				putToPack(user, i.getId());
			}
		}		
		
	}
	private List<Item> getTempPack(User user){
		String url = user.getUrl()+Portal.USER_INFO+Tools.getTimeStamp(true);
		String page = PageService.getPageWithCookie(url, user);
		Matcher m1 = temp.matcher(page);
		if(m1.find()){
			page = m1.group();
		}
		Matcher m = item.matcher(page);
		List<Item> l = new ArrayList<Item>();
		while(m.find()){
			l.add(new Item(m.group(1),Tools.hexToString(m.group(2)),m.group(3),m.group(4)));
			
		}
		return l;
	}
	private boolean putToPack(User user,String id){
		String url = user.getUrl()+PUT_TO_PACK+id+Tools.getTimeStamp(true);
		String page = PageService.getPageWithCookie(url, user);
		return Tools.success(page);
	}
	private boolean checkIt(User user,String id){
		String url = user.getUrl()+CHECK_URL+id+Tools.getTimeStamp(true);
		String page = PageService.getPageWithCookie(url, user);
		return Tools.success(page);
	}
	private boolean sellItem(User user,String id){
		String url = user.getUrl()+SELL+id+Tools.getTimeStamp(true);
		String page = PageService.getPageWithCookie(url, user);
		return Tools.success(page);
	}
}
