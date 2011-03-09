package com.blue.warrior;

import com.blue.common.BaseThread;
import com.blue.common.User;

public class WarriorThread extends BaseThread{
	private User user;
	public WarriorThread(User user) {
		this.user = user;
		start();
	}
	@Override
	public void run() {
		while(true){
			if(needStop){
				return;
			}
			try{
				
				if(user.getWarriorChoice() == 1 || user.getWarriorChoice() == 2){
					Warrior.startTrain(user);
				}
				
				sleep(6*1000*60);
			}catch(Exception e){
			}
		}
	}
}
