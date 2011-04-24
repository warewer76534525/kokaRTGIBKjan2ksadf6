package com.cd.downloader.service.impl;

import java.io.IOException;
import java.net.MalformedURLException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jms.core.JmsTemplate;

import com.cd.downloader.service.IFileDownloader;
import com.cd.message.DownlodCompleted;
import com.cd.message.DownlodRequest;


public class SimpleDownloadTask implements Runnable {
	protected final static Log log = LogFactory.getLog(SimpleDownloadTask.class);
	
	private IFileDownloader _simpleFileDownloader;
	private DownlodRequest _downloadRequest;
	private JmsTemplate _downloadCompledTemplate;

	public SimpleDownloadTask(JmsTemplate downloadCompledTemplate, IFileDownloader simpleFileDownloader, DownlodRequest downloadRequest) {
		_simpleFileDownloader = simpleFileDownloader;
		_downloadRequest = downloadRequest;
		_downloadCompledTemplate = downloadCompledTemplate;
	}

	public void run() {
		try {
			log.info("Download begin " + _downloadRequest.getFileName());
			_simpleFileDownloader.download(_downloadRequest);
			DownlodCompleted downloadCompleted = new DownlodCompleted(_downloadRequest.getFrom(), _downloadRequest.getUrl());
			_downloadCompledTemplate.convertAndSend(downloadCompleted);	
			
			log.info("Donwload completed " + _downloadRequest.getFileName());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
