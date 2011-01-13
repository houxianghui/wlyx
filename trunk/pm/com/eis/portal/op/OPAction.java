/*********************************************************
 * File: OPAction.java
 * 
 * Version 1.0
 * 
 * Date     2005-9-20
 * 
 * Author   �� ��
 * 
 ********************************************************/

package com.eis.portal.op;

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
import com.eis.portal.menu.*;
import com.eis.cache.*;

/**
 * ˵����
 * 
 */
public class OPAction extends BaseAction {

	/**
	 * 
	 */
	public OPAction() {
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

		if (act.equals("c")) { //���Ӳ���Ȩ�޶���

			String step = request.getParameter("step");

			if (null == step) { //��ʼ���׶Σ���ת�����Ӳ���Ȩ�޶���ҳ��

				return beforeAdd(mapping, form, request, response, user);

			} else //�û����ύ���ݣ�ִ�����ݱ���
				return add(mapping, form, request, response, user);
		} else if (act.equals("u")) { //�޸Ĳ���Ȩ�޶���

			String step = request.getParameter("step");

			if (null == step) { //��ʼ���׶Σ���ѯ����Ȩ����Ϣ����ת������Ȩ�޶����޸�ҳ��

				return editInfo(mapping, form, request, response, user);

			} else //�û����ύ����Ȩ�޶������ݣ�ִ�����ݱ���

				return update(mapping, form, request, response, user);

		} else if (act.equals("r")) { //��ѯ����Ȩ�޶�����Ϣ			

			return retrieve(mapping, form, request, response, user);

		} else if (act.equals("lr")) { //��ѯ����Ȩ�޶�����Ϣ

			//String op_code = request.getParameter("op_code");							

			return null;
			//retrieve(op_code, mapping, form, request, response, user);

		} else if (act.equals("d")) { //ɾ������Ȩ�޶���

			return delete(mapping, form, request, response, user);

		} else if (act.equals("list")) { //����ά������Ȩ�޶����б�

			return list(mapping, form, request, response, user);

		} else if (act.equals("ml")) { //���ز˵��б�

			return menulist(mapping, form, request, response, user);

		} else if (act.equals("ql")) { //��ѯ����Ȩ�޶����б�

			return querylist(mapping, form, request, response, user);

		} else if (act.equals("blk")) { //���հ���ʾҳ

			return mapping.findForward("blank");

		} else if (act.equals("bfl")) { //���׼��˵�ҳ�������޴����¼

			return mapping.findForward("beforeFirstLevel");

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

		PageObject page = new PageObject();
		String pageNo = request.getParameter("pageNO");

		String requery = request.getParameter("requery");

		if (pageNo == null
			|| (requery != null && requery.trim().equals("y"))) {
			page.setCurPage(1);
		} else {
			page.setCurPage(Integer.parseInt(pageNo));
		}
		page.addFilter("menu_id", request.getParameter("menu_id"));
		page.addFilter("caption", request.getParameter("caption"));

		//���ҵ�����
		BaseBO bo = (BaseBO) getBean("op_bo");

		//����ҵ������list��������,�����б���
		bo.list(page, user);

		//���������д��request������
		request.setAttribute("pageResult", page);

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
	public ActionForward menulist(
		ActionMapping mapping,
		BaseForm form,
		HttpServletRequest request,
		HttpServletResponse response,
		UserContext user)
		throws Exception {

		//���ҵ�����
		BaseBO bo = (BaseBO) getBean("menu_bo");

		//����ҵ������list��������,�����б���
		List list = bo.list(user);

		//���������д��request������		
		request.setAttribute("opmenuList", list);

		//ִ��ҳ����ת
		return mapping.findForward("menulist");
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
		BaseBO bo = (BaseBO) getBean("op_bo");

		OPForm bean = (OPForm) form;

		OPVO vo = new OPVO();

		vo.setOp_code(bean.getOp_code());

		//����ҵ������retrieve��������,��ѯ��ϸ��Ϣ
		vo = (OPVO) bo.retrieve(vo, user);

		//���������д��form������
		copyProperties(bean, vo);

		//ִ��ҳ����ת
		return mapping.findForward("view");
	}

	/**
		 * 
		 * @param menu_id 
		 * @param mapping
		 * @param form
		 * @param request
		 * @param response
		 * @param user
		 * @return
		 * @throws Exception
		 */
	public ActionForward retrieve(
		String op_code,
		ActionMapping mapping,
		BaseForm form,
		HttpServletRequest request,
		HttpServletResponse response,
		UserContext user)
		throws Exception {

		String whereclause = " where OP_CODE='" + op_code + "'";

		//���ҵ�����
		BaseBO bo = (BaseBO) getBean("op_bo");

		OPForm bean = (OPForm) form;

		BaseVO vo = new OPVO();

		//����ҵ������retrieve��������,��ѯ��ϸ��Ϣ
		vo = bo.retrieve(whereclause, user);

		//���������д��form������
		copyProperties(bean, vo);

		//ִ��ҳ����ת
		return mapping.findForward("view");
	}

	//���Ӳ���Ȩ�ޣ�δ�ύ֮ǰ
	public ActionForward beforeAdd(
		ActionMapping mapping,
		BaseForm form,
		HttpServletRequest request,
		HttpServletResponse response,
		UserContext user)
		throws Exception {

		return mapping.findForward("new");
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

		//����Ȩ��У��
		if (!OpMap.checkRoleAuth(user.getRoleID(), "op_c")) {

			BaseException e = new BaseException();
			e.setErrorCode("E020001");
			throw e;
		}

		//���ҵ�����
		OPBO bo = (OPBO) getBean("op_bo");

		OPForm bean = (OPForm) form;

		OPVO vo = new OPVO();

		//�������ݴ���
		copyProperties(vo, bean);

		//����ҵ������add��������

		bo.add(vo, user);

		//ִ��ҳ����ת,���ص��б�ҳ��
		//return mapping.findForward("list");
		return list(mapping, form, request, response, user);

	}

	public ActionForward editInfo(
		ActionMapping mapping,
		BaseForm form,
		HttpServletRequest request,
		HttpServletResponse response,
		UserContext user)
		throws Exception {

		//	  ���ҵ�����
		BaseBO bo = (BaseBO) getBean("op_bo");

		OPForm bean = (OPForm) form;

		OPVO vo = new OPVO();

		vo.setOp_code(bean.getOp_code());

		//����ҵ������retrieve��������,��ѯ��ϸ��Ϣ
		vo = (OPVO) bo.retrieve(vo, user);

		//���������д��form������		
		copyProperties(bean, vo);

		//	  ִ��ҳ����ת
		return mapping.findForward("edit");
	}

	public ActionForward update(
		ActionMapping mapping,
		BaseForm form,
		HttpServletRequest request,
		HttpServletResponse response,
		UserContext user)
		throws Exception {

		//����Ȩ��У��
		if (!OpMap.checkRoleAuth(user.getRoleID(), "op_u")) {

			BaseException e = new BaseException();
			e.setErrorCode("E020001");
			throw e;
		}

		//���ҵ�����
		BaseBO bo = (BaseBO) getBean("op_bo");

		OPForm bean = (OPForm) form;
		OPVO vo = new OPVO();

		//�������ݴ���
		copyProperties(vo, bean);

		//����ҵ������update��������
		bo.update(vo, user);

		//�������ݱ�����
		request.setAttribute("success", "y");

		//	  ִ��ҳ����ת
		return mapping.findForward("edit");
	}

	public ActionForward delete(
		ActionMapping mapping,
		BaseForm form,
		HttpServletRequest request,
		HttpServletResponse response,
		UserContext user)
		throws Exception {

		//����Ȩ��У��
		if (!OpMap.checkRoleAuth(user.getRoleID(), "op_d")) {

			BaseException e = new BaseException();
			e.setErrorCode("E020001");
			throw e;
		}

		//���ҵ�����
		BaseBO bo = (BaseBO) getBean("op_bo");

		OPForm bean = (OPForm) form;

		OPVO vo = new OPVO();

		vo.setOp_code(bean.getOp_code());

		//����ҵ������delete��������,ִ������ɾ��
		bo.delete(vo, user);

		//ִ��ҳ����ת,���ص��б�ҳ��
		String link =
			"OP.do?act=list&menu_id="
				+ bean.getMenu_id()
				+ "&menu_name="
				+ bean.getMenu_name();

		return forwardSuccessPage(request, mapping, "����ɾ���ɹ���", link);

	}

}
