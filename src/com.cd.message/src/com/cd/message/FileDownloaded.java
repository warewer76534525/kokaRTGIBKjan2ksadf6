package com.cd.message;

import java.io.Serializable;
import java.util.Date;

public class FileDownloaded implements Serializable {

	private static final long serialVersionUID = 1L;
	private String from;
	private String url;
	private Date time;
	private String downloadUrl;

	public FileDownloaded(String from, String url, String downloadUrl) {
		super();
		this.from = from;
		this.url = url;
		this.downloadUrl = downloadUrl;
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

	public String getDownloadUrl() {
		return downloadUrl;
	}

	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
	}

	@Override
	public String toString() {
		return "FileDownloaded [from=" + from + ", url=" + url + ", time="
				+ time + ", downloadUrl=" + downloadUrl + "]";
	}

}
