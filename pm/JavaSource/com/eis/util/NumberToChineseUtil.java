package com.eis.util;
public class NumberToChineseUtil {
	private static String[] number = {"零","一","二","三","四","五","六","七","八","九"};
	private static String[] UNIT = {"千","百","十"};
	public static String getChinese(int i){
		if(i/10 == 0){
			return number[i];
		}
		if(i/100==0){
			int t = i/10;
			int k = i%10;
			StringBuffer result = new StringBuffer();
			if(t != 1){
				result.append(number[t]);
			}
			result.append(UNIT[2]);
			if(k!=0){
				result.append(number[k]);
			}
			return result.toString();
		}
		if(i/1000==0){
			int j = i/100;
			int t = j/10;
			int k = i%10;
			StringBuffer result = new StringBuffer();
			result.append(number[j]);
			result.append(UNIT[1]);
			
			if(t!=0){
				result.append(number[t]);
				result.append(UNIT[2]);
			}else{
				if(k == 0){
					return result.toString();
				}else{
					result.append(number[t]);
				}
			}
			if(k!=0){
				result.append(number[k]);
			}
			return result.toString();
		}
		return "人数不能超过1000";
	}
}
