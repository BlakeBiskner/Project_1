package com.revature.testing;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.revature.connection.ConnFactory;
import com.revature.daoImplementation.ApplicationDaoImpl;
import com.revature.daoImplementation.EventDaoImpl;
import com.revature.daoImplementation.EventGradeDaoImpl;
import com.revature.daoImplementation.EventGradeFormatDaoImpl;
import com.revature.daoImplementation.EventTypeDaoImpl;
import com.revature.daoImplementation.UserDaoImpl;
import com.revature.models.Application;
import com.revature.models.Event;
import com.revature.models.EventGradeFormat;
import com.revature.models.EventType;
import com.revature.models.ReimbursementUser;

public class TestDriver {
	public static void main(String[] args) throws SQLException {
		Connection conn = ConnFactory.getInstance().getConnection();
		
		UserDaoImpl userDao = UserDaoImpl.getInstance();
		EventDaoImpl eventDao = EventDaoImpl.getInstance();
		EventTypeDaoImpl etDao = EventTypeDaoImpl.getInstance();
		EventGradeFormatDaoImpl egfDao = EventGradeFormatDaoImpl.getInstance();
		ApplicationDaoImpl appDao = ApplicationDaoImpl.getInstance();
		ReimbursementUser user = userDao.getUser("littlefinger");
		System.out.println(user);
		ArrayList<EventType> ets = etDao.getTypes();
		ArrayList<EventGradeFormat> egfs =egfDao.getGradeTypes();
		EventGradeFormat egf = egfs.get(2);
		EventType et = ets.get(1);
		Event event = new Event();
		Application app = new Application();
		
		event.setEventType(et);
		event.setGradeFormat(egf);
		event.setCost(199);
		event.setStartDate(Timestamp.valueOf("2018-09-01 10:30:00"));
		event.setPassingGrade("Pass");
		event.setTitle("Money Laundering Certification");
		event = eventDao.insertEvent(event);
		System.out.println(event);
		app.setEvent(event);
		app.setComments("Hiding money.");
		app.setDate(Timestamp.valueOf("2018-09-20 13:55:34"));
		app.setUserID(user.getUserID());
		app.setReimbursementAmount(200);
		app = appDao.insertApplication(app);
		System.out.println(app);
		app = appDao.getUserApplications(user).get(0);
		System.out.println(app);
		
		
		
		
		
		
		
//		appDao.getUserApplications(user).forEach(e->{
//			System.out.println(e);
//			e.setGradeComments("Winning");
//			EventGradeDaoImpl.getInstance().updateEventGrade(e);
//		});
	}
}
