package chatdownloader.service.impl;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.filter.AndFilter;
import org.jivesoftware.smack.filter.PacketFilter;
import org.jivesoftware.smack.filter.PacketTypeFilter;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Presence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ChatManager {
	
	protected final static Log log = LogFactory.getLog(ChatManager.class);
	
	@Autowired
	@Qualifier("xmppConnection")
	private XMPPConnection connection;
	
	@Autowired
	private PacketListener downloadPacketListener;

	@Value("${xmpp.user}")
	private String user;

	@Value("${xmpp.password}")
	private String password;
	
	

	@PostConstruct
	public void startUp() throws XMPPException {
		
		Thread chatManagerThread = new Thread() {
			@Override
			public void run() {
				try {
					connection.connect();
					connection.login(user, password);
					log.info("connected ...");
					
					Presence presence = new Presence(Presence.Type.available);
					connection.sendPacket(presence);
					
					PacketFilter filter = new AndFilter(new PacketTypeFilter(Message.class));
					connection.addPacketListener(downloadPacketListener, filter);
					log.info("listening message ...");
					 
					System.in.read();
					 
					// set presence status to unavailable
					presence = new Presence(Presence.Type.unavailable);
					connection.sendPacket(presence);
				} catch (XMPPException e) {
					e.printStackTrace();
				} catch (IOException e) {
					
					e.printStackTrace();
				}
			}
		};
		
		chatManagerThread.setName("Chat Downloader [" + user + "]");
		chatManagerThread.setDaemon(false);
		chatManagerThread.start();
		
	}

	@PreDestroy
	public void shutdown() throws XMPPException {
		Presence presence = new Presence(Presence.Type.unavailable);
		connection.sendPacket(presence);

		connection.disconnect();
		log.info("shutdown");
	}
	
	public void sendMessage(String to, String message) {
		Message msg = new Message(to, Message.Type.chat);
		msg.setBody(message);
		connection.sendPacket(msg);
	}
}
