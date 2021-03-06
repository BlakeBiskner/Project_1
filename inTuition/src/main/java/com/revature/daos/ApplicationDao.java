package com.revature.daos;

import java.sql.Connection;
import java.util.ArrayList;

import com.revature.models.Application;
import com.revature.models.ReimbursementUser;

public interface ApplicationDao {
	//CREATE
	public Application insertApplication(Application app);
	
	
	//READ
	
	public ArrayList<Application> getUserApplications(ReimbursementUser user);
	public ArrayList<Application> getApplicationsToReview(ReimbursementUser user);
	
	//UPDATE
	public Application updateApplication(Application app);


	public Application updateApplicationAfterApproval(Application app, Connection con);


	public ArrayList<Application> getApplicationsForBencoToView();




	
	
	//Delete
	
}
