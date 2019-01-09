package com.revature.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.daoImplementation.RequestDaoImpl;
import com.revature.models.MaterialRequest;

public class RequestMoreInformationController {

	public static String requestMoreInformation(HttpServletRequest request, HttpServletResponse response) {
		MaterialRequest req = new MaterialRequest();
		RequestDaoImpl reqDao = RequestDaoImpl.getInstance();
		
		//Set request values here
		
		
		req = reqDao.insertRequest(req);
		if(req!=null) {
			//everything is good
		}
		//everything is bad
		return null;
	}
}
