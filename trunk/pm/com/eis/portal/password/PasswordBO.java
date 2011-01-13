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
 * ˵������¼����ҵ���߼���
 * 
 */
public class PasswordBO extends BaseBO {

	/** 
	 * ���ӹ�������
	 */
	public void add(BaseVO vo, UserContext user) throws Exception {


	}

	/*
	 * ������������
	 */
	public void addList(BaseVO[] list, UserContext user) throws Exception {
		

	}
	/*
	 * �޸��û��������ݣ����ظ�������
	 */
	public int updatePwd(BaseVO vo, UserContext user) throws Exception {		
		
		BaseDAO  dao = (BaseDAO)BeanFactory.getBean("password_dao");
		PasswordVO loginVO = (PasswordVO)vo;
		
		loginVO.setPassword(MD5Util.digest(loginVO.getPassword()));
		loginVO.setOldPassword(MD5Util.digest(loginVO.getOldPassword()));
		
		/**����û������ԭ�����Ƿ������ݿ���һ�� */
		if(dao.queryCount("select USER_ID from ep_user where USER_ID ='" + loginVO.getUser_id() +"' and PASSWORD = '"+ loginVO.getOldPassword() +"'")<=0){
			BaseException ex = new BaseException();
			ex.setErrorCode("E010042");
			throw ex;
		}
		
		/**����dao�����update(vo, whereClause)���������µ�¼���û����룬���ظ������� */
		int rows=
		dao.update(vo, " where USER_ID ='" + loginVO.getUser_id() +"' and PASSWORD = '"+ loginVO.getOldPassword() +"'");
		
		return rows;
		
	
	}


	/*
	 * �޸�����
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
	 * �����޸�����
	 */
	public void updateList(BaseVO[] list, UserContext user) throws Exception {

	}

	/*
	 * ɾ������
	 */
	public void delete(BaseVO vo, UserContext user) throws Exception {

		
		BaseDAO  dao = (BaseDAO)BeanFactory.getBean("password_dao");
		dao.delete(dao.getDeleteSQL()+" where USER_ID ='" + ((PasswordVO) vo).getUser_id()+"'");

	}

	/*
	 * ����ɾ������
	 */
	public void deleteList(BaseVO[] list, UserContext user) throws Exception {
		

	}

	/*
	 * ��ѯ�б�û�в�ѯ�������������м�¼
	 */
	public List queryList(UserContext user) throws Exception {
		
		return null;
	}

	/*
	 * ��ѯ�б����ݲ�ѯ�������ط���������һҳ��¼
	 */
	public List queryList(PageObject page, UserContext user) throws Exception {
		
		
		BaseDAO  dao = (BaseDAO)BeanFactory.getBean("password_dao");
		String sql = "select * from ep_user";
		return dao.queryList(sql);
	}

	/* 
	 * ��ѯά�����ݣ����ݲ�ѯ�������ط���������һҳ��¼
	 */
	public List list(PageObject page, UserContext user) throws Exception {
		
		BaseDAO  dao = (BaseDAO)BeanFactory.getBean("password_dao");
		
		//ģ��ƥ������
		StringBuffer sql =new StringBuffer("select * from ep_user where 1=1 ");
		String user_id = (String)page.getFilter("user_id");
		if(user_id != null) 
		sql.append(" and USER_ID like '%"+user_id+"%'");
		
		String param_name = (String)page.getFilter("param_name");
		if(param_name != null) 
				sql.append(" and PARAM_NAME like '%"+param_name+"%'");

		SysLog.debug(sql.toString());
		
		//ִ�в�ѯ���
		page.setList(dao.queryPage(page,sql.toString()));
		
		return null;
	}

	/* 
	 * ��ѯά�����ݣ��������м�¼
	 */
	public List list(UserContext user) throws Exception {
		
		return null;
	}

	/* 
	 * ��ѯ��ϸ��Ϣ�����ص�һ��¼
	 */
	public BaseVO retrieve(BaseVO vo, UserContext user) throws Exception {
		
		BaseDAO  dao = (BaseDAO)BeanFactory.getBean("password_dao");
		return dao.retrieve(dao.getQuerySQL()+" where USER_ID='" + ((PasswordVO) vo).getUser_id()+"'");
	}
	
	/* 
	 * ����where������ѯ��ϸ��Ϣ�����ص�һ��¼
	 */
	public BaseVO retrieve(String whereClause, UserContext user) throws Exception {
		
		return null;
	}

}
