package com.cd.downloader.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jms.core.JmsTemplate;

import com.cd.downloader.service.IFileDownloader;
import com.cd.message.ErrorOccurred;
import com.cd.message.FileDownloaded;
import com.cd.message.DownloadFile;


public class SimpleDownloadTask implements Runnable {
	protected final static Log log = LogFactory.getLog(SimpleDownloadTask.class);
	
	private IFileDownloader _simpleFileDownloader;
	private DownloadFile _downloadFile;
	private JmsTemplate _downloadCompledTemplate;
	private JmsTemplate _errorOccurredTemplate;
	private String _downloadUrl;

	public SimpleDownloadTask(JmsTemplate downloadCompledTemplate, JmsTemplate errorOccurredTemplate, IFileDownloader simpleFileDownloader, DownloadFile downloadFile, String downloadUrl) {
		_simpleFileDownloader = simpleFileDownloader;
		_downloadFile = downloadFile;
		_downloadCompledTemplate = downloadCompledTemplate;
		_errorOccurredTemplate = errorOccurredTemplate;
		_downloadUrl = downloadUrl;
	}

	public void run() {
		try {
			log.info("Download begin " + _downloadFile.getFileName());
			_simpleFileDownloader.download(_downloadFile);
			
			String  downloadUrl =  _downloadUrl + _downloadFile.getFileName(); 
			FileDownloaded downloadCompleted = new FileDownloaded(_downloadFile.getFrom(), _downloadFile.getUrl(), downloadUrl);
			_downloadCompledTemplate.convertAndSend(downloadCompleted);	
			
			log.info("Donwload completed " + _downloadFile.getFileName());
		} catch (Exception e) {
			ErrorOccurred downloadError = new ErrorOccurred(_downloadFile.getFrom(), _downloadFile.getUrl(), e.getMessage());
			_errorOccurredTemplate.convertAndSend(downloadError);
			log.error(e.getMessage(), e);
		}
	}

}
