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

import com.cd.message.FileRemoved;


@Service
public class FileRemovedHandler implements MessageListener {
	protected final static Log log = LogFactory.getLog(FileRemovedHandler.class);
	
	@Autowired
	private ChatManager chatManager;
	
	@Override
	public void onMessage(Message message) {
		ObjectMessage mapMessage = (ObjectMessage) message;
		
		try {
			FileRemoved fileRemoved = (FileRemoved) mapMessage.getObject();
			
			String msg = String.format("[%s] %s removed ", fileRemoved.getTime().toString(), fileRemoved.getFileName());
			chatManager.sendMessage(fileRemoved.getFrom(), msg);
		} catch (JMSException e) {
			throw JmsUtils.convertJmsAccessException(e);
		}
	}

	
}
