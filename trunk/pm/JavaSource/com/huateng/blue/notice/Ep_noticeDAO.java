 
package com.huateng.blue.notice;  
import java.util.List; 
import java.sql.*; 
 
import com.eis.base.*; 
import com.eis.portal.UserContext; 
import com.eis.util.*; 
 
 
/** 
 * 说明：1的数据库访问类 
 */ 
public class Ep_noticeDAO extends BaseDAO { 
 
	/** 
	 * 构造函数 
	 */ 
	public Ep_noticeDAO() { 
		super(); 
	} 
 
	/** 
	 * 构造函数 
	 */ 
	public Ep_noticeDAO(String dsName) { 
		super(dsName); 
	} 
 
	/** 
	 * 初始化数据库访问语句 
	 */ 
	public void initSQL() { 
 
		setInsertSQL("  insert into ep_notice(NOTICE_COMMENT,OPER_ID,OPER_DATE,FILENAME,NOTICE_TOP) values (?,?,?,?,?)"); 
		setUpdateSQL(" update ep_notice set NOTICE_COMMENT=?,NOTICE_TOP=?"); 
		setQuerySQL(" select * from  ep_notice");  
		setListSQL(" select * from  ep_notice");  
		setDeleteSQL(" delete from  ep_notice");  

	} 
 
	/** 
	 * 对执行数据增加的PreparedStatement中的参数进行赋值 
	 */ 
	public void prepareInsertStatement(PreparedStatement ps, BaseVO bean) throws Exception { 
 
		Ep_noticeVO vo = (Ep_noticeVO) bean;  
		ps.setString(1, vo.getNotice_comment()); 
		ps.setString(2,vo.getOper_id());
		ps.setString(3,vo.getOper_date());
		ps.setString(4,vo.getFilename());
		ps.setInt(5,vo.getNotice_top());
 
	} 
 
	/** 
	 * 对执行数据修改的PreparedStatement中的参数进行赋值 
	 */ 
	public void prepareUpdateStatement(PreparedStatement ps, BaseVO bean) throws Exception { 
 
		Ep_noticeVO vo = (Ep_noticeVO) bean; 
		ps.setString(1, vo.getNotice_comment()); 
		ps.setInt(2,vo.getNotice_top());
 
	} 
 
	/** 
	 * 删除1 
	 */ 
	public void delete(BaseVO vo) throws Exception { 
 
		delete(getDeleteSQL()+" where NOTICE_NO="+((Ep_noticeVO) vo).getNotice_no()+""); 
 
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
 
		vo = retrieve(getQuerySQL()+" where NOTICE_NO="+((Ep_noticeVO) vo).getNotice_no()+""); 
 
	} 
 
	/** 
	 * 获取列表数据的一条纪录 
	 */ 
	public BaseVO retrieveResult(ResultSet rs) throws Exception { 
 
		Ep_noticeVO vo = new Ep_noticeVO(); 
		vo.setNotice_no(rs.getInt("NOTICE_NO")); 
		vo.setNotice_comment(rs.getString("NOTICE_COMMENT").trim()); 
		vo.setOper_id(rs.getString("OPER_ID")==null?"":rs.getString("OPER_ID").trim()); 
		vo.setOper_date(rs.getString("OPER_DATE")==null?"":rs.getString("OPER_DATE").trim());
		vo.setFilename(rs.getString("FILENAME")==null?"":rs.getString("FILENAME").trim());
		vo.setNotice_top(rs.getInt("NOTICE_TOP"));
		return vo; 
 
	} 
 
	/** 
	 * 获取明细信息 
	 */ 
	public BaseVO detail(ResultSet rs) throws Exception { 
 
		Ep_noticeVO vo = new Ep_noticeVO(); 
		vo.setNotice_no(rs.getInt("NOTICE_NO")); 
		vo.setNotice_comment(rs.getString("NOTICE_COMMENT").trim()); 
		vo.setOper_id(rs.getString("OPER_ID")==null?"":rs.getString("OPER_ID").trim()); 
		vo.setOper_date(rs.getString("OPER_DATE")==null?"":rs.getString("OPER_DATE").trim());
		vo.setFilename(rs.getString("FILENAME")==null?"":rs.getString("FILENAME").trim());
		vo.setNotice_top(rs.getInt("NOTICE_TOP"));
		return vo; 
 
	} 
 
} 

