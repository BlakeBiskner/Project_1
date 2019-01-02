package com.revature.daoImplementation;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.revature.models.EventGradeFormat;

class EventGradeFormatDaoImplTest {

	EventGradeFormatDaoImpl eventGradeFormatDao = EventGradeFormatDaoImpl.getInstance();
	@Test
	void testReadAllEventTypes() {
		ArrayList<EventGradeFormat> types = eventGradeFormatDao.getGradeTypes();
		System.out.println(types);
		assert(types.get(0).getId()>=1);
	}

}
