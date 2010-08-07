package com.start;

import java.util.Iterator;
import java.util.List;

import com.blue.common.User;
import com.blue.monstor.Monstor;
import com.blue.team.MianChiThread;
import com.blue.team.WuGuan;
import com.blue.team.WuGuanThread;

public class MianChi {
	public static void main(String[] args)throws Exception {
		List<User> l = Main.getUserInfo();
		
		
		Iterator<User> it = l.iterator();
		while(it.hasNext()){
			User user = it.next();
			user.login();
//			new WuGuanThread(user);
			new MianChiThread(user);
		}
	}
}
