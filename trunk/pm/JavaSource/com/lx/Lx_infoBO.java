 
package com.lx; 
 
import java.util.List; 
 
import com.eis.base.*; 
import com.eis.portal.UserContext; 
import com.eis.factory.*; 
import com.eis.util.*; 
 
 
/** 
 * ˵������ϵ��Ϣ��ҵ���߼��� 
 */ 
public class Lx_infoBO extends BaseBO { 
 
	/** 
	 * ���캯�� 
	 */ 
	public Lx_infoBO() { 
		super(); 
	} 
 
	/** 
	 * ������ϵ��Ϣ 
	 */ 
	public void add(BaseVO vo, UserContext user) throws Exception { 
 
		BaseDAO  dao = (BaseDAO)getBean("lx_info_dao"); 
		dao.add(vo); 
 
	} 
 
	/** 
	 * ����������ϵ��Ϣ 
	 */ 
	public void addList(BaseVO[] list, UserContext user) throws Exception { 
 
		BaseDAO  dao = (BaseDAO)getBean("lx_info_dao"); 
		dao.addList(list); 
 
	} 
 
	/** 
	 * �޸���ϵ��Ϣ 
	 */ 
	public void update(BaseVO vo, UserContext user) throws Exception { 
 
		BaseDAO  dao = (BaseDAO)getBean("lx_info_dao"); 
		dao.update(vo," where lx_id='"+((Lx_infoVO) vo).getLx_id()+"'"); 
 
	} 
 
	/** 
	 * �����޸���ϵ��Ϣ 
	 */ 
	public void updateList(BaseVO[] list, UserContext user) throws Exception { 
 
	} 
 
	/** 
	 * ɾ����ϵ��Ϣ 
	 */ 
	public void delete(BaseVO vo, UserContext user) throws Exception { 
 
		BaseDAO  dao = (BaseDAO)getBean("lx_info_dao"); 
		dao.delete(dao.getDeleteSQL()+" where lx_id='"+((Lx_infoVO) vo).getLx_id()+"'"); 
 
	} 
 
	/** 
	 * ����ɾ����ϵ��Ϣ 
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
 
		BaseDAO  dao = (BaseDAO)getBean("lx_info_dao"); 
		StringBuffer sql =new StringBuffer("select * from lx_info where 1=1 "); 
		//�ڴ˴����ģ��ƥ������ 
	

		 String name_f = (String)page.getFilter("name_f");
		 if (name_f != null && name_f.trim().length() > 0) {
			 sql.append(" and name like '" + name_f + "%'");
		 }
 
		//ִ�в�ѯ 
		page.setList(dao.queryPage(page,sql.toString())); 
		return page.getList(); 
 
	} 
	/** 
	 * ��ѯά�����ݣ����ݲ�ѯ�������ط���������һҳ��¼ 
	 */ 
	public List list(PageObject page, UserContext user) throws Exception { 
 		return null;
	} 
	/**
	 * ��ѯ�б���Ϣ
	 * 
	 * @param page
	 * @param form
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List list(PageObject page,BaseForm form ,UserContext user)throws Exception{
		BaseDAO  dao = (BaseDAO)getBean("lx_info_dao"); 
		StringBuffer sql = new StringBuffer("select * from lx_info where 1=1 ");
		sql.append(getQuerySql(form));
		page.setList(dao.queryPage(page,sql.toString()));
		return page.getList();
	}
	/**
	 * 
	 * ��ʼ����ѯ����
	 * @param form
	 * @return
	 */
	private StringBuffer getQuerySql(BaseForm form){
		Lx_infoForm lf = (Lx_infoForm)form;
		StringBuffer sb = new StringBuffer();
		if(!CheckUtil.isEmptry(lf.getName_f())){
			sb.append(" and name like '"+lf.getName_f()+"%' ");
		}
		if(!CheckUtil.isEmptry(lf.getDepart_f())){
			sb.append("and depart = '"+lf.getDepart_f()+"' ");
		}
		return sb;
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
 
		Lx_infoVO  bean = (Lx_infoVO)vo; 
		BaseDAO  dao = (BaseDAO)getBean("lx_info_dao"); 
 
		return dao.retrieve(dao.getQuerySQL()+" where lx_id='"+((Lx_infoVO) vo).getLx_id()+"'"); 
 
	} 
 
	/** 
	 * ����where������ѯ��ϸ��Ϣ�����ص�һ��¼ 
	 */ 
	public BaseVO retrieve(String whereClause, UserContext user) throws Exception { 
 
		BaseDAO  dao = (BaseDAO)getBean("lx_info_dao"); 
		return dao.retrieve(dao.getQuerySQL()+ whereClause); 
 
	} 
 
} 

