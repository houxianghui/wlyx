package com.blue.tools.login;

import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.blue.common.User;
import com.blue.tools.PageService;
import com.blue.tools.Tools;

public class VeryCD {
	private static Logger logger = Logger.getLogger(VeryCD.class);
	private static Pattern p = Pattern.compile("document\\.getElementById\\(\"play_frame\"\\)\\.src=\"(.*?)\"");
	public static void login( User user){
		if(PageService.readCookie(user)){
			return;
		}
		HttpURLConnection con = null;
		try{
			URL url = new URL("http://secure.verycd.com/signin?f=out");
			con = (HttpURLConnection) url.openConnection();
			String data = makeLoginData(user);
			if(con != null){
				con.setDoOutput(true);
				
				con.setRequestMethod("POST");
				con.addRequestProperty("Host", "secure.verycd.com");
				con.addRequestProperty("Content-Type",
						"application/x-www-form-urlencoded");
				con.addRequestProperty("Referer", "http://secure.verycd.com/3rdServices/50hero");
				PageService.setGZipOn(con);
				HttpURLConnection.setFollowRedirects(false);
				con.setInstanceFollowRedirects(false);
				
				OutputStream os =con.getOutputStream();
//				GZIPOutputStream gis = new GZIPOutputStream(os);
				if(os != null){
					os.write(data.getBytes("UTF-8"));
					os.flush();
					os.close();
				}
				//__utma=242249088.396578207.1271208283.1280120446.1280130776.185; __utmz=242249088.1280112946.182.177.utmcsr=game.verycd.com|utmccn=(referral)|utmcmd=referral|utmcct=/hero/; __utmc=242249088; __utmb=242249088.4.10.1280130776;dcm=1;
				StringBuffer sb = new StringBuffer("");
				String key = null;
				for(int i = 1;(key=con.getHeaderFieldKey(i))!=null ;i++){
					String value = con.getHeaderField(i);
					if (key.startsWith("Set-Cookie")) {
						if (!(value.contains("=deleted;"))) {
							int index = value.indexOf(";");
							if (index > 0)
								sb.append(value.substring(0, index + 1));
						}
					} else if ((key.startsWith("location:"))
							&& (value.contains("error_code"))) {
						logger.error("µÇÂ½Ê§°Ü");
						return ;
					}
				}
				
				user.setCookie(sb.toString());
//				String page = PageService.getPageWithCookie("http://secure.verycd.com/signin?ak=50hero&sid="+user.getHost(), user);
				String nextUrl = getLogin("http://secure.verycd.com/signin?ak=50hero&sid="+user.getHost(), sb.toString());
				String page = PageService.getPageWithCookie(nextUrl, user);
				Matcher m = p.matcher(page);
				if(m.find()){
					nextUrl = m.group(1);
				}
//				nextUrl = user.getUrl()+"passport.php?act=login&referer=%2F&username="+user.getUserName()+"&time="+Tools.getTimeStamp(true);//"&mac=ff2af4be183ef4a32a8dc6ec98c6c59f";
//				user.setCookie(sb.toString());
				String cookie = getCookie(nextUrl,user);
				user.setCookie(cookie);
				if(!Tools.isEmpty(user.getStockPwd())){
					PageService.setStocktCookie(user);
				}
				PageService.setCookie(user);
			}
		}catch(Exception e){
			logger.info(user.getUserName()+e.getMessage());
			if(con != null){
				con.setConnectTimeout(1);
			}
		}finally{
			if(con != null){
				con.disconnect();
			}
		}
	}
	public static String makeLoginData(User user) throws UnsupportedEncodingException {
		String data = "ru=http%3A%2F%2Fsecure.verycd.com%2F3rdServices%2F50hero&login_submit=%E7%99%BB%E5%BD%95&" +
				"username=" + URLEncoder.encode(user.getUserName(),"utf-8")+
				"&password=" + URLEncoder.encode(user.getPassword(),"utf-8")+
				"&_REFERER=";
		return data;
	}
	private static String getLogin(String pageUrl, String cookie){
		HttpURLConnection con = null;
		try{
			URL url = new URL(pageUrl);
			con = (HttpURLConnection) url.openConnection();
			if(con != null){
				con.addRequestProperty("Host", "secure.verycd.com");
				con.addRequestProperty("Cookie", cookie);
				con.addRequestProperty("Content-Type",
						"application/x-www-form-urlencoded");
//				setGZipOn(con);
				HttpURLConnection.setFollowRedirects(false);
				con.setInstanceFollowRedirects(false);
				
//				StringBuilder b = readBackInfo(con);
				String key = null;
				for(int i = 1;(key=con.getHeaderFieldKey(i))!=null ;i++){
					if(key.toUpperCase().startsWith("location".toUpperCase())){
						return con.getHeaderField(i);
					}
				}
			}
		}catch(Exception e){
			logger.error(e.getMessage());
			if(con != null){
				con.setConnectTimeout(1);
			}
		}finally{
			if(con != null){
				con.disconnect();
			}
		}
		return "";
	}
	private static String getCookie(String pageUrl,User user){
		HttpURLConnection con = null;
		try{
			URL url = new URL(pageUrl);
			con = (HttpURLConnection) url.openConnection();
			if(con != null){
				con.addRequestProperty("Host", user.getHost());
				con.addRequestProperty("Content-Type",
						"application/x-www-form-urlencoded");
				con.setRequestProperty("Cookie", user.getCookie());
//				setGZipOn(con);
				HttpURLConnection.setFollowRedirects(false);
				con.setInstanceFollowRedirects(false);
				
				StringBuffer sb = new StringBuffer();
				String key = null;
				for(int i = 1;(key=con.getHeaderFieldKey(i))!=null ;i++){
					String value = con.getHeaderField(i);
					if (key.startsWith("Set-Cookie")) {
						if (!(value.contains("=deleted;"))) {
							int index = value.indexOf(";");
							if (index > 0)
								sb.append(value.substring(0, index + 1));
						}
					} else if ((key.startsWith("location:"))
							&& (value.contains("error_code"))) {
						return null;
					}
				}
				return sb.toString();
			}
		}catch(Exception e){
			logger.error(e.getMessage());
			if(con != null){
				con.setConnectTimeout(1);
			}
		}finally{
			if(con != null){
				con.disconnect();
			}
		}
		
		return "";
	}
}
