package com.start;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.log4j.Logger;

import com.blue.common.User;
import com.blue.tools.ItemMerge;

public class FindItem {
	private static Logger logger = Logger.getLogger(FindItem.class);
	public static void main(String[] args)throws Exception {
		System.setProperty("sun.net.client.defaultConnectTimeout", "15000");
		System.setProperty("sun.net.client.defaultReadTimeout","15000");
		System.setProperty("GZIP","");
		
		List<User> users = Main.getUserInfo();
		for(User user:users){
			user.login(false);
		}
		logger.info("请输入要查找物品名称 ，不同关键词之间以空格分隔");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String key = null;
		while((key=br.readLine()) != null){
			ItemMerge.find(key, users);
		}
	}
}
