package com.start;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import com.blue.common.User;
import com.blue.monstor.Monstor;

public class CheckUser {
	public static void main(String[] args)throws Exception {
		List<User> l = Main.getUserInfo();
		while(true){
			System.out.println("�������û���������û���,�ָ�");
			Scanner scan = new Scanner(System.in);
			String s = scan.next();
			String[] users = s.split(",");
			Iterator<User> it = l.iterator();
			while(it.hasNext()){
				User u = it.next();
				for(int i = 0;i < users.length;i++){
					if(u.getUserName().equals(users[i])){
						u.login(false);
						Monstor.checkAndSell(u);
					}
				}
			}
		}
	}
}