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
 * ˵���������޸Ŀ��Ʋ�ʵ����
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

		if (act.equals("u")) { //�޸� 

			String step = request.getParameter("step");

			if (null == step) { //��ʼ���׶Σ���ѯ��ϸ��Ϣ����ת���޸�ҳ�� 

				return editInfo(mapping, form, request, response, user);

			} else //�û����ύ�޸ĺ�����ݣ�ִ�����ݱ��� 

				return update(mapping, form, request, response, user);
		} else
			//return null;
			return mapping.findForward("edit");
	}

	/** 
	 * ���޸�ҳ�� 
	 */
	public ActionForward editInfo(
		ActionMapping mapping,
		BaseForm form,
		HttpServletRequest request,
		HttpServletResponse response,
		UserContext user)
		throws Exception {


		//���õ��÷�ʽ�Ự����modify_manner��Ϊ�Ӳ˵�����
		request.getSession().setAttribute("modify_manner", "menu");

		//ִ��ҳ����ת,���޸�ҳ�� 
		return mapping.findForward("edit");
	}

	/** 
	 * �����û��������� 
	 */
	public ActionForward update(
		ActionMapping mapping,
		BaseForm form,
		HttpServletRequest request,
		HttpServletResponse response,
		UserContext user)
		throws Exception {

		//����޸�����ҵ�����PasswordBO
		PasswordBO bo = (PasswordBO) getBean("password_bo");
		
		//����޸�����ҵ�����PasswordBO
		PasswordVO vo = new PasswordVO();		
		
		
		//��form��ֵ�����PasswordVO 
		copyProperties(vo, form);

		
		//�ӡ�user_id���Ự�����л��ֵ,������VO��user_idֵ
		//˵������ֵ��LoginAction�У����û�����ΪĬ������ʱ��������Ϊuser_id
		vo.setUser_id((String)request.getSession().getAttribute("user_id"));		
		
		
		if(null == vo.getUser_id() ){			
			//���ֵΪ�գ�˵����Ĭ����������޸ĵ����
			//�����޸�����Ự��־����"modify_manner"Ϊ"done"
			//���û������Ķ����л�ȡuser_id,������VO
						
			vo.setUser_id(user.getUserID());
			request.getSession().setAttribute("modify_manner","done");
		
		}				
		
		
		
		//����ҵ�����BO��updatePwd(vo, user)����,�������� 
		if(bo.updatePwd(vo, user) <= 0){
			//����ʧ�ܣ���ʧ��ҳ��
			return mapping.findForward("fail");
		}	
		
		
		//���³ɹ������ɹ�ҳ��
		request.getSession().setAttribute("success", "y");

		//���Ự��־����"modify_manner"
		Object modify_manner =
			request.getSession().getAttribute("modify_manner");

		if (modify_manner == null) {   //��¼����
			//�Ƴ���user���Ự����
			request.getSession().removeAttribute("user");
			//ִ��ҳ����ת������¼ҳ�� 
			return mapping.findForward("login");

		} else {    //�˵�����
			//�Ƴ�"modify_manner"�Ự����
			//�ǼǸ������������־
			OpLog.Log(user, "03", "u", "�û���������", vo.getUser_id());	
			request.getSession().removeAttribute("modify_manner");
			//ִ��ҳ����ת�����ɹ�ҳ 
			return mapping.findForward("blank");


		}

		
	}

}