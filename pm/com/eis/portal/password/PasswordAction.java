/*********************************************************
 * File: 
 * 
 * Version 1.0
 * 
 * Date     2005-9-19
 * 
 * Author   lihaibao
 * 
 ********************************************************/

package com.eis.portal.password;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionRedirect;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.commons.beanutils.BeanUtilsBean;

import com.eis.portal.UserContext;
import com.eis.util.SysLog;
import com.eis.factory.*;

import java.util.List;

import com.eis.base.*;
import com.eis.portal.UserContext;
import com.eis.base.PageObject;
import com.eis.util.*;

/**
 * 说明：密码修改控制层实现类
 * 
 */
public class PasswordAction extends BaseAction {

	/**
	 * 
	 */
	public PasswordAction() {
		super();

	}

	/** 
	 * 执行请求处理 
	 */
	public ActionForward process(
		ActionMapping mapping,
		BaseForm form,
		HttpServletRequest request,
		HttpServletResponse response,
		UserContext user)
		throws Exception {
		String act = form.getAct();

		if (act.equals("u")) { //修改 

			String step = request.getParameter("step");

			if (null == step) { //初始化阶段，查询明细信息并跳转到修改页面 

				return editInfo(mapping, form, request, response, user);

			} else //用户已提交修改后的数据，执行数据保存 

				return update(mapping, form, request, response, user);
		} else
			//return null;
			return mapping.findForward("edit");
	}

	/** 
	 * 到修改页面 
	 */
	public ActionForward editInfo(
		ActionMapping mapping,
		BaseForm form,
		HttpServletRequest request,
		HttpServletResponse response,
		UserContext user)
		throws Exception {


		//设置调用方式会话对象“modify_manner”为从菜单调用
		request.getSession().setAttribute("modify_manner", "menu");

		//执行页面跳转,到修改页面 
		return mapping.findForward("edit");
	}

	/** 
	 * 更新用户密码数据 
	 */
	public ActionForward update(
		ActionMapping mapping,
		BaseForm form,
		HttpServletRequest request,
		HttpServletResponse response,
		UserContext user)
		throws Exception {

		//获得修改密码业务对象PasswordBO
		PasswordBO bo = (PasswordBO) getBean("password_bo");
		
		//获得修改密码业务对象PasswordBO
		PasswordVO vo = new PasswordVO();		
		
		
		//将form中值传输给PasswordVO 
		copyProperties(vo, form);

		
		//从“user_id”会话对象中获得值,并设置VO的user_id值
		//说明：此值在LoginAction中，当用户密码为默认密码时，被设置为user_id
		vo.setUser_id((String)request.getSession().getAttribute("user_id"));		
		
		
		if(null == vo.getUser_id() ){			
			//如果值为空，说明非默认密码而需修改的情况
			//设置修改情况会话标志对象"modify_manner"为"done"
			//从用户上下文对象中获取user_id,并赋给VO
						
			vo.setUser_id(user.getUserID());
			request.getSession().setAttribute("modify_manner","done");
		
		}				
		
		
		
		//调用业务对象BO的updatePwd(vo, user)方法,更新密码 
		if(bo.updatePwd(vo, user) <= 0){
			//更新失败，到失败页面
			return mapping.findForward("fail");
		}	
		
		
		//更新成功，到成功页面
		request.getSession().setAttribute("success", "y");

		//检查会话标志对象"modify_manner"
		Object modify_manner =
			request.getSession().getAttribute("modify_manner");

		if (modify_manner == null) {   //登录调用
			//移除“user”会话对象
			request.getSession().removeAttribute("user");
			//执行页面跳转，到登录页面 
			return mapping.findForward("login");

		} else {    //菜单调用
			//移除"modify_manner"会话对象
			//登记更改密码操作日志
			OpLog.Log(user, "03", "u", "用户更改密码", vo.getUser_id());	
			request.getSession().removeAttribute("modify_manner");
			//执行页面跳转，到成功页 
			return mapping.findForward("blank");


		}

		
	}

}