package com.blue.daily;

import java.util.Calendar;

import com.blue.common.*;

public class DailyWealsThread extends BaseThread {
	private User user;

	public DailyWealsThread(User user) {
		this.user = user;

		start();
	}
	@Override
	public void run() {
		while(true){
			//每天00:01后执行
			try{
				if(needStop){
					return;
				}
				Calendar c = Calendar.getInstance();
				int hour = c.get(Calendar.HOUR_OF_DAY);
				int minu = c.get(Calendar.MINUTE);
				if(hour >= 0 ){
					if(DailyWeals.alreadyHasWeals(user)){
//						long time = ((24-hour)*60-minu+1)*60*1000;
						sleep(3*60*60*1000);
					}else{
						if(!DailyWeals.getDailyWeals(user)){
							continue;
						}
					}
				}
			}catch(Exception e){}
		}
	}
}
