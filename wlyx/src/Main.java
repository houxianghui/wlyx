
import com.blue.common.User;
import com.blue.tools.PageService;
import com.blue.tools.Tools;


public class Main {
	public static void main(String[] args)throws Exception {
		System.out.println(System.currentTimeMillis());
		System.getProperties().put("socksProxyHost", "127.0.0.1");
		System.getProperties().put("socksProxyPort", "1080");
		
		User user = new User();
		user.setUrl("s4.verycd.9wee.com");
		user.setUserName("sp_lulu");
		user.setPassword("abc123");
		user.login();
		String url = "http://s4.verycd.9wee.com/modules/role_info.php?timeStamp=1279249695044&callback_func_name=callback_load_content&callback_obj_name=content";
		String page = PageService.getPageWithCookie(url,user);
		System.out.println(page);
	}
}
