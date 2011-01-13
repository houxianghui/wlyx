/*********************************************************
 * File: SessionListener.java
 * 
 * Version 1.0
 * 
 * Date     2005-10-19
 * 
 * Author   xin yong
 * 
 ********************************************************/

package com.eis.listener;

import java.sql.SQLException;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.eis.portal.UserContext;
import com.eis.util.*;
import com.eis.portal.userlogin.*;
import com.eis.factory.*;

/**
 * 说明：
 * 
 */
public class SessionListener implements HttpSessionListener {

	public void sessionCreated(HttpSessionEvent event) {

	}

	public void sessionDestroyed(HttpSessionEvent event) {

		HttpSession session = event.getSession();
		try {
			UserContext user = (UserContext) session.getAttribute("user");

			if (user != null) {

				SysLog.debug("退出用户编号：" + user.getUserID());
				SysLog.debug("用户退出时间：" + DateUtil.getTime());

				Object normal_exit = session.getAttribute("normal_exit");
				if (null == normal_exit) {
					//清除用户登录表的纪录
					UserLoginDAO dao =
						(UserLoginDAO) BeanFactory.getBean("userlogin_dao");
					dao.delete(
						dao.getDeleteSQL()
							+ " where USER_ID='"
							+ user.getUserID()
							+ "'");
							
					//纪录操作日志用户非正常退出						
					OpLog.Log(user,"01","logout","签退",user.getUserID());

				}

			}

		} catch (Throwable t) {
			t.printStackTrace();
			SysLog.error("系统自动处理用户非法退出失败!");
			SysLog.error("异常信息：" + t.getMessage());
		}
	}

}
