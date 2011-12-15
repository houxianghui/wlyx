 
package com.work; 
 
import java.util.List; 
import java.sql.*; 
 
import com.eis.base.*; 
import com.eis.portal.UserContext; 
 
 
/** 
 * ˵���������б�����ݿ������ 
 */ 
public class PROJECT_LISTDAO extends BaseDAO { 
 
	/** 
	 * ���캯�� 
	 */ 
	public PROJECT_LISTDAO() { 
		super(); 
	} 
 
	/** 
	 * ���캯�� 
	 */ 
	public PROJECT_LISTDAO(String dsName) { 
		super(dsName); 
	} 
 
	/** 
	 * ��ʼ�����ݿ������� 
	 */ 
	public void initSQL() { 
 
		setInsertSQL("  insert into PROJECT_LIST(USER_ID,PROJECT_NO,PROJECT_NAME,CURR_STEP,START_DATE,END_DATE) values (?,?,?,?,?,?)"); 
		setUpdateSQL(" update PROJECT_LIST set PROJECT_NAME=?,CURR_STEP=?,START_DATE=?,END_DATE=?"); 
		setQuerySQL(" select * from  PROJECT_LIST");  
		setListSQL(" select * from  PROJECT_LIST");  
		setDeleteSQL(" delete from  PROJECT_LIST");  
		//�˴����order by ��� 
		//setOrderBy(" order by REG_DT"); 
 
	} 
 
	/** 
	 * ��ִ���������ӵ�PreparedStatement�еĲ������и�ֵ 
	 */ 
	public void prepareInsertStatement(PreparedStatement ps, BaseVO bean) throws Exception { 
 
		PROJECT_LISTVO vo = (PROJECT_LISTVO) bean; 
		ps.setString(1, vo.getUser_id()); 
		ps.setString(2, vo.getProject_no()); 
		ps.setString(3, vo.getProject_name()); 
		ps.setString(4, vo.getCurr_step()); 
		ps.setString(5, vo.getStart_date()); 
		ps.setString(6, vo.getEnd_date()); 
 
	} 
 
	/** 
	 * ��ִ�������޸ĵ�PreparedStatement�еĲ������и�ֵ 
	 */ 
	public void prepareUpdateStatement(PreparedStatement ps, BaseVO bean) throws Exception { 
 
		PROJECT_LISTVO vo = (PROJECT_LISTVO) bean; 
		ps.setString(1, vo.getProject_name()); 
		ps.setString(2, vo.getCurr_step()); 
		ps.setString(3, vo.getStart_date()); 
		ps.setString(4, vo.getEnd_date()); 
 
	} 
 
	/** 
	 * ɾ�������б� 
	 */ 
	public void delete(BaseVO vo) throws Exception { 
 
		delete(getDeleteSQL()+" where USER_ID='"+((PROJECT_LISTVO) vo).getUser_id()+"'"+" and PROJECT_NO='"+((PROJECT_LISTVO) vo).getProject_no()+"'"); 
 
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
 
		vo = retrieve(getQuerySQL()+" where USER_ID='"+((PROJECT_LISTVO) vo).getUser_id()+"'"+" and PROJECT_NO='"+((PROJECT_LISTVO) vo).getProject_no()+"'"); 
 
	} 
 
	/** 
	 * ��ȡ�б����ݵ�һ����¼ 
	 */ 
	public BaseVO retrieveResult(ResultSet rs) throws Exception { 
 
		PROJECT_LISTVO vo = new PROJECT_LISTVO(); 
		vo.setUser_id(rs.getString("USER_ID").trim()); 
		vo.setProject_no(rs.getString("PROJECT_NO").trim()); 
		vo.setProject_name(rs.getString("PROJECT_NAME").trim()); 
		vo.setCurr_step(rs.getString("CURR_STEP").trim()); 
		vo.setStart_date(rs.getString("START_DATE").trim()); 
		vo.setEnd_date(rs.getString("END_DATE").trim()); 
		return vo; 
 
	} 
 
	/** 
	 * ��ȡ��ϸ��Ϣ 
	 */ 
	public BaseVO detail(ResultSet rs) throws Exception { 
 
		PROJECT_LISTVO vo = new PROJECT_LISTVO(); 
		vo.setUser_id(rs.getString("USER_ID").trim()); 
		vo.setProject_no(rs.getString("PROJECT_NO").trim()); 
		vo.setProject_name(rs.getString("PROJECT_NAME").trim()); 
		vo.setCurr_step(rs.getString("CURR_STEP").trim()); 
		vo.setStart_date(rs.getString("START_DATE").trim()); 
		vo.setEnd_date(rs.getString("END_DATE").trim()); 
		return vo; 
 
	} 
 
} 

