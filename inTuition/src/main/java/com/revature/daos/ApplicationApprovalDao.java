package com.revature.daos;

import java.util.ArrayList;

import com.revature.models.Application;
import com.revature.models.ApplicationApproval;

public interface ApplicationApprovalDao {

	public ApplicationApproval insertApproval(ApplicationApproval approval, Application app);

	public ArrayList<ApplicationApproval> getApplicationsApprovals(Application app);

	public ApplicationApproval nextApprovalNeeded(Application app);

	public ApplicationApproval updateApproval(ApplicationApproval approval);



}
