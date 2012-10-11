package com.blue.tools;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class FileUtil {
	public static InputStream readFile(String fileName){
		return FileUtil.class.getClassLoader().getResourceAsStream(fileName);
	}
	public static URL readUrl(String fileName){
		return FileUtil.class.getClassLoader().getResource(fileName);
	}
	public static String readFileToString(String fileName)throws Exception{
		BufferedReader br = null;
		try{
			br = new BufferedReader(new InputStreamReader(readFile(fileName)));
			StringBuffer sb = new StringBuffer();
			String s = null;
			while((s=br.readLine())!=null){
				sb.append(s);
			}
			return sb.toString();
		}finally{
			if(br != null){
				br.close();
			}
		}
	}
}
