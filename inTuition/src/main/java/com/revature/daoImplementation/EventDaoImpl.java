package com.revature.daoImplementation;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.connection.ConnFactory;
import com.revature.daos.EventDao;
import com.revature.models.Application;
import com.revature.models.Event;

import oracle.jdbc.OracleTypes;

public class EventDaoImpl implements EventDao {
	private Connection conn;
	private static EventDaoImpl eventDao = new EventDaoImpl();

	private EventDaoImpl() {

	}

	public static EventDaoImpl getInstance() {
		return eventDao;
	}
	@Override
	public Application insertEvent(Application app, Connection conn) {
		try {
			//INSERT INTO EVENT VALUES(null,4,'Graphic Design Certification',200,'10-SEP-02 02.10.10.123000000 PM',null,1,'P');

			String sql = "BEGIN INSERT INTO EVENT (e_id,e_type,e_name,e_date,e_enddate,e_egf_id,e_passing_grade)"
					+ " VALUES(NULL,?,?,?,?,?,?) RETURNING e_id INTO ?;  END;";

			CallableStatement cs = conn.prepareCall(sql);
			cs.setInt(1, app.getEventTypeID());
			cs.setString(2, app.getEventTitle());
			cs.setTimestamp(3, app.getEventStartDate());
			cs.setTimestamp(4, app.getEventEndDate());
			cs.setInt(5, app.getEventGradeFormatID());
			cs.setString(6, app.getPassingGrade());

			cs.registerOutParameter(7, OracleTypes.NUMBER); // specifies the index created by the trigger that
															// references the employee inserted
			cs.execute();

			int id = cs.getInt(7);

//			if (commit) {
//				conn.commit();
//			}
			if (id >= 1) {
				app.setEventID(id);
				return app;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}

	@Override
	public Event insertEvent(Event event) {
		// TODO Auto-generated method stub
		conn = ConnFactory.getInstance().getConnection();

		try {
			//INSERT INTO EVENT VALUES(null,4,'Graphic Design Certification',200,'10-SEP-02 02.10.10.123000000 PM',null,1,'P');

			String sql = "BEGIN INSERT INTO EVENT (e_id,e_type,e_name,e_date,e_enddate,e_egf_id,e_passing_grade)"
					+ " VALUES(NULL,?,?,?,?,?,?) RETURNING e_id INTO ?;  END;";

			CallableStatement cs = conn.prepareCall(sql);
			cs.setInt(1, event.getEventTypeID());
			cs.setString(2, event.getTitle());
			cs.setTimestamp(3, event.getStartDate());
			cs.setTimestamp(4, event.getEndDate());
			cs.setInt(5, event.getEventGradeFormatID());
			cs.setString(6, event.getPassingGrade());

			cs.registerOutParameter(7, OracleTypes.NUMBER); // specifies the index created by the trigger that
															// references the employee inserted
			cs.execute();

			int id = cs.getInt(7);

//			if (commit) {
//				conn.commit();
//			}
			conn.close();
			if (id >= 1) {
				event.setId(id);
				return event;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Event getEvent(int id) {
		Event event = new Event();
		// TODO Auto-generated method stub
		try {
			conn = ConnFactory.getInstance().getConnection();
			String sql = "SELECT e_id, e_name, e_date, e_enddate, e_passing_grade,"
					+ "egf_format, egf_description,egf_id, et_id,reimbursement_coverage,"
					+ "et_desc FROM event_view WHERE e_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			event = null;
			if (rs.next()) {
				event = new Event();
				event.setId(rs.getInt(1));
				event.setTitle(rs.getString(2));
				event.setStartDate(rs.getTimestamp(3));
				event.setEndDate(rs.getTimestamp(4));
				event.setPassingGrade(rs.getString(5));
				event.setGradeFormat(rs.getString(6));
				event.setGradeTypeDesc(rs.getString(7));
				event.setEventGradeFormatID(rs.getInt(8));
				event.setEventTypeID(rs.getInt(9));
				event.setCoverage(rs.getInt(10));
				event.setTypeDescription(rs.getString(11));
			}
			conn.close();
			return event;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
	public Event getEvent(Event event) {
		// TODO Auto-generated method stub
//		SELECT e_id, e_name, e_cost, e_date, e_enddate, e_passing_grade,egf_format, egf_description,egf_id, et_id,reimbursement_coverage,et_desc
		try {
			conn = ConnFactory.getInstance().getConnection();
			String sql = "SELECT e_id FROM event_view WHERE e_name = ? AND e_date = ? AND e_enddate = ? and e_passing_grade = ?"
					+ "AND et_id = ? AND egf_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, event.getTitle());
			ps.setTimestamp(2, event.getStartDate());
			ps.setTimestamp(3, event.getEndDate()); //This could be an issue if there is no enddate, hoping for if it is null it puts null there and checks for null
			ps.setString(4, event.getPassingGrade());
			ps.setInt(5, event.getEventTypeID());
			ps.setInt(6, event.getEventGradeFormatID());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				event.setId(rs.getInt(1));
			}
			else {
				event.setId(-1);
			}
			conn.close();
			return event;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
}
