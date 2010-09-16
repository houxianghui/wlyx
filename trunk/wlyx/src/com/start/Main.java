package com.start;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import com.blue.common.User;
import com.blue.monstor.UserMonitor;

public class Main {
	public static void main(String[] args)throws Exception {
		
		System.setProperty("sun.net.client.defaultConnectTimeout", "60000");
		System.setProperty("sun.net.client.defaultReadTimeout","60000");
		UserRead ur = new UserRead();
		List<User> l = ur.readUser();
	
		Iterator<User> it = l.iterator();
		while(it.hasNext()){
			User user = it.next();
			user.login(true);
			Thread.sleep(3*1000);
		}
		new UserMonitor(l);
	
	}
	public static List<User> getUserInfo() throws Exception, IOException {
		UserRead ur = new UserRead();
		return ur.readUser();
	}
	public static String readText(String fileName)throws Exception{
		File f = new File(fileName);
		FileReader fr = new FileReader(f);
		StringBuffer sb = new StringBuffer();
		BufferedReader br = new BufferedReader(fr);
		
		String s = null;
		while((s=br.readLine())!=null){
			sb.append(s+"\n");
		}
		return sb.toString();
	}	
}
