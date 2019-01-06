package com.revature.daoImplementation;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;

import com.revature.connection.ConnFactory;
import com.revature.daos.ApplicationApprovalDao;
import com.revature.models.Application;
import com.revature.models.ApplicationApproval;

import oracle.jdbc.OracleTypes;

public class ApplicationApprovalDaoImpl implements ApplicationApprovalDao {
	private Connection conn;
	private static ApplicationApprovalDaoImpl approvalDao = new ApplicationApprovalDaoImpl();

	private ApplicationApprovalDaoImpl() {

	}

	public static ApplicationApprovalDaoImpl getInstance() {
		return approvalDao;
	}


	@Override
	public ApplicationApproval insertApproval(ApplicationApproval approval,Application app) {
		// TODO Auto-generated method stub
		approval.setApprovalTime(Timestamp.from(Instant.now()));
		conn = ConnFactory.getInstance().getConnection();
		
		String approved = null;
		if(approval.getApproval()!=null) {
			if(approval.getApproval()) {
				approved = "Y";
			}
			else {
				approved = "N";
			}	
		}	
		
		try {
			conn.setAutoCommit(false);

			String sql = "BEGIN INSERT INTO APPLICATION_APPROVAL (aa_id,aa_application,aa_approver,approval_time,approval,reasoning)"
					+ " VALUES(NULL,?,?,?,?,?) RETURNING aa_id INTO ?;  END;";

			CallableStatement cs = conn.prepareCall(sql);
			cs.setInt(1,approval.getAppID());
			cs.setInt(2, approval.getApproverID());
			cs.setTimestamp(3, approval.getApprovalTime());
			cs.setString(4, approved);
			cs.setString(5, approval.getReasoning());
			
			cs.registerOutParameter(6, OracleTypes.NUMBER); // specifies the index created by the trigger that
			cs.execute();
			int id = cs.getInt(6);
			
//			if (commit) {
//				conn.commit();
//			}
			
			if (id >= 1) {
				approval.setApprovalID(id);
				app = ApplicationDaoImpl.getInstance().updateApplicationAfterApproval(app, conn);
				if(app!=null) {
					conn.commit();
				}
				conn.close();
				return approval;
				
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public ArrayList<ApplicationApproval> getApplicationsApprovals(Application app) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApplicationApproval nextApprovalNeeded(Application app) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApplicationApproval updateApproval(ApplicationApproval approval) {
		// TODO Auto-generated method stub
		return null;
	}

}
