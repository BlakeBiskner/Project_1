package com.revature.daoImplementation;

import org.junit.jupiter.api.Test;

import com.revature.models.Application;
import com.revature.models.ApplicationApproval;
import com.revature.models.ReimbursementUser;

class ApplicationApprovalDaoImplTest {
	ApplicationDaoImpl appDao = ApplicationDaoImpl.getInstance();
	UserDaoImpl userDao = UserDaoImpl.getInstance();
	ApplicationApprovalDaoImpl approvalDao = ApplicationApprovalDaoImpl.getInstance();

	
	void testDirectSupervisorApproval() {
		ReimbursementUser user = userDao.getUser("jaime");
		Application app = appDao.getApplicationsToReview(user).get(0);
		ReimbursementUser applicant = userDao.getApplicant(app);
		ApplicationApproval approval = new ApplicationApproval();
		approval.setApproval(true);
		approval.setAppID(app.getApplicationID());
		approval.setReasoning("Everything was good.");
		approval.setApproverID(user.getUserID());
		if(applicant.getDeptHeadID()==user.getUserID()) {  //check if approver is dept head
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
		}
		
		approval = approvalDao.insertApproval(approval, app);
		System.out.println(approval);
		app = appDao.getUserApplications(userDao.getUser(app.getUserID())).get(0);
		System.out.println(app);
		assert(!app.getStatus().equals("Submitted"));
	}

	
	void testDepartmentHeadApproval() {
		ReimbursementUser user = userDao.getUser("bobbyb");
		Application app = appDao.getApplicationsToReview(user).get(0);
		ReimbursementUser applicant = userDao.getApplicant(app);
		ApplicationApproval approval = new ApplicationApproval();
		approval.setApproval(true);
		approval.setAppID(app.getApplicationID());
		approval.setReasoning("Everything was good.");
		approval.setApproverID(user.getUserID());
		if(applicant.getDeptHeadID()==user.getUserID()) {  //check if approver is dept head
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
		}
		
		approval = approvalDao.insertApproval(approval, app);
		System.out.println(approval);
		app = appDao.getUserApplications(userDao.getUser(app.getUserID())).get(0);
		System.out.println(app);
		assert(app.getStatus().equals("Approved by Department Head"));
	}
	
	@Test
	void testBencoApproval() {
		ReimbursementUser user = userDao.getUser("littlefinger");
		Application app = appDao.getApplicationsToReview(user).get(0);
		ReimbursementUser applicant = userDao.getApplicant(app);
		ApplicationApproval approval = new ApplicationApproval();
		approval.setApproval(true);
		approval.setAppID(app.getApplicationID());
		approval.setReasoning("Everything was good.");
		approval.setApproverID(user.getUserID());
		if(applicant.getDeptHeadID()==user.getUserID()) {  //check if approver is dept head
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
			app.setReimbursementAmount(99);
		}
		
		approval = approvalDao.insertApproval(approval, app);
		System.out.println(approval);
		app = appDao.getUserApplications(userDao.getUser(app.getUserID())).get(0);
		System.out.println(app);
		assert(app.getStatus().equals("Approved"));
	}
}
