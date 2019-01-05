package com.revature.daoImplementation;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.connection.ConnFactory;
import com.revature.daos.EventParticipationDao;
import com.revature.models.Application;

import oracle.jdbc.OracleTypes;

public class EventParticipationDaoImpl implements EventParticipationDao {
	private Connection conn;
	private static EventParticipationDaoImpl eventGradeDao = new EventParticipationDaoImpl();

	private EventParticipationDaoImpl() {

	}

	public static EventParticipationDaoImpl getInstance() {
		return eventGradeDao;
	}
	public Application insertEventParticipation(Connection con,Application app) {
		// TODO Auto-generated method stub
		EventDaoImpl eventDao = EventDaoImpl.getInstance();
		String passed = null;
		if(app.getPassed()!=null) {
			if(app.getPassed()) {
				passed = "Y";
			}
			else {
				passed = "N";
			}	
		}
		

		try {
			String sql = "BEGIN INSERT INTO EVENT_PARTICIPATION (ep_id,ep_cost,ep_grade,ep_desc,passed,event_id)"
					+ " VALUES(NULL,?,?,?,?,?) RETURNING ep_id INTO ?;  END;";

			CallableStatement cs = con.prepareCall(sql);
			cs.setDouble(1, app.getCost());
			cs.setString(2, app.getGrade());
			cs.setString(3, app.getGradeComments());
			cs.setString(4, passed);
			cs.setInt(5, app.getEventID());

			cs.registerOutParameter(6, OracleTypes.NUMBER); // specifies the index created by the trigger that
												// references the employee inserted
			cs.execute();

			int id = cs.getInt(6);

//			if (commit) {
//				conn.commit();
//			}
			if (id >= 1) {
				app.setParticipationID(id);;
				return app;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public Application insertEventParticipation(Application app) {
		// TODO Auto-generated method stub
		conn = ConnFactory.getInstance().getConnection();

		String passed = null;
		if(app.getPassed()!=null) {
			if(app.getPassed()) {
				passed = "Y";
			}
			else {
				passed = "N";
			}	
		}
		

		try {
			String sql = "BEGIN INSERT INTO EVENT_PARTICIPATION (ep_id,ep_cost,ep_grade,ep_desc,passed,event_id)"
					+ " VALUES(NULL,?,?,?,?) RETURNING ep_id INTO ?;  END;";

			CallableStatement cs = conn.prepareCall(sql);
			cs.setDouble(1, app.getCost());
			cs.setString(2, app.getGrade());
			cs.setString(3, app.getGradeComments());
			cs.setString(4, passed);
			cs.setInt(5, app.getEventID());
			cs.registerOutParameter(6, OracleTypes.NUMBER); // specifies the index created by the trigger that
												// references the employee inserted
			cs.execute();

			int id = cs.getInt(6);
			conn.close();
			if (id >= 1) {
				app.setParticipationID(id);
				return app;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		return null;
	}

	@Override
	public Application updateEventParticipation(Application app) {
		String passed = null;
		if(app.getPassed()!=null) {
			if(app.getPassed()) {
				passed = "Y";
			}
			else {
				passed = "N";
			}	
		}		
		// TODO Auto-generated method stub
		try {

			conn = ConnFactory.getInstance().getConnection();
			String sql = "UPDATE EVENT_PARTICIPATION SET ep_grade = ?, ep_desc = ?,passed = ?,ep_cost = ? WHERE ep_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, app.getGrade());
			ps.setString(2, app.getGradeComments());
			ps.setString(3, passed);
			ps.setDouble(4, app.getCost());
			ps.setInt(5, app.getParticipationID());
			ResultSet rs = ps.executeQuery();
			conn.close();
			if (rs.next()) {
				return app;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
