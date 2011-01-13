 
package com.eis.dic.dictype; 
 
import java.util.List; 
 
import com.eis.base.*; 
import com.eis.base.BaseVO; 
import com.eis.base.BaseDAO; 
import com.eis.base.PageObject; 
import com.eis.portal.UserContext; 
import com.eis.factory.*; 
import com.eis.util.*; 
import com.eis.connectionPool.*;
 
 
/** 
 * 说明：字典归类信息的业务逻辑类 
 */ 
public class DicTypeBO extends BaseBO { 
 
	/** 
	 * 构造函数 
	 */ 
	public DicTypeBO() { 
		super(); 
	} 
 
	/** 
	 * 增加字典归类信息 
	 */ 
	public void add(BaseVO vo, UserContext user) throws Exception { 
 
		BaseDAO  dao = (BaseDAO)getBean("dictype_dao"); 
		
		//检查所输入归类码是否存在
		String sql =  dao.getQuerySQL()
						+ " where TYPE_ID='"+((DicTypeVO) vo).getType_id()+"'" ;
		
		if(dao.queryCount(sql)>0) {
			BaseException ex = new BaseException();
			ex.setErrorCode("E010041");
			throw ex;
		}
		
		dao.add(vo); 
		
		//登记增加字典归类信息操作日志		
		OpLog.Log(user,"03","c","增加字典归类信息",((DicTypeVO) vo).getType_id());	

 
	} 
 
	/** 
	 * 批量增加字典归类信息 
	 */ 
	public void addList(BaseVO[] list, UserContext user) throws Exception { 
 
		BaseDAO  dao = (BaseDAO)getBean("dictype_dao"); 
		dao.addList(list); 
 
	} 
 
	/** 
	 * 修改字典归类信息 
	 */ 
	public void update(BaseVO vo, UserContext user) throws Exception { 
 
		BaseDAO  dao = (BaseDAO)getBean("dictype_dao"); 
		dao.update(vo," where TYPE_ID='"+((DicTypeVO) vo).getType_id()+"'"); 
		
		//登记修改字典归类信息操作日志		
		OpLog.Log(user,"03","u","修改字典归类信息",((DicTypeVO) vo).getType_id());	

 
	} 
 
	/** 
	 * 批量修改字典归类信息 
	 */ 
	public void updateList(BaseVO[] list, UserContext user) throws Exception { 
 
	} 
 
	/** 
	 * 删除字典归类信息 
	 * 事务操作，删除对应的ep_sdic表
	 */ 
	public void delete(BaseVO vo, UserContext user) throws Exception { 
		
		java.sql.Connection con = DBPoolManager.getConnection();
		con.setAutoCommit(false);
	 	try{
			BaseDAO  dao = (BaseDAO)getBean("dictype_dao"); 
			//删除dic_type表中的对应项
			dao.delete(dao.getDeleteSQL()+" where TYPE_ID='"+((DicTypeVO) vo).getType_id()+"'",con); 
			//按照指定归类码，删除sdic表中信息
			dao.delete("delete from ep_sdic  where TYPE_ID='"+((DicTypeVO) vo).getType_id()+"'",con);
		
			//登记删除字典归类信息操作日志		
			OpLog.Log(user,"03","d","删除单级字典归类信息",((DicTypeVO) vo).getType_id());	
			//提交事务
			con.commit();
		} catch (Exception ex) {
			con.rollback();
		} finally {
			if (con != null)
				con.close();
		}
 
	} 
	/** 
	 * 删除字典归类信息 
	 * 事务操作，删除对应的ep_mdic表
	 */ 
	public void deleteM(BaseVO vo, UserContext user) throws Exception { 
		
		java.sql.Connection con = DBPoolManager.getConnection();
		con.setAutoCommit(false);
		try{
			BaseDAO  dao = (BaseDAO)getBean("dictype_dao"); 
			//删除dic_type表中的对应项
			dao.delete(dao.getDeleteSQL()+" where TYPE_ID='"+((DicTypeVO) vo).getType_id()+"'",con); 
			//按照指定归类码，删除sdic表中信息
			dao.delete("delete from ep_mdic  where TYPE_ID='"+((DicTypeVO) vo).getType_id()+"'",con);
		
			//登记删除字典归类信息操作日志		
			OpLog.Log(user,"03","d","删除多级字典归类信息",((DicTypeVO) vo).getType_id());	
			//提交事务
			con.commit();
		} catch (Exception ex) {
			con.rollback();
		} finally {
			if (con != null)
				con.close();
		}
 
	} 
 
	/** 
	 * 批量删除字典归类信息 
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
 
		BaseDAO  dao = (BaseDAO)getBean("dictype_dao"); 
		StringBuffer sql =new StringBuffer("select * from ep_dic_type where 1=1 "); 
		//在此处添加模糊匹配条件 
 
		//执行查询 
		page.setList(dao.queryPage(page,sql.toString())); 
		return page.getList(); 
 
	} 
 
	/** 
	 * 查询维护数据，根据查询条件返回符合条件的一页纪录 
	 */ 
	public List list(PageObject page, UserContext user) throws Exception { 
 
		BaseDAO  dao = (BaseDAO)getBean("dictype_dao"); 
		StringBuffer sql =new StringBuffer("select * from ep_dic_type where 1=1 "); 
		
		//在此处添加模糊匹配条件 
		String type_id= (String)page.getFilter("type_id_f");
		if(type_id!= null) 
		sql.append(" and TYPE_ID like '%"+type_id+"%'");
		
		String type_name= (String)page.getFilter("type_name_f");
		if(type_name!= null) 
		sql.append(" and TYPE_NAME like '%"+type_name+"%'");
		
		sql.append(dao.getOrderBy());
 
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
 
		DicTypeVO  bean = (DicTypeVO)vo; 
		BaseDAO  dao = (BaseDAO)getBean("dictype_dao"); 
		
		BaseVO v =  dao.retrieve(dao.getQuerySQL()+" where TYPE_ID='"+((DicTypeVO) vo).getType_id()+"'");
 
		//登记查看字典归类信息操作日志		
		OpLog.Log(user,"03","r","查看字典归类信息",((DicTypeVO) vo).getType_id());	 
		
		return v;
 
	} 
 
	/** 
	 * 根据where条件查询明细信息，返回单一纪录 
	 */ 
	public BaseVO retrieve(String whereClause, UserContext user) throws Exception { 
 
		BaseDAO  dao = (BaseDAO)getBean("dictype_dao"); 
		return dao.retrieve(dao.getQuerySQL()+ whereClause); 
 
	} 
 
} 

