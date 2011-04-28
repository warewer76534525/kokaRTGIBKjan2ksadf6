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

import com.cd.message.DownloadCompleted;


@Service
public class DownloadCompletedHandler implements MessageListener {
	protected final static Log log = LogFactory.getLog(DownloadCompletedHandler.class);
	
	/**
	 * @uml.property  name="chatManager"
	 * @uml.associationEnd  
	 */
	@Autowired
	private ChatManager chatManager;
	
	@Override
	public void onMessage(Message message) {
		ObjectMessage mapMessage = (ObjectMessage) message;
		
		try {
			DownloadCompleted downloadCompleted = (DownloadCompleted) mapMessage.getObject();
			
			String msg = String.format("[%s] Completed %s \n download url: %s", downloadCompleted.getTime().toString(), downloadCompleted.getUrl(), downloadCompleted.getDownloadUrl());
			chatManager.sendMessage(downloadCompleted.getFrom(), msg);
		} catch (JMSException e) {
			throw JmsUtils.convertJmsAccessException(e);
		}
	}

	
}
