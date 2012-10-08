package com.blue.start;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import com.blue.common.User;
import com.blue.huanjing.HuanJing;

public class GetSpecial {
	public static void main(String[] args) throws Exception {
		List<User> l = Main.getUserInfo();
		while (true) {
			System.out.println("请输入用户名");
			Scanner scan = new Scanner(System.in);
			String s = scan.next();
			String[] users = s.split(",");
			Iterator<User> it = l.iterator();
			while (it.hasNext()) {
				final User u = it.next();
				for (int i = 0; i < users.length; i++) {
					if (u.getUserName().equals(users[i])) {
						u.login(false);
						Scanner in = new Scanner(System.in);
						System.out.println("输入开始编号");
						String start = in.next();
						HuanJing.listSpecail(u, Integer.parseInt(start));
					}
				}
			}
		}

	}
}
