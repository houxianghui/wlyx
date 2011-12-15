 
package com.lx; 
 
import java.util.List; 
import java.sql.*; 
 
import com.eis.base.*; 
import com.eis.portal.UserContext; 
import com.eis.util.*; 
 
 
/** 
 * ˵������ϵ��Ϣ�����ݿ������ 
 */ 
public class Lx_infoDAO extends BaseDAO { 
 
	/** 
	 * ���캯�� 
	 */ 
	public Lx_infoDAO() { 
		super(); 
	} 
 
	/** 
	 * ���캯�� 
	 */ 
	public Lx_infoDAO(String dsName) { 
		super(dsName); 
	} 
 
	/** 
	 * ��ʼ�����ݿ������� 
	 */ 
	public void initSQL() { 
 
		setInsertSQL("  insert into lx_info(lx_id,depart,name,phone,mobile,email,stuff_id) values (?,?,?,?,?,?,?)"); 
		setUpdateSQL(" update lx_info set depart=?,name=?,phone=?,mobile=?,email=?,stuff_id=?"); 
		setQuerySQL(" select * from  lx_info");  
		setListSQL(" select * from  lx_info");  
		setDeleteSQL(" delete from  lx_info");  
		//�˴����order by ��� 
		//setOrderBy(" order by REG_DT"); 
 
	} 
 
	/** 
	 * ��ִ���������ӵ�PreparedStatement�еĲ������и�ֵ 
	 */ 
	public void prepareInsertStatement(PreparedStatement ps, BaseVO bean) throws Exception { 
 
		Lx_infoVO vo = (Lx_infoVO) bean; 
		ps.setString(1, vo.getLx_id()); 
		ps.setString(2, vo.getDepart()); 
		ps.setString(3, vo.getName()); 
		ps.setString(4, vo.getPhone()); 
		ps.setString(5, vo.getMobile()); 
		ps.setString(6, vo.getEmail()); 
		ps.setString(7,vo.getStuff_id());
 
	} 
 
	/** 
	 * ��ִ�������޸ĵ�PreparedStatement�еĲ������и�ֵ 
	 */ 
	public void prepareUpdateStatement(PreparedStatement ps, BaseVO bean) throws Exception { 
 
		Lx_infoVO vo = (Lx_infoVO) bean; 
		ps.setString(1, vo.getDepart()); 
		ps.setString(2, vo.getName()); 
		ps.setString(3, vo.getPhone()); 
		ps.setString(4, vo.getMobile()); 
		ps.setString(5, vo.getEmail()); 
		ps.setString(6,vo.getStuff_id());
 
	} 
 
	/** 
	 * ɾ����ϵ��Ϣ 
	 */ 
	public void delete(BaseVO vo) throws Exception { 
 
		delete(getDeleteSQL()+" where LX_ID='"+((Lx_infoVO) vo).getLx_id()+"'"); 
 
	} 
 
	/** 
	 * �б��ѯȫ������ 
	 */ 

 
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
 
		vo = retrieve(getQuerySQL()+" where LX_ID='"+((Lx_infoVO) vo).getLx_id()+"'"); 
 
	} 
 
	/** 
	 * ��ȡ�б����ݵ�һ����¼ 
	 */ 
	public BaseVO retrieveResult(ResultSet rs) throws Exception { 
 
		Lx_infoVO vo = new Lx_infoVO(); 
		vo.setLx_id(rs.getString("LX_ID").trim()); 
		vo.setDepart(rs.getString("DEPART").trim()); 
		vo.setName(rs.getString("NAME").trim()); 
		vo.setPhone(rs.getString("PHONE").trim()); 
		vo.setMobile(rs.getString("MOBILE").trim()); 
		vo.setEmail(rs.getString("EMAIL").trim()); 
		vo.setStuff_id(rs.getString("STUFF_ID"));
		return vo; 
 
	} 
 
	/** 
	 * ��ȡ��ϸ��Ϣ 
	 */ 
	public BaseVO detail(ResultSet rs) throws Exception { 
 
		Lx_infoVO vo = new Lx_infoVO(); 
		vo.setLx_id(rs.getString("LX_ID").trim()); 
		vo.setDepart(rs.getString("DEPART").trim()); 
		vo.setName(rs.getString("NAME").trim()); 
		vo.setPhone(rs.getString("PHONE").trim()); 
		vo.setMobile(rs.getString("MOBILE").trim()); 
		vo.setEmail(rs.getString("EMAIL").trim()); 
		vo.setStuff_id(rs.getString("STUFF_ID")); 
		return vo; 
 
	} 
 
} 

