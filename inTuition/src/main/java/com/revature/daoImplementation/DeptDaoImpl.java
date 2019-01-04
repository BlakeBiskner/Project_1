package com.revature.daoImplementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.connection.ConnFactory;
import com.revature.daos.DeptDao;
import com.revature.models.Department;

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
				depts.add(rs.getString("dept_name"));
			}
			conn.close();
			return depts;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<Department> getDepartmentsAndIds() {
		// TODO Auto-generated method stub
		ArrayList<Department> depts = new ArrayList<>();
		try {
			conn = ConnFactory.getInstance().getConnection();
			String sql = "SELECT dept_name,dept_id FROM DEPARTMENT";
			PreparedStatement ps = conn.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Department dept = new Department();
				dept.setName(rs.getString("dept_name"));
				dept.setId(rs.getInt("dept_id"));
				depts.add(dept);
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
