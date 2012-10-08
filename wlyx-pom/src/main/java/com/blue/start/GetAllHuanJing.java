package com.blue.start;

import java.util.Scanner;

import com.blue.common.User;
import com.blue.huanjing.HuanJing;

public class GetAllHuanJing {
	public static void main(String[] args) throws Exception {
		System.out.println("输入登录用户名");
		Scanner in = new Scanner(System.in);
		String name = in.next();
		System.out.println("输入密码");
		String pwd = in.next();
		User user = new User();
		user.setUserName(name);
		user.setPassword(pwd);
		user.login(false);

		HuanJing.listAll(user);

	}
}
