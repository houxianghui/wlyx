
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
//		PageService.postPage("http://s4.verycd.9wee.com/modules/auto_combats.php?act=start"+Tools.getTimeStamp(true), "mid=63&select_frequency=1&callback_func_name=callbackFnStartAutoCombat", user);
//		String url = "http://s4.verycd.9wee.com/modules/scenes.php?sid=154&callback_func_name=switch_scene_callback"+Tools.getTimeStamp(true);
		String url = "http://s4.verycd.9wee.com/modules/role_info.php?callback_func_name=callback_load_content&callback_obj_name=content"+Tools.getTimeStamp(true);
		String page = PageService.getPageWithCookie(url, user);
		BufferedWriter bw = new BufferedWriter(new FileWriter("f:/test.txt"));
		bw.write(page);
		System.out.println(page);
		Pattern p = Pattern.compile("item_id\":\"(\\d+).*?is_checkup\":\"(\\d+)\"");
		Matcher m = p.matcher(page);
		while(m.find()){
			System.out.println(m.group());
		}
//		Pattern p = Pattern.compile("µÈ¼¶£º<span class=highlight>Lv.(\\d+)");
//		Matcher m = p.matcher(page);
//		while(m.find()){
//			System.out.println(m.group(1));
//		}
//		System.out.println(page);
//		Pattern p = Pattern.compile("monster_id\":\"(\\d+)\",\"level_range\":\"Lv.(\\d+)-(\\d+)");
//		Matcher m = p.matcher(page);
//		while(m.find()){
//			System.out.println(m.group(1)+" "+m.group(2));
//		}
	}
}
