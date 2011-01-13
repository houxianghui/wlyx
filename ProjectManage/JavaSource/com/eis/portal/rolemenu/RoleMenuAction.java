/*********************************************************
 * File: RoleMenuAction.java
 * 
 * Version 1.0
 * 
 * Date     2005-9-21
 * 
 * Author   Chen Rong
 * 
 ********************************************************/

package com.eis.portal.rolemenu;

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
import com.eis.portal.role.*;
import java.util.*;
import com.eis.cache.*;

/**
 * 说明：
 * 
 */
public class RoleMenuAction extends BaseAction {

	/**
	 * 
	 */
	public RoleMenuAction() {
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

		if (act.equals("u")) { //修改角色菜单权限
			
				String step = request.getParameter("step");
			
				if (null == step) { //初始化阶段，到角色菜单信息修改页面
				
					return editInfo(mapping, form, request, response, user);					
				
				} else 
				//已提交角色菜单修改数据，执行数据保存
				//（包括删除以前拥有权限的菜单，和添加目前拥有权限的菜单）
			
					return update(mapping, form, request, response, user);
				
		}  else if (act.equals("list")) { //返回维护角色菜单权限列表
			
				String step = request.getParameter("step");
			
				if (null == step) { //显示角色菜单权限信息列表，为可修改页面
				
					return list(mapping, form, request, response, user);
				
				} else //已提交角色菜单权限修改，执行数据保存
			
					return update(mapping, form, request, response, user);			
			

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
		

		//获得角色菜单权限业务对象
		RoleMenuBO bo = (RoleMenuBO)getBean("rolemenu_bo");

		//获取角色代码
		
		String role_id = request.getParameter("role_id");
		
		/**调用业务对象的list（）方法,返回对象列表，
		 * 对象元素顺序依菜单层次顺序,每一对象中包含：
		 * 菜单级别、菜单编码、菜单名称、复选框是否勾选、
		 * 是否显示复选框
		 * 反映所选角色在全部菜单中拥有的菜单权限的情况
		 * **/		
		
		List list=bo.list(role_id,user);
		

		//将列表结果对象写到request对象中		
		request.setAttribute("selectmenuList", list);
		
		((RoleMenuForm)form).setRole_name(bo.queryRoleName(role_id));	
		

		//执行页面跳转
		return mapping.findForward("list");
	}
	
	
	public ActionForward editInfo(
		ActionMapping mapping,
		BaseForm form,
		HttpServletRequest request,
		HttpServletResponse response,
		UserContext user)
		throws Exception {
			
		//进行权限校验
		if (!OpMap.checkRoleAuth(user.getRoleID(), "role_perm")) {

			BaseException e = new BaseException();
			e.setErrorCode("E020001");
			throw e;
		}

		//获得角色菜单权限业务对象
		RoleMenuBO bo = (RoleMenuBO)getBean("rolemenu_bo");

		//获取角色代码		
		String role_id = request.getParameter("role_id");
		
		/**调用业务对象的list（）方法,返回对象集合，
		 * 对象元素顺序依菜单层次顺序,每一对象中包含：
		 * 菜单级别、菜单编码、菜单名称、复选框是否勾选、
		 * 是否显示复选框
		 * 返回集合包含菜单层次结构信息，所选角色拥有权限的菜单的信息
		 * **/		
		
		List list=bo.list(role_id,user);		
		

		//将列表结果对象写到request对象中		
		request.setAttribute("selectmenuList", list);	
		
		((RoleMenuForm)form).setRole_name(bo.queryRoleName(role_id));	
		

		//执行页面跳转
		return mapping.findForward("edit");		
		
	}


	/**更新角色菜单权限
	 *  */
	public ActionForward update(
		ActionMapping mapping,
		BaseForm form,
		HttpServletRequest request,
		HttpServletResponse response,
		UserContext user)
		throws Exception {

		//获得角色菜单权限业务对象
		RoleMenuBO bo = (RoleMenuBO)getBean("rolemenu_bo");

		//建立角色菜单数据对象
		RoleMenuVO vo = new RoleMenuVO();
		
		//从提交表单获得角色代码role_id
		String role_id=request.getParameter("role_id");	
		
		
		/**获取表单中复选框组selectmenu中勾选项组成的数组***/		
		String selectmenu[]=request.getParameterValues("selectmenu"); 
		List list=new ArrayList();
		
		/**若至少勾选了一项菜单***/
		if (selectmenu!=null)
		{ 
			/**将所选菜单操作权限放入list集合中，更新数据*/					  
		  for (int i=0;i<selectmenu.length;i++) 
		  { 			
			list.add(selectmenu[i]);
		  }
		  
		  /**调用业务对象的update（）方法，
		   * 对角色role_id，依照勾选菜单集合更新菜单权限
		   * 注意首先删除角色操作权限表中，
		   * 不在此角色role_id菜单权限之下的操作权限
		   **/
		  bo.update(role_id,list,user); 
		  
		  SysLog.debug("菜单权限更新完毕" );			  		  
		}
		else{
			
			/**若对角色不勾选任何菜单权限，
			 * 则调用业务对象的delete()方法，
			 * 删除角色role_id的所有菜单权限,
			 * 注意首先删除角色role_id与菜单权限相关的操作权限
			 ***/			
			
			bo.delete(role_id,list,user);
			
			SysLog.debug("菜单权限删除完毕" );	
		}
		
		((RoleMenuForm)form).setRole_name(bo.queryRoleName(role_id));	

		/**传递数据更新成功结果
		 **/
		//request.getSession().setAttribute("success", "y");

		// 执行页面跳转
		return editInfo(mapping, form, request, response, user);
		
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
		BaseBO bo = (BaseBO)getBean("role_bo");
		
		RoleForm bean = (RoleForm) form;

		RoleVO vo = new RoleVO();

		vo.setRole_id(bean.getRole_id());

		//调用业务对象的retrieve（）方法,查询明细信息
		vo = (RoleVO) bo.retrieve(vo, user);

		//将结果对象写到form对象中
		copyProperties(bean,vo);

		//执行页面跳转
		return mapping.findForward("view");
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
		//获得业务对象
		BaseBO bo = (BaseBO)getBean("role_bo");

		RoleForm bean = (RoleForm) form;
		
		RoleVO vo = new RoleVO();		
		
		//进行数据传输
		copyProperties(vo,bean);	
			
		
		vo.setReg_dt(DateUtil.getDTStr());
		vo.setUser_id("0007");

		//调用业务对象的add（）方法
		
		bo.add(vo, user);

		//执行页面跳转,返回到列表页面
		bean.setRole_name(null);
		return list(mapping, form, request, response, user);
		
	}





	public ActionForward delete(
		ActionMapping mapping,
	BaseForm form,
		HttpServletRequest request,
		HttpServletResponse response,
		UserContext user)
		throws Exception {

		//获得业务对象
		BaseBO bo = (BaseBO)getBean("role_bo");		
		
		RoleForm bean = (RoleForm)form;

		RoleVO vo = new RoleVO();

		vo.setRole_id(bean.getRole_id());
		
		//调用业务对象的retrieve（）方法,查询明细信息
		//vo = (RoleVO) bo.retrieve(vo, user);
		
		if((vo.getStatus()).equals(new String("0"))){
			
			//调用业务对象的delete（）方法,执行数据删除
			bo.delete(vo, user);			

			//执行页面跳转,返回到列表页面
		
			return forwardSuccessPage(request,mapping,"数据删除成功！","Role.do?act=list");
					
		}else{
			
			return forwardSuccessPage(request,mapping,"请先废除角色！","Role.do?act=list");
			
		}		
		
	}

}
