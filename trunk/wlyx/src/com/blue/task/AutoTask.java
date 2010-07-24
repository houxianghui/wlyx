package com.blue.task;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.blue.common.User;
import com.blue.tools.PageService;
import com.blue.tools.Tools;

public class AutoTask {
	private Pattern p = Pattern.compile("mission_auto_complete \\( 'day', '(\\d+)', '(\\d+)' \\)\">自动完成");
	private Pattern reward = Pattern.compile("<li>[经验|铜币|物品].*?(\\d+)");
	private Pattern finished = Pattern.compile("<a href=\"javascript:void\\(0\\);\" onclick=\"view_mission \\( 'day', (\\d+), true \\)\" class=\"purple\">领取奖励",Pattern.DOTALL);
	private Pattern p2 = Pattern.compile("立即完成");
	private Pattern free = Pattern.compile("免费完成任务.*?complete_auto_mission.*?(\\d+?),");
	private Pattern outTask = Pattern.compile("view_mission \\( 'day', (\\d+), true \\)");
	private Pattern hasDoing = Pattern.compile("将于.*? 完成");
	private Pattern getRewardOut = Pattern.compile("view_mission.*?(\\d+).*?领取奖励");
	
	public static final String TASK_LIST_URL = "modules/task.php";
	public static final String TASK_DETAIL_URL = "modules/role_mission.php?act=detail&function=day&id=";
	public static final String ACCEPT_TASK_URL = "modules/role_mission.php?act=detail&op=accept&function=day&id=";
	public static final String AUTO_TASK_URL = "modules/role_mission.php?act=detail&op=auto_complete&function=day&id=";
	public static final String REWARD_URL="modules/role_mission.php?act=rewards&function=day&id=";
	public static final String FREE_FINISH="modules/role_mission.php?act=detail&op=complete_auto_mission&function=day&isfree=1&callback_func_name=mission_common_callback&id=";
	public static final String JING_YAN_TU = "";
	//http://s4.verycd.9wee.com/modules/role_mission.php?timeStamp=1279894625156&act=list&state=1&callback_func_name=ajaxCallback&callback_obj_name=field_mission_box
	public static final String TASK_LIST_OUT = "modules/role_mission.php?act=list&state=1&callback_func_name=ajaxCallback&callback_obj_name=field_mission_box";
	
	public void autoAcceptTask(User user){
		String url = getTaskListUrl(user);
		String page = PageService.getPageWithCookie(url, user);
		Matcher m = p.matcher(page);		
		while(m.find()){
			if(acceptTask(user, m.group(1))){
				System.out.println(user.getUserName()+"accept task "+m.group(1)+" success");
			}
		}	
	}
	private String getTaskListUrl(User user){
		return user.getUrl()+TASK_LIST_URL+Tools.getTimeStamp(false);
	}
	private boolean freeFinish(User user){
		String url = getTaskListUrl(user);
		String page = PageService.getPageWithCookie(url, user);
		Matcher m = free.matcher(page);
		if(m.find()){
			String freeUrl = user.getUrl()+FREE_FINISH+m.group(1)+Tools.getTimeStamp(true);
			String s = PageService.getPageWithCookie(freeUrl, user);
			if(Tools.success(s)){
				System.out.println(user.getUserName()+"使用免费自动完成");
				return true;
			}
		}
		return false;
	}
	private String getTaskInfo(User user,String taskId){
		String url = user.getUrl()+TASK_DETAIL_URL+taskId+Tools.getTimeStamp(true);
		String page = PageService.getPageWithCookie(url, user);
		Matcher m = reward.matcher(page);
		if(m.find()){
			return m.group(1);
		}
		return null;
	}
	public void autoFinishTask(User user){
		String url = getTaskListUrl(user);
		String page = PageService.getPageWithCookie(url, user);
		Matcher m = p2.matcher(page);
		if(m.find()){
			System.out.println("already has task doing");
		}else{
			Matcher m2 = p.matcher(page);
			if(m2.find()){
				url = user.getUrl()+AUTO_TASK_URL+m2.group(1)+Tools.getTimeStamp(true);
				page = PageService.getPageWithCookie(url, user);			
				if(Tools.success(page)){
					System.out.println(user.getUserName()+"proxy task "+m2.group(1)+" finished ");
				}
			}
		}
		freeFinish(user);
		outAutoFinish(user);
	}
	public void outAutoFinish(User user){
		String url = user.getUrl()+TASK_LIST_OUT+Tools.getTimeStamp(true);
		String s = PageService.getPageWithCookie(url, user);
		Matcher m = hasDoing.matcher(s);
		if(m.find()){
			System.out.println("already has task doing");
			return;
		}
		Matcher m2 = outTask.matcher(s);
		if(m2.find()){
			url = user.getUrl()+AUTO_TASK_URL+m2.group(1)+Tools.getTimeStamp(true);
			String page = PageService.getPageWithCookie(url, user);			
			if(Tools.success(page)){
				System.out.println(user.getUserName()+"proxy task "+m2.group(1)+" finished ");
			}
		}
	}
	private boolean acceptTask(User user,String taskId){
		String url = user.getUrl()+ACCEPT_TASK_URL+taskId+Tools.getTimeStamp(true);
		String page = PageService.getPageWithCookie(url, user);
		return Tools.success(page);
		
	}
	public boolean getReward(User user){
		String url = getTaskListUrl(user);
		String page = PageService.getPageWithCookie(url, user);
		Matcher m = finished.matcher(page);
		String reward = user.getUrl()+REWARD_URL;
		if(m.find()){
			String taskId = m.group(1);
			String p = PageService.getPageWithCookie(reward+taskId+Tools.getTimeStamp(true), user);
			System.out.println(user.getUserName()+"get reward "+taskId+"");
			return Tools.success(p);
		}
		return getRewardOut(user);
	}
	private boolean getRewardOut(User user){
		String url = user.getUrl()+TASK_LIST_OUT+Tools.getTimeStamp(true);
		String page = PageService.getPageWithCookie(url, user);
		Matcher m = getRewardOut.matcher(page);
		if(m.find()){
			url = user.getUrl()+REWARD_URL+m.group(1)+Tools.getTimeStamp(true);
			page = PageService.getPageWithCookie(url, user);
		}
		return Tools.success(page);
	}
}
