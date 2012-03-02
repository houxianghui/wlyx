/**
 * 每小时一次的监控进程
 */
package com.blue.daily;


import com.blue.common.BaseThread;
import com.blue.common.Monitor;
import com.blue.common.User;
import com.blue.monitor.GuardMonitor;
import com.blue.team.TeamEnergy;
import com.blue.tools.ItemMerge;
import com.blue.tools.ItemTools;

public class DailyAward extends BaseThread {
	private User user;
	public DailyAward(User user) {
		this.user = user;
		start();
	}
	@Override
	public void run() {
		while(true){
			if(needStop){
				return;
			}
			try{
				Monitor.getAwards(user);
				ItemTools.checkAndSell(user);
				ItemMerge.merge(user);
				ItemMerge.mergeSiHaiKuFang(user);
				ItemMerge.mergeStock(user);
				GuardMonitor.getGuard(user);
				TeamEnergy.sendEnergy(user);
				sleep(60*60*1000);
			}catch(Exception e){
			}
		}
	}
}
