package com.blue.monstor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.blue.common.User;
import com.blue.tools.ItemTools;
import com.blue.tools.PageService;
import com.blue.tools.Tools;

public class Attack {
	private static Logger logger = Logger.getLogger(Attack.class);
	//http://s4.verycd.9wee.com/modules/monster_fight.php?mid=1004&rand=1320976260071&timeStamp=1320976257133&callback_func_name=callbackFnMonsterAction
	//{"success":"211137033910"}
	/*
	 * combatLive.combatLiveSpeed = 3;
		combatLive.combatStartTime = 63;
	 */
	private static Pattern speed = Pattern.compile("combatLive.combatLiveSpeed = (\\d+)");
	private static Pattern time = Pattern.compile("\"t\":(\\d+),\"f\":\"finish\"");
	private static Pattern p = Pattern.compile("success\":\"(\\S+?)\"");
	public static boolean attack(User user,String mid){
		String url = user.getUrl()+"modules/monster_fight.php?mid="+mid+Tools.getRandAndTime();
		String page = PageService.getPageWithCookie(url, user);
		Matcher m = p.matcher(page);
		if(m.find()){
			String id = m.group(1);
			logger.info(user.getRoleName()+"¿ªÊ¼¹¥»÷");
//			return true;
			//http://s4.verycd.9wee.com/modules/view_combat.php?combat_id=211137040109&start=0&rand=1320981556523&timeStamp=1320981550177&callback_func_name=ajaxCallback&callback_obj_name=dlg_view_combat
			//http://s4.verycd.9wee.com/modules/view_combat.php?combat_id=211137033910&start=0&rand=1320976260461&timeStamp=1320976257133&callback_func_name=ajaxCallback&callback_obj_name=dlg_view_combat
			url = user.getUrl()+"modules/view_combat.php?combat_id="+id+"&start=0"+Tools.getRandAndTime();
			page = PageService.getPageWithCookie(url, user);
			
			Matcher spd = speed.matcher(page);
			int speed = 1;
			int ct = 1;
			if(spd.find()){
				speed = Integer.parseInt(spd.group(1));
			}
			Matcher tm = time.matcher(page);
			if(tm.find()){
				ct = Integer.parseInt(tm.group(1));
			}
			int wait = ct/speed;
			try{
				Thread.sleep((wait+1)*1000);
				ItemTools.checkAndSell(user);
				Monstor.repairAll(user);
			}catch(Exception e){
				return false;
			}
			return true;
		}else{
			try{
				Thread.sleep(1*1000);
				return false;
			}catch(Exception e){
				return false;
			}
		}
	}
}
