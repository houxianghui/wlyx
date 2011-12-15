
/*********************************************************
 * File:Format.java
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

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.*;

import com.eis.db.DBUtil;

/**
 * ˵��������ʵ�ֶԸ������ֺ��ַ����ĸ�ʽ��
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
	/**���ݸ�������ֵ��ȷ��Ԫ����ֵҪ����󳤶�ת����ʽ������λ����0,�Է�Ϊ��λ
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
	
	/**���ݸ�����V+��ֵ��ȷ���ֺ͸�������ֵ�̶����ȷ��ر�����λС���ĸ�ʽ,��ȷ��Ԫ
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
	/**���ݸ������ַ�������ֵҪ����󳤶�ת����ʽ������λ���Ҳ��ո�.
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
	 * �����ʣ�����ʽ�����,�����ʽ"1000123.89"
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
	 * �Խ���ʽ�����,�����ʽ"1,000,123.89"
	 * @param ll java.lang.String
	 * @return java.lang.String  
	 */
	public static String formatSci(double ll) {
		java.text.DecimalFormat fm =
			new java.text.DecimalFormat("#########0.00");
		return fm.format(ll);

	}
	
	/**
	 * �Խ���ʽ�����,�����ʽ"1,000,123.89"
	 * @param ll java.lang.String
	 * @return java.lang.String  
	 */
	public static String formatSci(Double ll) {
		java.text.DecimalFormat fm =
			new java.text.DecimalFormat("#########0.00");
		return fm.format(ll);

	}

	/**
	  * �Խ���ʽ�����,�����ʽ"1000123.80",��Ҫ���ڴ���excel���ص�����
	  * @param ll java.lang.String
	  * @return java.lang.String  
	  */
	public static String formatCsv(double ll) {
		java.text.DecimalFormat fm = new java.text.DecimalFormat("#######0.00");
		return fm.format(ll);

	}
	/**
     * Create on 2007-1-5 15:00:30 Ranger
     * ������ҽ��ת���ɴ�д���,1220344.89 ת��Ϊ  Ҽ�۷�ʰ������ΰ���ʰ��Բ�ƽǾ���
     * 
     * @param input
     * @return
     */
    public static String formatCapR(String input){
		return getCapStrCH(input);
	}
	/**
	 * ������ҽ��ת���ɴ�д���,1220344.89 ת��Ϊ  Ҽ�۷�ʰ������ΰ���ʰ��Բ�ƽǾ��� 
	 * ������Bug����
	 * V1.1 ���Ӵ�����������
	 * 
	 * @param ll java.lang.String
	 * @return java.lang.String  
	 */
	
	public static String formatCapR_Deprecated(String input) {
		String s1 = "��Ҽ��������½��ƾ�";
		String s4 = "�ֽ���Ԫʰ��Ǫ��ʰ��Ǫ��ʰ��Ǫ";
		String temp = "";
		String result = "";
		
		int flag = 0;
		if (input == null)
			return "�����ִ��գ�";
		temp = input.trim();
		
		//�ж��ǲ��Ǹ���
		if(temp.charAt(0) == '-'){
		    temp = temp.substring(1);
		    flag = 1;
		}
		///~
		float f;
		try {
			f = Float.parseFloat(temp);
		} catch (Exception e) {
			return "�����ִ��������ִ���";
		}
		int len = 0; //С�����λ��
		if (temp.indexOf(".") == -1)
			len = temp.length();
		else
			len = temp.indexOf(".");
		if (len > s4.length() - 3)
			return ("�����ִ����ֻ�ܾ�ȷ��Ǫ�ڣ�С�����ֻ����λ��");

		SysLog.debug("***С����λ�ã�***" + len);

		int n1 = 0; //�����ִ���ÿλСд����
		int n2 = 1;
		String num = ""; //��д����
		String unit = ""; //��λ
		
		
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
				unit = "Ԫ";
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
					num = "��";
					unit = "";
				}
				if (i == len - 5) {
					num = "";
					unit = "��";
				}
				if (i == len - 9) {
					num = "";
					unit = "��";
				}
			} else if (n1 == 0 && i == len - 1 && len != 1) {
				num = "";
				unit = "Ԫ";
			} else {
				n1 = len - i + 2;
				unit = s4.substring(n1, n1 + 1);
			}

			result = result.concat(num).concat(unit);
		}
		if(flag == 1){
			return "��"+result;
		}
		return result;
	}
	public static String formatCapU(String input){
		return getCapStrUS(input);
	}
	/**
	 * ����Ԫ���ת���ɴ�д���,1220344.89 ת��Ϊ  Ҽ�۷�ʰ������ΰ���ʰ��Բ�ƽǾ��� 
	 * ��Bug����ʹ���������
	 * V1.1 2006-11-23 houxh ���Ӵ���������
	 * 
	 * @param ll java.lang.String
	 * @return java.lang.String  
	 */
	public static String formatCapU_deprecated(String input) {
		String s1 = "��Ҽ��������½��ƾ�";
		String s4 = "�ֽ���Ԫʰ��Ǫ��ʰ��Ǫ��ʰ��Ǫ";
		String temp = "";
		String result = "";
		int flag = 0;
		if (input == null)
			return "�����ִ��գ�";
		temp = input.trim();
		//�ж�������ǲ��Ǹ���
		if(temp.charAt(0)=='-'){
			temp = temp.substring(1);
			flag = 1;
		}
		float f;
		try {
			f = Float.parseFloat(temp);
		} catch (Exception e) {
			return "�����ִ��������ִ���";
		}
		int len = 0; //С�����λ��
		if (temp.indexOf(".") == -1)
			len = temp.length();
		else
			len = temp.indexOf(".");
		if (len > s4.length() - 3)
			return ("�����ִ����ֻ�ܾ�ȷ��Ǫ�ڣ�С�����ֻ����λ��");

		SysLog.debug("***С����λ�ã�***" + len);

		int n1 = 0; //�����ִ���ÿλСд����
		int n2 = 1;
		String num = ""; //��д����
		String unit = ""; //��λ

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
					num = "��";
					unit = "";
				}
				if (i == len - 5) {
					num = "";
					unit = "��";
				}
				if (i == len - 9) {
					num = "";
					unit = "��";
				}
			} else if (i == len - 1) {
				unit = "��Ԫ";
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
			unit = "ʰ";
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
		unit = "����";

		result = result.concat(num).concat(unit);
		if(flag == 1){
			return "��"+result;
		}
		return result;
	}

	/**
	 * �Ի���Ž�����Ч��У��
	 * @param ll java.lang.String
	 * @return java.lang.boolean
	 */
	public static boolean checkCardno(String cardno) {

		int len = cardno.length();
		int checkb;

		if (len == 19) {

			checkb = Integer.parseInt(String.valueOf(cardno.charAt(18)));
			SysLog.debug("***19λ���ŵ�һλ��***" + checkb);

			//��ǿ���ǰ��λУ��
			String checkf = cardno.substring(0, 4);
			SysLog.debug("***19λ����ǰ��λ:***" + checkf);
			if (!checkf.equals("9559") && !checkf.equals("6228")) {
				return false;
			}

		} else {

			checkb = Integer.parseInt(String.valueOf(cardno.charAt(15)));
			SysLog.debug("***16λ���ŵ�һλ��***" + checkb);

			//׼���ǿ���ǰ��λУ��
			String checkf = cardno.substring(0, 5);
			String checks = cardno.substring(0, 6);
			SysLog.debug("***16λ����ǰ��λ:***" + checkf);
			SysLog.debug("***16λ����ǰ��λ:***" + checks);
			if (!checkf.equals("53591") && !checkf.equals("49102") && !checks.equals("622830") && !checks.equals("622820")) {
				return false;
			}

		}

		//��ǿ��š�׼���ǿ��ŵ�һλУ��
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
		SysLog.debug("***�����:***" + s);
		res = 10 - Integer.parseInt(String.valueOf(s.charAt(s.length() - 1)));
		SysLog.debug("***������ĵ�һλ:***" + res);	
		SysLog.debug("***�����Ľ�����ĵ�һλ:***" + Integer.toString(res%10));		

		if ((res%10) != checkb) {
			return false;
		}

		return true;

	}
	/**
     * Create on 2007-1-5 14:10:24 Ranger
     * 
     * ��ʽ��������ַ���,��Խ��ת����С�������λ�Զ������������
     * 
     * @param s Ҫת���Ľ��
     * @return ת������ַ���
     */
    public static String getCapStrCH(String s){
		s = s.trim();
		if(s.charAt(0) == '-'){
			s = getR(s.substring(1));
			return "��"+s;
		}else{
			return getR(s);
		}
	}
	/**
     * Create on 2007-1-5 14:43:05 Ranger
     * 
     * �������˫���ȸ�����ת��Ϊ��д�����Զ��������������С�������λ����
     * 
     * @param d Ҫת���Ľ��
     * @return ת������ַ���
     */
    public static String getCapStrCH(double d){
		return getCapStrCH(String.valueOf(d));
	}
	/**
     * Create on 2007-1-5 15:44:58 Ranger
     * ��Ԫ��Сдת��������ֱ�ӵ��ã�С������λ����������
     * 
     * @param s Ҫת���Ľ��
     * @return ת������ַ���
     */
    public static String getCapStrUS(String s){
		String us = getCapStrCH(s);
		if(us.indexOf("��") != -1 || us.indexOf("��") != -1){
			if(us.lastIndexOf("��")>us.indexOf("Ԫ")){
				us = us.substring(0,us.indexOf("Ԫ"))+"��Ԫ��";
			}else{
				us = us.substring(0,us.indexOf("Ԫ"))+"��Ԫ";
			}
			
			s = s.trim();
			s = s.substring(s.indexOf("."));
			double d = Double.parseDouble(s);
			int t = (int)Math.round(d*100);
			s = getCapStrCH(t);
			s = s.substring(0,s.indexOf("Ԫ"))+"����";
			us += s;
			s = null;
		}else{
			us = us.substring(0,us.indexOf("Ԫ"))+"��Ԫ��";
		}
		
		return us;
	}
	/**
     * Create on 2007-1-5 15:47:31 Ranger
     * ��Ԫ��Сдת��������ֱ�ӵ��ã�С������λ����������
     * 
     * @param d double���
     * @return ת������ַ���
     */
    public static String getCapStrUS(double d){
		return getCapStrUS(String.valueOf(d));
	}
	/**
     * Create on 2007-1-5 14:41:16 Ranger
     * 
     * ˽�з�����������ֱ�ӵ��ã����������ڲ�����Ҫʱ�����getCapStr()����
     * Ϊ�򻯷����Ѹ����жϷŵ���������
     * 
     * @param s
     * @return
     */
    private static String getR(String s){
		
		String v = "��Ҽ��������½��ƾ�";
		String key = "�ֽ���Ԫʰ��Ǫ��ʰ��Ǫ��ʰ��Ǫ��";

		final int YUAN = key.indexOf("Ԫ");
		final int WAN = key.indexOf("��");
		final int YI = key.indexOf("��");
		final int ZHENG = key.indexOf("��");
		StringBuffer sb = new StringBuffer();
		
		double d = 0;
		int length = 0;
		boolean zf = false;	//ZeroFlag��־λ
		boolean tf = false;	//TabFlag��λ��־λ

		try{
			d = Double.parseDouble(s);
		}catch(Exception e){
			SysLog.debug("Ҫת���Ĳ������֣���������"+ "Format.getU()");
			return "";
		}
		d = Math.round(d*100)/100.0;//С�������λ��������
		if(d == 0){
			return "��Ԫ��";
		}
		
		NumberFormat format = new DecimalFormat("#####.00");
		s = format.format(d);

		if(s.length() > key.length()){
			SysLog.debug("�������֧��9999999999999.99����������"+ "Format.getU()");
			return "";
		}
		int dotPosition = s.indexOf(".");//С�����λ��

		for(int i = s.length()-1,j = 0;i >= 0;i--,j++){
			if(j == dotPosition){	//��С�����λ��������б�־λ			
				tf = zf =false;			
			}else{
				int t = s.charAt(j)-'0';
				if(t == 0){
					if(((i == WAN || i== YI) && tf == false) || i == YUAN){//�ж��Ƿ���Ҫ�ӵ�λ
						sb.append(key.charAt(i));
						tf = true;
					}else{						
						zf = true;						
					}
				}else{
					tf = false;
					if(((i == WAN || i== YI) && tf == false) || i == YUAN){
						tf = true;	//���־λ
					}
					if(zf == true){
						sb.append(v.charAt(0));//�ж��Ƿ���Ҫ�ӡ��㡱
					}
					zf = false;
					sb.append(v.charAt(t));
					sb.append(key.charAt(i));
				}
			}
		}
		//�ж��Ƿ���Ҫ����
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
		System.out.println("***��д��***" + result);
		System.out.println("***��Ԫ��д��***" + result1);
		System.out.println(result2);
	}
	/**���ݽ��׽��ͽ��ױ��֣���SMCU��С��λ����ת������ɷֵ�Ԫ��
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
