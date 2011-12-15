/*********************************************************
 * File: UserAction.java
 * 
 * Version 1.0
 * 
 * Date     2005-9-27
 * 
 * Author   �� ��
 * 
 ********************************************************/
package com.eis.portal.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import java.util.*;
import com.eis.base.*;
import com.eis.portal.UserContext;
import com.eis.util.*;
import com.eis.key.*;
import com.eis.cache.*;

/** 
 * ˵�����û��б�Ŀ����� 
 */
public class UserAction extends BaseAction {

	/** 
	 * ���캯�� 
	 */
	public UserAction() {
		super();
	}

	/** 
	 * ִ�������� 
	 */
	public ActionForward process(ActionMapping mapping, BaseForm form, HttpServletRequest request, HttpServletResponse response, UserContext user) throws Exception {
		String act = form.getAct();
		if (act.equals("c")) { //���� 

			String step = request.getParameter("step");

			if (null == step) { //��ʼ���׶Σ���ת������ҳ�� 

				return beforeAdd(mapping, form, request, response, user);

			} else //�û����ύ�������ݣ�ִ�����ݱ��� 

				return add(mapping, form, request, response, user);

		} else if (act.equals("check")) { //�����û�ʱ������û���¼��������ظ�

			return checkLoginId(mapping, form, request, response, user);

		} else if (act.equals("u")) { //�޸� 

			String step = request.getParameter("step");

			if (null == step) { //��ʼ���׶Σ���ѯ��ϸ��Ϣ����ת���޸�ҳ�� 

				return editInfo(mapping, form, request, response, user);

			} else //�û����ύ�޸ĺ�����ݣ�ִ�����ݱ��� 

				return update(mapping, form, request, response, user);

		} else if (act.equals("r")) { //��ѯ��ϸ��Ϣ 

			return retrieve(mapping, form, request, response, user);

		} else if (act.equals("r1")) { //��ѯ��ϸ��Ϣ 

			return retrieve1(mapping, form, request, response, user);

		} else if (act.equals("t")) { //�ϳ��û���Ϣ

			return terminate(mapping, form, request, response, user);

		} else if (act.equals("able")) { //�����û���Ϣ

			return able(mapping, form, request, response, user);

		} else if (act.equals("disable")) { //ͣ���û���Ϣ

			return disable(mapping, form, request, response, user);

		} else if (act.equals("d")) { //ɾ���û� 

			return delete(mapping, form, request, response, user);

		} else if (act.equals("list")) { //����ά���б� 

			return list(mapping, form, request, response, user);

		} else if (act.equals("list1")) { //����ά���б� 

			return list1(mapping, form, request, response, user);

		} else if (act.equals("ql")) { //��ѯ�б� 

			return queryList(mapping, form, request, response, user);

		} else if (act.equals("query1")) { //��ѯ�б� 

			return queryList1(mapping, form, request, response, user);

		} else if (act.equals("query2")) { //��ѯ�б� 

			return queryList2(mapping, form, request, response, user);

		} else if (act.equals("listpop")) { //��ѯ�б� 

			return listpop(mapping, form, request, response, user);
		} else if (act.equalsIgnoreCase("assignoperlist")) {

			return assignOperList(mapping, form, request, response, user);
		} else if (act.equals("setpwd")) { //�������� 

			return setPwd(mapping, form, request, response, user);

		} else if("exclude".equalsIgnoreCase(act)){
			return exclude(mapping, form, request, response, user);
		}
			return null;
	}

	/** 
	 * ��ѯά���б� 
	 */
	public ActionForward list(ActionMapping mapping, BaseForm form, HttpServletRequest request, HttpServletResponse response, UserContext user) throws Exception {

		PageObject page = new PageObject();
		String pageNo = request.getParameter("pageNO");

		String requery = request.getParameter("requery");

		String orgID = null;

		if (pageNo == null || (requery != null && requery.trim().equals("y"))) {
			//Ĭ�ϲ�ѯ			
			page.setCurPage(1);

		} else { //���²�ѯ
			page.setCurPage(Integer.parseInt(pageNo));
		}

		//�����һ�ν���˵������û���������Ự��Ϣ
		String init = request.getParameter("init");

		if (init != null && init.trim().length() > 0) {
			//�������Ŵ�ŵ��Ự��
			orgID = user.getOrgID();
			request.getSession().setAttribute("org_id", orgID);

		} else {
			orgID = (String) request.getSession().getAttribute("org_id");

		}

		if (requery != null && requery.trim().equals("y")) { //���²�ѯ

			//�������Ŵ�ŵ��Ự��
			orgID = request.getParameter("amsd_store_f");
			request.getSession().setAttribute("org_id", orgID);
		}

		//����Ƿ�����ӻ��޸�ҳ�淵�ص��б�
		String current_org = (String) request.getSession().getAttribute("user_org");
		if (null != current_org && current_org.trim().length() > 0) {
			request.getSession().setAttribute("org_id", current_org);
			request.getSession().removeAttribute("user_org");
			orgID = current_org;
		}

		//��ӻ�����ѯ�������� 
		page.addFilter("amsd_store_f", request.getSession().getAttribute("org_id"));

		//����û�������ѯ�������� 
		page.addFilter("user_name_f", request.getParameter("user_name_f"));

		//����û�״̬��ѯ�������� 
		page.addFilter("status_f", request.getParameter("status_f"));

		//���ҵ����� 
		BaseBO bo = (BaseBO) getBean("user_bo");

		//����ҵ������list��������,�����б��� 
		bo.list(page, user);

		//���������д��request������ 
		request.setAttribute("pageResult", page);

		((UserForm) form).setAmsd_store_f(orgID);

		//ִ��ҳ����ת 
		return mapping.findForward("list");
	}
	
	/**
	 * ��������ѡ��ָ��Ա
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public ActionForward assignOperList(ActionMapping mapping, BaseForm form, HttpServletRequest request, HttpServletResponse response, UserContext user) throws Exception {

		PageObject page = new PageObject();
		String pageNo = request.getParameter("pageNO");

		String requery = request.getParameter("requery");

		if (pageNo == null || (requery != null && requery.trim().equals("y"))) {
			page.setCurPage(1);
		} else {
			page.setCurPage(Integer.parseInt(pageNo));
		}
		
		page.addFilter("user_name_f", request.getParameter("user_name_f"));
		//String amsdStore = request.getParameter("currAmsdStore");
		String amsdStore=((UserForm)form).getCurrAmsdStore();
		
		if(amsdStore == null||amsdStore.equalsIgnoreCase("")){
			amsdStore=user.getOrgID();
		}
	
		//���ҵ����� 
		UserBO bo = (UserBO) getBean("user_bo");

		//����ҵ������list��������,�����б��� 
		bo.queryAssignOperList(page, user,amsdStore);

		request.setAttribute("pageResult", page);

		//ִ��ҳ����ת 
		return mapping.findForward("assignoperlistpop");
	}

	public ActionForward list1(ActionMapping mapping, BaseForm form, HttpServletRequest request, HttpServletResponse response, UserContext user) throws Exception {

		PageObject page = new PageObject();
		String pageNo = request.getParameter("pageNO");

		String requery = request.getParameter("requery");

		if (pageNo == null || (requery != null && requery.trim().equals("y"))) {
			page.setCurPage(1);
		} else {
			page.setCurPage(Integer.parseInt(pageNo));
		}

		//���ҵ����� 
		UserBO bo = (UserBO) getBean("user_bo");

		//����ҵ������list��������,�����б��� 
		bo.list2(page, user);

		//���������д��request������ 

		request.setAttribute("pageResult", page);

		//ִ��ҳ����ת 
		return mapping.findForward("list1");
	}

	/** 
	 * ��ѯ�б� 
	 */
	public ActionForward queryList(ActionMapping mapping, BaseForm form, HttpServletRequest request, HttpServletResponse response, UserContext user) throws Exception {

		PageObject page = new PageObject();
		String pageNo = request.getParameter("pageNO");

		String requery = request.getParameter("requery");

		if (pageNo == null || (requery != null && requery.trim().equals("y"))) {
			page.setCurPage(1);
		} else {
			page.setCurPage(Integer.parseInt(pageNo));
		}

		//���ҵ����� 
		BaseBO bo = (BaseBO) getBean("user_bo");

		//����ҵ������list��������,�����б��� 
		bo.list(page, user);

		//���������д��request������ 
		request.setAttribute("pageResult", page);

		//ִ��ҳ����ת 
		return mapping.findForward("querylist");
	}
	public ActionForward queryList1(ActionMapping mapping, BaseForm form, HttpServletRequest request, HttpServletResponse response, UserContext user) throws Exception {

		PageObject page = new PageObject();
		String pageNo = request.getParameter("pageNO");

		String requery = request.getParameter("requery");

		if (pageNo == null || pageNo.trim().length() == 0) {
			page.setCurPage(1);
		} else {
			page.setCurPage(Integer.parseInt(pageNo));
		}
		//�ڴ˴�ͨ��page.addFilter()������ӹ������� 
		page.addFilter("amsd_store_f", request.getParameter("amsd_store_f"));

		//���ҵ����� 
		UserBO bo = (UserBO) getBean("user_bo");

		//����ҵ������list��������,�����б��� 
		bo.queryList1(page, user);

		//���������д��request������ 
		request.setAttribute("pageResult", page);

		//ִ��ҳ����ת 
		return mapping.findForward("list1");

	}
	public ActionForward queryList2(ActionMapping mapping, BaseForm form, HttpServletRequest request, HttpServletResponse response, UserContext user) throws Exception {

		PageObject page = new PageObject();
		String pageNo = request.getParameter("pageNO");

		String requery = request.getParameter("requery");

		if (pageNo == null || pageNo.trim().length() == 0) {
			page.setCurPage(1);
		} else {
			page.setCurPage(Integer.parseInt(pageNo));
		}
		//�ڴ˴�ͨ��page.addFilter()������ӹ������� 

		page.addFilter("cre_branch_f", request.getParameter("cre_branch_f"));
		System.out.println(request.getParameter("cre_branch_f"));

		//���ҵ����� 
		UserBO bo = (UserBO) getBean("user_bo");

		//����ҵ������list��������,�����б��� 
		bo.queryList(page, user);

		//���������д��request������ 
		request.setAttribute("pageResult", page);

		//ִ��ҳ����ת 
		return mapping.findForward("list1");

	}
	public ActionForward beforeAdd(ActionMapping mapping, BaseForm form, HttpServletRequest request, HttpServletResponse response, UserContext user) throws Exception {
		//���ҵ����� 
		UserBO bo = (UserBO) getBean("user_bo");

		//����û�����
		//String user_id = request.getParameter("user_id");

		String orgID = (String) request.getSession().getAttribute("org_id");

		//����û�����ѡ��ûѡ��Ľ�ɫȨ�޼���
		List rolesNotSelected = bo.listRolesNotSelected(null, user, orgID);
		
		//������д���Ա��ɫ���û�ID
		List collRoles = bo.getCollRoles(user);

		//���������д��request������
		request.setAttribute("rolesNotSelected", rolesNotSelected);
		request.setAttribute("collRoles", collRoles);

		((UserForm) form).setAmsd_store(orgID);

		//ִ��ҳ����ת,���ص��б�ҳ�� 
		return mapping.findForward("new");
	}

	/** 
	 * �����û����� 
	 */
	public ActionForward add(ActionMapping mapping, BaseForm form, HttpServletRequest request, HttpServletResponse response, UserContext user) throws Exception {

		//����Ȩ��У��
		if (!OpMap.checkRoleAuth(user.getRoleID(), "user_c")) {

			BaseException e = new BaseException();
			e.setErrorCode("E020001");
			throw e;
		}

		//���ҵ����� 
		UserBO bo = (UserBO) getBean("user_bo");

		UserForm bean = (UserForm) form;

		/**��ȡ�����û���������������vo */

		UserVO vo = new UserVO();

		//�������ݴ��� 
		copyProperties(vo, bean);

		/**����û���½��������ظ� 
		 **/

		String hasLoginId = bo.retrieveLoginId(vo.getLogin_id(), user);
		SysLog.debug("��ѯ�����û�login_idΪ:" + hasLoginId);

		//����û������Ѵ����������½��ŵ��û�,��ʾ����ظ�����,��������ҳ��
		if (!hasLoginId.equals("0")) {
			return forwardError(request, mapping, "��½����ظ�,����������������ţ�");
		}

		/**�����û����룬�Զ���ȡ�û����룬Ϊ�û�����user_id���ֵ����
		 */

		//��ȡUSER_ID���ֵ
		String maxKey = bo.retrieveMax(user);
		SysLog.debug("���user_idΪ��" + maxKey);
		String nextKey = null;

		if (maxKey == null || maxKey.trim().equals("0") || maxKey.trim().length() < 1) {

			nextKey = "00000001";

		} else {

			//��ȡҪ�����û���USER_IDֵ
			nextKey = KeyGenerator.getNextKeyStr(maxKey, 8);
			SysLog.debug("���user_id+1��ֵΪ��" + nextKey);

		}
		//�����û�����
		vo.setUser_id(nextKey);

		/**���Ŵ�����ʱ��Ϊ01*/
		vo.setDepart_id("01");

		/**��������ɫ����
		 * ����û�δѡ������ɫ����ѡ��Ľ�ɫ�����еĵ�һ������Ϊ����ɫ
		 */

		//����Ƿ�����ѡ����һ����ɫ
		String selectRoles[] = request.getParameterValues("rolesRight");

		if (selectRoles == null || selectRoles.length < 1) {
			//���δ���û������κν�ɫ,��ʾ����,�������������û�
			return forwardError(request, mapping, "�����ٸ��û�����һ����ɫ��");

		}

		/**����Ƿ���ȷ������������ɫ���� 
		 */

		//��ȡ���е�����ɫ����
		String role_id = ((UserForm) form).getRole_id();

		if ((role_id == null) || (role_id.trim()).equals("")) {
			//���δ��������ɫ
			return forwardError(request, mapping, "������ѡ��ɫ��ѡ��һ������Ϊ����ɫ��");

		} else {
			//������������ɫ��������������ɫ�Ƿ���ѡ��Ľ�ɫ������

			/**
			 * �������ɫ�Ƿ�����ѡ��ɫ�б�ʶ
			 * 0-��ʾ������ѡ��ɫ�У�1-��ʾ������
			 */
			int check = 0;
			for (int i = 0; i < selectRoles.length; i++) {

				//�������ɫ�Ƿ�����ѡ��ɫ�У������,check=1
				if (role_id.equals(selectRoles[i])) {
					check = 1;
				}
			}
			if (check == 0) {
				//�������ɫ������ѡ��ɫ�У���ʾ������Ϣ������ҳ��
				return forwardError(request, mapping, "����ɫ������ѡ��ɫ�У�������ѡ��ɫ����һ����Ϊ����ɫ��");
			}

		}

		/**�����û�����ΪĬ��ֵ"00000000" */
		vo.setPassword(MD5Util.digest("00000000"));

		/**�����û�״̬Ϊ"1"������	*/
		vo.setStatus("1");

		/**���ý�������Ϊ����*/
		vo.setReg_dt(DateUtil.getDTStr());

		/**��������ʧЧ����Ϊ����*/
		vo.setModify_dt(DateUtil.getDTStr());

		/**���ø�������Ϊ����*/
		vo.setSt_chg_dt(DateUtil.getDTStr());

		/**���ù���Ա���� */
		vo.setAdmin_id(user.getUserID());

		/**��ȡ�����û���ɫ��ϵ������������б�rolesSelectList */

		//��ȡ�û�����user_id
		String user_id = vo.getUser_id();

		List list = new ArrayList();

		//���ύ��rolesRightԪ�ػ��Ϊ�û�ѡ��Ľ�ɫ����
		//xinyong ɾ������һ�д���,��ǰ���Ѿ���������ͬ���ݵĸñ���
		//String selectRoles[] = request.getParameterValues("rolesRight");

		if (selectRoles != null) {
			//������û����ٷ���һ����ɫ�������list������
			for (int i = 0; i < selectRoles.length; i++) {
				list.add(selectRoles[i]);
			}
		}

		//����ҵ������add��������,ͬʱ���û��������û���ɫ��ϵ�������Ӽ�¼ 
		bo.add(user_id, vo, list, user);

		request.getSession().setAttribute("user_org", vo.getAmsd_store());

		//ִ��ҳ����ת,���ص��б�ҳ�� 
		return list(mapping, form, request, response, user);

	}

	/** 
	 * �����û�ʱ������û���¼��������ظ� 
	 */

	public ActionForward checkLoginId(ActionMapping mapping, BaseForm form, HttpServletRequest request, HttpServletResponse response, UserContext user) throws Exception {

		//���ҵ����� 
		UserBO bo = (UserBO) getBean("user_bo");

		UserForm bean = (UserForm) form;

		/**��ȡ�����û���������������vo */

		UserVO vo = new UserVO();

		//�������ݴ��� 
		copyProperties(vo, bean);

		/**����û���½��������ظ� 
		 * */
		String hasLoginId = bo.retrieveLoginId(vo.getLogin_id(), user);

		//����û������Ѵ����������½��ŵ��û�,��ʾ����ظ�����,��������ҳ��
		if (!hasLoginId.equals("0")) {
			request.setAttribute("UserIdExist", "n");
			//ִ��ҳ����ת,�ص������û�ҳ��
			return beforeAdd(mapping, form, request, response, user);

		} else {
			request.setAttribute("UserIdExist", "y");
			//ִ��ҳ����ת,�ص������û�ҳ�� 
			return beforeAdd(mapping, form, request, response, user);

		}
	}

	/** 
	 * ��ѯά����Ϣ��ϸ 
	 */
	public ActionForward editInfo(ActionMapping mapping, BaseForm form, HttpServletRequest request, HttpServletResponse response, UserContext user) throws Exception {

		//���ҵ����� 
		UserBO bo = (UserBO) getBean("user_bo");

		UserVO vo = new UserVO();

		//��ò�ѯ���� 
		vo.setUser_id(((UserForm) form).getUser_id());

		//����ҵ������retrieve��������,��ѯ��ϸ��Ϣ 
		vo = (UserVO) bo.retrieve(vo, user);

		vo.setLogin_id2(vo.getLogin_id());

		//���������д��form������ 
		copyProperties(form, vo);

		//��ȡ�û�����
		String user_id = ((UserForm) form).getUser_id();
		SysLog.debug("�û����룺" + user_id);

		String orgID = (String) request.getSession().getAttribute("org_id");

		//����û�����ѡ��ûѡ��Ľ�ɫȨ�޼���
		List rolesNotSelected = bo.listRolesNotSelected(user_id, user, orgID);

		//��ý�ɫ��ѡ��Ĳ���Ȩ�޼���
		List rolesSelected = bo.listRolesSelected(user_id, user);
		
		//������д���Ա��ɫ���û�ID
		List collRoles = bo.getCollRoles(user);
		String collCheck = bo.doCollCheck(rolesSelected,collRoles);

		//���������д��request������
		request.setAttribute("rolesNotSelected", rolesNotSelected);
		request.setAttribute("rolesSelected", rolesSelected);
		request.setAttribute("collCheck",collCheck);
		request.setAttribute("collRoles", collRoles);

		((UserForm) form).setAmsd_store(orgID);

		request.getSession().setAttribute("user_org", vo.getAmsd_store());

		//ִ��ҳ����ת,��ת���޸�ҳ�� 
		return mapping.findForward("edit");
	}

	/** 
	 * �޸��û����� 
	 */
	public ActionForward update(ActionMapping mapping, BaseForm form, HttpServletRequest request, HttpServletResponse response, UserContext user) throws Exception {

		//����Ȩ��У��
		if (!OpMap.checkRoleAuth(user.getRoleID(), "user_u")) {

			BaseException e = new BaseException();
			e.setErrorCode("E020001");
			throw e;
		}

		//���ҵ����� 
		UserBO bo = (UserBO) getBean("user_bo");

		/**��ȡ�����û���������������vo */

		UserVO vo = new UserVO();

		//�������ݴ��� 
		copyProperties(vo, form);

		//����û���¼����Ƿ��ظ�
		String repeat = bo.checkLoginIdRepeat(vo, user);

		/**���ø�������Ϊ����*/
		vo.setSt_chg_dt(DateUtil.getDTStr());

		/**���ù���Ա���� */
		vo.setAdmin_id(user.getUserID());

		String user_id = request.getParameter("user_id");

		/**��ȡ�����û���ɫ��ϵ������������б�rolesSelectList*/

		//���ύ��rolesRightԪ�ػ��Ϊ�û�ѡ��Ľ�ɫ����
		String selectRoles[] = request.getParameterValues("rolesRight");

		List list = new ArrayList();

		//��ȡ����ɫ
		String role_id = ((UserForm) form).getRole_id();

		/**���ü������ɫ�Ƿ�����ѡ��ɫ�б�ʶ
		 * 0-��ʾ������ѡ��ɫ�У�1-��ʾ������
		 */
		int check = 0;

		/**����ѡ��ɫ�У�ͬʱ����¼��Ա�͸���Ա���򱨴�����ʧ��*/
		boolean ipt = false; //¼��Ա��ɫ��ǣ���Ϊture��ʶ¼��Ա
		boolean chk = false; //����Ա��ɫ��ǣ���Ϊture��ʶ����Ա
		String roleid = "";
		/***/

		//������û����ٷ���һ����ɫ�������list������
		if (selectRoles != null) {
			for (int i = 0; i < selectRoles.length; i++) {

				/**����ѡ��ɫ�У�ͬʱ����¼��Ա�͸���Ա���򱨴�����ʧ��*/
				roleid = bo.getLogicIdByRoleId(selectRoles[i], user);
				if (roleid != null && roleid.trim().length() > 0) {
					if (roleid.trim().equals("check")) {
						chk = true;
					}
					if (roleid.trim().equals("input")) {
						ipt = true;
					}
				}
				/***/

				//������û����ٷ���һ����ɫ�������list������
				list.add(selectRoles[i]);

				//�������ɫ�Ƿ�����ѡ��ɫ�У������,check=1
				if (role_id.equals(selectRoles[i])) {
					check = 1;
				}
			}
		}

		/**����ѡ��ɫ�У�ͬʱ����¼��Ա�͸���Ա���򱨴�����ʧ��*/
		if (ipt && chk) {
			return forwardError(request, mapping, "��ѡ��ɫ�в���ͬʱ����¼��Ա�͸���Ա��ɫ��");
		}
		/***/

		request.getSession().setAttribute("user_org", vo.getAmsd_store());

		if (check == 0) {
			//�������ɫ������ѡ��ɫ�У���ʾ������Ϣ������
			return forwardError(request, mapping, "������ѡ��ɫ��ѡ������ɫ��");
		} else {
			//�������ɫ����ѡ��ɫ��

			//����ҵ������update��������,ͬʱ�����û��������û���ɫ��ϵ������ 
			bo.update(user_id, vo, list, user);

			SysLog.debug(repeat);

			//�������ݱ�����
			if (repeat.equals("n")) { //�����¼��Ų��ظ�
				request.setAttribute("success", "y");
			} else {
				request.setAttribute("success", "n");
			}

			//ִ��ҳ����ת 		
			return editInfo(mapping, form, request, response, user);

		}

	}

	/** 
	 * ɾ���û����� 
	 */
	public ActionForward delete(ActionMapping mapping, BaseForm form, HttpServletRequest request, HttpServletResponse response, UserContext user) throws Exception {

		//����Ȩ��У��
		if (!OpMap.checkRoleAuth(user.getRoleID(), "user_d")) {

			BaseException e = new BaseException();
			e.setErrorCode("E020001");
			throw e;
		}

		//���ҵ����� 
		BaseBO bo = (BaseBO) getBean("user_bo");

		UserVO vo = new UserVO();

		//���ɾ����¼���� 
		vo.setUser_id(((UserForm) form).getUser_id());

		//����ҵ������retrieve��������,��ȡ�û�״̬
		vo = (UserVO) bo.retrieve(vo, user);

		request.getSession().setAttribute("user_org", vo.getAmsd_store());

		if (!(vo.getStatus()).equals("0")) {
			//����û�δ����,��ʾ����ɾ��,���������û�,�����б�
			return forwardSuccessPage(request, mapping, "����ɾ���û�,���������û���", "User.do?act=list");

		} else {
			//����,ɾ���û�,Ȼ�󷵻��б�			
			//����ҵ������delete��������,ִ������ɾ�� 
			bo.delete(vo, user);

			//�������ݱ�����
			//request.getSession().setAttribute("success", "y");

			//ִ��ҳ����ת		
			return forwardSuccessPage(request, mapping, "����ɾ���ɹ���", "User.do?act=list");
		}
	}

	/** 
	 * ��ѯ��ϸ��Ϣ 
	 */
	public ActionForward retrieve(ActionMapping mapping, BaseForm form, HttpServletRequest request, HttpServletResponse response, UserContext user) throws Exception {

		//���ҵ����� 
		UserBO bo = (UserBO) getBean("user_bo");

		UserVO vo = new UserVO();

		//��ò�ѯ���� 
		vo.setUser_id(((UserForm) form).getUser_id());

		//����ҵ������retrieve��������,��ѯ��ϸ��Ϣ 
		vo = (UserVO) bo.retrieve(vo, user);

		//���������д��form������ 
		copyProperties(form, vo);

		//ִ��ҳ����ת,���ص��б�ҳ�� 
		return mapping.findForward("view");
	}
	public ActionForward retrieve1(ActionMapping mapping, BaseForm form, HttpServletRequest request, HttpServletResponse response, UserContext user) throws Exception {

		//���ҵ����� 
		UserBO bo = (UserBO) getBean("user_bo");

		UserVO vo = new UserVO();

		//��ò�ѯ���� 
		vo.setUser_id(((UserForm) form).getUser_id());

		//����ҵ������retrieve��������,��ѯ��ϸ��Ϣ 
		vo = bo.retrieve1(vo, user);

		//���������д��form������ 
		copyProperties(form, vo);

		//ִ��ҳ����ת,���ص��б�ҳ�� 
		return mapping.findForward("view");
	}

	/** 
	 * �ϳ��û� 
	 */

	public ActionForward terminate(ActionMapping mapping, BaseForm form, HttpServletRequest request, HttpServletResponse response, UserContext user) throws Exception {

		//����Ȩ��У��
		if (!OpMap.checkRoleAuth(user.getRoleID(), "user_terminate")) {

			BaseException e = new BaseException();
			e.setErrorCode("E020001");
			throw e;
		}

		// ���ҵ�����
		UserBO bo = (UserBO) getBean("user_bo");

		UserForm bean = (UserForm) form;

		UserVO vo = new UserVO();

		vo.setUser_id(bean.getUser_id());

		//����ҵ������retrieve��������,��ȡ�û�״̬
		vo = (UserVO) bo.retrieve(vo, user);
		request.getSession().setAttribute("user_org", vo.getAmsd_store());

		if ((vo.getStatus()).equals("0")) {
			//����û��Ѿ�����,��ʾ,�����б�
			return forwardSuccessPage(request, mapping, "�û������ϣ�", "User.do?act=list");

		} else if ((vo.getStatus()).equals("1")) {
			//����û�δͣ��,��ʾ��������,����ͣ��,�����б�
			return forwardSuccessPage(request, mapping, "����ͣ�ô��û�,�ٽ��������û�������", "User.do?act=list");
		} else {
			//����,�����û�,Ȼ�󷵻��б�

			/**���ø�������Ϊ����*/
			vo.setSt_chg_dt(DateUtil.getDTStr());
			SysLog.debug("��������Ϊ��" + vo.getSt_chg_dt());

			request.getSession().setAttribute("user_org", vo.getAmsd_store());

			/**���ù���Ա���� */
			vo.setAdmin_id(user.getUserID());

			//����ҵ������terminate��������
			bo.terminate(vo, user);

			//�������ݱ�����
			//request.getSession().setAttribute("success", "y");

			//ִ��ҳ����ת		
			return forwardSuccessPage(request, mapping, "�û����ϳɹ���", "User.do?act=list");

		}
	}

	/** 
	 * �����û� 
	 */

	public ActionForward able(ActionMapping mapping, BaseForm form, HttpServletRequest request, HttpServletResponse response, UserContext user) throws Exception {

		//����Ȩ��У��
		if (!OpMap.checkRoleAuth(user.getRoleID(), "user_enable")) {

			BaseException e = new BaseException();
			e.setErrorCode("E020001");
			throw e;
		}

		// ���ҵ�����
		UserBO bo = (UserBO) getBean("user_bo");

		UserForm bean = (UserForm) form;

		UserVO vo = new UserVO();

		vo.setUser_id(bean.getUser_id());

		//����ҵ������retrieve��������,��ȡ�û�״̬
		vo = (UserVO) bo.retrieve(vo, user);

		request.getSession().setAttribute("user_org", vo.getAmsd_store());

		if ((vo.getStatus()).equals("1")) {
			//����û��Ѿ�����,��ʾ,�����б�
			return forwardSuccessPage(request, mapping, "�û������ã�", "User.do?act=list");

		} else if ((vo.getStatus()).equals("0")) {
			//����û�������,��ʾ����������,�����б�
			return forwardSuccessPage(request, mapping, "�û�������,���������ã�", "User.do?act=list");
		} else {
			//����,�����û�,Ȼ�󷵻��б�

			/**���ø�������Ϊ����*/
			vo.setSt_chg_dt(DateUtil.getDTStr());
			SysLog.debug("���ø�������Ϊ��" + vo.getSt_chg_dt());

			/**���ù���Ա���� */
			vo.setAdmin_id(user.getUserID());

			//����ҵ������able��������
			bo.able(vo, user);

			//�������ݱ�����
			//request.getSession().setAttribute("success", "y");

			//ִ��ҳ����ת		
			return forwardSuccessPage(request, mapping, "�û����óɹ���", "User.do?act=list");

		}

	}

	/** 
	 * ͣ���û� 
	 */

	public ActionForward disable(ActionMapping mapping, BaseForm form, HttpServletRequest request, HttpServletResponse response, UserContext user) throws Exception {

		//����Ȩ��У��
		if (!OpMap.checkRoleAuth(user.getRoleID(), "user_disable")) {

			BaseException e = new BaseException();
			e.setErrorCode("E020001");
			throw e;
		}

		// ���ҵ�����
		UserBO bo = (UserBO) getBean("user_bo");

		UserForm bean = (UserForm) form;

		UserVO vo = new UserVO();

		vo.setUser_id(bean.getUser_id());

		//����ҵ������retrieve��������,��ȡ�û�״̬
		vo = (UserVO) bo.retrieve(vo, user);

		request.getSession().setAttribute("user_org", vo.getAmsd_store());

		if ((vo.getStatus()).equals("2")) {
			//����û��Ѿ�ͣ��,��ʾ,�����б�
			return forwardSuccessPage(request, mapping, "�û���ͣ�ã�", "User.do?act=list");

		} else if ((vo.getStatus()).equals("0")) {
			//����û�������,��ʾ������ͣ��,�����б�
			return forwardSuccessPage(request, mapping, "�û�������,������ͣ�ã�", "User.do?act=list");
		} else {
			//����,ͣ���û�,Ȼ�󷵻��б�

			/**���ø�������Ϊ����*/

			vo.setSt_chg_dt(DateUtil.getDTStr());
			SysLog.debug("ͣ�ø�������Ϊ��" + vo.getSt_chg_dt());

			/**���ù���Ա���� */
			vo.setAdmin_id(user.getUserID());

			//����ҵ������disable��������
			bo.disable(vo, user);

			//�������ݱ�����
			//request.getSession().setAttribute("success", "y");

			//ִ��ҳ����ת		
			return forwardSuccessPage(request, mapping, "�û�ͣ�óɹ���", "User.do?act=list");

		}
	}
	public ActionForward exclude(ActionMapping mapping, BaseForm form, HttpServletRequest request, HttpServletResponse response, UserContext user) throws Exception {

		//����Ȩ��У��
		if (!OpMap.checkRoleAuth(user.getRoleID(), "user_disable")) {

			BaseException e = new BaseException();
			e.setErrorCode("E020001");
			throw e;
		}

		// ���ҵ�����
		UserBO bo = (UserBO) getBean("user_bo");

		UserForm bean = (UserForm) form;

		UserVO vo = new UserVO();

		vo.setUser_id(bean.getUser_id());

		//����ҵ������retrieve��������,��ȡ�û�״̬
		vo = (UserVO) bo.retrieve(vo, user);

		request.getSession().setAttribute("user_org", vo.getAmsd_store());

		if (!(vo.getStatus()).equals("1") && !vo.getStatus().equals("4")) {
			//����û��Ѿ�ͣ��,��ʾ,�����б�
			return forwardSuccessPage(request, mapping, "�û���ͣ�ã����������û�");

		}  else {
			//����,ͣ���û�,Ȼ�󷵻��б�

			/**���ø�������Ϊ����*/

			vo.setSt_chg_dt(DateUtil.getDTStr());

			/**���ù���Ա���� */
			vo.setAdmin_id(user.getUserID());

			//����ҵ������disable��������
			bo.exclude(vo, user);

			//�������ݱ�����
			//request.getSession().setAttribute("success", "y");

			//ִ��ҳ����ת		
			return forwardSuccessPage(request, mapping, "���ò������û��ɹ���", "User.do?act=list");

		}
	}
	/** 
	 * ��ѯ�û��б� 
	 */
	public ActionForward listpop(ActionMapping mapping, BaseForm form, HttpServletRequest request, HttpServletResponse response, UserContext user) throws Exception {

		PageObject page = new PageObject();
		String pageNo = request.getParameter("pageNO");
		String requery = request.getParameter("requery");

		String orgID = null;

		if (pageNo == null || (requery != null && requery.trim().equals("y"))) {
			page.setCurPage(1);
		} else {
			page.setCurPage(Integer.parseInt(pageNo));
		}

		if (requery == null || requery.trim().length() < 1) {
			orgID = user.getOrgID();
			((UserForm) form).setAmsd_store_f(orgID);
		}

		//��Ӳ�ѯ�������� 
		page.addFilter("amsd_store_f", ((UserForm) form).getAmsd_store_f());
		page.addFilter("user_name_f", ((UserForm) form).getUser_name_f());

		//���ҵ����� 
		UserBO bo = (UserBO) getBean("user_bo");

		//����ҵ������list��������,�����б��� 
		bo.listpop(page, user);

		//���������д��request������ 
		request.setAttribute("pageResult", page);

		//ִ��ҳ����ת 
		return mapping.findForward("listpop");
	}
	/** 
	 * �������� 
	 */

	public ActionForward setPwd(ActionMapping mapping, BaseForm form, HttpServletRequest request, HttpServletResponse response, UserContext user) throws Exception {

		//����Ȩ��У��
		if (!OpMap.checkRoleAuth(user.getRoleID(), "reset_pwd")) {

			BaseException e = new BaseException();
			e.setErrorCode("E020001");
			throw e;
		}

		// ���ҵ�����
		UserBO bo = (UserBO) getBean("user_bo");

		UserForm bean = (UserForm) form;

		if (bean.getUser_id().equals(user.getUserID())) {
			//����û��Ѿ�����,��ʾ,�����б�
			return forwardError(request, mapping, "���������Լ������룡");
		}
		bo.resetPwd(bean.getUser_id(), user);

		//ִ��ҳ����ת		
		return forwardSuccessPage(request, mapping, "�û�" + bean.getUser_name() + "���������ã�", "User.do?act=list");

	}
}
