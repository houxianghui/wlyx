 
package com.eis.portal.userlogout; 
 
import java.util.List; 
import java.sql.*; 
 
import com.eis.base.BaseVO; 
import com.eis.base.BaseDAO; 
import com.eis.base.PageObject; 
import com.eis.portal.UserContext; 
import com.eis.util.*; 
 
 
/** 
 * 说明：log的数据库访问类 
 */ 
public class UserLogoutDAO extends BaseDAO { 
 
	/** 
	 * 构造函数 
	 */ 
	public UserLogoutDAO() { 
		super(); 
	} 
 
	/** 
	 * 构造函数 
	 */ 
	public UserLogoutDAO(String dsName) { 
		super(dsName); 
	} 
 
	/** 
	 * 初始化数据库访问语句 
	 */ 
	public void initSQL() { 
 		
 		//插入用户登录记录到用户登录记录表
		setInsertSQL("  insert into ep_user_login(USER_ID,LOGIN_TIME) values (?,?)"); 
		setUpdateSQL(" update ep_user_login set USER_ID=?,LOGIN_TIME=?"); 
		setQuerySQL(" select * from  ep_user_login");  
		setListSQL(" select * from  ep_user_login");  
		//删除用户登录记录表中的登录记录
		setDeleteSQL(" delete  from  ep_user_login");  
		//此处添加order by 语句 
		//setOrderBy(" order by REG_DT"); 
 
	} 
 
	/** 
	 * 对执行数据增加的PreparedStatement中的参数进行赋值 
	 */ 
	public void prepareInsertStatement(PreparedStatement ps, BaseVO bean) throws Exception { 
 
		UserLogoutVO vo = (UserLogoutVO) bean; 
		ps.setString(1, vo.getUser_id()); 
		ps.setString(2, vo.getLogin_time()); 
 
	} 
 
	/** 
	 * 对执行数据修改的PreparedStatement中的参数进行赋值 
	 */ 
	public void prepareUpdateStatement(PreparedStatement ps, BaseVO bean) throws Exception { 
 
		UserLogoutVO vo = (UserLogoutVO) bean; 
		ps.setString(1, vo.getUser_id()); 
		ps.setString(2, vo.getLogin_time()); 
 
	} 
 
	/** 
	 * 删除log 
	 */ 
	public void delete(BaseVO bean) throws Exception { 
		UserLogoutVO vo = (UserLogoutVO) bean;
		
		delete(getDeleteSQL()+" where USER_ID='"+vo.getUser_id()+"'" ); 
 
	} 
 
 
	/** 
	 * 查询列表，根据查询条件返回一页数据 
	 */ 
	public List queryList(PageObject page, UserContext user) throws Exception { 
 
		return null; 
 
	} 
 
	/** 
	 * 维护功能的列表，根据查询条件，返回一页数据 
	 */ 
	public List list(PageObject page, UserContext user) throws Exception { 
 
		return null; 
 
	} 
 

 
	/** 
	 * 获取列表数据的一条纪录 
	 */ 
	public BaseVO retrieveResult(ResultSet rs) throws Exception { 
 
		UserLogoutVO vo = new UserLogoutVO(); 
		vo.setUser_id(rs.getString("USER_ID")); 
		vo.setLogin_time(rs.getString("LOGIN_TIME")); 
		return vo; 
 
	} 
 
	/** 
	 * 获取明细信息 
	 */ 
	public BaseVO detail(ResultSet rs) throws Exception { 
 
		UserLogoutVO vo = new UserLogoutVO(); 
		vo.setUser_id(rs.getString("USER_ID")); 
		vo.setLogin_time(rs.getString("LOGIN_TIME")); 
		return vo; 
 
	} 
 
} 

