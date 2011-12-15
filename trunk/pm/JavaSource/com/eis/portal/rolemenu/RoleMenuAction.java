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
 * ˵����
 * 
 */
public class RoleMenuAction extends BaseAction {

	/**
	 * 
	 */
	public RoleMenuAction() {
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

		String act = request.getParameter("act");

		if (act.equals("u")) { //�޸Ľ�ɫ�˵�Ȩ��
			
				String step = request.getParameter("step");
			
				if (null == step) { //��ʼ���׶Σ�����ɫ�˵���Ϣ�޸�ҳ��
				
					return editInfo(mapping, form, request, response, user);					
				
				} else 
				//���ύ��ɫ�˵��޸����ݣ�ִ�����ݱ���
				//������ɾ����ǰӵ��Ȩ�޵Ĳ˵��������Ŀǰӵ��Ȩ�޵Ĳ˵���
			
					return update(mapping, form, request, response, user);
				
		}  else if (act.equals("list")) { //����ά����ɫ�˵�Ȩ���б�
			
				String step = request.getParameter("step");
			
				if (null == step) { //��ʾ��ɫ�˵�Ȩ����Ϣ�б�Ϊ���޸�ҳ��
				
					return list(mapping, form, request, response, user);
				
				} else //���ύ��ɫ�˵�Ȩ���޸ģ�ִ�����ݱ���
			
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
		

		//��ý�ɫ�˵�Ȩ��ҵ�����
		RoleMenuBO bo = (RoleMenuBO)getBean("rolemenu_bo");

		//��ȡ��ɫ����
		
		String role_id = request.getParameter("role_id");
		
		/**����ҵ������list��������,���ض����б�
		 * ����Ԫ��˳�����˵����˳��,ÿһ�����а�����
		 * �˵����𡢲˵����롢�˵����ơ���ѡ���Ƿ�ѡ��
		 * �Ƿ���ʾ��ѡ��
		 * ��ӳ��ѡ��ɫ��ȫ���˵���ӵ�еĲ˵�Ȩ�޵����
		 * **/		
		
		List list=bo.list(role_id,user);
		

		//���б�������д��request������		
		request.setAttribute("selectmenuList", list);
		
		((RoleMenuForm)form).setRole_name(bo.queryRoleName(role_id));	
		

		//ִ��ҳ����ת
		return mapping.findForward("list");
	}
	
	
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

		//��ý�ɫ�˵�Ȩ��ҵ�����
		RoleMenuBO bo = (RoleMenuBO)getBean("rolemenu_bo");

		//��ȡ��ɫ����		
		String role_id = request.getParameter("role_id");
		
		/**����ҵ������list��������,���ض��󼯺ϣ�
		 * ����Ԫ��˳�����˵����˳��,ÿһ�����а�����
		 * �˵����𡢲˵����롢�˵����ơ���ѡ���Ƿ�ѡ��
		 * �Ƿ���ʾ��ѡ��
		 * ���ؼ��ϰ����˵���νṹ��Ϣ����ѡ��ɫӵ��Ȩ�޵Ĳ˵�����Ϣ
		 * **/		
		
		List list=bo.list(role_id,user);		
		

		//���б�������д��request������		
		request.setAttribute("selectmenuList", list);	
		
		((RoleMenuForm)form).setRole_name(bo.queryRoleName(role_id));	
		

		//ִ��ҳ����ת
		return mapping.findForward("edit");		
		
	}


	/**���½�ɫ�˵�Ȩ��
	 *  */
	public ActionForward update(
		ActionMapping mapping,
		BaseForm form,
		HttpServletRequest request,
		HttpServletResponse response,
		UserContext user)
		throws Exception {

		//��ý�ɫ�˵�Ȩ��ҵ�����
		RoleMenuBO bo = (RoleMenuBO)getBean("rolemenu_bo");

		//������ɫ�˵����ݶ���
		RoleMenuVO vo = new RoleMenuVO();
		
		//���ύ����ý�ɫ����role_id
		String role_id=request.getParameter("role_id");	
		
		
		/**��ȡ���и�ѡ����selectmenu�й�ѡ����ɵ�����***/		
		String selectmenu[]=request.getParameterValues("selectmenu"); 
		List list=new ArrayList();
		
		/**�����ٹ�ѡ��һ��˵�***/
		if (selectmenu!=null)
		{ 
			/**����ѡ�˵�����Ȩ�޷���list�����У���������*/					  
		  for (int i=0;i<selectmenu.length;i++) 
		  { 			
			list.add(selectmenu[i]);
		  }
		  
		  /**����ҵ������update����������
		   * �Խ�ɫrole_id�����չ�ѡ�˵����ϸ��²˵�Ȩ��
		   * ע������ɾ����ɫ����Ȩ�ޱ��У�
		   * ���ڴ˽�ɫrole_id�˵�Ȩ��֮�µĲ���Ȩ��
		   **/
		  bo.update(role_id,list,user); 
		  
		  SysLog.debug("�˵�Ȩ�޸������" );			  		  
		}
		else{
			
			/**���Խ�ɫ����ѡ�κβ˵�Ȩ�ޣ�
			 * �����ҵ������delete()������
			 * ɾ����ɫrole_id�����в˵�Ȩ��,
			 * ע������ɾ����ɫrole_id��˵�Ȩ����صĲ���Ȩ��
			 ***/			
			
			bo.delete(role_id,list,user);
			
			SysLog.debug("�˵�Ȩ��ɾ�����" );	
		}
		
		((RoleMenuForm)form).setRole_name(bo.queryRoleName(role_id));	

		/**�������ݸ��³ɹ����
		 **/
		//request.getSession().setAttribute("success", "y");

		// ִ��ҳ����ת
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
		//���ҵ�����

		//	  ����ҵ������list��������,�����б���

		//	  ���������д��request������

		//	  ִ��ҳ����ת
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

		//���ҵ�����
		BaseBO bo = (BaseBO)getBean("role_bo");
		
		RoleForm bean = (RoleForm) form;

		RoleVO vo = new RoleVO();

		vo.setRole_id(bean.getRole_id());

		//����ҵ������retrieve��������,��ѯ��ϸ��Ϣ
		vo = (RoleVO) bo.retrieve(vo, user);

		//���������д��form������
		copyProperties(bean,vo);

		//ִ��ҳ����ת
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
		//���ҵ�����
		BaseBO bo = (BaseBO)getBean("role_bo");

		RoleForm bean = (RoleForm) form;
		
		RoleVO vo = new RoleVO();		
		
		//�������ݴ���
		copyProperties(vo,bean);	
			
		
		vo.setReg_dt(DateUtil.getDTStr());
		vo.setUser_id("0007");

		//����ҵ������add��������
		
		bo.add(vo, user);

		//ִ��ҳ����ת,���ص��б�ҳ��
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

		//���ҵ�����
		BaseBO bo = (BaseBO)getBean("role_bo");		
		
		RoleForm bean = (RoleForm)form;

		RoleVO vo = new RoleVO();

		vo.setRole_id(bean.getRole_id());
		
		//����ҵ������retrieve��������,��ѯ��ϸ��Ϣ
		//vo = (RoleVO) bo.retrieve(vo, user);
		
		if((vo.getStatus()).equals(new String("0"))){
			
			//����ҵ������delete��������,ִ������ɾ��
			bo.delete(vo, user);			

			//ִ��ҳ����ת,���ص��б�ҳ��
		
			return forwardSuccessPage(request,mapping,"����ɾ���ɹ���","Role.do?act=list");
					
		}else{
			
			return forwardSuccessPage(request,mapping,"���ȷϳ���ɫ��","Role.do?act=list");
			
		}		
		
	}

}
