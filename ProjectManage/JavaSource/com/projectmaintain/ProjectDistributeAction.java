/*
 * @# ProjectDistributeAction.java 2008-11-21 houxh
 *
 * Copyright  (c)  2003 	Huateng. All Right Reserv
 */
 
package com.projectmaintain;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionRedirect;

import com.eis.base.BaseForm;
import com.eis.base.IbatisBaseAction;
import com.eis.exception.MessageException;
import com.eis.portal.UserContext;
import com.eis.util.CheckUtil;
import com.eis.util.DateUtil;
import com.eis.util.ValidateUtil;


public class ProjectDistributeAction extends IbatisBaseAction {

	/* 
	 * @see com.eis.base.BaseAction#process(org.apache.struts.action.ActionMapping, com.eis.base.BaseForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, com.eis.portal.UserContext)
	 */
	public ActionForward process(ActionMapping mapping, BaseForm form, HttpServletRequest request, HttpServletResponse response, UserContext user) throws Exception {
		String act=form.getAct();
		if("gd".equals(act)){
			return getDistributeInit(form,request,mapping);
		}
		if("gmp".equals(act)){
			return getMyProjects(mapping,user,request);
		}
		if("gdp".equals(act)){
			return getDoneProjects(request,mapping,user);
		}
		if("ud".equals(act)){
			return updateDistribute(form,mapping,request);
		}
		if("qd".equals(act)){
			return getProjectDistribute(request,mapping);
		}
		if("dl".equals(act)){
			return getProjectDistributeList(request,mapping);
		}
		if("edit".equals(act)){
			return editProjectDistribute(form,mapping);			
		}
		if("new".equals(act)){
			return addProjectDistribute(form,request,mapping);
		}
		if("add".equals(act)){
			return insertProjectDistribute(form,request,mapping);
		}
		if("update".equals(act)){
			return updateProjectDistribute(form,request,mapping,user);
		}
		if("detail".equals(act)){
			return getDetail(form,request,mapping);
		}
		if("delete".equals(act)){
			return delete(form,request,mapping);
		}
		if("display".equals(act)){
			return displayWorkTable(request,mapping);
		}
		if("doFinish".equals(act)){
			return doFinished(form,mapping,request,user);
		}
		if("addTask".equals(act)){
			return addTask(form);
		}
		if("check".equals(act)){
			return checkMyProjects(mapping,user,request);
		}
		if("score".equals(act)){
			return getScoreInfo(form,mapping,user,request);
		}
		if("issueRecord".equals(act)){
			return issueRecord(form,mapping);
		}
		if("getNotDoneWorks".equals(act)){
			return getNotDoneWorks(form,mapping,request);
		}
		return null;
	}
	public ActionForward getNotDoneWorks(BaseForm form,ActionMapping mapping,HttpServletRequest request)throws Exception{
		ProjectDistributeForm f = (ProjectDistributeForm)form;
		if(CheckUtil.isEmptry(f.getEndDate_f())){
			f.setEndDate_f(DateUtil.getDTStr());
		}
		if(CheckUtil.isEmptry(f.getDay())){
			f.setDay("0");
		}
		setPageResult(request,((ProjectDistributeBO)bo).getNotDoneWorks(form));
		return mapping.findForward("getNotDoneWorks");
	}
	public ActionForward getScoreInfo(BaseForm form,ActionMapping mapping,UserContext user,HttpServletRequest request)throws Exception{
		ProjectDistributeVO vo = (ProjectDistributeVO)((ProjectDistributeBO)bo).getProjectDistributeDetail(form);
		StringBuffer url = new StringBuffer("ScoreInfo.do?act=init&projectId=");
		url.append(vo.getProjectId());
		url.append("&userId=");
		url.append(vo.getUserId());
		url.append("&status=");
		url.append(vo.getStatus());
		return new ActionRedirect(url.toString());
	}
	public ActionForward issueRecord(BaseForm form,ActionMapping mapping)throws Exception{
		ProjectDistributeVO vo = (ProjectDistributeVO)((ProjectDistributeBO)bo).getProjectDistributeDetail(form);
		StringBuffer url = new StringBuffer("IssueRecord.do?act=list&projectId=");
		url.append(vo.getProjectId());
		url.append("&userId=");
		url.append(vo.getUserId());
		url.append("&status=");
		url.append(vo.getStatus());
		return new ActionRedirect(url.toString());
	}
	public ActionForward editProjectDistribute(BaseForm form, ActionMapping mapping)throws Exception{
		ProjectDistributeVO vo = (ProjectDistributeVO)((ProjectDistributeBO)bo).getProjectDistributeDetail(form);
		copyProperties(form,vo);
		return mapping.findForward("edit");
	}
	public ActionForward doFinished(BaseForm form,ActionMapping mapping,HttpServletRequest request,UserContext user)throws Exception{
		((ProjectDistributeBO)bo).validateStatus(form);
		((ProjectDistributeBO)bo).validateWork(form,user);
		((ProjectDistributeBO)bo).finishMyProject(form,user);
		return forwardSuccessPage(request,mapping,"保存成功","ProjectDistribute.do?act=gmp");
	}
	public ActionForward addTask(BaseForm form)throws Exception{
		((ProjectDistributeBO)bo).validateStatus(form);
		return new ActionForward("/TASK_LIST.do?act=c&project_no="+((ProjectDistributeForm)form).getProjectId()+"&curr_step="+((ProjectDistributeForm)form).getStatus()+"&id="+((ProjectDistributeForm)form).getId());
	}
	public ActionForward displayWorkTable(HttpServletRequest request,ActionMapping mapping)throws Exception{
		String flag = request.getParameter("flag");
		String date = request.getParameter("date");
		if(date == null || date.trim().length() == 0){
			date = DateUtil.getDTStr();
		}else{
			date = date+"01";
		}
		if("next".equals(flag)){
			date = DateUtil.getDateAfterMonth(date,1);			
		}else if("pre".equals(flag)){
			date = DateUtil.getDateBeforeMonth(date,1);
		}
		request.setAttribute("date",date.substring(0,6));
		request.setAttribute("worktable",((ProjectDistributeBO)bo).getDisplay(date));		
		return mapping.findForward("display");
	}
	
	public ActionForward delete(BaseForm form ,HttpServletRequest request,ActionMapping mapping)throws Exception{
		((ProjectDistributeBO)bo).remove(form);
		return forwardSuccessPage(request,mapping,"删除成功","ProjectDistribute.do?act=dl&projectId="+((ProjectDistributeForm)form).getProjectId());
	}	
	public ActionForward getDetail(BaseForm form ,HttpServletRequest request,ActionMapping mapping)throws Exception{
		((ProjectDistributeForm)form).setId(Integer.parseInt(request.getParameter("id")));
				
		ProjectDistributeVO vo = (ProjectDistributeVO)((ProjectDistributeBO)bo).getProjectDistributeDetail(form);
		copyProperties(form,vo);
		return mapping.findForward("detail");
	}
	public ActionForward updateProjectDistribute(BaseForm form ,HttpServletRequest request,ActionMapping mapping,UserContext user)throws Exception{
		ProjectDistributeVO vo = new ProjectDistributeVO();
		copyProperties(vo,form);
		ValidateUtil.rejectIfEmpty(vo.getReason(),"修改原因");
		((ProjectDistributeBO)bo).checkBusy(vo);
		((ProjectDistributeBO)bo).update(vo,user);
		return forwardSuccessPage(request,mapping,"更新成功","ProjectDistribute.do?act=dl&projectId="+vo.getProjectId());
	}	
	public ActionForward addProjectDistribute(BaseForm form ,HttpServletRequest request,ActionMapping mapping)throws Exception{
		String projectId = request.getParameter("projectId");
		((ProjectDistributeForm)form).setProjectId(projectId);		
		((ProjectDistributeForm)form).setStatus(request.getParameter("pStatus"));
		request.setAttribute("notSelectedStuff",((ProjectDistributeBO)bo).getNotSelectedStuff(form));
		return mapping.findForward("new");
	}	
	public ActionForward insertProjectDistribute(BaseForm form,HttpServletRequest request,ActionMapping mapping)throws Exception{
		
		String userIds[] = request.getParameterValues("rolesRight");
		if(userIds == null || userIds.length == 0){		
			throw new MessageException("没有分配人员");
		}else{			
			ProjectDistributeVO vos[] = new ProjectDistributeVO[userIds.length];
			for(int i = 0;i < userIds.length;i++){
				vos[i] = new ProjectDistributeVO();				
				copyProperties(vos[i],form);
				vos[i].setUserId(userIds[i]);
				((ProjectDistributeBO)bo).checkBusy(vos[i]);
			}
			bo.transOperation(vos);
		}	
		
		return forwardSuccessPage(request,mapping,"保存成功","ProjectDistribute.do?act=dl&projectId="+((ProjectDistributeForm)form).getProjectId());
	}
	/**
	 * 获得指定项目的分配情况
	 * 
	 * @param form
	 * @param request
	 * @param mapping
	 * @return
	 * @throws Exception
	 */
	public ActionForward getProjectDistributeList(HttpServletRequest request,ActionMapping mapping)throws Exception{
		String projectId = request.getParameter("projectId");
		request.setAttribute("projectId",projectId);
		request.setAttribute("pStatus",request.getParameter("status"));
		setPageResult(request,((ProjectDistributeBO)bo).queryForProjectDistributeList(projectId));
		return mapping.findForward("al");
	}
	public ActionForward getProjectDistribute(HttpServletRequest request,ActionMapping mapping)throws Exception{
		setPageResult(request,bo.queryForList(request.getParameter("projectId")));
		return mapping.findForward("view");
	}
	public ActionForward getDistributeInit(BaseForm form ,HttpServletRequest request,ActionMapping mapping)throws Exception{
		String projectId = request.getParameter("projectId");
		((ProjectDistributeForm)form).setProjectId(projectId);
		request.setAttribute("selectedStuff",((ProjectDistributeBO)bo).getSelectedStuff(projectId));
		request.setAttribute("notSelectedStuff",((ProjectDistributeBO)bo).getNotSelectedStuff(projectId));
		return mapping.findForward("ed");
	}
	public ActionForward getMyProjects(ActionMapping mapping,UserContext user,HttpServletRequest request)throws Exception{

		setPageResult(request,((ProjectDistributeBO)bo).getMyProjects(user.getUserID()));
		return mapping.findForward("list");
	}
	public ActionForward getDoneProjects(HttpServletRequest request,ActionMapping mapping,UserContext user)throws Exception{
		setPageResult(request,((ProjectDistributeBO)bo).getDoneProjectDistributes(user.getUserID()));
		return mapping.findForward("doneList");
	}
	public ActionForward updateDistribute(BaseForm form,ActionMapping mapping,HttpServletRequest request)throws Exception{
		String userIds[] = request.getParameterValues("rolesRight");
		if(userIds == null || userIds.length == 0){
			ProjectDistributeVO vo = new ProjectDistributeVO();
			vo.setProjectId(((ProjectDistributeForm)form).getProjectId());
			bo.delete(vo);
		}else{
			String projectId = request.getParameter("projectId");
			ProjectDistributeVO vos[] = new ProjectDistributeVO[userIds.length];
			for(int i = 0;i < userIds.length;i++){
				vos[i] = new ProjectDistributeVO();
				vos[i].setProjectId(projectId);
				vos[i].setUserId(userIds[i]);
			}
			bo.transOperation(vos);
		}
		return forwardSuccessPage(request,mapping,"更新成功","ProjectMaintain.do?act=qpl");
	}
	public ActionForward checkMyProjects(ActionMapping mapping,UserContext user,HttpServletRequest request)throws Exception{
		request.setAttribute("flag","n");//传到页面的信息，标志是否显示提示
		if(((ProjectDistributeBO)bo).checkBlankWork(user)){
			request.setAttribute("flag","y");
		}
		return mapping.findForward("note");
	}
}
