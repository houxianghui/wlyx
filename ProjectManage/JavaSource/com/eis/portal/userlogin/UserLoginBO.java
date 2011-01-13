 
package com.eis.portal.userlogin; 
 
import java.util.List; 
 
import com.eis.base.BaseBO; 
import com.eis.base.BaseVO; 
import com.eis.base.BaseDAO; 
import com.eis.base.PageObject; 
import com.eis.portal.UserContext; 
import com.eis.factory.*; 
import com.eis.util.*; 
 
 
/** 
 * 说明：管理用户登录记录表的业务逻辑类 
 */ 
public class UserLoginBO extends BaseBO { 
 
	/** 
	 * 构造函数 
	 */ 
	public UserLoginBO() { 
		super(); 
	} 
 
	/** 
	 * 增加管理用户登录记录表 
	 */ 
	public void add(BaseVO vo, UserContext user) throws Exception { 
 
		BaseDAO  dao = (BaseDAO)getBean("userlogin_dao"); 
		dao.add(vo); 
 
	} 
 
	/** 
	 * 批量增加管理用户登录记录表 
	 */ 
	public void addList(BaseVO[] list, UserContext user) throws Exception { 
 
		BaseDAO  dao = (BaseDAO)getBean("userlogin_dao"); 
		dao.addList(list); 
 
	} 
 
	/** 
	 * 修改管理用户登录记录表 
	 */ 
	public void update(BaseVO vo, UserContext user) throws Exception { 
 
		BaseDAO  dao = (BaseDAO)getBean("userlogin_dao"); 
		dao.update(vo," where USER_ID='"+((UserLoginVO) vo).getUser_id()+"'"); 
 
	} 
 
	/** 
	 * 批量修改管理用户登录记录表 
	 */ 
	public void updateList(BaseVO[] list, UserContext user) throws Exception { 
 
	} 
 
	/** 
	 * 删除管理用户登录记录表 
	 */ 
	public void delete(BaseVO vo, UserContext user) throws Exception { 
 
		BaseDAO  dao = (BaseDAO)getBean("userlogin_dao"); 
		dao.delete(dao.getDeleteSQL()+" where USER_ID='"+((UserLoginVO) vo).getUser_id()+"'"); 
 
	} 
 
	/** 
	 * 批量删除管理用户登录记录表 
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
 
		BaseDAO  dao = (BaseDAO)getBean("userlogin_dao"); 
		StringBuffer sql =new StringBuffer("select * from ep_user_login where 1=1 "); 
		//在此处添加模糊匹配条件 
		String user_id = (String) page.getFilter("user_id_f");
		if (user_id != null)
			sql.append(" and USER_ID like '%" + user_id + "%'");
			
		SysLog.debug(sql.toString());
		//执行查询 
		page.setList(dao.queryPage(page,sql.toString())); 
		return page.getList(); 
 
	} 
 
	/** 
	 * 查询维护数据，根据查询条件返回符合条件的一页纪录 
	 */ 
	public List list(PageObject page, UserContext user) throws Exception { 
 
		BaseDAO  dao = (BaseDAO)getBean("userlogin_dao"); 
		StringBuffer sql =new StringBuffer("select * from ep_user_login where 1=1 "); 
		//在此处添加模糊匹配条件 
		String user_id = (String) page.getFilter("user_id_f");
		if (user_id != null)
			sql.append(" and USER_ID like '%" + user_id + "%'");
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
 
		UserLoginVO  bean = (UserLoginVO)vo; 
		BaseDAO  dao = (BaseDAO)getBean("userlogin_dao"); 
 
		return dao.retrieve(dao.getQuerySQL()+" where USER_ID='"+((UserLoginVO) vo).getUser_id()+"'"); 
 
	} 
 
	/** 
	 * 根据where条件查询明细信息，返回单一纪录 
	 */ 
	public BaseVO retrieve(String whereClause, UserContext user) throws Exception { 
 
		BaseDAO  dao = (BaseDAO)getBean("userlogin_dao"); 
		return dao.retrieve(dao.getQuerySQL()+ whereClause); 
 
	} 
 
} 

