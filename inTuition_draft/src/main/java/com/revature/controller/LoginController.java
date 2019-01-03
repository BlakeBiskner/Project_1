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
	
	private static final String LOGIN_SUCCESS="/client/html/Home.html";
	private static final String LOGIN_FAILURE="/client/html/WelcomeAlert.html";
	
	public static String Login(HttpServletRequest request) {
		String username=request.getParameter("user");
		String password=request.getParameter("pass");
		
		System.out.println(username);
		System.out.println(password);
		
		UserDaoImpl userDao = UserDaoImpl.getInstance();//need to not do this everywhere
		ReimbursementUser actualUser = userDao.getUser(username);
		boolean pwMatch = BCrypt.checkpw(password, actualUser.getPassword());
		if(pwMatch) {
			request.getSession().setAttribute("User",actualUser);
			return LOGIN_SUCCESS;
		} else {
			return LOGIN_FAILURE;
		}
	}
}
