package com.revature.models;

import java.sql.Timestamp;

public class Application {
	private int applicationID, eventID, userID,gradeID;
	private double reimbursementAmount;
	private String comments,gradeComments,grade = null;
	private String eventTitle,gradeFormat,passingGrade,typeDescription,gradeTypeDesc,eventGradeFormatDesc = null;
	private double cost;
	private int typeCoverage,eventTypeID,eventGradeFormatID;
	private Timestamp eventStartDate,eventEndDate = null;
	
	public void setEvent(Event event) {
		eventID = event.getId();
		eventTitle = event.getTitle();
		gradeFormat = event.getGradeFormat();
		passingGrade = event.getPassingGrade();
		typeDescription = event.getTypeDescription();
		gradeTypeDesc = event.getGradeTypeDesc();
		eventGradeFormatDesc = event.getGradeTypeDesc();
		typeCoverage = event.getCoverage();
		eventTypeID = event.getEventTypeID();
		eventGradeFormatID = event.getEventGradeFormatID();
		eventStartDate = event.getStartDate();
		eventEndDate = event.getEndDate();
		cost = event.getCost();
	}
	@Override
	public String toString() {
		return "Application [applicationID=" + applicationID + ", eventID=" + eventID + ", userID=" + userID
				+ ", gradeID=" + gradeID + ", reimbursementAmount=" + reimbursementAmount + ", comments=" + comments
				+ ", gradeComments=" + gradeComments + ", grade=" + grade + ", eventTitle=" + eventTitle
				+ ", gradeFormat=" + gradeFormat + ", passingGrade=" + passingGrade
				+ ", typeDescription=" + typeDescription + ", gradeTypeDesc=" + gradeTypeDesc
				+ ", eventGradeFormatDesc=" + eventGradeFormatDesc + ", cost=" + cost + ", typeCoverage=" + typeCoverage
				+ ", eventTypeID=" + eventTypeID + ", eventGradeFormatID=" + eventGradeFormatID + ", eventStartDate="
				+ eventStartDate + ", eventEndDate=" + eventEndDate + ", date=" + date + "]";
	}
	public String getEventGradeFormatDesc() {
		return eventGradeFormatDesc;
	}
	public void setEventGradeFormatDesc(String eventGradeFormatDesc) {
		this.eventGradeFormatDesc = eventGradeFormatDesc;
	}
	
	
	public Timestamp getEventStartDate() {
		return eventStartDate;
	}
	public void setEventStartDate(Timestamp eventStartDate) {
		this.eventStartDate = eventStartDate;
	}
	public Timestamp getEventEndDate() {
		return eventEndDate;
	}
	public void setEventEndDate(Timestamp eventEndDate) {
		this.eventEndDate = eventEndDate;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public int getTypeCoverage() {
		return typeCoverage;
	}
	public void setTypeCoverage(int typeCoverage) {
		this.typeCoverage = typeCoverage;
	}
	public int getEventTypeID() {
		return eventTypeID;
	}
	public void setEventTypeID(int eventTypeID) {
		this.eventTypeID = eventTypeID;
	}
	public int getEventGradeFormatID() {
		return eventGradeFormatID;
	}
	public void setEventGradeFormatID(int eventGradeFormatID) {
		this.eventGradeFormatID = eventGradeFormatID;
	}
	public String getEventTitle() {
		return eventTitle;
	}
	public void setEventTitle(String eventTitle) {
		this.eventTitle = eventTitle;
	}

	public String getGradeFormat() {
		return gradeFormat;
	}
	public void setGradeFormat(String gradeFormat) {
		this.gradeFormat = gradeFormat;
	}
	public String getPassingGrade() {
		return passingGrade;
	}
	public void setPassingGrade(String passingGrade) {
		this.passingGrade = passingGrade;
	}
	public String getTypeDescription() {
		return typeDescription;
	}
	public void setTypeDescription(String typeDescription) {
		this.typeDescription = typeDescription;
	}
	public String getGradeTypeDesc() {
		return gradeTypeDesc;
	}
	public void setGradeTypeDesc(String gradeTypeDesc) {
		this.gradeTypeDesc = gradeTypeDesc;
	}
	public int getGradeID() {
		return gradeID;
	}
	public void setGradeID(int gradeID) {
		this.gradeID = gradeID;
	}
	
	public String getGradeComments() {
		return gradeComments;
	}
	public void setGradeComments(String gradeComments) {
		this.gradeComments = gradeComments;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	private Timestamp date;
	
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	public int getApplicationID() {
		return applicationID;
	}
	public void setApplicationID(int applicationID) {
		this.applicationID = applicationID;
	}
	public int getEventID() {
		return eventID;
	}
	public void setEventID(int eventID) {
		this.eventID = eventID;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public double getReimbursementAmount() {
		return reimbursementAmount;
	}
	public void setReimbursementAmount(double reimbursementAmount) {
		this.reimbursementAmount = reimbursementAmount;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	
}
