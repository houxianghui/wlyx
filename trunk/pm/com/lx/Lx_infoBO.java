 
package com.lx; 
 
import java.util.List; 
 
import com.eis.base.*; 
import com.eis.portal.UserContext; 
import com.eis.factory.*; 
import com.eis.util.*; 
 
 
/** 
 * 说明：联系信息的业务逻辑类 
 */ 
public class Lx_infoBO extends BaseBO { 
 
	/** 
	 * 构造函数 
	 */ 
	public Lx_infoBO() { 
		super(); 
	} 
 
	/** 
	 * 增加联系信息 
	 */ 
	public void add(BaseVO vo, UserContext user) throws Exception { 
 
		BaseDAO  dao = (BaseDAO)getBean("lx_info_dao"); 
		dao.add(vo); 
 
	} 
 
	/** 
	 * 批量增加联系信息 
	 */ 
	public void addList(BaseVO[] list, UserContext user) throws Exception { 
 
		BaseDAO  dao = (BaseDAO)getBean("lx_info_dao"); 
		dao.addList(list); 
 
	} 
 
	/** 
	 * 修改联系信息 
	 */ 
	public void update(BaseVO vo, UserContext user) throws Exception { 
 
		BaseDAO  dao = (BaseDAO)getBean("lx_info_dao"); 
		dao.update(vo," where lx_id='"+((Lx_infoVO) vo).getLx_id()+"'"); 
 
	} 
 
	/** 
	 * 批量修改联系信息 
	 */ 
	public void updateList(BaseVO[] list, UserContext user) throws Exception { 
 
	} 
 
	/** 
	 * 删除联系信息 
	 */ 
	public void delete(BaseVO vo, UserContext user) throws Exception { 
 
		BaseDAO  dao = (BaseDAO)getBean("lx_info_dao"); 
		dao.delete(dao.getDeleteSQL()+" where lx_id='"+((Lx_infoVO) vo).getLx_id()+"'"); 
 
	} 
 
	/** 
	 * 批量删除联系信息 
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
	public List queryList(PageObject page, UserContext user) throws Exception { 
 
		BaseDAO  dao = (BaseDAO)getBean("lx_info_dao"); 
		StringBuffer sql =new StringBuffer("select * from lx_info where 1=1 "); 
		//在此处添加模糊匹配条件 
	

		 String name_f = (String)page.getFilter("name_f");
		 if (name_f != null && name_f.trim().length() > 0) {
			 sql.append(" and name like '" + name_f + "%'");
		 }
 
		//执行查询 
		page.setList(dao.queryPage(page,sql.toString())); 
		return page.getList(); 
 
	} 
	/** 
	 * 查询维护数据，根据查询条件返回符合条件的一页纪录 
	 */ 
	public List list(PageObject page, UserContext user) throws Exception { 
 		return null;
	} 
	/**
	 * 查询列表信息
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
	 * 初始化查询条件
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
	 * 查询维护数据，返回所有纪录 
	 */ 
	public List list(UserContext user) throws Exception { 
 
		return null; 
 
	} 
 
	/** 
	 * 查询明细信息，返回单一纪录 
	 */ 
	public BaseVO retrieve(BaseVO vo, UserContext user) throws Exception { 
 
		Lx_infoVO  bean = (Lx_infoVO)vo; 
		BaseDAO  dao = (BaseDAO)getBean("lx_info_dao"); 
 
		return dao.retrieve(dao.getQuerySQL()+" where lx_id='"+((Lx_infoVO) vo).getLx_id()+"'"); 
 
	} 
 
	/** 
	 * 根据where条件查询明细信息，返回单一纪录 
	 */ 
	public BaseVO retrieve(String whereClause, UserContext user) throws Exception { 
 
		BaseDAO  dao = (BaseDAO)getBean("lx_info_dao"); 
		return dao.retrieve(dao.getQuerySQL()+ whereClause); 
 
	} 
 
} 

