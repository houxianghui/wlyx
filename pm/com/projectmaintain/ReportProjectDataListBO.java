/*
 * �������� 2009-2-12
 *
 * zhengpy
 * 
 * ReportProjectDataListBO
 */
package com.projectmaintain;

import java.util.List;

import com.eis.base.IbatisBaseBO;

/**
 * @author zhengpy
 *
 * @
 */
public class ReportProjectDataListBO extends IbatisBaseBO {

	/* ���� Javadoc��
	 * @see com.eis.base.IbatisBaseBO#update(java.lang.Object)
	 */
	public void update(Object obj) throws Exception {
		// TODO �Զ����ɷ������

	}

	/* ���� Javadoc��
	 * @see com.eis.base.IbatisBaseBO#insert(java.lang.Object)
	 */
	public void insert(Object obj) throws Exception {
		// TODO �Զ����ɷ������

	}

	/* ���� Javadoc��
	 * @see com.eis.base.IbatisBaseBO#queryForObject(java.lang.Object)
	 */
	public Object queryForObject(Object obj) throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/* ���� Javadoc��
	 * @see com.eis.base.IbatisBaseBO#queryForList(java.lang.Object)
	 */
	public List queryForList(Object obj) throws Exception {
		List daoList = dao.queryForList("getReportList",obj); 
		return daoList;
	}

	/* ���� Javadoc��
	 * @see com.eis.base.IbatisBaseBO#delete(java.lang.Object)
	 */
	public void delete(Object obj) throws Exception {
		// TODO �Զ����ɷ������

	}

	/* ���� Javadoc��
	 * @see com.eis.base.IbatisBaseBO#transOperation(java.lang.Object[])
	 */
	public void transOperation(Object[] obj) throws Exception {
		// TODO �Զ����ɷ������

	}

}
