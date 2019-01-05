package com.revature.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.revature.daoImplementation.EventGradeFormatDaoImpl;
import com.revature.daoImplementation.EventTypeDaoImpl;
import com.revature.daoImplementation.UserDaoImpl;
import com.revature.models.EventGradeFormat;
import com.revature.models.EventType;

public class NewFormController {
	
	private static final String FORM_URI="/client/html/ApplicationForm.html";
	
	public static String NewForm(HttpServletRequest request) {
		System.out.println("in NewFormController");
		// Instantiate DAOImpls
		EventGradeFormatDaoImpl eventGradeFormatDAO=EventGradeFormatDaoImpl.getInstance();
		ArrayList<EventGradeFormat> eventGradeFormat=eventGradeFormatDAO.getGradeTypes();
		EventTypeDaoImpl eventTypeDAO=EventTypeDaoImpl.getInstance();
		ArrayList<EventType> eventType=eventTypeDAO.getTypes();
		
//      // Put these in submission of app I think
//		ApplicationDaoImpl application= ApplicationDaoImpl.getInstance();
//		ApplicationMaterialDaoImpl applicationMaterial=ApplicationMaterialDaoImpl.getInstance();
		
		// Store necessary info for session
		request.getSession().setAttribute("EventGradeFormat", eventGradeFormat);
		request.getSession().setAttribute("EventType", eventType);

		
		//		request.getSession().setAttribute("Application", application);
//		request.getSession().setAttribute("ApplicationMaterial", applicationMaterial);
		
		return FORM_URI;
	}
}
