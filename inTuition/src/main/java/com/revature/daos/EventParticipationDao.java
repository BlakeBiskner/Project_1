package com.revature.daos;

import com.revature.models.Application;

public interface EventParticipationDao {
	//create
	public Application insertEventParticipation(Application app);

	//update
	public Application updateEventParticipation(Application app);

}
