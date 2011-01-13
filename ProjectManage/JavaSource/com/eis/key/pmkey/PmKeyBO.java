 
package com.eis.key.pmkey; 
 
import java.util.List; 
 
import com.eis.base.BaseBO; 
import com.eis.base.BaseVO; 
import com.eis.base.BaseDAO; 
import com.eis.base.PageObject; 
import com.eis.portal.UserContext; 
import com.eis.factory.*; 
import com.eis.util.*; 
 
 
/** 
 * ˵�����������õ�ҵ���߼��� 
 */ 
public class PmKeyBO extends BaseBO { 
 
	/** 
	 * ���캯�� 
	 */ 
	public PmKeyBO() { 
		super(); 
	} 
 
	/** 
	 * ������������ 
	 */ 
	public void add(BaseVO vo, UserContext user) throws Exception { 
 
		BaseDAO  dao = (BaseDAO)getBean("pmkey_dao"); 
		dao.add(vo); 
 
	} 
 
	/** 
	 * ���������������� 
	 */ 
	public void addList(BaseVO[] list, UserContext user) throws Exception { 
 
		BaseDAO  dao = (BaseDAO)getBean("pmkey_dao"); 
		dao.addList(list); 
 
	} 
 
	/** 
	 * �޸��������� 
	 */ 
	public void update(BaseVO vo, UserContext user) throws Exception { 
 
		BaseDAO  dao = (BaseDAO)getBean("pmkey_dao"); 
		dao.update(vo," where TB_NAME='"+((PmKeyVO) vo).getTb_name()+"'"); 
 
	} 
 
	/** 
	 * �����޸��������� 
	 */ 
	public void updateList(BaseVO[] list, UserContext user) throws Exception { 
 
	} 
 
	/** 
	 * ɾ���������� 
	 */ 
	public void delete(BaseVO vo, UserContext user) throws Exception { 
 
		BaseDAO  dao = (BaseDAO)getBean("pmkey_dao"); 
		dao.delete(dao.getDeleteSQL()+" where TB_NAME='"+((PmKeyVO) vo).getTb_name()+"'"); 
 
	} 
 
	/** 
	 * ����ɾ���������� 
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
 
		BaseDAO  dao = (BaseDAO)getBean("pmkey_dao"); 
		StringBuffer sql =new StringBuffer("select * from ep_pmkey where 1=1 "); 
		//�ڴ˴����ģ��ƥ������ 
 
		//ִ�в�ѯ 
		page.setList(dao.queryPage(page,sql.toString())); 
		return page.getList(); 
 
	} 
 
	/** 
	 * ��ѯά�����ݣ����ݲ�ѯ�������ط���������һҳ��¼ 
	 */ 
	public List list(PageObject page, UserContext user) throws Exception { 
 
		BaseDAO  dao = (BaseDAO)getBean("pmkey_dao"); 
		StringBuffer sql =new StringBuffer("select * from ep_pmkey where 1=1 "); 
		
		//�ڴ˴����ģ��ƥ������ 
		String tb_name= (String)page.getFilter("tb_name_f");
		if(tb_name!= null) 
		sql.append(" and TB_NAME like '%"+tb_name+"%'");
		
 
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
 
		PmKeyVO  bean = (PmKeyVO)vo; 
		BaseDAO  dao = (BaseDAO)getBean("pmkey_dao"); 
 
		return dao.retrieve(dao.getQuerySQL()+" where TB_NAME='"+((PmKeyVO) vo).getTb_name()+"'"); 
 
	} 
 
	/** 
	 * ����where������ѯ��ϸ��Ϣ�����ص�һ��¼ 
	 */ 
	public BaseVO retrieve(String whereClause, UserContext user) throws Exception { 
 
		BaseDAO  dao = (BaseDAO)getBean("pmkey_dao"); 
		return dao.retrieve(dao.getQuerySQL()+ whereClause); 
 
	} 
 
} 

