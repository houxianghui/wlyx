package com.blue.common;

import com.blue.beauty.Beauty;

public class MonitorThread extends BaseThread{
	private User user;
	public MonitorThread(User user) {
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
				Beauty.rongYu(user);
				Beauty.gongXian(user);
				
				Monitor.dailyWeals(user);				
				Monitor.mianChiWeals(user);
				Monitor.buyGlory(user);
				Monitor.roomWeal(user);
				Monitor.buyPool(user);
				Monitor.guoDu(user);
				sleep(60*60*1000);
			}catch(Exception e){
			}
		}
	}
}
