 
package com.eis.key.pmkey; 
 
import java.util.List; 
import java.sql.*; 
 
import com.eis.base.BaseVO; 
import com.eis.base.BaseDAO; 
import com.eis.base.PageObject; 
import com.eis.portal.UserContext; 
import com.eis.util.*; 
 
 
/** 
 * ˵�����������õ����ݿ������ 
 */ 
public class PmKeyDAO extends BaseDAO { 
 
	/** 
	 * ���캯�� 
	 */ 
	public PmKeyDAO() { 
		super(); 
	} 
 
	/** 
	 * ���캯�� 
	 */ 
	public PmKeyDAO(String dsName) { 
		super(dsName); 
	} 
 
	/** 
	 * ��ʼ�����ݿ������� 
	 */ 
	public void initSQL() { 
 
		setInsertSQL("  insert into ep_pmkey(TB_NAME,KEY_FIELD,STEP_LEN,MAX_VAL) values (?,?,?,?)"); 
		setUpdateSQL(" update ep_pmkey set KEY_FIELD=?,STEP_LEN=?,MAX_VAL=?"); 
		setQuerySQL(" select * from  ep_pmkey");  
		setListSQL(" select * from  ep_pmkey");  
		setDeleteSQL(" delete from  ep_pmkey");  
		//�˴����order by ��� 
		//setOrderBy(" order by REG_DT"); 
 
	} 
 
	/** 
	 * ��ִ���������ӵ�PreparedStatement�еĲ������и�ֵ 
	 */ 
	public void prepareInsertStatement(PreparedStatement ps, BaseVO bean) throws Exception { 
 
		PmKeyVO vo = (PmKeyVO) bean; 
		ps.setString(1, vo.getTb_name()); 
		ps.setString(2, vo.getKey_field()); 
		ps.setInt(3, vo.getStep_len()); 
		ps.setLong(4, vo.getMax_val()); 
 
	} 
 
	/** 
	 * ��ִ�������޸ĵ�PreparedStatement�еĲ������и�ֵ 
	 */ 
	public void prepareUpdateStatement(PreparedStatement ps, BaseVO bean) throws Exception { 
 
		PmKeyVO vo = (PmKeyVO) bean; 
		ps.setString(1, vo.getKey_field()); 
		ps.setInt(2, vo.getStep_len()); 
		ps.setLong(3, vo.getMax_val()); 
 
	} 
 
	/** 
	 * ɾ���������� 
	 */ 
	public void delete(BaseVO vo) throws Exception { 
 
		delete(getDeleteSQL()+" where TB_NAME='"+((PmKeyVO) vo).getTb_name()+"'"); 
 
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
	 * ��ȡ�б����ݵ�һ����¼ 
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
	 * ��ȡ��ϸ��Ϣ 
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

