package com.revature.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import com.revature.daoImplementation.ApplicationDaoImpl;
import com.revature.daoImplementation.EventDaoImpl;
import com.revature.models.Application;
import com.revature.models.ApplicationMaterial;
import com.revature.models.Event;
import com.revature.models.EventGradeFormat;
import com.revature.models.EventType;
import com.revature.models.ReimbursementUser;

public class ApplicationController {
	
	public static String Apply(HttpServletRequest request) {
		System.out.println("in Application Controller");
//		Part fileField;
//		try {
//			fileField = request.getPart("eventRelatedFiles");
//			if(fileField!=null) {
//				String fileName = fileField.getSubmittedFileName();
//				ApplicationMaterial material = new ApplicationMaterial();
//				material.setFileName(fileName);
//			}
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (ServletException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		Enumeration<String> paramNames = request.getParameterNames();
        while(paramNames.hasMoreElements()) {
            String parName = paramNames.nextElement();
            System.out.println(parName);
            System.out.println(request.getParameter(parName));
        }
		
		
		ReimbursementUser user=(ReimbursementUser)request.getSession().getAttribute("User");
		ArrayList<EventGradeFormat> egf=(ArrayList<EventGradeFormat>)request.getSession().getAttribute("EventGradeFormat");
		ArrayList<EventType> et=(ArrayList<EventType>)request.getSession().getAttribute("EventType");
		EventDaoImpl eventDao=EventDaoImpl.getInstance();
		Event event=new Event();
		Timestamp endDate = null;
		Integer eventTimeMissed = null;
		int userId=user.getUserID();
		String eventName=request.getParameter("eventName");
		String eventType=request.getParameter("eventType");
		Double eventCost=Double.valueOf(request.getParameter("eventCost"));
		String eventStartDate=request.getParameter("eventStartDate");
		String eventEndDate=request.getParameter("eventEndDate");
		System.out.println(request.getParameter("eventTimeMissed").getClass());
		String etm = request.getParameter("eventTimeMissed");
		

		if(etm != null && !etm.equals("")) {
		
			eventTimeMissed = Integer.valueOf(etm);
		}
		String eventJustification=request.getParameter("eventJustification");
		String eventGradeFormat=request.getParameter("eventGradeFormat");
		String eventGradePassing=request.getParameter("eventGradePassing");
		
		
		String timeStamp=(eventStartDate+" 00:00:00");
		Timestamp date=Timestamp.valueOf(timeStamp);
		if(eventEndDate != null && !eventEndDate.equals("")) {
			timeStamp = (eventEndDate+" 00:00:00");
			endDate = Timestamp.valueOf(timeStamp);
		}
		
		
		// Set application fields
		Application app=new Application();
		
		app.setUserID(userId);
		app.setNextApproverID(user.getDsID());
		
		for(EventGradeFormat x:egf) {
			if(x.getFormat().equals(eventGradeFormat)) {
				app.setEventGradeFormatID(x.getId());
			}
		}
		
		for(EventType x:et) {
			if(x.getDesc().equals(eventType)) {
				app.setEventTypeID(x.getId());
				app.setTypeCoverage(x.getCoverage());
			}
		}
		app.setEventTitle(eventName);
		app.setCost(eventCost);
		
		if(eventTimeMissed != null) {
			app.setTimeMissed(eventTimeMissed);	
		}
		app.setJustification(eventJustification);
		app.setPassingGrade(eventGradePassing);
		app.setEventStartDate(date);
		app.setEventEndDate(endDate);
//		System.out.println(eventName);
//		System.out.println(eventType);
//		System.out.println(eventCost);
//		System.out.println(eventStartDate);
//		System.out.println(eventEndDate);
//		System.out.println(eventTimeMissed);
//		System.out.println(eventJustification);
//		System.out.println(eventGradeFormat);
//		System.out.println(eventGradePassing);
//		System.out.println(date);
//		
		
		

		double cost = eventCost;
		double coverage = (double)(app.getTypeCoverage());
		double reimbursementAmount = cost * (coverage/100);
		app.setReimbursementAmount(reimbursementAmount);
		
		
		
		ApplicationDaoImpl appDao=ApplicationDaoImpl.getInstance();
		app=appDao.insertApplication(app);
		System.out.println(app);
		
		return "/client/html/Home.html";
		
	}

}
