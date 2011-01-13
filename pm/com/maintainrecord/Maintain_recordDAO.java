 
package com.maintainrecord; 
 
import java.util.List; 
import java.sql.*; 
 
import com.eis.base.*; 
import com.eis.portal.UserContext; 
 
 
/** 
 * 说明：技术支持服务记录的数据库访问类 
 */ 
public class Maintain_recordDAO extends BaseDAO { 
 
	/** 
	 * 构造函数 
	 */ 
	public Maintain_recordDAO() { 
		super(); 
	} 
 
	/** 
	 * 构造函数 
	 */ 
	public Maintain_recordDAO(String dsName) { 
		super(dsName); 
	} 
 
	/** 
	 * 初始化数据库访问语句 
	 */ 
	public void initSQL() { 
 
		setInsertSQL("  insert into maintain_record(SEQ_NO,QUS_DATE,QUS_USER,QUS_INFO,RES_USER,RES_RESULT,RES_MEMO,RES_COST,INPUT_USER,INPUT_TIME,RES_TIME) values (?,?,?,?,?,?,?,?,?,?,?)"); 
		setUpdateSQL(" update maintain_record set QUS_DATE=?,QUS_USER=?,QUS_INFO=?,RES_USER=?,RES_RESULT=?,RES_MEMO=?,RES_COST=?,INPUT_USER=?,INPUT_TIME=?,RES_TIME=?"); 
		setQuerySQL(" select * from  maintain_record");  
		setListSQL(" select * from  maintain_record");  
		setDeleteSQL(" delete from  maintain_record");  
		//此处添加order by 语句 
		//setOrderBy(" order by REG_DT"); 
 
	} 
 
	/** 
	 * 对执行数据增加的PreparedStatement中的参数进行赋值 
	 */ 
	public void prepareInsertStatement(PreparedStatement ps, BaseVO bean) throws Exception { 
 
		Maintain_recordVO vo = (Maintain_recordVO) bean; 
		ps.setString(1, vo.getSeq_no()); 
		ps.setString(2, vo.getQus_date()); 
		ps.setString(3, vo.getQus_user()); 
		ps.setString(4, vo.getQus_info()); 
		ps.setString(5, vo.getRes_user()); 
		ps.setString(6, vo.getRes_result()); 
		ps.setString(7, vo.getRes_memo()); 
		ps.setDouble(8, vo.getRes_cost()); 
		ps.setString(9, vo.getInput_user()); 
		ps.setString(10, vo.getInput_time()); 
		ps.setString(11,vo.getRes_time());
 
	} 
 
	/** 
	 * 对执行数据修改的PreparedStatement中的参数进行赋值 
	 */ 
	public void prepareUpdateStatement(PreparedStatement ps, BaseVO bean) throws Exception { 
 
		Maintain_recordVO vo = (Maintain_recordVO) bean; 
		ps.setString(1, vo.getQus_date()); 
		ps.setString(2, vo.getQus_user()); 
		ps.setString(3, vo.getQus_info()); 
		ps.setString(4, vo.getRes_user()); 
		ps.setString(5, vo.getRes_result()); 
		ps.setString(6, vo.getRes_memo()); 
		ps.setDouble(7, vo.getRes_cost()); 
		ps.setString(8, vo.getInput_user()); 
		ps.setString(9, vo.getInput_time()); 
		ps.setString(10,vo.getRes_time());
 
	} 
 
	/** 
	 * 删除技术支持服务记录 
	 */ 
	public void delete(BaseVO vo) throws Exception { 
 
		delete(getDeleteSQL()+" where SEQ_NO='"+((Maintain_recordVO) vo).getSeq_no()+"'"); 
 
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
 
		vo = retrieve(getQuerySQL()+" where SEQ_NO='"+((Maintain_recordVO) vo).getSeq_no()+"'"); 
 
	} 
 
	/** 
	 * 获取列表数据的一条纪录 
	 */ 
	public BaseVO retrieveResult(ResultSet rs) throws Exception { 
 
		Maintain_recordVO vo = new Maintain_recordVO(); 
		vo.setSeq_no(rs.getString("SEQ_NO")); 
		vo.setQus_date(rs.getString("QUS_DATE")); 
		vo.setQus_user(rs.getString("QUS_USER")); 
		vo.setQus_info(rs.getString("QUS_INFO")); 
		vo.setRes_user(rs.getString("RES_USER")); 
		vo.setRes_result(rs.getString("RES_RESULT")); 
		vo.setRes_memo(rs.getString("RES_MEMO")); 
		vo.setRes_cost(rs.getDouble("RES_COST")); 
		vo.setInput_user(rs.getString("INPUT_USER")); 
		vo.setInput_time(rs.getString("INPUT_TIME")); 
		vo.setRes_time(rs.getString("RES_TIME"));
		return vo; 
 
	} 
 
	/** 
	 * 获取明细信息 
	 */ 
	public BaseVO detail(ResultSet rs) throws Exception { 
 
		return retrieveResult(rs);
 
	} 
 
} 

