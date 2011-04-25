package com.cd.message;

import java.io.Serializable;
import java.util.Date;

public class DownloadError implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String from;
	private String url;
	private String message;
	private Date time;
	
	public DownloadError(String from, String url, String message) {
		super();
		this.from = from;
		this.url = url;
		this.message = message;
		time = new Date();
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
	

}
