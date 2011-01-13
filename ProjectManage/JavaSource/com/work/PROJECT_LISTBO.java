 
package com.work; 
 
import java.util.List; 
 
import com.eis.base.*; 
import com.eis.portal.UserContext;
import com.eis.util.CheckUtil;
 
 
/** 
 * 说明：工作列表的业务逻辑类 
 */ 
public class PROJECT_LISTBO extends BaseBO { 
 
	/** 
	 * 构造函数 
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
	 * 增加工作列表 
	 */ 
	public void add(BaseVO vo, UserContext user) throws Exception { 
 
		BaseDAO  dao = (BaseDAO)getBean("project_list_dao"); 
		dao.add(vo); 
 
	} 
 
	/** 
	 * 批量增加工作列表 
	 */ 
	public void addList(BaseVO[] list, UserContext user) throws Exception { 
 
		BaseDAO  dao = (BaseDAO)getBean("project_list_dao"); 
		dao.addList(list); 
 
	} 
 
	/** 
	 * 修改工作列表 
	 */ 
	public void update(BaseVO vo, UserContext user) throws Exception { 
 
		BaseDAO  dao = (BaseDAO)getBean("project_list_dao"); 
		dao.update(vo," where USER_ID='"+((PROJECT_LISTVO) vo).getUser_id()+"'"+" and PROJECT_NO='"+((PROJECT_LISTVO) vo).getProject_no()+"'"); 
 
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
 
		BaseDAO  dao = (BaseDAO)getBean("project_list_dao"); 
		dao.delete("update PROJECT_LIST set CURR_STEP='"+PROJECT_LISTVO.DEL+"' where USER_ID='"+((PROJECT_LISTVO) vo).getUser_id()+"'"+" and PROJECT_NO='"+((PROJECT_LISTVO) vo).getProject_no()+"'"); 
 
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
 
		BaseDAO  dao = (BaseDAO)getBean("project_list_dao"); 
		StringBuffer sql =new StringBuffer("select * from PROJECT_LIST where 1=1 and USER_ID='"+user.getUserID()+"' "); 
		//在此处添加模糊匹配条件 
 		initQuery(form,sql);
		//执行查询 
		sql.append(" order by PROJECT_NO");
		page.setList(dao.queryPage(page,sql.toString())); 
		return page.getList(); 
 
	} 
	/**
     * @author houxh 2007-12-27
     * 初始化查询条件
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
	 * 查询维护数据，根据查询条件返回符合条件的一页纪录 
	 */ 
	public List list(PageObject page, UserContext user) throws Exception { 
 
		BaseDAO  dao = (BaseDAO)getBean("project_list_dao"); 
		StringBuffer sql =new StringBuffer("select * from PROJECT_LIST where 1=1 and USER_ID='"+user.getUserID()+"' and CURR_STEP not in('"+PROJECT_LISTVO.PRD+"','"+PROJECT_LISTVO.DEL+"') "); 
		page.setList(dao.queryPage(page,sql.toString())); 
		return page.getList(); 
 
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
 
		BaseDAO  dao = (BaseDAO)getBean("project_list_dao"); 
 
		return dao.retrieve(dao.getQuerySQL()+" where USER_ID='"+((PROJECT_LISTVO) vo).getUser_id()+"'"+" and PROJECT_NO='"+((PROJECT_LISTVO) vo).getProject_no()+"'"); 
 
	} 
 
	/** 
	 * 根据where条件查询明细信息，返回单一纪录 
	 */ 
	public BaseVO retrieve(String whereClause, UserContext user) throws Exception { 
 
		BaseDAO  dao = (BaseDAO)getBean("project_list_dao"); 
		return dao.retrieve(dao.getQuerySQL()+ whereClause); 
 
	} 
 
} 

