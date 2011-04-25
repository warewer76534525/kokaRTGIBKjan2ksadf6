package com.cd.downloader.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.task.TaskExecutor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.cd.downloader.service.IDownloadManager;
import com.cd.message.DownlodaRequest;


@Service
public class DownloadManager implements IDownloadManager {
	
	@Value("${downloaded.dir}")
	private String downloadedDir;
	
	@Autowired
	private JmsTemplate downloadCompledTemplate;
	@Autowired
	private JmsTemplate downloadErrorTemplate;
	
	@Autowired
	private TaskExecutor taskExecutor;
	
	@Override
	public void queueDownloadRequest(DownlodaRequest downloadRequest) {
		taskExecutor.execute(new SimpleDownloadTask(downloadCompledTemplate, downloadErrorTemplate, new SimpleFileDownloader(downloadedDir), downloadRequest));
	}

}
