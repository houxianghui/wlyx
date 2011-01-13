 
package com.eis.portal.oplog; 
 
import java.util.List; 
import java.sql.*; 
 
import com.eis.base.*; 
import com.eis.portal.UserContext; 
import com.eis.util.*; 

import com.eis.key.*;
 
 
/** 
 * ˵����������־�����ݿ������ 
 */ 
public class OpLogDAO extends BaseDAO { 
 
	/** 
	 * ���캯�� 
	 */ 
	public OpLogDAO() { 
		super(); 
	} 
 
	/** 
	 * ���캯�� 
	 */ 
	public OpLogDAO(String dsName) { 
		super(dsName); 
	} 
 
	/** 
	 * ��ʼ�����ݿ������� 
	 */ 
	public void initSQL() { 
 
		setInsertSQL("  insert into ep_op_log(USER_ID,EVENT_TIME,EVENT_TYPE,ORG_ID,ROLE_ID,OP_ID,MEMO,OP_KEY,SYS_ID) values (?,?,?,?,?,?,?,?,?)"); 
		setUpdateSQL(" update ep_op_log set EVENT_TYPE=?,ORG_ID=?,ROLE_ID=?,OP_ID=?,MEMO=?,OP_KEY=?"); 
		setQuerySQL(" select * from  ep_op_log_view");  
		setListSQL(" select * from  ep_op_log_view");  
		setDeleteSQL(" delete from  ep_op_log");  
		//�˴����order by ��� 
		//setOrderBy(" order by REG_DT"); 
 
	} 
 
	/** 
	 * ��ִ���������ӵ�PreparedStatement�еĲ������и�ֵ 
	 */ 
	public void prepareInsertStatement(PreparedStatement ps, BaseVO bean) throws Exception { 
 
		OpLogVO vo = (OpLogVO) bean; 
		ps.setLong(9,KeyGenerator.getNextKey("ep_op_log")); 
		ps.setString(1, vo.getUser_id()); 
		ps.setString(2, vo.getEvent_time()); 
		ps.setString(3, vo.getEvent_type()); 
		ps.setString(4, vo.getOrg_id()); 
		ps.setString(5, vo.getRole_id()); 
		ps.setString(6, vo.getOp_id()); 
		ps.setString(7, vo.getMemo()); 
		ps.setString(8, vo.getOp_key()); 
 
	} 
 
	/** 
	 * ��ִ�������޸ĵ�PreparedStatement�еĲ������и�ֵ 
	 */ 
	public void prepareUpdateStatement(PreparedStatement ps, BaseVO bean) throws Exception { 
 
		OpLogVO vo = (OpLogVO) bean; 
		ps.setString(1, vo.getEvent_type()); 
		ps.setString(2, vo.getOrg_id()); 
		ps.setString(3, vo.getRole_id()); 
		ps.setString(4, vo.getOp_id()); 
		ps.setString(5, vo.getMemo()); 
		ps.setString(6, vo.getOp_key()); 
 
	} 
 
	/** 
	 * ɾ��������־ 
	 */ 
	public void delete(BaseVO vo) throws Exception { 
 
		delete(getDeleteSQL()+" where USER_ID='"+((OpLogVO) vo).getUser_id()+"'"+" and EVENT_TIME='"+((OpLogVO) vo).getEvent_time()+"'"); 
 
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
 
	@Override
	public int add(BaseVO vo, Connection conn) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}
 
	/** 
	 * ��ȡ�б����ݵ�һ����¼ 
	 */ 
	public BaseVO retrieveResult(ResultSet rs) throws Exception { 
 
		OpLogVO vo = new OpLogVO();
		vo.setSys_id(rs.getLong("SYS_ID")); 
		vo.setUser_id(rs.getString("USER_ID").trim());
		vo.setUser_login_id(rs.getString("LOGIN_ID").trim());
		vo.setUser_name(rs.getString("USER_NAME").trim()); 
		vo.setEvent_time(rs.getString("EVENT_TIME").trim()); 
		vo.setEvent_type(rs.getString("EVENT_TYPE").trim()); 
		vo.setOrg_id(rs.getString("ORG_ID").trim()); 
		vo.setOp_id(rs.getString("OP_ID").trim()); 
		vo.setMemo(rs.getString("MEMO").trim()); 
		return vo; 
 
	} 
 
	/** 
	 * ��ȡ��ϸ��Ϣ 
	 */ 
	public BaseVO detail(ResultSet rs) throws Exception { 
 
		OpLogVO vo = new OpLogVO(); 
		vo.setSys_id(rs.getLong("SYS_ID")); 
		vo.setUser_id(rs.getString("USER_ID").trim());
		vo.setUser_login_id(rs.getString("LOGIN_ID").trim());
		vo.setUser_name(rs.getString("USER_NAME").trim());  
		vo.setEvent_time(rs.getString("EVENT_TIME").trim()); 
		vo.setEvent_type(rs.getString("EVENT_TYPE").trim()); 
		vo.setOrg_id(rs.getString("ORG_ID").trim()); 
		vo.setRole_id(rs.getString("ROLE_ID").trim()); 
		vo.setOp_id(rs.getString("OP_ID").trim()); 
		vo.setMemo(rs.getString("MEMO").trim()); 
		vo.setOp_key(rs.getString("OP_KEY").trim()); 
		return vo; 
 
	} 
 
} 

