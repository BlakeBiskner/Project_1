package com.revature.models;

import java.sql.Timestamp;

public class Event {
	private String title,format,gradeFormat,passingGrade,grade,typeDescription,gradeTypeDesc;
	public String getGradeTypeDesc() {
		return gradeTypeDesc;
	}
	public void setGradeTypeDesc(String gradeTypeDesc) {
		this.gradeTypeDesc = gradeTypeDesc;
	}
	double cost;
	int coverage,eventTypeID,eventGradeFormatID,id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	Timestamp startDate,endDate;
	public int getEventTypeID() {
		return eventTypeID;
	}
	public void setEventTypeID(int eventTypeID) {
		this.eventTypeID = eventTypeID;
	}
	@Override
	public String toString() {
		return "Event [title=" + title + ", format=" + format + ", gradeFormat=" + gradeFormat + ", passingGrade="
				+ passingGrade + ", grade=" + grade + ", typeDescription=" + typeDescription + ", gradeTypeDesc="
				+ gradeTypeDesc + ", cost=" + cost + ", coverage=" + coverage + ", eventTypeID=" + eventTypeID
				+ ", eventGradeFormatID=" + eventGradeFormatID + ", id=" + id + ", startDate=" + startDate
				+ ", endDate=" + endDate + "]";
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
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
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
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getTypeDescription() {
		return typeDescription;
	}
	public void setTypeDescription(String typeDescription) {
		this.typeDescription = typeDescription;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
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
