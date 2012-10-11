package com.blue.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.blue.common.User;
import com.blue.monitor.RolesMonitor;

public class PortalServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RolesMonitor rm = RolesMonitor.getInstance();
		List<User> l = rm.getUsers();
		request.setAttribute("users", l);
		request.getRequestDispatcher("list.jsp").forward(request, response);
	}
}
