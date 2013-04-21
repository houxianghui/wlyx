package com.blue.start;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import com.blue.common.User;
import com.blue.tools.Tools;


public class Main implements Start{
	public void run() throws Exception {
		Tools.start();
	}
	
	public static void main(String[] args) throws Exception {
		Main m = new Main();
		if (System.getProperty("window") != null) {
			com.blue.frame.TestSystemTray.startWithFrame(m);
		} else {
			m.run();
		}
	}

	public static List<User> getUserInfo() throws Exception, IOException {
		UserRead ur = new UserRead();
		return ur.readUser();
	}

	public static String readText(String fileName) throws Exception {
		File f = new File(fileName);
		FileReader fr = new FileReader(f);
		StringBuffer sb = new StringBuffer();
		BufferedReader br = new BufferedReader(fr);

		String s = null;
		while ((s = br.readLine()) != null) {
			sb.append(s + "\n");
		}
		br.close();
		return sb.toString();
	}
}
