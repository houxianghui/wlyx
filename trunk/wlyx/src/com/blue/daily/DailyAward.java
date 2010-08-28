package com.blue.daily;

import org.apache.log4j.Logger;

import com.blue.common.Monitor;
import com.blue.common.User;

public class DailyAward extends Thread {
	private Logger logger = Logger.getLogger(this.getClass());
	private User user;
	public DailyAward(User user) {
		this.user = user;
	}
	@Override
	public void run() {
		while(true){
			try{
				Monitor.getAwards(user);
				sleep(60*60*1000);
			}catch(Exception e){
				logger.error(e.getMessage());
			}
		}
	}
}
