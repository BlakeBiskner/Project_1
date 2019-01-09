package com.revature.daoImplementation;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.revature.models.UserJob;

class UserJobDaoImplTest {
	UserJobDaoImpl jobDao = UserJobDaoImpl.getInstance();
	
	
	@Test
	void test() {
		ArrayList<UserJob> jobs = jobDao.getUserJobs();
		jobs.forEach(e->{
			System.out.println(e);
		});
		assert(jobs.size()>0);
	}

}
