package com.blue.servlet.start;

import java.net.URL;

import org.mortbay.jetty.Server;
import org.mortbay.jetty.webapp.WebAppContext;

import com.blue.start.Start;
import com.blue.tools.Tools;

public class JettyServerDev implements Start{
	public void run() throws Exception {
		//�һ���ʼ
		Tools.start();
		//��ط���������
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
		final JettyServerDev server = new JettyServerDev();
//		com.blue.frame.TestSystemTray.startWithFrame(server);
		server.run();
	}
}
