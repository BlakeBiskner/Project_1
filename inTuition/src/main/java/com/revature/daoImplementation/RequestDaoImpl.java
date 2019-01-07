package com.revature.daoImplementation;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.connection.ConnFactory;
import com.revature.daos.RequestDao;
import com.revature.models.Application;
import com.revature.models.ApplicationMaterial;
import com.revature.models.MaterialRequest;
import com.revature.models.ReimbursementUser;

import oracle.jdbc.OracleTypes;

public class RequestDaoImpl implements RequestDao {
	private static RequestDaoImpl reqDao = new RequestDaoImpl();
	private Connection conn;

	private RequestDaoImpl() {

	}

	public static RequestDaoImpl getInstance() {
		return reqDao;
	}

	@Override
	public MaterialRequest insertRequest(MaterialRequest request) {
		// TODO Auto-generated method stub
		try {
			conn = ConnFactory.getInstance().getConnection();
			// INSERT INTO EVENT VALUES(null,4,'Graphic Design Certification',200,'10-SEP-02
			// 02.10.10.123000000 PM',null,1,'P');

			String sql = "BEGIN INSERT INTO APPLICATION_MATERIAL_REQUEST (amr_id,amr_a_id,amr_requester_id,amr_requestee_id,amr_request) "
					+ "VALUES (null, ?,?,?,?) RETURNING amr_id INTO ?; END;";

			CallableStatement cs = conn.prepareCall(sql);
			cs.setInt(1, request.getAppID());
			cs.setInt(2, request.getRequesterID());
			cs.setInt(3, request.getRequesteeID());
			cs.setString(4, request.getRequest());
			cs.registerOutParameter(5, OracleTypes.NUMBER); // specifies the index created by the trigger that
															// references the employee inserted
			cs.execute();

			int id = cs.getInt(5);

//			if (commit) {
//				conn.commit();
//			}
			if (id >= 1) {
				request.setRequestID(id);
				return request;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public ArrayList<MaterialRequest> getApplicationRequests(Application app) {
		// TODO Auto-generated method stub
		ArrayList<MaterialRequest> reqs = new ArrayList<>();
		try {
			conn = ConnFactory.getInstance().getConnection();
			String sql = "SELECT amr_id,amr_request,amr_requestee_id,amr_requester_id,amr_am_id FROM APPLICATION_MATERIAL_REQUEST WHERE amr_a_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setInt(1, app.getApplicationID());
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				MaterialRequest req = new MaterialRequest();
				req.setAppID(app.getApplicationID());
				req.setMaterialID(rs.getInt("amr_am_id"));
				req.setRequest(rs.getString("amr_request"));
				req.setRequesteeID(rs.getInt("amr_requestee_id"));
				req.setRequesterID(rs.getInt("amr_requester_ID"));
				req.setRequestID(rs.getInt("amr_id"));
				reqs.add(req);
			}
			conn.close();
			return reqs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ArrayList<MaterialRequest> getUserRequests(ReimbursementUser requestee) {
		// TODO Auto-generated method stub
		ArrayList<MaterialRequest> reqs = new ArrayList<>();
		try {
			conn = ConnFactory.getInstance().getConnection();
			String sql = "SELECT amr_id,amr_request,amr_a_id,amr_requester_id,amr_am_id FROM APPLICATION_MATERIAL_REQUEST WHERE amr_requestee_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setInt(1, requestee.getUserID());
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				MaterialRequest req = new MaterialRequest();
				req.setAppID(rs.getInt("amr_a_id"));
				req.setMaterialID(rs.getInt("amr_am_id"));
				req.setRequest(rs.getString("amr_request"));
				req.setRequesteeID(requestee.getUserID());
				req.setRequesterID(rs.getInt("amr_requester_ID"));
				req.setRequestID(rs.getInt("amr_id"));
				reqs.add(req);
			}
			conn.close();
			return reqs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public MaterialRequest updateRequest(MaterialRequest request, ApplicationMaterial mat) {
		// TODO Auto-generated method stub
		try {
			mat = ApplicationMaterialDaoImpl.getInstance().insertMaterial(mat);
			if(mat==null) {
				return null;
			}
			conn = ConnFactory.getInstance().getConnection();
			String sql = "UPDATE APPLICATION_MATERIAL_REQUEST SET amr_am_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setInt(1, request.getMaterialID());
			ResultSet rs = ps.executeQuery();

			conn.close();
			if (rs.next()) {
				return request;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
