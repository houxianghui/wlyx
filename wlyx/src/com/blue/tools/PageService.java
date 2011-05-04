package com.blue.tools;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.zip.GZIPInputStream;

import org.apache.log4j.Logger;

import com.blue.common.User;
import com.blue.tools.login.GWee;
import com.blue.tools.login.VeryCD;

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
				Thread.sleep(5000L);
			} catch (InterruptedException e) {
				e.printStackTrace();
				logger.error(user.getRoleName()+" "+e.getMessage());
			}
		} while ((str == null) && (count-- > 0));
		return str;

	}

	private static String _getPageWithCookie(String page, User user) {
		HttpURLConnection con = null;
		try {
	
			URL url = new URL(page);
			con = (HttpURLConnection) url.openConnection();
			
			if(con != null){
				if (user.getCookie() != null) {
					con.addRequestProperty("Cookie", user.getCookie());
				}
				con.setRequestProperty("connection", "none");
				setGZipOn(con);
				HttpURLConnection.setFollowRedirects(false);

				StringBuffer b = readBackInfo(con);
				return b.toString();
			}
		} catch (Exception ex) {
			logger.error(user.getRoleName()+" "+ex.getMessage());
			try{
				Thread.sleep(5000);
			}catch(Exception e){}
		} finally {
			if(con != null){
				con.disconnect();
			}
		}
		return "";
	}

	public static String postPage(String page, String data, User user)
			 {
		HttpURLConnection con = null;
		try{
			URL url = new URL(page);
			con = (HttpURLConnection) url.openConnection();
			if(con != null){
				con.setDoOutput(true);
				con.setRequestMethod("POST");
				con.addRequestProperty("Host", user.getHost());
				con.addRequestProperty("Content-Type",
						"application/x-www-form-urlencoded");
				setGZipOn(con);
				if (user != null) {
					con.addRequestProperty("Cookie", user.getCookie());
				}
				
				OutputStream os = con.getOutputStream();
				os.write(data.getBytes("UTF-8"));
				os.flush();
				os.close();
				StringBuffer b = readBackInfo(con);
				return b.toString();
			}
		}catch(Exception e){
			logger.error(e.getMessage());
			try{
				Thread.sleep(5000);
			}catch(Exception ex){}
		}finally{
			if(con != null){
				con.disconnect();
			}
		}
		return "";
	}

	private static StringBuffer readBackInfo(HttpURLConnection con)
			throws IOException, UnsupportedEncodingException {
		String gzip = System.getProperty("GZIP");
		boolean isGzipOn = false;
		if(gzip != null){
			isGzipOn = true;
		}
		if(isGzipOn){
			GZIPInputStream gis = new GZIPInputStream(con.getInputStream());
	
			StringBuffer b = new StringBuffer();
			byte[] by = new byte[1024];
			int len = 0;
			while((len = gis.read(by))!=-1){
				b.append(new String(by,0,len,"UTF-8"));
			}
			gis.close();
			return b;
		}else{
			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(),"UTF-8"));
			StringBuffer sb = new StringBuffer();
			String s = null;
			while((s=br.readLine())!=null){
				sb.append(s+"\n");
			}
			br.close();
			return sb;
		}
		
	}


	public static String getPage(String pageUrl, User user)  {
		HttpURLConnection con = null;
		try{
			URL url = new URL(pageUrl);
			con = (HttpURLConnection) url.openConnection();
			if(con != null){
				con.addRequestProperty("Host", user.getHost());
				if (user != null) {
					con.addRequestProperty("Cookie", user.getCookie());
				}
				con.addRequestProperty("Content-Type",
						"application/x-www-form-urlencoded");
				setGZipOn(con);
				
				StringBuffer b = readBackInfo(con);
				return b.toString();
			}
		}catch(Exception e){
			logger.error(user.getRoleName()+e.getMessage());
			try{
				Thread.sleep(5000);
			}catch(Exception ex){}
		}finally{
			if(con != null){
				con.disconnect();
			}
		}
		return "";
	}

	public static void setGZipOn(HttpURLConnection con) {
		con.addRequestProperty("Accept-Encoding", "gzip,deflate");
		con.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.1.3) Gecko/20090824 Firefox/3.5.3");
	}

	public static void displayMap(Map m) {
		Set<String> s = m.keySet();
		Iterator<String> it = s.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
		}
	}
	
	public static boolean readCookie(User user){
		BufferedReader br = null;
		try{
			br = new BufferedReader(new FileReader("cookies/"+user.getUserName()+"."+user.getHost()+".ck"));
			String s = null;
			user.setCookie("");
			while((s=br.readLine()) != null){
				user.setCookie(user.getCookie()+s);
			}
			return true;
		}catch(Exception e){
			logger.info("没找到"+user.getUserName()+"的cookie文件，开始以普通方式登录");
		}finally{
			if(br != null)
				try{
					br.close();
				}catch(Exception e){}
		}
		return false;
	}
	public static void setCookie(User user){
		File f = new File("cookies");
		if(!f.exists()){
			f.mkdirs();
		}		
		BufferedWriter bw = null;
		try{
			bw = new BufferedWriter(new FileWriter("cookies/"+user.getUserName()+"."+user.getHost()+".ck"));
			bw.write(user.getCookie());
		}catch(Exception e){
			logger.info(user.getUserName()+"write cookie fail");
		}finally{
			if(bw != null){
				try{
					bw.close();
				}catch(Exception e){}
			}
		}
	}
	public static void login(User user){
		String host = user.getHost();
		if(host.indexOf("verycd") != -1){
			VeryCD.login(user);
			return;
		}
		GWee.login(user);
	}
}
