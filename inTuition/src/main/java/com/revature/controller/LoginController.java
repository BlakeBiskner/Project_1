package com.revature.controller;

import javax.servlet.http.HttpServletRequest;

import org.mindrot.jbcrypt.BCrypt;

import com.revature.daoImplementation.UserDaoImpl;
import com.revature.models.ReimbursementUser;
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
		
//		System.out.println(username);
//		System.out.println(password);
////		
		UserDaoImpl userDao = UserDaoImpl.getInstance();
		ReimbursementUser user = userDao.getUser(username);
		if(user==null) {
			//This happens when a user tries to login with an invalid username
			return "/client/html/Welcome.html";
		}
		boolean pwMatch = BCrypt.checkpw(password, user.getPassword());
		if(pwMatch) {
			//This means the user put in valid username and password
			return "/client/html/Home.html";
		}
		//The user put in a valid username, but an invalid password
		return "/client/html/Welcome.html";
	}

}
