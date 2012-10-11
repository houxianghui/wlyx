package com.blue.servlet.start;

import java.net.URL;

import org.mortbay.jetty.Server;
import org.mortbay.jetty.webapp.WebAppContext;

import com.blue.start.Start;

public class JettyServer implements Start{
	public JettyServer(String[] args) {
		if(args.length == 0){
			this.port = 8080;
		}else{
			try{
				this.port = Integer.parseInt(args[0]);
			}catch(Exception e){
				System.err.print("端口为数字 请输入正确的数字");
			}
			
		}
	}
	private int port;
	public void run() throws Exception {
		
		start(port);
	}
	private void start(int port)throws Exception{
		final String WEBAPPDIR = ".";
		 
		final Server server = new Server(8080);
		 
		 
		final String CONTEXTPATH = "/";
		 
		final URL warUrl = JettyServer.class.getClassLoader().getResource(WEBAPPDIR);
		final String warUrlString = warUrl.toExternalForm();
		server.setHandler(new WebAppContext(warUrlString, CONTEXTPATH));
		 
		server.start();
		server.join();
	}
	public static void main(String[] args) throws Exception{
		JettyServer server = new JettyServer(args);
		server.run();
//		TestSystemTray.startWithFrame(server);
	}
}
