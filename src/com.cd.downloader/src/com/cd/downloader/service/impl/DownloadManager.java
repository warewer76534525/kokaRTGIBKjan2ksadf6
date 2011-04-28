package com.cd.downloader.service.impl;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.task.TaskExecutor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.cd.downloader.service.IDownloadManager;
import com.cd.message.DownlodRequest;
import com.cd.message.ErrorOccurred;
import com.cd.message.FileRemoved;
import com.cd.message.RemoveDownloaded;


@Service
public class DownloadManager implements IDownloadManager {
	protected final static Log log = LogFactory.getLog(DownloadManager.class);
	
	@Value("${downloaded.dir}")
	private String downloadedDir;
	
	@Value("${download.url}")
	private String downloadUrl;
	
	@Autowired
	private JmsTemplate downloadCompledTemplate;
	@Autowired
	private JmsTemplate errorOccurredTemplate;
	@Autowired
	private JmsTemplate fileRemovedTemplate;
	
	@Autowired
	private TaskExecutor taskExecutor;
	
	@Override
	public void queueDownloadRequest(DownlodRequest downloadRequest) {
		taskExecutor.execute(new SimpleDownloadTask(downloadCompledTemplate, errorOccurredTemplate, new SimpleFileDownloader(downloadedDir), downloadRequest, downloadUrl));
	}

	@Override
	public void removeDownloadedFile(RemoveDownloaded removeDownloaded) {
		try {
			FileUtils.forceDelete(new File(downloadedDir, removeDownloaded.getFileName()));
			FileRemoved fileRemoved = new FileRemoved(removeDownloaded.getFrom(), removeDownloaded.getFileName());
			fileRemovedTemplate.convertAndSend(fileRemoved);
			log.info(removeDownloaded.getFileName() + " removed");
		} catch (Exception e) {
			ErrorOccurred downloadError = new ErrorOccurred(removeDownloaded.getFrom(), removeDownloaded.toString(), e.getMessage());
			errorOccurredTemplate.convertAndSend(downloadError);
		}
	}

}
