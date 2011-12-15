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
 * 说明：操作日志工具类
 * 
 */
public class OpLog {
	
	private static boolean isOpened = false;

	/**
	 * 纪录用户操作日志
	 * @param orgID - 机构号
	 * @param userID - 用户编号
	 * @param eventType - 事件类型
	 * @param opID - 操作类型
	 * @param memo - 操作内容
	 * @param key - 关键字
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
			SysLog.getLogger().error("记录操作日志失败！");
			SysLog.getLogger().error(t.getMessage());
			BaseException e = new BaseException();
			e.setErrorCode("E040001");
			throw e;

		}

	}

	/**
	 * 纪录用户操作日志
	 * @param orgID - 机构号
	 * @param userID - 用户编号
	 * @param eventType - 事件类型
	 * @param opID - 操作类型
	 * @param memo - 操作内容
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
			SysLog.getLogger().error("纪录操作日志失败！");
			SysLog.getLogger().error(t.getMessage());
			BaseException e = new BaseException();
			e.setErrorCode("E040001");
			throw e;

		}

	}

	/**
	 * 纪录用户操作日志
	 * @param user - 用户上下文对象
	 * @param eventType - 事件类型
	 * @param opID - 操作类型
	 * @param memo - 操作内容
	 * @param key - 关键字
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
	 * 纪录用户操作日志
	 * @param user - 用户上下文对象
	 * @param eventType - 事件类型
	 * @param opID - 操作类型
	 * @param memo - 操作内容
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
