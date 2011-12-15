 
package com.eis.portal.oplog; 
 
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
 
 
/** 
 * ˵����������־�Ŀ����� 
 */ 
public class OpLogAction extends BaseAction { 
 
	/** 
	 * ���캯�� 
	 */ 
	public OpLogAction() { 
		super(); 
	} 
 
	/** 
	 * ִ�������� 
	 */ 
		public ActionForward process(ActionMapping mapping,BaseForm form,HttpServletRequest request,HttpServletResponse response,UserContext user) throws Exception { 
		String act = form.getAct(); 
		
		if (act.equals("r")) { //��ѯ��ϸ��Ϣ 
 
			return retrieve(mapping, form, request, response, user); 

 
		} else if (act.equals("ql")) { //��ѯ�б� 
 
			return queryList(mapping, form, request, response, user); 
 
		} else 
			return null; 
	} 

	/** 
	 * ��ѯ�б� 
	 */ 
	public ActionForward queryList(ActionMapping mapping,BaseForm form,HttpServletRequest request,HttpServletResponse response,UserContext user) throws Exception { 
 
		PageObject page = new PageObject(); 
		
		String pageNo = request.getParameter("pageNO");

		String requery = request.getParameter("requery");

		if (pageNo == null
			|| (requery != null && requery.trim().equals("y"))) {
			page.setCurPage(1);	
			
		} else {
			page.setCurPage(Integer.parseInt(pageNo));			
			
		}
		
		//��һ�ν��룬��ִ�����ݿ��ѯ		
		if (pageNo == null && requery == null ) {		
			
			return mapping.findForward("querylist");				
			
		}

		//�ڴ˴�ͨ��page.addFilter()������ӹ������� 			
		page.addFilter("event_date_begin", request.getParameter("event_date_begin"));
		page.addFilter("event_date_end", request.getParameter("event_date_end"));
		page.addFilter("event_type", request.getParameter("event_type"));
		page.addFilter("op_id", request.getParameter("op_id"));
		
		page.addFilter("branch_id", request.getParameter("branch_id"));
		page.addFilter("login_id", request.getParameter("login_id"));
		page.addFilter("user_id","");
 
		//���ҵ����� 
		OpLogBO bo = (OpLogBO) getBean("oplog_bo"); 
		
		String login_id = (String) page.getFilter("login_id");
		if (login_id != null && login_id.trim().length()>0){		
			String user_id = bo.getUserID(login_id.trim());

			if (user_id != null && user_id.trim().length()>0){		
				page.addFilter("user_id", user_id);
			}else{
				return forwardError(request, mapping, "����������û���");
			}
		}
		
		//����ҵ������list��������,�����б��� 
		bo.queryList(page, user); 
 
		//���������д��request������ 
		request.setAttribute("pageResult", page); 
 
		//ִ��ҳ����ת 
		return mapping.findForward("querylist"); 
	} 
 

	/** 
	 * ��ѯ��ϸ��Ϣ 
	 */ 
	public ActionForward retrieve(ActionMapping mapping,BaseForm form,HttpServletRequest request,HttpServletResponse response,UserContext user) throws Exception { 
 
		//���ҵ����� 
		BaseBO bo = (BaseBO) getBean("oplog_bo"); 
 
		OpLogVO vo = new OpLogVO(); 
 
		//��ò�ѯ���� 
		vo.setSys_id(Long.parseLong(((OpLogForm)form).getSys_id())); 
		
 
		//����ҵ������retrieve��������,��ѯ��ϸ��Ϣ 
		vo=(OpLogVO)bo.retrieve(vo, user); 
 
		//���������д��form������ 
		copyProperties(form, vo); 
 
		//ִ��ҳ����ת,���ص��б�ҳ�� 
		return mapping.findForward("view"); 
	} 
 
 
} 

