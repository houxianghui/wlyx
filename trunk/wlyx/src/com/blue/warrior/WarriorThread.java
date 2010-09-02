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
				if(user.getWarriorChoice() == 0){
					return;
				}else{
					if(user.getWarriorChoice() == 1){
						Warrior.startTrain(user);
						
					}else if(user.getWarriorChoice() == 2){
						Warrior.startWork(user);
					}
				}
				sleep(10*1000*60);
			}catch(Exception e){
			}
		}
	}
}
