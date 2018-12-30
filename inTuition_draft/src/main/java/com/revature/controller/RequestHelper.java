package com.revature.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * Request Mapping
 * @author Blake Biskner
 * @version 1.0
 * 
 */

public class RequestHelper {
	
	private static final String BASE_URI="/inTuition_draft/client/html/";
	private static final String DEFAULT_URI="/client/html/Welcome.html";
	public static String process(HttpServletRequest request,HttpServletResponse response) {
		switch(request.getRequestURI()) {
		case (BASE_URI+"Login.do"):
			System.out.println("in Login.do case");
			return LoginController.Login(request);
		case (BASE_URI+"Register.do"):
			System.out.println("in Register.do case");
			return RegisterController.Register(request);
		case (BASE_URI+"HomeJSON.do"):
			System.out.println("in HomeJSON.do case");
			return HomeController.HomeJSON(request,response);
		default:
			return DEFAULT_URI;
		}
	}

}
