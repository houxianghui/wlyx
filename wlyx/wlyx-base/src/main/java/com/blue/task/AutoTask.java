package com.blue.task;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.blue.beauty.Beauty;
import com.blue.common.User;
import com.blue.tools.PageService;
import com.blue.tools.Tools;

public class AutoTask {
	private static Logger logger = Logger.getLogger(AutoTask.class);
	private static Pattern p = Pattern.compile("mission_auto_complete.*?'day',\\s*'(\\d+)',\\s*'(\\d+)'.*?�Զ����");
	private static Pattern tasks = Pattern.compile("<tr>.*?</tr>",Pattern.DOTALL);
	//mission_auto_complete ( 'day', '41001', '900' )
	private static Pattern finish = Pattern.compile("mission_auto_complete.*?,\\s*'(\\d+)',\\s*'(\\d+)'.*?������",Pattern.DOTALL);
	//<li>���飺<span class="highlight">+13546</span></span></li>
	private static Pattern reward = Pattern.compile("<li.*?(����|ͭ��|��Ʒ).*?(\\d+)",Pattern.DOTALL);
	private static Pattern finished = Pattern.compile("<a href=\"javascript:void\\(0\\);\" onclick=\"view_mission \\( 'day', (\\d+), true \\)\" class=\"purple\">��ȡ����",Pattern.DOTALL);
	private static Pattern p2 = Pattern.compile("�������");
	private static Pattern free = Pattern.compile("����������.*?complete_auto_mission.*?(\\d+?),");
	private static Pattern outTask = Pattern.compile("view_mission \\( 'day', (\\d+), true \\)");
	private static Pattern hasDoing = Pattern.compile("����.*? ���");
	private static Pattern getRewardOut = Pattern.compile("view_mission.*?(\\d+).*?��ȡ����");
	private static Pattern taskCount = Pattern.compile("�����ѽ�������������<span class=\"highlight\">(\\d+) / 20");
	//"quality":"3"
	private static Pattern dialog = Pattern.compile("mission_id.*?quality\":\"(\\d+)\",");
	
	public static final String TASK_LIST_URL = "modules/task.php";
	public static final String TASK_DETAIL_URL = "modules/role_mission.php?act=detail&function=day&id=";
	public static final String ACCEPT_TASK_URL = "modules/role_mission.php?act=detail&op=accept&function=day&id=";
	public static final String AUTO_TASK_URL = "modules/role_mission.php?act=detail&op=auto_complete&function=day&id=";
	public static final String REWARD_URL="modules/role_mission.php?act=rewards&function=day&id=";
	public static final String FREE_FINISH="modules/role_mission.php?act=detail&op=complete_auto_mission&function=day&isfree=1&callback_func_name=mission_common_callback&id=";
	//http://s4.verycd.9wee.com/modules/role_mission.php?timeStamp=1279894625156&act=list&state=1&callback_func_name=ajaxCallback&callback_obj_name=field_mission_box
	public static final String TASK_LIST_OUT = "modules/role_mission.php?act=list&state=1&callback_func_name=ajaxCallback&callback_obj_name=field_mission_box";
	
	public static void autoAcceptTask(User user){
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
				logger.info(user.getRoleName()+"���� "+m.group(1)+"�ɹ�");
			}
		}	
	}
	private static String getTaskListUrl(User user){
		return user.getUrl()+TASK_LIST_URL+Tools.getTimeStamp(false);
	}
	private static boolean freeFinish(User user){
		String url = getTaskListUrl(user);
		String page = PageService.getPageWithCookie(url, user);
		Matcher m = free.matcher(page);
		if(m.find()){
			String freeUrl = user.getUrl()+FREE_FINISH+m.group(1)+Tools.getTimeStamp(true);
			String s = PageService.getPageWithCookie(freeUrl, user);
			if(Tools.success(s)){
				logger.info(user.getRoleName()+"ʹ������Զ��������");
				return true;
			}
		}
		return false;
	}
	private static boolean getTask(User user,String taskId){
		String url = user.getUrl()+TASK_DETAIL_URL+taskId+Tools.getTimeStamp(true);
		String page = PageService.getPageWithCookie(url, user);
		Matcher m = reward.matcher(page);
		if(m.find()){
			if(m.group(1).equals("ͭ��")){
				if(Integer.parseInt(m.group(2))>= user.getMiniMoney()){
					logger.info(user.getRoleName()+"����ͭ��Ϊ"+m.group(2)+"���ռ�����"+taskId);
					return true;
				}
			}
			if(m.group(1).equals("����")){
				if(Integer.parseInt(m.group(2))>=user.getMiniJingYan()){
					logger.info(user.getRoleName()+"����ս��������"+taskId);
					return true;
				}
			}
			if(m.group(1).equals("��Ʒ")){
				Matcher t = dialog.matcher(page);
				if(t.find()){
					int lv = Integer.parseInt(t.group(1));
					if(lv >= user.getDialog()){
						logger.info(user.getRoleName()+"���ܶԻ�����"+taskId);
						return true;
					}
				}
			}
		}
		return false;
	}
	public static void autoFinishTask(User user){
		String url = getTaskListUrl(user);
		String page = PageService.getPageWithCookie(url, user);
		Matcher m = tasks.matcher(page);
		while(m.find()){
			String s = m.group();
			Matcher m2 = finish.matcher(s);
			if(m2.find()){
				url = user.getUrl()+AUTO_TASK_URL+m2.group(1)+Tools.getTimeStamp(true);
				String result = PageService.getPageWithCookie(url, user);			
				if(Tools.success(result)){
					logger.info(user.getRoleName()+"ί������"+m2.group(1)+"�ɹ� ");
				}
			}
			
		}
		
		if(freeFinish(user)){
			autoFinishTask(user);
		}
		outAutoFinish(user);
	}
	private static void outAutoFinish(User user){
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
				logger.info(user.getRoleName()+"ί������ "+m2.group(1)+"�ɹ� ");
			}
		}
	}
	private static boolean acceptTask(User user,String taskId){
		if(getTask(user, taskId)){
			String url = user.getUrl()+ACCEPT_TASK_URL+taskId+Tools.getTimeStamp(true);
			String page = PageService.getPageWithCookie(url, user);
			return Tools.success(page);
		}
		return false;
		
	}
	public static boolean getReward(User user){
		String url = getTaskListUrl(user);
		String page = PageService.getPageWithCookie(url, user);
		Matcher m = finished.matcher(page);
		String reward = user.getUrl()+REWARD_URL;
		if(m.find()){
			String taskId = m.group(1);
			String p = PageService.getPageWithCookie(reward+taskId+Tools.getTimeStamp(true), user);
			logger.info(user.getRoleName()+"��ȡ������ "+taskId);
			return Tools.success(p);
		}
		return getRewardOut(user);
	}
	private static boolean getRewardOut(User user){
		String url = user.getUrl()+TASK_LIST_OUT+Tools.getTimeStamp(true);
		String page = PageService.getPageWithCookie(url, user);
		Matcher m = getRewardOut.matcher(page);
		if(m.find()){
			url = user.getUrl()+REWARD_URL+m.group(1)+Tools.getTimeStamp(true);
			page = PageService.getPageWithCookie(url, user);
			logger.info(user.getRoleName()+"��ȡ������ "+m.group(1));
		}
		return Tools.success(page);
	}
}
