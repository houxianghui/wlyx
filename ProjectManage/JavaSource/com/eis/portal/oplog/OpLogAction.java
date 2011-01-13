 
package com.eis.portal.oplog; 
 
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse; 
import org.apache.struts.action.ActionForm; 
import org.apache.struts.action.ActionForward; 
import org.apache.struts.action.ActionMapping; 
import java.util.List; 
 
import com.eis.base.*; 
import com.eis.portal.UserContext; 
import com.eis.factory.*; 
import com.eis.util.*; 
 
 
/** 
 * 说明：操作日志的控制类 
 */ 
public class OpLogAction extends BaseAction { 
 
	/** 
	 * 构造函数 
	 */ 
	public OpLogAction() { 
		super(); 
	} 
 
	/** 
	 * 执行请求处理 
	 */ 
		public ActionForward process(ActionMapping mapping,BaseForm form,HttpServletRequest request,HttpServletResponse response,UserContext user) throws Exception { 
		String act = form.getAct(); 
		
		if (act.equals("r")) { //查询明细信息 
 
			return retrieve(mapping, form, request, response, user); 

 
		} else if (act.equals("ql")) { //查询列表 
 
			return queryList(mapping, form, request, response, user); 
 
		} else 
			return null; 
	} 

	/** 
	 * 查询列表 
	 */ 
	public ActionForward queryList(ActionMapping mapping,BaseForm form,HttpServletRequest request,HttpServletResponse response,UserContext user) throws Exception { 
 
		PageObject page = new PageObject(); 
		
		String pageNo = request.getParameter("pageNO");

		String requery = request.getParameter("requery");

		if (pageNo == null
			|| (requery != null && requery.trim().equals("y"))) {
			page.setCurPage(1);	
			
		} else {
			page.setCurPage(Integer.parseInt(pageNo));			
			
		}
		
		//第一次进入，不执行数据库查询		
		if (pageNo == null && requery == null ) {		
			
			return mapping.findForward("querylist");				
			
		}

		//在此处通过page.addFilter()方法添加过滤条件 			
		page.addFilter("event_date_begin", request.getParameter("event_date_begin"));
		page.addFilter("event_date_end", request.getParameter("event_date_end"));
		page.addFilter("event_type", request.getParameter("event_type"));
		page.addFilter("op_id", request.getParameter("op_id"));
		
		page.addFilter("branch_id", request.getParameter("branch_id"));
		page.addFilter("login_id", request.getParameter("login_id"));
		page.addFilter("user_id","");
 
		//获得业务对象 
		OpLogBO bo = (OpLogBO) getBean("oplog_bo"); 
		
		String login_id = (String) page.getFilter("login_id");
		if (login_id != null && login_id.trim().length()>0){		
			String user_id = bo.getUserID(login_id.trim());

			if (user_id != null && user_id.trim().length()>0){		
				page.addFilter("user_id", user_id);
			}else{
				return forwardError(request, mapping, "不存在这个用户！");
			}
		}
		
		//调用业务对象的list（）方法,返回列表结果 
		bo.queryList(page, user); 
 
		//将结果对象写到request对象中 
		request.setAttribute("pageResult", page); 
 
		//执行页面跳转 
		return mapping.findForward("querylist"); 
	} 
 

	/** 
	 * 查询明细信息 
	 */ 
	public ActionForward retrieve(ActionMapping mapping,BaseForm form,HttpServletRequest request,HttpServletResponse response,UserContext user) throws Exception { 
 
		//获得业务对象 
		BaseBO bo = (BaseBO) getBean("oplog_bo"); 
 
		OpLogVO vo = new OpLogVO(); 
 
		//获得查询主键 
		vo.setSys_id(Long.parseLong(((OpLogForm)form).getSys_id())); 
		
 
		//调用业务对象的retrieve（）方法,查询明细信息 
		vo=(OpLogVO)bo.retrieve(vo, user); 
 
		//将结果对象写到form对象中 
		copyProperties(form, vo); 
 
		//执行页面跳转,返回到列表页面 
		return mapping.findForward("view"); 
	} 
 
 
} 

