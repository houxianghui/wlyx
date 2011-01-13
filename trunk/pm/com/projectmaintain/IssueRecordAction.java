/*
 * @# IssueRecordAction.java 2009-6-11 houxh
 *
 * Copyright  (c)  2009 	Huateng. All Right Reserv
 */
 
package com.projectmaintain;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.eis.base.BaseForm;
import com.eis.base.IbatisBaseAction;
import com.eis.base.QueryPageParam;
import com.eis.portal.UserContext;


public class IssueRecordAction extends IbatisBaseAction {

	/* 
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
		if("list".equalsIgnoreCase(act)){
			return getIssueRecordList(form,request,mapping);
		}
		if("add".equalsIgnoreCase(act)){
			return add(form,request,mapping,user);
		}
		if("update".equalsIgnoreCase(act)){
			return update(form,request,mapping,user);
		}
		if("new".equalsIgnoreCase(act)){
			
			return mapping.findForward("new");
		}
		if("edit".equalsIgnoreCase(act)){
			return edit(request,mapping,form);
		}
		if("delete".equals(act)){
			return delete(form,request,mapping);
		}
		return null;
	}
	public ActionForward getIssueRecordList(BaseForm form,HttpServletRequest request,ActionMapping mapping)throws Exception{
		IssueRecordVO vo = new IssueRecordVO();
		vo.setProjectId(request.getParameter("projectId"));
		vo.setUserId(request.getParameter("userId"));
		vo.setStatus(request.getParameter("status"));
		copyProperties(form,vo);
		
		setPageResult(request,bo.queryForList(vo));
		
		request.setAttribute("userId",vo.getUserId());
		request.setAttribute("projectId",vo.getProjectId());
		request.setAttribute("status",vo.getStatus());
		
		return mapping.findForward("list");
	}
	public ActionForward add(BaseForm form,HttpServletRequest request,ActionMapping mapping,UserContext user)throws Exception{
		IssueRecordVO vo = new IssueRecordVO();
		copyProperties(vo,form);
		vo.setCheckUser(user.getUserID());
		bo.insert(vo);
		
		return forwardSuccessPage(request,mapping,"保存成功","IssueRecord.do?act=list&projectId="+vo.getProjectId()+"&userId="+vo.getUserId()+"&status="+vo.getStatus());
	}
	public ActionForward delete(BaseForm form,HttpServletRequest request,ActionMapping mapping)throws Exception{
		String id = request.getParameter("id");
		IssueRecordVO vo = new IssueRecordVO();
		vo.setId(Integer.parseInt(id));
		copyProperties(vo,form);
		bo.delete(vo);		
		
		return forwardSuccessPage(request,mapping,"删除成功","IssueRecord.do?act=list&projectId="+vo.getProjectId()+"&userId="+vo.getUserId()+"&status="+vo.getStatus());
		
	}
	public ActionForward update(BaseForm form,HttpServletRequest request,ActionMapping mapping,UserContext user)throws Exception{
		IssueRecordVO vo = new IssueRecordVO();
		copyProperties(vo,form);
		vo.setCheckUser(user.getUserID());
		bo.update(vo);
	
		return forwardSuccessPage(request,mapping,"更新成功","IssueRecord.do?act=list&projectId="+vo.getProjectId()+"&userId="+vo.getUserId()+"&status="+vo.getStatus());
	}
	public ActionForward edit(HttpServletRequest request,ActionMapping mapping,BaseForm form)throws Exception{

		String id = request.getParameter("id");
		
		IssueRecordVO vo = new IssueRecordVO();
	
		vo.setId(Integer.parseInt(id));
		
		vo = (IssueRecordVO)bo.queryForObject(vo);
		copyProperties(form,vo);
		
		return mapping.findForward("edit");
	}
	
}
