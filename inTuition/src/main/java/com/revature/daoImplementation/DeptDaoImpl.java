package com.revature.daoImplementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.connection.ConnFactory;
import com.revature.daos.DeptDao;

public class DeptDaoImpl implements DeptDao {
	private Connection conn;
	private static DeptDaoImpl deptDao = new DeptDaoImpl();

	private DeptDaoImpl() {

	}

	public static DeptDaoImpl getInstance() {
		return deptDao;
	}

	@Override
	public ArrayList<String> getDepartments() {
		// TODO Auto-generated method stub
		ArrayList<String> depts = new ArrayList<String>();
		try {
			conn = ConnFactory.getInstance().getConnection();
			String sql = "SELECT dept_name FROM DEPARTMENT";
			PreparedStatement ps = conn.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				depts.add(rs.getString(1));
			}
			conn.close();
			return depts;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
