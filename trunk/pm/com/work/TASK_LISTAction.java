 
package com.work; 
 
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse; 
import org.apache.struts.action.*; 
 
import com.eis.base.*; 
import com.eis.key.KeyGenerator;
import com.eis.portal.UserContext; 
import com.eis.util.DateUtil;
import com.eis.util.StringUtil;
import com.eis.cache.*;
 
 
/** 
 * ˵���������б�Ŀ����� 
 */ 
public class TASK_LISTAction extends BaseAction { 
 
	/** 
	 * ���캯�� 
	 */ 
	public TASK_LISTAction() { 
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
 
				return init(mapping,form,request); 
 
			} else //�û����ύ�������ݣ�ִ�����ݱ��� 
 
				return add( form, user); 
 
		}
		if (act.equals("u")) { //�޸� 
 
			String step = request.getParameter("step"); 
 
			if (null == step) { //��ʼ���׶Σ���ѯ��ϸ��Ϣ����ת���޸�ҳ�� 
 
				return editInfo(mapping, form, user); 
 
			} else //�û����ύ�޸ĺ�����ݣ�ִ�����ݱ��� 
 
				return update(form, request, user); 
 
		} 
		if (act.equals("r")) { //��ѯ��ϸ��Ϣ 
 
			return retrieve(mapping, form, user); 
 
		} 
		if (act.equals("d")) { //ɾ������ 
 
			return delete(mapping, form, request, user); 
 
		}
		if (act.equals("list")) { //����ά���б� 
 
			return list(mapping, request, user); 
 
		}
		if (act.equals("ql")) { //��ѯ�б� 
 
			return queryList(mapping, form, request, user); 
 
		} 
		if(act.equals("qa")){
			return queryAll(mapping,form,request,user);
		}
			
		return null;
	} 
 	public ActionForward init(ActionMapping mapping,BaseForm form,HttpServletRequest request)throws Exception{
 		
		TASK_LISTForm tform = (TASK_LISTForm)form;
		tform.setTask_date(DateUtil.getDTStr());
		tform.setTask_step(request.getParameter("curr_step"));
		tform.setProject_no(request.getParameter("project_no"));
		String s = request.getParameter("id");
		if(s == null || s.trim().length() == 0){
			tform.setId(0);
		}else{
			tform.setId(Integer.parseInt(s));
		}
		
		return mapping.findForward("new");
 	}
	/** 
	 * ��ѯά���б� 
	 */ 
	public ActionForward list(ActionMapping mapping,HttpServletRequest request,UserContext user) throws Exception { 
 
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
		BaseBO bo = (BaseBO) getBean("task_list_bo"); 
 
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
	public ActionForward queryList(ActionMapping mapping,BaseForm form,HttpServletRequest request,UserContext user) throws Exception { 
 
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
		BaseBO bo = (BaseBO) getBean("task_list_bo"); 
 
		//����ҵ������queryList��������,�����б��� 
		((TASK_LISTBO)bo).queryList(form,page, user); 
 
		//���������д��request������ 
		request.setAttribute("pageResult", page); 
 
		//ִ��ҳ����ת 
		return mapping.findForward("querylist"); 
	} 
 
	public ActionForward queryAll(ActionMapping mapping,BaseForm form,HttpServletRequest request,UserContext user) throws Exception { 
 
		PageObject page = new PageObject(); 
		setPageNo(request, page);
 
		BaseBO bo = (BaseBO) getBean("task_list_bo"); 
 
		((TASK_LISTBO)bo).queryListAll(form,page); 
 
		request.setAttribute("pageResult", page); 
 
		return mapping.findForward("qa"); 
	}

    public void setPageNo(HttpServletRequest request, PageObject page) {
        String pageNo = request.getParameter("pageNO"); 
        
        String requery = request.getParameter("requery"); 
        
        if (pageNo == null || (requery != null && requery.trim().equals("y"))) { 
        	page.setCurPage(1); 
        } else { 
        	page.setCurPage(Integer.parseInt(pageNo)); 
        } 
    } 
	/** 
	 * �������� 
	 */ 
	public ActionForward add(BaseForm form,UserContext user) throws Exception { 
 
		//����Ȩ��У�� 
		if (!OpMap.checkRoleAuth(user.getRoleID(), "tASK_LIST_c")) { 
			BaseException e = new BaseException(); 
			e.setErrorCode("E020001"); 
			throw e; 
		} 
 
		//���ҵ����� 
		BaseBO bo = (BaseBO) getBean("task_list_bo"); 
 
		TASK_LISTVO vo = new TASK_LISTVO(); 
 		
		//�������ݴ��� 
		copyProperties(vo, form); 
		
		vo.setTask_no(StringUtil.getFixLengthCharWithPreZero(String.valueOf(KeyGenerator.getNextKey("TASK_LIST")),20));
		vo.setTask_user(user.getUserID());
		vo.setUpdate_date(DateUtil.getDTStr());
		((TASK_LISTBO)bo).checkTaskStatus(vo);
		//�ڴ˴����������ֶεĸ�ֵ�����磺vo.setReg_dt(DateUtil.getDTStr()); 
		//����ҵ������add��������,�����б��� 
		bo.add(vo, user); 
 
		//ִ��ҳ����ת,���ص��б�ҳ�� 
		return new ActionRedirect("TASK_LIST.do?act=list"); 
	} 
 
 
	/** 
	 * ��ѯά����Ϣ��ϸ 
	 */ 
	public ActionForward editInfo(ActionMapping mapping,BaseForm form,UserContext user) throws Exception { 
 
		//���ҵ����� 
		BaseBO bo = (BaseBO) getBean("task_list_bo"); 
 
		TASK_LISTVO vo = new TASK_LISTVO(); 
 
		//��ò�ѯ���� 
		vo.setTask_no(((TASK_LISTForm)form).getTask_no()); 
 
 
		//����ҵ������retrieve��������,��ѯ��ϸ��Ϣ 
		vo=(TASK_LISTVO)bo.retrieve(vo, user); 
 
 
 
		//���������д��form������ 
		copyProperties(form, vo); 
 
		//ִ��ҳ����ת,���ص��б�ҳ�� 
		return mapping.findForward("edit"); 
	} 
 
 
	/** 
	 * �޸����� 
	 */ 
	public ActionForward update(BaseForm form,HttpServletRequest request,UserContext user) throws Exception { 
 
		//����Ȩ��У�� 
		if (!OpMap.checkRoleAuth(user.getRoleID(), "tASK_LIST_c")) { 
			BaseException e = new BaseException(); 
			e.setErrorCode("E020001"); 
			throw e; 
		} 
 
		//���ҵ����� 
		BaseBO bo = (BaseBO) getBean("task_list_bo"); 
 
		TASK_LISTVO vo = new TASK_LISTVO(); 
 
		//�ڴ˴����������ֶεĸ�ֵ�����磺form.setReg_dt(DateUtil.getDTStr()); 
 
		//�������ݴ��� 
		copyProperties(vo, form); 
 		vo.setUpdate_date(DateUtil.getDTStr());
		//����ҵ������update��������,�����б��� 
		bo.update(vo, user); 
 
		//�������ݱ����� 
		request.setAttribute("success", "y"); 
 
		//ִ��ҳ����ת 
		return new ActionRedirect("TASK_LIST.do?act=list");  
	} 
 
 
	/** 
	 * ɾ������ 
	 */ 
	public ActionForward delete(ActionMapping mapping,BaseForm form,HttpServletRequest request,UserContext user) throws Exception { 
 
		//����Ȩ��У�� 
		if (!OpMap.checkRoleAuth(user.getRoleID(), "tASK_LIST_c")) { 
			BaseException e = new BaseException(); 
			e.setErrorCode("E020001"); 
			throw e; 
		} 
 
		//���ҵ����� 
		BaseBO bo = (BaseBO) getBean("task_list_bo"); 
 
		TASK_LISTVO vo = new TASK_LISTVO(); 
 
		//���ɾ����¼���� 
		vo.setTask_no(((TASK_LISTForm)form).getTask_no()); 
 
 
		//����ҵ������delete��������,ִ������ɾ�� 
		bo.delete(vo, user); 
 
		//ִ��ҳ����ת 
		return forwardSuccessPage(request,mapping,"����ɾ���ɹ���","TASK_LIST.do?act=list"); 
	} 
 
 
	/** 
	 * ��ѯ��ϸ��Ϣ 
	 */ 
	public ActionForward retrieve(ActionMapping mapping,BaseForm form,UserContext user) throws Exception { 
 
		//���ҵ����� 
		BaseBO bo = (BaseBO) getBean("task_list_bo"); 
 
		TASK_LISTVO vo = new TASK_LISTVO(); 
 
		//��ò�ѯ���� 
		vo.setTask_no(((TASK_LISTForm)form).getTask_no()); 
 
 
		//����ҵ������retrieve��������,��ѯ��ϸ��Ϣ 
		vo=(TASK_LISTVO)bo.retrieve(vo, user); 
 
 
 
		//���������д��form������ 
		copyProperties(form, vo); 
 
		//ִ��ҳ����ת,���ص��б�ҳ�� 
		return mapping.findForward("view"); 
	} 
 
 
} 

