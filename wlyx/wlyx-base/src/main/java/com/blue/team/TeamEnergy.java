/**
 * 监控武馆经验 ，达到最大值自动分配
 */
package com.blue.team;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.blue.common.Portal;
import com.blue.common.User;
import com.blue.tools.PageService;
import com.blue.tools.Tools;

public class TeamEnergy {
	//http://s4.verycd.9wee.com/modules/team.php?act=view_energy&team_id=65&timeStamp=1318930245166&callback_func_name=ajaxCallback&callback_obj_name=view_energy_box
	private static final String VIEW_ENERGY= "modules/team.php?act=view_energy&team_id=";
	//http://s4.verycd.9wee.com/modules/team.php?act=send_energy&submit=1&timeStamp=1318930261988&callback_func_name=ajaxCallback
	private static final String SEND_ENERGY = "modules/team.php?act=send_energy&submit=1";
	
	private static Pattern fullEnergy = Pattern.compile("青龙门:<span class=\"highlight\" titlecontent=\"积累上限：(\\S+?)<br />牌匾加成：.*?\">(\\S+?)</span>"); 
	public static void sendEnergy(User user){
		if(needSend()){
			doSendEnergy(user);
			return;
		}else{
			return;
		}
//		Portal.setTeamId(user);
//		if(Tools.isEmpty(user.getTeamId())){
//			return;
//		}
//		String url = user.getUrl()+VIEW_ENERGY+user.getTeamId()+ Tools.getTimeStamp(true);
//		String page = PageService.getPageWithCookie(url, user);
//		Matcher m = fullEnergy.matcher(page);
//		if(m.find()){
//			if(m.group(1).equals(m.group(2))){
//				doSendEnergy(user);
//			}
//		}
	}
	
	private static boolean needSend(){
		Calendar c = Calendar.getInstance();
		int day = c.get(Calendar.DATE);
		if(day == 1 || day == 11 || day == 21){
			return true;
		}
		return false;
	}
	private static void doSendEnergy(User user){
		String url = user.getUrl()+SEND_ENERGY+Tools.getTimeStamp(true);
		PageService.getPageWithCookie(url, user);
	}
}
