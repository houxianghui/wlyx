package com.start;

import java.util.Scanner;

import com.blue.common.User;
import com.blue.huanjing.HuanJing;

public class AttackSpecail {
	public static void main(String[] args) throws Exception{
		System.out.println("�����¼�û���");
		Scanner in = new Scanner(System.in);
		String name = in.next();
		System.out.println("��������");
		String pwd = in.next();
		User user = new User();
		user.setUserName(name);
		user.setPassword(pwd);
		user.login(false);
		while(true){
			System.out.println("�������");
			String id = in.next();
			HuanJing.attack(user, id);
		}
	}
}
