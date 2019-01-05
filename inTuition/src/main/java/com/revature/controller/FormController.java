package com.revature.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.EventGradeFormat;
import com.revature.models.EventType;
import com.revature.models.FormSession;
import com.revature.models.ReimbursementUser;

public class FormController {
	
	public static String NewFormJSON(HttpServletRequest request, HttpServletResponse response) {
		ArrayList<EventGradeFormat> eventGradeFormat= (ArrayList<EventGradeFormat>)request.getSession().getAttribute("EventGradeFormat");
		ArrayList<EventType> eventType=(ArrayList<EventType>)request.getSession().getAttribute("EventType");
		// From initial set attribute in login
		ReimbursementUser user=(ReimbursementUser)request.getSession().getAttribute("User");
		FormSession session=new FormSession(user,eventType,eventGradeFormat);
		
		try {
			System.out.println("in FormController with "+session.toString());
			response.getWriter().write(new ObjectMapper().writeValueAsString(session));
		} catch(JsonProcessingException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
