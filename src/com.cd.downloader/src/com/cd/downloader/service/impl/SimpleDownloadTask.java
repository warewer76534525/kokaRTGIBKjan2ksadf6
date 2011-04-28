package com.cd.downloader.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jms.core.JmsTemplate;

import com.cd.downloader.service.IFileDownloader;
import com.cd.message.ErrorOccurred;
import com.cd.message.DownloadCompleted;
import com.cd.message.DownlodRequest;


public class SimpleDownloadTask implements Runnable {
	protected final static Log log = LogFactory.getLog(SimpleDownloadTask.class);
	
	private IFileDownloader _simpleFileDownloader;
	private DownlodRequest _downloadRequest;
	private JmsTemplate _downloadCompledTemplate;
	private JmsTemplate _errorOccurredTemplate;
	private String _downloadUrl;

	public SimpleDownloadTask(JmsTemplate downloadCompledTemplate, JmsTemplate errorOccurredTemplate, IFileDownloader simpleFileDownloader, DownlodRequest downloadRequest, String downloadUrl) {
		_simpleFileDownloader = simpleFileDownloader;
		_downloadRequest = downloadRequest;
		_downloadCompledTemplate = downloadCompledTemplate;
		_errorOccurredTemplate = errorOccurredTemplate;
		_downloadUrl = downloadUrl;
	}

	public void run() {
		try {
			log.info("Download begin " + _downloadRequest.getFileName());
			_simpleFileDownloader.download(_downloadRequest);
			
			String  downloadUrl =  _downloadUrl + _downloadRequest.getFileName(); 
			DownloadCompleted downloadCompleted = new DownloadCompleted(_downloadRequest.getFrom(), _downloadRequest.getUrl(), downloadUrl);
			_downloadCompledTemplate.convertAndSend(downloadCompleted);	
			
			log.info("Donwload completed " + _downloadRequest.getFileName());
		} catch (Exception e) {
			ErrorOccurred downloadError = new ErrorOccurred(_downloadRequest.getFrom(), _downloadRequest.getUrl(), e.getMessage());
			_errorOccurredTemplate.convertAndSend(downloadError);	
		}
	}

}
