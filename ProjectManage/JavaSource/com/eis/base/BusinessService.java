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
 * 说明：业务逻辑处理接口
 * 
 */
public interface BusinessService {


	/**
	 * 处理业务逻辑
	 * @param map - 数据传输对象
	 * @param user - 用户信息
	 */
	public abstract Object service(HashMap map,UserContext user) throws Exception;
	
    
	/**
	 * 添加数据
	 * @param vo - 要添加的数据对象
	 * @param user - 用户信息
	 */
	public abstract void add(BaseVO vo,UserContext user) throws Exception;
    
	/**
	 * 批量添加数据
	 * @param list - 批量添加的数据对象
	 * @param user - 用户信息
	 */
	public abstract void addList(BaseVO[] list,UserContext user) throws Exception;
    
    
	/**
	 * 更新数据
	 * @param vo - 要更新的数据对象
	 * @param user - 用户信息
	 */
	public abstract void update(BaseVO vo,UserContext user) throws Exception;
    
    
	/**
	 * 批量更新数据
	 * @param list - 批量更新的数据对象
	 * @param user - 用户信息
	 */
	public abstract void updateList(BaseVO[] list,UserContext user) throws Exception;
    
    
	/**
	 * 删除数据
	 * @param vo - 要删除的数据对象
	 * @param user - 用户信息
	 */
	public abstract void delete(BaseVO vo,UserContext user) throws Exception ;

	/**
	  * 批量删除数据
	  * @param list - 批量删除的数据对象
	  * @param user - 用户信息
	  */
	 public abstract void deleteList(BaseVO[] list,UserContext user) throws Exception;
   
       
	/**
	 * 查询数据，返回所有记录
	 * @param user - 用户信息
	 */
	public abstract java.util.List queryList(UserContext user) throws Exception;
    
    
	/**
	 * 查询数据，根据查询条件返回符合条件的一页纪录
	 * @param page - 翻页对象
	 * @param user - 用户信息
	 */
	public abstract java.util.List queryList(PageObject page,UserContext user) throws Exception;
    

	/**
	 * 查询数据，根据查询条件返回符合条件的所有纪录
	 * @param filter - 传递查询条件的对象
	 * @param user - 用户信息
	 */
	public abstract java.util.List queryList(HashMap filter,UserContext user) throws Exception;
    
    
	/**
	 * 查询维护数据，根据查询条件返回符合条件的一页纪录
	 * @param page - 翻页对象
	 * @param user - 用户信息
	 */
	public abstract java.util.List list(PageObject page,UserContext user) throws Exception;
    
    
	/**
	 * 查询维护数据，根据查询条件返回符合条件的所有纪录
	 * @param filter - 传递查询条件的对象
	 * @param user - 用户信息
	 */
	public abstract java.util.List list(HashMap filter,UserContext user) throws Exception;
    

	/**
	 * 查询维护数据，返回所有记录	  
	 * @param user - 用户信息
	 */
	public abstract java.util.List list(UserContext user) throws Exception;
  
  

    
	/**
	 * 查询明细信息,返回单一纪录
	 * @param whereClause - where条件语句
	 * @param user - 用户信息
	 */
	public abstract BaseVO retrieve(String whereClause,UserContext user) throws Exception;


	/**
	 * 查询明细信息,返回单一纪录
	 * @param vo - 信息对象
	 * @param user - 用户信息
	 */
	public abstract BaseVO retrieve(BaseVO vo,UserContext user) throws Exception;



}
