 
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
 * 说明：首页模板的业务逻辑类 
 */ 
public class HomePageBO extends BaseBO { 
 
	/** 
	 * 构造函数 
	 */ 
	public HomePageBO() { 
		super(); 
	} 
 
	/** 
	 * 增加首页模板 
	 */ 
	public void add(BaseVO vo, UserContext user) throws Exception { 
 
		BaseDAO  dao = (BaseDAO)getBean("homepage_dao"); 
		dao.add(vo); 
		
		//纪录操作日志
		OpLog.Log(user,"03","c","增加首页模板",Integer.toString(((HomePageVO)vo).getTempl_id()));

 
	} 
 
	/** 
	 * 批量增加首页模板 
	 */ 
	public void addList(BaseVO[] list, UserContext user) throws Exception { 
 
		BaseDAO  dao = (BaseDAO)getBean("homepage_dao"); 
		dao.addList(list); 
 
	} 
 
	/** 
	 * 修改首页模板 
	 */ 
	public void update(BaseVO vo, UserContext user) throws Exception { 
 
		BaseDAO  dao = (BaseDAO)getBean("homepage_dao"); 
		dao.update(vo," where TEMPL_ID="+((HomePageVO) vo).getTempl_id()+""); 
		//纪录操作日志
		OpLog.Log(user,"03","u","修改首页模板",Integer.toString(((HomePageVO)vo).getTempl_id()));
 
	} 
 
	/** 
	 * 批量修改首页模板 
	 */ 
	public void updateList(BaseVO[] list, UserContext user) throws Exception { 
 
	} 
 
	/** 
	 * 删除首页模板 
	 */ 
	public void delete(BaseVO vo, UserContext user) throws Exception { 
 
		BaseDAO  dao = (BaseDAO)getBean("homepage_dao"); 
		dao.delete(dao.getDeleteSQL()+" where TEMPL_ID="+((HomePageVO) vo).getTempl_id()+""); 
		//纪录操作日志
		OpLog.Log(user,"03","d","删除首页模板",Integer.toString(((HomePageVO)vo).getTempl_id()));
 
	} 
 
	/** 
	 * 批量删除首页模板 
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
 
		BaseDAO  dao = (BaseDAO)getBean("homepage_dao"); 
		StringBuffer sql =new StringBuffer("select * from ep_homepage where 1=1 "); 
		//在此处添加模糊匹配条件 
 
		//执行查询 
		page.setList(dao.queryPage(page,sql.toString())); 
		return page.getList(); 
 
	} 
 
	/** 
	 * 查询维护数据，根据查询条件返回符合条件的一页纪录 
	 */ 
	public List list(PageObject page, UserContext user) throws Exception { 
 
		BaseDAO  dao = (BaseDAO)getBean("homepage_dao"); 
		StringBuffer sql =new StringBuffer(dao.getListSQL()+" where 1=1 "); 
		
		//在此处添加模糊匹配条件 		
		String caption = (String)page.getFilter("caption");
		
		if(caption != null) 
		sql.append(" and CAPTION like '%"+caption+"%'");
		sql.append(dao.getOrderBy());
		
		
		SysLog.debug(sql.toString());
		
		
		//执行查询 
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
 
		HomePageVO  bean = (HomePageVO)vo; 
		BaseDAO  dao = (BaseDAO)getBean("homepage_dao"); 
		
		BaseVO v = dao.retrieve(dao.getQuerySQL()+" where TEMPL_ID="+((HomePageVO) vo).getTempl_id()+""); 
		
		//纪录操作日志
		OpLog.Log(user,"03","r","查看首页模板",Integer.toString(((HomePageVO)vo).getTempl_id())); 
 
		return v;
 
	} 
 
	/** 
	 * 根据where条件查询明细信息，返回单一纪录 
	 */ 
	public BaseVO retrieve(String whereClause, UserContext user) throws Exception { 
 
		BaseDAO  dao = (BaseDAO)getBean("homepage_dao"); 
		return dao.retrieve(dao.getQuerySQL()+ whereClause); 
 
	} 
 
} 

