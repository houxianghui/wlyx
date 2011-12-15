 
package com.work; 
 
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List; 
 
import com.eis.base.*; 
import com.eis.exception.MessageException;
import com.eis.portal.UserContext;
import com.eis.util.CheckUtil;
 
 
/** 
 * ˵���������б��ҵ���߼��� 
 */ 
public class TASK_LISTBO extends BaseBO { 
 
	/** 
	 * ���캯�� 
	 */ 
	public TASK_LISTBO() { 
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
 
		BaseDAO  dao = (BaseDAO)getBean("task_list_dao"); 
		dao.add(vo); 
 
	} 
 
	/** 
	 * �������ӹ����б� 
	 */ 
	public void addList(BaseVO[] list, UserContext user) throws Exception { 
 
		BaseDAO  dao = (BaseDAO)getBean("task_list_dao"); 
		dao.addList(list); 
 
	} 
 
	/** 
	 * �޸Ĺ����б� 
	 */ 
	public void update(BaseVO vo, UserContext user) throws Exception { 
 
		BaseDAO  dao = (BaseDAO)getBean("task_list_dao"); 
		dao.update(vo," where TASK_NO='"+((TASK_LISTVO) vo).getTask_no()+"'"); 
 
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
 
		BaseDAO  dao = (BaseDAO)getBean("task_list_dao"); 
		dao.delete(dao.getDeleteSQL()+" where TASK_NO='"+((TASK_LISTVO) vo).getTask_no()+"'"); 
 
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
 
		BaseDAO  dao = (BaseDAO)getBean("task_list_dao"); 
		StringBuffer sql =new StringBuffer("select 'P' as TYPE,TASK_NO," +
				"PROJECT_NO," +
				"TASK_DATE," +
				"TASK_COST," +
				"TASK_USER," +
				"TASK_MEMO," +
				"TASK_STEP," +
				"TASK_GOAL," +
				"UPDATE_DATE," +
				"TASK_TYPE " +
				"from TASK_LIST where 1=1 and TASK_USER = '"+user.getUserID()+"' "); 
		StringBuffer tsql = new StringBuffer("select 'T' as TYPE, ID as TASK_NO," +
				"ifnull(WORK_ID,'') as WORK_ID," +
				"WORK_DATE as TASK_DATE," +
				"'' as TASK_COST," +
				"USER_ID as TASK_USER," +
				"CONTENT as TASK_MEMO," +
				"'' as TASK_STEP," +
				"'' as TASK_GOAL," +
				"INPUT_DATE as UPDATE_DATE," +
				"'' as TASK_TYPE " +
				"from work_daily where 1=1 " );
		//�ڴ˴����ģ��ƥ������ 
 		initQuery(form,sql,tsql);
 		sql.append(" union ");
		sql.append(tsql.append(" group by WORK_DATE"));
		
		sql.append(" order by TASK_DATE");
		//ִ�в�ѯ 
		page.setList(dao.queryPage(page,sql.toString())); 
		return page.getList(); 
 
	}
	public List queryListAll(BaseForm form, PageObject page) throws Exception { 
 
		BaseDAO  dao = (BaseDAO)getBean("task_list_dao"); 
		StringBuffer psql =new StringBuffer("select 'P' as TYPE,TASK_NO," +
				"PROJECT_NO," +
				"TASK_DATE," +
				"TASK_COST," +
				"TASK_USER," +
				"TASK_MEMO," +
				"TASK_STEP," +
				"TASK_GOAL," +
				"UPDATE_DATE," +
				"TASK_TYPE " +
				"from TASK_LIST,ep_user where 1=1 and TASK_USER=USER_ID "); 
		StringBuffer tsql = new StringBuffer("select 'T' as TYPE, ID as TASK_NO," +
				"ifnull(WORK_ID,'') as PROJECT_NO," +
				"WORK_DATE as TASK_DATE," +
				"'' as TASK_COST," +
				"USER_ID as TASK_USER," +
				"CONTENT as TASK_MEMO," +
				"'' as TASK_STEP," +
				"'' as TASK_GOAL," +
				"INPUT_DATE as UPDATE_DATE," +
				"'' as TASK_TYPE " +
				"from work_daily where 1=1 " );
		//�ڴ˴����ģ��ƥ������ 
		boolean flag = initQuery(form,psql,tsql);
		if(!CheckUtil.isEmptry(((TASK_LISTForm)form).getTask_user_f())){
			psql.append(" and TASK_LIST.TASK_USER like '"+((TASK_LISTForm)form).getTask_user_f()+"%' ");
			tsql.append(" and USER_ID like '"+((TASK_LISTForm)form).getTask_user_f()+"%' ");
		}else{
			if(!flag){
				psql.append(" and 1=2");
				tsql.append(" and 1=2");
			}
		}
		psql.append(" union "+tsql.append(" group by WORK_DATE"));
		psql.append(" order by TASK_STEP,TASK_USER,TASK_DATE");
		//ִ�в�ѯ 
		page.setList(dao.queryPage(page,psql.toString())); 
		return page.getList(); 
 
	}
	/**
     * @author houxh 2007-12-27
     * ��ʼ����ѯ���
     * 
     * @param form
     * @param sql 
     */
    public boolean initQuery(BaseForm form,StringBuffer sql,StringBuffer tsql){
    	boolean flag = false;
		TASK_LISTForm tform = (TASK_LISTForm)form;
		if(!CheckUtil.isEmptry(tform.getTask_date_f())){
			sql.append(" and TASK_DATE >= '"+tform.getTask_date_f()+"' "); 
			tsql.append(" and WORK_DATE>='"+tform.getTask_date_f()+"' ");
			flag = true;
		}
		if(!CheckUtil.isEmptry(tform.getTask_date_e())){
			sql.append(" and TASK_DATE <= '"+tform.getTask_date_e()+"' ");
			tsql.append(" and WORK_DATE <= '"+tform.getTask_date_e()+"' ");
			flag = true;
		}
		if(!CheckUtil.isEmptry(tform.getProject_no_f())){
			sql.append(" and PROJECT_NO like '"+tform.getProject_no_f()+"%' ");
//			sql.append(" and WORK_ID like '"+tform.getProject_no_f()+"%' ");
			flag = true;
		}		
		return flag;
	}
 
	/** 
	 * ��ѯά�����ݣ����ݲ�ѯ�������ط���������һҳ��¼ 
	 */ 
	public List list(PageObject page, UserContext user) throws Exception { 
 
		BaseDAO  dao = (BaseDAO)getBean("task_list_dao"); 
		StringBuffer sql =new StringBuffer("select 'P' as TYPE,TASK_NO," +
				"PROJECT_NO," +
				"TASK_DATE," +
				"TASK_COST," +
				"TASK_USER," +
				"TASK_MEMO," +
				"TASK_STEP," +
				"TASK_GOAL," +
				"UPDATE_DATE," +
				"TASK_TYPE " + 
				"from TASK_LIST where 1=1 "); 
		sql.append(" and TASK_DATE >='"+getMonday()+"' and TASK_USER='"+user.getUserID()+"'");
		sql.append(" union ");
		sql.append("select 'T' as TYPE, ID as TASK_NO," +
				"ifNull(WORK_ID,'') as PROJECT_NO," +
				"WORK_DATE as TASK_DATE," +
				"'8' as TASK_COST," +
				"USER_ID as TASK_USER," +
				"'' as TASK_MEMO," +
				"'' as TASK_STEP," +
				"'' as TASK_GOAL," +
				"INPUT_DATE as UPDATE_DATE," +
				"'' as TASK_TYPE " +
				"from work_daily where 1=1 ");
		sql.append(" and WORK_DATE >='"+getMonday()+"' and USER_ID='"+user.getUserID()+"'");
		sql.append("group by WORK_DATE");
		//�ڴ˴����ģ��ƥ������ 
 		sql.append(" order by TASK_DATE");
		//ִ�в�ѯ 
		page.setList(dao.queryPage(page,sql.toString())); 
		return page.getList(); 
 
	} 
	public void checkTaskStatus(TASK_LISTVO vo)throws Exception{
		if(vo == null || vo.getProject_no().equals("��")){
			return;
		}
		BaseDAO  dao = (BaseDAO)getBean("task_list_dao"); 
		List l = dao.queryList("select * from TASK_LIST,project_list" +			" where TASK_DATE='"+vo.getTask_date()+"' and TASK_USER='"+vo.getTask_user()+"' and PROJECT_NO=PROJECT_ID");
		if(l == null || l.size() == 0){
			return;
		}else{
			throw new MessageException("ÿ��ֻ����һ��������������Ŀ���");
		}
	}
 	/**
     * @author houxh 2007-12-26
     * ��ñ���һ������
     * 
     * @return 
     */
	public String getMonday(){
		return getMonday(Calendar.getInstance().getTime());
	}
	public String getMonday(Date date){
		Calendar c= Calendar.getInstance();
		c.setTime(date);
		if(c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){
			c.add(Calendar.DATE, -6);
		}else{
			c.add(Calendar.DATE,Calendar.MONDAY - c.get(Calendar.DAY_OF_WEEK));
		}
		c.add(Calendar.DATE,-7);
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
		return sf.format(c.getTime());
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
 
		BaseDAO  dao = (BaseDAO)getBean("task_list_dao"); 
 
		return dao.retrieve(dao.getQuerySQL()+" where TASK_NO='"+((TASK_LISTVO) vo).getTask_no()+"'"); 
 
	} 
 
	/** 
	 * ����where������ѯ��ϸ��Ϣ�����ص�һ��¼ 
	 */ 
	public BaseVO retrieve(String whereClause, UserContext user) throws Exception { 
 
		BaseDAO  dao = (BaseDAO)getBean("task_list_dao"); 
		return dao.retrieve(dao.getQuerySQL()+ whereClause); 
 
	} 
 
} 

