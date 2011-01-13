package com.eis.servlet;

import java.io.IOException;
import javax.servlet.Servlet;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.eis.cache.*;
import com.eis.portal.*;
import com.eis.util.*;

import java.io.*;

/**
 * @version 	1.0
 * @author
 */
public class SysInitServlet
	extends HttpServlet{
		
	/**
	* @see javax.servlet.http.HttpServlet#void (javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	*  添加帐务调整模块：行动码字典、帐务调整权限对应表、配置信息加载功能  ljp 2009/08/27
	*  添加部分：PropertiesCatche；AcctsAdjustCatche；AcctsAdjustRightCatche
	*/
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
		SingleDicMap.reloadDicMap();
		ReDefSDicMap.reloadDicMap();
		OpMap.reloadDicMap();
//		OrgMap.loadDicMap();
		MLDicMap.reloadDicMap();
		RedefMDicMap.loadDicMap();
		ErrorCodeMap.reloadDicMap();
		
		Portal.init();
		
		resp.setContentType("text/html; charset=GBK");
		PrintWriter out = resp.getWriter();		
		out.println("<script language=javascript>");
		out.println("alert('应用数据缓存装载成功！');");
		out.println("</script>");
		out.println("<script language=javascript>");
		out.flush();
		out.close();
		

	}

	/**
	* @see javax.servlet.http.HttpServlet#void (javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	*/
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {

	}

	/**
	* @see javax.servlet.GenericServlet#void ()
	*  添加帐务调整模块：行动码字典、帐务调整权限对应表、配置信息加载功能  ljp 2009/08/27
	*  添加部分：PropertiesCatche；AcctsAdjustCatche；AcctsAdjustRightCatche
	*/
	public void init() throws ServletException {

		super.init();
		SingleDicMap.loadDicMap();
		ReDefSDicMap.loadDicMap();
		MLDicMap.loadDicMap();
		RedefMDicMap.loadDicMap();
		OpMap.loadDicMap();
//		OrgMap.loadDicMap();
		ErrorCodeMap.loadDicMap();
		Portal.init();
		

	}

}
