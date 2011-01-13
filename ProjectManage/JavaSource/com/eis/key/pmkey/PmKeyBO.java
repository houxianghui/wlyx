 
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
 * 说明：主键配置的业务逻辑类 
 */ 
public class PmKeyBO extends BaseBO { 
 
	/** 
	 * 构造函数 
	 */ 
	public PmKeyBO() { 
		super(); 
	} 
 
	/** 
	 * 增加主键配置 
	 */ 
	public void add(BaseVO vo, UserContext user) throws Exception { 
 
		BaseDAO  dao = (BaseDAO)getBean("pmkey_dao"); 
		dao.add(vo); 
 
	} 
 
	/** 
	 * 批量增加主键配置 
	 */ 
	public void addList(BaseVO[] list, UserContext user) throws Exception { 
 
		BaseDAO  dao = (BaseDAO)getBean("pmkey_dao"); 
		dao.addList(list); 
 
	} 
 
	/** 
	 * 修改主键配置 
	 */ 
	public void update(BaseVO vo, UserContext user) throws Exception { 
 
		BaseDAO  dao = (BaseDAO)getBean("pmkey_dao"); 
		dao.update(vo," where TB_NAME='"+((PmKeyVO) vo).getTb_name()+"'"); 
 
	} 
 
	/** 
	 * 批量修改主键配置 
	 */ 
	public void updateList(BaseVO[] list, UserContext user) throws Exception { 
 
	} 
 
	/** 
	 * 删除主键配置 
	 */ 
	public void delete(BaseVO vo, UserContext user) throws Exception { 
 
		BaseDAO  dao = (BaseDAO)getBean("pmkey_dao"); 
		dao.delete(dao.getDeleteSQL()+" where TB_NAME='"+((PmKeyVO) vo).getTb_name()+"'"); 
 
	} 
 
	/** 
	 * 批量删除主键配置 
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
 
		BaseDAO  dao = (BaseDAO)getBean("pmkey_dao"); 
		StringBuffer sql =new StringBuffer("select * from ep_pmkey where 1=1 "); 
		//在此处添加模糊匹配条件 
 
		//执行查询 
		page.setList(dao.queryPage(page,sql.toString())); 
		return page.getList(); 
 
	} 
 
	/** 
	 * 查询维护数据，根据查询条件返回符合条件的一页纪录 
	 */ 
	public List list(PageObject page, UserContext user) throws Exception { 
 
		BaseDAO  dao = (BaseDAO)getBean("pmkey_dao"); 
		StringBuffer sql =new StringBuffer("select * from ep_pmkey where 1=1 "); 
		
		//在此处添加模糊匹配条件 
		String tb_name= (String)page.getFilter("tb_name_f");
		if(tb_name!= null) 
		sql.append(" and TB_NAME like '%"+tb_name+"%'");
		
 
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
 
		PmKeyVO  bean = (PmKeyVO)vo; 
		BaseDAO  dao = (BaseDAO)getBean("pmkey_dao"); 
 
		return dao.retrieve(dao.getQuerySQL()+" where TB_NAME='"+((PmKeyVO) vo).getTb_name()+"'"); 
 
	} 
 
	/** 
	 * 根据where条件查询明细信息，返回单一纪录 
	 */ 
	public BaseVO retrieve(String whereClause, UserContext user) throws Exception { 
 
		BaseDAO  dao = (BaseDAO)getBean("pmkey_dao"); 
		return dao.retrieve(dao.getQuerySQL()+ whereClause); 
 
	} 
 
} 

