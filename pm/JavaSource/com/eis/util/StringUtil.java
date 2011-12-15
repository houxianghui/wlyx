
/*********************************************************
 * File:StringUtil.java
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

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 说明：该类实现对字符串的操作
 * 
 */

public final class StringUtil {

	/**
	 * 
	 */
	public StringUtil() {
		super();
	}

	/** 
	 * 在字符串前添空格补位
	 * @param str java.lang.String
	 * @param len ：需要补充的长度
	 * @return java.lang.String
	 */
	public static String addSpaceB(String str, int len) {

		String tmp = null;
		try {
			int number = Integer.parseInt(str);
			tmp = String.valueOf(number);
			for (int i = 0; i < len; i++)
				tmp = " " + tmp;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return tmp;
	}

	/** 
	 * 在字符串后添空格补位
	 * @param str java.lang.String
	 * @param len ：需要补充的长度
	 * @return java.lang.String
	 */
	public static String addSpaceA(String str, int len) {

		String tmp = null;
		try {
			int number = Integer.parseInt(str);
			tmp = String.valueOf(number);
			for (int i = 0; i < len; i++)
				tmp = tmp + " ";
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return tmp;
	}
	
	
	/** 
	 * 在字符串前添0补位
	 * @param str java.lang.String
	 * @param len ：需要补充的长度
	 * @return java.lang.String
	 */
	public static String addZeroB(String str, int len) {

		String tmp = null;
		try {
			int number = Integer.parseInt(str);
			tmp = String.valueOf(number);
			for (int i = 0; i < len; i++)
				tmp = "0" + tmp;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return tmp;
	}

	/** 
	 * 在字符串后添0补位
	 * @param str java.lang.String
	 * @param len ：需要补充的长度
	 * @return java.lang.String
	 */
	public static String addZeroA(String str, int len) {

		String tmp = null;
		try {
			int number = Integer.parseInt(str);
			tmp = String.valueOf(number);
			for (int i = 0; i < len; i++)
				tmp = tmp + "0";
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return tmp;
	}

	/** 
	 * 将字符串添0补位
	 * @param str java.lang.String
	 * @param len ：总长度
	 * @return java.lang.String
	 */
	public static String addZero(String str, int len) {

		String tmp = null;
		try {
			if (null == str || str.trim().length()<=0) {
				tmp = "";
				for (int i = 0; i < len; i++)
					tmp = "0" + tmp;

			} else {
				int number = Integer.parseInt(str);
				tmp = String.valueOf(number);
				int num = len - tmp.length();
				for (int i = 0; i < num; i++)
					tmp = "0" + tmp;
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return tmp;
	}
	
	
	/** 
	 * 将15位身份证转换成18位
	 * @param s15 java.lang.String
	 * @return java.lang.String
	 */
	public static String trans15To18(String s15) throws Exception {
		if (s15 == null) {
			throw new Exception("trans15To18(): Error input ID number: ID number is null!");
		}
		if (s15.length() != 15 && s15.length() != 18) {
			throw new Exception(
				"trans15To18(): Error input ID number("
					+ s15
					+ "): ID.length()!=15 && !=18");
		} else {
			char ch;
			char[] chA = s15.toCharArray(); //15位身份证号码
			int i = 0;

			//数据合法性检验
			for (i = 0; i < chA.length; i++) {
				if (chA[i] < '0' || chA[i] > '9') {
					if (i != 17) {
						throw new Exception(
							"trans15To18(): Error input ID number("
								+ s15
								+ "): is not number!");
					} else if (chA[i] != 'x' && chA[i] != 'X') {
						throw new Exception(
							"trans15To18(): Error input ID number("
								+ s15
								+ "): is not number!");
					}
				}
			}

			//如果s15是18位的身份证，不处理返回该值。
			if (s15.length() == 18) {
				return s15;
			}

			/** int [18] nPower={7,9,10,5,8,4,2,1,6,3,7,9,10,5,8,4,2,1};
			* 生成 nPower[18] 的算法：2^n % 11 (n=17,16,...,1,0)
			* 因为 2*9+1*9=11; 11%11=0。
			* 生成 15位身份证号码chA[15] 对应的nPower[15]为：
			*/

			int[] nPower = { 7, 9, 10, 5, 8, 4, 6, 3, 7, 9, 10, 5, 8, 4, 2 };
			int num = 0;
			for (i = 0; i < 15; i++) {
				num += nPower[i] * (chA[i] - '0');
			}
			num %= 11;
			switch (num) {
				case 0 :
					ch = '1';
					break;
				case 1 :
					ch = '0';
					break;
				case 2 :
					ch = 'X';
					break;
				default :
					ch = (char) ('0' + (12 - num));
					break;
			}
			return s15.substring(0, 6) + "19" + s15.substring(6) + ch;
		}
	}

	/** 
	 * 将18位身份证转换成15位
	 * @param s18 java.lang.String
	 * @return java.lang.String
	 */

	public static String trans18To15(String s18) throws Exception {

		if (s18 == null) {
			throw new Exception("trans18To15(): Error input ID number: ID number is null!");
		}

		if (s18.length() != 15 && s18.length() != 18) {
			throw new Exception(
				"trans15To18(): Error input ID number("
					+ s18
					+ "): ID.length()!=15 && !=18");
		} else {

			if (s18.length() == 15)
				return s18;
			else
				return s18.substring(0, 6) + s18.substring(8, 17);

		}

	}

	/***************************************************************************
	 * Function Name : split
	 *  - 将指定的字符串按指定的分隔符转换成字符串数组
	 *    如：splitBy("ItemA,ItemB,ItemC", ",")，将返回一个字符串数组：
	 *    {"ItemA", "ItemB", "ItemC"}
	 * 
	 * @param strSource
	 *  - 将要被转换成字符串数组的字符串
	 * @param strReg
	 *  - split函数根据strReg将字符串分解
	 * @return String[]
	 *  - 字符串数组
	 **************************************************************************/
	public static String[] split(String strSource, String strReg) {
		if (strSource == null)
			return null;
		if (strReg == null || strReg.equals(""))
			return new String[] { strSource };

		ArrayList arrLst = new ArrayList();
		splitA(strSource, strReg, arrLst);

		String arrRslt[] = new String[arrLst.size()];
		return (String[]) arrLst.toArray(arrRslt);
	}

	/***************************************************************************
	 * Function Name : splitA
	 *  - 将指定的字符串按指定的分割字符串分解后，将分解后的结果保存到ArrayList
	 *    对象中，该方法使用递归实现
	 *
	 * @param strSource
	 *  - 被分割的字符串
	 * @param strReg
	 *  - 分隔符
	 * @return ArrayList
	 *  - 分割后的结果
	 **************************************************************************/
	private static void splitA(
		String strSource,
		String strReg,
		ArrayList arrLst) {
		int iFind = strSource.indexOf(strReg);
		if (iFind == -1) {
			arrLst.add(strSource); // return strSource;
			return;
		}
		int nRegLen = strReg.length();
		int nSourceLen = strSource.length();
		arrLst.add(strSource.substring(0, iFind));

		splitA(
			strSource.substring(iFind + nRegLen, nSourceLen),
			strReg,
			arrLst);
	}

	/***************************************************************************
	 * Function Name : replace
	 *  - 将字符串中制定的子字符串用strReplace替换掉
	 *  - 如：replace("aaabbbccaa", "aa", "AA")将返回"AAabbccAA"
	 *
	 * @param strSource
	 *  - 目标字符串
	 * @param strReg
	 *  - 目标字符串中将要被替换掉的字符串
	 * @param strReplace
	 *  - strReg的替换者
	 * @return String
	 *  - 替换后的目标字符串
	 **************************************************************************/
	public static String replace(
		String strSource,
		String strReg,
		String strReplace) {
		int iFind = strSource.indexOf(strReg);
		if (iFind == -1)
			return strSource;
		int nRegLen = strReg.length();
		int nSourceLen = strSource.length();
		return strSource.substring(0, iFind)
			+ strReplace
			+ replace(
				strSource.substring(iFind + nRegLen, nSourceLen),
				strReg,
				strReplace);
	}

	/***************************************************************************
	 * Function Name : countInString
	 *  - 计算指定字符在指定字符传中出现的次数
	 *
	 * @param str
	 *  - 目标字符串
	 * @param find
	 *  - 所要查找的字符
	 * @return int
	 *  - find在str中的出现次数
	 **************************************************************************/
	public static int countInString(String str, char find) {
		int iFind = -1;
		int iCount = 0;
		if (str.indexOf(find) == -1)
			return 0;
		do {
			iCount++;
			iFind = str.indexOf(find);
			str = str.substring(iFind + 1, str.length());
		} while (str.indexOf(find) != -1);
		return iCount;
	}

	/***************************************************************************
	 * Function Name : isInArray
	 *  - 检查指定的字符串在指定的字符串数组中是否存在
	 *
	 * @param arrString
	 *  - 要比较的字符串数组
	 * @param strFind
	 *  - 所要查找的字符串
	 * @return
	 *  - 如果strFind存在于arrString中，则返回true，否则返回false
	 **************************************************************************/
	public static boolean isInArray(String[] arrString, String strFind) {
		for (int i = 0; i < arrString.length; i++) {
			if (arrString[i].equalsIgnoreCase(strFind))
				return true;
		}
		return false;
	}

	/***************************************************************************
	 * Function Name : isInString
	 *  - 查找指定的字符串是否在字符串中存在,该功能可以被countInString方法替代
	 *
	 * @param str
	 *  - 目标字符串
	 * @param find
	 *  - 所要查找的字符串
	 * @return boolean
	 **************************************************************************/
	public static boolean isInString(String str, String find) {
		boolean bValue = false;

		if (str.indexOf(find) != -1) {
			bValue = true;
		}
		return bValue;
	}

	/**
	 * 获得字符串对应的数据库字节长度
	 *  
	 * @param str 字符串
	 * @return int 数据库字节长度
	 */
	public static int getDBStrLength(String str) {

		if (str == null) {
			return 0;
		}

		return str.getBytes().length;
	}
	
	
	/**
	 * 对字符串进行转码
	 *  
	 * @param str 转码前的字符串
	 * @param originCharset  原字符串编码方式
	 * @param destCharset 转码后的字符串编码方式
	 * @return String 转码后的字符串
	 */	
	public static String transStr(String str,String originCharset,String destCharset) throws Exception {
		try {
			if (str != null)
				str = new String(str.getBytes(originCharset), destCharset);
		} catch (Exception ex) {
			SysLog.error("字符串转码失败：" + ex.getMessage());
		}
		return str;
	}
	
	/**
	 * Create on 2007-12-6 10:04:54 houxh
	 * 利用前补零获得给定长度length的字符串
	 * 
	 * @param s 要处理的字符串
	 * @param length 给定长度
	 * @return 处理后的字符串
	 */
	public static String getFixLengthCharWithPreZero(String s,int length){
		StringBuffer sb = new StringBuffer();
		for(int i = 0;i < length;i++){
			sb.append("0");
		}
		sb.append(s.trim());
		int i = sb.length();
		return sb.substring(i-length);
	}
	/**
	 * @author houxh 2008-1-30
	 * 根据卡片等级获得查询条件
	 * 	
	 * card_logo3 = 1普卡
	 * card_logo3 = 0金卡
	 * card_logo3 = 2白金卡
	 * 4 银联公务金卡
	 * AMED_CARD_NBR = 404119 or 403361 or 404121 or 404118 or  or 519412 
	 * or 519413 or 552599 or 558730
	 * AMED_CARD_NBR = 404120 or 404117 or 520082 or 520083
	 * 通过卡并判断
	 * 
	 * @param logo1 卡片等级
	 * @return 查询的SQL
	 */
	public static StringBuffer getCardLevelSql(String logo, String field) {
		final String NORMAL_CARD = "1";		//普卡
		final String GOLD_CARD = "0";			//金卡
		final String PLATINA_CARD = "2";		//白金卡
		final String DIAMOND_CARD = "3";		//钻石卡
		final String UP_OFFICAL_CARD = "4";	//银联公务金卡
		StringBuffer sb = new StringBuffer();
		if (logo == null || logo.trim().length() == 0) {
			return sb;	
		}
		sb.append(" and "+ field+" in(select AMCR_LB_LOGO from amcrr2 where AMCR_LB_QTRLY_AFF_FL in(");
		if (logo.trim().equals(NORMAL_CARD)) {
			sb.append("'M1','V1','M3','V3','C1','C3' ");
		} else if (logo.trim().equals(GOLD_CARD)) {
			sb.append("'M2','V2','C2'");
		} else if (logo.trim().equals(PLATINA_CARD)) {
			sb.append("'M4','V4','CP','VP','MM'");
		}else if(logo.trim().equals(UP_OFFICAL_CARD)){
			sb.append("'C2'");
		}else if(logo.trim().equals(DIAMOND_CARD)){
			sb.append("'CJ','VJ','MW'");
		}else{
			return new StringBuffer(" and 1=2 ");
		}
		sb.append(")) ");
		return sb;
	}

	/**
	 * @author houxh 2008-1-30
	 * card_logo2 = 01个人卡
	 * card_logo2 = 11商务卡
	 * @param logo 卡种
	 * @return 查询SQL
	 */
	public static String getCardTypeSql(final String logo, String field) {
		final String PERSONAL = "01";
		final String BUSSINESS = "11";
		if (logo == null || logo.trim().length() == 0) {
			return "";
		}
		if (logo.trim().equals(PERSONAL)) {
			return " and "+field+" in(select AMCR_LB_LOGO from amcrr2 where AMCR_LB_QTRLY_AFF_FL in('M1','M2','V1','V2','C1','C2','M4','V4','CP','CJ','MM','MW','VP','VJ')) ";
    
		} else if (logo.trim().equals(BUSSINESS)) {
			return " and "+field+" in(select AMCR_LB_LOGO from amcrr2 where AMCR_LB_QTRLY_AFF_FL in('V3','M3','C3')) ";
		}
		return "";
	}
	
	/**
	 * 用以消除中文空格或中英文混编空格,包括前空格和后空格及中间空格
	 * @param s
	 * @return
	 */
	public static String trim(String s){
		if(s == null){
			return "";
		}
		Pattern p = Pattern.compile("[　| ]*");
		Matcher m = p.matcher(s);
		if(m.find()){
			return m.replaceAll("");
		}
		return s;
	}
	public static String formatUser(String userName){
		return userName.replaceAll("\\d+", "");
	}
}
