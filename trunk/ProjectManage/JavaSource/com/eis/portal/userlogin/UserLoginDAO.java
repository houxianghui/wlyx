 
package com.eis.portal.userlogin; 
 
import java.util.List; 
import java.sql.*; 
 
import com.eis.base.BaseVO; 
import com.eis.base.BaseDAO; 
import com.eis.base.PageObject; 
import com.eis.portal.UserContext; 
import com.eis.util.*; 
 
 
/** 
 * ˵���������û���¼��¼������ݿ������ 
 */ 
public class UserLoginDAO extends BaseDAO { 
 
	/** 
	 * ���캯�� 
	 */ 
	public UserLoginDAO() { 
		super(); 
	} 
 
	/** 
	 * ���캯�� 
	 */ 
	public UserLoginDAO(String dsName) { 
		super(dsName); 
	} 
 
	/** 
	 * ��ʼ�����ݿ������� 
	 */ 
	public void initSQL() { 
 
		setInsertSQL("  insert into ep_user_login(USER_ID,LOGIN_TIME,CLIENT_IP) values (?,?,?)"); 
		 
		setQuerySQL(" select * from  ep_user_login");  
		setListSQL(" select * from  ep_user_login");  
		setDeleteSQL(" delete from  ep_user_login");  
		//�˴����order by ��� 
		//setOrderBy(" order by REG_DT"); 
 
	} 
 
	/** 
	 * ��ִ���������ӵ�PreparedStatement�еĲ������и�ֵ 
	 */ 
	public void prepareInsertStatement(PreparedStatement ps, BaseVO bean) throws Exception { 
 
		UserLoginVO vo = (UserLoginVO) bean; 
		ps.setString(1, vo.getUser_id()); 
		ps.setString(2, vo.getLogin_time()); 
		ps.setString(3, vo.getClient_ip());
 
	} 
 
	/** 
	 * ��ִ�������޸ĵ�PreparedStatement�еĲ������и�ֵ 
	 */ 
	public void prepareUpdateStatement(PreparedStatement ps, BaseVO bean) throws Exception { 
 
		UserLoginVO vo = (UserLoginVO) bean; 
		ps.setString(1, vo.getLogin_time()); 
 
	} 
 
	/** 
	 * ɾ�������û���¼��¼�� 
	 */ 
	public void delete(BaseVO vo) throws Exception { 
 
		delete(getDeleteSQL()+" where USER_ID='"+((UserLoginVO) vo).getUser_id()+"'"); 
 
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
 
		UserLoginVO vo = new UserLoginVO(); 
		vo.setUser_id(rs.getString("USER_ID").trim()); 
		vo.setLogin_time(rs.getString("LOGIN_TIME").trim());
		vo.setClient_ip(rs.getString("CLIENT_IP").trim());  
		return vo; 
 
	} 
 
	/** 
	 * ��ȡ��ϸ��Ϣ 
	 */ 
	public BaseVO detail(ResultSet rs) throws Exception { 
 
		UserLoginVO vo = new UserLoginVO(); 
		vo.setUser_id(rs.getString("USER_ID").trim()); 
		vo.setLogin_time(rs.getString("LOGIN_TIME").trim()); 
		vo.setClient_ip(rs.getString("CLIENT_IP").trim());
		return vo; 
 
	} 
 
} 

