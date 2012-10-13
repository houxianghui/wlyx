package com.blue.monitor;

import com.blue.common.BaseThread;
import com.blue.common.User;

public class PackageMonitorThread extends BaseThread {
	private User user;
	public PackageMonitorThread(User user) {
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
				PackageItemMonitor.upadtePackage(user);
				sleep(30*60*1000);
			}catch(Exception e){
			}
		}
	}
}
