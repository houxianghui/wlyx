
/*********************************************************
 * File:DateUtil.java
 *
 * Version 1.0
 *
 * Date     2005-8-7
 * Author   辛勇
 *
 * Copyright (C) 2005 huateng.
 * all rights reserved.
 *
 ********************************************************/

package com.eis.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.eis.db.DBUtil;
/**
 * 说明：该类实现对日期的操作
 *
 */

public final class DateUtil {

	/**
	 * 构造函数
	 */
	public DateUtil() {
		super();

	}

	/**
	* 获得系统当前时间“YYYY-MM-DD hh:mm:ss”
	* @return java.lang.String
	*/
	public static String getTime() {
		java.util.Date dt = new java.util.Date();
		java.sql.Timestamp ts = new java.sql.Timestamp(dt.getTime());
		String time = ts.toString();
		return time.substring(0, 19);
	}

	/**
	* 获得系统当前日期“YYYY-MM-DD”
	* @return java.lang.String
	*/
	public static String getDT() {
		java.util.Date dt = new java.util.Date();
		java.sql.Timestamp ts = new java.sql.Timestamp(dt.getTime());
		String time = ts.toString();
		return time.substring(0, 10);
	}

	/**
	* 获得系统当前日期“YYYYMMDD”
	* @return java.lang.String
	*/
	public static String getDTStr() {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");

		return format.format(new java.util.Date());
	}

	/**
	* 获得系统当前日期“YYYYMMDDhhmmss”
	* @return java.lang.String
	*/
	public static String getTimeStr() {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");

		return format.format(new java.util.Date());
	}
	
	
	/**
	 * 得到系统当前日期yyyyMMddHHmmssSSSS+2位随机数
	 * @return
	 */
	public static String getTimeRandomStr() {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSSS");
		String random = new Double(Math.random()*1000).toString().substring(0,2);

		return format.format(new java.util.Date())+random;
	}

	/**
	* 获得系统当前时间“hhmmss”
	* @return java.lang.String
	*/
	public static String getTimeOnly() {
		SimpleDateFormat format = new SimpleDateFormat("HHmmss");

		return format.format(new java.util.Date());
	}

	/**
	* 获得指定日期变量的固定格式“YYYY-MM-DD”
	* @return java.lang.String
	*/
	public static String getDT(java.util.Date dt) {
		String dtstr = null;
		if (dt == null)
			dtstr = "1900-01-01";
		else {
			java.sql.Timestamp ts = new java.sql.Timestamp(dt.getTime());
			String time = ts.toString();
			dtstr = time.substring(0, 10);
		}
		return dtstr;

	}

	/**
	* 获得系统当前年份“YYYY”
	* @return java.lang.String
	*/
	public static String getYear() {
		return getDT().substring(0, 4);
	}

	/**
	 * 获取的系统当前的月份YYYYMM
	 * @return
	 */
	public static String getMonth(){
		return getDT().substring(0,6);
	}

	/****
	* 获得当前时间
	* @return 当前时间距离标准时间的微秒数
	*/
	public static long getTimeInMillion() {

		return System.currentTimeMillis();
	}

	/**
		* 对日期时间进行格式化输出,输出格式"2005年1月1日 22:11:30"
		* @param ll java.lang.String
		* @return java.lang.String
		*/
	public static String formatDateTime(String dt) {
		if (dt == null || dt.trim().length()<14 || dt.trim().equals("00000000000000"))
			return "";

		return dt.substring(0, 4)
			+ "年"
			+ Integer.parseInt(dt.substring(4, 6))
			+ "月"
			+ Integer.parseInt(dt.substring(6, 8))
			+ "日 "
			+ dt.substring(8, 10)
			+ ":"
			+ dt.substring(10, 12)
			+ ":"
			+ dt.substring(12);

	}

	/**
		* 对日期进行格式化输出,输出格式"2005年1月1日"
		* @param ll java.lang.String
		* @return java.lang.String
		*/
	public static String formatDate(String dt) {
		if (dt == null || dt.trim().length()<8 || dt.trim().equals("00000000"))
			return "";
		else
			return dt.substring(0, 4)
				+ "年"
				+ Integer.parseInt(dt.substring(4, 6))
				+ "月"
				+ Integer.parseInt(dt.substring(6, 8))
				+ "日 ";

	}

	/**
		* 对日期时间进行格式化输出,输出格式"2005年01月01日 22:11:30"
		* @param dt-java.lang.String
		* @return java.lang.String
		*/
	public static String formatDateTime1(String dt) {
		if (dt == null || dt.trim().length()<14 || dt.trim().equals("00000000000000"))
			return "";

		return dt.substring(0, 4)
			+ "年"
			+ dt.substring(4, 6)
			+ "月"
			+ dt.substring(6, 8)
			+ "日 "
			+ dt.substring(8, 10)
			+ ":"
			+ dt.substring(10, 12)
			+ ":"
			+ dt.substring(12);

	}


	/**
		* 对时间进行格式化输出,输出格式"22:11:30"
		* @param time-java.lang.String
		* @return java.lang.String
		*/
	public static String formatTime(String time) {
		if (time == null || time.trim().length()<6 || time.trim().equals("000000"))
			return "";

		return time.substring(0, 2)
			+ ":"
			+ time.substring(2, 4)
			+ ":"
			+ time.substring(4, 6);

	}

	/**
	 * 根据传入日期“YYYYMM”获得月末日期“YYYYMMDD”
	 * @return java.lang.String
	 */
	public static String getMonEnd(String dt) {
		String date = dt;
		int year = Integer.parseInt(dt.substring(0, 4));
		int mon = Integer.parseInt(dt.substring(4, 6));
		if (mon == 1
			|| mon == 3
			|| mon == 5
			|| mon == 7
			|| mon == 8
			|| mon == 10
			|| mon == 12) {
			date = dt + "31";
		}
		if (mon == 4 || mon == 6 || mon == 9 || mon == 11) {
			date = dt + "30";
		}
		if (mon == 2) {
			if (year % 4 == 0) {
				if ((year % 100 == 0 && year % 400 == 0)
					|| (year % 100 != 0)) {
					date = dt + "29";
				}
			} else {
				date = dt + "28";
			}
		}

		return date;
	}

	/**
	 * 获得当前系统日期的上个月日期“YYYYMM”
	 * @return java.lang.String
	 */
	public static String getLastMon() {

		Calendar now = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMM");

		now.add(Calendar.MONTH, -1);

		String date = (String) formatter.format(now.getTime());
		return date;
	}

	/**
	 * 获得比当前系统日期晚n年的系统日期“YYYYMMDD”
	 * @param   year - 年数
	 * @return java.lang.String
	 */
	public static String getYearAfterSysdt(int year) {

		Calendar now = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");

		now.add(Calendar.YEAR, year);

		String tm = (String) formatter.format(now.getTime());

		return tm;
	}

	/**
	* 获得比指定日期晚n天的系统日期“YYYYMMDD”
	* @param   dt-指定日期
	* @param   days - 天数
	* @return java.lang.String
	*/
	public static String getDateAfter(String dt, int days) {

		Calendar now = Calendar.getInstance();

		dt =
			dt.substring(0, 4)
				+ "-"
				+ dt.substring(4, 6)
				+ "-"
				+ dt.substring(6, 8);

		java.util.Date date = java.sql.Date.valueOf(dt);

		now.setTime(date);

		now.add(Calendar.DAY_OF_YEAR, days);

		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		String tm = (String) formatter.format(now.getTime());

		return tm;
	}

	/**
	* 获得比指定日期早n天的系统日期“YYYYMMDD”
	* @param   dt-指定日期
	* @param   days - 天数
	* @return java.lang.String
	*/
	public static String getDateBefore(String dt, int days) {

		Calendar now = Calendar.getInstance();

		dt =
			dt.substring(0, 4)
				+ "-"
				+ dt.substring(4, 6)
				+ "-"
				+ dt.substring(6, 8);

		java.util.Date date = java.sql.Date.valueOf(dt);

		now.setTime(date);

		now.add(Calendar.DAY_OF_YEAR, -days);

		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		String tm = (String) formatter.format(now.getTime());

		return tm;
	}

	/**
	* 获得比系统当前日期晚n天的系统日期“YYYYMMDD”
	* @param   days - 天数
	* @return java.lang.String
	*/
	public static String getDateAfterSysdt(int days) {

		Calendar now = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");

		now.add(Calendar.DAY_OF_YEAR, days);

		String tm = (String) formatter.format(now.getTime());

		return tm;
	}

	/**
	* 获得比系统当前日期早n天的系统日期“YYYYMMDD”
	* @param   days - 天数
	* @return java.lang.String
	*/
	public static String getDateBeforeSysdt(int days) {

		Calendar now = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");

		now.add(Calendar.DAY_OF_YEAR, -days);

		String tm = (String) formatter.format(now.getTime());

		return tm;
	}
	/**
	* 依据指定格式获得比系统当前日期早n天的系统日期
	* @param   days - 天数
	* @return java.lang.String
	*/
	public static String getDateBeforeSysdt(int days,String pattern){
		
		Calendar now = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);

		now.add(Calendar.DAY_OF_YEAR, -days);

		String tm = (String) formatter.format(now.getTime());

		return tm;
	}


	/**
	* 获得比指定日期晚n月的日期“YYYYMMDD”
	* @param   dt-指定日期
	* @param   month - 月数
	* @return java.lang.String
	*/
	public static String getDateAfterMonth(String dt, int month) {

		Calendar now = Calendar.getInstance();

		dt =
			dt.substring(0, 4)
				+ "-"
				+ dt.substring(4, 6)
				+ "-"
				+ dt.substring(6, 8);

		java.util.Date date = java.sql.Date.valueOf(dt);

		now.setTime(date);

		now.add(Calendar.MONTH, month);

		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		String tm = (String) formatter.format(now.getTime());

		return tm;
	}

	/**
	* 获得比指定日期早n月的日期“YYYYMMDD”
	* @param   dt-指定日期
	* @param   month - 月数
	* @return java.lang.String
	*/
	public static String getDateBeforeMonth(String dt, int month) {

		Calendar now = Calendar.getInstance();

		dt =
			dt.substring(0, 4)
				+ "-"
				+ dt.substring(4, 6)
				+ "-"
				+ dt.substring(6, 8);

		java.util.Date date = java.sql.Date.valueOf(dt);

		now.setTime(date);

		now.add(Calendar.MONTH, -month);

		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		String tm = (String) formatter.format(now.getTime());

		return tm;
	}


	/**
	* 获得比当前系统日期晚n月的日期“YYYYMMDD”
	*
	* @param   month - 月数
	* @return java.lang.String
	*/
	public static String getDateAfterSysMonth(int month) {

		Calendar now = Calendar.getInstance();

		now.add(Calendar.MONTH, month);

		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		String tm = (String) formatter.format(now.getTime());

		return tm;
	}

	/**
	* 获得比当前系统日期早n月的日期“YYYYMMDD”
	*
	* @param   month - 月数
	* @return java.lang.String
	*/
	public static String getDateBeforeSysMonth(int month) {

		Calendar now = Calendar.getInstance();

		now.add(Calendar.MONTH, -month);

		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		String tm = (String) formatter.format(now.getTime());

		return tm;
	}


	
	/**
     * Create on 2007-4-12 17:26:10 Administrator
     *
     * 获得平台系统时间
     * @return yyyyMMdd
     */
    public static String getSystemDate(){
		DBUtil db = new DBUtil();

		String sql = "select EP_DATE from ep_system";
		try{
			return db.sqlQuerySingle(sql);
		}catch(Exception e){
			return null;
		}finally{
			db.close();
		}
	}
	/**
	 * Create on 2007-3-19 12:40:35 Ranger
	 *
	 * 将yyyyMMdd格式的字符串转换为Date格式
	 * @param s 要转换的字符串
	 * @return Date格式日期
	 */

	public static Date parseDate(String s) {
		Calendar c = Calendar.getInstance();
		try {
			int d = Integer.parseInt(s);
			int year = d / 10000;
			int month = (d % 10000) / 100;
			int day = d % 100;
			c.set(year, month - 1, day);
		} catch (Exception e) {
			return null;
		}
		return c.getTime();
	}
	/**
	 *
	 * 获得给定两个日期之间相隔的天数
	 *
	 * @param from 开始时间
	 * @param to 结束时间
	 * @return 天数
	 */
	public static long getDays(Date from, Date to) {

		long s = from.getTime();
		long e = to.getTime();

		return Math.round(((e - s) / (24 * 60 * 60 * 1000.0)));
	}
	
	public static long getDays(String from,String to){
		return getDays(parseDate(from),parseDate(to));
	}
		/**
	 * @author ljp
	 * 获得当前系统时间，精确到毫秒
	 * 
	 * @param   
	 * @param  
	 * @return 当前系统时间 毫秒
	 */	
	public static String getCurrDateMills(){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmssSSS");

		return formatter.format(new java.util.Date());
		
	}
	
	
	/**
	* @author ljp
	* 获得当前系统时间，精确到毫秒
	* 
	* 获得系统当前日期“YYYYMMDDhhmmssSS”
	* @return java.util.Date
	*/
	@SuppressWarnings("finally")
	public static Date getCurrDate() {	      
		DateFormat formatter = new SimpleDateFormat("yyyyMMddhhmmssSSS");       
		long now = System.currentTimeMillis(); 
		Calendar calendar = Calendar.getInstance();       
		calendar.setTimeInMillis(now); 
        return calendar.getTime(); 
     
	}
	
}
