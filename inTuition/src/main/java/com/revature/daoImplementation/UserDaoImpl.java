package com.revature.daoImplementation;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.mindrot.jbcrypt.BCrypt;

import com.revature.connection.ConnFactory;
import com.revature.daos.UserDao;
import com.revature.models.Application;
import com.revature.models.ReimbursementUser;
import com.revature.models.UserJob;

import oracle.jdbc.OracleTypes;

public class UserDaoImpl implements UserDao {
	private Connection conn;
	private static UserDaoImpl userDao = new UserDaoImpl();

	private UserDaoImpl() {

	}

	public static UserDaoImpl getInstance() {
		return userDao;
	}

	@Override
	public ReimbursementUser insertUser(ReimbursementUser user) {
		// TODO Auto-generated method stub
		conn = ConnFactory.getInstance().getConnection();

		try {
			String sql = "BEGIN INSERT INTO USR (usr_id,usr_firstname,usr_lastname,usr_username,usr_email,usr_direct_supervisor,"
					+ "usr_department,usr_job,usr_password)"
					+ " VALUES(NULL,?,?,?,?,?,?,?,?) RETURNING usr_id INTO ?;  END;";
//			String sql = "INSERT INTO USR (usr_id,usr_firstname,usr_lastname,usr_username,usr_email,usr_direct_supervisor,usr_department,usr_type,usr_password) VALUES(NULL,?,?,?,?,?,?,?,?)";
			// PreparedStatement ps = conn.prepareStatement(sql);
			System.out.println(user);
			CallableStatement cs = conn.prepareCall(sql);
			cs.setString(1, user.getFirstname());
			cs.setString(2, user.getLastname());
			cs.setString(3, user.getUsername());
			cs.setString(4, user.getEmail());
			cs.setInt(5, user.getDsID());
			cs.setInt(6, user.getDeptID());
			cs.setInt(7, user.getJobID());
			cs.setString(8, user.getPassword());
			// ps.setString(9, accountApproved);
			// ps.setString(10, hasEmail);
			cs.registerOutParameter(9, OracleTypes.NUMBER); // specifies the index created by the trigger that
															// references the employee inserted
			cs.execute();

			int id = cs.getInt(9);
			// System.out.println(id);

//			if (commit) {
//				conn.commit();
//			}
			conn.close();
			if (id >= 1) {
				user.setUserID(id);
				return user;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ReimbursementUser getUser(ReimbursementUser user) {
		// TODO Auto-generated method stub
		try {
			
			
			conn = ConnFactory.getInstance().getConnection();
			String sql = "SELECT "
					+ "usr_id,"
					+ "usr_firstname,"
					+ "usr_lastname,"
					+ "usr_username,"
					+ "usr_email,"
					+ "usr_password,"
					+ "usr_account_approved,"
					+ "usr_has_email,"
					+ "dept_name,"
					+ "job,"
					+ "usr_job_id,"
					+ "job_desc, "
					+ "ds_id,"
					+ "ds_firstname,"
					+ "ds_lastname, "
					+ "ds_username,"
					+ "ds_email,"
					+ "ds_dept_name,"
					+ "ds_job_id, "
					+ "ds_job,"
					+ "ds_job_desc,"
					+ "usr_department_id,"
					+ "ujt_id,"
					+ "job_type,"
					+ "ds_jt_id,"
					+ "ds_ujt_type,"
					+ "available_reimbursement,"
					+ "department_head_id "
					+ "FROM user_view WHERE usr_username = ?";
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, user.getUsername());
			ResultSet rs = ps.executeQuery();
			user = null;
			if (rs.next()) {
				user = new ReimbursementUser();
				user.setUserID(rs.getInt("usr_id"));
				user.setFirstname(rs.getString("usr_firstname"));
				user.setLastname(rs.getString("usr_lastname"));
				user.setUsername(rs.getString("usr_username"));
				user.setEmail(rs.getString("usr_email"));
				user.setPassword(rs.getString("usr_password"));
				user.setAccountApproved(rs.getString("usr_account_approved").equals("Y"));
				user.setHasEmail(rs.getString("usr_has_email").equals("Y") || rs.getString(8).equals("U"));
				user.setHasUrgentEmail(rs.getString("usr_has_email").equals("U"));
				user.setDept(rs.getString("dept_name"));
				user.setJob(rs.getString("job"));
				user.setJobID(rs.getInt("usr_job_id"));
				user.setJobDesc(rs.getString("job_desc"));
				user.setDsID(rs.getInt("ds_id"));
				user.setDsFirstname(rs.getString("ds_firstname"));
				user.setDsLastname(rs.getString("ds_lastname"));
				user.setDsUsername(rs.getString("ds_username"));
				user.setDsEmail(rs.getString("ds_email"));
				user.setDsDept(rs.getString("ds_dept_name"));
				user.setDsJobID(rs.getInt("ds_job_id"));
				user.setDsJob(rs.getString("ds_job"));
				user.setDsJobDesc(rs.getString("ds_job_desc"));

				user.setDeptID(rs.getInt("usr_department_id"));
				user.setJobTypeID(rs.getInt("ujt_id"));
				user.setJobType(rs.getString("job_type"));
				user.setDsJobTypeID(rs.getInt("ds_jt_id"));
				user.setDsJobType(rs.getString("ds_ujt_type"));
				user.setYearlyReimbursementRemaining(rs.getDouble("available_reimbursement"));
				user.setDeptHeadID(rs.getInt("department_head_id"));
			}
			conn.close();
			return user;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		return null;
	}
	@Override
	public ReimbursementUser getUser(int userID) {
		// TODO Auto-generated method stub
		try {
			
			
			conn = ConnFactory.getInstance().getConnection();
			String sql = "SELECT "
					+ "usr_id,"
					+ "usr_firstname,"
					+ "usr_lastname,"
					+ "usr_username,"
					+ "usr_email,"
					+ "usr_password,"
					+ "usr_account_approved,"
					+ "usr_has_email,"
					+ "dept_name,"
					+ "job,"
					+ "usr_job_id,"
					+ "job_desc, "
					+ "ds_id,"
					+ "ds_firstname,"
					+ "ds_lastname, "
					+ "ds_username,"
					+ "ds_email,"
					+ "ds_dept_name,"
					+ "ds_job_id, "
					+ "ds_job,"
					+ "ds_job_desc,"
					+ "usr_department_id,"
					+ "ujt_id,"
					+ "job_type,"
					+ "ds_jt_id,"
					+ "ds_ujt_type,"
					+ "available_reimbursement,"
					+ "department_head_id "
					+ "FROM user_view WHERE usr_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setInt(1, userID);
			ResultSet rs = ps.executeQuery();
			ReimbursementUser user = null;
			if (rs.next()) {
				user = new ReimbursementUser();
				user.setUserID(rs.getInt("usr_id"));
				user.setFirstname(rs.getString("usr_firstname"));
				user.setLastname(rs.getString("usr_lastname"));
				user.setUsername(rs.getString("usr_username"));
				user.setEmail(rs.getString("usr_email"));
				user.setPassword(rs.getString("usr_password"));
				user.setAccountApproved(rs.getString("usr_account_approved").equals("Y"));
				user.setHasEmail(rs.getString("usr_has_email").equals("Y") || rs.getString(8).equals("U"));
				user.setHasUrgentEmail(rs.getString("usr_has_email").equals("U"));
				user.setDept(rs.getString("dept_name"));
				user.setJob(rs.getString("job"));
				user.setJobID(rs.getInt("usr_job_id"));
				user.setJobDesc(rs.getString("job_desc"));
				user.setDsID(rs.getInt("ds_id"));
				user.setDsFirstname(rs.getString("ds_firstname"));
				user.setDsLastname(rs.getString("ds_lastname"));
				user.setDsUsername(rs.getString("ds_username"));
				user.setDsEmail(rs.getString("ds_email"));
				user.setDsDept(rs.getString("ds_dept_name"));
				user.setDsJobID(rs.getInt("ds_job_id"));
				user.setDsJob(rs.getString("ds_job"));
				user.setDsJobDesc(rs.getString("ds_job_desc"));

				user.setDeptID(rs.getInt("usr_department_id"));
				user.setJobTypeID(rs.getInt("ujt_id"));
				user.setJobType(rs.getString("job_type"));
				user.setDsJobTypeID(rs.getInt("ds_jt_id"));
				user.setDsJobType(rs.getString("ds_ujt_type"));
				user.setYearlyReimbursementRemaining(rs.getDouble("available_reimbursement"));
				user.setDeptHeadID(rs.getInt("department_head_id"));
			}
			conn.close();
			return user;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		return null;
	}

	
	
	@Override
	public ReimbursementUser getUser(String username) {
		// TODO Auto-generated method stub
		ReimbursementUser user = new ReimbursementUser();
		try {
			
			
			conn = ConnFactory.getInstance().getConnection();
			String sql = "SELECT "
					+ "usr_id,"
					+ "usr_firstname,"
					+ "usr_lastname,"
					+ "usr_username,"
					+ "usr_email,"
					+ "usr_password,"
					+ "usr_account_approved,"
					+ "usr_has_email,"
					+ "dept_name,"
					+ "job,"
					+ "usr_job_id,"
					+ "job_desc, "
					+ "ds_id,"
					+ "ds_firstname,"
					+ "ds_lastname, "
					+ "ds_username,"
					+ "ds_email,"
					+ "ds_dept_name,"
					+ "ds_job_id, "
					+ "ds_job,"
					+ "ds_job_desc,"
					+ "usr_department_id,"
					+ "ujt_id,"
					+ "job_type,"
					+ "ds_jt_id,"
					+ "ds_ujt_type,"
					+ "available_reimbursement,"
					+ "department_head_id "
					+ "FROM user_view WHERE usr_username = ?";
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			user = null;
			if (rs.next()) {
				user = new ReimbursementUser();
				user.setUserID(rs.getInt("usr_id"));
				user.setFirstname(rs.getString("usr_firstname"));
				user.setLastname(rs.getString("usr_lastname"));
				user.setUsername(rs.getString("usr_username"));
				user.setEmail(rs.getString("usr_email"));
				user.setPassword(rs.getString("usr_password"));
				user.setAccountApproved(rs.getString("usr_account_approved").equals("Y"));
				user.setHasEmail(rs.getString("usr_has_email").equals("Y") || rs.getString(8).equals("U"));
				user.setHasUrgentEmail(rs.getString("usr_has_email").equals("U"));
				user.setDept(rs.getString("dept_name"));
				user.setJob(rs.getString("job"));
				user.setJobID(rs.getInt("usr_job_id"));
				user.setJobDesc(rs.getString("job_desc"));
				user.setDsID(rs.getInt("ds_id"));
				user.setDsFirstname(rs.getString("ds_firstname"));
				user.setDsLastname(rs.getString("ds_lastname"));
				user.setDsUsername(rs.getString("ds_username"));
				user.setDsEmail(rs.getString("ds_email"));
				user.setDsDept(rs.getString("ds_dept_name"));
				user.setDsJobID(rs.getInt("ds_job_id"));
				user.setDsJob(rs.getString("ds_job"));
				user.setDsJobDesc(rs.getString("ds_job_desc"));

				user.setDeptID(rs.getInt("usr_department_id"));
				user.setJobTypeID(rs.getInt("ujt_id"));
				user.setJobType(rs.getString("job_type"));
				user.setDsJobTypeID(rs.getInt("ds_jt_id"));
				user.setDsJobType(rs.getString("ds_ujt_type"));
				user.setYearlyReimbursementRemaining(rs.getDouble("available_reimbursement"));	
				user.setDeptHeadID(rs.getInt("department_head_id"));
			}
			conn.close();
			return user;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ReimbursementUser getApplicant(Application app) {
		// TODO Auto-generated method stub
		try {
			
			
			conn = ConnFactory.getInstance().getConnection();
			String sql = "SELECT "
					+ "usr_id,"
					+ "usr_firstname,"
					+ "usr_lastname,"
					+ "usr_username,"
					+ "usr_email,"
					+ "usr_password,"
					+ "usr_account_approved,"
					+ "usr_has_email,"
					+ "dept_name,"
					+ "job,"
					+ "usr_job_id,"
					+ "job_desc, "
					+ "ds_id,"
					+ "ds_firstname,"
					+ "ds_lastname, "
					+ "ds_username,"
					+ "ds_email,"
					+ "ds_dept_name,"
					+ "ds_job_id, "
					+ "ds_job,"
					+ "ds_job_desc,"
					+ "usr_department_id,"
					+ "ujt_id,"
					+ "job_type,"
					+ "ds_jt_id,"
					+ "ds_ujt_type,"
					+ "available_reimbursement,"
					+ "department_head_id "
					+ "FROM user_view WHERE usr_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setInt(1, app.getUserID());
			ResultSet rs = ps.executeQuery();
			ReimbursementUser user = null;
			if (rs.next()) {
				user = new ReimbursementUser();
				user.setUserID(rs.getInt("usr_id"));
				user.setFirstname(rs.getString("usr_firstname"));
				user.setLastname(rs.getString("usr_lastname"));
				user.setUsername(rs.getString("usr_username"));
				user.setEmail(rs.getString("usr_email"));
				user.setPassword(rs.getString("usr_password"));
				user.setAccountApproved(rs.getString("usr_account_approved").equals("Y"));
				user.setHasEmail(rs.getString("usr_has_email").equals("Y") || rs.getString(8).equals("U"));
				user.setHasUrgentEmail(rs.getString("usr_has_email").equals("U"));
				user.setDept(rs.getString("dept_name"));
				user.setJob(rs.getString("job"));
				user.setJobID(rs.getInt("usr_job_id"));
				user.setJobDesc(rs.getString("job_desc"));
				user.setDsID(rs.getInt("ds_id"));
				user.setDsFirstname(rs.getString("ds_firstname"));
				user.setDsLastname(rs.getString("ds_lastname"));
				user.setDsUsername(rs.getString("ds_username"));
				user.setDsEmail(rs.getString("ds_email"));
				user.setDsDept(rs.getString("ds_dept_name"));
				user.setDsJobID(rs.getInt("ds_job_id"));
				user.setDsJob(rs.getString("ds_job"));
				user.setDsJobDesc(rs.getString("ds_job_desc"));

				user.setDeptID(rs.getInt("usr_department_id"));
				user.setJobTypeID(rs.getInt("ujt_id"));
				user.setJobType(rs.getString("job_type"));
				user.setDsJobTypeID(rs.getInt("ds_jt_id"));
				user.setDsJobType(rs.getString("ds_ujt_type"));
				user.setYearlyReimbursementRemaining(rs.getDouble("available_reimbursement"));
				user.setDeptHeadID(rs.getInt("department_head_id"));
			}
			conn.close();
			return user;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	

		return null;
	}

	@Override
	public ArrayList<ReimbursementUser> getAllUsers() {
		
		ArrayList<ReimbursementUser> users = new ArrayList<>();
		try {
			
			
			conn = ConnFactory.getInstance().getConnection();
			String sql = "SELECT "
					+ "usr_id,"
					+ "usr_firstname,"
					+ "usr_lastname,"
					+ "usr_username,"
					+ "usr_email,"
					+ "usr_password,"
					+ "usr_account_approved,"
					+ "usr_has_email,"
					+ "dept_name,"
					+ "job,"
					+ "usr_job_id,"
					+ "job_desc, "
					+ "ds_id,"
					+ "ds_firstname,"
					+ "ds_lastname, "
					+ "ds_username,"
					+ "ds_email,"
					+ "ds_dept_name,"
					+ "ds_job_id, "
					+ "ds_job,"
					+ "ds_job_desc,"
					+ "usr_department_id,"
					+ "ujt_id,"
					+ "job_type,"
					+ "ds_jt_id,"
					+ "ds_ujt_type,"
					+ "available_reimbursement,"
					+ "department_head_id "
					+ "FROM user_view";
			PreparedStatement ps = conn.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			ReimbursementUser user = null;
			while (rs.next()) {
				user = new ReimbursementUser();
				user.setUserID(rs.getInt("usr_id"));
				user.setFirstname(rs.getString("usr_firstname"));
				user.setLastname(rs.getString("usr_lastname"));
				user.setUsername(rs.getString("usr_username"));
				user.setEmail(rs.getString("usr_email"));
				user.setPassword(rs.getString("usr_password"));
				user.setAccountApproved(rs.getString("usr_account_approved").equals("Y"));
				user.setHasEmail(rs.getString("usr_has_email").equals("Y") || rs.getString(8).equals("U"));
				user.setHasUrgentEmail(rs.getString("usr_has_email").equals("U"));
				user.setDept(rs.getString("dept_name"));
				user.setJob(rs.getString("job"));
				user.setJobID(rs.getInt("usr_job_id"));
				user.setJobDesc(rs.getString("job_desc"));
				user.setDsID(rs.getInt("ds_id"));
				user.setDsFirstname(rs.getString("ds_firstname"));
				user.setDsLastname(rs.getString("ds_lastname"));
				user.setDsUsername(rs.getString("ds_username"));
				user.setDsEmail(rs.getString("ds_email"));
				user.setDsDept(rs.getString("ds_dept_name"));
				user.setDsJobID(rs.getInt("ds_job_id"));
				user.setDsJob(rs.getString("ds_job"));
				user.setDsJobDesc(rs.getString("ds_job_desc"));

				user.setDeptID(rs.getInt("usr_department_id"));
				user.setJobTypeID(rs.getInt("ujt_id"));
				user.setJobType(rs.getString("job_type"));
				user.setDsJobTypeID(rs.getInt("ds_jt_id"));
				user.setDsJobType(rs.getString("ds_ujt_type"));
				user.setYearlyReimbursementRemaining(rs.getDouble("available_reimbursement"));
				user.setDeptHeadID(rs.getInt("department_head_id"));
				users.add(user);
			}
			conn.close();
			return users;		// TODO Auto-generated method stub
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return null;
	}

}
