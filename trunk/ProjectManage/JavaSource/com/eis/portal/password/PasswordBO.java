/*********************************************************
 * File: LoginBO.java
 * 
 * Version 1.0
 * 
 * Date     2005-9-19
 * 
 * Author   lihaibao
 * 
 ********************************************************/

package com.eis.portal.password;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;


import com.eis.base.BaseException;
import com.eis.base.BaseBO;
import com.eis.base.BaseVO;
import com.eis.base.BaseDAO;
import com.eis.base.PageObject;
import com.eis.portal.UserContext;
import com.eis.factory.*;
import com.eis.util.*;

/**
 * 说明：登录功能业务逻辑类
 * 
 */
public class PasswordBO extends BaseBO {

	/** 
	 * 增加公共参数
	 */
	public void add(BaseVO vo, UserContext user) throws Exception {


	}

	/*
	 * 批量增加数据
	 */
	public void addList(BaseVO[] list, UserContext user) throws Exception {
		

	}
	/*
	 * 修改用户密码数据，返回更新行数
	 */
	public int updatePwd(BaseVO vo, UserContext user) throws Exception {		
		
		BaseDAO  dao = (BaseDAO)BeanFactory.getBean("password_dao");
		PasswordVO loginVO = (PasswordVO)vo;
		
		loginVO.setPassword(MD5Util.digest(loginVO.getPassword()));
		loginVO.setOldPassword(MD5Util.digest(loginVO.getOldPassword()));
		
		/**检查用户输入的原密码是否与数据库中一致 */
		if(dao.queryCount("select USER_ID from ep_user where USER_ID ='" + loginVO.getUser_id() +"' and PASSWORD = '"+ loginVO.getOldPassword() +"'")<=0){
			BaseException ex = new BaseException();
			ex.setErrorCode("E010042");
			throw ex;
		}
		
		/**调用dao对象的update(vo, whereClause)方法，更新登录的用户密码，返回更新行数 */
		int rows=
		dao.update(vo, " where USER_ID ='" + loginVO.getUser_id() +"' and PASSWORD = '"+ loginVO.getOldPassword() +"'");
		
		return rows;
		
	
	}


	/*
	 * 修改数据
	 */
	public void update(BaseVO vo, UserContext user) throws Exception {
		
		
		BaseDAO  dao = (BaseDAO)BeanFactory.getBean("password_dao");
		PasswordVO loginVO = (PasswordVO)vo;
		
		loginVO.setPassword(MD5Util.digest(loginVO.getPassword()));
		loginVO.setOldPassword(MD5Util.digest(loginVO.getOldPassword()));
		
				
		int rows=
		dao.update(vo, " where USER_ID ='" + loginVO.getUser_id() +"' and PASSWORD = '"+ loginVO.getOldPassword() +"'");
		
				
	
	}

	/*
	 * 批量修改数据
	 */
	public void updateList(BaseVO[] list, UserContext user) throws Exception {

	}

	/*
	 * 删除数据
	 */
	public void delete(BaseVO vo, UserContext user) throws Exception {

		
		BaseDAO  dao = (BaseDAO)BeanFactory.getBean("password_dao");
		dao.delete(dao.getDeleteSQL()+" where USER_ID ='" + ((PasswordVO) vo).getUser_id()+"'");

	}

	/*
	 * 批量删除数据
	 */
	public void deleteList(BaseVO[] list, UserContext user) throws Exception {
		

	}

	/*
	 * 查询列表，没有查询条件，返回所有纪录
	 */
	public List queryList(UserContext user) throws Exception {
		
		return null;
	}

	/*
	 * 查询列表，根据查询条件返回符合条件的一页纪录
	 */
	public List queryList(PageObject page, UserContext user) throws Exception {
		
		
		BaseDAO  dao = (BaseDAO)BeanFactory.getBean("password_dao");
		String sql = "select * from ep_user";
		return dao.queryList(sql);
	}

	/* 
	 * 查询维护数据，根据查询条件返回符合条件的一页纪录
	 */
	public List list(PageObject page, UserContext user) throws Exception {
		
		BaseDAO  dao = (BaseDAO)BeanFactory.getBean("password_dao");
		
		//模糊匹配条件
		StringBuffer sql =new StringBuffer("select * from ep_user where 1=1 ");
		String user_id = (String)page.getFilter("user_id");
		if(user_id != null) 
		sql.append(" and USER_ID like '%"+user_id+"%'");
		
		String param_name = (String)page.getFilter("param_name");
		if(param_name != null) 
				sql.append(" and PARAM_NAME like '%"+param_name+"%'");

		SysLog.debug(sql.toString());
		
		//执行查询结果
		page.setList(dao.queryPage(page,sql.toString()));
		
		return null;
	}

	/* 
	 * 查询维护数据，返回所有纪录
	 */
	public List list(UserContext user) throws Exception {
		
		return null;
	}

	/* 
	 * 查询明细信息，返回单一纪录
	 */
	public BaseVO retrieve(BaseVO vo, UserContext user) throws Exception {
		
		BaseDAO  dao = (BaseDAO)BeanFactory.getBean("password_dao");
		return dao.retrieve(dao.getQuerySQL()+" where USER_ID='" + ((PasswordVO) vo).getUser_id()+"'");
	}
	
	/* 
	 * 根据where条件查询明细信息，返回单一纪录
	 */
	public BaseVO retrieve(String whereClause, UserContext user) throws Exception {
		
		return null;
	}

}
