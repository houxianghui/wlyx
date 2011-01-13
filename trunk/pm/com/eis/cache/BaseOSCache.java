package com.eis.cache;

import com.opensymphony.oscache.base.NeedsRefreshException;
import com.opensymphony.oscache.general.GeneralCacheAdministrator;

public abstract class BaseOSCache {
	
	private static final GeneralCacheAdministrator CACHE = new GeneralCacheAdministrator();



public Object getFromOSCache(String key) throws Exception{
	
	Object object = null;
	try{
		object = CACHE.getFromCache(key);
	}catch(NeedsRefreshException NRE){  //有两种情况会出现NeedRefreshException，一是根据credit_id在缓存中
		try{
			
			object=getContent(key);//根据credit_id，重新从硬盘中读取xml文件，
			CACHE.flushEntry(key);                                                   //解析成Credit_Grade对象放入缓存中
			CACHE.putInCache(key, object);												   //如果putCreditGradeToCache失败，则返回null
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
	CACHE.flushEntry(key);     //解析成Credit_Grade对象放入缓存中
	CACHE.putInCache(key, object);
}

public abstract  Object getContent(String key) throws Exception ;
}


