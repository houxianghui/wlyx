package com.blue.huanjing;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.blue.common.User;
import com.blue.tools.PageService;
import com.blue.tools.Tools;

public class HuanJing {
	//http://s4.verycd.9wee.com/modules/duel.php?act=pvehall&action=fn&pve_id=243&timeStamp=1283594320917&callback_func_name=callbackfnPveHallFight
	public static final String ATTACK = "modules/duel.php?act=pvehall&action=fn&callback_func_name=callbackfnPveHallFight&pve_id=";
	//http://s4.verycd.9wee.com/modules/duel.php?act=pvehall&timeStamp=1283594503115&callback_func_name=callback_load_content&callback_obj_name=content
	public static final String ENEMY_LIST = "modules/duel.php?act=pvehall&callback_func_name=callback_load_content&callback_obj_name=content";
	//http://s4.verycd.9wee.com/modules/duel.php?act=pvehall&action=view_pve&id=29&s=0&timeStamp=1283612516610&callback_func_name=ajaxCallback&callback_obj_name=vie_pve
	public static final String DETAIL_LIST = "modules/duel.php?act=pvehall&action=view_pve&s=0&callback_func_name=ajaxCallback&callback_obj_name=vie_pve&id=";
	
	private static Pattern p = Pattern.compile("<span class=\"important\">(\\S+?)</span>.*?<span class=\"special\">(.+?)</span>.*?text_monster\">(.+?)</span>.*?\"skill\">(.+?)</span>.*(获胜奖励) <span class=\"highlight\">(\\d+)</span> (积分).*?fnPveHallAction\\( (\\d+) \\)",Pattern.DOTALL);
	
	public static void attack(User user,String id){
		String url = user.getUrl()+ATTACK+id+Tools.getTimeStamp(true);
		String page = PageService.getPage(url, user);
		System.out.println(Tools.success(page));
	}
	public static void listAll(User user)throws Exception{
		
		BufferedWriter bw = new BufferedWriter(new FileWriter(new File("幻境-"+user.getRoleName()+".txt")));
		for(int i = 1;i < 1000;i++){
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
		BufferedWriter bw = new BufferedWriter(new FileWriter(new File("幻境塔-"+user.getUserName()+":"+start+".txt")));
		for(int i = start;i < start+101;i++){
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
}
