package com.blue.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.blue.common.User;
import com.blue.monitor.RolesMonitor;
import com.blue.tools.PageService;

public class PortalServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("act") == null){
			list(request, response);
		}else if("login".equals(request.getParameter("act"))){
			login(request,response);
		}
	}

	private void login(HttpServletRequest request, HttpServletResponse response) {
		RolesMonitor rm = RolesMonitor.getInstance();
		String username = request.getParameter("username");
		User user = rm.getUser(username);
		if(user != null && user.getCookie() != null){
			PrintWriter pw = null;
			try{
				pw = response.getWriter();
				response.setContentType("text/html");
				response.setCharacterEncoding("utf-8");
				pw.write(PageService.getPageWithCookie(user.getUrl(), user));
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				if(pw != null){
					pw.close();
				}
			}
		}
	}

	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RolesMonitor rm = RolesMonitor.getInstance();
		List<User> l = rm.getUsers();
		request.setAttribute("users", l);
		request.getRequestDispatcher("list.jsp").forward(request, response);
	}
		
}
