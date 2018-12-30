package com.revature.daoImplementation;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.connection.ConnFactory;
import com.revature.daos.EventDao;
import com.revature.models.Event;
import com.revature.models.ReimbursementUser;

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
	public Event insertEvent(Event event) {
		// TODO Auto-generated method stub
		conn = ConnFactory.getInstance().getConnection();

		try {
			//INSERT INTO EVENT VALUES(null,4,'Graphic Design Certification',200,'10-SEP-02 02.10.10.123000000 PM',null,1,'P');

			String sql = "BEGIN INSERT INTO EVENT (e_id,e_type,e_name,e_cost,e_date,e_enddate,e_egf_id,e_passing_grade)"
					+ " VALUES(NULL,?,?,?,?,?,?,?) RETURNING e_id INTO ?;  END;";

			CallableStatement cs = conn.prepareCall(sql);
			cs.setInt(1, event.getEventTypeID());
			cs.setString(2, event.getTitle());
			cs.setDouble(3, event.getCost());
			cs.setTimestamp(4, event.getStartDate());
			cs.setTimestamp(5, event.getEndDate());
			cs.setInt(6, event.getEventGradeFormatID());
			cs.setString(7, event.getPassingGrade());

			cs.registerOutParameter(8, OracleTypes.NUMBER); // specifies the index created by the trigger that
															// references the employee inserted
			cs.execute();

			int id = cs.getInt(8);

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
	public Event getEvent(Event event) {
		// TODO Auto-generated method stub
//		SELECT e_id, e_name, e_cost, e_date, e_enddate, e_passing_grade,egf_format, egf_description,egf_id, et_id,reimbursement_coverage,et_desc
		try {
			conn = ConnFactory.getInstance().getConnection();
			String sql = "SELECT e_id, e_name, e_cost, e_date, e_enddate, e_passing_grade,"
					+ "egf_format, egf_description,egf_id, et_id,reimbursement_coverage,"
					+ "et_desc FROM event_view WHERE e_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, event.getId());
			ResultSet rs = ps.executeQuery();
			event = null;
			if (rs.next()) {
				event = new Event();
				event.setId(rs.getInt(1));
				event.setTitle(rs.getString(2));
				event.setCost(Double.parseDouble(rs.getString(3)));
				event.setStartDate(rs.getTimestamp(4));
				event.setEndDate(rs.getTimestamp(5));
				event.setPassingGrade(rs.getString(6));
				event.setGradeFormat(rs.getString(7));
				event.setGradeTypeDesc(rs.getString(8));
				event.setEventGradeFormatID(rs.getInt(9));
				event.setEventTypeID(rs.getInt(10));
				event.setCoverage(rs.getInt(11));
				event.setTypeDescription(rs.getString(12));
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
