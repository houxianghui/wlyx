package com.start;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.blue.common.User;
import com.blue.monstor.UserMonitor;

public class Main {
	public static void main(String[] args)throws Exception {
		
		System.setProperty("sun.net.client.defaultConnectTimeout", "60000");
		System.setProperty("sun.net.client.defaultReadTimeout","60000");
		List<User> l = getUserInfo();
	
		Iterator<User> it = l.iterator();
		while(it.hasNext()){
			User user = it.next();
			user.login(true);
			Thread.sleep(3*1000);
		}
		new UserMonitor(l);
	
	}
	public static List<User> getUserInfo() throws Exception, IOException {
		String s = readText("user.ini");
		Pattern sys = Pattern.compile("<SYSTEM_SET>\\s+(\\S+?):(\\d+?)\\s+</SYSTEM_SET>",Pattern.DOTALL);
		Matcher sysm = sys.matcher(s);
		Properties p = System.getProperties();
		if(sysm.find()){
			p.setProperty("http.proxyHost", sysm.group(1));
			p.setProperty("http.proxyPort", sysm.group(2));
		}
		Pattern u = Pattern.compile("<USER_SET>(.*?)</USER_SET>",Pattern.DOTALL);
		Matcher mu = u.matcher(s);
		String users = null;
		if(mu.find())
			users= mu.group(1);
		BufferedReader br = new BufferedReader(new StringReader(users));
		String t = null;
		
		List<User> l = new ArrayList<User>();
		while((t=br.readLine())!=null){
			if(t.startsWith("#")){
				continue;
			}
			String[] i = t.split(",");
			if(i.length > 4){
				User user = new User();
				user.setUserName(i[0]);
				user.setPassword(i[1]);
				user.setBeginTime(Integer.parseInt(i[2].trim()));
				user.setEndTime(Integer.parseInt(i[3].trim()));
				user.setWarriorChoice(Integer.parseInt(i[4].trim()));
				user.setDueSleepInteval(Integer.parseInt(i[5].trim()));
				user.setMiniMoney(Integer.parseInt(i[6].trim()));
				user.setNeedCatchSlavy(Integer.parseInt(i[7]));
				user.setSlavyMin(Integer.parseInt(i[8]));
				user.setQualitySave(Integer.parseInt(i[9]));
				user.setSavePoint(Integer.parseInt(i[10]));
				user.setNeedWar(i[11]);
				user.setGloryBuy(i[12]);
				user.setPainType(i[13]);
				user.setBuildDoor(Integer.parseInt(i[14]));
				user.setBlackStartTime(Integer.parseInt(i[15]));
				user.setBlackEndTime(Integer.parseInt(i[16]));
				user.setTianJiDoor(Integer.parseInt(i[17]));
				if(i.length>18){
					int needGetAward = Integer.parseInt(i[18]);
					user.setNeedGetAward(needGetAward == 1);
				}
				if(i.length>19){
					int needGuoDu = Integer.parseInt(i[19]);
					user.setNeedGuoDu(needGuoDu == 1);
				}
				if(i.length > 20){
					user.setBeatTeam(i[20]);
				}
				if(i.length>21){
					user.setDuelStartTime(Integer.parseInt(i[21]));
				}
				if(i.length>22){
					String flag = i[22];
					user.setDuelDropWeapon("1".equals(flag));
				}
				l.add(user);
			}
		}
		return l;
	}
	public static String readText(String fileName)throws Exception{
		File f = new File(fileName);
		FileReader fr = new FileReader(f);
		StringBuffer sb = new StringBuffer();
		BufferedReader br = new BufferedReader(fr);
		
		String s = null;
		while((s=br.readLine())!=null){
			sb.append(s+"\n");
		}
		return sb.toString();
	}	
}
