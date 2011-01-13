 
package com.maintainrecord; 
 
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
 * ˵��������֧�ַ����¼�Ŀ����� 
 */ 
public class Maintain_recordAction extends BaseAction { 
 
	/** 
	 * ���캯�� 
	 */ 
	public Maintain_recordAction() { 
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
				 
				return init(mapping,form); 
 
			} else //�û����ύ�������ݣ�ִ�����ݱ��� 
 
				return add(form,user); 
 
		} else if (act.equals("u")) { //�޸� 
 
			String step = request.getParameter("step"); 
 
			if (null == step) { //��ʼ���׶Σ���ѯ��ϸ��Ϣ����ת���޸�ҳ�� 
 
				return editInfo(mapping, form, user); 
 
			} else //�û����ύ�޸ĺ�����ݣ�ִ�����ݱ��� 
 
				return update( form, request, user); 
 
		} else if (act.equals("r")) { //��ѯ��ϸ��Ϣ 
 
			return retrieve(mapping, form, user); 
 
		} else if (act.equals("d")) { //ɾ������ 
 
			return delete(mapping, form, request, user); 
 
		} else if (act.equals("list")) { //����ά���б� 
 
			return list(mapping, form, request); 
 
		} else if (act.equals("ql")) { //��ѯ�б� 
 
			return queryList(mapping, form, request); 
 
		} else if(act.equals("p")){	//process
			return response(mapping,form,user);
		} else if(act.equals("up")){	//processUpdate
			return responseUpdate(form,user);
		}
			return null; 
	} 
 	public ActionForward init(ActionMapping mapping,BaseForm form){
 		((Maintain_recordForm)form).setQus_date(DateUtil.getDTStr());
 		
		return mapping.findForward("new");
 	}
	/** 
	 * ��ѯά���б� 
	 */ 
	public ActionForward list(ActionMapping mapping,BaseForm form,HttpServletRequest request) throws Exception { 
 
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
		BaseBO bo = (BaseBO) getBean("maintain_record_bo"); 
 		
		//����ҵ������list��������,�����б��� 
		((Maintain_recordBO)bo).list(page,form); 
 
		//���������д��request������ 
		request.setAttribute("pageResult", page); 
 
		//ִ��ҳ����ת 
		return mapping.findForward("list"); 
	} 
 
 
	/** 
	 * ��ѯ�б� 
	 */ 
	public ActionForward queryList(ActionMapping mapping,BaseForm form,HttpServletRequest request) throws Exception { 
 
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
		BaseBO bo = (BaseBO) getBean("maintain_record_bo"); 
 
		//����ҵ������queryList��������,�����б��� 
		((Maintain_recordBO)bo).queryList(page, form); 
 
		//���������д��request������ 
		request.setAttribute("pageResult", page); 
 
		//ִ��ҳ����ת 
		return mapping.findForward("querylist"); 
	} 
 
 
	/** 
	 * �������� 
	 */ 
	public ActionForward add(BaseForm form,UserContext user) throws Exception { 
 
		if (!OpMap.checkRoleAuth(user.getRoleID(), "maintain_record_c")) { 
			BaseException e = new BaseException(); 
			e.setErrorCode("E020001"); 
			throw e; 
		} 
 
		BaseBO bo = (BaseBO) getBean("maintain_record_bo"); 
 
		Maintain_recordVO vo = new Maintain_recordVO(); 
 
		copyProperties(vo, form); 
 		
		vo.initAddVO(user);
 		vo.setSeq_no(StringUtil.getFixLengthCharWithPreZero(String.valueOf(KeyGenerator.getNextKey("maintain_record")),10));
		
		bo.add(vo, user); 
 
		return new ActionRedirect("Maintain_record.do?act=list"); 
	}


 
 
	/** 
	 * ��ѯά����Ϣ��ϸ 
	 */ 
	public ActionForward editInfo(ActionMapping mapping,BaseForm form,UserContext user) throws Exception { 
 
		//���ҵ����� 
		BaseBO bo = (BaseBO) getBean("maintain_record_bo"); 
 
		Maintain_recordVO vo = new Maintain_recordVO(); 
 
		//��ò�ѯ���� 
		vo.setSeq_no(((Maintain_recordForm)form).getSeq_no()); 
 
 
		//����ҵ������retrieve��������,��ѯ��ϸ��Ϣ 
		vo=(Maintain_recordVO)bo.retrieve(vo, user); 
 
 
 
		//���������д��form������ 
		copyProperties(form, vo); 
 
		//ִ��ҳ����ת,���ص��б�ҳ�� 
		return mapping.findForward("edit"); 
	} 
	/**
     * @author houxh 2008-1-9
     * ��ҳ��
     * 
     * @param mapping
     * @param form
     * @param user
     * @return
     * @throws Exception 
     */
    public ActionForward response(ActionMapping mapping,BaseForm form,UserContext user) throws Exception { 
 
		BaseBO bo = (BaseBO) getBean("maintain_record_bo"); 
		Maintain_recordVO vo = new Maintain_recordVO(); 
		
		vo.setSeq_no(((Maintain_recordForm)form).getSeq_no()); 
		vo=(Maintain_recordVO)bo.retrieve(vo, user); 
		copyProperties(form, vo); 
		
		return mapping.findForward("resp"); 
	}  
 
	/** 
	 * �޸����� 
	 */ 
	public ActionForward update(BaseForm form,HttpServletRequest request,UserContext user) throws Exception { 
 
		//����Ȩ��У�� 
		if (!OpMap.checkRoleAuth(user.getRoleID(), "maintain_record_p")) { 
			BaseException e = new BaseException(); 
			e.setErrorCode("E020001"); 
			throw e; 
		} 
 
		//���ҵ����� 
		BaseBO bo = (BaseBO) getBean("maintain_record_bo"); 
 
		Maintain_recordVO vo = new Maintain_recordVO(); 
 
		//�ڴ˴����������ֶεĸ�ֵ�����磺form.setReg_dt(DateUtil.getDTStr()); 
 
		//�������ݴ��� 
		copyProperties(vo, form); 
 
		//����ҵ������update��������,�����б��� 
		bo.update(vo, user); 
 
		//�������ݱ����� 
		request.setAttribute("success", "y"); 
 
		//ִ��ҳ����ת 
		return new ActionRedirect("Maintain_record.do?act=list");
	} 
	/**
     * @author houxh 2008-1-9
     * �𸴸��²���
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @param user
     * @return
     * @throws Exception 
     */
    public ActionForward responseUpdate(BaseForm form,UserContext user) throws Exception { 
 
		//����Ȩ��У�� 
		if (!OpMap.checkRoleAuth(user.getRoleID(), "maintain_record_c")) { 
			BaseException e = new BaseException(); 
			e.setErrorCode("E020001"); 
			throw e; 
		} 
		BaseBO bo = (BaseBO) getBean("maintain_record_bo"); 
 
		Maintain_recordVO vo = new Maintain_recordVO(); 
 
		copyProperties(vo, form); 
 		
 		vo.setRes_time(DateUtil.getTimeStr());
 		vo.setRes_user(user.getUserID());
 		
		bo.update(vo, user); 
 
		return new ActionRedirect("Maintain_record.do?act=list");
	}  
 
	/** 
	 * ɾ������ 
	 */ 
	public ActionForward delete(ActionMapping mapping,BaseForm form,HttpServletRequest request,UserContext user) throws Exception { 
 
		//����Ȩ��У�� 
		if (!OpMap.checkRoleAuth(user.getRoleID(), "maintain_record_c")) { 
			BaseException e = new BaseException(); 
			e.setErrorCode("E020001"); 
			throw e; 
		} 
 
		//���ҵ����� 
		BaseBO bo = (BaseBO) getBean("maintain_record_bo"); 
 
		Maintain_recordVO vo = new Maintain_recordVO(); 
 
		//���ɾ����¼���� 
		vo.setSeq_no(((Maintain_recordForm)form).getSeq_no()); 
 
 
		//����ҵ������delete��������,ִ������ɾ�� 
		bo.delete(vo, user); 
 
		//ִ��ҳ����ת 
		return forwardSuccessPage(request,mapping,"����ɾ���ɹ���","Maintain_record.do?act=list"); 
	} 
 
 
	/** 
	 * ��ѯ��ϸ��Ϣ 
	 */ 
	public ActionForward retrieve(ActionMapping mapping,BaseForm form,UserContext user) throws Exception { 
 
		//���ҵ����� 
		BaseBO bo = (BaseBO) getBean("maintain_record_bo"); 
 
		Maintain_recordVO vo = new Maintain_recordVO(); 
 
		//��ò�ѯ���� 
		vo.setSeq_no(((Maintain_recordForm)form).getSeq_no()); 
 
 
		//����ҵ������retrieve��������,��ѯ��ϸ��Ϣ 
		vo=(Maintain_recordVO)bo.retrieve(vo, user); 
 
 
 
		//���������д��form������ 
		copyProperties(form, vo); 
 
		//ִ��ҳ����ת,���ص��б�ҳ�� 
		return mapping.findForward("view"); 
	} 
 
 
} 

