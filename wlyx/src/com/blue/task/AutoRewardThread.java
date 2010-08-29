package com.blue.task;

import org.apache.log4j.Logger;

import com.blue.common.User;

public class AutoRewardThread extends Thread {
	private Logger logger = Logger.getLogger(this.getClass());
	private User user;
	public AutoRewardThread(User user) {
		this.user = user;
		start();
	}
	@Override
	public void run() {
		while(true){
			try{
				if(AutoTask.getReward(user)){
					sleep(5*1000*60);
				}else{
					sleep(1000*60);
				}
			}catch(Exception e){
			}
		}
	}
}
