package com.blue.task;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.blue.beauty.Beauty;
import com.blue.common.User;
import com.blue.tools.PageService;
import com.blue.tools.Tools;

public class AutoTask {
	private Logger logger = Logger.getLogger(this.getClass());
	private Pattern p = Pattern.compile("mission_auto_complete \\( 'day', '(\\d+)', '(\\d+)' \\)\">自动完成");
	private Pattern finish = Pattern.compile("mission_auto_complete \\( 'day', '(\\d+)', '(\\d+)' \\)\">自动完成.*?进行中");
	private Pattern reward = Pattern.compile("<li>(经验|铜币|物品).*?(\\d+)");
	private Pattern finished = Pattern.compile("<a href=\"javascript:void\\(0\\);\" onclick=\"view_mission \\( 'day', (\\d+), true \\)\" class=\"purple\">领取奖励",Pattern.DOTALL);
	private Pattern p2 = Pattern.compile("立即完成");
	private Pattern free = Pattern.compile("免费完成任务.*?complete_auto_mission.*?(\\d+?),");
	private Pattern outTask = Pattern.compile("view_mission \\( 'day', (\\d+), true \\)");
	private Pattern hasDoing = Pattern.compile("将于.*? 完成");
	private Pattern getRewardOut = Pattern.compile("view_mission.*?(\\d+).*?领取奖励");
	private Pattern taskCount = Pattern.compile("今日已接受任务数量：<span class=\"highlight\">(\\d+) / 20");
	//"quality":"3"
	private Pattern dialog = Pattern.compile("quality\":\"(\\d+)\",");
	
	public static final String TASK_LIST_URL = "modules/task.php";
	public static final String TASK_DETAIL_URL = "modules/role_mission.php?act=detail&function=day&id=";
	public static final String ACCEPT_TASK_URL = "modules/role_mission.php?act=detail&op=accept&function=day&id=";
	public static final String AUTO_TASK_URL = "modules/role_mission.php?act=detail&op=auto_complete&function=day&id=";
	public static final String REWARD_URL="modules/role_mission.php?act=rewards&function=day&id=";
	public static final String FREE_FINISH="modules/role_mission.php?act=detail&op=complete_auto_mission&function=day&isfree=1&callback_func_name=mission_common_callback&id=";
	//http://s4.verycd.9wee.com/modules/role_mission.php?timeStamp=1279894625156&act=list&state=1&callback_func_name=ajaxCallback&callback_obj_name=field_mission_box
	public static final String TASK_LIST_OUT = "modules/role_mission.php?act=list&state=1&callback_func_name=ajaxCallback&callback_obj_name=field_mission_box";
	
	public void autoAcceptTask(User user){
		String url = getTaskListUrl(user);
		String page = PageService.getPageWithCookie(url, user);
		Matcher count = taskCount.matcher(page);
		if(count.find()){
			int i = Integer.parseInt(count.group(1));
			if(i > 4){
				Beauty.dailyTask(user);
			}
		}
		Matcher m = p.matcher(page);		
		while(m.find()){
			if(acceptTask(user, m.group(1))){
				logger.info(user.getRoleName()+"接受 "+m.group(1)+"成功");
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
				logger.info(user.getRoleName()+"使用免费自动完成任务");
				return true;
			}
		}
		return false;
	}
	private boolean getTask(User user,String taskId){
		String url = user.getUrl()+TASK_DETAIL_URL+taskId+Tools.getTimeStamp(true);
		String page = PageService.getPageWithCookie(url, user);
		Matcher m = reward.matcher(page);
		if(m.find()){
			if(m.group(1).equals("铜币")){
				if(Integer.parseInt(m.group(2))> user.getMiniMoney()){
					return true;
				}
			}
			if(m.group(1).equals("经验")){
				return true;
			}
			if(m.group(1).equals("物品")){
				Matcher t = dialog.matcher(page);
				if(t.find()){
					int lv = Integer.parseInt(t.group(1));
					if(lv >= 3){
						return true;
					}
				}
			}
		}
		return false;
	}
	public void autoFinishTask(User user){
		String url = getTaskListUrl(user);
		String page = PageService.getPageWithCookie(url, user);
		Matcher m = p2.matcher(page);
		if(m.find()){
		}else{
			Matcher m2 = finish.matcher(page);
			if(m2.find()){
				url = user.getUrl()+AUTO_TASK_URL+m2.group(1)+Tools.getTimeStamp(true);
				page = PageService.getPageWithCookie(url, user);			
				if(Tools.success(page)){
					logger.info(user.getRoleName()+"委托任务"+m2.group(1)+"成功 ");
				}
			}
		}
		if(freeFinish(user)){
			autoFinishTask(user);
		}
		outAutoFinish(user);
	}
	private void outAutoFinish(User user){
		String url = user.getUrl()+TASK_LIST_OUT+Tools.getTimeStamp(true);
		String s = PageService.getPageWithCookie(url, user);
		Matcher m = hasDoing.matcher(s);
		if(m.find()){
			return;
		}
		Matcher m2 = outTask.matcher(s);
		if(m2.find()){
			url = user.getUrl()+AUTO_TASK_URL+m2.group(1)+Tools.getTimeStamp(true);
			String page = PageService.getPageWithCookie(url, user);			
			if(Tools.success(page)){
				logger.info(user.getRoleName()+"委托任务 "+m2.group(1)+"成功 ");
			}
		}
	}
	private boolean acceptTask(User user,String taskId){
		if(getTask(user, taskId)){
			String url = user.getUrl()+ACCEPT_TASK_URL+taskId+Tools.getTimeStamp(true);
			String page = PageService.getPageWithCookie(url, user);
			return Tools.success(page);
		}
		return false;
		
	}
	public boolean getReward(User user){
		String url = getTaskListUrl(user);
		String page = PageService.getPageWithCookie(url, user);
		Matcher m = finished.matcher(page);
		String reward = user.getUrl()+REWARD_URL;
		if(m.find()){
			String taskId = m.group(1);
			String p = PageService.getPageWithCookie(reward+taskId+Tools.getTimeStamp(true), user);
			logger.info(user.getRoleName()+"领取任务奖励 "+taskId);
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
			logger.info(user.getRoleName()+"领取任务奖励 "+m.group(1));
		}
		return Tools.success(page);
	}
}
