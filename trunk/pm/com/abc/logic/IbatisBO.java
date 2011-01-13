/*
 * 创建日期 2009-7-29
 *
 * Author Songlijun
 */
package com.abc.logic;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.abc.service.DaoService;
import com.eis.base.BaseVOInterface;
import com.eis.base.PageObject;
import com.eis.util.CheckUtil;

 
public abstract class IbatisBO {
	protected DaoService daoServiceFacade; //数据持久层对象

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
}
