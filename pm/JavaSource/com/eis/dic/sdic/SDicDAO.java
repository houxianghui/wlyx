 
package com.eis.dic.sdic; 
 
import java.util.List; 
import java.sql.*; 
 
import com.eis.base.BaseVO; 
import com.eis.base.BaseDAO; 
import com.eis.base.PageObject; 
import com.eis.portal.UserContext; 
import com.eis.util.*; 
import com.eis.key.*;
 
/** 
 * ˵���������ֵ�����ݿ������ 
 */ 
public class SDicDAO extends BaseDAO { 
 
	/** 
	 * ���캯�� 
	 */ 
	public SDicDAO() { 
		super(); 
	} 
 
	/** 
	 * ���캯�� 
	 */ 
	public SDicDAO(String dsName) { 
		super(dsName); 
	} 
 
	/** 
	 * ��ʼ�����ݿ������� 
	 */ 
	public void initSQL() { 
 
		setInsertSQL("  insert into ep_sdic(SYS_ID,TYPE_ID,ITEM_CODE,ITEM_VAL,LIST_ORDER,LOGIC_ID,STAT) values (?,?,?,?,?,?,?)"); 
		setUpdateSQL(" update ep_sdic set TYPE_ID=?,ITEM_CODE=?,ITEM_VAL=?,LIST_ORDER=?,LOGIC_ID=?,STAT=?"); 
		setQuerySQL(" select * from  ep_sdic");  
		setListSQL(" select * from  ep_sdic");  
		setDeleteSQL(" delete from  ep_sdic");  
		//�˴����order by ��� 
		//setOrderBy(" order by REG_DT"); 
 
	} 
 
	/** 
	 * ��ִ���������ӵ�PreparedStatement�еĲ������и�ֵ 
	 */ 
	public void prepareInsertStatement(PreparedStatement ps, BaseVO bean) throws Exception { 
 
		SDicVO vo = (SDicVO) bean; 
		
		ps.setLong(1,KeyGenerator.getNextKey("ep_sdic")); 		
		ps.setString(2, vo.getType_id()); 
		ps.setString(3, vo.getItem_code()); 
		ps.setString(4, vo.getItem_val()); 
		ps.setShort(5, vo.getList_order()); 
		ps.setString(6, vo.getLogic_id()); 
		ps.setString(7, vo.getStatus()); 
 
	} 
 
	/** 
	 * ��ִ�������޸ĵ�PreparedStatement�еĲ������и�ֵ 
	 */ 
	public void prepareUpdateStatement(PreparedStatement ps, BaseVO bean) throws Exception { 
 
		SDicVO vo = (SDicVO) bean; 
		ps.setString(1, vo.getType_id()); 
		ps.setString(2, vo.getItem_code()); 
		ps.setString(3, vo.getItem_val()); 
		ps.setShort(4, vo.getList_order()); 
		ps.setString(5, vo.getLogic_id()); 
		ps.setString(6, vo.getStatus()); 
 
	} 
 
	/** 
	 * ɾ�������ֵ� 
	 */ 
	public void delete(BaseVO vo) throws Exception { 
 
		delete(getDeleteSQL()+" where SYS_ID="+((SDicVO) vo).getSys_id()+""); 
 
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
 
		SDicVO vo = new SDicVO(); 
		vo.setSys_id(rs.getLong("SYS_ID")); 
		vo.setType_id(rs.getString("TYPE_ID").trim()); 
		vo.setItem_code(rs.getString("ITEM_CODE").trim()); 
		vo.setItem_val(rs.getString("ITEM_VAL").trim()); 
		vo.setList_order(rs.getShort("LIST_ORDER")); 
		vo.setLogic_id(rs.getString("LOGIC_ID").trim()); 
		vo.setStatus(rs.getString("STAT").trim()); 
		
		if (vo.getStatus().equals("0"))
					vo.setStatus("����");
				else {
					vo.setStatus("����");

				}
		return vo; 
 
	} 
 
	/** 
	 * ��ȡ��ϸ��Ϣ 
	 */ 
	public BaseVO detail(ResultSet rs) throws Exception { 
 
		SDicVO vo = new SDicVO(); 
		vo.setSys_id(rs.getLong("SYS_ID")); 
		vo.setType_id(rs.getString("TYPE_ID").trim()); 
		vo.setItem_code(rs.getString("ITEM_CODE").trim()); 
		vo.setItem_val(rs.getString("ITEM_VAL").trim()); 
		vo.setList_order(rs.getShort("LIST_ORDER")); 
		vo.setLogic_id(rs.getString("LOGIC_ID").trim()); 
		vo.setStatus(rs.getString("STAT").trim()); 
		if (vo.getStatus().equals("0"))
			vo.setStatus("����");
		else {
			vo.setStatus("����");

			}
		return vo; 
 
	} 
 
} 

