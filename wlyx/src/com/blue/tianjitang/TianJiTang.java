package com.blue.tianjitang;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.blue.common.Portal;
import com.blue.common.User;
import com.blue.tools.PageService;
import com.blue.tools.Tools;

public class TianJiTang {
	private static Logger logger = Logger.getLogger(TianJiTang.class);
	//http://s4.verycd.9wee.com/modules/team_foster.php?act=mission&timeStamp=1281755338821&callback_func_name=ajaxCallback&callback_obj_name=team_fostor_mission
	public static final String TASK_LIST = "modules/team_foster.php?act=mission&callback_func_name=ajaxCallback&callback_obj_name=team_fostor_mission";
	//http://s4.verycd.9wee.com/modules/team_foster.php?act=mission&action=accept&mission_id=161&timeStamp=1281757220636&callback_func_name=refreshMissoin
	public static final String ACCEPT_WORK ="modules/team_foster.php?act=mission&action=accept&callback_func_name=refreshMissoin&mission_id=";
	//http://s4.verycd.9wee.com/modules/team_foster.php?act=mission&action=finish&mission_id=1&timeStamp=1281760874601&callback_func_name=ajaxCallback
	public static final String FINISH_TASK = "modules/team_foster.php?act=mission&action=finish&callback_func_name=ajaxCallback&mission_id=";
	//http://s4.verycd.9wee.com/modules/team_foster.php?act=mission&action=message&timeStamp=1281762976028
	public static final String LIU_YAN = "modules/team_foster.php?act=mission&action=message";
	//http://s4.verycd.9wee.com/modules/team_foster.php?act=build&action=building&bui_id=1&submit=1&build_inter=1&timeStamp=1281775419408
	public static final String BUILD = "modules/team_foster.php?act=build&action=building";
	
	public static Pattern jiXian = Pattern.compile("将自身 .*?(气血|内息|命中率|暴击率|破击率|躲闪率).*?提升到.*?(\\d+).*?missionInfo\\((\\d+)\\).*?(进行中|接受任务|已完成)",Pattern.DOTALL); 
	public static Pattern renWu = Pattern.compile("当天完成日常任务 <span class=\"highlight\">(\\d+)</span> 个以上</td>\\s*"+
			"<td align=\"right\" style=\"line-height: 20px;\">\\s*"+
			"<a onclick=\"missionInfo\\((\\d+)\\)\" href=\"javascript:void\\(0\\);\">接受任务</a>");
	public static Pattern jingJi = Pattern.compile("当天在竞技场发起 <span class=\"highlight\">(\\d+)</span> 次挑战</td>\\s*" +
			"<td align=\"right\" style=\"line-height: 20px;\">\\s*" +
			"<a onclick=\"missionInfo\\((\\d+)\\)\" href=\"javascript:void\\(0\\);\">接受任务");
	public static Pattern doing = Pattern.compile("<a onclick=\"missionInfo\\((\\d+)\\)\" href=\"javascript:void\\(0\\);\" class=\"highlight\">进行中</a>",Pattern.DOTALL);
	public static Pattern dailyWeal = Pattern.compile("在福利中心领取每日福利</td>\\s*" +
			"<td align=\"right\" style=\"line-height: 20px;\">\\s*" +
			"<a onclick=\"missionInfo\\((\\d+)\\)\" href=\"javascript:void\\(0\\);\">接受任务");
	public static Pattern liuYan = Pattern.compile("当天在武馆留言板发言 <span class=\"highlight\">(\\d+)</span> 次</td>\\s*" +
			"<td align=\"right\" style=\"line-height: 20px;\">\\s*" +
			"<a onclick=\"missionInfo\\((\\d+)\\)\" href=\"javascript:void\\(0\\);\">接受任务");
	public static Pattern tiHuGuan = Pattern.compile("积分</td>\\s*" +
			"<td align=\"right\" style=\"line-height: 20px;\">\\s*" +
			"<a onclick=\"missionInfo\\((\\d+)\\)\" href=\"javascript:void\\(0\\);\">接受任务");
	public static Pattern build = Pattern.compile("建筑积分:<span class=\"highlight\">(\\S+?)</span> &nbsp;&nbsp;贡献积分");
	private static Pattern feed = Pattern.compile("team_farm_feed\\((\\d+), (\\d+), (\\d+), (\\d+), (\\d+), (\\d+), ''\\);\" href=\"javascript:void\\(0\\);\">我要浇水");
	private static java.util.List<String> speak;
	static{
		initSpeak();
	}
	private static void initSpeak(){
		speak = new ArrayList<String>();
		File f = new File("speak.txt");
		if(!f.exists()){
			speak.add("我很懒，一个字都不写");			
			return;
		}
		BufferedReader br = null;
		try{
			br = new BufferedReader(new FileReader(f));
			String s = null;
			while((s=br.readLine()) != null){
				speak.add(s);
			}
		}catch(Exception e){
			logger.error("武馆留言语句初始化失败",e);
		}finally{
			if(br != null)
				try{
				br.close();
				}catch(Exception e){
					logger.error("关闭说话文件失败",e);
				}
		}
	}
	
	public static void autoTask(User user){
		if(!user.isAutoTianJi()){
			return;
		}
		Portal.setUserAttribute(user);
		
		String url = user.getUrl()+TASK_LIST+Tools.getTimeStamp(true);
		String page = PageService.getPageWithCookie(url, user);
		Matcher m = jiXian.matcher(page);
		while(m.find()){
			String s = m.group(1);
			if(user.getAttribMap().get(s) >= Integer.parseInt(m.group(2))){
				if(acceptTask(user, m.group(3))){
					logger.info(user.getRoleName()+"接受天机堂突破极限任务:将自身"+s+"提升到"+m.group(2));
				}
			}
		}
		m = renWu.matcher(page);
		while(m.find()){
			if(acceptTask(user, m.group(2))){
				logger.info(user.getRoleName()+"接受天机堂日常任务"+m.group(1)+"个以上");
			}
		}
		m = jingJi.matcher(page);
		while(m.find()){
			if(acceptTask(user, m.group(2))){
				logger.info(user.getRoleName()+"接受天机堂竞技任务"+m.group(1)+"个以上");
			}
		}
		m = dailyWeal.matcher(page);
		while(m.find()){
			if(acceptTask(user, m.group(1))){
				logger.info(user.getRoleName()+"接受天机堂任务在福利中心领取每日福利");
			}
		}
		m = liuYan.matcher(page);
		while(m.find()){
			if(acceptTask(user, m.group(1))){
				logger.info(user.getRoleName()+"接受留言任务");
				liuYan(user);
			}
		}
		m = tiHuGuan.matcher(page);
		while(m.find()){
			if(acceptTask(user, m.group(1))){
				logger.info(user.getRoleName()+"接受天机堂踢护馆任务");
			}
		}
		m = build.matcher(page);
		if(m.find()){
			java.text.DecimalFormat df = new java.text.DecimalFormat("#0,000");
			int point = 0;
			try{
				point = df.parse(m.group(1)).intValue();
			}catch(Exception e){
				logger.error(user.getRoleName()+e);
			}
			user.setBuildPoint(point);
		}
	}
	private static boolean acceptTask(User user,String id){
		String url = user.getUrl()+ACCEPT_WORK+id+Tools.getTimeStamp(true);
		String page = PageService.getPageWithCookie(url, user);
		return page != null && page.trim().length() == 0;
	}
	public static void autoFinish(User user){
		String url = user.getUrl()+TASK_LIST+Tools.getTimeStamp(true);
		String page = PageService.getPageWithCookie(url, user);
		Matcher m = doing.matcher(page);
		while(m.find()){
			String finish = user.getUrl()+FINISH_TASK+m.group(1)+Tools.getTimeStamp(true);
			page = PageService.getPageWithCookie(finish, user);
			if(Tools.success(page)){
				logger.info(user.getRoleName()+"交付天机堂任务"+m.group(1)+"成功");
			}
		}
	}
	public static boolean liuYan(User user){
		String url = user.getUrl()+LIU_YAN+Tools.getTimeStamp(true);
		Random r = new Random();
		int t = r.nextInt(speak.size());
		String data = "team_foster_message="+speak.get(t)+"&callback_func_name=refreshMissoin";
		String page = null;
		page = PageService.postPage(url, data, user);
		
		if(Tools.success(page)){
			logger.info(user.getRoleName()+"自动武馆留言");
			return true;
		}
		return false;
	}
	public static void build(User user){		
		if(user.getTianJiDoor() == 0){
//			logger.info(user.getRoleName()+"不修建天机阁");
			return;
		}
		//http://s4.verycd.9wee.com/modules/team_foster.php?act=build&action=building&bui_id=3&submit=1&build_inter=1&timeStamp=1283164009827
		String url = user.getUrl()+BUILD+"&bui_id="+user.getTianJiDoor()+"&submit=1&build_inter="+user.getBuildPoint()+Tools.getTimeStamp(true);
		String data = "callback_func_name=ajaxCallback";
		String page = PageService.postPage(url, data, user);
		if(Tools.success(page)){
			logger.info(user.getRoleName()+"修建天机阁"+user.getTianJiDoor()+"成功");
		}
	}
	public static void plant(User user){
		//http://s4.verycd.9wee.com/modules/team_foster.php?act=build&action=enter&bui_id=5&timeStamp=1331096889939&callback_func_name=ajaxCallback&callback_obj_name=team_foster_build5
		//http://s4.verycd.9wee.com/modules/team_foster.php?act=build&action=farmaction&farm_id=121&team_id=58&creature_type=2&bui_id=5&page=1&timeStamp=1331097341676&callback_func_name=callbackTeamfarm
		String url = user.getUrl()+"modules/team_foster.php?act=build&action=enter&bui_id=5"+Tools.getTimeStamp(true);
		
		String page = PageService.getPageWithCookie(url, user);
		Matcher m = feed.matcher(page);
		while(m.find()){
			StringBuffer sb = new StringBuffer(user.getUrl());
			sb.append("modules/team_foster.php?act=build&action=farmaction&farm_id=");
			sb.append(m.group(1));
			sb.append("&team_id=");
			sb.append(m.group(2));
			sb.append("&creature_type=");
			sb.append(m.group(3));
			sb.append("&bui_id=");
			sb.append(m.group(4));
			sb.append("&page=");
			sb.append(m.group(5));
			PageService.getPageWithCookie(sb.toString(), user);
		}
	}
}
