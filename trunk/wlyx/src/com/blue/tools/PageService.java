package com.blue.tools;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.blue.common.User;

public class PageService {
	private static Logger logger = Logger.getLogger(PageService.class);
	public static String getPageWithCookie(String page, User user) {
		String str = null;
		int count = 3;
		do {
			str = _getPageWithCookie(page, user);
			if ((str != null) && (str.length() != 0))
				continue;
			try {
				Thread.sleep(1000L);
			} catch (InterruptedException e) {
				e.printStackTrace();
				logger.error(user.getRoleName()+" "+e.getMessage());
			}
		} while ((str == null) && (count-- > 0));
		return str;

	}

	private static String _getPageWithCookie(String page, User user) {
		InputStream is = null;
		try {
			String line;
			URL url = new URL(page);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			
			if(con != null){
				if (user.getCookie() != null) {
					con.addRequestProperty("Cookie", user.getCookie());
				}
				con.setRequestProperty("connection", "none");
				HttpURLConnection.setFollowRedirects(false);
				is = con.getInputStream();
				BufferedReader reader = new BufferedReader(new InputStreamReader(
						is, "UTF-8"));
				StringBuilder b = new StringBuilder();
	
				while ((line = reader.readLine()) != null) {
					b.append(line);
					b.append("\r\n");
				}
				return b.toString();
			}
		} catch (Exception ex) {
			logger.error(user.getRoleName()+" "+ex.getMessage());
		} finally {
			try {
				if (is != null)
					is.close();
			} catch (Exception e) {
				logger.error(user.getRoleName()+" "+e.getMessage());
			}
		}
		return "";
	}

	public static String postPage(String page, String data, User user)
			 {
		String line;
		HttpURLConnection con = null;
		try{
			URL url = new URL(page);
			con = (HttpURLConnection) url.openConnection();
			if(con != null){
				con.setDoOutput(true);
				con.setRequestMethod("POST");
				con.addRequestProperty("Host", "s4.verycd.9wee.com");
				con.addRequestProperty("Content-Type",
						"application/x-www-form-urlencoded");
				if (user != null) {
					con.addRequestProperty("Cookie", user.getCookie());
				}
				OutputStream os = con.getOutputStream();
				os.write(data.getBytes("UTF-8"));
				os.flush();
				os.close();
		
				BufferedReader reader = new BufferedReader(new InputStreamReader(
						con.getInputStream(), "UTF-8"));
		
				StringBuilder b = new StringBuilder();
				while ((line = reader.readLine()) != null) {
					b.append(line);
				}
				reader.close();
				return b.toString();
			}
		}catch(Exception e){
			logger.error(e.getMessage());
		}finally{
			if(con != null){
				con.disconnect();
			}
		}
		return "";
	}


	public static String getPage(String pageUrl, User user)  {
		HttpURLConnection con = null;
		try{
			String line;
			URL url = new URL(pageUrl);
			con = (HttpURLConnection) url.openConnection();
			if(con != null){
				con.addRequestProperty("Host", "s4.verycd.9wee.com");
				if (user != null) {
					con.addRequestProperty("Cookie", user.getCookie());
				}
				con.addRequestProperty("Content-Type",
						"application/x-www-form-urlencoded");
				BufferedReader reader = new BufferedReader(new InputStreamReader(
						con.getInputStream(), "UTF-8"));
				StringBuilder b = new StringBuilder();
				while ((line = reader.readLine()) != null) {
					b.append(line);
				}
				reader.close();
				return b.toString();
			}
		}catch(Exception e){
			logger.error(user.getRoleName()+e.getMessage());
		}finally{
			if(con != null){
				con.disconnect();
			}
		}
		return "";
	}

	public static void displayMap(Map m) {
		Set<String> s = m.keySet();
		Iterator<String> it = s.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
		}
	}

	public static void login( User user){
		HttpURLConnection con = null;
		try{
			URL url = new URL("http://secure.verycd.com/signin?f=out");
			con = (HttpURLConnection) url.openConnection();
			String data = "ru=http%3A%2F%2Fsecure.verycd.com%2F3rdServices%2F50hero&login_submit=%E7%99%BB%E5%BD%95&" +
					"username=" + URLEncoder.encode(user.getUserName(),"utf-8")+
					"&password=" + URLEncoder.encode(user.getPassword(),"utf-8")+
					"&_REFERER=";
			if(con != null){
				con.setDoOutput(true);
				
				con.setRequestMethod("POST");
				con.addRequestProperty("Host", "secure.verycd.com");
				con.addRequestProperty("Content-Type",
						"application/x-www-form-urlencoded");
				con.addRequestProperty("Referer", "http://secure.verycd.com/3rdServices/50hero");
				HttpURLConnection.setFollowRedirects(false);
				con.setInstanceFollowRedirects(false);
				
				OutputStream os = con.getOutputStream();
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
				
				String nextUrl = getLogin("http://secure.verycd.com/signin?ak=50hero&sid=s4.verycd.9wee.com", sb.toString());
				String cookie = getCookie(nextUrl);
				user.setCookie(cookie);
			}
		}catch(Exception e){
			logger.info(user.getUserName()+e.getMessage());
		}finally{
			if(con != null){
				con.disconnect();
			}
		}
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
				HttpURLConnection.setFollowRedirects(false);
				con.setInstanceFollowRedirects(false);
				String line = null;
				BufferedReader reader = new BufferedReader(new InputStreamReader(
						con.getInputStream(), "UTF-8"));
				StringBuilder b = new StringBuilder();
				while ((line = reader.readLine()) != null) {
					b.append(line);
				}
				reader.close();
				String key = null;
				for(int i = 1;(key=con.getHeaderFieldKey(i))!=null ;i++){
					if(key.startsWith("location")){
						return con.getHeaderField(i);
					}
				}
			}
		}catch(Exception e){
			logger.error(e.getMessage());
		}finally{
			if(con != null){
				con.disconnect();
			}
		}
		return "";
	}
	private static String getCookie(String pageUrl){
		HttpURLConnection con = null;
		try{
			URL url = new URL(pageUrl);
			con = (HttpURLConnection) url.openConnection();
			if(con != null){
				con.addRequestProperty("Host", "s4.verycd.9wee.com");
				con.addRequestProperty("Content-Type",
						"application/x-www-form-urlencoded");
				HttpURLConnection.setFollowRedirects(false);
				con.setInstanceFollowRedirects(false);
				String line = null;
				BufferedReader reader = new BufferedReader(new InputStreamReader(
						con.getInputStream(), "UTF-8"));
				StringBuilder b = new StringBuilder();
				while ((line = reader.readLine()) != null) {
					b.append(line);
				}
				reader.close();
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
		}finally{
			if(con != null){
				con.disconnect();
			}
		}
		
		return "";
	}
}
