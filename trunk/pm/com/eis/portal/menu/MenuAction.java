/*********************************************************
 * File: MenuAction.java
 * 
 * Version 1.0
 * 
 * Date     2005-9-14
 * 
 * Author   Chen Rong
 * 
 ********************************************************/

package com.eis.portal.menu;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


import java.util.List;

import com.eis.base.*;
import com.eis.portal.UserContext;
import com.eis.base.PageObject;
import com.eis.util.*;
import com.eis.key.*;
import com.eis.portal.user.*;
import com.eis.cache.*;

/**
 * 说明：
 * 
 */
public class MenuAction extends BaseAction {

	/**
	 * 
	 */
	public MenuAction() {
		super();

	}

	/* （非 Javadoc）
	 * @see com.eis.base.BaseAction#process(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, com.eis.portal.UserContext)
	 */
	public ActionForward process(
		ActionMapping mapping,
		BaseForm form,
		HttpServletRequest request,
		HttpServletResponse response,
		UserContext user)
		throws Exception {

		String act = request.getParameter("act");

		if (act.equals("c")) { //增加菜单

			String step = request.getParameter("step");

			if (null == step) { //初始化阶段，跳转到增加菜单页面
				return mapping.findForward("new");

			} else //用户已提交菜单数据，执行数据保存
				return add(mapping, form, request, response, user);
		} else if (act.equals("u")) { //修改菜单

			String step = request.getParameter("step");
			
			if (null == step) { //初始化阶段，查询菜单信息并跳转到菜单修改页面
				
				return editInfo(mapping, form, request, response, user);
				
			} else //用户已提交菜单数据，执行数据保存
			
				return update(mapping, form, request, response, user);
				
		} else if (act.equals("r")) { //查询菜单明细信息			

			return retrieve(mapping, form, request, response, user);

		} else if (act.equals("lr")) { //查询菜单明细信息，菜单编码作为参数之一
			
			String menu_id = request.getParameter("menu_id");							

			return retrieve(menu_id, mapping, form, request, response, user);

		} else if (act.equals("d")) { //删除菜单

			return delete(mapping, form, request, response, user);

		} else if (act.equals("list")) { //返回菜单列表

			return list(mapping, form, request, response, user);

		} else if (act.equals("listWithPerm")) { //返回按用户的角色权限返回菜单列表

			return listWithPerm(mapping, form, request, response, user);
		
		}   else if (act.equals("blk")) { //到空白提示页

			return mapping.findForward("blank");

		}   else if (act.equals("bfl")) { //到首级菜单页，库中无此项记录
			//点击首级菜单时显示的页面，
			//包含：增加一级菜单按钮
			return mapping.findForward("beforeFirstLevel");

		}else
			return null;
		}

	/**  用于显示全部菜单列表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @param user
	 * @return 菜单列表
	 * @throws Exception
	 */
	public ActionForward list(
		ActionMapping mapping,
		BaseForm form,
		HttpServletRequest request,
		HttpServletResponse response,
		UserContext user)
		throws Exception {			
		

		//获得菜单管理业务对象
		BaseBO bo = (BaseBO)getBean("menu_bo");

		//调用菜单管理业务对象的list（user）方法,返回列表结果		
		List list=bo.list(user);

		//将结果对象写到request对象中		
		request.setAttribute("menuList", list);	
		

		//执行页面跳转
		return mapping.findForward("list");
	}
	
	
	/**  用于按用户权限返回菜单列表，不分页
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @param user
	 * @return 菜单列表
	 * @throws Exception
	 */
	public ActionForward listWithPerm(
		ActionMapping mapping,
		BaseForm form,
		HttpServletRequest request,
		HttpServletResponse response,
		UserContext user)
		throws Exception {				

		SysLog.debug("用户姓名为："+user.getUserName());		
		SysLog.debug("用户的角色为："+user.getRoleID());	

		//获得菜单业务对象
		MenuBO bo = (MenuBO)getBean("menu_bo");			

		//调用业务对象的listWithPerm（）方法,返回列表结果		
		List list=bo.listWithPerm(user);		
			

		//将结果对象写到request对象中		
		request.setAttribute("menuListPerm", list);			
		

		//执行页面跳转
		return mapping.findForward("listWithPerm");
	}



	/**查询菜单明细信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public ActionForward retrieve(
		ActionMapping mapping,
		BaseForm form,
		HttpServletRequest request,
		HttpServletResponse response,
		UserContext user)
		throws Exception {

		//获得业务对象
		BaseBO bo = (BaseBO)getBean("menu_bo");
		
		MenuForm bean = (MenuForm) form;

		MenuVO vo = new MenuVO();

		vo.setMenu_id(bean.getMenu_id());		
		

		//调用业务对象的retrieve（）方法,查询明细信息
		vo = (MenuVO) bo.retrieve(vo, user);

		//将结果对象写到form对象中
		copyProperties(bean,vo);

		//执行页面跳转
		return mapping.findForward("view");
	}
	
	
	/**查询菜单明细信息，菜单编码作为参数之一
		 * 
		 * @param menu_id 菜单编码
		 * @param mapping
		 * @param form
		 * @param request
		 * @param response
		 * @param user 用户身份信息
		 * @return
		 * @throws Exception
		 */
		public ActionForward retrieve(
			String menu_id,
			ActionMapping mapping,
			BaseForm form,
			HttpServletRequest request,
			HttpServletResponse response,
			UserContext user)
			throws Exception {				


			//获得菜单管理业务对象
			BaseBO bo = (BaseBO)getBean("menu_bo");
			
			//获得菜单管理数据传输对象
			BaseVO vo = new MenuVO();				
			
			String whereclause=" where a.MENU_ID='"+menu_id+"'";
	

			//调用菜单管理业务对象的retrieve（）方法,返回明细信息给BaseVO对象
			vo =  bo.retrieve(whereclause, user);
			
			//创建菜单管理Form对象MenuForm
			MenuForm bean = (MenuForm) form;

			//将结果BaseVO数据写到form对象中
			copyProperties(bean,vo);
			
			//设置刷新左侧菜单标志		
			//request.getSession().setAttribute("refresh", "y");
			

			//执行页面跳转
			return mapping.findForward("view");
		}
	
	

	/**增加菜单
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public ActionForward add(
		ActionMapping mapping,
		BaseForm form,
		HttpServletRequest request,
		HttpServletResponse response,
		UserContext user)
		throws Exception {
			
		//进行权限校验
		if (!OpMap.checkRoleAuth(user.getRoleID(), "menu_c")) {

			BaseException e = new BaseException();
			e.setErrorCode("E020001");
			throw e;
		}
		
		//获得业务对象
		MenuBO bo = (MenuBO)getBean("menu_bo");
		
		MenuForm bean = (MenuForm) form;

		MenuVO vo = new MenuVO();		
		
		//进行数据传输
		copyProperties(vo,bean);
		
		//将页节点标记设置为0，因当前暂不用其功能，一概设置为0
		vo.setMenu_mark("0");	
		
		//设置新增菜单的菜单编码
		//返回MENU_ID的最大值
		String maxKey=bo.retrieveMax(user);	
		
		String nextKey=null;
		
		if(maxKey=="0"||maxKey==null||maxKey.equals(" ")){
			
			nextKey="00000011";
			
		}else{
			
			//获取要新增菜单的MENU_ID值
			 nextKey=KeyGenerator.getNextKeyStr(maxKey,8);
			
		}		
		
		vo.setMenu_id(nextKey);

		//调用业务对象的add（）方法		
		bo.add(vo, user);
		
		
		//设置刷新左侧菜单标志		
		request.getSession().setAttribute("refresh", "y");

		//获取新增菜单编码
		String menu_id = vo.getMenu_id();
		//显示新增菜单的查看明细信息页面
		return retrieve(menu_id, mapping, form, request, response, user);

	}
	

	/**修改菜单，显示带有所选菜单信息的修改页面，
	 * 提交修改之前调用
	 * */
	public ActionForward editInfo(
		ActionMapping mapping,
		BaseForm form,
		HttpServletRequest request,
		HttpServletResponse response,
		UserContext user)
		throws Exception {

		//	  获得业务对象
		BaseBO bo = (BaseBO)getBean("menu_bo");
		
		MenuForm bean = (MenuForm) form;

		MenuVO vo = new MenuVO();

		vo.setMenu_id(bean.getMenu_id());

		//调用业务对象的retrieve（）方法,查询明细信息
		vo = (MenuVO) bo.retrieve(vo, user);

		//将结果对象写到form对象中		
		copyProperties(bean,vo);
	

		//	  执行页面跳转
		return mapping.findForward("edit");
	}
	

	/**修改菜单，提交修改后调用
	 * */
	public ActionForward update(
		ActionMapping mapping,
		BaseForm form,
		HttpServletRequest request,
		HttpServletResponse response,
		UserContext user)
		throws Exception {
			
		//进行权限校验
		if (!OpMap.checkRoleAuth(user.getRoleID(), "menu_u")) {

			BaseException e = new BaseException();
			e.setErrorCode("E020001");
			throw e;
		}

		//获得业务对象
		BaseBO bo = (BaseBO)getBean("menu_bo");

		MenuForm bean = (MenuForm) form;
		MenuVO vo = new MenuVO();

		//进行数据传输
		copyProperties(vo,bean);	
		
		
		
		//vo.setReg_dt(DateUtil.getDTStr());
		//vo.setUser_id("0001");
		vo.setMenu_mark("0");	

		//调用业务对象的update（）方法
		bo.update(vo, user);		


		//传递数据保存结果
		request.getSession().setAttribute("success", "y");		
		
		//设置刷新左侧菜单标志		
		request.getSession().setAttribute("refresh", "y");
		
		//执行页面跳转		
		return mapping.findForward("edit");
	}

	public ActionForward delete(
		ActionMapping mapping,
		BaseForm form,
		HttpServletRequest request,
		HttpServletResponse response,
		UserContext user)
		throws Exception {
			
		//进行权限校验
		if (!OpMap.checkRoleAuth(user.getRoleID(), "menu_d")) {

			BaseException e = new BaseException();
			e.setErrorCode("E020001");
			throw e;
		}

		//获得业务对象
		BaseBO bo = (BaseBO)getBean("menu_bo");
		
		
		MenuForm bean = (MenuForm) form;

		MenuVO vo = new MenuVO();

		vo.setMenu_id(bean.getMenu_id());

		//调用业务对象的delete（）方法,执行数据删除
		bo.delete(vo, user);
		
		//设置刷新左侧菜单标志		
		request.getSession().setAttribute("refresh", "y");

		//执行页面跳转,返回到列表页面
		
		return forwardSuccessPage(request,mapping,"数据删除成功！","Menu.do?act=blk");
		
	}	
	

}
