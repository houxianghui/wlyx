/*********************************************************
 * File: OPDAO.java
 * 
 * Version 1.0
 * 
 * Date     2005-9-20
 * 
 * Author   Chen Rong
 * 
 ********************************************************/

package com.eis.portal.op;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.sql.*;

import com.eis.base.BaseDAO;
import com.eis.base.BaseVO;
import com.eis.base.PageObject;
import com.eis.portal.UserContext;
import com.eis.base.*;


import com.eis.util.*;
/**
 * @说明：操作权限定义
 * 
 */
public class OPDAO extends BaseDAO {

	/**
	 * 
	 */
	public OPDAO() {
		super();
		
	}

	/**
	 * @param dsName
	 */
	public OPDAO(String dsName) {
		super(dsName);

	}
	
	/*
	 * 
	 */
	public void initSQL() {
		setInsertSQL("insert into ep_op_def(MENU_ID,OP_CODE,CAPTION) values(?,?,?)");
		setUpdateSQL("update ep_op_def set MENU_ID=?,CAPTION=? ");
		setQuerySQL("select a.*,b.MENU_NAME from ep_op_def a left join ep_menu b on a.MENU_ID=b.MENU_ID ");
		setListSQL(" select * from ep_op_def ");
		setDeleteSQL(" delete  from ep_op_def  ");
		
		setOrderBy(" order by OP_CODE");
	}

	/*
	 * 对执行数据增加的PreparedStatement中的参数进行赋值
	 */
	public void prepareInsertStatement(PreparedStatement ps, BaseVO bean)
		throws Exception {
		OPVO vo = (OPVO) bean;		
		

		ps.setString(1, vo.getMenu_id());
		ps.setString(2, vo.getOp_code());
		ps.setString(3, vo.getCaption());			

	}

	/*	 
	 * 对执行数据修改的PreparedStatement中的参数进行赋值
	 */
	 
	public void prepareUpdateStatement(PreparedStatement ps, BaseVO bean)
		throws Exception {
			OPVO vo = (OPVO) bean;

			ps.setString(1, vo.getMenu_id());
			ps.setString(2, vo.getCaption());			

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
		OPVO vo = new OPVO();
		vo.setMenu_id(rs.getString("MENU_ID"));
		vo.setMenu_name(rs.getString("MENU_NAME"));		
		vo.setOp_code(rs.getString("OP_CODE"));
		vo.setCaption(rs.getString("CAPTION"));		
		
		
		return vo;
	}

	/* 
	 * 获取明细信息
	 */
	public BaseVO detail(ResultSet rs) throws Exception {
				OPVO vo = new OPVO();
				vo.setMenu_id(rs.getString("MENU_ID"));
				vo.setMenu_name(rs.getString("MENU_NAME"));	
				vo.setOp_code(rs.getString("OP_CODE"));
				vo.setCaption(rs.getString("CAPTION"));				
				
				return vo;
	}

	/* 
	 * 
	 */
	public List list(PageObject page, UserContext user) throws Exception {
		
		return null;
	}
	
	
		
	

}
