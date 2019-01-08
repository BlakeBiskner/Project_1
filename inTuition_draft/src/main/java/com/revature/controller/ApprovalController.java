package com.revature.controller;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.daoImplementation.ApplicationApprovalDaoImpl;
import com.revature.daoImplementation.ApplicationDaoImpl;
import com.revature.daoImplementation.UserDaoImpl;
import com.revature.models.Application;
import com.revature.models.ApplicationApproval;
import com.revature.models.ReimbursementUser;

public class ApprovalController {

	public static String approve(HttpServletRequest request, HttpServletResponse response, boolean approved) {
		System.out.println("in Approval Controller");
		UserDaoImpl userDao = UserDaoImpl.getInstance();
		ApplicationDaoImpl appDao = ApplicationDaoImpl.getInstance();
		ApplicationApprovalDaoImpl approvalDao = ApplicationApprovalDaoImpl.getInstance();
		ReimbursementUser user=(ReimbursementUser)request.getSession().getAttribute("User");
		ApplicationApproval approval = new ApplicationApproval();
		Application app;
		double newReimbursementAmount = 0; //THE BENCO CAN CHANGE THE AMOUNT BEING REIMBURSED - THIS NEW (OR OLD) AMOUNT IS STORED HERE.
		//This app variable should store the application that is being approved.
		
		Enumeration<String> params = request.getParameterNames();
		
		while(params.hasMoreElements()) {
			System.out.println(params.nextElement());
		}
		
		
		int appID = Integer.parseInt(request.getParameter("approve"));
		app = appDao.getApplication(appID);
		System.out.println(app);
		//app.setApplicationID(applicationID);
		ReimbursementUser applicant = userDao.getApplicant(app);
		System.out.println(applicant);
		
		/*
		 * We check to see if the application is being approved as a department head. 
		 * We check if it is a dept head first before direct supervisor so that we an
		 * skip direct supervisor approval if the direct supervisor is the department head.
		 */
		if(!approved) {
			app.setStatus("Denied");
		}
		else if(applicant.getDeptHeadID()==user.getUserID()) {  //check if approver is dept head
			app.setStatus("Approved by Department Head");
			app.setNextApproverID(null);
		}
		else if(applicant.getDsID()==user.getUserID()) {
			//The application is being approved as a direct supervisor
			app.setStatus("Approved by Direct Supervisor");
			app.setNextApproverID(applicant.getDeptHeadID());
		}
		else if(user.getJob().equals("Benefits Coordinator")) {
			//The app is being approved as a benco
			app.setStatus("Approved");
			app.setReimbursementAmount(newReimbursementAmount);
		}
		
		approval.setAppID(appID);
		approval.setApproverID(user.getUserID());
		approval.setApproval(approved);
		//SET APPLICATION APPROVAL VALUES HERE
		//SUCH AS APPLICATION ID, APPROVER ID, REASONING, WHETHER IT WAS APPROVED OR DENIED
		System.out.println(approval);
		approval = approvalDao.insertApproval(approval,app);
		if(approval!=null) {
			System.out.println("good stuff");
			//everything went according 
		}
		System.out.println(approval);
		
		
		return "/client/html/Home.html";
	}
}
