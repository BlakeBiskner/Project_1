package com.revature.controller;

import javax.servlet.http.HttpServletRequest;
/**
 * Login Controller
 * @author Blake Biskner
 * @version 1.0
 * 
 */

public class LoginController {
	
	public static String Login(HttpServletRequest request) {
		String username=request.getParameter("user");
		String password=request.getParameter("pass");
		
		System.out.println(username);
		System.out.println(password);
		return "/client/html/Welcome.html";
	}

}
