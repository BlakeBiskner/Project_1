package com.revature.controller;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Request Mapping
 * 
 * @author Blake Biskner
 * @version 1.0
 * 
 */

public class RequestHelper {

	private static final String BASE_URI = "/inTuition/client/html/";
	private static final String DEFAULT_URI = "/client/html/Welcome.html";

	public static String process(HttpServletRequest request, HttpServletResponse response) {
		switch (request.getRequestURI()) {
		case (BASE_URI + "Login.do"):
			System.out.println("in Login.do case");
			return LoginController.Login(request);
		case (BASE_URI + "Register.do"):
			System.out.println("in Register.do case");
			if (request.getMethod().equals("GET")) {
				return DEFAULT_URI;
			}
			return RegisterController.Register(request);
		case (BASE_URI + "HomeJSON.do"):
			System.out.println("in HomeJSON.do case");
			return HomeController.HomeJSON(request, response);

		// New Application
		case (BASE_URI + "NewForm.do"):
			System.out.println("in NewForm.do case");
			return NewFormController.NewForm(request);
		case (BASE_URI + "NewFormJSON.do"):
			System.out.println("in NewFormJSON.do case");
			return FormController.NewFormJSON(request, response);
		case (BASE_URI + "Apply.do"):
			System.out.println("in Apply/do case");
			if (request.getMethod().equals("GET")) {
				return DEFAULT_URI;
			}
			return ApplicationController.Apply(request);
		case (BASE_URI + "WelcomeJSON.do"):
			System.out.println("In Welcome.do");
			return WelcomeController.welcomeJSON(request, response);

		// Application Approval
		case (BASE_URI + "Approval.do"):
			System.out.println("In approval.do");
			if (request.getMethod().equals("GET")) {
				return DEFAULT_URI;
			}
			return ApprovalController.approve(request, response, true);
		case (BASE_URI + "Deny.do"):
			System.out.println("In deny.do");
			if (request.getMethod().equals("GET")) {
				return DEFAULT_URI;
			}
			return ApprovalController.approve(request, response, false);

		// Grade Submission
		case (BASE_URI + "Grade.do"):
			System.out.println("In Grade.do");
			if (request.getMethod().equals("GET")) {
				return DEFAULT_URI;
			}
			return GradeController.grade(request, response);
		case(BASE_URI + "Download.do"):
			System.out.println("In Download.do");
			return DownloadController.downloadFile(request, response);
		default:
			return DEFAULT_URI;
		}
	}

}
