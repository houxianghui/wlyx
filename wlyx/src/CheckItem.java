import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import com.blue.common.Portal;
import com.blue.common.User;
import com.blue.monstor.Monstor;


public class CheckItem {
	public static void main(String[] args)throws Exception {
		Properties p = System.getProperties();
		p.setProperty("http.proxyHost", "172.16.17.88");
        p.setProperty("http.proxyPort", "8080");
		
		List<User> l = new ArrayList<User>();
		User user = new User();
		user.setUrl("s4.verycd.9wee.com");
		user.setUserName("sp_lulu");
		user.setPassword("abc123");
		user.login();
		l.add(user);
		user = new User();
		user.setUrl("s4.verycd.9wee.com");
		user.setUserName("blue_ranger");
		user.setPassword("abc123");
		user.login();
		l.add(user);
		user = new User();
		user.setUrl("s4.verycd.9wee.com");
		user.setUserName("xianghui_hou");
		user.setPassword("abc123");
		user.login();
		l.add(user);
		user = new User();
		user.setUrl("s4.verycd.9wee.com");
		user.setUserName("songlijun_tju");
		user.setPassword("zxcvbnm");
		user.login();
		l.add(user);
		user = new User();
		user.setUrl("s4.verycd.9wee.com");
		user.setUserName("weiwei_mi");
		user.setPassword("abc123");
		user.login();
		l.add(user);
		user = new User();
		user.setUrl("s4.verycd.9wee.com");
		user.setUserName("八面金锁");
		user.setPassword("abc123");
		user.login();
		l.add(user);
		user = new User();
		user.setUrl("s4.verycd.9wee.com");
		user.setUserName("定军枪");
		user.setPassword("abc123");
		user.login();
		l.add(user);
		user = new User();
		user.setUrl("s4.verycd.9wee.com");
		user.setUserName("sieera1");
		user.setPassword("14289533");
		user.login();
		l.add(user);
		Monstor m = new Monstor();
		Iterator<User> it = l.iterator();
		while(it.hasNext()){
			User u = it.next();
			System.out.println(u.getUserName()+ ":------------");
			m.displayTempPack(u);
		}
		
	}
}	
