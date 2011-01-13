/*
 * @# IbatisBaseBO.java 2008-4-10 houxh
 *
 * Copyright  (c)  2008 	Huateng. All Right Reserv
 */
 
package com.eis.base;

import java.util.List;


public abstract class IbatisBaseBO {
	protected IbatisBaseDao dao;

	public void setDao(IbatisBaseDao dao) {
		this.dao = dao;
	}
	public abstract void update(Object obj) throws Exception;
	public abstract void insert(Object obj) throws Exception;
	public abstract Object queryForObject(Object obj) throws Exception;
	public abstract List queryForList(Object obj) throws Exception;
	public abstract void delete(Object obj) throws Exception;
	public abstract void transOperation(Object[] obj)throws Exception;
	public List queryForPage(String stat,QueryPageParam param)throws Exception{
		return dao.queryForList(stat,param);
	}
	public int queryRowCount(String sql){
		return ((Integer)dao.queryForObject("queryRowCount",sql)).intValue();
	}
}
