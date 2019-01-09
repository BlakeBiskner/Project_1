package com.revature.models;

public class EventGradeFormat {
	private String desc,format;
	private int id;
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "EventGradeType [desc=" + desc + ", format=" + format + ", id=" + id + "]";
	}
	public EventGradeFormat(String desc, String format, int id) {
		super();
		this.desc = desc;
		this.format = format;
		this.id = id;
	}
}
