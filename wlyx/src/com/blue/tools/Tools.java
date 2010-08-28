package com.blue.tools;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Random;


public class Tools {
	public static String getTimeStamp(boolean hasAddFlag){
		if(hasAddFlag){
			return "&timeStamp="+System.currentTimeMillis();
		}
		return "?timeStamp="+System.currentTimeMillis();
	}
	public static String getRandAndTime(){
		return "&rand="+System.currentTimeMillis()+getTimeStamp(true);
	}
	public static String getGtimeAndTime(){
		//&g_time=1279160603&timeStamp=1279160670604
		return "&g_time="+System.currentTimeMillis()/1000+"&timeStamp"+System.currentTimeMillis();
	}
	public static boolean success(String page){
		return !page.contains("{\"error\":true") && page.trim().length() > 0;
	}
	//&timeMark=1282959774&time=10&timeStamp=1282959775563
	public static String getMarkAndTime(){
		Random r = new Random();
		int i = r.nextInt(1);
		if(i == 0){
			i = 4;
		}
		return "&timeMark="+System.currentTimeMillis()/1000+"&time="+i+"0&timeStamp="+System.currentTimeMillis();
	}
	/*
	 *\\uhhhh
	 */
	public static String hexToString(String hex){
		StringBuilder sb = new StringBuilder();
		String[] t = hex.split("\\\\u");
		for(int i = 0;i < t.length;i++){
			try{
				char c = (char)Integer.parseInt(t[i],16);
				sb.append(c);
			}catch(Exception e){}
			
		}
		return sb.toString();
	}
	public static int getNowHour(){
		Calendar c = Calendar.getInstance();
		return c.get(Calendar.HOUR_OF_DAY);
	}
	/**
	 * 将231,231,311格式的字符串转换为double
	 * @param s
	 * @return
	 * @throws Exception
	 */
	public static double getValue(String s)throws Exception{
		DecimalFormat df = new DecimalFormat("#,#0");
		double t = df.parse(s).doubleValue();
		return t;
	}

}
