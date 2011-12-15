
/*********************************************************
 * File:Format.java
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

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.*;

import com.eis.db.DBUtil;

/**
 * 说明：该类实现对各种数字和字符串的格式化
 * 
 */

public final class Format {

	/**
	 * 
	 */
	public Format() {
		super();
	}
	public static String formatFenToYuan(double d){
		return formatLL(d/100);
	}
	/**根据给出的数值精确到元和数值要求最后长度转换格式，不足位数左补0,以分为单位
	 * @param data
	 * @param length
	 * @return
	 * @throws Exception
	 */
	public static String formatAmt(String amt,int length)throws Exception{
		String prefix = "";
		if(null != amt){
			BigDecimal amt1 = new BigDecimal(amt).multiply(new BigDecimal("100"));
			amt1 = amt1.setScale(0,BigDecimal.ROUND_FLOOR);
			amt = amt1.toString();
			for(int i=1;i<=length;i++){
				prefix = prefix+"0";
			}
			amt = (prefix+amt).substring((prefix+amt).length()-length);

		}
			
	
		return amt;
	}
	
	/**根据给出的V+数值精确到分和给出的数值固定长度返回保留两位小数的格式,精确到元
	 * @param data
	 * @param length
	 * @return
	 * @throws Exception
	 */
	public static String vFormatAmt(String amt,int length)throws Exception{
		if(null != amt){
			BigDecimal amt1 = new BigDecimal(amt).divide(new BigDecimal("100"),2,BigDecimal.ROUND_HALF_EVEN);
			amt = amt1.toString();
		}

		return amt;
	}
	/**根据给出的字符串和数值要求最后长度转换格式，不足位数右补空格.
	 * @param s
	 * @param length
	 * @return
	 * @throws Exception
	 */
	public static String formatString(String s,int length)throws Exception{
		String prefix = "";
		if(null != s){
			for(int i=1;i<=length;i++){
				prefix = prefix+" ";
			}
			s = (s+prefix).substring(0,length);
		}
		return s;
	}
	
	public static String formatDat(String data,int length)throws Exception{
		String prefix = "";
		if(null != data){
			for(int i=1;i<=length;i++){
				prefix = prefix+"0";
			}
			data = (prefix+data).substring((prefix+data).length()-length);
		}
		return data;
	}
	
	/**
	 * 对利率，金额格式化输出,输出格式"1000123.89"
	 * @param ll java.lang.String
	 * @return java.lang.String	 
	 */
	public static String formatLL(double ll) {
		java.text.DecimalFormat fm = new java.text.DecimalFormat("#######0.00");
		return fm.format(ll);

	}
	public static String formatPercent(double d){
		DecimalFormat df = new DecimalFormat("####0.00%");
		return df.format(d);
	}

	/**
	 * 对金额格式化输出,输出格式"1,000,123.89"
	 * @param ll java.lang.String
	 * @return java.lang.String  
	 */
	public static String formatSci(double ll) {
		java.text.DecimalFormat fm =
			new java.text.DecimalFormat("#########0.00");
		return fm.format(ll);

	}
	
	/**
	 * 对金额格式化输出,输出格式"1,000,123.89"
	 * @param ll java.lang.String
	 * @return java.lang.String  
	 */
	public static String formatSci(Double ll) {
		java.text.DecimalFormat fm =
			new java.text.DecimalFormat("#########0.00");
		return fm.format(ll);

	}

	/**
	  * 对金额格式化输出,输出格式"1000123.80",主要用于处理excel下载的问题
	  * @param ll java.lang.String
	  * @return java.lang.String  
	  */
	public static String formatCsv(double ll) {
		java.text.DecimalFormat fm = new java.text.DecimalFormat("#######0.00");
		return fm.format(ll);

	}
	/**
     * Create on 2007-1-5 15:00:30 Ranger
     * 将人民币金额转换成大写输出,1220344.89 转换为  壹佰贰拾贰万零参佰肆拾肆圆捌角玖分
     * 
     * @param input
     * @return
     */
    public static String formatCapR(String input){
		return getCapStrCH(input);
	}
	/**
	 * 将人民币金额转换成大写输出,1220344.89 转换为  壹佰贰拾贰万零参佰肆拾肆圆捌角玖分 
	 * 方法有Bug抛弃
	 * V1.1 增加处理负数的能力
	 * 
	 * @param ll java.lang.String
	 * @return java.lang.String  
	 */
	
	public static String formatCapR_Deprecated(String input) {
		String s1 = "零壹贰叁肆伍陆柒捌玖";
		String s4 = "分角整元拾佰仟万拾佰仟亿拾佰仟";
		String temp = "";
		String result = "";
		
		int flag = 0;
		if (input == null)
			return "输入字串空！";
		temp = input.trim();
		
		//判断是不是负数
		if(temp.charAt(0) == '-'){
		    temp = temp.substring(1);
		    flag = 1;
		}
		///~
		float f;
		try {
			f = Float.parseFloat(temp);
		} catch (Exception e) {
			return "输入字串不是数字串！";
		}
		int len = 0; //小数点的位置
		if (temp.indexOf(".") == -1)
			len = temp.length();
		else
			len = temp.indexOf(".");
		if (len > s4.length() - 3)
			return ("输入字串最大只能精确到仟亿，小数点后只能两位！");

		SysLog.debug("***小数点位置：***" + len);

		int n1 = 0; //输入字串的每位小写数字
		int n2 = 1;
		String num = ""; //大写数字
		String unit = ""; //单位
		
		
		for (int i = 0; i < temp.length(); i++) {
			if (i == len) {
				continue;
			}
			n1 = Integer.parseInt(String.valueOf(temp.charAt(i)));
			SysLog.debug("***charat(i):***" + n1);
			num = s1.substring(n1, n1 + 1);
			/*
			if (n1 == 0 && i < len - 1) {
				unit = "";
			} else if (n1 == 0 && i == len - 1 && len != 1) {
				num = "";
				unit = "元";
			} else {
				n1 = len - i + 2;
				unit = s4.substring(n1, n1 + 1);
			}
			
			n1 = len - i + 2;
			unit = s4.substring(n1, n1 + 1);
			*/

			if (n1 == 0 && i < len - 1) {
				num = "";
				unit = "";
				n2 = Integer.parseInt(String.valueOf(temp.charAt(i + 1)));
				if (n2 != 0) {
					num = "零";
					unit = "";
				}
				if (i == len - 5) {
					num = "";
					unit = "万";
				}
				if (i == len - 9) {
					num = "";
					unit = "亿";
				}
			} else if (n1 == 0 && i == len - 1 && len != 1) {
				num = "";
				unit = "元";
			} else {
				n1 = len - i + 2;
				unit = s4.substring(n1, n1 + 1);
			}

			result = result.concat(num).concat(unit);
		}
		if(flag == 1){
			return "负"+result;
		}
		return result;
	}
	public static String formatCapU(String input){
		return getCapStrUS(input);
	}
	/**
	 * 将美元金额转换成大写输出,1220344.89 转换为  壹佰贰拾贰万零参佰肆拾肆圆捌角玖分 
	 * 有Bug抛弃使用这个方法
	 * V1.1 2006-11-23 houxh 增加处理负数功能
	 * 
	 * @param ll java.lang.String
	 * @return java.lang.String  
	 */
	public static String formatCapU_deprecated(String input) {
		String s1 = "零壹贰叁肆伍陆柒捌玖";
		String s4 = "分角整元拾佰仟万拾佰仟亿拾佰仟";
		String temp = "";
		String result = "";
		int flag = 0;
		if (input == null)
			return "输入字串空！";
		temp = input.trim();
		//判断输入的是不是负数
		if(temp.charAt(0)=='-'){
			temp = temp.substring(1);
			flag = 1;
		}
		float f;
		try {
			f = Float.parseFloat(temp);
		} catch (Exception e) {
			return "输入字串不是数字串！";
		}
		int len = 0; //小数点的位置
		if (temp.indexOf(".") == -1)
			len = temp.length();
		else
			len = temp.indexOf(".");
		if (len > s4.length() - 3)
			return ("输入字串最大只能精确到仟亿，小数点后只能两位！");

		SysLog.debug("***小数点位置：***" + len);

		int n1 = 0; //输入字串的每位小写数字
		int n2 = 1;
		String num = ""; //大写数字
		String unit = ""; //单位

		for (int i = 0; i < temp.length() - 2; i++) {
			if (i == len) {
				continue;
			}
			n1 = Integer.parseInt(String.valueOf(temp.charAt(i)));
			SysLog.debug("***charat(i):***" + n1);
			num = s1.substring(n1, n1 + 1);

			if (n1 == 0 && i < len - 1) {
				num = "";
				unit = "";
				n2 = Integer.parseInt(String.valueOf(temp.charAt(i + 1)));
				if (n2 != 0) {
					num = "零";
					unit = "";
				}
				if (i == len - 5) {
					num = "";
					unit = "万";
				}
				if (i == len - 9) {
					num = "";
					unit = "亿";
				}
			} else if (i == len - 1) {
				unit = "美元";
				if (n1 == 0 && len != 1) {
					num = "";
				}
			} else {
				n1 = len - i + 2;
				unit = s4.substring(n1, n1 + 1);
			}

			result = result.concat(num).concat(unit);
		}

		n1 = Integer.parseInt(String.valueOf(temp.charAt(temp.length() - 2)));
		SysLog.debug("***charat(i):***" + n1);
		num = s1.substring(n1, n1 + 1);

		if (n1 != 0) {
			unit = "拾";
		} else {
			num = "";
			unit = "";
		}
		result = result.concat(num).concat(unit);

		n2 = Integer.parseInt(String.valueOf(temp.charAt(temp.length() - 1)));
		SysLog.debug("***charat(i):***" + n2);

		if (n1 != 0 && n2 == 0) {
			num = "";
		} else {
			num = s1.substring(n2, n2 + 1);
		}
		unit = "美分";

		result = result.concat(num).concat(unit);
		if(flag == 1){
			return "负"+result;
		}
		return result;
	}

	/**
	 * 对还款卡号进行有效性校验
	 * @param ll java.lang.String
	 * @return java.lang.boolean
	 */
	public static boolean checkCardno(String cardno) {

		int len = cardno.length();
		int checkb;

		if (len == 19) {

			checkb = Integer.parseInt(String.valueOf(cardno.charAt(18)));
			SysLog.debug("***19位卡号第一位：***" + checkb);

			//借记卡号前四位校验
			String checkf = cardno.substring(0, 4);
			SysLog.debug("***19位卡号前四位:***" + checkf);
			if (!checkf.equals("9559") && !checkf.equals("6228")) {
				return false;
			}

		} else {

			checkb = Integer.parseInt(String.valueOf(cardno.charAt(15)));
			SysLog.debug("***16位卡号第一位：***" + checkb);

			//准贷记卡号前五位校验
			String checkf = cardno.substring(0, 5);
			String checks = cardno.substring(0, 6);
			SysLog.debug("***16位卡号前五位:***" + checkf);
			SysLog.debug("***16位卡号前六位:***" + checks);
			if (!checkf.equals("53591") && !checkf.equals("49102") && !checks.equals("622830") && !checks.equals("622820")) {
				return false;
			}

		}

		//借记卡号、准贷记卡号第一位校验
		int[] temp = new int[len];
		for (int i = 0; i < len - 1; i++) {
			temp[i] = Integer.parseInt(String.valueOf(cardno.charAt(i)));
			SysLog.debug("***temp[" + i + "]***" + temp[i]);
		}
		int res = 0;
		for (int i = len - 3; i >= 0; i = i - 2) {
			res = res + temp[i];
		}
		for (int i = len - 2; i >= 0; i = i - 2) {
			int a = temp[i] * 2;
			int sh = a / 10;
			int yu = a % 10;
			res = res + sh + yu;
		}
		String s = String.valueOf(res);
		SysLog.debug("***结果串:***" + s);
		res = 10 - Integer.parseInt(String.valueOf(s.charAt(s.length() - 1)));
		SysLog.debug("***结果串的第一位:***" + res);	
		SysLog.debug("***改造后的结果串的第一位:***" + Integer.toString(res%10));		

		if ((res%10) != checkb) {
			return false;
		}

		return true;

	}
	/**
     * Create on 2007-1-5 14:10:24 Ranger
     * 
     * 格式化输入的字符串,针对金额转换，小数点后两位自动四舍五入计算
     * 
     * @param s 要转换的金额
     * @return 转换后的字符串
     */
    public static String getCapStrCH(String s){
		s = s.trim();
		if(s.charAt(0) == '-'){
			s = getR(s.substring(1));
			return "负"+s;
		}else{
			return getR(s);
		}
	}
	/**
     * Create on 2007-1-5 14:43:05 Ranger
     * 
     * 把输入的双精度浮点数转换为大写，并自动进行四舍五入的小数点后两位计算
     * 
     * @param d 要转换的金额
     * @return 转换后的字符串
     */
    public static String getCapStrCH(double d){
		return getCapStrCH(String.valueOf(d));
	}
	/**
     * Create on 2007-1-5 15:44:58 Ranger
     * 美元大小写转换，可以直接调用，小数点两位后四舍五入
     * 
     * @param s 要转换的金额
     * @return 转换后的字符串
     */
    public static String getCapStrUS(String s){
		String us = getCapStrCH(s);
		if(us.indexOf("角") != -1 || us.indexOf("分") != -1){
			if(us.lastIndexOf("零")>us.indexOf("元")){
				us = us.substring(0,us.indexOf("元"))+"美元零";
			}else{
				us = us.substring(0,us.indexOf("元"))+"美元";
			}
			
			s = s.trim();
			s = s.substring(s.indexOf("."));
			double d = Double.parseDouble(s);
			int t = (int)Math.round(d*100);
			s = getCapStrCH(t);
			s = s.substring(0,s.indexOf("元"))+"美分";
			us += s;
			s = null;
		}else{
			us = us.substring(0,us.indexOf("元"))+"美元整";
		}
		
		return us;
	}
	/**
     * Create on 2007-1-5 15:47:31 Ranger
     * 美元大小写转换，可以直接调用，小数点两位后四舍五入
     * 
     * @param d double金额
     * @return 转换后的字符串
     */
    public static String getCapStrUS(double d){
		return getCapStrUS(String.valueOf(d));
	}
	/**
     * Create on 2007-1-5 14:41:16 Ranger
     * 
     * 私有方法，不允许直接调用，包括方法内部！需要时请调用getCapStr()方法
     * 为简化方法把负数判断放到方法外面
     * 
     * @param s
     * @return
     */
    private static String getR(String s){
		
		String v = "零壹贰叁肆伍陆柒捌玖";
		String key = "分角整元拾佰仟万拾佰仟亿拾佰仟万";

		final int YUAN = key.indexOf("元");
		final int WAN = key.indexOf("万");
		final int YI = key.indexOf("亿");
		final int ZHENG = key.indexOf("整");
		StringBuffer sb = new StringBuffer();
		
		double d = 0;
		int length = 0;
		boolean zf = false;	//ZeroFlag标志位
		boolean tf = false;	//TabFlag单位标志位

		try{
			d = Double.parseDouble(s);
		}catch(Exception e){
			SysLog.debug("要转换的不是数字！错误发生在"+ "Format.getU()");
			return "";
		}
		d = Math.round(d*100)/100.0;//小数点后两位四舍五入
		if(d == 0){
			return "零元整";
		}
		
		NumberFormat format = new DecimalFormat("#####.00");
		s = format.format(d);

		if(s.length() > key.length()){
			SysLog.debug("数字最大支持9999999999999.99！错误发生在"+ "Format.getU()");
			return "";
		}
		int dotPosition = s.indexOf(".");//小数点的位置

		for(int i = s.length()-1,j = 0;i >= 0;i--,j++){
			if(j == dotPosition){	//在小数点的位置清除所有标志位			
				tf = zf =false;			
			}else{
				int t = s.charAt(j)-'0';
				if(t == 0){
					if(((i == WAN || i== YI) && tf == false) || i == YUAN){//判断是否需要加单位
						sb.append(key.charAt(i));
						tf = true;
					}else{						
						zf = true;						
					}
				}else{
					tf = false;
					if(((i == WAN || i== YI) && tf == false) || i == YUAN){
						tf = true;	//清标志位
					}
					if(zf == true){
						sb.append(v.charAt(0));//判断是否需要加“零”
					}
					zf = false;
					sb.append(v.charAt(t));
					sb.append(key.charAt(i));
				}
			}
		}
		//判断是否需要加整
		if(zf == true && s.charAt(s.length()-2)=='0'){
			sb.append(key.charAt(ZHENG));
		}
		return sb.toString();		
	}
	public static void main(String[] args) {

		String s = "00100000000.05";
		//String result = formatCapR(s);
		String result = getCapStrCH(s);
		String result2 = getCapStrUS(s);
		
		String result1 = formatCapU(s);
		System.out.println("***大写金额：***" + result);
		System.out.println("***美元大写金额：***" + result1);
		System.out.println(result2);
	}
	/**根据交易金额和交易币种，读SMCU中小数位数，转换金额由分到元。
	 * @param amt
	 * @param currcode
	 * @return
	 * @throws Exception
	 */
	public static double getTranAmt(double amt,String currcode) throws Exception{
		StringBuffer sb = new StringBuffer("select SMCU_CURRENCY_NOD  from smcu where SMCU_CURRENCY_CODE='"+currcode+"' and SMCU_REC_STAT='1' and SMCU_ONLINE_STATUS='99'");
		DBUtil db = new DBUtil();
		String nod_result = "-1";
		try{
			nod_result = db.sqlQuerySingle(sb.toString());
		}finally{
			db.close();
		}
		int nod = Integer.parseInt(nod_result);
		BigDecimal tranamt = new BigDecimal(amt).divide(new BigDecimal(Math.pow(10,nod)),2,BigDecimal.ROUND_HALF_EVEN);

		return tranamt.doubleValue();
	}
	public static String formatTime(String time){
		String ss = time.substring(time.length()-2);
		String mm = time.substring(time.length()-4, time.length()-2);
		String hh = time.substring(0,time.length()-4);
		return hh+":"+mm+":"+ss;
		
	}


}
