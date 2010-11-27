package com.blue.tianjitang;

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
	public static Pattern tiHuGuan = Pattern.compile("����</td>\\s*" +
			"<td align=\"right\" style=\"line-height: 20px;\">\\s*" +
			"<a onclick=\"missionInfo\\((\\d+)\\)\" href=\"javascript:void\\(0\\);\">��������");
	
	public static Pattern build = Pattern.compile("��������:<span class=\"highlight\">(\\d+)</span> &nbsp;&nbsp;���׻���");
	private static String[] speak = {"��ү��������ô�߰����ʴ峤ȥ",
						"���޳ܵ����Ӻ����ҵ��������",
						"������С�꣬���������꣬���������꣬�����´��꣬ʵ����̫���ˣ�ȫ���˾���Ժ����ܱ���",
						".���¶������ڲ�Զ�����������˵Ŀ��Ի�ȥ���ʡ����ڴ���ս��ʱ�ڰ�...... ",
						"�Һͳ���Ψһ����������Ұ��ڿ㴩�����",
						"����������ѵ磡",
						"�����ɢ�������ߣ��Է�ȥ����˭ȥ˭��Ǯ",
						"�ӽ�����ҳ���Ϻ��Ҳ���ͱ���",
						"�����Ҹ�ƫ�������˻�վ����Ʊ����û�ˣ�ƫ����һ������ǰ�ʣ�����֪��Ʊ�������Ķ��𣿡�����һ�����ˣ����һ����ţ���",
						"������ĸ������Ļ���������С���أ� ",
						"������Ҫ�û��������������ҲҪ��",
						"˭Ҫ����ʶ����˭��û�Թ�����",
						"���Ǻ����ģ����ǿ��������Ұ�",
						"�������������������",
						"�������ε����ˣ�����������С�ӱߣ��໥�����š���̧ͷ�������ҵ��۾���������³������֡���������",
						"����������֣��㽫�����н2000000�Ĺ������������£�������ܴ�����������������ϡ�",
						"һֻ���һֻ��챻����-20��������,�ڶ����������,��û�¡�Ϊʲô���㲻֪�������ˣ���Ҳ��֪����",
						"������ʶ�:����÷���Ż�����֦�˺޵͡�������ʯ�飬��ʪ�ﴺ�̡�",
						"�������������ƣ�ʵ��С�̹ɷ��ơ�����С�㾺���ƣ��ƹ����˺�ͬ�ơ�",
						"ִ��֮�֣���֪�ӳ��������棬�Ӳ������ߡ�",
						"һ���������춼����",
						"���ֲ���������ѧ����ͺ��",
						"����������һ�������ǿգ���֮������ţ���棬������Ϊʧ������������ΪŤ���˲���",
						"���ǰ��죺��������нˮ�����˷���ˮ�磬�����������棬���˿ڴ�����̾һ��������¹����ְ����ˡ� ",
						"�����黭���ᣬϴ���������ۡ�",
						"���ǵ�Ŀ�꣺��Ǯ�������׬�� ",
						"����ʲô�����ĵ��£�˵�����ô�ҿ���һ�¡� ",
						"�˲����ң��Ҳ����ˣ��������ң��������֣����ٷ��ң��һ�һ�룻�˻����ң�ն�ݳ���"};
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
				logger.info(user.getRoleName()+"�������������"+m.group(1)+"�ɹ�");
			}
		}
	}
	public static boolean liuYan(User user){
		String url = user.getUrl()+LIU_YAN+Tools.getTimeStamp(true);
		Random r = new Random();
		int t = r.nextInt(speak.length-1);
		String data = "team_foster_message="+speak[t]+"&callback_func_name=refreshMissoin";
		String page = null;
		int count = 3;
		do{
			page = PageService.postPage(url, data, user);
		}while(!Tools.success(page) && count-- >= 0);
		
		if(Tools.success(page)){
			logger.info(user.getRoleName()+"�Զ��������");
			return true;
		}
		return false;
	}
	public static void build(User user){		
		if(user.getTianJiDoor() == 0){
//			logger.info(user.getRoleName()+"���޽������");
			return;
		}
		//http://s4.verycd.9wee.com/modules/team_foster.php?act=build&action=building&bui_id=3&submit=1&build_inter=1&timeStamp=1283164009827
		
		String url = user.getUrl()+BUILD+"&bui_id="+user.getTianJiDoor()+"&submit=1&build_inter="+user.getBuildPoint()+Tools.getTimeStamp(true);
		String data = "callback_func_name=ajaxCallback";
		String page = PageService.postPage(url, data, user);
		if(Tools.success(page)){
			logger.info(user.getRoleName()+"�޽������"+user.getTianJiDoor()+"�ɹ�");
		}
	}
}
