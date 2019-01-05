package com.revature.controller;

import java.sql.Timestamp;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.revature.daoImplementation.ApplicationDaoImpl;
import com.revature.daoImplementation.EventDaoImpl;
import com.revature.models.Application;
import com.revature.models.Event;
import com.revature.models.EventGradeFormat;
import com.revature.models.EventType;
import com.revature.models.ReimbursementUser;

public class ApplicationController {
	
	public static String Apply(HttpServletRequest request) {
		System.out.println("in Application Controller");
		ReimbursementUser user=(ReimbursementUser)request.getSession().getAttribute("User");
		ArrayList<EventGradeFormat> egfs=(ArrayList<EventGradeFormat>)request.getSession().getAttribute("EventGradeFormat");
		ArrayList<EventType> ets=(ArrayList<EventType>)request.getSession().getAttribute("EventType");
		EventDaoImpl eventDao=EventDaoImpl.getInstance();
		Event event=new Event();
		
		
		
		int userId=user.getUserID();
		String eventName=request.getParameter("eventName");
		String eventType=request.getParameter("eventType");
		Double eventCost=Double.valueOf(request.getParameter("eventCost"));
		String eventStartDate=request.getParameter("eventStartDate");
		String eventEndDate=request.getParameter("eventEndDate");
		int eventTimeMissed=Integer.valueOf(request.getParameter("eventTimeMissed"));
		String eventJustification=request.getParameter("eventJustification");
		String eventGradeFormat=request.getParameter("eventGradeFormat");
		String eventGradePassing=request.getParameter("eventGradePassing");
		
		String timeStamp=(eventStartDate+" 00:00:00");
		Timestamp startDate=Timestamp.valueOf(timeStamp);
		timeStamp = eventEndDate +" 00:00:00";
		Timestamp endDate = Timestamp.valueOf(timeStamp);
		// Set application fields
		Application app=new Application();
		app.setEventEndDate(endDate);
		app.setUserID(userId);
		app.setEventTitle(eventName);
		app.setCost(eventCost);
		app.setNextApproverID(user.getDsID());

		for(EventGradeFormat egf : egfs ) {
			if(egf.getFormat().equals(eventGradeFormat)) {
				app.setEventGradeFormatID(egf.getId());
			}
		}
		for(EventType et : ets) {
			if(et.getDesc().equals(eventType)) {
				app.setEventTypeID(et.getId());
			}
		}
		
		//check for no valid IDS for above
		
		app.setTimeMissed(eventTimeMissed);
		app.setJustification(eventJustification);
		app.setPassingGrade(eventGradePassing);
		app.setEventStartDate(startDate);
		System.out.println(eventName);
		System.out.println(eventType);
		System.out.println(eventCost);
		System.out.println(eventStartDate);
		System.out.println(eventEndDate);
		System.out.println(eventTimeMissed);
		System.out.println(eventJustification);
		System.out.println(eventGradeFormat);
		System.out.println(eventGradePassing);
		
		ApplicationDaoImpl appDao=ApplicationDaoImpl.getInstance();
		app=appDao.insertApplication(app);
		System.out.println(app);
		
		return "/client/html/Home.html";
		
	}

}
