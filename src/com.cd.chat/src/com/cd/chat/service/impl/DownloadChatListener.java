package com.cd.chat.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Packet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.cd.chat.specification.DownloadRequestSpecification;
import com.cd.message.DownloadFile;


@Service
public class DownloadChatListener implements PacketListener {
	protected final static Log log = LogFactory.getLog(DownloadChatListener.class);
	private static final String DOWNLOAD_PREFIX = "^d\\s";
	
	@Autowired
	private JmsTemplate simpleDownloadTemplate;
	private DownloadRequestSpecification spec;
	
	public DownloadChatListener() {
		spec = new DownloadRequestSpecification();
	}
	
	@Override
	public void processPacket(Packet p) {
		Message msg = (Message) p;
		log.info("incomming message");
		if (spec.isSatisfiedBy(msg.getBody())) {
			DownloadFile downloadRequest = new DownloadFile(getFrom(msg.getFrom()), getUrl(msg.getBody()));
			simpleDownloadTemplate.convertAndSend(downloadRequest);
		}
	}

	private String getUrl(String msg) {
		return msg.trim().replaceAll(DOWNLOAD_PREFIX, "");
	}
	
	private String getFrom(String from) {
		String from1 = from.trim().replaceAll("from=", "");
		String formUser = from1.substring(0, from1.indexOf('/'));
		
		return formUser;
	}
	
	
}
