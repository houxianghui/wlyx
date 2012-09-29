package com.blue.tools;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
	public static boolean isErrorPage(String page){
		return page.contains("{\"error\":true");
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
	public static String getNow(){
		Date d = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.format(d);
	}
	/*
	 *\\uhhhh
	 */
	public static String hexToString(String hex){
		if(hex.indexOf("\\u")==-1){
			return hex;
		}
		StringBuilder sb = new StringBuilder();
		String[] t = hex.split("\\\\u");
		for(int i = 0;i < t.length;i++){
			try{
				if(t[i].length() == 4){
					char c = (char)Integer.parseInt(t[i],16);
					sb.append(c);
				}else{
					sb.append(t[i].replaceAll("7b2c", ""));
				}
			}catch(Exception e){}
			
		}
		return sb.toString();
	}
	public static int getNowHour(){
		Calendar c = Calendar.getInstance();
		return c.get(Calendar.HOUR_OF_DAY);
	}
	public static int getNowMinute(){
		Calendar c = Calendar.getInstance();
		return c.get(Calendar.MINUTE);
	}
	public static int getMonth(){
		Calendar c = Calendar.getInstance();
		return c.get(Calendar.MONTH)+1;
	}
	public static int getDay(){
		Calendar c = Calendar.getInstance();
		return c.get(Calendar.DATE);
	}
	public static int getDayOfWeek(){
		Calendar c = Calendar.getInstance();
		return c.get(Calendar.DAY_OF_WEEK);
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
	public static boolean isEmpty(String s){
		return s == null || s.trim().length() == 0;
	}
	public static boolean needGetLingPai(){
		Calendar c = Calendar.getInstance();
		int day = c.get(Calendar.DAY_OF_WEEK);
		return day%2 != 0;
	}
	public static boolean contains(String s,String find){
		char[] chars = find.toCharArray();
		for(char c:chars){
			if(s.indexOf(c)!=-1){
				return true;
			}
		}
		return false;
	}
}
