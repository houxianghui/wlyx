package com.blue.warrior;

import org.apache.log4j.Logger;

import com.blue.common.User;

public class WarriorThread extends Thread{
	private Logger logger = Logger.getLogger(this.getClass());
	private User user;
	private Warrior warrior;
	public WarriorThread(User user,Warrior warrior) {
		this.user = user;
		this.warrior = warrior;
		start();
	}
	@Override
	public void run() {
		while(true){
			try{
				if(user.getWarriorChoice() == 0){
					return;
				}else{
					if(user.getWarriorChoice() == 1){
						warrior.startTrain(user);
						
					}else if(user.getWarriorChoice() == 2){
						warrior.startWork(user);
					}
				}
				sleep(5*1000*60);
			}catch(Exception e){
				e.printStackTrace();
				logger.error(e.getMessage());
			}
		}
	}
}
