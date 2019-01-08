package com.revature.daos;

import java.util.ArrayList;

import com.revature.models.Application;
import com.revature.models.ReimbursementUser;

public interface UserDao {
	//CREATE
	public ReimbursementUser insertUser(ReimbursementUser user);
	
	//READ
	public ReimbursementUser getUser(ReimbursementUser user);
	public ReimbursementUser getApplicant(Application app);
	public ReimbursementUser getUser(String username);
	public ReimbursementUser getUser(int id);
	public ArrayList<ReimbursementUser> getAllUsers();
	
	//UPDATE
	
	
	//DELETE
	
}
