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
 * ˵������Դ�ļ�������
 * 
 */
public class ResourceFile {
	
	/**
	 * ��ȡ��Դ�ļ���File����
	 * @param fileName - ��ǰ·���µ���Դ�ļ���
	 * @return ��Դ�ļ���File����
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
