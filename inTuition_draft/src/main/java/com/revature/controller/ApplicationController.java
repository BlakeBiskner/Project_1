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
		ArrayList<EventGradeFormat> egf=(ArrayList<EventGradeFormat>)request.getSession().getAttribute("EventGradeFormat");
		ArrayList<EventType> et=(ArrayList<EventType>)request.getSession().getAttribute("EventType");
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
		
		int egfId=0;
		int etId=0;
		
		String timeStamp=(eventStartDate+" 00:00:00");
		Timestamp date=Timestamp.valueOf(timeStamp);
		
		
		// Set application fields
		Application app=new Application();
		
		app.setUserID(userId);
		app.setNextApproverID(userId);
		
		for(EventGradeFormat x:egf) {
			if(x.getFormat().equals(eventGradeFormat)) {
				egfId=x.getId();
				app.setEventGradeFormatID(x.getId());
			}
		}
		
		for(EventType x:et) {
			if(x.getDesc().equals(eventType)) {
				etId=x.getId();
				app.setEventTypeID(x.getId());
			}
		}
		app.setEventTitle(eventName);
		app.setCost(eventCost);
		
		
		app.setTimeMissed(eventTimeMissed);
		app.setComments(eventJustification);
		app.setPassingGrade(eventGradePassing);
		app.setEventStartDate(date);
		System.out.println(eventName);
		System.out.println(eventType);
		System.out.println(eventCost);
		System.out.println(eventStartDate);
		System.out.println(eventEndDate);
		System.out.println(eventTimeMissed);
		System.out.println(eventJustification);
		System.out.println(eventGradeFormat);
		System.out.println(eventGradePassing);
		System.out.println(date);
		
		event.setPassingGrade(eventGradePassing);
		event.setTitle(eventName);
		event.setTypeDescription(eventType);
		event.setStartDate(date);
		event.setEventTypeID(etId);
		event.setEventGradeFormatID(egfId);
		
		event=eventDao.insertEvent(event);
		System.out.println(event);
		
		app.setEventID(event.getId());
		
		ApplicationDaoImpl appDao=ApplicationDaoImpl.getInstance();
		app=appDao.insertApplication(app);
		System.out.println(app);
		
		return "/client/html/Home.html";
		
	}

}
