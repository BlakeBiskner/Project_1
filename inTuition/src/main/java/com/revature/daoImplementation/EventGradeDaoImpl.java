package com.revature.daoImplementation;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.connection.ConnFactory;
import com.revature.daos.EventGradeDao;
import com.revature.models.Application;

import oracle.jdbc.OracleTypes;

public class EventGradeDaoImpl implements EventGradeDao {
	private Connection conn;
	private static EventGradeDaoImpl eventGradeDao = new EventGradeDaoImpl();

	private EventGradeDaoImpl() {

	}

	public static EventGradeDaoImpl getInstance() {
		return eventGradeDao;
	}
	public Application insertEventGrade(Connection con,Application app) {
		// TODO Auto-generated method stub

		try {

			String sql = "BEGIN INSERT INTO EVENT_GRADE (eg_id,eg_a_id,eg_grade,eg_desc)"
					+ " VALUES(NULL,?,?,?) RETURNING eg_id INTO ?;  END;";

			CallableStatement cs = con.prepareCall(sql);
			cs.setInt(1, app.getApplicationID());
			cs.setString(2, app.getGrade());
			cs.setString(3, app.getGradeComments());
			cs.registerOutParameter(4, OracleTypes.NUMBER); // specifies the index created by the trigger that
												// references the employee inserted
			cs.execute();

			int id = cs.getInt(4);

//			if (commit) {
//				conn.commit();
//			}
			if (id >= 1) {
				app.setGradeID(id);;
				return app;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public Application insertEventGrade(Application app) {
		// TODO Auto-generated method stub
		conn = ConnFactory.getInstance().getConnection();

		try {

			String sql = "BEGIN INSERT INTO EVENT_GRADE (eg_id,eg_a_id,eg_grade,eg_desc)"
					+ " VALUES(NULL,?,?,?) RETURNING eg_id INTO ?;  END;";

			CallableStatement cs = conn.prepareCall(sql);
			cs.setInt(1, app.getApplicationID());
			cs.setString(2, app.getGrade());
			cs.setString(3, app.getGradeComments());
			cs.registerOutParameter(4, OracleTypes.NUMBER); // specifies the index created by the trigger that
												// references the employee inserted
			cs.execute();

			int id = cs.getInt(4);

//			if (commit) {
//				conn.commit();
//			}
			conn.close();
			if (id >= 1) {
				app.setGradeID(id);;
				return app;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Application updateEventGrade(Application app) {
		
		// TODO Auto-generated method stub
		try {
			conn = ConnFactory.getInstance().getConnection();
			String sql = "UPDATE EVENT_GRADE SET eg_grade = ?, eg_desc = ? WHERE eg_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, app.getGrade());
			ps.setString(2, app.getGradeComments());
			ps.setInt(3, app.getGradeID());
			ResultSet rs = ps.executeQuery();
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
