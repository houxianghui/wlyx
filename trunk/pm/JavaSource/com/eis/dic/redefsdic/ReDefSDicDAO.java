 
package com.eis.dic.redefsdic; 
 
import java.util.List; 
import java.sql.*; 
 
import com.eis.base.BaseVO; 
import com.eis.base.BaseDAO; 
import com.eis.base.PageObject; 
import com.eis.portal.UserContext; 
import com.eis.util.*; 
 
 
/** 
 * ˵�����Զ��嵥���ֵ�����ݿ������ 
 */ 
public class ReDefSDicDAO extends BaseDAO { 
 
	/** 
	 * ���캯�� 
	 */ 
	public ReDefSDicDAO() { 
		super(); 
	} 
 
	/** 
	 * ���캯�� 
	 */ 
	public ReDefSDicDAO(String dsName) { 
		super(dsName); 
	} 
 
	/** 
	 * ��ʼ�����ݿ������� 
	 */ 
	public void initSQL() { 
 
		setInsertSQL("  insert into ep_redef_sdic(TYPE_ID,CAPTION,STMT,MEMO,USER_ID,REG_DT) values (?,?,?,?,?,?)"); 
		setUpdateSQL(" update ep_redef_sdic set CAPTION=?,STMT=?,MEMO=?,USER_ID=?,REG_DT=?"); 
		setQuerySQL(" select * from  ep_redef_sdic");  
		setListSQL(" select * from  ep_redef_sdic");  
		setDeleteSQL(" delete from  ep_redef_sdic");  
		//�˴����order by ��� 
		//setOrderBy(" order by REG_DT"); 
 
	} 
 
	/** 
	 * ��ִ���������ӵ�PreparedStatement�еĲ������и�ֵ 
	 */ 
	public void prepareInsertStatement(PreparedStatement ps, BaseVO bean) throws Exception { 
 
		ReDefSDicVO vo = (ReDefSDicVO) bean; 
		ps.setString(1, vo.getType_id()); 
		ps.setString(2, vo.getCaption()); 
		ps.setString(3, vo.getStmt()); 
		ps.setString(4, vo.getMemo()); 
		ps.setString(5, vo.getUser_id()); 
		ps.setString(6, vo.getReg_dt()); 
 
	} 
 
	/** 
	 * ��ִ�������޸ĵ�PreparedStatement�еĲ������и�ֵ 
	 */ 
	public void prepareUpdateStatement(PreparedStatement ps, BaseVO bean) throws Exception { 
 
		ReDefSDicVO vo = (ReDefSDicVO) bean; 
		ps.setString(1, vo.getCaption()); 
		ps.setString(2, vo.getStmt()); 
		ps.setString(3, vo.getMemo()); 
		ps.setString(4, vo.getUser_id()); 
		ps.setString(5, vo.getReg_dt()); 
 
	} 
 
	/** 
	 * ɾ���Զ��嵥���ֵ� 
	 */ 
	public void delete(BaseVO vo) throws Exception { 
 
		delete(getDeleteSQL()+" where TYPE_ID='"+((ReDefSDicVO) vo).getType_id()+"'"); 
 
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
 
		ReDefSDicVO vo = new ReDefSDicVO(); 
		vo.setType_id(rs.getString("TYPE_ID").trim()); 
		vo.setCaption(rs.getString("CAPTION").trim()); 
		vo.setMemo(rs.getString("MEMO").trim()); 
		vo.setUser_id(rs.getString("USER_ID").trim()); 
		vo.setReg_dt(rs.getString("REG_DT").trim()); 
		return vo; 
 
	} 
 
	/** 
	 * ��ȡ��ϸ��Ϣ 
	 */ 
	public BaseVO detail(ResultSet rs) throws Exception { 
 
		ReDefSDicVO vo = new ReDefSDicVO(); 
		vo.setType_id(rs.getString("TYPE_ID").trim()); 
		vo.setCaption(rs.getString("CAPTION").trim()); 
		vo.setStmt(rs.getString("STMT").trim()); 
		vo.setMemo(rs.getString("MEMO").trim()); 
		vo.setUser_id(rs.getString("USER_ID").trim()); 
		vo.setReg_dt(rs.getString("REG_DT").trim()); 
		return vo; 
 
	} 
 
} 

