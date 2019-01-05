package com.revature.models;

import java.util.ArrayList;

public class FormSession{
	
//	private ReimbursementUser user;
	
	private ReimbursementUser user;
	private ArrayList<EventType> eventTypes;
	private ArrayList<EventGradeFormat> eventGradeFormats;
	
	public FormSession() {
		
	}
	
	public FormSession(ReimbursementUser user,ArrayList<EventType> eventTypes, ArrayList<EventGradeFormat> eventGradeFormats) {
		this.eventTypes=eventTypes;
		this.eventGradeFormats=eventGradeFormats;
		this.user=user;
	}
	
	public ReimbursementUser getUser() {
		return user;
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
	
	public void setEventTypes(ArrayList<EventType> eventTypes) {
		this.eventTypes=eventTypes;
	}
	
	public void setEventGradeFormats(ArrayList<EventGradeFormat> eventGradeFormats) {
		this.eventGradeFormats=eventGradeFormats;
	}

	@Override
	public String toString() {
		return "FormSession [user=" + user + ", eventTypes=" + eventTypes + ", eventGradeFormats=" + eventGradeFormats
				+ "]";
	}
}
