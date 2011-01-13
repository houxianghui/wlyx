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
 * ˵������ɫ����Ȩ�޶�����Ʋ�ʵ����
 * 
 */
public class RoleOPAction extends BaseAction {

	/**
	 * 
	 */
	public RoleOPAction() {
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
		//��ȡ������ʽ
		String act = request.getParameter("act");
		SysLog.debug("act="+act);

		if (act.equals("u")) { //�޸Ľ�ɫ����Ȩ��
				//��ȡ���������ʶ
				String step = request.getParameter("step");	
				
			
				if (null == step) { //��ʼ���׶Σ�����ɫ����Ȩ����Ϣ�޸�ҳ��
				
					return editInfo(mapping, form, request, response, user);				
				
				} else 
				//���ύ��ɫ����Ȩ���޸����ݣ�ִ�����ݱ���
			
					return update(mapping, form, request, response, user);
			

		} else
			return null;
	}


	
	/**��ѯ��ɫ�Ŀ�ѡ����Ȩ��ѡ����Ϣ��
	 * ����ѡ����Ȩ��ѡ����Ϣ��ת����ɫ����Ȩ���޸�ҳ��
	 * */
	public ActionForward editInfo(
		ActionMapping mapping,
		BaseForm form,
		HttpServletRequest request,
		HttpServletResponse response,
		UserContext user)
		throws Exception {
			
			//����Ȩ��У��
			if (!OpMap.checkRoleAuth(user.getRoleID(), "role_perm")) {

				BaseException e = new BaseException();
				e.setErrorCode("E020001");
				throw e;
			}

			//��ý�ɫ����Ȩ��ҵ�����
			RoleOPBO bo = (RoleOPBO)getBean("roleop_bo");

			//����ҵ������list��������,�����б���
		
			String role_id = request.getParameter("role_id");
						
		
			//��ý�ɫ��ѡ�Ĳ���Ȩ�޼���
			List listNotSelected=bo.listOPNotSelected(role_id,user);
			//��ý�ɫ��ѡ��Ĳ���Ȩ�޼���
			List listSelected=bo.listSelected(role_id,user);			
			

			//���������д��request������		
			request.setAttribute("listNotSelected", listNotSelected);
			request.setAttribute("listSelected", listSelected);		
			
			((RoleOPForm)form).setRole_name(bo.queryRoleName(role_id));		
		

			//ִ��ҳ����ת,����ɫ����Ȩ���޸�ҳ��
			return mapping.findForward("edit");
	}


	/**���ݽ�ɫ����ѡ����Ȩ��ѡ�
	 * �������ݿ���Ϣ��ת����ɫ����Ȩ���޸�ҳ��
	 * */
	public ActionForward update(
		ActionMapping mapping,
		BaseForm form,
		HttpServletRequest request,
		HttpServletResponse response,
		UserContext user)
		throws Exception {

		//��ý�ɫ����Ȩ��ҵ�����
		RoleOPBO bo = (RoleOPBO)getBean("roleop_bo");

				
		//���ύ����ý�ɫ����role_id
		String role_id=request.getParameter("role_id");	
		
		//��ȡ��ѡ����Ȩ���б���е���Ŀ������ɫ�µĲ���Ȩ��ѡ��
		String selectop[]=request.getParameterValues("right"); 
		
		List list=new ArrayList();		
		if (selectop!=null)
		{ 		  
		 //������ض���ɫ����ѡ��һ�����Ȩ�ޣ��򣬽���ѡ����Ȩ�޷��� list�����У���������
		  for (int i=0;i<selectop.length;i++) 
		  { 			
			list.add(selectop[i]);
		  }
		  
		  //����ҵ������update�������������½�ɫѡ��Ĳ���Ȩ��
		  bo.update(role_id,list,user); 
		  
		  SysLog.debug("����Ȩ�޸������" );	
		  		  		  
		}
		else{
			//������ض���ɫ��ѡ���κβ���Ȩ�ޣ������delete()������ɾ���˽�ɫѡ�������ԭ����Ȩ��
			
			bo.delete(role_id,user);
			
			SysLog.debug("����Ȩ��ɾ�����" );	
		}
		
		((RoleOPForm)form).setRole_name(bo.queryRoleName(role_id));		

		//�������ݱ���ɹ���־���
		//request.getSession().setAttribute("success_op", "y");

		// ִ��ҳ����ת
		return editInfo(mapping, form, request, response, user);	
	}
	



}
