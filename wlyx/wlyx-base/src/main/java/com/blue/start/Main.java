package com.blue.start;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import com.blue.common.User;
import com.blue.monitor.RolesMonitor;
import com.blue.monstor.UserMonitor;


public class Main implements Start{
	@Override
	public void run() throws Exception {
		start();
	}
	public static void main(String[] args) throws Exception {
		Main m = new Main();
		if (System.getProperty("window") != null) {
			com.blue.frame.TestSystemTray.startWithFrame(m);
		} else {
			m.start();
		}
	}

	public void start() throws Exception {
		System.setProperty("sun.net.client.defaultConnectTimeout", "15000");
		System.setProperty("sun.net.client.defaultReadTimeout", "15000");
		System.setProperty("GZIP", "");

		UserRead ur = new UserRead();
		List<User> l = ur.readUser();
		RolesMonitor rm = RolesMonitor.getInstance();
		rm.setUsers(l);
		Iterator<User> it = l.iterator();
		while (it.hasNext()) {
			final User user = it.next();
			
			new Thread(){
				public void run() {
					try {
						user.login(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				};
			}.start();
			
//			Thread.sleep(3 * 1000);
		}
		new UserMonitor(l);
		
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
