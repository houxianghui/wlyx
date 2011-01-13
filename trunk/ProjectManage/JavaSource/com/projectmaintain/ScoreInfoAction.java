/*
 * @# ScoreInfoAction.java 2009-5-6 houxh
 *
 * Copyright  (c)  2003 	Huateng. All Right Reserv
 */
 
package com.projectmaintain;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.eis.base.BaseForm;
import com.eis.base.IbatisBaseAction;
import com.eis.exception.MessageException;
import com.eis.portal.UserContext;


public class ScoreInfoAction extends IbatisBaseAction {

	/* 
	 * @see com.eis.base.BaseAction#process(org.apache.struts.action.ActionMapping, com.eis.base.BaseForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, com.eis.portal.UserContext)
	 */
	public ActionForward process(ActionMapping mapping,BaseForm form,HttpServletRequest request,HttpServletResponse response,UserContext user) throws Exception {
			
		String act = form.getAct();
		if("init".equals(act)){
			return initScore(request,mapping,form);
		}
		if("save".equals(act)){
			return save(request,mapping,user);
		}
		if("calc".equals(act)){
			calc(response,request);
		}
		return null;
	}
	
	private void calc(HttpServletResponse response,HttpServletRequest request)throws Exception{
		String projectId = request.getParameter("projectId");
		String userId = request.getParameter("userId");
		String status = request.getParameter("status");
		ScoreInfoVO vo = new ScoreInfoVO();
		vo.setProjectId(projectId);
		vo.setUserId(userId);
		vo.setStatus(status);
		String s=((ScoreInfoBO)bo).calc(vo);
		if(s == null || Double.parseDouble(s)==0){
			s = "请先保存评分结果再进行试算";
		}
		response.setContentType("text/xml");
		response.setLocale(Locale.CHINESE);
		response.getWriter().print(s);
	}
	
	public ActionForward initScore(HttpServletRequest request,ActionMapping mapping,BaseForm form)throws Exception{
		ScoreInfoForm sForm = (ScoreInfoForm)form;
		sForm.setProjectId(request.getParameter("projectId"));
		sForm.setStatus(request.getParameter("status"));
		sForm.setUserId(request.getParameter("userId"));
		
		request.setAttribute("table",((ScoreInfoBO)bo).createTableElements(bo.queryForList(sForm)));
		
		return mapping.findForward("init");
	}
	public ActionForward save(HttpServletRequest request,ActionMapping mapping,UserContext user)throws Exception{
		HashMap map = new HashMap();
		Enumeration e = request.getParameterNames();		
		while(e.hasMoreElements()){
			processInfo(request,(String)e.nextElement(),map,user);			
		}
		bo.transOperation(map.values().toArray());
		return forwardSuccessPage(request,mapping,"保存成功");
	}
	private void processInfo(HttpServletRequest request,String s,HashMap map,UserContext user)throws Exception{
		String checkNo = "";
		int level = s.indexOf("level");
		int score = s.indexOf("score");
		String projectId = request.getParameter("projectId");
		String userId = request.getParameter("userId");
		String status = request.getParameter("status");
		
		if(level != -1 || score != -1){
			checkNo = s.substring(5);
		}else{
			return;
		}
		ScoreInfoVO vo = null;
		if(map.get(checkNo) == null){
			vo = new ScoreInfoVO();
			vo.setProjectId(projectId);
			vo.setUserId(userId);
			vo.setStatus(status);
			vo.setCheckNo(checkNo);	
			vo.setCheckUser(user.getUserID());		
		}else{
			vo = (ScoreInfoVO)map.get(checkNo);
		}
		if(level != -1){
			String grade = request.getParameter(s);
			rejectEmpty(grade,checkNo);
			vo.setGrade(grade);
		}
		if(score != -1){
			String sc = request.getParameter(s);
			rejectEmpty(sc,checkNo);
			vo.setScore(Integer.parseInt(sc));
		}
		map.put(checkNo,vo);
	}
	private void rejectEmpty(String s,String checkNo)throws MessageException{
		if(s == null || s.trim().length() == 0){
			throw new MessageException("评分项"+checkNo+"没有填写分数");
		}
	}
}
