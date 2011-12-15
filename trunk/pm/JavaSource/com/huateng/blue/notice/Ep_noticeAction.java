 
package com.huateng.blue.notice; 
 
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List; 
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse; 
import org.apache.struts.action.*; 
import org.apache.struts.upload.FormFile;
 
import com.eis.base.*; 
import com.eis.portal.UserContext; 
import com.eis.exception.MessageException;
import com.eis.factory.*; 
import com.eis.util.*; 
import com.eis.cache.*;
import com.eis.config.SysConfig;
 
 
/** 
 * ˵����1�Ŀ����� 
 */ 
public class Ep_noticeAction extends BaseAction { 
 
	/** 
	 * ���캯�� 
	 */ 
	public Ep_noticeAction() { 
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
 
		} else if(act.equals("pop")){
			
			return pop(mapping,form,request,response,user);
			
		} else if (act.equals("download")) { //�����ļ� 

			return download(mapping, form, request, response, user);

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
 		page.addFilter("oper_id",((Ep_noticeForm)form).getOper_id_f());
 		page.addFilter("start_date",((Ep_noticeForm)form).getStart_date_f());
		page.addFilter("end_date",((Ep_noticeForm)form).getEnd_date_f());
		
		//���ҵ����� 
		BaseBO bo = (BaseBO) getBean("ep_notice_bo"); 
 
		//����ҵ������list��������,�����б��� 
		bo.list(page, user); 
 
		//���������д��request������ 
		request.setAttribute("pageResult", page); 
 
		//ִ��ҳ����ת 
		return mapping.findForward("list"); 
	} 
 
 
	
 
	/** 
	 * �������� 
	 */ 
	public ActionForward add(ActionMapping mapping,BaseForm form,HttpServletRequest request,HttpServletResponse response,UserContext user) throws Exception { 
 
		//����Ȩ��У�� 
			if (!OpMap.checkRoleAuth(user.getRoleID(), "ep_notice_c")) { 
			BaseException e = new BaseException(); 
			e.setErrorCode("E020001"); 
			throw e; 
		} 
 		String s = new String(((Ep_noticeForm)form).getNotice_comment().getBytes(),"gbk");
 		
 		
		//���ҵ����� 
		BaseBO bo = (BaseBO) getBean("ep_notice_bo"); 
 
		Ep_noticeVO vo = new Ep_noticeVO(); 

		//�������ݴ��� 
		copyProperties(vo, form); 
		vo.setOper_id(user.getUserID());
		vo.setOper_date(DateUtil.getDTStr());
		FormFile file = ((Ep_noticeForm)form).getFile();
		String FileName = file.getFileName();
		if(!FileName.equals("")){
			if(file.getFileSize()==0){
				throw new MessageException("���ݲ���Ϊ��");

			}else{
			
				vo.setFilename(FileName);			
//				����ļ��ľ���·��
				  String filePath =
					  SysConfig.getProperty("query.notice.path")
					  + File.separator
						  + FileName;			
				  InputStream stream = null;
				  OutputStream bos = null;
				  try{
					  stream = file.getInputStream();
					  bos = new FileOutputStream(filePath);
					  int bytesRead = 0;
					  byte[] buffer = new byte[8192];
					  while ((bytesRead = stream.read(buffer, 0, 8192)) != -1) {
						  bos.write(buffer, 0, bytesRead);
					  }
				  }finally{
					  if(bos != null)
						  bos.close();
					  if(stream != null)
						  stream.close();
					  file.destroy();
				  }
			}
		} 
		//�ڴ˴����������ֶεĸ�ֵ�����磺vo.setReg_dt(DateUtil.getDTStr()); 
		//����ҵ������add��������,�����б��� 
		bo.add(vo, user); 
 		//NoteMap.ReloadDic();
		//ִ��ҳ����ת,���ص��б�ҳ�� 
		return new ActionRedirect("Ep_notice.do?act=list"); 
	} 
 
 
	/** 
	 * ��ѯά����Ϣ��ϸ 
	 */ 
	public ActionForward editInfo(ActionMapping mapping,BaseForm form,HttpServletRequest request,HttpServletResponse response,UserContext user) throws Exception { 
 
		//���ҵ����� 
		BaseBO bo = (BaseBO) getBean("ep_notice_bo"); 
 
		Ep_noticeVO vo = new Ep_noticeVO(); 
 
		//��ò�ѯ���� 
		vo.setNotice_no(Integer.parseInt(((Ep_noticeForm)form).getNotice_no())); 
 
 
		//����ҵ������retrieve��������,��ѯ��ϸ��Ϣ 
		vo=(Ep_noticeVO)bo.retrieve(vo, user); 
 
 
 
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
		if (!OpMap.checkRoleAuth(user.getRoleID(), "ep_notice_c")) { 
			BaseException e = new BaseException(); 
			e.setErrorCode("E020001"); 
			throw e; 
		} 
 
		//���ҵ����� 
		BaseBO bo = (BaseBO) getBean("ep_notice_bo"); 
 
		Ep_noticeVO vo = new Ep_noticeVO(); 
 
		//�ڴ˴����������ֶεĸ�ֵ�����磺form.setReg_dt(DateUtil.getDTStr()); 
 
		//�������ݴ��� 
		copyProperties(vo, form); 
 
		//����ҵ������update��������,�����б��� 
		bo.update(vo, user); 
 		//NoteMap.ReloadDic();
		//�������ݱ����� 
		request.setAttribute("success", "y"); 
 
		//ִ��ҳ����ת 
		return forwardSuccessPage(request,mapping,"�����޸ĳɹ���","Ep_notice.do?act=list");  
	} 
 
 
	/** 
	 * ɾ������ 
	 */ 
	public ActionForward delete(ActionMapping mapping,BaseForm form,HttpServletRequest request,HttpServletResponse response,UserContext user) throws Exception { 
 
		//����Ȩ��У��
		if (!OpMap.checkRoleAuth(user.getRoleID(), "ep_notice_c")) { 
			BaseException e = new BaseException(); 
			e.setErrorCode("E020001"); 
			throw e; 
		} 
 
		//���ҵ����� 
		BaseBO bo = (BaseBO) getBean("ep_notice_bo"); 
 
		Ep_noticeVO vo = new Ep_noticeVO(); 
 
		//���ɾ����¼���� 
		vo.setNotice_no(Integer.parseInt(((Ep_noticeForm)form).getNotice_no())); 
 		String FileName=((Ep_noticeForm)form).getFilename();
 		if(!FileName.equals("")){
			String filePath =SysConfig.getProperty("query.notice.path")+ File.separator + FileName;		
			if(!deleteFile(filePath)){
				throw new MessageException("ɾ���ļ�ʧ�ܣ�");
			}
 		}
		//����ҵ������delete��������,ִ������ɾ�� 
		bo.delete(vo, user); 
		
 		//NoteMap.ReloadDic();
		//ִ��ҳ����ת 
		return forwardSuccessPage(request,mapping,"����ɾ���ɹ���","Ep_notice.do?act=list"); 
	} 
 
 
	/** 
	 * ��ѯ��ϸ��Ϣ 
	 */ 
	public ActionForward retrieve(ActionMapping mapping,BaseForm form,HttpServletRequest request,HttpServletResponse response,UserContext user) throws Exception { 
 
		//���ҵ����� 
		BaseBO bo = (BaseBO) getBean("ep_notice_bo"); 
 
		Ep_noticeVO vo = new Ep_noticeVO(); 
 
		//��ò�ѯ���� 
		vo.setNotice_no(Integer.parseInt(((Ep_noticeForm)form).getNotice_no())); 
 
 
		//����ҵ������retrieve��������,��ѯ��ϸ��Ϣ 
		vo=(Ep_noticeVO)bo.retrieve(vo, user); 
 
 
 
		//���������д��form������ 
		copyProperties(form, vo); 
// 		((Ep_noticeForm)form).setNotice_no(String.valueOf(((Ep_noticeVO)vo).getNotice_no()));
//		((Ep_noticeForm)form).setNotice_comment(((Ep_noticeVO)vo).getNotice_comment());
		//ִ��ҳ����ת,���ص��б�ҳ�� 
		return mapping.findForward("view"); 
	} 
	public ActionForward pop(ActionMapping mapping,BaseForm form,HttpServletRequest request,HttpServletResponse response,UserContext user) throws Exception { 
 
		PageObject page = new PageObject(); 
		String pageNo = request.getParameter("pageNO"); 
 		boolean flag = false;
		String requery = request.getParameter("requery"); 
 
		if (pageNo == null || (requery != null && requery.trim().equals("y"))) { 
			page.setCurPage(1); 
		} else { 
			page.setCurPage(Integer.parseInt(pageNo)); 
		} 
 
		//�ڴ˴�ͨ��page.addFilter()������ӹ������� 
 		String t = request.getParameter("flag");
 		if(t != null && t.equals("1")){
 			flag = true;
 		}
 
		//���ҵ����� 
		Ep_noticeBO bo = (Ep_noticeBO) getBean("ep_notice_bo"); 
 
		//����ҵ������queryList��������,�����б��� 
		String s = bo.getNotice(flag);
 
		//���������д��request������ 
		request.setAttribute("info", s); 
 
			//ִ��ҳ����ת 
		return mapping.findForward("pop"); 
	}
	
	/** 
	 * �����ļ� 
	 */
	public ActionForward download(
		ActionMapping mapping,
		BaseForm form,
		HttpServletRequest request,
		HttpServletResponse response,
		UserContext user)
		throws Exception {

		String path = SysConfig.getProperty("query.notice.path");
		String requestFile = new String((request.getParameter("fileName")));

		path = path + File.separator;
		
		String realName = URLDecoder.decode(requestFile,"utf-8");
		String fileName = null;
		if(request.getHeader("User-Agent").toLowerCase().indexOf("firefox") != -1){
			fileName = new String(realName.getBytes("utf-8"),"iso8859-1");
		}else{
			fileName = URLEncoder.encode(realName, "UTF-8");
		}
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;filename=\"" + fileName + "\"");
		OutputStream os = null;
		FileInputStream fis = null;
		try{
			os = response.getOutputStream();
			fis = new FileInputStream(path+realName);
			byte[] b = new byte[1000];
			int i = 0;
			while((i=fis.read(b))!=-1){
				os.write(b,0,i);
			}
			
		}finally{
			if(os != null){
				os.flush();
				os.close();
			}
			if(fis != null){
				fis.close();
			}
		}
		
		
		//ִ��ҳ����ת
		return null;
	}
	public static boolean deleteFile(String fileName) {
	   File file = new File(fileName);
	   // ����ļ�·������Ӧ���ļ����ڣ�������һ���ļ�����ֱ��ɾ��
	   if(file.exists() && file.isFile()) {
	   	if(file.delete()) {
		   return true;
		} else {
		   return false;
		}
	   } else {
		  return false;
	   }
	}

 
 
} 

