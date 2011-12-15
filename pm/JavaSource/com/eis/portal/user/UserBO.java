/*********************************************************
 * File: UserBO.java
 * 
 * Version 1.0
 * 
 * Date     2005-9-27
 * 
 * Author   �� ��
 * 
 ********************************************************/

package com.eis.portal.user;

import java.util.List;
import java.sql.*;
import java.util.*;

import com.eis.base.*;
import com.eis.base.BaseVO;
import com.eis.base.BaseDAO;
import com.eis.base.PageObject;
import com.eis.portal.UserContext;
import com.eis.factory.*;
import com.eis.cache.*;
import com.eis.util.*;
import com.eis.connectionPool.*;

/** 
 * ˵�����û��б��ҵ���߼��� 
 */
public class UserBO extends BaseBO {

	/** 
	 * ���캯�� 
	 */
	public UserBO() {
		super();
	}

	/**	 
	 * �����û�user_id��ȫ����ɫ��û��ѡ��Ľ�ɫ
	 * @param user_id - �û����
	 * @return ��ѯ���Ľ�ɫ�б�
	 */
	public List listRolesNotSelected(
		String user_id,
		UserContext user,
		String orgID)
		throws Exception {
		/**
		 * �ӽ�ɫ�����ep_role���û���ɫ��ϵ��ep_user_role��,
		 * ѡ���û�з�����û�user_id��ȫ����ɫ��
		 */
		UserRoleDAO dao = (UserRoleDAO) BeanFactory.getBean("userrole_dao");
		String sql = null;
		if (user_id == null) { //�����û�
			//ֱ��ѡ��ָ���������еĽ�ɫ�ͽ�ɫ�����������ͬ�Ľ�ɫ,��ɫ״̬Ϊ����
			sql =			
				" select ROLE_ID,ROLE_NAME from ep_role where  STAT='1' ";			

		} else { //�޸Ľ�ɫ
			//ֱ��ѡ��ָ���������еĽ�ɫ�ͽ�ɫ�����������ͬ�Ľ�ɫ���Ҳ����û����н�ɫ��Χ��
			sql =
				" select ROLE_ID,ROLE_NAME from ep_role where STAT='1' "
				+" and ROLE_ID not in "
				+"(select ROLE_ID from ep_user_role where USER_ID='"+user_id+"')";	

		}

		return dao.queryList(sql);
	}

	/**	 
	 * ���ض��û�user_id����Ľ�ɫ
	 * @param user_id - �û����
	 * @return ��ѯ���Ľ�ɫ�б�
	 */
	public List listRolesSelected(String user_id, UserContext user)
		throws Exception {
		/**���û���ɫ��ϵ��ep_user_role�У�USER_ID,ROLE_ID����
		 * ѡ���ѷ�����û�user_id�Ľ�ɫ����
		 ***/
		UserRoleDAO dao = (UserRoleDAO) BeanFactory.getBean("userrole_dao");
		String sql = " select a.ROLE_ID,b.ROLE_NAME  ";
		sql = sql + "  from ep_user_role a,ep_role b  ";
		sql = sql + "  where a.ROLE_ID=b.ROLE_ID  ";
		sql = sql + "  and  a.USER_ID = '" + user_id + "'";
		return dao.queryList(sql);

	}
	
	/**	 
	 * ����ϵͳ�д��ڵĴ���Ա��ɫ��Ϣ
	 * @return ��ѯ���Ľ�ɫ�б�
	 */
	public List getCollRoles(
		UserContext user)
		throws Exception {
		/**
		 * �ӽ�ɫ�����ep_role�л�����еġ�����Ա����ɫ��Ϣ
		 */
		UserRoleDAO dao = (UserRoleDAO) BeanFactory.getBean("userrole_dao");
		String sql = " select ROLE_ID,ROLE_NAME from ep_role where LOGIC_ID='coll'";
		
		List list = new ArrayList();
		list = dao.queryList(sql);
		List collList = new ArrayList();
		if(list != null){
			Iterator iter = list.iterator();
			while(iter.hasNext()){
				collList.add(((UserRoleVO)iter.next()).getRole_id().trim());
			}
		}
		return collList;
	}

	/**	 
	 * У���û���ѡ��ɫ�б����Ƿ��д���Ա��ɫ
	 * @param  ��ѡ��ɫ�б�
	 * @param  ����Ա��ɫ�б�
	 * @return ��ѯ���Ľ�ɫ�б�
	 */
	public String doCollCheck(
		List rolesSelected,
    	List collRoles)
		throws Exception {
		/**
		 * У���û���ѡ��ɫ�б����Ƿ��д���Ա��ɫ
		 */
		String collCheck = "1";
		if(rolesSelected != null){
			Iterator rs_iter = rolesSelected.iterator();
			String roleSel = null;
			while(rs_iter.hasNext()){
				roleSel = ((UserRoleVO)rs_iter.next()).getRole_id();
				if(collRoles != null){
					Iterator coll_iter = collRoles.iterator();
					String coll_id = null;
					while(coll_iter.hasNext()){
						coll_id = (String)coll_iter.next();
						if(roleSel.trim().equals(coll_id.trim())){
							collCheck = "0";
						}
					}
				}
			}
		}
		return collCheck;
	}


	/** 
	 * �����û��������û���ɫ��ϵ������ 
	 */
	public void add(String user_id, BaseVO vo, List list, UserContext user)
		throws Exception {

		//��ͬһ�����У������û��������û���ɫ��ϵ������
		Connection conn = DBPoolManager.getConnection();
		conn.setAutoCommit(false);

		try {

			/**���û���������Ӽ�¼*/
			add(vo, user, conn);

			/**���û���ɫ��ϵ�������б��¼*/
			addList(user_id, list, user, conn);

			conn.commit();

			//�Ǽ������û�������־		
			OpLog.Log(user, "03", "c", "�����û�", ((UserVO) vo).getUser_id());

		} catch (Exception ex) {
			conn.rollback();
		} finally {
			if (conn != null)
				conn.close();
		}

	}

	/** 
	 * ���������û���ɫ��ϵ���б����� 
	 */
	public void addList(
		String user_id,
		List list,
		UserContext user,
		Connection conn)
		throws Exception {
		UserRoleDAO dao = (UserRoleDAO) getBean("userrole_dao");
		dao.addList(user_id, list, user, conn);

	}

	/** 
	 * �����û��������û���ɫ��ϵ������ 
	 */
	public void update(String user_id, BaseVO vo, List list, UserContext user)
		throws Exception {

		//��ͬһ�����У������û��������û���ɫ��ϵ������
		Connection conn = DBPoolManager.getConnection();
		conn.setAutoCommit(false);

		try {

			/**�����û������*/
			update(vo, user, conn);

			/**�����û���ɫ��ϵ��*/
			updateList(user_id, list, user, conn);

			conn.commit();

			//�Ǽ��޸��û�������־		
			OpLog.Log(user, "03", "u", "�޸��û�", ((UserVO) vo).getUser_id());

		} catch (Exception ex) {
			conn.rollback();
		} finally {
			if (conn != null)
				conn.close();
		}

	}

	/** 
	 * �޸��û��б�
	 */
	public void update(BaseVO vo, UserContext user, Connection conn)
		throws Exception {

		BaseDAO dao = (BaseDAO) getBean("user_dao");
		dao.update(
			vo,
			" where USER_ID='" + ((UserVO) vo).getUser_id() + "'",
			conn);

	}

	public void update(BaseVO vo, UserContext user) throws Exception {
	}

	/** 
	 * �����޸��û���ɫ��ϵ�б� 
	 */
	public void updateList(
		String user_id,
		List list,
		UserContext user,
		Connection conn)
		throws Exception {
		UserRoleDAO dao = (UserRoleDAO) getBean("userrole_dao");
		dao.updateList(user_id, list, user, conn);

	}

	/** 
	 * �����û��б� 
	 */
	public void add(BaseVO vo, UserContext user, Connection conn)
		throws Exception {
		BaseDAO dao = (BaseDAO) getBean("user_dao");
		dao.add(vo, conn);

	}

	public void add(BaseVO vo, UserContext user) throws Exception {
	}

	/**
	 * ���÷ϳ�״̬
	 */
	public void terminate(BaseVO vo, UserContext user) throws Exception {

		//���û�user_id��״̬����Ϊ0���ϳ�
		UserDAO dao = (UserDAO) BeanFactory.getBean("user_dao");
		String sql =
			"update ep_user set STAT='0',ST_CHG_DT='"
				+ ((UserVO) vo).getSt_chg_dt()
				+ "',ADMIN_ID='"
				+ ((UserVO) vo).getAdmin_id()
				+ "'";
		sql = sql + " where USER_ID ='" + ((UserVO) vo).getUser_id() + "'";
		dao.executeUpdate(sql);

		//�ǼǷϳ��û�������־		
		OpLog.Log(user, "03", "u", "�ϳ��û�", ((UserVO) vo).getUser_id());

	}

	/**
	 * ��������״̬
	 */
	public void able(BaseVO vo, UserContext user) throws Exception {

		//���û�user_id��״̬����Ϊ1������
		UserDAO dao = (UserDAO) BeanFactory.getBean("user_dao");
		String sql =
			"update ep_user set STAT='1',ST_CHG_DT='"
				+ ((UserVO) vo).getSt_chg_dt()
				+ "',ADMIN_ID='"
				+ ((UserVO) vo).getAdmin_id()
				+ "'";
		sql = sql + " where USER_ID ='" + ((UserVO) vo).getUser_id() + "'";
		dao.executeUpdate(sql);

		//�Ǽ������û�������־		
		OpLog.Log(user, "03", "u", "�����û�", ((UserVO) vo).getUser_id());

	}

	/**
	 * ����ͣ��״̬
	 */
	public void disable(BaseVO vo, UserContext user) throws Exception {

		//���û�user_id��״̬����Ϊ2��ͣ��
		UserDAO dao = (UserDAO) BeanFactory.getBean("user_dao");
		String sql =
			"update ep_user set STAT='2',ST_CHG_DT='"
				+ ((UserVO) vo).getSt_chg_dt()
				+ "',ADMIN_ID='"
				+ ((UserVO) vo).getAdmin_id()
				+ "'";
		sql = sql + " where USER_ID ='" + ((UserVO) vo).getUser_id() + "'";
		dao.executeUpdate(sql);

		//�Ǽ�ͣ���û�������־		
		OpLog.Log(user, "03", "u", "ͣ���û�", ((UserVO) vo).getUser_id());

	}
	public void exclude(BaseVO vo, UserContext user) throws Exception {

		//���û�user_id��״̬����Ϊ2��ͣ��
		UserDAO dao = (UserDAO) BeanFactory.getBean("user_dao");
		String sql =
			"update ep_user set STAT='4',ST_CHG_DT='"
				+ ((UserVO) vo).getSt_chg_dt()
				+ "',ADMIN_ID='"
				+ ((UserVO) vo).getAdmin_id()
				+ "'";
		sql = sql + " where USER_ID ='" + ((UserVO) vo).getUser_id() + "'";
		dao.executeUpdate(sql);

	}


	/** 
	 * ���������û��б� 
	 */
	public void addList(BaseVO[] list, UserContext user) throws Exception {

		BaseDAO dao = (BaseDAO) getBean("user_dao");
		dao.addList(list);

	}

	/** 
	 * �����޸��û��б� 
	 */
	public void updateList(BaseVO[] list, UserContext user) throws Exception {

	}

	/** 
	 * ɾ���û��б� 
	 */
	public void delete(BaseVO vo, UserContext user) throws Exception {

		/**��ͬһ������,��ɾ���û���ɫ��ϵ����ɾ���û�
		 * **/
		Connection conn = DBPoolManager.getConnection();
		conn.setAutoCommit(false);

		try {

			/**ɾ���û���ɫ��ϵ */
			UserRoleDAO userroledao = (UserRoleDAO) getBean("userrole_dao");
			userroledao.delete(
				userroledao.getDeleteSQL()
					+ " where USER_ID='"
					+ ((UserVO) vo).getUser_id()
					+ "'",
				conn);

			/**ɾ���û� */
			BaseDAO dao = (BaseDAO) getBean("user_dao");
			dao.delete(
				dao.getDeleteSQL()
					+ " where USER_ID='"
					+ ((UserVO) vo).getUser_id()
					+ "'",
				conn);

			//�ύ����
			conn.commit();

			//�Ǽ�ɾ���û�������־		
			OpLog.Log(user, "03", "d", "ɾ���û�", ((UserVO) vo).getUser_id());

		} catch (Exception ex) {
			conn.rollback();
		} finally {
			if (conn != null)
				conn.close();
		}

	}

	/** 
	 * ����ɾ���û��б� 
	 */
	public void deleteList(BaseVO[] list, UserContext user) throws Exception {

	}

	/** 
	 * ��ѯ�������û��Ľ�ɫ�б� 
	 */
	public List queryRolesList(UserContext user) throws Exception {

		UserRoleDAO dao = (UserRoleDAO) getBean("userrole_dao");

		return dao.queryList(
			"select ROLE_ID,0 as ROLE_NAME from ep_user_role where USER_ID='"
				+ user.getUserID()
				+ "'");

	}

	/** 
	 * ��ѯ�б�û�в�ѯ�������������м�¼ 
	 */
	public List queryList(UserContext user) throws Exception {

		return null;

	}

	/** 
	 * ��ѯ�б����ݲ�ѯ�������ط���������һҳ��¼ 
	 */
	public List queryList(PageObject page, UserContext user) throws Exception {

		BaseDAO dao = (BaseDAO) getBean("user_dao");
		StringBuffer sql = new StringBuffer("select * from ep_user where 1=1 and USER_ID in(select USER_ID from ep_user_role where ROLE_ID in(select ROLE_ID from ep_role where LOGIC_ID='check'))" );
		//�ڴ˴����ģ��ƥ������ 
		
		
		String amsd_store_f = (String)page.getFilter("cre_branch_f");
		if (amsd_store_f != null && amsd_store_f.trim().length() > 0) {
			sql.append(" and AMSD_STORE = '" + amsd_store_f + "'");
		}
		
		//ִ�в�ѯ 
		page.setList(dao.queryPage(page, sql.toString()));
		return page.getList();

	}
	public List queryList1(PageObject page, UserContext user) throws Exception {

		 BaseDAO dao = (BaseDAO) getBean("user_dao");
		 StringBuffer sql = new StringBuffer("select * from ep_user where 1=1 and USER_ID in(select USER_ID from ep_user_role where ROLE_ID in(select ROLE_ID from ep_role where LOGIC_ID='check'))" );
		 //�ڴ˴����ģ��ƥ������ 
	
	
		 String amsd_store_f = (String)page.getFilter("cre_branch_f");
	     if (amsd_store_f != null && amsd_store_f.trim().length() > 0) {
		   sql.append(" and AMSD_STORE = '" + amsd_store_f + "'");
	     }
	
		 //ִ�в�ѯ 
		 page.setList(dao.queryPage(page, sql.toString()));
		 return page.getList();
	}
	/** 
	 * ��ѯά�����ݣ����ݲ�ѯ�������ط���������һҳ��¼ 
	 */
	public List list(PageObject page, UserContext user) throws Exception {

		BaseDAO dao = (BaseDAO) getBean("user_dao");
		StringBuffer sql =
			new StringBuffer("select * from ep_user  where 1=1 ");

		//ģ��ƥ������ 	

		//����û�����������ѯ��������	
		String org_id = (String) page.getFilter("amsd_store_f");

		if (org_id != null)
			sql.append(" and AMSD_STORE = '" + org_id + "'");

		//����û�������ѯ��������
		String user_name = (String) page.getFilter("user_name_f");

		if (user_name != null && user_name.trim().length() > 0)
			sql.append(" and USER_NAME like '%" + user_name + "%'");

		//����û�״̬��ѯ�������� 
		String status = (String) page.getFilter("status_f");

		if (status != null && status.trim().length() > 0)
			sql.append(" and STAT = '" + status + "'");

		SysLog.debug(sql.toString());

		//ִ�в�ѯ 
		page.setList(dao.queryPage(page, sql.toString()));
		return page.getList();

	}
	public List list2(PageObject page, UserContext user) throws Exception {

		BaseDAO dao = (BaseDAO) getBean("user_dao");

		StringBuffer sql =
		//��ѯ��ǰ��½��Ա��������������������
			new StringBuffer("select ep_user.* from ep_user where 1=1 " );
		//��ѯ���

		/**
		 * ���Ʋ�Ѱ��Χ
		 */
		
		sql.append(" and USER_ID in(select USER_ID from ep_user_role where ROLE_ID in(select ROLE_ID from ep_role where LOGIC_ID='check'))");
		
		
	

		//ģ��ƥ������ 	

		
		//ִ�в�ѯ 
		page.setList(dao.queryLargePage(page, sql.toString()));
		return page.getList();

	}
	public List queryAssignOperList(PageObject page, UserContext user,String amsdStore) throws Exception {

		BaseDAO dao = (BaseDAO) getBean("user_dao");
		StringBuffer sql =new StringBuffer("select * from ep_user where 1=1 and AMSD_STORE='"+amsdStore+ "' and USER_ID in(select USER_ID from ep_user_role where ROLE_ID in(select ROLE_ID from ep_role where LOGIC_ID in('check','phone')))");
		
		//����û�������ѯ��������
	    String user_name = (String) page.getFilter("user_name_f");
	    if (user_name != null && user_name.trim().length() > 0)
			sql.append(" and USER_NAME like '%" + user_name + "%'");
			
		page.setList(dao.queryLargePage(page, sql.toString()));
		return page.getList();

	}
	
	/**
	 * �ж�һ���û��Ƿ��Ǹ���Ա���ߵ绰����Ա,����logicID
	 * @param vo
	 * @param logicId
	 * @return
	 * @throws Exception
	 */
	public boolean judgeUserLogicID(UserContext vo,String logicId) throws Exception{
		BaseDAO dao = (BaseDAO) getBean("user_dao");
		String sql="select * from ep_user_role where USER_ID='"+vo.getUserID()+"' and ROLE_ID in(select ROLE_ID from ep_role where LOGIC_ID='"+logicId+"')";
		if(dao.queryCount(sql)>0)
			return true;
		else
			return false;
	}

	/** 
	 * ��ѯά�����ݣ��������м�¼ 
	 */
	public List list(UserContext user) throws Exception {

		return null;

	}

	/** 
	 * ��ѯ��ϸ��Ϣ�����ص�һ��¼ 
	 */
	public BaseVO retrieve(BaseVO vo, UserContext user) throws Exception {

		UserVO bean = (UserVO) vo;
		BaseDAO dao = (BaseDAO) getBean("user_dao");

		BaseVO v =
			dao.retrieve(
				dao.getQuerySQL()
					+ " where USER_ID='"
					+ ((UserVO) vo).getUser_id()
					+ "'");

		//�Ǽ��û�������־		
		OpLog.Log(user, "03", "r", "��ѯ�û�", ((UserVO) vo).getUser_id());

		return v;

	}
	public UserVO retrieve1(BaseVO vo, UserContext user) throws Exception {

			UserVO bean = (UserVO) vo;
			BaseDAO dao = (BaseDAO) getBean("user_dao");

			UserVO v =
				(UserVO)dao.retrieve(
					dao.getQuerySQL()
						+ " where USER_ID='"
						+ ((UserVO) vo).getUser_id()
						+ "'");
      
			//�Ǽ��û�������־		


			return v;

		}

	/** 
	 * ����where������ѯ��ϸ��Ϣ�����ص�һ��¼ 
	 */
	public BaseVO retrieve(String whereClause, UserContext user)
		throws Exception {

		BaseDAO dao = (BaseDAO) getBean("user_dao");
		return dao.retrieve(dao.getQuerySQL() + whereClause);

	}

	/**�����û�����Ϊ���ֵ�ļ�¼ 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public String retrieveMax(UserContext user) throws Exception {

		UserDAO dao = (UserDAO) BeanFactory.getBean("user_dao");
		return dao.retrieveMax(user);

	}
	/**�����û���½���Ϊlogin_id�ļ�¼
	 * */
	public String retrieveLoginId(String login_id, UserContext user)
		throws Exception {

		UserDAO dao = (UserDAO) BeanFactory.getBean("user_dao");
		return dao.retrieveLoginId(login_id, user);
	}

	public String checkLoginIdRepeat(BaseVO vo, UserContext user)
		throws Exception {
		//�����û�ʱ������¼��������ظ�
		BaseDAO dao = (BaseDAO) getBean("user_dao");

		String repeat = "n";

		SysLog.debug("�û�ԭ����¼����Ϊ��" + ((UserVO) vo).getLogin_id2());
		SysLog.debug("�û�������ĵ�¼����Ϊ��" + ((UserVO) vo).getLogin_id());

		String sql1 =
			dao.getQuerySQL()
				+ " where LOGIN_ID='"
				+ ((UserVO) vo).getLogin_id()
				+ "' and LOGIN_ID <> '"
				+ ((UserVO) vo).getLogin_id2()
				+ "'";

		SysLog.debug(sql1);

		if (dao.queryCount(sql1) > 0) {
			repeat = "y";
			BaseException ex = new BaseException();
			ex.setErrorCode("E010041");
			throw ex;
		}
		return repeat;
	}

	/** 
	 * ��ѯ�û��б� 
	 */
	public List listpop(PageObject page, UserContext user) throws Exception {

		BaseDAO dao = (BaseDAO) getBean("user_dao");
		StringBuffer sql =
			new StringBuffer("select * from ep_user where 1=1 ");

		//ģ��ƥ������	
		String org_id = (String) page.getFilter("amsd_store_f");
		String user_name = (String) page.getFilter("user_name_f");
		if (org_id != null && org_id.trim().length() > 0)
			sql.append(" and AMSD_STORE = '" + org_id + "'");
		if (user_name != null && user_name.trim().length() > 0)
			sql.append(" and USER_NAME = '" + user_name + "'");

		SysLog.debug(sql.toString());

		//ִ�в�ѯ 
		page.setList(dao.queryPage(page, sql.toString()));
		return page.getList();
	}
	
	/**
	 * ��������
	 */
	public void resetPwd(String user_id, UserContext user) throws Exception {

		//���û�user_id��״̬����Ϊ2��ͣ��
		UserDAO dao = (UserDAO) BeanFactory.getBean("user_dao");
		String sql ="update ep_user set PASSWORD='"+ MD5Util.digest("00000000") + "' where USER_ID ='" + user_id + "'";
		dao.executeUpdate(sql);
	}

	/**����ѡ��ɫ�У�ͬʱ����¼��Ա�͸���Ա���򱨴�����ʧ��
	 * ���ݽ�ɫ���role_id��ȡ�ÿ��ƴ���logic_id*/
	public String getLogicIdByRoleId(String role_id, UserContext user) throws Exception {

		//���û�user_id��״̬����Ϊ2��ͣ��
		UserDAO dao = (UserDAO) BeanFactory.getBean("user_dao");
		String sql ="select LOGIC_ID from ep_role where ROLE_ID = '"+role_id+"'";
		return dao.querySingle(sql);
	}
	/***/

}
