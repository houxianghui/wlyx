/**
 * 50级国家礼包兑换
 */
package com.blue.start;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import com.blue.common.User;

public class CountryChangeMain {
	public static void main(String[] args) throws Exception {
		List<User> l = Main.getUserInfo();
		while (true) {
			System.out.println("请输入用户名，多个用户用,分割");
			Scanner scan = new Scanner(System.in);
			String s = scan.next();
			String[] users = s.split(",");
			Iterator<User> it = l.iterator();
			while (it.hasNext()) {
				final User u = it.next();
				for (int i = 0; i < users.length; i++) {
					if (u.getUserName().equals(users[i])) {
						u.login(false);
						new Thread() {
							public void run() {
								com.blue.daily.CountryChange.batchChange(u);
							};
						}.start();
						// Monstor.checkAndSell(u);
					}
				}
			}
		}
	}
}
