package com.blue.servlet.start;

import java.net.URL;

import org.mortbay.jetty.Server;
import org.mortbay.jetty.webapp.WebAppContext;

import com.blue.start.Start;
import com.blue.tools.Tools;

public class JettyServer implements Start{
	public void run() throws Exception {
		//挂机开始
		Tools.start();
		//监控服务器启动
		String port = System.getProperty("server.port");
		final String WEBAPPDIR = ".";
		 
		final Server server = new Server(Integer.parseInt(port));
		final String CONTEXTPATH = "/";
		 
		final URL warUrl = JettyServer.class.getClassLoader().getResource(WEBAPPDIR);
		final String warUrlString = warUrl.toExternalForm();
		server.setHandler(new WebAppContext(warUrlString, CONTEXTPATH));
		 
		server.start();
		server.join();
	}
	
	public static void main(String[] args) throws Exception{
		final JettyServer server = new JettyServer();
		com.blue.frame.TestSystemTray.startWithFrame(server);
	}
}
