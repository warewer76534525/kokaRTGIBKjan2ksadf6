package com.cd.message;

import java.io.Serializable;
import java.util.Date;

public class DownlodCompleted implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String from;
	private String url;
	private Date time;
	
	
	public DownlodCompleted(String from, String url) {
		super();
		this.from = from;
		this.url = url;
		this.time = new Date();
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

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "DownlodCompleted [from=" + from + ", url=" + url + ", time="
				+ time + "]";
	}
	
	
}
