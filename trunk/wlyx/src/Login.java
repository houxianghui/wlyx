import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;

import com.blue.common.User;
import com.blue.tools.PageService;


public class Login {
	public static void main(String[] args)throws Exception {
		Properties p = System.getProperties();
		p.setProperty("https.proxyHost", "172.16.17.88");
        p.setProperty("https.proxyPort", "8080");
        p.setProperty("http.proxyHost", "172.16.17.88");
        p.setProperty("http.proxyPort", "8080");
		String pageUrl = "http://secure.verycd.com/signin?f=out";
		String data = "ru=http%3A%2F%2Fsecure.verycd.com%2F3rdServices%2F50hero&login_submit=%E7%99%BB%E5%BD%95&username=xue_ranger&password=abc123&_REFERER=";
		User user = new User();
		user.setUserName("xue_ranger");
		user.setPassword("abc123");
		 PageService.login(user);
		
		String page = PageService.getPageWithCookie("http://s4.verycd.9wee.com/", user);
		System.out.println(page);
	}
}
