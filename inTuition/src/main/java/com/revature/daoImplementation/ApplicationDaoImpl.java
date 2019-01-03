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
			app = EventParticipationDaoImpl.getInstance().insertEventParticipation(conn, app);
			if(app==null || !(app.getParticipationID() >=1)) {
				conn.close();
				return null;
			}
//			a_id INTEGER,
//			user_id INTEGER NOT NULL,
//			comments VARCHAR2(300) NOT NULL,
//			time_missed NUMBER,
//			a_date TIMESTAMP,
//			reimbursement_amount NUMBER(10,2),
//			next_approver INTEGER,
//			status INTEGER NOT NULL,
//			event_participation INTEGER NOT NULL,
			conn.setAutoCommit(false);

			String sql = "BEGIN INSERT INTO APPLICATION (a_id,event_participation,user_id,comments,a_date,"
					+ "reimbursement_amount,status,time_missed,next_approver)"
					+ " VALUES(NULL,?,?,?,?,?,?,?,?) RETURNING a_id INTO ?;  END;";

			CallableStatement cs = conn.prepareCall(sql);
			cs.setInt(1, app.getParticipationID());
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
				conn.commit();
				conn.close();
				return app;
				
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
//		e_id, e_name, ep_cost, e_date, e_enddate, e_passing_grade,egf_format, egf_description,egf_id, et_id,
//		reimbursement_coverage,et_desc,
//		a_id,user_id,comments,a_date,reimbursement_amount,ep_id,ep_grade,ep_desc,
//		as_status status,as_id status_id, next_approver, passed
		try {
			conn = ConnFactory.getInstance().getConnection();
			String sql = "SELECT "
					+ "e_id,"
					+ "e_name, "
					+ "ep_cost, "
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
					+ "ep_id,"
					+ "ep_grade,"
					+ "ep_desc, "
					+ "status,"
					+ "status_id,"
					+ "next_approver,"
					+ "passed "
					+ "FROM application_view WHERE user_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, user.getUserID());
			System.out.println(user);
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
				app.setParticipationID(rs.getInt(17));
				app.setGrade(rs.getString(18));
				app.setGradeComments(rs.getString(19));
				app.setStatus(rs.getString(20));
				app.setStatusID(rs.getInt(21));
				app.setNextApproverID(rs.getInt(22));
				
				//app.setPassed(rs.getString(23).equals("Y"));
				String pf = rs.getString(23);
				if(pf!=null) {
					app.setPassed(pf.equals("Y"));
				}
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
		conn = ConnFactory.getInstance().getConnection();
		
		try {
			app = EventParticipationDaoImpl.getInstance().insertEventParticipation(conn, app);
			if(app==null || !(app.getParticipationID() >=1)) {
				conn.close();
				return null;
			}
//			a_id INTEGER,
//			user_id INTEGER NOT NULL,
//			comments VARCHAR2(300) NOT NULL,
//			time_missed NUMBER,
//			a_date TIMESTAMP,
//			reimbursement_amount NUMBER(10,2),
//			next_approver INTEGER,
//			status INTEGER NOT NULL,
//			event_participation INTEGER NOT NULL,
			conn.setAutoCommit(false);

			String sql = "UPDATE APPLICATION (event_participation,user_id,comments,a_date,"
					+ "reimbursement_amount,status,time_missed,next_approver)"
					+ " VALUES(?,?,?,?,?,?,?,?) WHERE a_id = ?";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, app.getParticipationID());
			ps.setInt(2, app.getUserID());
			ps.setString(3, app.getComments());
			ps.setTimestamp(4,app.getDate());
			ps.setDouble(5, app.getReimbursementAmount());
			ps.setInt(6, app.getStatusID());
			ps.setInt(7, app.getTimeMissed());
			ps.setInt(8, app.getNextApproverID());

			ResultSet rs = ps.executeQuery();
			conn.close();
			if (rs.next()) {
				return app;
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

}
