package com.cd.message;

import java.io.Serializable;
import java.util.Date;

public class RemoveFile implements Serializable {

	private static final long serialVersionUID = 1L;
	private String from;
	private String fileName;
	private Date time;

	public RemoveFile(String from, String fileName) {
		super();
		this.from = from;
		this.fileName = fileName;
		this.time = new Date();
	}

	/**
	 * @return
	 * @uml.property  name="from"
	 */
	public String getFrom() {
		return from;
	}

	/**
	 * @param from
	 * @uml.property  name="from"
	 */
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
		return "RemoveFile [from=" + from + ", fileName=" + fileName
				+ ", time=" + time + "]";
	}

	

}
