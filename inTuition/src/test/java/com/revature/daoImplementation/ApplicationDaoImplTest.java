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

	
	void testInsertApplication() {
		ReimbursementUser user = userDao.getUser("bobbyb");
		Event event = eventDao.getEvent(103);
		Application app = new Application();
		app.setEvent(event);
		app.setUserID(user.getUserID());
		app.setComments("WAAAAAAGH");
		app.setNextApproverID(userDao.getUser("stanlee").getUserID());
		app.setTimeMissed(10);
		app.setGrade("P");
		app.setPassed(true);
		app = appDao.insertApplication(app);
		System.out.println(app);
		assert(app.getApplicationID()>=1);
		
		
	}
	@Test
	void testgetUserApplications() {
		ReimbursementUser user = userDao.getUser("bobbyb");
		//System.out.println(user);
		ArrayList<Application> apps = appDao.getUserApplications(user);
		apps.forEach(e->{
			System.out.println(e);
		});
		assert(apps.size()>=1);
		
	}
	@Test
	void testUpdateApplication() {
		ReimbursementUser user = userDao.getUser("bobbyb");
		//System.out.println(user);
		ArrayList<Application> apps = appDao.getUserApplications(user);
		Application app = apps.get(0);
		System.out.println(app);
		app.setStatusID(3);//means approved
		app.setGrade("100");
		app.setGradeComments("WOOT WOOT");
		app.setReimbursementAmount(300);
		app.setPassed(true);
		app = appDao.updateApplication(app);
		assert(app!=null);
	}

}
