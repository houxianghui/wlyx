/*********************************************************
 * File: UserAction.java
 * 
 * Version 1.0
 * 
 * Date     2005-9-27
 * 
 * Author   陈 蓉
 * 
 ********************************************************/
package com.eis.portal.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import java.util.*;
import com.eis.base.*;
import com.eis.portal.UserContext;
import com.eis.util.*;
import com.eis.key.*;
import com.eis.cache.*;

/** 
 * 说明：用户列表的控制类 
 */
public class UserAction extends BaseAction {

	/** 
	 * 构造函数 
	 */
	public UserAction() {
		super();
	}

	/** 
	 * 执行请求处理 
	 */
	public ActionForward process(ActionMapping mapping, BaseForm form, HttpServletRequest request, HttpServletResponse response, UserContext user) throws Exception {
		String act = form.getAct();
		if (act.equals("c")) { //增加 

			String step = request.getParameter("step");

			if (null == step) { //初始化阶段，跳转到增加页面 

				return beforeAdd(mapping, form, request, response, user);

			} else //用户已提交新增数据，执行数据保存 

				return add(mapping, form, request, response, user);

		} else if (act.equals("check")) { //增加用户时，检查用户登录编号有无重复

			return checkLoginId(mapping, form, request, response, user);

		} else if (act.equals("u")) { //修改 

			String step = request.getParameter("step");

			if (null == step) { //初始化阶段，查询明细信息并跳转到修改页面 

				return editInfo(mapping, form, request, response, user);

			} else //用户已提交修改后的数据，执行数据保存 

				return update(mapping, form, request, response, user);

		} else if (act.equals("r")) { //查询明细信息 

			return retrieve(mapping, form, request, response, user);

		} else if (act.equals("r1")) { //查询明细信息 

			return retrieve1(mapping, form, request, response, user);

		} else if (act.equals("t")) { //废除用户信息

			return terminate(mapping, form, request, response, user);

		} else if (act.equals("able")) { //启用用户信息

			return able(mapping, form, request, response, user);

		} else if (act.equals("disable")) { //停用用户信息

			return disable(mapping, form, request, response, user);

		} else if (act.equals("d")) { //删除用户 

			return delete(mapping, form, request, response, user);

		} else if (act.equals("list")) { //返回维护列表 

			return list(mapping, form, request, response, user);

		} else if (act.equals("list1")) { //返回维护列表 

			return list1(mapping, form, request, response, user);

		} else if (act.equals("ql")) { //查询列表 

			return queryList(mapping, form, request, response, user);

		} else if (act.equals("query1")) { //查询列表 

			return queryList1(mapping, form, request, response, user);

		} else if (act.equals("query2")) { //查询列表 

			return queryList2(mapping, form, request, response, user);

		} else if (act.equals("listpop")) { //查询列表 

			return listpop(mapping, form, request, response, user);
		} else if (act.equalsIgnoreCase("assignoperlist")) {

			return assignOperList(mapping, form, request, response, user);
		} else if (act.equals("setpwd")) { //重置密码 

			return setPwd(mapping, form, request, response, user);

		} else if("exclude".equalsIgnoreCase(act)){
			return exclude(mapping, form, request, response, user);
		}
			return null;
	}

	/** 
	 * 查询维护列表 
	 */
	public ActionForward list(ActionMapping mapping, BaseForm form, HttpServletRequest request, HttpServletResponse response, UserContext user) throws Exception {

		PageObject page = new PageObject();
		String pageNo = request.getParameter("pageNO");

		String requery = request.getParameter("requery");

		String orgID = null;

		if (pageNo == null || (requery != null && requery.trim().equals("y"))) {
			//默认查询			
			page.setCurPage(1);

		} else { //重新查询
			page.setCurPage(Integer.parseInt(pageNo));
		}

		//如果第一次进入菜单，将用户机构存入会话信息
		String init = request.getParameter("init");

		if (init != null && init.trim().length() > 0) {
			//将机构号存放到会话中
			orgID = user.getOrgID();
			request.getSession().setAttribute("org_id", orgID);

		} else {
			orgID = (String) request.getSession().getAttribute("org_id");

		}

		if (requery != null && requery.trim().equals("y")) { //重新查询

			//将机构号存放到会话中
			orgID = request.getParameter("amsd_store_f");
			request.getSession().setAttribute("org_id", orgID);
		}

		//检查是否从增加或修改页面返回到列表
		String current_org = (String) request.getSession().getAttribute("user_org");
		if (null != current_org && current_org.trim().length() > 0) {
			request.getSession().setAttribute("org_id", current_org);
			request.getSession().removeAttribute("user_org");
			orgID = current_org;
		}

		//添加机构查询过滤条件 
		page.addFilter("amsd_store_f", request.getSession().getAttribute("org_id"));

		//添加用户姓名查询过滤条件 
		page.addFilter("user_name_f", request.getParameter("user_name_f"));

		//添加用户状态查询过滤条件 
		page.addFilter("status_f", request.getParameter("status_f"));

		//获得业务对象 
		BaseBO bo = (BaseBO) getBean("user_bo");

		//调用业务对象的list（）方法,返回列表结果 
		bo.list(page, user);

		//将结果对象写到request对象中 
		request.setAttribute("pageResult", page);

		((UserForm) form).setAmsd_store_f(orgID);

		//执行页面跳转 
		return mapping.findForward("list");
	}
	
	/**
	 * 申请主管选择指派员
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public ActionForward assignOperList(ActionMapping mapping, BaseForm form, HttpServletRequest request, HttpServletResponse response, UserContext user) throws Exception {

		PageObject page = new PageObject();
		String pageNo = request.getParameter("pageNO");

		String requery = request.getParameter("requery");

		if (pageNo == null || (requery != null && requery.trim().equals("y"))) {
			page.setCurPage(1);
		} else {
			page.setCurPage(Integer.parseInt(pageNo));
		}
		
		page.addFilter("user_name_f", request.getParameter("user_name_f"));
		//String amsdStore = request.getParameter("currAmsdStore");
		String amsdStore=((UserForm)form).getCurrAmsdStore();
		
		if(amsdStore == null||amsdStore.equalsIgnoreCase("")){
			amsdStore=user.getOrgID();
		}
	
		//获得业务对象 
		UserBO bo = (UserBO) getBean("user_bo");

		//调用业务对象的list（）方法,返回列表结果 
		bo.queryAssignOperList(page, user,amsdStore);

		request.setAttribute("pageResult", page);

		//执行页面跳转 
		return mapping.findForward("assignoperlistpop");
	}

	public ActionForward list1(ActionMapping mapping, BaseForm form, HttpServletRequest request, HttpServletResponse response, UserContext user) throws Exception {

		PageObject page = new PageObject();
		String pageNo = request.getParameter("pageNO");

		String requery = request.getParameter("requery");

		if (pageNo == null || (requery != null && requery.trim().equals("y"))) {
			page.setCurPage(1);
		} else {
			page.setCurPage(Integer.parseInt(pageNo));
		}

		//获得业务对象 
		UserBO bo = (UserBO) getBean("user_bo");

		//调用业务对象的list（）方法,返回列表结果 
		bo.list2(page, user);

		//将结果对象写到request对象中 

		request.setAttribute("pageResult", page);

		//执行页面跳转 
		return mapping.findForward("list1");
	}

	/** 
	 * 查询列表 
	 */
	public ActionForward queryList(ActionMapping mapping, BaseForm form, HttpServletRequest request, HttpServletResponse response, UserContext user) throws Exception {

		PageObject page = new PageObject();
		String pageNo = request.getParameter("pageNO");

		String requery = request.getParameter("requery");

		if (pageNo == null || (requery != null && requery.trim().equals("y"))) {
			page.setCurPage(1);
		} else {
			page.setCurPage(Integer.parseInt(pageNo));
		}

		//获得业务对象 
		BaseBO bo = (BaseBO) getBean("user_bo");

		//调用业务对象的list（）方法,返回列表结果 
		bo.list(page, user);

		//将结果对象写到request对象中 
		request.setAttribute("pageResult", page);

		//执行页面跳转 
		return mapping.findForward("querylist");
	}
	public ActionForward queryList1(ActionMapping mapping, BaseForm form, HttpServletRequest request, HttpServletResponse response, UserContext user) throws Exception {

		PageObject page = new PageObject();
		String pageNo = request.getParameter("pageNO");

		String requery = request.getParameter("requery");

		if (pageNo == null || pageNo.trim().length() == 0) {
			page.setCurPage(1);
		} else {
			page.setCurPage(Integer.parseInt(pageNo));
		}
		//在此处通过page.addFilter()方法添加过滤条件 
		page.addFilter("amsd_store_f", request.getParameter("amsd_store_f"));

		//获得业务对象 
		UserBO bo = (UserBO) getBean("user_bo");

		//调用业务对象的list（）方法,返回列表结果 
		bo.queryList1(page, user);

		//将结果对象写到request对象中 
		request.setAttribute("pageResult", page);

		//执行页面跳转 
		return mapping.findForward("list1");

	}
	public ActionForward queryList2(ActionMapping mapping, BaseForm form, HttpServletRequest request, HttpServletResponse response, UserContext user) throws Exception {

		PageObject page = new PageObject();
		String pageNo = request.getParameter("pageNO");

		String requery = request.getParameter("requery");

		if (pageNo == null || pageNo.trim().length() == 0) {
			page.setCurPage(1);
		} else {
			page.setCurPage(Integer.parseInt(pageNo));
		}
		//在此处通过page.addFilter()方法添加过滤条件 

		page.addFilter("cre_branch_f", request.getParameter("cre_branch_f"));
		System.out.println(request.getParameter("cre_branch_f"));

		//获得业务对象 
		UserBO bo = (UserBO) getBean("user_bo");

		//调用业务对象的list（）方法,返回列表结果 
		bo.queryList(page, user);

		//将结果对象写到request对象中 
		request.setAttribute("pageResult", page);

		//执行页面跳转 
		return mapping.findForward("list1");

	}
	public ActionForward beforeAdd(ActionMapping mapping, BaseForm form, HttpServletRequest request, HttpServletResponse response, UserContext user) throws Exception {
		//获得业务对象 
		UserBO bo = (UserBO) getBean("user_bo");

		//获得用户代码
		//String user_id = request.getParameter("user_id");

		String orgID = (String) request.getSession().getAttribute("org_id");

		//获得用户可以选择但没选择的角色权限集合
		List rolesNotSelected = bo.listRolesNotSelected(null, user, orgID);
		
		//获得所有催收员角色的用户ID
		List collRoles = bo.getCollRoles(user);

		//将结果对象写到request对象中
		request.setAttribute("rolesNotSelected", rolesNotSelected);
		request.setAttribute("collRoles", collRoles);

		((UserForm) form).setAmsd_store(orgID);

		//执行页面跳转,返回到列表页面 
		return mapping.findForward("new");
	}

	/** 
	 * 增加用户数据 
	 */
	public ActionForward add(ActionMapping mapping, BaseForm form, HttpServletRequest request, HttpServletResponse response, UserContext user) throws Exception {

		//进行权限校验
		if (!OpMap.checkRoleAuth(user.getRoleID(), "user_c")) {

			BaseException e = new BaseException();
			e.setErrorCode("E020001");
			throw e;
		}

		//获得业务对象 
		UserBO bo = (UserBO) getBean("user_bo");

		UserForm bean = (UserForm) form;

		/**获取更新用户定义表所需的数据vo */

		UserVO vo = new UserVO();

		//进行数据传输 
		copyProperties(vo, bean);

		/**检查用户登陆编号有无重复 
		 **/

		String hasLoginId = bo.retrieveLoginId(vo.getLogin_id(), user);
		SysLog.debug("查询到的用户login_id为:" + hasLoginId);

		//如果用户表中已存在所输入登陆编号的用户,提示编号重复错误,返回新增页面
		if (!hasLoginId.equals("0")) {
			return forwardError(request, mapping, "登陆编号重复,请重新输入其他编号！");
		}

		/**设置用户代码，自动获取用户代码，为用户表中user_id最大值＋１
		 */

		//获取USER_ID最大值
		String maxKey = bo.retrieveMax(user);
		SysLog.debug("最大user_id为：" + maxKey);
		String nextKey = null;

		if (maxKey == null || maxKey.trim().equals("0") || maxKey.trim().length() < 1) {

			nextKey = "00000001";

		} else {

			//获取要新增用户的USER_ID值
			nextKey = KeyGenerator.getNextKeyStr(maxKey, 8);
			SysLog.debug("最大user_id+1的值为：" + nextKey);

		}
		//设置用户代码
		vo.setUser_id(nextKey);

		/**部门代码暂时设为01*/
		vo.setDepart_id("01");

		/**设置主角色代码
		 * 如果用户未选择主角色，将选择的角色集合中的第一项设置为主角色
		 */

		//检查是否至少选择了一个角色
		String selectRoles[] = request.getParameterValues("rolesRight");

		if (selectRoles == null || selectRoles.length < 1) {
			//如果未给用户分配任何角色,提示错误,将不能增加新用户
			return forwardError(request, mapping, "请至少给用户分配一个角色！");

		}

		/**检查是否正确地设置了主角色代码 
		 */

		//获取表单中的主角色代码
		String role_id = ((UserForm) form).getRole_id();

		if ((role_id == null) || (role_id.trim()).equals("")) {
			//如果未设置主角色
			return forwardError(request, mapping, "请在已选角色中选择一项设置为主角色！");

		} else {
			//若输入了主角色，检查输入的主角色是否在选择的角色集合中

			/**
			 * 检查主角色是否在已选角色中标识
			 * 0-表示不在已选角色中，1-表示在其中
			 */
			int check = 0;
			for (int i = 0; i < selectRoles.length; i++) {

				//检查主角色是否在所选角色中，如果在,check=1
				if (role_id.equals(selectRoles[i])) {
					check = 1;
				}
			}
			if (check == 0) {
				//如果主角色不在所选角色中，提示错误信息，返回页面
				return forwardError(request, mapping, "主角色不在已选角色中，请在已选角色中择一设置为主角色！");
			}

		}

		/**设置用户密码为默认值"00000000" */
		vo.setPassword(MD5Util.digest("00000000"));

		/**设置用户状态为"1"—正常	*/
		vo.setStatus("1");

		/**设置建立日期为今天*/
		vo.setReg_dt(DateUtil.getDTStr());

		/**设置密码失效日期为今天*/
		vo.setModify_dt(DateUtil.getDTStr());

		/**设置更新日期为今天*/
		vo.setSt_chg_dt(DateUtil.getDTStr());

		/**设置管理员代码 */
		vo.setAdmin_id(user.getUserID());

		/**获取更新用户角色关系表所需的数据列表rolesSelectList */

		//获取用户代码user_id
		String user_id = vo.getUser_id();

		List list = new ArrayList();

		//从提交表单rolesRight元素获得为用户选择的角色集合
		//xinyong 删除下面一行代码,因前面已经定义了相同内容的该变量
		//String selectRoles[] = request.getParameterValues("rolesRight");

		if (selectRoles != null) {
			//如果对用户至少分配一个角色，则放入list集合中
			for (int i = 0; i < selectRoles.length; i++) {
				list.add(selectRoles[i]);
			}
		}

		//调用业务对象的add（）方法,同时在用户定义表和用户角色关系表中增加记录 
		bo.add(user_id, vo, list, user);

		request.getSession().setAttribute("user_org", vo.getAmsd_store());

		//执行页面跳转,返回到列表页面 
		return list(mapping, form, request, response, user);

	}

	/** 
	 * 增加用户时，检查用户登录编号有无重复 
	 */

	public ActionForward checkLoginId(ActionMapping mapping, BaseForm form, HttpServletRequest request, HttpServletResponse response, UserContext user) throws Exception {

		//获得业务对象 
		UserBO bo = (UserBO) getBean("user_bo");

		UserForm bean = (UserForm) form;

		/**获取更新用户定义表所需的数据vo */

		UserVO vo = new UserVO();

		//进行数据传输 
		copyProperties(vo, bean);

		/**检查用户登陆编号有无重复 
		 * */
		String hasLoginId = bo.retrieveLoginId(vo.getLogin_id(), user);

		//如果用户表中已存在所输入登陆编号的用户,提示编号重复错误,返回新增页面
		if (!hasLoginId.equals("0")) {
			request.setAttribute("UserIdExist", "n");
			//执行页面跳转,回到增加用户页面
			return beforeAdd(mapping, form, request, response, user);

		} else {
			request.setAttribute("UserIdExist", "y");
			//执行页面跳转,回到增加用户页面 
			return beforeAdd(mapping, form, request, response, user);

		}
	}

	/** 
	 * 查询维护信息明细 
	 */
	public ActionForward editInfo(ActionMapping mapping, BaseForm form, HttpServletRequest request, HttpServletResponse response, UserContext user) throws Exception {

		//获得业务对象 
		UserBO bo = (UserBO) getBean("user_bo");

		UserVO vo = new UserVO();

		//获得查询主键 
		vo.setUser_id(((UserForm) form).getUser_id());

		//调用业务对象的retrieve（）方法,查询明细信息 
		vo = (UserVO) bo.retrieve(vo, user);

		vo.setLogin_id2(vo.getLogin_id());

		//将结果对象写到form对象中 
		copyProperties(form, vo);

		//获取用户代码
		String user_id = ((UserForm) form).getUser_id();
		SysLog.debug("用户代码：" + user_id);

		String orgID = (String) request.getSession().getAttribute("org_id");

		//获得用户可以选择但没选择的角色权限集合
		List rolesNotSelected = bo.listRolesNotSelected(user_id, user, orgID);

		//获得角色已选择的操作权限集合
		List rolesSelected = bo.listRolesSelected(user_id, user);
		
		//获得所有催收员角色的用户ID
		List collRoles = bo.getCollRoles(user);
		String collCheck = bo.doCollCheck(rolesSelected,collRoles);

		//将结果对象写到request对象中
		request.setAttribute("rolesNotSelected", rolesNotSelected);
		request.setAttribute("rolesSelected", rolesSelected);
		request.setAttribute("collCheck",collCheck);
		request.setAttribute("collRoles", collRoles);

		((UserForm) form).setAmsd_store(orgID);

		request.getSession().setAttribute("user_org", vo.getAmsd_store());

		//执行页面跳转,跳转到修改页面 
		return mapping.findForward("edit");
	}

	/** 
	 * 修改用户数据 
	 */
	public ActionForward update(ActionMapping mapping, BaseForm form, HttpServletRequest request, HttpServletResponse response, UserContext user) throws Exception {

		//进行权限校验
		if (!OpMap.checkRoleAuth(user.getRoleID(), "user_u")) {

			BaseException e = new BaseException();
			e.setErrorCode("E020001");
			throw e;
		}

		//获得业务对象 
		UserBO bo = (UserBO) getBean("user_bo");

		/**获取更新用户定义表所需的数据vo */

		UserVO vo = new UserVO();

		//进行数据传输 
		copyProperties(vo, form);

		//检查用户登录编号是否重复
		String repeat = bo.checkLoginIdRepeat(vo, user);

		/**设置更新日期为今天*/
		vo.setSt_chg_dt(DateUtil.getDTStr());

		/**设置管理员代码 */
		vo.setAdmin_id(user.getUserID());

		String user_id = request.getParameter("user_id");

		/**获取更新用户角色关系表所需的数据列表rolesSelectList*/

		//从提交表单rolesRight元素获得为用户选择的角色集合
		String selectRoles[] = request.getParameterValues("rolesRight");

		List list = new ArrayList();

		//获取主角色
		String role_id = ((UserForm) form).getRole_id();

		/**设置检查主角色是否在已选角色中标识
		 * 0-表示不在已选角色中，1-表示在其中
		 */
		int check = 0;

		/**若所选角色中，同时包括录入员和复核员，则报错，保存失败*/
		boolean ipt = false; //录入员角色标记，若为ture标识录入员
		boolean chk = false; //复核员角色标记，若为ture标识复核员
		String roleid = "";
		/***/

		//如果对用户至少分配一个角色，则放入list集合中
		if (selectRoles != null) {
			for (int i = 0; i < selectRoles.length; i++) {

				/**若所选角色中，同时包括录入员和复核员，则报错，保存失败*/
				roleid = bo.getLogicIdByRoleId(selectRoles[i], user);
				if (roleid != null && roleid.trim().length() > 0) {
					if (roleid.trim().equals("check")) {
						chk = true;
					}
					if (roleid.trim().equals("input")) {
						ipt = true;
					}
				}
				/***/

				//如果对用户至少分配一个角色，则放入list集合中
				list.add(selectRoles[i]);

				//检查主角色是否在所选角色中，如果在,check=1
				if (role_id.equals(selectRoles[i])) {
					check = 1;
				}
			}
		}

		/**若所选角色中，同时包括录入员和复核员，则报错，保存失败*/
		if (ipt && chk) {
			return forwardError(request, mapping, "已选角色中不能同时存在录入员和复核员角色！");
		}
		/***/

		request.getSession().setAttribute("user_org", vo.getAmsd_store());

		if (check == 0) {
			//如果主角色不在所选角色中，提示错误信息，返回
			return forwardError(request, mapping, "请在已选角色中选择主角色！");
		} else {
			//如果主角色在所选角色中

			//调用业务对象的update（）方法,同时更新用户定义表和用户角色关系表数据 
			bo.update(user_id, vo, list, user);

			SysLog.debug(repeat);

			//传递数据保存结果
			if (repeat.equals("n")) { //如果登录编号不重复
				request.setAttribute("success", "y");
			} else {
				request.setAttribute("success", "n");
			}

			//执行页面跳转 		
			return editInfo(mapping, form, request, response, user);

		}

	}

	/** 
	 * 删除用户数据 
	 */
	public ActionForward delete(ActionMapping mapping, BaseForm form, HttpServletRequest request, HttpServletResponse response, UserContext user) throws Exception {

		//进行权限校验
		if (!OpMap.checkRoleAuth(user.getRoleID(), "user_d")) {

			BaseException e = new BaseException();
			e.setErrorCode("E020001");
			throw e;
		}

		//获得业务对象 
		BaseBO bo = (BaseBO) getBean("user_bo");

		UserVO vo = new UserVO();

		//获得删除纪录主键 
		vo.setUser_id(((UserForm) form).getUser_id());

		//调用业务对象的retrieve（）方法,获取用户状态
		vo = (UserVO) bo.retrieve(vo, user);

		request.getSession().setAttribute("user_org", vo.getAmsd_store());

		if (!(vo.getStatus()).equals("0")) {
			//如果用户未作废,提示不能删除,请先作废用户,返回列表
			return forwardSuccessPage(request, mapping, "不能删除用户,请先作废用户！", "User.do?act=list");

		} else {
			//否则,删除用户,然后返回列表			
			//调用业务对象的delete（）方法,执行数据删除 
			bo.delete(vo, user);

			//传递数据保存结果
			//request.getSession().setAttribute("success", "y");

			//执行页面跳转		
			return forwardSuccessPage(request, mapping, "数据删除成功！", "User.do?act=list");
		}
	}

	/** 
	 * 查询明细信息 
	 */
	public ActionForward retrieve(ActionMapping mapping, BaseForm form, HttpServletRequest request, HttpServletResponse response, UserContext user) throws Exception {

		//获得业务对象 
		UserBO bo = (UserBO) getBean("user_bo");

		UserVO vo = new UserVO();

		//获得查询主键 
		vo.setUser_id(((UserForm) form).getUser_id());

		//调用业务对象的retrieve（）方法,查询明细信息 
		vo = (UserVO) bo.retrieve(vo, user);

		//将结果对象写到form对象中 
		copyProperties(form, vo);

		//执行页面跳转,返回到列表页面 
		return mapping.findForward("view");
	}
	public ActionForward retrieve1(ActionMapping mapping, BaseForm form, HttpServletRequest request, HttpServletResponse response, UserContext user) throws Exception {

		//获得业务对象 
		UserBO bo = (UserBO) getBean("user_bo");

		UserVO vo = new UserVO();

		//获得查询主键 
		vo.setUser_id(((UserForm) form).getUser_id());

		//调用业务对象的retrieve（）方法,查询明细信息 
		vo = bo.retrieve1(vo, user);

		//将结果对象写到form对象中 
		copyProperties(form, vo);

		//执行页面跳转,返回到列表页面 
		return mapping.findForward("view");
	}

	/** 
	 * 废除用户 
	 */

	public ActionForward terminate(ActionMapping mapping, BaseForm form, HttpServletRequest request, HttpServletResponse response, UserContext user) throws Exception {

		//进行权限校验
		if (!OpMap.checkRoleAuth(user.getRoleID(), "user_terminate")) {

			BaseException e = new BaseException();
			e.setErrorCode("E020001");
			throw e;
		}

		// 获得业务对象
		UserBO bo = (UserBO) getBean("user_bo");

		UserForm bean = (UserForm) form;

		UserVO vo = new UserVO();

		vo.setUser_id(bean.getUser_id());

		//调用业务对象的retrieve（）方法,获取用户状态
		vo = (UserVO) bo.retrieve(vo, user);
		request.getSession().setAttribute("user_org", vo.getAmsd_store());

		if ((vo.getStatus()).equals("0")) {
			//如果用户已经作废,提示,返回列表
			return forwardSuccessPage(request, mapping, "用户已作废！", "User.do?act=list");

		} else if ((vo.getStatus()).equals("1")) {
			//如果用户未停用,提示不能作废,请先停用,返回列表
			return forwardSuccessPage(request, mapping, "请先停用此用户,再进行作废用户操作！", "User.do?act=list");
		} else {
			//否则,作废用户,然后返回列表

			/**设置更新日期为今天*/
			vo.setSt_chg_dt(DateUtil.getDTStr());
			SysLog.debug("作废日期为：" + vo.getSt_chg_dt());

			request.getSession().setAttribute("user_org", vo.getAmsd_store());

			/**设置管理员代码 */
			vo.setAdmin_id(user.getUserID());

			//调用业务对象的terminate（）方法
			bo.terminate(vo, user);

			//传递数据保存结果
			//request.getSession().setAttribute("success", "y");

			//执行页面跳转		
			return forwardSuccessPage(request, mapping, "用户作废成功！", "User.do?act=list");

		}
	}

	/** 
	 * 启用用户 
	 */

	public ActionForward able(ActionMapping mapping, BaseForm form, HttpServletRequest request, HttpServletResponse response, UserContext user) throws Exception {

		//进行权限校验
		if (!OpMap.checkRoleAuth(user.getRoleID(), "user_enable")) {

			BaseException e = new BaseException();
			e.setErrorCode("E020001");
			throw e;
		}

		// 获得业务对象
		UserBO bo = (UserBO) getBean("user_bo");

		UserForm bean = (UserForm) form;

		UserVO vo = new UserVO();

		vo.setUser_id(bean.getUser_id());

		//调用业务对象的retrieve（）方法,获取用户状态
		vo = (UserVO) bo.retrieve(vo, user);

		request.getSession().setAttribute("user_org", vo.getAmsd_store());

		if ((vo.getStatus()).equals("1")) {
			//如果用户已经启用,提示,返回列表
			return forwardSuccessPage(request, mapping, "用户已启用！", "User.do?act=list");

		} else if ((vo.getStatus()).equals("0")) {
			//如果用户已作废,提示不能再启用,返回列表
			return forwardSuccessPage(request, mapping, "用户已作废,不能再启用！", "User.do?act=list");
		} else {
			//否则,启用用户,然后返回列表

			/**设置更新日期为今天*/
			vo.setSt_chg_dt(DateUtil.getDTStr());
			SysLog.debug("启用更新日期为：" + vo.getSt_chg_dt());

			/**设置管理员代码 */
			vo.setAdmin_id(user.getUserID());

			//调用业务对象的able（）方法
			bo.able(vo, user);

			//传递数据保存结果
			//request.getSession().setAttribute("success", "y");

			//执行页面跳转		
			return forwardSuccessPage(request, mapping, "用户启用成功！", "User.do?act=list");

		}

	}

	/** 
	 * 停用用户 
	 */

	public ActionForward disable(ActionMapping mapping, BaseForm form, HttpServletRequest request, HttpServletResponse response, UserContext user) throws Exception {

		//进行权限校验
		if (!OpMap.checkRoleAuth(user.getRoleID(), "user_disable")) {

			BaseException e = new BaseException();
			e.setErrorCode("E020001");
			throw e;
		}

		// 获得业务对象
		UserBO bo = (UserBO) getBean("user_bo");

		UserForm bean = (UserForm) form;

		UserVO vo = new UserVO();

		vo.setUser_id(bean.getUser_id());

		//调用业务对象的retrieve（）方法,获取用户状态
		vo = (UserVO) bo.retrieve(vo, user);

		request.getSession().setAttribute("user_org", vo.getAmsd_store());

		if ((vo.getStatus()).equals("2")) {
			//如果用户已经停用,提示,返回列表
			return forwardSuccessPage(request, mapping, "用户已停用！", "User.do?act=list");

		} else if ((vo.getStatus()).equals("0")) {
			//如果用户已作废,提示不能再停用,返回列表
			return forwardSuccessPage(request, mapping, "用户已作废,不能再停用！", "User.do?act=list");
		} else {
			//否则,停用用户,然后返回列表

			/**设置更新日期为今天*/

			vo.setSt_chg_dt(DateUtil.getDTStr());
			SysLog.debug("停用更新日期为：" + vo.getSt_chg_dt());

			/**设置管理员代码 */
			vo.setAdmin_id(user.getUserID());

			//调用业务对象的disable（）方法
			bo.disable(vo, user);

			//传递数据保存结果
			//request.getSession().setAttribute("success", "y");

			//执行页面跳转		
			return forwardSuccessPage(request, mapping, "用户停用成功！", "User.do?act=list");

		}
	}
	public ActionForward exclude(ActionMapping mapping, BaseForm form, HttpServletRequest request, HttpServletResponse response, UserContext user) throws Exception {

		//进行权限校验
		if (!OpMap.checkRoleAuth(user.getRoleID(), "user_disable")) {

			BaseException e = new BaseException();
			e.setErrorCode("E020001");
			throw e;
		}

		// 获得业务对象
		UserBO bo = (UserBO) getBean("user_bo");

		UserForm bean = (UserForm) form;

		UserVO vo = new UserVO();

		vo.setUser_id(bean.getUser_id());

		//调用业务对象的retrieve（）方法,获取用户状态
		vo = (UserVO) bo.retrieve(vo, user);

		request.getSession().setAttribute("user_org", vo.getAmsd_store());

		if (!(vo.getStatus()).equals("1") && !vo.getStatus().equals("4")) {
			//如果用户已经停用,提示,返回列表
			return forwardSuccessPage(request, mapping, "用户已停用！请先启用用户");

		}  else {
			//否则,停用用户,然后返回列表

			/**设置更新日期为今天*/

			vo.setSt_chg_dt(DateUtil.getDTStr());

			/**设置管理员代码 */
			vo.setAdmin_id(user.getUserID());

			//调用业务对象的disable（）方法
			bo.exclude(vo, user);

			//传递数据保存结果
			//request.getSession().setAttribute("success", "y");

			//执行页面跳转		
			return forwardSuccessPage(request, mapping, "设置不导出用户成功！", "User.do?act=list");

		}
	}
	/** 
	 * 查询用户列表 
	 */
	public ActionForward listpop(ActionMapping mapping, BaseForm form, HttpServletRequest request, HttpServletResponse response, UserContext user) throws Exception {

		PageObject page = new PageObject();
		String pageNo = request.getParameter("pageNO");
		String requery = request.getParameter("requery");

		String orgID = null;

		if (pageNo == null || (requery != null && requery.trim().equals("y"))) {
			page.setCurPage(1);
		} else {
			page.setCurPage(Integer.parseInt(pageNo));
		}

		if (requery == null || requery.trim().length() < 1) {
			orgID = user.getOrgID();
			((UserForm) form).setAmsd_store_f(orgID);
		}

		//添加查询过滤条件 
		page.addFilter("amsd_store_f", ((UserForm) form).getAmsd_store_f());
		page.addFilter("user_name_f", ((UserForm) form).getUser_name_f());

		//获得业务对象 
		UserBO bo = (UserBO) getBean("user_bo");

		//调用业务对象的list（）方法,返回列表结果 
		bo.listpop(page, user);

		//将结果对象写到request对象中 
		request.setAttribute("pageResult", page);

		//执行页面跳转 
		return mapping.findForward("listpop");
	}
	/** 
	 * 重置密码 
	 */

	public ActionForward setPwd(ActionMapping mapping, BaseForm form, HttpServletRequest request, HttpServletResponse response, UserContext user) throws Exception {

		//进行权限校验
		if (!OpMap.checkRoleAuth(user.getRoleID(), "reset_pwd")) {

			BaseException e = new BaseException();
			e.setErrorCode("E020001");
			throw e;
		}

		// 获得业务对象
		UserBO bo = (UserBO) getBean("user_bo");

		UserForm bean = (UserForm) form;

		if (bean.getUser_id().equals(user.getUserID())) {
			//如果用户已经启用,提示,返回列表
			return forwardError(request, mapping, "不能重置自己的密码！");
		}
		bo.resetPwd(bean.getUser_id(), user);

		//执行页面跳转		
		return forwardSuccessPage(request, mapping, "用户" + bean.getUser_name() + "密码已重置！", "User.do?act=list");

	}
}
