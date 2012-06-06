package com.blue.common;

import com.blue.beauty.Beauty;
import com.blue.huanjing.HuanJing;
import com.blue.monitor.FreeItemMonitor;
import com.blue.monitor.RoomMonitor;
import com.blue.monitor.TarenaMonitor;
import com.blue.monitor.YiShouMonitor;
//import com.blue.monitor.FreeItemMonitor;
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
				HuanJing.readSave(user);
				RoomMonitor.getRoomFree(user);
				FreeItemMonitor.getFreeTimes(user);
				TarenaMonitor.getReward(user);
				YiShouMonitor.getYiShouEgg(user);
				sleep(10*60*1000);
			}catch(Exception e){
			}
		}
	}
}
