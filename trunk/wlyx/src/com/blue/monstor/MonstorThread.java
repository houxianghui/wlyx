package com.blue.monstor;

import org.apache.log4j.Logger;

import com.blue.common.User;
import com.blue.task.AutoTask;

public class MonstorThread extends Thread {
	private Logger logger = Logger.getLogger(this.getClass());
	private Monstor monstor;
	private User user;
	private AutoTask at;
	public MonstorThread(User user,Monstor monstor,AutoTask at) {
		this.user = user;
		this.monstor = monstor;
		this.at = at;
		start();
	}
	@Override
	public void run() {
		while(true){
			try{
				if(monstor.killMonstor(user,at)){
					sleep(3*60*1000);
				}else{
					sleep(5*10*1000);
				}
			}catch(Exception e){
				e.printStackTrace();
				logger.error(user.getRoleName()+" "+e.getMessage());
			}
		}
	}
}
