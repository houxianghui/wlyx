package com.eis.portal.userlogout;

import java.util.List;
import java.sql.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;

import com.eis.base.BaseBO;
import com.eis.base.BaseVO;
import com.eis.base.BaseDAO;
import com.eis.base.PageObject;
import com.eis.portal.UserContext;
import com.eis.factory.*;
import com.eis.util.*;
import com.eis.connectionPool.*;
import com.eis.portal.userlogin.*;
import com.eis.portal.oplog.*;

/** 
 * ˵����log��ҵ���߼��� 
 */
public class UserLogoutBO extends BaseBO {

	/** 
	 * ���캯�� 
	 */
	public UserLogoutBO() {
		super();
	}

	/** 
	 * �����û�ǩ�˲�����־��ɾ���û���¼��¼, ������� 
	 */
	public void exec(BaseVO logoutvo, BaseVO oplogvo) throws Exception {

		//��ͬһ�����У�ɾ���û���¼��¼�����ݣ��������û�������¼������
		Connection connts = DBPoolManager.getConnection();
		connts.setAutoCommit(false);

		try {

			/**���û���¼��¼��ɾ���û���¼��¼*/
			UserLogoutDAO userLogoutDAO = new UserLogoutDAO();

			userLogoutDAO.delete(
				userLogoutDAO.getDeleteSQL()
					+ " where USER_ID = '"
					+ ((UserLogoutVO) logoutvo).getUser_id().trim()
					+ "'",
				connts);

			connts.commit();

		} catch (Exception ex) {
			connts.rollback();
		} finally {
			if (connts != null)
				connts.close();
		}

	}

	/** 
	 * �����û���¼��¼��
	 */
	public void add(BaseVO vo, UserContext user) throws Exception {

		BaseDAO dao = (BaseDAO) BeanFactory.getBean("userlogout_dao");
		dao.add(vo);

	}

	/** 
	 * ��������log 
	 */
	public void addList(BaseVO[] list, UserContext user) throws Exception {

		BaseDAO dao = (BaseDAO) BeanFactory.getBean("userlogout_dao");
		dao.addList(list);

	}

	/** 
	 * �޸�log 
	 */
	public void update(BaseVO vo, UserContext user) throws Exception {

		BaseDAO dao = (BaseDAO) BeanFactory.getBean("userlogout_dao");
		dao.update(vo, " where");

	}

	/** 
	 * �����޸�log 
	 */
	public void updateList(BaseVO[] list, UserContext user) throws Exception {

	}

	/** 
	 * ɾ�� �û���¼��¼���е� log 
	 */
	public void delete(BaseVO vo, UserContext user) throws Exception {

		BaseDAO dao = (BaseDAO) BeanFactory.getBean("userlogout_dao");
		dao.delete(
			dao.getDeleteSQL()
				+ " where USER_ID ='"
				+ ((UserLogoutVO) vo).getUser_id().trim()
				+ "'");

	}

	/** 
	 * ����ɾ��log 
	 */
	public void deleteList(BaseVO[] list, UserContext user) throws Exception {

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

		BaseDAO dao = (BaseDAO) BeanFactory.getBean("userlogout_dao");
		StringBuffer sql =
			new StringBuffer("select * from ep_user_login where 1=1 ");
		//�ڴ˴����ģ��ƥ������ 

		//ִ�в�ѯ 
		page.setList(dao.queryPage(page, sql.toString()));
		return page.getList();

	}

	/** 
	 * ��ѯά�����ݣ����ݲ�ѯ�������ط���������һҳ��¼ 
	 */
	public List list(PageObject page, UserContext user) throws Exception {

		BaseDAO dao = (BaseDAO) BeanFactory.getBean("userlogout_dao");
		StringBuffer sql =
			new StringBuffer("select * from ep_user_login where 1=1 ");
		//�ڴ˴����ģ��ƥ������ 

		//ִ�в�ѯ 
		page.setList(dao.queryPage(page, sql.toString()));
		return page.getList();

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

		UserLogoutVO bean = (UserLogoutVO) vo;
		BaseDAO dao = (BaseDAO) BeanFactory.getBean("userlogin_dao");

		return dao.retrieve(dao.getQuerySQL() + " where");

	}

	/** 
	 * ����where������ѯ��ϸ��Ϣ�����ص�һ��¼ 
	 */
	public BaseVO retrieve(String whereClause, UserContext user)
		throws Exception {

		BaseDAO dao = (BaseDAO) BeanFactory.getBean("userlogin_dao");
		return dao.retrieve(dao.getQuerySQL() + whereClause);

	}

}
