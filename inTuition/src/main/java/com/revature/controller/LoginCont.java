package com.revature.controller;

import org.mindrot.jbcrypt.BCrypt;

import com.revature.daoImplementation.UserDaoImpl;
import com.revature.models.ReimbursementUser;

public class LoginController {
	public static boolean login(ReimbursementUser user) {
		UserDaoImpl userDao = new UserDaoImpl();//need to not do this everywhere
		ReimbursementUser actualUser = userDao.getUser(user);
		boolean pwMatch = BCrypt.checkpw(user.getPassword(), actualUser.getPassword());
		if(pwMatch) {
			return true;
		}
		
		//String hashedpw = BCrypt.hashpw(password, BCrypt.gensalt());
		
		return false;
	}

}
