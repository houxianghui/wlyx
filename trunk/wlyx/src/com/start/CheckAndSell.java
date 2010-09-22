package com.start;

import java.util.Iterator;
import java.util.List;
import com.blue.common.User;
import com.blue.monstor.ItemMerge;
import com.blue.monstor.Monstor;


public class CheckAndSell {
	public static void main(String[] args)throws Exception {
		
		List<User> l = Main.getUserInfo();
		
		Iterator<User> it = l.iterator();
		while(it.hasNext()){
			User user = it.next();
			user.login(false);
			Monstor.checkAndSell(user);
			ItemMerge.merge(user);
		}
		
	}
	
}
