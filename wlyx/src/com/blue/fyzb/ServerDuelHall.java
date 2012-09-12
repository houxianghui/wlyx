package com.blue.fyzb;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.blue.common.User;
import com.blue.enums.Profession;
import com.blue.tools.PageService;
import com.blue.tools.Tools;

public class ServerDuelHall {
	private static Logger logger = Logger.getLogger(ServerDuelHall.class);
	/**
	 * <a href="javascript:void(0);" onclick="fnServerDuelRoleFight( 7250 );">������ս</a>
																					</td>
					</tr>
										<tr>
						<td width="32%" align="left"><a title="ǹ��-�Ʒ�11��" href="javascript:void(0);">ǹ��-��...</a></td>
						<td width="17%" align="left">����ϵ</td>
						<td width="18%" align="left">�䰲��</td>
						<td width="15%" align="left" class="small_font">Lv.102</td>

	 */
	private static Pattern p = Pattern.compile("fnServerDuelRoleFight\\( (\\d+) \\);\">������ս.*?void\\(0\\);\">(.*?)</a>.*?left\">(.*?ϵ).*?Lv.(\\d+)",Pattern.DOTALL);
	public static List<Challenged> getChallenged(User user){
		String url = user.getUrl()+"modules/server_duel_hall.php?"+Tools.getTimeStamp(false);
		String page = PageService.getPageWithCookie(url, user);
		Matcher m = p.matcher(page);
		List<Challenged> l = new ArrayList<Challenged>();
		while(m.find()){
			Challenged c = new Challenged();
			c.setId(m.group(1));
			c.setUserName(m.group(2));
			c.setProfession(Profession.valueOf(m.group(3)));
			c.setLevel(Integer.parseInt(m.group(4)));
			l.add(c);
		}
		return l;
	}
	public static void challenge(User user){
		if(!user.isNeedServerDuelHall()){
			return;
		}
		List<Challenged> l = getChallenged(user);
		if(l.size() == 0){
			return;
		}
		Challenged c = l.get(0);
		String url = "modules/server_duel_fight.php?action=fight&rid="+c.getId()+Tools.getTimeStamp(true);
		PageService.getPageWithCookie(url, user);
		logger.info(user.getRoleName()+"�����ս"+c.getUserName());
	}
}
