/*********************************************************
 * File: MenuAction.java
 * 
 * Version 1.0
 * 
 * Date     2005-9-14
 * 
 * Author   Chen Rong
 * 
 ********************************************************/

package com.eis.portal.menu;

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
import com.eis.portal.user.*;
import com.eis.cache.*;

/**
 * ˵����
 * 
 */
public class MenuAction extends BaseAction {

	/**
	 * 
	 */
	public MenuAction() {
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

		if (act.equals("c")) { //���Ӳ˵�

			String step = request.getParameter("step");

			if (null == step) { //��ʼ���׶Σ���ת�����Ӳ˵�ҳ��
				return mapping.findForward("new");

			} else //�û����ύ�˵����ݣ�ִ�����ݱ���
				return add(mapping, form, request, response, user);
		} else if (act.equals("u")) { //�޸Ĳ˵�

			String step = request.getParameter("step");
			
			if (null == step) { //��ʼ���׶Σ���ѯ�˵���Ϣ����ת���˵��޸�ҳ��
				
				return editInfo(mapping, form, request, response, user);
				
			} else //�û����ύ�˵����ݣ�ִ�����ݱ���
			
				return update(mapping, form, request, response, user);
				
		} else if (act.equals("r")) { //��ѯ�˵���ϸ��Ϣ			

			return retrieve(mapping, form, request, response, user);

		} else if (act.equals("lr")) { //��ѯ�˵���ϸ��Ϣ���˵�������Ϊ����֮һ
			
			String menu_id = request.getParameter("menu_id");							

			return retrieve(menu_id, mapping, form, request, response, user);

		} else if (act.equals("d")) { //ɾ���˵�

			return delete(mapping, form, request, response, user);

		} else if (act.equals("list")) { //���ز˵��б�

			return list(mapping, form, request, response, user);

		} else if (act.equals("listWithPerm")) { //���ذ��û��Ľ�ɫȨ�޷��ز˵��б�

			return listWithPerm(mapping, form, request, response, user);
		
		}   else if (act.equals("blk")) { //���հ���ʾҳ

			return mapping.findForward("blank");

		}   else if (act.equals("bfl")) { //���׼��˵�ҳ�������޴����¼
			//����׼��˵�ʱ��ʾ��ҳ�棬
			//����������һ���˵���ť
			return mapping.findForward("beforeFirstLevel");

		}else
			return null;
		}

	/**  ������ʾȫ���˵��б�
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @param user
	 * @return �˵��б�
	 * @throws Exception
	 */
	public ActionForward list(
		ActionMapping mapping,
		BaseForm form,
		HttpServletRequest request,
		HttpServletResponse response,
		UserContext user)
		throws Exception {			
		

		//��ò˵�����ҵ�����
		BaseBO bo = (BaseBO)getBean("menu_bo");

		//���ò˵�����ҵ������list��user������,�����б���		
		List list=bo.list(user);

		//���������д��request������		
		request.setAttribute("menuList", list);	
		

		//ִ��ҳ����ת
		return mapping.findForward("list");
	}
	
	
	/**  ���ڰ��û�Ȩ�޷��ز˵��б�����ҳ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @param user
	 * @return �˵��б�
	 * @throws Exception
	 */
	public ActionForward listWithPerm(
		ActionMapping mapping,
		BaseForm form,
		HttpServletRequest request,
		HttpServletResponse response,
		UserContext user)
		throws Exception {				

		SysLog.debug("�û�����Ϊ��"+user.getUserName());		
		SysLog.debug("�û��Ľ�ɫΪ��"+user.getRoleID());	

		//��ò˵�ҵ�����
		MenuBO bo = (MenuBO)getBean("menu_bo");			

		//����ҵ������listWithPerm��������,�����б���		
		List list=bo.listWithPerm(user);		
			

		//���������д��request������		
		request.setAttribute("menuListPerm", list);			
		

		//ִ��ҳ����ת
		return mapping.findForward("listWithPerm");
	}



	/**��ѯ�˵���ϸ��Ϣ
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
		BaseBO bo = (BaseBO)getBean("menu_bo");
		
		MenuForm bean = (MenuForm) form;

		MenuVO vo = new MenuVO();

		vo.setMenu_id(bean.getMenu_id());		
		

		//����ҵ������retrieve��������,��ѯ��ϸ��Ϣ
		vo = (MenuVO) bo.retrieve(vo, user);

		//���������д��form������
		copyProperties(bean,vo);

		//ִ��ҳ����ת
		return mapping.findForward("view");
	}
	
	
	/**��ѯ�˵���ϸ��Ϣ���˵�������Ϊ����֮һ
		 * 
		 * @param menu_id �˵�����
		 * @param mapping
		 * @param form
		 * @param request
		 * @param response
		 * @param user �û������Ϣ
		 * @return
		 * @throws Exception
		 */
		public ActionForward retrieve(
			String menu_id,
			ActionMapping mapping,
			BaseForm form,
			HttpServletRequest request,
			HttpServletResponse response,
			UserContext user)
			throws Exception {				


			//��ò˵�����ҵ�����
			BaseBO bo = (BaseBO)getBean("menu_bo");
			
			//��ò˵��������ݴ������
			BaseVO vo = new MenuVO();				
			
			String whereclause=" where a.MENU_ID='"+menu_id+"'";
	

			//���ò˵�����ҵ������retrieve��������,������ϸ��Ϣ��BaseVO����
			vo =  bo.retrieve(whereclause, user);
			
			//�����˵�����Form����MenuForm
			MenuForm bean = (MenuForm) form;

			//�����BaseVO����д��form������
			copyProperties(bean,vo);
			
			//����ˢ�����˵���־		
			//request.getSession().setAttribute("refresh", "y");
			

			//ִ��ҳ����ת
			return mapping.findForward("view");
		}
	
	

	/**���Ӳ˵�
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
		if (!OpMap.checkRoleAuth(user.getRoleID(), "menu_c")) {

			BaseException e = new BaseException();
			e.setErrorCode("E020001");
			throw e;
		}
		
		//���ҵ�����
		MenuBO bo = (MenuBO)getBean("menu_bo");
		
		MenuForm bean = (MenuForm) form;

		MenuVO vo = new MenuVO();		
		
		//�������ݴ���
		copyProperties(vo,bean);
		
		//��ҳ�ڵ�������Ϊ0����ǰ�ݲ����书�ܣ�һ������Ϊ0
		vo.setMenu_mark("0");	
		
		//���������˵��Ĳ˵�����
		//����MENU_ID�����ֵ
		String maxKey=bo.retrieveMax(user);	
		
		String nextKey=null;
		
		if(maxKey=="0"||maxKey==null||maxKey.equals(" ")){
			
			nextKey="00000011";
			
		}else{
			
			//��ȡҪ�����˵���MENU_IDֵ
			 nextKey=KeyGenerator.getNextKeyStr(maxKey,8);
			
		}		
		
		vo.setMenu_id(nextKey);

		//����ҵ������add��������		
		bo.add(vo, user);
		
		
		//����ˢ�����˵���־		
		request.getSession().setAttribute("refresh", "y");

		//��ȡ�����˵�����
		String menu_id = vo.getMenu_id();
		//��ʾ�����˵��Ĳ鿴��ϸ��Ϣҳ��
		return retrieve(menu_id, mapping, form, request, response, user);

	}
	

	/**�޸Ĳ˵�����ʾ������ѡ�˵���Ϣ���޸�ҳ�棬
	 * �ύ�޸�֮ǰ����
	 * */
	public ActionForward editInfo(
		ActionMapping mapping,
		BaseForm form,
		HttpServletRequest request,
		HttpServletResponse response,
		UserContext user)
		throws Exception {

		//	  ���ҵ�����
		BaseBO bo = (BaseBO)getBean("menu_bo");
		
		MenuForm bean = (MenuForm) form;

		MenuVO vo = new MenuVO();

		vo.setMenu_id(bean.getMenu_id());

		//����ҵ������retrieve��������,��ѯ��ϸ��Ϣ
		vo = (MenuVO) bo.retrieve(vo, user);

		//���������д��form������		
		copyProperties(bean,vo);
	

		//	  ִ��ҳ����ת
		return mapping.findForward("edit");
	}
	

	/**�޸Ĳ˵����ύ�޸ĺ����
	 * */
	public ActionForward update(
		ActionMapping mapping,
		BaseForm form,
		HttpServletRequest request,
		HttpServletResponse response,
		UserContext user)
		throws Exception {
			
		//����Ȩ��У��
		if (!OpMap.checkRoleAuth(user.getRoleID(), "menu_u")) {

			BaseException e = new BaseException();
			e.setErrorCode("E020001");
			throw e;
		}

		//���ҵ�����
		BaseBO bo = (BaseBO)getBean("menu_bo");

		MenuForm bean = (MenuForm) form;
		MenuVO vo = new MenuVO();

		//�������ݴ���
		copyProperties(vo,bean);	
		
		
		
		//vo.setReg_dt(DateUtil.getDTStr());
		//vo.setUser_id("0001");
		vo.setMenu_mark("0");	

		//����ҵ������update��������
		bo.update(vo, user);		


		//�������ݱ�����
		request.getSession().setAttribute("success", "y");		
		
		//����ˢ�����˵���־		
		request.getSession().setAttribute("refresh", "y");
		
		//ִ��ҳ����ת		
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
		if (!OpMap.checkRoleAuth(user.getRoleID(), "menu_d")) {

			BaseException e = new BaseException();
			e.setErrorCode("E020001");
			throw e;
		}

		//���ҵ�����
		BaseBO bo = (BaseBO)getBean("menu_bo");
		
		
		MenuForm bean = (MenuForm) form;

		MenuVO vo = new MenuVO();

		vo.setMenu_id(bean.getMenu_id());

		//����ҵ������delete��������,ִ������ɾ��
		bo.delete(vo, user);
		
		//����ˢ�����˵���־		
		request.getSession().setAttribute("refresh", "y");

		//ִ��ҳ����ת,���ص��б�ҳ��
		
		return forwardSuccessPage(request,mapping,"����ɾ���ɹ���","Menu.do?act=blk");
		
	}	
	

}
