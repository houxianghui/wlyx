package com.blue.common;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.blue.tools.PageService;
import com.blue.tools.Tools;
import com.blue.warrior.Warrior;

public class Portal {
	private static Logger logger = Logger.getLogger(Portal.class);
	//http://s4.verycd.9wee.com/modules/role_info.php?timeStamp=1279701586234&callback_func_name=callback_load_content&callback_obj_name=content
	public static final String USER_INFO="modules/role_info.php?callback_func_name=callback_load_content&callback_obj_name=content";
	//http://s4.verycd.9wee.com/modules/scenes_role.php?sid=0&timeStamp=1279868148062&callback_func_name=switch_scene_callback
	public static final String GO_HOME = "modules/scenes_role.php?sid=0&callback_func_name=switch_scene_callback";
	public static final String MIAN_CHI = "modules/scene_walk.php?action=world_move&scene_id=164&callback_func_name=callbackFnWorldTransport";
	//http://s4.verycd.9wee.com/modules/revival.php?revival_type=2&timeStamp=1280717098093&callback_func_name=revival_callback
	public static final String REVIVE = "modules/revival.php?revival_type=2&callback_func_name=revival_callback";
	private static Pattern scene = Pattern.compile("var now_scene_id = (\\d+)"); 
	private static Pattern zhuangTai = Pattern.compile("״̬��.*?>(����|����|ѵ����|ս����|������|����|������|������)",Pattern.DOTALL);
	
	private static Pattern p = Pattern.compile("�ȼ���<span class=highlight>Lv.(\\d+)");
	private static Pattern point = Pattern.compile("�㾫��\">(\\d+)</span> / ");
	private static Pattern name = Pattern.compile("<span class=\"highlight\" title=\"�鿴������¼\"><a onclick=\"dialog.open\\('/modules/role_name.php', 'dlg_change_name' \\);\">(\\S+?)</a></span>");
	/*
	 * <li><div class="attr_hr_lite" title="�����ʣ����� <span class=highlight>+0%</span> �ļ������ж���">������ <span class="highlight small_font">+0%</span></div></li>
		<li><div class="attr_dr_lite" title="�����ʣ�<span class=highlight>4%</span> �ļ������ܶ��ֵĹ���">������ <span class="highlight small_font">4%</span></div></li>
		<li><div class="attr_ds_lite" title="�����ʣ�<span class=highlight>1%</span> �ļ��ʸ�������� 1.5 ���˺�">������ <span class="highlight small_font">1%</span></div></li>
		<li><div class="attr_id_lite" title="�ƻ��ʣ�<span class=highlight>0%</span> �ļ������Ӷ��ַ���">�ƻ��� <span class="highlight small_font">0%</span></div></li>
	 */
	private static Pattern attribute = Pattern.compile("�����ʣ����� <span class=highlight>\\+(\\d+)%</span>.*?�����ʣ�<span class=highlight>(\\d+)%</span>.*?�����ʣ�<span class=highlight>(\\d+)%</span>.*?�ƻ��ʣ�<span class=highlight>(\\d+)%",Pattern.DOTALL);
	
	private static Pattern HP = Pattern.compile("<div class=\"point_bar_bg\" title=\"��ǰ��Ѫ��<span class=highlight>(\\d+) / (\\d+)</span>");
	private static Pattern MP = Pattern.compile("<div class=\"point_bar_bg\" title=\"��ǰ��Ϣ��<span class=highlight>(\\d+) / (\\d+)</span>");
	public static void setUserAttribute(User user){
		String url = user.getUrl()+USER_INFO+Tools.getTimeStamp(true);
		String page = PageService.getPageWithCookie(url, user);
		Matcher m = attribute.matcher(page);
		if(m.find()){
			user.setMingZhong(Integer.parseInt(m.group(1)));
			user.setDuoShan(Integer.parseInt(m.group(2)));
			user.setBaoJi(Integer.parseInt(m.group(3)));
			user.setPoJi(Integer.parseInt(m.group(4)));
		}
		user.getAttribMap().put("������", user.getDuoShan());
		user.getAttribMap().put("������", user.getMingZhong());
		user.getAttribMap().put("������", user.getBaoJi());
		user.getAttribMap().put("�ƻ���", user.getPoJi());
	}
	public static boolean setUserInfo(User user){
		user.setKillMonstorOnce("24");
		String url = user.getUrl();
		String page = PageService.getPageWithCookie(url, user);
		while(!Tools.success(page)){
			try{
				user.login();
				page = PageService.getPageWithCookie(url, user);
			}catch(Exception e){
				logger.error(user.getRoleName()+"��½ʧ��");
			}
		}
		
		Matcher m = p.matcher(page);
		if(m.find()){
			user.setLevel(m.group(1));
		}
		m = point.matcher(page);
		if(m.find()){
			user.setPoint(m.group(1));
		}
		if(user.getSavePoint() == 0){
			user.setSavePoint(20);
		}
		m = name.matcher(page);
		if(m.find()){
			user.setRoleName(m.group(1));
		}else{
			user.setRoleName("------");
		}
		m = zhuangTai.matcher(page);
		if(m.find()){
			user.setStatus(m.group(1));
			if("����".equals(m.group(1))){
				revive(user);
			}
			if((!"����".equals(m.group(1)) && !"����".equals(m.group(1))) || Monitor.inWuGuan(user)){
				user.setCanMove(false);
			}else{
				user.setCanMove(true);
			}
		}
		m = HP.matcher(page);
		if(m.find()){
			user.setMaxHP(Integer.parseInt(m.group(2)));
			user.getAttribMap().put("��Ѫ", user.getMaxHP());
		}
		m = MP.matcher(page);
		if(m.find()){
			user.setMaxMP(Integer.parseInt(m.group(2)));
			user.getAttribMap().put("��Ϣ", user.getMaxMP());
		}
		int now = getNow();
		
		if(user.getBeginTime() > now || now >= user.getEndTime()){
			user.setShouldKillMonstor(false);
		}else{
			if(Integer.parseInt(user.getPoint()) > user.getSavePoint() && !Monitor.inWuGuan(user) && !Warrior.need10HoursTrain() && !Monitor.atFuBen(user)){
				user.setShouldKillMonstor(true);
			}else{
				user.setShouldKillMonstor(false);
			}
		}
		return Tools.success(page);
	}
	public static void revive(User user){
		String url = user.getUrl()+REVIVE+Tools.getTimeStamp(true);
		String page = PageService.getPageWithCookie(url, user);
		if(Tools.success(page)){
			logger.info(user.getRoleName()+"��ѻسǸ���ɹ�");
		}
	}
	private static int getNow(){
		Calendar c = Calendar.getInstance();
		return c.get(Calendar.HOUR_OF_DAY);
	}
	public static boolean goHome(User user){
		String url = null;
		int now = getNow();
		if(now > 12 || now < 8){
			url = user.getUrl()+MIAN_CHI+Tools.getTimeStamp(true);
			if(Monitor.atMianChi(user)){
				return true;
			}
		}else{
			
			url = user.getUrl()+GO_HOME+Tools.getTimeStamp(true);
			if(Monitor.atHome(user)){
				return true;
			}
		}
		if(Monitor.atFuBen(user)){
			logger.info(user.getRoleName()+"���ڸ�����ֹͣ�ƶ�");
			return true;
		}
		String page = PageService.getPageWithCookie(url, user);
		return Tools.success(page);
	}
	public static String getSceneId(User user){
		String url = user.getUrl();
		String page = PageService.getPageWithCookie(url, user);
		Matcher m = scene.matcher(page);
		if(m.find()){
			return m.group(1);
		}
		return null;
	}
	
}
