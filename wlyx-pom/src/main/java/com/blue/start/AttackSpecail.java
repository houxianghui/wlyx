package com.blue.start;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import com.blue.common.User;
import com.blue.huanjing.HuanJing;

public class AttackSpecail {
	public static void main(String[] args) throws Exception {

		List<User> l = Main.getUserInfo();
		System.out.println("«Î ‰»Î”√ªß√˚");
		Scanner scan = new Scanner(System.in);
		String s = scan.next();
		String[] users = s.split(",");
		Iterator<User> it = l.iterator();
		while (it.hasNext()) {
			final User u = it.next();
			for (int i = 0; i < users.length; i++) {
				if (u.getUserName().equals(users[i])) {
					u.login(false);
					System.out.println("π•ª˜±‡∫≈");
					Scanner in = new Scanner(System.in);
					while (true) {
						System.out.println("π•ª˜±‡∫≈");
						String id = in.next();
						HuanJing.attack(u, id);
					}
				}
			}
		}
	}
}
