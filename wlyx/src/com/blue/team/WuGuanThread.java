package com.blue.team;

import com.blue.common.Monitor;
import com.blue.common.User;

public class WuGuanThread extends Thread {
	private User user;
	public WuGuanThread(User user) {
		this.user = user;
		start();
	}
	@Override
	public void run() {
		while(true){
			try{
				if(Monitor.inWuGuan(user)){
					WuGuan.tiGuan(user);
					WuGuan.huGuan(user);
				}
				sleep(10*60*1000);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
}
