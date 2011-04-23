package chat.downloader.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Packet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import chat.downloader.message.DownlodRequest;

@Service
public class DownloadPacketListener implements PacketListener {
	protected final static Log log = LogFactory.getLog(DownloadPacketListener.class);
	private final static String DOWNLOAD_HTTP = "download http://";
	
	@Autowired
	JmsTemplate simpleDownloadTemplate;
	
	@Override
	public void processPacket(Packet p) {
		Message msg = (Message) p;
		
		if (isDownloadMessage(msg.getBody())) {
			DownlodRequest downloadRequest = new DownlodRequest(getFrom(msg.getFrom()), getUrl(msg.getBody()));
			simpleDownloadTemplate.convertAndSend(downloadRequest);
		}
	}

	private String getUrl(String msg) {
		return msg.trim().replaceAll(DOWNLOAD_HTTP, "http://");
	}
	
	private String getFrom(String from) {
		String from1 = from.trim().replaceAll("from=", "");
		String formUser = from1.substring(0, from1.indexOf('/'));
		
		return formUser;
	}
	
	private boolean isDownloadMessage(String message) {
		return message.contains(DOWNLOAD_HTTP);
	}
	
	
	
}
