package com.blue.common;

import com.blue.beauty.Beauty;
import com.blue.monitor.ShiKeMonitor;

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
				Beauty.jingLi(user);
				Monitor.dailyWeals(user);				
				Monitor.mianChiWeals(user);
				Monitor.buyGlory(user);
				Monitor.roomWeal(user);
				Monitor.buyPool(user);
				Monitor.guoDu(user);
				Monitor.getGuoDuAward(user);
				ShiKeMonitor.painShiKe(user);
				
				sleep(10*60*1000);
			}catch(Exception e){
			}
		}
	}
}
