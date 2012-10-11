package com.blue.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.blue.start.Main;

public class MainServlet extends HttpServlet {
	@Override
	public void init() throws ServletException {
		try{
			Main.start();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
