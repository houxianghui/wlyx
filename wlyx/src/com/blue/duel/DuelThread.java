package com.blue.duel;

import com.blue.common.User;

public class DuelThread extends Thread {
	private Duel d;
	private User user;
	public DuelThread(Duel d,User user) {
		this.d = d;
		this.user = user;
		start();
	}
	@Override
	public void run() {
		while(true){
			try{
				if(d.challenge(user)){
					if(!user.isFastChallenge()){
						sleep(1000*60*(user.getDueSleepInteval()+10));
					}else{
						sleep(1000*60*10);
					}
				}else{
					sleep(60*1000);
				}
			}catch(Exception e){}
		}
	}
}
