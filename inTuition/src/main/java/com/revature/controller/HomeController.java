package com.revature.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Application;
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
		ReimbursementUser user = (ReimbursementUser) request.getSession().getAttribute("User");
		ArrayList<Application> apps=(ArrayList<Application>) request.getSession().getAttribute("UserApplications");
		System.out.println(apps);
		FormSession session=new FormSession(user,apps);
		try {
			System.out.println("in HomeController with session "+session.toString());
			System.out.println("in HomeController with User" +user.toString());
			response.getWriter().write(new ObjectMapper().writeValueAsString(session));
//			response.getWriter().write(new ObjectMapper().writeValueAsString(user));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}
}
