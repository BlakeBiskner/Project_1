package com.revature.daos;

import java.sql.Connection;

import com.revature.models.Application;
import com.revature.models.Event;

public interface EventDao {
	//create
	public Event insertEvent(Event event);
	public Application insertEvent(Application app, Connection conn);
	
	//read
	public Event getEvent(Event event);

	public Event getEvent(int id);
	
	//update
	
	
	//delete
	

}
