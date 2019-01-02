package com.revature.daoImplementation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.revature.connection.ConnFactory;
import com.revature.daos.ApplicationMaterialDao;
import com.revature.models.Application;
import com.revature.models.ApplicationMaterial;

import oracle.jdbc.OracleTypes;

public class ApplicationMaterialDaoImpl implements ApplicationMaterialDao {
	private Connection conn;
	private static ApplicationMaterialDaoImpl applicationMaterialDao = new ApplicationMaterialDaoImpl();

	private ApplicationMaterialDaoImpl() {

	}

	public static ApplicationMaterialDaoImpl getInstance() {
		return applicationMaterialDao;
	}

	@Override
	public ApplicationMaterial insertMaterial(ApplicationMaterial material) {
		// TODO Auto-generated method stub
		conn = ConnFactory.getInstance().getConnection();

		try {
			File blob = material.getFile();
			FileInputStream in = new FileInputStream(blob);

			
			String sql = "BEGIN INSERT INTO APPLICATION_MATERIAL (am_id,am_a_id,am_description,am_material,am_filename)"
					+ " VALUES(NULL,?,?,?,?) RETURNING am_id INTO ?;  END;";

			CallableStatement cs = conn.prepareCall(sql);
			cs.setInt(1, material.getAppID());
			cs.setString(2, material.getDesc());
			cs.setBinaryStream(3, in, (int)blob.length()); 
			cs.setString(4, material.getFileName());
			cs.registerOutParameter(4, OracleTypes.NUMBER); // specifies the index created by the trigger that
			cs.execute();
			int id = cs.getInt(5);

//			if (commit) {
//				conn.commit();
//			}

			if (id >= 1) {
				material.setAppMatID(id);
				conn.close();
				return material;
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ArrayList<ApplicationMaterial> getApplicationMaterials(Application app) {
		// TODO Auto-generated method stub
		ArrayList<ApplicationMaterial> ams = new ArrayList<>();
		try {
			conn = ConnFactory.getInstance().getConnection();
			String sql = "SELECT am_id,am_a_id,am_description,am_material,am_filename FROM APPLICATION_MATERIAL WHERE am_a_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, app.getApplicationID());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ApplicationMaterial am = new ApplicationMaterial();
				am.setAppMatID(rs.getInt(1));
				am.setAppID(rs.getInt(2));
				am.setDesc(rs.getString(3));
				am.setFileName(rs.getString(5));
				File file = new File("temp");
				Blob blob = rs.getBlob(4);//cast with (Blob) if required. Blob from resultSet as rs.getBlob(index). 
				InputStream in = blob.getBinaryStream();
				OutputStream out = new FileOutputStream(file);
				byte[] buff = new byte[4096];  // how much of the blob to read/write at a time
				int len = 0;

				while ((len = in.read(buff)) != -1) {
				    out.write(buff, 0, len);
				}
				
				am.setFile(file);
				ams.add(am);
			}
			conn.close();
			return ams;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
