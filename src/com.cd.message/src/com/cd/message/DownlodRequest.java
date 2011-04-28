package com.cd.message;

import java.io.File;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

public class DownlodRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * @uml.property  name="from"
	 */
	private String from;
	/**
	 * @uml.property  name="url"
	 */
	private String url;
	/**
	 * @uml.property  name="time"
	 */
	private Date time;

	public DownlodRequest(String from, String url) {
		super();
		this.from = from;
		this.url = url;
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

	@Override
	public String toString() {
		return "DownlodRequest [from=" + from + ", url=" + url + ", time="
				+ time + "]";
	}

	public String getFileName() {
		URL url = null;
		try {
			url = new URL(this.url);
		} catch (MalformedURLException e) {
		}

		String fileName = new File(url.getFile()).getName();
		int questionMarkPos = fileName.indexOf('?');
		
		if (questionMarkPos > 0) {
			return fileName.substring(0, questionMarkPos);
		}
		return fileName;
	}

}
