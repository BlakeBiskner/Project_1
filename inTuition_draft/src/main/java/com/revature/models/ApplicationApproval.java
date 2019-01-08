package com.revature.models;

import java.sql.Timestamp;

public class ApplicationApproval {

	private int appID,approvalID,approverID;
	private String reasoning = null;
	private Boolean approval = null;
	private Timestamp approvalTime = null;
	public int getAppID() {
		return appID;
	}
	public void setAppID(int appID) {
		this.appID = appID;
	}
	public int getApprovalID() {
		return approvalID;
	}
	public void setApprovalID(int approvalID) {
		this.approvalID = approvalID;
	}
	public int getApproverID() {
		return approverID;
	}
	public void setApproverID(int approverID) {
		this.approverID = approverID;
	}
	public String getReasoning() {
		return reasoning;
	}
	public void setReasoning(String reasoning) {
		this.reasoning = reasoning;
	}
	public Boolean getApproval() {
		return approval;
	}
	public void setApproval(Boolean approval) {
		this.approval = approval;
	}
	public Timestamp getApprovalTime() {
		return approvalTime;
	}
	public void setApprovalTime(Timestamp approvalTime) {
		this.approvalTime = approvalTime;
	}
	@Override
	public String toString() {
		return "ApplicationApproval [appID=" + appID + ", approvalID=" + approvalID + ", approverID=" + approverID
				+ ", reasoning=" + reasoning + ", approval=" + approval + ", approvalTime=" + approvalTime + "]";
	}

	
}
