/*********************************************************
 * File: RoleAction.java
 * 
 * Version 1.0
 * 
 * Date     2005-9-12
 * 
 * Author   Chen Rong
 * 
 ********************************************************/

package com.eis.portal.role;

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
import com.eis.cache.*;

/**
 * 说明：角色管理
 * 
 */
public class RoleAction extends BaseAction {

	/**
	 * 
	 */
	public RoleAction() {
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

		if (act.equals("c")) { //增加角色

			String step = request.getParameter("step");

			if (null == step) { //初始化阶段，跳转到增加角色页面
				return mapping.findForward("new");

			} else //用户已提交角色数据，执行数据保存
				return add(mapping, form, request, response, user);
		} else if (act.equals("u")) { //修改角色

			String step = request.getParameter("step");
			if (null == step) { //初始化阶段，查询角色信息并跳转到角色修改页面
				return editInfo(mapping, form, request, response, user);
			} else //用户已提交角色数据，执行数据保存
				return update(mapping, form, request, response, user);
		} else if (act.equals("r")) { //查询角色信息

			return retrieve(mapping, form, request, response, user);

		} else if (act.equals("t")) { //废除角色信息

			return terminate(mapping, form, request, response, user);

		} else if (act.equals("d")) { //删除角色

			return delete(mapping, form, request, response, user);

		} else if (act.equals("list")) { //返回维护角色列表

			return list(mapping, form, request, response, user);

		} else if (act.equals("ql")) { //查询角色列表

			return querylist(mapping, form, request, response, user);

		} else
			return null;
	}

	/**
	 * 列表
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
		page.addFilter("role_name", request.getParameter("role_name"));

		//获得业务对象
		BaseBO bo = (BaseBO) getBean("role_bo");

		//调用业务对象的list（）方法,返回列表结果
		bo.list(page, user);

		//将结果对象写到request对象中
		request.setAttribute("pageResult", page);
		request.setAttribute("checked", ((RoleForm)form).getChecked());

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
	public ActionForward querylist(
		ActionMapping mapping,
		BaseForm form,
		HttpServletRequest request,
		HttpServletResponse response,
		UserContext user)
		throws Exception {
		//获得业务对象

		//	  调用业务对象的list（）方法,返回列表结果

		//	  将结果对象写到request对象中

		//	  执行页面跳转
		return mapping.findForward("querylist");
	}

	/**
	 * 查询明细
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
		BaseBO bo = (BaseBO) getBean("role_bo");

		RoleForm bean = (RoleForm) form;

		RoleVO vo = new RoleVO();

		vo.setRole_id(bean.getRole_id());
		

		//调用业务对象的retrieve（）方法,查询明细信息
		vo = (RoleVO) bo.retrieve(vo, user);

		//将结果对象写到form对象中
		copyProperties(bean, vo);

		//执行页面跳转
		return mapping.findForward("view");
	}

	/**
	 * 增加角色
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
		if (!OpMap.checkRoleAuth(user.getRoleID(), "role_c")) {

			BaseException e = new BaseException();
			e.setErrorCode("E020001");
			throw e;
		}

		//获得业务对象
		RoleBO bo = (RoleBO) getBean("role_bo");

		RoleForm bean = (RoleForm) form;

		RoleVO vo = new RoleVO();

		//进行数据传输
		copyProperties(vo, bean);	
		
		//对于全行角色，将机构号赋为"0",对于特定机构角色，检查级别和机构级别的一致性
		if (vo.getAmsd_store() == null
			|| vo.getAmsd_store().trim().length() <= 0){
				vo.setAmsd_store("0");
		
		}			
		

		vo.setReg_dt(DateUtil.getDTStr());
		vo.setUser_id(user.getUserID());
		vo.setStatus("1");

		//调用业务对象的add（）方法

		//返回ROLE_ID的最大值
		String maxKey = bo.retrieveMax(user);

		String nextKey = null;

		if (maxKey == null  || maxKey.trim().length()<1 || maxKey.trim().equals("0")) {

			nextKey = "00000001";

		} else {

			//获取要新增角色的ROLE_ID值
			nextKey = KeyGenerator.getNextKeyStr(maxKey, 8);
			//SysLog.debug("最大role_id+1的值为："+nextKey);

		}

		vo.setRole_id(nextKey);

		bo.add(vo, user);

		//执行页面跳转,返回到列表页面
		bean.setRole_name(null);
		bean.setChecked("checked=\"checked\"");
		return list(mapping, form, request, response, user);

	}


	/**显示增加角色页面 */
	public ActionForward editInfo(
		ActionMapping mapping,
		BaseForm form,
		HttpServletRequest request,
		HttpServletResponse response,
		UserContext user)
		throws Exception {

		//	  获得业务对象
		BaseBO bo = (BaseBO) getBean("role_bo");

		RoleForm bean = (RoleForm) form;

		RoleVO vo = new RoleVO();

		vo.setRole_id(bean.getRole_id());

		//调用业务对象的retrieve（）方法,查询明细信息
		vo = (RoleVO) bo.retrieve(vo, user);

		//将结果对象写到form对象中		
		copyProperties(bean, vo);

		//	  执行页面跳转
		return mapping.findForward("edit");
	}

    /**更新角色 */
	public ActionForward update(
		ActionMapping mapping,
		BaseForm form,
		HttpServletRequest request,
		HttpServletResponse response,
		UserContext user)
		throws Exception {
			
		//进行权限校验
		if (!OpMap.checkRoleAuth(user.getRoleID(), "role_u")) {

			BaseException e = new BaseException();
			e.setErrorCode("E020001");
			throw e;
		}

		//获得业务对象
		BaseBO bo = (BaseBO) getBean("role_bo");

		RoleForm bean = (RoleForm) form;
		RoleVO vo = new RoleVO();

		//进行数据传输
		copyProperties(vo, bean);
		
		//对于全行角色，将机构号赋为"0",对于特定机构角色，检查级别和机构级别的一致性
		if (vo.getAmsd_store() == null
			|| vo.getAmsd_store().trim().length() <= 0){
				vo.setAmsd_store("0");
		}		

		//更新日期
		vo.setReg_dt(DateUtil.getDTStr());
		//更新人员
		vo.setUser_id(user.getUserID());		

		//调用业务对象的update（）方法
		bo.update(vo, user);

		//传递数据保存结果
		request.setAttribute("success", "y");

		//	  执行页面跳转
		return mapping.findForward("edit");
	}


	/**废除角色
	 *  */
	public ActionForward terminate(
		ActionMapping mapping,
		BaseForm form,
		HttpServletRequest request,
		HttpServletResponse response,
		UserContext user)
		throws Exception {
			
		//进行权限校验
		if (!OpMap.checkRoleAuth(user.getRoleID(), "role_terminate")) {

			BaseException e = new BaseException();
			e.setErrorCode("E020001");
			throw e;
		}

		//获得角色业务对象
		RoleBO bo = (RoleBO) getBean("role_bo");

		RoleForm bean = (RoleForm) form;

		RoleVO vo = new RoleVO();

		vo.setRole_id(bean.getRole_id());

		//调用角色业务对象的retrieve（）方法,获取角色明细信息的vo
		vo = (RoleVO) bo.retrieve(vo, user);

		//查看角色是否已废除
		if ((vo.getStatus()).equals("0")) {
			//若角色已废除，提示，返回列表页面
			return forwardSuccessPage(
				request,
				mapping,
				"角色已废除！",
				"Role.do?act=list");

		} else {
			//若角色未废除，执行废除操作
			//设置更新时间为当日
			vo.setReg_dt(DateUtil.getDTStr());	
			SysLog.debug("角色作废日期为："+vo.getReg_dt());		
			//设置更新人员
			vo.setUser_id(user.getUserID());	

			//调用角色业务对象的terminate（）方法
			bo.terminate(vo, user);

			//传递数据保存结果
			//request.getSession().setAttribute("success", "y");

			//执行页面跳转		
			return forwardSuccessPage(
				request,
				mapping,
				"角色废除成功！",
				"Role.do?act=list");

		}

	}
	
	
	/**删除角色 */
	public ActionForward delete(
		ActionMapping mapping,
		BaseForm form,
		HttpServletRequest request,
		HttpServletResponse response,
		UserContext user)
		throws Exception {
			
		//进行权限校验
		if (!OpMap.checkRoleAuth(user.getRoleID(), "role_d")) {

			BaseException e = new BaseException();
			e.setErrorCode("E020001");
			throw e;
		}

		//获得业务对象
		BaseBO bo = (BaseBO) getBean("role_bo");

		RoleForm bean = (RoleForm) form;

		RoleVO vo = new RoleVO();

		vo.setRole_id(bean.getRole_id());
		SysLog.debug("表单中的role_id：" + vo.getRole_id());

		//查询所选角色的状态
		vo = (RoleVO) bo.retrieve(vo, user);
		SysLog.debug("role_id的状态：" + vo.getStatus());

		if ((vo.getStatus()).equals(new String("0"))) {

			SysLog.debug("开始调用删除角色:" + vo.getRole_id());

			//调用业务对象的delete（）方法,执行数据删除
			bo.delete(vo, user);

			//执行页面跳转,返回到列表页面

			return forwardSuccessPage(
				request,
				mapping,
				"数据删除成功！",
				"Role.do?act=list");

		} else {

			return forwardSuccessPage(
				request,
				mapping,
				"请先废除角色！",
				"Role.do?act=list");

		}

	}

}
