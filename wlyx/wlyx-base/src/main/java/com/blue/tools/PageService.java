package com.blue.tools;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;

import org.apache.http.Header;
import org.apache.http.HeaderIterator;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.ProtocolVersion;
import org.apache.http.RequestLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.blue.common.User;
import com.blue.tools.login.GWee;
import com.blue.tools.login.VeryCD;

public class PageService {
	
	private static Logger logger = Logger.getLogger(PageService.class);
	private static String CHECK_STOCK_PWD = "modules/warrior.php?act=hall&op=check_pwd";
	
	public static String getPageWithCookie(String page, User user) {
		String str = null;
		int count = 3;
		do {
			str = _getPageWithCookie(page, user);
			if ((str != null) && (str.length() != 0))
				break;
			try {
				Thread.sleep(5000L);
			} catch (InterruptedException e) {
				
				logger.error(user.getRoleName()+" "+e.getMessage());
				if(logger.isDebugEnabled()){
					logger.error(e);
				}
			}
		} while ((str == null) && (count-- > 0));
		return str;

	}
	public static boolean setStocktCookie(User user){
		HttpClient client = user.getClient();
		
		try{
			HttpPost post = new HttpPost(user.getUrl()+CHECK_STOCK_PWD+Tools.getTimeStamp(true));
			post.setEntity(new StringEntity("pwd="+user.getStockPwd()+"&callback_func_name=callback_submit_form_check_pwd&callback_obj_name=dlg_check_pwd"));
			HttpResponse response = client.execute(post);
			String s = EntityUtils.toString(response.getEntity(),"utf-8");
			Pattern p = Pattern.compile("");
			Matcher m = p.matcher(s);
			if(!m.find()){
				logger.info(user.getRoleName()+"仓库密码错误，不进行仓库合并操作");
				return false;
			}
			return true;
		}catch(Exception e){
			return false;
		}
		
//		String page = user.getUrl()+CHECK_STOCK_PWD+Tools.getTimeStamp(true);
//		Pattern p = Pattern.compile("");
//		String data = "pwd="+user.getStockPwd()+"&callback_func_name=callback_submit_form_check_pwd&callback_obj_name=dlg_check_pwd";
//		String result =  PageService.postPage(page, data, user);
//		Matcher m = p.matcher(result);
//		if(!m.find()){
//			logger.info(user.getRoleName()+"仓库密码错误，不进行仓库合并操作");
//			return false;
//		}
//		HttpURLConnection con = null;
//		InputStream inputStream = null;
//		try {
//	
//			URL url = new URL(page);
//			con = (HttpURLConnection) url.openConnection();
//			
//			if(con != null){
//				if (user.getCookie() != null) {
//					con.addRequestProperty("Cookie", user.getCookie());
//				}
//				con.setRequestMethod("POST");
//				con.setDoOutput(true);
//				con.setRequestProperty("connection", "none");
//				setGZipOn(con);
//				HttpURLConnection.setFollowRedirects(false);
//				OutputStream os = con.getOutputStream();
//				if(os != null){
//					os.write(data.getBytes("UTF-8"));
//					os.flush();
//					os.close();
//				}
//				StringBuffer sb = new StringBuffer("");
//				String key = null;
//				for(int i = 1;(key=con.getHeaderFieldKey(i))!=null ;i++){
//					String value = con.getHeaderField(i);
//					if (key.startsWith("Set-Cookie")) {
//						if (!(value.contains("=deleted;"))) {
//							int index = value.indexOf(";");
//							if (index > 0)
//								sb.append(value.substring(0, index + 1));
//						}
//					} else if ((key.startsWith("location:"))
//							&& (value.contains("error_code"))) {
//						logger.error(user.getRoleName()+"仓库密码错误");
//						return false;
//					}
//				}
//				user.setCookie(sb.toString());
//				return true;
//			}
//		}catch(SocketTimeoutException e){ 
//			if(con != null){
//				con.setConnectTimeout(1);
//			}
//		}catch (Exception ex) {
//			logger.error(user.getRoleName()+" "+ex.getMessage());
//			if(logger.isDebugEnabled()){
//				logger.error(ex);
//			}
//			if(con != null){
//				con.setConnectTimeout(1);
//			}
//		} finally {
//			if(inputStream != null){
//				try{
//					inputStream.close();
//				}catch(Exception e){
//					logger.error(e.getMessage());
//				}
//			}
//			if(con != null){
//				con.disconnect();
//			}
//		}
//		return false;
		
	}
	private static String _getPageWithCookie(String page, User user) {
		HttpClient client = user.getClient();
		try{
			HttpResponse response = client.execute(new HttpGet(page));
			return EntityUtils.toString(response.getEntity(), "utf-8");
		}catch(Exception e){
			return "";
		}
		
	}

	public static String postPage(String page, String data, User user)
			 {
		HttpClient client = user.getClient();
		try{
			HttpPost post = new HttpPost(page);
			post.setEntity(new StringEntity(data));
			HttpResponse response = client.execute(post);
			return EntityUtils.toString(response.getEntity(), "utf-8");
		}catch(Exception e){
			return "";
		}
//		HttpURLConnection con = null;
//		InputStream inputStream = null;
//		try{
//			URL url = new URL(page);
//			con = (HttpURLConnection) url.openConnection();
//			if(con != null){
//				con.setDoOutput(true);
//				con.setRequestMethod("POST");
//				con.addRequestProperty("Host", user.getHost());
//				con.addRequestProperty("Content-Type",
//						"application/x-www-form-urlencoded");
//				setGZipOn(con);
//				if (user != null) {
//					con.addRequestProperty("Cookie", user.getCookie());
//				}
//				
//				OutputStream os = con.getOutputStream();
//				os.write(data.getBytes("UTF-8"));
//				os.flush();
//				os.close();
//				inputStream = con.getInputStream();
//				StringBuffer b = readBackInfo(inputStream);
//				return b.toString();
//			}
//		}catch(SocketTimeoutException e){ 
//			if(con != null){
//				con.setConnectTimeout(1);
//			}
//		}catch(Exception e){
//			logger.error(user.getRoleName()+" "+e.getMessage());
//			if(logger.isDebugEnabled()){
//				logger.error(e);
//			}
//			if(con != null){
//				con.setConnectTimeout(1);
//			}
//		}finally{
//			if(inputStream != null){
//				try{
//				inputStream.close();
//				}catch(Exception e){
//					logger.error(e.getMessage());
//				}
//			}
//			if(con != null){
//				con.disconnect();
//			}
//		}
//		return "";
	}

	private static StringBuffer readBackInfo(InputStream inputStream) throws Exception
			{
		String gzip = System.getProperty("GZIP");
		String charset = "UTF-8";
		String charsetProperty = System.getProperty("charset");
		if(charsetProperty != null){
			charset = charsetProperty;
		}
		boolean isGzipOn = false;
		if(gzip != null){
			isGzipOn = true;
		}
		if(isGzipOn){
			GZIPInputStream gis;
			try {
				gis = new GZIPInputStream(inputStream);
				StringBuffer b = new StringBuffer();
				byte[] by = new byte[1024];
				int len = 0;
				while((len = gis.read(by))!=-1){
					b.append(new String(by,0,len,charset));
				}
				gis.close();
				return b;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		BufferedReader br = new BufferedReader(new InputStreamReader(inputStream,charset));
		StringBuffer sb = new StringBuffer();
		String s = null;
		while((s=br.readLine())!=null){
			sb.append(s+"\n");
		}
		br.close();
		return sb;
	}

	public static String getPage(String pageUrl){
		HttpURLConnection con = null;
		InputStream inputStream = null;
		try{
			URL url = new URL(pageUrl);
			con = (HttpURLConnection) url.openConnection();
			if(con != null){
				
				con.addRequestProperty("Content-Type",
						"application/x-www-form-urlencoded");
				inputStream = con.getInputStream();
				BufferedReader br = new BufferedReader(new InputStreamReader(inputStream,"utf-8"));
				StringBuffer sb = new StringBuffer();
				String s = null;
				while((s=br.readLine())!=null){
					sb.append(s+"\n");
				}
				br.close();
				return sb.toString();
			}
		}catch(SocketTimeoutException e){ 
			if(con != null){
				con.setConnectTimeout(1);
			}
		}catch(Exception e){
			logger.error(e.getMessage());
		}finally{
			if(inputStream != null){
				try{
					inputStream.close();
				}catch(Exception e){
					logger.error(e.getMessage());
				}
			}
			if(con != null){
				con.disconnect();
			}
		}
		return "";
	}
	public static String getPage(String pageUrl, String cookie,User user)  {
		HttpURLConnection con = null;
		InputStream inputStream = null;
		try{
			URL url = new URL(pageUrl);
			con = (HttpURLConnection) url.openConnection();
			if(con != null){
				
				if (cookie != null) {
//					con.addRequestProperty("Host", user.getHost());
					con.addRequestProperty("Cookie", cookie);
				}
				con.addRequestProperty("Content-Type",
						"application/x-www-form-urlencoded");
				setGZipOn(con);
				inputStream = con.getInputStream();
				StringBuffer b = readBackInfo(inputStream);
				return b.toString();
			}
		}catch(SocketTimeoutException e){ 
			if(con != null){
				con.setConnectTimeout(1);
			}
		}catch(Exception e){
			logger.error(user.getRoleName()+" "+e.getMessage());
		}finally{
			if(inputStream != null){
				try{
					inputStream.close();
				}catch(Exception e){
					logger.error(e.getMessage());
				}
			}
			if(con != null){
				con.disconnect();
			}
		}
		return "";
	}

	public static void setGZipOn(HttpURLConnection con) {
		boolean gzipon = System.getProperty("GZIP")!=null;
		if(gzipon){
			con.addRequestProperty("Accept-Encoding", "gzip,deflate");
			con.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.1.3) Gecko/20090824 Firefox/3.5.3");
		}
	}

	public static void displayMap(Map m) {
		Set<String> s = m.keySet();
		Iterator<String> it = s.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
		}
	}
	
	public static boolean readCookie(User user){
		//每周三重新登录
//		if(Tools.getDayOfWeek() == Calendar.WEDNESDAY){
//			return false;
//		}
		BufferedReader br = null;
		try{
			br = new BufferedReader(new FileReader("cookies/"+user.getUserName()+"."+user.getHost()+".ck"));
			String s = null;
			user.setCookie("");
			while((s=br.readLine()) != null){
				user.setCookie(user.getCookie()+s);
			}
			if(Tools.isEmpty(user.getCookie())){
				return false;
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
		if(host.indexOf("tw") != -1){
			return;
		}
		GWee.login(user);
	}
	public static String getCookie(String pageUrl,User user){
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
