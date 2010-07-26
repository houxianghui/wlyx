package com.start;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import com.blue.common.User;
import com.blue.daily.DailyWeals;
import com.blue.daily.DailyWealsThread;
import com.blue.duel.Duel;
import com.blue.duel.DuelThread;
import com.blue.monstor.Monstor;
import com.blue.task.AutoRewardThread;
import com.blue.task.AutoTask;
import com.blue.task.AutoTaskThread;
import com.blue.warrior.Warrior;
import com.blue.warrior.WarriorThread;

public class Main {
	public static void main(String[] args)throws Exception {
		Properties p = System.getProperties();
		p.setProperty("https.proxyHost", "172.16.17.88");
        p.setProperty("https.proxyPort", "8080");
        p.setProperty("http.proxyHost", "172.16.17.88");
        p.setProperty("http.proxyPort", "8080");
		List<User> l = new ArrayList<User>();
		User user = new User();	
		
		user.setUrl("s4.verycd.9wee.com");
		user.setUserName("xuxudddd");
		user.setPassword("168168");
		l.add(user);
		Duel d = new Duel();
		AutoTask at = new AutoTask();
		Warrior warrior = new Warrior();
		DailyWeals dw = new DailyWeals();
		Monstor m = new Monstor();
		Iterator<User> it = l.iterator();
		while(it.hasNext()){
			user = it.next();
			user.login();
			new WarriorThread(user, warrior);
			new DuelThread(d, user);
			new AutoTaskThread(at, user);
			new AutoRewardThread(at, user);
			new DailyWealsThread(user, dw);
//			new MonstorThread(user, m);
		}
	}
}
