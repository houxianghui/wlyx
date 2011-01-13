/*********************************************************
 * File: RoleDAO.java
 * 
 * Version 1.0
 * 
 * Date     2005-9-12
 * 
 * Author   Chen Rong
 * 
 ********************************************************/

package com.eis.portal.role;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.sql.*;

import com.eis.base.BaseDAO;
import com.eis.base.BaseVO;
import com.eis.base.PageObject;
import com.eis.portal.UserContext;
import com.eis.base.*;
import com.eis.cache.*;
import com.eis.factory.*;

import com.eis.portal.user.*;

import com.eis.util.*;
import com.eis.db.*;
/**
 * 说明：角色管理
 * 
 */
public class RoleDAO extends BaseDAO {

	/**
	 * 
	 */
	public RoleDAO() {
		super();

	}

	/**
	 * @param dsName
	 */
	public RoleDAO(String dsName) {
		super(dsName);

	}

	/*
	 * 
	 */
	public void initSQL() {
		setInsertSQL("insert into ep_role(ROLE_ID,ROLE_NAME,LOGIC_ID,TEMPL_ID,SESN_TIME,STAT,USER_ID,REG_DT) values(?,?,?,?,?,?,?,?)");
		setUpdateSQL("update ep_role set ROLE_NAME=?,LOGIC_ID=?,TEMPL_ID=?,SESN_TIME=?,STAT=?,USER_ID=?,REG_DT=? ");
		setQuerySQL(" select * from ep_role ");
		setListSQL(" select * from ep_role ");
		setDeleteSQL(" delete  from ep_role  ");

		setOrderBy(" order by REG_DT");
	}

	/*
	 * 对执行数据增加的PreparedStatement中的参数进行赋值
	 */
	public void prepareInsertStatement(PreparedStatement ps, BaseVO bean)
		throws Exception {
		RoleVO vo = (RoleVO) bean;
		int i = 1;
		ps.setString(i++, vo.getRole_id());
		ps.setString(i++, vo.getRole_name());
		ps.setString(i++, vo.getLogic_id());
		ps.setInt(i++, vo.getTempl_id());
		ps.setInt(i++, vo.getSesn_time());
		ps.setString(i++, vo.getStatus());
		ps.setString(i++, vo.getUser_id());
		ps.setString(i++, vo.getReg_dt());

	}

	/*	 
	 * 对执行数据修改的PreparedStatement中的参数进行赋值
	 */

	public void prepareUpdateStatement(PreparedStatement ps, BaseVO bean)
		throws Exception {
		RoleVO vo = (RoleVO) bean;
		int i = 1;
		//ps.setString(1, vo.getRole_id());
		ps.setString(i++, vo.getRole_name());
		ps.setString(i++, vo.getLogic_id());
		ps.setInt(i++, vo.getTempl_id());
		ps.setInt(i++, vo.getSesn_time());
		ps.setString(i++, vo.getStatus());
		ps.setString(i++, vo.getUser_id());
		ps.setString(i++, vo.getReg_dt());

	}

	/*
	 * 
	 */
	public void delete(BaseVO bean) throws Exception {

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
	public void retrieve(BaseVO bean) throws Exception {

	}

	/*
	 * 获取列表数据的一条纪录
	 */

	public BaseVO retrieveResult(ResultSet rs) throws Exception {
		RoleVO vo = new RoleVO();
		vo.setRole_id(rs.getString("ROLE_ID"));
		vo.setRole_name(rs.getString("ROLE_NAME").trim());
		vo.setTempl_id(rs.getInt("TEMPL_ID"));
		vo.setSesn_time(rs.getInt("SESN_TIME"));
		vo.setStatus(rs.getString("STAT"));
		vo.setUser_id(rs.getString("USER_ID"));
		vo.setReg_dt(rs.getString("REG_DT"));
	
		if (vo.getStatus().trim().equals("1"))
			vo.setStatus("正常");
		else
			vo.setStatus("作废");
			
		vo.setUser_id(ReDefSDicMap.getDicItemVal("0003",vo.getUser_id()));
		

		return vo;
	}

	/* 
	 * 获取明细信息
	 */
	public BaseVO detail(ResultSet rs) throws Exception {
		RoleVO vo = new RoleVO();
		vo.setRole_id(rs.getString("ROLE_ID"));
		vo.setRole_name(rs.getString("ROLE_NAME").trim());
		vo.setLogic_id(rs.getString("LOGIC_ID").trim());
		vo.setTempl_id(rs.getInt("TEMPL_ID"));
		vo.setSesn_time(rs.getInt("SESN_TIME"));
		vo.setStatus(rs.getString("STAT").trim());
		vo.setUser_id(rs.getString("USER_ID"));
		vo.setReg_dt(rs.getString("REG_DT"));

		return vo;
	}

	/* 
	 * 
	 */
	public List list(PageObject page, UserContext user) throws Exception {

		return null;
	}
	

}
