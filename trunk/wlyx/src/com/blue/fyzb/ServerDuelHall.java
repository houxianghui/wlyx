package com.blue.fyzb;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.blue.common.User;
import com.blue.enums.Profession;
import com.blue.tools.PageService;
import com.blue.tools.SkillUtil;
import com.blue.tools.Tools;
import com.start.GetAllHuanJing;

public class ServerDuelHall {
	private static Logger logger = Logger.getLogger(ServerDuelHall.class);
	/**
	 *	<tr>
						<td width="32%" align="left"><a title="��ʿ-������5��" href="javascript:void(0);">��ʿ-��...</a></td>
						<td width="17%" align="left">����ϵ</td>
						<td width="18%" align="left">�䰲��</td>
						<td width="15%" align="left" class="small_font">Lv.108</td>
						<td width="18%" align="center">
																								<a href="javascript:void(0);" onclick="fnServerDuelRoleFight( 1307, 10 );">������ս</a>
																					</td>
					</tr>

	 */
	private static Pattern external = Pattern.compile("<tr>.*?</tr>",Pattern.DOTALL);
	private static Pattern inner = Pattern.compile(".*?void\\(0\\);\">(.*?)</a>.*?left\">\\s*(.*?ϵ).*?Lv.(\\d+).*?fnServerDuelRoleFight\\( (\\d+).*?������ս</a>",Pattern.DOTALL);
	private static Pattern times = Pattern.compile("�����ѷ��� <span class=\"highlight\">(\\d+) / (\\d+)</span>");
	private static Pattern msg = Pattern.compile("message\":\"(.*?)<");
	public static List<Challenged> getChallenged(User user){
		String url = user.getUrl()+"modules/server_duel_hall.php?"+Tools.getTimeStamp(false);
		String page = PageService.getPageWithCookie(url, user);
		Matcher time = times.matcher(page);
		if(time.find()){
			if(time.group(1).equals(time.group(2))){
				return null;
			}
		}
		Matcher exMatcher = external.matcher(page);
		List<Challenged> l = new ArrayList<Challenged>();
		while(exMatcher.find()){
			String s = exMatcher.group();
			Matcher m = inner.matcher(s);
			if(m.find()){
				Challenged c = new Challenged();
				c.setId(m.group(4));
				c.setUserName(m.group(1));
				String name = m.group(2);
				if(Tools.contains(name, "��ɱ")){
					c.setProfession(Profession.��ɱϵ);
				}else if(Tools.contains(name, "�ƻ�")){
					c.setProfession(Profession.�ƻ�ϵ);
				}else if(Tools.contains(name, "��")){
					c.setProfession(Profession.����ϵ);
				}else if(Tools.contains(name, "��")){
					c.setProfession(Profession.����ϵ);
				}else if(Tools.contains(name, "����")){
					c.setProfession(Profession.����ϵ);
				}else if(Tools.contains(name, "����")){
					c.setProfession(Profession.����ϵ);
				}
				c.setLevel(Integer.parseInt(m.group(3)));
				l.add(c);
			}
		}
		return l;
	}
	public static void challenge(User user){
		getReward(user);
		if(!user.isNeedServerDuelHall()){
			return;
		}
		List<Challenged> l = getChallenged(user);
		if(l == null || l.size() == 0){
			return;
		}
		Random r = new Random();
		Challenged c = l.get(r.nextInt(l.size()));
		SkillUtil.equipSkill(user, c.getProfession());
		String url = user.getUrl()+"modules/server_duel_fight.php?action=fight&rid="+c.getId()+Tools.getTimeStamp(true);
		String page = PageService.getPageWithCookie(url, user);
		logger.info(user.getRoleName()+"�����ս"+c.getUserName());
	}
	
	private static void getReward(User user){
		int[] cut = {5,9,12,14,15};
		for(int i = 0;i<cut.length;i++){
			String url = user.getUrl()+"modules/server_duel_top.php?act=get&cnt="+cut[i]+Tools.getTimeStamp(true);
			String result = PageService.getPageWithCookie(url, user);
			Matcher m = msg.matcher(result);
			if(m.find()){
				logger.info(user.getRoleName()+Tools.hexToString((m.group(1)+m.group(2)+m.group(3))));
			}
		}
	}
}
