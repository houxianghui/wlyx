package com.blue.daily;

import java.util.Calendar;

import com.blue.common.*;

public class DailyWealsThread extends Thread {
	private User user;

	public DailyWealsThread(User user) {
		this.user = user;

		start();
	}
	@Override
	public void run() {
		while(true){
			//ÿ��00:01��ִ��
			try{
				Calendar c = Calendar.getInstance();
				int hour = c.get(Calendar.HOUR_OF_DAY);
				int minu = c.get(Calendar.MINUTE);
				if(hour >= 0 ){
					if(DailyWeals.alreadyHasWeals(user)){
						long time = ((24-hour)*60-minu+1)*60*1000;
						sleep(time);
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
