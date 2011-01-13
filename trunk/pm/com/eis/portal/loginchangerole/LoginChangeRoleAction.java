/*********************************************************
 * File: LoginChangeRoleAction.java
 * 
 * Version 1.0
 * 
 * Date     2005-10-19
 * 
 * Author   陈 蓉
 * 
 ********************************************************/

package com.eis.portal.loginchangerole;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


import com.eis.base.*;
import com.eis.cache.*;
import com.eis.portal.UserContext;

/**
 * 说明：用户更换登录角色
 * 
 */
public class LoginChangeRoleAction extends BaseAction {

	/**
	 * 
	 */
	public LoginChangeRoleAction() {
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
		//检查提交表单中的操作方式act值
		String act = request.getParameter("act");

		if (act.equals("list")) {  //更换角色之前	
			//列出用户已选的角色列表
			return list(mapping, form, request, response, user);					
				
		} else if (act.equals("changerole")) { 
			//执行以新的主角色登录
			return changerole(mapping, form, request, response, user);				

		}else
			return null;
		}

	/**更换角色之前，列出用户已选的角色列表
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
				
				//获取登陆角色的主角色编号
				String role_id=user.getRoleID();
				
				//从用户上下文对象中获取登陆用户的已选角色集合
				String[] roles=	user.getRole();
								
				
				//将主角色编号写到request对象中
				request.setAttribute("roleId", role_id);	
				
				//将角色数组对象写到request对象中		
				request.setAttribute("roles", roles);	
				
				
				//执行页面跳转，到选择角色页面
				return mapping.findForward("list");					
									
		
		}
		
		
	/**执行以新的主角色登录
	 * */	
		
	public ActionForward changerole(
			ActionMapping mapping,
			BaseForm form,
			HttpServletRequest request,
			HttpServletResponse response,
			UserContext user)
			throws Exception {
				
				//从提交表单中获得选择的登录角色，并设置用户上下文对象user主角色
				String role_id=request.getParameter("selectrole");
				
				user.setRoleID(role_id);
				
				user.setRoleName(ReDefSDicMap.getDicItemVal("0002",role_id));
				//判断独立审批人，如果是，显示审批员+额度
								
				BaseDAO homepageDAO = (BaseDAO) getBean("homepage_dao");	
				
				String homepage =
									homepageDAO.querySingle(
										"select URL from ep_homepage where TEMPL_ID in (select TEMPL_ID from ep_role where ROLE_ID='"
											+ user.getRoleID()
											+ "')");

								if (homepage == null || homepage.trim().length() <= 0) {
									request.getSession().setAttribute("homepage", "/blank.htm");
								} else {
									request.getSession().setAttribute("homepage", homepage);
								}			
				
				//将主角色编号写到request对象中
				request.getSession().setAttribute("user",user);					

				//执行页面跳转，到main页
				return mapping.findForward("changerole");						
		
		}

}
