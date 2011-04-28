package com.cd.message;

import java.io.Serializable;
import java.util.Date;

public class DownloadCompleted implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String from;
	private String url;
	private Date time;
	private String downloadUrl;
	
	
	public DownloadCompleted(String from, String url, String downloadUrl) {
		super();
		this.from = from;
		this.url = url;
		this.downloadUrl = downloadUrl;
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
	/**
	 * @return
	 * @uml.property  name="url"
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * @param url
	 * @uml.property  name="url"
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return
	 * @uml.property  name="time"
	 */
	public Date getTime() {
		return time;
	}

	/**
	 * @param time
	 * @uml.property  name="time"
	 */
	public void setTime(Date time) {
		this.time = time;
	}
	
	public String getDownloadUrl() {
		return downloadUrl;
	}

	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
	}

	@Override
	public String toString() {
		return "DownloadCompleted [from=" + from + ", url=" + url + ", time="
				+ time + ", downloadUrl=" + downloadUrl + "]";
	}

	
	
	
}
