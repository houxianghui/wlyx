 
package com.eis.key.pmkey; 
 
import java.util.List; 
import java.sql.*; 
 
import com.eis.base.BaseVO; 
import com.eis.base.BaseDAO; 
import com.eis.base.PageObject; 
import com.eis.portal.UserContext; 
import com.eis.util.*; 
 
 
/** 
 * 说明：主键配置的数据库访问类 
 */ 
public class PmKeyDAO extends BaseDAO { 
 
	/** 
	 * 构造函数 
	 */ 
	public PmKeyDAO() { 
		super(); 
	} 
 
	/** 
	 * 构造函数 
	 */ 
	public PmKeyDAO(String dsName) { 
		super(dsName); 
	} 
 
	/** 
	 * 初始化数据库访问语句 
	 */ 
	public void initSQL() { 
 
		setInsertSQL("  insert into ep_pmkey(TB_NAME,KEY_FIELD,STEP_LEN,MAX_VAL) values (?,?,?,?)"); 
		setUpdateSQL(" update ep_pmkey set KEY_FIELD=?,STEP_LEN=?,MAX_VAL=?"); 
		setQuerySQL(" select * from  ep_pmkey");  
		setListSQL(" select * from  ep_pmkey");  
		setDeleteSQL(" delete from  ep_pmkey");  
		//此处添加order by 语句 
		//setOrderBy(" order by REG_DT"); 
 
	} 
 
	/** 
	 * 对执行数据增加的PreparedStatement中的参数进行赋值 
	 */ 
	public void prepareInsertStatement(PreparedStatement ps, BaseVO bean) throws Exception { 
 
		PmKeyVO vo = (PmKeyVO) bean; 
		ps.setString(1, vo.getTb_name()); 
		ps.setString(2, vo.getKey_field()); 
		ps.setInt(3, vo.getStep_len()); 
		ps.setLong(4, vo.getMax_val()); 
 
	} 
 
	/** 
	 * 对执行数据修改的PreparedStatement中的参数进行赋值 
	 */ 
	public void prepareUpdateStatement(PreparedStatement ps, BaseVO bean) throws Exception { 
 
		PmKeyVO vo = (PmKeyVO) bean; 
		ps.setString(1, vo.getKey_field()); 
		ps.setInt(2, vo.getStep_len()); 
		ps.setLong(3, vo.getMax_val()); 
 
	} 
 
	/** 
	 * 删除主键配置 
	 */ 
	public void delete(BaseVO vo) throws Exception { 
 
		delete(getDeleteSQL()+" where TB_NAME='"+((PmKeyVO) vo).getTb_name()+"'"); 
 
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
 
		PmKeyVO vo = new PmKeyVO(); 
		vo.setTb_name(rs.getString("TB_NAME").trim()); 
		vo.setKey_field(rs.getString("KEY_FIELD").trim()); 
		vo.setStep_len(rs.getInt("STEP_LEN")); 
		vo.setMax_val(rs.getLong("MAX_VAL")); 
		return vo; 
 
	} 
 
	/** 
	 * 获取明细信息 
	 */ 
	public BaseVO detail(ResultSet rs) throws Exception { 
 
		PmKeyVO vo = new PmKeyVO(); 
		vo.setTb_name(rs.getString("TB_NAME").trim()); 
		vo.setKey_field(rs.getString("KEY_FIELD").trim()); 
		vo.setStep_len(rs.getInt("STEP_LEN")); 
		vo.setMax_val(rs.getLong("MAX_VAL")); 
		return vo; 
 
	} 
 
} 

