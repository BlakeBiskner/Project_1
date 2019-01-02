package com.revature.models;

import java.io.File;

public class ApplicationMaterial {
	File file;
	String desc, fileName;
	int appID, appMatID;
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public int getAppID() {
		return appID;
	}
	public void setAppID(int appID) {
		this.appID = appID;
	}
	public int getAppMatID() {
		return appMatID;
	}
	public void setAppMatID(int appMatID) {
		this.appMatID = appMatID;
	}
	@Override
	public String toString() {
		return "ApplicationMaterial [file=" + file + ", desc=" + desc + ", appID=" + appID + ", appMatID=" + appMatID
				+ "]";
	}
	
}
