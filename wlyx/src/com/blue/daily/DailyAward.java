package com.blue.daily;


import com.blue.common.BaseThread;
import com.blue.common.Monitor;
import com.blue.common.User;

public class DailyAward extends BaseThread {
	private User user;
	public DailyAward(User user) {
		this.user = user;
		start();
	}
	@Override
	public void run() {
		while(true){
			if(needStop){
				return;
			}
			try{
				Monitor.getAwards(user);
				sleep(60*60*1000);
			}catch(Exception e){
			}
		}
	}
}
