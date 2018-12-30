package com.revature.testing;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;

import com.revature.connection.ConnFactory;
import com.revature.daoImplementation.EventDaoImpl;
import com.revature.daoImplementation.EventGradeFormatDaoImpl;
import com.revature.daoImplementation.EventTypeDaoImpl;
import com.revature.daoImplementation.UserDaoImpl;
import com.revature.models.Event;
import com.revature.models.EventGradeFormat;
import com.revature.models.EventType;
import com.revature.models.ReimbursementUser;

public class TestDriver {
	public static void main(String[] args) throws SQLException {
		Connection conn = ConnFactory.getInstance().getConnection();
		
		UserDaoImpl userDao = UserDaoImpl.getInstance();
		String password = "Aryaa";
		ReimbursementUser ds = new ReimbursementUser();
		ds.setUsername("stanlee");
		EventTypeDaoImpl etdao = EventTypeDaoImpl.getInstance();
//		etdao.getTypes().forEach(e->{
//			System.out.println(e);
//		});
//		
		EventType et = etdao.getTypes().get(0);
		EventDaoImpl eventDao = EventDaoImpl.getInstance();
		Event event = new Event();
		event.setId(1);
		event.setEventTypeID(et.getId());
		//event = eventDao.getEvent(event);
		//System.out.println(event);
		EventGradeFormatDaoImpl egfDao = EventGradeFormatDaoImpl.getInstance();
		EventGradeFormat egf = egfDao.getGradeTypes().get(0);
		event.setEventGradeFormatID(egf.getId());
		event.setCost(33);
		event.setCoverage(20);
		event.setEndDate(Timestamp.from(Instant.now()));
		event.setStartDate(Timestamp.from(Instant.now()));
		event.setPassingGrade("PASS");
		event.setTitle("Artistic Liscense Certification");
		
		event = eventDao.insertEvent(event);
		System.out.println(event);
		//ds = userDao.getUser(ds);
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
		//conn.close();
		//boolean success = LoginCont.login(user);
		//System.out.println(success);
	}
}
