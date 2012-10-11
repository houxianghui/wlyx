package com.blue.team;

import com.blue.common.User;

public class MianChiThread extends Thread {
	private User user;
	public MianChiThread(User user) {
		this.user = user;
		start();
	}
	@Override
	public void run() {
		while(true){
			try{
				WuGuan.destroy(user);
				sleep(1000*30);
			}catch(Exception e){
			}
		}
	}
}
