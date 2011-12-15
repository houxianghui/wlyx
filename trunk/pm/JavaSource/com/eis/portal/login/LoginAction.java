/*********************************************************
 * File: LoginAction.java
 * 
 * Version 1.0
 * 
 * Date     2005-9-19
 * 
 * Author   lihaibao
 * 
 ********************************************************/

package com.eis.portal.login;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionRedirect;

import com.eis.base.BaseDAO;
import com.eis.base.BaseForm;
import com.eis.cache.ReDefSDicMap;
import com.eis.exception.MessageException;
import com.eis.factory.BeanFactory;
import com.eis.portal.UserContext;
import com.eis.portal.oplog.OpLogVO;
import com.eis.portal.user.UserBO;
import com.eis.portal.user.UserRoleVO;
import com.eis.portal.userlogin.UserLoginDAO;
import com.eis.portal.userlogin.UserLoginVO;
import com.eis.util.DateUtil;
import com.eis.util.MD5Util;
import com.eis.util.SysLog;
import com.huateng.blue.work.SignRecordBO;

/**
 * 说明：
 * 
 */
public class LoginAction extends Action {
	
	private SignRecordBO signRecordBO;

	/**
	 * 构造函数 
	 */
	public LoginAction() {
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
			HttpSession session = request.getSession();

			session.setMaxInactiveInterval(3600);
			LoginVO vo = new LoginVO();

			new BeanUtilsBean().copyProperties(vo, form);

			//存储客户端分辨率					
			String pixel = request.getParameter("PIXEL");			
			session.setAttribute("PIXEL", pixel);

			//检察			
			String checknum = (String) session.getAttribute("checknum");
			String num = request.getParameter("random");
			

			validateLoginId(vo.getLogin_id());

			if (checknum != null && checknum.equals(num)) {

				//加密
				String password = MD5Util.digest(vo.getPassword());

				//数据库查询验证
				//获得业务对象 
				LoginBO bo = (LoginBO) BeanFactory.getBean("login_bo");

				LoginVO userVO =
					(LoginVO) bo.retrieve(
						" where LOGIN_ID='"
							+ vo.getLogin_id()
							+ "' and PASSWORD='"
							+ password
							+ "'",
						null);
				

				if (userVO == null
					|| userVO.getUser_id().trim().length() <= 0) {
					//没有记录
					return forwardError(request, mapping, "用户名或密码错误!");

				}
				if(!password.equals(userVO.getPassword())){
					return forwardError(request,mapping,"用户名或密码错误!");
				}
				String user_id = userVO.getUser_id().trim();
				String login_time = DateUtil.getTimeStr(); //取得系统时间

				//检查用户状态
				String st = userVO.getStatus().trim(); //取得用户状态

				if (st.equals("2")) {
					return forwardError(
						request,
						mapping,
						"此用户处于停用状态，待启用后才能登录！");
				}
				if (st.equals("0")) {
					return forwardError(request, mapping, "此用户处于作废状态，不能登录！");
				}

				//检察用户是否过期
				//String expire_dt = userVO.getInvalid_dt(); //取得用户失效日期

				int expire_dt =
					java.lang.Integer.parseInt(userVO.getInvalid_dt());
				int begin_dt = 
					java.lang.Integer.parseInt(userVO.getBegin_dt());//取得用户起始日期

				String login_dt = login_time.substring(0, 8); //取得系统时间

				if (expire_dt < Integer.parseInt(login_dt) || begin_dt > Integer.parseInt(login_dt)) {

					return this.forwardError(
						request,
						mapping,
						"您的身份无效，请与系统管理员联系！");
				}

				//对USERCONTEXT对象赋值
				UserContext user = new UserContext();
				//赋用户编号				
				user.setUserID(userVO.getUser_id());
				session.setAttribute("user", user);

				//检察密码是否是系统默认密码00000000，若是，则转到密码修改页面
				if (password.equals(MD5Util.digest("00000000"))) {
					session.setAttribute("user_id", userVO.getUser_id());
					return forwardChangePassword(
						request,
						mapping,
						"您的密码为默认值，请先修改密码！");
				}

				//检察密码是否过期
				String modify_dt = userVO.getModify_dt(); //取得密码失效日期

				int modify_dt_int = java.lang.Integer.parseInt(modify_dt);

				int login_time_int = java.lang.Integer.parseInt(login_dt);

				if (login_time_int > modify_dt_int) {
					session.setAttribute("user_id", userVO.getUser_id());
					return forwardChangePassword(
						request,
						mapping,
						"您的密码已过期，请先修改密码！");
				}

				//赋用户姓名
				user.setUserName(userVO.getUser_name());

				//赋用户编号
				user.setUserID(user_id);

				//赋登陆编号
				user.setLoginID(userVO.getLogin_id());

				//赋机构代码
				user.setOrgID(userVO.getAmsd_store());


				//赋主角色编号
				user.setRoleID(userVO.getRole_id());

				//赋主角色名称
				
				user.setRoleName(
					ReDefSDicMap.getDicItemVal("0002", userVO.getRole_id()));				
						
				
				//赋角色组合	

				//获取UserContext对象为user的用户的已选角色列表

				UserBO userbo = (UserBO) BeanFactory.getBean("user_bo");
				List roleslist = userbo.queryRolesList(user);

				int length = 0; //角色列表长度变量	
				String[] roles = null; //存储角色代码列表的变量		

				if (roleslist != null) {
					Iterator iter = roleslist.iterator();
					while (iter.hasNext()) {
						iter.next();
						length = length + 1;
					}

					//建立长度为length的数组
					roles = new String[length];

					//将角色列表中的角色编号传入数组roles
					iter = roleslist.iterator();

					int i = 0;
					while (iter.hasNext()) {
						roles[i] = ((UserRoleVO) (iter.next())).getRole_id();
						SysLog.debug("角色：" + roles[i]);
						i++;
					}

				}

				user.setRole(roles);

				session.setAttribute("user", user);
				//签到
				signRecordBO.signOn(user);
				
				//记录登录日志和签到信息,进行事务控制			

				//记录签到信息
				UserLoginVO userLoginVO = new UserLoginVO();
				UserLoginDAO userLoginDAO =
					(UserLoginDAO) getBean("userlogin_dao");

				userLoginVO.setUser_id(user_id);
				userLoginVO.setLogin_time(login_time);
				userLoginVO.setClient_ip(request.getRemoteAddr());

////				UserLoginVO vo1 =
////					(UserLoginVO) userLoginDAO.retrieve(
////						userLoginDAO.getQuerySQL()
////							+ " where USER_ID='"
////							+ userLoginVO.getUser_id()
////							+ "'");
////				//验证是否重复登录
////				if (vo1 != null) {
////					return forwardError(request, mapping, "不能重复登录！");
////				}

				//记录操作日志

				OpLogVO opLogVO = new OpLogVO();
				
				opLogVO.setEvent_time(login_time);
				opLogVO.setEvent_type("01");
				opLogVO.setOrg_id(user.getOrgID());
				opLogVO.setRole_id(user.getRoleID());
				opLogVO.setUser_id(user.getUserID());
				opLogVO.setOp_id("login");
				opLogVO.setMemo(request.getRemoteAddr()+"登录");//opLogVO.setMemo("登录");
				opLogVO.setOp_key(user.getUserID());
				
				
//				bo.add(userLoginVO, opLogVO, user);

				//根据角色模板查找主页
				BaseDAO homepageDAO = (BaseDAO) getBean("homepage_dao");
				
				String homepage =
					homepageDAO.querySingle(
						"select URL from ep_homepage where TEMPL_ID in (select TEMPL_ID from ep_role where ROLE_ID='"
							+ user.getRoleID()
							+ "')");

				if (homepage == null || homepage.trim().length() <= 0) {
					session.setAttribute("homepage", "/blank.htm");
				} else {
					session.setAttribute("homepage", homepage);
				}

				//返回到默认首页	
				return new ActionRedirect("/main.jsp");
				//return mapping.findForward("default_homepage");

			} else {
				return forwardError(request, mapping, "非法登录!");
			}

		} catch(MessageException e){
			throw e;
		}catch (Throwable t) {
			t.printStackTrace();
			SysLog.debug("登录处理出错");
			SysLog.error(t.getMessage());

		}
		return null;

	}

	/** 
	 * 查询明细信息 
	 */
	public boolean retrieve(
		ActionMapping mapping,
		BaseForm form,
		HttpServletRequest request,
		HttpServletResponse response,
		UserContext user)
		throws Exception {

		return false;
	}
	private void validateLoginId(String s)throws Exception{
		if(s == null || s.trim().length() == 0){
			throw new MessageException("请输入用户名");
		}
		char[] c = s.trim().toCharArray();
		for(int i = 0;i<c.length;i++){
			if(c[i] == ' '){
				throw new MessageException("用户名不能包含空格");
			}
		}
	}

	/**
	 * 转向到错误信息提示页面
	 * @param request
	 * @param mapping
	 * @param message
	 * @return ActionForward
	 * 
	 */
	public ActionForward forwardError(
		HttpServletRequest request,
		ActionMapping mapping,
		String message) {

		request.setAttribute("message", message);

		return (mapping.findForward("default_error"));
	}

	/**
	 * 转向到密码修改信息提示页面
	 * @param request
	 * @param mapping
	 * @param message
	 * @return ActionForward
	 * 
	 */
	public ActionForward forwardChangePassword(
		HttpServletRequest request,
		ActionMapping mapping,
		String message) {

		request.setAttribute("message", message);

		return (mapping.findForward("chgpwd"));
	}

	/**
	 * 根据bean ID获得实例对象
	 * @param name - bean ID	 
	 * @return bean的实例对象
	 *
	 */
	public Object getBean(String name) throws Exception {

		return BeanFactory.getBean(name);
	}

	public SignRecordBO getSignRecordBO() {
		return signRecordBO;
	}

	public void setSignRecordBO(SignRecordBO signRecordBO) {
		this.signRecordBO = signRecordBO;
	}
	
	

	

	
}
