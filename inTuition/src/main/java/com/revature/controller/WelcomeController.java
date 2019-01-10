package com.revature.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.daoImplementation.ApplicationDaoImpl;
import com.revature.daoImplementation.ApplicationMaterialDaoImpl;
import com.revature.daoImplementation.DeptDaoImpl;
import com.revature.daoImplementation.UserDaoImpl;
import com.revature.daoImplementation.UserJobDaoImpl;
import com.revature.models.Application;
import com.revature.models.ApplicationMaterial;
import com.revature.models.Department;
import com.revature.models.RegistrationInfo;
import com.revature.models.ReimbursementUser;
import com.revature.models.UserJob;

public class WelcomeController {

	public static String welcomeJSON(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("In Welcome Controller");


		DeptDaoImpl deptDao = DeptDaoImpl.getInstance();
		ArrayList<Department> depts = deptDao.getDepartmentsAndIds();
		ArrayList<UserJob> jobs = UserJobDaoImpl.getInstance().getUserJobs();
		ArrayList<ReimbursementUser> users = UserDaoImpl.getInstance().getAllUsers();
		RegistrationInfo regInfo = new RegistrationInfo();
		regInfo.setDepts(depts);
		regInfo.setJobs(jobs);
		regInfo.setUsers(users);
		try {
			response.getWriter().write(new ObjectMapper().writeValueAsString(regInfo));

		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// TODO Auto-generated method stub
		return null;
	}

}
