package com.revature.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
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

public class MasterServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		System.out.println("in doPost in servlet");
		String targetUrl=RequestHelper.process(request, response);
		request.getRequestDispatcher(targetUrl).forward(request,response);
	}
}
