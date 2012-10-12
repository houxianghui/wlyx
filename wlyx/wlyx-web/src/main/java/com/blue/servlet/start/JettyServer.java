package com.blue.servlet.start;

import java.net.URL;

import org.mortbay.jetty.Server;
import org.mortbay.jetty.webapp.WebAppContext;

import com.blue.start.Start;

public class JettyServer implements Start{
	public void run() throws Exception {
		String port = System.getProperty("server.port");
		start(Integer.parseInt(port));
	}
	private void start(int port)throws Exception{
		final String WEBAPPDIR = ".";
		 
		final Server server = new Server(port);
		 
		 
		final String CONTEXTPATH = "/";
		 
		final URL warUrl = JettyServer.class.getClassLoader().getResource(WEBAPPDIR);
		final String warUrlString = warUrl.toExternalForm();
		server.setHandler(new WebAppContext(warUrlString, CONTEXTPATH));
		 
		server.start();
		server.join();
	}
	public static void main(String[] args) throws Exception{
		final JettyServer server = new JettyServer();
		new Thread(){
			public void run() {
				try {
					server.run();
				} catch (Exception e) {
					e.printStackTrace();
				}
			};
		}.start();
		
		
	}
}
