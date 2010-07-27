package com.blue.daily;

import java.util.Calendar;

import com.blue.common.*;

public class DailyWealsThread extends Thread {
	private User user;
	private DailyWeals dw;
	public DailyWealsThread(User user,DailyWeals dw) {
		this.user = user;
		this.dw = dw;
		start();
	}
	@Override
	public void run() {
		while(true){
			//每天00:01后执行
			try{
				Calendar c = Calendar.getInstance();
				int hour = c.get(Calendar.HOUR_OF_DAY);
				int minu = c.get(Calendar.MINUTE);
				if(hour >= 0 ){
					if(dw.alreadyHasWeals(user)){
						long time = ((24-hour)*60-minu+1)*60*1000;
						sleep(time);
					}else{
						if(!dw.getDailyWeals(user)){
							continue;
						}
					}
				}
			}catch(Exception e){}
		}
	}
}
