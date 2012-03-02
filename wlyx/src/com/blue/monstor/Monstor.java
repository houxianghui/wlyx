package com.blue.monstor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.blue.beauty.Beauty;
import com.blue.common.Move;
import com.blue.common.Portal;
import com.blue.common.User;
import com.blue.team.WuGuan;
import com.blue.tools.ItemTools;
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
	private static Logger logger = Logger.getLogger(Monstor.class);
	
	private static final String KILL_URL="modules/auto_combats.php?act=start";
		//http://s4.verycd.9wee.com/modules/auto_combats.php?act=view&rand=1280208689250&timeStamp=1280208680546&callback_func_name=ajaxCallback&callback_obj_name=dlg_view_monster
	public static final String VIEW_COMBAT="modules/auto_combats.php?act=view&callback_func_name=ajaxCallback&callback_obj_name=dlg_view_monster&id=";
	
	public static final String FREE_FINISH = "modules/auto_combats.php?act=complete&isfree=1&callback_func_name=callbackFnCancelAutoCombat";
	//http://s4.verycd.9wee.com/modules/role_item.php?act=repair_all_item&timeStamp=1280243508295&callback_func_name=itemClass.dragItemCallback
	public static final String REPAIR = "modules/role_item.php?act=repair_all_item&callback_func_name=itemClass.dragItemCallback";
	//http://s4.verycd.9wee.com/modules/scene_brick.php?act=down&rand=1281160957975&timeStamp=1281160945682&callback_func_name=callbackfnWarSceneBrick
	
	private static Pattern p = Pattern.compile("monster_id\":\"(\\d+)\",\"level_range\":\"Lv.(\\d+)-(\\d+)");
	//id+name+quality+checked
	private static Pattern freeFinish = Pattern.compile("免费完成修炼");
	private static Pattern getMonstorId = Pattern.compile("fnMoveToScene\\(\\s+(\\d+),",Pattern.DOTALL);
	public static boolean killMonstor(User user){
//		Portal.setUserInfo(user);
		if(Integer.parseInt(user.getPoint()) <= user.getSavePoint()){
			return Portal.goHome(user);
		}
		if(!user.isShouldKillMonstor()){
			return Portal.goHome(user);
		}
		if(!user.getStatus().equals("修炼中") && !user.getStatus().equals("战斗中")){
			ItemTools.checkAndSell(user);
		}
		return moveToMonstor(user);	
	}
	public static boolean canFreeFinish(User user){
		String url = user.getUrl()+VIEW_COMBAT+Tools.getRandAndTime();
		String page = PageService.getPageWithCookie(url, user);
		Matcher m = freeFinish.matcher(page);
		return m.find();
	}
	public static String getMonstorUrl(User user){
		//http://s4.verycd.9wee.com/modules/upgrade_help.php?act=practice&timeStamp=1329989639482&callback_func_name=ajaxCallback&callback_obj_name=dlg_view_practice
		String url =user.getUrl()+ "modules/upgrade_help.php?act=practice"+Tools.getTimeStamp(true);
		String page = PageService.getPageWithCookie(url, user);
		Matcher m = getMonstorId.matcher(page);
		//http://s4.verycd.9wee.com/modules/scene_walk.php?action=scene_move&scene_id=510&pk_status=0&rand=1329990188128&timeStamp=1329990180146&callback_func_name=callbackFnMoveToScene
		StringBuffer backUrl = null;
		if(m.find()){
			backUrl = new StringBuffer(user.getUrl()+"modules/scene_walk.php?action=scene_move&scene_id=");
			backUrl.append(m.group(1));
			backUrl.append("&pk_status=0");
			backUrl.append(Tools.getRandAndTime());
		}
		return backUrl == null?null:backUrl.toString();
	}
	private static boolean moveToMonstor(User user){
		
		if(!user.isCanMove()){
			logger.info(user.getRoleName()+user.getStatus()+",停止移动");
			return true;
		}
		//离开武馆
		WuGuan.leaveTeam(user);
		
		String page = null;
		try{
//			page = move(user);
			page = PageService.getPageWithCookie(getMonstorUrl(user), user);
			page = page.substring(page.indexOf("monster"));
		}catch(Exception e){
			return false;
		}
		
		Matcher m = p.matcher(page);
		String mid = null;
		int l = Integer.parseInt(user.getLevel())+3;
		String tmp = null;
		String noChoice = null;
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
			if(l-i == 2){
				noChoice = m.group(1);
			}
			
		}
		if(!find){
			if(tmp != null)
				mid = tmp;
			else
				mid = noChoice;
		}
		return killIt(mid, user);
	}
	
	private static String move(User user) throws Exception{
		String[] monstor = LevelVSMonstor.getMonstorInfo(user.getLevel());
		String page = null;
		int times = 3;
		while(times > 0){
			page = Move.worldMove(user, monstor[0]);
			if(!Tools.success(page)){
				times--;
				continue;
			}else{
				break;
			}
		}
		if(times <= 0){
			logger.info(user.getRoleName()+"连续3次移动失败，停止移动");			
			Portal.goHome(user);
			throw new Exception();
		}
		page = Move.secMove(user, monstor[1]);
		if(monstor.length == 3){
			if(monstor[2]!=null && monstor[2].trim().length() != 0){
				page = Move.thirdMove(user, monstor[2]);			
			}
		}
		return page;
	}
	public static boolean repairAll(User user){
		String url = user.getUrl()+REPAIR+Tools.getRandAndTime();
		String page = PageService.getPageWithCookie(url, user);
		
		if(Tools.success(page)){
			logger.info(user.getRoleName()+"修理装备");
			return true;
		}
		return false;
	}
	private static boolean killIt(String monstor,User user){
		if(user.isNeedBeauty()){
			Beauty.jingYan(user);
		}
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
			killIt(monstor,user);
		}
		return Tools.success(page);
	}
	
	private static String getData(String monstor,User user){
		int point = Integer.parseInt(user.getPoint());
		int killOnce = Integer.parseInt(user.getKillMonstorOnce());
		if(point - killOnce <= user.getSavePoint()){
			killOnce = point - user.getSavePoint();
		}
		
		return "mid="+monstor+"&select_frequency="+killOnce+"&callback_func_name=callbackFnStartAutoCombat";
	}
	
}
