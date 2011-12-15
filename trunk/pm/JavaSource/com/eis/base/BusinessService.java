/*********************************************************
 * File: BusinessService.java
 * 
 * Version 1.0
 * 
 * Date     2005-9-20
 * 
 * Author   xin yong
 * 
 ********************************************************/

package com.eis.base;

import java.util.*;

import com.eis.portal.UserContext;


/**
 * ˵����ҵ���߼�����ӿ�
 * 
 */
public interface BusinessService {


	/**
	 * ����ҵ���߼�
	 * @param map - ���ݴ������
	 * @param user - �û���Ϣ
	 */
	public abstract Object service(HashMap map,UserContext user) throws Exception;
	
    
	/**
	 * �������
	 * @param vo - Ҫ��ӵ����ݶ���
	 * @param user - �û���Ϣ
	 */
	public abstract void add(BaseVO vo,UserContext user) throws Exception;
    
	/**
	 * �����������
	 * @param list - ������ӵ����ݶ���
	 * @param user - �û���Ϣ
	 */
	public abstract void addList(BaseVO[] list,UserContext user) throws Exception;
    
    
	/**
	 * ��������
	 * @param vo - Ҫ���µ����ݶ���
	 * @param user - �û���Ϣ
	 */
	public abstract void update(BaseVO vo,UserContext user) throws Exception;
    
    
	/**
	 * ������������
	 * @param list - �������µ����ݶ���
	 * @param user - �û���Ϣ
	 */
	public abstract void updateList(BaseVO[] list,UserContext user) throws Exception;
    
    
	/**
	 * ɾ������
	 * @param vo - Ҫɾ�������ݶ���
	 * @param user - �û���Ϣ
	 */
	public abstract void delete(BaseVO vo,UserContext user) throws Exception ;

	/**
	  * ����ɾ������
	  * @param list - ����ɾ�������ݶ���
	  * @param user - �û���Ϣ
	  */
	 public abstract void deleteList(BaseVO[] list,UserContext user) throws Exception;
   
       
	/**
	 * ��ѯ���ݣ��������м�¼
	 * @param user - �û���Ϣ
	 */
	public abstract java.util.List queryList(UserContext user) throws Exception;
    
    
	/**
	 * ��ѯ���ݣ����ݲ�ѯ�������ط���������һҳ��¼
	 * @param page - ��ҳ����
	 * @param user - �û���Ϣ
	 */
	public abstract java.util.List queryList(PageObject page,UserContext user) throws Exception;
    

	/**
	 * ��ѯ���ݣ����ݲ�ѯ�������ط������������м�¼
	 * @param filter - ���ݲ�ѯ�����Ķ���
	 * @param user - �û���Ϣ
	 */
	public abstract java.util.List queryList(HashMap filter,UserContext user) throws Exception;
    
    
	/**
	 * ��ѯά�����ݣ����ݲ�ѯ�������ط���������һҳ��¼
	 * @param page - ��ҳ����
	 * @param user - �û���Ϣ
	 */
	public abstract java.util.List list(PageObject page,UserContext user) throws Exception;
    
    
	/**
	 * ��ѯά�����ݣ����ݲ�ѯ�������ط������������м�¼
	 * @param filter - ���ݲ�ѯ�����Ķ���
	 * @param user - �û���Ϣ
	 */
	public abstract java.util.List list(HashMap filter,UserContext user) throws Exception;
    

	/**
	 * ��ѯά�����ݣ��������м�¼	  
	 * @param user - �û���Ϣ
	 */
	public abstract java.util.List list(UserContext user) throws Exception;
  
  

    
	/**
	 * ��ѯ��ϸ��Ϣ,���ص�һ��¼
	 * @param whereClause - where�������
	 * @param user - �û���Ϣ
	 */
	public abstract BaseVO retrieve(String whereClause,UserContext user) throws Exception;


	/**
	 * ��ѯ��ϸ��Ϣ,���ص�һ��¼
	 * @param vo - ��Ϣ����
	 * @param user - �û���Ϣ
	 */
	public abstract BaseVO retrieve(BaseVO vo,UserContext user) throws Exception;



}
