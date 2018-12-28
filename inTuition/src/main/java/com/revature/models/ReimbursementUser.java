package com.revature.models;

public class ReimbursementUser {
	private String username, firstname, lastname, email, dept,password,job,jobDesc;
	private String dsUsername, dsFirstname, dsLastname, dsEmail, dsDept,dsJob,dsJobDesc;
	private int userID, permissionType,deptID,userTypeID;
	private int dsID, dsPermissionType;
	private boolean hasEmail,hasUrgentEmail,accountApproved;
	
	public ReimbursementUser() {
		hasEmail = false;
		hasUrgentEmail = false;
		accountApproved = true;
		deptID = 21;  //set default department as administration
		dsID = 99999; //set default direct supervisor as stan lee
		userTypeID = 22;
	}
	
	public void setDirectSupervisor(ReimbursementUser ds) {
		dsUsername = ds.getUsername();
		dsFirstname = ds.getFirstname();
		dsLastname = ds.getLastname();
		dsEmail = ds.getEmail();
		dsDept = ds.getDept();
		dsJob = ds.getJob();
		dsJobDesc = ds.getJobDesc();
		dsID = ds.getUserID();
		dsPermissionType = ds.getPermissionType();
	}
	public boolean isHasUrgentEmail() {
		return hasUrgentEmail;
	}
	public void setHasUrgentEmail(boolean hasUrgentEmail) {
		this.hasUrgentEmail = hasUrgentEmail;
	}
	public int getDeptID() {
		return deptID;
	}
	public void setDeptID(int deptID) {
		this.deptID = deptID;
	}
	public int getUserTypeID() {
		return userTypeID;
	}
	public void setUserTypeID(int userTypeID) {
		this.userTypeID = userTypeID;
	}
	public boolean isAccountApproved() {
		return accountApproved;
	}
	public void setAccountApproved(boolean accountApproved) {
		this.accountApproved = accountApproved;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public int getDsID() {
		return dsID;
	}
	public void setDsID(int dsID) {
		this.dsID = dsID;
	}
	public int getDsPermissionType() {
		return dsPermissionType;
	}
	public void setDsPermissionType(int dsPermissionType) {
		this.dsPermissionType = dsPermissionType;
	}	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public String getJobDesc() {
		return jobDesc;
	}
	public void setJobDesc(String jobDesc) {
		this.jobDesc = jobDesc;
	}
	public String getDsUsername() {
		return dsUsername;
	}
	public void setDsUsername(String dsUsername) {
		this.dsUsername = dsUsername;
	}
	public String getDsFirstname() {
		return dsFirstname;
	}
	public void setDsFirstname(String dsFirstname) {
		this.dsFirstname = dsFirstname;
	}
	public String getDsLastname() {
		return dsLastname;
	}
	public void setDsLastname(String dsLastname) {
		this.dsLastname = dsLastname;
	}
	public String getDsEmail() {
		return dsEmail;
	}
	public void setDsEmail(String dsEmail) {
		this.dsEmail = dsEmail;
	}
	public String getDsDept() {
		return dsDept;
	}
	public void setDsDept(String dsDept) {
		this.dsDept = dsDept;
	}
	public String getDsJob() {
		return dsJob;
	}
	public void setDsJob(String dsJob) {
		this.dsJob = dsJob;
	}
	public String getDsJobDesc() {
		return dsJobDesc;
	}
	public void setDsJobDesc(String dsJobDesc) {
		this.dsJobDesc = dsJobDesc;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public int getPermissionType() {
		return permissionType;
	}
	public void setPermissionType(int permissionType) {
		this.permissionType = permissionType;
	}
	public boolean isHasEmail() {
		return hasEmail;
	}
	public void setHasEmail(boolean hasEmail) {
		this.hasEmail = hasEmail;
	}
	@Override
	public String toString() {
		return "ReimbursementUser [username=" + username + ", firstname=" + firstname + ", lastname=" + lastname
				+ ", email=" + email + ", dept=" + dept + ", password=" + password + ", job=" + job + ", jobDesc="
				+ jobDesc + ", dsUsername=" + dsUsername + ", dsFirstname=" + dsFirstname + ", dsLastname=" + dsLastname
				+ ", dsEmail=" + dsEmail + ", dsDept=" + dsDept + ", dsJob=" + dsJob + ", dsJobDesc=" + dsJobDesc
				+ ", userID=" + userID + ", permissionType=" + permissionType + ", deptID=" + deptID + ", userTypeID="
				+ userTypeID + ", dsID=" + dsID + ", dsPermissionType=" + dsPermissionType + ", hasEmail=" + hasEmail
				+ ", hasUrgentEmail=" + hasUrgentEmail + ", accountApproved=" + accountApproved + "]";
	}
	

}
