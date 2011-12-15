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
 * 说明：log的业务逻辑类 
 */
public class UserLogoutBO extends BaseBO {

	/** 
	 * 构造函数 
	 */
	public UserLogoutBO() {
		super();
	}

	/** 
	 * 增加用户签退操作日志，删除用户登录记录, 事务操作 
	 */
	public void exec(BaseVO logoutvo, BaseVO oplogvo) throws Exception {

		//在同一事务中，删除用户登录记录表数据，和增加用户操作记录表数据
		Connection connts = DBPoolManager.getConnection();
		connts.setAutoCommit(false);

		try {

			/**在用户登录记录表删除用户登录记录*/
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
	 * 增加用户登录记录表
	 */
	public void add(BaseVO vo, UserContext user) throws Exception {

		BaseDAO dao = (BaseDAO) BeanFactory.getBean("userlogout_dao");
		dao.add(vo);

	}

	/** 
	 * 批量增加log 
	 */
	public void addList(BaseVO[] list, UserContext user) throws Exception {

		BaseDAO dao = (BaseDAO) BeanFactory.getBean("userlogout_dao");
		dao.addList(list);

	}

	/** 
	 * 修改log 
	 */
	public void update(BaseVO vo, UserContext user) throws Exception {

		BaseDAO dao = (BaseDAO) BeanFactory.getBean("userlogout_dao");
		dao.update(vo, " where");

	}

	/** 
	 * 批量修改log 
	 */
	public void updateList(BaseVO[] list, UserContext user) throws Exception {

	}

	/** 
	 * 删除 用户登录记录表中的 log 
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
	 * 批量删除log 
	 */
	public void deleteList(BaseVO[] list, UserContext user) throws Exception {

	}

	/** 
	 * 查询列表，没有查询条件，返回所有纪录 
	 */
	public List queryList(UserContext user) throws Exception {

		return null;

	}

	/** 
	 * 查询列表，根据查询条件返回符合条件的一页纪录 
	 */
	public List queryList(PageObject page, UserContext user) throws Exception {

		BaseDAO dao = (BaseDAO) BeanFactory.getBean("userlogout_dao");
		StringBuffer sql =
			new StringBuffer("select * from ep_user_login where 1=1 ");
		//在此处添加模糊匹配条件 

		//执行查询 
		page.setList(dao.queryPage(page, sql.toString()));
		return page.getList();

	}

	/** 
	 * 查询维护数据，根据查询条件返回符合条件的一页纪录 
	 */
	public List list(PageObject page, UserContext user) throws Exception {

		BaseDAO dao = (BaseDAO) BeanFactory.getBean("userlogout_dao");
		StringBuffer sql =
			new StringBuffer("select * from ep_user_login where 1=1 ");
		//在此处添加模糊匹配条件 

		//执行查询 
		page.setList(dao.queryPage(page, sql.toString()));
		return page.getList();

	}

	/** 
	 * 查询维护数据，返回所有纪录 
	 */
	public List list(UserContext user) throws Exception {

		return null;

	}

	/** 
	 * 查询明细信息，返回单一纪录 
	 */
	public BaseVO retrieve(BaseVO vo, UserContext user) throws Exception {

		UserLogoutVO bean = (UserLogoutVO) vo;
		BaseDAO dao = (BaseDAO) BeanFactory.getBean("userlogin_dao");

		return dao.retrieve(dao.getQuerySQL() + " where");

	}

	/** 
	 * 根据where条件查询明细信息，返回单一纪录 
	 */
	public BaseVO retrieve(String whereClause, UserContext user)
		throws Exception {

		BaseDAO dao = (BaseDAO) BeanFactory.getBean("userlogin_dao");
		return dao.retrieve(dao.getQuerySQL() + whereClause);

	}

}
