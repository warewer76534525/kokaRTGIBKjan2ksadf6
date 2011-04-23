package chat.downloader.service.impl;

import java.io.IOException;
import java.net.MalformedURLException;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.JmsUtils;
import org.springframework.stereotype.Service;

import chat.downloader.message.DownlodCompleted;
import chat.downloader.message.DownlodRequest;

@Service
public class SimpleFileDownloadListener implements MessageListener {
	protected final static Log log = LogFactory.getLog(SimpleFileDownloadListener.class);
	
	@Autowired
	@Qualifier("downloadCompledTemplate")
	private JmsTemplate downloadCompledTemplate;
	
	@Autowired SimpleFileDownloader simpleFileDownloader;
	
	@Override
	public void onMessage(Message message) {
		ObjectMessage mapMessage = (ObjectMessage) message;
		
		try {
			DownlodRequest downloadRequest = (DownlodRequest) mapMessage.getObject();
			log.info(downloadRequest);
			
			simpleFileDownloader.download(downloadRequest.getUrl());
			
			DownlodCompleted downloadCompleted = new DownlodCompleted(downloadRequest.getFrom(), downloadRequest.getUrl());
			log.info(downloadRequest);
			downloadCompledTemplate.convertAndSend(downloadCompleted);
			
		} catch (JMSException e) {
			throw JmsUtils.convertJmsAccessException(e);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
