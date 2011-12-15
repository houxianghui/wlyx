/*
 * @# IbatisBaseAction.java 2008-4-10 houxh
 *
 * Copyright  (c)  2008 	Huateng. All Right Reserv
 */
 
package com.eis.base;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.support.PagedListHolder;

import com.eis.portal.UserContext;
import com.eis.util.CheckUtil;


public abstract class IbatisBaseAction extends BaseAction {
	protected IbatisBaseBO bo;

	/* 
	 * @see com.eis.base.BaseAction#process(org.apache.struts.action.ActionMapping, com.eis.base.BaseForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, com.eis.portal.UserContext)
	 */
	public abstract ActionForward process(ActionMapping mapping, BaseForm form, HttpServletRequest request, HttpServletResponse response, UserContext user) throws Exception;

	public void setBo(IbatisBaseBO bo){
		this.bo = bo;
	}
	public IbatisBaseBO getBo(){
		return bo;
	}

	public void setPageResult(HttpServletRequest request, List resultList) {
		PageObject page = new PageObject();		
		PagedListHolder list = new PagedListHolder();		
		String pageNo = request.getParameter("pageNO");
		list.setSource(resultList);
		list.setPageSize(15);
				
		if (CheckUtil.isEmptry(pageNo)) {
			list.setPage(0);
		} else {
			list.setPage(Integer.parseInt(pageNo)-1);
		}		
		
		page.setCurPage(list.getPage()+1);
		page.setTotalPage(list.getPageCount());
		page.setTotalRecord(list.getNrOfElements());
		page.setMaxRecords(list.getNrOfElements());
		page.setStartPosition(list.getFirstElementOnPage()+1);
		
		page.setList(list.getPageList());
		request.setAttribute("pageResult", page);
	}
	/**
	 * 使用存储过程的翻页查询。存储过程名称:splitpage,queryRowCount
	 * 如果查询的列里面存在identity列，请注意进行转化，否则会报错（一个表里面不能有两个identity列）
	 * 
	 * @param request
	 * @param sql 翻页使用的sql
	 * @param statement 要使用的查询语句
	 * @throws Exception
	 */
	public void setPageResult(HttpServletRequest request,String sql,String statement)throws Exception{
		PageObject page = new PageObject();
	
		int pageNo = 1;
		if(!CheckUtil.isEmptry(request.getParameter("pageNO"))){
			pageNo = (Integer.parseInt(request.getParameter("pageNO")));
		}
		QueryPageParam query = new QueryPageParam();
		query.setPageNum(pageNo);
		query.setSql(sql);
	
		List l = bo.queryForPage(statement,query);
		int totalRowCount = bo.queryRowCount(sql);
	
		page.setCurPage(pageNo);
		page.setTotalPage(totalRowCount/query.getRowPerPage()+1);
		page.setTotalRecord(totalRowCount);
		page.setMaxRecords(totalRowCount);
	
		page.setList(l);
	
		request.setAttribute("pageResult",page);
	
	}
}
