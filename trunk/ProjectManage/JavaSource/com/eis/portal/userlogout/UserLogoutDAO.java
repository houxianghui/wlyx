 
package com.eis.portal.userlogout; 
 
import java.util.List; 
import java.sql.*; 
 
import com.eis.base.BaseVO; 
import com.eis.base.BaseDAO; 
import com.eis.base.PageObject; 
import com.eis.portal.UserContext; 
import com.eis.util.*; 
 
 
/** 
 * ˵����log�����ݿ������ 
 */ 
public class UserLogoutDAO extends BaseDAO { 
 
	/** 
	 * ���캯�� 
	 */ 
	public UserLogoutDAO() { 
		super(); 
	} 
 
	/** 
	 * ���캯�� 
	 */ 
	public UserLogoutDAO(String dsName) { 
		super(dsName); 
	} 
 
	/** 
	 * ��ʼ�����ݿ������� 
	 */ 
	public void initSQL() { 
 		
 		//�����û���¼��¼���û���¼��¼��
		setInsertSQL("  insert into ep_user_login(USER_ID,LOGIN_TIME) values (?,?)"); 
		setUpdateSQL(" update ep_user_login set USER_ID=?,LOGIN_TIME=?"); 
		setQuerySQL(" select * from  ep_user_login");  
		setListSQL(" select * from  ep_user_login");  
		//ɾ���û���¼��¼���еĵ�¼��¼
		setDeleteSQL(" delete  from  ep_user_login");  
		//�˴����order by ��� 
		//setOrderBy(" order by REG_DT"); 
 
	} 
 
	/** 
	 * ��ִ���������ӵ�PreparedStatement�еĲ������и�ֵ 
	 */ 
	public void prepareInsertStatement(PreparedStatement ps, BaseVO bean) throws Exception { 
 
		UserLogoutVO vo = (UserLogoutVO) bean; 
		ps.setString(1, vo.getUser_id()); 
		ps.setString(2, vo.getLogin_time()); 
 
	} 
 
	/** 
	 * ��ִ�������޸ĵ�PreparedStatement�еĲ������и�ֵ 
	 */ 
	public void prepareUpdateStatement(PreparedStatement ps, BaseVO bean) throws Exception { 
 
		UserLogoutVO vo = (UserLogoutVO) bean; 
		ps.setString(1, vo.getUser_id()); 
		ps.setString(2, vo.getLogin_time()); 
 
	} 
 
	/** 
	 * ɾ��log 
	 */ 
	public void delete(BaseVO bean) throws Exception { 
		UserLogoutVO vo = (UserLogoutVO) bean;
		
		delete(getDeleteSQL()+" where USER_ID='"+vo.getUser_id()+"'" ); 
 
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
 
		UserLogoutVO vo = new UserLogoutVO(); 
		vo.setUser_id(rs.getString("USER_ID")); 
		vo.setLogin_time(rs.getString("LOGIN_TIME")); 
		return vo; 
 
	} 
 
	/** 
	 * ��ȡ��ϸ��Ϣ 
	 */ 
	public BaseVO detail(ResultSet rs) throws Exception { 
 
		UserLogoutVO vo = new UserLogoutVO(); 
		vo.setUser_id(rs.getString("USER_ID")); 
		vo.setLogin_time(rs.getString("LOGIN_TIME")); 
		return vo; 
 
	} 
 
} 

