package com.revature.daoImplementation;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.mindrot.jbcrypt.BCrypt;

import com.revature.models.Application;
import com.revature.models.ReimbursementUser;

class UserDaoImplTest {
	UserDaoImpl userDao = UserDaoImpl.getInstance();
	ApplicationDaoImpl appDao = ApplicationDaoImpl.getInstance();
	
	void testUserInsertion() {
		ReimbursementUser user = new ReimbursementUser();
		user.setDeptID(21); //hardcoded department into database
		user.setFirstname("Olenna");
		user.setLastname("Tyrell");
		user.setDsID(99999);
		user.setEmail("queenofthorns@mailinator.com");
		user.setUsername("olenna");
		String hashedpw = BCrypt.hashpw("olenna", BCrypt.gensalt());
		user.setPassword(hashedpw);
		user.setJobID(2);
		user = userDao.insertUser(user);
		System.out.println(user);
		user = userDao.getUser(user.getUserID());
		assert(user.getUserID()>=1);
	}
	@Test
	void testUserReading() {
		ReimbursementUser user = userDao.getUser("olenna");
		System.out.println(user);
		assert(user.getUsername().equals("olenna"));
	}
	@Test
	void testUserReadingByUserObject() {
		ReimbursementUser user = new ReimbursementUser();// userDao.getUser("bobbyb");
		user.setUsername("bobbyb");
		user = userDao.getUser(user);
		System.out.println(user);
		assert(user.getUsername().equals("bobbyb"));
	}
	@Test
	void testUserReadingByID() {
		ReimbursementUser user = new ReimbursementUser();// userDao.getUser("bobbyb");
		user.setUsername("bobbyb");
		user = userDao.getUser(user);
		user = userDao.getUser(user.getUserID());
		System.out.println(user);
		assert(user.getUsername().equals("bobbyb"));
	}
	@Test
	void testUserReadingByApplication() {
		Application app = appDao.getUserApplications(userDao.getUser("bobbyb")).get(0);
		ReimbursementUser user = userDao.getApplicant(app);
		System.out.println(user);
		assert(user!=null);
	}
	
	@Test
	void testGetAllUsers() {
		ArrayList<ReimbursementUser> users = new ArrayList<>();
		users = userDao.getAllUsers();
		users.forEach(e->{
			System.out.println(e);
		});
		System.out.println("All Users: " + users.size());
		assert(users.size()>0);
	}

}
