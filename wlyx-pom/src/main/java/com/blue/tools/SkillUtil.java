package com.blue.tools;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.blue.common.User;
import com.blue.enums.Profession;
import com.blue.fyzb.ServerDuelConfig;

public class SkillUtil {
	private static Logger logger = Logger.getLogger(SkillUtil.class);
	private static Map<Profession, Map<String,String>> skills = new HashMap<Profession, Map<String,String>>();
	private static Pattern equipeSkill = Pattern.compile("id\":\"(\\d+)\",\"name\":\"(.*?)\"");
	static{
		BufferedReader br = null;
		try {
			InputStream is = FileUtil.readFile("skills.txt");
			br = new BufferedReader(new InputStreamReader(is));
			String s = null;
			Map<String,String> m = null;
			while((s = br.readLine()) != null){
				if(s.startsWith("#")){
					m = new HashMap<String, String>();
					skills.put(Profession.valueOf(s.substring(1)), m);
				}else{
					String[] result = s.split("=");
					m.put(result[0], result[1]);
				}
			}
		} catch (FileNotFoundException e) {
			logger.error(e);
		} catch (IOException e) {
			logger.error(e);
		}finally{
			if(br != null){
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	public static String getSkillId(User user,String skillName){
		return skills.get(user.getProfession()).get(skillName);
	}
	/**
	 * Ϊ�������������ս����
	 * @param user
	 * @param p
	 */
	public static void equipSkill(User user,Profession p){
		Map<Profession,ServerDuelConfig> m = user.getServerDuelConfigMap();
		Map<String,String> equiped = getEquipedSkill(user);
		ServerDuelConfig config = m.get(p);
		if(config == null){
			logger.info(user.getRoleName()+"δ����"+p+"����ս���ܣ�ʹ��Ĭ�ϼ���");
			return;
		}else{
			equipActiveSkill(user, config.getActiveSkill());
			
			String assistA = config.getAssistSkillA();
			String assistB = config.getAssistSkillB();
			if(Tools.isEmpty(assistA) && Tools.isEmpty(assistB)){
				return;
			}
			Set<String> assists = equiped.keySet();
			//���û�����ü��ܣ���Ĭ��Ϊtrue
			boolean isExistA = Tools.isEmpty(assistA);
			boolean isExistB = Tools.isEmpty(assistB);
			
			for(String s:assists){
				if(!s.equals(assistA) && !s.equals(assistB)){
					removeSkill(user, equiped.get(s));
				}else{
					if(s.equals(assistA)){
						isExistA = true;
					}else if(s.equals(assistB)){
						isExistB = true;
					}
				}
			}
			if(!isExistA){
				equipAssistSkill(user, assistA);
			}
			if(!isExistB){
				equipAssistSkill(user, assistB);
			}
		}
	}
	/**
	 * ʹ����������
	 * @param user
	 * @param skillName
	 * @return
	 */
	public static boolean equipActiveSkill(User user,String skillName){
		String url = user.getUrl()+"modules/role_skill.php?act=skill_equip&equip_type=0"+"&skill_id="+getSkillId(user, skillName)+Tools.getTimeStamp(true);
		String page = PageService.getPageWithCookie(url, user);
		if(page.indexOf("skill_id") != -1){
			logger.info(user.getRoleName()+"ʩչ"+skillName+"�ɹ�");
			return true;
		}else{
			logger.info(user.getRoleName()+"ʹ��"+skillName+"ʧ��");
			return false;
		}
	}
	/**
	 * ʹ�ø�������
	 * @param user
	 * @param skillName
	 * @return
	 */
	public static boolean equipAssistSkill(User user,String skillName){
		String url = user.getUrl()+"modules/role_skill.php?act=skill_use&skill_id="+getSkillId(user, skillName)+Tools.getTimeStamp(true);
		String page = PageService.getPageWithCookie(url, user);
		if(page.indexOf("skill_id") != -1){
			logger.info(user.getRoleName()+"ʩչ"+skillName+"�ɹ�");
			return true;
		}else{
			logger.info(user.getRoleName()+"ʹ��"+skillName+"ʧ��");
			return false;
		}
	}
	/**
	 * �Ƴ���������
	 * @param user
	 * @param skillId
	 * @return
	 */
	public static boolean removeSkill(User user,String skillId){
		String url = user.getUrl()+"modules/role_info.php?act=remove_state&state_id="+skillId+Tools.getTimeStamp(true);
		String page = PageService.postPage(url, "callback_func_name=callback_load_modules", user);
		if(page.indexOf("error")!=-1){
			logger.info(user.getRoleName()+"�Ƴ���������"+skillId+"�ɹ�");
			return true;
		}
		return false;
	}
	/**
	 * ��õ�ǰ�Ѿ�ʹ�õĸ�������
	 * @param user
	 * @return
	 */
	public static Map<String,String> getEquipedSkill(User user){
		String url = user.getUrl();
		String page = PageService.getPageWithCookie(url, user);
		int index = page.indexOf("window.roleStates");
		if(index > 0 ){
			page = page.substring(index);
		}
		Map<String,String> map = new HashMap<String, String>();
		Matcher m = equipeSkill.matcher(page);
		while(m.find()){
			map.put(Tools.hexToString(m.group(2)), m.group(1));
		}
		return map;
	}
}
