package com.revature.daoImplementation;

import org.junit.jupiter.api.Test;

import com.revature.models.Application;
import com.revature.models.MaterialRequest;
import com.revature.models.ReimbursementUser;

class RequestDaoImplTest {
	ApplicationDaoImpl appDao = ApplicationDaoImpl.getInstance();
	RequestDaoImpl reqDao = RequestDaoImpl.getInstance();
	UserDaoImpl userDao = UserDaoImpl.getInstance();
	

	void test() {
		ReimbursementUser user = userDao.getUser("bobbyb");
		Application app = appDao.getApplicationsToReview(user).get(0);
		MaterialRequest req = new MaterialRequest();
		req.setAppID(app.getApplicationID());
		req.setRequest("I'm going to need the grade to approve.");
		req.setRequesteeID(app.getUserID());
		req.setRequesterID(user.getUserID());
		req = reqDao.insertRequest(req);
		System.out.println(req);
		assert(req!=null);
	}
	
	@Test
	void testGetRequestOfApplication() {
		ReimbursementUser user = userDao.getUser("bobbyb");
		Application app = appDao.getApplicationsToReview(user).get(0);
		MaterialRequest req = reqDao.getApplicationRequests(app).get(0);
		System.out.println(req);
		assert(req!=null);
	}
	
	@Test
	void testGetRequestsByRequestee () {
		ReimbursementUser user = userDao.getUser("cercei");
		MaterialRequest req = reqDao.getUserRequests(user).get(0);
		System.out.println(req);
		assert(req!=null);
		
	}

}
