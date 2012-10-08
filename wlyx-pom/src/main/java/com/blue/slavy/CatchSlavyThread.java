package com.blue.slavy;


import com.blue.beauty.Beauty;
import com.blue.common.BaseThread;
import com.blue.common.Monitor;
import com.blue.common.User;

public class CatchSlavyThread extends BaseThread {
	private User user;
	
	public CatchSlavyThread(User user){
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
				Beauty.genGuTu(user);
				Monitor.activeSlavys(user);
				if(user.getNeedCatchSlavy() == 0){
					return;
				}
				CatchSlavy.catchSlavy(user);
				sleep(8*60*1000);
			}catch(Exception e){
			
			}
		}
	}
}
