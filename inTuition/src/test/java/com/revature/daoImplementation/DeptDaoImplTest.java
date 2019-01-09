package com.revature.daoImplementation;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.revature.models.Department;

class DeptDaoImplTest {
	DeptDaoImpl deptDao = DeptDaoImpl.getInstance();
	@Test
	void testGetDepartments() {
		ArrayList<String> depts = deptDao.getDepartments();
		System.out.println(depts);
		assert(depts.size()>=1);
	}
	@Test
	void testGetDepartmentsAndIds() {
		ArrayList<Department> depts = deptDao.getDepartmentsAndIds();
		System.out.println(depts);
		assert(depts.size()>=1);
	}

}
