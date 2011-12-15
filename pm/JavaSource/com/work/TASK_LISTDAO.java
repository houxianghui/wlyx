 
package com.work; 
 
import java.util.List; 
import java.sql.*; 
 
import com.eis.base.*; 
import com.eis.portal.UserContext; 
 
 
/** 
 * ˵���������б�����ݿ������ 
 */ 
public class TASK_LISTDAO extends BaseDAO { 
 
	/** 
	 * ���캯�� 
	 */ 
	public TASK_LISTDAO() { 
		super(); 
	} 
 
	/** 
	 * ���캯�� 
	 */ 
	public TASK_LISTDAO(String dsName) { 
		super(dsName); 
	} 
 
	/** 
	 * ��ʼ�����ݿ������� 
	 */ 
	public void initSQL() { 
 
		setInsertSQL("  insert into TASK_LIST(TASK_NO,TASK_DATE,TASK_STEP,TASK_TYPE,TASK_GOAL,TASK_COST,TASK_USER,UPDATE_DATE,TASK_MEMO,PROJECT_NO,ID) values (?,?,?,?,?,?,?,?,?,?,?)"); 
		setUpdateSQL(" update TASK_LIST set TASK_DATE=?,TASK_STEP=?,TASK_TYPE=?,TASK_GOAL=?,TASK_COST=?,TASK_USER=?,UPDATE_DATE=?,TASK_MEMO=?,PROJECT_NO=?"); 
		setQuerySQL(" select * from  TASK_LIST");  
		setListSQL(" select * from  TASK_LIST");  
		setDeleteSQL(" delete from  TASK_LIST");  
		//�˴����order by ��� 
		//setOrderBy(" order by REG_DT"); 
 
	} 
 
	/** 
	 * ��ִ���������ӵ�PreparedStatement�еĲ������и�ֵ 
	 */ 
	public void prepareInsertStatement(PreparedStatement ps, BaseVO bean) throws Exception { 
 
		TASK_LISTVO vo = (TASK_LISTVO) bean; 
		ps.setString(1, vo.getTask_no()); 
		ps.setString(2, vo.getTask_date()); 
		ps.setString(3, vo.getTask_step()); 
		ps.setString(4, vo.getTask_type()); 
		ps.setString(5, vo.getTask_goal()); 
		ps.setDouble(6, vo.getTask_cost()); 
		ps.setString(7, vo.getTask_user()); 
		ps.setString(8, vo.getUpdate_date()); 
		ps.setString(9, vo.getTask_memo()); 
		ps.setString(10,vo.getProject_no());
		ps.setInt(11,vo.getId());
 
	} 
 
	/** 
	 * ��ִ�������޸ĵ�PreparedStatement�еĲ������и�ֵ 
	 */ 
	public void prepareUpdateStatement(PreparedStatement ps, BaseVO bean) throws Exception { 
 
		TASK_LISTVO vo = (TASK_LISTVO) bean; 
		ps.setString(1, vo.getTask_date()); 
		ps.setString(2, vo.getTask_step()); 
		ps.setString(3, vo.getTask_type()); 
		ps.setString(4, vo.getTask_goal()); 
		ps.setDouble(5, vo.getTask_cost()); 
		ps.setString(6, vo.getTask_user()); 
		ps.setString(7, vo.getUpdate_date()); 
		ps.setString(8, vo.getTask_memo()); 
 		ps.setString(9,vo.getProject_no());
	} 
 
	/** 
	 * ɾ�������б� 
	 */ 
	public void delete(BaseVO vo) throws Exception { 
 
		delete(getDeleteSQL()+" where TASK_NO='"+((TASK_LISTVO) vo).getTask_no()+"'"); 
 
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
 
		vo = retrieve(getQuerySQL()+" where TASK_NO='"+((TASK_LISTVO) vo).getTask_no()+"'"); 
 
	} 
 
	/** 
	 * ��ȡ�б����ݵ�һ����¼ 
	 */ 
	public BaseVO retrieveResult(ResultSet rs) throws Exception { 
 
		TASK_LISTVO vo = new TASK_LISTVO(); 
		vo.setTask_no(rs.getString("TASK_NO").trim()); 
		vo.setTask_date(rs.getString("TASK_DATE").trim()); 
		vo.setTask_step(rs.getString("TASK_STEP").trim()); 
		vo.setTask_type(rs.getString("TASK_TYPE").trim()); 
		vo.setTask_goal(rs.getString("TASK_GOAL").trim()); 
		vo.setTask_cost(rs.getDouble("TASK_COST")); 
		vo.setTask_user(rs.getString("TASK_USER").trim()); 
		vo.setUpdate_date(rs.getString("UPDATE_DATE").trim()); 
		vo.setTask_memo(rs.getString("TASK_MEMO").trim()); 
		vo.setProject_no(rs.getString("PROJECT_NO").trim());
		vo.setType(rs.getString("TYPE"));
		return vo; 
 
	} 
 
	/** 
	 * ��ȡ��ϸ��Ϣ 
	 */ 
	public BaseVO detail(ResultSet rs) throws Exception { 
 
		return retrieveResult(rs);
 
	} 
 
} 

