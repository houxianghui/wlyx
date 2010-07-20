
import com.blue.common.Move;
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
		user.setUserName("songlijun_tju");
		user.setPassword("zxcvbnm");
		user.login();
		
		String url = "http://s4.verycd.9wee.com/modules/auto_combats.php?act=show&mid=63&callback_func_name=ajaxCallback&callback_obj_name=dlg_view_monster";
		String s = PageService.getPageWithCookie(url+Tools.getTimeStamp(true), user);
		System.out.println(s);
//		System.out.println(s);
		url = "http://s4.verycd.9wee.com/modules/auto_combats.php?act=start&timeStamp=1279616126608";
		s = PageService.postPage(url, s, user);
		System.out.println(s);
		
//		String url = "http://s4.verycd.9wee.com/";
//		String module = "modules/scene_walk.php?action=world_move&callback_func_name=callbackFnWorldTransport&scene_id=134";
//		url += module;
//		url += Tools.getRandAndTime();
//		String page = PageService.getPageWithCookie(url,user);
//		System.out.println(page);
	}
}
