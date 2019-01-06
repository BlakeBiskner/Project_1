package com.revature.models;

import java.sql.Timestamp;
import java.util.HashMap;

import com.revature.exceptions.InvalidInputException;

public class Application {
	private int applicationID, eventID, userID,participationID;
	private double reimbursementAmount;
	private String justification,gradeComments,grade = null;
	private String eventTitle,gradeFormat,passingGrade,typeDescription,gradeTypeDesc,eventGradeFormatDesc,status = null;
	private double cost;
	private int typeCoverage,eventTypeID,eventGradeFormatID,nextApproverID;
	private Timestamp eventStartDate,eventEndDate = null;
	private int timeMissed,statusID;
	private Boolean passed = null;
	private static HashMap<String,Integer> statusTypes = new HashMap<String,Integer>();
	public Application() {
		if (statusTypes.size()==0) {
			statusTypes.put("Denied",0);
			statusTypes.put("Submitted",1);
			statusTypes.put("Approved by Direct Supervisor", 2);
			statusTypes.put("Approved by Department Head",3);
			statusTypes.put("Approved",4);
		}
	}
	@Override
	public String toString() {
		return "Application [applicationID=" + applicationID + ", eventID=" + eventID + ", userID=" + userID
				+ ", participationID=" + participationID + ", reimbursementAmount=" + reimbursementAmount
				+ ", comments=" + justification + ", gradeComments=" + gradeComments + ", grade=" + grade + ", eventTitle="
				+ eventTitle + ", gradeFormat=" + gradeFormat + ", passingGrade=" + passingGrade + ", typeDescription="
				+ typeDescription + ", gradeTypeDesc=" + gradeTypeDesc + ", eventGradeFormatDesc="
				+ eventGradeFormatDesc + ", status=" + status + ", cost=" + cost + ", typeCoverage=" + typeCoverage
				+ ", eventTypeID=" + eventTypeID + ", eventGradeFormatID=" + eventGradeFormatID + ", nextApproverID="
				+ nextApproverID + ", eventStartDate=" + eventStartDate + ", eventEndDate=" + eventEndDate
				+ ", timeMissed=" + timeMissed + ", statusID=" + statusID + ", passed=" + passed + ", date=" + date
				+ "]";
	}
	public int getParticipationID() {
		return participationID;
	}
	public void setParticipationID(int participationID) {
		this.participationID = participationID;
	}
	public Boolean getPassed() {
		return passed;
	}
	public void setPassed(boolean passed) {
		this.passed = passed;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		if(!statusTypes.containsKey(status)) {
			throw new InvalidInputException(status + " is not a valid status.");		
		}
		else {
			this.status = status;
			this.statusID = statusTypes.get(status);
		}
	}
	public int getStatusID() {
		if(status==null) {
			return 1;   //This indicates that an application has a status of pending.
		}
		return statusID;
	}
	public void setStatusID(int statusID) {
		this.statusID = statusID;
	}
	public int getTimeMissed() {
		return timeMissed;
	}
	public void setTimeMissed(int timeMissed) {
		this.timeMissed = timeMissed;
	}
	public int getNextApproverID() {
		return nextApproverID;
	}
	public void setNextApproverID(int nextApprovedID) {
		this.nextApproverID = nextApprovedID;
	}
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
	public String getJustification() {
		return justification;
	}
	public void setJustification(String comments) {
		this.justification = comments;
	}
	
}
