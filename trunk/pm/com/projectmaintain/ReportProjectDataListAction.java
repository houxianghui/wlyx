/*
 * 创建日期 2009-2-12
 *
 * zhengpy
 * 
 * ReportProjectDataListAction
 */
package com.projectmaintain;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.eis.base.BaseForm;
import com.eis.base.IbatisBaseAction;
import com.eis.portal.UserContext;
import com.eis.util.SysLog;

/**
 * @author zhengpy
 *
 * @
 */
public class ReportProjectDataListAction extends IbatisBaseAction {

	/* （非 Javadoc）
	 * @see com.eis.base.BaseAction#process(org.apache.struts.action.ActionMapping, com.eis.base.BaseForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, com.eis.portal.UserContext)
	 */
	public ActionForward process(
		ActionMapping mapping,
		BaseForm form,
		HttpServletRequest request,
		HttpServletResponse response,
		UserContext user)
		throws Exception {
		String act = form.getAct();
		if("init".equals(act)){
			return getReportInit(mapping,request);
		}
		if("list".equals(act)){
			return getReportList(form,mapping,request);
		}
		return null;
	}

	/**
	 * @param form
	 * @param mapping
	 * @param request
	 * @return
	 */
	private ActionForward getReportList(BaseForm form, ActionMapping mapping, HttpServletRequest request) throws Exception {
		setPageResult(request,bo.queryForList(form));
		return mapping.findForward("list");
	}

	/**
	 * @param mapping
	 * @param request
	 * @return
	 */
	private ActionForward getReportInit(ActionMapping mapping, HttpServletRequest request) {
		return mapping.findForward("init");
	}

}
