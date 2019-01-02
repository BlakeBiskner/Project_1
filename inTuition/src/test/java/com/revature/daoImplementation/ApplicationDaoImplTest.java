package com.revature.daoImplementation;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.revature.models.Application;
import com.revature.models.Event;
import com.revature.models.ReimbursementUser;

class ApplicationDaoImplTest {
	ApplicationDaoImpl appDao = ApplicationDaoImpl.getInstance();
	EventDaoImpl eventDao = EventDaoImpl.getInstance();
	EventGradeFormatDaoImpl egfDao = EventGradeFormatDaoImpl.getInstance();
	EventTypeDaoImpl etDao = EventTypeDaoImpl.getInstance();
	UserDaoImpl userDao = UserDaoImpl.getInstance();

	@Test
	void testInsertApplication() {
		ReimbursementUser user = userDao.getUser("bobbyb");
		Event event = eventDao.getEvent(83);
		Application app = new Application();
		app.setEvent(event);
		app.setUserID(user.getUserID());
		app.setComments("I need to be able to learn the economics behind leading a rebellion.");
		app.setNextApproverID(userDao.getUser("stanlee").getUserID());
		app.setTimeMissed(10);
		app = appDao.insertApplication(app);
		System.out.println(app);
		assert(app.getApplicationID()>=1);
		
		
	}
	@Test
	void testgetUserApplications() {
		ReimbursementUser user = userDao.getUser("bobbyb");
		ArrayList<Application> apps = appDao.getUserApplications(user);
		System.out.println(apps);
		assert(apps.size()>=1);
		
	}

}
