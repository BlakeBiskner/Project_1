package com.revature.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.daoImplementation.DeptDaoImpl;
import com.revature.models.Department;

public class WelcomeController {

	public static String apply(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("In Welcome Controller");
		DeptDaoImpl deptDao = DeptDaoImpl.getInstance();
		ArrayList<Department> depts = deptDao.getDepartmentsAndIds(); 
//		try {
////			response.getWriter().write(new ObjectMapper().writeValueAsString(session));
////			response.getWriter().write(new ObjectMapper().writeValueAsString(user));
//		} catch (JsonProcessingException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}

		// TODO Auto-generated method stub
		return null;
	}

}
