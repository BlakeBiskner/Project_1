package com.revature.controller;

import javax.servlet.http.HttpServletRequest;

import org.mindrot.jbcrypt.BCrypt;

import com.revature.daoImplementation.UserDaoImpl;
import com.revature.models.ReimbursementUser;

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
		
//		// Add input validation
//		
		System.out.println(username);
		System.out.println(password);
		System.out.println(firstName);
		System.out.println(lastName);
		System.out.println(email);
		System.out.println(department);
		System.out.println(supervisor);
//		
		UserDaoImpl userDao = UserDaoImpl.getInstance();  //we need to not make new dao objects all the time
		ReimbursementUser user = new ReimbursementUser(username,password,firstName, lastName, email,department,supervisor);

		
		String hashedpw = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
		user.setPassword(hashedpw);
		user = userDao.insertUser(user);
		if(user==null) {
			//This happens when for some reason the database is unable to insert this user into the table
			return "/client/html/Welcome.html";
		}

		//return false;
		//were  bad
		
		return "/client/html/Welcome.html";
	}
}
