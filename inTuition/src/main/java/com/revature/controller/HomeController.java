package com.revature.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.daoImplementation.ApplicationDaoImpl;
import com.revature.daoImplementation.ApplicationMaterialDaoImpl;
import com.revature.daoImplementation.UserDaoImpl;
import com.revature.models.Application;
import com.revature.models.ApplicationMaterial;
import com.revature.models.FormSession;
import com.revature.models.ReimbursementUser;

/**
 * Home Controller
 * 
 * @author Blake Biskner
 * @version 1.0
 * 
 */

public class HomeController {

	public static String HomeJSON(HttpServletRequest request, HttpServletResponse response) {
		ReimbursementUser actualUser = (ReimbursementUser) request.getSession().getAttribute("User");
//		ArrayList<Application> apps=(ArrayList<Application>) request.getSession().getAttribute("UserApplications");
//		ArrayList<Application> reviewApps=(ArrayList<Application>) request.getSession().getAttribute("ReviewApps");
//		ArrayList<ReimbursementUser> reviewUserApps=(ArrayList<ReimbursementUser>) request.getSession().getAttribute("ReviewAppUsers");
//		
		// Check if user has any applications
		UserDaoImpl userDao = UserDaoImpl.getInstance();//need to not do this everywhere
		
		ApplicationDaoImpl appDao=ApplicationDaoImpl.getInstance();
		ApplicationMaterialDaoImpl appMatDao = ApplicationMaterialDaoImpl.getInstance();
		ArrayList<Application> userApps=appDao.getUserApplications(actualUser);
		if(userApps!=null) { // If has applications
			request.getSession().setAttribute("UserApplications",userApps);
			System.out.println("User apps"+userApps);
		}
		// Check if have any applications to review
		ArrayList<Application> reviewApps=appDao.getApplicationsToReview(actualUser);
		ArrayList<ReimbursementUser> reviewUsers = null;
		ArrayList<ApplicationMaterial> appMats = new ArrayList<>();
		System.out.println("Apps to review\n" +reviewApps);
		if(reviewApps!=null) {
			// For each application insert the application user at a corresponding index in another array
			reviewUsers=new ArrayList<ReimbursementUser>();
			for(Application app:reviewApps) {
				ReimbursementUser user=userDao.getApplicant(app);
				System.out.println(user);
				reviewUsers.add(user);
//				ArrayList<ApplicationMaterial> thisAppMats = appMatDao.getApplicationMaterials(app);
//				if(thisAppMats.size()>0) {
//					appMats.addAll(thisAppMats);
//				}
			}
			System.out.println("User to review\n");
			request.getSession().setAttribute("ReviewApps", reviewApps);
			request.getSession().setAttribute("ReviewAppUsers", reviewUsers);
		
		}
		System.out.println(reviewApps);
		System.out.println(userApps);
		FormSession session=new FormSession(actualUser,userApps,reviewApps, reviewUsers);
		session.setMats(appMats);
		
		try {
			System.out.println("in HomeController with session "+session.toString());
			System.out.println("in HomeController with User" +actualUser.toString());
			response.getWriter().write(new ObjectMapper().writeValueAsString(session));
//			response.getWriter().write(new ObjectMapper().writeValueAsString(user));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return "/client/html/Home.html";
	}
}
