package com.cd.downloader.service.impl;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.support.JmsUtils;
import org.springframework.stereotype.Service;

import com.cd.downloader.service.IDownloadManager;
import com.cd.message.DownloadFile;

@Service
public class TorrentDownloadHandler implements MessageListener {
	protected final static Log log = LogFactory.getLog(TorrentDownloadHandler.class);
	
	@Autowired
	IDownloadManager downloadManager;
	
	@Override
	public void onMessage(Message message) {
		ObjectMessage mapMessage = (ObjectMessage) message;
		try {
			DownloadFile downloadRequest = (DownloadFile) mapMessage.getObject();
			downloadManager.queueTorrentDownloadRequest(downloadRequest);
		} catch (JMSException e) {
			throw JmsUtils.convertJmsAccessException(e);
		}
	}

}
