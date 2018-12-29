package com.revature.testing;

import java.sql.Connection;
import java.sql.SQLException;

import com.revature.connection.ConnFactory;
import com.revature.controller.LoginController;
import com.revature.controller.RegisterController;
import com.revature.daoImplementation.UserDaoImpl;
import com.revature.models.ReimbursementUser;

public class TestDriver {
	public static void main(String[] args) throws SQLException {
		Connection conn = ConnFactory.getInstance().getConnection();
		
		UserDaoImpl userDao = new UserDaoImpl();
		String password = "Arya";
		ReimbursementUser ds = new ReimbursementUser();
		ds.setUsername("stanlee");
		ds = userDao.getUser(ds);
//		if (ds != null) {
//			System.out.println(ds);
//		}
		ReimbursementUser user = new ReimbursementUser();
		//user.setEmail("arya@email.com");
		//user.setFirstname("Arya");
	//	user.setLastname("Stark");
		user.setPassword(password);
		user.setUsername("noone");
		//user.setDirectSupervisor(ds);
		//System.out.println(user);
//		user = userDao.insertUser(user);
//		if(user!=null) {
//			System.out.println(user);
//		}
		//user = userDao.insertUser(user);
		//boolean success = RegisterController.register(user);
		//System.out.println(user);
		conn.close();
		boolean success = LoginController.login(user);
		System.out.println(success);
	}
}
