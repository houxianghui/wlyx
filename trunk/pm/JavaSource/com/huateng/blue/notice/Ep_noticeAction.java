 
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
 * 说明：1的控制类 
 */ 
public class Ep_noticeAction extends BaseAction { 
 
	/** 
	 * 构造函数 
	 */ 
	public Ep_noticeAction() { 
		super(); 
	} 
 
	/** 
	 * 执行请求处理 
	 */ 
		public ActionForward process(ActionMapping mapping,BaseForm form,HttpServletRequest request,HttpServletResponse response,UserContext user) throws Exception { 
		String act = form.getAct(); 
		if (act.equals("c")) { //增加 
 
			String step = request.getParameter("step"); 
 
			if (null == step) { //初始化阶段，跳转到增加页面 
 
				return mapping.findForward("new"); 
 
			} else //用户已提交新增数据，执行数据保存 
 
				return add(mapping, form, request, response, user); 
 
		} else if (act.equals("u")) { //修改 
 
			String step = request.getParameter("step"); 
 
			if (null == step) { //初始化阶段，查询明细信息并跳转到修改页面 
 
				return editInfo(mapping, form, request, response, user); 
 
			} else //用户已提交修改后的数据，执行数据保存 
 
				return update(mapping, form, request, response, user); 
 
		} else if (act.equals("r")) { //查询明细信息 
 
			return retrieve(mapping, form, request, response, user); 
 
		} else if (act.equals("d")) { //删除数据 
 
			return delete(mapping, form, request, response, user); 
 
		} else if (act.equals("list")) { //返回维护列表 
 
			return list(mapping, form, request, response, user); 
 
		} else if(act.equals("pop")){
			
			return pop(mapping,form,request,response,user);
			
		} else if (act.equals("download")) { //下载文件 

			return download(mapping, form, request, response, user);

		} else
			return null; 
	} 
 
	/** 
	 * 查询维护列表 
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
 		
		//在此处通过page.addFilter()方法添加过滤条件 
 		page.addFilter("oper_id",((Ep_noticeForm)form).getOper_id_f());
 		page.addFilter("start_date",((Ep_noticeForm)form).getStart_date_f());
		page.addFilter("end_date",((Ep_noticeForm)form).getEnd_date_f());
		
		//获得业务对象 
		BaseBO bo = (BaseBO) getBean("ep_notice_bo"); 
 
		//调用业务对象的list（）方法,返回列表结果 
		bo.list(page, user); 
 
		//将结果对象写到request对象中 
		request.setAttribute("pageResult", page); 
 
		//执行页面跳转 
		return mapping.findForward("list"); 
	} 
 
 
	
 
	/** 
	 * 增加数据 
	 */ 
	public ActionForward add(ActionMapping mapping,BaseForm form,HttpServletRequest request,HttpServletResponse response,UserContext user) throws Exception { 
 
		//进行权限校验 
			if (!OpMap.checkRoleAuth(user.getRoleID(), "ep_notice_c")) { 
			BaseException e = new BaseException(); 
			e.setErrorCode("E020001"); 
			throw e; 
		} 
 		String s = new String(((Ep_noticeForm)form).getNotice_comment().getBytes(),"gbk");
 		
 		
		//获得业务对象 
		BaseBO bo = (BaseBO) getBean("ep_notice_bo"); 
 
		Ep_noticeVO vo = new Ep_noticeVO(); 

		//进行数据传输 
		copyProperties(vo, form); 
		vo.setOper_id(user.getUserID());
		vo.setOper_date(DateUtil.getDTStr());
		FormFile file = ((Ep_noticeForm)form).getFile();
		String FileName = file.getFileName();
		if(!FileName.equals("")){
			if(file.getFileSize()==0){
				throw new MessageException("内容不能为空");

			}else{
			
				vo.setFilename(FileName);			
//				获得文件的绝对路径
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
		//在此处进行其它字段的赋值，例如：vo.setReg_dt(DateUtil.getDTStr()); 
		//调用业务对象的add（）方法,返回列表结果 
		bo.add(vo, user); 
 		//NoteMap.ReloadDic();
		//执行页面跳转,返回到列表页面 
		return new ActionRedirect("Ep_notice.do?act=list"); 
	} 
 
 
	/** 
	 * 查询维护信息明细 
	 */ 
	public ActionForward editInfo(ActionMapping mapping,BaseForm form,HttpServletRequest request,HttpServletResponse response,UserContext user) throws Exception { 
 
		//获得业务对象 
		BaseBO bo = (BaseBO) getBean("ep_notice_bo"); 
 
		Ep_noticeVO vo = new Ep_noticeVO(); 
 
		//获得查询主键 
		vo.setNotice_no(Integer.parseInt(((Ep_noticeForm)form).getNotice_no())); 
 
 
		//调用业务对象的retrieve（）方法,查询明细信息 
		vo=(Ep_noticeVO)bo.retrieve(vo, user); 
 
 
 
		//将结果对象写到form对象中 
		copyProperties(form, vo); 
 
		//执行页面跳转,返回到列表页面 
		return mapping.findForward("edit"); 
	} 
 
 
	/** 
	 * 修改数据 
	 */ 
	public ActionForward update(ActionMapping mapping,BaseForm form,HttpServletRequest request,HttpServletResponse response,UserContext user) throws Exception { 
 
		//进行权限校验 
		if (!OpMap.checkRoleAuth(user.getRoleID(), "ep_notice_c")) { 
			BaseException e = new BaseException(); 
			e.setErrorCode("E020001"); 
			throw e; 
		} 
 
		//获得业务对象 
		BaseBO bo = (BaseBO) getBean("ep_notice_bo"); 
 
		Ep_noticeVO vo = new Ep_noticeVO(); 
 
		//在此处进行其它字段的赋值，例如：form.setReg_dt(DateUtil.getDTStr()); 
 
		//进行数据传输 
		copyProperties(vo, form); 
 
		//调用业务对象的update（）方法,返回列表结果 
		bo.update(vo, user); 
 		//NoteMap.ReloadDic();
		//传递数据保存结果 
		request.setAttribute("success", "y"); 
 
		//执行页面跳转 
		return forwardSuccessPage(request,mapping,"数据修改成功！","Ep_notice.do?act=list");  
	} 
 
 
	/** 
	 * 删除数据 
	 */ 
	public ActionForward delete(ActionMapping mapping,BaseForm form,HttpServletRequest request,HttpServletResponse response,UserContext user) throws Exception { 
 
		//进行权限校验
		if (!OpMap.checkRoleAuth(user.getRoleID(), "ep_notice_c")) { 
			BaseException e = new BaseException(); 
			e.setErrorCode("E020001"); 
			throw e; 
		} 
 
		//获得业务对象 
		BaseBO bo = (BaseBO) getBean("ep_notice_bo"); 
 
		Ep_noticeVO vo = new Ep_noticeVO(); 
 
		//获得删除纪录主键 
		vo.setNotice_no(Integer.parseInt(((Ep_noticeForm)form).getNotice_no())); 
 		String FileName=((Ep_noticeForm)form).getFilename();
 		if(!FileName.equals("")){
			String filePath =SysConfig.getProperty("query.notice.path")+ File.separator + FileName;		
			if(!deleteFile(filePath)){
				throw new MessageException("删除文件失败！");
			}
 		}
		//调用业务对象的delete（）方法,执行数据删除 
		bo.delete(vo, user); 
		
 		//NoteMap.ReloadDic();
		//执行页面跳转 
		return forwardSuccessPage(request,mapping,"数据删除成功！","Ep_notice.do?act=list"); 
	} 
 
 
	/** 
	 * 查询明细信息 
	 */ 
	public ActionForward retrieve(ActionMapping mapping,BaseForm form,HttpServletRequest request,HttpServletResponse response,UserContext user) throws Exception { 
 
		//获得业务对象 
		BaseBO bo = (BaseBO) getBean("ep_notice_bo"); 
 
		Ep_noticeVO vo = new Ep_noticeVO(); 
 
		//获得查询主键 
		vo.setNotice_no(Integer.parseInt(((Ep_noticeForm)form).getNotice_no())); 
 
 
		//调用业务对象的retrieve（）方法,查询明细信息 
		vo=(Ep_noticeVO)bo.retrieve(vo, user); 
 
 
 
		//将结果对象写到form对象中 
		copyProperties(form, vo); 
// 		((Ep_noticeForm)form).setNotice_no(String.valueOf(((Ep_noticeVO)vo).getNotice_no()));
//		((Ep_noticeForm)form).setNotice_comment(((Ep_noticeVO)vo).getNotice_comment());
		//执行页面跳转,返回到列表页面 
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
 
		//在此处通过page.addFilter()方法添加过滤条件 
 		String t = request.getParameter("flag");
 		if(t != null && t.equals("1")){
 			flag = true;
 		}
 
		//获得业务对象 
		Ep_noticeBO bo = (Ep_noticeBO) getBean("ep_notice_bo"); 
 
		//调用业务对象的queryList（）方法,返回列表结果 
		String s = bo.getNotice(flag);
 
		//将结果对象写到request对象中 
		request.setAttribute("info", s); 
 
			//执行页面跳转 
		return mapping.findForward("pop"); 
	}
	
	/** 
	 * 下载文件 
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
		
		
		//执行页面跳转
		return null;
	}
	public static boolean deleteFile(String fileName) {
	   File file = new File(fileName);
	   // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
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

