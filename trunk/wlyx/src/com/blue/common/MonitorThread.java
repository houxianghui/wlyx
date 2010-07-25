package com.blue.common;

import com.blue.beauty.Beauty;

public class MonitorThread extends Thread{
	private Monitor monitor;
	private User user;
	public MonitorThread(Monitor monitor,User user) {
		this.monitor = monitor;
		this.user = user;
	}
	@Override
	public void run() {
		while(true){
			try{
				if(Beauty.rongYu(user)){
					sleep(20*60*60*1000);
				}else{
					sleep(60*60*1000);
				}
				if(Beauty.gongXian(user)){
					sleep(20*60*60*1000);
				}else{
					sleep(60*60*1000);
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
}
