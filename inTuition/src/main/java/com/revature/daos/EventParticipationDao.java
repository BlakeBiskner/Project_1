package com.revature.daos;

import java.sql.Connection;

import com.revature.models.Application;

public interface EventParticipationDao {
	//create
	public Application insertEventParticipation(Application app);

	//update
	public Application updateEventParticipation(Application app);

	public Application updateEventParticipation(Application app, Connection con);

}
