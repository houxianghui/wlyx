package com.blue.start;

import java.util.Iterator;
import java.util.Scanner;

import com.blue.common.User;
import com.blue.monitor.FreeItemMonitor;
import com.blue.monstor.Attack;

public class SearchMain {
	public static void main(String[] args) throws Exception {
		User user = new User();
		System.out.println("�������û���");
		Scanner scan = new Scanner(System.in);
		String s = scan.next();
		System.out.println("����������");
		String pwd = scan.next();
		System.out.println("���������ô���");
		String time = scan.next();

		int t = Integer.parseInt(time);
		user.setUserName(s);
		user.setPassword(pwd);
		user.login(false);
		FreeItemMonitor.search(user, t);
	}
}
