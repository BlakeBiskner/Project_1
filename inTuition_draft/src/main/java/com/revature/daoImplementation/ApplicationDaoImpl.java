package com.revature.daoImplementation;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;

import com.revature.connection.ConnFactory;
import com.revature.daos.ApplicationDao;
import com.revature.models.Application;
import com.revature.models.ReimbursementUser;

import oracle.jdbc.OracleTypes;

public class ApplicationDaoImpl implements ApplicationDao {
	private Connection conn;
	private static ApplicationDaoImpl applicationDao = new ApplicationDaoImpl();

	private ApplicationDaoImpl() {

	}

	public static ApplicationDaoImpl getInstance() {
		return applicationDao;
	}


	@Override
	public Application insertApplication(Application app) {//This method uses transaction - autocommit false then commits after eventgrade has been added
		// TODO Auto-generated method stub
		app.setDate(Timestamp.from(Instant.now()));
		conn = ConnFactory.getInstance().getConnection();
		
		try {
			conn.setAutoCommit(false);

			String sql = "BEGIN INSERT INTO APPLICATION (a_id,event_id,user_id,comments,a_date,"
					+ "reimbursement_amount,status,time_missed,next_approver)"
					+ " VALUES(NULL,?,?,?,?,?,?,?,?) RETURNING a_id INTO ?;  END;";

			CallableStatement cs = conn.prepareCall(sql);
			cs.setInt(1, app.getEventID());
			cs.setInt(2, app.getUserID());
			cs.setString(3, app.getComments());
			cs.setTimestamp(4,app.getDate());
			cs.setDouble(5, app.getReimbursementAmount());
			cs.setInt(6, app.getStatusID());
			cs.setInt(7, app.getTimeMissed());
			cs.setInt(8, app.getNextApproverID());
			cs.registerOutParameter(9, OracleTypes.NUMBER); // specifies the index created by the trigger that
			cs.execute();
			int id = cs.getInt(9);
			
//			if (commit) {
//				conn.commit();
//			}
			
			if (id >= 1) {
				app.setApplicationID(id);
				app = EventGradeDaoImpl.getInstance().insertEventGrade(conn, app);
				if(app!=null && app.getGradeID()>=1) {
					conn.commit();
					conn.close();
					return app;
				}
				
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ArrayList<Application> getUserApplications(ReimbursementUser user) {
		// TODO Auto-generated method stub
		ArrayList<Application> apps = new ArrayList<Application>();
//		e_id, e_name, e_cost, e_date, e_enddate, e_passing_grade,egf_format, egf_description,egf_id, et_id,
//		reimbursement_coverage,et_desc,
//		a_id,user_id,comments,a_date,reimbursement_amount,eg_id,eg_a_id,eg_grade,eg_desc,
//		as_status status,as_id status_id, next_approver
		try {
			conn = ConnFactory.getInstance().getConnection();
			String sql = "SELECT "
					+ "e_id,"
					+ "e_name, "
					+ "e_cost, "
					+ "e_date, "
					+ "e_enddate, "
					+ "e_passing_grade,"
					+ "egf_format,"
					+ "egf_description,"
					+ "egf_id, "
					+ "et_id," 
					+ "reimbursement_coverage,"
					+ "et_desc," 
					+ "a_id,"
					+ "comments,"
					+ "a_date,"
					+ "reimbursement_amount,"
					+ "eg_id,"
					+ "eg_grade,"
					+ "eg_desc, "
					+ "status,"
					+ "status_id,"
					+ "next_approver,"
					+ "passed "
					+ "FROM application_view WHERE user_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setInt(1, user.getUserID());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Application app = new Application();
				
				
				app.setEventID(rs.getInt(1));
				app.setEventTitle(rs.getString(2));
				app.setCost(rs.getDouble(3));
				app.setEventStartDate(rs.getTimestamp(4));
				app.setEventEndDate(rs.getTimestamp(5));
				app.setPassingGrade(rs.getString(6));
				app.setGradeFormat(rs.getString(7));
				app.setEventGradeFormatDesc(rs.getString(8));
				app.setEventGradeFormatID(rs.getInt(9));
				app.setEventTypeID(rs.getInt(10));
				app.setTypeCoverage(rs.getInt(11));
				app.setTypeDescription(rs.getString(12));
				app.setApplicationID(rs.getInt(13));
				app.setComments(rs.getString(14));
				app.setDate(rs.getTimestamp(15));
				app.setReimbursementAmount(rs.getDouble(16));
				app.setGradeID(rs.getInt(17));
				app.setGrade(rs.getString(18));
				app.setGradeComments(rs.getString(19));
				app.setStatus(rs.getString(20));
				app.setStatusID(rs.getInt(21));
				app.setNextApproverID(rs.getInt(22));
				app.setPassed(rs.getString(23));
				apps.add(app);
			}
			conn.close();
			return apps;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public Application updateApplication(Application app) {
		// TODO Auto-generated method stub
		return null;
	}

}
