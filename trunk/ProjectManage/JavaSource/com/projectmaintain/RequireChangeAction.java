/*
 * @# RequireChangeAction.java 2009-6-12 houxh
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
import com.eis.portal.UserContext;
import com.eis.util.DateUtil;


public class RequireChangeAction extends IbatisBaseAction {

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
			return getQueryList(form,request,mapping);
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
	public ActionForward getQueryList(BaseForm form,HttpServletRequest request,ActionMapping mapping)throws Exception{
		RequireChangeVO vo = new RequireChangeVO();
		vo.setProjectId(request.getParameter("projectId"));
		vo.setUserId(request.getParameter("version"));
		copyProperties(form,vo);
		
//		String sql="select * from require_changes where PROJECT_ID='"+vo.getProjectId()+"'";
//		setPageResult(request,sql,"queryRequireChangeList");
		setPageResult(request, bo.queryForList(vo));
		request.setAttribute("version",new Integer(vo.getVersion()));
		request.setAttribute("projectId",vo.getProjectId());
		
		return mapping.findForward("list");
	}
	public ActionForward add(BaseForm form,HttpServletRequest request,ActionMapping mapping,UserContext user)throws Exception{
		RequireChangeVO vo = new RequireChangeVO();
		copyProperties(vo,form);
		vo.setUserId(user.getUserID());
		vo.setChangeDate(DateUtil.getDTStr());
		
		int version = ((RequireChangeBO)bo).queryForNextVersion(vo.getProjectId());
		vo.setVersion(version);
		
		bo.insert(vo);
		
		return forwardSuccessPage(request,mapping,"保存成功","RequireChange.do?act=list&projectId="+vo.getProjectId()+"&version="+vo.getVersion());
	}
	public ActionForward delete(BaseForm form,HttpServletRequest request,ActionMapping mapping)throws Exception{
		String projectId = request.getParameter("projectId");
		String version = request.getParameter("version");
		RequireChangeVO vo = new RequireChangeVO();
		vo.setProjectId(projectId);
		vo.setVersion(Integer.parseInt(version));
		
		bo.delete(vo);		
		
		return forwardSuccessPage(request,mapping,"删除成功","RequireChange.do?act=list&projectId="+vo.getProjectId()+"&version="+vo.getVersion());
		
	}
	public ActionForward update(BaseForm form,HttpServletRequest request,ActionMapping mapping,UserContext user)throws Exception{
		RequireChangeVO vo = new RequireChangeVO();
		copyProperties(vo,form);
		vo.setUserId(user.getUserID());
				
		bo.update(vo);
	
		return forwardSuccessPage(request,mapping,"更新成功","RequireChange.do?act=list&projectId="+vo.getProjectId()+"&version="+vo.getVersion());
	}
	public ActionForward edit(HttpServletRequest request,ActionMapping mapping,BaseForm form)throws Exception{

		String projectId = request.getParameter("projectId");
		String version = request.getParameter("version");
		
		RequireChangeVO vo = new RequireChangeVO();
	
		vo.setProjectId(projectId);
		vo.setVersion(Integer.parseInt(version));
		
		vo = (RequireChangeVO)bo.queryForObject(vo);
		
		copyProperties(form,vo);
		
		return mapping.findForward("edit");
	}

}
