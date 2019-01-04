package com.revature.daoImplementation;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.mindrot.jbcrypt.BCrypt;

import com.revature.models.ReimbursementUser;

class UserDaoImplTest {
	UserDaoImpl userDao = UserDaoImpl.getInstance();

	
	void testUserInsertion() {
		ReimbursementUser user = new ReimbursementUser();
		user.setDeptID(21); //hardcoded department into database
		user.setFirstname("Cercei");
		user.setLastname("Lannister");
		user.setDsID(99999);
		user.setEmail("winordie@mailinator.com");
		user.setUsername("cercei");
		String hashedpw = BCrypt.hashpw("cercei", BCrypt.gensalt());
		user.setPassword(hashedpw);
		user.setJobID(2);
		user = userDao.insertUser(user);
		System.out.println(user.getUserID());
		assert(user.getUserID()>=1);
		assert(true);
	}
	
	@Test
	void testUserReading() {
		ReimbursementUser user = userDao.getUser("bobbyb");
		System.out.println(user);
		assert(user.getUsername().equals("bobbyb"));
	}
	@Test
	void testUserReadingByUserObject() {
		ReimbursementUser user = new ReimbursementUser();// userDao.getUser("bobbyb");
		user.setUsername("bobbyb");
		user = userDao.getUser(user);
		System.out.println(user);
		assert(user.getUsername().equals("bobbyb"));
	}

}
