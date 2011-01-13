 
package com.eis.dic.sdic; 
 
import java.util.List; 
 
import com.eis.base.BaseBO; 
import com.eis.base.BaseVO; 
import com.eis.base.BaseDAO; 
import com.eis.base.PageObject; 
import com.eis.portal.UserContext; 
import com.eis.factory.*; 
import com.eis.util.*; 
 
 
/** 
 * ˵���������ֵ��ҵ���߼��� 
 */ 
public class SDicBO extends BaseBO { 
 
	/** 
	 * ���캯�� 
	 */ 
	public SDicBO() { 
		super(); 
	} 
 
	/** 
	 * ���ӵ����ֵ� 
	 */ 
	public void add(BaseVO vo, UserContext user) throws Exception { 
 
		BaseDAO  dao = (BaseDAO)getBean("sdic_dao"); 
		dao.add(vo); 
		
		
		//�Ǽ����ӵ����ֵ���ϸ������־		
		OpLog.Log(user,"03","c","���ӵ����ֵ���ϸ",Long.toString(((SDicVO) vo).getSys_id()));	
 
	} 
 
	/** 
	 * �������ӵ����ֵ� 
	 */ 
	public void addList(BaseVO[] list, UserContext user) throws Exception { 
 
		BaseDAO  dao = (BaseDAO)getBean("sdic_dao"); 
		dao.addList(list); 
 
	} 
 
	/** 
	 * �޸ĵ����ֵ� 
	 */ 
	public void update(BaseVO vo, UserContext user) throws Exception { 
 
		BaseDAO  dao = (BaseDAO)getBean("sdic_dao"); 
		dao.update(vo," where SYS_ID="+((SDicVO) vo).getSys_id()+""); 
		
		//�Ǽ��޸ĵ����ֵ���ϸ������־		
		OpLog.Log(user,"03","u","�޸ĵ����ֵ���ϸ",Long.toString(((SDicVO) vo).getSys_id()));
 
	} 
 
	/** 
	 * �����޸ĵ����ֵ� 
	 */ 
	public void updateList(BaseVO[] list, UserContext user) throws Exception { 
 
	} 
 
	/** 
	 * ɾ�������ֵ� 
	 */ 
	public void delete(BaseVO vo, UserContext user) throws Exception { 
 
		BaseDAO  dao = (BaseDAO)getBean("sdic_dao"); 
		dao.delete(dao.getDeleteSQL()+" where SYS_ID="+((SDicVO) vo).getSys_id()+""); 
		
		//�Ǽ�ɾ�������ֵ���ϸ������־		
		OpLog.Log(user,"03","d","ɾ�������ֵ���ϸ",Long.toString(((SDicVO) vo).getSys_id()));
 
	} 
 
	/** 
	 * ����ɾ�������ֵ� 
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
 
		BaseDAO  dao = (BaseDAO)getBean("sdic_dao"); 
		StringBuffer sql =new StringBuffer("select * from ep_sdic where 1=1 "); 
		//�ڴ˴����ģ��ƥ������ 
 
		//ִ�в�ѯ 
		page.setList(dao.queryPage(page,sql.toString())); 
		return page.getList(); 
 
	} 
 
	/** 
	 * ��ѯά�����ݣ����ݲ�ѯ�������ط���������һҳ��¼ 
	 */ 
	public List list(PageObject page, UserContext user) throws Exception { 
 
		BaseDAO  dao = (BaseDAO)getBean("sdic_dao"); 
		StringBuffer sql =new StringBuffer("select * from ep_sdic where 1=1 "); 
		
		//�ڴ˴����ģ��ƥ������ 
		String item_code= (String)page.getFilter("item_code_f");
		if(item_code!= null && item_code.trim().length()>0 ) 
		sql.append(" and ITEM_CODE like '%"+item_code+"%'");
		
		String item_val= (String)page.getFilter("item_val_f");
		if(item_val!= null && item_val.trim().length()>0 ) 
		sql.append(" and ITEM_VAL like '%"+item_val+"%'");
				
		String logic_id= (String)page.getFilter("logic_id_f");
		if(logic_id!= null && logic_id.trim().length()>0 ) 
		sql.append(" and LOGIC_ID like '%"+logic_id+"%'");
		
		String type_id= (String)page.getFilter("type_id");
		if(type_id!= null && type_id.trim().length()>0 ) 
		sql.append(" and TYPE_ID like '%"+type_id+"%'");
		
		sql.append(" order by LIST_ORDER ");
				
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
 
		SDicVO  bean = (SDicVO)vo; 
		BaseDAO  dao = (BaseDAO)getBean("sdic_dao");
		
		BaseVO v = dao.retrieve(dao.getQuerySQL()+" where SYS_ID="+((SDicVO) vo).getSys_id()+""); 
 			
		//�Ǽǲ鿴�����ֵ���ϸ������־		
		OpLog.Log(user,"03","r","�鿴�����ֵ���ϸ",Long.toString(((SDicVO) vo).getSys_id()));
		
		return v;
 
	} 
 
	/** 
	 * ����where������ѯ��ϸ��Ϣ�����ص�һ��¼ 
	 */ 
	public BaseVO retrieve(String whereClause, UserContext user) throws Exception { 
 
		BaseDAO  dao = (BaseDAO)getBean("sdic_dao"); 
		return dao.retrieve(dao.getQuerySQL()+ whereClause); 
 
	} 
 
} 

