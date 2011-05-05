package com.blue.daily;

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
			//ÿ��00:01��ִ��
			try{
				if(needStop){
					return;
				}
				
				if(DailyWeals.alreadyHasWeals(user)){
//						long time = ((24-hour)*60-minu+1)*60*1000;
					sleep(3*60*60*1000);
				}else{
					DailyWeals.getDailyWeals(user);
					sleep(6*60*1000);
				}
			}catch(Exception e){}
		}
	}
}
