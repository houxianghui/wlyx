 
package com.eis.portal.userlogin; 
 
import java.util.List; 
import java.sql.*; 
 
import com.eis.base.BaseVO; 
import com.eis.base.BaseDAO; 
import com.eis.base.PageObject; 
import com.eis.portal.UserContext; 
import com.eis.util.*; 
 
 
/** 
 * 说明：管理用户登录记录表的数据库访问类 
 */ 
public class UserLoginDAO extends BaseDAO { 
 
	/** 
	 * 构造函数 
	 */ 
	public UserLoginDAO() { 
		super(); 
	} 
 
	/** 
	 * 构造函数 
	 */ 
	public UserLoginDAO(String dsName) { 
		super(dsName); 
	} 
 
	/** 
	 * 初始化数据库访问语句 
	 */ 
	public void initSQL() { 
 
		setInsertSQL("  insert into ep_user_login(USER_ID,LOGIN_TIME,CLIENT_IP) values (?,?,?)"); 
		 
		setQuerySQL(" select * from  ep_user_login");  
		setListSQL(" select * from  ep_user_login");  
		setDeleteSQL(" delete from  ep_user_login");  
		//此处添加order by 语句 
		//setOrderBy(" order by REG_DT"); 
 
	} 
 
	/** 
	 * 对执行数据增加的PreparedStatement中的参数进行赋值 
	 */ 
	public void prepareInsertStatement(PreparedStatement ps, BaseVO bean) throws Exception { 
 
		UserLoginVO vo = (UserLoginVO) bean; 
		ps.setString(1, vo.getUser_id()); 
		ps.setString(2, vo.getLogin_time()); 
		ps.setString(3, vo.getClient_ip());
 
	} 
 
	/** 
	 * 对执行数据修改的PreparedStatement中的参数进行赋值 
	 */ 
	public void prepareUpdateStatement(PreparedStatement ps, BaseVO bean) throws Exception { 
 
		UserLoginVO vo = (UserLoginVO) bean; 
		ps.setString(1, vo.getLogin_time()); 
 
	} 
 
	/** 
	 * 删除管理用户登录记录表 
	 */ 
	public void delete(BaseVO vo) throws Exception { 
 
		delete(getDeleteSQL()+" where USER_ID='"+((UserLoginVO) vo).getUser_id()+"'"); 
 
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
 
		UserLoginVO vo = new UserLoginVO(); 
		vo.setUser_id(rs.getString("USER_ID").trim()); 
		vo.setLogin_time(rs.getString("LOGIN_TIME").trim());
		vo.setClient_ip(rs.getString("CLIENT_IP").trim());  
		return vo; 
 
	} 
 
	/** 
	 * 获取明细信息 
	 */ 
	public BaseVO detail(ResultSet rs) throws Exception { 
 
		UserLoginVO vo = new UserLoginVO(); 
		vo.setUser_id(rs.getString("USER_ID").trim()); 
		vo.setLogin_time(rs.getString("LOGIN_TIME").trim()); 
		vo.setClient_ip(rs.getString("CLIENT_IP").trim());
		return vo; 
 
	} 
 
} 

