package com.blue.task;

import com.blue.common.User;

public class AutoRewardThread extends Thread {
	private AutoTask at;
	private User user;
	public AutoRewardThread(AutoTask at,User user) {
		this.at = at;
		this.user = user;
		start();
	}
	@Override
	public void run() {
		while(true){
			try{
				if(at.getReward(user)){
					sleep(5*1000*60);
				}else{
					sleep(1000*60);
				}
			}catch(Exception e){}
		}
	}
}
