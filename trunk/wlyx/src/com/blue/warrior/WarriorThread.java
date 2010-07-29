package com.blue.warrior;

import com.blue.common.User;

public class WarriorThread extends Thread{
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
						sleep(5*1000*60);
					}else if(user.getWarriorChoice() == 2){
						warrior.startWork(user);
					}
				}
				
			}catch(Exception e){}
		}
	}
}
