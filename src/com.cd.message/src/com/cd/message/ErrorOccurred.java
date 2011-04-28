package com.cd.message;

import java.io.Serializable;
import java.util.Date;

public class ErrorOccurred implements Serializable {

	private static final long serialVersionUID = 1L;
	private String from;
	private String request;
	private String message;
	private Date time;

	public ErrorOccurred(String from, String request, String message) {
		super();
		this.from = from;
		this.request = request;
		this.message = message;
		time = new Date();
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
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
