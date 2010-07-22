package com.blue.monstor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ItemTest {
	public static void main(String[] args)throws Exception {
		Pattern p = Pattern.compile("item_id\":\"(\\d+)\",\"role_id\":\"\\d+\",\"name\":\"(\\S+?)\",.*?quality\":\"(\\d+).*?is_checkup\":\"(\\d+)\"",Pattern.UNICODE_CASE);
		Matcher  m = p.matcher(readText("./src/com/blue/monstor/item.txt"));
		StringBuilder sb = null;
		while(m.find()){
			sb = new StringBuilder();
			System.out.print(m.group(1)+" ");
			String s = m.group(2);
			String[] t = s.split("\\\\u");
			for(int i = 0;i < t.length;i++){
				try{
					char c = (char)Integer.parseInt(t[i],16);
					sb.append(c);
				}catch(Exception e){}
				
			}
			System.out.print(sb);
			System.out.println(" "+m.group(3)+" "+m.group(4));
		}
		
//		System.out.println(sb);
//		System.out.println("\u72c2");
		
	}
	public static String readText(String filename)throws Exception{
		File f = new File(filename);
		FileReader fr = new FileReader(f);
		StringBuffer sb = new StringBuffer();
		BufferedReader br = new BufferedReader(fr);
		
		String s = null;
		while((s=br.readLine())!=null){
			sb.append(s+"\n");
		}
		return sb.toString();
	}
}
