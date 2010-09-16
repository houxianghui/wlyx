import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.blue.beauty.Beauty;
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


public class TiGuan {
	public static void main(String[] args) throws Exception{
		System.getProperties().put("socksProxyHost", "127.0.0.1");
		System.getProperties().put("socksProxyPort", "1080");
		List<User> l = new ArrayList<User>();
		User user = new User();
		user.setUrl("s4.verycd.9wee.com");
		user.setUserName("xue_ranger");
		user.setPassword("abc123");
		user.setMiniJingYan(100);
		l.add(user);
		user = new User();
		user.setUrl("s4.verycd.9wee.com");
		user.setUserName("songlijun_tju");
		user.setPassword("zxcvbnm");
		l.add(user);		
		user = new User();
		user.setUrl("s4.verycd.9wee.com");
		user.setUserName("sp_lulu");
		user.setPassword("abc123");
		l.add(user);
		user = new User();
		user.setUrl("s4.verycd.9wee.com");
		user.setUserName("blue_ranger");
		user.setPassword("abc123");
		l.add(user);
		user = new User();
		user.setUrl("s4.verycd.9wee.com");
		user.setUserName("xianghui_hou");
		user.setPassword("abc123");
		l.add(user);
		Duel d = new Duel();
		AutoTask at = new AutoTask();
		Warrior warrior = new Warrior();
		DailyWeals dw = new DailyWeals();
		Monstor m = new Monstor();
		Iterator<User> it = l.iterator();
		while(it.hasNext()){
			user = it.next();
			user.login(true);
			Beauty.tiGuan(user);
//			new MonstorThread(user, m);
		}
	}
}
