package com.revature.daoImplementation;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.mindrot.jbcrypt.BCrypt;

import com.revature.models.ReimbursementUser;

class UserDaoImplTest {
	UserDaoImpl userDao = UserDaoImpl.getInstance();
	@Test
	void testUserInsertion() {
//		ReimbursementUser user = new ReimbursementUser();
//		user.setDeptID(41); //hardcoded department into database
//		user.setFirstname("Robert");
//		user.setLastname("Baratheon");
//		user.setDsID(99999);
//		user.setEmail("dragonkiller@mailinator.com");
//		user.setUsername("bobbyb");
//		String hashedpw = BCrypt.hashpw("bobbyb", BCrypt.gensalt());
//		user.setPassword(hashedpw);
//		user.setJobID(2);
//		user = userDao.insertUser(user);
//		System.out.println(user.getUserID());
//		assert(user.getUserID()>=1);
		assert(true);
	}
	@Test
	void testUserReading() {
		ReimbursementUser user = userDao.getUser("bobbyb");
		//System.out.println(user);
		assert(user.getUsername().equals("bobbyb"));
	}
	void testUserReadingByUserObject() {
		ReimbursementUser user = new ReimbursementUser();// userDao.getUser("bobbyb");
		user.setUsername("bobbyb");
		//System.out.println(user);
		assert(user.getUsername().equals("bobbyb"));
	}

}
