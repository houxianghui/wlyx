package com.start;

import java.util.Scanner;

import com.blue.common.User;
import com.blue.huanjing.HuanJing;

public class GetSpecial {
	public static void main(String[] args)throws Exception {
		System.out.println("�����¼�û���");
		Scanner in = new Scanner(System.in);
		String name = in.next();
		System.out.println("��������");
		String pwd = in.next();
		User user = new User();
		user.setUserName(name);
		user.setPassword(pwd);
		user.login(false);
		System.out.println("���뿪ʼ���");
		String start = in.next();
		
		HuanJing.listSpecail(user, Integer.parseInt(start));
		
	}
}
