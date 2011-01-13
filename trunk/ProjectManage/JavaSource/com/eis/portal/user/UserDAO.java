/*********************************************************
 * File: UserDAO.java
 * 
 * Version 1.0
 * 
 * Date     2005-9-27
 * 
 * Author   陈 蓉
 * 
 ********************************************************/

package com.eis.portal.user;

import java.util.List;
import java.sql.*;

import com.eis.base.BaseVO;
import com.eis.base.BaseDAO;
import com.eis.base.PageObject;
import com.eis.portal.UserContext;
import com.eis.util.*;
import com.eis.db.*;

/** 
 * 说明：用户列表的数据库访问类 
 */
public class UserDAO extends BaseDAO {

	/** 
	 * 构造函数 
	 */
	public UserDAO() {
		super();
	}

	/** 
	 * 构造函数 
	 */
	public UserDAO(String dsName) {
		super(dsName);
	}

	/** 
	 * 初始化数据库访问语句 
	 */
	public void initSQL() {

		setInsertSQL("  insert into ep_user(USER_ID,DEPART_ID,ROLE_ID,LOGIN_ID,USER_NAME,PASSWORD,PHONE,MOBILE,EMAIL,POSTCODE,ADDRESS,STAT,REG_DT,BEGIN_DT,INVALID_DT,MODIFY_DT,MEMO,ST_CHG_DT,ADMIN_ID) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		setUpdateSQL(" update ep_user set DEPART_ID=?,ROLE_ID=?,LOGIN_ID=?,USER_NAME=?,PASSWORD=?,PHONE=?,MOBILE=?,EMAIL=?,POSTCODE=?,ADDRESS=?,STAT=?,REG_DT=?,BEGIN_DT=?,INVALID_DT=?,MODIFY_DT=?,MEMO=?,ST_CHG_DT=?,ADMIN_ID=?");
		setQuerySQL("select * from ep_user");
		setListSQL(" select * from  ep_user");
		setDeleteSQL(" delete from  ep_user");
		//此处添加order by 语句 
		//setOrderBy(" order by STAT"); 

	}

	/** 
	 * 对执行数据增加的PreparedStatement中的参数进行赋值 
	 */
	public void prepareInsertStatement(PreparedStatement ps, BaseVO bean)
		throws Exception {

		UserVO vo = (UserVO) bean;
		ps.setString(1, vo.getUser_id());
		ps.setString(2, vo.getDepart_id());
		ps.setString(3, vo.getRole_id());
		ps.setString(4, vo.getLogin_id());
		ps.setString(5, vo.getUser_name());
		ps.setString(6, vo.getPassword());
		ps.setString(7, vo.getPhone());
		ps.setString(8, vo.getMobile());
		ps.setString(9, vo.getEmail());
		ps.setString(10, vo.getPostcode());
		ps.setString(11, vo.getAddress());
		ps.setString(12, vo.getStatus());
		ps.setString(13, vo.getReg_dt());
		ps.setString(14, vo.getBegin_dt());
		ps.setString(15, vo.getInvalid_dt());
		ps.setString(16, vo.getModify_dt());
		ps.setString(17, vo.getMemo());
		ps.setString(18, vo.getSt_chg_dt());
		ps.setString(19, vo.getAdmin_id());

	}

	/** 
	 * 对执行数据修改的PreparedStatement中的参数进行赋值 
	 */
	public void prepareUpdateStatement(PreparedStatement ps, BaseVO bean)
		throws Exception {

		UserVO vo = (UserVO) bean;
		ps.setString(1, vo.getDepart_id());
		ps.setString(2, vo.getRole_id());
		ps.setString(3, vo.getLogin_id());
		ps.setString(4, vo.getUser_name());
		ps.setString(5, vo.getPassword());
		ps.setString(6, vo.getPhone());
		ps.setString(7, vo.getMobile());
		ps.setString(8, vo.getEmail());
		ps.setString(9, vo.getPostcode());
		ps.setString(10, vo.getAddress());
		ps.setString(11, vo.getStatus());
		ps.setString(12, vo.getReg_dt());
		ps.setString(13, vo.getBegin_dt());
		ps.setString(14, vo.getInvalid_dt());
		ps.setString(15, vo.getModify_dt());
		ps.setString(16, vo.getMemo());
		ps.setString(17, vo.getSt_chg_dt());
		ps.setString(18, vo.getAdmin_id());

	}

	/** 
	 * 删除用户列表 
	 */
	public void delete(BaseVO vo) throws Exception {

		delete(
			getDeleteSQL()
				+ " where USER_ID='"
				+ ((UserVO) vo).getUser_id()
				+ "'");

	}


	/** 
	 * 查询列表，根据查询条件返回一页数据 
	 */
	public List queryList(PageObject page, UserContext user) throws Exception {

		return null;

	}

	/** 
	 * 维护功能的列表，根据查询条件，返回一页数据 
	 */
	public List list(PageObject page, UserContext user) throws Exception {

		return null;

	}



	/** 
	 * 获取列表数据的一条纪录 
	 */
	public BaseVO retrieveResult(ResultSet rs) throws Exception {

		UserVO vo = new UserVO();
		vo.setUser_id(rs.getString("USER_ID").trim());
		vo.setRole_id(rs.getString("ROLE_ID").trim());
		vo.setLogin_id(rs.getString("LOGIN_ID").trim());
		vo.setUser_name(rs.getString("USER_NAME").trim());
		vo.setPhone(rs.getString("PHONE").trim());
		vo.setMobile(rs.getString("MOBILE").trim());
		vo.setPostcode(rs.getString("POSTCODE").trim());
		vo.setAddress(rs.getString("ADDRESS").trim());
		vo.setBegin_dt(rs.getString("BEGIN_DT").trim());
		vo.setInvalid_dt(rs.getString("INVALID_DT").trim());
		vo.setMemo(rs.getString("MEMO").trim());
		//vo.setAmsd_name(rs.getString("AMSD_NAME"));
		vo.setStatus(rs.getString("STAT").trim());
		vo.setReg_dt(rs.getString("REG_DT").trim());
		return vo;

	}

	/** 
	 * 获取明细信息 
	 */
	public BaseVO detail(ResultSet rs) throws Exception {

		UserVO vo = new UserVO();
		vo.setUser_id(rs.getString("USER_ID").trim());
		vo.setDepart_id(rs.getString("DEPART_ID").trim());
		vo.setRole_id(rs.getString("ROLE_ID").trim());
		vo.setLogin_id(rs.getString("LOGIN_ID").trim());
		vo.setUser_name(rs.getString("USER_NAME").trim());
		vo.setPassword(rs.getString("PASSWORD").trim());
		vo.setPhone(rs.getString("PHONE").trim());
		vo.setMobile(rs.getString("MOBILE").trim());
		vo.setEmail(rs.getString("EMAIL").trim());
		vo.setPostcode(rs.getString("POSTCODE").trim());
		vo.setAddress(rs.getString("ADDRESS").trim());
		vo.setStatus(rs.getString("STAT").trim());
		vo.setReg_dt(rs.getString("REG_DT").trim());
		vo.setBegin_dt(rs.getString("BEGIN_DT").trim());
		vo.setInvalid_dt(rs.getString("INVALID_DT").trim());
		vo.setModify_dt(rs.getString("MODIFY_DT").trim());
		vo.setMemo(rs.getString("MEMO").trim());
		vo.setSt_chg_dt(rs.getString("ST_CHG_DT").trim());
		vo.setAdmin_id(rs.getString("ADMIN_ID").trim());
		//vo.setAmsd_name(rs.getString("AMSD_NAME").trim());
		return vo;

	}

	/**返回用户代码为最大值的记录
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public String retrieveMax(UserContext user) throws Exception {
		DBQueryUtil dbUtil = new DBQueryUtil();
		try {
			return dbUtil.sqlQuerySingle("select MAX(USER_ID) from ep_user ");
		} finally {
			dbUtil.close();
		}

	}

	/**返回用户登陆编号为login_id的记录
	 * **/
	public String retrieveLoginId(String login_id, UserContext user)
		throws Exception {
		DBQueryUtil dbUtil = new DBQueryUtil();
		try {
			return dbUtil.sqlQuerySingle(
				"select count(*) from ep_user where LOGIN_ID='"
					+ login_id
					+ "'");
		} finally {
			dbUtil.close();
		}

	}

}
