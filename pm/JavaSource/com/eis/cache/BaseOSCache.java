package com.eis.cache;

import com.opensymphony.oscache.base.NeedsRefreshException;
import com.opensymphony.oscache.general.GeneralCacheAdministrator;

public abstract class BaseOSCache {
	
	private static final GeneralCacheAdministrator CACHE = new GeneralCacheAdministrator();



public Object getFromOSCache(String key) throws Exception{
	
	Object object = null;
	try{
		object = CACHE.getFromCache(key);
	}catch(NeedsRefreshException NRE){  //��������������NeedRefreshException��һ�Ǹ���credit_id�ڻ�����
		try{
			
			object=getContent(key);//����credit_id�����´�Ӳ���ж�ȡxml�ļ���
			CACHE.flushEntry(key);                                                   //������Credit_Grade������뻺����
			CACHE.putInCache(key, object);												   //���putCreditGradeToCacheʧ�ܣ��򷵻�null
			object=CACHE.getFromCache(key);
		}catch(Exception e){			                      
			return null;
			}
		}
		return object;	
	}
public void putInCache(String key) throws Exception{
	Object object = null;
	object = getContent(key);
	CACHE.flushEntry(key);     //������Credit_Grade������뻺����
	CACHE.putInCache(key, object);
}

public abstract  Object getContent(String key) throws Exception ;
}


