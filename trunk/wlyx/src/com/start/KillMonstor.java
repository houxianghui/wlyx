package com.start;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import com.blue.common.User;
import com.blue.monstor.Attack;

public class KillMonstor {
	public static void main(String[] args) throws Exception{
		List<User> l = Main.getUserInfo();
		System.out.println("���ֶ��ƶ�Ҫ����ص�������û���");
		while(true){
			System.out.println("�������û���");
			Scanner scan = new Scanner(System.in);
			String s = scan.next();
			System.out.println("�����빥������");
			String time = scan.next();
			System.out.println("�����������");
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
							System.out.println((j+1)+"�ι������");
						}
					}
				}
				
			}
		}
	}
}
