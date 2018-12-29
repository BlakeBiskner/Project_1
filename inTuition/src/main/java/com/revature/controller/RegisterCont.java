package com.revature.controller;

import org.mindrot.jbcrypt.BCrypt;

import com.revature.daoImplementation.UserDaoImpl;
import com.revature.models.ReimbursementUser;

public class RegisterCont {
	public static boolean register(ReimbursementUser user) {
		UserDaoImpl userDao = new UserDaoImpl();  //we need to not make new dao objects all the time
		String hashedpw = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
		user.setPassword(hashedpw);
		user = userDao.insertUser(user);
		System.out.println(user);
		if(user!=null) {
			return true;
		}
		return false;
	}

}
