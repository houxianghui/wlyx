package com.blue.tools;

import java.io.InputStream;
import java.net.URL;

public class FileUtil {
	public static InputStream readFile(String fileName){
		return FileUtil.class.getClassLoader().getResourceAsStream(fileName);
	}
	public static URL readUrl(String fileName){
		return FileUtil.class.getClassLoader().getResource(fileName);
	}
}
