package com.revature.testing;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;

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
		ReimbursementUser user = userDao.getUser("littlefinger");
		ApplicationDaoImpl appDao = ApplicationDaoImpl.getInstance();
		appDao.getUserApplications(user).forEach(e->{
			System.out.println(e);
			e.setGradeComments("Winning");
			EventGradeDaoImpl.getInstance().updateEventGrade(e);
		});
	}
}
