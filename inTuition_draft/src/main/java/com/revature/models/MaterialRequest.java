package com.revature.models;

public class MaterialRequest {
	private int requesterID, requesteeID,appID, requestID;
	private Integer materialID = null;
	private String request;
	public int getRequesterID() {
		return requesterID;
	}
	public void setRequesterID(int requesterID) {
		this.requesterID = requesterID;
	}
	public int getRequesteeID() {
		return requesteeID;
	}
	public void setRequesteeID(int requesteeID) {
		this.requesteeID = requesteeID;
	}
	public int getAppID() {
		return appID;
	}
	public void setAppID(int appID) {
		this.appID = appID;
	}
	public Integer getMaterialID() {
		return materialID;
	}
	public void setMaterialID(Integer materialID) {
		this.materialID = materialID;
	}
	public String getRequest() {
		return request;
	}
	public void setRequest(String request) {
		this.request = request;
	}
	public int getRequestID() {
		return requestID;
	}
	public void setRequestID(int requestID) {
		this.requestID = requestID;
	}
	@Override
	public String toString() {
		return "MaterialRequest [requesterID=" + requesterID + ", requesteeID=" + requesteeID + ", appID=" + appID
				+ ", requestID=" + requestID + ", materialID=" + materialID + ", request=" + request + "]";
	}
	
	
}
