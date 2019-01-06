package com.revature.models;

import java.util.ArrayList;

public class RegistrationInfo {
	private ArrayList<Department> depts;
	private ArrayList<UserJob> jobs;
	private ArrayList<ReimbursementUser> users;
	public ArrayList<Department> getDepts() {
		return depts;
	}
	public void setDepts(ArrayList<Department> depts) {
		this.depts = depts;
	}
	public ArrayList<UserJob> getJobs() {
		return jobs;
	}
	public void setJobs(ArrayList<UserJob> jobs) {
		this.jobs = jobs;
	}
	public ArrayList<ReimbursementUser> getUsers() {
		return users;
	}
	public void setUsers(ArrayList<ReimbursementUser> users) {
		this.users = users;
	}
	@Override
	public String toString() {
		return "RegistrationInfo [depts=" + depts + ", jobs=" + jobs + ", users=" + users + "]";
	}
	
	
}
