package com.revature.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.mindrot.jbcrypt.BCrypt;

import com.revature.daoImplementation.ApplicationDaoImpl;
import com.revature.daoImplementation.UserDaoImpl;
import com.revature.models.Application;
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
		if(actualUser==null) {
			return LOGIN_FAILURE;
		}
		boolean pwMatch = BCrypt.checkpw(password, actualUser.getPassword());
		if(pwMatch) {
			// Check if user has any applications
			ApplicationDaoImpl appDao=ApplicationDaoImpl.getInstance();
			ArrayList<Application> userApps=appDao.getUserApplications(actualUser);
			if(appDao.getUserApplications(actualUser)!=null) { // If has applications
				request.getSession().setAttribute("UserApplications",userApps);
				System.out.println("User apps"+userApps);
			}
			request.getSession().setAttribute("User",actualUser);
			return LOGIN_SUCCESS;
		} else {
			return LOGIN_FAILURE;
		}
	}
}
