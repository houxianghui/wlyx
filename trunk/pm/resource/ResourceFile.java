/*********************************************************
 * File: ResourceFile.java
 * 
 * Version 1.0
 * 
 * Date     2005-9-10
 * 
 * Author   xin yong
 * 
 ********************************************************/

package resource;

import java.io.*;

/**
 * 说明：资源文件访问类
 * 
 */
public class ResourceFile {
	
	/**
	 * 获取资源文件的File对象
	 * @param fileName - 当前路径下的资源文件名
	 * @return 资源文件的File对象
	 */
	public static synchronized InputStream getResource(String fileName){
		ResourceFile res = new ResourceFile();
		return res.parseFile(fileName);
	}
	
	public InputStream parseFile(String fileName){
		ResourceFile res = new ResourceFile();		
		return getClass().getResourceAsStream(fileName);	
	}

}
