package com.huateng.blue.work;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionRedirect;

import com.abc.action.IbatisAction;
import com.eis.base.BaseForm;
import com.eis.exception.MessageException;
import com.eis.portal.UserContext;
import com.eis.util.CheckUtil;
import com.eis.util.DateUtil;
import com.eis.util.ValidateUtil;
import com.projectmaintain.ProjectDistributeForm;
import com.projectmaintain.ProjectDistributeVO;

public class WorkDistributeAction extends IbatisAction {
	
	@Override
	public ActionForward process(ActionMapping mapping, BaseForm form,
			HttpServletRequest request, HttpServletResponse response,
			UserContext user) throws Exception {
		String act=form.getAct();
		if("init".equals(act)){
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
			return getWorkDistributeList(request,mapping);
		}
		if("edit".equals(act)){
			return editWorkDistribute(form,mapping,request);			
		}
		if("new".equals(act)){
			return addWorkDistribute(form,request,mapping);
		}
		if("add".equals(act)){
			return insertWorkDistribute(form,request,mapping,user);
		}
		if("update".equals(act)){
			return updateWorkDistribute(form,request,mapping,user);
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
//		setPageResult(request,((ProjectDistributeBO)bo).getNotDoneWorks(form));
		return mapping.findForward("getNotDoneWorks");
	}
	public ActionForward getScoreInfo(BaseForm form,ActionMapping mapping,UserContext user,HttpServletRequest request)throws Exception{
//		ProjectDistributeVO vo = (ProjectDistributeVO)((ProjectDistributeBO)bo).getProjectDistributeDetail(form);
		StringBuffer url = new StringBuffer("ScoreInfo.do?act=init&projectId=");
//		url.append(vo.getProjectId());
//		url.append("&userId=");
//		url.append(vo.getUserId());
//		url.append("&status=");
//		url.append(vo.getStatus());
		return new ActionRedirect(url.toString());
	}
	public ActionForward issueRecord(BaseForm form,ActionMapping mapping)throws Exception{
//		ProjectDistributeVO vo = (ProjectDistributeVO)((ProjectDistributeBO)bo).getProjectDistributeDetail(form);
		StringBuffer url = new StringBuffer("IssueRecord.do?act=list&projectId=");
//		url.append(vo.getProjectId());
//		url.append("&userId=");
//		url.append(vo.getUserId());
//		url.append("&status=");
//		url.append(vo.getStatus());
		return new ActionRedirect(url.toString());
	}
	public ActionForward editWorkDistribute(BaseForm form, ActionMapping mapping,HttpServletRequest request)throws Exception{
		WorkDistribute vo = (WorkDistribute)((WorkDistributeBO)bo).queryForObject(request.getParameter("id"));
		copyProperties(form,vo);
		return mapping.findForward("edit");
	}
	public ActionForward doFinished(BaseForm form,ActionMapping mapping,HttpServletRequest request,UserContext user)throws Exception{
//		((ProjectDistributeBO)bo).validateStatus(form);
//		((ProjectDistributeBO)bo).validateWork(form,user);
//		((ProjectDistributeBO)bo).finishMyProject(form,user);
		return forwardSuccessPage(request,mapping,"保存成功","ProjectDistribute.do?act=gmp");
	}
	public ActionForward addTask(BaseForm form)throws Exception{
//		((ProjectDistributeBO)bo).validateStatus(form);
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
//		request.setAttribute("worktable",((ProjectDistributeBO)bo).getDisplay(date));		
		return mapping.findForward("display");
	}
	
	public ActionForward delete(BaseForm form ,HttpServletRequest request,ActionMapping mapping)throws Exception{
		String workId = request.getParameter("workId");
		bo.delete(request.getParameter("id"));
		return forwardSuccessPage(request,mapping,"删除成功","WorkDistribute.do?act=dl&workId="+workId);
	}	
	public ActionForward getDetail(BaseForm form ,HttpServletRequest request,ActionMapping mapping)throws Exception{
		String id = request.getParameter("id");
		WorkDistribute wd = (WorkDistribute)bo.queryForObject(id);		
//		ProjectDistributeVO vo = (ProjectDistributeVO)((ProjectDistributeBO)bo).getProjectDistributeDetail(form);
		copyProperties(form,wd);
		return mapping.findForward("detail");
	}
	/**
	 * 修改任务分配信息
	 * @param form
	 * @param request
	 * @param mapping
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public ActionForward updateWorkDistribute(BaseForm form ,HttpServletRequest request,ActionMapping mapping,UserContext user)throws Exception{
		ValidateUtil.rejectIfEmpty(((WorkDistributeForm)form).getReason(),"修改原因");
//		((ProjectDistributeBO)bo).checkBusy(vo);	//busy校验，针对每日只能做一件事的情况做判断
		((WorkDistributeForm)form).setInputUser(user.getUserID());	//设置修改人员编号
		((WorkDistributeBO)bo).update(form);
		return forwardSuccessPage(request,mapping,"更新成功","WorkDistribute.do?act=dl&workId="+((WorkDistributeForm)form).getWorkId());
	}	
	/**
	 * 进入新增任务分配页面
	 * @param form
	 * @param request
	 * @param mapping
	 * @return
	 * @throws Exception
	 */
	public ActionForward addWorkDistribute(BaseForm form ,HttpServletRequest request,ActionMapping mapping)throws Exception{
		String workId = request.getParameter("workId");
		((WorkDistributeForm)form).setWorkId(workId);		
		request.setAttribute("notSelectedStuff",((WorkDistributeBO)bo).getNotSelectedStuff(workId));
		return mapping.findForward("new");
	}	
	/**
	 * 新增任务分配
	 * @param form
	 * @param request
	 * @param mapping
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public ActionForward insertWorkDistribute(BaseForm form,HttpServletRequest request,ActionMapping mapping,UserContext user)throws Exception{
		
		String userIds[] = request.getParameterValues("rolesRight");
		if(userIds == null || userIds.length == 0){		
			throw new MessageException("没有分配人员");
		}else{			
			WorkDistribute vos[] = new WorkDistribute[userIds.length];
			for(int i = 0;i < userIds.length;i++){
				vos[i] = new WorkDistribute();				
				copyProperties(vos[i],form);
				vos[i].setUserId(userIds[i]);
				vos[i].setInputUser(user.getUserID());
				vos[i].setInputDate(DateUtil.getDTStr());
				vos[i].setWorkStatus("N");
//				((ProjectDistributeBO)bo).checkBusy(vos[i]);
			}
			((WorkDistributeBO)bo).transOperation(vos);
		}	
		
		return forwardSuccessPage(request,mapping,"保存成功","WorkDistribute.do?act=dl&workId="+((WorkDistributeForm)form).getWorkId());
	}
	/**
	 * 获得指定任务的分配情况
	 * 
	 * @param form
	 * @param request
	 * @param mapping
	 * @return
	 * @throws Exception
	 */
	public ActionForward getWorkDistributeList(HttpServletRequest request,ActionMapping mapping)throws Exception{
		String projectId = request.getParameter("workId");
		request.setAttribute("workId",projectId);
		((WorkDistributeBO)bo).setPageList(request);
		return mapping.findForward("al");
	}
	public ActionForward getProjectDistribute(HttpServletRequest request,ActionMapping mapping)throws Exception{
		setPageResult(request,bo.queryForList(request.getParameter("workId")));
		return mapping.findForward("view");
	}
	public ActionForward getDistributeInit(BaseForm form ,HttpServletRequest request,ActionMapping mapping)throws Exception{
		String workId = request.getParameter("workId");
		((WorkDistributeForm)form).setWorkId(workId);
		request.setAttribute("selectedStuff",((WorkDistributeBO)bo).getSelectedStuff(workId));
		request.setAttribute("notSelectedStuff",((WorkDistributeBO)bo).getNotSelectedStuff(workId));
		return mapping.findForward("ed");
	}
	public ActionForward getMyProjects(ActionMapping mapping,UserContext user,HttpServletRequest request)throws Exception{

//		setPageResult(request,((ProjectDistributeBO)bo).getMyProjects(user.getUserID()));
		return mapping.findForward("list");
	}
	public ActionForward getDoneProjects(HttpServletRequest request,ActionMapping mapping,UserContext user)throws Exception{
//		setPageResult(request,((ProjectDistributeBO)bo).getDoneProjectDistributes(user.getUserID()));
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
//			bo.transOperation(vos);
		}
		return forwardSuccessPage(request,mapping,"更新成功","ProjectMaintain.do?act=qpl");
	}
	public ActionForward checkMyProjects(ActionMapping mapping,UserContext user,HttpServletRequest request)throws Exception{
		request.setAttribute("flag","n");//传到页面的信息，标志是否显示提示
//		if(((ProjectDistributeBO)bo).checkBlankWork(user)){
			request.setAttribute("flag","y");
//		}
		return mapping.findForward("note");
	}

}
