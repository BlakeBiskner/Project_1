package com.revature.daoImplementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.connection.ConnFactory;
import com.revature.daos.UserJobDao;
import com.revature.models.EventType;
import com.revature.models.UserJob;

public class UserJobDaoImpl implements UserJobDao {
	private Connection conn;
	private static UserJobDaoImpl jobDaoImpl = new UserJobDaoImpl();

	
	private UserJobDaoImpl() {
		
	}
	public static UserJobDaoImpl getInstance() {
		return jobDaoImpl;
	}
	
	
	@Override
	public ArrayList<UserJob> getUserJobs() {
		// TODO Auto-generated method stub
		
		ArrayList<UserJob> jobs = new ArrayList<>();
		try {
			conn = ConnFactory.getInstance().getConnection();
			String sql = "SELECT usr_j_id, usr_j_desc,usr_j_name,ujt_id,ujt_type FROM USER_JOB INNER JOIN USER_JOB_TYPE ON usr_j_type=ujt_id";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				UserJob job = new UserJob();
				job.setJob(rs.getString("usr_j_name"));
				job.setJobDescription(rs.getString("usr_j_desc"));
				job.setJobID(rs.getInt("usr_j_id"));
				job.setJobType(rs.getString("ujt_type"));
				job.setJobTypeID(rs.getInt("ujt_id"));
				jobs.add(job);
			}
			conn.close();
			return jobs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
