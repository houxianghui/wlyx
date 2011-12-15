 
package com.work; 
 
import java.util.List; 
import java.sql.*; 
 
import com.eis.base.*; 
import com.eis.portal.UserContext; 
 
 
/** 
 * 说明：工作列表的数据库访问类 
 */ 
public class PROJECT_LISTDAO extends BaseDAO { 
 
	/** 
	 * 构造函数 
	 */ 
	public PROJECT_LISTDAO() { 
		super(); 
	} 
 
	/** 
	 * 构造函数 
	 */ 
	public PROJECT_LISTDAO(String dsName) { 
		super(dsName); 
	} 
 
	/** 
	 * 初始化数据库访问语句 
	 */ 
	public void initSQL() { 
 
		setInsertSQL("  insert into PROJECT_LIST(USER_ID,PROJECT_NO,PROJECT_NAME,CURR_STEP,START_DATE,END_DATE) values (?,?,?,?,?,?)"); 
		setUpdateSQL(" update PROJECT_LIST set PROJECT_NAME=?,CURR_STEP=?,START_DATE=?,END_DATE=?"); 
		setQuerySQL(" select * from  PROJECT_LIST");  
		setListSQL(" select * from  PROJECT_LIST");  
		setDeleteSQL(" delete from  PROJECT_LIST");  
		//此处添加order by 语句 
		//setOrderBy(" order by REG_DT"); 
 
	} 
 
	/** 
	 * 对执行数据增加的PreparedStatement中的参数进行赋值 
	 */ 
	public void prepareInsertStatement(PreparedStatement ps, BaseVO bean) throws Exception { 
 
		PROJECT_LISTVO vo = (PROJECT_LISTVO) bean; 
		ps.setString(1, vo.getUser_id()); 
		ps.setString(2, vo.getProject_no()); 
		ps.setString(3, vo.getProject_name()); 
		ps.setString(4, vo.getCurr_step()); 
		ps.setString(5, vo.getStart_date()); 
		ps.setString(6, vo.getEnd_date()); 
 
	} 
 
	/** 
	 * 对执行数据修改的PreparedStatement中的参数进行赋值 
	 */ 
	public void prepareUpdateStatement(PreparedStatement ps, BaseVO bean) throws Exception { 
 
		PROJECT_LISTVO vo = (PROJECT_LISTVO) bean; 
		ps.setString(1, vo.getProject_name()); 
		ps.setString(2, vo.getCurr_step()); 
		ps.setString(3, vo.getStart_date()); 
		ps.setString(4, vo.getEnd_date()); 
 
	} 
 
	/** 
	 * 删除工作列表 
	 */ 
	public void delete(BaseVO vo) throws Exception { 
 
		delete(getDeleteSQL()+" where USER_ID='"+((PROJECT_LISTVO) vo).getUser_id()+"'"+" and PROJECT_NO='"+((PROJECT_LISTVO) vo).getProject_no()+"'"); 
 
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
	 * 查询明细信息，返回单一纪录 
	 */ 
	public void retrieve(BaseVO vo) throws Exception { 
 
		vo = retrieve(getQuerySQL()+" where USER_ID='"+((PROJECT_LISTVO) vo).getUser_id()+"'"+" and PROJECT_NO='"+((PROJECT_LISTVO) vo).getProject_no()+"'"); 
 
	} 
 
	/** 
	 * 获取列表数据的一条纪录 
	 */ 
	public BaseVO retrieveResult(ResultSet rs) throws Exception { 
 
		PROJECT_LISTVO vo = new PROJECT_LISTVO(); 
		vo.setUser_id(rs.getString("USER_ID").trim()); 
		vo.setProject_no(rs.getString("PROJECT_NO").trim()); 
		vo.setProject_name(rs.getString("PROJECT_NAME").trim()); 
		vo.setCurr_step(rs.getString("CURR_STEP").trim()); 
		vo.setStart_date(rs.getString("START_DATE").trim()); 
		vo.setEnd_date(rs.getString("END_DATE").trim()); 
		return vo; 
 
	} 
 
	/** 
	 * 获取明细信息 
	 */ 
	public BaseVO detail(ResultSet rs) throws Exception { 
 
		PROJECT_LISTVO vo = new PROJECT_LISTVO(); 
		vo.setUser_id(rs.getString("USER_ID").trim()); 
		vo.setProject_no(rs.getString("PROJECT_NO").trim()); 
		vo.setProject_name(rs.getString("PROJECT_NAME").trim()); 
		vo.setCurr_step(rs.getString("CURR_STEP").trim()); 
		vo.setStart_date(rs.getString("START_DATE").trim()); 
		vo.setEnd_date(rs.getString("END_DATE").trim()); 
		return vo; 
 
	} 
 
} 

