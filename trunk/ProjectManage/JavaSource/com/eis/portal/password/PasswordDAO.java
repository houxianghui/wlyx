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

package com.eis.portal.password;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Vector;
import java.sql.*;
import java.io.*;
import java.util.*;
import java.text.*;

import com.eis.base.BaseDAO;
import com.eis.base.BaseVO;
import com.eis.base.PageObject;
import com.eis.portal.UserContext;
import com.eis.config.*;

import com.eis.util.*;
/**
 * ˵����
 * 
 */
public class PasswordDAO extends BaseDAO {

	/**
	 * 
	 */
	public PasswordDAO() {
		super();
		
	}

	/**
	 * @param dsName
	 */
	public PasswordDAO(String dsName) {
		super(dsName);

	}
	
	/*
	 * 
	 */
	public void initSQL() {
		
		setUpdateSQL("update ep_user set PASSWORD=?,MODIFY_DT=?");
		setQuerySQL(" select * from ep_user ");
		
	}

	/*
	 * ��ִ���������ӵ�PreparedStatement�еĲ������и�ֵ
	 */
	
	public void prepareInsertStatement(PreparedStatement ps, BaseVO bean)
		throws Exception {
	}
	
	/*	 
	 * ��ִ�������޸ĵ�PreparedStatement�еĲ������и�ֵ
	 */
	 
	public void prepareUpdateStatement(PreparedStatement ps, BaseVO bean)
		throws Exception {
			PasswordVO vo = (PasswordVO) bean;
			
			//�������ʧЧ����
			int passwordPeriod = Integer.parseInt(SysConfig.getProperty("passwordPeriod"));
			
			Calendar now = Calendar.getInstance();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");	
			
			now.add(Calendar.DAY_OF_YEAR,passwordPeriod);
			
			
			String mod_dt = (String)formatter.format(now.getTime());
			
			
			ps.setString(1, vo.getPassword());
			ps.setString(2,mod_dt);
					

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
	 * ��ȡ�б����ݵ�һ����¼
	 */
	 
	public BaseVO retrieveResult(ResultSet rs) throws Exception {
		PasswordVO vo = new PasswordVO();
		vo.setUser_id(rs.getString("USER_ID"));
		vo.setPassword(rs.getString("PASSWORD"));
		return vo;
	}

	/* 
	 * ��ȡ��ϸ��Ϣ
	 */
	 
	public BaseVO detail(ResultSet rs) throws Exception {
		PasswordVO vo = new PasswordVO();
				return vo;
				
	}
	
	/* 
	 * 
	 */
	public List list(PageObject page, UserContext user) throws Exception {
		
		return null;
	}

}
