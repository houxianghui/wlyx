/*
 * 创建日期 2009-7-29
 *
 * Author Songlijun
 */
package com.abc.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.support.PagedListHolder;

import com.abc.logic.IbatisBO; 
import com.eis.base.BaseAction;
import com.eis.base.BaseForm;
import com.eis.base.PageObject;
import com.eis.config.SysConfig;
import com.eis.portal.UserContext;
import com.eis.util.CheckUtil;

public abstract class IbatisAction extends BaseAction {
	protected IbatisBO bo;

	/* 
	 * @see com.eis.base.BaseAction#process(org.apache.struts.action.ActionMapping, com.eis.base.BaseForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, com.eis.portal.UserContext)
	 */
	public abstract ActionForward process(
		ActionMapping mapping,
		BaseForm form,
		HttpServletRequest request,
		HttpServletResponse response,
		UserContext user)
		throws Exception;

	public void setBo(IbatisBO bo) {
		this.bo = bo;
	}
	public IbatisBO getBo() {
		return bo;
	}
	public void setPageResult(HttpServletRequest request, List resultList) {
		PageObject page = new PageObject();		
		PagedListHolder list = new PagedListHolder();		
		String pageNo = request.getParameter("pageNO");
		list.setSource(resultList);
		list.setPageSize(Integer.parseInt(SysConfig.getProperty("rowsPerPage")));
				
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
}
