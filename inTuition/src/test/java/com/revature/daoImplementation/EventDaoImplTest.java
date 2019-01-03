package com.revature.daoImplementation;

import java.sql.Timestamp;

import org.junit.jupiter.api.Test;

import com.revature.models.Event;

class EventDaoImplTest {
	EventDaoImpl eventDao = EventDaoImpl.getInstance();
	EventGradeFormatDaoImpl egfDao = EventGradeFormatDaoImpl.getInstance();
	EventTypeDaoImpl etDao = EventTypeDaoImpl.getInstance();
	
	
	void test() {
		Event event = new Event();
		event.setGradeFormat(egfDao.getGradeTypes().get(0));
		event.setEventType(etDao.getTypes().get(0));
		event.setPassingGrade("Pass");
		event.setStartDate(Timestamp.valueOf("2017-08-08 10:30:00"));
		event.setTitle("Rebellion Economics");
		event = eventDao.insertEvent(event);
		System.out.println(event);
		assert(event.getId()>=1);
	}
	@Test
	void testGetEvent() {
		Event event = new Event();
		
		event = eventDao.getEvent(103);
		System.out.println(event);
		assert(event.getId()>=1);
	}
	
}
