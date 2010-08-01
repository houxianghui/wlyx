package com.blue.task;


import org.apache.log4j.Logger;

import com.blue.common.User;

public class AutoTaskThread extends Thread {
	private Logger logger = Logger.getLogger(this.getClass());
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
			}catch(Exception e){
				e.printStackTrace();
				logger.info(user.getRoleName()+" "+e.getMessage());
			}
		}
	}
}
