package com.revature.controller;

import javax.servlet.http.HttpServletRequest;

/**
 * Register Controller
 * 
 * @author Blake Biskner
 * @version 1.0
 * 
 */

public class RegisterController {
	
	public static String Register(HttpServletRequest request) {
		// Obtain data from form
		System.out.println("in RegisterController");
		
		String username=request.getParameter("preUser");
		String password=request.getParameter("prePass");
		String firstName=request.getParameter("first");
		String lastName=request.getParameter("last");
		String email=request.getParameter("email");
		String department=request.getParameter("dept");
		String supervisor=request.getParameter("supervisor");
		
		// Add input validation
		
		System.out.println(username);
		System.out.println(password);
		System.out.println(firstName);
		System.out.println(lastName);
		System.out.println(email);
		System.out.println(department);
		System.out.println(supervisor);
		
		return "/client/html/Welcome.html";
	}
}
