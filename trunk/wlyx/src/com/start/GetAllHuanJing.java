package com.start;

import java.util.Iterator;
import java.util.List;

import com.blue.common.User;
import com.blue.huanjing.HuanJing;

public class GetAllHuanJing {
	public static void main(String[] args)throws Exception {
		List<User> l = Main.getUserInfo();
		Iterator<User> it = l.iterator();
		while(it.hasNext()){
			HuanJing.listAll(it.next());
		}
	}
}
