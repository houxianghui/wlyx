package com.huateng.blue.work;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.abc.action.IbatisAction;
import com.eis.base.BaseForm;
import com.eis.portal.UserContext;
import com.eis.util.CheckUtil;

public class MainProblemAction extends IbatisAction {

	@Override
	public ActionForward process(ActionMapping mapping, BaseForm form,
			HttpServletRequest request, HttpServletResponse response,
			UserContext user) throws Exception {
		String act = request.getParameter("act");
		if("edit".equals(act)){
			return edit(mapping,request);
		}else if("update".equals(act)){
			return update(mapping,request);
		}
		return null;
	}
	public ActionForward edit(ActionMapping mapping,HttpServletRequest request)throws Exception{
		List<MainProblem> l = bo.queryForList(null);
		request.setAttribute("list", l);
		return mapping.findForward("edit");
	}
	public ActionForward update(ActionMapping mapping,HttpServletRequest request)throws Exception{
		List<MainProblem> l = new ArrayList<MainProblem>();
		Enumeration<String > e = request.getParameterNames();
		while(e.hasMoreElements()){
			String s = e.nextElement();
			if(s.startsWith("problem")){
				MainProblem m = new MainProblem();
				String content = request.getParameter(s);
				if(!CheckUtil.isEmptry(content)){
					m.setContent(content);
					l.add(m);
				}
			}
		}
		((MainProblemBO)bo).batchUpdate(l);
		return forwardSuccessPage(request, mapping, "±£´æ³É¹¦","MainProblem.do?act=edit");
	}
}
