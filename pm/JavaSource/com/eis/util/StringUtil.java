
/*********************************************************
 * File:StringUtil.java
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

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ˵��������ʵ�ֶ��ַ����Ĳ���
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
	 * ���ַ���ǰ��ո�λ
	 * @param str java.lang.String
	 * @param len ����Ҫ����ĳ���
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
	 * ���ַ�������ո�λ
	 * @param str java.lang.String
	 * @param len ����Ҫ����ĳ���
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
	 * ���ַ���ǰ��0��λ
	 * @param str java.lang.String
	 * @param len ����Ҫ����ĳ���
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
	 * ���ַ�������0��λ
	 * @param str java.lang.String
	 * @param len ����Ҫ����ĳ���
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
	 * ���ַ�����0��λ
	 * @param str java.lang.String
	 * @param len ���ܳ���
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
	 * ��15λ���֤ת����18λ
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
			char[] chA = s15.toCharArray(); //15λ���֤����
			int i = 0;

			//���ݺϷ��Լ���
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

			//���s15��18λ�����֤���������ظ�ֵ��
			if (s15.length() == 18) {
				return s15;
			}

			/** int [18] nPower={7,9,10,5,8,4,2,1,6,3,7,9,10,5,8,4,2,1};
			* ���� nPower[18] ���㷨��2^n % 11 (n=17,16,...,1,0)
			* ��Ϊ 2*9+1*9=11; 11%11=0��
			* ���� 15λ���֤����chA[15] ��Ӧ��nPower[15]Ϊ��
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
	 * ��18λ���֤ת����15λ
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
	 *  - ��ָ�����ַ�����ָ���ķָ���ת�����ַ�������
	 *    �磺splitBy("ItemA,ItemB,ItemC", ",")��������һ���ַ������飺
	 *    {"ItemA", "ItemB", "ItemC"}
	 * 
	 * @param strSource
	 *  - ��Ҫ��ת�����ַ���������ַ���
	 * @param strReg
	 *  - split��������strReg���ַ����ֽ�
	 * @return String[]
	 *  - �ַ�������
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
	 *  - ��ָ�����ַ�����ָ���ķָ��ַ����ֽ�󣬽��ֽ��Ľ�����浽ArrayList
	 *    �����У��÷���ʹ�õݹ�ʵ��
	 *
	 * @param strSource
	 *  - ���ָ���ַ���
	 * @param strReg
	 *  - �ָ���
	 * @return ArrayList
	 *  - �ָ��Ľ��
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
	 *  - ���ַ������ƶ������ַ�����strReplace�滻��
	 *  - �磺replace("aaabbbccaa", "aa", "AA")������"AAabbccAA"
	 *
	 * @param strSource
	 *  - Ŀ���ַ���
	 * @param strReg
	 *  - Ŀ���ַ����н�Ҫ���滻�����ַ���
	 * @param strReplace
	 *  - strReg���滻��
	 * @return String
	 *  - �滻���Ŀ���ַ���
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
	 *  - ����ָ���ַ���ָ���ַ����г��ֵĴ���
	 *
	 * @param str
	 *  - Ŀ���ַ���
	 * @param find
	 *  - ��Ҫ���ҵ��ַ�
	 * @return int
	 *  - find��str�еĳ��ִ���
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
	 *  - ���ָ�����ַ�����ָ�����ַ����������Ƿ����
	 *
	 * @param arrString
	 *  - Ҫ�Ƚϵ��ַ�������
	 * @param strFind
	 *  - ��Ҫ���ҵ��ַ���
	 * @return
	 *  - ���strFind������arrString�У��򷵻�true�����򷵻�false
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
	 *  - ����ָ�����ַ����Ƿ����ַ����д���,�ù��ܿ��Ա�countInString�������
	 *
	 * @param str
	 *  - Ŀ���ַ���
	 * @param find
	 *  - ��Ҫ���ҵ��ַ���
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
	 * ����ַ�����Ӧ�����ݿ��ֽڳ���
	 *  
	 * @param str �ַ���
	 * @return int ���ݿ��ֽڳ���
	 */
	public static int getDBStrLength(String str) {

		if (str == null) {
			return 0;
		}

		return str.getBytes().length;
	}
	
	
	/**
	 * ���ַ�������ת��
	 *  
	 * @param str ת��ǰ���ַ���
	 * @param originCharset  ԭ�ַ������뷽ʽ
	 * @param destCharset ת�����ַ������뷽ʽ
	 * @return String ת�����ַ���
	 */	
	public static String transStr(String str,String originCharset,String destCharset) throws Exception {
		try {
			if (str != null)
				str = new String(str.getBytes(originCharset), destCharset);
		} catch (Exception ex) {
			SysLog.error("�ַ���ת��ʧ�ܣ�" + ex.getMessage());
		}
		return str;
	}
	
	/**
	 * Create on 2007-12-6 10:04:54 houxh
	 * ����ǰ�����ø�������length���ַ���
	 * 
	 * @param s Ҫ������ַ���
	 * @param length ��������
	 * @return �������ַ���
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
	 * ���ݿ�Ƭ�ȼ���ò�ѯ����
	 * 	
	 * card_logo3 = 1�տ�
	 * card_logo3 = 0��
	 * card_logo3 = 2�׽�
	 * 4 ���������
	 * AMED_CARD_NBR = 404119 or 403361 or 404121 or 404118 or  or 519412 
	 * or 519413 or 552599 or 558730
	 * AMED_CARD_NBR = 404120 or 404117 or 520082 or 520083
	 * ͨ�������ж�
	 * 
	 * @param logo1 ��Ƭ�ȼ�
	 * @return ��ѯ��SQL
	 */
	public static StringBuffer getCardLevelSql(String logo, String field) {
		final String NORMAL_CARD = "1";		//�տ�
		final String GOLD_CARD = "0";			//��
		final String PLATINA_CARD = "2";		//�׽�
		final String DIAMOND_CARD = "3";		//��ʯ��
		final String UP_OFFICAL_CARD = "4";	//���������
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
	 * card_logo2 = 01���˿�
	 * card_logo2 = 11����
	 * @param logo ����
	 * @return ��ѯSQL
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
	 * �����������Ŀո����Ӣ�Ļ��ո�,����ǰ�ո�ͺ�ո��м�ո�
	 * @param s
	 * @return
	 */
	public static String trim(String s){
		if(s == null){
			return "";
		}
		Pattern p = Pattern.compile("[��| ]*");
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
