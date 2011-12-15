
package com.abc.logic;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.abc.service.DaoService;
import com.eis.base.BaseVOInterface;
import com.eis.base.CommonSqlMapDao;
import com.eis.base.PageObject;
import com.eis.util.CheckUtil;

 
public abstract class IbatisBO {
	protected String namespace;
	protected DaoService daoServiceFacade; //���ݳ־ò����
	protected CommonSqlMapDao dao;
	public DaoService getDaoServiceFacade() {
		return daoServiceFacade;
	}
	public void setDaoServiceFacade(DaoService daoServiceFacade) {
		this.daoServiceFacade = daoServiceFacade;
	}

	public abstract void update(Object obj) throws Exception;
	public abstract void insert(Object obj) throws Exception;
	public abstract Object queryForObject(Object obj) throws Exception;
	public abstract List queryForList(Object obj) throws Exception;
	public abstract void delete(Object obj) throws Exception;
	
	public void setPageList(String statment, QueryObject queryObject, HttpServletRequest request) throws Exception {
		PageObject page = new PageObject();
		String pageNo = request.getParameter("pageNO");
		int p = 0;
		if (!CheckUtil.isEmptry(pageNo)) {
			p = Integer.parseInt(pageNo) - 1;
		}
		page.setCurPage(p + 1);
		queryObject.setIsCount(1);
		BaseVOInterface bv = (BaseVOInterface)daoServiceFacade.queryForObject(statment, queryObject);
		
		int count = ((BaseVOInterface) daoServiceFacade.queryForObject(statment, queryObject)).getQueryCount();
		page.setMaxRecords(count);
		page.setTotalRecord(count);
		queryObject.setIsCount(0);
		int startPosition = page.getStartPosition() - 1;
		int endPosition = PageObject.getAmountPerPage();
		List list = daoServiceFacade.queryForList(statment, queryObject, startPosition, endPosition);
		page.setList(list);
		request.setAttribute("list",list);
		request.setAttribute("pageResult", page);
	}
	/**
	 * ʹ��ibatis�Զ�����ʱʹ�õķ�������Ҫ�޸��Զ����ɵ�xml
	 * @param namespace	���ƿռ�
	 * @param example	��ѯ����
	 * @param request	
	 * @throws Exception
	 */
	public void setPageList(String namespace,Object example,HttpServletRequest request)throws Exception{
		int count = (Integer)dao.queryForObject(namespace+".ibatorgenerated_countByExample", example);
		PageObject page = new PageObject();
		String pageNo = request.getParameter("pageNO");
		int p = 0;
		if (!CheckUtil.isEmptry(pageNo)) {
			p = Integer.parseInt(pageNo) - 1;
		}
		page.setCurPage(p + 1);
		page.setMaxRecords(count);
		page.setTotalRecord(count);
		int startPosition = page.getStartPosition() - 1;
		int endPosition = PageObject.getAmountPerPage();
		List list = dao.queryForList(namespace+".ibatorgenerated_selectByExample", example, startPosition, endPosition);
		page.setList(list);
		request.setAttribute("list",list);
		request.setAttribute("pageResult", page);
	}
	/**
	 * �����Լ�д��sqlmap�еĲ�ѯ��ҳ
	 * @param countState	ͳ�Ƹ�����sql������Ҫ��namespace
	 * @param queryState	��ѯ�б��sql������Ҫ��namespace
	 * @param example		��ѯʹ�õĲ�������
	 * @param request
	 * @throws Exception
	 */
	public void setPageList(String countState,String queryState,Object example,HttpServletRequest request)throws Exception{
		int count = (Integer)dao.queryForObject(countState, example);
		PageObject page = new PageObject();
		String pageNo = request.getParameter("pageNO");
		int p = 0;
		if (!CheckUtil.isEmptry(pageNo)) {
			p = Integer.parseInt(pageNo) - 1;
		}
		page.setCurPage(p + 1);
		page.setMaxRecords(count);
		page.setTotalRecord(count);
		int startPosition = page.getStartPosition() - 1;
		int endPosition = PageObject.getAmountPerPage();
		List list = dao.queryForList(queryState, example, startPosition, endPosition);
		page.setList(list);
		request.setAttribute("list",list);
		request.setAttribute("pageResult", page);
	}
	public String getNamespace() {
		return namespace;
	}
	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}
	public CommonSqlMapDao getDao() {
		return dao;
	}
	public void setDao(CommonSqlMapDao dao) {
		this.dao = dao;
	}

}
