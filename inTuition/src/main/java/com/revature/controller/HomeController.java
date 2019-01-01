package com.revature.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
		try {
			System.out.println("in HomeController with User" +user.toString());
			response.getWriter().write(new ObjectMapper().writeValueAsString(user));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}
}
