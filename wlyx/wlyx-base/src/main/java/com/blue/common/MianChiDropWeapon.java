package com.blue.common;

import java.util.Calendar;

public class MianChiDropWeapon {
	public static int[] times = {(15*60+50)*60,16*60*60,(16*60+10)*60,(16*60+20)*60,(16*60+30)*60,(16*60+40)*60,(16*60+50)*60};
	public static int getNow(){
		Calendar c = Calendar.getInstance();
		int hour = c.get(Calendar.HOUR_OF_DAY);
		int min = c.get(Calendar.MINUTE);
		int sec = c.get(Calendar.SECOND);
		return (hour*60+min)*60+sec;
	}
	public static int getWaitSecond(){
		int now = getNow();
		for(int i = 0;i < times.length;i++){
			if(now < times[i]){
				return times[i]-now-10;	//ÌáÇ°10ÃëÐ¶Îä
			}
		}
		int dayTime = 24*60*60;
		return dayTime-now+times[0]-10;
	}
}
