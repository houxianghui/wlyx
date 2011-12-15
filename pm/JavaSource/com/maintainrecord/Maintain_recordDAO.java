 
package com.maintainrecord; 
 
import java.util.List; 
import java.sql.*; 
 
import com.eis.base.*; 
import com.eis.portal.UserContext; 
 
 
/** 
 * ˵��������֧�ַ����¼�����ݿ������ 
 */ 
public class Maintain_recordDAO extends BaseDAO { 
 
	/** 
	 * ���캯�� 
	 */ 
	public Maintain_recordDAO() { 
		super(); 
	} 
 
	/** 
	 * ���캯�� 
	 */ 
	public Maintain_recordDAO(String dsName) { 
		super(dsName); 
	} 
 
	/** 
	 * ��ʼ�����ݿ������� 
	 */ 
	public void initSQL() { 
 
		setInsertSQL("  insert into maintain_record(SEQ_NO,QUS_DATE,QUS_USER,QUS_INFO,RES_USER,RES_RESULT,RES_MEMO,RES_COST,INPUT_USER,INPUT_TIME,RES_TIME) values (?,?,?,?,?,?,?,?,?,?,?)"); 
		setUpdateSQL(" update maintain_record set QUS_DATE=?,QUS_USER=?,QUS_INFO=?,RES_USER=?,RES_RESULT=?,RES_MEMO=?,RES_COST=?,INPUT_USER=?,INPUT_TIME=?,RES_TIME=?"); 
		setQuerySQL(" select * from  maintain_record");  
		setListSQL(" select * from  maintain_record");  
		setDeleteSQL(" delete from  maintain_record");  
		//�˴����order by ��� 
		//setOrderBy(" order by REG_DT"); 
 
	} 
 
	/** 
	 * ��ִ���������ӵ�PreparedStatement�еĲ������и�ֵ 
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
	 * ��ִ�������޸ĵ�PreparedStatement�еĲ������и�ֵ 
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
	 * ɾ������֧�ַ����¼ 
	 */ 
	public void delete(BaseVO vo) throws Exception { 
 
		delete(getDeleteSQL()+" where SEQ_NO='"+((Maintain_recordVO) vo).getSeq_no()+"'"); 
 
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
 
		vo = retrieve(getQuerySQL()+" where SEQ_NO='"+((Maintain_recordVO) vo).getSeq_no()+"'"); 
 
	} 
 
	/** 
	 * ��ȡ�б����ݵ�һ����¼ 
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
	 * ��ȡ��ϸ��Ϣ 
	 */ 
	public BaseVO detail(ResultSet rs) throws Exception { 
 
		return retrieveResult(rs);
 
	} 
 
} 

