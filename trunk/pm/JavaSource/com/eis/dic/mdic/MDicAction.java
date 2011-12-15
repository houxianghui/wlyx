 
package com.eis.dic.mdic; 
 
import java.util.List; 
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse; 
import org.apache.struts.action.*; 
 
import com.eis.base.*; 
import com.eis.portal.UserContext; 
import com.eis.factory.*; 
import com.eis.util.*; 
import com.eis.cache.*; 
import com.eis.key.*;
 
 
/** 
 * ˵�����༶�ֵ�Ŀ����� 
 */ 
public class MDicAction extends BaseAction { 
 
	/** 
	 * ���캯�� 
	 */ 
	public MDicAction() { 
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
 
		}else if (act.equals("cfl")) { //����һ���ֵ� 
 
		   	return mapping.findForward("cfl"); 
 
	   	}else if (act.equals("blank")) { //���ؿհ�ҳ�����frame����ˢ���Ҳ�frame 
 
	  	 	return mapping.findForward("blank"); 
 
   		}
		else if (act.equals("listPop")) { //�����Ķ༶�ֵ��б� 
 
			return listPop(mapping, form, request, response, user); 
 
		}else 
			return null; 
	} 
 
	/** 
	 * ��ѯά���б� 
	 */ 
	public ActionForward list(ActionMapping mapping,BaseForm form,HttpServletRequest request,HttpServletResponse response,UserContext user) throws Exception { 
 
		PageObject page = new PageObject(); 
		page = null;
 
		//�ڴ˴�ͨ��page.addFilter()������ӹ������� 
		String type_id = request.getParameter("type_id");
 
		//���ҵ����� 
		MDicBO bo = (MDicBO) getBean("mdic_bo"); 
 
		//����ҵ������list��������,�����б��� 
		List listr = bo.listR(page, user,type_id); 
 
		//���������д��request������ 
		request.setAttribute("ListR", listr); 
 
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
		BaseBO bo = (BaseBO) getBean("mdic_bo"); 
 
		//����ҵ������queryList��������,�����б��� 
		bo.queryList(page, user); 
 
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
		if (!OpMap.checkRoleAuth(user.getRoleID(), "mDic_c")) { 
			BaseException e = new BaseException(); 
			e.setErrorCode("E020001"); 
			throw e; 
		} 
 
		//���ҵ����� 
		BaseBO bo = (BaseBO) getBean("mdic_bo"); 
 
		MDicVO vo = new MDicVO(); 
 
		//�������ݴ��� 
		copyProperties(vo, form); 
 
		//�ڴ˴����������ֶεĸ�ֵ; 
		
		vo.setSys_id(KeyGenerator.getNextKey("ep_mdic"));
		//����ҵ������add��������,�����б��� 
		bo.add(vo, user); 
		
		request.setAttribute("refresh", "y"); //ˢ�����frame
		
		//ִ��ҳ����ת,���ص��б�ҳ�� 
		//return new ActionRedirect("MDic.do?act=blank"); 
		return mapping.findForward("blank");
	} 
 
 
	/** 
	 * ��ѯά����Ϣ��ϸ 
	 */ 
	public ActionForward editInfo(ActionMapping mapping,BaseForm form,HttpServletRequest request,HttpServletResponse response,UserContext user) throws Exception { 
 
		//���ҵ����� 
		BaseBO bo = (BaseBO) getBean("mdic_bo"); 
 
		MDicVO vo = new MDicVO(); 
 
		//��ò�ѯ���� 
		vo.setSys_id(Long.parseLong(((MDicForm)form).getSys_id())); 
 
 
		//����ҵ������retrieve��������,��ѯ��ϸ��Ϣ 
		vo=(MDicVO)bo.retrieve(vo, user); 
 
 
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
		if (!OpMap.checkRoleAuth(user.getRoleID(), "mDic_u")) { 
			BaseException e = new BaseException(); 
			e.setErrorCode("E020001"); 
			throw e; 
		} 
 
		//���ҵ����� 
		BaseBO bo = (BaseBO) getBean("mdic_bo"); 
 
		MDicVO vo = new MDicVO(); 
 
		//�ڴ˴����������ֶεĸ�ֵ�����磺form.setReg_dt(DateUtil.getDTStr()); 
 
		//�������ݴ��� 
		copyProperties(vo, form); 
 
		//����ҵ������update��������,�����б��� 
		bo.update(vo, user); 
 
		//�������ݱ����� 
		//request.setAttribute("success", "y"); 
		
		request.setAttribute("refresh","y");
 
 
		//ִ��ҳ����ת 
		return mapping.findForward("blank"); 
	} 
 
 
	/** 
	 * ɾ������ 
	 */ 
	public ActionForward delete(ActionMapping mapping,BaseForm form,HttpServletRequest request,HttpServletResponse response,UserContext user) throws Exception { 
 
		//����Ȩ��У�� 
		if (!OpMap.checkRoleAuth(user.getRoleID(), "mDic_d")) { 
			BaseException e = new BaseException(); 
			e.setErrorCode("E020001"); 
			throw e; 
		} 
 
		//���ҵ����� 
		MDicBO bo = (MDicBO) getBean("mdic_bo"); 
 
		MDicVO vo = new MDicVO(); 
 
		//���ɾ����¼���� 
		vo.setSys_id(Long.parseLong(((MDicForm)form).getSys_id())); 
 
 
		//����ҵ������delete��������,ִ������ɾ�� 
		bo.deleteMDic(vo, user); 
 
		request.setAttribute("refresh","y");
 
 
		//ִ��ҳ����ת 
		return mapping.findForward("blank"); 
		//return forwardSuccessPage(request,mapping,"����ɾ���ɹ���","MDic.do?act=list&type_id="+((MDicForm)form).getType_id()+""); 
	} 
 
 
	/** 
	 * ��ѯ��ϸ��Ϣ 
	 */ 
	public ActionForward retrieve(ActionMapping mapping,BaseForm form,HttpServletRequest request,HttpServletResponse response,UserContext user) throws Exception { 
 
		//���ҵ����� 
		BaseBO bo = (BaseBO) getBean("mdic_bo"); 
 
		MDicVO vo = new MDicVO(); 
 
		//��ò�ѯ���� 
		vo.setSys_id(Long.parseLong(((MDicForm)form).getSys_id())); 
		//����ҵ������retrieve��������,��ѯ��ϸ��Ϣ 
		vo=(MDicVO)bo.retrieve(vo, user); 

		//���������д��form������ 
		copyProperties(form, vo); 
 
		//ִ��ҳ����ת,���ص��б�ҳ�� 
		return mapping.findForward("view"); 
	} 
 
	/** 
	 * �����б�ҳ��
	 */ 
	public ActionForward listPop(ActionMapping mapping,BaseForm form,HttpServletRequest request,HttpServletResponse response,UserContext user) throws Exception { 
 
 		//�ڴ˴�ͨ��page.addFilter()������ӹ������� 
		String typeID = request.getParameter("type_id");
 
	 
		//���������д��request������ 
		request.setAttribute("ItemList", MLDicMap.getOptionCollection(typeID)); 
		request.setAttribute("TypeName", MLDicMap.getDicMapName(typeID));
		
		request.setAttribute("idField", request.getParameter("idField"));
		
		request.setAttribute("nameField", request.getParameter("nameField"));
		
		
 
		//ִ��ҳ����ת 
		return mapping.findForward("listPop"); 
	} 
} 

