package com.eis.dic.dictype;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import java.util.List;

import com.eis.base.*;
import com.eis.portal.UserContext;
import com.eis.factory.*;
import com.eis.util.*;
import com.eis.cache.*;

/** 
 * ˵�����ֵ������Ϣ�Ŀ����� 
 */
public class DicTypeAction extends BaseAction {

	/** 
	 * ���캯�� 
	 */
	public DicTypeAction() {
		super();
	}

	/** 
	 * ִ�������� 
	 */
	public ActionForward process(
		ActionMapping mapping,
		BaseForm form,
		HttpServletRequest request,
		HttpServletResponse response,
		UserContext user)
		throws Exception {
		String act = form.getAct();
		if (act.equals("c")) { //���� 

			String step = request.getParameter("step");

			if (null == step) { //��ʼ���׶Σ���ת������ҳ�� 

				return mapping.findForward("new");

			} else //�û����ύ�������ݣ�ִ�����ݱ��� 

				return add(mapping, form, request, response, user);

		} else if (act.equals("u")) { //�޸� 

			String step = request.getParameter("step");

			if (null == step) { //��ʼ���׶Σ���ѯ��ϸ��Ϣ����ת���޸�ҳ�� 

				return editInfo(mapping, form, request, response, user);

			} else //�û����ύ�޸ĺ�����ݣ�ִ�����ݱ��� 

				return update(mapping, form, request, response, user);

		} else if (act.equals("r")) { //��ѯ��ϸ��Ϣ 

			return retrieve(mapping, form, request, response, user);

		} else if (act.equals("d")) { //ɾ������ 

			return delete(mapping, form, request, response, user);

		} else if (act.equals("list")) { //����ά���б� 

			return list(mapping, form, request, response, user);

		} else if (act.equals("ql")) { //��ѯ�б� 

			return queryList(mapping, form, request, response, user);

		} else if (act.equals("dm")) { //�༶�ֵ�ɾ�� 

			return deleteM(mapping, form, request, response, user);

		}else
			return null;
	}

	/** 
	 * ��ѯά���б� 
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

		//�ڴ˴�ͨ��page.addFilter()������ӹ������� 
		page.addFilter("type_id_f", request.getParameter("type_id_f"));
		page.addFilter("type_name_f", request.getParameter("type_name_f"));

		//���ҵ����� 
		BaseBO bo = (BaseBO) getBean("dictype_bo");

		//����ҵ������list��������,�����б��� 
		bo.list(page, user);

		//���������д��request������ 
		request.setAttribute("pageResult", page);

		//ִ��ҳ����ת 
		return mapping.findForward("list");
	}

	/** 
	 * ��ѯ�б� 
	 */
	public ActionForward queryList(
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

		//�ڴ˴�ͨ��page.addFilter()������ӹ������� 

		//���ҵ����� 
		BaseBO bo = (BaseBO) getBean("dictype_bo");

		//����ҵ������list��������,�����б��� 
		bo.list(page, user);

		//���������д��request������ 
		request.setAttribute("pageResult", page);

		//ִ��ҳ����ת 
		return mapping.findForward("querylist");
	}

	/** 
	 * �������� 
	 */
	public ActionForward add(
		ActionMapping mapping,
		BaseForm form,
		HttpServletRequest request,
		HttpServletResponse response,
		UserContext user)
		throws Exception {
			
			//����Ȩ��У��
			if (!OpMap.checkRoleAuth(user.getRoleID(), "dicType_c")) {
				BaseException e = new BaseException();
				e.setErrorCode("E020001");
				throw e;
			}
		
		

		//���ҵ����� 
		BaseBO bo = (BaseBO) getBean("dictype_bo");

		DicTypeVO vo = new DicTypeVO();

		//�������ݴ��� 
		copyProperties(vo, form);

		//�ڴ˴����������ֶεĸ�ֵ�����磺vo.setReg_dt(DateUtil.getDTStr()); 

		//����ҵ������add��������,�����б��� 
		bo.add(vo, user);

		//ִ��ҳ����ת,���ص��б�ҳ�� 
		return list(mapping, form, request, response, user);
	}

	/** 
	 * ��ѯά����Ϣ��ϸ 
	 */
	public ActionForward editInfo(
		ActionMapping mapping,
		BaseForm form,
		HttpServletRequest request,
		HttpServletResponse response,
		UserContext user)
		throws Exception {

		//���ҵ����� 
		BaseBO bo = (BaseBO) getBean("dictype_bo");

		DicTypeVO vo = new DicTypeVO();

		//��ò�ѯ���� 
		vo.setType_id(((DicTypeForm) form).getType_id());

		//����ҵ������retrieve��������,��ѯ��ϸ��Ϣ 
		vo = (DicTypeVO) bo.retrieve(vo, user);

		//���������д��form������ 
		copyProperties(form, vo);

		//ִ��ҳ����ת,���ص��б�ҳ�� 
		return mapping.findForward("edit");
	}

	/** 
	 * �޸����� 
	 */
	public ActionForward update(
		ActionMapping mapping,
		BaseForm form,
		HttpServletRequest request,
		HttpServletResponse response,
		UserContext user)
		throws Exception {
			
			//����Ȩ��У��
			if (!OpMap.checkRoleAuth(user.getRoleID(), "dicType_u")) {
				BaseException e = new BaseException();
				e.setErrorCode("E020001");
				throw e;
			}

		//���ҵ����� 
		BaseBO bo = (BaseBO) getBean("dictype_bo");

		DicTypeVO vo = new DicTypeVO();

		//�ڴ˴����������ֶεĸ�ֵ�����磺form.setReg_dt(DateUtil.getDTStr()); 

		//�������ݴ��� 
		copyProperties(vo, form);

		//����ҵ������update��������,�����б��� 
		bo.update(vo, user);

		//�������ݱ����� 
		request.setAttribute("success", "y");

		//ִ��ҳ����ת 
		return mapping.findForward("edit");
	}

	/** 
	 * ɾ������ 
	 */
	public ActionForward delete(
		ActionMapping mapping,
		BaseForm form,
		HttpServletRequest request,
		HttpServletResponse response,
		UserContext user)
		throws Exception {

			//����Ȩ��У��
			if (!OpMap.checkRoleAuth(user.getRoleID(), "dicType_d")) {
				BaseException e = new BaseException();
				e.setErrorCode("E020001");
				throw e;
			}
			
		//���ҵ����� 
		BaseBO bo = (BaseBO) getBean("dictype_bo");

		DicTypeVO vo = new DicTypeVO();

		//���ɾ����¼���� 
		vo.setType_id(((DicTypeForm) form).getType_id());

		//����ҵ������delete��������,ִ������ɾ�� 
		bo.delete(vo, user);

		//ִ��ҳ����ת 
		return forwardSuccessPage(
			request,
			mapping,
			"����ɾ���ɹ���",
			"DicType.do?act=list");
	}

	/** 
	 * ��ѯ��ϸ��Ϣ 
	 */
	public ActionForward retrieve(
		ActionMapping mapping,
		BaseForm form,
		HttpServletRequest request,
		HttpServletResponse response,
		UserContext user)
		throws Exception {

		//���ҵ����� 
		BaseBO bo = (BaseBO) getBean("dictype_bo");

		DicTypeVO vo = new DicTypeVO();

		//��ò�ѯ���� 
		vo.setType_id(((DicTypeForm) form).getType_id());

		//����ҵ������retrieve��������,��ѯ��ϸ��Ϣ 
		vo = (DicTypeVO) bo.retrieve(vo, user);

		//���������д��form������ 
		copyProperties(form, vo);
		//ִ��ҳ����ת,���ص��б�ҳ�� 
		return mapping.findForward("view");
	}
	/** 
	 * ɾ������ 
	 */
	public ActionForward deleteM(
		ActionMapping mapping,
		BaseForm form,
		HttpServletRequest request,
		HttpServletResponse response,
		UserContext user)
		throws Exception {

			//����Ȩ��У��
			if (!OpMap.checkRoleAuth(user.getRoleID(), "dicType_d")) {
				BaseException e = new BaseException();
				e.setErrorCode("E020001");
				throw e;
			}
			
		//���ҵ����� 
		DicTypeBO bo = (DicTypeBO) getBean("dictype_bo");

		DicTypeVO vo = new DicTypeVO();

		//���ɾ����¼���� 
		vo.setType_id(((DicTypeForm) form).getType_id());

		//����ҵ������delete��������,ִ������ɾ�� 
		bo.deleteM(vo, user);

		//ִ��ҳ����ת 
		return forwardSuccessPage(
			request,
			mapping,
			"����ɾ���ɹ���",
			"DicType.do?act=list");
	}

}
