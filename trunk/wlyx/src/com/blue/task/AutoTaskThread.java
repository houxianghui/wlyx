package com.blue.task;


import com.blue.common.User;

public class AutoTaskThread extends Thread {
	private User user;
	public AutoTaskThread(User user) {
		this.user = user;
		start();
	}
	@Override
	public void run() {
		
		while(true){
			try{
				AutoTask.autoAcceptTask(user);
				AutoTask.autoFinishTask(user);
				sleep(5*1000*60);
			}catch(Exception e){
			}
		}
	}
}
