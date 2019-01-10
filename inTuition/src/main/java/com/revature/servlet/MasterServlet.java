package com.revature.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.controller.RequestHelper;

/**
 * Master Servlet
 * @author Blake Biskner
 * @version 1.0
 * 
 */

@MultipartConfig
public class MasterServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		System.out.println("in doGet in servlet");
		String jsonStuff=RequestHelper.process(request, response);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		System.out.println("in doPost in servlet");
		System.out.println(request.getRequestURI());
		String targetUrl=RequestHelper.process(request, response);
		if(targetUrl!=null && !targetUrl.equals("download")) {
			request.getRequestDispatcher(targetUrl).forward(request,response);		
		}
	
	}
}
