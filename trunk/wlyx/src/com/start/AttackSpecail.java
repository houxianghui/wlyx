package com.start;

import java.util.Scanner;

import com.blue.common.User;
import com.blue.huanjing.HuanJing;

public class AttackSpecail {
	public static void main(String[] args) throws Exception{
		System.out.println("ÊäÈëµÇÂ¼ÓÃ»§Ãû");
		Scanner in = new Scanner(System.in);
		String name = in.next();
		System.out.println("ÊäÈëÃÜÂë");
		String pwd = in.next();
		User user = new User();
		user.setUserName(name);
		user.setPassword(pwd);
		user.login(false);
		while(true){
			System.out.println("¹¥»÷±àºÅ");
			String id = in.next();
			HuanJing.attack(user, id);
		}
	}
}
