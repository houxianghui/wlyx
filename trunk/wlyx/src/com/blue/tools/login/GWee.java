package com.blue.tools.login;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.apache.log4j.Logger;

import com.blue.common.User;
import com.blue.tools.PageService;

public class GWee {
	private static final String VERIFY_SERVER = "passport.9wee.com";
	private static Logger logger = Logger.getLogger(GWee.class);
	public static void login( User user){
//		if(PageService.readCookie(user)){
//			return;
//		}
		HttpURLConnection con = null;
		try{
			URL url = new URL("https://"+VERIFY_SERVER+"/login");
			con = (HttpURLConnection) url.openConnection();
			String data = "username=" + URLEncoder.encode(user.getUserName(),"utf-8")+
					"&password=" + URLEncoder.encode(user.getPassword(),"utf-8")+"&x=20&y=16";
			if(con != null){
				con.setDoOutput(true);
				
				con.setRequestMethod("POST");
				con.addRequestProperty("Host", "passport.9wee.com");
				con.addRequestProperty("Content-Type",
						"application/x-www-form-urlencoded");
				con.addRequestProperty("Referer", "http://hero.9wee.com/");
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
							if (index > 0){
//								if(value.indexOf("weeCookie")!=-1)
								sb.append(value.substring(0, index + 1));
							}
						}
					} else if ((key.startsWith("location:"))
							&& (value.contains("error_code"))) {
						logger.error("µÇÂ½Ê§°Ü");
						return ;
					}
				}
				
				String nextUrl = getLogin("http://"+user.getHost()+"/passport.php?act=login&referer=/&from=http://hero.9wee.com", sb,user);
				nextUrl = getLogin(nextUrl,sb,user);
				String cookie = getCookie(nextUrl,user,sb.toString());
				user.setCookie(cookie);
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
	private static String getLogin(String pageUrl, StringBuffer cookie,User user){
		HttpURLConnection con = null;
		String backUrl = "";
		try{
			URL url = new URL(pageUrl);
			con = (HttpURLConnection) url.openConnection();
			if(con != null){
				con.addRequestProperty("Host", user.getHost());
				con.addRequestProperty("Cookie", cookie.toString());
				con.addRequestProperty("Content-Type",
						"application/x-www-form-urlencoded");
//				setGZipOn(con);
				HttpURLConnection.setFollowRedirects(false);
				con.setInstanceFollowRedirects(false);
				
//				StringBuilder b = readBackInfo(con);
				String key = null;
				for(int i = 1;(key=con.getHeaderFieldKey(i))!=null ;i++){
					if(key.toLowerCase().startsWith("location")){
						backUrl = con.getHeaderField(i);
					}
					String value = con.getHeaderField(i);
					if (key.startsWith("Set-Cookie")) {
						if (!(value.contains("=deleted;"))) {
							int index = value.indexOf(";");
							if (index > 0)
								cookie.append(value.substring(0, index + 1));
						}
					} else if ((key.startsWith("location:"))
							&& (value.contains("error_code"))) {
						
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
		return backUrl;
	}
	private static String getCookie(String pageUrl,User user,String cookie){
		HttpURLConnection con = null;
		try{
			URL url = new URL(pageUrl);
			con = (HttpURLConnection) url.openConnection();
			if(con != null){
				con.addRequestProperty("Host", user.getHost());
//				con.addRequestProperty("Content-Type","application/x-www-form-urlencoded");
				con.addRequestProperty("Cookie", cookie);
				con.addRequestProperty("Accept", "application/x-ms-application, image/jpeg, application/xaml+xml, image/gif, image/pjpeg, application/x-ms-xbap, application/vnd.ms-excel, application/vnd.ms-powerpoint, application/msword, */*");
				con.addRequestProperty("Accept-Language", "zh-CN");
				con.addRequestProperty("Connection", "Keep-Alive");
				con.addRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1; WOW64; Trident/4.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; MDDC; Media Center PC 6.0; InfoPath.2; .NET4.0C)");
				PageService.setGZipOn(con);
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
