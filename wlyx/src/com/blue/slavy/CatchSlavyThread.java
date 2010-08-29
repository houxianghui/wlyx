package com.blue.slavy;


import com.blue.common.User;

public class CatchSlavyThread extends Thread {
	private User user;
	
	public CatchSlavyThread(User user){
		this.user = user;
		start();
	}
	@Override
	public void run() {
		while(true){
			try{
				if(user.getNeedCatchSlavy() == 0){
					return;
				}
				CatchSlavy.catchSlavy(user);
				sleep(5*60*1000);
			}catch(Exception e){
			
			}
		}
	}
}
