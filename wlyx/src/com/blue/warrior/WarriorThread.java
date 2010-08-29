package com.blue.warrior;

import com.blue.common.User;

public class WarriorThread extends Thread{
	private User user;
	public WarriorThread(User user) {
		this.user = user;
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
						Warrior.startTrain(user);
						
					}else if(user.getWarriorChoice() == 2){
						Warrior.startWork(user);
					}
				}
				sleep(5*1000*60);
			}catch(Exception e){
			}
		}
	}
}
