package com.revature.daoImplementation;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.revature.models.EventType;

class EventTypeDaoImplTest {
	EventTypeDaoImpl eventTypeDao = EventTypeDaoImpl.getInstance();
	@Test
	void testReadAllEventTypes() {
		ArrayList<EventType> types = eventTypeDao.getTypes();
		//System.out.println(types);
		assert(types.get(0).getId()>=1);
	}

}
