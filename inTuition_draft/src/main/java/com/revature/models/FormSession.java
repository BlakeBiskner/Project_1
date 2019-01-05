package com.revature.models;

import java.util.ArrayList;

public class FormSession{
	
	private ReimbursementUser user;
	private ArrayList<Application> userApps;
	private ArrayList<EventType> eventTypes;
	private ArrayList<EventGradeFormat> eventGradeFormats;
	
	
	public FormSession() {
		
	}
	// Home Page
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
	
	@Override
	public String toString() {
		return "FormSession [user=" + user + ", userApps=" + userApps + ", eventTypes=" + eventTypes
				+ ", eventGradeFormats=" + eventGradeFormats + "]";
	}
}
