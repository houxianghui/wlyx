/*********************************************************
 * File:BaseAction.java
 *
 * ����Action��ĸ��࣬ͳһʵ�ֻỰ״̬��֤
 * @version 1.0
 *
 * Date     2005-8-2
 * @author   ����
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
 * ˵����Actionʵ����չ��
 *
 */

public abstract class BaseAction extends ActionSupport {
	/**
	 * ���캯��
	 */
	public BaseAction() {
		super();

	}

	/**
	 * Action���
	 * ʵ��session��Ч���ж�
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

		} else { //�����û�������

			try {

				return process(mapping, (BaseForm)form, request, response, userContext);

			}catch(OpenWinException e){
				request.setAttribute("message",e.getMessage());
				return mapping.findForward("error_msg");
			}catch (MessageException e) {

				return forwardError(request,mapping,e.getMessage());

			}catch (BaseException e) {
		

				//���ݴ������ѯ������ʾ��Ϣ
				request.setAttribute("error",e);

				//ת�򵽴�����ʾҳ��

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
	 * ת��
	 * @param ActionMapping
	 * @param pageName
	 * @return ActionForward
	 *
	 */
	public ActionForward forward(ActionMapping mapping, String pageName) {
		return (mapping.findForward(pageName));
	}

	/**
	 * �ض���
	 * @param mapping
	 * @param pageName
	 * @return ActionForward
	 *
	 */
	public ActionForward forward(String pageName) {
		return (new ActionForward(pageName));
	}

	/**
	 * �ض���
	 * @param mapping
	 * @param pageName
	 * @return ActionForward
	 *
	 */
	public ActionForward redirect(ActionMapping mapping, String pageName) {
		return new ActionRedirect(mapping.findForward(pageName));
	}

	/**
	 * �ض���
	 * @param mapping
	 * @param path - ��Դ���·��
	 * @return ActionForward
	 *
	 */
	public ActionForward redirect(String path) {
		return new ActionRedirect(path);
	}



	/**
	 * ת�򵽳ɹ���Ϣ��ʾҳ��
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
	 * ת�򵽳ɹ���Ϣ��ʾ,��ָ������ҳ��
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
	 * ת�򵽴�����Ϣ��ʾҳ��
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
	 * ת�򵽴�����Ϣ��ʾҳ��,��ָ������ҳ��
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
	 * ת�򵽴�����Ϣ��ʾҳ��,��ʾ����Ϣ��رմ���
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
	 * ����bean ID���ʵ������
	 * @param name - bean ID
	 * @return bean��ʵ������
	 *
	 */
	public Object getBean(String name) throws Exception {		
		if( this.getWebApplicationContext() ==null){
			return BeanFactory.getBean(name);
		}
		return this.getWebApplicationContext().getBean(name);
	}

	/**
	 * ����������֮��������ݴ���
	 * @param dest - Ŀ�����
	 * @param origin - ԭ����
	 *
	 */
	public void copyProperties(Object dest,Object origin) throws Exception {
		new BeanUtilsBean().copyProperties(dest,origin);
	}


	/**
	 * ��HashMap�����еļ�ֵ���Ƶ�һ��������
	 *
	 * @param map - ���Դ���Ե�HashMap����
	 * @param dest - Ŀ�����
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
	//-----------------���·�����Ҫ����----------------------

	/**
	 * ����������
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
