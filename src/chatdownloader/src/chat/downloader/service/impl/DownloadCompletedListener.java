package chat.downloader.service.impl;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.support.JmsUtils;
import org.springframework.stereotype.Service;

import chat.downloader.message.DownlodCompleted;

@Service
public class DownloadCompletedListener implements MessageListener {
	protected final static Log log = LogFactory.getLog(DownloadCompletedListener.class);
	
	@Autowired
	private ChatManager chatManager;
	
	@Override
	public void onMessage(Message message) {
		ObjectMessage mapMessage = (ObjectMessage) message;
		
		try {
			DownlodCompleted downloadCompleted = (DownlodCompleted) mapMessage.getObject();
			
			String msg = String.format("[%s] Completed %s", downloadCompleted.getTime().toString(), downloadCompleted.getUrl());
			chatManager.sendMessage(downloadCompleted.getFrom(), msg);
		} catch (JMSException e) {
			throw JmsUtils.convertJmsAccessException(e);
		}
	}

	
}
