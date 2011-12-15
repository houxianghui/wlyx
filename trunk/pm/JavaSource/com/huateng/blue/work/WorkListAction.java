package com.huateng.blue.work;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.abc.action.IbatisAction;
import com.eis.base.BaseForm;

import com.eis.exception.MessageException;
import com.eis.portal.UserContext;
import com.projectmaintain.PreProjectVO;


public class WorkListAction extends IbatisAction {
	
	@Override
	public ActionForward process(ActionMapping mapping, BaseForm form,
			HttpServletRequest request, HttpServletResponse response,
			UserContext user) throws Exception {
		String act = form.getAct();
		if("list".equals(act)){
			return list(mapping,form,request,user);
		}
		if("initEdit".equals(act)){
			return initEdit(form, mapping,request,user);
		}
		if("qa".equals(act)){
			return listAll(mapping,form,request,user);
		}
		if("add".equals(act)){
			return addWork(form,mapping,request,user);
		}
		if("update".equals(act)){
			return updateWork(form, mapping, request, user);
		}
		if("view".equals(act)){
			return viewWork(form, mapping, request);
		}
		if("epw".equals(act)){
			return editPreWorks(form,mapping,request,user);
		}
		if("upp".equals(act)){
			return updatePreProjects(form, mapping, request);
		}
		if("delete".equals(act)){
			return deleteWork(form,mapping,request,user);
		}
		if("gmiw".equals(act)){
			return getMyInputWorks(mapping,form,request,user);
		}
		if("gdw".equals(act)){
			return getFinishedWorks(mapping,form,request,user);
		}
		if("finish".equals(act)){
			return doFinish(mapping,form,request,user);
		}
		if("setMain".equals(act)){
			return setMainWork(mapping,form,request,user);
		}
		return null;
	}
	private ActionForward setMainWork(ActionMapping mapping,BaseForm form,HttpServletRequest request,UserContext user)throws Exception{
		String workId = request.getParameter("workId");
		String isMain = request.getParameter("isMain");
		((WorkListBO)bo).setMainWork(workId,isMain);
		return forwardSuccessPage(request,mapping,"任务状态切换成功","WorkList.do?act=list");
	}
	private ActionForward list(ActionMapping mapping,BaseForm form,HttpServletRequest request,UserContext user)throws Exception{
		((WorkListBO)bo).queryForPage(request,form,user);
		return mapping.findForward("list");
	}
	private ActionForward getFinishedWorks(ActionMapping mapping,BaseForm form,HttpServletRequest request,UserContext user)throws Exception{
		((WorkListBO)bo).getFinishedWorks(request,form,user);
		return mapping.findForward("gdw");
	}
	private ActionForward doFinish(ActionMapping mapping,BaseForm form,HttpServletRequest request,UserContext user)throws Exception{
		((WorkListBO)bo).doFinishWork(request.getParameter("workId"));
		return forwardSuccessPage(request,mapping,"修改成功","WorkList.do?act=gmiw");
	}
	private ActionForward getMyInputWorks(ActionMapping mapping,BaseForm form,HttpServletRequest request,UserContext user)throws Exception{
		((WorkListBO)bo).queryForPage(request,form,user);
		return mapping.findForward("gm");
	}
	private ActionForward listAll(ActionMapping mapping,BaseForm form,HttpServletRequest request,UserContext user)throws Exception{
		((WorkListBO)bo).queryForPage(request,form,user);
		return mapping.findForward("listAll");
	}
	private ActionForward initEdit(BaseForm form,ActionMapping mapping,HttpServletRequest request,UserContext user)throws Exception{
		((WorkListBO)bo).validateUpdateStatus(request.getParameter("workId"),user);
		WorkList vo =(WorkList) bo.queryForObject(request.getParameter("workId"));
		copyProperties(form,vo);
		return mapping.findForward("edit");
	}
	private ActionForward addWork(BaseForm form,ActionMapping mapping,HttpServletRequest request,UserContext user)throws Exception{
		WorkList vo = new WorkList();
		copyProperties(vo,form);
		vo.setInputUser(user.getUserID());
		try{
			bo.insert(vo);
		}catch(Exception e){
			throw new MessageException(e.getCause().getCause().getMessage());
		}
		return forwardSuccessPage(request,mapping,"保存成功","WorkList.do?act=list");
	}
	private ActionForward updateWork(BaseForm form,ActionMapping mapping,HttpServletRequest request,UserContext user)throws Exception{
		String workId = ((WorkListForm)form).getWorkId();
		((WorkListBO)bo).validateUpdateStatus(workId,user);
		((WorkListForm)form).setInputUser(user.getUserID());		//设置更新人员ＩＤ
		bo.update(form);
		return forwardSuccessPage(request,mapping,"更新成功","WorkList.do?act=list");
	}
	private ActionForward deleteWork(BaseForm form,ActionMapping mapping,HttpServletRequest request,UserContext user)throws Exception{
		((WorkListBO)bo).validateUpdateStatus(request.getParameter("workId"),user);
		bo.delete(request.getParameter("workId"));
		return forwardSuccessPage(request,mapping,"删除成功","WorkList.do?act=list");
	}
	private ActionForward viewWork(BaseForm form,ActionMapping mapping,HttpServletRequest request)throws Exception{
		copyProperties(form,bo.queryForObject(request.getParameter("workId")));
		return mapping.findForward("view");
	}
	private ActionForward editPreWorks(BaseForm form,ActionMapping mapping,HttpServletRequest request,UserContext user)throws Exception{
		String workId = request.getParameter("workId");
		((WorkListBO)bo).validateUpdateStatus(workId, user);
		request.setAttribute("selectedProjects",((WorkListBO)bo).getSelectedWorks(workId));
		request.setAttribute("notSelectedProjects",((WorkListBO)bo).getNotSelectedWorks(workId));
		return mapping.findForward("epp");
	}
	private ActionForward updatePreProjects(BaseForm form,ActionMapping mapping,HttpServletRequest request)throws Exception{
		String projectIds[] = request.getParameterValues("rolesRight");
		if(projectIds == null){
			PreProjectVO vo = new PreProjectVO();
			vo.setProjectId(((WorkListForm)form).getWorkId());
			((WorkListBO)bo).deletePreProjects(vo);
		}else{
			String projectId = request.getParameter("workId");
			PreProjectVO vos[] = new PreProjectVO[projectIds.length];
			for(int i = 0;i < projectIds.length;i++){
				vos[i] = new PreProjectVO();
				vos[i].setProjectId(projectId);
				vos[i].setPreProject(projectIds[i]);
			}
			((WorkListBO)bo).transOperation(vos);
		}
		
		return forwardSuccessPage(request,mapping,"更新成功","WorkList.do?act=list");
	}

}	
