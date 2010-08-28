package com.blue.tianjitang;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.blue.common.Portal;
import com.blue.common.User;
import com.blue.tools.PageService;
import com.blue.tools.Tools;

public class TianJiTang {
	private Logger logger = Logger.getLogger(this.getClass());
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
	
	public static Pattern jiXian = Pattern.compile("������ .*?(��Ѫ|��Ϣ|������|������|�ƻ���|������).*?������.*?(\\d+).*?missionInfo\\((\\d+)\\).*?(������|��������|�����)",Pattern.DOTALL); 
	public static Pattern renWu = Pattern.compile("��������ճ����� <span class=\"highlight\">(\\d+)</span> ������</td>\\s*"+
			"<td align=\"right\" style=\"line-height: 20px;\">\\s*"+
			"<a onclick=\"missionInfo\\((\\d+)\\)\" href=\"javascript:void\\(0\\);\">��������</a>");
	public static Pattern jingJi = Pattern.compile("�����ھ��������� <span class=\"highlight\">(\\d+)</span> ����ս</td>\\s*" +
			"<td align=\"right\" style=\"line-height: 20px;\">\\s*" +
			"<a onclick=\"missionInfo\\((\\d+)\\)\" href=\"javascript:void\\(0\\);\">��������");
	public static Pattern doing = Pattern.compile("<a onclick=\"missionInfo\\((\\d+)\\)\" href=\"javascript:void\\(0\\);\" class=\"highlight\">������</a>",Pattern.DOTALL);
	public static Pattern dailyWeal = Pattern.compile("�ڸ���������ȡÿ�ո���</td>\\s*" +
			"<td align=\"right\" style=\"line-height: 20px;\">\\s*" +
			"<a onclick=\"missionInfo\\((\\d+)\\)\" href=\"javascript:void\\(0\\);\">��������");
	public static Pattern liuYan = Pattern.compile("������������԰巢�� <span class=\"highlight\">(\\d+)</span> ��</td>\\s*" +
			"<td align=\"right\" style=\"line-height: 20px;\">\\s*" +
			"<a onclick=\"missionInfo\\((\\d+)\\)\" href=\"javascript:void\\(0\\);\">��������");
	public static Pattern tiHuGuan = Pattern.compile("missionInfo\\((\\d+)\\).*?����\\s*" +
			"</td>\\s*<td align=\"right\" style=\"line-height: 20px;\">\\s*<a onclick=\"missionInfo\\((\\d+)\\)\" href=\"javascript:void\\(0\\);\">��������");
	
	public static Pattern build = Pattern.compile("��������:<span class=\"highlight\">(\\d+)</span> &nbsp;&nbsp;���׻���");
	public void autoTask(User user){
		Portal.setUserAttribute(user);
		
		String url = user.getUrl()+TASK_LIST+Tools.getTimeStamp(true);
		String page = PageService.getPageWithCookie(url, user);
		Matcher m = jiXian.matcher(page);
		while(m.find()){
			String s = m.group(1);
			if(user.getAttribMap().get(s) >= Integer.parseInt(m.group(2))){
				if(acceptTask(user, m.group(3))){
					logger.info(user.getRoleName()+"���������ͻ�Ƽ�������:������"+s+"������"+m.group(2));
				}
			}
		}
		m = renWu.matcher(page);
		while(m.find()){
			if(acceptTask(user, m.group(2))){
				logger.info(user.getRoleName()+"����������ճ�����"+m.group(1)+"������");
			}
		}
		m = jingJi.matcher(page);
		while(m.find()){
			if(acceptTask(user, m.group(2))){
				logger.info(user.getRoleName()+"��������þ�������"+m.group(1)+"������");
			}
		}
		m = dailyWeal.matcher(page);
		while(m.find()){
			if(acceptTask(user, m.group(1))){
				logger.info(user.getRoleName()+"��������������ڸ���������ȡÿ�ո���");
			}
		}
		m = liuYan.matcher(page);
		while(m.find()){
			if(acceptTask(user, m.group(1))){
				logger.info(user.getRoleName()+"������������");
				liuYan(user);
			}
		}
		m = tiHuGuan.matcher(page);
		while(m.find()){
			if(acceptTask(user, m.group(1))){
				logger.info(user.getRoleName()+"����������߻�������");
			}
		}
		m = build.matcher(page);
		if(m.find()){
			user.setBuildPoint(Integer.parseInt(m.group(1)));
		}
	}
	private boolean acceptTask(User user,String id){
		String url = user.getUrl()+ACCEPT_WORK+id+Tools.getTimeStamp(true);
		String page = PageService.getPageWithCookie(url, user);
		return page != null && page.trim().length() == 0;
	}
	public void autoFinish(User user){
		String url = user.getUrl()+TASK_LIST+Tools.getTimeStamp(true);
		String page = PageService.getPageWithCookie(url, user);
		Matcher m = doing.matcher(page);
		while(m.find()){
			String finish = user.getUrl()+FINISH_TASK+m.group(1)+Tools.getTimeStamp(true);
			page = PageService.getPageWithCookie(finish, user);
			if(Tools.success(page)){
				logger.info(user.getRoleName()+"�������������"+m.group(1)+"�ɹ�");
			}
		}
	}
	private void liuYan(User user){
		String url = user.getUrl()+LIU_YAN+Tools.getTimeStamp(true);
		String data = "team_foster_message=%E4%BB%BB%E5%8A%A1&callback_func_name=refreshMissoin";
		String page = PageService.postPage(url, data, user);
		if(Tools.success(page)){
			logger.info(user.getRoleName()+"�Զ��������");
		}
	}
	public void build(User user){		
		if(user.getTianJiDoor() == 0){
//			logger.info(user.getRoleName()+"���޽������");
			return;
		}
		String url = user.getUrl()+BUILD+"&bui_id=1&submit="+user.getTianJiDoor()+"&build_inter="+user.getBuildPoint()+Tools.getTimeStamp(true);
		String data = "callback_func_name=ajaxCallback";
		String page = PageService.postPage(url, data, user);
		if(Tools.success(page)){
			logger.info(user.getRoleName()+"�޽������"+user.getTianJiDoor()+"�ɹ�");
		}
	}
}
