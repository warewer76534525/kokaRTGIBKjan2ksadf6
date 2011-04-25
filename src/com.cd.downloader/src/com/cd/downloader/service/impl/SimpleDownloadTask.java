package com.cd.downloader.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jms.core.JmsTemplate;

import com.cd.downloader.service.IFileDownloader;
import com.cd.message.DownloadError;
import com.cd.message.DownloadCompleted;
import com.cd.message.DownlodaRequest;


public class SimpleDownloadTask implements Runnable {
	protected final static Log log = LogFactory.getLog(SimpleDownloadTask.class);
	
	private IFileDownloader _simpleFileDownloader;
	private DownlodaRequest _downloadRequest;
	private JmsTemplate _downloadCompledTemplate;
	private JmsTemplate _downloadErrorTemplate;

	public SimpleDownloadTask(JmsTemplate downloadCompledTemplate, JmsTemplate downloadErrorTemplate, IFileDownloader simpleFileDownloader, DownlodaRequest downloadRequest) {
		_simpleFileDownloader = simpleFileDownloader;
		_downloadRequest = downloadRequest;
		_downloadCompledTemplate = downloadCompledTemplate;
		_downloadErrorTemplate = downloadErrorTemplate;
	}

	public void run() {
		try {
			log.info("Download begin " + _downloadRequest.getFileName());
			_simpleFileDownloader.download(_downloadRequest);
			DownloadCompleted downloadCompleted = new DownloadCompleted(_downloadRequest.getFrom(), _downloadRequest.getUrl());
			_downloadCompledTemplate.convertAndSend(downloadCompleted);	
			
			log.info("Donwload completed " + _downloadRequest.getFileName());
		} catch (Exception e) {
			DownloadError downloadError = new DownloadError(_downloadRequest.getFrom(), _downloadRequest.getUrl(), e.getMessage());
			_downloadErrorTemplate.convertAndSend(downloadError);	
		}
	}

}
