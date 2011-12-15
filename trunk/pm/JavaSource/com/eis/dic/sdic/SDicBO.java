 
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
 * 说明：单级字典的业务逻辑类 
 */ 
public class SDicBO extends BaseBO { 
 
	/** 
	 * 构造函数 
	 */ 
	public SDicBO() { 
		super(); 
	} 
 
	/** 
	 * 增加单级字典 
	 */ 
	public void add(BaseVO vo, UserContext user) throws Exception { 
 
		BaseDAO  dao = (BaseDAO)getBean("sdic_dao"); 
		dao.add(vo); 
		
		
		//登记增加单级字典明细操作日志		
		OpLog.Log(user,"03","c","增加单级字典明细",Long.toString(((SDicVO) vo).getSys_id()));	
 
	} 
 
	/** 
	 * 批量增加单级字典 
	 */ 
	public void addList(BaseVO[] list, UserContext user) throws Exception { 
 
		BaseDAO  dao = (BaseDAO)getBean("sdic_dao"); 
		dao.addList(list); 
 
	} 
 
	/** 
	 * 修改单级字典 
	 */ 
	public void update(BaseVO vo, UserContext user) throws Exception { 
 
		BaseDAO  dao = (BaseDAO)getBean("sdic_dao"); 
		dao.update(vo," where SYS_ID="+((SDicVO) vo).getSys_id()+""); 
		
		//登记修改单级字典明细操作日志		
		OpLog.Log(user,"03","u","修改单级字典明细",Long.toString(((SDicVO) vo).getSys_id()));
 
	} 
 
	/** 
	 * 批量修改单级字典 
	 */ 
	public void updateList(BaseVO[] list, UserContext user) throws Exception { 
 
	} 
 
	/** 
	 * 删除单级字典 
	 */ 
	public void delete(BaseVO vo, UserContext user) throws Exception { 
 
		BaseDAO  dao = (BaseDAO)getBean("sdic_dao"); 
		dao.delete(dao.getDeleteSQL()+" where SYS_ID="+((SDicVO) vo).getSys_id()+""); 
		
		//登记删除单级字典明细操作日志		
		OpLog.Log(user,"03","d","删除单级字典明细",Long.toString(((SDicVO) vo).getSys_id()));
 
	} 
 
	/** 
	 * 批量删除单级字典 
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
 
		BaseDAO  dao = (BaseDAO)getBean("sdic_dao"); 
		StringBuffer sql =new StringBuffer("select * from ep_sdic where 1=1 "); 
		//在此处添加模糊匹配条件 
 
		//执行查询 
		page.setList(dao.queryPage(page,sql.toString())); 
		return page.getList(); 
 
	} 
 
	/** 
	 * 查询维护数据，根据查询条件返回符合条件的一页纪录 
	 */ 
	public List list(PageObject page, UserContext user) throws Exception { 
 
		BaseDAO  dao = (BaseDAO)getBean("sdic_dao"); 
		StringBuffer sql =new StringBuffer("select * from ep_sdic where 1=1 "); 
		
		//在此处添加模糊匹配条件 
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
 
		SDicVO  bean = (SDicVO)vo; 
		BaseDAO  dao = (BaseDAO)getBean("sdic_dao");
		
		BaseVO v = dao.retrieve(dao.getQuerySQL()+" where SYS_ID="+((SDicVO) vo).getSys_id()+""); 
 			
		//登记查看单级字典明细操作日志		
		OpLog.Log(user,"03","r","查看单级字典明细",Long.toString(((SDicVO) vo).getSys_id()));
		
		return v;
 
	} 
 
	/** 
	 * 根据where条件查询明细信息，返回单一纪录 
	 */ 
	public BaseVO retrieve(String whereClause, UserContext user) throws Exception { 
 
		BaseDAO  dao = (BaseDAO)getBean("sdic_dao"); 
		return dao.retrieve(dao.getQuerySQL()+ whereClause); 
 
	} 
 
} 

