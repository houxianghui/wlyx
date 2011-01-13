
/*********************************************************
 * File:DateUtil.java
 *
 * Version 1.0
 *
 * Date     2005-8-7
 * Author   ����
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
 * ˵��������ʵ�ֶ����ڵĲ���
 *
 */

public final class DateUtil {

	/**
	 * ���캯��
	 */
	public DateUtil() {
		super();

	}

	/**
	* ���ϵͳ��ǰʱ�䡰YYYY-MM-DD hh:mm:ss��
	* @return java.lang.String
	*/
	public static String getTime() {
		java.util.Date dt = new java.util.Date();
		java.sql.Timestamp ts = new java.sql.Timestamp(dt.getTime());
		String time = ts.toString();
		return time.substring(0, 19);
	}

	/**
	* ���ϵͳ��ǰ���ڡ�YYYY-MM-DD��
	* @return java.lang.String
	*/
	public static String getDT() {
		java.util.Date dt = new java.util.Date();
		java.sql.Timestamp ts = new java.sql.Timestamp(dt.getTime());
		String time = ts.toString();
		return time.substring(0, 10);
	}

	/**
	* ���ϵͳ��ǰ���ڡ�YYYYMMDD��
	* @return java.lang.String
	*/
	public static String getDTStr() {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");

		return format.format(new java.util.Date());
	}

	/**
	* ���ϵͳ��ǰ���ڡ�YYYYMMDDhhmmss��
	* @return java.lang.String
	*/
	public static String getTimeStr() {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");

		return format.format(new java.util.Date());
	}
	
	
	/**
	 * �õ�ϵͳ��ǰ����yyyyMMddHHmmssSSSS+2λ�����
	 * @return
	 */
	public static String getTimeRandomStr() {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSSS");
		String random = new Double(Math.random()*1000).toString().substring(0,2);

		return format.format(new java.util.Date())+random;
	}

	/**
	* ���ϵͳ��ǰʱ�䡰hhmmss��
	* @return java.lang.String
	*/
	public static String getTimeOnly() {
		SimpleDateFormat format = new SimpleDateFormat("HHmmss");

		return format.format(new java.util.Date());
	}

	/**
	* ���ָ�����ڱ����Ĺ̶���ʽ��YYYY-MM-DD��
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
	* ���ϵͳ��ǰ��ݡ�YYYY��
	* @return java.lang.String
	*/
	public static String getYear() {
		return getDT().substring(0, 4);
	}

	/**
	 * ��ȡ��ϵͳ��ǰ���·�YYYYMM
	 * @return
	 */
	public static String getMonth(){
		return getDT().substring(0,6);
	}

	/****
	* ��õ�ǰʱ��
	* @return ��ǰʱ������׼ʱ���΢����
	*/
	public static long getTimeInMillion() {

		return System.currentTimeMillis();
	}

	/**
		* ������ʱ����и�ʽ�����,�����ʽ"2005��1��1�� 22:11:30"
		* @param ll java.lang.String
		* @return java.lang.String
		*/
	public static String formatDateTime(String dt) {
		if (dt == null || dt.trim().length()<14 || dt.trim().equals("00000000000000"))
			return "";

		return dt.substring(0, 4)
			+ "��"
			+ Integer.parseInt(dt.substring(4, 6))
			+ "��"
			+ Integer.parseInt(dt.substring(6, 8))
			+ "�� "
			+ dt.substring(8, 10)
			+ ":"
			+ dt.substring(10, 12)
			+ ":"
			+ dt.substring(12);

	}

	/**
		* �����ڽ��и�ʽ�����,�����ʽ"2005��1��1��"
		* @param ll java.lang.String
		* @return java.lang.String
		*/
	public static String formatDate(String dt) {
		if (dt == null || dt.trim().length()<8 || dt.trim().equals("00000000"))
			return "";
		else
			return dt.substring(0, 4)
				+ "��"
				+ Integer.parseInt(dt.substring(4, 6))
				+ "��"
				+ Integer.parseInt(dt.substring(6, 8))
				+ "�� ";

	}

	/**
		* ������ʱ����и�ʽ�����,�����ʽ"2005��01��01�� 22:11:30"
		* @param dt-java.lang.String
		* @return java.lang.String
		*/
	public static String formatDateTime1(String dt) {
		if (dt == null || dt.trim().length()<14 || dt.trim().equals("00000000000000"))
			return "";

		return dt.substring(0, 4)
			+ "��"
			+ dt.substring(4, 6)
			+ "��"
			+ dt.substring(6, 8)
			+ "�� "
			+ dt.substring(8, 10)
			+ ":"
			+ dt.substring(10, 12)
			+ ":"
			+ dt.substring(12);

	}


	/**
		* ��ʱ����и�ʽ�����,�����ʽ"22:11:30"
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
	 * ���ݴ������ڡ�YYYYMM�������ĩ���ڡ�YYYYMMDD��
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
	 * ��õ�ǰϵͳ���ڵ��ϸ������ڡ�YYYYMM��
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
	 * ��ñȵ�ǰϵͳ������n���ϵͳ���ڡ�YYYYMMDD��
	 * @param   year - ����
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
	* ��ñ�ָ��������n���ϵͳ���ڡ�YYYYMMDD��
	* @param   dt-ָ������
	* @param   days - ����
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
	* ��ñ�ָ��������n���ϵͳ���ڡ�YYYYMMDD��
	* @param   dt-ָ������
	* @param   days - ����
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
	* ��ñ�ϵͳ��ǰ������n���ϵͳ���ڡ�YYYYMMDD��
	* @param   days - ����
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
	* ��ñ�ϵͳ��ǰ������n���ϵͳ���ڡ�YYYYMMDD��
	* @param   days - ����
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
	* ����ָ����ʽ��ñ�ϵͳ��ǰ������n���ϵͳ����
	* @param   days - ����
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
	* ��ñ�ָ��������n�µ����ڡ�YYYYMMDD��
	* @param   dt-ָ������
	* @param   month - ����
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
	* ��ñ�ָ��������n�µ����ڡ�YYYYMMDD��
	* @param   dt-ָ������
	* @param   month - ����
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
	* ��ñȵ�ǰϵͳ������n�µ����ڡ�YYYYMMDD��
	*
	* @param   month - ����
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
	* ��ñȵ�ǰϵͳ������n�µ����ڡ�YYYYMMDD��
	*
	* @param   month - ����
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
     * ���ƽ̨ϵͳʱ��
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
	 * ��yyyyMMdd��ʽ���ַ���ת��ΪDate��ʽ
	 * @param s Ҫת�����ַ���
	 * @return Date��ʽ����
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
	 * ��ø�����������֮�����������
	 *
	 * @param from ��ʼʱ��
	 * @param to ����ʱ��
	 * @return ����
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
	 * ��õ�ǰϵͳʱ�䣬��ȷ������
	 * 
	 * @param   
	 * @param  
	 * @return ��ǰϵͳʱ�� ����
	 */	
	public static String getCurrDateMills(){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmssSSS");

		return formatter.format(new java.util.Date());
		
	}
	
	
	/**
	* @author ljp
	* ��õ�ǰϵͳʱ�䣬��ȷ������
	* 
	* ���ϵͳ��ǰ���ڡ�YYYYMMDDhhmmssSS��
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
