package com.revature.models;

import java.util.ArrayList;

public class FormSession{
	
	private ReimbursementUser user;
	private ArrayList<Application> userApps;
	private ArrayList<EventType> eventTypes;
	private ArrayList<EventGradeFormat> eventGradeFormats;
	private ArrayList<Application> reviewApps;
	private ArrayList<ReimbursementUser> reviewAppUsers;
	private ArrayList<ApplicationMaterial> mats;
	
	
	
	public ArrayList<ApplicationMaterial> getMats() {
		return mats;
	}
	public void setMats(ArrayList<ApplicationMaterial> mats) {
		this.mats = mats;
	}
	public void setReviewAppUsers(ArrayList<ReimbursementUser> reviewAppUsers) {
		this.reviewAppUsers = reviewAppUsers;
	}
	public FormSession() {
		
	}
	// Home Page
	public FormSession(ReimbursementUser user,ArrayList<Application> userApps, ArrayList<Application> reviewApps, ArrayList<ReimbursementUser> reviewAppUsers) {
		this.user=user;
		this.userApps=userApps;
		this.reviewApps=reviewApps;
		this.reviewAppUsers=reviewAppUsers;
	}
	
	public FormSession(ReimbursementUser user,ArrayList<Application> userApps) {
		this.user=user;
		this.userApps=userApps;
	}
	
	// New Application
	public FormSession(ArrayList<EventType> eventTypes, ArrayList<EventGradeFormat> eventGradeFormats) {
		this.eventTypes=eventTypes;
		this.eventGradeFormats=eventGradeFormats;
	}
	
	public ReimbursementUser getUser() {
		return user;
	}
	
	public ArrayList<Application> getUserApps(){
		return userApps;
	}
	public ArrayList<EventType> getEventTypes(){
		return eventTypes;
	}
	
	public ArrayList<EventGradeFormat> getEventGradeFormats(){
		return eventGradeFormats;
	}
	
	public ArrayList<Application> getReviewApps(){
		return reviewApps;
	}
	
	public ArrayList<ReimbursementUser> getReviewAppUsers(){
		return reviewAppUsers;
	}
	
	public void setUser(ReimbursementUser user) {
		this.user=user;
	}
	
	public void setUserApps(ArrayList<Application> userApps) {
		this.userApps=userApps;
	}
	
	public void setEventTypes(ArrayList<EventType> eventTypes) {
		this.eventTypes=eventTypes;
	}
	
	public void setEventGradeFormats(ArrayList<EventGradeFormat> eventGradeFormats) {
		this.eventGradeFormats=eventGradeFormats;
	}
	
	public void setReviewApps(ArrayList<Application> reviewApps) {
		this.reviewApps=reviewApps;
	}
	
	public void setReviewUserApps(ArrayList<ReimbursementUser> reviewAppUsers) {
		this.reviewAppUsers=reviewAppUsers;
	}
	@Override
	public String toString() {
		return "FormSession [user=" + user + ", userApps=" + userApps + ", eventTypes=" + eventTypes
				+ ", eventGradeFormats=" + eventGradeFormats + ", reviewApps=" + reviewApps + ", reviewAppUsers="
				+ reviewAppUsers + "]";
	}
}
