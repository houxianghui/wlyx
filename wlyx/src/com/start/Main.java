package com.start;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.blue.common.MonitorThread;
import com.blue.common.User;
import com.blue.daily.DailyWeals;
import com.blue.daily.DailyWealsThread;
import com.blue.duel.Duel;
import com.blue.duel.DuelThread;
import com.blue.monstor.Monstor;
import com.blue.monstor.MonstorThread;
import com.blue.task.AutoRewardThread;
import com.blue.task.AutoTask;
import com.blue.task.AutoTaskThread;
import com.blue.team.WuGuanThread;
import com.blue.warrior.Warrior;
import com.blue.warrior.WarriorThread;

public class Main {
	public static void main(String[] args)throws Exception {
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
			if(i.length > 3){
				User user = new User();
				user.setUserName(i[0]);
				user.setPassword(i[1]);
				user.setBeginTime(Integer.parseInt(i[2]));
				user.setEndTime(Integer.parseInt(i[3]));
				l.add(user);
			}
		}
		
		Duel d = new Duel();
		AutoTask at = new AutoTask();
		Warrior warrior = new Warrior();
		DailyWeals dw = new DailyWeals();
		Monstor m = new Monstor();
		Iterator<User> it = l.iterator();
		while(it.hasNext()){
			User user = it.next();
			user.login();
			new WarriorThread(user, warrior);	//大厅
			new DuelThread(d, user);			//竞技
			new AutoTaskThread(at, user);		//任务
			new AutoRewardThread(at, user);		//任务奖励
			new DailyWealsThread(user, dw);		//每日福利
			new MonstorThread(user, m);			//野训
			new MonitorThread(user);			//图片等
			new WuGuanThread(user);				//武馆
		}
	
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
