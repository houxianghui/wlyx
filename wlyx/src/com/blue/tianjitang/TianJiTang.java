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
	
	public static Pattern build = Pattern.compile("建筑积分:<span class=\"highlight\">(\\d+)</span> &nbsp;&nbsp;贡献积分");
	private static String[] speak = {"大爷，美国怎么走啊？问村长去",
						"你无耻的样子很有我当年的神韵",
						"外面下小雨，屋里下中雨，外面下中雨，屋里下大雨，实在雨太大了，全家人就上院子里避避雨",
						".这事儿离现在不远，家里有老人的可以回去问问……在春秋战国时期啊...... ",
						"我和超人唯一的区别就是我把内裤穿里边了",
						"您大点声不费电！",
						"待会儿散场都别走，吃饭去——谁去谁掏钱",
						"从今儿起，我吃龙虾再也不就饼了",
						"这天我跟偏见来在了火车站，车票都卖没了，偏见到一警察面前问：“你知道票贩子在哪儿吗？”警察一听乐了：“我还找呐！”",
						"你见过哪个黑社会的还纹着蜡笔小新呢？ ",
						"有困难要帮，没有困难制造困难也要帮",
						"谁要不认识您，谁就没吃过猪肉",
						"我是黑社会的，你们可怜可怜我吧",
						"哪里跌倒就在哪里躺下",
						"我昨晚梦到你了：我们漫步在小河边，相互依偎着。你抬头凝视着我的眼睛，深情地吐出三个字……汪汪汪",
						"读出下面的字，你将获得月薪2000000的工作，试题如下：簟璁醭歙艽绱癀穑魍旃偬彘硪钚鲥硐。",
						"一只猪和一只企鹅被关在-20℃的冷库里,第二天企鹅死了,猪没事。为什么？你不知道？对了，猪也不知道！",
						"请大声朗读:：卧梅又闻花，卧枝伤恨低。邀闻卧石碎，卧湿达春绿。",
						"打破老婆终身制，实行小姨股份制。引入小姐竞争制，推广情人合同制。",
						"执子之手，方知子丑，泪流满面，子不走我走。",
						"一觉醒来，天都黑了",
						"人又不聪明，还学别人秃顶",
						"曾经和朋友一起仰望星空，随之我们内牛满面，他是因为失恋，我则是因为扭伤了脖子",
						"我是白领：今天领了薪水，交了房租水电，买了油米泡面，摸了口袋，感叹一声，这个月工资又白领了… ",
						"琴棋书画不会，洗衣做饭嫌累。",
						"我们的目标：向钱看，向厚赚。 ",
						"你有什么不开心的事？说出来让大家开心一下。 ",
						"人不犯我，我不犯人；人若犯我，礼让三分；人再犯我，我还一针；人还犯我，斩草除根"};
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
				logger.info(user.getRoleName()+"交付天机堂任务"+m.group(1)+"成功");
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
}
