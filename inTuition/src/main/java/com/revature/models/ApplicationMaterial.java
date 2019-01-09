package com.revature.models;

import java.io.File;
import java.io.InputStream;
import java.sql.Blob;

public class ApplicationMaterial {
	Blob blob;
	File file;
	InputStream is;
	String desc, fileName;
	int appID, appMatID;
	
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public Blob getBlob() {
		return blob;
	}
	public void setBlob(Blob blob) {
		this.blob = blob;
	}
	public InputStream getIs() {
		return is;
	}
	public void setIs(InputStream is) {
		this.is = is;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
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
		return "ApplicationMaterial [file=" + blob + ", desc=" + desc + ", appID=" + appID + ", appMatID=" + appMatID
				+ "]";
	}
	
}
