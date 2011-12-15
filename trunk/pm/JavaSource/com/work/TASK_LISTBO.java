 
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
 * 说明：工作列表的业务逻辑类 
 */ 
public class TASK_LISTBO extends BaseBO { 
 
	/** 
	 * 构造函数 
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
	 * 增加工作列表 
	 */ 
	public void add(BaseVO vo, UserContext user) throws Exception { 
 
		BaseDAO  dao = (BaseDAO)getBean("task_list_dao"); 
		dao.add(vo); 
 
	} 
 
	/** 
	 * 批量增加工作列表 
	 */ 
	public void addList(BaseVO[] list, UserContext user) throws Exception { 
 
		BaseDAO  dao = (BaseDAO)getBean("task_list_dao"); 
		dao.addList(list); 
 
	} 
 
	/** 
	 * 修改工作列表 
	 */ 
	public void update(BaseVO vo, UserContext user) throws Exception { 
 
		BaseDAO  dao = (BaseDAO)getBean("task_list_dao"); 
		dao.update(vo," where TASK_NO='"+((TASK_LISTVO) vo).getTask_no()+"'"); 
 
	} 
 
	/** 
	 * 批量修改工作列表 
	 */ 
	public void updateList(BaseVO[] list, UserContext user) throws Exception { 
 
	} 
 
	/** 
	 * 删除工作列表 
	 */ 
	public void delete(BaseVO vo, UserContext user) throws Exception { 
 
		BaseDAO  dao = (BaseDAO)getBean("task_list_dao"); 
		dao.delete(dao.getDeleteSQL()+" where TASK_NO='"+((TASK_LISTVO) vo).getTask_no()+"'"); 
 
	} 
 
	/** 
	 * 批量删除工作列表 
	 */ 
	public void deleteList(BaseVO[] list, UserContext user) throws Exception { 
 
	} 
 
	/** 
	 * 查询列表，没有查询条件，返回所有纪录 
	 */ 
	public List queryList(UserContext user) throws Exception { 
 
		return null; 
 
	} 
 
	/** 
	 * 查询列表，根据查询条件返回符合条件的一页纪录 
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
		//在此处添加模糊匹配条件 
 		initQuery(form,sql,tsql);
 		sql.append(" union ");
		sql.append(tsql.append(" group by WORK_DATE"));
		
		sql.append(" order by TASK_DATE");
		//执行查询 
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
		//在此处添加模糊匹配条件 
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
		//执行查询 
		page.setList(dao.queryPage(page,psql.toString())); 
		return page.getList(); 
 
	}
	/**
     * @author houxh 2007-12-27
     * 初始化查询语句
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
	 * 查询维护数据，根据查询条件返回符合条件的一页纪录 
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
		//在此处添加模糊匹配条件 
 		sql.append(" order by TASK_DATE");
		//执行查询 
		page.setList(dao.queryPage(page,sql.toString())); 
		return page.getList(); 
 
	} 
	public void checkTaskStatus(TASK_LISTVO vo)throws Exception{
		if(vo == null || vo.getProject_no().equals("无")){
			return;
		}
		BaseDAO  dao = (BaseDAO)getBean("task_list_dao"); 
		List l = dao.queryList("select * from TASK_LIST,project_list" +			" where TASK_DATE='"+vo.getTask_date()+"' and TASK_USER='"+vo.getTask_user()+"' and PROJECT_NO=PROJECT_ID");
		if(l == null || l.size() == 0){
			return;
		}else{
			throw new MessageException("每天只能有一个工作可以有项目编号");
		}
	}
 	/**
     * @author houxh 2007-12-26
     * 获得本周一的日期
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
	 * 查询维护数据，返回所有纪录 
	 */ 
	public List list(UserContext user) throws Exception { 
 
		return null; 
 
	} 
 
	/** 
	 * 查询明细信息，返回单一纪录 
	 */ 
	public BaseVO retrieve(BaseVO vo, UserContext user) throws Exception { 
 
		BaseDAO  dao = (BaseDAO)getBean("task_list_dao"); 
 
		return dao.retrieve(dao.getQuerySQL()+" where TASK_NO='"+((TASK_LISTVO) vo).getTask_no()+"'"); 
 
	} 
 
	/** 
	 * 根据where条件查询明细信息，返回单一纪录 
	 */ 
	public BaseVO retrieve(String whereClause, UserContext user) throws Exception { 
 
		BaseDAO  dao = (BaseDAO)getBean("task_list_dao"); 
		return dao.retrieve(dao.getQuerySQL()+ whereClause); 
 
	} 
 
} 

