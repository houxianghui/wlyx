 
package com.huateng.blue.notice;  
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List; 
 
import com.eis.base.*; 
import com.eis.portal.UserContext; 
import com.eis.factory.*; 
import com.eis.util.*;
 
 
/** 
 * ˵����1��ҵ���߼��� 
 */ 
public class Ep_noticeBO extends BaseBO { 
	private static HashMap map = new HashMap();
 	private static String s = "";
 	private boolean flag = false;
	/** 
	 * ���캯�� 
	 */ 
	public Ep_noticeBO() { 
		super(); 
	} 
 
	/** 
	 * ����1 
	 */ 
	public void add(BaseVO vo, UserContext user) throws Exception { 
 
		BaseDAO  dao = (BaseDAO)getBean("ep_notice_dao"); 
		dao.add(vo); 
 
	} 
 
	/** 
	 * ��������1 
	 */ 
	public void addList(BaseVO[] list, UserContext user) throws Exception { 
 
		BaseDAO  dao = (BaseDAO)getBean("ep_notice_dao"); 
		dao.addList(list); 
 
	} 
 
	/** 
	 * �޸�1 
	 */ 
	public void update(BaseVO vo, UserContext user) throws Exception { 
 
		BaseDAO  dao = (BaseDAO)getBean("ep_notice_dao"); 
		dao.update(vo," where NOTICE_NO="+((Ep_noticeVO) vo).getNotice_no()+""); 
 
	} 
 
	/** 
	 * �����޸�1 
	 */ 
	public void updateList(BaseVO[] list, UserContext user) throws Exception { 
 
	} 
 
	/** 
	 * ɾ��1 
	 */ 
	public void delete(BaseVO vo, UserContext user) throws Exception { 
 
		BaseDAO  dao = (BaseDAO)getBean("ep_notice_dao"); 
		dao.delete(dao.getDeleteSQL()+" where NOTICE_NO="+((Ep_noticeVO) vo).getNotice_no()+""); 
 
	} 
 
	/** 
	 * ����ɾ��1 
	 */ 
	public void deleteList(BaseVO[] list, UserContext user) throws Exception { 
 
	} 
 
	/** 
	 * ��ѯ�б�û�в�ѯ�������������м�¼ 
	 */ 
	public List queryList(UserContext user) throws Exception { 
 
		return null; 
 
	} 
 
	/** 
	 * ��ѯ�б����ݲ�ѯ�������ط���������һҳ��¼ 
	 */ 
	public List queryList(PageObject page, UserContext user) throws Exception { 
 
		BaseDAO  dao = (BaseDAO)getBean("ep_notice_dao"); 
		StringBuffer sql =new StringBuffer("select * from ep_notice where 1=1 "); 
		//�ڴ˴����ģ��ƥ������ 
 
		//ִ�в�ѯ 
		page.setList(dao.queryPage(page,sql.toString())); 
		return page.getList(); 
 
	} 
 
	/** 
	 * ��ѯά�����ݣ����ݲ�ѯ�������ط���������һҳ��¼ 
	 */ 
	public List list(PageObject page, UserContext user) throws Exception { 
 
		BaseDAO  dao = (BaseDAO)getBean("ep_notice_dao"); 
		StringBuffer sql =new StringBuffer("select ep_notice.* from ep_notice "); 
		//ִ�в�ѯ 
		String oper_name = (String)page.getFilter("oper_id");
		if(oper_name != null && oper_name.trim().length() > 0){
			sql.append(",ep_user where ep_user.USER_NAME='"+oper_name+"' and ep_user.AMSD_STORE='000000000' and ep_user.USER_ID=ep_notice.OPER_ID ");
		}else{
			sql.append(" where 1=1 ");
		}
		String start_date = (String)page.getFilter("start_date");
		if(start_date != null && start_date.trim().length() > 0){
			sql.append(" and OPER_DATE>='"+start_date+"' ");
		}
		String end_date = (String)page.getFilter("end_date");
		if(end_date != null && end_date.trim().length() > 0){
			sql.append(" and OPER_DATE<='"+end_date+"' ");
		}
		page.setList(dao.queryPage(page,sql.toString()+" order by NOTICE_TOP desc,OPER_DATE desc")); 
		return page.getList(); 
 
	} 
 
	/** 
	 * ��ѯά�����ݣ��������м�¼ 
	 */ 
	public List list(UserContext user) throws Exception { 
 
		return null; 
 
	} 
 
	/** 
	 * ��ѯ��ϸ��Ϣ�����ص�һ��¼ 
	 */ 
	public BaseVO retrieve(BaseVO vo, UserContext user) throws Exception { 
 
		Ep_noticeVO  bean = (Ep_noticeVO)vo; 
		BaseDAO  dao = (BaseDAO)getBean("ep_notice_dao"); 
 
		return dao.retrieve(dao.getQuerySQL()+" where NOTICE_NO="+((Ep_noticeVO) vo).getNotice_no()+""); 
 
	} 
	

 
	/** 
	 * ����where������ѯ��ϸ��Ϣ�����ص�һ��¼ 
	 */ 
	public BaseVO retrieve(String whereClause, UserContext user) throws Exception { 
 
		BaseDAO  dao = (BaseDAO)getBean("ep_notice_dao"); 
		return dao.retrieve(dao.getQuerySQL()+ whereClause); 
 
	} 
	public String getNotice(boolean flag)throws Exception{
		HashMap map = new HashMap();
		BaseDAO  dao = (BaseDAO)getBean("ep_notice_dao");
		List l = dao.queryList("select * from ep_notice order by NOTICE_TOP desc,OPER_DATE desc");
		Iterator it = l.iterator();
		Ep_noticeVO vo = null;
		StringBuffer s = new StringBuffer();
		int i = 1;
		while(it.hasNext()){
			vo = (Ep_noticeVO)it.next();
			s.append(i+++"��"+vo.getNotice_comment());
			if(!vo.getFilename().equals("")){
				s.append("<a href='Ep_notice.do?act=download&fileName="+URLEncoder.encode(URLEncoder.encode(vo.getFilename(),"utf-8"),"utf-8")+"'>"+vo.getFilename()+"</a>");
			}
			int dates=(int)DateUtil.getDays(DateUtil.parseDate(vo.getOper_date()),(DateUtil.parseDate(DateUtil.getDTStr())));
			if(dates<=7){
				s.append("<img src='images/new.gif'></img>");
			}
			s.append("<br>");
			
			if(i == 6){
				map.put("five",s.toString()+"<a href='Ep_notice.do?act=pop&flag=1'>����>>..</a><br>");		
			}
		}
		map.put("more",s.toString());
		while(it.hasNext()){
			vo = (Ep_noticeVO)it.next();
		}
		if(flag == true || i <= 6){
			return map.get("more").toString();
		}else
		return map.get("five").toString();
	}

 
} 

