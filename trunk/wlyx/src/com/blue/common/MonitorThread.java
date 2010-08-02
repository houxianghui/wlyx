package com.blue.common;

import com.blue.beauty.Beauty;

public class MonitorThread extends Thread{
	private User user;
	public MonitorThread(User user) {
		this.user = user;
		start();
	}
	@Override
	public void run() {
		while(true){
			try{
				Beauty.rongYu(user);
				Beauty.gongXian(user);
				Monitor.activeSlavys(user);
				Monitor.dailyWeals(user);				
				Monitor.mianChiWeals(user);
				Monitor.buyGlory(user);
				Monitor.roomWeal(user);
				sleep(10*60*1000);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
}
