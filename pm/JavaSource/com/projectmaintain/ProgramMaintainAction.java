/*
 * @# ProgramMaintainAction.java 2008-11-12 houxh
 *
 * Copyright  (c)  2003 	Huateng. All Right Reserv
 */
 
package com.projectmaintain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.eis.base.BaseForm;
import com.eis.base.IbatisBaseAction;
import com.eis.exception.MessageException;
import com.eis.portal.UserContext;


public class ProgramMaintainAction extends IbatisBaseAction {

	/* 
	 * @see com.eis.base.BaseAction#process(org.apache.struts.action.ActionMapping, com.eis.base.BaseForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, com.eis.portal.UserContext)
	 */
	public ActionForward process(ActionMapping mapping, BaseForm form, HttpServletRequest request, HttpServletResponse response, UserContext user) throws Exception {
		String act = form.getAct();
		if("upload".equals(act)){
			return processFile(request,mapping,form);
		}
		if("input".equals(act)){
			return processInput(request,mapping,form);
		}
		if("edit".equals(act)){
			return editProgramList(request,mapping,form);
		}
		if("ql".equals(act)){
			return getProgramList(request,mapping,form);
		}
		return forwardError(request,mapping,"参数异常,异常发生在"+this.getClass().getName());
	}
	public ActionForward processFile(HttpServletRequest request,ActionMapping mapping,BaseForm form)throws Exception{
		String projectId = request.getParameter("projectId");
		FormFile file = ((ProgramMaintainForm)form).getFile();
		if(file == null){
			return forwardError(request,mapping,"请选择文件");
		}
		BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream(),"GBK"));
		updateProgramList(projectId, br);
		br.close();
		return forwardSuccessPage(request,mapping,"上传成功","ProjectMaintain.do?act=qpl");
	}
	private void updateProgramList(String projectId, BufferedReader br) throws IOException, Exception {
		String s=null;;
		ArrayList al = new ArrayList();
		ArrayList programs = new ArrayList();
		while((s=br.readLine()) != null){
			if(s.trim().length() == 0){
				continue;
			}
			ProgramMaintainVO vo = new ProgramMaintainVO();
			vo.setProjectId(projectId);
			vo.setProgram(s);
			al.add(vo);
			programs.add(s.trim());
		}
		if(al.size() == 0){
			throw new MessageException("内容不能为空");
		}
		((ProgramMaintainBO)bo).rejectRepeated(programs);
		bo.transOperation(al.toArray());
	}
	
	public ActionForward getProgramList(HttpServletRequest request,ActionMapping mapping,BaseForm form)throws Exception{
		String projectId = request.getParameter("projectId");
		if(projectId != null){
			((ProgramMaintainForm)form).setProjectId(projectId);
		}
		setPageResult(request,bo.queryForList(form));
		return mapping.findForward("ql");
	}
	public ActionForward editProgramList(HttpServletRequest request,ActionMapping mapping,BaseForm form)throws Exception{
		String projectId = request.getParameter("projectId");
		if(projectId != null){
			((ProgramMaintainForm)form).setProjectId(projectId);
		}
		List l = bo.queryForList(form);
		Iterator it = l.iterator();
		StringBuffer sb = new StringBuffer();
		while(it.hasNext()){
			ProgramMaintainVO vo = (ProgramMaintainVO)it.next();
			sb.append(vo.getProgram()+"\n");
		}
		((ProgramMaintainForm)form).setProgram(sb.toString());
		return mapping.findForward("edit");
	}
	public ActionForward processInput(HttpServletRequest request,ActionMapping mapping,BaseForm form)throws Exception{
		String projectId = request.getParameter("projectId");
		String program = ((ProgramMaintainForm)form).getProgram();
		program = new String(program.getBytes(),"GBK");
		BufferedReader br = new BufferedReader(new StringReader(program));
		
		updateProgramList(projectId,br);
		return forwardSuccessPage(request,mapping,"维护成功","ProjectMaintain.do?act=qpl");
	}

}
