package com.blue.servlet;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.blue.common.User;
import com.blue.monitor.RolesMonitor;
import com.blue.tools.PageService;
import com.blue.tools.login.VeryCD;

public class PortalServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("act") == null) {
			list(request, response);
		} else if ("login".equals(request.getParameter("act"))) {
			login(request, response);
		}
	}

	private void login(HttpServletRequest request, HttpServletResponse response) {
		RolesMonitor rm = RolesMonitor.getInstance();
		String username = request.getParameter("username");
		User user = rm.getUser(username);
		String url = "http://game.verycd.com/hero/";
		String page = PageService.getPage(url, null);
		OutputStream pw = null;
		try {
			pw = response.getOutputStream();
			response.setContentType("text/html;charset=utf-8");
			response.setCharacterEncoding("utf-8");
		
			pw.write(autoLogin(user, page).toString().getBytes("utf-8"));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pw != null) {
				try {
					pw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	private StringBuffer autoLogin(User user, String page) {
		StringBuffer sb = new StringBuffer();

		int bodyIndex = page.indexOf("</body>");
		if (bodyIndex == -1) {
			return sb.append(page);
		}
		sb.append(page.substring(0, bodyIndex));
		sb.append("<script>");
		sb.append("document.getElementsByName(\"username\")[0].value=\"" + user.getUserName() + "\";");
		sb.append("document.getElementsByName(\"password\")[0].value=\"" + user.getPassword() + "\";");
//		sb.append("document.forms[0].submit();");
//		sb.append("document.forms[0].action=\"http://secure.verycd.com/signin\";");
//		sb.append("document.getElementById(\"login_button\").click();");
		sb.append("</script>");
		sb.append(page.substring(bodyIndex));
		return sb;
	}

	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RolesMonitor rm = RolesMonitor.getInstance();
		List<User> l = rm.getUsers();
		request.setAttribute("users", l);
		request.getRequestDispatcher("list.jsp").forward(request, response);
	}

}
