/*
 * @# RequireChangeBO.java 2009-6-12 houxh
 *
 * Copyright  (c)  2009 	Huateng. All Right Reserv
 */
 
package com.projectmaintain;

import java.util.List;

import com.eis.base.IbatisBaseBO;


public class RequireChangeBO extends IbatisBaseBO {

	/* 
	 * @see com.eis.base.IbatisBaseBO#update(java.lang.Object)
	 */
	public void update(Object obj) throws Exception {
		dao.update("updateRequireChange",obj);
	}

	/* 
	 * @see com.eis.base.IbatisBaseBO#insert(java.lang.Object)
	 */
	public void insert(Object obj) throws Exception {
		dao.insert("insertRequireChange",obj);
	}

	/* 
	 * @see com.eis.base.IbatisBaseBO#queryForObject(java.lang.Object)
	 */
	public Object queryForObject(Object obj) throws Exception {
		return dao.queryForObject("queryRequireChange",obj);
	}

	/* 
	 * @see com.eis.base.IbatisBaseBO#queryForList(java.lang.Object)
	 */
	public List queryForList(Object obj) throws Exception {
		return dao.queryForList("queryRequireChangeList",obj);
	}

	/* 
	 * @see com.eis.base.IbatisBaseBO#delete(java.lang.Object)
	 */
	public void delete(Object obj) throws Exception {
		dao.delete("deleteRequireChange",obj);
	}
	
	public int queryForNextVersion(String projectId){
		return ((Integer)dao.queryForObject("queryForNextRequireChangeVersion",projectId)).intValue();
	}
	/* 
	 * @see com.eis.base.IbatisBaseBO#transOperation(java.lang.Object[])
	 */
	public void transOperation(Object[] obj) throws Exception {

	}

}