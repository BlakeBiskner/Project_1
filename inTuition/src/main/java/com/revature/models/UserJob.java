package com.revature.models;

public class UserJob {
	private int jobID, jobTypeID;
	private String job,jobType,jobDescription = null;
	public int getJobID() {
		return jobID;
	}
	public void setJobID(int jobID) {
		this.jobID = jobID;
	}
	public int getJobTypeID() {
		return jobTypeID;
	}
	public void setJobTypeID(int jobTypeID) {
		this.jobTypeID = jobTypeID;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public String getJobType() {
		return jobType;
	}
	public void setJobType(String jobType) {
		this.jobType = jobType;
	}
	public String getJobDescription() {
		return jobDescription;
	}
	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}
	@Override
	public String toString() {
		return "UserJob [jobID=" + jobID + ", jobTypeID=" + jobTypeID + ", job=" + job + ", jobType=" + jobType
				+ ", jobDescription=" + jobDescription + "]";
	}
	
	
	
}
