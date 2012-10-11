package com.blue.tools;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.URLEncoder;

import com.blue.common.User;

/*
 * 基础知识，项目中不再使用
 */
public class CookieManage {
	public static String getCookie(User user)throws Exception{
		String s = login(user);
		if(s == null){
			return null;
		}
		String url = xuanQu(s);
		if(url == null){
			return null;
		}
		return getLogin(url);
	}
	public static String login(User user)throws Exception{
		Socket socket = new Socket("secure.verycd.com", 80);
		
		try {
			String line;
			StringBuilder sb = new StringBuilder();
			sb.append("POST /signin?f=out HTTP/1.1\r\n");
			sb.append("Host: secure.verycd.com\r\n");
			sb
					.append("User-Agent: Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.1.3) Gecko/20090824 Firefox/3.5.3\r\n");
			sb
					.append("Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8\r\n");
			sb.append("Accept-Language: zh-cn,zh;q=0.5\r\n");
			sb.append("Accept-Encoding: gzip,deflate\r\n");
			sb.append("Accept-Charset: GB2312,utf-8;q=0.7,*;q=0.7\r\n");
			sb
					.append("Referer: http://secure.verycd.com/3rdServices/50hero2\r\n");
			sb.append("Content-Type: application/x-www-form-urlencoded\r\n");
			String data = "ru=http%3A%2F%2Fsecure.verycd.com%2F3rdServices%2F50hero&login_submit=%E7%99%BB%E5%BD%95&username="
					+ URLEncoder.encode(user.getUserName(), "UTF-8")
					+ "&password="
					+ URLEncoder.encode(user.getPassword(), "UTF-8");
			sb.append("Content-Length: " + data.getBytes("UTF-8").length
					+ "\r\n");
			sb.append("\r\n");
			sb.append(data);
			OutputStream os = socket.getOutputStream();
			os.write(sb.toString().getBytes("UTF-8"));
			// 请求
			os.flush();
			// 返回
			InputStream is = socket.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					is, "UTF-8"));

			StringBuilder cookieSb = new StringBuilder();

			while ((line = reader.readLine()) != null) {
				if (line.startsWith("Set-Cookie:")) {
					if (!(line.contains("=deleted;"))) {
						int index = line.indexOf(";");
						if (index > 12)
							cookieSb.append(line.substring(12, index + 1));
					}
				} else if ((line.startsWith("location:"))
						&& (line.contains("error_code"))) {
					return null;
				}
			}

			is.close();
			reader.close();
			return cookieSb.toString();
		} finally {
			socket.close();
		}
	}
	private static String xuanQu(String cookie) throws Exception {
		Socket socket = new Socket("secure.verycd.com", 80);
		try {
			String line;
			StringBuilder sb = new StringBuilder();

			sb.append("GET /signin?ak=50hero&sid="
					+ "s4.verycd.9wee.com"
					+ " HTTP/1.1\r\n");
			sb.append("Host: secure.verycd.com\r\n");
			sb
					.append("User-Agent: Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.1.3) Gecko/20090824 Firefox/3.5.3\r\n");
			sb
					.append("Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8\r\n");
			sb.append("Accept-Language: zh-cn,zh;q=0.5\r\n");
			sb.append("Accept-Encoding: gzip,deflate\r\n");
			sb.append("Accept-Charset: GB2312,utf-8;q=0.7,*;q=0.7\r\n");
			sb.append("Referer: http://game.verycd.com/hero/\r\n");
			
			sb.append("Cookie: " + cookie + "\r\n");
			sb.append("\r\n");
			
			OutputStream os = socket.getOutputStream();
			os.write(sb.toString().getBytes("UTF-8"));
			os.flush();
			InputStream is = socket.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					is, "UTF-8"));

			while ((line = reader.readLine()) != null) {
				if (line.startsWith("location:")) {
					String str1 = line.substring(10);
					return str1;
				}
			}
			is.close();
			reader.close();
			return null;
		} finally {
			socket.close();
		}
	}
	private static String getLogin(String url) throws Exception {
		Socket socket = new Socket("s4.verycd.9wee.com", 80);
		try {
			String line;
			StringBuilder sb = new StringBuilder();
			sb.append("GET " + url + " HTTP/1.1\r\n");
			sb.append("Host: " + "s4.verycd.9wee.com" + "\r\n");
			sb.append("User-Agent: Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.1.3) Gecko/20090824 Firefox/3.5.3\r\n");
			sb.append("Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8\r\n");
			sb.append("Accept-Language: zh-cn,zh;q=0.5\r\n");
			sb.append("Accept-Encoding: gzip,deflate\r\n");
			sb.append("Accept-Charset: GB2312,utf-8;q=0.7,*;q=0.7\r\n");
			sb.append("Referer: http://game.verycd.com/hero_xuanqu.html\r\n");
			sb.append("\r\n\r\n");
			
			OutputStream os = socket.getOutputStream();
			os.write(sb.toString().getBytes("UTF-8"));
			os.flush();
			InputStream is = socket.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					is, "UTF-8"));

			StringBuilder cookieSb = new StringBuilder();

			while ((line = reader.readLine()) != null) {
				if (line.length() == 0) {
					break;
				}
				if (line.startsWith("Set-Cookie:")) {
					int index = line.indexOf(";");
					if (index > 12) {
						cookieSb.append(line.substring(12, index + 1));
					}
				}
			}
			is.close();
			reader.close();
			return cookieSb.toString();
		} finally {
			socket.close();
		}
	}
}
