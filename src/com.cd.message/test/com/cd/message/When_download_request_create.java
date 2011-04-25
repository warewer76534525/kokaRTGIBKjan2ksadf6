package com.cd.message;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;


public class When_download_request_create {
	private DownlodaRequest _downloadRequest;

	
	@Before
	public void setUp() {
		String downloadUrl = "http://av.vimeo.com/05745/532/33495217.mp4?token=1303641426_9fc83833298b4feeab8c86fc7ff52678";
		_downloadRequest = new DownlodaRequest("adisembiring", downloadUrl);
	}
	
	@Test
	public void should_able_to_get_file_name() {
		String expectedFileName = "33495217.mp4";
		String fileName = _downloadRequest.getFileName();
		
		System.out.println(fileName);
		Assert.assertEquals(fileName, expectedFileName);
	}
}	
