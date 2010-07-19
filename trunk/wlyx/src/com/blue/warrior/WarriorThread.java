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
				warrior.startTrain(user);
				sleep(5*1000*60);
			}catch(Exception e){}
		}
	}
}
