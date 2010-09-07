package com.blue.duel;

import com.blue.common.BaseThread;
import com.blue.common.User;

public class DuelThread extends BaseThread {

	private User user;
	public DuelThread(User user) {
		this.user = user;
		start();
	}
	@Override
	public void run() {
		while(true){
			try{
				if(needStop){
					return;
				}
				if(Duel.duel(user)){
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
