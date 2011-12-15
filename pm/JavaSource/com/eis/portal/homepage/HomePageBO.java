 
package com.eis.portal.homepage; 
 
import java.util.List; 
 
import com.eis.base.BaseBO; 
import com.eis.base.BaseVO; 
import com.eis.base.BaseDAO; 
import com.eis.base.PageObject; 
import com.eis.portal.UserContext; 
import com.eis.factory.*; 
import com.eis.util.*; 
 
 
/** 
 * ˵������ҳģ���ҵ���߼��� 
 */ 
public class HomePageBO extends BaseBO { 
 
	/** 
	 * ���캯�� 
	 */ 
	public HomePageBO() { 
		super(); 
	} 
 
	/** 
	 * ������ҳģ�� 
	 */ 
	public void add(BaseVO vo, UserContext user) throws Exception { 
 
		BaseDAO  dao = (BaseDAO)getBean("homepage_dao"); 
		dao.add(vo); 
		
		//��¼������־
		OpLog.Log(user,"03","c","������ҳģ��",Integer.toString(((HomePageVO)vo).getTempl_id()));

 
	} 
 
	/** 
	 * ����������ҳģ�� 
	 */ 
	public void addList(BaseVO[] list, UserContext user) throws Exception { 
 
		BaseDAO  dao = (BaseDAO)getBean("homepage_dao"); 
		dao.addList(list); 
 
	} 
 
	/** 
	 * �޸���ҳģ�� 
	 */ 
	public void update(BaseVO vo, UserContext user) throws Exception { 
 
		BaseDAO  dao = (BaseDAO)getBean("homepage_dao"); 
		dao.update(vo," where TEMPL_ID="+((HomePageVO) vo).getTempl_id()+""); 
		//��¼������־
		OpLog.Log(user,"03","u","�޸���ҳģ��",Integer.toString(((HomePageVO)vo).getTempl_id()));
 
	} 
 
	/** 
	 * �����޸���ҳģ�� 
	 */ 
	public void updateList(BaseVO[] list, UserContext user) throws Exception { 
 
	} 
 
	/** 
	 * ɾ����ҳģ�� 
	 */ 
	public void delete(BaseVO vo, UserContext user) throws Exception { 
 
		BaseDAO  dao = (BaseDAO)getBean("homepage_dao"); 
		dao.delete(dao.getDeleteSQL()+" where TEMPL_ID="+((HomePageVO) vo).getTempl_id()+""); 
		//��¼������־
		OpLog.Log(user,"03","d","ɾ����ҳģ��",Integer.toString(((HomePageVO)vo).getTempl_id()));
 
	} 
 
	/** 
	 * ����ɾ����ҳģ�� 
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
 
		BaseDAO  dao = (BaseDAO)getBean("homepage_dao"); 
		StringBuffer sql =new StringBuffer("select * from ep_homepage where 1=1 "); 
		//�ڴ˴����ģ��ƥ������ 
 
		//ִ�в�ѯ 
		page.setList(dao.queryPage(page,sql.toString())); 
		return page.getList(); 
 
	} 
 
	/** 
	 * ��ѯά�����ݣ����ݲ�ѯ�������ط���������һҳ��¼ 
	 */ 
	public List list(PageObject page, UserContext user) throws Exception { 
 
		BaseDAO  dao = (BaseDAO)getBean("homepage_dao"); 
		StringBuffer sql =new StringBuffer(dao.getListSQL()+" where 1=1 "); 
		
		//�ڴ˴����ģ��ƥ������ 		
		String caption = (String)page.getFilter("caption");
		
		if(caption != null) 
		sql.append(" and CAPTION like '%"+caption+"%'");
		sql.append(dao.getOrderBy());
		
		
		SysLog.debug(sql.toString());
		
		
		//ִ�в�ѯ 
		page.setList(dao.queryPage(page,sql.toString())); 
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
 
		HomePageVO  bean = (HomePageVO)vo; 
		BaseDAO  dao = (BaseDAO)getBean("homepage_dao"); 
		
		BaseVO v = dao.retrieve(dao.getQuerySQL()+" where TEMPL_ID="+((HomePageVO) vo).getTempl_id()+""); 
		
		//��¼������־
		OpLog.Log(user,"03","r","�鿴��ҳģ��",Integer.toString(((HomePageVO)vo).getTempl_id())); 
 
		return v;
 
	} 
 
	/** 
	 * ����where������ѯ��ϸ��Ϣ�����ص�һ��¼ 
	 */ 
	public BaseVO retrieve(String whereClause, UserContext user) throws Exception { 
 
		BaseDAO  dao = (BaseDAO)getBean("homepage_dao"); 
		return dao.retrieve(dao.getQuerySQL()+ whereClause); 
 
	} 
 
} 

