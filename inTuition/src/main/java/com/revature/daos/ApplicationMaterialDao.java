package com.revature.daos;

import java.util.ArrayList;

import com.revature.models.Application;
import com.revature.models.ApplicationMaterial;

public interface ApplicationMaterialDao {
	public ApplicationMaterial insertMaterial(ApplicationMaterial material);
	
	
	public ArrayList<ApplicationMaterial> getApplicationMaterials(Application app);
}
