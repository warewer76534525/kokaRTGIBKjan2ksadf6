package com.cd.chat.service.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Packet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.cd.message.RemoveDownloaded;


@Service
public class RemoveChatListener implements PacketListener {
	protected final static Log log = LogFactory.getLog(RemoveChatListener.class);
	private Pattern pattern;
	private Matcher matcher;
	private static final String REMOVE_PATTERN = "^r\\s[_a-zA-Z0-9\\-\\.]*";
	
	
	@Autowired
	JmsTemplate removeDownloadedTemplate;
	
	public RemoveChatListener() {
		pattern = Pattern.compile(REMOVE_PATTERN);
	}
	
	@Override
	public void processPacket(Packet p) {
		Message msg = (Message) p;
		if (isRemoveFileMessage(msg.getBody())) {
			RemoveDownloaded removeDownloaded = new RemoveDownloaded(getFrom(msg.getFrom()), getFileName(msg.getBody()));
			removeDownloadedTemplate.convertAndSend(removeDownloaded);
		}
	}

	private String getFileName(String msg) {
		return msg.trim().replaceAll("^r\\s", "");
	}
	
	private String getFrom(String from) {
		String from1 = from.trim().replaceAll("from=", "");
		String formUser = from1.substring(0, from1.indexOf('/'));
		
		return formUser;
	}
	
	private boolean isRemoveFileMessage(String message) {
		matcher = pattern.matcher(message);
		return matcher.matches();
	}
	
	
	
}
