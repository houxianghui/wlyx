package com.blue.tianjitang;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.blue.common.User;
import com.blue.tools.PageService;
import com.blue.tools.Tools;

public class PlantSeeds {
	private static Logger logger = Logger.getLogger(PlantSeeds.class);
	//http://s4.verycd.9wee.com/modules/team_foster.php?act=build&action=enter&bui_id=5&timeStamp=1331099223870&callback_func_name=ajaxCallback&callback_obj_name=team_foster_build5
	private static String QI_ZHEN_URL = "modules/team_foster.php?act=build&action=enter&bui_id=5";
	private static Pattern seeds = Pattern.compile("fnBuyFarmPlant\\( (\\d+), '(\\S+?)', (\\d+) \\);");
	private static Pattern feed = Pattern.compile("team_farm_feed\\((\\d+), (\\d+), (\\d+), (\\d+), (\\d+), (\\d+), ''\\);\" href=\"javascript:void\\(0\\);\">我要");
	private static Pattern harvest = Pattern.compile("team_farm_feed\\((\\d+), (\\d+), (\\d+), (\\d+), (\\d+), (\\d+), ''\\);\" href=\"javascript:void\\(0\\);\">收获",Pattern.DOTALL);
	private static Pattern plant = Pattern.compile("team_farm_plant\\((\\d+), (\\d+), (\\d+), (\\d+)\\);\" href=\"javascript:void\\(0\\);\">我要种植");
	private static Pattern getPackSeeds = Pattern.compile("<label for=\"radio_farm_plant_base_(\\d+)\">(\\S+?)</label>",Pattern.DOTALL);
	
	public static void getSeeds(User user)throws Exception{
		String url = user.getUrl()+QI_ZHEN_URL+Tools.getTimeStamp(true);
		String page = PageService.getPageWithCookie(url, user);
		Matcher m = seeds.matcher(page);
		BufferedWriter bw = new BufferedWriter(new FileWriter("种子.txt"));
		while(m.find()){
			bw.write(m.group(1)+"\t"+m.group(2));
			bw.newLine();
		}
		bw.close();
	}
	private static boolean needPlant(User user){
		String day = String.valueOf(Tools.getDayOfWeek());
		if(user.getPlantDay().indexOf(day)==-1){
			logger.info(user.getRoleName()+"今日无需种植");
			return false;
		}
		int time = Tools.getNowHour();
		if(time<10){
			logger.info("未到10点，不进行种植操作");
			return false;
		}
		return true;
	}
	private static boolean needBuy(User user,Matcher m)throws Exception{
		if(Tools.isEmpty(user.getBuySeed())){
			return false;
		}
		//http://s4.verycd.9wee.com/modules/team_foster.php?act=build&action=farmplant&submit=0&farm_id=125&team_id=58&bui_id=5&page=1&timeStamp=1331114428554&callback_func_name=ajaxCallback&callback_obj_name=fostor_farm_plant
		String page = plantTry(user, m);
		Pattern p = Pattern.compile("result\":\"(.*?)\"");
		Matcher rm = p.matcher(page);
		if(rm.find()){
			String msg = Tools.hexToString(rm.group(1));
			if(msg.indexOf("无可种植的种子")!=-1){
				return true;
			}
		}
		return false;
	}
	private static String plantTry(User user, Matcher m) {
		String url = user.getUrl()+"modules/team_foster.php?act=build&action=farmplant&submit=0"+getPlantTryData(m);
		return PageService.getPageWithCookie(url, user);
	}
	private static void buySeed(User user)throws Exception{
		//http://s4.verycd.9wee.com/modules/team_foster.php?act=build&action=farmbuy&base_id=6254&timeStamp=1331100559126&callback_func_name=callbackfnBuyFarmPlant
		String url = user.getUrl()+"modules/team_foster.php?act=build&action=farmbuy&base_id="+user.getBuySeed()+Tools.getTimeStamp(true);
		String page = PageService.getPageWithCookie(url, user);
		logger.info(getReturnMsg(user, page));
	}

	public static void plant(User user)throws Exception{
		
		String url = user.getUrl()+QI_ZHEN_URL+Tools.getTimeStamp(true);
		String page = PageService.getPageWithCookie(url, user);
		Matcher hm = harvest.matcher(page);
		while(hm.find()){
			harvest(user, hm);
		}
		if(!needPlant(user)){
			return;
		}
		Matcher m = plant.matcher(page);
		if(m.find()){
			if(needBuy(user,m)){
				buySeed(user);
			}
			//http://s4.verycd.9wee.com/modules/team_foster.php?act=build&action=farmplant&submit=0&farm_id=121&team_id=58&bui_id=5&page=1&timeStamp=1331453618308&callback_func_name=ajaxCallback&callback_obj_name=fostor_farm_plant
			String result = plantTry(user, m);
			Matcher canPlant = getPackSeeds.matcher(result);
			while(canPlant.find()){
				//http://s4.verycd.9wee.com/modules/team_foster.php?act=build&action=farmplant&submit=1&timeStamp=1331103129720
				String plant = user.getUrl()+"modules/team_foster.php?act=build&action=farmplant&submit=1"+Tools.getTimeStamp(true);
				String s = PageService.postPage(plant, getPlantData(m,user,canPlant).toString(), user);
				logger.info(user.getRoleName()+getReturnMsg(user, s));
			}
			
			
		}
	}
	private static String getReturnMsg(User user, String page) {
		Pattern p = Pattern.compile("result\":\"(.*?)\"");
		Matcher rm = p.matcher(page);
		if(rm.find()){
			String msg = Tools.hexToString(rm.group(1));
			return (user.getRoleName()+msg);
		}
		return "";
	}
	private static StringBuffer getPlantData(Matcher m,User user,Matcher canPlant) {
		//team_id=58&farm_id=123&bui_id=5&page=1&radio_farm_plant_base=6254&confirm_button=%E7%A1%AE%E5%AE%9A&cancel_button=%E5%8F%96%E6%B6%88&callback_func_name=ajaxCallback
		StringBuffer data = new StringBuffer("team_id=");
		data.append(m.group(2));
		data.append("&farm_id=");
		data.append(m.group(1));
		data.append("&bui_id=");
		data.append(m.group(3));
		data.append("&page=");
		data.append(m.group(4));
		data.append("&radio_farm_plant_base=");
		data.append(canPlant.group(1));
		data.append("&confirm_button=%E7%A1%AE%E5%AE%9A&cancel_button=%E5%8F%96%E6%B6%88&callback_func_name=ajaxCallback");
		return data;
	}
	public static void harvest(User user,Matcher m){
		if(!user.isAutoHarvest()){
//			logger.info(user.getRoleName()+"设置为不自动收获");
			return;
		}
		//http://s4.verycd.9wee.com/modules/team_foster.php?act=build&action=farmaction&submit=1&farm_id=121&team_id=58&creature_type=4&bui_id=5&page=1&timeStamp=1331168476685&callback_func_name=callbackTeamfarm
		String url = user.getUrl()+"modules/team_foster.php?act=build&action=farmaction&submit=1"+getFarmData(m)+Tools.getTimeStamp(true);
		String page = PageService.getPageWithCookie(url, user);
		logger.info(getReturnMsg(user, page));
	}
	public static void farm(User user){
		if(!user.isNeedFarm()){
			return;
		}
		//http://s4.verycd.9wee.com/modules/team_foster.php?act=build&action=enter&bui_id=5&timeStamp=1331096889939&callback_func_name=ajaxCallback&callback_obj_name=team_foster_build5
		//http://s4.verycd.9wee.com/modules/team_foster.php?act=build&action=farmaction&farm_id=121&team_id=58&creature_type=2&bui_id=5&page=1&timeStamp=1331097341676&callback_func_name=callbackTeamfarm
		//http://s4.verycd.9wee.com/modules/team_foster.php?act=build&action=farmaction&farm_id=122&team_id=58&creature_type=3&bui_id=5&page=1&timeStamp=1331116993587&callback_func_name=callbackTeamfarm
		String url = user.getUrl()+QI_ZHEN_URL+Tools.getTimeStamp(true);
		
		String page = PageService.getPageWithCookie(url, user);
		Matcher m = feed.matcher(page);
		while(m.find()){
			StringBuffer sb = new StringBuffer(user.getUrl());
			sb.append("modules/team_foster.php?act=build&action=farmaction");
			sb.append(getFarmData(m));
			
			PageService.getPageWithCookie(sb.toString(), user);
		}
	}
	private static StringBuffer getFarmData(Matcher m) {
		StringBuffer sb = new StringBuffer("&farm_id=");
		sb.append(m.group(1));
		sb.append("&team_id=");
		sb.append(m.group(2));
		sb.append("&creature_type=");
		sb.append(m.group(3));
		sb.append("&bui_id=");
		sb.append(m.group(4));
		sb.append("&page=");
		sb.append(m.group(5));
		return sb;
	}
	private static StringBuffer getPlantTryData(Matcher m) {
		StringBuffer sb = new StringBuffer("&farm_id=");
		sb.append(m.group(1));
		sb.append("&team_id=");
		sb.append(m.group(2));
		sb.append("&bui_id=");
		sb.append(m.group(3));
		sb.append("&page=");
		sb.append(m.group(4));
		return sb;
	}
	
}
