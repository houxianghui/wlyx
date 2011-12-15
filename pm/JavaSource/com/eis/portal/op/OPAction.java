/*********************************************************
 * File: OPAction.java
 * 
 * Version 1.0
 * 
 * Date     2005-9-20
 * 
 * Author   陈 蓉
 * 
 ********************************************************/

package com.eis.portal.op;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import java.util.List;

import com.eis.base.*;
import com.eis.portal.UserContext;
import com.eis.base.PageObject;
import com.eis.util.*;
import com.eis.key.*;
import com.eis.portal.menu.*;
import com.eis.cache.*;

/**
 * 说明：
 * 
 */
public class OPAction extends BaseAction {

	/**
	 * 
	 */
	public OPAction() {
		super();

	}

	/* （非 Javadoc）
	 * @see com.eis.base.BaseAction#process(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, com.eis.portal.UserContext)
	 */
	public ActionForward process(
		ActionMapping mapping,
		BaseForm form,
		HttpServletRequest request,
		HttpServletResponse response,
		UserContext user)
		throws Exception {

		String act = request.getParameter("act");

		if (act.equals("c")) { //增加操作权限定义

			String step = request.getParameter("step");

			if (null == step) { //初始化阶段，跳转到增加操作权限定义页面

				return beforeAdd(mapping, form, request, response, user);

			} else //用户已提交数据，执行数据保存
				return add(mapping, form, request, response, user);
		} else if (act.equals("u")) { //修改操作权限定义

			String step = request.getParameter("step");

			if (null == step) { //初始化阶段，查询操作权限信息并跳转到操作权限定义修改页面

				return editInfo(mapping, form, request, response, user);

			} else //用户已提交操作权限定义数据，执行数据保存

				return update(mapping, form, request, response, user);

		} else if (act.equals("r")) { //查询操作权限定义信息			

			return retrieve(mapping, form, request, response, user);

		} else if (act.equals("lr")) { //查询操作权限定义信息

			//String op_code = request.getParameter("op_code");							

			return null;
			//retrieve(op_code, mapping, form, request, response, user);

		} else if (act.equals("d")) { //删除操作权限定义

			return delete(mapping, form, request, response, user);

		} else if (act.equals("list")) { //返回维护操作权限定义列表

			return list(mapping, form, request, response, user);

		} else if (act.equals("ml")) { //返回菜单列表

			return menulist(mapping, form, request, response, user);

		} else if (act.equals("ql")) { //查询操作权限定义列表

			return querylist(mapping, form, request, response, user);

		} else if (act.equals("blk")) { //到空白提示页

			return mapping.findForward("blank");

		} else if (act.equals("bfl")) { //到首级菜单页，库中无此项记录

			return mapping.findForward("beforeFirstLevel");

		} else
			return null;
	}

	/**
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public ActionForward list(
		ActionMapping mapping,
		BaseForm form,
		HttpServletRequest request,
		HttpServletResponse response,
		UserContext user)
		throws Exception {

		PageObject page = new PageObject();
		String pageNo = request.getParameter("pageNO");

		String requery = request.getParameter("requery");

		if (pageNo == null
			|| (requery != null && requery.trim().equals("y"))) {
			page.setCurPage(1);
		} else {
			page.setCurPage(Integer.parseInt(pageNo));
		}
		page.addFilter("menu_id", request.getParameter("menu_id"));
		page.addFilter("caption", request.getParameter("caption"));

		//获得业务对象
		BaseBO bo = (BaseBO) getBean("op_bo");

		//调用业务对象的list（）方法,返回列表结果
		bo.list(page, user);

		//将结果对象写到request对象中
		request.setAttribute("pageResult", page);

		//执行页面跳转
		return mapping.findForward("list");
	}

	/**
		 * 
		 * @param mapping
		 * @param form
		 * @param request
		 * @param response
		 * @param user
		 * @return
		 * @throws Exception
		 */
	public ActionForward menulist(
		ActionMapping mapping,
		BaseForm form,
		HttpServletRequest request,
		HttpServletResponse response,
		UserContext user)
		throws Exception {

		//获得业务对象
		BaseBO bo = (BaseBO) getBean("menu_bo");

		//调用业务对象的list（）方法,返回列表结果
		List list = bo.list(user);

		//将结果对象写到request对象中		
		request.setAttribute("opmenuList", list);

		//执行页面跳转
		return mapping.findForward("menulist");
	}

	/**
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public ActionForward querylist(
		ActionMapping mapping,
		BaseForm form,
		HttpServletRequest request,
		HttpServletResponse response,
		UserContext user)
		throws Exception {

		//	  执行页面跳转
		return mapping.findForward("querylist");
	}

	/**
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public ActionForward retrieve(
		ActionMapping mapping,
		BaseForm form,
		HttpServletRequest request,
		HttpServletResponse response,
		UserContext user)
		throws Exception {

		//获得业务对象
		BaseBO bo = (BaseBO) getBean("op_bo");

		OPForm bean = (OPForm) form;

		OPVO vo = new OPVO();

		vo.setOp_code(bean.getOp_code());

		//调用业务对象的retrieve（）方法,查询明细信息
		vo = (OPVO) bo.retrieve(vo, user);

		//将结果对象写到form对象中
		copyProperties(bean, vo);

		//执行页面跳转
		return mapping.findForward("view");
	}

	/**
		 * 
		 * @param menu_id 
		 * @param mapping
		 * @param form
		 * @param request
		 * @param response
		 * @param user
		 * @return
		 * @throws Exception
		 */
	public ActionForward retrieve(
		String op_code,
		ActionMapping mapping,
		BaseForm form,
		HttpServletRequest request,
		HttpServletResponse response,
		UserContext user)
		throws Exception {

		String whereclause = " where OP_CODE='" + op_code + "'";

		//获得业务对象
		BaseBO bo = (BaseBO) getBean("op_bo");

		OPForm bean = (OPForm) form;

		BaseVO vo = new OPVO();

		//调用业务对象的retrieve（）方法,查询明细信息
		vo = bo.retrieve(whereclause, user);

		//将结果对象写到form对象中
		copyProperties(bean, vo);

		//执行页面跳转
		return mapping.findForward("view");
	}

	//增加操作权限，未提交之前
	public ActionForward beforeAdd(
		ActionMapping mapping,
		BaseForm form,
		HttpServletRequest request,
		HttpServletResponse response,
		UserContext user)
		throws Exception {

		return mapping.findForward("new");
	}

	/**
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public ActionForward add(
		ActionMapping mapping,
		BaseForm form,
		HttpServletRequest request,
		HttpServletResponse response,
		UserContext user)
		throws Exception {

		//进行权限校验
		if (!OpMap.checkRoleAuth(user.getRoleID(), "op_c")) {

			BaseException e = new BaseException();
			e.setErrorCode("E020001");
			throw e;
		}

		//获得业务对象
		OPBO bo = (OPBO) getBean("op_bo");

		OPForm bean = (OPForm) form;

		OPVO vo = new OPVO();

		//进行数据传输
		copyProperties(vo, bean);

		//调用业务对象的add（）方法

		bo.add(vo, user);

		//执行页面跳转,返回到列表页面
		//return mapping.findForward("list");
		return list(mapping, form, request, response, user);

	}

	public ActionForward editInfo(
		ActionMapping mapping,
		BaseForm form,
		HttpServletRequest request,
		HttpServletResponse response,
		UserContext user)
		throws Exception {

		//	  获得业务对象
		BaseBO bo = (BaseBO) getBean("op_bo");

		OPForm bean = (OPForm) form;

		OPVO vo = new OPVO();

		vo.setOp_code(bean.getOp_code());

		//调用业务对象的retrieve（）方法,查询明细信息
		vo = (OPVO) bo.retrieve(vo, user);

		//将结果对象写到form对象中		
		copyProperties(bean, vo);

		//	  执行页面跳转
		return mapping.findForward("edit");
	}

	public ActionForward update(
		ActionMapping mapping,
		BaseForm form,
		HttpServletRequest request,
		HttpServletResponse response,
		UserContext user)
		throws Exception {

		//进行权限校验
		if (!OpMap.checkRoleAuth(user.getRoleID(), "op_u")) {

			BaseException e = new BaseException();
			e.setErrorCode("E020001");
			throw e;
		}

		//获得业务对象
		BaseBO bo = (BaseBO) getBean("op_bo");

		OPForm bean = (OPForm) form;
		OPVO vo = new OPVO();

		//进行数据传输
		copyProperties(vo, bean);

		//调用业务对象的update（）方法
		bo.update(vo, user);

		//传递数据保存结果
		request.setAttribute("success", "y");

		//	  执行页面跳转
		return mapping.findForward("edit");
	}

	public ActionForward delete(
		ActionMapping mapping,
		BaseForm form,
		HttpServletRequest request,
		HttpServletResponse response,
		UserContext user)
		throws Exception {

		//进行权限校验
		if (!OpMap.checkRoleAuth(user.getRoleID(), "op_d")) {

			BaseException e = new BaseException();
			e.setErrorCode("E020001");
			throw e;
		}

		//获得业务对象
		BaseBO bo = (BaseBO) getBean("op_bo");

		OPForm bean = (OPForm) form;

		OPVO vo = new OPVO();

		vo.setOp_code(bean.getOp_code());

		//调用业务对象的delete（）方法,执行数据删除
		bo.delete(vo, user);

		//执行页面跳转,返回到列表页面
		String link =
			"OP.do?act=list&menu_id="
				+ bean.getMenu_id()
				+ "&menu_name="
				+ bean.getMenu_name();

		return forwardSuccessPage(request, mapping, "数据删除成功！", link);

	}

}
