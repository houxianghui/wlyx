/*********************************************************
 * File: RoleOPAction.java
 * 
 * Version 1.0
 * 
 * Date     2005-9-24
 * 
 * Author   Chen Rong
 * 
 ********************************************************/

package com.eis.portal.roleop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


import java.util.List;
import java.util.*;

import com.eis.base.*;
import com.eis.portal.UserContext;
import com.eis.base.PageObject;
import com.eis.util.*;
import com.eis.portal.role.*;
import com.eis.portal.rolemenu.*;
import com.eis.cache.*;


/**
 * 说明：角色操作权限定义控制层实现类
 * 
 */
public class RoleOPAction extends BaseAction {

	/**
	 * 
	 */
	public RoleOPAction() {
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
		//获取操作方式
		String act = request.getParameter("act");
		SysLog.debug("act="+act);

		if (act.equals("u")) { //修改角色操作权限
				//获取操作步骤标识
				String step = request.getParameter("step");	
				
			
				if (null == step) { //初始化阶段，到角色操作权限信息修改页面
				
					return editInfo(mapping, form, request, response, user);				
				
				} else 
				//已提交角色操作权限修改数据，执行数据保存
			
					return update(mapping, form, request, response, user);
			

		} else
			return null;
	}


	
	/**查询角色的可选操作权限选项信息，
	 * 和已选操作权限选项信息，转到角色操作权限修改页面
	 * */
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

			//获得角色操作权限业务对象
			RoleOPBO bo = (RoleOPBO)getBean("roleop_bo");

			//调用业务对象的list（）方法,返回列表结果
		
			String role_id = request.getParameter("role_id");
						
		
			//获得角色待选的操作权限集合
			List listNotSelected=bo.listOPNotSelected(role_id,user);
			//获得角色已选择的操作权限集合
			List listSelected=bo.listSelected(role_id,user);			
			

			//将结果对象写到request对象中		
			request.setAttribute("listNotSelected", listNotSelected);
			request.setAttribute("listSelected", listSelected);		
			
			((RoleOPForm)form).setRole_name(bo.queryRoleName(role_id));		
		

			//执行页面跳转,到角色操作权限修改页面
			return mapping.findForward("edit");
	}


	/**根据角色的已选操作权限选项，
	 * 更新数据库信息，转到角色操作权限修改页面
	 * */
	public ActionForward update(
		ActionMapping mapping,
		BaseForm form,
		HttpServletRequest request,
		HttpServletResponse response,
		UserContext user)
		throws Exception {

		//获得角色操作权限业务对象
		RoleOPBO bo = (RoleOPBO)getBean("roleop_bo");

				
		//从提交表单获得角色代码role_id
		String role_id=request.getParameter("role_id");	
		
		//获取已选操作权限列表框中的项目，即角色新的操作权限选择
		String selectop[]=request.getParameterValues("right"); 
		
		List list=new ArrayList();		
		if (selectop!=null)
		{ 		  
		 //如果对特定角色至少选择一项操作权限，则，将所选操作权限放入 list集合中，更新数据
		  for (int i=0;i<selectop.length;i++) 
		  { 			
			list.add(selectop[i]);
		  }
		  
		  //调用业务对象的update（）方法，更新角色选择的操作权限
		  bo.update(role_id,list,user); 
		  
		  SysLog.debug("操作权限更新完毕" );	
		  		  		  
		}
		else{
			//如果对特定角色不选择任何操作权限，则调用delete()方法，删除此角色选择的所有原操作权限
			
			bo.delete(role_id,user);
			
			SysLog.debug("操作权限删除完毕" );	
		}
		
		((RoleOPForm)form).setRole_name(bo.queryRoleName(role_id));		

		//传递数据保存成功标志结果
		//request.getSession().setAttribute("success_op", "y");

		// 执行页面跳转
		return editInfo(mapping, form, request, response, user);	
	}
	



}
