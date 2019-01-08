package com.revature.daos;

import java.util.ArrayList;

import com.revature.models.Application;
import com.revature.models.ApplicationMaterial;
import com.revature.models.MaterialRequest;
import com.revature.models.ReimbursementUser;

public interface RequestDao {

	//CREATE
	public MaterialRequest insertRequest(MaterialRequest request);
	
	//READ
	public ArrayList<MaterialRequest> getApplicationRequests(Application app);
	
	public ArrayList<MaterialRequest> getUserRequests(ReimbursementUser requestee);
	
	
	//UPDATE
	
	public MaterialRequest updateRequest(MaterialRequest request, ApplicationMaterial mat);
	
}
