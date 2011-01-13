 
package com.work; 
 
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse; 
import org.apache.struts.action.*; 
 
import com.eis.base.*; 
import com.eis.key.KeyGenerator;
import com.eis.portal.UserContext; 
import com.eis.util.DateUtil;
import com.eis.util.StringUtil;
import com.eis.cache.*;
 
 
/** 
 * 说明：工作列表的控制类 
 */ 
public class TASK_LISTAction extends BaseAction { 
 
	/** 
	 * 构造函数 
	 */ 
	public TASK_LISTAction() { 
		super(); 
	} 
 
	/** 
	 * 执行请求处理 
	 */ 
		public ActionForward process(ActionMapping mapping,BaseForm form,HttpServletRequest request,HttpServletResponse response,UserContext user) throws Exception { 
		String act = form.getAct(); 
		if (act.equals("c")) { //增加 
 
			String step = request.getParameter("step"); 
 
			if (null == step) { //初始化阶段，跳转到增加页面 
 
				return init(mapping,form,request); 
 
			} else //用户已提交新增数据，执行数据保存 
 
				return add( form, user); 
 
		}
		if (act.equals("u")) { //修改 
 
			String step = request.getParameter("step"); 
 
			if (null == step) { //初始化阶段，查询明细信息并跳转到修改页面 
 
				return editInfo(mapping, form, user); 
 
			} else //用户已提交修改后的数据，执行数据保存 
 
				return update(form, request, user); 
 
		} 
		if (act.equals("r")) { //查询明细信息 
 
			return retrieve(mapping, form, user); 
 
		} 
		if (act.equals("d")) { //删除数据 
 
			return delete(mapping, form, request, user); 
 
		}
		if (act.equals("list")) { //返回维护列表 
 
			return list(mapping, request, user); 
 
		}
		if (act.equals("ql")) { //查询列表 
 
			return queryList(mapping, form, request, user); 
 
		} 
		if(act.equals("qa")){
			return queryAll(mapping,form,request,user);
		}
			
		return null;
	} 
 	public ActionForward init(ActionMapping mapping,BaseForm form,HttpServletRequest request)throws Exception{
 		
		TASK_LISTForm tform = (TASK_LISTForm)form;
		tform.setTask_date(DateUtil.getDTStr());
		tform.setTask_step(request.getParameter("curr_step"));
		tform.setProject_no(request.getParameter("project_no"));
		String s = request.getParameter("id");
		if(s == null || s.trim().length() == 0){
			tform.setId(0);
		}else{
			tform.setId(Integer.parseInt(s));
		}
		
		return mapping.findForward("new");
 	}
	/** 
	 * 查询维护列表 
	 */ 
	public ActionForward list(ActionMapping mapping,HttpServletRequest request,UserContext user) throws Exception { 
 
		PageObject page = new PageObject(); 
		String pageNo = request.getParameter("pageNO"); 
 
		String requery = request.getParameter("requery"); 
 
		if (pageNo == null || (requery != null && requery.trim().equals("y"))) { 
			page.setCurPage(1); 
		} else { 
			page.setCurPage(Integer.parseInt(pageNo)); 
		} 
 
		//在此处通过page.addFilter()方法添加过滤条件 
 
 
		//获得业务对象 
		BaseBO bo = (BaseBO) getBean("task_list_bo"); 
 
		//调用业务对象的list（）方法,返回列表结果 
		bo.list(page, user); 
 
		//将结果对象写到request对象中 
		request.setAttribute("pageResult", page); 
 
		//执行页面跳转 
		return mapping.findForward("list"); 
	} 
 
 
	/** 
	 * 查询列表 
	 */ 
	public ActionForward queryList(ActionMapping mapping,BaseForm form,HttpServletRequest request,UserContext user) throws Exception { 
 
		PageObject page = new PageObject(); 
		String pageNo = request.getParameter("pageNO"); 
 
		String requery = request.getParameter("requery"); 
 
		if (pageNo == null || (requery != null && requery.trim().equals("y"))) { 
			page.setCurPage(1); 
		} else { 
			page.setCurPage(Integer.parseInt(pageNo)); 
		} 
 
		//在此处通过page.addFilter()方法添加过滤条件 
 
 
		//获得业务对象 
		BaseBO bo = (BaseBO) getBean("task_list_bo"); 
 
		//调用业务对象的queryList（）方法,返回列表结果 
		((TASK_LISTBO)bo).queryList(form,page, user); 
 
		//将结果对象写到request对象中 
		request.setAttribute("pageResult", page); 
 
		//执行页面跳转 
		return mapping.findForward("querylist"); 
	} 
 
	public ActionForward queryAll(ActionMapping mapping,BaseForm form,HttpServletRequest request,UserContext user) throws Exception { 
 
		PageObject page = new PageObject(); 
		setPageNo(request, page);
 
		BaseBO bo = (BaseBO) getBean("task_list_bo"); 
 
		((TASK_LISTBO)bo).queryListAll(form,page); 
 
		request.setAttribute("pageResult", page); 
 
		return mapping.findForward("qa"); 
	}

    public void setPageNo(HttpServletRequest request, PageObject page) {
        String pageNo = request.getParameter("pageNO"); 
        
        String requery = request.getParameter("requery"); 
        
        if (pageNo == null || (requery != null && requery.trim().equals("y"))) { 
        	page.setCurPage(1); 
        } else { 
        	page.setCurPage(Integer.parseInt(pageNo)); 
        } 
    } 
	/** 
	 * 增加数据 
	 */ 
	public ActionForward add(BaseForm form,UserContext user) throws Exception { 
 
		//进行权限校验 
		if (!OpMap.checkRoleAuth(user.getRoleID(), "tASK_LIST_c")) { 
			BaseException e = new BaseException(); 
			e.setErrorCode("E020001"); 
			throw e; 
		} 
 
		//获得业务对象 
		BaseBO bo = (BaseBO) getBean("task_list_bo"); 
 
		TASK_LISTVO vo = new TASK_LISTVO(); 
 		
		//进行数据传输 
		copyProperties(vo, form); 
		
		vo.setTask_no(StringUtil.getFixLengthCharWithPreZero(String.valueOf(KeyGenerator.getNextKey("TASK_LIST")),20));
		vo.setTask_user(user.getUserID());
		vo.setUpdate_date(DateUtil.getDTStr());
		((TASK_LISTBO)bo).checkTaskStatus(vo);
		//在此处进行其它字段的赋值，例如：vo.setReg_dt(DateUtil.getDTStr()); 
		//调用业务对象的add（）方法,返回列表结果 
		bo.add(vo, user); 
 
		//执行页面跳转,返回到列表页面 
		return new ActionRedirect("TASK_LIST.do?act=list"); 
	} 
 
 
	/** 
	 * 查询维护信息明细 
	 */ 
	public ActionForward editInfo(ActionMapping mapping,BaseForm form,UserContext user) throws Exception { 
 
		//获得业务对象 
		BaseBO bo = (BaseBO) getBean("task_list_bo"); 
 
		TASK_LISTVO vo = new TASK_LISTVO(); 
 
		//获得查询主键 
		vo.setTask_no(((TASK_LISTForm)form).getTask_no()); 
 
 
		//调用业务对象的retrieve（）方法,查询明细信息 
		vo=(TASK_LISTVO)bo.retrieve(vo, user); 
 
 
 
		//将结果对象写到form对象中 
		copyProperties(form, vo); 
 
		//执行页面跳转,返回到列表页面 
		return mapping.findForward("edit"); 
	} 
 
 
	/** 
	 * 修改数据 
	 */ 
	public ActionForward update(BaseForm form,HttpServletRequest request,UserContext user) throws Exception { 
 
		//进行权限校验 
		if (!OpMap.checkRoleAuth(user.getRoleID(), "tASK_LIST_c")) { 
			BaseException e = new BaseException(); 
			e.setErrorCode("E020001"); 
			throw e; 
		} 
 
		//获得业务对象 
		BaseBO bo = (BaseBO) getBean("task_list_bo"); 
 
		TASK_LISTVO vo = new TASK_LISTVO(); 
 
		//在此处进行其它字段的赋值，例如：form.setReg_dt(DateUtil.getDTStr()); 
 
		//进行数据传输 
		copyProperties(vo, form); 
 		vo.setUpdate_date(DateUtil.getDTStr());
		//调用业务对象的update（）方法,返回列表结果 
		bo.update(vo, user); 
 
		//传递数据保存结果 
		request.setAttribute("success", "y"); 
 
		//执行页面跳转 
		return new ActionRedirect("TASK_LIST.do?act=list");  
	} 
 
 
	/** 
	 * 删除数据 
	 */ 
	public ActionForward delete(ActionMapping mapping,BaseForm form,HttpServletRequest request,UserContext user) throws Exception { 
 
		//进行权限校验 
		if (!OpMap.checkRoleAuth(user.getRoleID(), "tASK_LIST_c")) { 
			BaseException e = new BaseException(); 
			e.setErrorCode("E020001"); 
			throw e; 
		} 
 
		//获得业务对象 
		BaseBO bo = (BaseBO) getBean("task_list_bo"); 
 
		TASK_LISTVO vo = new TASK_LISTVO(); 
 
		//获得删除纪录主键 
		vo.setTask_no(((TASK_LISTForm)form).getTask_no()); 
 
 
		//调用业务对象的delete（）方法,执行数据删除 
		bo.delete(vo, user); 
 
		//执行页面跳转 
		return forwardSuccessPage(request,mapping,"数据删除成功！","TASK_LIST.do?act=list"); 
	} 
 
 
	/** 
	 * 查询明细信息 
	 */ 
	public ActionForward retrieve(ActionMapping mapping,BaseForm form,UserContext user) throws Exception { 
 
		//获得业务对象 
		BaseBO bo = (BaseBO) getBean("task_list_bo"); 
 
		TASK_LISTVO vo = new TASK_LISTVO(); 
 
		//获得查询主键 
		vo.setTask_no(((TASK_LISTForm)form).getTask_no()); 
 
 
		//调用业务对象的retrieve（）方法,查询明细信息 
		vo=(TASK_LISTVO)bo.retrieve(vo, user); 
 
 
 
		//将结果对象写到form对象中 
		copyProperties(form, vo); 
 
		//执行页面跳转,返回到列表页面 
		return mapping.findForward("view"); 
	} 
 
 
} 

