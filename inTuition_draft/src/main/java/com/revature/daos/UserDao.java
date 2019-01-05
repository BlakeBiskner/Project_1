package com.revature.daos;

import com.revature.models.ReimbursementUser;

public interface UserDao {
	//CREATE
	public ReimbursementUser insertUser(ReimbursementUser user);
	
	//READ
	public ReimbursementUser getUser(ReimbursementUser user);
	public ReimbursementUser getUser(String username);
	public ReimbursementUser getUser(int id);
	
	//UPDATE
	
	
	//DELETE
	
}
