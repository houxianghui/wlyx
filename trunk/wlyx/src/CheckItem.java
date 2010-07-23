import com.blue.common.Portal;
import com.blue.common.User;
import com.blue.monstor.Monstor;


public class CheckItem {
	public static void main(String[] args)throws Exception {
//		System.getProperties().put("socksProxyHost", "127.0.0.1");
//		System.getProperties().put("socksProxyPort", "1080");
		
		User user = new User();
		user.setUrl("s4.verycd.9wee.com");
		user.setUserName("xue_ranger");
		user.setPassword("abc123");
		user.login();
		
		Monstor m = new Monstor();
		m.displayTempPack(user);
		
	}
}	
