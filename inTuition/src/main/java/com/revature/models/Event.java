package com.revature.models;

import java.sql.Timestamp;

public class Event {
	private String title,gradeFormat,passingGrade,typeDescription,gradeTypeDesc;
	private int coverage,eventTypeID,eventGradeFormatID,id;
	private Timestamp startDate,endDate;

	
	@Override
	public String toString() {
		return "Event [title=" + title + ", gradeFormat=" + gradeFormat + ", passingGrade=" + passingGrade
				+ ", typeDescription=" + typeDescription + ", gradeTypeDesc=" + gradeTypeDesc + ", coverage=" + coverage
				+ ", eventTypeID=" + eventTypeID + ", eventGradeFormatID=" + eventGradeFormatID + ", id=" + id
				+ ", startDate=" + startDate + ", endDate=" + endDate + "]";
	}
	public void setEventType(EventType type) {
		typeDescription = type.getDesc();
		eventTypeID = type.getId();
		coverage = type.getCoverage();
	}
	public void setGradeFormat(EventGradeFormat egf) {
		gradeFormat = egf.getFormat();
		gradeTypeDesc = egf.getDesc();
		eventGradeFormatID = egf.getId();
	}
	public String getGradeTypeDesc() {
		return gradeTypeDesc;
	}
	public void setGradeTypeDesc(String gradeTypeDesc) {
		this.gradeTypeDesc = gradeTypeDesc;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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

	public int getCoverage() {
		return coverage;
	}
	public void setCoverage(int coverage) {
		this.coverage = coverage;
	}
	public Timestamp getStartDate() {
		return startDate;
	}
	public void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
	}
	public Timestamp getEndDate() {
		return endDate;
	}
	public void setEndDate(Timestamp endDate) {
		this.endDate = endDate;
	}
	

}
