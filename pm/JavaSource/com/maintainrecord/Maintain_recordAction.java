 
package com.maintainrecord; 
 
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
 * 说明：技术支持服务记录的控制类 
 */ 
public class Maintain_recordAction extends BaseAction { 
 
	/** 
	 * 构造函数 
	 */ 
	public Maintain_recordAction() { 
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
				 
				return init(mapping,form); 
 
			} else //用户已提交新增数据，执行数据保存 
 
				return add(form,user); 
 
		} else if (act.equals("u")) { //修改 
 
			String step = request.getParameter("step"); 
 
			if (null == step) { //初始化阶段，查询明细信息并跳转到修改页面 
 
				return editInfo(mapping, form, user); 
 
			} else //用户已提交修改后的数据，执行数据保存 
 
				return update( form, request, user); 
 
		} else if (act.equals("r")) { //查询明细信息 
 
			return retrieve(mapping, form, user); 
 
		} else if (act.equals("d")) { //删除数据 
 
			return delete(mapping, form, request, user); 
 
		} else if (act.equals("list")) { //返回维护列表 
 
			return list(mapping, form, request); 
 
		} else if (act.equals("ql")) { //查询列表 
 
			return queryList(mapping, form, request); 
 
		} else if(act.equals("p")){	//process
			return response(mapping,form,user);
		} else if(act.equals("up")){	//processUpdate
			return responseUpdate(form,user);
		}
			return null; 
	} 
 	public ActionForward init(ActionMapping mapping,BaseForm form){
 		((Maintain_recordForm)form).setQus_date(DateUtil.getDTStr());
 		
		return mapping.findForward("new");
 	}
	/** 
	 * 查询维护列表 
	 */ 
	public ActionForward list(ActionMapping mapping,BaseForm form,HttpServletRequest request) throws Exception { 
 
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
		BaseBO bo = (BaseBO) getBean("maintain_record_bo"); 
 		
		//调用业务对象的list（）方法,返回列表结果 
		((Maintain_recordBO)bo).list(page,form); 
 
		//将结果对象写到request对象中 
		request.setAttribute("pageResult", page); 
 
		//执行页面跳转 
		return mapping.findForward("list"); 
	} 
 
 
	/** 
	 * 查询列表 
	 */ 
	public ActionForward queryList(ActionMapping mapping,BaseForm form,HttpServletRequest request) throws Exception { 
 
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
		BaseBO bo = (BaseBO) getBean("maintain_record_bo"); 
 
		//调用业务对象的queryList（）方法,返回列表结果 
		((Maintain_recordBO)bo).queryList(page, form); 
 
		//将结果对象写到request对象中 
		request.setAttribute("pageResult", page); 
 
		//执行页面跳转 
		return mapping.findForward("querylist"); 
	} 
 
 
	/** 
	 * 增加数据 
	 */ 
	public ActionForward add(BaseForm form,UserContext user) throws Exception { 
 
		if (!OpMap.checkRoleAuth(user.getRoleID(), "maintain_record_c")) { 
			BaseException e = new BaseException(); 
			e.setErrorCode("E020001"); 
			throw e; 
		} 
 
		BaseBO bo = (BaseBO) getBean("maintain_record_bo"); 
 
		Maintain_recordVO vo = new Maintain_recordVO(); 
 
		copyProperties(vo, form); 
 		
		vo.initAddVO(user);
 		vo.setSeq_no(StringUtil.getFixLengthCharWithPreZero(String.valueOf(KeyGenerator.getNextKey("maintain_record")),10));
		
		bo.add(vo, user); 
 
		return new ActionRedirect("Maintain_record.do?act=list"); 
	}


 
 
	/** 
	 * 查询维护信息明细 
	 */ 
	public ActionForward editInfo(ActionMapping mapping,BaseForm form,UserContext user) throws Exception { 
 
		//获得业务对象 
		BaseBO bo = (BaseBO) getBean("maintain_record_bo"); 
 
		Maintain_recordVO vo = new Maintain_recordVO(); 
 
		//获得查询主键 
		vo.setSeq_no(((Maintain_recordForm)form).getSeq_no()); 
 
 
		//调用业务对象的retrieve（）方法,查询明细信息 
		vo=(Maintain_recordVO)bo.retrieve(vo, user); 
 
 
 
		//将结果对象写到form对象中 
		copyProperties(form, vo); 
 
		//执行页面跳转,返回到列表页面 
		return mapping.findForward("edit"); 
	} 
	/**
     * @author houxh 2008-1-9
     * 答复页面
     * 
     * @param mapping
     * @param form
     * @param user
     * @return
     * @throws Exception 
     */
    public ActionForward response(ActionMapping mapping,BaseForm form,UserContext user) throws Exception { 
 
		BaseBO bo = (BaseBO) getBean("maintain_record_bo"); 
		Maintain_recordVO vo = new Maintain_recordVO(); 
		
		vo.setSeq_no(((Maintain_recordForm)form).getSeq_no()); 
		vo=(Maintain_recordVO)bo.retrieve(vo, user); 
		copyProperties(form, vo); 
		
		return mapping.findForward("resp"); 
	}  
 
	/** 
	 * 修改数据 
	 */ 
	public ActionForward update(BaseForm form,HttpServletRequest request,UserContext user) throws Exception { 
 
		//进行权限校验 
		if (!OpMap.checkRoleAuth(user.getRoleID(), "maintain_record_p")) { 
			BaseException e = new BaseException(); 
			e.setErrorCode("E020001"); 
			throw e; 
		} 
 
		//获得业务对象 
		BaseBO bo = (BaseBO) getBean("maintain_record_bo"); 
 
		Maintain_recordVO vo = new Maintain_recordVO(); 
 
		//在此处进行其它字段的赋值，例如：form.setReg_dt(DateUtil.getDTStr()); 
 
		//进行数据传输 
		copyProperties(vo, form); 
 
		//调用业务对象的update（）方法,返回列表结果 
		bo.update(vo, user); 
 
		//传递数据保存结果 
		request.setAttribute("success", "y"); 
 
		//执行页面跳转 
		return new ActionRedirect("Maintain_record.do?act=list");
	} 
	/**
     * @author houxh 2008-1-9
     * 答复更新操作
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @param user
     * @return
     * @throws Exception 
     */
    public ActionForward responseUpdate(BaseForm form,UserContext user) throws Exception { 
 
		//进行权限校验 
		if (!OpMap.checkRoleAuth(user.getRoleID(), "maintain_record_c")) { 
			BaseException e = new BaseException(); 
			e.setErrorCode("E020001"); 
			throw e; 
		} 
		BaseBO bo = (BaseBO) getBean("maintain_record_bo"); 
 
		Maintain_recordVO vo = new Maintain_recordVO(); 
 
		copyProperties(vo, form); 
 		
 		vo.setRes_time(DateUtil.getTimeStr());
 		vo.setRes_user(user.getUserID());
 		
		bo.update(vo, user); 
 
		return new ActionRedirect("Maintain_record.do?act=list");
	}  
 
	/** 
	 * 删除数据 
	 */ 
	public ActionForward delete(ActionMapping mapping,BaseForm form,HttpServletRequest request,UserContext user) throws Exception { 
 
		//进行权限校验 
		if (!OpMap.checkRoleAuth(user.getRoleID(), "maintain_record_c")) { 
			BaseException e = new BaseException(); 
			e.setErrorCode("E020001"); 
			throw e; 
		} 
 
		//获得业务对象 
		BaseBO bo = (BaseBO) getBean("maintain_record_bo"); 
 
		Maintain_recordVO vo = new Maintain_recordVO(); 
 
		//获得删除纪录主键 
		vo.setSeq_no(((Maintain_recordForm)form).getSeq_no()); 
 
 
		//调用业务对象的delete（）方法,执行数据删除 
		bo.delete(vo, user); 
 
		//执行页面跳转 
		return forwardSuccessPage(request,mapping,"数据删除成功！","Maintain_record.do?act=list"); 
	} 
 
 
	/** 
	 * 查询明细信息 
	 */ 
	public ActionForward retrieve(ActionMapping mapping,BaseForm form,UserContext user) throws Exception { 
 
		//获得业务对象 
		BaseBO bo = (BaseBO) getBean("maintain_record_bo"); 
 
		Maintain_recordVO vo = new Maintain_recordVO(); 
 
		//获得查询主键 
		vo.setSeq_no(((Maintain_recordForm)form).getSeq_no()); 
 
 
		//调用业务对象的retrieve（）方法,查询明细信息 
		vo=(Maintain_recordVO)bo.retrieve(vo, user); 
 
 
 
		//将结果对象写到form对象中 
		copyProperties(form, vo); 
 
		//执行页面跳转,返回到列表页面 
		return mapping.findForward("view"); 
	} 
 
 
} 

