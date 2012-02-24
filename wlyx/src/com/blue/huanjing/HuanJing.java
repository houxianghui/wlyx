package com.blue.huanjing;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.blue.common.Portal;
import com.blue.common.User;
import com.blue.tools.PageService;
import com.blue.tools.Tools;

public class HuanJing {
	private static Logger logger = Logger.getLogger(HuanJing.class);
	//http://s4.verycd.9wee.com/modules/duel.php?act=pvehall&action=fn&pve_id=243&timeStamp=1283594320917&callback_func_name=callbackfnPveHallFight
	public static final String ATTACK = "modules/duel.php?act=pvehall&action=fn&callback_func_name=callbackfnPveHallFight&pve_id=";
	//http://s4.verycd.9wee.com/modules/duel.php?act=pvehall&timeStamp=1283594503115&callback_func_name=callback_load_content&callback_obj_name=content
	public static final String ENEMY_LIST = "modules/duel.php?act=pvehall&callback_func_name=callback_load_content&callback_obj_name=content";
	//http://s4.verycd.9wee.com/modules/duel.php?act=pvehall&action=view_pve&id=29&s=0&timeStamp=1283612516610&callback_func_name=ajaxCallback&callback_obj_name=vie_pve
	//http://s4.verycd.9wee.com/modules/duel.php?act=pvehall&rand=1285764490443&timeStamp=1285764490165&callback_func_name=ajaxCallback&callback_obj_name=content
	//http://s4.verycd.9wee.com/modules/duel.php?act=pvehall&action=view_pve&id=1&s=0&timeStamp=1285764827991&callback_func_name=ajaxCallback&callback_obj_name=vie_pve
	public static final String DETAIL_LIST = "modules/duel.php?act=pvehall&action=view_pve&s=0&callback_func_name=ajaxCallback&callback_obj_name=vie_pve&id=";
	
	private static Pattern p = Pattern.compile("<span class=\"important\">(\\S+?)</span>.*?<span class=\"special\">(.+?)</span>.*?text_monster\">(.+?)</span>.*?\"skill\">(.+?)</span>.*(获胜奖励) <span class=\"highlight\">(.*?)</span> (积分).*?fnPveHallAction\\((.*?)\\)",Pattern.DOTALL);
	//http://s4.verycd.9wee.com/modules/duel.php?act=pvehall&action=read_save_info&op=pay&timeStamp=1305868506663&callback_func_name=ajaxCallback
	private static final String READ_SAVE = "modules/duel.php?act=pvehall&action=read_save_info&op=pay";
	public static void attack(User user,String id){
		Portal.setUserInfo(user);
		if(user.getCurrHP() < user.getMaxHP()){
			int i = 3;
			while(!Portal.custRoom(user) && i>=0){
				try{
					Thread.sleep(2*1000);
				}catch(Exception e){
					logger.info("客房回血失败，等待2秒");
				}
			}
		}
		String url = user.getUrl()+ATTACK+id+Tools.getTimeStamp(true);
		String page = PageService.getPage(url, user);
		System.out.println(Tools.success(page));
	}
	public static void listAll(User user)throws Exception{
		
		BufferedWriter bw = new BufferedWriter(new FileWriter(new File("幻境-"+user.getRoleName()+".txt")));
		for(int i = 1;i < 100;i++){
			if(i % 10 == 0){
				System.out.println("checking "+i+" finished");
			}
			String url = user.getUrl()+DETAIL_LIST+i+Tools.getTimeStamp(true);
			String page = PageService.getPageWithCookie(url, user);
			Matcher m = p.matcher(page);
			while(m.find()){
				for(int j = 1;j<=m.groupCount();j++){
					bw.write(m.group(j)+"\t");
				}
				bw.newLine();
			}
		}
		bw.close();
	}
	public static void listSpecail(User user,int start)throws Exception{
		BufferedWriter bw = new BufferedWriter(new FileWriter(new File("幻境塔-"+user.getUserName()+start+".txt"),true));
		for(int i = start;i < start+100;i++){
			if((i-start) % 10 == 0){
				System.out.println((i-start)+"% finished...");
			}
			String url = user.getUrl()+DETAIL_LIST+i+Tools.getTimeStamp(true);
			String page = PageService.getPageWithCookie(url, user);
			if(page == null || page.trim().length() == 0||Tools.isErrorPage(page)){
				break;
			}
			Matcher m = p.matcher(page);
			while(m.find()){
				for(int j = 1;j<=m.groupCount();j++){
					bw.write(m.group(j)+"\t");
				}
				bw.newLine();
			}
		}
		bw.close();
	}
	public static void readSave(User user){
		if(!user.isNeedReadSave()){
			return;
		}
		String url = user.getUrl()+READ_SAVE+Tools.getTimeStamp(true);
		String page = PageService.getPageWithCookie(url, user);
		Pattern p = Pattern.compile("result\":\"(\\s+?)\"");
		Matcher m = p.matcher(page);
		if(m.find()){
			if("读盘成功".equals(m.group(1))){
				logger.info(user.getRoleName()+"读取幻境塔进度成功");
			}
		}
	}
}
