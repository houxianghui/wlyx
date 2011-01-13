 
package com.eis.portal.userlogin; 
 
import java.util.List; 
 
import com.eis.base.BaseBO; 
import com.eis.base.BaseVO; 
import com.eis.base.BaseDAO; 
import com.eis.base.PageObject; 
import com.eis.portal.UserContext; 
import com.eis.factory.*; 
import com.eis.util.*; 
 
 
/** 
 * ˵���������û���¼��¼���ҵ���߼��� 
 */ 
public class UserLoginBO extends BaseBO { 
 
	/** 
	 * ���캯�� 
	 */ 
	public UserLoginBO() { 
		super(); 
	} 
 
	/** 
	 * ���ӹ����û���¼��¼�� 
	 */ 
	public void add(BaseVO vo, UserContext user) throws Exception { 
 
		BaseDAO  dao = (BaseDAO)getBean("userlogin_dao"); 
		dao.add(vo); 
 
	} 
 
	/** 
	 * �������ӹ����û���¼��¼�� 
	 */ 
	public void addList(BaseVO[] list, UserContext user) throws Exception { 
 
		BaseDAO  dao = (BaseDAO)getBean("userlogin_dao"); 
		dao.addList(list); 
 
	} 
 
	/** 
	 * �޸Ĺ����û���¼��¼�� 
	 */ 
	public void update(BaseVO vo, UserContext user) throws Exception { 
 
		BaseDAO  dao = (BaseDAO)getBean("userlogin_dao"); 
		dao.update(vo," where USER_ID='"+((UserLoginVO) vo).getUser_id()+"'"); 
 
	} 
 
	/** 
	 * �����޸Ĺ����û���¼��¼�� 
	 */ 
	public void updateList(BaseVO[] list, UserContext user) throws Exception { 
 
	} 
 
	/** 
	 * ɾ�������û���¼��¼�� 
	 */ 
	public void delete(BaseVO vo, UserContext user) throws Exception { 
 
		BaseDAO  dao = (BaseDAO)getBean("userlogin_dao"); 
		dao.delete(dao.getDeleteSQL()+" where USER_ID='"+((UserLoginVO) vo).getUser_id()+"'"); 
 
	} 
 
	/** 
	 * ����ɾ�������û���¼��¼�� 
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
 
		BaseDAO  dao = (BaseDAO)getBean("userlogin_dao"); 
		StringBuffer sql =new StringBuffer("select * from ep_user_login where 1=1 "); 
		//�ڴ˴����ģ��ƥ������ 
		String user_id = (String) page.getFilter("user_id_f");
		if (user_id != null)
			sql.append(" and USER_ID like '%" + user_id + "%'");
			
		SysLog.debug(sql.toString());
		//ִ�в�ѯ 
		page.setList(dao.queryPage(page,sql.toString())); 
		return page.getList(); 
 
	} 
 
	/** 
	 * ��ѯά�����ݣ����ݲ�ѯ�������ط���������һҳ��¼ 
	 */ 
	public List list(PageObject page, UserContext user) throws Exception { 
 
		BaseDAO  dao = (BaseDAO)getBean("userlogin_dao"); 
		StringBuffer sql =new StringBuffer("select * from ep_user_login where 1=1 "); 
		//�ڴ˴����ģ��ƥ������ 
		String user_id = (String) page.getFilter("user_id_f");
		if (user_id != null)
			sql.append(" and USER_ID like '%" + user_id + "%'");
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
 
		UserLoginVO  bean = (UserLoginVO)vo; 
		BaseDAO  dao = (BaseDAO)getBean("userlogin_dao"); 
 
		return dao.retrieve(dao.getQuerySQL()+" where USER_ID='"+((UserLoginVO) vo).getUser_id()+"'"); 
 
	} 
 
	/** 
	 * ����where������ѯ��ϸ��Ϣ�����ص�һ��¼ 
	 */ 
	public BaseVO retrieve(String whereClause, UserContext user) throws Exception { 
 
		BaseDAO  dao = (BaseDAO)getBean("userlogin_dao"); 
		return dao.retrieve(dao.getQuerySQL()+ whereClause); 
 
	} 
 
} 

