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
		UserDaoImpl userDao = UserDaoImpl.getInstance();  //we need to not make new dao objects all the time
		
		
		System.out.println("in RegisterController");
		
		String username=request.getParameter("preUser");
		String password=request.getParameter("prePass");
		String firstName=request.getParameter("first");
		String lastName=request.getParameter("last");
		String email=request.getParameter("email");
		String department=request.getParameter("dept");
		String supervisor=request.getParameter("supervisor");
		String job = request.getParameter("job");
		// Add input validation
		
		System.out.println(username);
		System.out.println(password);
		System.out.println(firstName);
		System.out.println(lastName);
		System.out.println(email);
		System.out.println(department);
		System.out.println(supervisor);
		System.out.println(job);
		ReimbursementUser user = new ReimbursementUser();
		user.setUsername(username);
		user.setPassword(password);
		user.setEmail(email);
		user.setDeptID(Integer.valueOf(department));
		user.setFirstname(firstName);
		user.setLastname(lastName);
		user.setDsID(Integer.valueOf(supervisor));
		user.setJobID(Integer.valueOf(job));
		
		String hashedpw = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
		user.setPassword(hashedpw);
		user = userDao.insertUser(user);
		System.out.println(user);
		if(user!=null) {
			//return true;
			//WERE GOOD
		}
		
		return "/client/html/Welcome.html";
	}
}
