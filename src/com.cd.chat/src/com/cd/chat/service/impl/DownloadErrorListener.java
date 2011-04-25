package com.cd.chat.service.impl;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.support.JmsUtils;
import org.springframework.stereotype.Service;

import com.cd.message.DownloadError;


@Service
public class DownloadErrorListener implements MessageListener {
	protected final static Log log = LogFactory.getLog(DownloadErrorListener.class);
	
	@Autowired
	private ChatManager chatManager;
	
	@Override
	public void onMessage(Message message) {
		ObjectMessage mapMessage = (ObjectMessage) message;
		
		try {
			DownloadError downloadError = (DownloadError) mapMessage.getObject();
			
			String msg = String.format("[%s] %s ", downloadError.getTime().toString(), downloadError.getMessage());
			chatManager.sendMessage(downloadError.getFrom(), msg);
		} catch (JMSException e) {
			throw JmsUtils.convertJmsAccessException(e);
		}
	}

	
}
