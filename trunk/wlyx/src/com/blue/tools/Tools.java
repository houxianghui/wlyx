package com.blue.tools;


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
		return !page.contains("{\"error\":true") && page.length() > 10;
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
}
