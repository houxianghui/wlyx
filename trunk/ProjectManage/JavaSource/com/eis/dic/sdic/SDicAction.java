 
package com.eis.dic.sdic; 
 
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
 * ˵���������ֵ�Ŀ����� 
 */ 
public class SDicAction extends BaseAction { 
 
	/** 
	 * ���캯�� 
	 */ 
	public SDicAction() { 
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
				String type_id = (String)request.getSession().getAttribute("type_id");
				
				((SDicForm)form).setType_id(type_id);
 
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
 
		} else 
			return null; 
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
		page.addFilter("item_code_f", request.getParameter("item_code_f"));
		page.addFilter("item_val_f", request.getParameter("item_val_f"));
		page.addFilter("logic_id_f", request.getParameter("logic_id_f"));
		
		page.addFilter("type_id", request.getParameter("type_id"));
		
				
		request.getSession().setAttribute("type_id",request.getParameter("type_id"));
        
		//���ҵ����� 
		BaseBO bo = (BaseBO) getBean("sdic_bo"); 
 
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
 
		//�ڴ˴�ͨ��page.addFilter()������ӹ������� 
 
 
		//���ҵ����� 
		BaseBO bo = (BaseBO) getBean("sdic_bo"); 
 
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
	public ActionForward add(ActionMapping mapping,BaseForm form,HttpServletRequest request,HttpServletResponse response,UserContext user) throws Exception { 
 
		//����Ȩ��У��
		if (!OpMap.checkRoleAuth(user.getRoleID(), "sDic_c")) {
			BaseException e = new BaseException();
			e.setErrorCode("E020001");
			throw e;
		}
		
		//���ҵ����� 
		BaseBO bo = (BaseBO) getBean("sdic_bo"); 
 
		SDicVO vo = new SDicVO(); 
 
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
	public ActionForward editInfo(ActionMapping mapping,BaseForm form,HttpServletRequest request,HttpServletResponse response,UserContext user) throws Exception { 
 
		//���ҵ����� 
		BaseBO bo = (BaseBO) getBean("sdic_bo"); 
 
		SDicVO vo = new SDicVO(); 
 
		//��ò�ѯ���� 
		vo.setSys_id(Long.parseLong(((SDicForm)form).getSys_id())); 
 
 
		//����ҵ������retrieve��������,��ѯ��ϸ��Ϣ 
		vo=(SDicVO)bo.retrieve(vo, user); 
 
 
 
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
		if (!OpMap.checkRoleAuth(user.getRoleID(), "sDic_u")) {
			BaseException e = new BaseException();
			e.setErrorCode("E020001");
			throw e;
		}
		
		//���ҵ����� 
		BaseBO bo = (BaseBO) getBean("sdic_bo"); 
 
		SDicVO vo = new SDicVO(); 
 
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
		if (!OpMap.checkRoleAuth(user.getRoleID(), "sDic_d")) {
			BaseException e = new BaseException();
			e.setErrorCode("E020001");
			throw e;
		}
		
		//���ҵ����� 
		BaseBO bo = (BaseBO) getBean("sdic_bo"); 
 
		SDicVO vo = new SDicVO(); 
 
		//���ɾ����¼���� 
		vo.setSys_id(Long.parseLong(((SDicForm)form).getSys_id())); 
 
 
		//����ҵ������delete��������,ִ������ɾ�� 
		bo.delete(vo, user); 
        
        
		String type_id_a=((SDicForm)form).getType_id();
		SysLog.debug("**************type_id_a=************************"+ type_id_a);
		
		//ִ��ҳ����ת 
		return forwardSuccessPage(request,mapping,"����ɾ���ɹ���","SDic.do?act=list&type_id="+type_id_a); 
	} 
 
 
	/** 
	 * ��ѯ��ϸ��Ϣ 
	 */ 
	public ActionForward retrieve(ActionMapping mapping,BaseForm form,HttpServletRequest request,HttpServletResponse response,UserContext user) throws Exception { 
 
		//���ҵ����� 
		BaseBO bo = (BaseBO) getBean("sdic_bo"); 
 
		SDicVO vo = new SDicVO(); 
 
		//��ò�ѯ���� 
		vo.setSys_id(Long.parseLong(((SDicForm)form).getSys_id())); 
 
 
		//����ҵ������retrieve��������,��ѯ��ϸ��Ϣ 
		vo=(SDicVO)bo.retrieve(vo, user); 
 
 
 
		//���������д��form������ 
		copyProperties(form, vo); 
 
		//ִ��ҳ����ת,���ص��б�ҳ�� 
		return mapping.findForward("view"); 
	} 
 
 
} 

