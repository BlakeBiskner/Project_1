package com.revature.controller;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.daoImplementation.ApplicationDaoImpl;
import com.revature.models.Application;

public class GradeController {
	
	public static String grade(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("In grade controller");
		
		String grade=request.getParameter("grade");
		System.out.println(grade);
		String appIdStr=request.getParameter("gradeId");
		System.out.println(appIdStr);
		
		// Troubleshoot
		Enumeration<String> paramNames = request.getParameterNames();
        while(paramNames.hasMoreElements()) {
            System.out.println(paramNames.nextElement());
            System.out.println(request.getParameter(paramNames.nextElement()));
        }
		int appId=Integer.parseInt(appIdStr);
		System.out.println(appId);
		
		ApplicationDaoImpl appDao=ApplicationDaoImpl.getInstance();
		
		Application app=appDao.getApplication(appId);
		app.setGrade(grade);
		app=appDao.updateApplication(app);
		if(app==null) {
			System.out.println("Wrong");
		}
		
		return "/client/html/Home.html";
	}
}
