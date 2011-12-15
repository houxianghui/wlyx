package com.eis.util;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Diff {
	public static List<DiffInfo> diff(Object src,Object target,String[] fields)throws Exception{
		List<DiffInfo> l = new ArrayList<DiffInfo>();
		for(int i = 0;i < fields.length;i++){
			Method m = src.getClass().getDeclaredMethod(getMethodName(fields[i]), null);
			Object o1 = m.invoke(src, null);
			Method m2 = target.getClass().getDeclaredMethod(getMethodName(fields[i]), null);
			Object o2 = m2.invoke(target, null);
			if((o1 != null && !o1.equals(o2)) || (o2 != null && !o2.equals(o1))){
				l.add(new DiffInfo(fields[i],o1,o2));
			}
		}
		return l;
	}
	public static String getMethodName(String field){
		StringBuffer sb = new StringBuffer();
		sb.append("get");
		sb.append(field.substring(0,1).toUpperCase());
		sb.append(field.substring(1));
		return sb.toString();
	}
	public static String setMethodName(String field){
		StringBuffer sb = new StringBuffer();
		sb.append("set");
		sb.append(field.substring(0,1).toUpperCase());
		sb.append(field.substring(1));
		return sb.toString();
	}
	
}
