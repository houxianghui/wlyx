/*********************************************************
 * File: UserRoleDAO.java
 * 
 * Version 1.0
 * 
 * Date     2005-9-28
 * 
 * Author   �� ��
 * 
 ********************************************************/ 
 
package com.eis.portal.user; 
 
import java.util.*; 
import java.sql.*; 
 
import com.eis.base.BaseVO; 
import com.eis.base.BaseDAO; 
import com.eis.base.PageObject; 
import com.eis.portal.UserContext; 
import com.eis.util.*; 
import com.eis.base.*;
 
 
/** 
 * ˵�����û���ɫ��ϵ�����ݿ������ 
 */ 
public class UserRoleDAO extends BaseDAO { 
 
	/** 
	 * ���캯�� 
	 */ 
	public UserRoleDAO() { 
		super(); 
	} 
 
	/** 
	 * ���캯�� 
	 */ 
	public UserRoleDAO(String dsName) { 
		super(dsName); 
	} 
 
	/** 
	 * ��ʼ�����ݿ������� 
	 */ 
	public void initSQL() { 
 
		setInsertSQL("  insert into ep_user_role(USER_ID,ROLE_ID) values (?,?)"); 
		setUpdateSQL(" update ep_user_role set USER_ID=?,ROLE_ID=?"); 
		setQuerySQL(" select * from  ep_user_role ");  
		setQuerySQL(" select * from  ep_user_role ");  
		setDeleteSQL(" delete from  ep_user_role ");  
		//�˴����order by ��� 
		//setOrderBy(" order by REG_DT"); 
 
	} 
 
	/** 
	 * ��ִ���������ӵ�PreparedStatement�еĲ������и�ֵ 
	 */ 
	public void prepareInsertStatement(PreparedStatement ps, BaseVO bean) throws Exception { 
 
		UserVO vo = (UserVO) bean;
		ps.setString(1, vo.getUser_id());  
		ps.setString(2, vo.getRole_id()); 
				 
 
	} 
 
	/** 
	 * ��ִ�������޸ĵ�PreparedStatement�еĲ������и�ֵ 
	 */ 
	public void prepareUpdateStatement(PreparedStatement ps, BaseVO bean) throws Exception { 
 
		UserVO vo = (UserVO) bean; 
		ps.setString(1, vo.getUser_id()); 
		ps.setString(2, vo.getRole_id()); 		
 
	} 
 
	/** 
	 * ɾ���û���ɫ�б� 
	 */ 
	public void delete(BaseVO vo) throws Exception { 
 
		delete(getDeleteSQL()+" where USER_ID='"+((UserRoleVO) vo).getUser_id()+"'"); 
 
	} 
 


	/**�����û���ɫ��ϵ�б�*/
	public void addList(String user_id, List list, UserContext user,Connection conn) throws Exception {
		/** �����û��Ľ�ɫ�����������뵽�û���ɫ��ϵ��ep_user_role��
		 ***/			

		if (list != null) {
			
			PreparedStatement ps = null;

			try {				
				ps = conn.prepareStatement(getInsertSQL());

				Iterator iter = list.iterator();
				while (iter.hasNext()) {
					String role_id = (String) iter.next();
					
					ps.setString(1, user_id);
					ps.setString(2, role_id);
					ps.executeUpdate();

				}
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException ex) {

				while (ex != null) {
					SysLog.error("������������ʧ�ܣ�");
					SysLog.error("error code:" + ex.getErrorCode());
					SysLog.error("SQL State:" + ex.getSQLState());
					SysLog.error("�쳣��Ϣ:" + ex.getSQLState());
					ex = ex.getNextException();
				}

				BaseException e = new BaseException();
				e.setErrorCode("E010012");
				throw e;
			} catch (Throwable t) {

				SysLog.error("������������ʧ�ܣ�");
				SysLog.error(t.getMessage());
				BaseException e = new BaseException();
				e.setErrorCode("E010012");
				throw e;

			} finally {				

			}
		}

	}


	
	/**�����û���ɫ��ϵ*/
	public void updateList(String user_id, List list, UserContext user,Connection conn) throws Exception {
		/**ɾ�����û�user_idԭ���Ľ�ɫѡ��
		 * ���µĽ�ɫ�����������뵽�û���ɫ��ϵ��ep_user_role��
		 * **/

		//ɾ���û�user_id�����н�ɫѡ��			
		delete(user_id, user);  //cannot delete last ep_user_role because ep_user exists?
		SysLog.debug("after delete from �û���ɫ��ϵ��");

		/**���û����ڵĽ�ɫѡ�񼯺���ӵ����ݿ���**/		

		if (list != null) {
			
			PreparedStatement ps = null;

			try {				
				ps = conn.prepareStatement(getInsertSQL());

				Iterator iter = list.iterator();
				while (iter.hasNext()) {
					String role_id = (String) iter.next();
					
					ps.setString(1, user_id);
					ps.setString(2, role_id);
					ps.executeUpdate();

				}
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException ex) {

				while (ex != null) {
					SysLog.error("������������ʧ�ܣ�");
					SysLog.error("error code:" + ex.getErrorCode());
					SysLog.error("SQL State:" + ex.getSQLState());
					SysLog.error("�쳣��Ϣ:" + ex.getSQLState());
					ex = ex.getNextException();
				}

				BaseException e = new BaseException();
				e.setErrorCode("E010012");
				throw e;
			} catch (Throwable t) {

				SysLog.error("������������ʧ�ܣ�");
				SysLog.error(t.getMessage());
				BaseException e = new BaseException();
				e.setErrorCode("E010012");
				throw e;

			} finally {				
			}
		}

	}
	
	
	/**ɾ���û���ɫ����*/
	public void delete(String user_id, UserContext user) throws Exception {
		/** ɾ���û���ɫ��ϵ��ep_user_role�������й��û�user_id�ļ�¼
		 * **/
		delete("delete from ep_user_role where USER_ID='" + user_id + "'");
		//Ҫ����row��	

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
 
		UserRoleVO vo = new UserRoleVO();
		vo.setRole_id(rs.getString("ROLE_ID").trim()); 		 
		vo.setRole_name(rs.getString("ROLE_NAME").trim()); 		
		return vo; 
 
	} 
 
	/** 
	 * ��ȡ��ϸ��Ϣ 
	 */ 
	public BaseVO detail(ResultSet rs) throws Exception { 
 
		UserVO vo = new UserVO(); 
		vo.setRole_id(rs.getString("ROLE_ID").trim());
		vo.setUser_id(rs.getString("USER_ID").trim());		
		return vo; 
 
	} 
 
} 

