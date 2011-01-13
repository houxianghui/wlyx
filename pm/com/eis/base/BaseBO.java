/*********************************************************
 * File:BaseBO.java
 * 
 * @version 1.0
 * 
 * Date     2005-6-1
 * @author   ����
 * 
 * Copyright (C) 2005 huateng.
 * all rights reserved.
 * 
 ********************************************************/

package com.eis.base;

import java.util.*;
import javax.naming.*;

import com.eis.portal.UserContext;
import com.eis.factory.*;


/**
 * ˵����ҵ���߼����������
 * 
 */

public abstract class BaseBO implements BusinessService{
	
	/**
	 * ����bean ID���ʵ������
	 * @param name - bean ID	 
	 * @return bean��ʵ������
	 *
	 */
	public Object getBean(String name) throws Exception {
		
		return BeanFactory.getBean(name);
	}
	
	
	//---------------  ���·�����������²���Ҫ�������أ�����ڴ˴��ṩĬ��ʵ��  -----------------
	/**
	 * �����������
	 * @param list - ������ӵ����ݶ���
	 * @param user - �û���Ϣ
	 */
	public void addList(BaseVO[] list,UserContext user) throws Exception {
		
	}  
	
	/**
	 * ������������
	 * @param list - �������µ����ݶ���
	 * @param user - �û���Ϣ
	 */
	public  void updateList(BaseVO[] list,UserContext user) throws Exception{
		
	}
    
	/**
	  * ����ɾ������
	  * @param list - ����ɾ�������ݶ���
	  * @param user - �û���Ϣ
	  */
	 public  void deleteList(BaseVO[] list,UserContext user) throws Exception{
	 	
	 }
   
  
	/**
	 * ��ѯ���ݣ����ݲ�ѯ�������ط������������м�¼
	 * @param filter - ���ݲ�ѯ�����Ķ���
	 * @param user - �û���Ϣ
	 */
	public java.util.List queryList(HashMap filter,UserContext user) throws Exception{
		return null;
	}
	
	 
	/**
	 * ��ѯά�����ݣ����ݲ�ѯ�������ط������������м�¼
	 * @param filter - ���ݲ�ѯ�����Ķ���
	 * @param user - �û���Ϣ
	 */
	public java.util.List list(HashMap filter,UserContext user) throws Exception{
		return null;
	}
    
	
	/**
	 * ҵ���߼���������ڣ��÷���ֻʵ�ֶԸ����ⲿ�쳣�Ĳ���ͷ��룬����ʵ�ֵ���process����
	 * @param map - ���ݴ������
	 * @param user - �û���Ϣ
	 */
	public Object service(HashMap map,UserContext user) throws Exception{
		return null;
		
	}
	
	/**
	 * �����ҵ���߼�����ʵ�ַ������������඼Ҫ���ø÷���
	 * @param map - ���ݴ������
	 * @param user - �û���Ϣ
	 */
	public Object process(HashMap map,UserContext user) throws Exception{
	 return null;
	}


}
