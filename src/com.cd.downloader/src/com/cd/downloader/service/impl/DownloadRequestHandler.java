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
import com.cd.message.DownlodRequest;

@Service
public class DownloadRequestHandler implements MessageListener {
	protected final static Log log = LogFactory.getLog(DownloadRequestHandler.class);
	
	@Autowired
	IDownloadManager downloadManager;
	
	@Override
	public void onMessage(Message message) {
		ObjectMessage mapMessage = (ObjectMessage) message;
		try {
			DownlodRequest downloadRequest = (DownlodRequest) mapMessage.getObject();
			downloadManager.queueDownloadRequest(downloadRequest);
		} catch (JMSException e) {
			throw JmsUtils.convertJmsAccessException(e);
		}
	}

}
