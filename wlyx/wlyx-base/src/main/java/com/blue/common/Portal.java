package com.blue.common;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.blue.enums.Profession;
import com.blue.team.WuGuan;
import com.blue.tools.PageService;
import com.blue.tools.Tools;
import com.blue.warrior.Warrior;

/**
 * @author blue
 *
 */
/**
 * @author blue
 *
 */
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
	private static Pattern profession = Pattern.compile("��ϵ��<span class=\"highlight\">.*? - (.*?)</");
	private static Pattern attribute = Pattern.compile("�����ʣ����� <span class=highlight>\\+(\\d+)%</span>.*?�����ʣ�<span class=highlight>(\\d+)%</span>.*?�����ʣ�<span class=highlight>(\\d+)%</span>.*?�ƻ��ʣ�<span class=highlight>(\\d+)%",Pattern.DOTALL);
	
	private static Pattern HP = Pattern.compile("<div class=\"point_bar_bg\" title=\"��ǰ��Ѫ��<span class=highlight>(\\d+) / (\\d+)</span>");
	private static Pattern MP = Pattern.compile("<div class=\"point_bar_bg\" title=\"��ǰ��Ϣ��<span class=highlight>(\\d+) / (\\d+)</span>");
	/*
	 * <div>��� <span class="highlight">Ԫ��</span> �����˹�������<a href="javascript:void(0);" onclick="view_combat ( '223019181188' )">�鿴</a>��</div>

	<span class="date" style="float:right">12��30�� 01:09</span>
	 */
	private static Pattern beating = Pattern.compile("���.*?�����˹���.*?(\\d+)��(\\d+)�� (\\d+):(\\d+)</span>",Pattern.DOTALL);
	//http://s4.verycd.9wee.com/modules/message.php?timeStamp=1293642534287&act=events&callback_func_name=ajaxCallback&callback_obj_name=message_list
	private static String BEAT_INFO = "modules/message.php?&act=events&callback_func_name=ajaxCallback&callback_obj_name=message_list";
	//http://s4.verycd.9wee.com/modules/duel.php?act=pvehall&rand=1293891706483&timeStamp=1293891706483&callback_func_name=ajaxCallback&callback_obj_name=content
	private static String huanJing = "modules/duel.php?act=pvehall&callback_func_name=ajaxCallback&callback_obj_name=content";
	private static String ROOM_RECOVER="modules/warrior.php?act=guestroom&op=restore&id=1";
	private static Pattern position = Pattern.compile("<div class=\"city_scene_name\">(.*?)</div>",Pattern.DOTALL);
	private static Pattern teamPosition = Pattern.compile("team_name\":\"(.*?)\"");
	private static Pattern mapPosition = Pattern.compile("map_name\": \"(.*?)\"");
	//���
	private static Pattern capacity = Pattern.compile("����鿴���͹�ϵ\" >(.*?)</a> ",Pattern.DOTALL);
	private static Pattern dailyWeals = Pattern.compile("������ǩ�������õĽ�����</span><span class=\"text_scene\">(.*?)</span>",Pattern.DOTALL);
	
	private static Pattern taskInfo = Pattern.compile("�����ѽ�������������<span class=\"highlight\">(.*?)</span>");
	
	private static Pattern duelInfo = Pattern.compile("�������Ѿ������� <span class=\"highlight\">(.*?)</span> ����ս");
	public static int getIntValue(String s){
		int i = 0;
		while(i < s.length()){
			if(s.charAt(i) != '0'){
				return Integer.parseInt(s.substring(i));
			}
			i++;
		}
		return -1;
	}
	public static boolean isBeating(User user){
		String url = user.getUrl()+BEAT_INFO+Tools.getTimeStamp(true);
		String page = PageService.getPageWithCookie(url, user);
		Matcher m = beating.matcher(page);
		
		return isBeating(m);
	}
	/**
	 * ���ݴ����matcher�жϣ��Ƿ�ոս���ս��������Ұ�ֺͻþ����жϣ�matcher��4������˳��Ϊ�¡��ա�ʱ���֣��жϼ��3����
	 * @param m
	 * @return
	 */
	public static boolean isBeating(Matcher m) {
		if(m.find()){			
			int month = getIntValue(m.group(1));
			int day = getIntValue(m.group(2));
			int nowMonth = Tools.getMonth();
			int nowDay = Tools.getDay();	//��β
			if((nowMonth - month == 1) || (nowMonth==1 && month==12)){	//��ĩ ���
				if(nowDay != 1){
					return false;
				}
			}else if(nowMonth == month){
				if(nowDay - day>1){
					return false;
				}
			}else{
				return false;
			}
			int hour = getIntValue(m.group(3));
			int min = getIntValue(m.group(4));
			int nowHour = Tools.getNowHour();
			int nowMin = Tools.getNowMinute();
			if(nowHour==0){
				if(hour==0){
					if(nowMin-min < 3){
						return true;
					}
				}else{
					if(nowHour+24-hour==1){
						if(nowMin+60-min < 3){
							return true;
						}
					}
				}
				
			}else{
				if(nowHour-hour == 0){
					if(nowMin-min < 3){
						return true;
					}
				}else if(nowHour-hour == 1){
					if(nowMin+60-min < 3){
						return true;
					}
				}
			}
		}
		return false;
	}
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
		m = profession.matcher(page);
		if(m.find()){
			user.setProfession(Profession.valueOf(m.group(1)));
		}
	}
	public static boolean setUserInfo(User user){
		String url = user.getUrl();
		String page = PageService.getPageWithCookie(url, user);
		while(!Tools.success(page)){
			try{
				if(user.getCookie() == null){
					user.login(true);
				}
				page = PageService.getPageWithCookie(url, user);
			}catch(Exception e){
				logger.error(user.getRoleName()+e.getMessage());
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
		if(isBeating(user)){
			user.setCanMove(false);
		}
		if(Monitor.isHuanJing(user)){
			user.setCanMove(false);
		}
		m = HP.matcher(page);
		if(m.find()){
			user.setCurrHP(Integer.parseInt(m.group(1)==null?"0":m.group(1)));
			user.setMaxHP(Integer.parseInt(m.group(2)==null?"0":m.group(2)));
			user.getAttribMap().put("��Ѫ", user.getMaxHP());
		}
		m = MP.matcher(page);
		if(m.find()){
			user.setCurrMP(Integer.parseInt(m.group(1)==null?"0":m.group(1)));
			user.setMaxMP(Integer.parseInt(m.group(2)==null?"0":m.group(2)));
			user.getAttribMap().put("��Ϣ", user.getMaxMP());
		}
		int now = getNow();
		
		if(user.getBeginTime() > now || now >= user.getEndTime()){
			user.setShouldKillMonstor(false);
		}else{
			if(Integer.parseInt(user.getPoint()) > user.getSavePoint() && !Warrior.need10HoursTrain() && !Monitor.atFuBen(user)){
				if(Warrior.canWar(user)){
					user.setShouldKillMonstor(false);
				}else{
					user.setShouldKillMonstor(true);
				}
			}else{
				user.setShouldKillMonstor(false);
			}
		}
		setTaskInfo(user);
		setGotWeals(user);
		setCapacity(user, page);
		setPosition(user);
		setDuelInfo(user);
		return Tools.success(page);
	}
	
	/**
	 * ���þ�����Ϣ
	 * @param user
	 */
	private static void setDuelInfo(User user) {
		String url = user.getUrl()+"modules/duel.php?act=hall"+Tools.getTimeStamp(true);
		String page = PageService.getPageWithCookie(url, user);
		Matcher m = duelInfo.matcher(page);
		if(m.find()){
			user.setDuelInfo(m.group(1));
		}
	}
	/**
	 * �������������Ϣ0/20
	 * @param user
	 */
	private static void setTaskInfo(User user) {
		String url = user.getUrl()+"modules/task.php?"+Tools.getTimeStamp(false);
		String page = PageService.getPageWithCookie(url, user);
		Matcher m = taskInfo.matcher(page);
		if(m.find()){
			user.setTaskInfo(m.group(1));
		}
	}
	/**
	 * ���ý�ɫ�ĵ��ո���
	 * @param user
	 */
	private static void setGotWeals(User user){
		String url = user.getUrl()+"modules/day_weals.php?act=show"+Tools.getRandAndTime();
		String page = PageService.getPageWithCookie(url, user);
		setGotWeals(user, page);
	}
	private static void setGotWeals(User user,String page){
		Matcher m = dailyWeals.matcher(page);
		if(m.find()){
			user.setGotWeals(m.group(1));
		}
	}
	/**
	 * ���ý�ɫ�������Ϣ��ū����ū������
	 * @param user
	 * @param page
	 */
	private static void setCapacity(User user,String page){
		Matcher m = capacity.matcher(page);
		if(m.find()){
			user.setCapacity(m.group(1));
		}
	}
	private static void setPosition(User user){
		String url = user.getUrl()+"modules/scene.php?"+Tools.getTimeStamp(false);
		String page = PageService.getPageWithCookie(url, user);
		Matcher m = position.matcher(page);
		
		if(m.find()){
			user.setPosition(m.group(1));
		}else{
			m = teamPosition.matcher(page);
			if(m.find()){
				user.setPosition(Tools.hexToString(m.group(1)));
			}else{
				m = mapPosition.matcher(page);
				if(m.find()){
					user.setPosition(Tools.hexToString(m.group(1)));
				}
			}
		}
	}
	public static void setTeamId(User user){
		String url = user.getUrl()+WuGuan.MY_TEAM+Tools.getTimeStamp(true);
		String page = PageService.getPageWithCookie(url, user);
		Matcher m = WuGuan.myTeamId.matcher(page);
		if(m.find()){
			user.setTeamId(m.group(1));
		}
	}
	/**
	 * �سǸ���
	 * @param user
	 */
	public static void revive(User user){
		String url = user.getUrl()+REVIVE+Tools.getTimeStamp(true);
		String page = PageService.getPageWithCookie(url, user);
		if(Tools.success(page)){
			custRoom(user);
			logger.info(user.getRoleName()+"��ѻسǸ���ɹ�");
		}
	}
	/**
	 * �ͷ���Ѫ
	 * @param user
	 */
	public static boolean custRoom(User user){
		//http://s4.verycd.9wee.com/modules/warrior.php?act=guestroom&op=restore&id=1&timeStamp=1308725110871&callback_func_name=warrior_common_callback
		String url = user.getUrl()+ROOM_RECOVER+Tools.getTimeStamp(true);
		String page = PageService.getPageWithCookie(url, user);
		if(Tools.success(page)){
			logger.info(user.getRoleName()+"�ͷ���Ѫ���");
			return true;
		}
		return false;
	}
	private static int getNow(){
		Calendar c = Calendar.getInstance();
		return c.get(Calendar.HOUR_OF_DAY);
	}
	public static boolean goHome(User user){
		String url = null;
		int now = getNow();
		if(now >= 13 || now < 8){
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
		if(Monitor.isDefeatingMianChi(user)){
			logger.info(user.getRoleName()+"�����ųط���ս��ֹͣ�ƶ�");
			return true;
		}
		if(isBeating(user)){
			logger.info(user.getRoleName()+"����������֣�ֹͣ�ƶ�");
			return true;
		}
		if(Monitor.isHuanJing(user)){
			logger.info(user.getRoleName()+"���ڻþ�����ֹͣ�ƶ�");
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
