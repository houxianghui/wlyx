 
package com.huateng.blue.notice;  
import java.util.List; 
import java.sql.*; 
 
import com.eis.base.*; 
import com.eis.portal.UserContext; 
import com.eis.util.*; 
 
 
/** 
 * ˵����1�����ݿ������ 
 */ 
public class Ep_noticeDAO extends BaseDAO { 
 
	/** 
	 * ���캯�� 
	 */ 
	public Ep_noticeDAO() { 
		super(); 
	} 
 
	/** 
	 * ���캯�� 
	 */ 
	public Ep_noticeDAO(String dsName) { 
		super(dsName); 
	} 
 
	/** 
	 * ��ʼ�����ݿ������� 
	 */ 
	public void initSQL() { 
 
		setInsertSQL("  insert into ep_notice(NOTICE_COMMENT,OPER_ID,OPER_DATE,FILENAME,NOTICE_TOP) values (?,?,?,?,?)"); 
		setUpdateSQL(" update ep_notice set NOTICE_COMMENT=?,NOTICE_TOP=?"); 
		setQuerySQL(" select * from  ep_notice");  
		setListSQL(" select * from  ep_notice");  
		setDeleteSQL(" delete from  ep_notice");  

	} 
 
	/** 
	 * ��ִ���������ӵ�PreparedStatement�еĲ������и�ֵ 
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
	 * ��ִ�������޸ĵ�PreparedStatement�еĲ������и�ֵ 
	 */ 
	public void prepareUpdateStatement(PreparedStatement ps, BaseVO bean) throws Exception { 
 
		Ep_noticeVO vo = (Ep_noticeVO) bean; 
		ps.setString(1, vo.getNotice_comment()); 
		ps.setInt(2,vo.getNotice_top());
 
	} 
 
	/** 
	 * ɾ��1 
	 */ 
	public void delete(BaseVO vo) throws Exception { 
 
		delete(getDeleteSQL()+" where NOTICE_NO="+((Ep_noticeVO) vo).getNotice_no()+""); 
 
	} 

 
	/** 
	 * ��ѯ�б����ݲ�ѯ��������һҳ���� 
	 */ 
	public List queryList(PageObject page, UserContext user) throws Exception { 
 
		return null; 
 
	} 
 
	/** 
	 * ά�����ܵ��б����ݲ�ѯ����������һҳ���� 
	 */ 
	public List list(PageObject page, UserContext user) throws Exception { 
 
		return null; 
 
	} 
 
	/** 
	 * ��ѯ��ϸ��Ϣ�����ص�һ��¼ 
	 */ 
	public void retrieve(BaseVO vo) throws Exception { 
 
		vo = retrieve(getQuerySQL()+" where NOTICE_NO="+((Ep_noticeVO) vo).getNotice_no()+""); 
 
	} 
 
	/** 
	 * ��ȡ�б����ݵ�һ����¼ 
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
	 * ��ȡ��ϸ��Ϣ 
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

