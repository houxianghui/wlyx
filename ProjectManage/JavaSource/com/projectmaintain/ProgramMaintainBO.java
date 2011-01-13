/*
 * @# ProgramMaintainBO.java 2008-11-12 houxh
 *
 * Copyright  (c)  2003 	Huateng. All Right Reserv
 */
 
package com.projectmaintain;

import java.util.Arrays;
import java.util.List;

import com.eis.base.IbatisBaseBO;
import com.eis.exception.MessageException;


public class ProgramMaintainBO extends IbatisBaseBO {

	/* 
	 * @see com.eis.base.IbatisBaseBO#update(java.lang.Object)
	 */
	public void update(Object obj) throws Exception {
		dao.update("updateProgram",obj);
	}

	/* 
	 * @see com.eis.base.IbatisBaseBO#insert(java.lang.Object)
	 */
	public void insert(Object obj) throws Exception {
		dao.insert("insertProgram",obj);
	}

	/* 
	 * @see com.eis.base.IbatisBaseBO#queryForObject(java.lang.Object)
	 */
	public Object queryForObject(Object obj) throws Exception {
		return dao.queryForObject("getProgram",obj);
	}

	/* 
	 * @see com.eis.base.IbatisBaseBO#queryForList(java.lang.Object)
	 */
	public List queryForList(Object obj) throws Exception {
		return dao.queryForList("getProgramList",obj);
	}

	/* 
	 * @see com.eis.base.IbatisBaseBO#delete(java.lang.Object)
	 */
	public void delete(Object obj) throws Exception {
		dao.delete("deleteProgram",obj);
	}

	/* 
	 * @see com.eis.base.IbatisBaseBO#transOperation(java.lang.Object[])
	 */
	public void transOperation(Object[] obj) throws Exception {
		dao.delete("deleteProgram",obj[0]);
		for(int i = 0;i < obj.length;i++){
			dao.insert("insertProgram",obj[i]);
		}
	}
	public void rejectRepeated(List l)throws MessageException{
		Object[] obj = l.toArray();
		Arrays.sort(obj);
		for(int i = 0;i < obj.length-1;i++){
			if(obj[i].equals(obj[i+1])){
				throw new MessageException("ÓÐÖØ¸´ÄÚÈÝ"+obj[i].toString());
			}
		}
	}
}
