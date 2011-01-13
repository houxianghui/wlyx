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
 * ˵������ɫ����
 * 
 */
public class RoleAction extends BaseAction {

	/**
	 * 
	 */
	public RoleAction() {
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

		if (act.equals("c")) { //���ӽ�ɫ

			String step = request.getParameter("step");

			if (null == step) { //��ʼ���׶Σ���ת�����ӽ�ɫҳ��
				return mapping.findForward("new");

			} else //�û����ύ��ɫ���ݣ�ִ�����ݱ���
				return add(mapping, form, request, response, user);
		} else if (act.equals("u")) { //�޸Ľ�ɫ

			String step = request.getParameter("step");
			if (null == step) { //��ʼ���׶Σ���ѯ��ɫ��Ϣ����ת����ɫ�޸�ҳ��
				return editInfo(mapping, form, request, response, user);
			} else //�û����ύ��ɫ���ݣ�ִ�����ݱ���
				return update(mapping, form, request, response, user);
		} else if (act.equals("r")) { //��ѯ��ɫ��Ϣ

			return retrieve(mapping, form, request, response, user);

		} else if (act.equals("t")) { //�ϳ���ɫ��Ϣ

			return terminate(mapping, form, request, response, user);

		} else if (act.equals("d")) { //ɾ����ɫ

			return delete(mapping, form, request, response, user);

		} else if (act.equals("list")) { //����ά����ɫ�б�

			return list(mapping, form, request, response, user);

		} else if (act.equals("ql")) { //��ѯ��ɫ�б�

			return querylist(mapping, form, request, response, user);

		} else
			return null;
	}

	/**
	 * �б�
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

		//���ҵ�����
		BaseBO bo = (BaseBO) getBean("role_bo");

		//����ҵ������list��������,�����б���
		bo.list(page, user);

		//���������д��request������
		request.setAttribute("pageResult", page);
		request.setAttribute("checked", ((RoleForm)form).getChecked());

		//ִ��ҳ����ת
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
		//���ҵ�����

		//	  ����ҵ������list��������,�����б���

		//	  ���������д��request������

		//	  ִ��ҳ����ת
		return mapping.findForward("querylist");
	}

	/**
	 * ��ѯ��ϸ
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
		BaseBO bo = (BaseBO) getBean("role_bo");

		RoleForm bean = (RoleForm) form;

		RoleVO vo = new RoleVO();

		vo.setRole_id(bean.getRole_id());
		

		//����ҵ������retrieve��������,��ѯ��ϸ��Ϣ
		vo = (RoleVO) bo.retrieve(vo, user);

		//���������д��form������
		copyProperties(bean, vo);

		//ִ��ҳ����ת
		return mapping.findForward("view");
	}

	/**
	 * ���ӽ�ɫ
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
			
		//����Ȩ��У��
		if (!OpMap.checkRoleAuth(user.getRoleID(), "role_c")) {

			BaseException e = new BaseException();
			e.setErrorCode("E020001");
			throw e;
		}

		//���ҵ�����
		RoleBO bo = (RoleBO) getBean("role_bo");

		RoleForm bean = (RoleForm) form;

		RoleVO vo = new RoleVO();

		//�������ݴ���
		copyProperties(vo, bean);	
		
		//����ȫ�н�ɫ���������Ÿ�Ϊ"0",�����ض�������ɫ����鼶��ͻ��������һ����
		if (vo.getAmsd_store() == null
			|| vo.getAmsd_store().trim().length() <= 0){
				vo.setAmsd_store("0");
		
		}			
		

		vo.setReg_dt(DateUtil.getDTStr());
		vo.setUser_id(user.getUserID());
		vo.setStatus("1");

		//����ҵ������add��������

		//����ROLE_ID�����ֵ
		String maxKey = bo.retrieveMax(user);

		String nextKey = null;

		if (maxKey == null  || maxKey.trim().length()<1 || maxKey.trim().equals("0")) {

			nextKey = "00000001";

		} else {

			//��ȡҪ������ɫ��ROLE_IDֵ
			nextKey = KeyGenerator.getNextKeyStr(maxKey, 8);
			//SysLog.debug("���role_id+1��ֵΪ��"+nextKey);

		}

		vo.setRole_id(nextKey);

		bo.add(vo, user);

		//ִ��ҳ����ת,���ص��б�ҳ��
		bean.setRole_name(null);
		bean.setChecked("checked=\"checked\"");
		return list(mapping, form, request, response, user);

	}


	/**��ʾ���ӽ�ɫҳ�� */
	public ActionForward editInfo(
		ActionMapping mapping,
		BaseForm form,
		HttpServletRequest request,
		HttpServletResponse response,
		UserContext user)
		throws Exception {

		//	  ���ҵ�����
		BaseBO bo = (BaseBO) getBean("role_bo");

		RoleForm bean = (RoleForm) form;

		RoleVO vo = new RoleVO();

		vo.setRole_id(bean.getRole_id());

		//����ҵ������retrieve��������,��ѯ��ϸ��Ϣ
		vo = (RoleVO) bo.retrieve(vo, user);

		//���������д��form������		
		copyProperties(bean, vo);

		//	  ִ��ҳ����ת
		return mapping.findForward("edit");
	}

    /**���½�ɫ */
	public ActionForward update(
		ActionMapping mapping,
		BaseForm form,
		HttpServletRequest request,
		HttpServletResponse response,
		UserContext user)
		throws Exception {
			
		//����Ȩ��У��
		if (!OpMap.checkRoleAuth(user.getRoleID(), "role_u")) {

			BaseException e = new BaseException();
			e.setErrorCode("E020001");
			throw e;
		}

		//���ҵ�����
		BaseBO bo = (BaseBO) getBean("role_bo");

		RoleForm bean = (RoleForm) form;
		RoleVO vo = new RoleVO();

		//�������ݴ���
		copyProperties(vo, bean);
		
		//����ȫ�н�ɫ���������Ÿ�Ϊ"0",�����ض�������ɫ����鼶��ͻ��������һ����
		if (vo.getAmsd_store() == null
			|| vo.getAmsd_store().trim().length() <= 0){
				vo.setAmsd_store("0");
		}		

		//��������
		vo.setReg_dt(DateUtil.getDTStr());
		//������Ա
		vo.setUser_id(user.getUserID());		

		//����ҵ������update��������
		bo.update(vo, user);

		//�������ݱ�����
		request.setAttribute("success", "y");

		//	  ִ��ҳ����ת
		return mapping.findForward("edit");
	}


	/**�ϳ���ɫ
	 *  */
	public ActionForward terminate(
		ActionMapping mapping,
		BaseForm form,
		HttpServletRequest request,
		HttpServletResponse response,
		UserContext user)
		throws Exception {
			
		//����Ȩ��У��
		if (!OpMap.checkRoleAuth(user.getRoleID(), "role_terminate")) {

			BaseException e = new BaseException();
			e.setErrorCode("E020001");
			throw e;
		}

		//��ý�ɫҵ�����
		RoleBO bo = (RoleBO) getBean("role_bo");

		RoleForm bean = (RoleForm) form;

		RoleVO vo = new RoleVO();

		vo.setRole_id(bean.getRole_id());

		//���ý�ɫҵ������retrieve��������,��ȡ��ɫ��ϸ��Ϣ��vo
		vo = (RoleVO) bo.retrieve(vo, user);

		//�鿴��ɫ�Ƿ��ѷϳ�
		if ((vo.getStatus()).equals("0")) {
			//����ɫ�ѷϳ�����ʾ�������б�ҳ��
			return forwardSuccessPage(
				request,
				mapping,
				"��ɫ�ѷϳ���",
				"Role.do?act=list");

		} else {
			//����ɫδ�ϳ���ִ�зϳ�����
			//���ø���ʱ��Ϊ����
			vo.setReg_dt(DateUtil.getDTStr());	
			SysLog.debug("��ɫ��������Ϊ��"+vo.getReg_dt());		
			//���ø�����Ա
			vo.setUser_id(user.getUserID());	

			//���ý�ɫҵ������terminate��������
			bo.terminate(vo, user);

			//�������ݱ�����
			//request.getSession().setAttribute("success", "y");

			//ִ��ҳ����ת		
			return forwardSuccessPage(
				request,
				mapping,
				"��ɫ�ϳ��ɹ���",
				"Role.do?act=list");

		}

	}
	
	
	/**ɾ����ɫ */
	public ActionForward delete(
		ActionMapping mapping,
		BaseForm form,
		HttpServletRequest request,
		HttpServletResponse response,
		UserContext user)
		throws Exception {
			
		//����Ȩ��У��
		if (!OpMap.checkRoleAuth(user.getRoleID(), "role_d")) {

			BaseException e = new BaseException();
			e.setErrorCode("E020001");
			throw e;
		}

		//���ҵ�����
		BaseBO bo = (BaseBO) getBean("role_bo");

		RoleForm bean = (RoleForm) form;

		RoleVO vo = new RoleVO();

		vo.setRole_id(bean.getRole_id());
		SysLog.debug("���е�role_id��" + vo.getRole_id());

		//��ѯ��ѡ��ɫ��״̬
		vo = (RoleVO) bo.retrieve(vo, user);
		SysLog.debug("role_id��״̬��" + vo.getStatus());

		if ((vo.getStatus()).equals(new String("0"))) {

			SysLog.debug("��ʼ����ɾ����ɫ:" + vo.getRole_id());

			//����ҵ������delete��������,ִ������ɾ��
			bo.delete(vo, user);

			//ִ��ҳ����ת,���ص��б�ҳ��

			return forwardSuccessPage(
				request,
				mapping,
				"����ɾ���ɹ���",
				"Role.do?act=list");

		} else {

			return forwardSuccessPage(
				request,
				mapping,
				"���ȷϳ���ɫ��",
				"Role.do?act=list");

		}

	}

}
