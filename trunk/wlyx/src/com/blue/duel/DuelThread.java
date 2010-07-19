package com.blue.duel;

import java.util.List;

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
			boolean success = false;
			d.setChallengerInfo(user);
			if(user.getChallengeTimes()<15){
				List<Challenger> l = user.getChallengeList();
				Challenger c = l.get(l.size()-2);
				success = d.challenge(user, c);
				System.out.println("challenge "+c.getId()+" success");
			}
			try{
				if(success){
					sleep(1000*60*60);
				}else{
					sleep(10*60*1000);
				}
			}catch(Exception e){}
		}
	}
}
