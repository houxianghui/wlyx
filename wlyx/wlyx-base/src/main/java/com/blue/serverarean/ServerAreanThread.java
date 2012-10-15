package com.blue.serverarean;

import com.blue.common.BaseThread;
import com.blue.common.User;

public class ServerAreanThread extends BaseThread {
	private User user;
	public ServerAreanThread(User user) {
		this.user = user;
		start();
	}
	public void run() {
		while(true){
			if(needStop){
				return;
			}
			try{
				ServerArean.sign(user);
			}catch(Exception e){
				e.printStackTrace();
			}
			try{
				ServerArean.challenge(user);
				Thread.sleep(10*60*1000);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	};
}
