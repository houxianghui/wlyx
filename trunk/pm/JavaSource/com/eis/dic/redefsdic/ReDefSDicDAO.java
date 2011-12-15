 
package com.eis.dic.redefsdic; 
 
import java.util.List; 
import java.sql.*; 
 
import com.eis.base.BaseVO; 
import com.eis.base.BaseDAO; 
import com.eis.base.PageObject; 
import com.eis.portal.UserContext; 
import com.eis.util.*; 
 
 
/** 
 * 说明：自定义单级字典的数据库访问类 
 */ 
public class ReDefSDicDAO extends BaseDAO { 
 
	/** 
	 * 构造函数 
	 */ 
	public ReDefSDicDAO() { 
		super(); 
	} 
 
	/** 
	 * 构造函数 
	 */ 
	public ReDefSDicDAO(String dsName) { 
		super(dsName); 
	} 
 
	/** 
	 * 初始化数据库访问语句 
	 */ 
	public void initSQL() { 
 
		setInsertSQL("  insert into ep_redef_sdic(TYPE_ID,CAPTION,STMT,MEMO,USER_ID,REG_DT) values (?,?,?,?,?,?)"); 
		setUpdateSQL(" update ep_redef_sdic set CAPTION=?,STMT=?,MEMO=?,USER_ID=?,REG_DT=?"); 
		setQuerySQL(" select * from  ep_redef_sdic");  
		setListSQL(" select * from  ep_redef_sdic");  
		setDeleteSQL(" delete from  ep_redef_sdic");  
		//此处添加order by 语句 
		//setOrderBy(" order by REG_DT"); 
 
	} 
 
	/** 
	 * 对执行数据增加的PreparedStatement中的参数进行赋值 
	 */ 
	public void prepareInsertStatement(PreparedStatement ps, BaseVO bean) throws Exception { 
 
		ReDefSDicVO vo = (ReDefSDicVO) bean; 
		ps.setString(1, vo.getType_id()); 
		ps.setString(2, vo.getCaption()); 
		ps.setString(3, vo.getStmt()); 
		ps.setString(4, vo.getMemo()); 
		ps.setString(5, vo.getUser_id()); 
		ps.setString(6, vo.getReg_dt()); 
 
	} 
 
	/** 
	 * 对执行数据修改的PreparedStatement中的参数进行赋值 
	 */ 
	public void prepareUpdateStatement(PreparedStatement ps, BaseVO bean) throws Exception { 
 
		ReDefSDicVO vo = (ReDefSDicVO) bean; 
		ps.setString(1, vo.getCaption()); 
		ps.setString(2, vo.getStmt()); 
		ps.setString(3, vo.getMemo()); 
		ps.setString(4, vo.getUser_id()); 
		ps.setString(5, vo.getReg_dt()); 
 
	} 
 
	/** 
	 * 删除自定义单级字典 
	 */ 
	public void delete(BaseVO vo) throws Exception { 
 
		delete(getDeleteSQL()+" where TYPE_ID='"+((ReDefSDicVO) vo).getType_id()+"'"); 
 
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
 
		ReDefSDicVO vo = new ReDefSDicVO(); 
		vo.setType_id(rs.getString("TYPE_ID").trim()); 
		vo.setCaption(rs.getString("CAPTION").trim()); 
		vo.setMemo(rs.getString("MEMO").trim()); 
		vo.setUser_id(rs.getString("USER_ID").trim()); 
		vo.setReg_dt(rs.getString("REG_DT").trim()); 
		return vo; 
 
	} 
 
	/** 
	 * 获取明细信息 
	 */ 
	public BaseVO detail(ResultSet rs) throws Exception { 
 
		ReDefSDicVO vo = new ReDefSDicVO(); 
		vo.setType_id(rs.getString("TYPE_ID").trim()); 
		vo.setCaption(rs.getString("CAPTION").trim()); 
		vo.setStmt(rs.getString("STMT").trim()); 
		vo.setMemo(rs.getString("MEMO").trim()); 
		vo.setUser_id(rs.getString("USER_ID").trim()); 
		vo.setReg_dt(rs.getString("REG_DT").trim()); 
		return vo; 
 
	} 
 
} 

