 
package com.lx; 
 
import java.util.List; 
import java.sql.*; 
 
import com.eis.base.*; 
import com.eis.portal.UserContext; 
import com.eis.util.*; 
 
 
/** 
 * 说明：联系信息的数据库访问类 
 */ 
public class Lx_infoDAO extends BaseDAO { 
 
	/** 
	 * 构造函数 
	 */ 
	public Lx_infoDAO() { 
		super(); 
	} 
 
	/** 
	 * 构造函数 
	 */ 
	public Lx_infoDAO(String dsName) { 
		super(dsName); 
	} 
 
	/** 
	 * 初始化数据库访问语句 
	 */ 
	public void initSQL() { 
 
		setInsertSQL("  insert into lx_info(lx_id,depart,name,phone,mobile,email,stuff_id) values (?,?,?,?,?,?,?)"); 
		setUpdateSQL(" update lx_info set depart=?,name=?,phone=?,mobile=?,email=?,stuff_id=?"); 
		setQuerySQL(" select * from  lx_info");  
		setListSQL(" select * from  lx_info");  
		setDeleteSQL(" delete from  lx_info");  
		//此处添加order by 语句 
		//setOrderBy(" order by REG_DT"); 
 
	} 
 
	/** 
	 * 对执行数据增加的PreparedStatement中的参数进行赋值 
	 */ 
	public void prepareInsertStatement(PreparedStatement ps, BaseVO bean) throws Exception { 
 
		Lx_infoVO vo = (Lx_infoVO) bean; 
		ps.setString(1, vo.getLx_id()); 
		ps.setString(2, vo.getDepart()); 
		ps.setString(3, vo.getName()); 
		ps.setString(4, vo.getPhone()); 
		ps.setString(5, vo.getMobile()); 
		ps.setString(6, vo.getEmail()); 
		ps.setString(7,vo.getStuff_id());
 
	} 
 
	/** 
	 * 对执行数据修改的PreparedStatement中的参数进行赋值 
	 */ 
	public void prepareUpdateStatement(PreparedStatement ps, BaseVO bean) throws Exception { 
 
		Lx_infoVO vo = (Lx_infoVO) bean; 
		ps.setString(1, vo.getDepart()); 
		ps.setString(2, vo.getName()); 
		ps.setString(3, vo.getPhone()); 
		ps.setString(4, vo.getMobile()); 
		ps.setString(5, vo.getEmail()); 
		ps.setString(6,vo.getStuff_id());
 
	} 
 
	/** 
	 * 删除联系信息 
	 */ 
	public void delete(BaseVO vo) throws Exception { 
 
		delete(getDeleteSQL()+" where LX_ID='"+((Lx_infoVO) vo).getLx_id()+"'"); 
 
	} 
 
	/** 
	 * 列表查询全部数据 
	 */ 

 
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
 
		vo = retrieve(getQuerySQL()+" where LX_ID='"+((Lx_infoVO) vo).getLx_id()+"'"); 
 
	} 
 
	/** 
	 * 获取列表数据的一条纪录 
	 */ 
	public BaseVO retrieveResult(ResultSet rs) throws Exception { 
 
		Lx_infoVO vo = new Lx_infoVO(); 
		vo.setLx_id(rs.getString("LX_ID").trim()); 
		vo.setDepart(rs.getString("DEPART").trim()); 
		vo.setName(rs.getString("NAME").trim()); 
		vo.setPhone(rs.getString("PHONE").trim()); 
		vo.setMobile(rs.getString("MOBILE").trim()); 
		vo.setEmail(rs.getString("EMAIL").trim()); 
		vo.setStuff_id(rs.getString("STUFF_ID"));
		return vo; 
 
	} 
 
	/** 
	 * 获取明细信息 
	 */ 
	public BaseVO detail(ResultSet rs) throws Exception { 
 
		Lx_infoVO vo = new Lx_infoVO(); 
		vo.setLx_id(rs.getString("LX_ID").trim()); 
		vo.setDepart(rs.getString("DEPART").trim()); 
		vo.setName(rs.getString("NAME").trim()); 
		vo.setPhone(rs.getString("PHONE").trim()); 
		vo.setMobile(rs.getString("MOBILE").trim()); 
		vo.setEmail(rs.getString("EMAIL").trim()); 
		vo.setStuff_id(rs.getString("STUFF_ID")); 
		return vo; 
 
	} 
 
} 

