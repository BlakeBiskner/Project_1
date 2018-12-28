package com.revature.daoImplementation;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.mindrot.jbcrypt.BCrypt;

import com.revature.connection.ConnFactory;
import com.revature.daos.UserDao;
import com.revature.models.ReimbursementUser;

import oracle.jdbc.OracleTypes;

public class UserDaoImpl implements UserDao {
	private static final Connection CONN = ConnFactory.getInstance().getConnection();

	@Override
	public ReimbursementUser insertUser(ReimbursementUser user) {
		// TODO Auto-generated method stub
		String accountApproved, hasEmail;
		if (user.isAccountApproved()) {
			accountApproved = "Y";
		} else {
			accountApproved = "N";
		}

		if (user.isHasUrgentEmail()) {
			hasEmail = "U";
		} else if (user.isHasEmail()) {
			hasEmail = "Y";
		} else {
			hasEmail = "N";
		}

		try {
			String sql = "BEGIN INSERT INTO USR (usr_id,usr_firstname,usr_lastname,usr_username,usr_email,usr_direct_supervisor,"
					+ "usr_department,usr_type,usr_password,usr_account_approved,usr_has_email)"
					+ " VALUES(NULL,?,?,?,?,?,?,?,?,?,?) RETURNING usr_id INTO ?;  END;";

			// INSERT INTO USR
			// VALUES(null,'firstname','lastname','username','email',directSupervisorID,departmentID,typeID,'password','accountApproved','hasEmail');
			CallableStatement cs = CONN.prepareCall(sql);

			cs.setString(1, user.getFirstname());
			cs.setString(2, user.getLastname());
			cs.setString(3, user.getUsername());
			cs.setString(4, user.getEmail());
			cs.setInt(5, user.getDsID());
			cs.setInt(6, user.getDeptID());
			cs.setInt(7, user.getUserTypeID());
			cs.setString(8, user.getPassword());
			cs.setString(9, accountApproved);
			cs.setString(10, hasEmail);

			cs.registerOutParameter(11, OracleTypes.NUMBER); // specifies the index created by the trigger that
															// references the employee inserted
			cs.execute();
			int id = cs.getInt(11);
			user.setUserID(id);
			return user;
//			if (commit) {
//				conn.commit();
//			}
//			if (id >= 1) {
//				employee.setEmployeeID(id);
//				return employee;
//			}
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
			String sql = "SELECT usr_id,usr_firstname,usr_lastname,usr_username,usr_email,usr_password,"
					+ "usr_account_approved,usr_has_email,dept_name, usr_t_permissions,job,"
					+ "job_desc, ds_id,ds_firstname,ds_lastname, ds_username,ds_email,ds_dept_name, ds_usr_t_permissions,"
					+ " ds_job,  ds_job_desc, usr_type_id,usr_department_id FROM user_view WHERE usr_username = ?";
			PreparedStatement ps = CONN.prepareStatement(sql);

			ps.setString(1, user.getUsername());
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				user = new ReimbursementUser();
				user.setUserID(rs.getInt(1));
				user.setFirstname(rs.getString(2));
				user.setLastname(rs.getString(3));
				user.setUsername(rs.getString(4));
				user.setEmail(rs.getString(5));
				user.setPassword(rs.getString(6));
				user.setAccountApproved(rs.getString(7).equals("Y"));
				user.setHasEmail(rs.getString(8).equals('Y') || rs.getString(8).equals("U"));
				user.setHasUrgentEmail(rs.getString(8).equals("U"));
				user.setDept(rs.getString(9));
				user.setPermissionType(rs.getInt(10));
				user.setJob(rs.getString(11));
				user.setJobDesc(rs.getString(12));
				user.setDsID(rs.getInt(13));
				user.setDsFirstname(rs.getString(14));
				user.setDsLastname(rs.getString(15));
				user.setDsUsername(rs.getString(16));
				user.setDsEmail(rs.getString(17));
				user.setDsDept(rs.getString(18));
				user.setDsPermissionType(rs.getInt(19));
				user.setDsJob(rs.getString(20));
				user.setDsJobDesc(rs.getString(21));

				user.setUserTypeID(rs.getInt(22));
				user.setDeptID(rs.getInt(23));

				return user;

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ReimbursementUser getUser(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	public static void main(String[] args) {
		String password = "noone";
		String hashedpw = BCrypt.hashpw(password, BCrypt.gensalt());
		UserDaoImpl userDao = new UserDaoImpl();
		ReimbursementUser ds = new ReimbursementUser();
		ds.setUsername("stanlee");
		ds = userDao.getUser(ds);
		if (ds != null) {
			System.out.println(ds);
		}
		ReimbursementUser user = new ReimbursementUser();
		user.setEmail("arya@email.com");
		user.setFirstname("Arya");
		user.setLastname("Stark");
		user.setPassword(hashedpw);
		user.setUsername("arya");
		//System.out.println(user);
//		user = userDao.insertUser(user);
//		if(user!=null) {
//			System.out.println(user);
//		}
		user = userDao.getUser(user);
		System.out.println(user);
	}
}
