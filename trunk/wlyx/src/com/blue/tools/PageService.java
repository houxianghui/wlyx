package com.blue.tools;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;

import com.blue.common.User;

public class PageService {
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
		} catch (FileNotFoundException ex) {
			System.out.println("NOT FOUND:" + page);
			return null;
		} catch (ConnectException ex) {
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (is != null)
					is.close();
			} catch (Exception localException3) {
			}
		}
		return null;
	}

	public static String postPage(String page, String data,User user)
			throws Exception {
		String line;
		URL url = new URL(page);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setDoOutput(true);
		con.setRequestMethod("POST");
		con.addRequestProperty("Host", "s4.verycd.9wee.com");
		con.addRequestProperty("Content-Type",
				"application/x-www-form-urlencoded");
		con.addRequestProperty("Cookie", user.getCookie());
		OutputStream os = con.getOutputStream();
		os.write(data.getBytes("UTF-8"));
		os.flush();
		os.close();
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				con.getInputStream(), "UTF-8"));

		StringBuilder b = new StringBuilder();
		while ((line = reader.readLine()) != null) {
			b.append(line);
			System.out.println(line);
		}
		reader.close();
		return b.toString();
	}
}
