package com.blue.fyzb;

import com.blue.common.BaseThread;
import com.blue.common.User;

public class ServerDuelHallThread extends BaseThread {
	private User user;
	
	public ServerDuelHallThread(User user) {
		this.user = user;
		start();
	}
	@Override
	public void run() {
		while(!needStop){
			try {
				ServerDuelHall.challenge(user);
				Thread.sleep(3*60*1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
