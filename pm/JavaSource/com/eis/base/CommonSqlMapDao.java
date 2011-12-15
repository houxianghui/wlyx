/*
 * @# CommonSqlMapDao.java 2008-4-10 houxh
 *
 * Copyright  (c)  2008 	Huateng. All Right Reserv
 */
 
package com.eis.base;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.DataAccessException;

import com.abc.logic.QueryObject;
import com.eis.util.CheckUtil;


public class CommonSqlMapDao extends IbatisBaseDao{
	public void insert(String stat,Object obj) throws DataAccessException{
		getSqlMapClientTemplate().insert(stat,obj);
		
	}
	public int update(String stat,Object obj) throws DataAccessException{
		return getSqlMapClientTemplate().update(stat,obj);
	}
	/* 
	 * @see com.eis.base.IbatisBaseDao#delete(java.lang.String, java.lang.Object)
	 */
	public int delete(String stat, Object obj) throws DataAccessException {
		return getSqlMapClientTemplate().delete(stat,obj);
	}
	/* 
	 * @see com.eis.base.IbatisBaseDao#queryForObject(java.lang.String, java.lang.Object)
	 */
	public Object queryForObject(String stat, Object obj) throws DataAccessException {
		return getSqlMapClientTemplate().queryForObject(stat,obj);
	}
	/* 
	 * @see com.eis.base.IbatisBaseDao#queryForList(java.lang.String, java.lang.Object)
	 */
	public List queryForList(String stat, Object obj) throws DataAccessException {
		return getSqlMapClientTemplate().queryForList(stat,obj);
		
	}
	public void insertByGenerate(String namespace,Object obj) throws DataAccessException{
		getSqlMapClientTemplate().insert(namespace+".ibatorgenerated_insertSelective",obj);
		
	}
	public int updateByGenerate(String namespace,Object obj) throws DataAccessException{
		return getSqlMapClientTemplate().update(namespace+".ibatorgenerated_updateByPrimaryKeySelective",obj);
	}
	/* 
	 * @see com.eis.base.IbatisBaseDao#delete(java.lang.String, java.lang.Object)
	 */
	public int deleteByGenerate(String namespace, Object obj) throws DataAccessException {
		return getSqlMapClientTemplate().delete(namespace+".ibatorgenerated_deleteByPrimaryKey",obj);
	}
	public int deleteByGenerateWithCondition(String namespace,Object obj)throws DataAccessException{
		return getSqlMapClientTemplate().delete(namespace+".ibatorgenerated_deleteByExample", obj);
	}
	/* 
	 * @see com.eis.base.IbatisBaseDao#queryForObject(java.lang.String, java.lang.Object)
	 */
	public Object queryForObjectByGenerate(String namespace, Object obj) throws DataAccessException {
		return getSqlMapClientTemplate().queryForObject(namespace+".ibatorgenerated_selectByPrimaryKey",obj);
	}
	/* 
	 * @see com.eis.base.IbatisBaseDao#queryForList(java.lang.String, java.lang.Object)
	 */
	public List queryForListByGenerate(String namespace, Object obj) throws DataAccessException {
		return getSqlMapClientTemplate().queryForList(namespace+".ibatorgenerated_selectByExample",obj);
		
	}
	public List queryForList(String stat,Object obj,int skip,int max) throws DataAccessException{
		return getSqlMapClientTemplate().queryForList(stat,obj,skip,max);
	}
	public void setPageList(String stat,QueryObject queryObject,HttpServletRequest request)throws DataAccessException{
		PageObject page = new PageObject();
		String pageNo = request.getParameter("pageNO");
		int p = 0;
		if (!CheckUtil.isEmptry(pageNo)) {
			p = Integer.parseInt(pageNo)-1;
		}
		page.setCurPage(p+1);
		queryObject.setIsCount(1);
		int count=((BaseVO)getSqlMapClientTemplate().queryForObject(stat, queryObject)).getQueryCount();
		page.setMaxRecords(count);
		page.setTotalRecord(count);
		queryObject.setIsCount(0);
		int startPosition = page.getStartPosition()-1;
		int endPosition = page.getEndPosition();
		
		page.setList(queryForList(stat,queryObject,startPosition,endPosition));
		
		request.setAttribute("pageResult",page);
		
	}

	
}
