/*********************************************************
 * File:BaseBO.java
 * 
 * @version 1.0
 * 
 * Date     2005-6-1
 * @author   辛勇
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
 * 说明：业务逻辑处理抽象类
 * 
 */

public abstract class BaseBO implements BusinessService{
	
	/**
	 * 根据bean ID获得实例对象
	 * @param name - bean ID	 
	 * @return bean的实例对象
	 *
	 */
	public Object getBean(String name) throws Exception {
		
		return BeanFactory.getBean(name);
	}
	
	
	//---------------  以下方法多数情况下不需要子类重载，因此在此处提供默认实现  -----------------
	/**
	 * 批量添加数据
	 * @param list - 批量添加的数据对象
	 * @param user - 用户信息
	 */
	public void addList(BaseVO[] list,UserContext user) throws Exception {
		
	}  
	
	/**
	 * 批量更新数据
	 * @param list - 批量更新的数据对象
	 * @param user - 用户信息
	 */
	public  void updateList(BaseVO[] list,UserContext user) throws Exception{
		
	}
    
	/**
	  * 批量删除数据
	  * @param list - 批量删除的数据对象
	  * @param user - 用户信息
	  */
	 public  void deleteList(BaseVO[] list,UserContext user) throws Exception{
	 	
	 }
   
  
	/**
	 * 查询数据，根据查询条件返回符合条件的所有纪录
	 * @param filter - 传递查询条件的对象
	 * @param user - 用户信息
	 */
	public java.util.List queryList(HashMap filter,UserContext user) throws Exception{
		return null;
	}
	
	 
	/**
	 * 查询维护数据，根据查询条件返回符合条件的所有纪录
	 * @param filter - 传递查询条件的对象
	 * @param user - 用户信息
	 */
	public java.util.List list(HashMap filter,UserContext user) throws Exception{
		return null;
	}
    
	
	/**
	 * 业务逻辑处理方法入口，该方法只实现对各种外部异常的捕获和翻译，具体实现调用process方法
	 * @param map - 数据传输对象
	 * @param user - 用户信息
	 */
	public Object service(HashMap map,UserContext user) throws Exception{
		return null;
		
	}
	
	/**
	 * 具体的业务逻辑处理实现方法，所有子类都要重置该方法
	 * @param map - 数据传输对象
	 * @param user - 用户信息
	 */
	public Object process(HashMap map,UserContext user) throws Exception{
	 return null;
	}


}
