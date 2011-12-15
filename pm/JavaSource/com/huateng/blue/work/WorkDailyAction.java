package com.huateng.blue.work;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.util.WebUtils;

import com.abc.action.IbatisAction;
import com.eis.base.BaseForm;
import com.eis.config.SysConfig;
import com.eis.exception.MessageException;
import com.eis.portal.UserContext;
import com.eis.util.CheckUtil;
import com.eis.util.DateUtil;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;

public class WorkDailyAction extends IbatisAction {

	@Override
	public ActionForward process(ActionMapping mapping, BaseForm form,
			HttpServletRequest request, HttpServletResponse response,
			UserContext user) throws Exception {
		String act = request.getParameter("act");
		if ("new".equals(act)) {
			String date = DateUtil.getDTStr();
			request.setAttribute("date", date);
			return mapping.findForward("new");
		}
		if ("add".equals(act)) {
			return add(mapping, form, request, user);
		}
		if ("edit".equals(act)) {
			return edit(mapping, form, request, user);
		}
		if ("update".equals(act)) {
			return update(mapping, form, request, user);
		}
		if ("delete".equals(act)) {
			return delete(mapping, form, request, user);
		}
		if ("view".equals(act)) {
			return view(mapping, form, request, user);
		}
		if("download".equals(act)){
			return download(request,response);
		}
		if("preview".equals(act)){
			preview(request,response);
		}
		if("initDown".equals(act)){
			request.setAttribute("date", DateUtil.getDTStr());
			return mapping.findForward("init");
		}
		return null;
	}

	private ActionForward add(ActionMapping mapping, BaseForm form,
			HttpServletRequest request, UserContext user) throws Exception{
		
		String date = request.getParameter("date");
		if(((WorkDailyBO)bo).checkRepeated(date,user)){
			return forwardError(request, mapping, "已经存在该日日报，将跳到修改页面","WorkDaily.do?act=edit&date="+date);
		}
		return makeLists(mapping, request, user);
	}
	private ActionForward update(ActionMapping mapping, BaseForm form,
			HttpServletRequest request, UserContext user) throws Exception{
		return makeLists(mapping, request, user);
	}

	private ActionForward makeLists(ActionMapping mapping,
			HttpServletRequest request, UserContext user)
			throws MessageException {
		String date = request.getParameter("date");
		Enumeration<String> e =request.getParameterNames();
		String today = DateUtil.getDTStr();
		List<WorkDaily> works = new ArrayList<WorkDaily>();
		List<DailyAchieve> achieves = new ArrayList<DailyAchieve>();
		List<DailyIssue> issues = new ArrayList<DailyIssue>();
		Pattern wPattern = Pattern.compile("work_\\d+$");
		Pattern aPattern = Pattern.compile("achieve_\\d+$");
		Pattern iPattern = Pattern.compile("issue_\\d+$");
		while(e.hasMoreElements()){
			String s = e.nextElement();
			Matcher m = wPattern.matcher(s);
			if(m.find()){
				WorkDaily wd = new WorkDaily();
				wd.setContent(request.getParameter(s));
				if(CheckUtil.isEmptry(wd.getContent())){
					continue;
				}
				wd.setWorkDate(date);
				wd.setUserId(user.getUserID());
				wd.setInputDate(today);
				works.add(wd);
				continue;
			}
			m = aPattern.matcher(s);
			if(m.find()){
				DailyAchieve da = new DailyAchieve();
				da.setAchievement(request.getParameter(s));
				if(CheckUtil.isEmptry(da.getAchievement())){
					continue;
				}
				da.setAchievePercent(request.getParameter(s+"_percent"));
				da.setUserId(user.getUserID());
				da.setWorkDate(date);
				da.setInputDate(today);
				achieves.add(da);
				continue;
			}
			m = iPattern.matcher(s);
			if(m.find()){
				DailyIssue di = new DailyIssue();
				di.setIssue(request.getParameter(s));
				if(CheckUtil.isEmptry(di.getIssue())){
					continue;
				}
				di.setIssuePercent(request.getParameter(s+"_percent"));
				di.setUserId(user.getUserID());
				di.setInputDate(today);
				di.setWorkDate(date);
				issues.add(di);
				continue;
			}
			
		}
		if(works.size()==0){
			throw new MessageException("未录入当日工作内容");
		}
		((WorkDailyBO)bo).batchUpdate(works, achieves, issues, date,user);
		return forwardSuccessPage(request, mapping, "保存成功","TASK_LIST.do?act=list");
	}

	private ActionForward edit(ActionMapping mapping, BaseForm form,
			HttpServletRequest request, UserContext user) {
		String date  = request.getParameter("date");
		((WorkDailyBO)bo).setDailyInfo(date,user, request);
		return mapping.findForward("edit");
	}


	private ActionForward delete(ActionMapping mapping, BaseForm form,
			HttpServletRequest request, UserContext user) {
		String date = request.getParameter("date");
		((WorkDailyBO)bo).batchDelete(date, user);
		return forwardSuccessPage(request, mapping, "删除成功","TASK_LIST.do?act=list");
	}

	private ActionForward view(ActionMapping mapping, BaseForm form,
			HttpServletRequest request, UserContext user) {
		String date  = request.getParameter("date");
		((WorkDailyBO)bo).setDailyInfo(date,user, request);
		return mapping.findForward("detail");
	}
	private ActionForward download(HttpServletRequest request,HttpServletResponse response)throws Exception{
		WebUtils.setWebAppRootSystemProperty(getServletContext());
		System.setProperty("resource", System.getProperty(WebUtils.DEFAULT_WEB_APP_ROOT_KEY)+"/WEB-INF/classes/resource");
		String chName = ((WorkDailyBO)bo).generateDaily(request);
		down(request, response, chName, SysConfig.getProperty("file.download"));
		
		return null;
	}
	private void preview(HttpServletRequest request,HttpServletResponse response)throws Exception{
		WebUtils.setWebAppRootSystemProperty(getServletContext());
		System.setProperty("resource", System.getProperty(WebUtils.DEFAULT_WEB_APP_ROOT_KEY)+"/WEB-INF/classes/resource");

		response.setContentType("application/pdf");
		Document document = new Document();
       
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();
        ((WorkDailyBO)bo).generateDailyDocument(request,document);
        document.close();
	}
}
