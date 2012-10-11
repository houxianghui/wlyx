package com.blue.servlet.start;

import java.net.URL;

import org.mortbay.jetty.Server;
import org.mortbay.jetty.servlet.Context;
import org.mortbay.jetty.servlet.ServletHolder;
import org.mortbay.jetty.webapp.WebAppContext;

import com.blue.servlet.PortalServlet;

public class JettyServer {
	private static void start(String appdir)throws Exception{
		final String WEBAPPDIR = appdir;
		 
		final Server server = new Server(8080);
		 
		 
		final String CONTEXTPATH = "/";
		 
		final URL warUrl = JettyServer.class.getClassLoader().getResource(WEBAPPDIR);
		final String warUrlString = warUrl.toExternalForm();
		server.setHandler(new WebAppContext(warUrlString, CONTEXTPATH));
		 
		final Context context = new Context(server, "/servlets", Context.SESSIONS);
		context.addServlet(new ServletHolder(new PortalServlet()), "/list");
		 
		server.start();
		server.join();
	}
	public static void main(String[] args) throws Exception{
		com.blue.frame.TestSystemTray.startWithFrame();
		start(".");
	}
}
