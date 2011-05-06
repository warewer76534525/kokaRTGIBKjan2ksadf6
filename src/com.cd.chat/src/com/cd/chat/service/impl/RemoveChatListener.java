package com.cd.chat.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Packet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.cd.chat.specification.RemoveRequestSpecification;
import com.cd.message.RemoveDownloaded;


@Service
public class RemoveChatListener implements PacketListener {
	private static final String REMOVE_PREFIX = "^r\\s";

	protected final static Log log = LogFactory.getLog(RemoveChatListener.class);
	
	RemoveRequestSpecification spec;
	
	@Autowired
	JmsTemplate removeDownloadedTemplate;
	
	public RemoveChatListener() {
		spec = new RemoveRequestSpecification();
	}
	
	@Override
	public void processPacket(Packet p) {
		Message msg = (Message) p;
		if (spec.isSatisfiedBy(msg.getBody())) {
			RemoveDownloaded removeDownloaded = new RemoveDownloaded(getFrom(msg.getFrom()), getFileName(msg.getBody()));
			removeDownloadedTemplate.convertAndSend(removeDownloaded);
		}
	}

	private String getFileName(String msg) {
		return msg.trim().replaceAll(REMOVE_PREFIX, "");
	}
	
	private String getFrom(String from) {
		String from1 = from.trim().replaceAll("from=", "");
		String formUser = from1.substring(0, from1.indexOf('/'));
		
		return formUser;
	}
		
}
