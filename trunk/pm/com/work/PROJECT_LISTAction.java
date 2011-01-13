 
package com.work; 
 
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse; 
import org.apache.struts.action.*; 
 
import com.eis.base.*; 
import com.eis.portal.UserContext; 
import com.eis.cache.*;
 
 
/** 
 * ˵���������б�Ŀ����� 
 */ 
public class PROJECT_LISTAction extends BaseAction { 
 
	/** 
	 * ���캯�� 
	 */ 
	public PROJECT_LISTAction() { 
		super(); 
	} 
 
	/** 
	 * ִ�������� 
	 */ 
		public ActionForward process(ActionMapping mapping,BaseForm form,HttpServletRequest request,HttpServletResponse response,UserContext user) throws Exception { 
		String act = form.getAct(); 
		if (act.equals("c")) { //���� 
 
			String step = request.getParameter("step"); 
 
			if (null == step) { //��ʼ���׶Σ���ת������ҳ�� 
 				return init(mapping,form,request,user);
				
 
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
 
		} else 
			return null; 
	} 
 	public ActionForward init(ActionMapping mapping,BaseForm form,HttpServletRequest request,UserContext user)throws Exception{
		return mapping.findForward("new"); 
 	}
 
	/** 
	 * ��ѯά���б� 
	 */ 
	public ActionForward list(ActionMapping mapping,BaseForm form,HttpServletRequest request,HttpServletResponse response,UserContext user) throws Exception { 
 
		PageObject page = new PageObject(); 
		String pageNo = request.getParameter("pageNO"); 
 
		String requery = request.getParameter("requery"); 
 
		if (pageNo == null || (requery != null && requery.trim().equals("y"))) { 
			page.setCurPage(1); 
		} else { 
			page.setCurPage(Integer.parseInt(pageNo)); 
		} 
 
		//�ڴ˴�ͨ��page.addFilter()������ӹ������� 
 
 
		//���ҵ����� 
		BaseBO bo = (BaseBO) getBean("project_list_bo"); 
 
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
	public ActionForward queryList(ActionMapping mapping,BaseForm form,HttpServletRequest request,HttpServletResponse response,UserContext user) throws Exception { 
 
		PageObject page = new PageObject(); 
		String pageNo = request.getParameter("pageNO"); 
 
		String requery = request.getParameter("requery"); 
 
		if (pageNo == null || (requery != null && requery.trim().equals("y"))) { 
			page.setCurPage(1); 
		} else { 
			page.setCurPage(Integer.parseInt(pageNo)); 
		} 
 
		//���ҵ����� 
		BaseBO bo = (BaseBO) getBean("project_list_bo"); 
 
		//����ҵ������queryList��������,�����б��� 
		((PROJECT_LISTBO)bo).queryList(form,page, user); 
 
		//���������д��request������ 
		request.setAttribute("pageResult", page); 
 
		//ִ��ҳ����ת 
		return mapping.findForward("querylist"); 
	} 
 
 
	/** 
	 * �������� 
	 */ 
	public ActionForward add(ActionMapping mapping,BaseForm form,HttpServletRequest request,HttpServletResponse response,UserContext user) throws Exception { 
 
		//����Ȩ��У�� 
		if (!OpMap.checkRoleAuth(user.getRoleID(), "pROJECT_LIST_c")) { 
			BaseException e = new BaseException(); 
			e.setErrorCode("E020001"); 
			throw e; 
		} 
 
		//���ҵ����� 
		BaseBO bo = (BaseBO) getBean("project_list_bo"); 
 
		PROJECT_LISTVO vo = new PROJECT_LISTVO(); 
 
		//�������ݴ��� 
		copyProperties(vo, form); 
 		vo.setUser_id(user.getUserID());
		//�ڴ˴����������ֶεĸ�ֵ�����磺vo.setReg_dt(DateUtil.getDTStr()); 
		//����ҵ������add��������,�����б��� 
		bo.add(vo, user); 
 
		//ִ��ҳ����ת,���ص��б�ҳ�� 
		return new ActionRedirect("PROJECT_LIST.do?act=list"); 
	} 
 
 
	/** 
	 * ��ѯά����Ϣ��ϸ 
	 */ 
	public ActionForward editInfo(ActionMapping mapping,BaseForm form,HttpServletRequest request,HttpServletResponse response,UserContext user) throws Exception { 
 
		//���ҵ����� 
		BaseBO bo = (BaseBO) getBean("project_list_bo"); 
 
		PROJECT_LISTVO vo = new PROJECT_LISTVO(); 
 
		//��ò�ѯ���� 
		vo.setUser_id(((PROJECT_LISTForm)form).getUser_id()); 
		vo.setProject_no(((PROJECT_LISTForm)form).getProject_no()); 
 
 
		//����ҵ������retrieve��������,��ѯ��ϸ��Ϣ 
		vo=(PROJECT_LISTVO)bo.retrieve(vo, user); 
 
 
 
		//���������д��form������ 
		copyProperties(form, vo); 
 
		//ִ��ҳ����ת,���ص��б�ҳ�� 
		return mapping.findForward("edit"); 
	} 
 
 
	/** 
	 * �޸����� 
	 */ 
	public ActionForward update(ActionMapping mapping,BaseForm form,HttpServletRequest request,HttpServletResponse response,UserContext user) throws Exception { 
 
		//����Ȩ��У�� 
		if (!OpMap.checkRoleAuth(user.getRoleID(), "pROJECT_LIST_c")) { 
			BaseException e = new BaseException(); 
			e.setErrorCode("E020001"); 
			throw e; 
		} 
 
		//���ҵ����� 
		BaseBO bo = (BaseBO) getBean("project_list_bo"); 
 
		PROJECT_LISTVO vo = new PROJECT_LISTVO(); 
 
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
	public ActionForward delete(ActionMapping mapping,BaseForm form,HttpServletRequest request,HttpServletResponse response,UserContext user) throws Exception { 
 
		//����Ȩ��У�� 
		if (!OpMap.checkRoleAuth(user.getRoleID(), "pROJECT_LIST_c")) { 
			BaseException e = new BaseException(); 
			e.setErrorCode("E020001"); 
			throw e; 
		} 
 
		//���ҵ����� 
		BaseBO bo = (BaseBO) getBean("project_list_bo"); 
 
		PROJECT_LISTVO vo = new PROJECT_LISTVO(); 
 
		//���ɾ����¼���� 
		vo.setUser_id(((PROJECT_LISTForm)form).getUser_id()); 
		vo.setProject_no(((PROJECT_LISTForm)form).getProject_no()); 
 
 
		//����ҵ������delete��������,ִ������ɾ�� 
		bo.delete(vo, user); 
 
		//ִ��ҳ����ת 
		return forwardSuccessPage(request,mapping,"����ɾ���ɹ���","PROJECT_LIST.do?act=list"); 
	} 
 
 
	/** 
	 * ��ѯ��ϸ��Ϣ 
	 */ 
	public ActionForward retrieve(ActionMapping mapping,BaseForm form,HttpServletRequest request,HttpServletResponse response,UserContext user) throws Exception { 
 
		//���ҵ����� 
		BaseBO bo = (BaseBO) getBean("project_list_bo"); 
 
		PROJECT_LISTVO vo = new PROJECT_LISTVO(); 
 
		//��ò�ѯ���� 
		vo.setUser_id(((PROJECT_LISTForm)form).getUser_id()); 
		vo.setProject_no(((PROJECT_LISTForm)form).getProject_no()); 
 
 
		//����ҵ������retrieve��������,��ѯ��ϸ��Ϣ 
		vo=(PROJECT_LISTVO)bo.retrieve(vo, user); 
 
 
 
		//���������д��form������ 
		copyProperties(form, vo); 
 
		//ִ��ҳ����ת,���ص��б�ҳ�� 
		return mapping.findForward("view"); 
	} 
 
 
} 

