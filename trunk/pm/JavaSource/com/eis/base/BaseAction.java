/*********************************************************
 * File:BaseAction.java
 *
 * 所有Action类的父类，统一实现会话状态验证
 * @version 1.0
 *
 * Date     2005-8-2
 * @author   辛勇
 *
 * Copyright (C) 2005 Huateng
 * all rights reserved.
 *
 ********************************************************/

package com.eis.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionRedirect;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.springframework.web.struts.ActionSupport;

import com.eis.portal.UserContext;
import com.eis.util.SysLog;
import com.eis.factory.*;
import com.eis.exception.*;

/**
 * 说明：Action实现扩展类
 *
 */

public abstract class BaseAction extends ActionSupport {
	/**
	 * 构造函数
	 */
	public BaseAction() {
		super();

	}

	/**
	 * Action入口
	 * 实现session有效性判断
	 */
	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {



		UserContext userContext =
			(UserContext) request.getSession().getAttribute("user");

		if (null == userContext) {

			return mapping.findForward("relogin");

		} else { //处理用户的请求

			try {

				return process(mapping, (BaseForm)form, request, response, userContext);

			}catch(OpenWinException e){
				request.setAttribute("message",e.getMessage());
				return mapping.findForward("error_msg");
			}catch (MessageException e) {

				return forwardError(request,mapping,e.getMessage());

			}catch (BaseException e) {
		

				//根据错误码查询错误提示信息
				request.setAttribute("error",e);

				//转向到错误提示页面

				return mapping.findForward("base_error");

			} catch (Exception e) {
				e.printStackTrace();
				SysLog.error(e.toString());
				e.printStackTrace();
				Throwable t = e.getCause();
				Throwable c = t;
				if(t == null){
					request.setAttribute("message",e.getMessage());
				}else{
					while(t != null){
						c = t;
						t = t.getCause();
					}
					request.setAttribute("message",c.getMessage());
				}
				return mapping.findForward("default_error");

			} catch (Throwable t) {
				t.printStackTrace();
				t.printStackTrace();

				return mapping.findForward("default_error");
			}

		}

	}



	/**
	 * 转向
	 * @param ActionMapping
	 * @param pageName
	 * @return ActionForward
	 *
	 */
	public ActionForward forward(ActionMapping mapping, String pageName) {
		return (mapping.findForward(pageName));
	}

	/**
	 * 重定向
	 * @param mapping
	 * @param pageName
	 * @return ActionForward
	 *
	 */
	public ActionForward forward(String pageName) {
		return (new ActionForward(pageName));
	}

	/**
	 * 重定向
	 * @param mapping
	 * @param pageName
	 * @return ActionForward
	 *
	 */
	public ActionForward redirect(ActionMapping mapping, String pageName) {
		return new ActionRedirect(mapping.findForward(pageName));
	}

	/**
	 * 重定向
	 * @param mapping
	 * @param path - 资源相对路径
	 * @return ActionForward
	 *
	 */
	public ActionForward redirect(String path) {
		return new ActionRedirect(path);
	}



	/**
	 * 转向到成功信息提示页面
	 * @param request
	 * @param mapping
	 * @param msg
	 * @return ActionForward
	 *
	 */
	public ActionForward forwardSuccessPage(
		HttpServletRequest request,
		ActionMapping mapping,
		String msg) {

		request.setAttribute("message", msg);

		return mapping.findForward("default_success");
	}

	/**
	 * 转向到成功信息提示,并指定返回页面
	 * @param request
	 * @param mapping
	 * @param msg
	 * @param backUrl
	 * @return ActionForward
	 *
	 */
	public ActionForward forwardSuccessPage(
		HttpServletRequest request,
		ActionMapping mapping,
		String msg,
		String backURL) {

		request.setAttribute("message", msg);

		request.setAttribute("backurl", backURL);

		return mapping.findForward("default_success");
	}

	/**
	 * 转向到错误信息提示页面
	 * @param request
	 * @param mapping
	 * @param message
	 * @return ActionForward
	 *
	 */
	public ActionForward forwardError(
		HttpServletRequest request,
		ActionMapping mapping,
		String message) {

		request.setAttribute("message", message);

		return (mapping.findForward("default_error"));
	}

	/**
	 * 转向到错误信息提示页面,并指定返回页面
	 * @param request
	 * @param mapping
	 * @param msg
	 * @return ActionForward
	 *
	 */
	public ActionForward forwardError(
		HttpServletRequest request,
		ActionMapping mapping,
		String msg,
		String backURL) {

		request.setAttribute("message", msg);
		request.setAttribute("backurl", backURL);
		return (mapping.findForward("default_error"));
	}


	/**
	 * 转向到错误信息提示页面,提示完信息后关闭窗口
	 * @param request
	 * @param mapping
	 * @param message
	 * @return ActionForward
	 *
	 */
	public ActionForward forwardErrorClose(
		HttpServletRequest request,
		ActionMapping mapping,
		String message) {

		request.setAttribute("message", message);

		return (mapping.findForward("error_close"));
	}


	/**
	 * 根据bean ID获得实例对象
	 * @param name - bean ID
	 * @return bean的实例对象
	 *
	 */
	public Object getBean(String name) throws Exception {		
		if( this.getWebApplicationContext() ==null){
			return BeanFactory.getBean(name);
		}
		return this.getWebApplicationContext().getBean(name);
	}

	/**
	 * 在两个对象之间进行数据传输
	 * @param dest - 目标对象
	 * @param origin - 原对象
	 *
	 */
	public void copyProperties(Object dest,Object origin) throws Exception {
		new BeanUtilsBean().copyProperties(dest,origin);
	}


	/**
	 * 将HashMap对象中的键值复制到一个对象中
	 *
	 * @param map - 存放源属性的HashMap对象
	 * @param dest - 目标对象
	 *
	 */
	public void copyProperties(HashMap map,Object dest) throws Exception {
		new BeanUtilsBean().populate(dest,map);
	}
	public void down(HttpServletRequest request,HttpServletResponse response,String chName,String path)throws Exception{
		String fileName = null;
		if(request.getHeader("User-Agent").toLowerCase().indexOf("firefox") != -1){
			fileName = new String(chName.getBytes("utf-8"),"iso8859-1");
		}else{
			fileName = URLEncoder.encode(chName, "UTF-8");
		}
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;filename=\"" + fileName + "\"");
		OutputStream os = null;
		FileInputStream fis = null;
		try{
			os = response.getOutputStream();
			fis = new FileInputStream(path+File.separator+chName);
			byte[] b = new byte[1000];
			int i = 0;
			while((i=fis.read(b))!=-1){
				os.write(b,0,i);
			}
			
		}finally{
			if(os != null){
				os.flush();
				os.close();
			}
			if(fis != null){
				fis.close();
			}
		}
	}
	public String getClasspath(){
		return getServletContext().getRealPath("/");
	}
	public String getResourcePath(){
		return getClasspath()+"/resource";
	}
	//-----------------以下方法需要重载----------------------

	/**
	 * 进行请求处理
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @param user
	 * @return ActionForward
	 * @throws Exception
	 *
	 */
	public abstract ActionForward process(
		ActionMapping mapping,
		BaseForm form,
		HttpServletRequest request,
		HttpServletResponse response,
		UserContext user)
		throws Exception;

}
