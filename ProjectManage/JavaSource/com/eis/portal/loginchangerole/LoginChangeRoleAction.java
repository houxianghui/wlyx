/*********************************************************
 * File: LoginChangeRoleAction.java
 * 
 * Version 1.0
 * 
 * Date     2005-10-19
 * 
 * Author   �� ��
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
 * ˵�����û�������¼��ɫ
 * 
 */
public class LoginChangeRoleAction extends BaseAction {

	/**
	 * 
	 */
	public LoginChangeRoleAction() {
		super();

	}

	/* ���� Javadoc��
	 * @see com.eis.base.BaseAction#process(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, com.eis.portal.UserContext)
	 */
	public ActionForward process(
		ActionMapping mapping,
		BaseForm form,
		HttpServletRequest request,
		HttpServletResponse response,
		UserContext user)
		throws Exception {
		//����ύ���еĲ�����ʽactֵ
		String act = request.getParameter("act");

		if (act.equals("list")) {  //������ɫ֮ǰ	
			//�г��û���ѡ�Ľ�ɫ�б�
			return list(mapping, form, request, response, user);					
				
		} else if (act.equals("changerole")) { 
			//ִ�����µ�����ɫ��¼
			return changerole(mapping, form, request, response, user);				

		}else
			return null;
		}

	/**������ɫ֮ǰ���г��û���ѡ�Ľ�ɫ�б�
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
				
				//��ȡ��½��ɫ������ɫ���
				String role_id=user.getRoleID();
				
				//���û������Ķ����л�ȡ��½�û�����ѡ��ɫ����
				String[] roles=	user.getRole();
								
				
				//������ɫ���д��request������
				request.setAttribute("roleId", role_id);	
				
				//����ɫ�������д��request������		
				request.setAttribute("roles", roles);	
				
				
				//ִ��ҳ����ת����ѡ���ɫҳ��
				return mapping.findForward("list");					
									
		
		}
		
		
	/**ִ�����µ�����ɫ��¼
	 * */	
		
	public ActionForward changerole(
			ActionMapping mapping,
			BaseForm form,
			HttpServletRequest request,
			HttpServletResponse response,
			UserContext user)
			throws Exception {
				
				//���ύ���л��ѡ��ĵ�¼��ɫ���������û������Ķ���user����ɫ
				String role_id=request.getParameter("selectrole");
				
				user.setRoleID(role_id);
				
				user.setRoleName(ReDefSDicMap.getDicItemVal("0002",role_id));
				//�ж϶��������ˣ�����ǣ���ʾ����Ա+���
								
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
				
				//������ɫ���д��request������
				request.getSession().setAttribute("user",user);					

				//ִ��ҳ����ת����mainҳ
				return mapping.findForward("changerole");						
		
		}

}
