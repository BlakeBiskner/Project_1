package com.revature.models;

public class EventType {
	private String desc;
	private int coverage;
	private int id;
	public EventType(int id, String desc,int coverage) {
		this.id = id;
		this.desc = desc;
		this.coverage = coverage;
	}
	@Override
	public String toString() {
		return "EventType [desc=" + desc + ", coverage=" + coverage + ", id=" + id + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public EventType(String desc, int coverage) {
		this.desc= desc;
		this.coverage = coverage;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public int getCoverage() {
		return coverage;
	}
	public void setCoverage(int coverage) {
		this.coverage = coverage;
	}
	
}
