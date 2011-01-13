/*
 * @# BirthdayUtil.java 2009-8-21 houxh
 *
 * Copyright  (c)  2009 	Huateng. All Right Reserv
 */
 
package com.eis.util;

import java.util.Calendar;


public class BirthdayUtil {
	final static String constellations[] = {"白羊座","金牛座","双子座","巨蟹座","狮子座","处女座","天秤座","天蝎座","射手座","魔蝎座","水瓶座","双鱼座"};
	final static int constellationDays[][] = {{321,420},{421,521},{522,621},{622,722},{723,823},{824,923},{924,1023},{1024,1122},{1123,1221},{1222,120},{121,219},{220,320}};
	final static String animalOfTheYear[] = {"鼠","牛","虎","兔","龙","蛇","马","羊","猴","鸡","狗","猪"};
	final static String[] yue = new String[] { "", "正", "二", "三", "四",
					"五", "六", "七", "八", "九", "十", "十一", "十二" };
	/**
	 * 2009-8-21 15:33:54 houxh
	 * 获得农历的日
	 * @param day
	 * @return
	 */
	public final static String getChinaDate(int day) {
		String a = "";
		if (day == 10)
			return "初十";
		if (day == 20)
			return "二十";
		if (day == 30)
			return "三十";
		int two = (int) ((day) / 10);
		if (two == 0)
			a = "初";
		if (two == 1)
			a = "十";
		if (two == 2)
			a = "廿";
		if (two == 3)
			a = "三";
		int one = (int) (day % 10);
		switch (one) {
		case 1:
			a += "一";
			break;
		case 2:
			a += "二";
			break;
		case 3:
			a += "三";
			break;
		case 4:
			a += "四";
			break;
		case 5:
			a += "五";
			break;
		case 6:
			a += "六";
			break;
		case 7:
			a += "七";
			break;
		case 8:
			a += "八";
			break;
		case 9:
			a += "九";
			break;
		}
		return a;
	}
	public static int getAge(String birthDay,Calendar c){
		if(CheckUtil.isEmptry(birthDay) || c == null){
			return 0;
		}
		int now = c.get(Calendar.YEAR);
		int birthYear = Integer.parseInt(birthDay.substring(0,4));
		return now - birthYear;
	}
	public static int getAge(int birthYear,Calendar c){
		int now = c.get(Calendar.YEAR);
		return now - birthYear;
	}
	public static String getConstellation(String birthDay){
		int date = Integer.parseInt(birthDay.substring(4));
		return getConstellation(date);
	}
	public static String getConstellation(int date){
		for(int i = 0;i<constellationDays.length;i++){
			if(date >= constellationDays[i][0] && date <= constellationDays[i][1]){
				return constellations[i];
			}
		}
		return constellations[9];
	}
	public static String getConstellation(int month,int day){
		int date = month*100+day;
		return getConstellation(date);
	}
	public static String getAnimalOfTheYear(String birthDay){
		return getAnimalOfTheYear(Integer.parseInt(birthDay.substring(0,4)));
	}
	public static String getAnimalOfTheYear(int year){
		int base = 1984;
		int step = (year - base)%12;
		if(step < 0){
			step += 12;
		}
		return animalOfTheYear[step]; 
	}
	public static String getLunaDate(String birthDay){
		int[] info = DateConvert.getLunarCalendar(birthDay);
		StringBuffer sb = new StringBuffer();
		sb.append("阴历生日:");
		sb.append(info[0]);
		sb.append("(");
		sb.append(getAnimalOfTheYear(info[0]));
		sb.append(")年");
		sb.append(yue[info[1]]);
		sb.append("月");
		sb.append(getChinaDate(info[2]));
		sb.append(" 星座:");
		sb.append(getConstellation(birthDay));
		sb.append(" 年龄:");
		sb.append(getAge(birthDay,Calendar.getInstance()));
		
		return sb.toString();
	}
	private static String formatDate(int[] date){
		StringBuffer sb = new StringBuffer();
		sb.append(date[0]);
		sb.append("-");
		sb.append(date[1]);
		sb.append("-");
		sb.append(date[2]);
		return sb.toString();
	}
	public static String getInfo(String birthDay,boolean isLuna){
		StringBuffer sb = new StringBuffer();
		if(isLuna){
			int[] gDate = DateConvert.getGregorianCalendar(birthDay);
			sb.append("阳历生日:");
			sb.append(formatDate(gDate));
			sb.append(" 星座:");
			sb.append(getConstellation(gDate[1],gDate[2]));
			sb.append(" 生肖:");
			sb.append(getAnimalOfTheYear(gDate[0]));
			sb.append(" 年龄:");
			sb.append(getAge(gDate[0],Calendar.getInstance()));
		}else{
			sb.append(getLunaDate(birthDay));
		}
		return sb.toString();
	}
}
