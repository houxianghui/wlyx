 
package com.lx; 
 
import java.util.List; 
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse; 
import org.apache.struts.action.*; 
import com.eis.key.*;
import com.eis.base.*; 
import com.eis.portal.UserContext; 
import com.eis.factory.*; 
import com.eis.util.*; 
import com.eis.cache.*; 
 
 
/** 
 * ˵������ϵ��Ϣ�Ŀ����� 
 */ 
public class Lx_infoAction extends BaseAction { 
 
	/** 
	 * ���캯�� 
	 */ 
	public Lx_infoAction() { 
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
		BaseBO bo = (BaseBO) getBean("lx_info_bo"); 
		((Lx_infoBO)bo).list(page, form, user); 
		request.setAttribute("pageResult", page); 
		
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
		BaseBO bo = (BaseBO) getBean("lx_info_bo"); 
		((Lx_infoBO)bo).list(page,form,user); 
		request.setAttribute("pageResult", page); 
		
		return mapping.findForward("querylist"); 
	} 
 
 
	/** 
	 * �������� 
	 */ 
	public ActionForward add(ActionMapping mapping,BaseForm form,HttpServletRequest request,HttpServletResponse response,UserContext user) throws Exception { 
 
		//����Ȩ��У�� 
		if (!OpMap.checkRoleAuth(user.getRoleID(), "lx_info_c")) { 
			BaseException e = new BaseException(); 
			e.setErrorCode("E020001"); 
			throw e; 
		} 
 
		BaseBO bo = (BaseBO) getBean("lx_info_bo"); 
 
		Lx_infoVO vo = new Lx_infoVO(); 
 
		copyProperties(vo, form); 
		String lx_id = Long.toString(KeyGenerator.getNextKey("lx_info"));
		lx_id = StringUtil.addZero(lx_id, 8);
		vo.setLx_id(lx_id);
		bo.add(vo, user); 
 
		return new ActionRedirect("Lx_info.do?act=list"); 
	} 
 
 
	/** 
	 * ��ѯά����Ϣ��ϸ 
	 */ 
	public ActionForward editInfo(ActionMapping mapping,BaseForm form,HttpServletRequest request,HttpServletResponse response,UserContext user) throws Exception { 
 
		BaseBO bo = (BaseBO) getBean("lx_info_bo"); 
		Lx_infoVO vo = new Lx_infoVO(); 
		vo.setLx_id(((Lx_infoForm)form).getLx_id()); 
		vo=(Lx_infoVO)bo.retrieve(vo, user); 
		copyProperties(form, vo); 
		return mapping.findForward("edit"); 
	} 
 
 
	/** 
	 * �޸����� 
	 */ 
	public ActionForward update(ActionMapping mapping,BaseForm form,HttpServletRequest request,HttpServletResponse response,UserContext user) throws Exception { 
 
		//����Ȩ��У�� 
		if (!OpMap.checkRoleAuth(user.getRoleID(), "lx_info_u")) { 
			BaseException e = new BaseException(); 
			e.setErrorCode("E020001"); 
			throw e; 
		} 
 
		//���ҵ����� 
		BaseBO bo = (BaseBO) getBean("lx_info_bo"); 
 
		Lx_infoVO vo = new Lx_infoVO(); 
 
		//�ڴ˴����������ֶεĸ�ֵ�����磺form.setReg_dt(DateUtil.getDTStr()); 
 
		//�������ݴ��� 
		copyProperties(vo, form); 
 
		//����ҵ������update��������,�����б��� 
		bo.update(vo, user); 
 
		//�������ݱ����� 
		return forwardSuccessPage(request,mapping,"�����޸ĳɹ���","Lx_info.do?act=list"); 
	} 
 
 
	/** 
	 * ɾ������ 
	 */ 
	public ActionForward delete(ActionMapping mapping,BaseForm form,HttpServletRequest request,HttpServletResponse response,UserContext user) throws Exception { 
 
		//����Ȩ��У�� 
		if (!OpMap.checkRoleAuth(user.getRoleID(), "lx_info_d")) { 
			BaseException e = new BaseException(); 
			e.setErrorCode("E020001"); 
			throw e; 
		} 
 
		//���ҵ����� 
		BaseBO bo = (BaseBO) getBean("lx_info_bo"); 
 
		Lx_infoVO vo = new Lx_infoVO(); 
 
		//���ɾ����¼���� 
		vo.setLx_id(((Lx_infoForm)form).getLx_id()); 
 
 
		//����ҵ������delete��������,ִ������ɾ�� 
		bo.delete(vo, user); 
 
		//ִ��ҳ����ת 
		return forwardSuccessPage(request,mapping,"����ɾ���ɹ���","Lx_info.do?act=list"); 
	} 
 
 
	/** 
	 * ��ѯ��ϸ��Ϣ 
	 */ 
	public ActionForward retrieve(ActionMapping mapping,BaseForm form,HttpServletRequest request,HttpServletResponse response,UserContext user) throws Exception { 
 
		//���ҵ����� 
		BaseBO bo = (BaseBO) getBean("lx_info_bo"); 
 
		Lx_infoVO vo = new Lx_infoVO(); 
 
		//��ò�ѯ���� 
		vo.setLx_id(((Lx_infoForm)form).getLx_id()); 
 
 
		//����ҵ������retrieve��������,��ѯ��ϸ��Ϣ 
		vo=(Lx_infoVO)bo.retrieve(vo, user); 
 
 
 
		//���������д��form������ 
		copyProperties(form, vo); 
 
		//ִ��ҳ����ת,���ص��б�ҳ�� 
		return mapping.findForward("view"); 
	} 
 
 
} 

