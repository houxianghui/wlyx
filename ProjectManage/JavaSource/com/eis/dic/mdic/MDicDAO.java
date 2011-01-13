 
package com.eis.dic.mdic; 
 
import java.util.List; 
import java.sql.*; 
 
import com.eis.base.*; 
import com.eis.portal.UserContext; 
import com.eis.util.*; 
 
 
/** 
 * 说明：多级字典的数据库访问类 
 */ 
public class MDicDAO extends BaseDAO { 
 
	/** 
	 * 构造函数 
	 */ 
	public MDicDAO() { 
		super(); 
	} 
 
	/** 
	 * 构造函数 
	 */ 
	public MDicDAO(String dsName) { 
		super(dsName); 
	} 
 
	/** 
	 * 初始化数据库访问语句 
	 */ 
	public void initSQL() { 
 
		setInsertSQL("  insert into ep_mdic(SYS_ID,TYPE_ID,PARENT_ID,ITEM_ID,ITEM_VAL,LIST_ORDER,ITEM_LEVEL,LOGIC_ID,STAT) values (?,?,?,?,?,?,?,?,?)"); 
		setUpdateSQL(" update ep_mdic set TYPE_ID=?,PARENT_ID=?,ITEM_ID=?,ITEM_VAL=?,LIST_ORDER=?,ITEM_LEVEL=?,LOGIC_ID=?,STAT=?"); 
		setQuerySQL(" select * from  ep_mdic");  
		setListSQL(" select * from  ep_mdic");  
		setDeleteSQL(" delete from  ep_mdic");  
		//此处添加order by 语句 
		//setOrderBy(" order by REG_DT"); 
 
	} 
 
	/** 
	 * 对执行数据增加的PreparedStatement中的参数进行赋值 
	 */ 
	public void prepareInsertStatement(PreparedStatement ps, BaseVO bean) throws Exception { 
 
		MDicVO vo = (MDicVO) bean; 
		ps.setLong(1, vo.getSys_id()); 
		ps.setString(2, vo.getType_id()); 
		ps.setLong(3, vo.getParent_id()); 
		ps.setString(4, vo.getItem_id()); 
		ps.setString(5, vo.getItem_val()); 
		ps.setInt(6, vo.getList_order()); 
		ps.setInt(7, vo.getItem_level()); 
		ps.setString(8, vo.getLogic_id()); 
		ps.setString(9, vo.getStatus()); 
 
	} 
 
	/** 
	 * 对执行数据修改的PreparedStatement中的参数进行赋值 
	 */ 
	public void prepareUpdateStatement(PreparedStatement ps, BaseVO bean) throws Exception { 
 
		MDicVO vo = (MDicVO) bean; 
		ps.setString(1, vo.getType_id()); 
		ps.setLong(2, vo.getParent_id()); 
		ps.setString(3, vo.getItem_id()); 
		ps.setString(4, vo.getItem_val()); 
		ps.setInt(5, vo.getList_order()); 
		ps.setInt(6, vo.getItem_level()); 
		ps.setString(7, vo.getLogic_id()); 
		ps.setString(8, vo.getStatus()); 
 
	} 
 
	/** 
	 * 删除多级字典 
	 */ 
	public void delete(BaseVO vo) throws Exception { 
 
		delete(getDeleteSQL()+" where SYS_ID="+((MDicVO) vo).getSys_id()+""); 
 
	} 
 

	/** 
	 * 查询列表，根据查询条件，返回一页数据 
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
 
		MDicVO vo = new MDicVO(); 
		vo.setSys_id(rs.getLong("SYS_ID")); 
		vo.setType_id(rs.getString("TYPE_ID").trim()); 
		vo.setParent_id(rs.getLong("PARENT_ID")); 
		vo.setItem_id(rs.getString("ITEM_ID").trim()); 
		vo.setItem_val(rs.getString("ITEM_VAL").trim()); 
		vo.setList_order(rs.getShort("LIST_ORDER")); 
		vo.setItem_level(rs.getShort("ITEM_LEVEL")); 
		vo.setLogic_id(rs.getString("LOGIC_ID").trim()); 
		vo.setStatus(rs.getString("STAT").trim()); 
		return vo; 
 
	} 
 
	/** 
	 * 获取明细信息 
	 */ 
	public BaseVO detail(ResultSet rs) throws Exception { 
 
		MDicVO vo = new MDicVO(); 
		vo.setSys_id(rs.getLong("SYS_ID")); 
		vo.setType_id(rs.getString("TYPE_ID").trim()); 
		vo.setParent_id(rs.getLong("PARENT_ID")); 
		vo.setItem_id(rs.getString("ITEM_ID").trim()); 
		vo.setItem_val(rs.getString("ITEM_VAL").trim()); 
		vo.setList_order(rs.getShort("LIST_ORDER")); 
		vo.setItem_level(rs.getShort("ITEM_LEVEL")); 
		vo.setLogic_id(rs.getString("LOGIC_ID").trim()); 
		vo.setStatus(rs.getString("STAT").trim()); 
		return vo; 
 
	} 
 
} 

