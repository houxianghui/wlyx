 
package com.eis.dic.mdic; 
 
import java.util.List; 
import java.sql.*; 
 
import com.eis.base.*; 
import com.eis.portal.UserContext; 
import com.eis.util.*; 
 
 
/** 
 * ˵�����༶�ֵ�����ݿ������ 
 */ 
public class MDicDAO extends BaseDAO { 
 
	/** 
	 * ���캯�� 
	 */ 
	public MDicDAO() { 
		super(); 
	} 
 
	/** 
	 * ���캯�� 
	 */ 
	public MDicDAO(String dsName) { 
		super(dsName); 
	} 
 
	/** 
	 * ��ʼ�����ݿ������� 
	 */ 
	public void initSQL() { 
 
		setInsertSQL("  insert into ep_mdic(SYS_ID,TYPE_ID,PARENT_ID,ITEM_ID,ITEM_VAL,LIST_ORDER,ITEM_LEVEL,LOGIC_ID,STAT) values (?,?,?,?,?,?,?,?,?)"); 
		setUpdateSQL(" update ep_mdic set TYPE_ID=?,PARENT_ID=?,ITEM_ID=?,ITEM_VAL=?,LIST_ORDER=?,ITEM_LEVEL=?,LOGIC_ID=?,STAT=?"); 
		setQuerySQL(" select * from  ep_mdic");  
		setListSQL(" select * from  ep_mdic");  
		setDeleteSQL(" delete from  ep_mdic");  
		//�˴����order by ��� 
		//setOrderBy(" order by REG_DT"); 
 
	} 
 
	/** 
	 * ��ִ���������ӵ�PreparedStatement�еĲ������и�ֵ 
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
	 * ��ִ�������޸ĵ�PreparedStatement�еĲ������и�ֵ 
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
	 * ɾ���༶�ֵ� 
	 */ 
	public void delete(BaseVO vo) throws Exception { 
 
		delete(getDeleteSQL()+" where SYS_ID="+((MDicVO) vo).getSys_id()+""); 
 
	} 
 

	/** 
	 * ��ѯ�б����ݲ�ѯ����������һҳ���� 
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
	 * ��ȡ��ϸ��Ϣ 
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

