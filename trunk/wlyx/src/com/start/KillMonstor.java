package com.start;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import com.blue.common.User;
import com.blue.monstor.Attack;

public class KillMonstor {
	public static void main(String[] args) throws Exception{
		List<User> l = Main.getUserInfo();
		System.out.println("请手动移动要怪物地点后输入用户名");
		while(true){
			System.out.println("请输入用户名");
			Scanner scan = new Scanner(System.in);
			String s = scan.next();
			System.out.println("请输入攻击次数");
			String time = scan.next();
			System.out.println("请输入怪物编号");
			String mid = scan.next();
			int t = Integer.parseInt(time);
			String[] users = s.split(",");
			Iterator<User> it = l.iterator();
			while(it.hasNext()){
				User u = it.next();
				for(int i = 0;i < users.length;i++){
					if(u.getUserName().equals(users[i])){
						u.login(false);
						for(int j =0;j<t;j++){
							boolean b = false;
							do{
								b =	Attack.attack(u,mid);
							}while(!b);
							System.out.println((j+1)+"次攻击完成");
						}
					}
				}
				
			}
		}
	}
}
