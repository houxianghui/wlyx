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
 * ˵����
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

				SysLog.debug("�˳��û���ţ�" + user.getUserID());
				SysLog.debug("�û��˳�ʱ�䣺" + DateUtil.getTime());

				Object normal_exit = session.getAttribute("normal_exit");
				if (null == normal_exit) {
					//����û���¼��ļ�¼
					UserLoginDAO dao =
						(UserLoginDAO) BeanFactory.getBean("userlogin_dao");
					dao.delete(
						dao.getDeleteSQL()
							+ " where USER_ID='"
							+ user.getUserID()
							+ "'");
							
					//��¼������־�û��������˳�						
					OpLog.Log(user,"01","logout","ǩ��",user.getUserID());

				}

			}

		} catch (Throwable t) {
			t.printStackTrace();
			SysLog.error("ϵͳ�Զ������û��Ƿ��˳�ʧ��!");
			SysLog.error("�쳣��Ϣ��" + t.getMessage());
		}
	}

}
