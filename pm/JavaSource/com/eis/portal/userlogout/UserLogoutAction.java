package com.eis.portal.userlogout;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.sql.*;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.commons.beanutils.BeanUtilsBean;

import com.eis.base.*;
import com.eis.portal.UserContext;
import com.eis.factory.*;
import com.eis.util.*;
import com.eis.connectionPool.*;
import com.eis.portal.userlogin.*;
import com.eis.portal.oplog.*;

/** 
 * 说明：签退的控制类 
 */
public class UserLogoutAction extends Action {

	/** 
	 * 构造函数 
	 */
	public UserLogoutAction() {
		super();
	}

	/** 
	 * 执行请求处理 
	 */
	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {
		try {
			//从“user”会话对象中获得用户上下文对象UserContext
			UserContext user =
				(UserContext) request.getSession().getAttribute("user");
				
			//创建用户签退的数据对象
			UserLogoutVO vo = new UserLogoutVO();
			//创建用户签退的业务对象
			UserLogoutBO bo =
				(UserLogoutBO) BeanFactory.getBean("userlogout_bo");
			
			//没用？	
			new BeanUtilsBean().copyProperties(vo, form);

			String login_time_detail = DateUtil.getTimeStr(); //获得系统时间

			//从用户上下文对象UserContext中获得user_id
			String user_id = user.getUserID();

			/**删除登录记录ep_user_login表中的用户记录，
			 * 同时记录操作日志到ep_op_log表
			 * */
			
			//建立用户登录数据对象，设置其user_id属性为登录用户user_id
			UserLogoutVO userLogoutVO = new UserLogoutVO();
			//UserLoginVO userLogoutVO = new UserLoginVO();
			userLogoutVO.setUser_id(user_id);

			
			/**建立操作日志数据对象OpLogVO，并赋值*/
			OpLogVO opLogVO = new OpLogVO();
			//设置其事件时间为系统时间
			opLogVO.setEvent_time(login_time_detail);
			//设置其事件类型
			opLogVO.setEvent_type("01");
			//机构
			opLogVO.setOrg_id(user.getOrgID());
			//主角色
			opLogVO.setRole_id(user.getRoleID());
			opLogVO.setUser_id(user.getUserID());
			//操作类型
			opLogVO.setOp_id("logout");
			opLogVO.setMemo("签退");
			//设置其主键为“”
			opLogVO.setOp_key(user.getUserID());

			/**执行业务对象的exec(...)方法, 
			 * 删除登录记录ep_user_login表中的用户记录，
			 * 同时记录操作日志到ep_op_log表*/	
			bo.exec(userLogoutVO, opLogVO);

			//设置正常退出标志
			request.getSession().setAttribute("normal_exit", "1");

			request.getSession().invalidate();

		} catch (Throwable t) {
			SysLog.error("用户签退失败！" + t.getMessage());
		}
		//跳转到签退成功页面
		return mapping.findForward("exit");

	}

}