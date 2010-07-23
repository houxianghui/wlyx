import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
import com.blue.warrior.Warrior;
import com.blue.warrior.WarriorThread;


public class Start {
	public static void main(String[] args) throws Exception{
//		System.getProperties().put("socksProxyHost", "127.0.0.1");
//		System.getProperties().put("socksProxyPort", "1080");
		List<User> l = new ArrayList<User>();
		User user = new User();
		user.setUrl("s4.verycd.9wee.com");
		user.setUserName("xue_ranger");
		user.setPassword("abc123");
		l.add(user);
//		user = new User();
//		user.setUrl("s4.verycd.9wee.com");
//		user.setUserName("sp_lulu");
//		user.setPassword("abc123");
//		l.add(user);		
		Duel d = new Duel();
		AutoTask at = new AutoTask();
		Warrior warrior = new Warrior();
		DailyWeals dw = new DailyWeals();
		Monstor m = new Monstor();
		Iterator<User> it = l.iterator();
		while(it.hasNext()){
			it.next().login();
			new WarriorThread(user, warrior);
			new DuelThread(d, user);
			new AutoTaskThread(at, user);
			new AutoRewardThread(at, user);
			new DailyWealsThread(user, dw);
			new MonstorThread(user, m);
		}
	}
}
