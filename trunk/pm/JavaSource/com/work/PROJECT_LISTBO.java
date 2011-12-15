 
package com.work; 
 
import java.util.List; 
 
import com.eis.base.*; 
import com.eis.portal.UserContext;
import com.eis.util.CheckUtil;
 
 
/** 
 * ˵���������б��ҵ���߼��� 
 */ 
public class PROJECT_LISTBO extends BaseBO { 
 
	/** 
	 * ���캯�� 
	 */ 
	public PROJECT_LISTBO() { 
		super(); 
	} 
	 /*
	 * @see com.eis.base.BusinessService#queryList(com.eis.base.PageObject, com.eis.portal.UserContext)
	 */
	public List queryList(PageObject page, UserContext user) throws Exception {
	    return null;
	}

	/** 
	 * ���ӹ����б� 
	 */ 
	public void add(BaseVO vo, UserContext user) throws Exception { 
 
		BaseDAO  dao = (BaseDAO)getBean("project_list_dao"); 
		dao.add(vo); 
 
	} 
 
	/** 
	 * �������ӹ����б� 
	 */ 
	public void addList(BaseVO[] list, UserContext user) throws Exception { 
 
		BaseDAO  dao = (BaseDAO)getBean("project_list_dao"); 
		dao.addList(list); 
 
	} 
 
	/** 
	 * �޸Ĺ����б� 
	 */ 
	public void update(BaseVO vo, UserContext user) throws Exception { 
 
		BaseDAO  dao = (BaseDAO)getBean("project_list_dao"); 
		dao.update(vo," where USER_ID='"+((PROJECT_LISTVO) vo).getUser_id()+"'"+" and PROJECT_NO='"+((PROJECT_LISTVO) vo).getProject_no()+"'"); 
 
	} 
 
	/** 
	 * �����޸Ĺ����б� 
	 */ 
	public void updateList(BaseVO[] list, UserContext user) throws Exception { 
 
	} 
 
	/** 
	 * ɾ�������б� 
	 */ 
	public void delete(BaseVO vo, UserContext user) throws Exception { 
 
		BaseDAO  dao = (BaseDAO)getBean("project_list_dao"); 
		dao.delete("update PROJECT_LIST set CURR_STEP='"+PROJECT_LISTVO.DEL+"' where USER_ID='"+((PROJECT_LISTVO) vo).getUser_id()+"'"+" and PROJECT_NO='"+((PROJECT_LISTVO) vo).getProject_no()+"'"); 
 
	} 
 
	/** 
	 * ����ɾ�������б� 
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
	public List queryList(BaseForm form, PageObject page, UserContext user) throws Exception { 
 
		BaseDAO  dao = (BaseDAO)getBean("project_list_dao"); 
		StringBuffer sql =new StringBuffer("select * from PROJECT_LIST where 1=1 and USER_ID='"+user.getUserID()+"' "); 
		//�ڴ˴����ģ��ƥ������ 
 		initQuery(form,sql);
		//ִ�в�ѯ 
		sql.append(" order by PROJECT_NO");
		page.setList(dao.queryPage(page,sql.toString())); 
		return page.getList(); 
 
	} 
	/**
     * @author houxh 2007-12-27
     * ��ʼ����ѯ����
     * 
     * @param form
     * @param sql 
     */
    public void initQuery(BaseForm form,StringBuffer sql){
		PROJECT_LISTForm pform = (PROJECT_LISTForm)form;
		if(!CheckUtil.isEmptry(pform.getCurr_step_f())){
			sql.append(" and CURR_STEP = '"+pform.getCurr_step_f()+"'");
		}
		if(!CheckUtil.isEmptry(pform.getProject_no_f())){
			sql.append(" and PROJECT_NO like '"+pform.getProject_no_f()+"%'");
		}
	}
 
	/** 
	 * ��ѯά�����ݣ����ݲ�ѯ�������ط���������һҳ��¼ 
	 */ 
	public List list(PageObject page, UserContext user) throws Exception { 
 
		BaseDAO  dao = (BaseDAO)getBean("project_list_dao"); 
		StringBuffer sql =new StringBuffer("select * from PROJECT_LIST where 1=1 and USER_ID='"+user.getUserID()+"' and CURR_STEP not in('"+PROJECT_LISTVO.PRD+"','"+PROJECT_LISTVO.DEL+"') "); 
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
 
		BaseDAO  dao = (BaseDAO)getBean("project_list_dao"); 
 
		return dao.retrieve(dao.getQuerySQL()+" where USER_ID='"+((PROJECT_LISTVO) vo).getUser_id()+"'"+" and PROJECT_NO='"+((PROJECT_LISTVO) vo).getProject_no()+"'"); 
 
	} 
 
	/** 
	 * ����where������ѯ��ϸ��Ϣ�����ص�һ��¼ 
	 */ 
	public BaseVO retrieve(String whereClause, UserContext user) throws Exception { 
 
		BaseDAO  dao = (BaseDAO)getBean("project_list_dao"); 
		return dao.retrieve(dao.getQuerySQL()+ whereClause); 
 
	} 
 
} 

