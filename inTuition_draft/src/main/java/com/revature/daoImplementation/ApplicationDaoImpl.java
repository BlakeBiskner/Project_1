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
		try {
			conn = ConnFactory.getInstance().getConnection();

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
			cs.setString(3, app.getJustification());
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
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Application app = new Application();
				
				
				app.setEventID(rs.getInt("e_id"));
				app.setEventTitle(rs.getString("e_name"));
				app.setCost(rs.getDouble("ep_cost"));
				app.setEventStartDate(rs.getTimestamp("e_date"));
				app.setEventEndDate(rs.getTimestamp("e_enddate"));
				app.setPassingGrade(rs.getString("e_passing_grade"));
				app.setGradeFormat(rs.getString("egf_format"));
				app.setEventGradeFormatDesc(rs.getString("egf_description"));
				app.setEventGradeFormatID(rs.getInt("egf_id"));
				app.setEventTypeID(rs.getInt("et_id"));
				app.setTypeCoverage(rs.getInt("reimbursement_coverage"));
				app.setTypeDescription(rs.getString("et_desc"));
				app.setApplicationID(rs.getInt("a_id"));
				app.setJustification(rs.getString("comments"));
				app.setDate(rs.getTimestamp("a_date"));
				app.setReimbursementAmount(rs.getDouble("reimbursement_amount"));
				app.setParticipationID(rs.getInt("ep_id"));
				app.setGrade(rs.getString("ep_grade"));
				app.setGradeComments(rs.getString("ep_desc"));
				app.setStatus(rs.getString("status"));
				app.setStatusID(rs.getInt("status_id"));
				app.setNextApproverID(rs.getInt("next_approver"));
				app.setUserID(user.getUserID());
				//app.setPassed(rs.getString(23).equals("Y"));
				String pf = rs.getString("passed");
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
//			String sql = "EXECUTE update_application("
//					+ "new_a_id => ?,"
//					+ "new_ep_id => ?,"
//					+ "new_reimbursement_amount => ?,"
//					+ "new_next_approved => ?,"
//					+ "new_status => ?,"
//					+ "new_ep_cost => ?,"
//					+ "new_ep_grade => ?,"
//					+ "new_ep_desc => ?,"
//					+ "new_passed => ?)";
			String sql = "{call update_application(?,?,?,?,?,?,?,?,?)}";
			CallableStatement cs = conn.prepareCall(sql);
			cs.setInt(1, app.getApplicationID());
			cs.setInt(2, app.getParticipationID());
			cs.setDouble(3, app.getReimbursementAmount());
			cs.setInt(4, app.getNextApproverID());
			cs.setInt(5, app.getStatusID());
			cs.setDouble(6, app.getCost());
			cs.setString(7, app.getGrade());
			cs.setString(8, app.getGradeComments());
			cs.setString(9, passed);
			
			cs.execute();
			conn.close();
			return app;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}


	@Override
	public Application updateApplicationAfterApproval(Application app, Connection con) {
		// TODO Auto-generated method stub

		try {			

			String sql = "UPDATE APPLICATION SET reimbursement_amount = ?, next_approver = ?, status = ? "
					+ "WHERE a_id = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setDouble(1, app.getReimbursementAmount());
			if(app.getNextApproverID()==null || app.getNextApproverID() == 0) {
				ps.setNull(2, 0);
			}
			else {
				ps.setInt(2, app.getNextApproverID());	
			}
			ps.setInt(3, app.getStatusID());
			ps.setInt(4, app.getApplicationID());
			System.out.println(sql);
			System.out.println(app.getReimbursementAmount());
			System.out.println(app.getNextApproverID());
			System.out.println(app.getStatusID());
			System.out.println(app.getApplicationID());
			ps.execute();
			return app;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	
	@Override
	public ArrayList<Application> getApplicationsToReview(ReimbursementUser user) {
		if (user.getJob().equals("Benefits Coordinator")) {
			return this.getApplicationsForBencoToView();
		}
		// TODO Auto-generated method stub
		ArrayList<Application> apps = new ArrayList<Application>();
//		e_id, e_name, ep_cost, e_date, e_enddate, e_passing_grade,egf_format, egf_description,egf_id, et_id,
//		reimbursement_coverage,et_desc,
//		a_id,user_id,comments,a_date,reimbursement_amount,ep_id,ep_grade,ep_desc,
//		as_status status,as_id status_id, next_approver, passed
		try {
			conn = ConnFactory.getInstance().getConnection();
			String sql = "SELECT "
					+ "user_id,"
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
					+ "FROM application_view WHERE next_approver = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, user.getUserID());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Application app = new Application();
				
				
				app.setEventID(rs.getInt("e_id"));
				app.setEventTitle(rs.getString("e_name"));
				app.setCost(rs.getDouble("ep_cost"));
				app.setEventStartDate(rs.getTimestamp("e_date"));
				app.setEventEndDate(rs.getTimestamp("e_enddate"));
				app.setPassingGrade(rs.getString("e_passing_grade"));
				app.setGradeFormat(rs.getString("egf_format"));
				app.setEventGradeFormatDesc(rs.getString("egf_description"));
				app.setEventGradeFormatID(rs.getInt("egf_id"));
				app.setEventTypeID(rs.getInt("et_id"));
				app.setTypeCoverage(rs.getInt("reimbursement_coverage"));
				app.setTypeDescription(rs.getString("et_desc"));
				app.setApplicationID(rs.getInt("a_id"));
				app.setJustification(rs.getString("comments"));
				app.setDate(rs.getTimestamp("a_date"));
				app.setReimbursementAmount(rs.getDouble("reimbursement_amount"));
				app.setParticipationID(rs.getInt("ep_id"));
				app.setGrade(rs.getString("ep_grade"));
				app.setGradeComments(rs.getString("ep_desc"));
				app.setStatus(rs.getString("status"));
				app.setStatusID(rs.getInt("status_id"));
				app.setNextApproverID(rs.getInt("next_approver"));
				app.setUserID(rs.getInt("user_id"));
				//app.setPassed(rs.getString(23).equals("Y"));
				String pf = rs.getString("passed");
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
	public ArrayList<Application> getApplicationsForBencoToView() {
		// TODO Auto-generated method stub
		ArrayList<Application> apps = new ArrayList<Application>();
//		e_id, e_name, ep_cost, e_date, e_enddate, e_passing_grade,egf_format, egf_description,egf_id, et_id,
//		reimbursement_coverage,et_desc,
//		a_id,user_id,comments,a_date,reimbursement_amount,ep_id,ep_grade,ep_desc,
//		as_status status,as_id status_id, next_approver, passed
		try {
			conn = ConnFactory.getInstance().getConnection();
			String sql = "SELECT "
					+ "user_id,"
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
					+ "FROM application_view WHERE status = 'Pending Approval By Benefits Coordinator' ";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Application app = new Application();
				
				
				app.setEventID(rs.getInt("e_id"));
				app.setEventTitle(rs.getString("e_name"));
				app.setCost(rs.getDouble("ep_cost"));
				app.setEventStartDate(rs.getTimestamp("e_date"));
				app.setEventEndDate(rs.getTimestamp("e_enddate"));
				app.setPassingGrade(rs.getString("e_passing_grade"));
				app.setGradeFormat(rs.getString("egf_format"));
				app.setEventGradeFormatDesc(rs.getString("egf_description"));
				app.setEventGradeFormatID(rs.getInt("egf_id"));
				app.setEventTypeID(rs.getInt("et_id"));
				app.setTypeCoverage(rs.getInt("reimbursement_coverage"));
				app.setTypeDescription(rs.getString("et_desc"));
				app.setApplicationID(rs.getInt("a_id"));
				app.setJustification(rs.getString("comments"));
				app.setDate(rs.getTimestamp("a_date"));
				app.setReimbursementAmount(rs.getDouble("reimbursement_amount"));
				app.setParticipationID(rs.getInt("ep_id"));
				app.setGrade(rs.getString("ep_grade"));
				app.setGradeComments(rs.getString("ep_desc"));
				app.setStatus(rs.getString("status"));
				app.setStatusID(rs.getInt("status_id"));
				app.setNextApproverID(rs.getInt("next_approver"));
				app.setUserID(rs.getInt("user_id"));
				//app.setPassed(rs.getString(23).equals("Y"));
				String pf = rs.getString("passed");
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
	public Application getApplication(int a_id) {
		try {
			conn = ConnFactory.getInstance().getConnection();
			String sql = "SELECT "
					+ "user_id,"
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
					+ "FROM application_view WHERE a_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, a_id);
			ResultSet rs = ps.executeQuery();
			Application app = null;
			
			if (rs.next()) {
				
				app = new Application();
				app.setEventID(rs.getInt("e_id"));
				app.setEventTitle(rs.getString("e_name"));
				app.setCost(rs.getDouble("ep_cost"));
				app.setEventStartDate(rs.getTimestamp("e_date"));
				app.setEventEndDate(rs.getTimestamp("e_enddate"));
				app.setPassingGrade(rs.getString("e_passing_grade"));
				app.setGradeFormat(rs.getString("egf_format"));
				app.setEventGradeFormatDesc(rs.getString("egf_description"));
				app.setEventGradeFormatID(rs.getInt("egf_id"));
				app.setEventTypeID(rs.getInt("et_id"));
				app.setTypeCoverage(rs.getInt("reimbursement_coverage"));
				app.setTypeDescription(rs.getString("et_desc"));
				app.setApplicationID(rs.getInt("a_id"));
				app.setJustification(rs.getString("comments"));
				app.setDate(rs.getTimestamp("a_date"));
				app.setReimbursementAmount(rs.getDouble("reimbursement_amount"));
				app.setParticipationID(rs.getInt("ep_id"));
				app.setGrade(rs.getString("ep_grade"));
				app.setGradeComments(rs.getString("ep_desc"));
				app.setStatus(rs.getString("status"));
				app.setStatusID(rs.getInt("status_id"));
				app.setNextApproverID(rs.getInt("next_approver"));
				app.setUserID(rs.getInt("user_id"));
				//app.setPassed(rs.getString(23).equals("Y"));
				String pf = rs.getString("passed");
				if(pf!=null) {
					app.setPassed(pf.equals("Y"));
				}
			}
			conn.close();
			return app;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return null;
	}
}
