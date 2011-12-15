/*********************************************************
 * File: LoginDAO.java
 * 
 * Version 1.0
 * 
 * Date     2005-9-19
 * 
 * Author   lihaibao
 * 
 ********************************************************/


package com.eis.portal.login;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Vector;
import java.sql.*;
import java.io.*;

import com.eis.base.BaseDAO;
import com.eis.base.BaseVO;
import com.eis.base.PageObject;
import com.eis.portal.UserContext;

import com.eis.util.*;
/**
 * 说明：
 * 
 */
public class LoginDAO extends BaseDAO {

	/**
	 * 
	 */
	public LoginDAO() {
		super();
		
	}

	/**
	 * @param dsName
	 */
	public LoginDAO(String dsName) {
		super(dsName);

	}
	

	
	/*
	 * 
	 */
	public void initSQL() {
		setInsertSQL("  insert into ep_user(USER_ID,DEPART_ID,ROLE_ID,LOGIN_ID,USER_NAME,PASSWORD,PHONE,MOBILE,EMAIL,POSTCODE,ADDRESS,STAT,REG_DT,BEGIN_DT,INVALID_DT,MODIFY_DT,MEMO,ST_CHG_DT,ADMIN_ID,AMSD_STORE) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"); 
		setUpdateSQL("update ep_user set PASSWORD=? ");  
		setQuerySQL(" select * from  ep_user");  

	}

	/** 
	 * 对执行数据增加的PreparedStatement中的参数进行赋值 
	 */ 
	public void prepareInsertStatement(PreparedStatement ps, BaseVO bean) throws Exception { 
 
		LoginVO vo = (LoginVO) bean; 
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
		ps.setString(20, vo.getAmsd_store()); 
 
	} 
	
	/*	 
	 * 对执行数据修改的PreparedStatement中的参数进行赋值
	 * 
	 */
	 
	public void prepareUpdateStatement(PreparedStatement ps, BaseVO bean)
		throws Exception {
		LoginVO vo = (LoginVO) bean;
		ps.setString(1, vo.getPassword());		

	}
	
	/** 
	 * 删除用户登录 
	 */ 
	public void delete(BaseVO vo) throws Exception { 
 
		delete(getDeleteSQL()+" where USER_ID='"+((LoginVO) vo).getUser_id()+"'"); 
 
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
	 * 查询明细信息，返回单一纪录 
	 */ 


	/** 
	 * 获取列表数据的一条纪录 
	 */ 
	public BaseVO retrieveResult(ResultSet rs) throws Exception { 
 
		LoginVO vo = new LoginVO(); 
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
		return vo; 
 
	} 

	/** 
	 * 获取明细信息 
	 */ 
	public BaseVO detail(ResultSet rs) throws Exception { 
 
		LoginVO vo = new LoginVO(); 
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
		return vo; 
 
	} 

}
