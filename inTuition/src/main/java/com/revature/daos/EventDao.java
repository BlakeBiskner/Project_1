package com.revature.daos;

import com.revature.models.Event;

public interface EventDao {
	//create
	public Event insertEvent(Event event);
	
	//read
	public Event getEvent(Event event);

	Event getEvent(int id);
	
	//update
	
	
	//delete
	

}
