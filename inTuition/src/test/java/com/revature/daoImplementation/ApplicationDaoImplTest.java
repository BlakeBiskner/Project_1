package com.revature.daoImplementation;

import java.sql.Timestamp;
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
		Application app = new Application();
		Event event = new Event();
		event.setGradeFormat(egfDao.getGradeTypes().get(0));
		event.setEventType(etDao.getTypes().get(0));
		event.setPassingGrade("Pass");
		event.setStartDate(Timestamp.valueOf("2017-08-08 10:30:00"));
		event.setTitle("Kingdom Mismanagement Economics");
		event = eventDao.insertEvent(event);
		app.setEvent(event);
		app.setUserID(user.getUserID());
		app.setJustification("I need to be able to do the things.");
		app.setNextApproverID(user.getDsID());
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
	@Test
	void testGetApplicationsToReview() {
		ReimbursementUser user = userDao.getUser("bobbyb");
		System.out.println("Below is important.");
		ArrayList<Application> apps = appDao.getApplicationsToReview(user);
		apps.forEach(e->{
			System.out.println(e);
		});
		assert(apps.size()>0);
	}

}
