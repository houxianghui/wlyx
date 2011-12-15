/*
 * @# ProjectMaintainAction.java 2008-11-6 houxh
 *
 * Copyright  (c)  2003 	Huateng. All Right Reserv
 */
 
package com.projectmaintain;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.eis.base.BaseForm;
import com.eis.base.IbatisBaseAction;
import com.eis.cache.SingleDic;
import com.eis.cache.SingleDicMap;
import com.eis.portal.UserContext;
import com.eis.util.CheckUtil;
import com.eis.util.ValidateUtil;


public class ProjectMaintainAction extends IbatisBaseAction {

	/* 
	 * @see com.eis.base.BaseAction#process(org.apache.struts.action.ActionMapping, com.eis.base.BaseForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, com.eis.portal.UserContext)
	 */
	public ActionForward process(ActionMapping mapping, BaseForm form, HttpServletRequest request, HttpServletResponse response, UserContext user) throws Exception {
		String act = form.getAct();
		if("ap".equals(act)){		//add project
			return addProject(form,mapping,request,user);
		}
		if("up".equals(act)){		//update project
			return updateProject(form,mapping,request,user);
		}
		if("ep".equals(act)){		//edit project
			return initEdit(form,mapping);
		}
		if("dp".equals(act)){		//delete project
			return deleteProject(form,mapping,request,user);
		}
		if("qp".equals(act)){		//query project
			return queryProject(form,mapping,request);
		}
		if("qpl".equals(act)){		//query active projects
			return queryProjectList(form,mapping,request);
		}
		if("qal".equals(act)){		//query all projects
			return queryAllProjectList(form,mapping,request);
		}
		if("epp".equals(act)){		//edit preprojects
			return editPreProjects(form,mapping,request);
		}
		if("upp".equals(act)){		//update preprojects
			return updatePreProjects(form,mapping,request);
		}
		if("gpp".equals(act)){		//get preprojects
			return getPreProjects(form,mapping,request);
		}
		if("gcp".equals(act)){		//get conflict projects
			return getConflictProjects(form,mapping,request);
		}
		if("gc".equals(act)){
			return getChangeList(mapping,request,form);
		}
		if("gmip".equals(act)){
			return getMyInputProjects(form,mapping,request,user);
		}
		if("d".equals(act)){
			getSubMenu(request,response);
			return null;
		}
		return forwardError(request,mapping,"页面未找到,错误发生在"+this.getClass().getName());
	}
	
	private void getSubMenu(HttpServletRequest request,HttpServletResponse response)throws Exception{
		
		String type = null;
		HashMap map = null;
		String projectClass = request.getParameter("projectClass").trim();
		if(ProjectMaintainForm.V_PLUS.equals(projectClass)){
			map = SingleDicMap.getDicMap(ProjectMaintainForm.V_PLUS_COLLECTION);
			type = ProjectMaintainForm.V_PLUS_COLLECTION;
		}else if(ProjectMaintainForm.EIS.equals(projectClass)){
			map = SingleDicMap.getDicMap(ProjectMaintainForm.EIS_COLLECTION);
			type = ProjectMaintainForm.EIS_COLLECTION;
		}
		StringBuffer sb = new StringBuffer("<?xml version=\"1.0\" encoding=\"gbk\"?><options>");
		if(map != null){
			processValues(sb,map,type);
		}else{
			sb.append("<option><key>&nbsp;</key><value>&nbsp;</value><option>");
		}
		
		sb.append("</options>");
		
		response.setContentType("text/html;charset=gbk");
		PrintWriter pw = response.getWriter();
		response.setContentType("text/xml");
		response.setLocale(Locale.CHINESE);
		
		pw.write(sb.toString());
		pw.close();
	}
	private void processValues(StringBuffer sb,HashMap map,String type){
		Set set = map.keySet();
		Iterator it = set.iterator();
		while(it.hasNext()){
			String key = (String)it.next();
			sb.append("<option>");
			setKey(key,sb);
			String value = SingleDicMap.getDicItemVal(type,key);
			setValue(value,sb);
			sb.append("</option>");
		}
	}
	private void setKey(String key,StringBuffer sb){
		sb.append("<key>");
		sb.append(key);
		sb.append("</key>");
	}
	private void setValue(String value,StringBuffer sb){
		sb.append("<value>");
		sb.append(value);
		sb.append("</value>");
	}
	private ActionForward getChangeList(ActionMapping mapping,HttpServletRequest request,BaseForm form)throws Exception{
		String projectId = request.getParameter("projectId");
		String id = request.getParameter("id");
		if(CheckUtil.isEmptry(id)){
			id = "-1";
		}
		((ProjectMaintainForm)form).setId(Integer.parseInt(id));
		setPageResult(request,((ProjectMaintainBO)bo).getChangeRecord(projectId,id));
		return mapping.findForward("changelist");
	}
	public ActionForward initEdit(BaseForm form,ActionMapping mapping)throws Exception{
		ProjectMaintainVO vo =(ProjectMaintainVO) bo.queryForObject(form);
		copyProperties(form,vo);
		return mapping.findForward("edit");
	}
	public ActionForward addProject(BaseForm form,ActionMapping mapping,HttpServletRequest request,UserContext user)throws Exception{
		ProjectMaintainVO vo = new ProjectMaintainVO();
		copyProperties(vo,form);
		vo.setUserId(user.getUserID());
		bo.insert(vo);
		return forwardSuccessPage(request,mapping,"保存成功","ProjectMaintain.do?act=qpl");
	}
	public ActionForward updateProject(BaseForm form,ActionMapping mapping,HttpServletRequest request,UserContext user)throws Exception{
		((ProjectMaintainBO)bo).validateUpdateStatus(form);
		ProjectMaintainVO vo = new ProjectMaintainVO();
		copyProperties(vo,form);
		ValidateUtil.rejectIfEmpty(vo.getReason(),"修改原因");
		vo.setUserId(user.getUserID());		
		bo.update(vo);
		return forwardSuccessPage(request,mapping,"更新成功","ProjectMaintain.do?act=qpl");
	}
	public ActionForward deleteProject(BaseForm form,ActionMapping mapping,HttpServletRequest request,UserContext user)throws Exception{
		ProjectMaintainVO vo = new ProjectMaintainVO();
		copyProperties(vo,form);
		vo.setUserId(user.getUserID());
		bo.delete(vo);
		return forwardSuccessPage(request,mapping,"删除成功","ProjectMaintain.do?act=qpl");
	}
	public ActionForward queryProject(BaseForm form,ActionMapping mapping,HttpServletRequest request)throws Exception{
		((ProjectMaintainForm)form).setProjectId(request.getParameter("projectId"));
		copyProperties(form,bo.queryForObject(form));
		return mapping.findForward("view");
	}
	public ActionForward queryProjectList(BaseForm form,ActionMapping mapping,HttpServletRequest request)throws Exception{
		setPageResult(request,bo.queryForList(form));
		return mapping.findForward("qpl");
	}
	public ActionForward getMyInputProjects(BaseForm form,ActionMapping mapping,HttpServletRequest request,UserContext user)throws Exception{
		((ProjectMaintainForm)form).setUserId(user.getUserID());
		setPageResult(request,((ProjectMaintainBO)bo).getMyInputProjects(form));
		return mapping.findForward("gmip");
	}
	public ActionForward queryAllProjectList(BaseForm form,ActionMapping mapping,HttpServletRequest request)throws Exception{
		setPageResult(request,((ProjectMaintainBO)bo).queryForAllList(form));
		return mapping.findForward("qal");
	}
	public ActionForward editPreProjects(BaseForm form,ActionMapping mapping,HttpServletRequest request)throws Exception{
		request.setAttribute("selectedProjects",((ProjectMaintainBO)bo).getSelectedProjects(form));
		request.setAttribute("notSelectedProjects",((ProjectMaintainBO)bo).getNotSelectedProjects(form));
		return mapping.findForward("epp");
	}
	public ActionForward updatePreProjects(BaseForm form,ActionMapping mapping,HttpServletRequest request)throws Exception{
		String projectIds[] = request.getParameterValues("rolesRight");
		if(projectIds == null){
			PreProjectVO vo = new PreProjectVO();
			vo.setProjectId(((ProjectMaintainForm)form).getProjectId());
			((ProjectMaintainBO)bo).deletePreProjects(vo);
		}else{
			String projectId = request.getParameter("projectId");
			PreProjectVO vos[] = new PreProjectVO[projectIds.length];
			for(int i = 0;i < projectIds.length;i++){
				vos[i] = new PreProjectVO();
				vos[i].setProjectId(projectId);
				vos[i].setPreProject(projectIds[i]);
			}
			bo.transOperation(vos);
		}
		
		return forwardSuccessPage(request,mapping,"更新成功","ProjectMaintain.do?act=qpl");
	}
	public ActionForward getPreProjects(BaseForm form,ActionMapping mapping,HttpServletRequest request)throws Exception{
		request.setAttribute("selectedProjects",((ProjectMaintainBO)bo).getSelectedProjects(form));
		return mapping.findForward("gpp");
	}
	public ActionForward getConflictProjects(BaseForm form,ActionMapping mapping,HttpServletRequest request)throws Exception{
		List relate = ((ProjectMaintainBO)bo).getRelateProjects(form);
		List l = ((ProjectMaintainBO)bo).getConflictProjects(form);
		request.setAttribute("conflictProjects", getConflictTable(l,relate).toString());
		return mapping.findForward("gcp");
	}
	private StringBuffer getConflictTable(List l,List relate) {
		StringBuffer head = new StringBuffer("<tr align='center' class='dtPanel_Top01'><td>");
		StringBuffer body = new StringBuffer("<tr align='left' class='dtPanel_Main'><td align='center' class='dtPanel_Top01'>");
		Iterator it = l.iterator();
		String preProject = null;
		while(it.hasNext()){
			ConflictProjectVO vo = (ConflictProjectVO)it.next();
			if(!relate.contains(vo.getPreProject())){
				if(preProject == null){
					preProject = vo.getPreProject();
					head.append(vo.getProjectId()+"</td>");
					body.append("冲突程序列表");
					head.append("<td>"+preProject+"-"+vo.getProjectName()+"&nbsp;阶段("+SingleDicMap.getDicItemVal(SingleDic.PROJECT_MANAGE_COLLECTION,vo.getStatus())+")</td>");
					body.append("<td>"+vo.getProgram()+"<br>");
	
				}else{
					if(preProject.equals(vo.getPreProject())){
						body.append(vo.getProgram()+"<br>");
					}else{
						preProject = vo.getPreProject();
						head.append("<td>"+preProject+"-"+vo.getProjectName()+"&nbsp;阶段("+SingleDicMap.getDicItemVal(SingleDic.PROJECT_MANAGE_COLLECTION,vo.getStatus())+")</td>");
						body.append("</td><td>"+vo.getProgram()+"<br>");
					}
				}
			}
			
		}
		head.append("</td></tr>");
		body.append("</td></tr>");
		head.append(body);
		return head;
	}
}
