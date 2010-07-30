package com.blue.slavy;

import com.blue.common.User;

public class CatchSlavyThread extends Thread {
	private User user;
	private CatchSlavy cs;
	
	public CatchSlavyThread(User user,CatchSlavy cs){
		this.user = user;
		this.cs = cs;
		start();
	}
	@Override
	public void run() {
		while(true){
			try{
				if(user.getNeedCatchSlavy() == 0){
					return;
				}
				cs.catchSlavy(user);
				sleep(5*60*1000);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
}
