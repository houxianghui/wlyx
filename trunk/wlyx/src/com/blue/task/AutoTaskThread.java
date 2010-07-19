package com.blue.task;


import com.blue.common.User;

public class AutoTaskThread extends Thread {
	private AutoTask at;
	private User user;
	public AutoTaskThread(AutoTask at,User user) {
		this.at = at;
		this.user = user;
		start();
	}
	@Override
	public void run() {
		
		while(true){
			try{
				at.autoAcceptTask(user);
				at.autoFinishTask(user);
				sleep(5*1000*60);
			}catch(Exception e){}
		}
	}
}
