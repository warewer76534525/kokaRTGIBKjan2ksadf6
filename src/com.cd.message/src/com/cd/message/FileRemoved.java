package com.cd.message;

import java.io.Serializable;
import java.util.Date;

public class FileRemoved implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String from;
	private String fileName;
	private Date time;
	
	
	public FileRemoved(String from, String fileName) {
		super();
		this.from = from;
		this.fileName = fileName;
		this.time = new Date();
	}


	public String getFrom() {
		return from;
	}


	public void setFrom(String from) {
		this.from = from;
	}


	public String getFileName() {
		return fileName;
	}


	public void setFileName(String fileName) {
		this.fileName = fileName;
	}


	public Date getTime() {
		return time;
	}


	public void setTime(Date time) {
		this.time = time;
	}


	@Override
	public String toString() {
		return "FileRemoved [from=" + from + ", fileName=" + fileName
				+ ", time=" + time + "]";
	}
	
	
	
	
}
