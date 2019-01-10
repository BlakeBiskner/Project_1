package com.revature.controller;

import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.daoImplementation.ApplicationDaoImpl;
import com.revature.daoImplementation.ApplicationMaterialDaoImpl;
import com.revature.daoImplementation.UserDaoImpl;
import com.revature.models.Application;
import com.revature.models.ApplicationMaterial;
import com.revature.models.ReimbursementUser;

public class DownloadController {

	public static String downloadFile(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("In download controller");
		Enumeration<String> paramNames = request.getParameterNames();
		while (paramNames.hasMoreElements()) {
			String nextName = paramNames.nextElement();
			System.out.println(nextName);
			System.out.println(request.getParameter(nextName));
		}
		int fileID = Integer.parseInt(request.getParameter("fileID"));
		ApplicationMaterial mat = ApplicationMaterialDaoImpl.getInstance().getApplicationMaterial(fileID, response);
		System.out.println(mat);
		response.setContentType("APPLICATION/OCTET-STREAM");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + mat.getFileName() + "\"");


		return "download";
		
		
		
		//		ApplicationMaterial mat = null;

		
		
//		
//		ApplicationDaoImpl appDao = ApplicationDaoImpl.getInstance();
//		for (ReimbursementUser user : users) {
//			ArrayList<Application> apps = appDao.getUserApplications(UserDaoImpl.getInstance().getUser("sansa"));
//			for (int i = 0; i < apps.size() && mat == null; i++) {
//				mat = ApplicationMaterialDaoImpl.getInstance().getApplicationMaterials(apps.get(i), response);
//				System.out.println(appMats.size());
//				if (appMats.size() > 0) {
//					mat = appMats.get(0);
//					System.out.println(mat);
//				}
//			}
//			if(mat!=null) {
//				break;
//			}
//		}


		
		
		
		
	}
}
