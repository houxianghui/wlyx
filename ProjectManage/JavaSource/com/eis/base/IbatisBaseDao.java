/*
 * @# IbatisBaseDao.java 2008-4-10 houxh
 *
 * Copyright  (c)  2008 	Huateng. All Right Reserv
 */
 
package com.eis.base;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;


public abstract class IbatisBaseDao extends SqlMapClientDaoSupport{
	public abstract void insert(String stat,Object obj) throws DataAccessException;
	public abstract int delete(String stat,Object obj) throws DataAccessException;
	public abstract int update(String stat,Object obj) throws DataAccessException;
	public abstract List queryForList(String stat,Object obj) throws DataAccessException;
	public abstract Object queryForObject(String stat,Object obj) throws DataAccessException;
}
