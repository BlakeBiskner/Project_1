package com.revature.controller;

import java.util.ArrayList;
import java.util.Enumeration;

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
			if(userApps!=null) { // If has applications
				request.getSession().setAttribute("UserApplications",userApps);
				System.out.println("User apps"+userApps);
			}
			// Check if have any applications to review
			ArrayList<Application> reviewApps=appDao.getApplicationsToReview(actualUser);
			System.out.println("Apps to review\n" +reviewApps);
			if(reviewApps!=null) {
				// For each application insert the application user at a corresponding index in another array
				ArrayList<ReimbursementUser> reviewUsers=new ArrayList<ReimbursementUser>();
				for(Application app:reviewApps) {
					ReimbursementUser user=userDao.getApplicant(app);
					System.out.println(user);
					reviewUsers.add(user);
				}
				System.out.println("User to review\n");
				request.getSession().setAttribute("ReviewApps", reviewApps);
				request.getSession().setAttribute("ReviewAppUsers", reviewUsers);
			}
			request.getSession().setAttribute("User",actualUser);
			return LOGIN_SUCCESS;
		} else {
			return LOGIN_FAILURE;
		}
	}
}
