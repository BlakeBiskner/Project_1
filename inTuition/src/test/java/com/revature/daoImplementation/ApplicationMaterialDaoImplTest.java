package com.revature.daoImplementation;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.revature.models.Application;
import com.revature.models.ApplicationMaterial;
import com.revature.models.ReimbursementUser;

class ApplicationMaterialDaoImplTest {
	UserDaoImpl userDao = UserDaoImpl.getInstance();
	ApplicationDaoImpl appDao = ApplicationDaoImpl.getInstance();
	ApplicationMaterialDaoImpl appMatDao = ApplicationMaterialDaoImpl.getInstance();
	
	@Test
	void test() {
	ReimbursementUser user = userDao.getUser("sansa");
	Application app = appDao.getUserApplications(user).get(0);
	System.out.println(app);
	
	ArrayList<ApplicationMaterial> appMats = appMatDao.getApplicationMaterials(app);
	System.out.println(appMats);
	
	
	assert(appMats!=null);
	}

}
