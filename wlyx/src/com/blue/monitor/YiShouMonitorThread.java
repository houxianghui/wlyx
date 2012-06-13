package com.blue.monitor;

import com.blue.beauty.Beauty;
import com.blue.common.BaseThread;
import com.blue.common.User;

public class YiShouMonitorThread extends BaseThread {
	private User user;
	public YiShouMonitorThread(User user) {
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
				YiShouMonitor.getYiShouEgg(user);
				sleep(10*60*1000);
			}catch(Exception e){
			}
		}
	}
}
