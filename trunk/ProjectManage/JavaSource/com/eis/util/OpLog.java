/*********************************************************
 * File: OpLog.java
 * 
 * Version 1.0
 * 
 * Date     2005-10-31
 * 
 * Author   xin yong
 * 
 ********************************************************/

package com.eis.util;

import com.eis.base.*;
import com.eis.portal.*;
import com.eis.factory.*;
import com.eis.portal.oplog.*;

/**
 * ˵����������־������
 * 
 */
public class OpLog {
	
	private static boolean isOpened = false;

	/**
	 * ��¼�û�������־
	 * @param orgID - ������
	 * @param userID - �û����
	 * @param eventType - �¼�����
	 * @param opID - ��������
	 * @param memo - ��������
	 * @param key - �ؼ���
	 */
	public static void Log(
		String orgID,
		String userID,
		String roleID,
		String eventType,
		String opID,
		String memo,
		String key)
		throws Exception {
		if(!isOpened){
			return;
		}
		try {
			OpLogDAO dao = (OpLogDAO) BeanFactory.getBean("oplog_dao");
			OpLogVO vo = new OpLogVO();
			vo.setEvent_time(DateUtil.getTimeStr());
			vo.setEvent_type(eventType);
			vo.setMemo(memo);
			vo.setOp_id(opID);
			vo.setOp_key(key);
			vo.setOrg_id(orgID);
			vo.setRole_id(roleID);
			vo.setUser_id(userID);
			dao.add(vo);

		} catch (Throwable t) {
			SysLog.getLogger().error("��¼������־ʧ�ܣ�");
			SysLog.getLogger().error(t.getMessage());
			BaseException e = new BaseException();
			e.setErrorCode("E040001");
			throw e;

		}

	}

	/**
	 * ��¼�û�������־
	 * @param orgID - ������
	 * @param userID - �û����
	 * @param eventType - �¼�����
	 * @param opID - ��������
	 * @param memo - ��������
	 */
	public static void Log(
		String orgID,
		String userID,
		String roleID,
		String eventType,
		String opID,
		String memo)
		throws Exception {
		if(!isOpened){
			return;
		}
		try {
			OpLogDAO dao = (OpLogDAO) BeanFactory.getBean("oplog_dao");
			OpLogVO vo = new OpLogVO();
			vo.setEvent_time(DateUtil.getTimeStr());
			vo.setEvent_type(eventType);
			vo.setMemo(memo);
			vo.setOp_id(opID);
			vo.setOp_key("");
			vo.setOrg_id(orgID);
			vo.setRole_id(roleID);
			vo.setUser_id(userID);
			dao.add(vo);

		} catch (Throwable t) {
			SysLog.getLogger().error("��¼������־ʧ�ܣ�");
			SysLog.getLogger().error(t.getMessage());
			BaseException e = new BaseException();
			e.setErrorCode("E040001");
			throw e;

		}

	}

	/**
	 * ��¼�û�������־
	 * @param user - �û������Ķ���
	 * @param eventType - �¼�����
	 * @param opID - ��������
	 * @param memo - ��������
	 * @param key - �ؼ���
	 */
	public static void Log(
		UserContext user,
		String eventType,
		String opID,
		String memo,
		String key)
		throws Exception {
		Log(
			user.getOrgID(),
			user.getUserID(),
			user.getRoleID(),
			eventType,
			opID,
			memo,
			key);
	}

	/**
	 * ��¼�û�������־
	 * @param user - �û������Ķ���
	 * @param eventType - �¼�����
	 * @param opID - ��������
	 * @param memo - ��������
	 */
	public static void Log(
		UserContext user,
		String eventType,
		String opID,
		String memo)
		throws Exception {
		Log(
			user.getOrgID(),
			user.getUserID(),
			user.getRoleID(),
			eventType,
			opID,
			memo);
	}

}
