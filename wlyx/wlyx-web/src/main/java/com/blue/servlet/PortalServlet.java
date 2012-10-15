package com.blue.servlet;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.blue.common.Portal;
import com.blue.common.User;
import com.blue.monitor.PackageItemMonitor;
import com.blue.monitor.RolesMonitor;
import com.blue.servlet.pojo.FindItemResult;
import com.blue.start.FindItem;
import com.blue.tools.Item;
import com.blue.tools.ItemMerge;
import com.blue.tools.PageService;
import com.blue.tools.Tools;
import com.blue.tools.login.VeryCD;

public class PortalServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("act") == null) {
			list(request, response);
		} else if ("login".equals(request.getParameter("act"))) {
			login(request, response);
		}else if("find".equals(request.getParameter("act"))){
			find(request, response);
		}else if("stopTrain".equals(request.getParameter("act"))){
			stopTrain(request, response);
		}
	}

	private void login(HttpServletRequest request, HttpServletResponse response) {
		RolesMonitor rm = RolesMonitor.getInstance();
		String username = request.getParameter("username");
		User user = rm.getUser(username);
		String url = "http://game.verycd.com/hero/";
		String page = PageService.getPage(url, null,null);
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
	private void find(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
//		request.setCharacterEncoding("GBK");
		String item = request.getParameter("item");
		item = URLDecoder.decode(item,"UTF-8");
		
		final String[] regexs = item.split(" ");
		if(regexs.length == 0){
			return;
		}
		RolesMonitor rm = RolesMonitor.getInstance();
		List<User> users = rm.getUsers();
		final List<FindItemResult> result = Collections.synchronizedList(new ArrayList<FindItemResult>());
		List<Thread> threads = new ArrayList<Thread>();
		for(final User user:users){
			Thread t = new Thread(){
				@Override
				public void run() {
					if(user.getPackageItems() == null || user.getStockItems() == null || user.getTeamStockItems()==null){
						PackageItemMonitor.upadtePackage(user);
					}
					findInList(regexs, user, result, "包裹", user.getPackageItems());
					findInList(regexs, user, result, "仓库", user.getStockItems());
					findInList(regexs, user, result, "四海库房", user.getTeamStockItems());
				}
			};
			t.start();
			threads.add(t);
		}
		for(Thread t : threads){
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		request.setAttribute("result", result);
		request.getRequestDispatcher("find.jsp").forward(request, response);
	}
	private void findInList(String[] regexs,User user,List<FindItemResult> result,String position,List<Item> target){
		boolean find = true;
		if(target == null){
			return;
		}
		for(Item i :target){
			find = true;
			for(String s:regexs){
				if(i.getCNName().indexOf(s) < 0){
					find = false;
					break;
				}
			}
			if(find){
				FindItemResult f = new FindItemResult();
				f.setRoleName(user.getRoleName());
				f.setCount(i.getCount());
				f.setPosition(position);
				f.setItemName(i.getCNName());
				result.add(f);
			}
		}
	}
	private void stopTrain(HttpServletRequest request,HttpServletResponse response){
		String userName = request.getParameter("userName");
		try {
			userName = URLDecoder.decode(userName,"gb18030");
			RolesMonitor rm = RolesMonitor.getInstance();
			User user = rm.getUser(userName);
			String url = "modules/auto_combats.php?act=cancel";
			PageService.getPageWithCookie(user.getUrl()+url+Tools.getTimeStamp(true), user);
			url = "modules/warrior.php?act=hall&op=train&cancel=1";
			PageService.getPageWithCookie(user.getUrl()+url+Tools.getTimeStamp(true), user);
			Portal.setUserInfo(user);
			write(response,"stop train success");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	private void write(HttpServletResponse response, String s){
		PrintWriter pw  = null;
		try {
			pw = response.getWriter();
			pw.write(s);
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(pw != null){
				pw.close();
			}
		}
		
	}
}
