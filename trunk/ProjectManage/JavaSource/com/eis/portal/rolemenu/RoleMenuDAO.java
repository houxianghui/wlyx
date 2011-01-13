/*********************************************************
 * File: RoleMenuDAO.java
 * 
 * Version 1.0
 * 
 * Date     2005-9-21
 * 
 * Author   Chen Rong
 * 
 ********************************************************/

package com.eis.portal.rolemenu;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.sql.*;
import java.util.*;

import com.eis.base.BaseDAO;
import com.eis.base.BaseVO;
import com.eis.base.PageObject;
import com.eis.portal.UserContext;

import com.eis.util.*;
import com.eis.portal.rolemenu.*;

import com.eis.db.*;
import com.eis.base.*;
import com.eis.portal.roleop.*;
import com.eis.portal.op.*;
import com.eis.factory.*;

/**
 * ˵������ɫ�˵�Ȩ�޹���
 * 
 */
public class RoleMenuDAO extends BaseDAO {

	/**
	 * 
	 */
	public RoleMenuDAO() {
		super();

	}

	/**
	 * @param dsName
	 */
	public RoleMenuDAO(String dsName) {
		//super(dsName);

	}

	/*
	 * 
	 */
	public void initSQL() {
		setInsertSQL("insert into ep_role_menu(ROLE_ID,MENU_ID) values(?,?)");
		setUpdateSQL("update ep_role_menu set ROLE_ID=?,MENU_ID=? ");
		setQuerySQL(" select * from ep_role_menu ");
		setListSQL(" select * from ep_role_menu ");
		setDeleteSQL(" delete  from ep_role_menu  ");

		setOrderBy(" order by MENU_ID");
	}

	/*
	 * ��ִ���������ӵ�PreparedStatement�еĲ������и�ֵ
	 */
	public void prepareInsertStatement(PreparedStatement ps, BaseVO bean)
		throws Exception {
		RoleMenuVO vo = (RoleMenuVO) bean;

		ps.setString(1, vo.getRole_id());
		ps.setString(2, vo.getMenu_id());

	}

	/*	 
	 * ��ִ�������޸ĵ�PreparedStatement�еĲ������и�ֵ
	 */

	public void prepareUpdateStatement(PreparedStatement ps, BaseVO bean)
		throws Exception {
		RoleMenuVO vo = (RoleMenuVO) bean;

		ps.setString(1, vo.getRole_id());
		ps.setString(2, vo.getMenu_id());

	}

	/**���½�ɫ�˵�Ȩ��*/
	public void update(String role_id, List list, UserContext user, Connection connts)
		throws Exception {
		/**ɾ���Խ�ɫrole_idԭ��ѡ��Ĳ˵�Ȩ�ޣ�
		 * ���µĲ˵�Ȩ��ѡ���������뵽��ɫ�˵���ϵ��ep_role_menu��
		 * **/

		//ɾ����ɫrole_id������ԭ���˵�Ȩ��ѡ��			
		delete(role_id, user,connts);//������������
		SysLog.debug("after delete from ��ɫ�˵���ϵ��");

		/**�������õĽ�ɫ�˵���ϵ��ӵ����ݿ���**/

		if (list != null) {

			Connection conn = null;
			PreparedStatement ps = null;

			try {
				//conn = getConnection();
				//ps = conn.prepareStatement(getInsertSQL());
				ps = connts.prepareStatement(getInsertSQL());//����

				Iterator iter = list.iterator();
				while (iter.hasNext()) {
					String menu_id = (String) iter.next();

					ps.setString(1, role_id); //��ɫ����
					ps.setString(2, menu_id); //Ȩ����
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
				try {
					//�ͷ����ݿ�����
					if (conn != null) {
						conn.close();
					}
				} catch (Exception ex) {
					SysLog.error("BaseDAO �ͷ����ݿ�����ʧ��");
					SysLog.error(ex.getMessage());
					BaseException e = new BaseException();
					e.setErrorCode("E010019");
					throw e;
				}

			}
		}

	}

	/**ɾ����ɫ�˵�Ȩ��*/
	public void delete(String role_id, UserContext user,Connection connts) throws Exception {
		/**ɾ���Խ�ɫrole_idԭ��ѡ������в���Ȩ�ޣ�
		 * ɾ����ɫ�˵���ϵ��ep_role_menu�������й�role_id�ļ�¼
		 * **/
		delete("delete from ep_role_menu where ROLE_ID='" + role_id + "'",connts);

	}
	
	
	/** ���ȥ����ѡ�Ĳ˵������µĲ���Ȩ�����ѷ������ɫ�ģ�������ɾ����Щ����Ȩ�ޣ�
	 * �ҳ�ԭ����ѡ������ȡ����ѡ��Ĳ˵����� 
	 * ���ڼ����е�ÿ��menu_id, ɾ�������������ѷ�����˴˽�ɫ�Ĳ���Ȩ��
	 * */	
	public void deleteOPs(String role_id,List list,UserContext user,Connection conn) throws Exception{
		
		/**����ɫ��ǰѡ��Ĳ˵�menu_id����д��һ�ַ���
		 * ����ѡ��Ĳ˵���ż���Ϊ00000013��00000015
		 * �򴴽��ַ���str='00000013','00000015'������sql�����
		 */	
		String str=null;
		str="''";
		
		if(list!=null){			
			Iterator iter=list.iterator();
			while(iter.hasNext()){
				str=str+",'"+(String)iter.next()+"'";
				
			}			
		}
		
			
		//��ý�ɫȨ�����ݷ��ʶ���
		RoleOPDAO  roleopdao = (RoleOPDAO)BeanFactory.getBean("roleop_dao");
			
		String sql="delete from ep_role_auth where ROLE_ID='"+role_id+"' and OP_CODE in  "
				+" ( select OP_CODE from ep_op_def where MENU_ID not in  "
				+"("+str+"))";	
				
		roleopdao.delete(sql,conn);	
	}	
	

	/*
	 * 
	 */
	public void delete(BaseVO bean) throws Exception {

	}
	
	/*
	 *��ѯ��ɫrole_id����Ĳ˵�Ȩ���б�����ҳ 
	 */
	public List queryListByRoleId(String sql,String role_id) throws Exception {

		ArrayList list;

		Connection conn = null;
		
		DBQueryUtil dbUtil = new DBQueryUtil();

		try {
			conn = getConnection();
			Statement stmt =
				conn.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);

			
			String totalString =
				dbUtil.sqlQuerySingle("select count(*) from ep_role_menu where ROLE_ID='"+role_id+"'");
			int total = Integer.parseInt(totalString);

			
			//�����¼��Ϊ0������
			if (total <= 0) {
				return null;
			}
			list = new ArrayList(total);
			
						

			ResultSet rs = stmt.executeQuery(sql);
			
			rs.next();
			

			for (int i = 1; i <= total; i++) {

				list.add(retrieveResult(rs));
				rs.next();

			}

			if (rs != null)
				rs.close();

			if (stmt != null)
				stmt.close();

		} catch (SQLException ex) {

			while (ex != null) {
				SysLog.error("BaseDAO��ѯ���ʧ�ܣ�");
				SysLog.error("error code:" + ex.getErrorCode());
				SysLog.error("SQL State:" + ex.getSQLState());
				SysLog.error("�쳣��Ϣ:" + ex.getSQLState());
				ex = ex.getNextException();
			}

			BaseException e = new BaseException();
			e.setErrorCode("E010018");
			throw e;

		} catch (Throwable t) {
			SysLog.error("BaseDAO��ѯ���ʧ�ܣ�");
			SysLog.error(t.getMessage());

			BaseException e = new BaseException();
			e.setErrorCode("E010018");
			throw e;
		} finally {
			dbUtil.close();
			try {
				//�ͷ����ݿ�����
				if (conn != null) {
					conn.close();
				}
			} catch (Exception ex) {
				SysLog.error("BaseDAO �ͷ����ݿ�����ʧ�ܣ�");
				SysLog.error(ex.getMessage());
				BaseException e = new BaseException();
				e.setErrorCode("E010019");
				throw e;
			}
		}
		return list;
	}
	


	
	//�ҵ�list�͸��ײ˵�����ɵļ���
	List getSelectMenuSet(List list) throws Exception {

		HashMap map = new HashMap();
		List listResult = new ArrayList();
		
		Connection conn = getConnection();

		try {
			//����Բ˵���ѡ�񼯺ϲ�Ϊ�գ�Ѱ�Ҵ˲˵����ϼ��丸�˵���ɵļ���
			if (list != null) {
				Iterator iter = list.iterator();
				String menu_id = null; //�˵�����

				while (iter.hasNext()) {
					menu_id = (String) iter.next();					
					//���menu_id�ĸ��׽ڵ㼯�ϣ�����map��
					getParentMenuSet(menu_id, map, conn);
				}
			}
			
			Object[] parent_menu = map.values().toArray();
			for (int i = 0; i < parent_menu.length; i++) {
				listResult.add(parent_menu[i].toString());
				SysLog.debug("parent_id" + parent_menu[i].toString());
			}
			
			//��Ҷ�ڵ㼯�ϼ���
			listResult.addAll(list);
			
		} catch (Exception ex) {
			 SysLog.debug(ex.getMessage());
		} finally {
			try {
				//�ͷ����ݿ�����
				if (conn != null) {
					conn.close();
				}
			} catch (Exception ex) {
				SysLog.error("BaseDAO �ͷ����ݿ�����ʧ�ܣ�");
				SysLog.error(ex.getMessage());
				BaseException e = new BaseException();
				e.setErrorCode("E010019");
				throw e;
			}
		}


		

		return listResult;
	}

	/**�������ڷ��أ��˵�menu_id�ĸ��ײ˵�����
	 ***/
	public void getParentMenuSet(String mid, HashMap map, Connection conn)
		throws Exception {

			DBQueryUtil db = new DBQueryUtil();
		try {
			
			Statement stmt =
				conn.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);

			String sql = null;
			String menu_id = null; //�˵���ű���
			String parent_id = null;
			int count = 0;
			ResultSet rs = null;
			

			/**��ѯ�˵����Ϊmid�Ĳ˵��Ƿ����ϼ��˵� */
			sql =
				" select PARENT_ID from ep_menu "
					+ " where PARENT_ID!='00000000' and MENU_ID='"
					+ mid
					+ "' ";

		
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				parent_id = rs.getString("PARENT_ID");

				map.put(parent_id, parent_id); 
				getParentMenuSet(parent_id, map, conn);

			}

			
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();

		} catch (SQLException ex) {

			while (ex != null) {
				SysLog.error("BaseDAO��ѯ���ʧ�ܣ�");
				SysLog.error("error code:" + ex.getErrorCode());
				SysLog.error("SQL State:" + ex.getSQLState());
				SysLog.error("�쳣��Ϣ:" + ex.getSQLState());
				ex = ex.getNextException();
			}

			BaseException e = new BaseException();
			e.setErrorCode("E010018");
			throw e;

		} catch (Throwable t) {
			SysLog.error("BaseDAO��ѯ���ʧ�ܣ�");
			SysLog.error(t.getMessage());

			BaseException e = new BaseException();
			e.setErrorCode("E010018");
			throw e;
		} finally {
			db.close();
		}

	}

	/*
	 * 
	 */
	public List queryList(PageObject page, UserContext user) throws Exception {

		return null;
	}

	/*
	* 
	*/
	public List queryList(UserContext user) throws Exception {
		String sql = "select * from ep_role_menu ";
		return queryList(sql);
	}

	public List selectMenuList(String role_id, UserContext user)
		throws Exception {
		/**ѡ�񸸽ڵ�Ϊ"00000000"�Ĳ˵�����һ���˵�
		 * Ȼ���ÿһ�˵�����selectMenuPartList()������
		 * �����ش���˲˵����������в˵�������ʾ�Ķ���ļ��� 
		 * **/

		List list = new ArrayList();

		Connection conn = null;

		try {
			conn = getConnection();
			selectMenuPartList(role_id, "00000000", list, conn);

		} catch (SQLException ex) {

			while (ex != null) {
				SysLog.error("BaseDAO��ѯ���ʧ�ܣ�");
				SysLog.error("error code:" + ex.getErrorCode());
				SysLog.error("SQL State:" + ex.getSQLState());
				SysLog.error("�쳣��Ϣ:" + ex.getSQLState());
				ex = ex.getNextException();
			}

			BaseException e = new BaseException();
			e.setErrorCode("E010018");
			throw e;

		} catch (Throwable t) {
			SysLog.error("BaseDAO��ѯ���ʧ�ܣ�");
			SysLog.error(t.getMessage());

			BaseException e = new BaseException();
			e.setErrorCode("E010018");
			throw e;
		}finally{
			if(conn != null)
			conn.close();
		}

		return list;

	}

	/**�������ڷ��أ�
	 * �ܴ���˵�menu_id�����¼��˵��������ʾ��Ϣ�Ķ��󼯺ϣ�
	 * ���У����ڽ�ɫrole_idӵ�еĲ˵�Ȩ��������ע
	 * **/
	public List selectMenuPartList(
		String role_id,
		String menu_id,
		List list,
		Connection conn)
		throws Exception {

			DBQueryUtil db = new DBQueryUtil();
		try {
			
			Statement stmt =
				conn.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);

			String sql = null;

			String mid = null; //�˵���ű���
			String name = null; //�˵����Ʊ���			
			int level = 1; //�˵��������
			int check = 0; //�Ƿ�����ɫӵ��Ȩ�޵Ĳ˵���1Ϊ��ѡ��0Ϊ��ѡ

			/**��ȡ�˵����Ϊmenu_id�ļ�¼������ֶ�ֵ	
			 ***/

			sql = "select * from ep_menu where PARENT_ID='" + menu_id + "'";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				SysLog.debug("��ȡ�˵���ʼ");
				mid = rs.getString("MENU_ID"); //�˵����
				name = rs.getString("MENU_NAME"); //�˵�����
				level = rs.getInt("MENU_LEVEL"); //�˵�����				
				SysLog.debug("�˵����" + mid);

				/**��ѯ�˵����Ϊmenu_id�ļ�¼�Ƿ��ǽ�ɫrole_idӵ��Ȩ�޵Ĳ˵���
				 * ����ѯep_role_menu�����Ƿ��в˵����Ϊmenu_id����ɫΪrole_id�ļ�¼��
				 * ����,�ǽ�ɫӵ��Ȩ�޲˵�����check=1��ʾ,������ҳ�潫��ѡ��ѡ��
				 * ����check=0��������ҳ�治��ѡ��ѡ��
				 * **/

				
				int count =
					db.sqlQueryCount(
						"select * from ep_role_menu where MENU_ID='"
							+ mid
							+ "' and ROLE_ID='"
							+ role_id
							+ "'");

				if (count <= 0) {
					check = 0;
				} else {
					check = 1;
				}

				SelectMenuVO vo = new SelectMenuVO();
				vo.setMenu_id(mid);
				vo.setMenu_name(name);
				vo.setMenu_level(level);
				vo.setCheck(check);
				vo.setCheck_display(1); //��ʾ��ѡ������Ϊ1

				/**��ѯ�˵����Ϊmenu_id�ļ�¼�Ƿ�����¼��˵�
							 ***/

				count =
					db.sqlQueryCount(
						"select * from ep_menu where PARENT_ID='" + mid + "'");

				if (count >= 1) {

					/**������¼��˵���check_display=0,
					 * ������ҳ�潫����ʾ��ѡ��
					 * */

					vo.setCheck_display(0); //����ʾ��ѡ������check_displayΪ0
					list.add(vo);

					selectMenuPartList(role_id, mid, list, conn);

				} else {

					/**������¼��˵������˵�ΪҶ���˵���
					 * ����check_display=1,������ҳ�潫��ʾ��ѡ��,
					 * ����ʾ��ѡ���
					 **/

					vo.setCheck_display(1); //��ʾ��ѡ������Ϊ1
					list.add(vo);

				}

				

			}

			if (rs != null)
				rs.close();

			if (stmt != null)
				stmt.close();

		} catch (SQLException ex) {

			while (ex != null) {
				SysLog.error("BaseDAO��ѯ���ʧ�ܣ�");
				SysLog.error("error code:" + ex.getErrorCode());
				SysLog.error("SQL State:" + ex.getSQLState());
				SysLog.error("�쳣��Ϣ:" + ex.getSQLState());
				ex = ex.getNextException();
			}

			BaseException e = new BaseException();
			e.setErrorCode("E010018");
			throw e;

		} catch (Throwable t) {
			SysLog.error("BaseDAO��ѯ���ʧ�ܣ�");
			SysLog.error(t.getMessage());

			BaseException e = new BaseException();
			e.setErrorCode("E010018");
			throw e;
		} finally {
			db.close();
		}
		return list;
	}

	/*
	 * 
	 */
	public void retrieve(BaseVO bean) throws Exception {

	}

	/*
	 * ��ȡ�б����ݵ�һ����¼
	 */

	public BaseVO retrieveResult(ResultSet rs) throws Exception {
		RoleMenuVO vo = new RoleMenuVO();
		vo.setRole_id(rs.getString("ROLE_ID"));
		vo.setMenu_id(rs.getString("MENU_ID"));
		return vo;
	}

	/* 
	 * ��ȡ��ϸ��Ϣ
	 */
	public BaseVO detail(ResultSet rs) throws Exception {
		RoleMenuVO vo = new RoleMenuVO();
		vo.setRole_id(rs.getString("ROLE_ID"));
		vo.setMenu_id(rs.getString("MENU_ID"));
		return vo;
	}

	/* 
	 * 
	 */
	public List list(PageObject page, UserContext user) throws Exception {

		return null;
	}

}
