 
package com.eis.dic.mdic; 
 
import java.util.*; 
 
import com.eis.base.*; 
import com.eis.portal.UserContext; 
import com.eis.factory.*; 
import com.eis.util.*; 
import com.eis.connectionPool.*;
import com.eis.db.*;

 
 
/** 
 * 说明：多级字典的业务逻辑类 
 */ 
public class MDicBO extends BaseBO { 
 
	/** 
	 * 构造函数 
	 */ 
	public MDicBO() { 
		super(); 
	} 
	
	/*
	 * 删除所选字典及下级字典数据
	 */
	public void deleteMDic(BaseVO vo, UserContext user) throws Exception {
		//在同一事务中，删除与菜单表有外键关系的所有表
		java.sql.Connection con = DBPoolManager.getConnection();
		con.setAutoCommit(false);
		try {
			ArrayList list = new ArrayList();
			
			//获取本级和下级字典集合，集合是按字典级别由低向高获取的
			getSubDic(((MDicVO) vo).getSys_id(),list);
			for(int i=0;i<list.size();i++) {
				SysLog.debug("下级字典编号:"+list.get(i).toString());
				
				//按菜单级别由低高删除菜单
				deleteDic(Long.parseLong((String)list.get(i)),con);
			}
			//提交事务
			con.commit();
		} catch (Exception ex) {
			con.rollback();
		} finally {
			if (con != null)
				con.close();
		}

	}
	/*
	 * 递归查询菜单数据，将本级和之下各级菜单加入集合list
	 */
	public void getSubDic(long sys_id, List list) throws Exception {

		
		DBQueryUtil db = new DBQueryUtil();
		try {
			java.sql.ResultSet rs =
				db.sqlQuery(
					"select SYS_ID from ep_mdic  where PARENT_ID =" + sys_id + "");
			while (rs.next()) {
				//如果有下级菜单，递归调用				
				getSubDic(rs.getLong("SYS_ID"), list);
			}
			//将本级菜单加入集合
			if (!list.contains(Long.toString(sys_id)))
				list.add(Long.toString(sys_id));
		} finally {
			db.close();
		}
	}

	/*
	 * 删除与字典项有外键关系的所有关系，和删除数据
	 */
	public void deleteDic(long sys_id, java.sql.Connection con)
		throws Exception {

		//删除菜单表中的记录，ep_menu	
		BaseDAO dao = (BaseDAO) BeanFactory.getBean("mdic_dao");		
		dao.delete(
			dao.getDeleteSQL()
				+ " where SYS_ID ="
				+ sys_id
				+ "",
			con);

	}
	
	/** 
	 * 增加多级字典 
	 */ 
	public void add(BaseVO vo, UserContext user) throws Exception { 
 
		BaseDAO  dao = (BaseDAO)getBean("mdic_dao"); 
		dao.add(vo); 
 
	} 
 
	/** 
	 * 批量增加多级字典 
	 */ 
	public void addList(BaseVO[] list, UserContext user) throws Exception { 
 
		BaseDAO  dao = (BaseDAO)getBean("mdic_dao"); 
		dao.addList(list); 
 
	} 
 
	/** 
	 * 修改多级字典 
	 */ 
	public void update(BaseVO vo, UserContext user) throws Exception { 
 
		BaseDAO  dao = (BaseDAO)getBean("mdic_dao"); 
		dao.update(vo," where SYS_ID="+((MDicVO) vo).getSys_id()+""); 

 
	} 
 
	/** 
	 * 批量修改多级字典 
	 */ 
	public void updateList(BaseVO[] list, UserContext user) throws Exception { 
 
	} 
 
	/** 
	 * 删除多级字典 
	 */ 
	public void delete(BaseVO vo, UserContext user) throws Exception { 
 
		BaseDAO  dao = (BaseDAO)getBean("mdic_dao"); 
		dao.delete(dao.getDeleteSQL()+" where SYS_ID="+((MDicVO) vo).getSys_id()+""); 
 
	} 
 
	/** 
	 * 批量删除多级字典 
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
 
		BaseDAO  dao = (BaseDAO)getBean("mdic_dao"); 
		StringBuffer sql =new StringBuffer("select * from ep_mdic where 1=1 "); 
		//在此处添加模糊匹配条件 
 
		//执行查询 
		page.setList(dao.queryPage(page,sql.toString())); 
		return page.getList(); 
 
	} 
	public List list(PageObject page, UserContext user) throws Exception { 
		return null;
	}
	
 
	/** 
	 * 查询维护数据，根据查询条件返回符合条件的一页纪录 
	 */ 
	public List listR(PageObject page, UserContext user,String type_id) throws Exception { 
 
		BaseDAO  dao = (BaseDAO)getBean("mdic_dao"); 
		StringBuffer sql =new StringBuffer("select * from ep_mdic where 1=1 and TYPE_ID = '"+type_id+"' order by SYS_ID,PARENT_ID,LIST_ORDER"); 
		//在此处添加模糊匹配条件 
 		SysLog.debug(sql.toString());
		//执行查询 
		return dao.queryList(sql.toString()); 

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
 
		MDicVO  bean = (MDicVO)vo; 
		BaseDAO  dao = (BaseDAO)getBean("mdic_dao"); 
 
		return dao.retrieve(dao.getQuerySQL()+" where SYS_ID="+((MDicVO) vo).getSys_id()+""); 
 
	} 
 
	/** 
	 * 根据where条件查询明细信息，返回单一纪录 
	 */ 
	public BaseVO retrieve(String whereClause, UserContext user) throws Exception { 
 
		BaseDAO  dao = (BaseDAO)getBean("mdic_dao"); 
		return dao.retrieve(dao.getQuerySQL()+ whereClause); 
 
	} 
 
} 

